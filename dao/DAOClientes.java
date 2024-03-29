/**
 * DAO de los clientes
 * @author Ximena Rojas
 * @version 1.2
 */

package ProyectoOpalo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import ProyectoOpalo.dto.DTOClientes;
import javax.swing.*;
import javax.swing.table.*;

public class DAOClientes{

	private Connection conexion = null;
	private PreparedStatement prepared = null;
    private ResultSet result;
	private DTOClientes oCliente;

	public DAOClientes(){ //construsctor sin parametros

	}

	public Connection getConexion(){ //Método usado para estalecer la conexión con la base de datos

		try{

			String driver = "com.mysql.cj.jdbc.Driver";

			Class.forName(driver);

			String jdbcUrl = "jdbc:mysql://25.67.54.75:3306/ProyectoOpalo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
	        String usuario = "proyecto";
	        String paswd = "hola";

	       	conexion = DriverManager.getConnection(jdbcUrl, usuario, paswd);

		}catch(SQLException oExcepcionSQL){

			JOptionPane.showMessageDialog(null, "Error al establecer la conexi\u00F3n");
			oExcepcionSQL.printStackTrace();

		}catch (Exception oExcepcion) {

			oExcepcion.printStackTrace();

		}finally{

			return conexion;

		}

	}//fin conexion


	/**Método utilizado para registrar un nuevo cliente
	* @param oCliente es el cliente que se creó en la DTO
	*/
	public void agregarCliente(DTOClientes oCliente){ //Método utilizado para registrar un nuevo cliente

		int eExecucion;
		String buscar;
		String insertar;
		ResultSet oResultado;

		try{

			conexion = getConexion() ;
			
			buscar =  	"SELECT id_cliente " +
          				"FROM Cliente " 
                         + "WHERE nombre = ? AND aPaterno = ? AND aMaterno = ?;";

            prepared = conexion.prepareStatement(buscar);

            prepared.setString(1, oCliente.getNombre());
            prepared.setString(2, oCliente.getPaterno());
            prepared.setString(3, oCliente.getMaterno());

            oResultado = prepared.executeQuery();

            if (!oResultado.next()){

         		insertar =  "INSERT INTO Cliente (nombre, aPaterno, aMaterno, correo, telefono, direccion) "
         						+ "VALUES (?, ?, ?, ?, ?, ?);";

         		prepared = conexion.prepareStatement(insertar);

         		prepared.setString(1, oCliente.getNombre());
            	prepared.setString(2, oCliente.getPaterno());
            	prepared.setString(3, oCliente.getMaterno());
            	prepared.setString(4, oCliente.getCorreo());
            	prepared.setString(5, oCliente.getTelefono());
            	prepared.setString(6, oCliente.getDireccion());

            	eExecucion = prepared.executeUpdate();

         		if (eExecucion == 1){

         			JOptionPane.showMessageDialog(null, "Cliente registrado");

         		}

            } else {

            	JOptionPane.showMessageDialog(null, "Error. El cliente ya existe, ingresar datos nuevamente.");
            }

		} catch(SQLException oExcepcionSQL) {

			JOptionPane.showMessageDialog(null, "Error al establecer la conexi\u00F3n");
			oExcepcionSQL.printStackTrace();

		} catch(Exception oExcepcion){

			oExcepcion.printStackTrace();

		} finally {

			try{

				if (conexion != null) {

					conexion.close();

				}	

			}catch(SQLException oExcepcion){

					oExcepcion.printStackTrace();

			}
		}
	}//fin agregar


	/**Método utilizado para eliminar clientes de la BD
	* @param oCliente es el cliente que se creó en la DTO
	*/
	public void eliminarCliente(DTOClientes oCliente){

		String eliminar;

		try {

			conexion = getConexion();

			eliminar = "DELETE FROM Cliente WHERE id_cliente = ?;";

			prepared = conexion.prepareStatement(eliminar);

			prepared.setInt(1, oCliente.getIdCliente());

			if(prepared.executeUpdate() != 0){

				JOptionPane.showMessageDialog(null, "Cliente eliminado");
			}

			conexion.close();

		}catch(SQLException oExcepcion){

			oExcepcion.printStackTrace();

		}catch(Exception oExcepcion){

			oExcepcion.printStackTrace();

		}finally{

			try {

				if (conexion != null) {

					conexion.close();
					
				}
			}catch(SQLException oExcepcion){

				oExcepcion.printStackTrace();

			}
		}
	}//fin eliminar

	
	/**Método utilizado para modificar los registros de un cliente de la BD
	* @param oCliente es el cliente que se creó en la DTO
	*/
	public void modificarCliente(DTOClientes oCliente){

		String modificar;

		try {

			conexion = getConexion();

			modificar = "UPDATE Cliente "
						+ "SET nombre = ?, aPaterno = ?, aMaterno = ?, correo = ?, telefono = ?, direccion = ? "
						+ "WHERE id_cliente = ?;";

			prepared = conexion.prepareStatement(modificar);

			prepared.setString(1, oCliente.getNombre());
          	prepared.setString(2, oCliente.getPaterno());
           	prepared.setString(3, oCliente.getMaterno());
           	prepared.setString(4, oCliente.getCorreo());
           	prepared.setString(5, oCliente.getTelefono());
           	prepared.setString(6, oCliente.getDireccion());
           	prepared.setInt(7, oCliente.getIdCliente());

           	if (prepared.executeUpdate() != 0) {
           		
           		JOptionPane.showMessageDialog(null, "Cliente modificado");
           	
           	}
		
		}catch(SQLException oExcepcion){

			oExcepcion.printStackTrace();

		} catch(Exception oExcepcion){

			oExcepcion.printStackTrace();

		} finally{

			try {

				if (conexion != null) {

					conexion.close();
					
				}
			}catch(SQLException oExcepcion){

				oExcepcion.printStackTrace();

			}
		}

	}//Fin modificar

	/**Método utilizado para buscar un cliente registrado en la BD
	* @param eId es el identificador único de cada cliente
	*/
	public DTOClientes buscarCliente(int eId){

		String buscar;
		ResultSet resultado;
		DTOClientes cliente = new DTOClientes();

		try{

			conexion = getConexion();

			buscar = "SELECT * FROM Cliente WHERE id_cliente = ?;";

			prepared = conexion.prepareStatement(buscar);

			prepared.setInt(1, eId);

			resultado = prepared.executeQuery();

			if (resultado.next()) {

				cliente = new DTOClientes (	resultado.getInt("id_cliente"),
											resultado.getString("nombre"),
											resultado.getString("aPaterno"),
											resultado.getString("aMaterno"),
											resultado.getString("correo"),
											resultado.getString("telefono"),
											resultado.getString("direccion"));
				
			} else {

				JOptionPane.showMessageDialog(null, "Error. El cliente no existe, intente de nuevo.");

			}
			
		}catch(SQLException oExcepcion){

			oExcepcion.printStackTrace();

		}catch(Exception oExcepcion){

			oExcepcion.printStackTrace();

		}finally {

			try {

				if (conexion != null) {

				   conexion.close();
				   
				} 

			} catch (SQLException oExcepcion){

				oExcepcion.printStackTrace();

			}
		}

		return cliente;

	}//fin buscar


	/**Método utilizado para buscar clientes en la BD de acuerdo con su nombre
	* @param nombre del cliente que se desea buscar
	*@param modelo es el diseño de la tabla
	*/
	public DTOClientes buscarNombre(String nombre, DefaultTableModel modelo){

		String buscar;
		ResultSet resultado;
		DTOClientes cliente = new DTOClientes();

		try{

			conexion = getConexion();

			buscar = "SELECT COUNT(*) FROM NombreConcatenado WHERE completo LIKE ?;";

			prepared = conexion.prepareStatement(buscar);

			prepared.setString(1, "%" + nombre + "%");
			
			resultado = prepared.executeQuery();

			if (resultado.next()) {
			
				int filas = resultado.getInt(1);
				
				if (filas != 0) {
					
					String sConsulta;
					sConsulta = "SELECT * FROM NombreConcatenado WHERE completo LIKE ?;";
					prepared = conexion.prepareStatement(sConsulta);
					prepared.setString(1, "%" + nombre + "%");
					resultado = prepared.executeQuery();

					if (filas == 1 && resultado.next()) {
						
						cliente = new DTOClientes (	resultado.getInt("id_cliente"),
											 		resultado.getString("nombre"),
													resultado.getString("aPaterno"),
													resultado.getString("aMaterno"),
													resultado.getString("correo"),
													resultado.getString("telefono"),
													resultado.getString("direccion"));

					} else {
						
						getTabla(modelo, nombre);

					}

				} else {

					JOptionPane.showMessageDialog(null, "Error. El cliente no existe, intente de nuevo.");

				}
		
			} 
			
		}catch(SQLException oExcepcion){

			oExcepcion.printStackTrace();

		}catch(Exception oExcepcion){

			oExcepcion.printStackTrace();

		}finally {

			try {

				if (conexion != null) {

				   conexion.close();
				   
				} 

			} catch (SQLException oExcepcion){

				oExcepcion.printStackTrace();

			}
		}

		return cliente;

	}//fin buscar

	

	/**Método utilizado para listar los datos de los clientes
	* @param modelo es el modelo de la tabla de la interfaz
	*/
	public void getTabla(DefaultTableModel modelo){
		
		String oConsultaTabla;
		ResultSet oResultado;

		try {

			conexion = getConexion();

			oConsultaTabla = 	  "SELECT id_cliente, nombre, aPaterno, aMaterno, correo, telefono, direccion "
								+ "FROM Cliente ORDER BY id_cliente;";
	
			prepared = conexion.prepareStatement(oConsultaTabla);

			oResultado = prepared.executeQuery();

			modelo.setRowCount(0);

			while (oResultado.next()) {
				
				modelo.addRow(new Object[]{oResultado.getInt("id_cliente"), oResultado.getString("nombre"), oResultado.getString("aPaterno"), oResultado.getString("aMaterno"), oResultado.getString("correo"), oResultado.getString("telefono"), oResultado.getString("direccion")});

			} 

			conexion.close();

		} catch (SQLException oExcepcion) {

	        oExcepcion.printStackTrace();

	    } catch (Exception oExcepcion) {
	         
	        oExcepcion.printStackTrace();

	    } finally {

	        try {

	            if (conexion != null) {

	               conexion.close();
	               
	            } 

	        }catch (SQLException oExcepcion){

	            oExcepcion.printStackTrace();

	        }
	    }
	}

	/** Crea una tabla donde muestra los datos de los clientes consultados
	*@param modelo sirve para la creación de la tabla
	*@param nombre nombre del cliente
	*/
	public void getTabla(DefaultTableModel modelo, String nombre){
		
		String oConsultaTabla;
		ResultSet oResultado;

		try {

			conexion = getConexion();

			oConsultaTabla = "SELECT * FROM NombreConcatenado WHERE completo LIKE ?;";
	
			prepared = conexion.prepareStatement(oConsultaTabla);

			prepared.setString(1, "%" + nombre + "%");

			oResultado = prepared.executeQuery();
			
			modelo.setRowCount(0);
			
			while (oResultado.next()) {
				
				modelo.addRow(new Object[]{oResultado.getInt("id_cliente"), oResultado.getString("nombre"), oResultado.getString("aPaterno"), oResultado.getString("aMaterno"), oResultado.getString("correo"), oResultado.getString("telefono"), oResultado.getString("direccion")});
				
			} 
			
			conexion.close();

		} catch (SQLException oExcepcion) {

	        oExcepcion.printStackTrace();

	    } catch (Exception oExcepcion) {
	         
	        oExcepcion.printStackTrace();

	    } finally {

	        try {

	            if (conexion != null) {

	               conexion.close();
	               
	            } 

	        }catch (SQLException oExcepcion){

	            oExcepcion.printStackTrace();

	        }
	    }
	}

}