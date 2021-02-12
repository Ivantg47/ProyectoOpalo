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

public class ControlProducto implements ActionListener{

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
				int buscar = Integer.parseInt(igu.getBuscar());
				igu.setCampos(dao.getPoducto(buscar));
				igu.setBuscar();
				// JOptionPane.showMessageDialog(null, "Busca");

			break;

			case "modificar":
				dao.actualizarProducto(igu.getCampos());
				igu.limpiar();
				// JOptionPane.showMessageDialog(null, "modificar");
			break;

			case "eliminar":
				dao.borrarProducto(igu.getCampos());
				JOptionPane.showMessageDialog(null, "eliminar");
			break;

			case "agregar":
				
				// producto = igu.getCampos();
				dao.agregarPoducto(igu.getCampos());
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

	// public void getCampos(DTOProducto producto){

	// 	igu.camposTexto[1];
	// 	igu.camposTexto[1];
	// 	igu.camposTexto[1];
	// }

	public class EventoRaton extends MouseAdapter{

		public void mousePressed(MouseEvent evento){
			
			JTextField btboton = (JTextField) evento.getSource();

			if (btboton.getText().equals("Codigo/Nombre")) {
								
				btboton.setText(null);
				igu.limpiar();
			}
		}

		public void mouseReleased(MouseEvent evento){

			// JTextField btboton = (JTextField) evento.getSource();
			
			// if (btboton.getText() != "+" && btboton.getText() != "-" && btboton.getText() != "*" && btboton.getText() != "/") {
			// 	System.out.println("paso2");
			// 	btboton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

			// }
				
		}
	}
}