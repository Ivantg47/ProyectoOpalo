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

package ProyectoOpalo.dto;

public class DTOCompra {

	private int eIdCompra;
	private float fCantidad, fTotal;
	private String sFechaCompra;

	//Constructor Vacío
	public DTOCompra(){

	}

	//Constructor con parametros.
	public DTOCompra(int eIdCompra, float fCantidad, float fTotal, String sFechaCompra){

		this.eIdCompra = eIdCompra;
		this.fCantidad = fCantidad;
		this.fTotal = fTotal;
		this.sFechaCompra = sFechaCompra;

	}

	public int getIdCompra(){
		return eIdCompra;
	}	

	public float getCantidad(){
		return fCantidad;
	}	

	public float getTotal(){
		return fTotal;
	}	

	public String getFechaCompra(){
		return sFechaCompra;
	}	

	public void setIdCompra(int eIdCompra){
		this.eIdCompra = eIdCompra;
	}

	public void setIdCompra(float fCantidad){
		this.fCantidad = fCantidad;
	}

	public void setIdCompra(float fTotal){
		this.fTotal = fTotal;
	}

	public void setIdCompra(String sFechaCompra){
		this.sFechaCompra = sFechaCompra;
	}
	
}
