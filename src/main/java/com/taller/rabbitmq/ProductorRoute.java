package com.taller.rabbitmq;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ProductorRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        onException(Exception.class)
            .maximumRedeliveries(3)
            .redeliveryDelay(2000)
            .log("ERROR - No se pudo publicar en RabbitMQ: ${exception.message}")
            .handled(true);

        from("file:input?noop=true&idempotentRepository=#fileRepo&fileName=pedido-creado.json")
            .routeId("ruta-productora")
            .log("=== PRODUCTOR: Archivo detectado: ${header.CamelFileName} ===")
            .log("=== PRODUCTOR: Contenido del mensaje: ${body} ===")
            .to("spring-rabbitmq:default?queues=integracion.pedidos.creados&routingKey=integracion.pedidos.creados")
            .log("=== PRODUCTOR: Mensaje publicado exitosamente en RabbitMQ ===");
    }
}