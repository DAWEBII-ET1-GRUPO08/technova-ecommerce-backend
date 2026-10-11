package com.ecommerce_backend.inventory_service.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record ConsumeStockRequest(
        @NotEmpty
        List<@Valid StockItemRequest> items
) {
}
