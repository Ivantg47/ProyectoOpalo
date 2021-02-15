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
import java.util.Date;
import java.sql.*;

public class DAOVentas{

	private Connection conexion = null;
	private PreparedStatement prepared;
    private ResultSet result, resultF;
    IGUVentas igu;

    public DAOVentas(){

    }

	public void agregarVenta(DTOVentas ventas){
		try {

			conexion = getConnection();

			String sql = "INSERT INTO Venta (tipoPago,id_cancelacion,fecha,estado) VALUES (?, ?, ?, ?);";

			prepared = conexion.prepareStatement(sql);

			prepared.setString(1, ventas.getTipoPago());
			prepared.setInt(2, ventas.getCancelacion());
			java.util.Date fecha = new java.util.Date();
			java.sql.Date sqlFecha = new java.sql.Date(fecha.getTime());
			prepared.setDate(3, sqlFecha);
			ventas.setEstado("realizado");
			prepared.setString(4,ventas.getEstado());

			int resultado = prepared.executeUpdate();

			//Facturas
			String facturasql = "INSERT INTO Factura (fecha) VALUES (?);";

			prepared = conexion.prepareStatement(facturasql);

			java.util.Date fechaF = new java.util.Date();
			java.sql.Date sqlFechaF = new java.sql.Date(fecha.getTime());
			prepared.setDate(1,sqlFechaF);

			int resultadoF = prepared.executeUpdate();


			if (resultado == 1 && resultadoF == 1){

				sql = "SELECT last_insert_id() AS id_venta;";
				prepared = conexion.prepareStatement(sql);
				result = prepared.executeQuery();

				facturasql = "SELECT last_insert_id() AS id_factura;";
				prepared = conexion.prepareStatement(facturasql);
				resultF = prepared.executeQuery();

				if (result.next()){
					
					//Ventas y productos
					String venta_Productosql = "INSERT INTO Venta_Producto (id_venta,id_producto,cantidad) VALUES (?, ?, ?);";

					prepared = conexion.prepareStatement(venta_Productosql);

					prepared.setInt(1, result.getInt("id_venta"));
					prepared.setInt(2, ventas.getIdProducto());
					prepared.setFloat(3, ventas.getCantidadVendida());

					int resultadoV_P = prepared.executeUpdate();

					//Ventas y clientes
					String venta_Clientesql = "INSERT INTO Venta_Cliente (id_venta,id_cliente,id_factura) VALUES (?, ?, ?);";

					prepared = conexion.prepareStatement(venta_Productosql);

					prepared.setInt(1, result.getInt("id_venta"));
					prepared.setInt(2, ventas.getIdCliente());
					prepared.setInt(3, resultF.getInt("id_factura"));

					int resultadoVP = prepared.executeUpdate();
					

				}


				JOptionPane.showMessageDialog(null, "Venta registrada");

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

	public DTOVentas buscarVenta(int idVenta){
		
		DTOVentas ventas = new DTOVentas();
		IGUVentas iguVentas = new IGUVentas();

		try {

			conexion = getConnection();

			String sql = "SELECT * FROM Venta WHERE id_venta = ?;";
	
			prepared = conexion.prepareStatement(sql);
	
			prepared.setInt(1, idVenta);

			result = prepared.executeQuery();

			if (result.next()) {

				ventas.setIdVenta(result.getInt("id_venta"));				
				ventas.setTipoPago(result.getString("tipoPago"));
				ventas.setCancelacion(result.getInt("id_cancelacion"));
				ventas.setFecha(String.valueOf(result.getDate("fecha")));
				ventas.setEstado(result.getString("estado"));

				iguVentas.mostrarDatosBusqueda(ventas);

			} else {

				JOptionPane.showMessageDialog(null, "No hay ventas registradas con ese id");

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

	    return ventas;
	}

	public void CancelarVenta(int idVenta, DTOVentas ventas){
		try {

			conexion = getConnection();

			String sql = "UPDATE Venta SET id_cancelacion = 2, estado = 'cancelado' WHERE id_venta = ?";

			prepared = conexion.prepareStatement(sql);

			ventas.setIdVenta(idVenta);
			prepared.setInt(1,ventas.getIdVenta());

			int resultado = prepared.executeUpdate();

			if (resultado == 1){

				JOptionPane.showMessageDialog(null, "Venta cancelada");

			} else {

				JOptionPane.showMessageDialog(null, "No existe una venta con el id ingresado.");

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

			String sql = "SELECT * FROM Venta;";
	
			prepared = conexion.prepareStatement(sql);

			result = prepared.executeQuery();

			modelo.setRowCount(0);

			while (result.next()) {
				
				modelo.addRow(new Object[]{result.getInt("id_venta"), result.getInt("id_cancelacion"), result.getString("tipoPago"), result.getDate("fecha"), result.getString("estado")});

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