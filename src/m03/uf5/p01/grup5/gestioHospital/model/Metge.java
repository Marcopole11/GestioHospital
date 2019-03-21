/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.p01.grup5.gestioHospital.model;

/**
 *
 * @author miral
 */
public class Metge extends Persona {

    int numEmpleat, salariMensual;
    String codiCompteCorrent;

    public Metge(boolean casaObloque,String nom, String primerCognom, String segonCognom, String numSegSocial, String nif, String tel, int numEmpleat, int salariMensual, String codiCompteCorrent, String tipo,String carrer,int numero,int planta,String porta,String ciudad,int codiPostal) throws Exception {
        super(casaObloque,nom, primerCognom, segonCognom, numSegSocial, nif, tel, tipo,carrer,numero,planta,porta,ciudad,codiPostal);
        this.numEmpleat = numEmpleat;
        this.salariMensual = salariMensual;
        this.codiCompteCorrent = codiCompteCorrent;
    }

    @Override
    public String toString() {
        return cognom1 + " " + cognom2 + " " + nom + "\n         NIF: " + nif + "\n Seg. Social: " + numSegSocial
                + "\n     Telèfon: " + telefon + "\n     Numero Empleat: " + numEmpleat + "\n     Salari mensual: " + salariMensual
                + "\n     Numero Compte: " + codiCompteCorrent + "\n     Adreça: " + adreca.toString();
    }
}