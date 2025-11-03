package com.example.exa02.service.general.impl;

import com.example.exa02.controller.error.ResourceNotFoundException;
import com.example.exa02.dto.CategoriaDTO;
import com.example.exa02.entity.Categoria;
import com.example.exa02.mapper.CategoriaMapper;
import com.example.exa02.repository.CategoriaRepository;
import com.example.exa02.service.general.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private  final CategoriaMapper categoriaMapper;
    @Override
    public CategoriaDTO create(CategoriaDTO categoriaDTO) throws ServiceException {
        try {
            Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
            Categoria categoriaGuardada = categoriaRepository.save(categoria);
            return categoriaMapper.toDTO(categoriaGuardada);
        } catch (Exception e) {
            log.error("Error al crear la categoría", e);
            throw new ServiceException("Error al crear la categoría", e);
        }
    }

    @Override
    public CategoriaDTO read(Long aLong) throws ServiceException {
        try {
            Categoria categoria = categoriaRepository.findById(aLong)
                    .orElseThrow(() -> new ResourceNotFoundException("Categoría con id " + aLong + " no encontrada"));
            return categoriaMapper.toDTO(categoria);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al leer la categoría con id " + aLong, e);
        }
    }

    @Override
    public CategoriaDTO update(Long aLong, CategoriaDTO categoriaDTO) throws ServiceException {
        try {
            Categoria categoria = categoriaRepository.findById(aLong)
                    .orElseThrow(() -> new ResourceNotFoundException("Categoría con id " + aLong + " no encontrada"));
            categoria.setNombre(categoriaDTO.getNombre());
            categoria.setEstado(categoriaDTO.getEstado());
            Categoria categoriaActualizada = categoriaRepository.save(categoria);
            return categoriaMapper.toDTO(categoriaActualizada);

        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar la categoría con id " + aLong, e);
        }
    }

    @Override
    public void delete(Long aLong) throws ServiceException {
        try {
            if (!categoriaRepository.existsById(aLong)) {
                throw new ResourceNotFoundException("Categoría con id " + aLong + " no encontrada");
            }
            categoriaRepository.deleteById(aLong);

        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar la categoría con id " + aLong, e);
        }
    }

    @Override
    public List<CategoriaDTO> listAll() throws ServiceException {
        try {
            List<Categoria> categorias = categoriaRepository.findAll();
            return categoriaMapper.toDTOList(categorias);
        } catch (Exception e) {
            throw new ServiceException("Error al listar todas las categorías", e);
        }
    }
}
