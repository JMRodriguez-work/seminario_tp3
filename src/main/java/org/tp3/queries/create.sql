CREATE DATABASE IF NOT EXISTS seminario;
USE seminario;

CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    contraseña VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    rol VARCHAR(50) NOT NULL
);

CREATE TABLE historial_cargas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME NOT NULL,
    id_usuario INT,
    ruta VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    dni_cuit_cuil VARCHAR(20) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE polizas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT,
    poliza VARCHAR(255) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    id_archivo INT,
    rama VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id),
    FOREIGN KEY (id_archivo) REFERENCES historial_cargas(id) ON DELETE CASCADE
);

CREATE TABLE deuda (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT,
    suma_asegurada DECIMAL(15, 2) NOT NULL,
    prima DECIMAL(15, 2) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_vto DATE NOT NULL,
    moneda VARCHAR(255) NOT NULL,
    rama VARCHAR(255) NOT NULL,
    id_archivo INT,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id),
    FOREIGN KEY (id_archivo) REFERENCES historial_cargas(id) ON DELETE CASCADE
);