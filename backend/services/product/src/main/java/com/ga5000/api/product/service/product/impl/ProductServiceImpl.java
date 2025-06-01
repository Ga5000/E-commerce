package com.ga5000.api.product.service.product.impl;

import com.ga5000.api.media.UploadImagesRequest;
import com.ga5000.api.media.UploadResponse;
import com.ga5000.api.product.domain.model.product.Product;
import com.ga5000.api.product.domain.repository.product.ProductRepository;
import com.ga5000.api.product.dto.request.product.ProductRequest;
import com.ga5000.api.product.service.grpc.mapper.GrpcMapper;
import com.ga5000.api.product.service.grpc.media.MediaGrpcClient;
import com.ga5000.api.product.service.product.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepo;
    private final MediaGrpcClient mediaGrpcClient;


    @Transactional
    @Override
    public void createProduct(ProductRequest request, List<MultipartFile> files) {
        validateProductName(request.name());
        List<String> imageUrls = getUploadedImageUrls(uploadImages(files)); // Upload images and get URLs

    }

    private void validateProductName(String name){
        if (productRepo.existsByNameIgnoreCase(name)) throw new RuntimeException();
    }

    private List<String> getUploadedImageUrls(UploadResponse response) {
        return response.getUrlsList();
    }

    private UploadResponse uploadImages(List<MultipartFile> files) {
        UploadImagesRequest uploadImagesRequest = UploadImagesRequest.newBuilder()
                .addAllFiles(GrpcMapper.toProtoMultipartFileList(files))
                .build();

        return mediaGrpcClient.getStub().uploadImages(uploadImagesRequest);
    }

    private void saveProduct(Product product) {
        productRepo.save(product);
    }
}
