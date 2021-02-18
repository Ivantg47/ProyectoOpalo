/**
 * Clase que implementa una interfaz para el modulo de compras.
 * @author Diego Puebla Aldama
 * @author Ivan Tronco
 * @version 2.5
 */

package ProyectoOpalo.igu;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import ProyectoOpalo.control.ControlCompra;
import ProyectoOpalo.dto.DTOCompra;
import ProyectoOpalo.dao.DAOCompra;

public class IGUCompras extends JFrame{

	public DTOCompra compras = new DTOCompra(); //instancia de la clase DTO que se usa para recopilar los datos de la interfaz.
	private DefaultTableModel modelo, modeloDTO; //Modelos para las tablas usadas.
	private JTable tabla;

    private ControlCompra control = new ControlCompra(this); 
	private DTOCompra[] comprasTabla; //Areglo de las compras que se van agregando.
	public static int indiceTabla = 0; //Indice del arreglo que incrementra según se agregan objetos osea filas.
	private DAOCompra oDao = new DAOCompra(); //Instancia de la clase DAO que accede a la BD.

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
		new JLabel("Descripcion"),
		new JLabel("Precio Unitario"),
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
		panelCompras.add(getPanelTablaCompras());
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
        btBuscar.setActionCommand("buscarCompra");

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
		btBuscar.setActionCommand("buscarProducto");

		JButton btLimpiar = new JButton(new ImageIcon(getClass().getResource("/iconos/borrador (2).png")));
		btLimpiar.setToolTipText("Limpiar campos");
		btLimpiar.addActionListener(control);
		btLimpiar.setActionCommand("limpiarProducto");
        
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
        btAceptarP.setActionCommand("Verificar");

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
    public JPanel getPanelTablaCompras(){

		JPanel panelTabla = new JPanel();

		JTable tablaCompras = new JTable();

		modeloDTO = new DefaultTableModel();

		modeloDTO.setColumnIdentifiers(new Object[]{"#", "Nombre", "Cantidad", "Costo Unitario", "SubTotal"});

		tablaCompras.setModel(modeloDTO);
		JScrollPane jScroll = new JScrollPane(tablaCompras);

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


		total = new JLabel("         Total Compra");
		total.setFont(new Font("Tahoma", Font.PLAIN, 36));

		texTotal = new JTextField("$ 0.00");
		texTotal.setHorizontalAlignment(JTextField.RIGHT);
		texTotal.setFont(new Font("Tahoma", Font.PLAIN, 30));
		texTotal.setPreferredSize(new Dimension(200, 40));

		botones.add(btAgregar);
		botones.add(btCancelar);
		botones.add(total);
		botones.add(texTotal);

		botones.setPreferredSize(new Dimension(775, 90));
		// botones.setBackground(new Color(255,100,255));
		return botones;
	}

	/**
	 * Función que define la estructura de la tabla de lista de compras.
	 * @return panel es un panel con la tabla, almenos la estructura básica.
	 */
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

	/**
	 * Función que devuelve un objeto compra con los datos que el usuario tecleó en la interfaz.
	 * @return compra que es una instancia de DTOCompra con los atributos llenos excepto el idInsumo que se recupera luego en la BD.
	 */
    public DTOCompra getCampos(){

        DTOCompra compra = new DTOCompra();
		String fecha;

		fecha = texAnio.getText() + "-" +  texMes.getText() + "-" + texDia.getText();

        compra.setNombre(aTextoProducto[0].getText());// Descripcion
        compra.setTotal(Float.valueOf(aTextoProducto[1].getText()));// Precio
        compra.setCantidad(Float.valueOf(aTextoProducto[2].getText()));//Cantidad
		compra.setFechaCompra(fecha);//Total

        return compra;

    }
	
	/**
	 * Método que establece el total y lo despliega en la sección correspondiente.
	 * @param fTotal recibe el total de la clase control.
	 */
	public void setTotal(Float fTotal){
		texTotal.setText("$" + String.valueOf(fTotal));
	}

	/**
	 * Función que devuelve la lista de las compras.
	 * @param aCompras recibe las compras de la control.
	 * @return ticket que es la lista de compras.
	 */
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

	/**
	 * Método que agrega una compra a la tabla.
	 * @param compra recibe la compra a gregar de la clase control.
	 */
	public void agregarCompraTabla(DTOCompra compra){

		comprasTabla[indiceTabla] = compra;

	}

	private DefaultTableModel oModelo;
	private JTable oTabla;

	/**
	 * Función que devuelve la lista de las compras.
	 * @return oPanel que se refiere a la lista de compras.
	 */
	public JPanel getPanelInventario(){

		JPanel oPanel = new JPanel();
		DAOCompra oDAO = new DAOCompra();

		oPanel.setBorder(BorderFactory.createTitledBorder("Pre Registro"));
		oModelo = new DefaultTableModel();
		oModelo.setColumnIdentifiers(new Object[]{"Numero", "Nombre", "Cantidad", "Costo"});

		oTabla = new JTable(oModelo);
		JScrollPane jScroll = new JScrollPane(oTabla);

		jScroll.setViewportView(oTabla);

		oPanel.add(jScroll, BorderLayout.CENTER);
		return oPanel;

	}

	/**
	 * Método que muestra los datos de la compra que se buscó desde el panel de consulta.
	 * @param compras recibe la compra del método buscar.
	 */
	public void mostrarDatosBusqueda(DTOCompra compras){

		JOptionPane.showMessageDialog(null,getTablaConsulta(compras));
		
	}

	/**
	 * Función que establece la forma de presentar la compra buscada.
	 * @param compras Recibe la compra buscada.
	 * @return panelTabla que es un panel con los datos de la compra consultada.
	 */
	public JPanel getTablaConsulta(DTOCompra compras){

		JPanel panelTabla = new JPanel();

		JTable tablaConsulta = new JTable();

		String [] nombre = {
                "ID", "Insumo", "Cantidad", "Costo Unitario", "Fecha Compra", "Total"
        };

		tablaConsulta.setModel(new DefaultTableModel(
            
            new Object [][] {

            	{"Insumo", "Cantidad", "Costo Unitario", "Fecha Compra", "Total"},
                {compras.getNombre(), compras.getCantidad(), compras.getTotal(), compras.getFechaCompra(), compras.getFinal()}

            }, nombre
            
        ));

		panelTabla.add(tablaConsulta);
		panelTabla.setBorder(BorderFactory.createTitledBorder("Consulta Compras"));

		return panelTabla;

	}

	/**
	 * Función que devuelve el modelo de la tabla de inventario.
	 * @return oModelo que es un DefaultTableModel.
	 */
	public DefaultTableModel getModelo(){

		return oModelo;

	}

	/**
	 * Método que limpia los campos del texto introducido por el usuario.
	 */
	public void limpiarProducto(){

		aTextoProducto[0].setText(null);
		aTextoProducto[1].setText(null);
		aTextoProducto[2].setText(null);

	}

	/**
	 * Función que lee el dato que se va a buscar y lo devuelve a la clase control.
	 * @return idCompra es el número que quiere buscar el usuario.
	 */
	public int leerDatoBuscar(){
		
		int idCompra = Integer.valueOf(campoBuscar.getText());

		return idCompra;

	}
	/*
	public static void main(String[] args) {
		
		IGUCompras prueba = new IGUCompras();
	}
*/
}