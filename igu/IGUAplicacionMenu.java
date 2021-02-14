/**
 * Clase que implementa una interfaz para el modulo de compras.
 * @author Ivan Tronco
 * @version 1.0
 */

package ProyectoOpalo.igu;

import javax.swing.*;
import java.awt.*;
// import ProyectoOpalo.control.*;

public class IGUAplicacionMenu extends JFrame{

	// private ControlProducto producto;

	public IGUAplicacionMenu(){

		super("Pasteler\u00EDa Fun Cake");

		//add(getBarraHtas(), BorderLayout.NORTH);

	//	setJMenuBar(getBarraMenu());
		setJTabbedPane();
		setSize(750, 670);
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	public void setJTabbedPane(){

		JTabbedPane tpPanel = new JTabbedPane();
		IGUInsumo iguInsumo = new IGUInsumo();
		IGUProducto iguProducto = new IGUProducto();
		IGUCompras iguCompras = new IGUCompras();
		// IGUClientes iguClientes = new IGUClientes();
		IGUVentas iguVentas = new IGUVentas();
		IGUReporte iguReporte = new IGUReporte();
//		producto = new ControlProducto(iguProducto);

		tpPanel.addTab("", new ImageIcon(getClass().getResource("/iconos/cliente (2).png")), new IGUClientes().getIGUClientes());

		tpPanel.addTab("", new ImageIcon(getClass().getResource("/iconos/ventas.png")), iguVentas.getPanelDatos());

		tpPanel.addTab("", new ImageIcon(getClass().getResource("/iconos/bolsa-de-la-compra.png")), iguCompras.getPanelDatos());

		tpPanel.addTab("", new ImageIcon(getClass().getResource("/iconos/dulces (5).png")), iguProducto.getIGUProducto());

		tpPanel.addTab("", new ImageIcon(getClass().getResource("/iconos/harina (7).png")), iguInsumo.getIGUInsumo());
		/*
		tpPanel.addTab("", new ImageIcon(ICONOS + "inventario (5).png"), pPanel6);
*/
		tpPanel.addTab("", new ImageIcon(getClass().getResource("/iconos/reporte (1).png")), iguReporte.getIGUReporte());

		add(tpPanel);
	}

	public static void main(String[] args) {
		
		IGUAplicacionMenu oIGUAplicacionMenu = new IGUAplicacionMenu();

	}
}


