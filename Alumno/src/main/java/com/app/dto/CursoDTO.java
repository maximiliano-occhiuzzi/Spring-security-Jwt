package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoDTO {
    private Long id;
    private String ciclo_lectivo;
    private String division;
    private String grado;
    private String turno;
    private String cupo_maximo;
    private DocenteDTO docente;   // <-- antes tenía docenteId (Long), ahora el objeto completo
}