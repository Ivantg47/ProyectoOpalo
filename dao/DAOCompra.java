/**
 * Clase que conecta con la BD para los métodos de compras de insumos.
 * @author Diego Puebla Aldama
 * @version 1.0
 */

 /*
 ;

 */

package ProyectoOpalo.dao;
import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import ProyectoOpalo.dto.DTOCompra;
import ProyectoOpalo.igu.IGUCompras;
import javax.swing.*;
import javax.swing.table.*;

public class DAOCompra{

    private Connection conexion = null;
	private PreparedStatement prepared;
    private ResultSet result;
    static Statement stmt = null;

    //Método constructor para instanciar en la clase control.
    public DAOCompra(){

    }

    //Necesito insertar en Compras y en Compra_Insumo por cada compra, pero debo obtener la PK de el insumo que se compró para Compra_Insumo y si no existe mandar a registrarlo.
    public DTOCompra verificarInsumo(DTOCompra oCompra){
        
        //primero verificamos que exista o no el insumo.
        try {
           
            conexion = getConnection();
            String sql = "SELECT id_Insumo FROM Insumo WHERE nombre = ?;";
            prepared = conexion.prepareStatement(sql);
            prepared.setString(1, oCompra.getNombre());

            result = prepared.executeQuery();

            if(result.next()){ //si existe el insumo

                oCompra.setIdInsumo(result.getInt("id_Insumo")); //guardamos el ID para registrarlo en Compra_insumo

            }else{

                JOptionPane.showMessageDialog(null, "No existe el insumo, favor de ir a registrarlo.");
                return null; //no encontró el insumo.
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Excepcion en el método DTOCompra.verificarInsumo() " + e);

        }

        return oCompra; //Ya tiene el ID y se pasa a la tabla de pre-registro.

    }

    public void insertarSecundaria(DTOCompra compra){

        try {

			conexion = getConnection();

			String sql = "INSERT INTO Secundaria (nombre, cantidad, costo) VALUES (?, ?, ?);";
	
			prepared = conexion.prepareStatement(sql);
	
			prepared.setString(1, compra.getNombre());
			prepared.setFloat(2, compra.getCantidad());
			prepared.setFloat(3, compra.getTotal());

            if (prepared.executeUpdate() != 0) {
				
				JOptionPane.showMessageDialog(null, "Producto preregistrado");

			}else{

                JOptionPane.showMessageDialog(null, "Producto no preregistrado");

            }

			conexion.close();

        }catch(Exception e){

        }

    }

    public void getTabla(DefaultTableModel modelo){

		try {

			conexion = getConnection();

			String sql = "SELECT c.id_compra, i.nombre, ci.cantidad, ci.costoTotal, c.fechaCompra FROM Compras c INNER JOIN Compra_Insumo ci ON (c.id_compra = ci.id_compra) INNER JOIN Insumo i ON (ci.id_insumo = i.id_Insumo) ORDER BY c.fechaCompra;";
	
			prepared = conexion.prepareStatement(sql);

			result = prepared.executeQuery();

			modelo.setRowCount(0);

			while (result.next()) {
				
				modelo.addRow(new Object[]{result.getInt("id_compra"), result.getString("nombre"), result.getFloat("cantidad"), result.getFloat("costoTotal")});

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

    //mét pam
    public DTOCompra buscarCompra(int idCompra){
		
		DTOCompra compra = new DTOCompra();
		IGUCompras igu = new IGUCompras();

		try {

			conexion = getConnection();

			String sql = "SELECT c.id_compra, i.nombre, ci.cantidad, ci.costoTotal, c.fechaCompra FROM Compras c INNER JOIN Compra_Insumo ci ON (c.id_compra = ci.id_compra) INNER JOIN Insumo i ON (ci.id_insumo = i.id_Insumo) WHERE c.id_compra = ?;";
	
			prepared = conexion.prepareStatement(sql);
	
			prepared.setInt(1, idCompra);

			result = prepared.executeQuery();

			if (result.next()) {

				compra.setIdInsumo(result.getInt("id_compra"));				
				compra.setNombre(result.getString("nombre"));
				compra.setCantidad(result.getFloat("cantidad"));
				compra.setTotal(result.getFloat("costoTotal"));
				compra.setFechaCompra(String.valueOf(result.getDate("fechaCompra")));

				igu.mostrarDatosBusqueda(compra);

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

	    return compra;
	}

    //Método que establece la conexión con la BD
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