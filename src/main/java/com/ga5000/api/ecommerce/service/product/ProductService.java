package com.ga5000.api.ecommerce.service.product;

import com.ga5000.api.ecommerce.domain.product.Product;
import com.ga5000.api.ecommerce.dto.image.ImageRequest;
import com.ga5000.api.ecommerce.dto.product.ProductRequest;
import com.ga5000.api.ecommerce.dto.product.ProductResponseInfo;
import com.ga5000.api.ecommerce.dto.product.search.SearchProductParams;
import com.ga5000.api.ecommerce.dto.product.search.SearchProductResponse;
import com.ga5000.api.ecommerce.exception.product.ProductAlreadyExistsException;
import com.ga5000.api.ecommerce.exception.product.ProductNotFoundException;
import com.ga5000.api.ecommerce.repository.product.ProductRepository;
import com.ga5000.api.ecommerce.repository.product.specification.ProductSpecification;
import com.ga5000.api.ecommerce.service.category.CategoryService;
import com.ga5000.api.ecommerce.service.image.ImageService;
import com.ga5000.api.ecommerce.utils.DtoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

import static com.ga5000.api.ecommerce.utils.RepositoryUtils.areFieldsEqual;
import static com.ga5000.api.ecommerce.utils.RepositoryUtils.findByIdOrThrow;

@Service
public class ProductService implements IProductService {
    private final ImageService imageService;
    private final CategoryService categoryService;
    private final ProductRepository productRepository;
    private final DtoMapper dtoMapper;

    public ProductService(ImageService imageService, CategoryService categoryService,
                          ProductRepository productRepository, DtoMapper dtoMapper) {
        this.imageService = imageService;
        this.categoryService = categoryService;
        this.productRepository = productRepository;
        this.dtoMapper = dtoMapper;
    }

    @Transactional
    @Override
    public void createProduct(ProductRequest productRequest, List<MultipartFile> files) {
        validateProduct(productRequest.name());
        var newProduct = new Product();
        BeanUtils.copyProperties(productRequest, newProduct);
        saveProduct(newProduct);
        associateProductWithCategory(newProduct, productRequest.categoriesIds());
        files.forEach(file -> imageService.addImageToProduct(new ImageRequest(newProduct, file)));

    }

    @Transactional
    @Override
    public void updateProduct(UUID productId, ProductRequest productRequest) {
        Product existingProduct = findByIdOrThrow(productId, productRepository, ProductNotFoundException::new);
        if (!areFieldsEqual(existingProduct.getName(), productRequest.name())) {
            validateProduct(productRequest.name());
        }
        BeanUtils.copyProperties(productRequest, existingProduct);

        productRepository.removeUnlistedCategories(productId, productRequest.categoriesIds());
        productRepository.addNewCategories(productId, productRequest.categoriesIds());
        saveProduct(existingProduct);
    }

    @Transactional
    @Override
    public void deleteProduct(UUID productId) {
        Product product = findByIdOrThrow(productId, productRepository, ProductNotFoundException::new);
        product.getImages().forEach(imageService::removeImage);
        productRepository.delete(product);
    }

    @Override
    public void updateDiscount(UUID productId, int discount) {
        Product existingProduct = findByIdOrThrow(productId, productRepository, ProductNotFoundException::new);
       existingProduct.setDiscount(discount);
        saveProduct(existingProduct);
    }

    public Page<SearchProductResponse> searchProducts(
            SearchProductParams params,
            Pageable pageable,
            Sort.Direction direction) {

        pageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(direction, "name")
        );

        Page<Product> productPage;
        if (hasSearchParams(params)) {
            Specification<Product> spec = ProductSpecification.filterByParams(params);
            productPage = productRepository.findAll(spec, pageable);
        } else {
            productPage = productRepository.findRandomProducts(pageable);
        }


        return productPage.map(product ->
                new SearchProductResponse(
                        product.getProductId(),
                        product.getName(),
                        imageService.getProductImages(product).get(0),
                        product.getPrice(),
                        product.getDiscount()
                )
        );
    }


    @Override
    public ProductResponseInfo getProductInfo(UUID productId) {
        Product product = findByIdOrThrow(productId, productRepository, ProductNotFoundException::new);
        return dtoMapper
                .toProductResponseInfo(product, categoryService.getProductCategories(product),
                                                imageService.getProductImages(product));
    }

    private void validateProduct(String name) throws ProductAlreadyExistsException {
        if (productRepository.findByNameIgnoreCase(name) != null) {
            throw new ProductAlreadyExistsException();
        }
    }

    private void saveProduct(Product product) {
        productRepository.save(product);
    }

    private boolean hasSearchParams(SearchProductParams params){
        return !(params.name() == null || params.name().isEmpty()) ||
                (params.minPrice() != null && params.minPrice() > 0) ||
                (params.maxPrice() != null && params.maxPrice() > 0) ||
                (params.categories() != null && !params.categories().isEmpty()) ||
                params.available() ||
                params.hasDiscount();

    }


    private void associateProductWithCategory(Product product, List<UUID> categoriesIds) {
        categoriesIds.forEach(categoryId ->
                        productRepository.associateProductWithCategory(product.getProductId(), categoryId));
    }




}
