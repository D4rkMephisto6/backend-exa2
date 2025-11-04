package com.example.DemoAngular1.service.general.impl;

import com.example.DemoAngular1.controller.error.ResourceNotFoundException;
import com.example.DemoAngular1.dto.ClienteDTO;
import com.example.DemoAngular1.entity.Cliente;
import com.example.DemoAngular1.mapper.ClienteMapper;
import com.example.DemoAngular1.repository.ClienteRepository;
import com.example.DemoAngular1.service.general.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Override
    public ClienteDTO create(ClienteDTO clienteDTO) throws ServiceException {
        try {
            Cliente cliente = clienteMapper.toEntity(clienteDTO);
            Cliente clienteGuardado = clienteRepository.save(cliente);
            return clienteMapper.toDTO(clienteGuardado);
        } catch (Exception e) {
            log.error("Error al crear el cliente", e);
            throw new ServiceException("Error al crear el cliente", e);
        }
    }

    @Override
    public ClienteDTO read(Long id) throws ServiceException {
        try {
            Cliente cliente = clienteRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Cliente con id " + id + " no encontrado"));
            return clienteMapper.toDTO(cliente);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al leer el cliente con id " + id, e);
        }
    }

    @Override
    public ClienteDTO update(Long id, ClienteDTO clienteDTO) throws ServiceException {
        try {
            Cliente cliente = clienteRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Cliente con id " + id + " no encontrado"));

            cliente.setDireccion(clienteDTO.getDireccion());
            cliente.setTelefono(clienteDTO.getTelefono());
            cliente.setNombre(clienteDTO.getNombre());
            cliente.setEmail(clienteDTO.getEmail());
            cliente.setDni(clienteDTO.getDni());
            cliente.setFechaNacimiento(clienteDTO.getFechaNacimiento());
            cliente.setFechaInscripcion(clienteDTO.getFechaInscripcion());
            cliente.setTemaInteres(clienteDTO.getTemaInteres());
            cliente.setEstado(clienteDTO.getEstado());

            Cliente clienteActualizado = clienteRepository.save(cliente);
            return clienteMapper.toDTO(clienteActualizado);

        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al actualizar el cliente con id {}", id, e);
            throw new ServiceException("Error al actualizar el cliente con id " + id, e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            if (!clienteRepository.existsById(id)) {
                throw new ResourceNotFoundException("Cliente con id " + id + " no encontrado");
            }
            clienteRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al eliminar el cliente con id {}", id, e);
            throw new ServiceException("Error al eliminar el cliente con id " + id, e);
        }
    }

    @Override
    public List<ClienteDTO> listAll() throws ServiceException {
        try {
            List<Cliente> clientes = clienteRepository.findAll();
            System.out.println("Cantidad de sanciones encontradas: " + clientes.size());
            return clienteMapper.toDTOList(clientes);
        } catch (Exception e) {
            log.error("Error al listar los clientes", e);
            throw new ServiceException("Error al listar todos los clientes", e);
        }
    }
}
