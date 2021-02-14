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
import ProyectoOpalo.dto.DTOProducto;
import javax.swing.*;
import javax.swing.table.*;

public class DAOClientes{

	private Connection conexion = null;
	private PreparedStatement prepared;
    private ResultSet result;
	DTOClientes cliente;

	public DAOClientes(){

	}

	public void Connection getConexion(){

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


	public void agregarCliente(DTOClientes cliente){


	}//fin agregar


}