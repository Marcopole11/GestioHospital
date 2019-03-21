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

}