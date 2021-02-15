/**
 * Clase que implementa una interfaz para el modulo de clientes.
 * @author Rojas Fajardo Ximena
 * @version 1.2
 */

package ProyectoOpalo.igu;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import ProyectoOpalo.dto.DTOClientes;
import ProyectoOpalo.dao.DAOClientes;
import ProyectoOpalo.control.ControlClientes;

public class IGUClientes extends JFrame{

	public static final String ICONOS = "/iconos/"; 
	

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


	private JTextField campoBuscar;

	private DefaultTableModel modelo;
 	private JTable tabla;
	
	private ControlClientes control = new ControlClientes(this);

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
		panel.add(getPanelBuscar(), BorderLayout.NORTH);
		panel.add(getPanelListaClientes(), BorderLayout.CENTER); //
		panel.add(getPanelGeneral(), BorderLayout.WEST);

		return panel;

	}

	public JPanel getPanelBuscar(){

		JPanel panel = new JPanel();

		panel.setBorder(BorderFactory.createTitledBorder(""));
		panel.setLayout(new FlowLayout());

		JLabel titulo = new JLabel("Clientes");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 36));
		panel.add(titulo);

		JLabel etBuscar = new JLabel("Buscar");
		panel.add(etBuscar);

		campoBuscar = new JTextField();
		campoBuscar.setText("");
		campoBuscar.setPreferredSize(new Dimension(200,25));
		//campoBuscar.addFocusListener(control);
		panel.add(campoBuscar);

		JButton btBuscar = new JButton(new ImageIcon(getClass().getResource("/iconos/lupa.png")));
		btBuscar.setPreferredSize(new Dimension(32,32));
		panel.add(btBuscar);
		btBuscar.addActionListener(control);
		btBuscar.setActionCommand("btBuscar");


		return panel;

	}

	public JPanel getPanelListaClientes(){

		JPanel panel = new JPanel();
		DAOClientes dao = new DAOClientes();

		panel.setBorder(BorderFactory.createTitledBorder("Lista de Clientes"));
		modelo = new DefaultTableModel();
		modelo.setColumnIdentifiers(new Object[]{"ID", "Nombre", "Apellido Paterno", "Apellido Materno", "Correo", "Telefono", "Direccion"});

		//creacion de la tabla
		JTable tabla = new JTable(modelo);
		JScrollPane jScroll = new JScrollPane(tabla);

		dao.getTabla(modelo); //error

		jScroll.setViewportView(tabla);

		panel.add(jScroll, BorderLayout.CENTER);

		return panel;

	}
	
	public JPanel getPanelGeneral(){

		JPanel panelGeneral = new JPanel();

		panelGeneral.setLayout(new GridLayout(2, 1));

		panelGeneral.add(getPanelLayout());
		panelGeneral.add(getPanelBotones());
		

		panelGeneral.setBorder(BorderFactory.createTitledBorder("Datos del Cliente"));


		return panelGeneral;

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

		JButton btAgregar = new JButton(new ImageIcon(getClass().getResource("/iconos/agregar.png")));
		btAgregar.addActionListener(control);
		btAgregar.setActionCommand("btAgregar");
		btAgregar.setToolTipText("Agregar");
		btAgregar.setPreferredSize(new Dimension(48, 48));	

		JButton btEliminar = new JButton(new ImageIcon(getClass().getResource("/iconos/eliminar.png")));
		btEliminar.addActionListener(control);
		btEliminar.setActionCommand("btEliminar");
		btEliminar.setToolTipText("Eliminar");
		btEliminar.setPreferredSize(new Dimension(48, 48));

		JButton btModificar = new JButton(new ImageIcon(getClass().getResource("/iconos/modificar.png")));
		btModificar.addActionListener(control);
		btModificar.setActionCommand("btModificar");
		btModificar.setToolTipText("Modificar");
		btModificar.setPreferredSize(new Dimension(48, 48));

		JButton btLimpiar = new JButton(new ImageIcon(getClass().getResource("/iconos/borrador.png")));
		btLimpiar.setToolTipText("Limpiar");
		btLimpiar.addActionListener(control);
		btLimpiar.setActionCommand("btLimpiar");
		btLimpiar.setPreferredSize(new Dimension(48, 48));

		panelBotones.add(btAgregar);
		panelBotones.add(btEliminar);
		panelBotones.add(btModificar);
		panelBotones.add(btLimpiar);
		
		return panelBotones;
	}

	public DTOClientes getDTO(){

		String sNombre = "", sPaterno = "", sMaterno = "", sCorreo = "", sTelefono = "", sDireccion = "";

		DTOClientes oCliente;

		sNombre =  camposTexto[1].getText();
		sPaterno = camposTexto[2].getText();
		sMaterno = camposTexto[3].getText();
		sCorreo = camposTexto[4].getText();
		sTelefono = camposTexto[5].getText();
		sDireccion = camposTexto[6].getText();



		oCliente = new DTOClientes(sNombre, sPaterno, sMaterno, sCorreo, sTelefono, sDireccion);


		return oCliente;	

	}

	public int getId(){

		int eId = 0;
	//	if (campoBuscar.getText().compareTo("") != 0) {


			System.out.println("Paso 3");

			System.out.println("Paso 3.1: " + campoBuscar.getText());


			

	//	}
			


			eId = Integer.valueOf(campoBuscar.getText());
			

			System.out.println("Paso 4: " + eId);
		return eId;
	}

	public DefaultTableModel getModelo(){

		return modelo;

	}

	public void limpiar(){


		for (JTextField campo : camposTexto) {
			
			campo.setText(null);

		}

		for (JTextField campo : camposTexto) {
			
			campo.setText(null);
			
		}
	}

	public boolean camposVacios(){

		boolean vacio = false;
		int pos = 1;

		do {

			if (camposTexto[pos].getText().compareTo("") == 0) {
				
				vacio = true;

			}

			pos++;

		} while (!vacio || pos < camposTexto.length);

		return vacio;

	}


	public void mostrarDTO(DTOClientes oClientes){

		camposTexto[1].setText(oClientes.getNombre());
		camposTexto[2].setText(oClientes.getPaterno());
		camposTexto[3].setText(oClientes.getMaterno());
		camposTexto[4].setText(oClientes.getCorreo());
		camposTexto[5].setText(oClientes.getTelefono());
		camposTexto[6].setText(oClientes.getDireccion());

	}

	
	
}