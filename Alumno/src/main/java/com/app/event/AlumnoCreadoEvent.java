package com.app.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Evento que se publica en RabbitMQ cuando se da de alta un alumno.
 *
 * OJO: a propósito NO reutilizamos la entidad Alumno acá. El evento es un
 * "contrato" propio, separado del modelo interno de la base. Así, si mañana
 * cambiamos la entidad Alumno (agregamos un campo interno, por ejemplo),
 * no rompemos a todo el que esté escuchando este evento.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoCreadoEvent implements Serializable {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
}
