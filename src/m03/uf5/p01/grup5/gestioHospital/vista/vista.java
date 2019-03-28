package m03.uf5.p01.grup5.gestioHospital.vista;

import java.util.Scanner;

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
 * @author Marc Cardenas, Roger Miralles y Raul Barrero.
 */
public class vista {
    
    
    public static void main(String[] args){
        Ventana.menuPrincipal.abrir(new ControlHospital());
        //principal.dispatchEvent(new WindowEvent(principal, WindowEvent.WINDOW_CLOSING));
    }
    
    private static Scanner in=new Scanner(System.in);
    
    public static void muestraTexto(String frase){
        System.out.println(frase);
    }
    
    public static String pedirString() {
        return in.nextLine();
    }
    
    public static int pedirInt(){
        int temp;
        try {
        temp = Integer.parseInt(pedirString());
        }catch(IllegalArgumentException e) {
            muestraTexto("¡El texto introducido no era un numero!");
            temp = Integer.MIN_VALUE;
        }
        return temp;
    }
    public static long pedirLong(){
        long temp1;
        try {            
            temp1 = Long.parseLong(pedirString());
        }catch(IllegalArgumentException e) {
            muestraTexto("¡El texto introducido no era un numero!");
            temp1 = Long.MIN_VALUE;
        }
        return temp1;
    }
}
