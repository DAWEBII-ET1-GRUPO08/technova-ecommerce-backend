package com.ecommerce_backend.order_service.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateOrderRequest(
        @NotBlank
        String recipientName,

        @NotBlank
        String phone,

        @NotBlank
        String address,

        @NotBlank
        String district,

        @NotBlank
        String city,

        String reference
) {
}
