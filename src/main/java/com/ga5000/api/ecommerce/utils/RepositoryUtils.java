package com.ga5000.api.ecommerce.utils;

import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public class RepositoryUtils {
    public static <T> T findByIdOrThrow(UUID id, JpaRepository<T, UUID> repository, Supplier<? extends RuntimeException> exceptionSupplier) {
        return repository.findById(id).orElseThrow(exceptionSupplier);
    }

    public static <T> boolean areFieldsEqual(T field1, T field2){
        return Objects.equals(field1, field2);
    }
}
