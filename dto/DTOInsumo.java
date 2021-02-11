package ProyectoOpalo.dto;

public class DTOInsumo{

	private int eId = 0;
	private String sNombre;
	private String sUnidadMedida;
	private float fExistenciaActual;
	private float fExistenciaMinima;
	private float fExistenciaMaxima;

	public DTOInsumo(String sNombre, String sUnidadMedida, float fExistenciaActual, float fExistenciaMinima, float fExistenciaMaxima){

	 	this.sNombre = sNombre;
		this.sUnidadMedida = sUnidadMedida;
		this.fExistenciaActual = fExistenciaActual;
		this.fExistenciaMinima = fExistenciaMinima;
		this.fExistenciaMaxima = fExistenciaMaxima;
		
	}

	public void setId(int eId){

		this.eId = eId;

	}

	public void setNombre(String sNombre ){

		this.sNombre = sNombre;

	}

	public void setUnidadMedida(String fUnidadMedida ){

		this.sUnidadMedida = fUnidadMedida;

	}


	public void setExistenciaActual(float fExistenciasActuales ){

		this.fExistenciaActual = fExistenciaActual;

	}

	public void setExistenciaMinima( float fExistenciasMinimas ){

		this.fExistenciaMinima = fExistenciaMinima;
	}


	public void setExistenciaMaxima(float fExistenciasMaximas ){

		this.fExistenciaMaxima = fExistenciaMaxima;

	}

	public int getId(){

		return eId;

	} 

	public String getNombre(){

		return sNombre;
	 
	}

	public String getUnidadMedida(){

		return sUnidadMedida;
	}

	public float getExistenciaMinima(){

		return fExistenciaMinima;

	}
	

	public float getExistenciaActual(){

		return fExistenciaActual;

	}

	public float getExistenciaMaxima(){

		return fExistenciaMaxima;


	}

	public String toString(){

		return "ID: " + eId + "Nombre: " + sNombre + "UnidadMedida: " + sUnidadMedida +
		"ExistenciasActuales: " + fExistenciaActual + "ExistenciasMinimas: " + fExistenciaMinima + "ExistenciasMaximas: " + fExistenciaMaxima;

	}

}