/**
 * Clase que conecta con la BD para los métodos de compras de insumos.
 * @author Diego Puebla Aldama
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

public class DAOCompra{

    private Connection conexion = null;
	private PreparedStatement prepared;
    private ResultSet result;
	//DTOCompra oCompra;

    //Método constructor para instanciar en la clase control.
    public DAOCompra(){

    }

    //Necesito insertar en Compras y en Compra_Insumo por cada compra, pero debo obtener la PK de el insumo que se compró para Compra_Insumo y si no existe mandar a registrarlo.
    public void registrarCompra(DTOCompra oCompra){
        
        //primero verificamos que exista o no el insumo.
        try {
           
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "No existe el insumo, favor de ir a registrarlo.");

        }



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