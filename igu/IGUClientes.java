/**
 * Clase que implementa una interfaz para el modulo de clientes.
 * @author Rojas Fajardo Ximena
 * @version 1.0
 */

package ProyectoOpalo.igu;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class IGUClientes extends JFrame{

	public static final String ICONOS = "/iconos/"; //ruta para la carpeta de imagenes
	
	private JComboBox<String> combo1;

	private JTextField camposTexto[] = {
		new JTextField(),
		new JTextField(),
		new JTextField(),
		new JTextField(),
		new JTextField(),
		new JTextField(),
		new JTextField(),
	};

	private JLabel etiquetas[] = {
		new JLabel("ID Cliente"),
		new JLabel("Nombre"),
		new JLabel("Apellido Paterno"),
		new JLabel("Apellido Materno"),
		new JLabel("Correo"),
		new JLabel("Telefono"),
		new JLabel("Direccion"),
	};

	public IGUClientes(){
/*
		super("Clientes");

		add(getIGUClientes());

		setSize(700, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
*/
	}

	public JPanel getIGUClientes(){

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

		JLabel titulo = new JLabel("Clientes");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 36));
		panel.add(titulo);

		JLabel buscar = new JLabel("Buscar");
		panel.add(buscar);

		JTextField campoBuscar = new JTextField();
		campoBuscar.setText("ID del Cliente");
		campoBuscar.setPreferredSize(new Dimension(200,25));
		panel.add(campoBuscar);

		JButton btBuscar = new JButton(new ImageIcon(getClass().getResource("/iconos/lupa.png")));
		btBuscar.setPreferredSize(new Dimension(32,32));
		panel.add(btBuscar);


		return panel;

	}

	public JPanel getPanel2(){

		JPanel panel = new JPanel();

		panel.setBorder(BorderFactory.createTitledBorder("Lista de Clientes"));

		//creacion de la tabla
		JTable tabla = new JTable();
		JScrollPane jScroll = new JScrollPane(tabla);

		String [] nombre = {
                "ID Cliente", "Nombre", "Apellido Paterno", "Apellido Materno"
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

		JButton btFlechaDer = new JButton(new ImageIcon(ICONOS + "flechaDer.png"));
		btFlechaDer.setToolTipText("Siguiente");		
		JButton btFlechaDobleDer = new JButton(new ImageIcon(ICONOS + "flechaDobleDer.png"));
		btFlechaDobleDer.setToolTipText("Fin");		
		JButton btFlechaDobleIzq = new JButton(new ImageIcon(ICONOS + "flechaDobleIzq.png"));
		btFlechaDobleIzq.setToolTipText("Inicio");
		JButton btFlechaIzq = new JButton(new ImageIcon(ICONOS + "flechaIzq.png"));
		btFlechaIzq.setToolTipText("Anterior");
		

		panel.add(btFlechaDobleIzq, BorderLayout.SOUTH);
		panel.add(btFlechaIzq, BorderLayout.SOUTH);
		panel.add(btFlechaDer, BorderLayout.SOUTH);
		panel.add(btFlechaDobleDer, BorderLayout.SOUTH);


		return panel;

	}
	
	public JPanel getPanelLayoutGeneral(){

		JPanel panelLayoutGeneral = new JPanel();

		panelLayoutGeneral.setLayout(new GridLayout(2, 1));

		panelLayoutGeneral.add(getPanelLayout());
		panelLayoutGeneral.add(getPanelBotones());
		

		panelLayoutGeneral.setBorder(BorderFactory.createTitledBorder("Datos del Cliente"));


		return panelLayoutGeneral;

	}

	public JPanel getPanelLayout(){

		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(14, 1));

		for (int i = 0; i < camposTexto.length; i++){
			panel.add(etiquetas[i]);
			panel.add(camposTexto[i]);

		}

		return panel;

	}

	public JPanel getPanelBotones(){

		JPanel panelBotones = new JPanel();

		panelBotones.setLayout(new FlowLayout());

		JButton btAgregar = new JButton(new ImageIcon(ICONOS + "agregar.png"));
		btAgregar.setToolTipText("Agregar");

		JButton btEliminar = new JButton(new ImageIcon(ICONOS + "eliminar.png"));
		btEliminar.setToolTipText("Eliminar");

		JButton btModificar = new JButton(new ImageIcon(ICONOS + "modificar.png"));
		btModificar.setToolTipText("Modificar");

		panelBotones.add(btAgregar);
		panelBotones.add(btEliminar);
		panelBotones.add(btModificar);

		return panelBotones;
	}

	public static void main(String[] args) {
		IGUClientes ventana = new IGUClientes();
		
	}
	
}