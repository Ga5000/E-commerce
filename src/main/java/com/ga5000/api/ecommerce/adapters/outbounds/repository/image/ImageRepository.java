package com.ga5000.api.ecommerce.adapters.outbounds.repository.image;

import com.ga5000.api.ecommerce.domain.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {
}
