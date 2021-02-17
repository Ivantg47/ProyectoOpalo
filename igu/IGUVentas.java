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

	public IGUVentas(){

	}

	public JPanel getPanelVentas(){

		JPanel panelVentas = new JPanel();

		panelVentas.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		panelVentas.add(getPanelBuscar());
		panelVentas.add(getPanelVenta());

		return panelVentas;

	}//getPanelVentas

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

	}//getPanelVenta

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

	}//getPanelBuscar

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

	}//getPanelDatos

	public JPanel getPanelDatosCliente(){

		JPanel panelCliente = new JPanel();

		panelCliente.setBorder(BorderFactory.createTitledBorder("Datos del cliente: "));

		panelCliente.setLayout(null);

		for (int i = 0; i < aDatosCliente.length; i++){

			panelCliente.add(aDatosCliente[i]);
			panelCliente.add(aTextoCliente[i]); 

		}
		//id cliente
		aDatosCliente[0].setBounds(10, 12, 50, 25);
		aTextoCliente[0].setBounds(10, 36, 50, 25);
		//nombre cliente
		aDatosCliente[1].setBounds(65, 12, 80, 25);
		aTextoCliente[1].setBounds(65, 36, 240, 25);
		aTextoCliente[1].setEnabled(false);

		JButton btBuscar = new JButton(new ImageIcon(getClass().getResource("/iconos/lupa (2).png")));
		btBuscar.setToolTipText("Buscar cliente");
		btBuscar.addActionListener(control);
		btBuscar.setActionCommand("buscarCliente");

		panelCliente.add(btBuscar);
		btBuscar.setBounds(310, 36, 25, 25);

		panelCliente.setPreferredSize(new Dimension(350, 75));
		// panelCliente.setBackground(new Color(155,155,1));

		return panelCliente;

	}//getPanelDatosCliente
		

	public JPanel getPanelDatosProducto(){

		JPanel panelProductos = new JPanel();

		panelProductos.setBorder(BorderFactory.createTitledBorder("Datos del producto: "));

		panelProductos.setLayout(null);

		for (int i = 0; i < aDatosProducto.length; i++){

			panelProductos.add(aDatosProducto[i]);
			panelProductos.add(aTextoProducto[i]);

		}
		//codigo
		aDatosProducto[0].setBounds(10, 12, 50, 25);
        aTextoProducto[0].setBounds(10, 35, 50, 25);
        aTextoProducto[0].setHorizontalAlignment(JTextField.RIGHT);
        aTextoProducto[0].addActionListener(control);
        aTextoProducto[0].setActionCommand("buscarProducto");
        //descripcion
        aDatosProducto[1].setBounds(65, 12, 80, 25);
        aTextoProducto[1].setBounds(65, 35, 220, 25);
        aTextoProducto[1].setEnabled(false);
        //precio
        aDatosProducto[2].setBounds(290, 12, 70, 25);
        aTextoProducto[2].setBounds(290, 35, 70, 25);
        aTextoProducto[2].setHorizontalAlignment(JTextField.RIGHT);
        aTextoProducto[2].setEnabled(false);
        //disponibles
        aDatosProducto[3].setBounds(365, 12, 70, 25);
        aTextoProducto[3].setBounds(365, 35, 70, 25);
        aTextoProducto[3].setHorizontalAlignment(JTextField.RIGHT);
        aTextoProducto[3].setEnabled(false);
        //catidad
        aDatosProducto[4].setBounds(440, 12, 70, 25);
        aTextoProducto[4].setBounds(440, 35, 70, 25);
        aTextoProducto[4].setHorizontalAlignment(JTextField.RIGHT);
        //total
    /*    aDatosProducto[5].setBounds(515, 12, 85, 25);
        aTextoProducto[5].setBounds(515, 35, 85, 25);
        aTextoProducto[5].setHorizontalAlignment(JTextField.RIGHT);
        aTextoProducto[5].setEnabled(false);
*/
        JButton btBuscar = new JButton(new ImageIcon(getClass().getResource("/iconos/lupa (2).png")));
		btBuscar.setToolTipText("Buscar producto");
		btBuscar.addActionListener(control);
		// btBuscar.setActionCommand("buscarProducto");

		panelProductos.add(btBuscar);
		btBuscar.setBounds(605, 35, 25, 25);

        panelProductos.setPreferredSize(new Dimension(640, 68));
        // panelProductos.setBackground(new Color(155,155,100));

		return panelProductos;

	}//getPanelDatosProducto

	public JPanel getPanelProducto(){

		JPanel panel = new JPanel();

		panel.add(getPanelDatosProducto());

		JButton btAgregar = new JButton(new ImageIcon(getClass().getResource("/iconos/agregar3.png")));
		btAgregar.setPreferredSize(new Dimension(55, 55));
		btAgregar.addActionListener(control);
        btAgregar.setActionCommand("agregar");

        JButton btQuitar = new JButton(new ImageIcon(getClass().getResource("/iconos/quitar.png")));
        btQuitar.setPreferredSize(new Dimension(55, 55));
        btQuitar.addActionListener(control);
        btQuitar.setActionCommand("quitar");

		panel.add(btAgregar);
		panel.add(btQuitar);

		panel.setPreferredSize(new Dimension(775, 85));
		// panel.setBackground(new Color(255,100,100));
		return panel;
	}//getPanelProducto

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

	}//getPanelTablaProductos

	public JPanel getPanelBotonesVenta(){

		JPanel botones = new JPanel();

		JButton btAgregar = new JButton(new ImageIcon(getClass().getResource("/iconos/efectivo.png")));
		btAgregar.setPreferredSize(new Dimension(80, 80));
		btAgregar.addActionListener(control);
        // btAgregar.setActionCommand("Verificar");

        JButton btCancelar = new JButton(new ImageIcon(getClass().getResource("/iconos/trash.png")));
		btCancelar.setPreferredSize(new Dimension(80, 80));
		btCancelar.addActionListener(control);
        // btAgregar.setActionCommand("Verificar");


		JLabel total = new JLabel("        Total Venta  $");
		total.setFont(new Font("Tahoma", Font.PLAIN, 36));

		texTotal = new JTextField("0.00");
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
	}//getPanelBotonesVenta

/////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setCampoCliente(DTOClientes cliente){

		if (cliente.getIdCliente() != 0){

			aTextoCliente[1].setText(cliente.getNombre() + " "
									+ cliente.getPaterno() + " "
									+ cliente.getMaterno());

		} else {

			aTextoCliente[0].setText(null);

		}

	}//setCampoCliente

	public int getCampoCliente() throws NumberFormatException{

		return Integer.valueOf(aTextoCliente[0].getText());

	}//getCampoCliente

	public void setCampoProducto(DTOProducto producto){
		
		if (producto.getCodigo() != 0){

			aTextoProducto[1].setText(producto.getNombre() + " " + producto.getDescripcion());
			aTextoProducto[2].setText(String.valueOf(producto.getPrecio()));
			aTextoProducto[3].setText(String.valueOf(producto.getActual()));

		} 

	}//setCampoProducto

	public int getCampoProducto() throws NumberFormatException{

		return Integer.valueOf(aTextoProducto[0].getText());

	}//getCampoProducto

	public void limpiarCampoProducto(){
		
		aTextoProducto[0].setText(null);
		aTextoProducto[1].setText(null);
		aTextoProducto[2].setText(null);
		aTextoProducto[3].setText(null);
		aTextoProducto[4].setText(null);
		

	}//limpiarCampoProducto

	public void agregarProducto() throws NumberFormatException, IllegalArgumentException{

		if (!aTextoProducto[1].getText().equals("")) {

			if (!aTextoProducto[4].getText().equals("") && Integer.valueOf(aTextoProducto[4].getText()) != 0 && aTextoProducto[4].getText().compareTo(aTextoProducto[3].getText()) <= 0) {
				
				modelo.addRow(new Object[]{Integer.valueOf(aTextoProducto[0].getText()), aTextoProducto[1].getText(), aTextoProducto[2].getText(),
									Integer.valueOf(aTextoProducto[4].getText()), 
									(Integer.valueOf(aTextoProducto[4].getText()) * Float.valueOf(aTextoProducto[2].getText()))});

				texTotal.setText(String.valueOf(Float.valueOf(texTotal.getText()) + (Integer.valueOf(aTextoProducto[4].getText()) * Float.valueOf(aTextoProducto[2].getText()))));

			} else {

				throw new IllegalArgumentException("No se a seleccionado un cantidad");

			}

		} else {

			throw new IllegalArgumentException("No se a seleccionado un producto");

		}

	}//agregarProducto

	public void limpiarCampoVenta(){
		

		aTextoProducto[0].setText(null);
		aTextoProducto[1].setText(null);
		aTextoProducto[2].setText(null);
		aTextoProducto[3].setText(null);
		aTextoProducto[4].setText(null);
		

	}//limpiarCampoProducto


/////////////////////////////////////////////////////////////////////////////////////////////////////	
	

/*
	public static void main(String[] args) {
		
		IGUVentas ventana = new IGUVentas();
	
	}
*/
}