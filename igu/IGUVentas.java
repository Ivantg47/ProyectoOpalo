
package ProyectoOpalo.igu;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class IGUVentas extends JFrame{

	private JLabel dia = new JLabel("D\u00EDa: ");
	private JTextField texDia = new JTextField();

	private JLabel mes = new JLabel ("Mes: ");
	private JTextField texMes = new JTextField();

	private JLabel anio = new JLabel("A\u00F1o: ");
	private JTextField texAnio = new JTextField();

	private JLabel aDatosCliente[] = {

		new JLabel("ID: "),
		new JLabel("Nombre: "),
		new JLabel("Forma de Pago: ")

	};

	private JTextField aTextoCliente[] = {

		new JTextField(),
		new JTextField(),
		new JTextField()

	};

	private JLabel aDatosProducto[] = {

		new JLabel("ID: "),
		new JLabel("Cantidad: "),
		new JLabel("Total: ")

	};

	private JTextField aTextoProducto[] = {

		new JTextField(),
		new JTextField(),
		new JTextField()

	};

	private JButton btAceptarP = new JButton("Aceptar"), btCancelarP = new JButton("Cancelar");
	private JButton btAceptarC = new JButton("Aceptar"), btCancelarC = new JButton("Cancelar");
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
		
		panelDatos.add(getFecha());
		panelDatos.add(getDatosCliente());
		panelDatos.add(getDatosProducto());

		panelDatos.add(getEdicionVentas());

		panelDatos.add(getTablaVentas(), BorderLayout.SOUTH);

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

	public JPanel getDatosCliente(){

		JPanel panelCliente = new JPanel();

		panelCliente.setBorder(BorderFactory.createTitledBorder("Datos del cliente: "));

		panelCliente.setLayout(new GridLayout(3,2,1,1));

		for (int i = 0; i < aDatosCliente.length; i++){

			panelCliente.add(aDatosCliente[i]);
			panelCliente.add(aTextoCliente[i]);
			panelCliente.add(getBotonesCliente()); 

		}

		return panelCliente;

	}

	public JPanel getDatosProducto(){

		JPanel panelProductos = new JPanel();

		panelProductos.setBorder(BorderFactory.createTitledBorder("Datos del producto: "));

		panelProductos.setLayout(new GridLayout(3,1,1,1));

		for (int i = 0; i < aDatosProducto.length; i++){

			panelProductos.add(aDatosProducto[i]);
			panelProductos.add(aTextoProducto[i]);
			panelProductos.add(getBotonesProducto()); 

		}

		return panelProductos;

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

		JTextField campoBuscar = new JTextField();
		campoBuscar.setText(" ingrese un dato ");
		campoBuscar.setPreferredSize(new Dimension(200,20));
		panelBuscar.add(campoBuscar);

		JButton btBuscar = new JButton(new ImageIcon("lupa.png"));
		btBuscar.setPreferredSize(new Dimension(32,32));
		panelBuscar.add(btBuscar);

		return panelBuscar;

	}

      public JPanel getTablaVentas(){

		JPanel panelTabla = new JPanel();

		JTable tablaVentas = new JTable();
		//JScrollPane jScroll = new JScrollPane(tablaVentas);

		String [] nombre = {
                "Venta", "Cliente", "Producto", "Nombre", "Cantidad", "Estado", "Descripción", "Total"
        };

		tablaVentas.setModel(new DefaultTableModel(
            
            new Object [][] {


            	{"id Venta", "id Cliente", "nombre", "id Producto", "producto", "Cantidad", "Estado", "Descripci\u00F3n", "Total"},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}

            }, nombre
            
        ));

		//jScroll.setViewportView(tablaVentas);

		//panelTabla.add(jScroll);
		panelTabla.add(tablaVentas);
		panelTabla.setBorder(BorderFactory.createTitledBorder("Registros de ventas"));

		return panelTabla;

	}


	public JPanel getBotonesCliente(){

		JPanel botones = new JPanel();

		botones.setLayout(new GridLayout(1,1));
		botones.add(btAceptarC);
		botones.add(btCancelarC);
		
		return botones;
	}
	
	public JPanel getBotonesProducto(){

		JPanel botones = new JPanel();

		botones.setLayout(new GridLayout(1,1));
		botones.add(btAceptarP);
		botones.add(btCancelarP);
		
		return botones;
	}


	public JPanel getBotonesVenta(){

		JPanel botones = new JPanel();

		botones.setLayout(new GridLayout(1,1,10,10));
		botones.add(btAgregar);
		botones.add(btCancelar);
		
		return botones;
	}
/*
	public static void main(String[] args) {
		
		IGUVentas ventana = new IGUVentas();
	
	}
*/
}