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
		panel.add(getPanelListaClientes(), BorderLayout.CENTER);
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

		JTextField campoBuscar = new JTextField();
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

		dao.getTabla(modelo);

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
		btAgregar.setToolTipText("Agregar");
		btAgregar.addActionListener(control);
		btAgregar.setActionCommand("btAgregar");

		JButton btEliminar = new JButton(new ImageIcon(getClass().getResource("/iconos/eliminar.png")));
		btEliminar.setToolTipText("Eliminar");
		btAgregar.addActionListener(control);
		btAgregar.setActionCommand("btEliminar");

		JButton btModificar = new JButton(new ImageIcon(getClass().getResource("/iconos/modificar.png")));
		btModificar.setToolTipText("Modificar");
		btAgregar.addActionListener(control);
		btAgregar.setActionCommand("btModificar");

		panelBotones.add(btAgregar);
		panelBotones.add(btEliminar);
		panelBotones.add(btModificar);

		
		return panelBotones;
	}

	public DTOClientes getDTO(){

		DTOClientes oCliente = new DTOClientes();

		if(!camposTexto[0].getText().equals("")){

			//oCliente.setId(Integer.valueOf(camposTexto[0].getText()));

		}

		oCliente.setNombre(camposTexto[1].getText());
		oCliente.setPaterno(camposTexto[2].getText());
		oCliente.setMaterno(camposTexto[3].getText());
		oCliente.setCorreo(camposTexto[4].getText());
		oCliente.setTelefono(camposTexto[5].getText());
		oCliente.setDireccion(camposTexto[6].getText());
		
		return oCliente;	

	}

	public int getId(){

		int eId = 0;

		if (campoBuscar.getText().compareTo("") != 0) {

			eId = Integer.valueOf(campoBuscar.getText());
			
		}

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

		/*camposTexto[0].setText(String.valueOf(oClientes.getId()));
		camposTexto[1].setText(oClientes.getNombre());
		camposTexto[2].setText(oClientes.getPaterno());
		camposTexto[3].setText(oClientes.getMaterno());
		camposTexto[4].setText(oClientes.getCorreo());
		camposTexto[5].setText(oClientes.getTelefono());
		camposTexto[6].setText(oClientes.getDireccion());
*/
	}

	
	
}