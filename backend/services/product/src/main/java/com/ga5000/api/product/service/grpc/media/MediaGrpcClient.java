package com.ga5000.api.product.service.grpc.media;

import com.ga5000.api.media.MediaServiceGrpc;
import com.ga5000.api.media.UploadImagesRequest;
import com.ga5000.api.media.UploadResponse;
import com.google.protobuf.ByteString;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class MediaGrpcClient {
    @GrpcClient("media-service")
    private MediaServiceGrpc.MediaServiceBlockingStub mediaStub;

    public UploadResponse uploadImages(List<MultipartFile> files) throws IOException {
        UploadImagesRequest.Builder requestBuilder = UploadImagesRequest.newBuilder();
        for (MultipartFile file : files) {
            requestBuilder.addFiles(
                    com.ga5000.api.media.MultipartFile.newBuilder()
                            .setFilename(file.getOriginalFilename())
                            .setContentType(file.getContentType())
                            .setData(ByteString.copyFrom(file.getBytes())) // Convert to ByteString
                            .build()
            );
        }
        return mediaStub.uploadImages(requestBuilder.build());
    }

    public MediaServiceGrpc.MediaServiceBlockingStub getStub() {
        return mediaStub;
    }
}
