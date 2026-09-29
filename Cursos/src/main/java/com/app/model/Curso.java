package com.app.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.sym.Name;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@Table(name = "cursos")
@NoArgsConstructor
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String ciclo_lectivo;

	private String division;
	private String grado;
	private String turno;
	private String cupo_maximo;

    @OneToOne(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Personal docente;
		
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alumno> alumnos = new ArrayList<>();
	
    public void addAlumno(Alumno alumno) {
    	alumnos.add(alumno);
    	alumno.setCurso(this);
    }

    public void removeAlumno(Alumno alumno) {
    	alumnos.remove(alumno);
    	alumno.setCurso(null);
    }
    
    
    
    
    
}
