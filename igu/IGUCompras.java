/**
 * Clase que implementa una interfaz para el modulo de compras.
 * @author Diego Puebla Aldama
 * @version 1.0
 */

package ProyectoOpalo.igu;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class IGUCompras extends JFrame{

	private JLabel dia = new JLabel("D\u00EDa: ");
	private JTextField texDia = new JTextField();

	private JLabel mes = new JLabel ("Mes: ");
	private JTextField texMes = new JTextField();

	private JLabel anio = new JLabel("A\u00F1o: ");
	private JTextField texAnio = new JTextField();

	private JLabel aDatosCliente[] = {

		new JLabel("Insumo: "),
		new JLabel("Cantidad: "),
		new JLabel("Costo: ")

	};

	private JTextField aTextoCliente[] = {

		new JTextField(),
		new JTextField(),
		new JTextField()

	};

	private JLabel aDatosProducto[] = {

		new JLabel("Insumo: "),
		new JLabel("Cantidad: "),
		new JLabel("Costo: ")

	};

	private JTextField aTextoProducto[] = {

		new JTextField(),
		new JTextField(),
		new JTextField()

	};

	private JButton btAceptarP = new JButton("Aceptar"), btCancelarP = new JButton("Cancelar");
	private JButton btAceptarC = new JButton("Aceptar"), btCancelarC = new JButton("Cancelar");
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

	public JPanel getPanelDatos(){

		JPanel panelDatos = new JPanel();

		panelDatos.setLayout(new GridLayout(5,1,10,10));
		
		//panelDatos.add(getFecha());
		//panelDatos.add(getDatosCliente());
		panelDatos.add(getDatosProducto());
		panelDatos.add(getTablaCompras());
        panelDatos.add(getEdicionCompras());
        panelDatos.add(getTablaListado(), BorderLayout.SOUTH);
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

		panelProductos.setBorder(BorderFactory.createTitledBorder("Datos de la compra: "));

		panelProductos.setLayout(new GridLayout(3,1,1,1));

		for (int i = 0; i < aDatosProducto.length; i++){

			panelProductos.add(aDatosProducto[i]);
			panelProductos.add(aTextoProducto[i]);
			panelProductos.add(getBotonesProducto()); 

		}

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

		JButton btBuscar = new JButton(new ImageIcon("lupa.png"));
		btBuscar.setPreferredSize(new Dimension(32,32));
		panelBuscar.add(btBuscar);

		return panelBuscar;

	}

    public JPanel getTablaCompras(){

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
		panelTabla.setBorder(BorderFactory.createTitledBorder("Compras a Registrar:"));

		return panelTabla;

	}

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


	public JPanel getBotonesCompra(){

		JPanel botones = new JPanel();

		botones.setLayout(new GridLayout(1,1,10,10));
		botones.add(btAgregar);
		botones.add(btCancelar);
		
		return botones;
	}
/*
	public static void main(String[] args) {
		
		IGUCompras Comprana = new IGUCompras();
	
	}
*/
}