/**
 * Clase DTO de la compra.
 * @author Diego Puebla Aldama
 * @version 1.0
 */


package ProyectoOpalo.dto;

public class DTOCompra {

	//Atributos de la clase
	private int eId;

	private String sFechaCompra;

	/**
 	 * Atributo que guarda el id de cancelacion de la venta.
	 */

	private int eIDcancelacion;

	/**
 	 * Atributo que guarda el motivo de la cancelacion.
	 */
	private String sMotivo;

	private int aIDInsumos[];
	private int aCantidades[];
	private float aCostosUnitarios[];

	/**
	 * Método constructor vacío de la clase DTO
	 */
	public DTOCompra(){

	}

	/**
	 * getter del atributo eId
	 * @return eId entero que hace referencia al id del insumo que se compró.
	 */
	public int getId(){

		return eId;

	}	

	/**
	 * getter del atributo sFechaCompra
	 * @return sFechaCompra una cadena con la fecha que será convertida a sql.date
	 */
	public String getFechaCompra(){

		return sFechaCompra;

	}	

	public int[] getIDInsumos(){

		return aIDInsumos;

	}

	public int[] getCantidades(){

		return aCantidades;

	}

	public float[] getCostosUnitarios(){

		return aCostosUnitarios;

	}

	/**
     * getCancelacion es un método que devuelve el id de cancelacion.
     * @return eIDcancelacion id de cancelación de la venta.
     */
	public int getCancelacion(){
		return eIDcancelacion;
	}

	/**
     *  getMotivo es un método que devuelve el motivo de la cancelacion.
     * @return sMotivo motivo cancelacion.
     */
	public String getMotivo(){
		return sMotivo;
	}

	/**
     * setCancelacion es un método que modifica el valor del atributo cancelacion.
     * @param eIDcancelacion atributo que define el id de cancelación de la venta.
     */
	public void setCancelacion(int eIDcancelacion){
		this.eIDcancelacion = eIDcancelacion;
	}

	/**
     * setMotivo es un método que modifica el valor del atributo del motivo de cancelacion.
     * @param sMotivo atributo que define la fecha en que se realiza una venta.
     */
	public void setMotivo(String sMotivo){
		this.sMotivo = sMotivo;
	}


	/**
	 * Método setter del atributo eId
	 * @param eId entero que hace referencia al id del insumo que se compró.
	 */
	public void setId(int eId){
		this.eId = eId;
	}

	
	/**
	 * Método setter del atributo sFechaCompra
	 * @param sFechaCompra una cadena con la fecha que será convertida a sql.date
	 */
	public void setFechaCompra(String sFechaCompra){
		this.sFechaCompra = sFechaCompra;
	}

	public void setIDInsumos(int aIDInsumos[]){
		this.aIDInsumos = aIDInsumos;
	}

	public void setCantidades(int aCantidades[]){
		this.aCantidades = aCantidades;
	}

	public void setCostosUnitarios(float aCostosUnitarios[]){

		this.aCostosUnitarios = aCostosUnitarios;
	}

}
