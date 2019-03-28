/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.p01.grup5.gestioHospital.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marc Cardenas, Raúl Barrero, Roger Miralles
 */
public class Hospital {

    public List<Pacient> pacients;
    public List<Metge> metges;
    public List<Malaltia> malalties;
    public Adreca adreca;

    public Hospital(String tipo,String carrer,int numero,int planta,String porta,String ciutat,int codiPostal) {
        pacients = new ArrayList<Pacient>();
        metges = new ArrayList<Metge>();
        malalties = new ArrayList<Malaltia>();
        adreca = new Adreca(tipo,carrer,numero,planta,porta,ciutat,codiPostal);
    }
    public Hospital(Adreca adreca) {
        pacients = new ArrayList<Pacient>();
        metges = new ArrayList<Metge>();
        malalties = new ArrayList<Malaltia>();
        this.adreca = adreca;
    }
    public void nouPacient(boolean casaObloque,String nom, String primerCognom, String segonCognom, String numSegSocial, String nif, String tel, String tipo,String carrer,int numero,int planta,String porta,String ciudad,int codiPostal) throws Exception {
        pacients.add(new Pacient(casaObloque,nom, primerCognom, segonCognom, numSegSocial, nif, tel, tipo,carrer,numero,planta,porta,ciudad,codiPostal, pacients.size()));
    }
    public void nouMetge(boolean casaObloque,String nom, String primerCognom, String segonCognom, String numSegSocial, String nif, String tel, int salariMensual, String codiCompteCorrent, String tipo,String carrer,int numero,int planta,String porta,String ciudad,int codiPostal) throws Exception {
        metges.add(new Metge(casaObloque,nom, primerCognom, segonCognom, numSegSocial, nif, tel, metges.size(), salariMensual, codiCompteCorrent, tipo,carrer,numero,planta,porta,ciudad,codiPostal));
    }
    public void novaMalatia(String nom, String tractament, boolean causaBaixa, long duradaTractament) {
        malalties.add(new Malaltia(malalties.size(), nom, tractament, causaBaixa, duradaTractament));
    }
    /**
     * Troba un pacient buscant per DNI
     * @param NIF DNI del pacient
     * @return El pacient amb el DNI especificat
     */
    public Pacient pacient(String NIF) {
        for (Pacient ts : pacients) {
            if (ts.nif.equals(NIF)) {
                return ts;
            }
        }
        return null;
    }
    /**
     * Troba un pacient buscant per el seu número de seguritat social
     * @param num número de la seguritat social del pacient
     * @return El pacient propietari d'aquest número
     */
    public Pacient pacientSegSoc(String num) {
        for (Pacient ts : pacients) {
            if (ts.numSegSocial.equals(num)) {
                return ts;
            }
        }
        return null;
    }
    /**
     * Troba un metge buscant per DNI
     * @param NIF DNI del metge
     * @return El metge amb el DNI especificat
     */
    public Metge metge(String NIF) {
        for (Metge ts : metges) {
            if (ts.nif.equals(NIF)) {
                return ts;
            }
        }
        return null;
    }
    
    /**
     * Troba una malaltia buscant per codi
     * @param codi de malaltia
     * @return malaltia amb el codi especificat
     */
    public Malaltia malaltia(int codiMalaltia) {
        for (Malaltia ts : malalties) {
            if (ts.codi==codiMalaltia) {
                return ts;
            }
        }
        return null;
    }
}

