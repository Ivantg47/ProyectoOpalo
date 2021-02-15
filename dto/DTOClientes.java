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

	public DTOClientes(String sNombre, String aPaterno, String aMaterno, String sCorreo, String sTelefono, String sDireccion){

		//this.eIdCliente = eIdCliente;
		this.sNombre = sNombre;
		this.aPaterno = aPaterno;
		this.aMaterno = aMaterno;
		this.sCorreo = sCorreo;
		this.sTelefono = sTelefono;
		this.sDireccion = sDireccion;

	}


	public DTOClientes(int eIdCliente, String sNombre, String aPaterno, String aMaterno, String sCorreo, String sTelefono, String sDireccion){

		this(sNombre, aPaterno, aMaterno, sCorreo, sTelefono, sDireccion);
		this.eIdCliente = eIdCliente;
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

	public int getIdCliente(){

		return eIdCliente;

	}

	public String getNombre(){

		return sNombre;

	}

	public String getPaterno() {

		return aPaterno;

	}

	public String getMaterno() {

		return aMaterno;

	}

	public String getCorreo(){

		return sCorreo;
	}

	public String getTelefono(){

		return sTelefono;

	}

	public String getDireccion(){

		return sDireccion;
		
	}

}