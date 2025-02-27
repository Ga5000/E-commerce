package com.ga5000.api.ecommerce.controller.address;

import com.ga5000.api.ecommerce.dto.address.AddressRequest;
import com.ga5000.api.ecommerce.dto.address.AddressResponse;
import com.ga5000.api.ecommerce.service.address.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createAddress(@RequestBody AddressRequest addressRequest) {
        addressService.createAddress(addressRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap("success", "Address created successfully"));
    }

    @DeleteMapping("/remove/{addressId}")
    public ResponseEntity<Map<String, String>> removeAddress(@PathVariable UUID addressId) {
        addressService.removeAddress(addressId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap("success", "Address removed successfully"));
    }

    @GetMapping
    public ResponseEntity<Set<AddressResponse>> getAddresses(){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.getAddresses());
    }
}