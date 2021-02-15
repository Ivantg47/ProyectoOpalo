/**
 * Clase control del insumo.
 * @author Frida Janeth Hernández Torres
 * @version 1.5
 */

package ProyectoOpalo.control;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import ProyectoOpalo.igu.IGUInsumo;
import ProyectoOpalo.dao.DAOInsumo;
import ProyectoOpalo.dto.DTOInsumo;

public class ControlInsumo implements ActionListener, FocusListener{

	/**
     * Atributo que almacena un insumo.
     */
	private DTOInsumo oDTO;
	/**
     * Atributo que muestra la interface de insumo.
     */
	private IGUInsumo oIGU;
	/**
     * Atributo que maneja las operaciones de la BD.
     */
	private DAOInsumo oDAO;
	

	public ControlInsumo(IGUInsumo oIGU){

		this.oIGU = oIGU;

	}//ControlInsumo

	/**
     * Metodo, que escucha los eventos de los botones.
     * @param oEvento indica que un boton fue precionado
     */

	public void actionPerformed(ActionEvent oEvento){

		oDAO = new DAOInsumo();
		oDAO.getTabla(oIGU.getModelo());

		switch(oEvento.getActionCommand()){

			case "btAgregar":

				if(!oIGU.camposVacios()){

					oDTO = oIGU.leerDTO();

					if(datosCorrectos(oDTO)){

						oDAO.agregar(oDTO);
						oDAO.getTabla(oIGU.getModelo());
					}
					
				}
				
			break;

			case "btBuscar":

				if (!oIGU.getCampoBuscar().equals("Codigo/Nombre")) {  

					if (isNumeric(oIGU.getCampoBuscar())) {

						oDTO = oDAO.buscar(oIGU.getID());
						oIGU.mostrarDTO(oDTO);
						oDAO.getTabla(oIGU.getModelo());

					} else {

						oDTO = oDAO.buscarPorNombre(oIGU.getCampoBuscar());
						oIGU.mostrarDTO(oDTO);
						oDAO.getTabla(oIGU.getModelo());
						
					}

				} else {

					JOptionPane.showMessageDialog(null, "Error. Cambo vacio, escriba un codigo.");

				}
				
			break;

			case "btEliminar":
				
				if(oDTO != null && oIGU.getID() == oDTO.getId()){

					oDAO.eliminar(oDTO);
					oIGU.limpiarCamposTexto();
					oDAO.getTabla(oIGU.getModelo());

				} else {

					JOptionPane.showMessageDialog(null, "Error. Primero busque el insumo a eliminar.");
				}

			break;

			case "btModificar":

				if(oDTO != null && oIGU.getID() == oDTO.getId() && !oIGU.camposVacios()){

					oDTO = oIGU.leerDTO();

					if(datosCorrectos(oDTO)){
						oDTO.setId(oIGU.getID());
						oDAO.modificarInsumo(oDTO);
						oDAO.getTabla(oIGU.getModelo());
					}

				} else {

					JOptionPane.showMessageDialog(null, "Error. Primero busque el insumo a modificar.");
				}

			break;

			case "btLimpiar":

				oIGU.limpiarCamposTexto();

			break;

			
		}

	}//actionPerformed

	/**
     * Metodo que verifica si los datos en el objeto oDTO son validos.
     * @param oDTO objeto que contiene los valores del insumo
     * @return verdadero o falso
     */
	public boolean datosCorrectos(DTOInsumo oDTO){

		boolean bDatosCorrectos = false;

		if(oDTO.getNombre().compareTo("") != 0 && oDTO.getExistenciaMinima() > 0 && oDTO.getExistenciaMaxima() > 0 && oDTO.getExistenciaActual() > 0){

			bDatosCorrectos = true;
		}

		return bDatosCorrectos;
	}

	/**
     * Metodo, que espera a que el campo buscar sea seleccionado y en caso de no tener datos los limpia.
     * @param e indica cuando el campo buscar es seleccionado
     */
	public void focusGained(FocusEvent e) {
		
		JTextField campo = (JTextField) e.getSource();

		if(campo.getText().equals("Codigo/Nombre")){

	        campo.setForeground(Color.BLACK);
	        campo.setText(null);

    	}
           
    }

    /**
     * Metodo, que espera a que el campo buscar sea desseleccionado y en caso de no tener datos coloca un texto.
     * @param e indica cuando el campo buscar es desseleccionado
     */
    public void focusLost(FocusEvent e) {

    	JTextField campo = (JTextField) e.getSource();

    	if(campo.getText().equals("")){

    		campo.setText("Codigo/Nombre");
			campo.setForeground(new Color(111,111,111));

    	}
        
    }

    /**
     * Metodo que verifica si una cadena es un numero entero
     * @return verdadero o falso
     */
    public static boolean isNumeric(String sCadena) {

        boolean oResultado;

        try {

            Integer.parseInt(sCadena);
            oResultado = true;

        } catch (NumberFormatException oExcepcion) {

            oResultado = false;

        }

        return oResultado;
    }//isNumeric

}