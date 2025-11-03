package com.example.exa02.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name="PRODUCTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, unique=true, length=120)
    private String nombre;
    @Column(nullable=false, precision=12, scale=2)
    private BigDecimal precio;
    @Column
    private Integer stock;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CATEGORIA_ID", nullable=false)
    private Categoria categoria;

}
