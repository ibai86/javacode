package store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderRequestDto (

    @NotNull
    Long customerId,

    @NotEmpty
    List<Long> productIds,

    @NotBlank
    String shippingAddress
) {
}
