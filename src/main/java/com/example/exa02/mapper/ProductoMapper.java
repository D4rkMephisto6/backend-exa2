package com.example.exa02.mapper;

import com.example.exa02.dto.ProductoDTO;
import com.example.exa02.entity.Producto;
import com.example.exa02.mapper.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductoMapper extends BaseMapper<Producto, ProductoDTO> {

    @Mapping(target = "categoria", ignore = true)
    Producto toEntity(ProductoDTO dto);

    @Mapping(source = "categoria.id", target = "categoriaId")
    ProductoDTO toDTO(Producto entity);
}
