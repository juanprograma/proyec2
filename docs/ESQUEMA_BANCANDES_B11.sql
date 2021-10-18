--- Sentencias SQL para la creación del esquema de bancandes.

-- Creación del secuenciador
create sequence bancandes_sequence;

-- Creaación de la tabla CLIENTE y especificación de sus restricciones.
CREATE TABLE CLIENTE(
    TIPODOCUMENTO VARCHAR2(255 BYTE), 
	NUMERODOCUMENTO NUMBER,
    NOMBRE VARCHAR2(255 BYTE),
    NACIONALIDAD VARCHAR2(255 BYTE),
    DIRECCIONFISICA VARCHAR2(255 BYTE),
    DIRECCIONELECTRONICA VARCHAR2(255 BYTE),
    TELEFONO NUMBER,
    CIUDAD VARCHAR2(255 BYTE),
    DEPARTAMENTO VARCHAR2(255 BYTE),
    CODIGOPOSTAL VARCHAR2(255 BYTE),
    TIPO VARCHAR2(255 BYTE),
	CONSTRAINT CLIENTE_PK PRIMARY KEY (TIPODOCUMENTO, NUMERODOCUMENTO)
);
	
ALTER TABLE CLIENTE
	ADD CONSTRAINT UN_CLIENTE_DIRELE_TELE
	UNIQUE (DIRECCIONELECTRONICA, TELEFONO)
ENABLE;

ALTER TABLE CLIENTE
	ADD CONSTRAINT CK_CLIENTE_TIPO
	CHECK (TIPO IN ('Natural', 'Juridica'))
ENABLE;

-- Creaación de la tabla OFICINA y especificación de sus restricciones
CREATE TABLE OFICINA 
   (IDOFICINA NUMBER, 
	NOMBRE VARCHAR2(255 BYTE),  
	DIRECCION VARCHAR2(255 BYTE), 
	CONSTRAINT OFICINA_PK PRIMARY KEY (IDOFICINA));

-- Creaación de la tabla GERENTEOFICINA y especificación de sus restricciones
CREATE TABLE GERENTEOFICINA(
    TIPODOCUMENTO VARCHAR2(255 BYTE), 
	NUMERODOCUMENTO NUMBER,
    NOMBRE VARCHAR2(255 BYTE),
    NACIONALIDAD VARCHAR2(255 BYTE),
    DIRECCIONFISICA VARCHAR2(255 BYTE),
    DIRECCIONELECTRONICA VARCHAR2(255 BYTE),
    TELEFONO NUMBER,
    CIUDAD VARCHAR2(255 BYTE),
    DEPARTAMENTO VARCHAR2(255 BYTE),
    CODIGOPOSTAL VARCHAR2(255 BYTE),
    ESGERENTE NUMBER,
    ESCLIENTE CHAR,
	CONSTRAINT GERENTEOFICINA_PK PRIMARY KEY (TIPODOCUMENTO, NUMERODOCUMENTO)
);

ALTER TABLE GERENTEOFICINA
	ADD CONSTRAINT CK_GERENTEOFI_ESCLIENTE
	CHECK(ESCLIENTE IN ('Y', 'N'))
ENABLE;

ALTER TABLE GERENTEOFICINA
	ADD CONSTRAINT UN_GERENTEOFI_DIRELE_TELE
	UNIQUE (DIRECCIONELECTRONICA, TELEFONO)
ENABLE;

ALTER TABLE GERENTEOFICINA
    ADD CONSTRAINT FK_GERENTEOFICINA_ESGERENTE
        FOREIGN KEY (ESGERENTE)
        REFERENCES OFICINA(IDOFICINA)
ENABLE;

-- Creaación de la tabla GERENTEGENERAL y especificación de sus restricciones
CREATE TABLE GERENTEGENERAL(
    TIPODOCUMENTO VARCHAR2(255 BYTE), 
    NUMERODOCUMENTO NUMBER,
    NOMBRE VARCHAR2(255 BYTE),
    NACIONALIDAD VARCHAR2(255 BYTE),
    DIRECCIONFISICA VARCHAR2(255 BYTE),
    DIRECCIONELECTRONICA VARCHAR2(255 BYTE),
    TELEFONO NUMBER,
    CIUDAD VARCHAR2(255 BYTE),
    DEPARTAMENTO VARCHAR2(255 BYTE),
    CODIGOPOSTAL VARCHAR2(255 BYTE),
    ESCLIENTE CHAR,
	CONSTRAINT GERENTEGENERAL_PK PRIMARY KEY (TIPODOCUMENTO, NUMERODOCUMENTO)
);

ALTER TABLE GERENTEGENERAL
	ADD CONSTRAINT UN_GERENTEGENE_DIRELE_TELE
	UNIQUE (DIRECCIONELECTRONICA, TELEFONO)
ENABLE;

ALTER TABLE GERENTEGENERAL
	ADD CONSTRAINT CK_GERENTEGENERAL_ESCLIENTE
	CHECK (ESCLIENTE IN ('Y', 'N'))
ENABLE;

-- Creaación de la tabla CAJERO y especificación de sus restricciones
CREATE TABLE CAJERO(
    TIPODOCUMENTO VARCHAR2(255 BYTE), 
	NUMERODOCUMENTO NUMBER,
    NOMBRE VARCHAR2(255 BYTE),
    NACIONALIDAD VARCHAR2(255 BYTE),
    DIRECCIONFISICA VARCHAR2(255 BYTE),
    DIRECCIONELECTRONICA VARCHAR2(255 BYTE),
    TELEFONO NUMBER,
    CIUDAD VARCHAR2(255 BYTE),
    DEPARTAMENTO VARCHAR2(255 BYTE),
    CODIGOPOSTAL VARCHAR2(255 BYTE),
    ESCLIENTE CHAR,
	CONSTRAINT CAJERO_PK PRIMARY KEY (TIPODOCUMENTO, NUMERODOCUMENTO)
);

ALTER TABLE CAJERO
	ADD CONSTRAINT UN_CAJERO_DIRELE_TELE
	UNIQUE (DIRECCIONELECTRONICA, TELEFONO)
ENABLE;

ALTER TABLE CAJERO
    ADD CONSTRAINT CK_CAJERO_ESCLIENTE
    CHECK(ESCLIENTE IN ('Y', 'N'))
ENABLE;

-- Creaación de la tabla CUENTA y especificación de sus restricciones
CREATE TABLE CUENTA 
(
  IDCUENTA NUMBER,
  ACTIVA CHAR,
  FECHACREACION VARCHAR2(255 BYTE),
  SALDO NUMBER,
  FECHAULTIMOMOV DATE,
  TIPO VARCHAR2(255 BYTE),
  CUENTASTIPODOC VARCHAR2(255 BYTE),
  CUENTASNUMDOC NUMBER,
  CUENTASOFICINA NUMBER,
  CONSTRAINT CUENTA_PK PRIMARY KEY (IDCUENTA)
);

ALTER TABLE CUENTA
    ADD CONSTRAINT FK_CUENTA_CUENTAS
    FOREIGN KEY (CUENTASTIPODOC, CUENTASNUMDOC)
    REFERENCES CLIENTE(TIPODOCUMENTO, NUMERODOCUMENTO)
ENABLE;
    
ALTER TABLE CUENTA
    ADD CONSTRAINT FK_CUENTA_CUENTASOFICINA
    FOREIGN KEY (CUENTASOFICINA)
    REFERENCES OFICINA(IDOFICINA)
ENABLE;

ALTER TABLE CUENTA
    ADD CONSTRAINT CK_CUENTA_TIPO
    CHECK(TIPO IN ('Ahorros', 'Corriente', 'AFC', 'CDT'))
ENABLE;

ALTER TABLE CUENTA
    ADD CONSTRAINT CK_CUENTA_ACTIVA
    CHECK(ACTIVA IN('Y', 'N'))
ENABLE;

-- Creaación de la tabla PRESTAMOS y especificación de sus restricciones
CREATE TABLE PRESTAMO
(
  IDPRESTAMO NUMBER,
  MONTO NUMBER,
  INTERES NUMBER(2, 2),
  SALDOPENDIENTE NUMBER,
  DIAPAGO DATE,
  VALORCUOTAMINIMA NUMBER,
  NUMEROCUOTAS NUMBER,
  TIPO VARCHAR2(255 BYTE),
  PRESTAMOSTIPODOC VARCHAR2(255 BYTE),
  PRESTAMOSNUMDOC VARCHAR2(255 BYTE),
  CONSTRAINT PRESTAMO_PK PRIMARY KEY (IDPRESTAMO)
);

ALTER TABLE PRESTAMO
ADD CONSTRAINT CK_PRESTAMO_TIPO
    CHECK (TIPO IN ('Vivienda', 'Estudio', 'Automovil', 'Calamidad domestica', 'Libre inversion'))
ENABLE;
    
ALTER TABLE PRESTAMO
ADD CONSTRAINT FK_PRESTAMOS
    FOREIGN KEY (PRESTAMOSTIPODOC, PRESTAMOSNUMDOC)
    REFERENCES CLIENTE(TIPODOCUMENTO, NUMERODOCUMENTO)
ENABLE;

-- Creaación de la tabla PUNTOATENCION y especificación de sus restricciones
CREATE TABLE PUNTOATENCION
(
  TIPO VARCHAR2(255 BYTE),
  LOCALIZACION VARCHAR2(255 BYTE),
  PERTENECE NUMBER,
  ASISTETIPODOC VARCHAR2(255 BYTE),
  ASISTENUMDOC NUMBER,
  ATIENDETIPODOC VARCHAR2(255 BYTE),
  ATIENDENUMDOC VARCHAR2(255 BYTE),
  CONSTRAINT PUNTOATENCION_PK PRIMARY KEY (LOCALIZACION, PERTENECE)
);

ALTER TABLE PUNTOATENCION
ADD CONSTRAINT FK_PUNTOATENCION_ASISTE
    FOREIGN KEY (ASISTETIPODOC, ASISTENUMDOC)
    REFERENCES CLIENTE(TIPODOCUMENTO, NUMERODOCUMENTO)
ENABLE;

ALTER TABLE PUNTOATENCION
ADD CONSTRAINT FK_PUNTOATENCION_ATIENDE
    FOREIGN KEY (ATIENDETIPODOC, ATIENDENUMDOC)
    REFERENCES CAJERO(TIPODOCUMENTO, NUMERODOCUMENTO)
ENABLE;

ALTER TABLE PUNTOATENCION
ADD CONSTRAINT UN_PUNTOATENCION
    UNIQUE(ASISTETIPODOC, ASISTENUMDOC, ATIENDETIPODOC, ATIENDENUMDOC)
ENABLE;

-- Creaación de la tabla CAJERO y especificación de sus restricciones
CREATE TABLE CAJEROAUTOMATICO
(
  IDCAJERO NUMBER,
  SONLOCALIZACION VARCHAR2(255 BYTE),
  SONPERTENECE NUMBER,
  CONSTRAINT CAJEROAUTOMATICO_PK PRIMARY KEY (IDCAJERO)
);

ALTER TABLE CAJEROAUTOMATICO
ADD CONSTRAINT FK_CAJEROAUTOMATICO_SON
    FOREIGN KEY (SONLOCALIZACION, SONPERTENECE)
    REFERENCES PUNTOATENCION(LOCALIZACION, PERTENECE)
ENABLE;

-- Creaación de la tabla OPERACIONCUENTA y especificación de sus restricciones
CREATE TABLE OPERACIONCUENTA
(
  IDOPCUENTA NUMBER,
  VALOR NUMBER,
  FECHAHORA TIMESTAMP,
  TIPO VARCHAR2(255 BYTE),
  OPERACIONESCAJERO NUMBER, 
  OPERACIONESPUNTOLOCALIZACION VARCHAR2(255 BYTE),
  OPERACIONESPUNTOPERTENECE NUMBER,
  OPERACIONESTIPODOCCLIENTE VARCHAR2(255 BYTE),
  OPERACIONESNUMDOCCLIENTE NUMBER,
  CONSTRAINT OPERACIONCUENTA_PK PRIMARY KEY (IDOPCUENTA)
);

ALTER TABLE OPERACIONCUENTA
    ADD CONSTRAINT CK_OPERACIONCUENTA_TIPO
    CHECK(TIPO IN('Abrir', 'Cerrar', 'Transferir', 'Retirar', 'Consignar'))
ENABLE;

ALTER TABLE OPERACIONCUENTA
    ADD CONSTRAINT FK_OPERACIONCUENTA_OPERACIONESCAJERO
    FOREIGN KEY (OPERACIONESCAJERO)
    REFERENCES CAJEROAUTOMATICO(IDCAJERO)
ENABLE;

ALTER TABLE OPERACIONCUENTA
    ADD CONSTRAINT FK_OPERACIONCUENTA_OPERACIONESPUNTO
    FOREIGN KEY (OPERACIONESPUNTOLOCALIZACION, OPERACIONESPUNTOPERTENECE)
    REFERENCES PUNTOATENCION(LOCALIZACION, PERTENECE)
ENABLE;

ALTER TABLE OPERACIONCUENTA
    ADD CONSTRAINT FK_OPERACIONCUENTA_OPCLIENTE
    FOREIGN KEY (OPERACIONESTIPODOCCLIENTE, OPERACIONESNUMDOCCLIENTE)
    REFERENCES CLIENTE(TIPODOCUMENTO, NUMERODOCUMENTO)
ENABLE;

-- Creaación de la tabla OPERACIONCDT y especificación de sus restricciones
CREATE TABLE OPERACIONCDT
(
  IDOPCDT NUMBER,
  VALOR NUMBER,
  FECHAHORA TIMESTAMP,
  TIPO VARCHAR2(255 BYTE),
  OPERACIONESPUNTOLOCALIZACION VARCHAR2(255 BYTE),
  OPERACIONESPUNTOPERTENECE NUMBER,
  OPERACIONESTIPODOCCLIENTE VARCHAR2(255 BYTE),
  OPERACIONESNUMDOCCLIENTE NUMBER,
  CONSTRAINT OPERACIONCDT_PK PRIMARY KEY (IDOPCDT)
);

ALTER TABLE OPERACIONCDT
    ADD CONSTRAINT CK_OPERACIONCDT_TIPO
    CHECK (TIPO IN ('Abrir', 'Renovar', 'Cerrar'))
ENABLE;

ALTER TABLE OPERACIONCDT
    ADD CONSTRAINT FK_OPERACIONCDT_OPERACIONESPUNNTO
    FOREIGN KEY (OPERACIONESPUNTOLOCALIZACION, OPERACIONESPUNTOPERTENECE)
    REFERENCES PUNTOATENCION(LOCALIZACION, PERTENECE)
ENABLE;

ALTER TABLE OPERACIONCDT
    ADD CONSTRAINT FK_OPERACIONCDT_OPCLIENTE
    FOREIGN KEY (OPERACIONESTIPODOCCLIENTE, OPERACIONESNUMDOCCLIENTE)
    REFERENCES CLIENTE(TIPODOCUMENTO, NUMERODOCUMENTO)
ENABLE;

-- Creaación de la tabla OPERACIONDEPOSITOSINVERSION y especificación de sus restricciones
CREATE TABLE OPERACIONDEPOSITOSINVERSION
(
  IDOPDEPOSITOSINVERSION NUMBER,
  VALOR NUMBER,
  FECHAHORA TIMESTAMP,
  TIPO VARCHAR2(255 BYTE),
  OPERACIONESPUNTOLOCALIZACION VARCHAR2(255 BYTE),
  OPERACIONESPUNTOPERTENECE NUMBER,
  OPERACIONESTIPODOCCLIENTE VARCHAR2(255 BYTE),
  OPERACIONESNUMDOCCLIENTE NUMBER,
  CONSTRAINT OPERACIONESDEPOSITOINVERSION_PK PRIMARY KEY (IDOPDEPOSITOSINVERSION)
);

ALTER TABLE OPERACIONDEPOSITOSINVERSION
    ADD CONSTRAINT CK_OPERACIONDEPOSITOSINVERSION_TIPO
    CHECK (TIPO IN ('Abrir', 'Liquidar', 'Cerrar', 'Remover'))
ENABLE;

ALTER TABLE OPERACIONDEPOSITOSINVERSION
    ADD CONSTRAINT FK_OPERACIONDEPOSITOSINVERSION_OPERACIONESPUNNTO
    FOREIGN KEY (OPERACIONESPUNTOLOCALIZACION, OPERACIONESPUNTOPERTENECE)
    REFERENCES PUNTOATENCION(LOCALIZACION, PERTENECE)
ENABLE;

ALTER TABLE OPERACIONDEPOSITOSINVERSION
    ADD CONSTRAINT FK_OPERACIONDEPOSITOSINVERSION_OPCLIENTE
    FOREIGN KEY (OPERACIONESTIPODOCCLIENTE, OPERACIONESNUMDOCCLIENTE)
    REFERENCES CLIENTE(TIPODOCUMENTO, NUMERODOCUMENTO)
ENABLE;

-- Creaación de la tabla OPERACIONPRESTAMO y especificación de sus restricciones
CREATE TABLE OPERACIONPRESTAMO
(
  IDOPPRESTAMO NUMBER,
  VALOR NUMBER,
  FECHAHORA TIMESTAMP,
  TIPO VARCHAR2(255 BYTE),
  OPERACIONESPUNTOLOCALIZACION VARCHAR2(255 BYTE),
  OPERACIONESPUNTOPERTENECE NUMBER,
  OPERACIONESGERENTETIPODOC VARCHAR2(255 BYTE),
  OPERACIONESGERENTENUMDOC NUMBER,
  OPERACIONESTIPODOCCLIENTE VARCHAR2(255 BYTE),
  OPERACIONESNUMDOCCLIENTE NUMBER,
  CONSTRAINT OPERACIONESPRESTAMO_PK PRIMARY KEY (IDOPPRESTAMO)
);

ALTER TABLE OPERACIONPRESTAMO
    ADD CONSTRAINT CK_OPERACIONESPRESTAMO_TIPO
    CHECK (TIPO IN ('Solicitar', 'Aprobar', 'PagarCuota', 'PagarCuotaExtraordinaria', 'Rechazar'))
ENABLE;

ALTER TABLE OPERACIONPRESTAMO
    ADD CONSTRAINT FK_OPERACIONESPRESTAMO_OPERACIONESPUNTO
    FOREIGN KEY (OPERACIONESPUNTOLOCALIZACION, OPERACIONESPUNTOPERTENECE)
    REFERENCES PUNTOATENCION(LOCALIZACION, PERTENECE)
ENABLE;

ALTER TABLE OPERACIONPRESTAMO
    ADD CONSTRAINT FK_OPERACIONESPRESTAMO_OPERACIONESGERENTE
    FOREIGN KEY (OPERACIONESGERENTETIPODOC, OPERACIONESGERENTENUMDOC)
    REFERENCES GERENTEOFICINA(TIPODOCUMENTO, NUMERODOCUMENTO)
ENABLE;

ALTER TABLE OPERACIONPRESTAMO
    ADD CONSTRAINT FK_OPERACIONPRESTAMO_OPCLIENTE
    FOREIGN KEY (OPERACIONESTIPODOCCLIENTE, OPERACIONESNUMDOCCLIENTE)
    REFERENCES CLIENTE(TIPODOCUMENTO, NUMERODOCUMENTO)
ENABLE;

-- Creaación de la tabla OPERACIONESACCIONES y especificación de sus restricciones
CREATE TABLE OPERACIONESACCIONES
(
  IDOPACCIONES NUMBER,
  VALOR NUMBER,
  FECHAHORA TIMESTAMP,
  TIPO VARCHAR2(255 BYTE),
  OPERACIONESPUNTOLOCALIZACION VARCHAR2(255 BYTE),
  OPERACIONESPUNTOPERTENECE NUMBER,
  OPERACIONESTIPODOCCLIENTE VARCHAR2(255 BYTE),
  OPERACIONESNUMDOCCLIENTE NUMBER,
  CONSTRAINT OPERACIONESACCIONES_PK PRIMARY KEY (IDOPACCIONES)
);

ALTER TABLE OPERACIONESACCIONES
    ADD CONSTRAINT CK_OPERACIONESACCIONES_TIPO
    CHECK (TIPO IN ('Vender', 'Comprar'))
ENABLE;

ALTER TABLE OPERACIONESACCIONES
    ADD CONSTRAINT FK_OPERACIONESACCIONES_OPERACIONESPUNNTO
    FOREIGN KEY (OPERACIONESPUNTOLOCALIZACION, OPERACIONESPUNTOPERTENECE)
    REFERENCES PUNTOATENCION(LOCALIZACION, PERTENECE)
ENABLE;

ALTER TABLE OPERACIONESACCIONES
    ADD CONSTRAINT FK_OPERACIONESACCIONES_OPCLIENTE
    FOREIGN KEY (OPERACIONESTIPODOCCLIENTE, OPERACIONESNUMDOCCLIENTE)
    REFERENCES CLIENTE(TIPODOCUMENTO, NUMERODOCUMENTO)
ENABLE;

COMMIT;