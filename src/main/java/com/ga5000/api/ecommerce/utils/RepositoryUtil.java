package com.ga5000.api.ecommerce.utils;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public class RepositoryUtil {
    private RepositoryUtil(){}

    public static <T, ID> T getById(ID id, JpaRepository<T, ID> repository, String notFoundMessage) throws EntityNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(notFoundMessage));
    }
}
