/**
 * Clase DTO de los clientes
 * @author Ximena Rojas
 * @version 1.2
 */

package ProyectoOpalo.dto;

public class DTOClientes {

	//Datos del cliente que se van a almacenar
	private int eIdCliente;
	private String sNombre;
	private String aPaterno;
	private String aMaterno;
	private String sCorreo;
	private String sTelefono;
	private String sDireccion;


	public DTOClientes(){ //Constructor vacío
		
	}

	//constructor que inicializa los atributos
	public DTOClientes(String sNombre, String aPaterno, String aMaterno, String sCorreo, String sTelefono, String sDireccion){

		this.sNombre = sNombre;
		this.aPaterno = aPaterno;
		this.aMaterno = aMaterno;
		this.sCorreo = sCorreo;
		this.sTelefono = sTelefono;
		this.sDireccion = sDireccion;

	}

	public DTOClientes(DTOClientes oCliente){
		
		this.eIdCliente = oCliente.getIdCliente();
		this.sNombre = oCliente.getNombre();
		this.aPaterno = oCliente.getPaterno();
		this.aMaterno = oCliente.getMaterno();
		this.sCorreo = oCliente.getCorreo();
		this.sTelefono = oCliente.getTelefono();
		this.sDireccion = oCliente.getDireccion();

	}

	//constructor que inicializa los atributos
	public DTOClientes(int eIdCliente, String sNombre, String aPaterno, String aMaterno, String sCorreo, String sTelefono, String sDireccion){

		this(sNombre, aPaterno, aMaterno, sCorreo, sTelefono, sDireccion);
		this.eIdCliente = eIdCliente;
	}

	/**Método utilizado asignar valores
	* @param eIdClientes es el identificador único de cada cliente
	*/
	public void setIdCliente (int eIdCliente) {

		this.eIdCliente = eIdCliente;

	}

	/**Método utilizado asignar valores
	* @param sNombre es el nombre de cada cliente
	*/
	public void setNombre(String sNombre) {

		this.sNombre = sNombre;

	}

	/**Método utilizado asignar valores
	* @param aPaterno es el apellido Paterno de cada cliente
	*/
	public void setPaterno(String aPaterno){

		this.aPaterno = aPaterno;

	}

	/**Método utilizado asignar valores
	* @param aMaterno es el apellido materno de cada cliente
	*/
	public void setMaterno(String aMaterno) {

		this.aMaterno = aMaterno;

	}

	/**Método utilizado asignar valores
	* @param sCorreo es el correo electronico de cada cliente
	*/
	public void setCorreo(String sCorreo){

		this.sCorreo = sCorreo;

	}

	/**Método utilizado asignar valores
	* @param sTelefono es el número de telefono de cada cliente
	*/
	public void setTelefono(String sTelefono){

		this.sTelefono = sTelefono;

	}

	/**Método utilizado asignar valores
	* @param sDireccion es la direccion de cada cliente
	*/
	public void setDireccion(String sDireccion){

		this.sDireccion = sDireccion;
	
	}


	/**Método utilizado para obtener los valores
	* @param eIdClientes es el identificador único de cada cliente
	*/
	public int getIdCliente(){

		return eIdCliente;

	}

	/**Método utilizado obtener valores
	* @param aMaterno es el apellido materno de cada cliente
	*/
	public String getNombre(){

		return sNombre;

	}


	/**Método utilizado obtener valores
	* @param aPaterno es el apellido Paterno de cada cliente
	*/
	public String getPaterno() {

		return aPaterno;

	}

	/**Método utilizado obtener valores
	* @param aMaterno es el apellido materno de cada cliente
	*/
	public String getMaterno() {

		return aMaterno;

	}

	/**Método utilizado obtener valores
	* @param sCorreo es el correo electronico de cada cliente
	*/
	public String getCorreo(){

		return sCorreo;
	}

	/**Método utilizado obtener valores
	* @param sTelefono es el número de telefono de cada cliente
	*/
	public String getTelefono(){

		return sTelefono;

	}

	/**Método utilizado obtener valores
	* @param sDireccion es la direccion de cada cliente
	*/
	public String getDireccion(){

		return sDireccion;
		
	}

}