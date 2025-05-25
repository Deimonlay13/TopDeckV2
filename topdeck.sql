
CREATE TABLE stage(
    id_stage RAW(16) DEFAULT SYS_GUID(),
    stage VARCHAR2(20) NOT NULL,
    CONSTRAINT PK_STAGE PRIMARY KEY (id_stage),
    CONSTRAINT UQ_STAGE UNIQUE (stage)
);

CREATE TABLE edicion(
    id_edicion RAW(16) DEFAULT SYS_GUID(),
    edicion VARCHAR2(20) NOT NULL,
    CONSTRAINT PK_EDICION PRIMARY KEY (id_edicion),
    CONSTRAINT UQ_EDICION UNIQUE (edicion)
);

CREATE TABLE tipo(
    id_tipo RAW(16) DEFAULT SYS_GUID(),
    tipo VARCHAR2(20) NOT NULL,
    CONSTRAINT PK_TIPO PRIMARY KEY (id_tipo),
    CONSTRAINT UQ_TIPO UNIQUE (tipo)
);

CREATE TABLE condicion(
    id_condicion RAW(16) DEFAULT SYS_GUID(),
    condicion VARCHAR2(20) NOT NULL,
    CONSTRAINT PK_CONDICION PRIMARY KEY (id_condicion),
    CONSTRAINT UQ_CONDICION UNIQUE (condicion)
);

CREATE TABLE categoria(
    id_categoria RAW(16) DEFAULT SYS_GUID(),
    categoria VARCHAR2(20) NOT NULL,
    CONSTRAINT PK_CATEGORIA PRIMARY KEY (id_categoria),
    CONSTRAINT UQ_CATEGORIA UNIQUE (categoria)
);

CREATE TABLE rareza(
    id_rareza RAW(16) DEFAULT SYS_GUID(),
    rareza VARCHAR2(20) NOT NULL,
    CONSTRAINT PK_RAREZA PRIMARY KEY (id_rareza),
    CONSTRAINT UQ_RAREZA UNIQUE (rareza)
);

CREATE TABLE energia(
    id_energia RAW(16) DEFAULT SYS_GUID(),
    energia VARCHAR2(20) NOT NULL,
    CONSTRAINT PK_ENERGIA PRIMARY KEY (id_energia),
    CONSTRAINT UQ_ENERGIA UNIQUE (energia)
);

CREATE TABLE idioma(
    id_idioma RAW(16) DEFAULT SYS_GUID(),
    idioma VARCHAR2(20) NOT NULL,
    CONSTRAINT PK_IDIOMA PRIMARY KEY (id_idioma),
    CONSTRAINT UQ_IDIOMA UNIQUE (idioma)
);

CREATE TABLE marca(
    id_marca RAW(16) DEFAULT SYS_GUID(),
    marca VARCHAR2(20) NOT NULL,
    CONSTRAINT PK_MARCA PRIMARY KEY (id_marca),
    CONSTRAINT UQ_MARCA UNIQUE (marca)
);

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

CREATE TABLE carta(
    nro_carta NUMBER NOT NULL,
    nom_carta VARCHAR2(20) NOT NULL,
    descripcion VARCHAR2(255) NOT NULL,
    precio NUMBER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    imagen BLOB,
    id_marca RAW(16) NOT NULL,
    id_stage RAW(16) NOT NULL,
    id_edicion RAW(16) NOT NULL,
    id_tipo RAW(16) NOT NULL,
    id_condicion RAW(16) NOT NULL,
    id_categoria RAW(16) NOT NULL,
    id_rareza RAW(16) NOT NULL,
    id_energia RAW(16) NOT NULL,
    id_idioma RAW(16) NOT NULL,
    id_estado RAW(16) NOT NULL,
    CONSTRAINT PK_NRO_CARTA PRIMARY KEY (nro_carta),
    CONSTRAINT UQ_NOM_CARTA UNIQUE (nom_carta),
    CONSTRAINT FK_CARTA_MARCA FOREIGN KEY(id_marca) REFERENCES marca(id_marca),
    CONSTRAINT FK_CARTA_STAGE FOREIGN KEY(id_stage) REFERENCES stage(id_stage),
    CONSTRAINT FK_CARTA_EDICION FOREIGN KEY(id_edicion) REFERENCES edicion(id_edicion),
    CONSTRAINT FK_CARTA_TIPO FOREIGN KEY(id_tipo) REFERENCES tipo(id_tipo),
    CONSTRAINT FK_CARTA_CONDICION FOREIGN KEY(id_condicion) REFERENCES condicion(id_condicion),
    CONSTRAINT FK_CARTA_CATEGORIA FOREIGN KEY(id_categoria) REFERENCES categoria(id_categoria),
    CONSTRAINT FK_CARTA_RAREZA FOREIGN KEY(id_rareza) REFERENCES rareza(id_rareza),
    CONSTRAINT FK_CARTA_ENERGIA FOREIGN KEY(id_energia) REFERENCES energia(id_energia),
    CONSTRAINT FK_CARTA_IDIOMA FOREIGN KEY(id_idioma) REFERENCES idioma(id_idioma),
    CONSTRAINT FK_CARTA_ESTADO FOREIGN KEY(id_estado) REFERENCES estado(id_estado)
);

CREATE TABLE sobre (
    id_sobre RAW(16) DEFAULT SYS_GUID(),
    nombre VARCHAR2(30) NOT NULL,
    precio NUMBER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT PK_SOBRE PRIMARY KEY (id_sobre),
    CONSTRAINT UQ_NOMBRE UNIQUE (nombre)
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

CREATE TABLE comentario (
    id_comentario RAW(16) DEFAULT SYS_GUID(),
    id_persona RAW(16) NOT NULL,
    nro_carta NUMBER,
    id_sobre RAW(16),
    comentario VARCHAR2(500) NOT NULL,
    calificacion NUMBER(1) CHECK (calificacion BETWEEN 1 AND 5),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT PK_COMENTARIO PRIMARY KEY (id_comentario),
    CONSTRAINT FK_COMENTARIO_PERSONA FOREIGN KEY (id_persona) REFERENCES persona(id_persona),
    CONSTRAINT FK_COMENTARIO_CARTA FOREIGN KEY (nro_carta) REFERENCES carta(nro_carta),
    CONSTRAINT FK_COMENTARIO_SOBRE FOREIGN KEY (id_sobre) REFERENCES sobre(id_sobre)
);
