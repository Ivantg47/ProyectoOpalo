/**
 * Clase DTO de la compra.
 * @author Diego Puebla Aldama
 * @version 1.0
 */

/* Related tables:
CREATE TABLE Compras (	idCompras serial NOT NULL,
						fechaCompra DATE NOT NULL,
						CONSTRAINT PK_Compras PRIMARY KEY (idCompras));

CREATE TABLE Compra_Insumo (	id_Compra INT REFERENCES Compras(idCompras), (SELECT last_insert_id())
				id_Insumo INT REFERENCES Insumos(idInsumos), 
				Cantidad FLOAT NOT NULL, 
				CostoTotal   FLOAT NOT NULL, 
				CONSTRAINT PK_Compra_Insumo PRIMARY KEY (id_Compra, id_Insumo));
 */

package ProyectoOpalo.dto;

public class DTOCompra {

	private int eIdInsumo;
	private float fCantidad, fTotal, fFinal;
	private String sFechaCompra, sNombre;

	//Constructor Vacío
	public DTOCompra(){

	}

	//Constructor con parametros.
	public DTOCompra(int eIdInsumo, float fCantidad, float fTotal, String sFechaCompra, String sNombre){

		this.eIdInsumo = eIdInsumo;
		this.fCantidad = fCantidad;
		this.fTotal = fTotal;
		this.sFechaCompra = sFechaCompra;
		this.sNombre = sNombre;

	}

	public void extraer(DTOCompra objeto){

		this.fCantidad = objeto.fCantidad;
		this.fTotal = objeto.fTotal;
		this.sFechaCompra = objeto.sFechaCompra;
		this.sNombre = objeto.sNombre;

	}

	public int getIdInsumo(){
		return eIdInsumo;
	}	

	public float getCantidad(){
		return fCantidad;
	}	

	public float getTotal(){
		return fTotal;
	}	

	public float getFinal(){
		return fFinal;
	}	

	public String getFechaCompra(){
		return sFechaCompra;
	}	

	public String getNombre(){
		return sNombre;
	}

	public void setIdInsumo(int eIdInsumo){
		this.eIdInsumo = eIdInsumo;
	}

	public void setCantidad(float fCantidad){
		this.fCantidad = fCantidad;
	}

	public void setFinal(float flotante){
		this.fFinal = flotante;
	}

	public void setTotal(float fTotal){
		this.fTotal = fTotal;
	}

	public void setFechaCompra(String sFechaCompra){
		this.sFechaCompra = sFechaCompra;
	}

	public void setNombre(String sNombre){
		this.sNombre = sNombre;
	}

}
