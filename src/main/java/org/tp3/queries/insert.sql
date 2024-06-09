INSERT INTO usuario (nombre, contraseña, email, rol) VALUES
('Admin', 'admincontra', 'admin@gmail.com', 'Administrador'),
('Martin', 'usuariocontra', 'usuario@gmail.com', 'Usuario');

INSERT INTO clientes (nombre, dni_cuit_cuil, email) VALUES
('Juan Perez', '20304050607', 'juan.perez@gmail.com'),
('Maria Lopez', '27304050608', 'maria.lopez@gmail.com'),
('Carlos Gomez', '24304050609', 'carlos.gomez@gmail.com');

INSERT INTO historial_cargas (fecha, id_usuario, ruta) VALUES
(NOW(), 1, '/uploads/polizas_2024_05_16.csv'),
(NOW(), 2, '/uploads/deudas_2024_05_16.csv');

INSERT INTO polizas (id_cliente, poliza, fecha_inicio, fecha_fin, id_archivo, rama) VALUES
(1, '123456', '2024-01-01', '2025-01-01', 1, 'Auto'),
(2, '654321', '2024-01-01', '2025-01-01', 1, 'Hogar'),
(3, '789012', '2024-01-01', '2025-01-01', 1, 'Vida');

INSERT INTO deuda (id_cliente, suma_asegurada, prima, fecha_inicio, fecha_vto, moneda,rama ,id_archivo) VALUES
(1, 100000.00, 5000.00, '2024-01-01', '2025-01-01', 'Dólar', 'Auto', 2),
(2, 200000.00, 10000.00, '2024-01-01', '2025-01-01', 'Dólar', 'Auto', 2),
(3, 150000.00, 7500.00, '2024-01-01', '2025-01-01', 'Peso', 'Auto', 2);
