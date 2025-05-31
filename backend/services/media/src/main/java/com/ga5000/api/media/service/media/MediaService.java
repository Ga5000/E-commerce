package com.ga5000.api.media.service.media;

import com.ga5000.api.media.DeleteImagesRequest;
import com.ga5000.api.media.UploadImagesRequest;
import com.ga5000.api.media.UploadResponse;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;

public interface MediaService {
    void uploadImage(UploadImagesRequest request, StreamObserver<UploadResponse> responseObserver);
    void deleteImage(DeleteImagesRequest request, StreamObserver<Empty> responseObserver);
}
