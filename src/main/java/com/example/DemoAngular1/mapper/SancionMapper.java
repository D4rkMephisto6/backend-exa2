package com.example.DemoAngular1.mapper;

import com.example.DemoAngular1.dto.SancionDTO;
import com.example.DemoAngular1.entity.Sancion;
import com.example.DemoAngular1.mapper.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SancionMapper extends BaseMapper<Sancion, SancionDTO> {

    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "cliente.nombre", target = "clienteNombre")
    SancionDTO toDTO(Sancion sancion);

    @Mapping(target = "cliente", ignore = true)
    Sancion toEntity(SancionDTO sancionDTO);
}
