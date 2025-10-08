# Banco Microservicios - Prueba Técnica

Sistema de banca digital basado en microservicios que incluye gestión de clientes y cuentas.

## Estructura del Proyecto

- `cliente-service/`: Microservicio para la gestión de clientes
- `cuenta-service/`: Microservicio para la gestión de cuentas bancarias
- `docker-compose.yml`: Configuración para desplegar la aplicación con Docker
- `BaseDatos.sql`: Scripts de inicialización de la base de datos

## Requisitos Previos

- Java 17 o superior
- Maven 3.8 o superior
- Docker 20.10+ y Docker Compose
- Git (opcional, solo para clonar el repositorio)

## Despliegue con Docker

### 1. Clonar el repositorio (si aún no lo has hecho)
```bash
git clone <repositorio>
cd banco-microservicios
```

### 2. Construir las imágenes de los microservicios
```bash
# Construir cliente-service
docker build -t cliente-service ./cliente-service

# Construir cuenta-service
docker build -t cuenta-service ./cuenta-service
```

### 3. Iniciar los servicios con Docker Compose
```bash
docker-compose up -d
```

### 4. Verificar que los servicios estén en ejecución
```bash
docker-compose ps
```

## Puertos de los Servicios

- **cliente-service**: http://localhost:8081
- **cuenta-service**: http://localhost:8082
- **RabbitMQ Management**: http://localhost:15672
  - Usuario: guest
  - Contraseña: guest
- **Base de Datos Cliente**: localhost:5433
- **Base de Datos Cuenta**: localhost:5434

## Variables de Entorno

Los servicios pueden configurarse mediante las siguientes variables de entorno:

### cliente-service
- `SPRING_DATASOURCE_URL`: URL de conexión a la base de datos
- `SPRING_DATASOURCE_USERNAME`: Usuario de la base de datos
- `SPRING_DATASOURCE_PASSWORD`: Contraseña de la base de datos
- `SPRING_RABBITMQ_HOST`: Host de RabbitMQ

### cuenta-service
- `SPRING_DATASOURCE_URL`: URL de conexión a la base de datos
- `SPRING_DATASOURCE_USERNAME`: Usuario de la base de datos
- `SPRING_DATASOURCE_PASSWORD`: Contraseña de la base de datos
- `SPRING_RABBITMQ_HOST`: Host de RabbitMQ

## Detener los Servicios

Para detener todos los servicios y limpiar los contenedores:

```bash
docker-compose down
```

## Solución de Problemas

- Si los servicios no se inician correctamente, revisa los logs:
  ```bash
  docker-compose logs <nombre_servicio>
  ```

- Para forzar la reconstrucción de las imágenes:
  ```bash
  docker-compose up -d --build
  ```

- Si necesitas reiniciar un servicio específico:
  ```bash
  docker-compose restart <nombre_servicio>
  ```
