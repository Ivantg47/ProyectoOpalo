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

	private JTextField camposTexto[] = { 	//Cra un campo de texto por cada dato de los clientes que se necesitan
		new JTextField(),
		new JTextField(),
		new JTextField(),
		new JTextField(),
		new JTextField(),
		new JTextField(),
		new JTextField(),
	};

	private JLabel etiquetas[] = { //añade las etiquetas de los datos de los clientes
		new JLabel("ID Cliente"),
		new JLabel("Nombre"),
		new JLabel("Apellido Paterno"),
		new JLabel("Apellido Materno"),
		new JLabel("Correo"),
		new JLabel("Telefono"),
		new JLabel("Direccion"),
	};


	private JTextField campoBuscar;//campo de texto usado para uscar un cliente

	private DefaultTableModel modelo;//modelo de la tabla e datos
 	private JTable tabla;
	
	private ControlClientes control = new ControlClientes(this);

	public IGUClientes(){ //Constructor sin parametros


	}

	/** Método que crea un panel para los clientes
	*@return panel de clientes
	*/
	public JPanel getIGUClientes(){

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(getPanelBuscar(), BorderLayout.NORTH);
		panel.add(getPanelListaClientes(), BorderLayout.CENTER); //
		panel.add(getPanelDatosCliente(), BorderLayout.WEST);

		return panel;

	}

	/** Método que crea el panel para buscar a los clientes
	*@return panel
	*/
	public JPanel getPanelBuscar(){

		JPanel panel = new JPanel();

		panel.setBorder(BorderFactory.createTitledBorder(""));
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel titulo = new JLabel("Clientes        ");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 36));
		panel.add(titulo);

		JLabel etBuscar = new JLabel("Buscar");
		panel.add(etBuscar);

		campoBuscar = new JTextField();
		campoBuscar.setText("Codigo/Nombre");
		campoBuscar.setForeground(new Color(111,111,111));
		campoBuscar.setPreferredSize(new Dimension(200,25));
		campoBuscar.addFocusListener(control);
		panel.add(campoBuscar);

		JButton btBuscar = new JButton(new ImageIcon(getClass().getResource("/iconos/lupa (2).png")));
		btBuscar.setPreferredSize(new Dimension(25,25));
		btBuscar.addActionListener(control);
		btBuscar.setActionCommand("btBuscar");

		panel.add(btBuscar);

		return panel;

	}

	/** Método usado para mostrar la lista de clientes
	*@return panel panel donde se muestra os datos
	*/
	public JPanel getPanelListaClientes(){

		JPanel panel = new JPanel();
		DAOClientes dao = new DAOClientes();

		panel.setBorder(BorderFactory.createTitledBorder("Lista de Clientes"));
		modelo = new DefaultTableModel();
		modelo.setColumnIdentifiers(new Object[]{"ID", "Nombre", "Apellido Paterno", "Apellido Materno", "Correo", "Telefono", "Direccion"});

		//creacion de la tabla
		tabla = new JTable(modelo);
		JScrollPane jScroll = new JScrollPane(tabla);

		dao.getTabla(modelo); //error

		jScroll.setViewportView(tabla);

		panel.add(jScroll, BorderLayout.CENTER);

		return panel;

	}
	
	/**Método utilizado para mostrar los datos de los clientes
	* @return panelGeneral es el panel principal de la interfaz
	*/
	public JPanel getPanelDatosCliente(){

		JPanel panelGeneral = new JPanel();

		panelGeneral.setLayout(new FlowLayout(FlowLayout.CENTER, 0,10));

		panelGeneral.add(getPanelDatos());
		panelGeneral.add(getPanelBotones());

		panelGeneral.setPreferredSize(new Dimension(230, 670));
		panelGeneral.setBorder(BorderFactory.createTitledBorder("Datos del Cliente"));

		return panelGeneral;

	}

	/**
	* Método utilizado para mostrar un campo donde recibir los datos y el nombre del dato que le corresponde
	*/
	public JPanel getPanelDatos(){

		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(14, 1));

		for (int i = 0; i < camposTexto.length; i++){
			panel.add(etiquetas[i]);
			panel.add(camposTexto[i]);

		}

		camposTexto[0].setEnabled(false);
		panel.setPreferredSize(new Dimension(210, 340));

		return panel;

	}

	/**Método utilizado para mostrar los botones necesarios para las acciones de la interfaz
	* @return panel que muestra los botones 
	*/
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

	/**Método utilizado para recibir los datos del cliente
	* @return oCliente es el objeto cliente 
	*/
	public DTOClientes getDTO(){

		int eId = 0;
		String sNombre = "", sPaterno = "", sMaterno = "", sCorreo = "", sTelefono = "", sDireccion = "";
		DTOClientes oCliente;
		boolean bEsCadena = true;
		boolean bEsNumero = true;
		int eCont = 1;

		while(bEsCadena && eCont < 4){

			if (isNumeric(camposTexto[eCont].getText())){

				bEsCadena = false;

			}

			eCont++;

		}

		if (!isNumeric(camposTexto[5].getText())) {
			
			bEsNumero = false;
			JOptionPane.showMessageDialog(null, "Error. Telefono no puede contener letras.");

		} 

		if (!bEsCadena){

			JOptionPane.showMessageDialog(null, "Error. El nombre y apellidos no pueden contener numeros");

		}

		
		if(bEsCadena && bEsNumero){

			sNombre =  camposTexto[1].getText().toUpperCase();
			sPaterno = camposTexto[2].getText().toUpperCase();
			sMaterno = camposTexto[3].getText().toUpperCase();
			sCorreo = camposTexto[4].getText().toUpperCase();
			sTelefono = camposTexto[5].getText();
			sDireccion = camposTexto[6].getText().toUpperCase();

		} 

		if (camposTexto[0].getText().compareTo("") != 0){

			eId = Integer.valueOf(camposTexto[0].getText());
			oCliente = new DTOClientes(eId, sNombre, sPaterno, sMaterno, sCorreo, sTelefono, sDireccion);

		} else {

			oCliente = new DTOClientes(sNombre, sPaterno, sMaterno, sCorreo, sTelefono, sDireccion);
		}

		return oCliente;	

	}

	/**Método utilizado para recibir el ID del cliente
	* @return eId el id del cliente 
	*/
	public int getId(){

		int eId = 0;

		if (campoBuscar.getText().compareTo("") != 0) {

			eId = Integer.valueOf(campoBuscar.getText());
	
		}
		
		return eId;
	}

	/**Método crear la tabla de datos
	* @return modelo de la tabla
	*/
	public DefaultTableModel getModelo(){

		return modelo;

	}

	/**Método utilizado para limpiar los campos de datos
	* 
	*/
	public void limpiar(){


		for (JTextField campo : camposTexto) {
			
			campo.setText(null);

		}

		for (JTextField campo : camposTexto) {
			
			campo.setText(null);
			
		}

		campoBuscar.setText("Codigo/Nombre");
	}

	/**Método utilizado para comprobar si los campos de datos están vacios
	* @return vacio indica si es campos vacios es verdadero o falso
	*/
	public boolean camposVacios(){

		boolean vacio = false;
		int pos = 1;

		do {

			if (camposTexto[pos].getText().compareTo("") == 0) {
				
				vacio = true;

			}

			pos++;

		} while (!vacio && pos < camposTexto.length);

		if(vacio){

			JOptionPane.showMessageDialog(null, "Error. Llenar campos vacios");

		}

		return vacio;

	}

	/**Método utilizado para mostrar Los datos del cliente
	* 
	*/
	public void mostrarDTO(DTOClientes oClientes){

		if (oClientes.getIdCliente() != 0) {
			camposTexto[0].setText(String.valueOf(oClientes.getIdCliente()));
		}
		camposTexto[1].setText(oClientes.getNombre());
		camposTexto[2].setText(oClientes.getPaterno());
		camposTexto[3].setText(oClientes.getMaterno());
		camposTexto[4].setText(oClientes.getCorreo());
		camposTexto[5].setText(oClientes.getTelefono());
		camposTexto[6].setText(oClientes.getDireccion());

	}

	/**Método utilizado para comprobar si un dato es númerico
	* @return oResultado indica si es verdadero o falso 
	*/
	public static boolean isNumeric(String oCadena) {

        boolean oResultado;

        try {

            Double.parseDouble(oCadena);
            oResultado = true;

        } catch (NumberFormatException oExcepcion) {

            oResultado = false;

        }

        return oResultado;
    }

    
    public String getCampoBuscar(){

    	return campoBuscar.getText();

    }

    public void setBuscar(){

		campoBuscar.setText("Codigo/Nombre");
		campoBuscar.setForeground(new Color(111,111,111));
		
	}
	
}