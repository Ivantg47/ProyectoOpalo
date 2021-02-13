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

	private DTOProducto producto;
	private DAOProducto dao;
	private IGUProducto igu;

	public ControlProducto(IGUProducto igu){

		this.igu = igu;

	}

	public void actionPerformed(ActionEvent evento){

		JButton fuente = (JButton) evento.getSource();
		dao = new DAOProducto();
		
		switch (fuente.getActionCommand()){

			case "buscar":
				if (isNumeric(igu.getBuscar())) {
					
					int codigo = Integer.parseInt(igu.getBuscar());
					igu.setCampos(dao.getPoducto(codigo));

				} else {
					
					igu.setCampos(dao.getPoducto(igu.getBuscar(), igu.getModelo()));

				}

				// dao.getTabla(igu.getModelo());
				igu.setBuscar();

			break;

			case "modificar":
				dao.actualizarProducto(igu.getCampos());
				dao.getTabla(igu.getModelo());
				igu.limpiar();

			break;

			case "eliminar":
				dao.borrarProducto(igu.getCampos());
				dao.getTabla(igu.getModelo());
				igu.limpiar();

			break;

			case "agregar":
				dao.agregarPoducto(igu.getCampos());
				dao.getTabla(igu.getModelo());
				igu.limpiar();

			break;

			case "siguiente":
				JOptionPane.showMessageDialog(null, "siguiente");
			break;

			case "fin":
				JOptionPane.showMessageDialog(null, "fin");
			break;

			case "anterior":
				JOptionPane.showMessageDialog(null, "anterior");
			break;

			case "inicio":
				JOptionPane.showMessageDialog(null, "inicio");
			break;
			

		} 
	}

	public static boolean isNumeric(String cadena) {

        boolean resultado;

        try {

            Integer.parseInt(cadena);
            resultado = true;

        } catch (NumberFormatException excepcion) {

            resultado = false;

        }

        return resultado;
    }

	public void focusGained(FocusEvent e) {

		JTextField campo = (JTextField) e.getSource();
        campo.setForeground(Color.BLACK);
        campo.setText(null);
           
    }

    public void focusLost(FocusEvent e) {

    	JTextField campo = (JTextField) e.getSource();

    	if(campo.getText().equals("")){

    		igu.setBuscar();

    	}
        
    }

}