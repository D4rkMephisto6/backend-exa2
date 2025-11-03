package com.example.exa02.service.general.impl;

import com.example.exa02.controller.error.ResourceNotFoundException;
import com.example.exa02.dto.ProductoDTO;
import com.example.exa02.entity.Categoria;
import com.example.exa02.entity.Producto;
import com.example.exa02.mapper.CategoriaMapper;
import com.example.exa02.mapper.ProductoMapper;
import com.example.exa02.repository.CategoriaRepository;
import com.example.exa02.repository.ProductoRepository;
import com.example.exa02.service.general.service.ProductoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;
    private final CategoriaMapper categoriaMapper;
    private final CategoriaRepository categoriaRepository;

    @Override
    public ProductoDTO create(ProductoDTO productoDTO) throws ServiceException {
        try {
            Categoria categoria = categoriaRepository.getById(productoDTO.getCategoriaId());

            Producto prod = productoMapper.toEntity(productoDTO);
            prod.setCategoria(categoria);
            Producto newProduct = productoRepository.save(prod);
            newProduct.setCategoria(categoria);
            return productoMapper.toDTO(newProduct);
        } catch (Exception e) {
            log.error("Error al crear el producto", e);
            throw new ServiceException("Error al crear el producto", e);
        }
    }

    @Override
    public ProductoDTO read(Long aLong) throws ServiceException {
        try {
            Producto prod = productoRepository.findById(aLong)
                    .orElseThrow(() -> new ResourceNotFoundException("Producto con id " + aLong + " no encontrada"));
            return productoMapper.toDTO(prod);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al leer el producto con id " + aLong, e);
        }
    }

    @Override
    public ProductoDTO update(Long aLong, ProductoDTO productoDTO) throws ServiceException {
        try {
            Producto producto = productoRepository.findById(aLong)
                    .orElseThrow(() -> new ResourceNotFoundException("Producto con id " + aLong + " no encontrada"));
            producto.setNombre(productoDTO.getNombre());
            producto.setPrecio(productoDTO.getPrecio());
            producto.setStock(productoDTO.getStock());
            Producto newProduct = productoRepository.save(producto);
            return productoMapper.toDTO(newProduct);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar el producto con id " + aLong, e);
        }
    }

    @Override
    public void delete(Long aLong) throws ServiceException {
        try {
            if (!productoRepository.existsById(aLong)) {
                throw new ResourceNotFoundException("Producto con id " + aLong + " no encontrada");
            }
            productoRepository.deleteById(aLong);

        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar el producto con id " + aLong, e);
        }
    }

    @Override
    public List<ProductoDTO> listAll() throws ServiceException {
        try {
            List<Producto> productos = productoRepository.findAll();
            return productoMapper.toDTOList(productos);
        } catch (Exception e) {
            throw new ServiceException("Error al listar todos los productos", e);
        }
    }
}
