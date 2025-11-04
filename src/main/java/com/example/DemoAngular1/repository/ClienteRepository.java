package com.example.DemoAngular1.repository;

import com.example.DemoAngular1.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {


    boolean existsByDniIgnoreCase(String dni);


    Cliente findByDniIgnoreCase(String dni);
}