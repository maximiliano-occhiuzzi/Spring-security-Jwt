package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoConCursoDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private CursoDTO curso;
}