/**
 * Clase DTO de Ventas.
 * @author Pamela Stephanie Moreno Parker
 * @version 1.0
 */

package ProyectoOpalo.dto;

public class DTOVentas{

	private int idVenta;
	private String stipoPago;
	private int eIDcancelacion;
	private String sfecha;
	private String sEstado;
	private float fCantidadVendida;
	private int eIdCliente;
	
	public DTOVentas(){

	}

	public DTOVentas(int idVenta, String stipoPago, int eIDcancelacion, String sfecha, 
					 String sEstado, float fCantidadVendida, int eIdCliente){

		setIdVenta(idVenta); 
		setTipoPago(stipoPago);
		setCancelacion(eIDcancelacion);
		setFecha(sfecha);
		setEstado(sEstado);
		setCantidadVendida(fCantidadVendida);
		setIdCliente(eIdCliente);


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

	public String toString(){

		return "id: " + idVenta + "\ntipo pago: " + stipoPago + "\nCancelacion: " 
				+ eIDcancelacion + "\nfecha: " + sfecha + "\nEstado: " + sEstado + 
				"\nCantidad Vendida: " + fCantidadVendida;
	}
}