package com.example.DemoAngular1.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "CLIENTE", schema = "ANGULARBD1")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;

    @Column(name = "direccion_cliente")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "nombre_cliente")
    private String nombre;

    @Column(name = "email")
    private String email;

    @Column(name = "nro_dni")
    private String dni;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "fecha_inscripcion")
    private LocalDate fechaInscripcion;

    @Column(name = "tema_interes")
    private String temaInteres;

    @Column(name = "estado")
    private Boolean estado;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sancion> sanciones;
}
