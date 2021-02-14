/**
 * Clase control de ventas.
 * @author Pamela Stephanie Moreno Parker
 * @version 1.0
 */

package ProyectoOpalo.control;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import ProyectoOpalo.igu.IGUVentas;
import ProyectoOpalo.dao.DAOVentas;
import ProyectoOpalo.dto.DTOVentas;

public class ControlVenta implements ActionListener, FocusListener{

	private DTOVentas dtoVentas;
	private DAOVentas daoVentas;
	private IGUVentas iguVentas;

	public ControlVenta(IGUVentas iguVentas){

		this.iguVentas = iguVentas;

	}

	public void actionPerformed(ActionEvent evento){

		JButton fuente = (JButton) evento.getSource();
		daoVentas = new DAOVentas();
		
		switch (fuente.getActionCommand()){

			case "btAceptarC":
				iguVentas.leerDatosCliente();		
				break;
			case "btLimpiarC":
				iguVentas.limpiarDatosCliente();
				break;
			case "btAgregar":
				daoVentas.agregarVenta(daoVentas);
				break;
				
		}
	}

	public void focusGained(FocusEvent e) {

		JTextField campo = (JTextField) e.getSource();

    }

    public void focusLost(FocusEvent e) {

    	JTextField campo = (JTextField) e.getSource();
        
    }

}