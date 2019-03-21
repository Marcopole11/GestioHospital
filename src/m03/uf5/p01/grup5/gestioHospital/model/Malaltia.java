/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.p01.grup5.gestioHospital.model;

import java.time.Duration;

/**
 *
 * @author Marc Cardenas, Raúl Barrero, Roger Miralles
 */
public class Malaltia {

    public int codi;
    public String nom, tractament;
    boolean causaBaixa;
    Duration duradaTractament;

    public Malaltia(int codi, String nom, String tractament, boolean causaBaixa, long duradaTractament) {
        this.codi = codi;
        this.nom = nom;
        this.causaBaixa = causaBaixa;
        this.tractament = tractament;
        this.duradaTractament = Duration.ofDays(duradaTractament);
    }

    @Override
    public String toString() {
        return "Codi: " + this.codi + "\nNom: " + this.nom + "\nBaixa: " + catBool(this.causaBaixa) + "\n Tractament:\n| " + this.tractament.replace("\n", "\n| ") + "\nduradaTractament: " + this.duradaTractament.toDays() + " dies";
    }
    private String catBool(Boolean eng){
        if(eng){
            return "Si";
        } else {
            return "No";
        }
    }
}
