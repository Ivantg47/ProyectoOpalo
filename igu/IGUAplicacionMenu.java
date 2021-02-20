/**
 * Clase que implementa una interfaz para el modulo de compras.
 * @author Ivan Tronco
 * @version 1.5
 */

package ProyectoOpalo.igu;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import ProyectoOpalo.dao.*;

public class IGUAplicacionMenu extends JFrame implements ChangeListener{

	/**
 	 * Atributo que almacena las pestñas de la interfaz.
	 */	
	private JTabbedPane tpPanel;
	/**
 	 * Atributo que almacena la igu producto.
	 */	
	private IGUProducto producto;
	/**
 	 * Atributo que almacena la igu cliente.
	 */	
	private IGUClientes cliente;
	/**
 	 * Atributo que almacena la igu insumo.
	 */	
	private IGUInsumo insumo;
	/**
 	 * Atributo que almacena la igu ventas.
	 */	
	private IGUVentas venta;
	/**
 	 * Atributo que almacena la igu compras.
	 */	
	private IGUCompras compra;
	/**
 	 * Atributo que almacena la igu de reportes.
	 */	
	private IGUReporte reporte;

	/**
     * Constructor sin parametros para la clase principal.
     */
	public IGUAplicacionMenu(){

		super("Pasteler\u00EDa Fun Cake");

		setJTabbedPane();
		setSize(800, 670);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	/**
     * Metodo que añade las taps de cada modulo a la interfaz principal
     */
	public void setJTabbedPane(){
		
		tpPanel = new JTabbedPane();
		tpPanel.addChangeListener(this);
		cliente = new IGUClientes();
		producto = new IGUProducto();
		insumo = new IGUInsumo();
		venta = new IGUVentas();
		compra = new IGUCompras();
		reporte = new IGUReporte();

		tpPanel.addTab("", new ImageIcon(getClass().getResource("/iconos/cliente (2).png")), cliente.getIGUClientes());

		tpPanel.addTab("", new ImageIcon(getClass().getResource("/iconos/ventas.png")), venta.getPanelVentas());

		tpPanel.addTab("", new ImageIcon(getClass().getResource("/iconos/bolsa-de-la-compra.png")), compra.getPanelCompras());

		tpPanel.addTab("", new ImageIcon(getClass().getResource("/iconos/dulces (5).png")), producto.getIGUProducto());

		tpPanel.addTab("", new ImageIcon(getClass().getResource("/iconos/harina (7).png")), insumo.getIGUInsumo());
	
		tpPanel.addTab("", new ImageIcon(getClass().getResource("/iconos/reporte (1).png")), reporte.getIGUReporte());

		add(tpPanel);
	}

	/**
     * Metodo que limpia y actualiza la pestaña seleccionada
     */
	public void stateChanged(ChangeEvent event) {
 
	    switch (tpPanel.getSelectedIndex()){

	    	case 0:// cliente
	    		new DAOClientes().getTabla(cliente.getModelo());
	    		cliente.limpiar();
	    	break;
	    	case 1:// venta
	    		venta.nuevaVenta();
	    	break;
	    	case 2: //compra
	    		compra.nuevaVenta();
	    	break;
	    	case 3: //producto
	    		new DAOProducto().getTabla(producto.getModelo());
	    		producto.limpiar();
	    	break;
	    	case 4: //insumo
	    		new DAOInsumo().getTabla(insumo.getModelo());
	    		insumo.limpiarCamposTexto();
	    	break;
	    	case 5: //reporte
	    		reporte.limpiarReporte();
	    	break;
	    }
 
	}
	
	/**
     * Metodo principal
     */
	public static void main(String[] args) {
		
		IGUAplicacionMenu oIGUAplicacionMenu = new IGUAplicacionMenu();

	}
}


