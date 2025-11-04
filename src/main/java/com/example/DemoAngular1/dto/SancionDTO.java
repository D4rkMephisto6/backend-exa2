package com.example.DemoAngular1.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SancionDTO {
    private Long id;
    private String tipo;
    private Integer dias;
    private Long clienteId;
    private String clienteNombre;
}
