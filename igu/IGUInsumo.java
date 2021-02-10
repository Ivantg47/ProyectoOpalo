/**
 * Clase que implementa una interfaz para el modulo de insumos.
 * @author Ivan Tronco
 * @author Frida Hernandez
 * @version 1.0
 */

package ProyectoOpalo.igu;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class IGUInsumo extends JFrame{

	public static final String ICONOS = "/iconos/"; //ruta para la carpeta de imagenes
	private JComboBox<String> combo1;

	private JTextField camposTexto[] = {
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
		new JLabel("Unidad medida"),
	};

	private JLabel etiquetasExistencias[] = {
		new JLabel("Actual"),
		new JLabel("M\u00EDnima"),
		new JLabel("M\u00E1xima"),
	};

	public IGUInsumo(){
/*
		super("Insumo");

		add(getIGUInsumo());

		setSize(700, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
*/
	}

	public JPanel getIGUInsumo(){

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(getPanel1(), BorderLayout.NORTH);
		panel.add(getPanel2(), BorderLayout.CENTER);
		panel.add(getPanelLayoutGeneral(), BorderLayout.WEST);

		return panel;

	}

	public JPanel getPanel1(){

		JPanel panel = new JPanel();

		panel.setBorder(BorderFactory.createTitledBorder(""));
		panel.setLayout(new FlowLayout());

		JLabel titulo = new JLabel("Insumos");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 36));
		panel.add(titulo);

		JLabel buscar = new JLabel("Buscar");
		panel.add(buscar);

		JTextField campoBuscar = new JTextField();
		campoBuscar.setText("Codigo/Nombre");
		campoBuscar.setPreferredSize(new Dimension(200,25));
		panel.add(campoBuscar);

		JButton btBuscar = new JButton(new ImageIcon(getClass().getResource("/iconos/lupa.png")));
		btBuscar.setPreferredSize(new Dimension(32,32));
		panel.add(btBuscar);


		return panel;

	}

	public JPanel getPanel2(){

		JPanel panel = new JPanel();

		panel.setBorder(BorderFactory.createTitledBorder("Inventario"));
		//panel.setLayout(new GridLayout(1,4));

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

		JButton btFlechaDer = new JButton(new ImageIcon(getClass().getResource("/iconos/flechaDer.png")));
		btFlechaDer.setToolTipText("Siguiente");		
		JButton btFlechaDobleDer = new JButton(new ImageIcon(getClass().getResource("/iconos/flechaDobleDer.png")));
		btFlechaDobleDer.setToolTipText("Fin");		
		JButton btFlechaDobleIzq = new JButton(new ImageIcon(getClass().getResource("/iconos/flechaDobleIzq.png")));
		btFlechaDobleIzq.setToolTipText("Inicio");
		JButton btFlechaIzq = new JButton(new ImageIcon(getClass().getResource("/iconos/flechaIzq.png")));
		btFlechaIzq.setToolTipText("Anterior");
		

		panel.add(btFlechaDobleIzq, BorderLayout.SOUTH);
		panel.add(btFlechaIzq, BorderLayout.SOUTH);
		panel.add(btFlechaDer, BorderLayout.SOUTH);
		panel.add(btFlechaDobleDer, BorderLayout.SOUTH);


		return panel;

	}
	
	public JPanel getPanelLayoutGeneral(){

		JPanel panelLayoutGeneral = new JPanel();

		panelLayoutGeneral.setLayout(new GridLayout(3, 1, 50, 10));

		panelLayoutGeneral.add(getPanelLayout());
		panelLayoutGeneral.add(getPanelExistencias());
		panelLayoutGeneral.add(getPanelBotones());
		

		panelLayoutGeneral.setBorder(BorderFactory.createTitledBorder("Datos insumo"));


		return panelLayoutGeneral;

	}

	public JPanel getPanelLayout(){

		JPanel panel = new JPanel();

		combo1 = new JComboBox<String>();

		panel.setLayout(new GridLayout(6, 1));

		for (int i = 0; i < camposTexto.length; i++){
			panel.add(etiquetas[i]);
			panel.add(camposTexto[i]);
		}

		panel.add(etiquetas[etiquetas.length - 1]);
		panel.add(combo1);

		combo1.addItem("Miligramo");
        combo1.addItem("Gramo");
        combo1.addItem("Kilogramo");
        combo1.addItem("Mililitro");
        combo1.addItem("Litro");
        combo1.addItem("Onza");
        combo1.addItem("Galon");
        combo1.addItem("Pieza");

		return panel;

	}

	public JPanel getPanelExistencias(){

		JPanel panelExistencias = new JPanel();

		panelExistencias.setLayout(new GridLayout(2, 3, 10, 0));

		for (int eContador = 0; eContador < etiquetasExistencias.length; eContador++){

			panelExistencias.add(etiquetasExistencias[eContador]);
			
		}

		for (int eContador = 0; eContador < camposTextoExistencias.length; eContador++){

			panelExistencias.add(camposTextoExistencias[eContador]);

		}

		panelExistencias.setBorder(BorderFactory.createTitledBorder("Existencias"));

		return panelExistencias;

	}
	

	public JPanel getPanelBotones(){

		JPanel panelBotones = new JPanel();

		panelBotones.setLayout(new FlowLayout());

		JButton btAgregar = new JButton(new ImageIcon(getClass().getResource("/iconos/agregar.png")));
		btAgregar.setToolTipText("Agregar");
	//	btAgregar.setPreferredSize(new Dimension(32,32));

		JButton btEliminar = new JButton(new ImageIcon(getClass().getResource("/iconos/eliminar.png")));
		btEliminar.setToolTipText("Eliminar");

		JButton btModificar = new JButton(new ImageIcon(getClass().getResource("/iconos/modificar.png")));
		btModificar.setToolTipText("Modificar");

		panelBotones.add(btAgregar);
		panelBotones.add(btEliminar);
		panelBotones.add(btModificar);

		return panelBotones;
	}
/*
	public static void main(String[] args) {
		IGUInsumo ventana = new IGUInsumo();
		
	}
*/	
}