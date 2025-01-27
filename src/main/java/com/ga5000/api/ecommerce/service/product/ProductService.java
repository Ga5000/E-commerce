package com.ga5000.api.ecommerce.service.product;

import com.ga5000.api.ecommerce.domain.product.Product;
import com.ga5000.api.ecommerce.domain.product.ProductSearchDto;
import com.ga5000.api.ecommerce.domain.product.image.Image;
import com.ga5000.api.ecommerce.dto.page.PageRequestDto;
import com.ga5000.api.ecommerce.dto.product.ProductRequestDto;
import com.ga5000.api.ecommerce.dto.product.ProductResponseDto;
import com.ga5000.api.ecommerce.dto.product.SearchParams;
import com.ga5000.api.ecommerce.repository.product.ProductRepository;
import com.ga5000.api.ecommerce.service.category.CategoryService;
import com.ga5000.api.ecommerce.service.image.IImageService;
import com.ga5000.api.ecommerce.utils.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

import static com.ga5000.api.ecommerce.utils.ExceptionMessage.PRODUCT_EXISTS;
import static com.ga5000.api.ecommerce.utils.ExceptionMessage.PRODUCT_NOT_FOUND;
import static com.ga5000.api.ecommerce.utils.RepositoryUtil.getById;
import static com.ga5000.api.ecommerce.utils.RequestUtil.mapRequest;
import static com.ga5000.api.ecommerce.utils.ValidationUtil.checkEntityAvailability;
import static com.ga5000.api.ecommerce.utils.ValidationUtil.isSameValue;

public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final IImageService imageService;
    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository, IImageService imageService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.imageService = imageService;
        this.categoryService = categoryService;
    }

    @Override
    public void createProduct(ProductRequestDto productRequestDto) {
        var product = new Product();

        checkEntityAvailability(productRequestDto.name(),
                productRepository, productRepository::findByNameIgnoreCase,
                PRODUCT_EXISTS);

        mapRequest(productRequestDto, product);
        addCategories(product, productRequestDto.categoriesIds());
        addImages(product, productRequestDto.files());
        saveProduct(product);
    }

    @Override
    public void updateProduct(UUID productId, ProductRequestDto productRequestDto) {
        Product existingProduct = getById(productId, productRepository, PRODUCT_NOT_FOUND);
        if(!isSameValue(productRequestDto.name(), existingProduct.getName())) {
            checkEntityAvailability(productRequestDto.name(),
                    productRepository, productRepository::findByNameIgnoreCase,
                    PRODUCT_EXISTS);
        }

        mapRequest(productRequestDto, existingProduct);
        addCategories(existingProduct, productRequestDto.categoriesIds());
        addImages(existingProduct, productRequestDto.files());
        saveProduct(existingProduct);
    }

    @Override
    public void deleteProduct(UUID productId) {
        Product product = getById(productId, productRepository, PRODUCT_NOT_FOUND);
        productRepository.delete(product);
    }

    @Override
    public ProductResponseDto getProduct(UUID productId) {
        Product product = getById(productId, productRepository, PRODUCT_NOT_FOUND);
        return Mapper.toProductResponseDto(product);
    }

    @Override
    public Page<ProductSearchDto> searchProducts(SearchParams params, PageRequestDto pageRequest) {
        Pageable page = PageRequest.of(pageRequest.page(), pageRequest.size(), pageRequest.direction());
        Page<Product> products = productRepository.searchProducts(params, page);

        return products.map(Mapper::toProductSearchDto);

    }


    private void saveProduct(Product product){
        productRepository.save(product);
    }

    private void addCategories(Product product, List<UUID> categoryIds){
        for(var categoryId : categoryIds){
            categoryService.addCategoryToProduct(categoryId, product);
        }
    }

    private void addImages(Product product, List<MultipartFile> files){
        for(var file : files){
            imageService.addImageToProduct(file, product);
        }
    }
}
