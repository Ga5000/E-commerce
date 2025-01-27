package com.ga5000.api.ecommerce.service.image;

import com.ga5000.api.ecommerce.domain.product.Product;
import com.ga5000.api.ecommerce.domain.product.image.Image;
import com.ga5000.api.ecommerce.dto.image.ImageResponseDto;
import com.ga5000.api.ecommerce.exception.AwsServiceException;
import com.ga5000.api.ecommerce.exception.FileException;
import com.ga5000.api.ecommerce.repository.image.ImageRepository;
import com.ga5000.api.ecommerce.service.s3.S3Service;
import com.ga5000.api.ecommerce.utils.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.ga5000.api.ecommerce.utils.ExceptionMessage.*;
import static com.ga5000.api.ecommerce.utils.RepositoryUtil.getById;

@Service
public class ImageService implements IImageService{
    private final ImageRepository imageRepository;
    private final S3Service s3Service;

    public ImageService(ImageRepository imageRepository, S3Service s3Service) {
        this.imageRepository = imageRepository;
        this.s3Service = s3Service;
    }

    @Override
    public void addImageToProduct(MultipartFile file, Product product) {
       var newImage = new Image(product);
       try{
           newImage.setUrl(s3Service.uploadImage(file));
       } catch (IOException e) {
           throw new FileException(FILE_ERROR);
       }catch (AwsServiceException ex){
           throw new AwsServiceException(S3_SERVICE_ERROR);
       }
       saveImage(newImage);
    }

    @Override
    public void deleteImage(UUID imageId) {
        Image image = getById(imageId, imageRepository, IMAGE_NOT_FOUND);
        imageRepository.delete(image);
    }

    @Override
    public List<ImageResponseDto> getAllProductImages(Product product) {
        return imageRepository.findAllByProduct(product)
                .stream().map(Mapper::toImageResponseDto).toList();
    }

    private void saveImage(Image image){
        imageRepository.save(image);
    }
}
