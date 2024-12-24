package com.ga5000.api.ecommerce.service.product;

import com.ga5000.api.ecommerce.aws.s3bucket.Bucket;
import com.ga5000.api.ecommerce.domain.category.Category;
import com.ga5000.api.ecommerce.domain.product.Product;
import com.ga5000.api.ecommerce.dto.ProductRequestDto;
import com.ga5000.api.ecommerce.dto.ProductResponseDto;
import com.ga5000.api.ecommerce.repository.category.CategoryRepository;
import com.ga5000.api.ecommerce.repository.product.ProductRepository;
import com.ga5000.api.ecommerce.utils.exceptions.Message;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final Bucket awsBucket;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, Bucket awsBucket) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.awsBucket = awsBucket;
    }

    @Override
    public void createProduct(ProductRequestDto productRequestDto) throws EntityExistsException {
        if (productRepository.findByNameIgnoreCase(productRequestDto.name()).isPresent()) {
            throw new EntityExistsException(Message.ProductMessage.PRODUCT_EXISTS.name());
        }
        var newProduct = new Product();
        addCategoriesToProduct(newProduct, productRequestDto.categoriesIds());
        List<String> urls = uploadFilesAndGetUrls(productRequestDto.files());
        addImagesToProduct(newProduct, urls);
        BeanUtils.copyProperties(productRequestDto, newProduct);
        productRepository.save(newProduct);
    }

    private List<String> uploadFilesAndGetUrls(List<MultipartFile> files) throws EntityExistsException {
        List<String> urls = new ArrayList<>();
        try {
            for (MultipartFile file : files) {
                awsBucket.uploadFile(file);
                String key = awsBucket.generateKey(file);
                urls.add(awsBucket.getFileUrl(key).toString());
            }
        } catch (S3Exception | IOException e) {
            throw new EntityExistsException(Message.ProductMessage.PRODUCT_EXISTS.name());
        }
        return urls;
    }

    @Override
    public void updateProduct(UUID productId, ProductRequestDto productRequestDto) throws EntityExistsException, EntityNotFoundException {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(Message.ProductMessage.PRODUCT_NOT_FOUND.name()));

        if (productRepository.findByNameIgnoreCase(productRequestDto.name()).isPresent()) {
            if (!existingProduct.getName().equalsIgnoreCase(productRequestDto.name())) {
                throw new EntityExistsException(Message.ProductMessage.PRODUCT_EXISTS.name());
            }
        }

        existingProduct.getCategories().clear();
        addCategoriesToProduct(existingProduct, productRequestDto.categoriesIds());
        BeanUtils.copyProperties(productRequestDto, existingProduct);
        productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(UUID productId) throws EntityNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(Message.ProductMessage.PRODUCT_NOT_FOUND.name()));

        productRepository.delete(product);
    }

    @Override
    public Page<ProductResponseDto> getProducts() {
        return null;
    }

    private void addCategoriesToProduct(Product product, List<UUID> categoriesIds) throws EntityNotFoundException {
        for (UUID categoryId : categoriesIds) {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new EntityNotFoundException(Message.CategoryMessage.CATEGORY_NOT_FOUND.name()));
            product.addCategory(category);
        }
    }

    private void addImagesToProduct(Product product, List<String> urls) {
        for (String url : urls) {
            product.getImages().add(url);
        }
    }
}