package com.ga5000.api.ecommerce.domain.repository.image;

import com.ga5000.api.ecommerce.domain.model.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {
}
