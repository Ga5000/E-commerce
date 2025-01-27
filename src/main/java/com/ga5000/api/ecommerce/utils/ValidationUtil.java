package com.ga5000.api.ecommerce.utils;

import com.ga5000.api.ecommerce.domain.user.client.Client;
import jakarta.persistence.EntityExistsException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.function.Function;

import static com.ga5000.api.ecommerce.utils.ExceptionMessage.ACCESS_DENIED;

public class ValidationUtil {
    private ValidationUtil() {
    }

    public static <T, R> void checkEntityAvailability(
            R value,
            JpaRepository<T, ?> repository,
            Function<R, T> findFunction,
            String existsMessage
    ) throws EntityExistsException {
        T entity = findFunction.apply(value);
        if (entity != null) {
            throw new EntityExistsException(existsMessage);
        }
    }

    public static boolean isSameValue(String existingValue, String newValue) {
        return existingValue.equals(newValue);
    }

    public static <S> void validateOwnership(Client client, S entity, ClientExtractor<S> extractor) throws SecurityException {
        Client owner = extractor.getClient(entity);
        if (!owner.getUserId().equals(client.getUserId())) {
            throw new SecurityException(ACCESS_DENIED);
        }
    }

    @FunctionalInterface
    public interface ClientExtractor<S> {
        Client getClient(S entity);
    }
}
