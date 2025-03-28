package com.ga5000.api.ecommerce.utils;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.function.Supplier;

public class RepositoryUtils {
    private RepositoryUtils() {}

    public static <T> T findByIdOrThrow(UUID id, JpaRepository<T, UUID> repository, Supplier<? extends RuntimeException> exceptionSupplier) {
        return repository.findById(id).orElseThrow(exceptionSupplier);
    }
    public static void existsByIdOrThrow(UUID id, JpaRepository<?, UUID> repository, Supplier<? extends RuntimeException> exceptionSupplier) {
        if (!repository.existsById(id)) {
            throw exceptionSupplier.get();
        }
    }
}
