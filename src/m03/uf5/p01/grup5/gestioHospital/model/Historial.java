/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.p01.grup5.gestioHospital.model;

/**
 *
 * @author Marc Cardenas, Raúl Barrero, Roger Miralles
 */
public class Historial {

    public int codi;

    public Historial(int iex) {
        codi = iex;
    }
    public Visita visites = new Visita();
    
    @Override
    public String toString() {
        return "Historial de visites:\n" + visites.toString();
    }
}