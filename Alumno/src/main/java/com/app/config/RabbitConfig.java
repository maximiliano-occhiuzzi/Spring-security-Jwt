package com.app.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGE = "alumno.eventos";
    public static final String ROUTING_KEY_ALUMNO_CREADO = "alumno.creado";

    // Topic exchange: permite rutear por patrones de routing key (ej: "alumno.*")
    // si el día de mañana agregamos más eventos (alumno.baja, alumno.modificado, etc).
    @Bean
    public TopicExchange alumnoEventosExchange() {
        return new TopicExchange(EXCHANGE);
    }

    // Sin esto, Spring AMQP serializa los objetos con Java Serialization
    // (binario, ilegible, y frágil entre distintas JVMs/versiones).
    // Con este converter, viaja como JSON plano por la cola.
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                          MessageConverter jsonMessageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter);
        return template;
    }
}
