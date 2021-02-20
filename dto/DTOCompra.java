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

	private String sEstado;

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

	/**
	 * Método que retorna el id del insumo.
	 * @return aIDInsumos que es el id de los insumos de la compra.
	 */
	public int[] getIDInsumos(){

		return aIDInsumos;

	}

	/**
	 * Método que obtiene el arreglo de las cantidades de las compras.
	 * @return aCantidades que es el arreglo de las cantidades de las compras.
	 */
	public int[] getCantidades(){

		return aCantidades;

	}

	/**
	 * Método que obtiene el arreglo de costos unitarios.
	 * @return aCostosUnitarios es el arreglo de los costos unitarios de las comrpas.
	 */
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
     * getEstado es un método que devuelve el estado de la venta.
     * @return sEstado estado de la venta.
     */
	public String getEstado(){
		return sEstado;
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
     * setEstado es un método que modifica el valor del atributo estado de una venta.
     * @param sEstado atributo que define el estado de una venta.
     */
	public void setEstado(String sEstado){
		this.sEstado = sEstado;
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

	/**
	 * Método que establece el id del insumo.
	 * @param aIDInsumos recibe un entero que va a ser el id del insumo.
	 */
	public void setIDInsumos(int aIDInsumos[]){
		this.aIDInsumos = aIDInsumos;
	}

	/**
	 * Método que establece la cantidad comprada.
	 * @param aCantidades entero que se refiere a la cantidad comprada del insumo.
	 */
	public void setCantidades(int aCantidades[]){
		this.aCantidades = aCantidades;
	}

	/**
	 * Método que establece los costos unitarios del insumo.
	 * @param aCostosUnitarios es un flotante que estbalece el costo unitario del insumo.
	 */
	public void setCostosUnitarios(float aCostosUnitarios[]){

		this.aCostosUnitarios = aCostosUnitarios;
	}

}
