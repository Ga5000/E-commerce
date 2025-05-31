package com.ga5000.api.media.service.media.impl;

import com.ga5000.api.media.*;
import com.ga5000.api.media.service.media.MediaService;
import com.ga5000.api.media.service.minIO.MinIOService;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@GrpcService
@RequiredArgsConstructor
public class MediaServiceImpl extends MediaServiceGrpc.MediaServiceImplBase implements MediaService {

    private final MinIOService minIOService;

    @Override
    public void uploadImage(UploadImagesRequest request, StreamObserver<UploadResponse> responseObserver) {
        List<String> urls = new ArrayList<>();

        for (MultipartFile file : request.getFilesList()) {
           urls.add(uploadImage(genFileName(file.getFilename()), file));
        }

        UploadResponse response = UploadResponse.newBuilder()
                .addAllUrls(urls)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteImage(DeleteImagesRequest request, StreamObserver<Empty> responseObserver) {
        for (String filename : request.getFilenamesList()) {
            minIOService.deleteFile(filename);
        }
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    private String genFileName(String originalFilename) {
        return UUID.randomUUID() + "-" + originalFilename;
    }

    private String uploadImage(String filename, MultipartFile file){
        return minIOService.uploadFile(filename, file.getContentType(), file.getData().toByteArray());
    }
}
