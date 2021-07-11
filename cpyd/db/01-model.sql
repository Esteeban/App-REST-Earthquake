BEGIN TRANSACTION;

--
-- Tabla para credenciales usadas en el servicio
--

DROP TABLE IF EXISTS credentials CASCADE;
CREATE TABLE credentials (
	pk bigserial NOT NULL,
	token varchar(255) NOT NULL,
	app varchar(255) NOT NULL,
	password varchar(255) NOT NULL,
	active boolean DEFAULT '0',
	UNIQUE(token),
	PRIMARY KEY(pk)
);

CREATE UNIQUE INDEX ON credentials(UPPER(TRIM(both FROM app)));

DROP TABLE IF EXISTS sismo CASCADE;
CREATE TABLE sismo (
    pk bigserial NOT NULL,
    fechaHora timestamp NOT NULL,
    latitud float NOT NULL,
    longitud float NOT NULL,
    profundidad integer NOT NULL,
    magnitud float NOT NULL,
    agencia varchar (5) NOT NULL,
    referencia varchar(255) NOT null,
    PRIMARY KEY(pk)
);
CREATE INDEX ON sismo(fechaHora);

COMMIT;