/**
 * DAO de los clientes
 * @author Ximena Rojas
 * @version 1.0
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

	public DAOClientes(){

	}

	public Connection getConexion(){

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


	public void agregarCliente(DTOClientes oCliente){

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

				JOptionPane.showMessageDialog(null, "Error. El cliente no existe no existe, intente de nuevo.");

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

}