package ProyectoOpalo.dao;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

public class DAOReportes{

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
     * Constructor, que no recibe parametros.
     */
    public DAOReportes(){

    }

    /**
     * Metodo que obtiene la informacion de ventas de un periodo determinado
     * @param modelo modelo de la tabla reportes
     * @param fechaInicio fecha inicial de consulta
     * @param fechaFin fecha final de la consulta
     */
    public void generarReporte(DefaultTableModel modelo, Date fechaInicio, Date fechaFin)throws SQLException{

    	try {

			conexion = getConnection();

			String sql = "CALL reportes (?, ?);";

			prepared = conexion.prepareStatement(sql);

			prepared.setDate(1, fechaInicio);
			prepared.setDate(2, fechaFin);

			result = prepared.executeQuery();
			
			if (result.next()) {
				
				modelo.addRow(new Object[]{result.getInt("id_producto"), result.getString("descripcion"), 
											result.getInt("cantidad"), result.getFloat("total")});

				while (result.next()){
					
					modelo.addRow(new Object[]{result.getInt("id_producto"), result.getString("descripcion"), 
											result.getInt("cantidad"), result.getFloat("total")});

				}


			} else {

				throw new IllegalArgumentException("No se generaron ventas en el perido del: " + fechaInicio + " al " + fechaFin + ".");

			}
			
			prepared.close();
			result.close();
			conexion.close();

		} catch (SQLException es) {

	        es.printStackTrace();
	        throw new SQLException();

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