package com.example.DemoAngular1.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO {
    private Long id;
    private String direccion;
    private String telefono;
    private String nombre;
    private String email;
    private String dni;
    private LocalDate fechaNacimiento;
    private LocalDate fechaInscripcion;
    private String temaInteres;
    private Boolean estado;
}
