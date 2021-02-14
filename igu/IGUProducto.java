/**
 * Clase que implementa una interfaz para el modulo de productos.
 * @author Frida Hernadez
 * @author Ivan Tronco
 * @version 1.2
 */

package ProyectoOpalo.igu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import ProyectoOpalo.control.ControlProducto;
import ProyectoOpalo.dto.DTOProducto;
import ProyectoOpalo.dao.DAOProducto;

public class IGUProducto extends JFrame{

 	private JButton btModificar, btEliminar, btAgregar, btBuscar, btLimpiar;
 	private ControlProducto control = new ControlProducto(this);
 	private DefaultTableModel modelo;
 	private JTextField campoBuscar;
 	private JTable tabla;

	private JTextField camposTexto[] = {
		new JTextField(), //0 -> codigo
		new JTextField(), //1 -> nombre
		new JTextField(), //2 -> descripcion
		new JTextField(), //3 -> precio
	};

	private JTextField camposTextoExistencias[] = {
		new JTextField(), //0 -> actual
		new JTextField(), //1 -> minima
		new JTextField(), //2 -> maxima
	};

	private JLabel etiquetas[] = {
		new JLabel("Codigo"),
		new JLabel("Nombre"),
		new JLabel("Descripci\u00F3n"),
		new JLabel("Precio"),
	};

	private JLabel etiquetasExistencias[] = {
		new JLabel("Actual"),
		new JLabel("M\u00EDnima"),
		new JLabel("M\u00E1xima"),
	};

	public IGUProducto(){

	}

	public JPanel getIGUProducto(){

		JPanel panel = new JPanel();

		panel.setLayout(new BorderLayout());

		panel.add(getPanelBuscar(), BorderLayout.NORTH);
		panel.add(getPanelInventario(), BorderLayout.CENTER);
		panel.add(getPanelDatosProducto(), BorderLayout.WEST);

		return panel;

	}

	public JPanel getPanelBuscar(){

		JPanel panel = new JPanel();

		panel.setBorder(BorderFactory.createTitledBorder(""));
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel titulo = new JLabel("Producto        ");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 36));
		panel.add(titulo);

		JLabel buscar = new JLabel("Buscar");
		panel.add(buscar);

		campoBuscar = new JTextField();
		campoBuscar.setText("Codigo/Nombre");
		campoBuscar.setForeground(new Color(111,111,111));
		campoBuscar.setPreferredSize(new Dimension(200,25));
		campoBuscar.addFocusListener(control);
		panel.add(campoBuscar);

		btBuscar = new JButton(new ImageIcon(getClass().getResource("/iconos/lupa.png")));
		btBuscar.setPreferredSize(new Dimension(32,32));

		btBuscar.addActionListener(control);
		btBuscar.setActionCommand("buscar");

		panel.add(btBuscar);


		return panel;

	}

	public JPanel getPanelInventario(){

		JPanel panel = new JPanel();
		DAOProducto dao = new DAOProducto();

		panel.setBorder(BorderFactory.createTitledBorder("Inventario"));
		modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"Codigo", "Nombre", "Descripcion", "Existencias"});
		//creacion de la tabla
		tabla = new JTable(modelo);
		JScrollPane jScroll = new JScrollPane(tabla);

		dao.getTabla(modelo);

        jScroll.setViewportView(tabla);


		panel.add(jScroll, BorderLayout.CENTER);

		return panel;

	}
	
	public JPanel getPanelDatosProducto(){

		JPanel panelDatosProducto = new JPanel();

		// panelDatosProducto.setLayout(new GridLayout(3, 1, 50, 10));
		panelDatosProducto.setLayout(new FlowLayout(FlowLayout.CENTER, 0,10));
		
		panelDatosProducto.add(getPanelDatos());
		panelDatosProducto.add(getPanelExistencias());
		panelDatosProducto.add(getPanelBotones());
		panelDatosProducto.setPreferredSize(new Dimension(230, 670));

		panelDatosProducto.setBorder(BorderFactory.createTitledBorder("Datos producto"));


		return panelDatosProducto;

	}

	public JPanel getPanelDatos(){

		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(8, 1));

		for (int i = 0; i < camposTexto.length; i++){

			panel.add(etiquetas[i]);
			panel.add(camposTexto[i]);

		}
		camposTexto[0].setEnabled(false);
		panel.setPreferredSize(new Dimension(210, 200));

		return panel;

	}

	public JPanel getPanelExistencias(){

		JPanel panelExistencias = new JPanel();

		panelExistencias.setLayout(new GridLayout(2, 3, 10, 0));

		for (int eContador = 0; eContador < etiquetasExistencias.length; eContador++){

			panelExistencias.add(etiquetasExistencias[eContador]);
			etiquetasExistencias[eContador].setSize(50, 30);
			
		}

		for (int eContador = 0; eContador < camposTextoExistencias.length; eContador++){

			panelExistencias.add(camposTextoExistencias[eContador]);

		}

		panelExistencias.setBorder(BorderFactory.createTitledBorder("Existencias"));
		panelExistencias.setPreferredSize(new Dimension(210, 80));

		return panelExistencias;

	}
	

	public JPanel getPanelBotones(){

		JPanel panelBotones = new JPanel();

		panelBotones.setLayout(new FlowLayout());

		btAgregar = new JButton(new ImageIcon(getClass().getResource("/iconos/agregar.png")));
		btAgregar.setToolTipText("Agregar");
		btAgregar.addActionListener(control);
		btAgregar.setActionCommand("agregar");
		btAgregar.setPreferredSize(new Dimension(48, 48));

		btEliminar = new JButton(new ImageIcon(getClass().getResource("/iconos/eliminar.png")));
		btEliminar.setToolTipText("Eliminar");
		btEliminar.addActionListener(control);
		btEliminar.setActionCommand("eliminar");
		btEliminar.setPreferredSize(new Dimension(48, 48));

		btModificar = new JButton(new ImageIcon(getClass().getResource("/iconos/modificar.png")));
		btModificar.setToolTipText("Modificar");
		btModificar.addActionListener(control);
		btModificar.setActionCommand("modificar");
		btModificar.setPreferredSize(new Dimension(48, 48));

		btLimpiar = new JButton(new ImageIcon(getClass().getResource("/iconos/borrador.png")));
		btLimpiar.setToolTipText("Limpiar");
		btLimpiar.addActionListener(control);
		btLimpiar.setActionCommand("limpiar");
		btLimpiar.setPreferredSize(new Dimension(48, 48));

		panelBotones.add(btAgregar);
		panelBotones.add(btEliminar);
		panelBotones.add(btModificar);
		panelBotones.add(btLimpiar);

		// panelBotones.setPreferredSize(new Dimension(210, 40));

		return panelBotones;

	}

	public void limpiar(){

		for (JTextField campo : camposTextoExistencias) {
			
			campo.setText(null);

		}

		for (JTextField campo : camposTexto) {
			
			campo.setText(null);
			
		}

	}

	public DTOProducto getCampos(){

		DTOProducto producto = new DTOProducto();

		if (!camposTexto[0].getText().equals("")) {

			producto.setCodigo(Integer.valueOf(camposTexto[0].getText()));

		}

		producto.setNombre(camposTexto[1].getText());
		producto.setDescripcion(camposTexto[2].getText());
		producto.setPrecio(Float.valueOf(camposTexto[3].getText()));
		producto.setActual(Integer.valueOf(camposTextoExistencias[0].getText()));
		producto.setMinimo(Integer.valueOf(camposTextoExistencias[1].getText()));
		producto.setMaximo(Integer.valueOf(camposTextoExistencias[2].getText()));
		
		return producto;
	}

	public void setCampos(DTOProducto producto){

		limpiar();

		if (producto.getCodigo() != 0) {
			
			camposTexto[0].setText(String.valueOf(producto.getCodigo()));
			camposTexto[1].setText(producto.getNombre());
			camposTexto[2].setText(producto.getDescripcion());
			camposTexto[3].setText(String.valueOf(producto.getPrecio()));
			camposTextoExistencias[0].setText(String.valueOf(producto.getActual()));
			camposTextoExistencias[1].setText(String.valueOf(producto.getMinimo()));
			camposTextoExistencias[2].setText(String.valueOf(producto.getMaximo()));

		} 

		// System.out.println(producto);
	}

	public String getBuscar(){

		return campoBuscar.getText();
	}

	public void setBuscar(){

		campoBuscar.setText("Codigo/Nombre");
		campoBuscar.setForeground(new Color(111,111,111));
		
	}

	public DefaultTableModel getModelo(){

		return modelo;

	}


	public static void main(String[] args) {

		IGUProducto ventana = new IGUProducto();
		
	}
	
}