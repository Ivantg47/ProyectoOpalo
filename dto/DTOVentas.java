/**
 * Clase DTO de Ventas.
 * @author Pamela Stephanie Moreno Parker
 * @author Ivan Tronco
 * @version 1.0
 */

package ProyectoOpalo.dto;

public class DTOVentas{

	/**
 	 * Atributo que guarda el id de la venta.
	 */
	private int idVenta;
	/**
 	 * Atributo que guarda el tipo de pago de la venta.
	 */
	private String stipoPago;
	/**
 	 * Atributo que guarda el id de cancelacion de la venta.
	 */
	private int eIDcancelacion;
	/**
 	 * Atributo que guarda la fecha en que se realiza la venta.
	 */
	private String sfecha;
	/**
 	 * Atributo que guarda el estado de la venta como realizado o cancelado según sea el caso.
	 */
	private String sEstado;
	/**
 	 * Atributo que guarda la descripcion de un producto.
	 */
	private String sDescripcion;
	/**
 	 * Atributo que guarda la cantidad vendida de un producto.
	 */
	private float fCantidadVendida;
	/**
 	 * Atributo que guarda el id del cliente.
	 */
	private int eIdCliente;
	/**
 	 * Arreglo que guarda el id de un producto.
	 */
	private int aIdProducto[];
	/**
 	 * Arreglo que guarda la cantidad vendida de un producto.
	 */
	private int aCantidad[];
	
	/**
     * Constructor vacio para la clase DTOVentas
     */
	public DTOVentas(){

	}

	/**
     * setIdVenta es un método que modifica el valor del atributo id de la venta.
     * @param idVenta atributo que define el id de la venta.
     */
	public void setIdVenta(int idVenta){
		this.idVenta = idVenta;
	}

	/**
     * setTipoPago es un método que modifica el valor del atributo de tipo de Pago.
     * @param stipoPago atributo que define el tipo de pago de la venta.
     */
	public void setTipoPago(String stipoPago){
		this.stipoPago = stipoPago;
	}

	/**
     * setCancelacion es un método que modifica el valor del atributo cancelacion.
     * @param eIDcancelacion atributo que define el id de cancelación de la venta.
     */
	public void setCancelacion(int eIDcancelacion){
		this.eIDcancelacion = eIDcancelacion;
	}

	/**
     * setFecha es un método que modifica el valor del atributo de la fecha de una venta.
     * @param sfecha atributo que define la fecha en que se realiza una venta.
     */
	public void setFecha(String sfecha){
		this.sfecha = sfecha;
	}

	/**
     * setEstado es un método que modifica el valor del atributo estado de una venta.
     * @param sEstado atributo que define el estado de una venta.
     */
	public void setEstado(String sEstado){
		this.sEstado = sEstado;
	}

	/**
     * setCantidadVendida es un método que modifica el valor de la cantidad vendida en una venta.
     * @param fCantidadVendida atributo que define la cantidad vendida en una venta.
     */
	public void setCantidadVendida(float fCantidadVendida){
		this.fCantidadVendida = fCantidadVendida;
	}

	/**
     * setIdCliente es un método que modifica el valor de id de un cliente.
     * @param eIdCliente atributo que define el id de un cliente.
     */
	public void setIdCliente(int eIdCliente){
		this.eIdCliente = eIdCliente;
	}


	/**
     * setIdProducto es un método que modifica el valor de id de un Producto.
     * @param aIdProducto[] arreglo de id de un producto.
     */
	public void setIdProducto(int aIdProducto[]){
		this.aIdProducto = aIdProducto;
	}

	/**
     * setCantidad es un método que modifica el valor de la cantida vendida en una venta.
     * @param aCantidad[] arreglo de cantidades de prodcutos vendidas en una venta.
     */
	public void setCantidad(int aCantidad[]){
		this.aCantidad = aCantidad;
	}

	/**
     * setDescripcion es un método que modifica el valor de la descripcion de un producto.
     * @param sDescripcion atributo que define la descripcion de un producto.
     */
	public void setDescripcion(String sDescripcion){
		this.sDescripcion = sDescripcion;
	}

	/**
     * getIdVenta es un método que devuelve el id de la venta.
     * @return idVenta id de la venta.
     */
	public int getIdVenta(){
		return idVenta;
	}

	/**
     * getTipoPago es un método que devuelve el tipo de pago en una venta.
     * @return stipoPago tipo de pago de la venta.
     */
	public String getTipoPago(){
		return stipoPago;
	}

	/**
     * getCancelacion es un método que devuelve el id de cancelacion.
     * @return eIDcancelacion id de cancelación de laa venta.
     */
	public int getCancelacion(){
		return eIDcancelacion;
	}

	/**
     * getCancelacion es un método que devuelve el id de cancelacion.
     * @return eIDcancelacion id de cancelación de laa venta.
     */
	public String getFecha(){
		return sfecha;
	}

	public String getEstado(){
		return sEstado;
	}

	public float getCantidadVendida(){
		return fCantidadVendida;
	}

	public int getIdCliente(){
		return eIdCliente;
	}

	public int[] getIdProducto(){

		return aIdProducto;

	}

	public int[] getCantidad(){

		return aCantidad; 

	}

	public String getDescripcion(){
		return sDescripcion;
	}

	// public String toString(){

	// 	return "id: " + idVenta + "\ntipo pago: " + stipoPago + "\nCancelacion: " 
	// 			+ eIDcancelacion + "\nfecha: " + sfecha + "\nEstado: " + sEstado + 
	// 			"\nCantidad Vendida: " + fCantidadVendida;
	// }
}