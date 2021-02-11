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

		
		switch (fuente.getActionCommand()){
			case "buscar":
				JOptionPane.showMessageDialog(null, "Busca");
			break;
			case "modificar":
				JOptionPane.showMessageDialog(null, "modificar");
			break;
			case "eliminar":
				JOptionPane.showMessageDialog(null, "eliminar");
			break;
			case "agregar":
				JOptionPane.showMessageDialog(null, "agregar");
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

	public class EventoRaton extends MouseAdapter{

		public void mousePressed(MouseEvent evento){
			
			JTextField btboton = (JTextField) evento.getSource();

			if (btboton.getText().equals("Codigo/Nombre")) {
								
				btboton.setText(null);

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