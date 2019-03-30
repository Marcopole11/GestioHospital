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
        create(tipo, carrer, numero, ciudad, codiPostal);
    }

    public Adreca(String tipo, String carrer,int numero, int planta, String porta, String ciudad, int codiPostal) {
        create(tipo, carrer, numero, ciudad, codiPostal);
        this.planta=planta;
        this.porta=porta;
    }
    private void create(String tipo, String carrer,int numero, String ciudad, int codiPostal){
        switch(tipo){
            case "Avenida": case "avenida": case "avinguda": case "Avinguda":
                this.tipo = "Av.";
                break;
            case "Calle": case "calle": case "carrer": case "Carrer":
                this.tipo = "c/";
                break;
            case "Carretera": case "carretera":
                this.tipo = "ca/";
            default:
                this.tipo = tipo;
        }
        this.carrer=carrer;
        this.numero=numero;
        this.planta=0;
        this.porta=null;
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
    public static String[] CSV(){
        return new String[]{"tipo","carrer","numero","ciudad","codiPostal","planta","porta"};
    }
    public String toCSV(){
        String base = tipo+";"+carrer+";"+numero+";"+ciutat+";"+entero(codiPostal,5);
        if(porta != null)return base+";"+planta+";"+porta;
        else return base;
    }
    public Adreca(String[] csv,int[] orden){
        if(csv.length == 5){
            create(csv[0], csv[1],Integer.parseInt(csv[2]), csv[3], Integer.parseInt(csv[4]));
        } else {
            create(csv[0], csv[1],Integer.parseInt(csv[2]), csv[3], Integer.parseInt(csv[4]));
            this.planta=Integer.parseInt(csv[5]);
            this.porta=csv[6];
        }
    }
    private String entero(int dato, int largo){
        String mandar = ""+dato;
        while(mandar.length() < largo){
            mandar = "0"+mandar;
        }
        return mandar;
    }
    public String getCiutat() {
        return ciutat;
    }

    public long getCodiPostal() {
        return codiPostal;
    }

    public String getTipo(){
        return tipo;
    }
    
    public String getCarrer() {
        return carrer;
    }

    public int getNumero() {
        return numero;
    }

    public int getPlanta() {
        return planta;
    }

    public String getPorta() {
        return porta;
    }
}
