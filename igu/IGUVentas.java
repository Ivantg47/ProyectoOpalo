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
import java.text.DecimalFormat;

import ProyectoOpalo.control.ControlVenta;
import ProyectoOpalo.dao.DAOVentas;
import ProyectoOpalo.dto.DTOVentas;
import ProyectoOpalo.dao.DAOProducto;
import ProyectoOpalo.dto.DTOProducto;
import ProyectoOpalo.dao.DAOClientes;
import ProyectoOpalo.dto.DTOClientes;

public class IGUVentas extends JFrame{

	private static final float IVA = 0.16f;
	private ControlVenta control = new ControlVenta(this);
	private DecimalFormat formato = new DecimalFormat("$ #,##0.00");
	private DefaultTableModel modelo;
	private JTable tabla;
	private float total;
	private JTextField campoBuscar;
	private JTextField campoFolio;
	private JTextField campoFecha;

	private JTextField texTotal[] = {

		new JTextField(), //0 -> subtotal
		new JTextField(), //1 -> iva
		new JTextField(), //2 -> total

	};

	private JLabel aDatosCliente[] = {

		new JLabel("ID"),
		new JLabel("Nombre")

	};

	public JTextField aTextoCliente[] = {

		new JTextField(), //0 -> id
		new JTextField()  //1 -> nombre

	};

	private JLabel aDatosProducto[] = {

		new JLabel("ID"),
		new JLabel("Descripcion"),
		new JLabel("Precio"),
		new JLabel("Disponible"),
		new JLabel("Cantidad")

	};

	private JTextField aTextoProducto[] = {

		new JTextField(), //0 -> id
		new JTextField(), //1 -> descripcion
		new JTextField(), //2 -> Precio
		new JTextField(), //3 -> Disponible
		new JTextField(), //4 -> cantidad

	};

	
	

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
		campoBuscar.setPreferredSize(new Dimension(200,25));
		panelBuscar.add(campoBuscar);
		campoBuscar.addFocusListener(control);
		campoBuscar.addActionListener(control);
		campoBuscar.setActionCommand("buscarVenta");

		JButton btBuscar = new JButton(new ImageIcon(getClass().getResource("/iconos/lupa (2).png")));
		btBuscar.setPreferredSize(new Dimension(25,25));
		panelBuscar.add(btBuscar);
		btBuscar.addActionListener(control);
        btBuscar.setActionCommand("buscarVenta");

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
		campoFecha = new JTextField(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

		panel.add(folio);
		panel.add(fecha);
		panel.add(campoFolio);
		panel.add(campoFecha);
		campoFecha.setEnabled(false);
		campoFolio.setEnabled(false);


		cliente.setBounds(5, 5, 375, 68);
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
		aTextoCliente[0].addActionListener(control);
		aTextoCliente[0].setActionCommand("buscarCliente");
		aTextoCliente[0].setHorizontalAlignment(JTextField.RIGHT);
		//nombre cliente
		aDatosCliente[1].setBounds(65, 12, 80, 25);
		aTextoCliente[1].setBounds(65, 36, 240, 25);
		aTextoCliente[1].setEnabled(false);

		JButton btBuscar = new JButton(new ImageIcon(getClass().getResource("/iconos/lupa (2).png")));
		btBuscar.setToolTipText("Buscar cliente");
		btBuscar.addActionListener(control);
		btBuscar.setActionCommand("buscarCliente");

		JButton btLimpiar = new JButton(new ImageIcon(getClass().getResource("/iconos/borrador (2).png")));
		btLimpiar.setToolTipText("Limpiar campos");
		btLimpiar.addActionListener(control);
		btLimpiar.setActionCommand("limpiarCliente");

		panelCliente.add(btBuscar);
		panelCliente.add(btLimpiar);
		btBuscar.setBounds(310, 36, 25, 25);
		btLimpiar.setBounds(340, 36, 25, 25);

		panelCliente.setPreferredSize(new Dimension(370, 75));
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
        aTextoProducto[1].setBounds(65, 35, 280, 25);
        aTextoProducto[1].setEnabled(false);
        //precio
        aDatosProducto[2].setBounds(350, 12, 70, 25);
        aTextoProducto[2].setBounds(350, 35, 70, 25);
        aTextoProducto[2].setHorizontalAlignment(JTextField.RIGHT);
        aTextoProducto[2].setEnabled(false);
        //disponibles
        aDatosProducto[3].setBounds(425, 12, 70, 25);
        aTextoProducto[3].setBounds(425, 35, 70, 25);
        aTextoProducto[3].setHorizontalAlignment(JTextField.RIGHT);
        aTextoProducto[3].setEnabled(false);
        //catidad
        aDatosProducto[4].setBounds(500, 12, 70, 25);
        aTextoProducto[4].setBounds(500, 35, 70, 25);
        aTextoProducto[4].setHorizontalAlignment(JTextField.RIGHT);
        aTextoProducto[4].setEnabled(false);
        aTextoProducto[4].addActionListener(control);
        aTextoProducto[4].setActionCommand("agregar");
        

        JButton btBuscar = new JButton(new ImageIcon(getClass().getResource("/iconos/lupa (2).png")));
		btBuscar.setToolTipText("Buscar producto");
		btBuscar.addActionListener(control);
		btBuscar.setActionCommand("buscarProducto");

		JButton btLimpiar = new JButton(new ImageIcon(getClass().getResource("/iconos/borrador (2).png")));
		btLimpiar.setToolTipText("Limpiar campos");
		btLimpiar.addActionListener(control);
		btLimpiar.setActionCommand("limpiarProducto");

		panelProductos.add(btBuscar);
		panelProductos.add(btLimpiar);
		btBuscar.setBounds(575, 35, 25, 25);
		btLimpiar.setBounds(605, 35, 25, 25);

        panelProductos.setPreferredSize(new Dimension(640, 68));
        // panelProductos.setBackground(new Color(155,155,100));

		return panelProductos;

	}//getPanelDatosProducto

	public JPanel getPanelProducto(){

		JPanel panel = new JPanel();

		panel.add(getPanelDatosProducto());

		JButton btAgregar = new JButton(new ImageIcon(getClass().getResource("/iconos/agregar3.png")));
		btAgregar.setPreferredSize(new Dimension(50, 50));
		btAgregar.setToolTipText("Agregar producto");
		btAgregar.addActionListener(control);
        btAgregar.setActionCommand("agregarProducto");

        JButton btQuitar = new JButton(new ImageIcon(getClass().getResource("/iconos/quitar.png")));
        btQuitar.setPreferredSize(new Dimension(50, 50));
        btQuitar.setToolTipText("Quitar producto");
        btQuitar.addActionListener(control);
        btQuitar.setActionCommand("quitarProducto");

		panel.add(btAgregar);
		panel.add(btQuitar);

		panel.setPreferredSize(new Dimension(775, 85));
		// panel.setBackground(new Color(255,100,100));
		return panel;
	}//getPanelProducto

	public JPanel getPanelTablaProductos(){

		JPanel panelTabla = new JPanel();
		tabla = new JTable();

		modelo = new DefaultTableModel();
		modelo.setColumnIdentifiers(new Object[]{"#", "Nombre", "Precio Unitario", "Cantidad", "SubTotal"});
		tabla.setModel(modelo);

		JScrollPane jScroll = new JScrollPane(tabla);
		panelTabla.add(jScroll);
		jScroll.setPreferredSize(new Dimension(755, 190));

		panelTabla.setPreferredSize(new Dimension(775, 200));
		// panelTabla.setBackground(new Color(100,100,100));

		return panelTabla;

	}//getPanelTablaProductos

	public JPanel getPanelBotonesVenta(){

		JPanel botones = new JPanel();
		
		JButton btVenta = new JButton(new ImageIcon(getClass().getResource("/iconos/payment-method.png")));
		btVenta.setPreferredSize(new Dimension(80, 80));
		btVenta.setToolTipText("Generar venta");
		btVenta.addActionListener(control);
        btVenta.setActionCommand("concretarVenta");

        JButton btCancelar = new JButton(new ImageIcon(getClass().getResource("/iconos/cancel.png")));
		btCancelar.setPreferredSize(new Dimension(80, 80));
		btCancelar.setToolTipText("Cancelar venta");
		btCancelar.addActionListener(control);
        btCancelar.setActionCommand("cancelarVenta");

        JButton btNuevo = new JButton(new ImageIcon(getClass().getResource("/iconos/agregar-archivo.png")));
		btNuevo.setPreferredSize(new Dimension(80, 80));
		btNuevo.setToolTipText("Nueva venta");
		btNuevo.addActionListener(control);
        btNuevo.setActionCommand("nuevaVenta");

        botones.add(btNuevo);
        botones.add(btCancelar);
		botones.add(btVenta);

		botones.add(getPanelTotal());

		botones.setPreferredSize(new Dimension(775, 90));
		// botones.setBackground(new Color(255,100,255));
		return botones;

	}//getPanelBotonesVenta

	public JPanel getPanelTotal(){

		JPanel pTotal = new JPanel();
		pTotal.setLayout(null);

		JLabel subtotal = new JLabel("Subtotal:");
		subtotal.setBounds(210, 2, 60, 23);
		pTotal.add(subtotal);
		JLabel iva = new JLabel("I.V.A:");
		iva.setBounds(210, 30, 60, 23);
		pTotal.add(iva);
		JLabel total = new JLabel("Total:");
		total.setBounds(210, 57, 60, 23);
		pTotal.add(total);
		// total.setFont(new Font("Tahoma", Font.PLAIN, 36));

		//subtotal
		texTotal[0].setText(formato.format(0));
		texTotal[0].setHorizontalAlignment(JTextField.RIGHT);
		pTotal.add(texTotal[0]);
		texTotal[0].setBounds(270, 2, 100, 23);
		texTotal[0].setEnabled(false);
		//iva
		texTotal[1].setText(formato.format(0));
		texTotal[1].setHorizontalAlignment(JTextField.RIGHT);
		pTotal.add(texTotal[1]);
		texTotal[1].setBounds(270, 30, 100, 23);
		texTotal[1].setEnabled(false);
		//total
		texTotal[2].setText(formato.format(0));
		texTotal[2].setHorizontalAlignment(JTextField.RIGHT);
		pTotal.add(texTotal[2]);
		texTotal[2].setBounds(270, 57, 100, 23);
		texTotal[2].setEnabled(false);
		// texTotal.setFont(new Font("Tahoma", Font.PLAIN, 30));

		pTotal.setPreferredSize(new Dimension(380, 85));
		// pTotal.setBackground(new Color(200,100,155));
		
		return pTotal;
	}//getPanelTotal

/////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setCampoCliente(DTOClientes cliente) {

		if (cliente.getIdCliente() != 0){

			aTextoCliente[1].setText(cliente.getNombre() + " "
									+ cliente.getPaterno() + " "
									+ cliente.getMaterno());
			aTextoCliente[0].setEnabled(false);
			aTextoProducto[0].requestFocus();

		} else {

			aTextoCliente[0].setText(null);

		}

	}//setCampoCliente

	public int getCampoCliente() throws NumberFormatException {

		return Integer.valueOf(aTextoCliente[0].getText());

	}//getCampoCliente

	public void limpiarCampoCliente(){

		aTextoCliente[0].setText(null);
		aTextoCliente[1].setText(null);
		aTextoCliente[0].setEnabled(true);
		aTextoCliente[0].requestFocus();
		
	}//limpiarCampo

	public void setCampoProducto(DTOProducto producto) {
		
		if (producto.getCodigo() != 0){

			aTextoProducto[0].setEnabled(false);
			aTextoProducto[1].setText(producto.getNombre() + " " + producto.getDescripcion());
			aTextoProducto[2].setText(String.valueOf(producto.getPrecio()));
			aTextoProducto[3].setText(String.valueOf(producto.getActual()));
			aTextoProducto[4].setEnabled(true);
			aTextoProducto[4].requestFocus();

		} 

	}//setCampoProducto

	public int getCampoProducto() throws NumberFormatException {

		return Integer.valueOf(aTextoProducto[0].getText());

	}//getCampoProducto

	public void limpiarCampoProducto(){
		
		aTextoProducto[0].setText(null);
		aTextoProducto[1].setText(null);
		aTextoProducto[2].setText(null);
		aTextoProducto[3].setText(null);
		aTextoProducto[4].setText(null);
		aTextoProducto[0].setEnabled(true);
		aTextoProducto[4].setEnabled(false);
		aTextoProducto[0].requestFocus();
		
	}//limpiarCampoProducto

	public void agregarProducto() throws NumberFormatException, IllegalArgumentException {

		if (!aTextoProducto[1].getText().equals("")) {

			if (!aTextoProducto[4].getText().equals("") && Integer.valueOf(aTextoProducto[4].getText()) != 0) {
				
				if (aTextoProducto[4].getText().compareTo(aTextoProducto[3].getText()) <= 0){
					modelo.addRow(new Object[]{Integer.valueOf(aTextoProducto[0].getText()), aTextoProducto[1].getText(), aTextoProducto[2].getText(),
											Integer.valueOf(aTextoProducto[4].getText()), 
											(Integer.valueOf(aTextoProducto[4].getText()) * Float.valueOf(aTextoProducto[2].getText()))});
					
					total = total + (Integer.valueOf(aTextoProducto[4].getText()) * Float.valueOf(aTextoProducto[2].getText()));
					texTotal[2].setText(formato.format(total));
					texTotal[1].setText(formato.format(total * IVA));
					texTotal[0].setText(formato.format(total - (total * IVA)));
					aTextoProducto[0].setEnabled(true);
					aTextoProducto[0].requestFocus();
					
				} else {

					throw new IllegalArgumentException("Solo hay: " + aTextoProducto[3].getText() + " producto disponibles.");

				}

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
		
	}//limpiarCampoVenta

	public void quitarProducto() throws IllegalArgumentException, ArrayIndexOutOfBoundsException {

		if (tabla.getSelectedRow() != -1) {
		
				total = total - (Float) tabla.getValueAt(tabla.getSelectedRow(), 4);
				texTotal[2].setText(formato.format(total));
				modelo.removeRow(tabla.getSelectedRow());

		} else {

			throw new IllegalArgumentException("No se ha seleccionado un producto");

		}
	 
	}//quitarProducto

	public DTOVentas generarVenta() throws IllegalArgumentException {

		campoVacio();
		DTOVentas venta = new DTOVentas();

		if (!campoFolio.getText().equals("")) {

	
			venta.setFecha(campoFecha.getText());
			venta.setIdCliente(Integer.valueOf(aTextoCliente[0].getText()));
	
			int idPreoducto[] = new int[modelo.getRowCount()];
			int idCantidad[] = new int[modelo.getRowCount()];
	
			for (int con = 0; con < modelo.getRowCount(); con++) {
				
				idPreoducto[con] = (int) tabla.getValueAt(con, 0);
				idCantidad[con] = (int) tabla.getValueAt(con, 3);
	
			}
	
			venta.setIdProducto(idPreoducto);
			venta.setCantidad(idCantidad);
	
	        Object[] options = { new ImageIcon(getClass().getResource("/iconos/efectivo (1).png")), 
	        					new ImageIcon(getClass().getResource("/iconos/tarjeta-de-credito (1).png")) };
			
			int opcion = JOptionPane.showOptionDialog(this, "Seleccione metodo de pago", "Metodo de pago",
						 JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);

			if (opcion == 0){
	
				venta.setTipoPago("EFECTIVO");
	
			} else if (opcion == 0){
	
				venta.setTipoPago("TARJETA");
	
			} else {

				throw new IllegalArgumentException("cancelo");

			}
				
				venta.setEstado("CONCRETADA");

		} else {

			throw new IllegalArgumentException("La venta ya esta registrada");

		}

		return venta;

	} 

	public void campoVacio() throws NullPointerException, IllegalArgumentException {

		if (aTextoCliente[1].getText().equals("")) {
			
			throw new IllegalArgumentException("No a sido seleccionado un cliente");

		} else if (modelo.getRowCount() == 0) {
			
			throw new IllegalArgumentException("No se han ingresado productos");

		}

	}

	public void nuevaVenta(){

		limpiarCampoProducto();
		limpiarCampoCliente();

		modelo.setRowCount(0);
		texTotal[0].setText(formato.format(0));
		texTotal[1].setText(formato.format(0));
		texTotal[2].setText(formato.format(0));
		total = 0.0f;
		campoFecha.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		campoFolio.setText(null);

	}

	public int getCampoBuscar() throws NumberFormatException{

		return Integer.valueOf(campoBuscar.getText());

	}

	public DefaultTableModel getModelo(){

		return modelo;
	}

	public void setVenta(DTOVentas venta){

		for (int con = 0; con < modelo.getRowCount(); con++) {

			total = total + (float) tabla.getValueAt(con, 4);
			
			texTotal[2].setText(formato.format(total));
			texTotal[1].setText(formato.format(total * IVA));
			texTotal[0].setText(formato.format(total - (total * IVA)));
		}

		campoFolio.setText(String.valueOf(venta.getIdVenta()));
		campoFecha.setText(venta.getFecha());

		aTextoCliente[0].setText(String.valueOf(venta.getIdCliente()));
		aTextoCliente[0].setEnabled(false);
		aTextoProducto[0].setEnabled(false);
		aTextoCliente[1].setText(venta.getCliente());

		campoBuscar.setText("Folio");
		campoBuscar.setForeground(new Color(111,111,111));
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////	
	

/*
	public static void main(String[] args) {
		
		IGUVentas ventana = new IGUVentas();
	
	}
*/
}