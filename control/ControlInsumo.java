package ProyectoOpalo.control;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import ProyectoOpalo.igu.IGUInsumo;
import ProyectoOpalo.dao.DAOInsumo;
import ProyectoOpalo.dto.DTOInsumo;

public class ControlInsumo implements ActionListener, FocusListener{

	private DTOInsumo oDTO;
	private IGUInsumo oIGU;
	private DAOInsumo oDAO;
	

	public ControlInsumo(IGUInsumo oIGU){

		this.oIGU = oIGU;

	}


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

				if (oIGU.getID() != 0){

					oDTO = oDAO.buscar(oIGU.getID());
					oIGU.mostrarDTO(oDTO);
					oDAO.getTabla(oIGU.getModelo());
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

	}

	public boolean datosCorrectos(DTOInsumo oDTO){

		boolean bDatosCorrectos = false;

		if(oDTO.getNombre().compareTo("") != 0 && oDTO.getExistenciaMinima() > 0 && oDTO.getExistenciaMaxima() > 0 && oDTO.getExistenciaActual() > 0){

			bDatosCorrectos = true;
		}

		return bDatosCorrectos;
	}

	public void focusGained(FocusEvent e) {
		
		JTextField campo = (JTextField) e.getSource();

		if(campo.getText().equals("Codigo/Nombre")){

	        campo.setForeground(Color.BLACK);
	        campo.setText(null);

    	}
           
    }

    public void focusLost(FocusEvent e) {

    	JTextField campo = (JTextField) e.getSource();

    	if(campo.getText().equals("")){

    		campo.setText("Codigo/Nombre");
			campo.setForeground(new Color(111,111,111));

    	}
        
    }

}