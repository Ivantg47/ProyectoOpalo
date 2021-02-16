/**
 * Clase DTO de Ventas.
 * @author Pamela Stephanie Moreno Parker
 * @version 1.0
 */

package ProyectoOpalo.dto;

public class DTOVentas{

	private int idVenta;
	private String stipoPago;
	private int eIDcancelacion = 1;
	private String sfecha = "";
	private String sEstado = "";
	private String sDescripcion = "";
	private float fCantidadVendida;
	private int eIdCliente;
	private int eIdProducto;
	
	public DTOVentas(){

	}

	public DTOVentas(String stipoPago, int eIDcancelacion, String sfecha, 
					 String sEstado, float fCantidadVendida, int eIdCliente, int eIdProducto){

		setIdCliente(eIdCliente);
		setTipoPago(stipoPago);
		setIdProducto(eIdProducto);
		setCantidadVendida(fCantidadVendida);

		setFecha(sfecha);
		setEstado(sEstado);
		setCancelacion(eIDcancelacion);

	}		

	public DTOVentas(int idVenta, String stipoPago, int eIDcancelacion, String sfecha, 
					 String sEstado, float fCantidadVendida, int eIdCliente, int eIdProducto,
					 String sDescripcion){

		setIdVenta(idVenta); 
		setTipoPago(stipoPago);
		setCancelacion(eIDcancelacion);
		setFecha(sfecha);
		setEstado(sEstado);
		setCantidadVendida(fCantidadVendida);
		setIdCliente(eIdCliente);
		setIdProducto(eIdProducto);
		setDescripcion(sDescripcion);

	}		

	public void setIdVenta(int idVenta){
		this.idVenta = idVenta;
	}

	public void setTipoPago(String stipoPago){
		this.stipoPago = stipoPago;
	}

	public void setCancelacion(int eIDcancelacion){
		this.eIDcancelacion = eIDcancelacion;
	}

	public void setFecha(String sfecha){
		this.sfecha = sfecha;
	}

	public void setEstado(String sEstado){
		this.sEstado = sEstado;
	}

	public void setCantidadVendida(float fCantidadVendida){
		this.fCantidadVendida = fCantidadVendida;
	}

	public void setIdCliente(int eIdCliente){
		this.eIdCliente = eIdCliente;
	}

	public void setIdProducto(int eIdProducto){
		this.eIdProducto = eIdProducto;
	}

	public void setDescripcion(String sDescripcion){
		this.sDescripcion = sDescripcion;
	}

	public int getIdVenta(){
		return idVenta;
	}

	public String getTipoPago(){
		return stipoPago;
	}

	public int getCancelacion(){
		return eIDcancelacion;
	}

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

	public int getIdProducto(){
		return eIdProducto;
	}

	public String getDescripcion(){
		return sDescripcion;
	}

	public String toString(){

		return "id: " + idVenta + "\ntipo pago: " + stipoPago + "\nCancelacion: " 
				+ eIDcancelacion + "\nfecha: " + sfecha + "\nEstado: " + sEstado + 
				"\nCantidad Vendida: " + fCantidadVendida;
	}
}