package store.dto;

public record ProductRequestDto(
        Long id,
        int quantityInOrder
) {
}
