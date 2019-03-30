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
    public boolean causaBaixa;
    public Duration duradaTractament;

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
    public String catBool(Boolean eng){
        if(eng){
            return "Si";
        } else {
            return "No";
        }
    }
    public static String[] CSV(){
        return new String[]{"codi","nom","causaBaixa","tractament","duradaTractament"};
    }
    public String toCSV(){
        return codi+";"+nom+";"+catBool(causaBaixa)+";"+tractament+";"+duradaTractament.toDays();
    }
    public Malaltia(String[]csv)throws Exception{
        this.codi=Integer.parseInt(csv[0]);
        this.nom=csv[1];
        this.causaBaixa=csv[2].equals("Si");
        this.tractament=csv[3];
        this.duradaTractament=Duration.ofDays(Long.parseLong(csv[4]));
    }    
    
    public int getCodi() {
        return this.codi;
    }
    
    public String getNom() {
        return nom;
    }
    
    public boolean isCausaBaixa() {
        return causaBaixa;
    }

    public String isCausaBaixaString() {
        if(this.isCausaBaixa()){
            return "Si";
        }else{
            return "No";
        }
    }    
    
    public String getTractament() {
        return tractament;
    }

   
    public Duration getDuradaTractament() {
        return duradaTractament;
    }

}
