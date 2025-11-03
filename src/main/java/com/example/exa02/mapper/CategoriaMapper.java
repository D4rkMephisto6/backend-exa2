package com.example.exa02.mapper;

import com.example.exa02.dto.CategoriaDTO;
import com.example.exa02.entity.Categoria;
import com.example.exa02.mapper.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoriaMapper extends BaseMapper<Categoria, CategoriaDTO> {
    CategoriaMapper mapper = Mappers.getMapper(CategoriaMapper.class);
}
