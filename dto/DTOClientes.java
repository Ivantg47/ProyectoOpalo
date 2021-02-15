/**
 * Clase DTO de los clientes
 * @author Ximena Rojas
 * @version 1.0
 */

package ProyectoOpalo.dto;

public class DTOClientes {

	private int eIdCliente;
	private String sNombre;
	private String aPaterno;
	private String aMaterno;
	private String sCorreo;
	private String sTelefono;
	private String sDireccion;

	public DTOClientes(){

	}

	public DTOClientes(int eIdCliente, String sNombre, String aPaterno, String aMaterno, String sCorreo, String sTelefono, String sDireccion){

		this.eIdCliente = eIdCliente;
		this.sNombre = sNombre;
		this.aPaterno = aPaterno;
		this.aMaterno = aMaterno;
		this.sCorreo = sCorreo;
		this.sTelefono = sTelefono;
		this.sDireccion = sDireccion;

	}

	public void setIdCliente (int eIdCliente) {

		this.eIdCliente = eIdCliente;

	}

	public void setNombre(String sNombre) {

		this.sNombre = sNombre;

	}

	public void setPaterno(String aPaterno){

		this.aPaterno = aPaterno;

	}

	public void setMaterno(String aMaterno) {

		this.aMaterno = aMaterno;

	}

	public void setCorreo(String sCorreo){

		this.sCorreo = sCorreo;

	}

	public void setTelefono(String sTelefono){

		this.sTelefono = sTelefono;

	}

	public void setDireccion(String sDireccion){

		this.sDireccion = sDireccion;
	
	}

	public int getIdCliente(int eIdCliente){

		return eIdCliente;

	}

	public String getNombre(String sNombre){

		return sNombre;

	}

	public String getPaterno(String aPaterno) {

		return aPaterno;

	}

	public String getMaterno(String aMaterno) {

		return aMaterno;

	}

	public String getCorreo(String sCorreo){

		return sCorreo;
	}

	public String getTelefono(String sTelefono){

		return sTelefono;

	}

	public String getDireccion(String sDireccion){

		return sDireccion;
		
	}

	public String toString(){

		return "ID: " + eIdCliente + "\nNombre: " + sNombre + " " + aPaterno + " " + aMaterno + 
				"\nCorreo: " + sCorreo + "\nTelefono: " + sTelefono + "\nDirección: " + sDireccion;

	}

}