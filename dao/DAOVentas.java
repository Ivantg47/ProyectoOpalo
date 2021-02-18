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
import ProyectoOpalo.igu.IGUVentas;
import javax.swing.*;
import javax.swing.table.*;
// import java.util.Date;
import java.sql.*;

public class DAOVentas{

	private Connection conexion = null;
	private PreparedStatement prepared;
    private ResultSet result, resultF;

    public DAOVentas(){

    }

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

	// public void CancelarVenta(int idVenta, DTOVentas ventas){
	// 	try {

	// 		conexion = getConnection();

	// 		String sql = "UPDATE Venta SET id_cancelacion = 2, estado = 'cancelado' WHERE id_venta = ?";

	// 		prepared = conexion.prepareStatement(sql);

	// 		ventas.setIdVenta(idVenta);
	// 		prepared.setInt(1,ventas.getIdVenta());

	// 		int resultado = prepared.executeUpdate();

	// 		if (resultado == 1){

	// 			JOptionPane.showMessageDialog(null, "Venta cancelada");

	// 		} else {

	// 			JOptionPane.showMessageDialog(null, "No existe una venta con el id ingresado.");

	// 		}

	// 		conexion.close();

	// 	} catch (SQLException es) {

	//         es.printStackTrace();

	//     } catch (Exception e) {
	         
	//         e.printStackTrace();

	//     } finally {

	//         try {

	//             if (conexion != null) {

	//                conexion.close();
	               
	//             } 

	//         }catch (SQLException es){

	//             es.printStackTrace();

	//         }
	//     }

	// }

	// public void getTabla(DefaultTableModel modelo){

	// 	try {

	// 		conexion = getConnection();

	// 		String sql = "SELECT V.id_venta,C.descripcion,V.tipoPago,V.fecha,V.estado FROM Venta V, Cancelacion C WHERE V.id_cancelacion = C.id_cancelacion;";
	
	// 		prepared = conexion.prepareStatement(sql);

	// 		result = prepared.executeQuery();

	// 		modelo.setRowCount(0);

	// 		while (result.next()) {
				
	// 			modelo.addRow(new Object[]{result.getInt("V.id_venta"), result.getString("C.descripcion"), result.getString("V.tipoPago"), result.getDate("V.fecha"), result.getString("V.estado")});

	// 		} 

	// 		conexion.close();

	// 	} catch (SQLException es) {

	//         es.printStackTrace();

	//     } catch (Exception e) {
	         
	//         e.printStackTrace();

	//     } finally {

	//         try {

	//             if (conexion != null) {

	//                conexion.close();
	               
	//             } 

	//         }catch (SQLException es){

	//             es.printStackTrace();

	//         }
	//     }

 //    }

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