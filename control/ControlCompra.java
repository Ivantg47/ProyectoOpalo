/**
 * Clase control de las compras.
 * @author Diego Puebla Aldama
 * @version 1.0
 */

package ProyectoOpalo.control;

import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.*;
import ProyectoOpalo.igu.IGUCompras;
import ProyectoOpalo.dao.DAOCompra;
import ProyectoOpalo.dto.DTOCompra;

public class ControlCompra implements ActionListener, FocusListener{
    
    //instancias de las otras clases
    private DAOCompra dao;
    private IGUCompras igu;
    //Número que indica la suma de los costos.
    public float suma = 0;
    //Arreglo dónde vamos a almacenar las compras antes de registrarlas en la BD.
    public static DTOCompra[] aCompras = new DTOCompra[10];
    public static int indice = -1;

    /**
     * Método que llena la tabla de la interfaz.
     * @param modelo recibe la estructura de la tabla.
     */
    public void llenarTabla(DefaultTableModel modelo){

        modelo.setRowCount(0);
        
        float subtotal;

        for (int i = 0; i <= indice; i++) {

            try {
                subtotal = aCompras[i].getCantidad() * aCompras[i].getTotal();
                modelo.addRow(new Object[]{aCompras[i].getIdInsumo(), aCompras[i].getNombre(), aCompras[i].getCantidad(), aCompras[i].getTotal(), subtotal});
            } catch (Exception e) {
                System.out.println("Ex" + e);
            }

        }

    }

    /**
     * Constructor de esta clase que recibe y establece la interfaz.
     * @param igu instancia de la interfaz.
     */
    public ControlCompra(IGUCompras igu){

        this.igu = igu;

    }

    /**
     * Método que establece los comandos a realizar según la señal que llegue de la interfaz.
     */
    public void actionPerformed(ActionEvent evento){

        DTOCompra oCompra;

        JButton fuente = (JButton) evento.getSource();
        dao = new DAOCompra();

        switch (fuente.getActionCommand()) {
            case "Verificar":
                oCompra = igu.getCampos();
                dao.verificarInsumo(oCompra);
                System.out.println(oCompra.getIdInsumo()); //Si existe el insumo le asigna el id y nuestro objeto está completo.
                if(oCompra.getIdInsumo() > 0){ //Si está completo el objeto.
                    indice++;
                    aCompras[indice] = oCompra;
                    System.out.println(aCompras[indice].getNombre());
                }
                //Refresca la interfaz
                llenarTabla(igu.getModeloDTO());
                igu.setTotal(getTotal(aCompras));
                igu.limpiarProducto();
                break;

            case "Quitar":
                aCompras[indice] = null;
                indice--;
                llenarTabla(igu.getModeloDTO());
                igu.setTotal(getTotal(aCompras));
                break;
                    
            case "btBuscar":
				DAOCompra daoCompraB = new DAOCompra();
				igu.compras = daoCompraB.buscarCompra(igu.leerDatoBuscar());
				break;

            case "Registrar":
                dao.insertarCompras(aCompras, indice);
                break;

            case "Tirar":
                for (int i = 0; i <= indice; i++) {
                    aCompras[i] = null;
                }
                indice = -1;
                llenarTabla(igu.getModeloDTO());
                igu.setTotal(getTotal(aCompras));
                break;

        }

    }

    /**
     * Función que calcula el total de la compra.
     * @param aCompras Recibe el arreglo de las compras.
     * @return total que es la suma de los costos de las compras.
     */
    public float getTotal(DTOCompra[] aCompras){

        float total = 0;

        for (int i = 0; i <= indice; i++) {

            try {
                total = total + (aCompras[i].getTotal() * aCompras[i].getCantidad());
            } catch (Exception e) {
                System.out.println("Ex" + e);
            }

        }

        return total;

    }

    /**
     * Metodo, que espera a que el campo buscar sea seleccionado y en caso de no tener datos los limpia.
     * @param evento indica cuando el campo buscar es seleccionado.
     */
    public void focusGained(FocusEvent evento) {

		JTextField campo = (JTextField) evento.getSource();

        if(campo.getText().equals("Folio")){

            campo.setForeground(Color.BLACK);
            campo.setText(null);

        }
           
    }

    /**
     * Metodo, que espera a que el campo buscar sea desseleccionado y en caso de no tener datos coloca un texto.
     * @param evento indica cuando el campo buscar es desseleccionado.
     */
    public void focusLost(FocusEvent evento) {

    	JTextField campo = (JTextField) evento.getSource();

        if(campo.getText().equals("")){

            campo.setText("Folio");
            campo.setForeground(new Color(111,111,111));

        }

        
    }

}
