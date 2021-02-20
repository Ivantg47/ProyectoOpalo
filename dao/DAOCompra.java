/**
 * Clase que conecta con la BD para los métodos de compras de insumos.
 * @author Diego Puebla Aldama
 * @version 2.0
 */

package ProyectoOpalo.dao;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Date;
import ProyectoOpalo.dto.DTOCompra;
import ProyectoOpalo.igu.IGUCompras;
import javax.swing.*;
import javax.swing.table.*;

public class DAOCompra{

    private Connection conexion = null;
	private PreparedStatement prepared;
    private ResultSet result, resultF;

	/**
	 * Método constructor para instanciar en la clase control. 
	 */
    public DAOCompra(){

    }

    public Connection getConnection() {
   
        try {
            
            String driver = "com.mysql.cj.jdbc.Driver";

            Class.forName(driver);

	        String jdbcUrl = "jdbc:mysql://25.67.54.75:3306/ProyectoOpalo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
	        String usuario = "proyecto";
	        String paswd = "hola";

	        conexion = DriverManager.getConnection(jdbcUrl, usuario, paswd);
        
            
        } catch(SQLException oExcepcionSQL){

			JOptionPane.showMessageDialog(null, "Error al establecer la conexi\u00F3n");
			oExcepcionSQL.printStackTrace();

		} catch (Exception oExcepcion) {

			oExcepcion.printStackTrace();

		}
       
        return conexion;
        
    } 

	/**
	 * Función que verifica la existencia del insumo en la relación Insumo de la BD y en caso de existir devuelve el objeto con su ID.
	 * @param oCompra una compra que le hace falta el ID del insumo.
	 * @return oCompra que es el objeto con o sin el ID dependiendo del resultado.
	 */

	public int agregar(DTOCompra compra) throws IllegalArgumentException, SQLException{

		int folio = 0;

		try{

			conexion = getConnection();

			String sql = "INSERT INTO Compras (id_compra, fechaCompra) VALUES (?, ?);";

			prepared = conexion.prepareStatement(sql);

			prepared.setInt(1, compra.getId());
			prepared.setDate(2, java.sql.Date.valueOf(compra.getFechaCompra()));

			if (prepared.executeUpdate() != 0) {

				sql = "SELECT last_insert_id() AS codigo;";
				prepared = conexion.prepareStatement(sql);
				result = prepared.executeQuery();

				if (result.next()){
					folio = result.getInt("codigo");
					int aInsumos[] = compra.getIDInsumos();
					int aCantidad[] = compra.getCantidades();
					float aPrecioTotal[] = compra.getCostosUnitarios();

					for (int con = 0; con < aInsumos.length; con++){

						sql = "INSERT INTO Compra_Insumo VALUES (?, ?, ?, ?);";
							prepared = conexion.prepareStatement(sql);
							prepared.setInt(1, result.getInt("codigo"));
							prepared.setInt(2, aInsumos[con]);
							prepared.setInt(3, aCantidad[con]);
							prepared.setFloat(4, aPrecioTotal[con]);

						if (prepared.executeUpdate() == 0) {
							
							throw new IllegalArgumentException("Error al registrar la venta ");

						}

					}
				} else {

					throw new IllegalArgumentException("Error al registrar la compra");
				}

			} else {

				throw new IllegalArgumentException("Error al registrar la compra");
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

	public DTOCompra buscar(int idCompra, DefaultTableModel modelo) throws IllegalArgumentException, SQLException{

		DTOCompra compra = new DTOCompra();

		try{

			conexion = getConnection();
			String sql = "SELECT * FROM Compras WHERE Id_compra = ?;";

			prepared = conexion.prepareStatement(sql);

			prepared.setInt(1, idCompra);

			result = prepared.executeQuery();

			if (result.next()) {

				compra.setId(idCompra);
				compra.setFechaCompra(result.getString("fechaCompra"));
				compra.setEstado(result.getString("estado"));

				sql = ("SELECT I.id_Insumo AS IdInsumo, I.nombre AS nombre, "
					+ "CI.cantidad AS cantidad, CI.costoTotal AS precio, fechaCompra "
					+ "FROM Compras C "
					+ "INNER JOIN Compra_Insumo CI "
					+ "INNER JOIN Insumo I "
					+ "WHERE(C.Id_compra = ? AND C.Id_compra  = CI.Id_compra AND CI.id_Insumo = I.id_Insumo);");

					prepared = conexion.prepareStatement(sql);

					prepared.setInt(1, idCompra);

					result = prepared.executeQuery();

					while (result.next()){
						
						modelo.addRow(new Object[]{result.getInt("IdInsumo"), result.getString("nombre"), result.getFloat("cantidad"),
												result.getInt("precio"), (result.getInt("cantidad") * result.getFloat("precio"))});

					}
				

			} else {

				throw new IllegalArgumentException("No hay compras registradas con ese folio");

			}

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

	    return compra;
		

	}

	public void cancelar(DTOCompra compra){

		try{

			conexion = getConnection();

			String sql = "CALL cancelar (?);";

			prepared = conexion.prepareStatement(sql);

			prepared.setString(1, compra.getMotivo());

			result = prepared.executeQuery();

			if (result.next()){

				sql =  "UPDATE Compras SET id_cancelacion = ?, estado = ? WHERE Id_compra = ? ;";

				prepared = conexion.prepareStatement(sql);

				prepared.setInt(1, result.getInt("id_cancelacion"));
				prepared.setString(2, compra.getEstado());
				prepared.setInt(3, compra.getId());			

				if (prepared.executeUpdate() == 0) {
					
					throw new IllegalArgumentException("Error: En cancelacion");

				}

			} else {

				throw new IllegalArgumentException("Error: En cancelacion");
			}

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
}