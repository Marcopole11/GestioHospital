/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.p01.grup5.gestioHospital.vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Marc Cardenas, Raul Barrero, Roger Miralles
 */
public class Ventana {

    byte tipo;
    int totalW, totalH;
    private GraphicsConfiguration gc;
    public static Ventana menuPrincipal = new Ventana((byte) 1);
    public static Ventana menuAnadir = new Ventana((byte) 2);
    public static Ventana menuMostrar = new Ventana((byte) 3);

    private Ventana(byte tipo) {
        this.tipo = tipo;
    }

    public JFrame abrir() {
        JFrame ventana;
        ventana = new JFrame(gc);
        Dimension ventanaT = Toolkit.getDefaultToolkit().getScreenSize();
        ventana.setLayout(null);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setTitle("Gestio Hospital");
        ventana.setSize((int)(ventanaT.width / 1.5),(int)(ventanaT.height / 1.5));
        ventana.setLocationRelativeTo(null);
        totalW = ventana.getBounds().width;
        totalH = ventana.getSize().height;
        
        ventana.setVisible(true);
        ventana.setResizable(false);
        switch(tipo){
            case 1:
                System.out.println("lego");
                return Vmenu(ventana); 
            case 2:
                return (ventana);
            case 3:
                return (ventana);
            default:
                return ventana;
        }
    }
    public JFrame Vmenu(JFrame ventana){
        JButton boton = new JButton();
        boton.setBounds(porcW(5)+30, porcH(5)+30, porcW(40)-60, porcH(90)-60);
        System.out.println(totalH);
        System.out.println(((double)(totalH-15)/100*100));
        ventana.add(boton);
        return ventana;
    }
    public int porcW(double entr){return (int)((double)totalW/100*entr);}
    public int porcH(double entr){return (int)((double)(totalH-30)/100*entr);}
    
    public JFrame VmenuAnadir(JFrame ventana){
        return ventana;
    }
    
    public JFrame VmenuMostrar(JFrame ventana){
        return ventana;
    }
}
