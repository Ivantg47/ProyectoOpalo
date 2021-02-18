/**
 * Clase IGU de Ventas.
 * @author Pamela Stephanie Moreno Parker
 * @author Ivan Tronco
 * @version 1.0
 */

package ProyectoOpalo.control;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import ProyectoOpalo.igu.IGUVentas;
import ProyectoOpalo.dao.*;
import ProyectoOpalo.dto.*;

public class ControlVenta implements ActionListener, FocusListener{

	/**
 	 * Atributo del tipo DAOVentas.
	 */	
	private DAOVentas daoVentas;
	/**
 	 * Atributo del tipo DTOVentas.
	 */	
	private DTOVentas dtoventas;
	/**
 	 * Atributo del tipo IGUVentas.
	 */	
	private IGUVentas iguVentas;

	/**
     * Constructor para la clase ControlVenta
     * @param iguVentas objeto de la clase IGUVentas.
     */	
	public ControlVenta(IGUVentas iguVentas){

		this.iguVentas = iguVentas;

	}

	/**
     * actionPerformed es un método que sirve para configurar los eventos que suceden al 
     * manipular la intefaz
     * @param evento objeto del tipo ActionEvent
     */
	public void actionPerformed(ActionEvent evento){

		daoVentas = new DAOVentas();
		
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
				
				iguVentas.quitarProducto();
				
			} else if (evento.getActionCommand().equals("venta")) {

				daoVentas.agregarVenta(iguVentas.generarVenta());
				iguVentas.nuevaVenta();

				JOptionPane.showMessageDialog( null, "Venta registrada",
               					"Registro venta", JOptionPane.INFORMATION_MESSAGE);

			} else if (evento.getActionCommand().equals("cancelar")) {

				// daoVentas.cancelarVenta(iguVentas.generarVenta());
				iguVentas.nuevaVenta();

				JOptionPane.showMessageDialog( null, "Venta cancelada",
               					"Cancelacion venta", JOptionPane.INFORMATION_MESSAGE);

			} else if (evento.getActionCommand().equals("nueva")) {

				iguVentas.nuevaVenta();

			} 

		} catch (NullPointerException nullEx) {

			JOptionPane.showMessageDialog(iguVentas, "No debe dejar campos vacios",
           									"Campo vacio", JOptionPane.ERROR_MESSAGE);

		} catch (NumberFormatException numEx) {

			JOptionPane.showMessageDialog(iguVentas, "Dato incorrecto",
           				"Error en formato de número", JOptionPane.ERROR_MESSAGE);

		} catch (IllegalArgumentException illEx){

			JOptionPane.showMessageDialog(iguVentas, illEx.getMessage(),
           				"Error", JOptionPane.ERROR_MESSAGE);

		} catch (ArrayIndexOutOfBoundsException arEx){

			JOptionPane.showMessageDialog(iguVentas, "No se han agregado productos",
           									"Fuera de rango", JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex){

			// JOptionPane.showMessageDialog(iguVentas, "Error en base de datos",
   //         				"Error en base de datos", JOptionPane.ERROR_MESSAGE);

		}
	}
	
	/**
     * focusGained es un método que sirve para saber cuando se selecciona un campo de texto.
     * @param e objeto del tipo FocusEvent
     */	
	public void focusGained(FocusEvent e) {

		JTextField campo = (JTextField) e.getSource();

    }

    /**
     * focusLost es un método que sirve para saber cuando se deselcciona un campo de texto.
     * @param e objeto del tipo FocusEvent
     */	
    public void focusLost(FocusEvent e) {

    	JTextField campo = (JTextField) e.getSource();
        
    }

}