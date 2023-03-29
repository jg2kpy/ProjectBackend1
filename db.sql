-- cliente
CREATE TABLE public.cliente (
    id_cliente INTEGER PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    nro_documento VARCHAR(50) NOT NULL,
    tipo_documento VARCHAR(50) NOT NULL,
    nacionalidad VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    telefono VARCHAR(50) NOT NULL,
    fecha_nacimiento DATE
);
CREATE SEQUENCE public.cliente_sec;

--reglas de asignacion de puntos
CREATE TABLE public.reglasAsigPuntos (
    id_reglasAsigPuntos INTEGER PRIMARY KEY,
    limite_inferior INTEGER NOT NULL,
    limite_superior INTEGER NOT NULL,
    monto_equivalencia INTEGER NOT NULL
);
CREATE SEQUENCE public.reglasAsigPuntos_sec;

--concepto puntos
CREATE TABLE public.conceptoPuntos (
    id_conceptoPuntos INTEGER PRIMARY KEY,
    descripcion VARCHAR(200) NOT NULL,
    puntos_requeridos INTEGER NOT NULL
);
CREATE SEQUENCE public.conceptoPuntos_sec;

--bolsa de puntos
CREATE TABLE public.bolsaPuntos (
    id_bolsaPuntos INTEGER PRIMARY KEY,
    id_cliente INTEGER NOT NULL,
    fecha_asignacion TIMESTAMP NOT NULL,
    fecha_caducidad TIMESTAMP NOT NULL,
    puntaje_asignado INTEGER NOT NULL,
    puntaje_utilizado INTEGER NOT NULL,
    saldo_puntos INTEGER NOT NULL,
    monto_operacion INTEGER NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
CREATE SEQUENCE public.bolsaPuntos_sec;

--cabecera de uso de puntos
CREATE TABLE public.usoPuntosCabecera (
    id_usoPuntosCabecera INTEGER PRIMARY KEY,
    id_cliente INTEGER NOT NULL,
    puntaje_utilizado INTEGER NOT NULL,
    fecha TIMESTAMP NOT NULL,
    id_conceptoPuntos INTEGER NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    FOREIGN KEY (id_conceptoPuntos) REFERENCES conceptoPuntos (id_conceptoPuntos) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
CREATE SEQUENCE public.usoPuntosCabecera_sec;

--detalle de uso de puntos
CREATE TABLE public.usoPuntosDetalle (
    id_usoPuntosDetalle INTEGER PRIMARY KEY,
    id_usoPuntosCabecera INTEGER NOT NULL,
    puntaje_utilizado INTEGER NOT NULL,
    id_bolsaPuntos INTEGER NOT NULL,
    FOREIGN KEY (id_usoPuntosCabecera) REFERENCES usoPuntosCabecera(id_usoPuntosCabecera) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    FOREIGN KEY (id_bolsaPuntos) REFERENCES bolsaPuntos(id_bolsaPuntos) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
CREATE SEQUENCE public.usoPuntosDetalle_sec;

--vencimiento de puntos
CREATE TABLE public.vecimientoPuntos (
                                         id_VecimientoPuntos INTEGER PRIMARY KEY,
                                         fecha_inicio_validez DATE NOT NULL,
                                         fecha_fin_validez DATE NOT NULL,
                                         dias_duracion INTEGER NOT NULL
);
CREATE SEQUENCE public.vecimientoPuntos_sec;
