/**
 * Clase DTO del producto.
 * @author Ivan Tronco
 * @version 1.0
 */

package ProyectoOpalo.dto;

public class DTOProducto{

	private int codigo, min, max, actual;
	private float precio;
	private String nombre, descripcion;

	public DTOProducto(){

	}

	public DTOProducto(int codigo, String nombre, String descripcion, float precio, int min, int max, int actual){

		setCodigo(codigo);
		setNombre(nombre);
		setDescripcion(descripcion);
		setPrecio(precio);
		setMinimo(min);
		setMaximo(max);
		setActual(actual);
		
	}

	public void setCodigo(int codigo){

		this.codigo = codigo;
	}

	public void setNombre(String nombre){

		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion){

		this.descripcion = descripcion;
	}

	public void setPrecio(float precio){

		this.precio = precio;
	}

	public void setMinimo(int min){

		this.min = min;
	}

	public void setMaximo(int max){

		this.max = max;
	}

	public void setActual(int actual){

		this.actual = actual;
	}

	public int getCodigo(){

		return codigo;
	}

	public String getNombre(){

		return nombre;
	}

	public String getDescripcion(){

		return descripcion;
	}

	public float getPrecio(){

		return precio;
	}

	public int getMinimo(){

		return min;
	}

	public int getMaximo(){

		return max;
	}

	public int getActual(){

		return actual;
	}

	public String toString(){

		return "Codigo: " + codigo + "\nNombre: " + nombre + "\nDescripcion: " + descripcion + "\nPrecio: $" + precio + 
				"\nMinimo: " + min + "\nMaximo: " + max + "\nActual: " + actual;

	}
}