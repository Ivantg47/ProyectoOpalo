/**
 * Clase DAO del producto.
 * @author Ivan Tronco
 * @version 1.0
 */

package ProyectoOpalo.dao;

import java.sql.*;
import ProyectoOpalo.dto.*;
import javax.swing.*;
import javax.swing.table.*;

public class DAOProducto{

	private Connection conexion = null;
	private PreparedStatement prepared;
    private ResultSet result;
//	DTOProducto producto;

    public DAOProducto(){

    }

	public void agregarPoducto(DTOProducto producto){

		try {

			conexion = getConnection();

			String sql = "INSERT INTO Producto (nombre, descripcion, existenciaMinima, existenciaMaxima, existenciaActuales) VALUES (?, ?, ?, ?, ?);";
	
			prepared = conexion.prepareStatement(sql);
	
			prepared.setString(1, producto.getNombre());
			prepared.setString(2, producto.getDescripcion());
			prepared.setInt(3, producto.getMinimo());
			prepared.setInt(4, producto.getMaximo());
			prepared.setInt(5, producto.getActual());

			if (prepared.executeUpdate() != 0) {
				
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

	public DTOProducto getPoducto(int codigo){

		DTOProducto producto = new DTOProducto();

		try {

			conexion = getConnection();

			String sql = "SELECT * FROM Producto WHERE codigo = ?;";
	
			prepared = conexion.prepareStatement(sql);
	
			prepared.setInt(1, codigo);

			result = prepared.executeQuery();

			if (result.next()) {
				
				producto.setCodigo(result.getInt("codigo"));
				producto.setNombre(result.getString("nombre"));
				producto.setDescripcion(result.getString("descripcion"));
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


	public DTOProducto getPoducto(String nombre){

		DTOProducto producto = new DTOProducto();

		try {

			conexion = getConnection();

			String sql = "SELECT * FROM Producto WHERE nombre = ?;";
	
			prepared = conexion.prepareStatement(sql);
	
			prepared.setString(1, nombre);

			result = prepared.executeQuery();

			if (result.next()) {
				
				producto.setCodigo(result.getInt("codigo"));
				producto.setNombre(result.getString("nombre"));
				producto.setDescripcion(result.getString("descripcion"));
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

	public void actualizarProducto(DTOProducto producto){

		try {

			conexion = getConnection();

			String sql = "UPDATE Producto SET nombre = ?, descripcion = ?, existenciaMinima = ?, existenciaMaxima = ?, existenciaActuales = ? WHERE codigo = ?;";
	
			prepared = conexion.prepareStatement(sql);
	
			prepared.setString(1, producto.getNombre());
			prepared.setString(2, producto.getDescripcion());
			prepared.setInt(3, producto.getMinimo());
			prepared.setInt(4, producto.getMaximo());
			prepared.setInt(5, producto.getActual());
			prepared.setInt(6, producto.getCodigo());

			if (prepared.executeUpdate() != 0) {
				
				JOptionPane.showMessageDialog(null, "Producto actualizado");

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

	public void borrarProducto(int codigo){

		try {

			conexion = getConnection();

			String sql = "DELETE FROM Producto WHERE codigo = ?;";
	
			prepared = conexion.prepareStatement(sql);
	
			prepared.setInt(1, codigo);

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

			String sql = "SELECT codigo, nombre, descripcion, existenciaActual FROM Producto;";
	
			prepared = conexion.prepareStatement(sql);

			result = prepared.executeQuery();

			while (result.next()) {
				
				modelo.addRow(new Object[]{result.getInt("codigo"), result.getString("nombre"), result.getString("descripcion"), result.getInt("existenciaActual")});

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

			String sql = "SELECT codigo, nombre, descripcion, existenciaActual FROM Producto WHERE nombre = ?;";
	
			prepared = conexion.prepareStatement(sql);

			prepared.setString(1, nombre);

			result = prepared.executeQuery();

			while (result.next()) {
				
				modelo.addRow(new Object[]{result.getInt("codigo"), result.getString("nombre"), result.getString("descripcion"), result.getInt("existenciaActual")});

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