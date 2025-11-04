package com.example.DemoAngular1.repository;

import com.example.DemoAngular1.entity.Sancion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SancionRepository extends JpaRepository<Sancion, Long> {
    boolean existsByClienteId(Long idCliente);
}
