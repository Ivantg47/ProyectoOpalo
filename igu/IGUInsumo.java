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
import ProyectoOpalo.dao.DAOInsumo;
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
		new JLabel("Nombre"),
		new JLabel("Unidad medida"),
	};

	private JLabel aEtiquetasExistencias[] = {
		new JLabel("Actual"),
		new JLabel("M\u00EDnima"),
		new JLabel("M\u00E1xima"),
	};

	private JTextField oCampoTxBuscar;

	private DefaultTableModel oModelo;
 	private JTable oTabla;
	
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
		DAOInsumo oDAO = new DAOInsumo();

		oPanel.setBorder(BorderFactory.createTitledBorder("Inventario"));
		oModelo = new DefaultTableModel();
		oModelo.setColumnIdentifiers(new Object[]{"Codigo", "Nombre", "Unidad", "Existencias"});
		//oPanel.setLayout(new GridLayout(1,4));

		//creacion de la oTabla
		oTabla = new JTable(oModelo);
		JScrollPane jScroll = new JScrollPane(oTabla);

		oDAO.getTabla(oModelo);

		jScroll.setViewportView(oTabla);

		oPanel.add(jScroll, BorderLayout.CENTER);

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

		String sNombre = "", sUnidadMedida ;
		float  fExistenciaActual, fExistenciaMinima, fExistenciaMaxima;
		DTOInsumo oDTOInsumo;

		//if (!isNumeric(aCamposTextoDatos[1].getText())){

			sNombre = aCamposTextoDatos[1].getText();
			sUnidadMedida = oComboUnidadMedida.getSelectedItem().toString();
			fExistenciaActual = Float.valueOf(aCamposTextoExistencias[0].getText());
			fExistenciaMinima = Float.valueOf(aCamposTextoExistencias[1].getText());
			fExistenciaMaxima = Float.valueOf(aCamposTextoExistencias[2].getText());

		/*} else{

			JOptionPane.showMessageDialog(null, "Error. Nombre numerico, ingresar nuevamente.");

		}
		*/

		oDTOInsumo = new DTOInsumo(sNombre, sUnidadMedida, fExistenciaActual, fExistenciaMinima, fExistenciaMaxima);

		

		return oDTOInsumo;
	}


	public int getID(){

		int eID = 0;

		if(oCampoTxBuscar.getText().compareTo("") != 0){

			eID = Integer.valueOf(oCampoTxBuscar.getText());

		} 	

		return eID;

	}


	public void mostrarDTO(DTOInsumo oInsumo){

		aCamposTextoDatos[0].setText( String.valueOf(oInsumo.getId()) );
		aCamposTextoDatos[1].setText( oInsumo.getNombre() );
		oComboUnidadMedida.setSelectedItem( String.valueOf(oInsumo.getUnidadMedida()) );
		aCamposTextoExistencias[0].setText( String.valueOf(oInsumo.getExistenciaActual()) );
		aCamposTextoExistencias[1].setText( String.valueOf(oInsumo.getExistenciaMinima()) );
		aCamposTextoExistencias[2].setText( String.valueOf(oInsumo.getExistenciaMaxima()) );

	}	

	public void limpiarCamposTexto() {

       for(int eContador = 0; eContador < aCamposTextoDatos.length; eContador++) {

        	aCamposTextoDatos[eContador].setText(null);

       }

       for(int eContador = 0; eContador < aCamposTextoExistencias.length; eContador++) {

        	aCamposTextoExistencias[eContador].setText(null);

       }

       oCampoTxBuscar.setText(null);
       oComboUnidadMedida.setSelectedItem( null );
   }

   public boolean camposVacios(){

   		boolean bCamposVacios = false;
   		String sMensaje = "Error. Llenar campos vacios: ";
	
   		if (aCamposTextoDatos[1].getText().compareTo("") == 0){

   			bCamposVacios = true;
   			sMensaje += "Nombre ";

   		}

   		if (oComboUnidadMedida.getSelectedItem() == null){

   			bCamposVacios = true;
   			sMensaje += "Unidad medida ";

   		}

		for(int ePos = 0; ePos < aCamposTextoExistencias.length; ePos++) {

			if (aCamposTextoExistencias[ePos].getText().compareTo("") == 0){

				bCamposVacios = true;

				sMensaje += "Existencia ";
				sMensaje += aEtiquetasExistencias[ePos].getText();
				sMensaje += " ";

			}

       	}

   		if (bCamposVacios == true){

   			JOptionPane.showMessageDialog(null, sMensaje);
   		}



   		return bCamposVacios;
   }

   	public DefaultTableModel getModelo(){

		return oModelo;

	}

   		 
}