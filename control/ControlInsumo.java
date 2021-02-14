package ProyectoOpalo.control;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import ProyectoOpalo.igu.IGUInsumo;
import ProyectoOpalo.dao.DAOInsumo;
import ProyectoOpalo.dto.DTOInsumo;

public class ControlInsumo implements ActionListener{

	private DTOInsumo oDTO;
	private IGUInsumo oIGU;
	private DAOInsumo oDAO;
	

	public ControlInsumo(IGUInsumo oIGU){

		this.oIGU = oIGU;

	}


	public void actionPerformed(ActionEvent oEvento){

		oDAO = new DAOInsumo();

		switch(oEvento.getActionCommand()){

			case "btAgregar":

				if(!oIGU.camposVacios()){

					oDTO = oIGU.leerDTO();
					oDAO.agregar(oDTO);
				}
				
			break;

			case "btBuscar":

				if (oIGU.getID() != 0){

					oDTO = oDAO.buscar(oIGU.getID());
					oIGU.mostrarDTO(oDTO);
				} else {

					JOptionPane.showMessageDialog(null, "Error. Cambo vacio, escriba un codigo.");
				}
				
			break;

			case "btEliminar":
				
				if(oDTO != null && oIGU.getID() == oDTO.getId()){

					oDAO.eliminar(oDTO);
					oIGU.limpiarCamposTexto();

				} else {

					JOptionPane.showMessageDialog(null, "Error. Primero busque el insumo a eliminar.");
				}

			break;

			case "btModificar":

				if(oDTO != null && oIGU.getID() == oDTO.getId() && !oIGU.camposVacios()){

						oDTO = oIGU.leerDTO();
						oDTO.setId(oIGU.getID());
						oDAO.modificarInsumo(oDTO);

				} else {

					JOptionPane.showMessageDialog(null, "Error. Primero busque el insumo a modificar.");
				}

			break;


			
		}

	}


}