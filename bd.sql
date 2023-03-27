-- cliente
CREATE TABLE cliente (
    id_cliente INTEGER PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    nro_documento VARCHAR(50) NOT NULL,
    tipo_documento VARCHAR(50) NOT NULL,
    nacionalidad VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    telefono VARCHAR(50) NOT NULL,
    fecha_nacimiento DATE NOT NULL
);

CREATE SEQUENCE cliente_sec START WITH 1 INCREMENT BY 1;

--reglas de asignacion de puntos
CREATE TABLE reglasAsigPuntos (
    id_reglasAsigPuntos INTEGER PRIMARY KEY,
    limite_inferior INTEGER NOT NULL,
    limite_superior INTEGER NOT NULL,
    monto_equivalencia INTEGER NOT NULL
);

CREATE SEQUENCE reglasAsigPuntos_sec START WITH 1 INCREMENT BY 1;

--uso puntos
CREATE TABLE usoPuntos (
    id_usoPuntos INTEGER PRIMARY KEY,
    descripcion VARCHAR(200) NOT NULL,
    puntos_requeridos INTEGER NOT NULL
);

CREATE SEQUENCE usoPuntos_sec START WITH 1 INCREMENT BY 1;

--bolsa de puntos
CREATE TABLE bolsaPuntos (
    id_bolsaPuntos SERIAL PRIMARY KEY,
    id_cliente INTEGER NOT NULL,
    fecha_asignacion TIMESTAMP NOT NULL,
    fecha_caducidad TIMESTAMP NOT NULL,
    puntaje_asignado INTEGER NOT NULL,
    puntaje_utilizado INTEGER NOT NULL,
    saldo_puntos INTEGER NOT NULL,
    monto_operacion INTEGER NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente)
);