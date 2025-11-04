package com.example.DemoAngular1.controller.general;

import com.example.DemoAngular1.dto.SancionDTO;
import com.example.DemoAngular1.service.general.service.SancionService;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/sanciones")
@CrossOrigin(origins = "http://localhost:4200")
public class SancionController {
    private final SancionService sancionService;

    @GetMapping
    public ResponseEntity<List<SancionDTO>> listAll() throws ServiceException {
        return ResponseEntity.ok(sancionService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SancionDTO> read(@PathVariable Long id) throws ServiceException {
        SancionDTO dto = sancionService.read(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<SancionDTO> create(@RequestBody SancionDTO sancionDTO) throws ServiceException {
        SancionDTO created = sancionService.create(sancionDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SancionDTO> update(@PathVariable Long id, @RequestBody SancionDTO sancionDTO) throws ServiceException {
        SancionDTO updated = sancionService.update(id, sancionDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ServiceException {
        sancionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
