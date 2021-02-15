/**
 * Clase que implementa una interfaz para el modulo de compras.
 * @author Diego Puebla Aldama
 * @version 2.0
 */

package ProyectoOpalo.igu;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import ProyectoOpalo.control.ControlCompra;
import ProyectoOpalo.dto.DTOCompra;
import ProyectoOpalo.dao.DAOCompra;

public class IGUCompras extends JFrame{

	private DefaultTableModel modelo, modeloDTO;
	private JTable tabla;

    private ControlCompra control = new ControlCompra(this);
	private DTOCompra[] comprasTabla; //areglo de las compras que se van agregando.
	public static int indiceTabla = 0; //indice del arreglo que incrementra según se agregan objetos osea filas.
	private DAOCompra oDao = new DAOCompra();

	private JLabel dia = new JLabel("D\u00EDa: ");
	private JTextField texDia = new JTextField();

	private JLabel mes = new JLabel ("Mes: ");
	private JTextField texMes = new JTextField();

	private JLabel anio = new JLabel("A\u00F1o: ");
	private JTextField texAnio = new JTextField();

	private JLabel aDatosProducto[] = {

		new JLabel("Insumo: "),
		new JLabel("Cantidad: "),
		new JLabel("Costo: "),
        new JLabel("Fecha de Compra")

	};

	private JTextField aTextoProducto[] = {

		new JTextField(), //Nombre del insumo
		new JTextField(), // Cantidad
		new JTextField(), // Costo
        new JTextField() //fechaCompra

	};

    //botones de pre registro
	private JButton btAceptarP = new JButton("Aceptar"), btCancelarP = new JButton("Cancelar");

	private JButton btAgregar = new JButton("Registrar Compras"), btCancelar = new JButton("Cancelar Registro");

	public IGUCompras(){
	/*
		super("Registro de Compra");

		add(getPanelDatos());
		setLocation(300,30);
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	*/
	}
	//public static JPanel panelDatos = new JPanel();
	public JPanel getPanelDatos(){

		JPanel panelDatos = new JPanel();

		panelDatos.setLayout(new GridLayout(5,1,10,10));
		//oDao.creaPre();
		//panelDatos.add(getFecha());
		panelDatos.add(getDatosProducto());
		panelDatos.add(getTablaCompras());
		//panelDatos.add(getPanelInventario());
		//panelDatos.add(getTicket());
        panelDatos.add(getEdicionCompras());
        panelDatos.add(getTablaListado());
		return panelDatos;

	}

	public JPanel getFecha(){

		JPanel panelFecha = new JPanel();

		panelFecha.add(dia);
		dia.setBounds(550,20,50,20);
		panelFecha.add(texDia);
		texDia.setBounds(580,20,20,20);

		panelFecha.add(mes);
		mes.setBounds(620,20,70,20);
		panelFecha.add(texMes);
		texMes.setBounds(660,20,20,20);

		panelFecha.add(anio);
		anio.setBounds(700,20,70,20);
		panelFecha.add(texAnio);
		texAnio.setBounds(730,20,30,20);

		return panelFecha;

	}

	public JPanel getDatosProducto(){

		JPanel panelProductos = new JPanel();

		panelProductos.setBorder(BorderFactory.createTitledBorder("Datos de la compra: "));

		panelProductos.setLayout(new GridLayout(4,1,1,1));

		for (int i = 0; i < aDatosProducto.length; i++){

			panelProductos.add(aDatosProducto[i]);
			panelProductos.add(aTextoProducto[i]);
			panelProductos.add(getBotonesProducto()); 

		}

        aTextoProducto[0].addFocusListener(control);
        aTextoProducto[1].addFocusListener(control);
        aTextoProducto[2].addFocusListener(control);
        aTextoProducto[3].addFocusListener(control);

        btAceptarP.addActionListener(control);
        btAceptarP.setActionCommand("Verificar");
        btCancelarP.addActionListener(control);
        btCancelarP.setActionCommand("Limpiar");

		return panelProductos;

	}

	public JPanel getEdicionCompras(){

		JPanel panelEdicion = new JPanel();

		panelEdicion.add(getBotonesCompra()/*, BorderLayout.EAST*/); 
		panelEdicion.add(getBuscarCompra());

		return panelEdicion;

	}

	public JPanel getBuscarCompra(){

		JPanel panelBuscar = new JPanel();

		JLabel buscar = new JLabel("Buscar");
		panelBuscar.add(buscar);

		JTextField campoBuscar = new JTextField();
		campoBuscar.setText(" Ingrese fecha e.j. 01-01-2000");
		campoBuscar.setPreferredSize(new Dimension(200,20));
		panelBuscar.add(campoBuscar);
		campoBuscar.addFocusListener(control);

		JButton btBuscar = new JButton(new ImageIcon("igu/lupita.png"));
		btBuscar.setPreferredSize(new Dimension(32,32));
		panelBuscar.add(btBuscar);
		btBuscar.addActionListener(control);
        btBuscar.setActionCommand("btBuscar");

		return panelBuscar;

	}

	//Método que sea un getModeloDTO() como el otro

	public DefaultTableModel getModeloDTO(){

		return modeloDTO;

	}

    public JPanel getTablaCompras(){

		JPanel panelTabla = new JPanel();

		JTable tablaCompras = new JTable();
		//JScrollPane jScroll = new JScrollPane(tablaCompras);

		modeloDTO = new DefaultTableModel();

		modeloDTO.setColumnIdentifiers(new Object[]{"#", "Nombre", "Cantidad", "Costo", "Fecha de Compra", "Total"});

		tablaCompras.setModel(modeloDTO);
		JScrollPane jScroll = new JScrollPane(tablaCompras);

		//jScroll.setViewportView(tablaCompras);

		panelTabla.add(jScroll);
		panelTabla.setBorder(BorderFactory.createTitledBorder("Compras a Registrar:"));

		return panelTabla;

	}
/*
    public JPanel getTablaListado(){

		JPanel panelTabla = new JPanel();

		JTable tablaCompras = new JTable();
		//JScrollPane jScroll = new JScrollPane(tablaCompras);

		String [] nombre = {
                "#", "Nombre", "Cantidad", "Costo", "Fecha de Compra","Total"
        };

		tablaCompras.setModel(new DefaultTableModel(
            
            new Object [][] {


            	{"#", "Nombre", "Cantidad", "Costo", "Fecha de Compra","Total"},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}

            }, nombre
            
        ));

		//jScroll.setViewportView(tablaCompras);

		//panelTabla.add(jScroll);
		panelTabla.add(tablaCompras);
		panelTabla.setBorder(BorderFactory.createTitledBorder("Resultados de la busqueda"));

		return panelTabla;

	}
*/
	//nuevo
	public JPanel getTablaListado(){

		JPanel panel = new JPanel();
		DAOCompra dao = new DAOCompra();

		panel.setBorder(BorderFactory.createTitledBorder("Registros"));
		modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"ID", "Insumo", "Cantidad", "Costo Total", "Fecha Compra"});
		//creacion de la tabla
		tabla = new JTable(modelo);
		JScrollPane jScroll = new JScrollPane(tabla);

		dao.getTabla(modelo);

        jScroll.setViewportView(tabla);


		panel.add(jScroll, BorderLayout.CENTER);

		return panel;

	}

	public JPanel getBotonesProducto(){

		JPanel botones = new JPanel();

		botones.setLayout(new GridLayout(1,1));

		botones.add(btAceptarP);
		botones.add(btCancelarP);

		return botones;
	}


	public JPanel getBotonesCompra(){

		JPanel botones = new JPanel();

		botones.setLayout(new GridLayout(1,1,10,10));
		botones.add(btAgregar);
		botones.add(btCancelar);
		
		return botones;
	}

    public DTOCompra getCampos(){

        DTOCompra compra = new DTOCompra();

        compra.setNombre(aTextoProducto[0].getText());
        compra.setCantidad(Float.valueOf(aTextoProducto[1].getText()));
        compra.setTotal(Float.valueOf(aTextoProducto[2].getText()));
        compra.setFechaCompra(aTextoProducto[3].getText());

        return compra;

    }

	public JPanel getTicket(DTOCompra[] aCompras){

		JPanel ticket = new JPanel();

		ticket.setBorder(BorderFactory.createTitledBorder("Ticket:"));

		ticket.setLayout(new GridLayout(4,1,1,1));

		JLabel primerRegistro = new JLabel();
		JLabel segundoRegistro = new JLabel();
		JLabel tercerRegistro = new JLabel();
		JLabel cuartoRegistro = new JLabel();
		JLabel quintoRegistro = new JLabel();

		ticket.add(primerRegistro);
		ticket.add(segundoRegistro);
		ticket.add(tercerRegistro);
		ticket.add(cuartoRegistro);
		ticket.add(quintoRegistro);

		return ticket;

	}

	public void agregarCompraTabla(DTOCompra compra){

		comprasTabla[indiceTabla] = compra;

	}

	private DefaultTableModel oModelo;
	private JTable oTabla;

	public JPanel getPanelInventario(){

		JPanel oPanel = new JPanel();
		DAOCompra oDAO = new DAOCompra();

		oPanel.setBorder(BorderFactory.createTitledBorder("Pre Registro"));
		oModelo = new DefaultTableModel();
		oModelo.setColumnIdentifiers(new Object[]{"Numero", "Nombre", "Cantidad", "Costo"});
		//oPanel.setLayout(new GridLayout(1,4));

		//creacion de la oTabla
		oTabla = new JTable(oModelo);
		JScrollPane jScroll = new JScrollPane(oTabla);

		//oDAO.getTabla(oModelo);

		jScroll.setViewportView(oTabla);

		oPanel.add(jScroll, BorderLayout.CENTER);
		return oPanel;

	}

	public void mostrarDatosBusqueda(DTOCompra compras){

		JOptionPane.showMessageDialog(null,getTablaConsulta(compras));
		
	}

//Método de búsqueda de PAM
	public JPanel getTablaConsulta(DTOCompra compras){

		JPanel panelTabla = new JPanel();

		JTable tablaConsulta = new JTable();

		String [] nombre = {
                "ID", "Insumo", "Cantidad", "Costo Total", "Fecha Compra"
        };

		tablaConsulta.setModel(new DefaultTableModel(
            
            new Object [][] {

            	{"Insumo", "Cantidad", "Costo Total", "Fecha Compra"},
                {compras.getNombre(), compras.getCantidad(), compras.getTotal(), compras.getFechaCompra()}

            }, nombre
            
        ));

		panelTabla.add(tablaConsulta);
		panelTabla.setBorder(BorderFactory.createTitledBorder("Consulta Compras"));

		return panelTabla;

	}

	public DefaultTableModel getModelo(){

		return oModelo;

	}

	public void limpiarProducto(){

		aTextoProducto[0].setText(null);
		aTextoProducto[1].setText(null);
		aTextoProducto[2].setText(null);
		aTextoProducto[3].setText(null);

	}

}