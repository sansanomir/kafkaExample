drop table IF EXISTS precios;
drop table IF EXISTS coches;
drop table IF EXISTS marcas;

CREATE TABLE marcas (
     marca_id INT NOT NULL,
     nombre_marca VARCHAR(64) NOT NULL
 );

CREATE TABLE coches (
      coche_id INT AUTO_INCREMENT,
      color VARCHAR(64) NOT NULL,
      nombre_modelo VARCHAR(64) NOT NULL,
      marca_id INT NOT NULL,
      PRIMARY KEY (coche_id),
      FOREIGN KEY (marca_id) REFERENCES marcas (marca_id)
);

CREATE TABLE precios (
      precio_id INT AUTO_INCREMENT,
      fecha_inicio VARCHAR(64) NOT NULL,
      fecha_fin VARCHAR(64) NOT NULL,
      precio FLOAT NOT NULL,
      coche_id INT NOT NULL,
      PRIMARY KEY (precio_id),
      FOREIGN KEY (coche_id) REFERENCES coches (coche_id)
);

