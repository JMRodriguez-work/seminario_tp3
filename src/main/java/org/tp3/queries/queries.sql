SELECT * FROM clientes;

SELECT * FROM polizas;

SELECT * FROM deuda;

SELECT * FROM historial_cargas;

SELECT * FROM usuario;

SELECT p.* 
FROM polizas p
JOIN clientes c ON p.id_cliente = c.id
WHERE c.nombre = 'Juan Perez';

SELECT d.* 
FROM deuda d
JOIN clientes c ON d.id_cliente = c.id
WHERE c.nombre = 'Maria Lopez';
