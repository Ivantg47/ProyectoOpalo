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

public class ControlClientes implements ActionListener, FocusListener{

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

				if (!igu.camposVacios()){

					dto = igu.getDTO();

					if(datosCorrectos(dto)){

						dao.agregarCliente(dto);
						dao.getTabla(igu.getModelo());
					}

				}
			
			break;

			case ("btEliminar"):

				if(!igu.camposVacios()){

					dto = igu.getDTO();
					dao.eliminarCliente(dto);
					igu.limpiar();
					dao.getTabla(igu.getModelo());

				} else {

					JOptionPane.showMessageDialog(null, "Error. Primero busque el cliente a eliminar.");
				}


			break;

			case "btModificar":

				if(!igu.camposVacios()){

					dto = igu.getDTO();

					if(datosCorrectos(dto)){

					//	dto.setIdCliente(igu.getId());
						dao.modificarCliente(dto);
						dao.getTabla(igu.getModelo());

					}

				} else {

					JOptionPane.showMessageDialog(null, "Error. Primero busque el cliente a modificar.");
				}

			break;

			case "btBuscar":
				if (!igu.getCampoBuscar().equals("Codigo/Nombre")) { 
					
					dao.getTabla(igu.getModelo());

					if (isNumeric(igu.getCampoBuscar())) {

						dto = dao.buscarCliente(igu.getId());
						igu.mostrarDTO(dto);

					} else {
						System.out.println("nombre");
						if(comprobarNombre(igu.getCampoBuscar())){
							
							dto = dao.buscarNombre(igu.getCampoBuscar(), igu.getModelo());
							igu.mostrarDTO(dto);

						} else {
							System.out.println("no entro");
						}
					}
		
					igu.setBuscar();

				} else {

					JOptionPane.showMessageDialog(null, "Error. Escriba un ID.");

				}

			break;

			case "btLimpiar":

				igu.limpiar();

			break;
		}

	}//actionPerformed


	public boolean datosCorrectos(DTOClientes dto){

		boolean bDatosCorrectos = false;

		if(dto.getNombre().compareTo("") != 0 && dto.getPaterno().compareTo("") != 0 && dto.getMaterno().compareTo("") != 0 && dto.getCorreo().compareTo("") != 0 && dto.getTelefono().compareTo("") != 0 && dto.getDireccion().compareTo("") != 0){

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

  	public boolean comprobarNombre(String sNombre){

  		
	    for (int eCont = 0; eCont < sNombre.length(); eCont++) {
	        char cCaracter = sNombre.charAt(eCont);
	       
	        if (!((cCaracter >= 'a' && cCaracter <= 'z') || (cCaracter >= 'A' && cCaracter <= 'Z') || cCaracter == ' ')) {

	            return false;

	        }
	    }

	    return true;

  	}

}