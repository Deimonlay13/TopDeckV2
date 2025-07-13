
CREATE TABLE medio_de_pago(
    id_medio_de_pago RAW(16) DEFAULT SYS_GUID(),
    medio_de_pago VARCHAR2(20) NOT NULL,
    CONSTRAINT PK_MEDIO_DE_PAGO PRIMARY KEY (id_medio_de_pago),
    CONSTRAINT UQ_MEDIO_DE_PAGO UNIQUE (medio_de_pago)
);

CREATE TABLE region(
    id_region RAW(16) DEFAULT SYS_GUID(),
    region VARCHAR2(20) NOT NULL,
    CONSTRAINT PK_REGION PRIMARY KEY (id_region),
    CONSTRAINT UQ_REGION UNIQUE (region)
);

CREATE TABLE estado(
    id_estado RAW(16) DEFAULT SYS_GUID(),
    estado VARCHAR2(20) NOT NULL,
    CONSTRAINT PK_ESTADO PRIMARY KEY (id_estado),
    CONSTRAINT UQ_ESTADO UNIQUE (estado)
);

CREATE TABLE comuna (
    id_comuna RAW(16) DEFAULT SYS_GUID(),
    comuna VARCHAR2(30) NOT NULL,
    CONSTRAINT PK_COMUNA PRIMARY KEY (id_comuna),
    CONSTRAINT UQ_COMUNA UNIQUE (comuna)
);

CREATE TABLE rol(
    id_rol RAW(16) DEFAULT SYS_GUID(),
    rol VARCHAR2(20) NOT NULL,
    descripcion VARCHAR2(255) NOT NULL,
    disabled NUMBER(1),
    CONSTRAINT PK_ROL PRIMARY KEY (id_rol),
    CONSTRAINT UQ_ROL UNIQUE (rol),
    CONSTRAINT CHK_DISABLED CHECK (disabled IN (0, 1)),
);

CREATE TABLE entrega (
    id_entrega RAW(16) DEFAULT SYS_GUID(),
    entrega VARCHAR2(30) NOT NULL,
    CONSTRAINT PK_ENTREGA PRIMARY KEY (id_entrega),
    CONSTRAINT UQ_ENTREGA UNIQUE (entrega)
);
--arreglar el email (lower)
CREATE TABLE persona (
    id_persona RAW(16) DEFAULT SYS_GUID(),
    nombre VARCHAR2(50) NOT NULL,
    apellidos VARCHAR2(50) NOT NULL,
    email VARCHAR2(255) NOT NULL,
    contrasena VARCHAR2(255) NOT NULL,
    token VARCHAR2(255),
    CONSTRAINT PK_PERSONA PRIMARY KEY (id_persona),
    CONSTRAINT UQ_PERSONA_EMAIL UNIQUE (LOWER(email))
);

CREATE TABLE estado_pedido (
    id_estado_pedido RAW(16) DEFAULT SYS_GUID(),
    estado VARCHAR2(20) NOT NULL,
    CONSTRAINT PK_ESTADO_PEDIDO PRIMARY KEY (id_estado_pedido),
    CONSTRAINT UQ_ESTADO_PEDIDO UNIQUE (estado)
);

CREATE TABLE pedido (
    id_pedido RAW(16) DEFAULT SYS_GUID(),
    id_persona RAW(16) NOT NULL,
    id_estado RAW(16) NOT NULL,
    id_medio_de_pago RAW(16) NOT NULL,
    id_entrega RAW(16) NOT NULL,
    total NUMBER(10, 2) NOT NULL,
    id_estado_pedido RAW(16) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT PK_PEDIDO PRIMARY KEY (id_pedido),
    CONSTRAINT FK_PEDIDO_PERSONA FOREIGN KEY (id_persona) REFERENCES persona(id_persona),
    CONSTRAINT FK_PEDIDO_ESTADO FOREIGN KEY (id_estado) REFERENCES estado(id_estado),
    CONSTRAINT FK_PEDIDO_MEDIO FOREIGN KEY (id_medio_de_pago) REFERENCES medio_de_pago(id_medio_de_pago),
    CONSTRAINT FK_PEDIDO_ENTREGA FOREIGN KEY (id_entrega) REFERENCES entrega(id_entrega),
    CONSTRAINT FK_PEDIDO_ESTADO_PEDIDO FOREIGN KEY (id_estado_pedido) REFERENCES estado_pedido(id_estado_pedido)
);

CREATE TABLE detalle_pedido (
    id_detalle RAW(16) DEFAULT SYS_GUID(),
    id_pedido RAW(16) NOT NULL,
    producto NUMBER NOT NULL,
    cantidad NUMBER NOT NULL,
    precio_unitario NUMBER(10, 2) NOT NULL,
    subtotal NUMBER(10, 2) NOT NULL,
    CONSTRAINT PK_DETALLE_PEDIDO PRIMARY KEY (id_detalle),
    CONSTRAINT FK_DETALLE_PEDIDO FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
    CONSTRAINT FK_DETALLE_CARTA FOREIGN KEY (nro_carta) REFERENCES carta(nro_carta)
);

CREATE TABLE direccion (
    id_direccion RAW(16) DEFAULT SYS_GUID(),
    id_persona RAW(16) NOT NULL,
    id_comuna RAW(16) NOT NULL,
    direccion VARCHAR2(255) NOT NULL,
    telefono VARCHAR2(25),
    CONSTRAINT PK_DIRECCION PRIMARY KEY (id_direccion),
    CONSTRAINT FK_DIRECCION_PERSONA FOREIGN KEY (id_persona) REFERENCES persona(id_persona),
    CONSTRAINT FK_DIRECCION_COMUNA FOREIGN KEY (id_comuna) REFERENCES comuna(id_comuna)
);
