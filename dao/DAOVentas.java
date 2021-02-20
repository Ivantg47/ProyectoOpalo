/**
 * Clase DAO de Ventas.
 * @author Pamela Stephanie Moreno Parker
 * @author Ivan Tronco
 * @version 1.3
 */

package ProyectoOpalo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import ProyectoOpalo.dto.DTOVentas;
import ProyectoOpalo.igu.IGUVentas;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

public class DAOVentas{

	/**
 	 * Atributo del tipo Connection que sirve para establecer la conexión con la base de datos.
	 */
	private Connection conexion = null;
	/**
 	 * Atributo del tipo PreparedStatement que sirve para realizar operaciones en la base de datos.
	 */
	private PreparedStatement prepared;
	/**
 	 * Atributo del tipo ResultSet que sirve para obtener resultados de la base de datos.
	 */
    private ResultSet result, resultF;

    /**
     * Constructor vacio para la clase DAOVentas.
     */	
    public DAOVentas(){

    }

    /**
     * agregarVenta es un método que establece conexión con la base de datos e inserta los datos
     * de una venta en las tablas correspondientes.
     * @param venta objeto del tipo DTOVentas.
     * @return folio numero de folio de la venta
     */
	public int agregarVenta(DTOVentas venta) throws IllegalArgumentException, SQLException{

		int folio = 0;

		try {

			conexion = getConnection();

			String sql = "INSERT INTO Venta (tipoPago, fecha, estado) VALUES (?, ?, ?);";

			prepared = conexion.prepareStatement(sql);

			prepared.setString(1, venta.getTipoPago());
			prepared.setDate(2, Date.valueOf(venta.getFecha()));
			prepared.setString(3,venta.getEstado());

			if (prepared.executeUpdate() != 0) {
				
				sql = "SELECT last_insert_id() AS codigo;";
				prepared = conexion.prepareStatement(sql);
				result = prepared.executeQuery();

				if (result.next()){

					folio = result.getInt("codigo");
					sql = "INSERT INTO Venta_Cliente (id_venta, id_cliente) VALUES (?, ?);";
					prepared = conexion.prepareStatement(sql);
					prepared.setInt(1, folio);
					prepared.setInt(2, venta.getIdCliente());

					if (prepared.executeUpdate() != 0) {
						
						int producto[] = venta.getIdProducto();
						int cantidad[] = venta.getCantidad();

						for (int con = 0; con < producto.length; con++) {
							
							sql = "INSERT INTO Venta_Producto VALUES (?, ?, ?);";
							prepared = conexion.prepareStatement(sql);
							prepared.setInt(1, result.getInt("codigo"));
							prepared.setInt(2, producto[con]);
							prepared.setInt(3, cantidad[con]);

							if (prepared.executeUpdate() == 0) {
								
								throw new IllegalArgumentException("Error al registrar la venta ");

							}

						}

					} else {
						
						throw new IllegalArgumentException("Error al registrar la venta ");

					}

				}


			} else {

				throw new IllegalArgumentException("Error al registrar la venta ");

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

	    return folio;
	}

	/**
     * buscarVenta es un método que establece conexión con la base de datos y hace una consulta 
     * para obtener los datos de una venta.
     * @param idVenta id de la venta
     * @param modelo atributo del tipo DefaultTableModel
     * @return venta datos de la venta
     */
	public DTOVentas buscarVenta(int idVenta, DefaultTableModel modelo) throws IllegalArgumentException, SQLException{
		
		DTOVentas venta = new DTOVentas();
		
		try {

			conexion = getConnection();

			String sql = "SELECT * FROM DatosVenta WHERE id_venta = ?;";

			prepared = conexion.prepareStatement(sql);

			prepared.setInt(1, idVenta);

			result = prepared.executeQuery();
			
			if (result.next()) {
				
				venta.setIdVenta(idVenta);
				venta.setFecha(result.getString("fecha"));
				venta.setIdCliente(result.getInt("id_cliente"));
				venta.setCliente(result.getString("nombre"));
				venta.setEstado(result.getString("estado"));

				sql = ("SELECT P.id_producto AS id, CONCAT(P.nombre, ' ', P.descripcion) AS nombre, VP.cantidad, Pr.precio"
						+ " FROM Venta_Producto AS VP, Producto AS P ,"
						+ " (SELECT id_producto AS idProd, MAX(fecha) AS fecha, precio FROM Precio"
						+ " WHERE fecha <= ?"
						+ " GROUP BY id_producto) AS Pr"
						+ " WHERE VP.id_venta = ? AND VP.id_producto = P.id_producto AND P.id_producto = Pr.idProd;");

				prepared = conexion.prepareStatement(sql);

				prepared.setDate(1, result.getDate("fecha"));
				prepared.setInt(2, idVenta);

				result = prepared.executeQuery();

				while (result.next()){
					
					modelo.addRow(new Object[]{result.getInt("id"), result.getString("nombre"), result.getFloat("precio"),
											result.getInt("cantidad"), (result.getInt("cantidad") * result.getFloat("precio"))});

				}


			} else {

				throw new IllegalArgumentException("No hay ventas registradas con ese folio");

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

	    return venta;
	}

	/**
     * cancelarVenta es un método que establece conexión con la base de datos y hace las modificaciones 
     * correspondientes para cancelar una venta.
     * @param venta objeto del tipo DTOVentas con los datos de la venta
     */
	public void cancelarVenta(DTOVentas venta){

		try {

			conexion = getConnection();

			String sql = "CALL cancelar (?);";

			prepared = conexion.prepareStatement(sql);

			prepared.setString(1,venta.getMotivo());

			result = prepared.executeQuery();

			if (result.next()) {
				
				sql = "UPDATE Venta SET id_cancelacion = ?, estado = ? WHERE id_venta = ?;";
				prepared = conexion.prepareStatement(sql);

				prepared.setInt(1, result.getInt("id_cancelacion"));
				prepared.setString(2, venta.getEstado());
				prepared.setInt(3,venta.getIdVenta());

				if (prepared.executeUpdate() == 0) {
					
					throw new IllegalArgumentException("Error: En cancelacion");

				}

			} else {

				throw new IllegalArgumentException("Error: En cancelacion");

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

	}


	/**
     * getConnection es un método que establece conexión con la base de datos
     * @return conexion objeto del tipo Connection
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