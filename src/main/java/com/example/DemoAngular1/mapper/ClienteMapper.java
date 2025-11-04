package com.example.DemoAngular1.mapper;

import com.example.DemoAngular1.dto.ClienteDTO;
import com.example.DemoAngular1.entity.Cliente;
import com.example.DemoAngular1.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper extends BaseMapper<Cliente, ClienteDTO> {
}
