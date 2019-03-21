/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.p01.grup5.gestioHospital.model;

/**
 *
 * @author Marc Cardenas, Raúl Barrero, Roger Miralles
 */
public class Adreca {

    private String tipo;

    String ciutat, carrer, porta;
    int numero, planta, codiPostal;
        
    public Adreca(String tipo, String carrer,int numero, String ciudad, int codiPostal) {
        switch(tipo){
            case "Avenida": case "avenida": case "avinguda": case "Avinguda":
                this.tipo = "Av.";
                break;
            case "Calle": case "calle": case "carrer": case "Carrer":
                this.tipo = "c/";
                break;
            case "Carretera": case "carretera":
                this.tipo = "ca/";
        }
        this.carrer=carrer;
        this.numero=numero;
        this.planta=0;
        this.porta=null;
        this.ciutat=ciudad;
        this.codiPostal=codiPostal;
    }

    public Adreca(String tipo, String carrer,int numero, int planta, String porta, String ciudad, int codiPostal) {
           switch(tipo){
            case "Avenida": case "avenida": case "avinguda": case "Avinguda":
                this.tipo = "Av.";
                break;
            case "Calle": case "calle": case "carrer": case "Carrer":
                this.tipo = "c/";
                break;
            case "Carretera": case "carretera":
                this.tipo = "ca/";
        }
        this.carrer=carrer;
        this.numero=numero;
        this.planta=planta;
        this.porta=porta;
        this.ciutat=ciudad;
        this.codiPostal=codiPostal;
    }

    private static boolean isNotNumeric(String dato) {
        return dato.matches("^.*[^0-9].*$");
    }

    public String toString() {
        if (porta != null) {
            return tipo + " " + carrer + " nº " + numero + ", " + planta + " " + porta + ", " + ciutat + ", " + entero(codiPostal,5);
        } else {
            return tipo + " " + carrer + " nº " + numero + ", " + ciutat + ", " + entero(codiPostal,5);
        }
    }
    private String entero(int dato, int largo){
        String mandar = ""+dato;
        while(mandar.length() < largo){
            mandar = "0"+mandar;
        }
        return mandar;
    }
}
