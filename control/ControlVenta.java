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
import ProyectoOpalo.dto.DTOProducto;

public class ControlVenta implements ActionListener, FocusListener{

	private DAOVentas daoVentas;
	private DTOVentas dtoventas;
	private IGUVentas iguVentas;

	public ControlVenta(IGUVentas iguVentas){

		this.iguVentas = iguVentas;

	}

	public void actionPerformed(ActionEvent evento){

		JButton fuente = (JButton) evento.getSource();
		
		switch (fuente.getActionCommand()){

			case "btAceptarC":
				iguVentas.leerDatosCliente();		
				break;
			case "btLimpiarC":
				iguVentas.limpiarDatosCliente();
				break;
			case "btAceptarP":
				iguVentas.leerDatosProducto();
				break;
			case "btLimpiarP":
				iguVentas.limpiarDatosProducto();
				break;
			case "btAgregar":
				DAOVentas daoVentas = new DAOVentas();
				daoVentas.agregarVenta(iguVentas.ventas);
				break;
			case "btBuscar":
				DAOVentas daoVentasB = new DAOVentas();
				iguVentas.ventas = daoVentasB.buscarVenta(iguVentas.leerDatoBuscar());
				break;
			case "btCancelar":
				DAOVentas daoVentasC = new DAOVentas();
				daoVentasC.CancelarVenta(iguVentas.leerDatoBuscar(), iguVentas.ventas);
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