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
import ProyectoOpalo.dao.*;
import ProyectoOpalo.dto.*;
// import ProyectoOpalo.dto.DTOProducto;

public class ControlVenta implements ActionListener, FocusListener{

	private DAOVentas daoVentas;
	private DTOVentas dtoventas;
	private IGUVentas iguVentas;

	public ControlVenta(IGUVentas iguVentas){

		this.iguVentas = iguVentas;

	}

	public void actionPerformed(ActionEvent evento){

		if (evento.getSource() instanceof JTextField) {
			JTextField fuente = (JTextField) evento.getSource();
		} else {
			JButton fuente = (JButton) evento.getSource();
		}
		
		
		try{

			if (evento.getActionCommand().equals("buscarCliente")) {
				
				iguVentas.setCampoCliente(new DAOClientes().buscarCliente(iguVentas.getCampoCliente()));

			} else if (evento.getActionCommand().equals("limpiarCliente")) {

				iguVentas.limpiarCampoCliente();
				
			} else if (evento.getActionCommand().equals("buscarProducto")) {
				
				iguVentas.setCampoProducto(new DAOProducto().getProducto(iguVentas.getCampoProducto()));

			} else if (evento.getActionCommand().equals("limpiarProducto")) {

				iguVentas.limpiarCampoProducto();
				
			} else if (evento.getActionCommand().equals("agregar")) {
				
				iguVentas.agregarProducto();
				iguVentas.limpiarCampoProducto();
				
			} else if (evento.getActionCommand().equals("quitar")) {
				
				
				
			} else if (evento.getActionCommand().equals("")) {


			}

		} catch (NullPointerException nullEx) {

			JOptionPane.showMessageDialog(iguVentas, "No debe dejar campos vacios",
           									"Campo vacio", JOptionPane.ERROR_MESSAGE);

		} catch (NumberFormatException numEx) {

			JOptionPane.showMessageDialog( null, "Dato incorrecto",
           				"Error en formato de número", JOptionPane.ERROR_MESSAGE);

		} catch (IllegalArgumentException illEx){

			JOptionPane.showMessageDialog( null, illEx.getMessage(),
           				"Error en formato de número", JOptionPane.ERROR_MESSAGE);

		}
	}

	public void focusGained(FocusEvent e) {

		JTextField campo = (JTextField) e.getSource();

    }

    public void focusLost(FocusEvent e) {

    	JTextField campo = (JTextField) e.getSource();
        
    }

}