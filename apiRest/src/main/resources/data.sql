INSERT INTO marcas (marca_id, nombre_marca) VALUES (1, 'Toyota');
INSERT INTO marcas (marca_id, nombre_marca) VALUES (2, 'Mazda');
INSERT INTO marcas (marca_id, nombre_marca) VALUES (3, 'Honda');
INSERT INTO marcas (marca_id, nombre_marca) VALUES (4, 'Renault');

INSERT INTO coches (color, nombre_modelo, marca_id) VALUES ('Azul','Corolla', 1);
INSERT INTO coches (color, nombre_modelo, marca_id) VALUES ('Rojo','Mazda3', 2);
INSERT INTO coches (color, nombre_modelo, marca_id) VALUES ('Negro','Civic', 3);
INSERT INTO coches (color, nombre_modelo, marca_id) VALUES ('Verde','Twingo', 4);

INSERT INTO precios (fecha_inicio, fecha_fin, precio ,coche_id) VALUES ('2021-07-12','2021-07-20','23.000', 1);
INSERT INTO precios (fecha_inicio, fecha_fin, precio ,coche_id) VALUES ('2020-05-01','2020-07-12','25.000', 2);
INSERT INTO precios (fecha_inicio, fecha_fin, precio ,coche_id) VALUES ('2020-05-02','2020-07-11','22.000', 2);
INSERT INTO precios (fecha_inicio, fecha_fin, precio ,coche_id) VALUES ('2020-01-13','2020-12-12','26.000', 3);
INSERT INTO precios (fecha_inicio, fecha_fin, precio ,coche_id) VALUES ('2020-02-16','2020-04-12','18.000', 4);

select * from coches;
select * from marcas;
select * from precios;

