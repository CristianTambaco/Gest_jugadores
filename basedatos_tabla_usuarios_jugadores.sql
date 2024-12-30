-- Crear la base de datos
CREATE DATABASE futbol_db;

-- Usar la base de datos
USE futbol_db;

-- Crear tabla de usuarios
CREATE TABLE Usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50) NOT NULL,
    contrasena VARCHAR(100) NOT NULL
);

-- Crear tabla de jugadores
CREATE TABLE Jugadores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    posicion VARCHAR(50) NOT NULL,
    equipo VARCHAR(50) NOT NULL,
    edad INT NOT NULL
);

INSERT INTO Usuarios(usuario, contrasena)
VALUES
("usuario", "usuario123");

INSERT INTO Jugadores(nombre, posicion, equipo, edad)
VALUES
("Joe", "Delantero", "Los Invencibles", 25);


