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
		oPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel oEtiquetaInsumos = new JLabel("Insumos        ");
		oEtiquetaInsumos.setFont(new Font("Tahoma", Font.PLAIN, 36));
		oPanel.add(oEtiquetaInsumos);

		JLabel oEtiquetaBuscar = new JLabel("Buscar");
		oPanel.add(oEtiquetaBuscar);

		oCampoTxBuscar = new JTextField();
		oCampoTxBuscar.setText("Codigo/Nombre");
		oCampoTxBuscar.setForeground(new Color(111,111,111));
		oCampoTxBuscar.setPreferredSize(new Dimension(200,25));
		oCampoTxBuscar.addFocusListener(oControl);
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

		oPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0,10));

		oPanel.add(getPanelCombo());
		oPanel.add(getPanelExistencias());
		oPanel.add(getPanelBotonesCrud());
		oPanel.setPreferredSize(new Dimension(230, 670));

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

		aCamposTextoDatos[0].setEnabled(false);

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

        oPanel.setPreferredSize(new Dimension(210, 150));

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
		oPanel.setPreferredSize(new Dimension(210, 80));

		return oPanel;

	}
	

	public JPanel getPanelBotonesCrud(){

		JPanel oPanel = new JPanel();

		oPanel.setLayout(new FlowLayout());

		JButton btAgregar = new JButton(new ImageIcon(getClass().getResource("/iconos/agregar.png")));
		btAgregar.addActionListener(oControl);
		btAgregar.setActionCommand("btAgregar");
		btAgregar.setToolTipText("Agregar");
		btAgregar.setPreferredSize(new Dimension(48, 48));

		JButton btEliminar = new JButton(new ImageIcon(getClass().getResource("/iconos/eliminar.png")));
		btEliminar.addActionListener(oControl);
		btEliminar.setActionCommand("btEliminar");
		btEliminar.setToolTipText("Eliminar");
		btEliminar.setPreferredSize(new Dimension(48, 48));

		JButton btModificar = new JButton(new ImageIcon(getClass().getResource("/iconos/modificar.png")));
		btModificar.addActionListener(oControl);
		btModificar.setActionCommand("btModificar");
		btModificar.setToolTipText("Modificar");
		btModificar.setPreferredSize(new Dimension(48, 48));

		JButton btLimpiar = new JButton(new ImageIcon(getClass().getResource("/iconos/borrador.png")));
		btLimpiar.setToolTipText("Limpiar");
		btLimpiar.addActionListener(oControl);
		btLimpiar.setActionCommand("btLimpiar");
		btLimpiar.setPreferredSize(new Dimension(48, 48));

		oPanel.add(btAgregar);
		oPanel.add(btEliminar);
		oPanel.add(btModificar);
		oPanel.add(btLimpiar);

		return oPanel;
	}

	public DTOInsumo leerDTO(){

		String sNombre = "", sUnidadMedida ;
		int eCont = 0;
		boolean bEsFlotante = true;
		float  fExistenciaActual = 0.0f, fExistenciaMinima = 0.0f, fExistenciaMaxima = 0.0f;
		DTOInsumo oDTOInsumo;

		if (!isNumeric(aCamposTextoDatos[1].getText())){

			sNombre = aCamposTextoDatos[1].getText();

			while(bEsFlotante && eCont < aCamposTextoExistencias.length){

				if (!isNumericFloat(aCamposTextoExistencias[eCont].getText()) ) {

					bEsFlotante = false;
					
				}

				eCont++;
			}

			if (bEsFlotante){

				fExistenciaActual = Float.valueOf(aCamposTextoExistencias[0].getText());
				fExistenciaMinima = Float.valueOf(aCamposTextoExistencias[1].getText());
				fExistenciaMaxima = Float.valueOf(aCamposTextoExistencias[2].getText());
			} else {

				JOptionPane.showMessageDialog(null, "Error. Existencias no numericas, ingresar nuevamente.");

			}
			

		} else{

			JOptionPane.showMessageDialog(null, "Error. Nombre numerico, ingresar nuevamente.");

		}
		
		sUnidadMedida = oComboUnidadMedida.getSelectedItem().toString();
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

       oCampoTxBuscar.setText("Codigo/Nombre");
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

	public static boolean isNumeric(String oCadena) {

        boolean oResultado;

        try {

            Integer.parseInt(oCadena);
            oResultado = true;

        } catch (NumberFormatException oExcepcion) {

            oResultado = false;

        }

        return oResultado;
    }

    public static boolean isNumericFloat(String oCadena) {

        boolean oResultado;

        try {

            Float.parseFloat(oCadena);
            oResultado = true;

        } catch (NumberFormatException oExcepcion) {

            oResultado = false;

        }

        return oResultado;
    }

   		 
}