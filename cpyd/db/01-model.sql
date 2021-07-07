BEGIN TRANSACTION;

--
-- Tabla para credenciales usadas en el servicio
--

DROP TABLE IF EXISTS credentials CASCADE;
CREATE TABLE credentials(
	pk bigserial NOT NULL,
	token varchar(255) NOT NULL,
	app varchar(255) NOT NULL,
	password varchar(255) NOT NULL,
	active boolean DEFAULT '0',
	UNIQUE(token),
	PRIMARY KEY(pk)
);

CREATE UNIQUE INDEX ON credentials(UPPER(TRIM(both FROM app));

--
-- Tabla para almacenar x
--


