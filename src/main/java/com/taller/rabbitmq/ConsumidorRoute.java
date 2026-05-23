package com.taller.rabbitmq;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ConsumidorRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("spring-rabbitmq:default?queues=integracion.pedidos.creados&routingKey=integracion.pedidos.creados")
            .routeId("ruta-consumidora")
            .log("=== CONSUMIDOR: Mensaje recibido desde RabbitMQ ===")
            .log("=== CONSUMIDOR: Contenido: ${body} ===")
            .to("file:output?fileName=pedido-procesado.json")
            .log("=== CONSUMIDOR: Mensaje guardado en output/pedido-procesado.json ===");
    }
}