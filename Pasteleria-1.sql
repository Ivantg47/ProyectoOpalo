
CREATE TABLE Cancelacion (id_cancelacion SERIAL PRIMARY KEY ,descripcion VARCHAR(250) NOT NULL);
CREATE TABLE Compras (id_compra SERIAL NOT NULL, fechaCompra DATE NOT NULL, id_cancelacion INT REFERENCES Cancelacion(id_cancelacion), estado VARCHAR(75) NOT NULL DEFAULT 'REALIZADA', CONSTRAINT PK_Compras PRIMARY KEY (id_compra));
CREATE TABLE Cliente (id_cliente SERIAL PRIMARY KEY, nombre VARCHAR(50) NOT NULL, aPaterno VARCHAR(50) NOT NULL, aMaterno VARCHAR(50) NOT NULL, correo VARCHAR(100) NOT NULL, telefono VARCHAR(10) NOT NULL, direccion VARCHAR(100)	NOT NULL);
CREATE TABLE Producto (id_producto SERIAL NOT NULL, nombre VARCHAR(100) NOT NULL, descripcion VARCHAR (100) NOT NULL, existenciaMinima INT NOT NULL, existenciaMaxima INT NOT NULL, existenciaActual INT NOT NULL, CONSTRAINT PK_Producto PRIMARY KEY (id_producto));
CREATE TABLE Factura (id_factura SERIAL PRIMARY KEY, fecha DATE NOT NULL);
CREATE TABLE Insumo (id_Insumo SERIAL PRIMARY KEY, unidadMedida VARCHAR(25) NOT NULL, nombre VARCHAR(50) NOT NULL, existenciaMinima FLOAT NOT NULL, existenciaMaxima FLOAT NOT NULL, existenciaActual FLOAT NOT NULL);
CREATE TABLE Compra_Insumo (id_compra INT REFERENCES Compras(id_compra), id_insumo INT REFERENCES Insumo(id_Insumo), cantidad FLOAT NOT NULL, CHECK (cantidad > 0), costoTotal FLOAT NOT NULL, CONSTRAINT PK_Compra_Insumo PRIMARY KEY (id_compra, id_insumo));
CREATE TABLE Receta (id_insumo INT REFERENCES Insumo(id_Insumo), id_producto INT REFERENCES Producto(id_producto), cantidad FLOAT NOT NULL, CHECK (cantidad > 0), CONSTRAINT PK_Receta PRIMARY KEY (id_insumo, id_producto));
CREATE TABLE Precio (id_producto INT REFERENCES Producto(id_producto), fecha DATE NOT NULL, precio FLOAT NOT NULL, CHECK (precio > 0), CONSTRAINT PK_Precio PRIMARY KEY (fecha, id_producto));
CREATE TABLE Venta (id_venta SERIAL PRIMARY KEY, tipoPago VARCHAR(30) NOT NULL, id_cancelacion INT REFERENCES Cancelacion(id_cancelacion), fecha DATE NOT NULL, estado VARCHAR(75) NOT NULL);
CREATE TABLE Venta_Producto (id_venta INT REFERENCES Venta(id_venta), id_producto INT REFERENCES Producto(id_producto), cantidad INT NOT NULL, CHECK (cantidad > 0), CONSTRAINT PK_Ven_Prod PRIMARY KEY (id_venta, id_producto));
CREATE TABLE Venta_Cliente (id_venta INT REFERENCES Venta(id_venta), id_cliente INT REFERENCES Cliente(id_cliente), id_factura INT REFERENCES Factura(id_factura), CONSTRAINT PK_Venta_Producto PRIMARY KEY (id_venta, id_cliente));


/*
DROP TABLE Venta_Cliente;
DROP TABLE Venta_Producto;
DROP TABLE Venta;
DROP TABLE Precio;
DROP TABLE Receta;
DROP TABLE Compra_Insumo;
DROP TABLE Insumo;
DROP TABLE Factura;
DROP TABLE Producto;
DROP TABLE Cliente;
DROP TABLE Compras;
DROP TABLE Cancelacion;
*/

/*TABLAS*/

CREATE TABLE Cancelacion (id_cancelacion 	SERIAL 		 PRIMARY KEY 
						,descripcion 		VARCHAR(250) NOT NULL
						);


CREATE TABLE Compras (id_compra 	SERIAL 		NOT NULL
					 ,fechaCompra 	DATE 		NOT NULL
					 ,id_cancelacion INT 		REFERENCES Cancelacion(id_cancelacion)
					 ,estado 		VARCHAR(75) NOT NULL DEFAULT 'REALIZADA'
					 ,CONSTRAINT 	PK_Compras 	PRIMARY KEY (id_compra)
					 );


CREATE TABLE Cliente (id_cliente 	SERIAL 			PRIMARY KEY
					,nombre 		VARCHAR(50) 	NOT NULL
					,aPaterno 		VARCHAR(50) 	NOT NULL
					,aMaterno 		VARCHAR(50) 	NOT NULL
					,correo 		VARCHAR(100) 	NOT NULL
					,telefono 		VARCHAR(10) 	NOT NULL
					,direccion 		VARCHAR(100)	NOT NULL
					);


CREATE TABLE Producto (id_producto 			SERIAL 			NOT NULL
						,nombre 			VARCHAR(100) 	NOT NULL
						,descripcion 		VARCHAR (100) 	NOT NULL
						,existenciaMinima 	INT 			NOT NULL
						,existenciaMaxima 	INT 			NOT NULL
						,existenciaActual 	INT 			NOT NULL
						,CONSTRAINT 		PK_Producto 	PRIMARY KEY (id_producto)
						);


CREATE TABLE Factura (id_factura 	SERIAL 	PRIMARY KEY
					 ,fecha			DATE 	NOT NULL
					 );


CREATE TABLE Insumo (id_Insumo 			SERIAL 		PRIMARY KEY
					,unidadMedida 		VARCHAR(25) NOT NULL
					,nombre 			VARCHAR(50) NOT NULL
					,existenciaMinima 	FLOAT 		NOT NULL
					,existenciaMaxima 	FLOAT 		NOT NULL
					,existenciaActual 	FLOAT 		NOT NULL
					);


CREATE TABLE Compra_Insumo (id_compra 	INT 	REFERENCES Compras(id_compra)
							,id_insumo 	INT 	REFERENCES Insumo(id_Insumo)
							,cantidad 	FLOAT 	NOT NULL, CHECK (cantidad > 0)
							,costoTotal FLOAT 	NOT NULL
							,CONSTRAINT PK_Compra_Insumo PRIMARY KEY (id_compra, id_insumo)
							);


CREATE TABLE Receta (id_insumo 		INT 		REFERENCES Insumo(id_Insumo)
					,id_producto 	INT 		REFERENCES Producto(id_producto)
					,cantidad 		FLOAT 		NOT NULL, CHECK (cantidad > 0),
					CONSTRAINT 		PK_Receta 	PRIMARY KEY (id_insumo, id_producto)
					);


CREATE TABLE Precio (id_producto 	INT 		REFERENCES Producto(id_producto)
					,fecha 			DATE 		NOT NULL
					,precio 		FLOAT 		NOT NULL, CHECK (precio > 0)
					,CONSTRAINT 	PK_Precio 	PRIMARY KEY (fecha, id_producto)
					);


CREATE TABLE Venta (id_venta 		SERIAL 		PRIMARY KEY
					,tipoPago 		VARCHAR(30) NOT NULL
					,id_cancelacion INT 		REFERENCES Cancelacion(id_cancelacion)
					,fecha 			DATE 		NOT NULL
					,estado 		VARCHAR(75) NOT NULL
					);


CREATE TABLE Venta_Producto (id_venta 		INT 		REFERENCES Venta(id_venta)
							,id_producto 	INT 		REFERENCES Producto(id_producto)
							,cantidad 		INT 		NOT NULL, CHECK (cantidad >= 0)
							,CONSTRAINT 	PK_Ven_Prod PRIMARY KEY (id_venta, id_producto)
							);


CREATE TABLE Venta_Cliente (id_venta 	INT 				REFERENCES Venta(id_venta)
							,id_cliente INT 				REFERENCES Cliente(id_cliente)
							,id_factura INT 				REFERENCES Factura(id_factura)
							,CONSTRAINT PK_Venta_Producto 	PRIMARY KEY (id_venta, id_cliente)
							);



/*VISTAS*/

CREATE OR REPLACE VIEW Precio_Fecha AS SELECT id_producto AS codigo, MAX(fecha) AS fecha FROM Precio GROUP BY id_producto;
	
CREATE OR REPLACE VIEW DatoProducto AS SELECT Pr.id_producto AS id_producto, Pr.nombre AS nombre, Pr.descripcion AS descripcion,Pr.existenciaMinima AS existenciaMinima, 
	Pr.existenciaMaxima AS existenciaMaxima,Pr.existenciaActual AS existenciaActual, P.precio AS precio 
	FROM Producto Pr INNER JOIN Precio P ON(Pr.id_producto = P.id_producto) INNER JOIN Precio_Fecha Pf ON(Pr.id_producto = Pf.codigo AND P.fecha = Pf.fecha);
	
CREATE OR REPLACE  VIEW NombreConcatenado AS SELECT id_cliente, nombre, aPaterno, aMaterno, correo, telefono, direccion,
	CONCAT(nombre, ' ', aPaterno, ' ', aMaterno) AS completo FROM Cliente;

CREATE OR REPLACE  VIEW DatosVenta AS
SELECT V.id_venta, C.id_cliente, fecha, CONCAT(nombre, ' ', aPaterno, ' ', aMaterno) AS nombre, V.estado
FROM Venta V INNER JOIN Venta_Cliente VC ON (V.id_venta = VC.id_venta)
INNER JOIN Cliente C ON (VC.id_cliente = C.id_cliente);



/*DISPARADORES*/

DELIMITER //
CREATE TRIGGER reducir_producto 
AFTER INSERT ON Venta_Producto FOR EACH ROW 
BEGIN 
UPDATE Producto SET existenciaActual = (existenciaActual - NEW.cantidad) WHERE id_producto = NEW.id_producto; 
END;//
DELIMITER ;

DELIMITER //
DROP IF EXIST 
CREATE TRIGGER aumentar_insumo 
AFTER INSERT ON Compra_Insumo FOR EACH ROW 
BEGIN 
UPDATE Insumo SET existenciaActual = (existenciaActual + NEW.cantidad) WHERE id_insumo = NEW.id_insumo; 
END;//
DELIMITER ;



/*PROCEDIMIENTOS*/

DELIMITER //
DROP PROCEDURE IF EXISTS reportes;
CREATE PROCEDURE reportes (IN FechaInicio DATE, IN FechaFin DATE)
BEGIN
DROP TEMPORARY TABLE IF EXISTS Reporte;
CREATE TEMPORARY TABLE Reporte(id_producto INT NOT NULL
,descripcion VARCHAR(50) NOT NULL
,cantidad INT NOT NULL
,total FLOAT NOT NULL
,fecha DATE NOT NULL
,id INT NOT NULL	
);
WHILE FechaInicio <= FechaFin do 
INSERT INTO Reporte (
SELECT P.id_producto AS id, CONCAT(P.nombre, ' ', P.descripcion) AS nombre, VP.cantidad, (VP.cantidad * Pr.precio), V.fecha, V.id_venta
FROM Venta AS V INNER JOIN Venta_Producto AS VP ON (VP.id_venta = V.id_venta)
INNER JOIN Producto AS P ON (VP.id_producto = P.id_producto)
INNER JOIN (SELECT id_producto AS idProd, MAX(fecha) AS fecha, precio FROM Precio
WHERE fecha <= FechaInicio GROUP BY id_producto) AS Pr ON (P.id_producto = Pr.idProd)
WHERE V.fecha = FechaInicio
);
SET FechaInicio = DATE_ADD(FechaInicio,INTERVAL 1 DAY);
END WHILE;
SELECT id_producto, descripcion, SUM(cantidad) AS cantidad, SUM(total) AS total FROM Reporte GROUP BY id_producto, descripcion;
END; //
DELIMITER ;


DELIMITER //
DROP PROCEDURE IF EXISTS cancelar;
CREATE PROCEDURE cancelar (IN motivo VARCHAR(250))
BEGIN
DECLARE id INT;
SET id = (SELECT COUNT(*) FROM Cancelacion WHERE descripcion = motivo);
IF  id = 0 THEN
INSERT INTO Cancelacion (descripcion) VALUES (motivo);
END IF;
SELECT id_cancelacion FROM Cancelacion WHERE descripcion = motivo;
END; //
DELIMITER ;


