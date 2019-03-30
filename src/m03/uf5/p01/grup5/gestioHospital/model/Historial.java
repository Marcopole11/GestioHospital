/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.p01.grup5.gestioHospital.model;

import java.util.List;

/**
 *
 * @author Marc Cardenas, Raúl Barrero, Roger Miralles
 */
public class Historial {

    public int codi;
    public Visita visites;
    public Historial(int iex) {
        codi = iex;
        visites = new Visita();
    }
    public Historial(int iex, String[] csv, List<Malaltia> malalties) {
        codi = iex;
        visites = new Visita(csv, malalties);
    }
    
    @Override
    public String toString() {
        return "Historial de visites:\n" + visites.toString();
    }
    public int getCodi () {
        return this.codi;
    }
}