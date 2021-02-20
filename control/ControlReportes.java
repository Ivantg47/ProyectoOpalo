/**
 * Clase control de reportes.
 * @author Ivan Tronco
 * @version 1.0
 */

package ProyectoOpalo.control;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import ProyectoOpalo.igu.*;
import ProyectoOpalo.dao.*;
import java.sql.Date;


public class ControlReportes implements ActionListener, FocusListener {

	/**
     * Atributo que almacena la dao de producto.
     */
	// private DAOReportes dao;

	/**
     * Atributo que conecta con la interfas de producto.
     */
	private IGUReporte igu;

	/**
     * Constructor, recibe igu de profucto.
     * @param igu
     */
	public ControlReportes(IGUReporte igu){

		this.igu = igu;

	}//ControlReportes

	/**
     * actionPerformed es un método que sirve para configurar los eventos que suceden al 
     * manipular la intefaz
     * @param evento objeto del tipo ActionEvent
     */
	public void actionPerformed(ActionEvent evento){
		
		try{	
			
			if (evento.getActionCommand().equals("consulta")) {
				String fechaI = igu.getFechaInicio(), fechaF = igu.getFechaFinal();

				if (fechaI.compareTo(fechaF) <= 0) {

					new DAOReportes().generarReporte(igu.getModelo(), Date.valueOf(fechaI ), Date.valueOf(fechaF));
					igu.setReporte("Reporte del " + fechaI  + " al " + fechaF);
					igu.generarTotal();

				} else {

					JOptionPane.showMessageDialog( null, "La fecha inicial no puede ser mayor a la fecha final",
           				"Error en formato de fecha", JOptionPane.ERROR_MESSAGE);
					
				}

			} else if (evento.getActionCommand().equals("imprimir")) {
				
				JOptionPane.showMessageDialog( null, "Imprimiendo...",
           				"Impresion", JOptionPane.PLAIN_MESSAGE, new ImageIcon(getClass().getResource("/iconos/impresora (2).png")));

			}
			

		} catch (IllegalArgumentException illEx){

			JOptionPane.showMessageDialog( null, "La fecha de usar formato YYYY-MM-DD",
           				"Error en formato de fecha", JOptionPane.ERROR_MESSAGE);

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

		if (campo.getText().equals("yyyy-mm-dd")){

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

    	if (campo.getText().equals("")){

	    	campo.setText("yyyy-mm-dd");
			campo.setForeground(new Color(111,111,111));

    	}   	
        
    }
}