/**
 * Clase que implementa una interfaz para el modulo de compras.
 * @author Ivan Tronco
 * @version 1.0
 */

package ProyectoOpalo.igu;

import javax.swing.*;
import java.awt.*;
import ProyectoOpalo.control.*;

public class IGUAplicacionMenu extends JFrame{

	private ControlProducto producto;

	public IGUAplicacionMenu(){

		super("Pastelería Fun Cake");

		//add(getBarraHtas(), BorderLayout.NORTH);

	//	setJMenuBar(getBarraMenu());
		setJTabbedPane();
		setSize(750, 670);
		setLocation(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	public void setJTabbedPane(){

		JTabbedPane tpPanel = new JTabbedPane();
		IGUInsumo iguInsumo = new IGUInsumo();
		IGUProducto iguProducto = new IGUProducto();
		IGUCompras iguCompras = new IGUCompras();
		IGUClientes iguClientes = new IGUClientes();
		IGUVentas iguVentas = new IGUVentas();
		IGUReporte iguReporte = new IGUReporte();
		producto = new ControlProducto(iguProducto);

		//JPanel pPanel1 = new JPanel();
		//pPanel1.add(new JLabel("Cliente"));
		tpPanel.addTab("", new ImageIcon(getClass().getResource("/iconos/cliente (2).png")), iguClientes.getIGUClientes());

		
		// JPanel pPanel2 = new JPanel();
		// pPanel2.add(new JLabel("Ventas"));
		tpPanel.addTab("", new ImageIcon(getClass().getResource("/iconos/ventas.png")), iguVentas.getPanelDatos());

		//JPanel pPanel3 = new JPanel();
		//pPanel3.add(new JLabel("Compras"));
		tpPanel.addTab("", new ImageIcon(getClass().getResource("/iconos/bolsa-de-la-compra.png")), iguCompras.getIGUCompras());
		
		//JPanel pPanel4 = new JPanel();
		//pPanel4.add(new JLabel("Productos"));
		tpPanel.addTab("", new ImageIcon(getClass().getResource("/iconos/dulces (5).png")), iguProducto.getIGUProducto());

		//JPanel pPanel5 = new JPanel();
		//pPanel5.add(new JButton("Salir"));
		tpPanel.addTab("", new ImageIcon(getClass().getResource("/iconos/harina (7).png")), iguInsumo.getIGUInsumo());
		/*
		JPanel pPanel6 = new JPanel();
		pPanel6.add(new JLabel("Inventario"));
		tpPanel.addTab("", new ImageIcon(ICONOS + "inventario (5).png"), pPanel6);
*/
		//JPanel pPanel7 = new JPanel();
		//pPanel7.add(new JLabel("Reportes"));
		tpPanel.addTab("", new ImageIcon(getClass().getResource("/iconos/reporte (1).png")), iguReporte.getIGUReporte());

		add(tpPanel);
	}

	public static void main(String[] args) {
		
		IGUAplicacionMenu oIGUAplicacionMenu = new IGUAplicacionMenu();

	}
}