package com.ga5000.api.product.service.grpc.mapper;

import com.ga5000.api.media.MultipartFile;
import com.google.protobuf.ByteString;

import java.io.IOException;
import java.util.List;

public class GrpcMapper {
    public static List<MultipartFile> toProtoMultipartFileList(List<org.springframework.web.multipart.MultipartFile> files) {
        return files.stream()
                .map(file -> {
                    try{
                        return MultipartFile.newBuilder()
                                .setFilename(file.getOriginalFilename())
                                .setContentType(file.getContentType())
                                .setData(ByteString.copyFrom(file.getBytes()))
                                .build();
                    }catch (IOException e){
                        throw new RuntimeException(e);
                    }
                }).toList();
    }
}
