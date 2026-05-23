# Taller Semana 7 - Mensajería con RabbitMQ y Apache Camel

Materia: Integración de Sistemas (ISWZ3104)  
Grupo 7

---

## ¿Qué hace este proyecto?

Lee un archivo JSON (`pedido-creado.json`) que simula un pedido de ecommerce, lo envía a una cola de RabbitMQ usando Apache Camel, y otro proceso lo consume desde la cola y lo guarda en una carpeta de salida.

```
pedido-creado.json → Camel → RabbitMQ → Camel → output/pedido-procesado.json
```

## Tecnologías usadas

- Java 17
- Spring Boot 3.2.5
- Apache Camel 4.5.0
- RabbitMQ 3.13 (en Docker)
- Maven

## Cómo ejecutarlo

**1.** Levantar RabbitMQ con Docker:

```bash
docker run -d --name rabbitmq-demo \
  -p 5672:5672 \
  -p 15672:15672 \
  rabbitmq:3-management
```

**2.** Compilar y correr:

```bash
mvn clean compile
mvn spring-boot:run
```

**3.** Revisar que en la consola aparezcan los logs del productor y consumidor, y que se genere el archivo `output/pedido-procesado.json`.

La consola de RabbitMQ está en http://localhost:15672 (usuario: `guest`, contraseña: `guest`).

## Estructura

```
├── input/pedido-creado.json           # archivo de entrada
├── output/                            # aquí se guarda el resultado
├── src/main/java/com/taller/rabbitmq/
│   ├── TallerRabbitMQApplication.java
│   ├── ProductorRoute.java            # lee el JSON y publica en RabbitMQ
│   ├── ConsumidorRoute.java           # consume desde RabbitMQ y guarda
│   └── IdempotentConfig.java          # evita reprocesar archivos
├── src/main/resources/
│   └── application.properties         # config de conexión a RabbitMQ
├── evidencias/                        # capturas e informe
└── pom.xml
```

## Integrantes

- Carlos Ochoa
- Martín Jiménez
- Alejandro Moreira
- Andrew Vilcacundo
- Steven Carrillo