/**
 * Clase que implementa una interfaz para el modulo de insumos.
 * @author Ivan Tronco
 * @author Frida Hernandez
 * @version 1.0
 */

package ProyectoOpalo.igu;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class IGUInsumo extends JFrame{

	public static final String ICONOS = "/iconos/"; //ruta para la carpeta de imagenes

	private JComboBox<String> oComboUnidadMedida;

	private JTextField aCamposTextoDatos[] = {
		new JTextField(),
		new JTextField(),
	};

	private JTextField aCamposTextoDatosExistencias[] = {
		new JTextField(),
		new JTextField(),
		new JTextField(),
	};

	private JLabel aEtiquetasDatos[] = {
		new JLabel("Codigo"),
		new JLabel("Nombres"),
		new JLabel("Unidad medida"),
	};

	private JLabel aEtiquetasExistencias[] = {
		new JLabel("Actual"),
		new JLabel("M\u00EDnima"),
		new JLabel("M\u00E1xima"),
	};

	public IGUInsumo(){
/*
		super("Insumo");

		add(getIGUInsumo());

		setSize(700, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
*/
	}

	public JPanel getIGUInsumo(){

		JPanel oPanel = new JPanel();
		oPanel.setLayout(new BorderLayout());
		oPanel.add(getoPaneloEtiquetaBuscar(), BorderLayout.NORTH);
		oPanel.add(getPanelInventario(), BorderLayout.CENTER);
		oPanel.add(getPanelDatos(), BorderLayout.WEST);

		return oPanel;

	}

	public JPanel getoPaneloEtiquetaBuscar(){

		JPanel oPanel = new JPanel();

		oPanel.setBorder(BorderFactory.createTitledBorder(""));
		oPanel.setLayout(new FlowLayout());

		JLabel oEtiquetaInsumos = new JLabel("Insumos");
		oEtiquetaInsumos.setFont(new Font("Tahoma", Font.PLAIN, 36));
		oPanel.add(oEtiquetaInsumos);

		JLabel oEtiquetaBuscar = new JLabel("Buscar");
		oPanel.add(oEtiquetaBuscar);

		JTextField oCampoTxBuscar = new JTextField();
		oCampoTxBuscar.setText("Codigo/aNombres");
		oCampoTxBuscar.setPreferredSize(new Dimension(200,25));
		oPanel.add(oCampoTxBuscar);

		JButton oBotonBuscar = new JButton(new ImageIcon(getClass().getResource("/iconos/lupa.png")));
		oBotonBuscar.setPreferredSize(new Dimension(32,32));
		oPanel.add(oBotonBuscar);

		return oPanel;

	}

	public JPanel getPanelInventario(){

		JPanel oPanel = new JPanel();

		oPanel.setBorder(BorderFactory.createTitledBorder("Inventario"));
		//oPanel.setLayout(new GridLayout(1,4));

		//creacion de la oTabla
		JTable oTabla = new JTable();
		JScrollPane jScroll = new JScrollPane(oTabla);

		String [] aNombres = {
                "Codigo", "Nombre", "Cantidad"
            };

		oTabla.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            }, aNombres
            
        ));

        jScroll.setViewportView(oTabla);

		oPanel.add(jScroll, BorderLayout.CENTER);

		JButton btFlechaDer = new JButton(new ImageIcon(getClass().getResource("/iconos/flechaDer.png")));
		btFlechaDer.setToolTipText("Siguiente");		
		JButton btFlechaDobleDer = new JButton(new ImageIcon(getClass().getResource("/iconos/flechaDobleDer.png")));
		btFlechaDobleDer.setToolTipText("Fin");		
		JButton btFlechaDobleIzq = new JButton(new ImageIcon(getClass().getResource("/iconos/flechaDobleIzq.png")));
		btFlechaDobleIzq.setToolTipText("Inicio");
		JButton btFlechaIzq = new JButton(new ImageIcon(getClass().getResource("/iconos/flechaIzq.png")));
		btFlechaIzq.setToolTipText("Anterior");
		

		oPanel.add(btFlechaDobleIzq, BorderLayout.SOUTH);
		oPanel.add(btFlechaIzq, BorderLayout.SOUTH);
		oPanel.add(btFlechaDer, BorderLayout.SOUTH);
		oPanel.add(btFlechaDobleDer, BorderLayout.SOUTH);


		return oPanel;

	}
	
	public JPanel getPanelDatos(){

		JPanel oPanel = new JPanel();

		oPanel.setLayout(new GridLayout(3, 1, 50, 10));

		oPanel.add(getPanelCombo());
		oPanel.add(getPanelExistencias());
		oPanel.add(getPanelBotonesCrud());
		

		oPanel.setBorder(BorderFactory.createTitledBorder("Datos insumo"));


		return oPanel;

	}

	public JPanel getPanelCombo(){

		JPanel oPanel = new JPanel();

		oComboUnidadMedida = new JComboBox<String>();

		oPanel.setLayout(new GridLayout(6, 1));

		for (int i = 0; i < aCamposTextoDatos.length; i++){
			oPanel.add(aEtiquetasDatos[i]);
			oPanel.add(aCamposTextoDatos[i]);
		}

		oPanel.add(aEtiquetasDatos[aEtiquetasDatos.length - 1]);
		oPanel.add(oComboUnidadMedida);

		oComboUnidadMedida.addItem("Miligramo");
        oComboUnidadMedida.addItem("Gramo");
        oComboUnidadMedida.addItem("Kilogramo");
        oComboUnidadMedida.addItem("Mililitro");
        oComboUnidadMedida.addItem("Litro");
        oComboUnidadMedida.addItem("Onza");
        oComboUnidadMedida.addItem("Galon");
        oComboUnidadMedida.addItem("Pieza");

		return oPanel;

	}

	public JPanel getPanelExistencias(){

		JPanel oPanel = new JPanel();

		oPanel.setLayout(new GridLayout(2, 3, 10, 0));

		for (int eContador = 0; eContador < aEtiquetasExistencias.length; eContador++){

			oPanel.add(aEtiquetasExistencias[eContador]);
			
		}

		for (int eContador = 0; eContador < aCamposTextoDatosExistencias.length; eContador++){

			oPanel.add(aCamposTextoDatosExistencias[eContador]);

		}

		oPanel.setBorder(BorderFactory.createTitledBorder("Existencias"));

		return oPanel;

	}
	

	public JPanel getPanelBotonesCrud(){

		JPanel oPanelBotones = new JPanel();

		oPanelBotones.setLayout(new FlowLayout());

		JButton btAgregar = new JButton(new ImageIcon(getClass().getResource("/iconos/agregar.png")));
		btAgregar.setToolTipText("Agregar");

		JButton btEliminar = new JButton(new ImageIcon(getClass().getResource("/iconos/eliminar.png")));
		btEliminar.setToolTipText("Eliminar");

		JButton btModificar = new JButton(new ImageIcon(getClass().getResource("/iconos/modificar.png")));
		btModificar.setToolTipText("Modificar");

		oPanelBotones.add(btAgregar);
		oPanelBotones.add(btEliminar);
		oPanelBotones.add(btModificar);

		return oPanelBotones;
	}
}