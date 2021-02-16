/**
 * Clase IGU de Ventas.
 * @author Pamela Stephanie Moreno Parker
 * @author Ivan Tronco
 * @version 1.0
 */

package ProyectoOpalo.igu;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.Date;
import java.text.SimpleDateFormat;

import ProyectoOpalo.control.ControlVenta;
import ProyectoOpalo.dao.DAOVentas;
import ProyectoOpalo.dto.DTOVentas;
import ProyectoOpalo.dao.DAOProducto;
import ProyectoOpalo.dto.DTOProducto;
import ProyectoOpalo.dao.DAOClientes;
import ProyectoOpalo.dto.DTOClientes;

public class IGUVentas extends JFrame{

	private ControlVenta control = new ControlVenta(this);
	public DTOVentas ventas = new DTOVentas();
	private DefaultTableModel modelo;
	private JTable tabla;

	JTextField campoBuscar, texTotal;

	private JLabel aDatosCliente[] = {

		new JLabel("ID"),
		new JLabel("Nombre")

	};

	public JTextField aTextoCliente[] = {

		new JTextField(),
		new JTextField()

	};

	private JLabel aDatosProducto[] = {

		new JLabel("ID"),
		new JLabel("Descripcion"),
		new JLabel("Precio"),
		new JLabel("Disponible"),
		new JLabel("Cantidad"),
		new JLabel("Total"),

	};

	private JTextField aTextoProducto[] = {

		new JTextField(), //0 -> id
		new JTextField(), //1 -> descripcion
		new JTextField(), //2 -> Precio
		new JTextField(), //3 -> Disponible
		new JTextField(), //4 -> cantidad
		new JTextField()  //5 -> total

	};

	private JTextField campoFolio, campoFecha;

	private JButton btAceptarP, btLimpiarP;
	private JButton btAceptarC = new JButton("Aceptar"), btLimpiarC = new JButton("Limpiar");
	private JButton btAgregar, btCancelar;


	public IGUVentas(){

	}

	public JPanel getPanelVentas(){

		JPanel panelVentas = new JPanel();

		panelVentas.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		panelVentas.add(getPanelBuscar());
		panelVentas.add(getPanelVenta());

		return panelVentas;

	}

	public JPanel getPanelVenta(){

		JPanel panelVentas = new JPanel();

		panelVentas.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelVentas.setBorder(BorderFactory.createTitledBorder("Detalles de venta"));
		
		panelVentas.add(getPanelDatos());
		panelVentas.add(getPanelProducto());
		panelVentas.add(getPanelTablaProductos());
		panelVentas.add(getPanelBotonesVenta());

		panelVentas.setPreferredSize(new Dimension(785, 500));
		// panelVentas.setBackground(new Color(155,255,100));

		return panelVentas;

	}

	public JPanel getPanelBuscar(){

		JPanel panelBuscar = new JPanel();

		panelBuscar.setBorder(BorderFactory.createTitledBorder(""));
		panelBuscar.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel titulo = new JLabel("Ventas        ");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 36));
		panelBuscar.add(titulo);

		JLabel buscar = new JLabel("Buscar");
		panelBuscar.add(buscar);

		campoBuscar = new JTextField();
		campoBuscar.setText("Folio");
		campoBuscar.setForeground(new Color(111,111,111));
		campoBuscar.setPreferredSize(new Dimension(200,20));
		panelBuscar.add(campoBuscar);
		campoBuscar.addFocusListener(control);

		JButton btBuscar = new JButton(new ImageIcon(getClass().getResource("/iconos/lupa.png")));
		btBuscar.setPreferredSize(new Dimension(32,32));
		panelBuscar.add(btBuscar);
		btBuscar.addActionListener(control);
        btBuscar.setActionCommand("btBuscar");

        panelBuscar.setPreferredSize(new Dimension(785, 55));
        // panelBuscar.setBackground(new Color(255,155,100));
		return panelBuscar;

	}

	public JPanel getPanelDatos(){

		JPanel panel = new JPanel();
		JPanel cliente = new JPanel();
		cliente = getPanelDatosCliente();

		panel.setLayout(null);

		panel.add(cliente);

		JLabel folio = new JLabel("Folio");
		JLabel fecha = new JLabel("Fecha");
		campoFolio = new JTextField();
		campoFecha = new JTextField(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));

		panel.add(folio);
		panel.add(fecha);
		panel.add(campoFolio);
		panel.add(campoFecha);
		campoFecha.setEnabled(false);
		campoFolio.setEnabled(false);


		cliente.setBounds(5, 5, 350, 68);
		folio.setBounds(520, 5, 40, 25);
		campoFolio.setBounds(552, 5, 50, 25);
		fecha.setBounds(610, 5, 40, 25);
		campoFecha.setBounds(655, 5, 90, 25);
		campoFecha.setForeground(Color.BLACK);

		panel.setPreferredSize(new Dimension(775, 75));
		// panel.setBackground(new Color(155,1,155));
		return panel;

	}

	public JPanel getPanelDatosCliente(){

		JPanel panelCliente = new JPanel();

		panelCliente.setBorder(BorderFactory.createTitledBorder("Datos del cliente: "));

		panelCliente.setLayout(null);

		for (int i = 0; i < aDatosCliente.length; i++){

			panelCliente.add(aDatosCliente[i]);
			panelCliente.add(aTextoCliente[i]); 

		}

		aDatosCliente[0].setBounds(10, 12, 60, 25);
		aTextoCliente[0].setBounds(10, 36, 60, 25);

		aDatosCliente[1].setBounds(75, 12, 80, 25);
		aTextoCliente[1].setBounds(75, 36, 230, 25);

		panelCliente.setPreferredSize(new Dimension(350, 75));
		// panelCliente.setBackground(new Color(155,155,1));

		return panelCliente;

	}

<<<<<<< HEAD
	public void leerDatosCliente(){		

		DAOClientes cliente = new DAOClientes();
		DTOClientes clienteB = new DTOClientes();
=======

	public JPanel getPanelDatosProducto(){

		JPanel panelProductos = new JPanel();

		panelProductos.setBorder(BorderFactory.createTitledBorder("Datos del producto: "));

		panelProductos.setLayout(null);

		for (int i = 0; i < aDatosProducto.length; i++){

			panelProductos.add(aDatosProducto[i]);
			panelProductos.add(aTextoProducto[i]);

		}
		//codigo
		aDatosProducto[0].setBounds(10, 12, 60, 25);
        aTextoProducto[0].setBounds(10, 35, 60, 25);
        //descripcion
        aDatosProducto[1].setBounds(75, 12, 80, 25);
        aTextoProducto[1].setBounds(75, 35, 220, 25);
        //precio
        aDatosProducto[2].setBounds(300, 12, 70, 25);
        aTextoProducto[2].setBounds(300, 35, 70, 25);
        //disponibles
        aDatosProducto[3].setBounds(375, 12, 70, 25);
        aTextoProducto[3].setBounds(375, 35, 70, 25);
        //catidad
        aDatosProducto[4].setBounds(450, 12, 70, 25);
        aTextoProducto[4].setBounds(450, 35, 70, 25);
        //total
        aDatosProducto[5].setBounds(525, 12, 100, 25);
        aTextoProducto[5].setBounds(525, 35, 100, 25);

        panelProductos.setPreferredSize(new Dimension(640, 68));
        // panelProductos.setBackground(new Color(155,155,100));

		return panelProductos;

	}

	public JPanel getPanelProducto(){

		JPanel panel = new JPanel();

		panel.add(getPanelDatosProducto());

		btAceptarP = new JButton(new ImageIcon(getClass().getResource("/iconos/agregar3.png")));
		btAceptarP.setPreferredSize(new Dimension(55, 55));
		btAceptarP.addActionListener(control);
        btAceptarP.setActionCommand("Verificar");

        btLimpiarP = new JButton(new ImageIcon(getClass().getResource("/iconos/quitar.png")));
        btLimpiarP.setPreferredSize(new Dimension(55, 55));
        btLimpiarP.addActionListener(control);
        btLimpiarP.setActionCommand("Limpiar");

		panel.add(btAceptarP);
		panel.add(btLimpiarP);

		panel.setPreferredSize(new Dimension(775, 85));
		// panel.setBackground(new Color(255,100,100));
		return panel;
	}

	public JPanel getPanelTablaProductos(){

		JPanel panelTabla = new JPanel();
		JTable tablaProducto = new JTable();

		modelo = new DefaultTableModel();
		modelo.setColumnIdentifiers(new Object[]{"#", "Nombre", "Precio Unitario", "Cantidad", "SubTotal"});
		tablaProducto.setModel(modelo);

		JScrollPane jScroll = new JScrollPane(tablaProducto);
		panelTabla.add(jScroll);
		jScroll.setPreferredSize(new Dimension(755, 200));

		panelTabla.setPreferredSize(new Dimension(775, 210));
		// panelTabla.setBackground(new Color(100,100,100));

		return panelTabla;

	}

	public JPanel getPanelBotonesVenta(){

		JPanel botones = new JPanel();

		btAgregar = new JButton(new ImageIcon(getClass().getResource("/iconos/save.png")));
		btAgregar.setPreferredSize(new Dimension(80, 80));
		btAgregar.addActionListener(control);
        // btAgregar.setActionCommand("Verificar");

        btCancelar = new JButton(new ImageIcon(getClass().getResource("/iconos/trash.png")));
		btCancelar.setPreferredSize(new Dimension(80, 80));
		btCancelar.addActionListener(control);
        // btAgregar.setActionCommand("Verificar");


		JLabel total = new JLabel("         Total Venta");
		total.setFont(new Font("Tahoma", Font.PLAIN, 36));

		texTotal = new JTextField("$ 0.00");
		texTotal.setHorizontalAlignment(JTextField.RIGHT);
		texTotal.setFont(new Font("Tahoma", Font.PLAIN, 30));
		texTotal.setPreferredSize(new Dimension(200, 40));

		botones.add(btAgregar);
		botones.add(btCancelar);
		botones.add(total);
		botones.add(texTotal);

		botones.setPreferredSize(new Dimension(775, 85));
		// botones.setBackground(new Color(255,100,255));
		return botones;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////	
	public void leerDatosCliente(){
>>>>>>> 2606c48870de189c450a3889ade331ffc0925b90

		int idCliente = Integer.valueOf(aTextoCliente[0].getText());
		String tipoPago = aTextoCliente[1].getText();

		clienteB = cliente.buscarCliente(idCliente);

		if (clienteB.getIdCliente() == idCliente) {
			
			ventas.setIdCliente(idCliente);
			ventas.setTipoPago(tipoPago);

		}

	}

	public void limpiarDatosCliente(){

		aTextoCliente[0].setText(null);
		aTextoCliente[1].setText(null);

		System.out.println("limpiado");

	}

	public JPanel getDatosProducto(){

		JPanel panelProductos = new JPanel();

		panelProductos.setBorder(BorderFactory.createTitledBorder("Datos del producto: "));

		panelProductos.setLayout(new GridLayout(2,2,1,1));

		for (int i = 0; i < aDatosProducto.length; i++){

			panelProductos.add(aDatosProducto[i]);
			panelProductos.add(aTextoProducto[i]);
			panelProductos.add(getBotonesProducto()); 

		}

		btAceptarP.addActionListener(control);
		btAceptarP.setActionCommand("btAceptarP");

		btLimpiarP.addActionListener(control);
		btLimpiarP.setActionCommand("btLimpiarP");


		return panelProductos;

	}


	public void leerDatosProducto(){

		DAOProducto producto = new DAOProducto();
		DTOProducto productoB = new DTOProducto();

		int idProdcuto = Integer.valueOf(aTextoProducto[0].getText());
		float cantidad = Float.valueOf(aTextoProducto[1].getText());

		productoB = producto.getProducto(idProdcuto);

		if (productoB.getCodigo() == idProdcuto) {
			
			ventas.setIdProducto(idProdcuto);
			ventas.setCantidadVendida(cantidad);



		} else {

			JOptionPane.showMessageDialog(null, "no esxiste el producto");

		}


	}

	public void limpiarDatosProducto(){

		aTextoProducto[0].setText(null);
		aTextoProducto[1].setText(null);

		System.out.println("limpiado");

	}


	public JPanel getEdicionVentas(){

		JPanel panelEdicion = new JPanel();

		panelEdicion.add(getBotonesVenta(), BorderLayout.EAST); 
		panelEdicion.add(getBuscarVenta());

		return panelEdicion;

	}

	public JPanel getBuscarVenta(){

		JPanel panelBuscar = new JPanel();

		JLabel buscar = new JLabel("Buscar");
		panelBuscar.add(buscar);

		campoBuscar.setPreferredSize(new Dimension(200,20));
		panelBuscar.add(campoBuscar);
	

		JButton btBuscar = new JButton(new ImageIcon("C:/Users/Gatit/Desktop/ProyectoOpalo/igu/lupita.png"));
		btBuscar.setPreferredSize(new Dimension(32,32));
		panelBuscar.add(btBuscar);
		
		btBuscar.addActionListener(control);
		btBuscar.setActionCommand("btBuscar");		

		return panelBuscar;

	}

	public int leerDatoBuscar(){
		
		int idVenta = Integer.valueOf(campoBuscar.getText());
		ventas.setIdVenta(idVenta);

		return idVenta;
	}

	public void mostrarDatosBusqueda(DTOVentas ventas){

		JOptionPane.showMessageDialog(null,getTablaConsulta(ventas));

	}

	public JPanel getTablaConsulta(DTOVentas ventas){

		JPanel panelTabla = new JPanel();

		JTable tablaConsulta = new JTable();

		String [] nombre = {
                "id_venta", "tipoPago", "cancelacion", "fecha", "Estado"
        };

		tablaConsulta.setModel(new DefaultTableModel(
            
            new Object [][] {

            	{"id_venta", "tipoPago", "cancelacion", "fecha", "Estado"},
                {ventas.getIdVenta(), ventas.getTipoPago(), ventas.getDescripcion(), ventas.getFecha(), ventas.getEstado()}

            }, nombre
            
        ));

		panelTabla.add(tablaConsulta);
		panelTabla.setBorder(BorderFactory.createTitledBorder("Consulta ventas"));

		return panelTabla;

	}

	public JPanel getTablaProductos(){

		JPanel panelTabla = new JPanel();

		JTable tablaProducto = new JTable();
		//JScrollPane jScroll = new JScrollPane(tablaVentas);

		String [] nombre = {
                "id producto", "nombre", "cantidad", "Total"
        };

		tablaProducto.setModel(new DefaultTableModel(
            
            new Object [][] {


            	{"id Producto", "nombre", "cantidad", "Total"},
                {null, null, null},
                {null, null, null},
                {null, null, null}

            }, nombre
            
        ));

		//jScroll.setViewportView(tablaVentas);

		//panelTabla.add(jScroll);
		panelTabla.add(tablaProducto);
		panelTabla.setBorder(BorderFactory.createTitledBorder("Productos"));

		return panelTabla;

	}

	public JPanel getTablaVentas(){

		JPanel panel = new JPanel();
		DAOVentas dao = new DAOVentas();

		panel.setBorder(BorderFactory.createTitledBorder("Registros"));
		modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"Venta","cancelacion","tipoPago", "fecha", "Estado"});
		
		tabla = new JTable(modelo);
		JScrollPane jScroll = new JScrollPane(tabla);

		dao.getTabla(modelo);

        jScroll.setViewportView(tabla);


		panel.add(jScroll, BorderLayout.SOUTH);

		return panel;

	}

	public JPanel getBotonesCliente(){

		JPanel botones = new JPanel();

		botones.setLayout(new GridLayout(1,1));
		botones.add(btAceptarC);
		botones.add(btLimpiarC);
		
		return botones;
	}
	
	public JPanel getBotonesProducto(){

		JPanel botones = new JPanel();

		botones.setLayout(new GridLayout(1,1));
		botones.add(btAceptarP);
		botones.add(btLimpiarP);
		
		return botones;
	}


	public JPanel getBotonesVenta(){

		JPanel botones = new JPanel();

		botones.setLayout(new GridLayout(1,1,10,10));
		botones.add(btAgregar);
		btAgregar.addActionListener(control);
		btAgregar.setActionCommand("btAgregar");
		botones.add(btCancelar);
		btCancelar.addActionListener(control);
		btCancelar.setActionCommand("btCancelar");

		
		return botones;
	}

/*
	public static void main(String[] args) {
		
		IGUVentas ventana = new IGUVentas();
	
	}
*/
