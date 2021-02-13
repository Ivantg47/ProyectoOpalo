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

import ProyectoOpalo.dto.DTOInsumo;
import ProyectoOpalo.control.ControlInsumo;

public class IGUInsumo extends JFrame{

	public static final String ICONOS = "/iconos/"; 

	private JComboBox<String> oComboUnidadMedida;

	private JTextField aCamposTextoDatos[] = {
		new JTextField(),
		new JTextField(),
	};

	private JTextField aCamposTextoExistencias[] = {
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

	private JTextField oCampoTxBuscar;

	
	private ControlInsumo oControl = new ControlInsumo(this);

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
		oPanel.add(getPanelBuscar(), BorderLayout.NORTH);
		oPanel.add(getPanelInventario(), BorderLayout.CENTER);
		oPanel.add(getPanelDatos(), BorderLayout.WEST);

		return oPanel;

	}

	public JPanel getPanelBuscar(){

		JPanel oPanel = new JPanel();

		oPanel.setBorder(BorderFactory.createTitledBorder(""));
		oPanel.setLayout(new FlowLayout());

		JLabel oEtiquetaInsumos = new JLabel("Insumos");
		oEtiquetaInsumos.setFont(new Font("Tahoma", Font.PLAIN, 36));
		oPanel.add(oEtiquetaInsumos);

		JLabel oEtiquetaBuscar = new JLabel("Buscar");
		oPanel.add(oEtiquetaBuscar);

		oCampoTxBuscar = new JTextField();
		oCampoTxBuscar.setText("Codigo/aNombres");
		oCampoTxBuscar.setPreferredSize(new Dimension(200,25));
		oPanel.add(oCampoTxBuscar);

		JButton oBotonBuscar = new JButton(new ImageIcon(getClass().getResource("/iconos/lupa.png")));
		oBotonBuscar.addActionListener(oControl);
		oBotonBuscar.setActionCommand("btBuscar");
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

		for (int eContador = 0; eContador < aCamposTextoExistencias.length; eContador++){

			oPanel.add(aCamposTextoExistencias[eContador]);

		}

		oPanel.setBorder(BorderFactory.createTitledBorder("Existencias"));

		return oPanel;

	}
	

	public JPanel getPanelBotonesCrud(){

		JPanel oPanel = new JPanel();

		oPanel.setLayout(new FlowLayout());

		JButton btAgregar = new JButton(new ImageIcon(getClass().getResource("/iconos/agregar.png")));
		btAgregar.addActionListener(oControl);
		btAgregar.setActionCommand("btAgregar");
		btAgregar.setToolTipText("Agregar");

		JButton btEliminar = new JButton(new ImageIcon(getClass().getResource("/iconos/eliminar.png")));
		btEliminar.addActionListener(oControl);
		btEliminar.setActionCommand("btEliminar");
		btEliminar.setToolTipText("Eliminar");

		JButton btModificar = new JButton(new ImageIcon(getClass().getResource("/iconos/modificar.png")));
		btModificar.addActionListener(oControl);
		btModificar.setActionCommand("btModificar");
		btModificar.setToolTipText("Modificar");

		oPanel.add(btAgregar);
		oPanel.add(btEliminar);
		oPanel.add(btModificar);

		return oPanel;
	}

	public DTOInsumo leerDTO(){

		String sNombre, sUnidadMedida;
		float  fExistenciaActual, fExistenciaMinima, fExistenciaMaxima;
		DTOInsumo oDTOInsumo;

		sNombre = aCamposTextoDatos[1].getText();
		sUnidadMedida = oComboUnidadMedida.getSelectedItem().toString();
		fExistenciaActual = Float.valueOf(aCamposTextoExistencias[0].getText());
		fExistenciaMinima = Float.valueOf(aCamposTextoExistencias[1].getText());
		fExistenciaMaxima = Float.valueOf(aCamposTextoExistencias[2].getText());

		oDTOInsumo = new DTOInsumo(sNombre, sUnidadMedida, fExistenciaActual, fExistenciaMinima, fExistenciaMaxima);

		return oDTOInsumo;
	}


	public int getID(){

		return Integer.valueOf(oCampoTxBuscar.getText());

	}


	public void mostrarDTO(DTOInsumo oInsumo){

		aCamposTextoDatos[0].setText( String.valueOf(oInsumo.getId()) );
		aCamposTextoDatos[1].setText( oInsumo.getNombre() );
		oComboUnidadMedida.setSelectedItem( String.valueOf(oInsumo.getUnidadMedida()) );
		aCamposTextoExistencias[0].setText( String.valueOf(oInsumo.getExistenciaActual()) );
		aCamposTextoExistencias[1].setText( String.valueOf(oInsumo.getExistenciaMinima()) );
		aCamposTextoExistencias[2].setText( String.valueOf(oInsumo.getExistenciaMaxima()) );

	}	
		 
}