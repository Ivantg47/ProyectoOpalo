/**Control de los clientes
*@author Ximena Rojas
* @version 1.0
*/

package ProyectoOpalo.control;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import ProyectoOpalo.igu.IGUClientes;
import ProyectoOpalo.dao.DAOClientes;
import ProyectoOpalo.dto.DTOClientes;

public class ControlClientes implements ActionListener{

	private DTOClientes dto;
	private DAOClientes dao;
	private IGUClientes igu;

	public ControlClientes(IGUClientes igu){

		this.igu = igu;
	}

	public void actionPerformed(ActionEvent oEvento){

		dao = new DAOClientes();
		dao.getTabla(igu.getModelo());

		switch (oEvento.getActionCommand()){

			case "btAgregar":
				//if (!igu.camposVacios()){

					dto = igu.getDTO();

					/*if(datosCorrectos(dto)){*/

						dao.agregarCliente(dto);
						dao.getTabla(igu.getModelo());
					//}

				//}
			
			break;

			case ("btEliminar"):

				//if(dto != null && igu.getId() == dto.getIdCliente() && !igu.camposVacios()){

					dao.eliminarCliente(dto);
					igu.limpiar();
					dao.getTabla(igu.getModelo());
				//} else {

				//	JOptionPane.showMessageDialog(null, "Error. Primero busque el cliente a eliminar.");
				//}


			break;

			case "btModificar":

				//if(dto != null && /*igu.getId() == dto.getIdCliente() && !igu.camposVacios()){

					dto = igu.getDTO();

					//if(datosCorrectos(dto)){
						dto.setIdCliente(igu.getId());
						dao.modificarCliente(dto);
						dao.getTabla(igu.getModelo());
					//}

				//} else {

					//JOptionPane.showMessageDialog(null, "Error. Primero busque el cliente a modificar.");
				//}

			break;

			case "btBuscar":

			//	if (igu.getId() != 0) {
					dto = dao.buscarCliente(igu.getId());
					igu.mostrarDTO(dto);
					//dao.getTabla(igu.getModelo());
						
			/*	} else {

					JOptionPane.showMessageDialog(null, "Error. Escriba un ID.");

				}*/

			break;
		}
	}


	public boolean datosCorrectos(DTOClientes dto){

		boolean bDatosCorrectos = false;

		if(dto.getNombre().compareTo("") != 0 && dto.getPaterno().compareTo("") != 0 && dto.getMaterno().compareTo("") != 0){

			bDatosCorrectos = true;
		}
		
		return bDatosCorrectos;
	}



}