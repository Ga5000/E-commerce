package com.ga5000.api.media.service.minIO;

public interface MinIOService {
    String uploadFile(String filename, String contentType, byte[] data);
    void deleteFile(String filename);
}
