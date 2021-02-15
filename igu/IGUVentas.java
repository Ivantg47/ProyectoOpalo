/**
 * Clase IGU de Ventas.
 * @author Pamela Stephanie Moreno Parker
 * @version 1.0
 */

package ProyectoOpalo.igu;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

import ProyectoOpalo.control.ControlVenta;
import ProyectoOpalo.dao.DAOVentas;
import ProyectoOpalo.dto.DTOVentas;
import ProyectoOpalo.dao.DAOProducto;
import ProyectoOpalo.dto.DTOProducto;

public class IGUVentas extends JFrame{

	private ControlVenta control = new ControlVenta(this);
	public DTOVentas ventas = new DTOVentas();
	private DefaultTableModel modelo;
	private JTable tabla;

	JTextField campoBuscar = new JTextField();

	private JLabel aDatosCliente[] = {

		new JLabel("ID cliente: "),
		new JLabel("Forma de Pago: ")

	};

	public JTextField aTextoCliente[] = {

		new JTextField(),
		new JTextField()

	};

	private JLabel aDatosProducto[] = {

		new JLabel("ID producto: "),
		new JLabel("Cantidad: "),

	};

	private JTextField aTextoProducto[] = {

		new JTextField(),
		new JTextField()

	};

	private JButton btAceptarP = new JButton("Aceptar"), btLimpiarP = new JButton("Limpiar");
	private JButton btAceptarC = new JButton("Aceptar"), btLimpiarC = new JButton("Limpiar");
	private JButton btAgregar = new JButton("Agregar Venta"), btCancelar = new JButton("Cancelar Venta");

	public IGUVentas(){
	/*
		super("Registro de Venta");

		add(getPanelDatos());
		setLocation(300,30);
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	*/
	}

	public JPanel getPanelDatos(){

		JPanel panelDatos = new JPanel();

		panelDatos.setLayout(new GridLayout(5,1,10,10));
		
		panelDatos.add(getDatosCliente());
		panelDatos.add(getDatosProducto());

		panelDatos.add(getTablaProductos());

		panelDatos.add(getEdicionVentas());

		panelDatos.add(getTablaVentas(), BorderLayout.SOUTH);

		return panelDatos;

	}

	public JPanel getDatosCliente(){

		JPanel panelCliente = new JPanel();

		panelCliente.setBorder(BorderFactory.createTitledBorder("Datos del cliente: "));

		panelCliente.setLayout(new GridLayout(2,2,1,1));

		for (int i = 0; i < aDatosCliente.length; i++){

			panelCliente.add(aDatosCliente[i]);
			panelCliente.add(aTextoCliente[i]);
			panelCliente.add(getBotonesCliente()); 

		}

		btAceptarC.addActionListener(control);
		btAceptarC.setActionCommand("btAceptarC");

		btLimpiarC.addActionListener(control);
		btLimpiarC.setActionCommand("btLimpiarC");

		return panelCliente;

	}

	public void leerDatosCliente(){

		int idCliente = Integer.valueOf(aTextoCliente[0].getText());
		String tipoPago = aTextoCliente[1].getText();
		ventas.setIdCliente(idCliente);
		ventas.setTipoPago(tipoPago);

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

	//	productoB = producto.getPoducto(idProdcuto);

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
                {ventas.getIdVenta(), ventas.getTipoPago(), ventas.getCancelacion(), ventas.getFecha(), ventas.getEstado()}

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
}