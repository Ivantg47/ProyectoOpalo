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

	private JLabel folio = new JLabel("Folio: ");
	private JTextField texFolio = new JTextField();

	private JLabel dia = new JLabel("D\u00EDa: ");
	private JTextField texDia = new JTextField();

	private JLabel mes = new JLabel ("Mes: ");
	private JTextField texMes = new JTextField();

	private JLabel anio = new JLabel("A\u00F1o: ");
	private JTextField texAnio = new JTextField();

	private JLabel aDatosProducto[] = {

		new JLabel("Codigo"),
		new JLabel("Descripcion"),
		new JLabel("Precio"),
        new JLabel("Cantidad"),
        new JLabel("Total")

	};

	private JTextField aTextoProducto[] = {

		new JTextField(), //Codigo
		new JTextField(), // Descripcion
		new JTextField(), // Precio
        new JTextField(), //Cantidad
        new JTextField()  //Total

	};

    //botones de pre registro
	private JButton btAceptarP, btCancelarP, btLimpiar;

	private JButton btAgregar, btCancelar;

	private JLabel total;
	private JTextField texTotal;


	public IGUCompras(){

		// add(getPanelCompras());
		// setSize(800, 670);
		// setLocationRelativeTo(null);
		// setDefaultCloseOperation(EXIT_ON_CLOSE);
		// setVisible(true);

	}
	//public static JPanel panelDatos = new JPanel();
	public JPanel getPanelCompras(){

		JPanel panelCompras = new JPanel();

		panelCompras.setLayout(new FlowLayout(FlowLayout.CENTER));
		// panelDatosCompras.setLayout(new GridLayout(5,1,10,10));

		panelCompras.add(getFecha());
		panelCompras.add(getPanelInsumo());
		panelCompras.add(getTablaCompras());
		panelCompras.add(getBotonesCompra());
		

		return panelCompras;

	}

	public JPanel getFecha(){

		JPanel panelFecha = new JPanel();

		panelFecha.setLayout(null);

		panelFecha.add(folio);
		folio.setBounds(420,4,35,25);
		panelFecha.add(texFolio);
		texFolio.setBounds(460,4,60,25);

		panelFecha.add(dia);
		dia.setBounds(530,4,30,25);
		texDia.setPreferredSize(new Dimension(40, 25));
		panelFecha.add(texDia);
		texDia.setBounds(565,4,40,25);

		panelFecha.add(mes);
		mes.setBounds(615,4,30,25);
		texMes.setPreferredSize(new Dimension(40, 25));
		panelFecha.add(texMes);
		texMes.setBounds(650,4,40,25);

		panelFecha.add(anio);
		anio.setBounds(700,4,30,25);
		// texAnio.setPreferredSize(new Dimension(40, 25));
		panelFecha.add(texAnio);
		texAnio.setBounds(735,4,40,25);

		panelFecha.setPreferredSize(new Dimension(785, 35));
		// panelFecha.setBackground(new Color(255,255,100));
		return panelFecha;

	}

	public JPanel getDatosInsumo(){

		JPanel panelProductos = new JPanel();

		panelProductos.setBorder(BorderFactory.createTitledBorder("Datos del insumo: "));

		panelProductos.setLayout(new GridLayout(2,6,1,1));

		for (int i = 0; i < aDatosProducto.length; i++){

			panelProductos.add(aDatosProducto[i]);

		}

		for (int i = 0; i < aTextoProducto.length; i++){

			panelProductos.add(aTextoProducto[i]);

		}

		panelProductos.setPreferredSize(new Dimension(620, 75)); 

        aTextoProducto[0].addFocusListener(control);
        aTextoProducto[1].setPreferredSize(new Dimension(150, 25));
        aTextoProducto[2].addFocusListener(control);
        aTextoProducto[3].addFocusListener(control);

        

		return panelProductos;

	}

	public JPanel getPanelInsumo(){

		JPanel panel = new JPanel();

		panel.add(getDatosInsumo());

		btAceptarP = new JButton(new ImageIcon(getClass().getResource("/iconos/agregar3.png")));
		btAceptarP.setPreferredSize(new Dimension(55, 55));
		btAceptarP.addActionListener(control);
        btAceptarP.setActionCommand("Verificar");

        btCancelarP = new JButton(new ImageIcon(getClass().getResource("/iconos/quitar.png")));
        btCancelarP.setPreferredSize(new Dimension(55, 55));
        btCancelarP.addActionListener(control);
        btCancelarP.setActionCommand("Limpiar");

		panel.add(btAceptarP);
		panel.add(btCancelarP);

		panel.setPreferredSize(new Dimension(785, 85));
		// panel.setBackground(new Color(255,100,100));
		return panel;
	}

	public JPanel getTablaCompras(){

		JPanel panelTabla = new JPanel();

		JTable tablaCompras = new JTable();
		//JScrollPane jScroll = new JScrollPane(tablaCompras);

		modeloDTO = new DefaultTableModel();

		modeloDTO.setColumnIdentifiers(new Object[]{"#", "Nombre", "Cantidad", "Costo Unitario", "SubTotal"});

		tablaCompras.setModel(modeloDTO);
		JScrollPane jScroll = new JScrollPane(tablaCompras);

		//jScroll.setViewportView(tablaCompras);

		panelTabla.add(jScroll);

		jScroll.setPreferredSize(new Dimension(765, 270));
		panelTabla.setPreferredSize(new Dimension(785, 300));
		// panelTabla.setBackground(new Color(100,100,100));
		// panelTabla.setBorder(BorderFactory.createTitledBorder("Compras a Registrar:"));

		return panelTabla;

	}

	public JPanel getBotonesCompra(){

		JPanel botones = new JPanel();

		btAgregar = new JButton(new ImageIcon(getClass().getResource("/iconos/save.png")));
		btAgregar.setPreferredSize(new Dimension(80, 80));
		btAgregar.addActionListener(control);
        // btAgregar.setActionCommand("Verificar");

        btCancelar = new JButton(new ImageIcon(getClass().getResource("/iconos/trash.png")));
		btCancelar.setPreferredSize(new Dimension(80, 80));
		btCancelar.addActionListener(control);
        // btAgregar.setActionCommand("Verificar");

	//	botones.setLayout(new GridLayout(1,1,10,10));
		total = new JLabel("    Total Compra");
		total.setFont(new Font("Tahoma", Font.PLAIN, 36));

		texTotal = new JTextField("0.00");
		texTotal.setHorizontalAlignment(JTextField.RIGHT);
		texTotal.setFont(new Font("Tahoma", Font.PLAIN, 30));
		texTotal.setPreferredSize(new Dimension(200, 40));

		botones.add(btAgregar);
		botones.add(btCancelar);
		botones.add(total);
		botones.add(texTotal);

		botones.setPreferredSize(new Dimension(785, 90));
		// botones.setBackground(new Color(255,100,255));
		return botones;
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////
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

		JButton btBuscar = new JButton(new ImageIcon(getClass().getResource("/iconos/lupa.png")));
		btBuscar.setPreferredSize(new Dimension(32,32));
		panelBuscar.add(btBuscar);
		btBuscar.addActionListener(control);
        btBuscar.setActionCommand("btBuscar");

		return panelBuscar;

	}

	//Método que sea un getModelo() como el otro

    
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

	public static void main(String[] args) {
		
		IGUCompras prueba = new IGUCompras();
	}

}