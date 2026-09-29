package com.app.service;

import com.app.config.RabbitConfig;
import com.app.dto.AlumnoConCursoDTO;
import com.app.event.AlumnoCreadoEvent;
import com.app.model.Alumno;
import com.app.repository.AlumnoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public Alumno altaAlumno(Alumno alumno) {
        Alumno guardado = alumnoRepository.save(alumno);

        AlumnoCreadoEvent evento = new AlumnoCreadoEvent(
                guardado.getId(),
                guardado.getNombre(),
                guardado.getApellido(),
                guardado.getEmail()
        );

        // El alta a la base ya se hizo (arriba). Publicar el evento es
        // un "nice to have": si RabbitMQ no está levantado (typico en
        // desarrollo, si no lo estás usando todavía), no tiene sentido
        // que el alumno no se pueda dar de alta por eso. Se loguea el
        // problema y se sigue, en vez de dejar que la excepción rompa
        // toda la request.
        try {
            rabbitTemplate.convertAndSend(
                    RabbitConfig.EXCHANGE,
                    RabbitConfig.ROUTING_KEY_ALUMNO_CREADO,
                    evento
            );
        } catch (AmqpException e) {
            log.warn("No se pudo publicar el evento AlumnoCreadoEvent en RabbitMQ " +
                    "(¿está levantado el broker?). El alumno igual se guardó. Detalle: {}",
                    e.getMessage());
        }

        return guardado;
    }

    @Override
    public List<Alumno> listarAlumnos() {
        // tu implementación existente, sin tocar
        return alumnoRepository.findAll();
    }

    @Override
    public AlumnoConCursoDTO obtenerConCurso(Long alumnoId) {
        Alumno alumno = alumnoRepository.findById(alumnoId)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado con id: " + alumnoId));

        AlumnoConCursoDTO dto = new AlumnoConCursoDTO();
        dto.setId(alumno.getId());
        dto.setNombre(alumno.getNombre());
        dto.setApellido(alumno.getApellido());
        dto.setDni(alumno.getDni());
        dto.setEmail(alumno.getEmail());

        return dto;
    }
}