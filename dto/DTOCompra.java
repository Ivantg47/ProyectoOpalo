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

	//Atributos de la clase
	private int eIdInsumo;
	private float fCantidad, fTotal, fFinal;
	private String sFechaCompra, sNombre;

	/**
	 * Método constructor vacío de la clase DTO
	 */
	public DTOCompra(){

	}

	/**
	 * Constructor con parametros de la clase DTO
	 * @param eIdInsumo un entero que hace referencia al id del insumo que se compró.
	 * @param fCantidad un flotante con la cantidad comprada del insumo.
	 * @param fTotal un flotante con el costo total de la compra.
	 * @param sFechaCompra una cadena con la fecha que será convertida a sql.date
	 * @param sNombre una cadena con la cadena de insumo ingresada por el usuario.
	 */
	public DTOCompra(int eIdInsumo, float fCantidad, float fTotal, String sFechaCompra, String sNombre){

		this.eIdInsumo = eIdInsumo;
		this.fCantidad = fCantidad;
		this.fTotal = fTotal;
		this.sFechaCompra = sFechaCompra;
		this.sNombre = sNombre;

	}

	/**
	 * Método que recibe un objeto y le asigna sus valores a el que llama.
	 * @param objeto un objeto DTO lleno.
	 */
	public void extraer(DTOCompra objeto){

		this.fCantidad = objeto.fCantidad;
		this.fTotal = objeto.fTotal;
		this.sFechaCompra = objeto.sFechaCompra;
		this.sNombre = objeto.sNombre;

	}

	/**
	 * getter del atributo eIdInsumo
	 * @return eIdInsumo entero que hace referencia al id del insumo que se compró.
	 */
	public int getIdInsumo(){
		return eIdInsumo;
	}	

	/**
	 * getter del atributo fCantidad
	 * @return fCantidad flotante con la cantidad comprada del insumo.
	 */
	public float getCantidad(){
		return fCantidad;
	}	

	/**
	 * getter del atributo fTotal
	 * @return fTotal flotante con el costo total de la compra.
	 */
	public float getTotal(){
		return fTotal;
	}	

	/**
	 * getter del atributo fFinal
	 * @return fFinal
	 */
	public float getFinal(){
		return fFinal;
	}	

	/**
	 * getter del atributo sFechaCompra
	 * @return sFechaCompra una cadena con la fecha que será convertida a sql.date
	 */
	public String getFechaCompra(){
		return sFechaCompra;
	}	

	/**
	 * getter del atributo sNombre
	 * @return sNombre cadena con la cadena de insumo ingresada por el usuario.
	 */
	public String getNombre(){
		return sNombre;
	}

	/**
	 * Método setter del atributo eIdInsumo
	 * @param eIdInsumo entero que hace referencia al id del insumo que se compró.
	 */
	public void setIdInsumo(int eIdInsumo){
		this.eIdInsumo = eIdInsumo;
	}

	/**
	 * Método setter del atributo fCantidad 
	 * @param fCantidad flotante con la cantidad comprada del insumo.
	 */
	public void setCantidad(float fCantidad){
		this.fCantidad = fCantidad;
	}

	/**
	 * Método setter del atributo fFinal
	 * @param flotante flotante con el costo total de la compra.
	 */
	public void setFinal(float flotante){
		this.fFinal = flotante;
	}

	/**
	 * Método setter del atributo fTotal
	 * @param fTotal
	 */
	public void setTotal(float fTotal){
		this.fTotal = fTotal;
	}

	/**
	 * Método setter del atributo sFechaCompra
	 * @param sFechaCompra una cadena con la fecha que será convertida a sql.date
	 */
	public void setFechaCompra(String sFechaCompra){
		this.sFechaCompra = sFechaCompra;
	}

	/**
	 * Método setter del atributo sNombre
	 * @param sNombre cadena con la cadena de insumo ingresada por el usuario.
	 */
	public void setNombre(String sNombre){
		this.sNombre = sNombre;
	}

}
