/**
 * Clase DAO de Ventas.
 * @author Pamela Stephanie Moreno Parker
 * @version 1.0
 */

package ProyectoOpalo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import ProyectoOpalo.dto.DTOVentas;
import javax.swing.*;
import javax.swing.table.*;

public class DAOVentas{

	private Connection conexion = null;
	private PreparedStatement prepared;
    private ResultSet result;
	DTOVentas ventas;

    public DAOVentas(){

    }

	public void agregarVenta(DAOVentas venta){

		try {

			conexion = getConnection();

			String sql = "INSERT INTO Venta (tipoPago,id_cancelacion,fecha,estado) VALUES (?, ?, ?, ?);";
	
			prepared = conexion.prepareStatement(sql);

			prepared.setString(1, ventas.getTipoPago());
			ventas.setCancelacion(0);
			prepared.setInt(2, ventas.getCancelacion());
			java.util.Date fecha = new java.util.Date();
			java.sql.Date sqlFecha = new java.sql.Date(fecha.getTime());
			prepared.setDate(3, sqlFecha);
			ventas.setEstado("realizado");
			prepared.setString(4, ventas.getEstado());

			prepared = conexion.prepareStatement(sql);
			result = prepared.executeQuery();

			if (result.next()){

				JOptionPane.showMessageDialog(null, "Producto registrado");

			}

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

	}
/*
	public DTOProducto getPoducto(int codigo){

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

			} 

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

	    return producto;
	}


	public DTOProducto getPoducto(String nombre, DefaultTableModel modelo){

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

				}
			
			} 

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

	    return producto;
	}

	public void actualizarProducto(DTOProducto producto){

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

				if (prepared.executeUpdate() != 0) {

					JOptionPane.showMessageDialog(null, "Producto actualizado");

				}

			} 

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
	}

	public void borrarProducto(DTOProducto producto){

		try {

			conexion = getConnection();

			String sql = "DELETE FROM Producto WHERE id_producto = ?;";
	
			prepared = conexion.prepareStatement(sql);
	
			prepared.setInt(1, producto.getCodigo());

			if (prepared.executeUpdate() != 0) {
				
				JOptionPane.showMessageDialog(null, "Producto borrado");

			} 

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
	}

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

	}

	public void getTabla(DefaultTableModel modelo, String nombre){

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

	}
*/
	public Connection getConnection() {
   
        try {
            
            String driver = "com.mysql.cj.jdbc.Driver";

            Class.forName(driver);

	        String jdbcUrl = "jdbc:mysql://25.67.54.75:3306/ProyectoOpalo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
	        String usuario = "proyecto";
	        String paswd = "hola";

	        conexion = DriverManager.getConnection(jdbcUrl, usuario, paswd);
        //    JOptionPane.showMessageDialog(null, "Conexion Exitosa");
            
        } catch(SQLException oExcepcionSQL){

			JOptionPane.showMessageDialog(null, "Error al establecer la conexi\u00F3n");
			oExcepcionSQL.printStackTrace();

		} catch (Exception oExcepcion) {

			oExcepcion.printStackTrace();

		}
       
        return conexion;
        
    } 
}