package com.ga5000.api.ecommerce.dto.address;

import com.ga5000.api.ecommerce.domain.address.Address;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link Address}
 */
public record AddressResponseDto(UUID addressId, @NotBlank(message = "CEP é obrigatório") String cep,
                                 @NotBlank(message = "Rua é obrigatória") String street, String complement,
                                 @NotBlank(message = "Bairro é obrigatório") String neighborhood,
                                 @NotBlank(message = "Cidade é obrigatória") String city,
                                 @NotBlank(message = "Estado é obrigatório") String state,
                                 @NotBlank(message = "País é obrigatório") String country,
                                 String formattedAddress) implements Serializable {
}