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

		setJTabbedPane();
		setSize(800, 670);
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	public void setJTabbedPane(){
		
		JTabbedPane tpPanel = new JTabbedPane();

		tpPanel.addTab("", new ImageIcon(getClass().getResource("/iconos/cliente (2).png")), new IGUClientes().getIGUClientes());

		tpPanel.addTab("", new ImageIcon(getClass().getResource("/iconos/ventas.png")), new IGUVentas().getPanelVentas());

		tpPanel.addTab("", new ImageIcon(getClass().getResource("/iconos/bolsa-de-la-compra.png")), new IGUCompras().getPanelCompras());

		tpPanel.addTab("", new ImageIcon(getClass().getResource("/iconos/dulces (5).png")), new IGUProducto().getIGUProducto());

		tpPanel.addTab("", new ImageIcon(getClass().getResource("/iconos/harina (7).png")), new IGUInsumo().getIGUInsumo());
	
		tpPanel.addTab("", new ImageIcon(getClass().getResource("/iconos/reporte (1).png")), new IGUReporte().getIGUReporte());

		add(tpPanel);
	}

	public static void main(String[] args) {
		
		IGUAplicacionMenu oIGUAplicacionMenu = new IGUAplicacionMenu();

	}
}


