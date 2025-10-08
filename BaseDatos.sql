-- cliente-service
CREATE TABLE clientes (
    cliente_id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100),
    genero VARCHAR(10),
    edad INT,
    identificacion VARCHAR(20) UNIQUE,
    direccion VARCHAR(200),
    telefono VARCHAR(20),
    contrasena VARCHAR(100),
    estado BOOLEAN
);

-- cuenta-service
CREATE TABLE cuentas (
    id BIGSERIAL PRIMARY KEY,
    numero_cuenta VARCHAR(20) UNIQUE,
    tipo_cuenta VARCHAR(20),
    saldo_inicial DECIMAL(19,2),
    estado BOOLEAN,
    cliente_id BIGINT
);

CREATE TABLE movimientos (
    id BIGSERIAL PRIMARY KEY,
    fecha TIMESTAMP,
    tipo_movimiento VARCHAR(20),
    valor DECIMAL(19,2),
    saldo DECIMAL(19,2),
    cuenta_id BIGINT
);
