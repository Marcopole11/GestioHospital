/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.p01.grup5.gestioHospital.vista;

import java.awt.GraphicsConfiguration;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import m03.uf5.p01.grup5.gestioHospital.controlador.*;
/**
 *
 * @author Marc Cardenas, Raul Barrero, Roger Miralles
 */
public class gestorVentana {
    public boolean abierto;
    
    public gestorVentana(){
        
    }
    public boolean abrirVentana(Ventana tipo){
        abierto = true;
        LocalVentana venta = new LocalVentana();
        venta.iniciar(tipo);
        while(abierto){
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(gestorVentana.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    private class LocalVentana extends Thread{
    Ventana tipo;
        public void iniciar(Ventana tipo){
            this.tipo = tipo;
            
            start();
        }
        @Override
        public void run(){
            JFrame ventana = tipo.abrir(new ControlHospital());
            ventana.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent we) {
                    abierto = false;
                    super.windowClosed(we);
                }
            });
            System.out.println("salgo del hilo");
            // este no es el fichero que toca
        }
    }
}
