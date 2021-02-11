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

public class IGUProducto extends JFrame{

 	private JButton btModificar, btEliminar, btAgregar, btFlechaDer, btFlechaDobleDer, btFlechaDobleIzq, btFlechaIzq, btBuscar;
 	private ControlProducto control = new ControlProducto(this);
 	private ControlProducto.EventoRaton raton = control.new EventoRaton();

	private JTextField camposTexto[] = {
		new JTextField(),
		new JTextField(),
		new JTextField(),
		new JTextField(),
	};

	private JTextField camposTextoExistencias[] = {
		new JTextField(),
		new JTextField(),
		new JTextField(),
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

		JLabel titulo = new JLabel("Producto      ");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 36));
		panel.add(titulo);

		JLabel buscar = new JLabel("Buscar");
		panel.add(buscar);

		JTextField campoBuscar = new JTextField();
		campoBuscar.setText("Codigo/Nombre");
		campoBuscar.setPreferredSize(new Dimension(200,25));
		campoBuscar.addMouseListener(raton);
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

		panel.setBorder(BorderFactory.createTitledBorder("Inventario"));

		//creacion de la tabla
		JTable tabla = new JTable();
		JScrollPane jScroll = new JScrollPane(tabla);

		String [] nombre = {
                "Codigo", "Nombre", "Cantidad"
            };

		tabla.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            }, nombre
            
        ));

        jScroll.setViewportView(tabla);

		panel.add(jScroll, BorderLayout.CENTER);

		btFlechaDer = new JButton(new ImageIcon(getClass().getResource("/iconos/flechaDer.png")));
		btFlechaDer.setToolTipText("Siguiente");
		btFlechaDer.addActionListener(control);
		btFlechaDer.setActionCommand("siguiente");

		btFlechaDobleDer = new JButton(new ImageIcon(getClass().getResource("/iconos/flechaDobleDer.png")));
		btFlechaDobleDer.setToolTipText("Fin");	
		btFlechaDobleDer.addActionListener(control);
		btFlechaDobleDer.setActionCommand("fin");

		btFlechaDobleIzq = new JButton(new ImageIcon(getClass().getResource("/iconos/flechaDobleIzq.png")));
		btFlechaDobleIzq.setToolTipText("Inicio");
		btFlechaDobleIzq.addActionListener(control);
		btFlechaDobleIzq.setActionCommand("inicio");

		btFlechaIzq = new JButton(new ImageIcon(getClass().getResource("/iconos/flechaIzq.png")));
		btFlechaIzq.setToolTipText("anterior");
		btFlechaIzq.addActionListener(control);
		btFlechaIzq.setActionCommand("anterior");
		

		panel.add(btFlechaDobleIzq, BorderLayout.SOUTH);
		panel.add(btFlechaIzq, BorderLayout.SOUTH);
		panel.add(btFlechaDer, BorderLayout.SOUTH);
		panel.add(btFlechaDobleDer, BorderLayout.SOUTH);


		return panel;

	}
	
	public JPanel getPanelDatosProducto(){

		JPanel panelDatosProducto = new JPanel();

		// panelDatosProducto.setLayout(new GridLayout(3, 1, 50, 10));
		panelDatosProducto.setLayout(new FlowLayout(FlowLayout.CENTER, 0,10));
		System.out.println("flow");
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

		btEliminar = new JButton(new ImageIcon(getClass().getResource("/iconos/eliminar.png")));
		btEliminar.setToolTipText("Eliminar");
		btEliminar.addActionListener(control);
		btEliminar.setActionCommand("eliminar");

		btModificar = new JButton(new ImageIcon(getClass().getResource("/iconos/modificar.png")));
		btModificar.setToolTipText("Modificar");
		btModificar.addActionListener(control);
		btModificar.setActionCommand("modificar");

		panelBotones.add(btAgregar);
		panelBotones.add(btEliminar);
		panelBotones.add(btModificar);

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

	public static void main(String[] args) {

		IGUProducto ventana = new IGUProducto();
		
	}
	
}