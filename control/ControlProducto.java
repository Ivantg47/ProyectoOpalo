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

public class ControlProducto{

	private DTOProducto producto;
	private DAOProducto dao;
	private IGUProducto igu;

	public ControlProducto(IGUProducto igu){

		this.igu = igu;

	}

	public void btBuscarActionPerformed(ActionEvent evt){

		JOptionPane.showMessageDialog(null, "hola");

	}
}