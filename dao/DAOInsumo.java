/**
 * Clase control del insumo.
 * @author Frida Janeth Hernández Torres
 * @version 1.5
 */

package ProyectoOpalo.dao; //Pertenece a este paquete

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import javax.swing.table.*;
import javax.swing.JOptionPane;

import ProyectoOpalo.dto.DTOInsumo;

public class DAOInsumo{

	/**
     * Atributo que guarda la conexion con la base de datos.
     */
	private Connection oConexion;
	/**
     * Atributo que almacena la consultas a la base de datos.
     */
	private PreparedStatement oSentencia;
	 /**
     * Atributo que almacena un insumo
     */
	private DTOInsumo oDTOInsumo;
	
	/**
     * Constructor, que no recibe parametros.
     */
	public DAOInsumo(){

		Connection oConexion = null;
		PreparedStatement oSentencia = null;

	}

	/**
     * Metodo para establecer una conexion a la base de datos.
     * @return conexion a la base de datos
     */	
	public Connection getConexion(){

		try{

			String sDriver = "com.mysql.cj.jdbc.Driver";

			Class.forName(sDriver);

			String sJdbcUrl = "jdbc:mysql://25.67.54.75:3306/ProyectoOpalo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
			String sUsuario ="proyecto";
			String sPassword = "hola";

			oConexion = DriverManager.getConnection(sJdbcUrl, sUsuario, sPassword);


		}catch(SQLException oExcepcionSQL){

			JOptionPane.showMessageDialog(null, "Error al establecer la conexi\u00F3n");
			oExcepcionSQL.printStackTrace();

		} catch (Exception oExcepcion) {

			oExcepcion.printStackTrace();

		} finally {

			return oConexion;

		}

	}//getConexion

	/**
     * Metodo para agregar un insumo a la base de datos.
     * @param oInsumo contiene el insumo.
     */
	public void agregar(DTOInsumo oInsumo){

		int eExecucion;
		String sConsultaBuscar;
		String sConsultaInsertar;
		ResultSet oResultado;

		try{

			oConexion = getConexion();

			//Buscamos si ya existe ese insumo
			sConsultaBuscar =     "SELECT id_Insumo " 
                               	+ "FROM Insumo " 
                         		+ "WHERE nombre = ?; ";

            oSentencia = oConexion.prepareStatement(sConsultaBuscar);

            oSentencia.setString(1, oInsumo.getNombre());            

            oResultado = oSentencia.executeQuery();

            //Si no existe ese insumo lo insertamos
            if(!oResultado.next()){

            	sConsultaInsertar =   "INSERT INTO Insumo (unidadMedida, nombre, existenciaMinima,  existenciaMaxima, existenciaActual) " 
                               		+ "VALUES(?, ?, ?, ?, ?);";

	            oSentencia = oConexion.prepareStatement(sConsultaInsertar);

	            oSentencia.setString(1, oInsumo.getUnidadMedida());
	            oSentencia.setString(2, oInsumo.getNombre());
	            oSentencia.setFloat(3, oInsumo.getExistenciaMinima());
	            oSentencia.setFloat(4, oInsumo.getExistenciaMaxima());
	            oSentencia.setFloat(5, oInsumo.getExistenciaActual());

	            eExecucion = oSentencia.executeUpdate();

	            if (eExecucion == 1) {

	            	JOptionPane.showMessageDialog(null, "Insumo registrado");

				} 

            } else {

            	JOptionPane.showMessageDialog(null, "Error. El insumo ya existe, ingresar datos nuevamente.");

            }

		} catch (SQLException oExcepcionSQL) {

				oExcepcionSQL.printStackTrace();

		} catch (Exception oExcepcion) {

				oExcepcion.printStackTrace();

		} finally {

			try {

				if (oConexion != null) {

				   oConexion.close();
				   
				} 

			} catch (SQLException oExcepcion){

				oExcepcion.printStackTrace();

			}

		}

		

	}//Fin agregarInsumo

	/**
     * Metodo para busca un insumo  por id en la base de datos.
     * @param eID id que se desea buscar 
     */
	public DTOInsumo buscar(int eID){

		String sConsultaBuscar;
		ResultSet oResultado;
		oDTOInsumo = new DTOInsumo();

		try{

			oConexion = getConexion();

			sConsultaBuscar =     "SELECT * " 
                               	+ "FROM Insumo " 
                         		+ "WHERE id_Insumo = ? ";
                                

            oSentencia = oConexion.prepareStatement(sConsultaBuscar);

            oSentencia.setInt(1, eID);

            oResultado = oSentencia.executeQuery();

            
            if(oResultado.next()){

            	oDTOInsumo = new DTOInsumo(	  oResultado.getInt("id_Insumo"), 
            								  oResultado.getString("nombre"), 
            							 	  oResultado.getString("unidadMedida"), 
            							 	  oResultado.getFloat("existenciaActual"), 
            							 	  oResultado.getFloat("existenciaMinima"), 
            							 	  oResultado.getFloat("existenciaMaxima"));

            } else {

            	JOptionPane.showMessageDialog(null, "Error. El insumo no existe, intentar con otro codigo.");

            }

            

		} catch (SQLException oExcepcionSQL) {

				oExcepcionSQL.printStackTrace();

		} catch (Exception oExcepcion) {

				oExcepcion.printStackTrace();

		} finally {

			try {

				if (oConexion != null) {

				   oConexion.close();
				   
				} 

			} catch (SQLException oExcepcion){

				oExcepcion.printStackTrace();

			}

		}

		return oDTOInsumo;

		
	}//Buscar

	/**
     * Metodo para busca una insumo por nombre en la base de datos.
     * @param sNombre nombre que se desea buscar 
     */
	public DTOInsumo buscarPorNombre(String sNombre){

		String sConsultaBuscar;
		ResultSet oResultado;
		oDTOInsumo = new DTOInsumo();

		try{

			oConexion = getConexion();

			sConsultaBuscar =     "SELECT * " 
                               	+ "FROM Insumo " 
                         		+ "WHERE nombre = ? ";
                                

            oSentencia = oConexion.prepareStatement(sConsultaBuscar);

            oSentencia.setString(1, sNombre);

            oResultado = oSentencia.executeQuery();

            
            if(oResultado.next()){

            	oDTOInsumo = new DTOInsumo(	  oResultado.getInt("id_Insumo"), 
            								  oResultado.getString("nombre"), 
            							 	  oResultado.getString("unidadMedida"), 
            							 	  oResultado.getFloat("existenciaActual"), 
            							 	  oResultado.getFloat("existenciaMinima"), 
            							 	  oResultado.getFloat("existenciaMaxima"));

            } else {

            	JOptionPane.showMessageDialog(null, "Error. El insumo no existe, intentar con otro nombre.");

            }

            

		} catch (SQLException oExcepcionSQL) {

				oExcepcionSQL.printStackTrace();

		} catch (Exception oExcepcion) {

				oExcepcion.printStackTrace();

		} finally {

			try {

				if (oConexion != null) {

				   oConexion.close();
				   
				} 

			} catch (SQLException oExcepcion){

				oExcepcion.printStackTrace();

			}

		}

		return oDTOInsumo;

		
	}//BuscarPorNombre

	/**
     * Metodo para eliminar un insumo de la base de datos.
     * @param oInsumo insumo a eliminar
     */
	public void eliminar(DTOInsumo oInsumo){

		String sConsultaEliminar;

		try { 

			oConexion = getConexion();

			sConsultaEliminar = "DELETE FROM Insumo WHERE id_Insumo = ?;";
	
			oSentencia = oConexion.prepareStatement(sConsultaEliminar);
	
			oSentencia.setInt(1, oInsumo.getId());

			if (oSentencia.executeUpdate() != 0) {
				
				JOptionPane.showMessageDialog(null, "Insumo borrado");

			} 

			oConexion.close();

		} catch (SQLException oExcepcion) {

	        oExcepcion.printStackTrace();

	    } catch (Exception oExcepcion) {
	         
	        oExcepcion.printStackTrace();

	    } finally {

	        try {

	            if (oConexion != null) {

	               oConexion.close();
	               
	            } 

	        }catch (SQLException oExcepcion){

	            oExcepcion.printStackTrace();

	        }
	    }
	}//eliminar

	/**
     * Metodo para modificar un insumo de la base de datos.
     * @param oInsumo insumo a actualizar
     */
	public void modificarInsumo(DTOInsumo oInsumo){

		String oConsultaModificar;

		try {

			oConexion = getConexion();
			

			oConsultaModificar = 	  "UPDATE Insumo "  
									+ "SET unidadMedida = ?, nombre = ?, "
									+ "existenciaMinima = ?, existenciaMaxima = ?, existenciaActual = ? " 
									+ "WHERE id_Insumo = ?;";
	
			oSentencia = oConexion.prepareStatement(oConsultaModificar);
			
			oSentencia.setString(1, oInsumo.getUnidadMedida());
			oSentencia.setString(2, oInsumo.getNombre());
			oSentencia.setFloat(3, oInsumo.getExistenciaMinima());
			oSentencia.setFloat(4, oInsumo.getExistenciaMaxima());
			oSentencia.setFloat(5, oInsumo.getExistenciaActual());
			oSentencia.setInt(6, oInsumo.getId());

			
			if (oSentencia.executeUpdate() != 0) {
				
				JOptionPane.showMessageDialog(null, "Insumo actualizado");

			} 


		} catch (SQLException oExcepcion) {

	        oExcepcion.printStackTrace();

	    } catch (Exception oExcepcion) {
	         
	        oExcepcion.printStackTrace();

	    } finally {

	        try {

	            if (oConexion != null) {

	               oConexion.close();
	               
	            } 

	        }catch (SQLException oExcepcion){

	            oExcepcion.printStackTrace();

	        }
	    }

	}//modificarInsumo

	/**
     * Metodo para llenar la tabla de inventario insumo.
     * @param oModelo modelo de la tabla insumo.
     */
	public void getTabla(DefaultTableModel oModelo){
		
		String oConsultaTabla;
		ResultSet oResultado;

		try {

			oConexion = getConexion();

			oConsultaTabla = 	  "SELECT id_Insumo, nombre, unidadMedida, existenciaActual "
								+ "FROM Insumo ORDER BY id_Insumo;";
	
			oSentencia = oConexion.prepareStatement(oConsultaTabla);

			oResultado = oSentencia.executeQuery();

			oModelo.setRowCount(0);

			while (oResultado.next()) {
				
				oModelo.addRow(new Object[]{oResultado.getInt("id_Insumo"), oResultado.getString("nombre"), oResultado.getString("unidadMedida"), oResultado.getInt("existenciaActual")});

			} 

			oConexion.close();

		} catch (SQLException oExcepcion) {

	        oExcepcion.printStackTrace();

	    } catch (Exception oExcepcion) {
	         
	        oExcepcion.printStackTrace();

	    } finally {

	        try {

	            if (oConexion != null) {

	               oConexion.close();
	               
	            } 

	        }catch (SQLException oExcepcion){

	            oExcepcion.printStackTrace();

	        }
	    }
	}//getTabla

}
