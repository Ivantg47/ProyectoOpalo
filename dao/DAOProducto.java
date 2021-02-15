/**
 * Clase DAO del producto.11
 * @author Ivan Tronco
 * @version 1.5
 */

package ProyectoOpalo.dao;

// import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import ProyectoOpalo.dto.DTOProducto;
import javax.swing.*;
import javax.swing.table.*;

public class DAOProducto{

	/**
     * Atributo que guarda la conexion con la base de datos.
     */
	private Connection conexion = null;
	/**
     * Atributo que almacena la consultas a la base de datos.
     */
	private PreparedStatement prepared;
	/**
     * Atributo que guarda los resultados de una consulta.
     */
    private ResultSet result;
    /**
     * Atributo que almacena un producto.
     */
	private DTOProducto producto;

	/**
     * Constructor, que no recibe parametros.
     */
    public DAOProducto(){

    }

    /**
     * Metodo para agregar un producto a la base de datos.
     * @param producto recibe el producto 
     */
	public void agregarProducto(DTOProducto producto) throws IllegalArgumentException{

		try {

			conexion = getConnection();

			String sql = "INSERT INTO Producto (nombre, descripcion, existenciaMinima, existenciaMaxima, existenciaActual) VALUES (?, ?, ?, ?, ?);";
	
			prepared = conexion.prepareStatement(sql);
	
			prepared.setString(1, producto.getNombre());
			prepared.setString(2, producto.getDescripcion());
			prepared.setInt(3, producto.getMinimo());
			prepared.setInt(4, producto.getMaximo());
			prepared.setInt(5, producto.getActual());

			if (prepared.executeUpdate() != 0) {
				
				sql = "SELECT last_insert_id() AS codigo;";
				prepared = conexion.prepareStatement(sql);
				result = prepared.executeQuery();

				if (result.next()){

					sql = "INSERT INTO Precio VALUES(?,?,?);";
					prepared = conexion.prepareStatement(sql);
					prepared.setInt(1, result.getInt("codigo"));
					java.util.Date fecha = new java.util.Date();
					java.sql.Date sqlFecha = new java.sql.Date(fecha.getTime());
					prepared.setDate(2, sqlFecha);
					prepared.setFloat(3, producto.getPrecio());

					prepared.executeUpdate();

				}

			} else {

				throw new IllegalArgumentException("El producto ya esta registrado");

			}

			prepared.close();
			result.close();
			conexion.close();

		} catch (SQLException es) {

	        es.printStackTrace();

	    } finally {

	        try {

	            if (conexion != null) {

	               conexion.close();
	               
	            } 

	        }catch (SQLException es){

	            es.printStackTrace();

	        }
	    }

	}//agregarProducto

	/**
     * Metodo para busca una producto por codigo/id en la base de datos.
     * @param codigo codigo que se desea buscar 
     */
	public DTOProducto getProducto(int codigo) throws IllegalArgumentException{

		DTOProducto producto = new DTOProducto();
		
		try {

			conexion = getConnection();

			String sql = "SELECT * FROM DatoProducto WHERE id_producto = ?;";
	
			prepared = conexion.prepareStatement(sql);
	
			prepared.setInt(1, codigo);

			result = prepared.executeQuery();

			if (result.next()) {
				
				producto.setCodigo(result.getInt("id_producto"));
				producto.setNombre(result.getString("nombre"));
				producto.setDescripcion(result.getString("descripcion"));
				producto.setPrecio(result.getFloat("precio"));
				producto.setMinimo(result.getInt("existenciaMinima"));
				producto.setMaximo(result.getInt("existenciaMaxima"));
				producto.setActual(result.getInt("existenciaActual"));

			} else {

				throw new IllegalArgumentException("No hay productos registrados con ese codigo");

			}

			prepared.close();
			result.close();
			conexion.close();

		} catch (SQLException es) {

	        es.printStackTrace();

	    } finally {

	        try {

	            if (conexion != null) {

	               conexion.close();
	               
	            } 

	        }catch (SQLException es){

	            es.printStackTrace();

	        }
	    }

	    return producto;
	}//getProducto

	/**
     * Metodo para busca una producto por nombre en la base de datos.
     * @param nombre nombre que se desea buscar 
     * @param modelo modelo de la tabla inventario
     */
	public DTOProducto getProducto(String nombre, DefaultTableModel modelo) throws IllegalArgumentException{

		DTOProducto producto = new DTOProducto();

		try {

			conexion = getConnection();

			String sql = "SELECT COUNT(*) AS filas FROM DatoProducto WHERE nombre = ?;";
	
			prepared = conexion.prepareStatement(sql);
	
			prepared.setString(1, nombre);

			result = prepared.executeQuery();

			if (result.next()) {
				
				int filas = result.getInt(1);

				if (filas != 0) {
					
					sql = "SELECT * FROM DatoProducto WHERE nombre = ?;";
					prepared = conexion.prepareStatement(sql);
					prepared.setString(1, nombre);
					result = prepared.executeQuery();

					if (filas == 1 && result.next()) {

						producto.setCodigo(result.getInt("id_producto"));
						producto.setNombre(result.getString("nombre"));
						producto.setDescripcion(result.getString("descripcion"));
						producto.setPrecio(result.getFloat("precio"));
						producto.setMinimo(result.getInt("existenciaMinima"));
						producto.setMaximo(result.getInt("existenciaMaxima"));
						producto.setActual(result.getInt("existenciaActual"));

					} else {
						
						getTabla(modelo, nombre);

					}

				} else {

					throw new IllegalArgumentException("No hay productos registrados con ese nombre");

				}
			
			}

			prepared.close();
			result.close();
			conexion.close();

		} catch (SQLException es) {

	        es.printStackTrace();

	    } finally {

	        try {

	            if (conexion != null) {

	               conexion.close();
	               
	            } 

	        }catch (SQLException es){

	            es.printStackTrace();

	        }
	    }

	    return producto;
	}//getProducto

	/**
     * Metodo para actualizar un producto de la base de datos.
     * @param producto producto a actualizar
     */
	public void actualizarProducto(DTOProducto producto)throws IllegalArgumentException{

		try {

			conexion = getConnection();

			String sql = "UPDATE Producto SET nombre = ?, descripcion = ?, existenciaMinima = ?, existenciaMaxima = ?, existenciaActual = ? WHERE id_producto = ?;";
	
			prepared = conexion.prepareStatement(sql);
	
			prepared.setString(1, producto.getNombre());
			prepared.setString(2, producto.getDescripcion());
			prepared.setInt(3, producto.getMinimo());
			prepared.setInt(4, producto.getMaximo());
			prepared.setInt(5, producto.getActual());
			prepared.setInt(6, producto.getCodigo());

			if (prepared.executeUpdate() != 0) {
				
				sql = "INSERT INTO Precio VALUES(?,?,?) ON DUPLICATE KEY UPDATE precio = ?;";
				prepared = conexion.prepareStatement(sql);
				prepared.setInt(1, producto.getCodigo());
				java.util.Date fecha = new java.util.Date();
				java.sql.Date sqlFecha = new java.sql.Date(fecha.getTime());
				prepared.setDate(2, sqlFecha);
				prepared.setFloat(3, producto.getPrecio()); 
				prepared.setFloat(4, producto.getPrecio());

				if (prepared.executeUpdate() == 0) {

					throw new IllegalArgumentException("El producto no existe");

				}

			} else {
				
				throw new IllegalArgumentException("El producto no existe");

			}

			prepared.close();
			conexion.close();

		} catch (SQLException es) {

	        es.printStackTrace();

	    } finally {

	        try {

	            if (conexion != null) {

	               conexion.close();
	               
	            } 

	        }catch (SQLException es){

	            es.printStackTrace();

	        }
	    }
	}//actualizarProducto

	/**
     * Metodo para eliminar un producto de la base de datos.
     * @param producto producto a eliminar
     */
	public void borrarProducto(DTOProducto producto) throws IllegalArgumentException {

		try {

			conexion = getConnection();

			String sql = "DELETE FROM Producto WHERE id_producto = ?;";
	
			prepared = conexion.prepareStatement(sql);
	
			prepared.setInt(1, producto.getCodigo());

			if (prepared.executeUpdate() == 0) {
				
				throw new IllegalArgumentException("El producto no existe");

			} 

			prepared.close();
			conexion.close();

		} catch (SQLException es) {

	        es.printStackTrace();

	    } finally {

	        try {

	            if (conexion != null) {

	               conexion.close();
	               
	            } 

	        }catch (SQLException es){

	            es.printStackTrace();

	        }
	    }
	}//borrarProducto

	/**
     * Metodo para llenar la tabla de inventario producto.
     * @param modelo modelo de la tabla producto
     */
	public void getTabla(DefaultTableModel modelo){

		try {

			conexion = getConnection();

			String sql = "SELECT id_producto, nombre, descripcion, existenciaActual FROM Producto ORDER BY id_producto;";
	
			prepared = conexion.prepareStatement(sql);

			result = prepared.executeQuery();

			modelo.setRowCount(0);

			while (result.next()) {
				
				modelo.addRow(new Object[]{result.getInt("id_producto"), result.getString("nombre"), result.getString("descripcion"), result.getInt("existenciaActual")});

			} 

			prepared.close();
			result.close();
			conexion.close();

		} catch (SQLException es) {

	        es.printStackTrace();

	    } catch (Exception e) {
	         
	        e.printStackTrace();

	    } finally {

	        try {

	            if (conexion != null) {

	               conexion.close();
	               
	            } 

	        }catch (SQLException es){

	            es.printStackTrace();

	        }
	    }

	}//getTabla

	/**
     * Metodo para llenar la tabla de inventario producto restringida a un nombre.
     * @param modelo modelo de la tabla producto
     * @param nombre nombre que se desea buscar
     */
	public void getTabla (DefaultTableModel modelo, String nombre){

		try {

			conexion = getConnection();

			String sql = "SELECT id_producto, nombre, descripcion, existenciaActual FROM Producto WHERE nombre = ? ORDER BY id_producto;";
	
			prepared = conexion.prepareStatement(sql);

			prepared.setString(1, nombre);

			result = prepared.executeQuery();

			modelo.setRowCount(0);
			
			while (result.next()) {
				
				modelo.addRow(new Object[]{result.getInt("id_producto"), result.getString("nombre"), result.getString("descripcion"), result.getInt("existenciaActual")});

			} 

			prepared.close();
			result.close();
			conexion.close();

		} catch (SQLException es) {

	        es.printStackTrace();

	    } catch (Exception e) {
	         
	        e.printStackTrace();

	    } finally {

	        try {

	            if (conexion != null) {

	               conexion.close();
	               
	            } 

	        }catch (SQLException es){

	            es.printStackTrace();

	        }
	    }

	}//getTabla

	/**
     * Metodo para establecer una conexion a la base de datos.
     * @return conexion a la base de datos
     */
	public Connection getConnection() {
   
        try {
            
            String driver = "com.mysql.cj.jdbc.Driver";

            Class.forName(driver);

	        String jdbcUrl = "jdbc:mysql://25.67.54.75:3306/ProyectoOpalo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
	        String usuario = "proyecto";
	        String paswd = "hola";

	        conexion = DriverManager.getConnection(jdbcUrl, usuario, paswd);
            
        } catch (SQLException sqlEx){

			JOptionPane.showMessageDialog(null, "Error al establecer la conexi\u00F3n", 
									"Error de conexi\u00F3n", JOptionPane.ERROR_MESSAGE);
			sqlEx.printStackTrace();

		} catch (Exception ex) {

			ex.printStackTrace();

		}
       
        return conexion;
        
    } 
}