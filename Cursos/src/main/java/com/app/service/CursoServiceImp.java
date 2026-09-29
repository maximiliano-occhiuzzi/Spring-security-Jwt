package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.model.Curso;
import com.app.repository.AlumnoRepository;
import com.app.repository.CursoRepository;

@Service
public class CursoServiceImp implements CursoService {

    private final CursoRepository cursoRepository;
    private final AlumnoRepository alumnoRepository;

    public CursoServiceImp(CursoRepository cursoRepository, AlumnoRepository alumnoRepository) {
        this.cursoRepository = cursoRepository;
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public Curso altaCursos(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    @Override
    public Optional<Curso> obtenerPorId(Long id) {
        return cursoRepository.findById(id);
    }

    // Nota para más adelante: si se retoma "registrar/sacar alumno de un
    // curso", la forma correcta de referenciar el alumno existente es
    // alumnoRepository.findById(idAlumno) (o getReferenceById si ya se
    // sabe que existe), NUNCA "new Alumno(); alumno.setId(idAlumno)" —
    // eso crea una entidad "a mano" con un id que el cliente decide,
    // en vez de pedirle a JPA la entidad real ya persistida.
}
