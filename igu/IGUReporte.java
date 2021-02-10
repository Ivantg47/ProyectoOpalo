/**
 * Clase que implementa una interfaz para el modulo de reportes.
 * @author Ivan Tronco
 * @version 1.0
 */

package ProyectoOpalo.igu;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class IGUReporte extends JFrame{

	public static final String ICONOS = "/iconos/"; //ruta para la carpeta de imagenes
	private JComboBox<String> combo1;

	private JLabel etiquetas[] = {
		new JLabel("Año"),
		new JLabel("Periodo"),
		new JLabel("Semana"),
	};

	public IGUReporte(){
/*
		super("Insumo");

		add(getIGUReporte());

		setSize(700, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
*/
	}

	public JPanel getIGUReporte(){

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(getPanel1(), BorderLayout.NORTH);
		panel.add(getPanel2(), BorderLayout.CENTER);
		panel.add(getPanelLayoutGeneral(), BorderLayout.WEST);

		return panel;

	}

	public JPanel getPanel1(){

		JPanel panel = new JPanel();

		panel.setBorder(BorderFactory.createTitledBorder(""));
		//panel.setLayout(new FlowLayout());

		JLabel titulo = new JLabel("Reporte de Ventas");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 36));
		panel.add(titulo);

		return panel;

	}

	public JPanel getPanel2(){

		JPanel panel = new JPanel();

		panel.setBorder(BorderFactory.createTitledBorder("Reporte"));
		panel.setLayout(new FlowLayout());

		//creacion de la tabla
		JTable tabla = new JTable();
		JScrollPane jScroll = new JScrollPane(tabla);

		String [] nombre = {
                "Producto", "Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes"
                , "Sabado", "Total Unidades", "Total Venta"
            };

		tabla.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            }, nombre
            
        ));

        jScroll.setViewportView(tabla);

		panel.add(jScroll);

		JLabel venta = new JLabel("Ventas semanal");
		venta.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(venta);
		JTextField campo = new JTextField();
		campo.setPreferredSize(new Dimension(200,40));
		panel.add(campo);


		JButton btImprimir = new JButton(new ImageIcon(ICONOS + "impresora (2).png"));
		panel.add(btImprimir);
		return panel;

	}
	
	public JPanel getPanelLayoutGeneral(){

		JPanel panel = new JPanel();

		panel.add(getPanelLayout(), BorderLayout.NORTH);

		return panel;

	}

	public JPanel getPanelLayout(){

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(7, 1, 50, 10));

		panel.add(new JLabel("A\u00F1o"));
		panel.add(new JTextField());

		panel.add(new JLabel("Periodo"));

		JComboBox combo1 = new JComboBox<String>();
		combo1.addItem("Diario");
        combo1.addItem("Semanal");
        combo1.addItem("Mensual");
        combo1.addItem("Anual");
        panel.add(combo1);

        panel.add(new JLabel("Semana"));

		JComboBox combo2 = new JComboBox<String>();

		for (int i = 1; i <= 52; i++) {
			combo2.addItem(i);
		}
		
        panel.add(combo2);


		panel.setBorder(BorderFactory.createTitledBorder("Datos Reporte"));

		JButton consulta = new JButton("Consultar");
		panel.add(consulta);

		return panel;

	}
/*
	public static void main(String[] args) {
		IGUReporte ventana = new IGUReporte();
		
	}
*/	
}