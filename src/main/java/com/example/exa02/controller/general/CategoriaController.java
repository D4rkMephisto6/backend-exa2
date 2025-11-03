package com.example.exa02.controller.general;

import com.example.exa02.dto.CategoriaDTO;
import com.example.exa02.service.general.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/categorias")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaController {
    private final CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listAll() throws ServiceException {
        return ResponseEntity.ok(categoriaService.listAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> read(@PathVariable Long id) throws ServiceException {
        CategoriaDTO dto = categoriaService.read(id);
        return ResponseEntity.ok(dto);
    }
    @PostMapping
    public ResponseEntity<CategoriaDTO> create(@RequestBody CategoriaDTO categoriaDTO) throws ServiceException {
        CategoriaDTO created = categoriaService.create(categoriaDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> update(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO) throws ServiceException {
        CategoriaDTO updated = categoriaService.update(id, categoriaDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ServiceException {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
