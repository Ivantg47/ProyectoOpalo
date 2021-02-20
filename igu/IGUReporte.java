/**
 * Clase que implementa una interfaz para el modulo de reportes.
 * @author Ivan Tronco
 * @version 1.0
 */

package ProyectoOpalo.igu;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.text.DecimalFormat;
import ProyectoOpalo.control.ControlReportes;

public class IGUReporte extends JFrame{

	/**
     * Atributo que crea la clase control de repotes.
     */
 	private ControlReportes control = new ControlReportes(this);

	/**
     * Atributo que guarda la tabla de reportes.
     */
	private JTable tabla;

	/**
     * Atributo que determina el modelo de la tabla.
     */
 	private DefaultTableModel modelo;

 	/**
     * Atributo que genera un campo de texto para la fecha.
     */
 	private JTextField campoInicio;

 	/**
     * Atributo que genera un campo de texto para la fecha.
     */
 	private JTextField campoFinal;

 	/**
     * Atributo que genera un campo de texto para el total de venta.
     */
 	private JTextField campoTotal;

 	/**
     * Atributo que muestra el periodo del reporte.
     */
 	private JLabel reporte;

 	/**
     * Atributo que determina el formato del campoTotal.
     */
 	private DecimalFormat formato = new DecimalFormat("$ #,##0.00");


 	/**
     * Constructor, sin parametros.
     */
	public IGUReporte(){

	}

	/**
     * Metodo para generar el panel de la interfas de insumo.
     * @return panel de igu reporte
     */
	public JPanel getIGUReporte(){

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(getPanelTitulo(), BorderLayout.NORTH);
		panel.add(getPanelTabla(), BorderLayout.CENTER);
		panel.add(getPanelGeneral(), BorderLayout.WEST);

		return panel;

	}

	/**
     * Metodo para generar el panel del titulo de reporte.
     * @return panel de titulo
     */
	public JPanel getPanelTitulo(){

		JPanel panel = new JPanel();

		panel.setBorder(BorderFactory.createTitledBorder(""));
		//panel.setLayout(new FlowLayout());

		JLabel titulo = new JLabel("Reporte de Ventas");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 36));
		panel.add(titulo);

		return panel;

	}

	/**
     * Metodo para generar el panel de la tabla de reportes.
     * @return panel de tabla
     */
	public JPanel getPanelTabla(){

		JPanel panel = new JPanel();

		panel.setBorder(BorderFactory.createTitledBorder("Reporte"));
		panel.setLayout(new FlowLayout());

		reporte = new JLabel();
		reporte.setPreferredSize(new Dimension(500,35));
		reporte.setFont(new Font("Tahoma", Font.PLAIN, 24));
		reporte.setHorizontalAlignment(JLabel.CENTER);

		panel.add(reporte);
		//creacion de la tabla
		modelo = new DefaultTableModel();
		modelo.setColumnIdentifiers(new Object[]{"Codigo", "Nombre", "Descripcion", "Existencias"});
		tabla = new JTable(modelo);
        
		JScrollPane jScroll = new JScrollPane(tabla);
		jScroll.setPreferredSize(new Dimension(500, 370));

		panel.add(jScroll);

		JLabel venta = new JLabel("     Total Venta:");
		venta.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(venta);
		campoTotal = new JTextField(formato.format(0));
		campoTotal.setPreferredSize(new Dimension(200,40));
		campoTotal.setHorizontalAlignment(JTextField.RIGHT);
		campoTotal.setFont(new Font("Tahoma", Font.PLAIN, 24));

		panel.add(campoTotal);

		JButton btImprimir = new JButton(new ImageIcon(getClass().getResource("/iconos/impresora (2).png")));
		btImprimir.addActionListener(control);
		btImprimir.setActionCommand("imprimir");
		panel.add(btImprimir);

		return panel;

	}
	
	/**
     * Metodo para generar el panel que alberga el panel consulta.
     * @return panel
     */	
	public JPanel getPanelGeneral(){

		JPanel panel = new JPanel();

		panel.add(getPanelConsulta(), BorderLayout.NORTH);
		panel.setPreferredSize(new Dimension(200, 670));

		return panel;

	}

	/**
     * Metodo para generar el panel para introducir datos.
     * @return panel de consulta
     */
	public JPanel getPanelConsulta(){

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 1, 50, 10));

		panel.add(new JLabel("Fecha inicio:"));
		campoInicio = new JTextField("yyyy-mm-dd");
		campoInicio.setForeground(new Color(111,111,111));
		campoInicio.addFocusListener(control);
		campoInicio.setActionCommand("fecahaInicio");

		panel.add(campoInicio);

		panel.add(new JLabel("Fecha final:"));
		campoFinal = new JTextField("yyyy-mm-dd");
		campoFinal.setForeground(new Color(111,111,111));
		campoFinal.addFocusListener(control);
		campoFinal.setActionCommand("fecahaFinal");
	
		panel.add(campoFinal);

		panel.setBorder(BorderFactory.createTitledBorder("Datos Reporte"));

		JButton consulta = new JButton("Consultar");
		consulta.addActionListener(control);
		consulta.setActionCommand("consulta");
		panel.add(consulta);
		panel.setPreferredSize(new Dimension(190, 250));

		return panel;

	}

	/**
     * Metodo para obtener el modelo de la tabla.
     * @return modelo de la tabla
     */
	public DefaultTableModel getModelo(){

		return modelo;

	}

	/**
     * Metodo para obtener la fecha inicial.
     * @return fecha inicial
     */
	public String getFechaInicio(){

		return campoInicio.getText();

	}
	
	/**
     * Metodo para obtener la fecha final.
     * @return fecha final
     */
	public String getFechaFinal(){

		return campoFinal.getText();

	}

	/**
     * Metodo para obtener el total monetario de las ventas del reporte.
     */
	public void generarTotal(){

		float total = 0;

		for (int con = 0; con < modelo.getRowCount(); con++) {

			total += (float) tabla.getValueAt(con, 3);
			
		}

		campoTotal.setText(formato.format(total));

		limpiarCampos();

	}	

	/**
     * Metodo para limpiar los campos de las fechas.
     */
	public void limpiarCampos(){

		campoInicio.setText("yyyy-mm-dd");
		campoInicio.setForeground(new Color(111,111,111));
		campoFinal.setText("yyyy-mm-dd");
		campoFinal.setForeground(new Color(111,111,111));

	}

	/**
     * Metodo para limpiar la tabla reporte.
     */
	public void limpiarReporte(){

		modelo.setRowCount(0);

	}

	/**
     * Metodo para indicar el perido del reporte.
     */
	public void setReporte(String periodo){

		reporte.setText(periodo);

	}

}