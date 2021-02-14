/**
 * Clase DTO de la compra.
 * @author Diego Puebla Aldama
 * @version 1.0
 */

/* Related tables:
CREATE TABLE Compras (	idCompras serial NOT NULL,
						fechaCompra DATE NOT NULL,
						CONSTRAINT PK_Compras PRIMARY KEY (idCompras));

CREATE TABLE Compra_Insumo (	id_Compra INT REFERENCES Compras(idCompras), 
				id_Insumo INT REFERENCES Insumos(idInsumos), 
				Cantidad FLOAT NOT NULL, 
				CostoTotal   FLOAT NOT NULL, 
				CONSTRAINT PK_Compra_Insumo PRIMARY KEY (id_Compra, id_Insumo));
 */
public class DTOCompra {
    
}
