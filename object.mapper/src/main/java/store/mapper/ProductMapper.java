package store.mapper;

import store.dto.ProductDto;
import store.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductDto dto);

    ProductDto toDto(Product product);

    List<ProductDto> toDtoList(List<Product> productsDto);
}
