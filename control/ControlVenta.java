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
				
			} else if (evento.getActionCommand().equals("agregarProducto")) {
				
				iguVentas.agregarProducto();
				iguVentas.limpiarCampoProducto();
				
			} else if (evento.getActionCommand().equals("quitarProducto")) {
				
				iguVentas.quitarProducto();
				
			} else if (evento.getActionCommand().equals("concretarVenta")) {

				int folio = daoVentas.agregarVenta(iguVentas.generarVenta());
				iguVentas.nuevaVenta();

				JOptionPane.showMessageDialog( null, "Venta registrada con folio: " + folio,
               					"Registro venta", JOptionPane.INFORMATION_MESSAGE);

			} else if (evento.getActionCommand().equals("cancelarVenta")) {

				// daoVentas.cancelarVenta(iguVentas.generarVenta());
				iguVentas.nuevaVenta();

				JOptionPane.showMessageDialog( null, "Venta cancelada",
               					"Cancelacion venta", JOptionPane.INFORMATION_MESSAGE);

			} else if (evento.getActionCommand().equals("nuevaVenta")) {

				iguVentas.nuevaVenta();

			} else if (evento.getActionCommand().equals("buscarVenta")) {
				
				iguVentas.nuevaVenta();
				iguVentas.setVenta(daoVentas.buscarVenta(iguVentas.getCampoBuscar(), iguVentas.getModelo()));
				

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

			ex.printStackTrace();

		}
	}
	
	/**
     * focusGained es un método que sirve para saber cuando se selecciona un campo de texto.
     * @param evento objeto del tipo FocusEvent
     */	
	public void focusGained(FocusEvent evento) {

		JTextField campo = (JTextField) evento.getSource();

		if(campo.getText().equals("Folio")){

	        campo.setForeground(Color.BLACK);
	        campo.setText(null);

    	}
    }

    /**
     * focusLost es un método que sirve para saber cuando se deselcciona un campo de texto.
     * @param evento objeto del tipo FocusEvent
     */	
    public void focusLost(FocusEvent evento) {

    	JTextField campo = (JTextField) evento.getSource();

    	if(campo.getText().equals("")){

	    	campo.setText("Folio");
			campo.setForeground(new Color(111,111,111));

		}
        
    }

}