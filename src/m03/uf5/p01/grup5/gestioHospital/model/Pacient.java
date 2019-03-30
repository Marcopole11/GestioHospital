T/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.p01.grup5.gestioHospital.model;

import java.util.List;

/**
 *
 * @author Marc Cardenas, Raul Barrero, Roger Miralles
 */
public class Pacient extends Persona {

    public Historial historial;

    public Pacient(boolean casaObloque,String nom, String primerCognom, String segonCognom, String numSegSocial, String nif, String tel, String tipo,String carrer,int numero,int planta,String porta,String ciudad,int codiPostal, int iex) throws Exception {
        super(casaObloque, nom, primerCognom, segonCognom, numSegSocial, nif, tel, tipo,carrer,numero,planta,porta,ciudad,codiPostal);
        historial = new Historial(iex);
    }

    @Override
    public String toString() {
        return "\n=================================\n" + cognom1 + " " + cognom2 + ", " + nom + "\n         NIF: " + nif + "\n Seg. Social: " + numSegSocial + "\n     Telèfon: " + telefon + "\n     Adreça: " + adreca.toString() + "\n`````````````````````````````````\n" + historial;
    }
    public static String[] CSV(){
        return new String[]{"nom","cognom1","cognom2","numSegSocial","nif","telefon","adreca","historial"};
    }
    public String toCSV(int adrID){
        return nom+";"+cognom1+";"+cognom2+";"+numSegSocial+";"+nif+";"+telefon+";"+adrID+";"+historial.visites.toCSV();
    }
    public Pacient(int iex, String[] csv,List<Adreca> adreces, List<Malaltia> malalties) throws Exception {
        super(csv[0], csv[1], csv[2], csv[3], csv[4], csv[5], adreces.get(Integer.parseInt(csv[6])-2));
        if(csv[7].equals("X")){
            historial = new Historial(iex);
        } else {
           historial = new Historial(iex, csv[7].split(","), malalties); 
        }
    }
    public Historial getHistorial() {
        return this.historial;
    }
}