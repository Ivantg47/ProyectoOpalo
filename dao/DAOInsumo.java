import java.sql.*;
package ProyectoOpalo.dao;//Pertenece a este paquete

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class DAOInsumo{

	private Connection oConexion = null;
	private PreparedStatement oSentencia = null;
	
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

	}

	public void agregarInsumo(DTOInsumo oInsumo){

		int eExecucion

		try{

			oConexion = getConexion();

			String sConsultaInsertar = 	"INSERT INTO Insumo (unidadMedida, nombre, existenciaMinima,  existenciaMaxima, existenciaActual) " +
                               			"VALUES(?, ?, ?, ?, ?);";

            oSentencia = oConexion.prepareStatement(sConsultaInsertar);

            oSentencia.setString(1, oInsumo.getUnidadMedida());
            oSentencia.setString(2, oInsumo.getNombre());
            oSentencia.setDouble(3, oInsumo.getExistenciaMinima());
            oSentencia.setDouble(4, oInsumo.getExistenciaMaxima());
            oSentencia.setDouble(5, oInsumo.getExistenciaActual());

            eExecucion = oSentencia.executeUpdate();

            if (eExecucion == 1) {

            	JOptionPane.showMessageDialog(null, "Insumo registrado");

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

		

	}

}//Fin agregarInsumo

/*

+borrarProducto(in eIdInsumo: int, inout ePos: int): void
+consultar(in eIdInsumo: int): void
+modificarInsumo(in eIdProducto: int): void
+mostrarDatosInsumo(): String
+getInsumo(): String
+getInsumoss():String
+buscarInsumo(in eIdProducto: int): int
+modificarExistencias(in CantidadModificar: float): void
*/