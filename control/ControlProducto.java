/**
 * Clase control del producto.
 * @author Ivan Tronco
 * @version 1.0
 */

package ProyectoOpalo.control;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import ProyectoOpalo.igu.IGUProducto;
import ProyectoOpalo.dao.DAOProducto;
import ProyectoOpalo.dto.DTOProducto;

public class ControlProducto implements ActionListener, FocusListener{

	/**
     * Atributo que almacena un producto.
     */
	private DTOProducto producto;

	/**
     * Atributo que almacena la dao de producto.
     */
	private DAOProducto dao;

	/**
     * Atributo que conecta con la interfas de producto.
     */
	private IGUProducto igu;

	/**
     * Constructor, recibe igu de profucto.
     * @param igu
     */
	public ControlProducto(IGUProducto igu){

		this.igu = igu;

	}//ControlProducto

	/**
     * Metodo, que escucha los eventos de los botones.
     * @param evento indica que un boton fue precionado
     */
	public void actionPerformed(ActionEvent evento){

		dao = new DAOProducto();

		try{

			if (evento.getActionCommand().equals("buscar")) {
				
				if (!igu.getBuscar().equals("Codigo/Nombre")) {
					
					dao.getTabla(igu.getModelo());
				
					if (isNumeric(igu.getBuscar())) {
									
						igu.setCampos(dao.getProducto(Integer.parseInt(igu.getBuscar())));
						
	
					} else {
						
						igu.setCampos(dao.getProducto(igu.getBuscar(), igu.getModelo()));
	
					}
					
					igu.setBuscar();
				}

			} else if (evento.getActionCommand().equals("agregar")) {
				
				dao.agregarProducto(igu.getCampos());
				dao.getTabla(igu.getModelo());
				igu.limpiar();

				JOptionPane.showMessageDialog( null, "Producto registrado",
               					"Registro producto", JOptionPane.INFORMATION_MESSAGE);

			} else if (evento.getActionCommand().equals("eliminar")) {
				
				dao.borrarProducto(igu.getCampos());
				dao.getTabla(igu.getModelo());
				igu.limpiar();

				JOptionPane.showMessageDialog( null, "Producto eliminado",
               					"Eliminar producto", JOptionPane.INFORMATION_MESSAGE);

			} else if (evento.getActionCommand().equals("modificar")) {
				
				dao.actualizarProducto(igu.getCampos());
				dao.getTabla(igu.getModelo());
				igu.limpiar();

				JOptionPane.showMessageDialog( null, "Producto actualizado",
               					"Actualizar producto", JOptionPane.INFORMATION_MESSAGE);

			} else if (evento.getActionCommand().equals("limpiar")) {
				
				igu.limpiar();

			}

		} catch (NullPointerException nullEx) {

			JOptionPane.showMessageDialog( null, "No debe dejar campos vacios",
           									"Campo vacio", JOptionPane.ERROR_MESSAGE);

		} catch (NumberFormatException numEx) {

			JOptionPane.showMessageDialog( null, "Dato incorrecto",
           				"Error en formato de número", JOptionPane.ERROR_MESSAGE);

		} catch (IllegalArgumentException illEx){

			JOptionPane.showMessageDialog( null, illEx.getMessage(),
           				"Error en formato de número", JOptionPane.ERROR_MESSAGE);

		}
		
	}//actionPerformed

	/**
     * Metodo que verifica si una cadena es un numero entero
     * @return verdadero o falso
     */
	public static boolean isNumeric(String cadena) {

        boolean resultado;

        try {

            Integer.parseInt(cadena);
            resultado = true;

        } catch (NumberFormatException ex) {

            resultado = false;

        }

        return resultado;
    }//isNumeric

    /**
     * Metodo, que espera a que el campo buscar sea seleccionado y en caso de no tener datos los limpia.
     * @param evento indica cuando el campo buscar es seleccionado
     */
	public void focusGained(FocusEvent evento) {

		JTextField campo = (JTextField) evento.getSource();

		if(campo.getText().equals("Codigo/Nombre")){

	        campo.setForeground(Color.BLACK);
	        campo.setText(null);

    	}
           
    }//focusGained

    /**
     * Metodo, que espera a que el campo buscar sea desseleccionado y en caso de no tener datos coloca un texto.
     * @param evento indica cuando el campo buscar es desseleccionado
     */
    public void focusLost(FocusEvent evento) {

    	JTextField campo = (JTextField) evento.getSource();

    	if(campo.getText().equals("")){

    		igu.setBuscar();

    	}
        
    }//focusLost

}