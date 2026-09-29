package com.app.dto;

public record AlumnoAltaDTO(
    String nombre,
    String apellido,
    String dni,
    String email,
    Long cursoId
) {}