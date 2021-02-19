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

public class IGUReporte extends JFrame{

	/**
     * Atributo que determina el modelo de la tabla.
     */
	private JTable tabla;

	/**
     * Atributo que determina el modelo de la tabla.
     */
 	private DefaultTableModel modelo;

 	/**
     * Atributo que determina el modelo de la tabla.
     */
 	private JTextField campoInicio;

 	/**
     * Atributo que determina el modelo de la tabla.
     */
 	private JTextField campoFinal;

 	/**
     * Atributo que determina el modelo de la tabla.
     */
 	private JTextField campoTotal;

 	/**
     * Atributo que determina el modelo de la tabla.
     */
 	private DecimalFormat formato = new DecimalFormat("$ #,##0.00");



	public IGUReporte(){

	}

	public JPanel getIGUReporte(){

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(getPanelTitulo(), BorderLayout.NORTH);
		panel.add(getPanelTabla(), BorderLayout.CENTER);
		panel.add(getPanelGeneral(), BorderLayout.WEST);

		return panel;

	}

	public JPanel getPanelTitulo(){

		JPanel panel = new JPanel();

		panel.setBorder(BorderFactory.createTitledBorder(""));
		//panel.setLayout(new FlowLayout());

		JLabel titulo = new JLabel("Reporte de Ventas");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 36));
		panel.add(titulo);

		return panel;

	}

	public JPanel getPanelTabla(){

		JPanel panel = new JPanel();

		panel.setBorder(BorderFactory.createTitledBorder("Reporte"));
		panel.setLayout(new FlowLayout());

		//creacion de la tabla
		modelo = new DefaultTableModel();
		modelo.setColumnIdentifiers(new Object[]{"Codigo", "Nombre", "Descripcion", "Existencias"});
		tabla = new JTable(modelo);
        
		JScrollPane jScroll = new JScrollPane(tabla);


		panel.add(jScroll);

		JLabel venta = new JLabel("     Ventas semanal");
		venta.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(venta);
		campoTotal = new JTextField();
		campoTotal.setPreferredSize(new Dimension(200,40));
		panel.add(campoTotal);


		JButton btImprimir = new JButton(new ImageIcon(getClass().getResource("/iconos/impresora (2).png")));
		panel.add(btImprimir);
		return panel;

	}
	
	public JPanel getPanelGeneral(){

		JPanel panel = new JPanel();

		panel.add(getPanelConsulta(), BorderLayout.NORTH);
		panel.setPreferredSize(new Dimension(200, 670));

		return panel;

	}

	public JPanel getPanelConsulta(){

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 1, 50, 10));

		panel.add(new JLabel("Fecha inicio:"));
		campoInicio = new JTextField("yyyy-mm-dd");
		campoInicio.setForeground(new Color(111,111,111));
		// campoInicio.addFocusListener(control);
		campoInicio.setActionCommand("fecahaInicio");

		panel.add(campoInicio);

		panel.add(new JLabel("Fecha final:"));
		campoFinal = new JTextField("yyyy-mm-dd");
		campoFinal.setForeground(new Color(111,111,111));
		// campoFinal.addFocusListener(control);
		campoFinal.setActionCommand("fecahaFinal");
	
		panel.add(campoFinal);

		panel.setBorder(BorderFactory.createTitledBorder("Datos Reporte"));

		JButton consulta = new JButton("Consultar");
		// consulta.addActionCommand(control);
		consulta.setActionCommand("consulta");
		panel.add(consulta);
		panel.setPreferredSize(new Dimension(190, 250));

		return panel;

	}

	public DefaultTableModel getModelo(){

		return modelo;

	}
	
}