# Sistema Bancario con Microservicios

Sistema de banca digital basado en microservicios que permite la gestión de clientes y cuentas bancarias, con comunicación asíncrona entre servicios.

## Requisitos del Sistema

- Docker 20.10 o superior
- Docker Compose
- Conexión a internet para descargar las imágenes de Docker

## Estructura del Proyecto

```
banco-microservicios/
├── cliente-service/    # Servicio de gestión de clientes
├── cuenta-service/     # Servicio de gestión de cuentas
├── docker-compose.yml  # Configuración de Docker Compose
└── .gitignore         # Archivos ignorados por Git
```

## Instrucciones de Instalación y Ejecución

### 1. Clonar el Repositorio

```bash
git clone <url-del-repositorio>
cd banco-microservicios
```

### 2. Construir y Ejecutar con Docker Compose

1. Abre una terminal en la carpeta del proyecto
2. Ejecuta el siguiente comando para construir y levantar todos los servicios:

```bash
docker-compose up -d --build
```

3. Verifica que todos los contenedores estén en ejecución:

```bash
docker-compose ps
```

Deberías ver 5 servicios en estado "Up":
- cliente-service
- cuenta-service
- cliente-db
- cuenta-db
- rabbitmq

### 3. Verificar el Estado de los Servicios

- Cliente Service: http://localhost:8081/actuator/health
- Cuenta Service: http://localhost:8082/actuator/health

## Pruebas Básicas

### 1. Crear un Cliente

```bash
curl -X POST "http://localhost:8081/clientes" \
-H "Content-Type: application/json" \
-d '{
    "nombre": "Juan Perez",
    "genero": "MASCULINO",
    "edad": 30,
    "identificacion": "12345678",
    "direccion": "Calle Falsa 123",
    "telefono": "+5491122334455",
    "contrasena": "clave123",
    "estado": true
}'
```

### 2. Verificar que se creó la cuenta por defecto

```bash
# Obtener el ID del cliente recién creado (ajusta el ID según corresponda)
curl "http://localhost:8082/cuentas/cliente/1"
```

### 3. Realizar un depósito

```bash
curl -X POST "http://localhost:8082/movimientos" \
-H "Content-Type: application/json" \
-d '{
    "cuentaId": 1,
    "tipoMovimiento": "DEPOSITO",
    "valor": 1000.00
}'
```

## Acceso a las Bases de Datos

### Base de Datos de Clientes
- Puerto: 5433
- Base de datos: cliente_db
- Usuario: user
- Contraseña: password

### Base de Datos de Cuentas
- Puerto: 5434
- Base de datos: cuenta_db
- Usuario: user
- Contraseña: password

## Monitoreo y Gestión

### RabbitMQ Management
- URL: http://localhost:15672
- Usuario: guest
- Contraseña: guest

## Solución de Problemas Comunes

### Los servicios no inician

1. Verifica que Docker esté en ejecución
2. Revisa los logs de los servicios:
   ```bash
   docker-compose logs <nombre_servicio>
   ```
3. Verifica que los puertos no estén en uso

### Error de conexión a la base de datos

1. Asegúrate de que los contenedores de PostgreSQL estén en ejecución
2. Verifica las credenciales en el archivo docker-compose.yml
3. Revisa los logs del servicio con problemas

### Reconstruir un servicio específico

```bash
docker-compose up -d --build <nombre_servicio>
```

## Detener los Servicios

Para detener todos los servicios y eliminar los contenedores:

```bash
docker-compose down
```

Para eliminar también los volúmenes (datos de las bases de datos):

```bash
docker-compose down -v
```
## Documentación de la API

Cada servicio incluye documentación interactiva de la API mediante Swagger:

- Cliente Service: http://localhost:8081/swagger-ui.html
- Cuenta Service: http://localhost:8082/swagger-ui.html

## Licencia

Este proyecto está bajo la Licencia MIT.
