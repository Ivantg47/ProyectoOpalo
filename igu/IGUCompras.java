/**
 * Clase que implementa una interfaz para el modulo de compras.
 * @author Diego Puebla Aldama
 * @author Ivan Tronco
 * @version 2.5
 */

package ProyectoOpalo.igu;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.*;
import ProyectoOpalo.control.ControlCompra;
import ProyectoOpalo.dto.DTOCompra;
import ProyectoOpalo.dao.DAOCompra;
import java.text.DecimalFormat;

import ProyectoOpalo.dao.*;
import ProyectoOpalo.dto.*;

public class IGUCompras extends JFrame{

	public DTOCompra compras = new DTOCompra(); //instancia de la clase DTO que se usa para recopilar los datos de la interfaz.
	private DefaultTableModel modelo, modeloDTO; //Modelos para las tablas usadas.
	private JTable tabla;

    private ControlCompra control = new ControlCompra(this); 
	private DTOCompra[] comprasTabla; //Areglo de las compras que se van agregando.
	public static int indiceTabla = 0; //Indice del arreglo que incrementra según se agregan objetos osea filas.
	private DAOCompra oDao = new DAOCompra(); //Instancia de la clase DAO que accede a la BD.

	private float fTotal = 0;
	private DecimalFormat formato = new DecimalFormat("$ #,##0.00");

	//Labels y textfields de fecha y folio.
	private JLabel folio = new JLabel("Folio: "); 
	private JTextField texFolio = new JTextField();

	private JLabel dia = new JLabel("D\u00EDa: ");
	private JTextField texDia = new JTextField();

	private JLabel mes = new JLabel ("Mes: ");
	private JTextField texMes = new JTextField();

	private JLabel anio = new JLabel("A\u00F1o: ");
	private JTextField texAnio = new JTextField();

	//arreglo de labels para los datos de la compra.
	private JLabel aDatosProducto[] = {

		new JLabel("Codigo"),
		new JLabel("Nombre"),
		new JLabel("Costo Unitario"),
        new JLabel("Cantidad")

	};

	//arreglo de labels para los datos de la compra.
	private JTextField aTextoProducto[] = {

		new JTextField(), // 0 -> Codigo
		new JTextField(), // 1 -> Descripcion
		new JTextField(), // 2 -> Precio
        new JTextField(), // 3 -> Cantidad

	};

    //botones de pre registro
	private JButton btAceptarP, btCancelarP, btLimpiar;
	private JButton btAgregar, btCancelar;

	//áreas dónde se imprimirá la suma del total de la compra.
	private JLabel total;
	private JTextField texTotal;

	/**
	 * Método constructor de la clase interfaz.
	 */
	public IGUCompras(){

	}

	/**
	 * Función para obtener el panel superior de la vista.
	 * @return panelCompras que es el panel dónde se busca una compra registrada en la BD.
	 */
	public JPanel getPanelCompras(){

		JPanel panelCompras = new JPanel();

		panelCompras.setLayout(new FlowLayout(FlowLayout.CENTER));

		panelCompras.add(getPanelBuscar());
		panelCompras.add(getPanelCompra());		

		return panelCompras;

	}

	/**
	 * Función para obtener el panel dónde se registra un insumo comprado.
	 * @return panelCompras que es el panel con los labels y textfields de registro de compras.
	 */
	public JPanel getPanelCompra(){

		JPanel panelCompras = new JPanel();

		panelCompras.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelCompras.setBorder(BorderFactory.createTitledBorder("Detalles de compra"));

		panelCompras.add(getPanelFecha());
		panelCompras.add(getPanelInsumo());
		panelCompras.add(getPaneltabla());
		panelCompras.add(getPanelBotonesCompra());
		
		panelCompras.setPreferredSize(new Dimension(785, 500));

		return panelCompras;

	} 

	private JTextField campoBuscar = new JTextField();

	/**
	 * Funciuón que devulve a la interfaz el componente de busqueda.
	 * @return panelBuscar que es un pequeño componente con un textfield y un botón de busqueda.
	 */
	public JPanel getPanelBuscar(){

		JPanel panelBuscar = new JPanel();

		panelBuscar.setBorder(BorderFactory.createTitledBorder(""));
		panelBuscar.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel titulo = new JLabel("Compras        ");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 36));
		panelBuscar.add(titulo);

		JLabel buscar = new JLabel("Buscar");
		panelBuscar.add(buscar);

		campoBuscar.setText("Folio");
		campoBuscar.setForeground(new Color(111,111,111));
		campoBuscar.setPreferredSize(new Dimension(200,25));
		panelBuscar.add(campoBuscar);
		campoBuscar.addFocusListener(control);
		campoBuscar.addActionListener(control);
        campoBuscar.setActionCommand("buscarCompra");

		JButton btBuscar = new JButton(new ImageIcon(getClass().getResource("/iconos/lupa (2).png")));
		btBuscar.setPreferredSize(new Dimension(25,25));
		panelBuscar.add(btBuscar);
		btBuscar.addActionListener(control);
        btBuscar.setActionCommand("Buscar");

        panelBuscar.setPreferredSize(new Dimension(785, 55));
        
		return panelBuscar;

	}

	/**
	 * Método que devuelve a la vista un conjunto de textfields que sirven para que un usuario ingrese la fecha de la compra.
	 * @return panelFecha que es un componente para realizar el registro de la fecha.
	 */
	public JPanel getPanelFecha(){

		JPanel panelFecha = new JPanel();

		panelFecha.setLayout(null);

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
		panelFecha.add(texAnio);
		texAnio.setBounds(735,4,40,25);

		panelFecha.setPreferredSize(new Dimension(775, 35));
		
		return panelFecha;

	}

	/**
	 * Función que devuelve un componente que permite agregar una compra a la lista de compras.
	 * @return panelProductos que consta de 3 textfields para llenar los datos del producto.
	 */
	public JPanel getPanelDatosInsumo(){

		JPanel panelProductos = new JPanel();

		panelProductos.setBorder(BorderFactory.createTitledBorder("Datos del insumo: "));

		panelProductos.setLayout(null);

		for (int i = 0; i < aDatosProducto.length; i++){

			panelProductos.add(aDatosProducto[i]);
			panelProductos.add(aTextoProducto[i]);

		}
		
        aDatosProducto[0].setBounds(10, 12, 50, 25);
        aTextoProducto[0].setBounds(10, 35, 50, 25);
        aTextoProducto[0].setHorizontalAlignment(JTextField.RIGHT);
        aTextoProducto[0].addActionListener(control);
        aTextoProducto[0].setActionCommand("buscarInsertar");

        aDatosProducto[1].setBounds(65, 12, 80, 25);
        aTextoProducto[1].setBounds(65, 35, 280, 25);
        aTextoProducto[1].setEnabled(false);

        aDatosProducto[2].setBounds(350, 12, 100, 25);
        aTextoProducto[2].setBounds(350, 35, 100, 25);
        aTextoProducto[2].setHorizontalAlignment(JTextField.RIGHT);

        aDatosProducto[3].setBounds(455, 12, 90, 25);
        aTextoProducto[3].setBounds(455, 35, 90, 25);
        aTextoProducto[3].setHorizontalAlignment(JTextField.RIGHT);

        JButton btBuscar = new JButton(new ImageIcon(getClass().getResource("/iconos/lupa (2).png")));
		btBuscar.setToolTipText("Buscar insumo");
		btBuscar.addActionListener(control);
		btBuscar.setActionCommand("BuscarInsumo");

		JButton btLimpiar = new JButton(new ImageIcon(getClass().getResource("/iconos/borrador (2).png")));
		btLimpiar.setToolTipText("Limpiar campos");
		btLimpiar.addActionListener(control);
		btLimpiar.setActionCommand("limpiarInsumo");
        
        panelProductos.add(btBuscar);
		panelProductos.add(btLimpiar);
		btBuscar.setBounds(550, 35, 25, 25);
		btLimpiar.setBounds(580, 35, 25, 25);

        panelProductos.setPreferredSize(new Dimension(615, 75));

		return panelProductos;

	}

	/**
	 * Función que devuelve a la vista dos botones para agregar o quitar de la lista de compras.
	 * @return panel que consta de dos botones, uno para agregar y uno para quitar una compra de la lista.
	 */
	public JPanel getPanelInsumo(){

		JPanel panel = new JPanel();

		panel.add(getPanelDatosInsumo());

		btAceptarP = new JButton(new ImageIcon(getClass().getResource("/iconos/agregar3.png")));
		btAceptarP.setPreferredSize(new Dimension(55, 55));
		btAceptarP.addActionListener(control);
        btAceptarP.setActionCommand("AgregarInsumo");

        btCancelarP = new JButton(new ImageIcon(getClass().getResource("/iconos/quitar.png")));
        btCancelarP.setPreferredSize(new Dimension(55, 55));
        btCancelarP.addActionListener(control);
        btCancelarP.setActionCommand("Quitar");

		panel.add(btAceptarP);
		panel.add(btCancelarP);

		panel.setPreferredSize(new Dimension(775, 85));
		
		return panel;
	}

	/**
	 * Función que permite recuperar el modelo con el que está diseñado la lista de compras.
	 * @return modeloDTO que es el modelo de la tabla.
	 */
	public DefaultTableModel getModeloDTO(){

		return modeloDTO;

	}

	/**
	 * Función que devuelve a la vista una tabla con las compras que se han ido agregando.
	 * @return panelTabla que consta de una tabla con los productos que se han ido agregando.
	 */
    public JPanel getPaneltabla(){

		JPanel panelTabla = new JPanel();
		tabla = new JTable();

		modeloDTO = new DefaultTableModel();

		modeloDTO.setColumnIdentifiers(new Object[]{"#", "Nombre", "Cantidad", "Costo Unitario", "SubTotal"});

		tabla.setModel(modeloDTO);
		JScrollPane jScroll = new JScrollPane(tabla);

		panelTabla.add(jScroll);

		jScroll.setPreferredSize(new Dimension(755, 230));
		panelTabla.setPreferredSize(new Dimension(775, 240));

		return panelTabla;

	}

	/**
	 * Función que devuelve a la interfáz dos botones para registrar los productos de la lista o desechar la lista.
	 * @return botones que son dos botones para registrar los productos de la lista o desechar la lista.
	 */
	public JPanel getPanelBotonesCompra(){

		JPanel botones = new JPanel();

		btAgregar = new JButton(new ImageIcon(getClass().getResource("/iconos/save.png")));
		btAgregar.setPreferredSize(new Dimension(80, 80));
		btAgregar.addActionListener(control);
        btAgregar.setActionCommand("Registrar");

        btCancelar = new JButton(new ImageIcon(getClass().getResource("/iconos/trash.png")));
		btCancelar.setPreferredSize(new Dimension(80, 80));
		btCancelar.addActionListener(control);
        btCancelar.setActionCommand("Tirar");

        JButton btNuevo = new JButton(new ImageIcon(getClass().getResource("/iconos/borradorGrande.png")));
		btNuevo.setPreferredSize(new Dimension(80, 80));
		btNuevo.setToolTipText("Limpiar campos");
		btNuevo.addActionListener(control);
        btNuevo.setActionCommand("nuevaVenta");
		total = new JLabel("         Total Compra");
		total.setFont(new Font("Tahoma", Font.PLAIN, 36));

		texTotal = new JTextField("$ 0.00");
		texTotal.setHorizontalAlignment(JTextField.RIGHT);
		texTotal.setFont(new Font("Tahoma", Font.PLAIN, 30));
		texTotal.setPreferredSize(new Dimension(100, 40));

		botones.add(btAgregar);
		botones.add(btCancelar);
		botones.add(btNuevo);
		botones.add(total);
		botones.add(texTotal);

		botones.setPreferredSize(new Dimension(775, 90));
		// botones.setBackground(new Color(255,100,255));
		return botones;
	}

		public void setCampoInsumo(DTOInsumo oInsumo) {

		if (oInsumo.getId() != 0){

			aTextoProducto[1].setText(oInsumo.getNombre());//nombre

		} 

	}//setCampoInsumo

	public int getCampoInsumo() throws NumberFormatException {

		return Integer.valueOf(aTextoProducto[0].getText());

	}//getCampoProducto

	public void limpiarCampoInsumo(){
		
		aTextoProducto[0].setText(null);
		aTextoProducto[1].setText(null);
		aTextoProducto[2].setText(null);
		aTextoProducto[3].setText(null);

	}//limpiarCampoProducto


	public void agregarInsumo() throws NumberFormatException, IllegalArgumentException {

		if (!aTextoProducto[0].getText().equals("")) {

			if (Float.valueOf(aTextoProducto[2].getText()) > 0 && Integer.valueOf(aTextoProducto[3].getText()) > 0){

				modeloDTO.addRow(new Object[]{	Integer.valueOf(aTextoProducto[0].getText()), 
											aTextoProducto[1].getText(), 
											Integer.valueOf(aTextoProducto[3].getText()),
											Float.valueOf(aTextoProducto[2].getText()), 
											(Integer.valueOf(aTextoProducto[3].getText()) * Float.valueOf(aTextoProducto[2].getText()))});
					

				fTotal += (Integer.valueOf(aTextoProducto[3].getText()) * Float.valueOf(aTextoProducto[2].getText()));
				texTotal.setText(formato.format(fTotal));

			} else {

				throw new IllegalArgumentException("Error, Las cantidades deben ser mayores a 0");

			}

		} else {

			throw new IllegalArgumentException("Error, primero busque un insumo");

		} 

	}//AgregarInsumo

	public void quitarInsumo() throws IllegalArgumentException, ArrayIndexOutOfBoundsException {

		if (tabla.getSelectedRow() != -1) {
		
				fTotal = fTotal - (Float) tabla.getValueAt(tabla.getSelectedRow(), 4);
				texTotal.setText(formato.format(fTotal));
				modeloDTO.removeRow(tabla.getSelectedRow());

		} else {

			throw new IllegalArgumentException("No se ha seleccionado un ");

		}
	 
	}//quitarProducto

	public DTOCompra generarCompra() throws IllegalArgumentException{

		campoVacio();
		DTOCompra compra = new DTOCompra();
		String sFecha = "";
		Date oFecha;

		try{

			sFecha = texAnio.getText() + "-" + texMes.getText() + "-" + texDia.getText();
		
			oFecha = java.sql.Date.valueOf(sFecha);

			int aIDInsumos[] = new int[modeloDTO.getRowCount()];
			int  aCantidades[] = new int[modeloDTO.getRowCount()];
			float aCostosUnitarios[] = new float[modeloDTO.getRowCount()];

			for (int con = 0; con < modeloDTO.getRowCount(); con++) {
				
				aIDInsumos[con] = (int) tabla.getValueAt(con, 0);
				aCantidades[con] = (int) tabla.getValueAt(con, 2);
				aCostosUnitarios[con] = (float) tabla.getValueAt(con, 3);
			}

			compra.setFechaCompra(sFecha);
			compra.setIDInsumos(aIDInsumos);
			compra.setCantidades(aCantidades);
			compra.setCostosUnitarios(aCostosUnitarios);

			return compra;

		}catch(IllegalArgumentException illEx){

			throw new IllegalArgumentException("Fecha invalida");
		}

	}

	public void campoVacio() throws NullPointerException, IllegalArgumentException {

		if (modeloDTO.getRowCount() == 0) {
			
			throw new IllegalArgumentException("No se han ingresado insumos");

		}

	}

	public void nuevaVenta(){

		limpiarCampoInsumo();

		modeloDTO.setRowCount(0);
		texDia.setText(null);
		texMes.setText(null);
		texAnio.setText(null);
		fTotal = 0.0f;
		texTotal.setText("$ 0.00");
		campoBuscar.setText(null);

		

	}

	public int getCampoBuscar()throws NumberFormatException{

		return Integer.valueOf(campoBuscar.getText());
	}

	public void setCompra(DTOCompra compra){

		for (int con = 0; con < modeloDTO.getRowCount(); con++) {

			fTotal += (float) tabla.getValueAt(con, 4);
		
		}

		texTotal.setText(formato.format(fTotal));

		String [] partes = compra.getFechaCompra().split("-");

		texAnio.setText(partes[0]);
		texMes.setText(partes[1]);
		texDia.setText(partes[2]);
	}
}