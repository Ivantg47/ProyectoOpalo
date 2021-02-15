/**
 * Clase DTO del producto.
 * @author Ivan Tronco
 * @version 1.0
 */

package ProyectoOpalo.dto;

public class DTOProducto{

	/**
     * Atributo que almacena el codigo o id del producto.
     */
	private int codigo;
	/**
     * Atributo que almacena las existencias minimas que se debe tener del producto.
     */
	private int min;
	/**
     * Atributo que almacena las existencias maximas que puede tener producto.
     */
	private int max;
	/**
     * Atributo que almacena las existencias actual del producto.
     */
	private int actual;
	/**
     * Atributo que almacena el precio del producto.
     */
	private float precio;
	/**
     * Atributo que almacena el nombre del producto.
     */
	private String nombre;
	/**
     * Atributo que almacena una brebe descripcion del producto.
     */
	private String descripcion;

	/**
     * Constructor, que no recibe parametros.
     */
	public DTOProducto(){

	}

	/**
     * Constructor, recibe codigo, nombre, descripcion, precio, min, max, actual.
     * @param codigo codigo o id del producto
     * @param nombre nombre del producto
     * @param descripcion brebe descripcion del producto
     * @param precio precio del producto
     * @param min existencia minima del producto
     * @param max existencia maxima del producto
     * @param actual existencia actual del producto
     */
	public DTOProducto(int codigo, String nombre, String descripcion, float precio, int min, int max, int actual){

		setCodigo(codigo);
		setNombre(nombre);
		setDescripcion(descripcion);
		setPrecio(precio);
		setMinimo(min);
		setMaximo(max);
		setActual(actual);
		
	}

	/**
     * Metodo, modifica el valor del atributo codigo.
     * @param codigo indica nuevo parametro del codigo del producto
     */
	public void setCodigo(int codigo){

		this.codigo = codigo;
	}

	/**
     * Metodo, modifica el valor del atributo nombre.
     * @param nombre indica nuevo parametro del nombre del producto
     */
	public void setNombre(String nombre){

		this.nombre = nombre;
	}

	/**
     * Metodo, modifica el valor del atributo descripcion.
     * @param descripcion indica nuevo parametro del descripcion del producto
     */
	public void setDescripcion(String descripcion){

		this.descripcion = descripcion;
	}

	/**
     * Metodo, modifica el valor del atributo precio.
     * @param precio indica nuevo parametro del precio del producto
     */
	public void setPrecio(float precio){

		this.precio = precio;
	}

	/**
     * Metodo, modifica el valor del atributo min.
     * @param min indica nuevo parametro de existencia minima del producto
     */
	public void setMinimo(int min){

		this.min = min;
	}

	/**
     * Metodo, modifica el valor del atributo max.
     * @param max indica nuevo parametro de existencia maxima del producto
     */
	public void setMaximo(int max){

		this.max = max;
	}

	/**
     * Metodo, modifica el valor del atributo actual.
     * @param actual indica nuevo parametro de existencia actual del producto
     */
	public void setActual(int actual){

		this.actual = actual;
	}

	/**
     * Metodo, regresa el codigo del producto.
     * @return codigo del producto
     */
	public int getCodigo(){

		return codigo;
	}

	/**
     * Metodo, regresa el nombre del producto.
     * @return nombre del producto
     */
	public String getNombre(){

		return nombre;
	}

	/**
     * Metodo, regresa la descripcion del producto.
     * @return descripcion del producto
     */
	public String getDescripcion(){

		return descripcion;
	}

	/**
     * Metodo, regresa el precio del producto.
     * @return precio del producto
     */
	public float getPrecio(){

		return precio;
	}

	/**
     * Metodo, regresa la existencia minima del producto.
     * @return existencia minima del producto
     */
	public int getMinimo(){

		return min;
	}

	/**
     * Metodo, regresa la existencia maxima del producto.
     * @return existencia maxima del producto
     */
	public int getMaximo(){

		return max;
	}

	/**
     * Metodo, regresa la existencia actual del producto.
     * @return existencia actual del producto
     */
	public int getActual(){

		return actual;
	}

	/**
     * Metodo para mostrar el contenido de todos los atributos del producto
     * @return cadena con los atributos del producto
     */
	public String toString(){

		return "Codigo: " + codigo + "\nNombre: " + nombre + "\nDescripcion: " + descripcion + "\nPrecio: $" + precio + 
				"\nMinimo: " + min + "\nMaximo: " + max + "\nActual: " + actual;

	}
}