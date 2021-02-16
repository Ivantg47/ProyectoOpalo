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
    
    //private DTOCompra oCompra;
    private DAOCompra dao;
    private IGUCompras igu;
    public float suma = 0;
    public static DTOCompra[] aCompras = new DTOCompra[10];
    public static int indice = -1; //Me dice cuantos hay en el arreglo

    //Método que sea llenarTabla() que recibe el modelo de la igu mediante getModeloDTO() dentro de ese modelo pongo modelo.addrow() sin el modelo.setRowCount(0);

    public void llenarTabla(DefaultTableModel modelo){

        //Falta arreglar lo del subtotal, almenos lo del arreglo ya está.
        //modeloDTO.setColumnIdentifiers(new Object[]{"#", "Nombre", "Cantidad", "Costo Unitario", "SubTotal"});
        modelo.setRowCount(0);
        
        float subtotal;

        for (int i = 0; i <= indice; i++) {

            try {
                subtotal = aCompras[i].getCantidad() * aCompras[i].getTotal();
//                suma += aCompras[i].getTotal();
                modelo.addRow(new Object[]{aCompras[i].getIdInsumo(), aCompras[i].getNombre(), aCompras[i].getCantidad(), aCompras[i].getTotal(), subtotal});
            } catch (Exception e) {
                System.out.println("Ex" + e);
            }

            

        }

    }

    public ControlCompra(IGUCompras igu){

        this.igu = igu;

    }

    public void actionPerformed(ActionEvent evento){

        DTOCompra oCompra;

        JButton fuente = (JButton) evento.getSource();
        dao = new DAOCompra();

        switch (fuente.getActionCommand()) {
            case "Verificar":
                oCompra = igu.getCampos();
                dao.verificarInsumo(oCompra);
                System.out.println(oCompra.getIdInsumo()); //Si le asigna el id y nuestro objeto está completo.
                if(oCompra.getIdInsumo() > 0){ //Si está completo el objeto.
                    indice++;
                    aCompras[indice] = oCompra;
                    System.out.println(aCompras[indice].getNombre());
                }
                llenarTabla(igu.getModeloDTO());
                igu.setTotal(getTotal(aCompras));
                break;

            case "Limpiar":
                igu.limpiarProducto();
                for (DTOCompra dtoCompra : aCompras) {
                    System.out.println(aCompras[indice].getNombre());
                }
                break;
                    
            case "btBuscar":
				DAOCompra daoCompraB = new DAOCompra();
				igu.compras = daoCompraB.buscarCompra(igu.leerDatoBuscar());
				break;

            case "Registrar":
                dao.insertarCompras(aCompras, indice);
                break;


        }

    }

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

    //investigar esto
    public void focusGained(FocusEvent e) {

		JTextField campo = (JTextField) e.getSource();
           
    }

    public void focusLost(FocusEvent e) {

    	JTextField campo = (JTextField) e.getSource();

        
    }

}
