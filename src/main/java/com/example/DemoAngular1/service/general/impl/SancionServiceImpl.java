package com.example.DemoAngular1.service.general.impl;

import com.example.DemoAngular1.controller.error.ResourceNotFoundException;
import com.example.DemoAngular1.dto.SancionDTO;
import com.example.DemoAngular1.entity.Cliente;
import com.example.DemoAngular1.entity.Sancion;
import com.example.DemoAngular1.mapper.SancionMapper;
import com.example.DemoAngular1.repository.ClienteRepository;
import com.example.DemoAngular1.repository.SancionRepository;
import com.example.DemoAngular1.service.general.service.SancionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SancionServiceImpl implements SancionService {

    private final SancionRepository sancionRepository;
    private final ClienteRepository clienteRepository;
    private final SancionMapper sancionMapper;

    @Override
    public SancionDTO create(SancionDTO sancionDTO) throws ServiceException {
        try {
            Cliente cliente = clienteRepository.findById(sancionDTO.getClienteId())
                    .orElseThrow(() -> new ResourceNotFoundException("Cliente con id " + sancionDTO.getClienteId() + " no encontrado"));
            Sancion sancion = sancionMapper.toEntity(sancionDTO);
            sancion.setCliente(cliente);
            Sancion sancionGuardada = sancionRepository.save(sancion);
            return sancionMapper.toDTO(sancionGuardada);
        } catch (Exception e) {
            log.error("Error al crear la sanción", e);
            throw new ServiceException("Error al crear la sanción", e);
        }
    }

    @Override
    public SancionDTO read(Long id) throws ServiceException {
        try {
            Sancion sancion = sancionRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Sanción con id " + id + " no encontrada"));
            return sancionMapper.toDTO(sancion);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al leer la sanción con id " + id, e);
        }
    }

    @Override
    public SancionDTO update(Long id, SancionDTO sancionDTO) throws ServiceException {
        try {
            Sancion sancion = sancionRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Sanción con id " + id + " no encontrada"));

            sancion.setTipo(sancionDTO.getTipo());
            sancion.setDias(sancionDTO.getDias());

            Sancion sancionActualizada = sancionRepository.save(sancion);
            return sancionMapper.toDTO(sancionActualizada);

        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al actualizar la sanción con id {}", id, e);
            throw new ServiceException("Error al actualizar la sanción con id " + id, e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            if (!sancionRepository.existsById(id)) {
                throw new ResourceNotFoundException("Sanción con id " + id + " no encontrada");
            }
            sancionRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al eliminar la sanción con id {}", id, e);
            throw new ServiceException("Error al eliminar la sanción con id " + id, e);
        }
    }

    @Override
    public List<SancionDTO> listAll() throws ServiceException {
        try {
            List<Sancion> sanciones = sancionRepository.findAll();
            System.out.println("Cantidad de sanciones encontradas: " + sanciones.size());
            return sancionMapper.toDTOList(sanciones);
        } catch (Exception e) {
            log.error("Error al listar las sanciones", e);
            throw new ServiceException("Error al listar todas las sanciones", e);
        }
    }
}
