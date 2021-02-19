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
import ProyectoOpalo.dto.DTOCompra;
import ProyectoOpalo.igu.IGUCompras;
import javax.swing.*;
import javax.swing.table.*;

public class DAOCompra{

	//Variables globales.
    private Connection conexion = null;
	private PreparedStatement prepared;
    private ResultSet result;
    static Statement stmt = null;

	/**
	 * Método constructor para instanciar en la clase control. 
	 */
    public DAOCompra(){

    }

	/**
	 * Función que verifica la existencia del insumo en la relación Insumo de la BD y en caso de existir devuelve el objeto con su ID.
	 * @param oCompra una compra que le hace falta el ID del insumo.
	 * @return oCompra que es el objeto con o sin el ID dependiendo del resultado.
	 */
/*
    public DTOCompra verificarInsumo(DTOCompra oCompra){
        
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
*/
	/**
	 * Método que devuelve la tabla con la información necesarioa del registro a la intefaz.
	 * @param modelo Recibe el modelo de la interfaz y lo llena.
	 */
/*
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
*/
	/**
	 * Método que inserta las compras en la BD.
	 * @param aCompras el arreglo de la lista de compras.
	 * @param indice el número de registros que tiene la lista.
	 */
/*
	public void insertarCompras(DTOCompra[] aCompras, int indice){

		String insertCompras, insertCompra_Insumo, lastID, mensaje = "";
		PreparedStatement preparedCompra, prepID, preparedCI;
		ResultSet resID;
		int prueba, indica = -1;

		int intArray[];
		intArray = new int[10];

		for (int i = 0; i <= indice; i++) {
			
			try {
			
				conexion = getConnection();
				String sql = "SELECT id_Insumo FROM Insumo WHERE nombre = ?;";
				prepared = conexion.prepareStatement(sql);
				prepared.setString(1, aCompras[i].getNombre());
	
				result = prepared.executeQuery();
	
				if(result.next()){ //si existe el insumo
	
					insertCompras = "INSERT INTO Compras (fechaCompra) VALUES(?);";
					preparedCompra = conexion.prepareStatement(insertCompras);
					preparedCompra.setDate(1, java.sql.Date.valueOf(aCompras[i].getFechaCompra()));

					if (preparedCompra.executeUpdate() != 0) {
				
						lastID = "SELECT last_insert_id() AS id_compra;";
						prepID = conexion.prepareStatement(lastID);
						resID = prepID.executeQuery();
		
						if (resID.next()){
		
							prueba = resID.getInt("id_compra");
							System.out.println("id_compra = " + prueba);
							insertCompra_Insumo = "INSERT INTO Compra_Insumo (id_compra, id_insumo, cantidad, costoTotal) VALUES(?, ?, ?, ?);";
							preparedCI = conexion.prepareStatement(insertCompra_Insumo);
							preparedCI.setInt(1, prueba);
							preparedCI.setInt(2, aCompras[i].getIdInsumo());
							preparedCI.setFloat(3, aCompras[i].getCantidad());
							preparedCI.setFloat(4, aCompras[i].getTotal());

							if (preparedCI.executeUpdate() != 0) {
								System.out.println("Se ha insertado en Compra_insumo");
								indica++;
								intArray[indica] = prueba;
							}
		
						} else {
							System.out.println("Error en el insertar Compra_Insumo");
						}
					} else {
						System.out.println("Error en el select last ID");
					}
	
				}else{
	
					JOptionPane.showMessageDialog(null, "Error en la insercion de la compra");
	
				}
	
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error: " + e);
			}

		}

		for (int j = 0; j <= indica; j++) {
			
			mensaje += mensaje + ", " + intArray[j];

		}

		JOptionPane.showMessageDialog(null, "Se han insertado las compras: " + mensaje + ".");

	}
*/
    /**
	 * Función que busca la compra y si la encuentra la retorna.
	 * @param idCompra el id que está buscando el usuario.
	 * @return compra que es el objeto con la compra si es que existe.
	 */
/*
    public DTOCompra buscarCompra(int idCompra){
		
		DTOCompra compra = new DTOCompra();
		IGUCompras igu = new IGUCompras();
		float uno, dos, tres;

		try {

			conexion = getConnection();

			String sql = "SELECT c.id_compra, i.nombre, ci.cantidad, ci.costoTotal, c.fechaCompra FROM Compras c INNER JOIN Compra_Insumo ci ON (c.id_compra = ci.id_compra) INNER JOIN Insumo i ON (ci.id_insumo = i.id_Insumo) WHERE ci.id_compra = ?;";
	
			prepared = conexion.prepareStatement(sql);
	
			prepared.setInt(1, idCompra);

			result = prepared.executeQuery();

			if (result.next()) {

				compra.setIdInsumo(result.getInt("id_compra"));				
				compra.setNombre(result.getString("nombre"));
				compra.setCantidad(result.getFloat("cantidad"));
				uno = result.getFloat("cantidad");
				compra.setTotal(result.getFloat("costoTotal"));
				dos = result.getFloat("costoTotal");
				compra.setFechaCompra(String.valueOf(result.getDate("fechaCompra")));
				tres = uno * dos; 
				compra.setFinal(tres);//luego

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
*/
    /**
	 * Método que establece la conexión con la BD.
	 * @return conexion que es un objeto de la clase Connection.
	 */
/*
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
 */

}