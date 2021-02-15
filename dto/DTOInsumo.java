/**
 * Clase control del insumo.
 * @author Frida Janeth Hernández Torres
 * @version 1.5
 */

package ProyectoOpalo.dto;

public class DTOInsumo{

	/**
     * Atributo entero que almacena el valor del id del insumo.
     */
	private int eId;

	/**
     * Atributo tipo cadena que almacena el nombre del insumo.
     */
	private String sNombre;

	/**
     * Atributo tipo cadena que almacena la unidad de medida del nombre del insumo.
     */
	private String sUnidadMedida;

	/**
     * Atributo tipo flotante que almacena la existencia actual  del insumo.
     */
	private float fExistenciaActual;

	/**
     * Atributo tipo flotante que almacena la existencia minima  del insumo.
     */
	private float fExistenciaMinima;

	/**
     * Atributo tipo flotante que almacena la existencia maxima  del insumo.
     */
	private float fExistenciaMaxima;

	/**
     * Constructor en blanco.
     */
	public DTOInsumo(){
		
	}

	/**
     * Constructor, que inicializa los atributos.
     * @param sNombre cadena que contiene el nombre del insumo.
     * @param sUnidadMedida cadena que contiene la unidad de medida del insumo.
     * @param fExistenciaActual cadena que contiene la existencia actual del insumo.
     * @param fExistenciaMinima cadena que contiene la existencia minima del insumo.
     * @param fExistenciaMaxima cadena que contiene la existencia maxima del insumo.
     */

	public DTOInsumo(String sNombre, String sUnidadMedida, float fExistenciaActual, float fExistenciaMinima, float fExistenciaMaxima){

	 	this.sNombre = sNombre;
		this.sUnidadMedida = sUnidadMedida;
		this.fExistenciaActual = fExistenciaActual;
		this.fExistenciaMinima = fExistenciaMinima;
		this.fExistenciaMaxima = fExistenciaMaxima;
		
	}

	/**
     * Constructor, que inicializa los atributos.
     * @param sNombre cadena que contiene el nombre del insumo.
     * @param sUnidadMedida cadena que contiene la unidad de medida del insumo.
     * @param fExistenciaActual cadena que contiene la existencia actual del insumo.
     * @param fExistenciaMinima cadena que contiene la existencia minima del insumo.
     * @param fExistenciaMaxima cadena que contiene la existencia maxima del insumo.
     */
	public DTOInsumo(int eId, String sNombre, String sUnidadMedida, float fExistenciaActual, float fExistenciaMinima, float fExistenciaMaxima){
		this(sNombre, sUnidadMedida, fExistenciaActual, fExistenciaMinima, fExistenciaMaxima);
		this.eId = eId;
		
	}

	/**
     * Constructor, que inicializa los atributos.
     * @param sNombre cadena que contiene el nombre del insumo.
     */
	public void setId(int eId){

		this.eId = eId;

	}

	/**
     * Metodo, para asignarle un valor al nombre del insumo.
     * @param sNombre cadena que contiene el nombre del insumo.
     */
	public void setNombre(String sNombre ){

		this.sNombre = sNombre;

	}

	/**
     * Metodo, para asignarle un valor a la unidad de medida del insumo.
     * @param fUnidadMedida cadena que contiene la unidad de medida del insumo.
     */
	public void setUnidadMedida(String fUnidadMedida ){

		this.sUnidadMedida = fUnidadMedida;

	}

	/**
     * Metodo, para asignarle un valor a las existencias actuales del insumo.
     * @param fExistenciasActuales cadena que contiene las existencias actuales del insumo.
     */
	public void setExistenciaActual(float fExistenciasActuales ){

		this.fExistenciaActual = fExistenciaActual;

	}

	/**
     * Metodo, para asignarle un valor a las existencias minimas del insumo.
     * @param fExistenciasMinimas cadena que contiene las existencias minimas del insumo.
     */
	public void setExistenciaMinima( float fExistenciasMinimas ){

		this.fExistenciaMinima = fExistenciaMinima;
	}

	/**
     * Metodo, para asignarle un valor a las existencias maximas del insumo.
     * @param fExistenciasMinimas cadena que contiene las existencias maximas del insumo.
     */
	public void setExistenciaMaxima(float fExistenciasMaximas ){

		this.fExistenciaMaxima = fExistenciaMaxima;

	}

	/**
     * Metodo, para obtener el id del insumo
     * @return eId variable entera.
     */
	public int getId(){

		return eId;

	} 

	/**
     * Metodo, para obtener el nombre del insumo
     * @return sNombre variable tipo cadena.
     */
	public String getNombre(){

		return sNombre;
	 
	}

	/**
     * Metodo, para obtener la unidad de medida del insumo
     * @return sUnidadMedida variable tipo cadena.
     */
	public String getUnidadMedida(){

		return sUnidadMedida;
	}

	/**
     * Metodo, para obtener las existencias minimas del insumo
     * @return fExistenciaMinima variable tipo flotante.
     */
	public float getExistenciaMinima(){

		return fExistenciaMinima;

	}
	
	/**
     * Metodo, para obtener las existencias actuales del insumo
     * @return fExistenciaActual variable tipo flotante.
     */
	public float getExistenciaActual(){

		return fExistenciaActual;

	}

	/**
     * Metodo, para obtener las existencias maximas del insumo
     * @return fExistenciaMaxima variable tipo flotante.
     */
	public float getExistenciaMaxima(){

		return fExistenciaMaxima;


	}

}