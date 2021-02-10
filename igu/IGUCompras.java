
package ProyectoOpalo.igu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Clase que implementa una interfaz para el modulo de compras.
 * @author Diego Puebla Aldama
 * @version 1.0
 */

public class IGUCompras extends JFrame{

    private JComboBox<String> unidadesMedida;

    /**
     * Método que genera la interfaz para ser instanciada
     */
    public IGUCompras(){

    }

    public JPanel getIGUCompras(){

        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(3,0));
        panel.add(getBarraHtas());
        panel.add(getFormulario()); //, BorderLayout.NORTH);
        panel.add(getTablaCompras());//, BorderLayout.PAGE_END);

        return panel;
    }
    /**
     * Método que crea una barra de herramientas con los botones de la sección.
     * @return La barra de herramientas para ser agregada a la interfaz.
     */
    public JToolBar getBarraHtas(){

        //Creamos los botones
        JToolBar barraHtas = new JToolBar();
        JButton btSalir = new JButton();
        JButton btRegistrar = new JButton();
        JButton btModificar = new JButton();
        JButton btConsultar = new JButton();
        JButton btListar = new JButton();

        //Colocamos el texto a los botones
        btSalir.setText("Salir");
        btRegistrar.setText("Registrar");
        btModificar.setText("Modificar");
        btConsultar.setText("Consultar");
        btListar.setText("Listar");

        //Agregamos los botones
        barraHtas.add(btRegistrar);
        barraHtas.add(btModificar);
        barraHtas.add(btConsultar);
        barraHtas.add(btListar);
        barraHtas.add(btSalir);

        return barraHtas;

    }

    /**
     * Método que obtiene el formulario con los paneles y los campos de texto, además de los botones.
     * @return Regresa el formulario enh un panel.
     */
    public JPanel getFormulario(){

        //Declaramos nuestras columnas
        JPanel labels = new JPanel();
        JPanel textFields = new JPanel();
        JPanel botones = new JPanel();
        JPanel formulario = new JPanel();

        //Declaramos los botones
        JButton btAceptar = new JButton();
        btAceptar.setText("Aceptar");
        JButton btCancelar = new JButton();
        btCancelar.setText("Cancelar");
        JButton btBorrar = new JButton();
        btBorrar.setText("Borrar");

        //Declaramos nuestros labels
        JLabel etiquetas[] = {
            new JLabel("Producto"),
            new JLabel ("Fecha de Compra"),
            new JLabel("Cantidad"),
            new JLabel("Total")
        };

        //Declaramos los textFields
        JTextField camposTexto[] = {
            new JTextField(),
            new JTextField(),
            new JTextField(),
            new JTextField()
        };
        

        //Establecemos las columnas
        labels.setLayout(new GridLayout(0, 1));
        textFields.setLayout(new GridLayout(0, 1));
        botones.setLayout(new GridLayout(3, 1));
        formulario.setLayout(new GridLayout(1,3));
        
        //Agregamos los labels
        for (int i = 0; i < etiquetas.length; i++){
			labels.add(etiquetas[i]);
        }
        
        //Agregamos los txtfields
        for (int i = 0; i < camposTexto.length; i++){
			textFields.add(camposTexto[i]);
        }

        //Agregamos los botones
        botones.add(btAceptar);
        botones.add(btCancelar);
        botones.add(btBorrar);

        //Preparamos el formulario
        formulario.add(labels);
        formulario.add(textFields);
        formulario.add(botones);

        formulario.setBorder(BorderFactory.createTitledBorder("Formulario de Registro de Compras"));

        return formulario;

    }

    /**
     * Método que genera la tabla de registros.
     * @return Regresa una tabla para ser mostrada en la interfaz.
     */
    public JPanel getTablaCompras(){

        JPanel panelTabla = new JPanel();
       
        //creacion de la tabla
		JTable tablaCompras = new JTable();

        //Parte del código de Frida
		String [] nombre = {
                "ID", "Fecha", "Producto", "Cantidad", "Total" 
            };
        
        //Más código de Frida pero funciona
		tablaCompras.setModel(new DefaultTableModel(
            new Object [][] {
                {"ID Compra", "Fecha Compra", "Producto", "Cantidad", "Total"},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            }, nombre
            
        ));
        
        //Agregamos la tabla al panel
        panelTabla.add(tablaCompras);

        panelTabla.setBorder(BorderFactory.createTitledBorder("Registros de Compras"));

        return panelTabla;

    }
/*
    public static void main(String[] args) {
     
        IGUCompras oIguMenu = new IGUCompras();

    }
*/
}