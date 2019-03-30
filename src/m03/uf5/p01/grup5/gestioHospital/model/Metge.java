/*
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
public class Metge extends Persona {

    public int numEmpleat, salariMensual;
    public String codiCompteCorrent;

    public Metge(boolean casaObloque,String nom, String primerCognom, String segonCognom, String numSegSocial, String nif, String tel, int numEmpleat, int salariMensual, String codiCompteCorrent, String tipo,String carrer,int numero,int planta,String porta,String ciudad,int codiPostal) throws Exception {
        super(casaObloque,nom, primerCognom, segonCognom, numSegSocial, nif, tel, tipo,carrer,numero,planta,porta,ciudad,codiPostal);
        this.numEmpleat = numEmpleat;
        this.salariMensual = salariMensual;
        this.codiCompteCorrent = codiCompteCorrent;
    }
    
;
    @Override
    public String toString() {
        return cognom1 + " " + cognom2 + " " + nom + "\n         NIF: " + nif + "\n Seg. Social: " + numSegSocial
                + "\n     Telèfon: " + telefon + "\n     Numero Empleat: " + numEmpleat + "\n     Salari mensual: " + salariMensual
                + "\n     Numero Compte: " + codiCompteCorrent + "\n     Adreça: " + adreca.toString();
    }
    public static String[] CSV(){
        return new String[]{"nom","cognom1","cognom2","numSegSocial","nif","telefon","adreca","numEmpleat","salariMensual","codiCompteCorrent"};
    }
    public String toCSV(int adrID){
        return nom+";"+cognom1+";"+cognom2+";"+numSegSocial+";"+nif+";"+telefon+";"+adrID+";"+numEmpleat+";"+salariMensual+";"+codiCompteCorrent;
    }
    public Metge(String[] csv,List<Adreca> adreces) throws Exception {
        super(csv[0], csv[1], csv[2], csv[3], csv[4], csv[5], adreces.get(Integer.parseInt(csv[6])-2));
        this.numEmpleat = Integer.parseInt(csv[7]);
        this.salariMensual = Integer.parseInt(csv[8]);
        this.codiCompteCorrent = csv[9];
    }
    public int getNumEmpleat() {
        return numEmpleat;
    }

    public int getSalariMensual() {
        return salariMensual;
    }

    public String getCodiCompteCorrent() {
        return codiCompteCorrent;
    }
}
