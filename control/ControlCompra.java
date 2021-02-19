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
import ProyectoOpalo.dao.*;
import ProyectoOpalo.dto.*;

public class ControlCompra implements ActionListener, FocusListener{
    
    //instancias de las otras clases
    private DAOCompra oDAO;
    private IGUCompras oIGU;
    private DTOCompra oDTO;



    /**
     * Constructor de esta clase que recibe y establece la interfaz.
     * @param oIGU instancia de la interfaz.
     */
    public ControlCompra(IGUCompras oIGU){

        this.oIGU = oIGU;

    }  

    /**
     * Método que establece los comandos a realizar según la señal que llegue de la interfaz.
     */
    public void actionPerformed(ActionEvent evento){

        oDAO = new DAOCompra();

        try{

            if(evento.getActionCommand().equals("BuscarInsumo")){

                oIGU.setCampoInsumo(new DAOInsumo().buscar(oIGU.getCampoInsumo()));

            } else if(evento.getActionCommand().equals("limpiarInsumo")){

                oIGU.limpiarCampoInsumo();

            } else if(evento.getActionCommand().equals("AgregarInsumo")){

                oIGU.agregarInsumo();
                oIGU.limpiarCampoInsumo();

            }else if (evento.getActionCommand().equals("Quitar")) {

                oIGU.quitarInsumo();

            } else if (evento.getActionCommand().equals("Registrar")) {

                int folio = oDAO.agregar(oIGU.generarCompra());
                oIGU.nuevaVenta();

                JOptionPane.showMessageDialog( null, "Venta registrada con folio: " + folio,
                                "Registro venta", JOptionPane.INFORMATION_MESSAGE);

            }     

        }catch (Exception ex){

            ex.printStackTrace();

        }

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
