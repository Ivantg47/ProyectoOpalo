/**
 * Clase control de las compras.
 * @author Diego Puebla Aldama
 * @version 1.0
 */

package ProyectoOpalo.control;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import ProyectoOpalo.igu.IGUCompras;
import ProyectoOpalo.dao.DAOCompra;
import ProyectoOpalo.dto.DTOCompra;

public class ControlCompra implements ActionListener, FocusListener{
    
    //private DTOCompra oCompra;
    private DAOCompra dao;
    private IGUCompras igu;
    public static DTOCompra[] aCompras = new DTOCompra[10];
    public static int indice; //Me dice cuantos hay en el arreglo

    //Método que sea llenarTabla() que recibe el modelo de la igu mediante getModeloDTO() dentro de ese modelo pongo modelo.addrow() sin el modelo.setRowCount(0);

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
                //llenarTabla();
                break;

            case "Limpiar":
                igu.limpiarProducto();
                for (DTOCompra dtoCompra : aCompras) {
                    System.out.println(aCompras[indice].getNombre());
                }
                break;
            /*        
            case "btBuscar":
				DAOCompra daoCompraB = new DAOCompra();
				igu.ventas = daoCompraB.buscarCompra(igu.leerDatoBuscar());//Preguntaaaaaar
				break;*/


        }

    }

    //investigar esto
    public void focusGained(FocusEvent e) {

		JTextField campo = (JTextField) e.getSource();
           
    }

    public void focusLost(FocusEvent e) {

    	JTextField campo = (JTextField) e.getSource();

        
    }

}
