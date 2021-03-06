DROP DATABASE IF EXISTS calculadora_db;
CREATE DATABASE calculadora_db;
USE calculadora_db;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS usuarios;
CREATE TABLE usuarios (
	id_usuario MEDIUMINT(5) UNSIGNED NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(20) NOT NULL,
	password VARCHAR(20) NOT NULL,
	PRIMARY KEY (id_usuario)
);

DELIMITER $$
CREATE TRIGGER new_user BEFORE INSERT ON usuarios FOR EACH ROW
BEGIN
	IF ((SELECT COUNT(*) FROM usuarios WHERE nombre = new.nombre) > 0) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Ya existe un usuario con este nombre.'; 
	END IF;
END $$
DELIMITER ;

DROP TABLE IF EXISTS operaciones;
CREATE TABLE operaciones (
	id_operacion INT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	id_usuario MEDIUMINT(5) UNSIGNED NOT NULL,
	fecha DATETIME NOT NULL,
	operacion TEXT NOT NULL,
	operacion_formateada TEXT NOT NULL,
	resultado DECIMAL(65,30) NOT NULL,
	PRIMARY KEY (id_operacion),
	CONSTRAINT fk_operaciones_usuarios FOREIGN KEY (id_usuario) REFERENCES usuarios (id_usuario) ON DELETE RESTRICT ON UPDATE CASCADE 
);

SET FOREIGN_KEY_CHECKS = 1;