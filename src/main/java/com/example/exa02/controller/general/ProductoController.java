package com.example.exa02.controller.general;

import com.example.exa02.dto.ProductoDTO;
import com.example.exa02.service.general.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/productos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController {
    private final ProductoService productosService;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listAll() throws ServiceException {
        return ResponseEntity.ok(productosService.listAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> read(@PathVariable Long id) throws ServiceException {
        ProductoDTO dto = productosService.read(id);
        return ResponseEntity.ok(dto);
    }
    @PostMapping
    public ResponseEntity<ProductoDTO> create(@RequestBody ProductoDTO productoDTO) throws ServiceException {
        ProductoDTO created = productosService.create(productoDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> update(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) throws ServiceException {
        ProductoDTO updated = productosService.update(id, productoDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ServiceException {
        productosService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
