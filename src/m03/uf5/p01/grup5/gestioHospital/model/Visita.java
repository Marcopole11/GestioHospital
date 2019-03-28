/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.p01.grup5.gestioHospital.model;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Marc Cardenas, Raul Barrero, Roger Miralles
 */
public class Visita {

    private Visita nodo;
    private LocalDateTime data;
    private String metge;
    private Malaltia diagnostic;

    Visita() {
        metge = "";
    }

    @Override
    public String toString() {
        if (metge.length() == 0) {
            return " | No ha rebut cap visita.";
        } else {
            return listar(1);
        }
    }
    private String listar(int i) {
        String printo = " | Visita nº" + i + "\n |  A carreg de " + metge + "\n |  | Dia: "
                + data.getDayOfMonth() + "-" + data.getMonthValue() + "-" + data.getYear()
                + "\n |  | Hora: " + data.getHour() + ":";
        if ((data.getMinute() + "").length() == 1) {
            printo += "0";
        }
        printo += data.getMinute() + "\n |  | Diagnòstic:\n |  |  "
                + diagnostic.toString().replaceAll("\n", "\n |  |  ") + "\n |\n";
        if (nodo != null) {
            printo += nodo.listar(i + 1);
        }
        return printo;
    }
    /**
     * Afegeix una visita més al llistat de visites
     * @param hora hora de la visita en format 24h
     * @param diagnostic malaltia que li van diagnosticar
     */
    public void visitar(int dia, int mes, int any, double hora, Metge metge, Malaltia diagnostic) {
        String mt = metge.nom + " " + metge.cognom1;
        visitar(dia, mes, any, hora, mt, diagnostic);
    }
    private void visitar(int dia, int mes, int any, double hora, String metge, Malaltia diagnostic) {
        if (this.metge.length() == 0) {
            this.data = LocalDateTime.of(any, mes, dia, (int) hora, (int) ((hora - (int) hora) * 100), 0);
            this.metge = metge;
            this.diagnostic = diagnostic;
        } else {
            if (nodo == null) {
                nodo = new Visita();
            }
            nodo.visitar(dia, mes, any, hora, metge, diagnostic);
        }
    }
    public String toCSV(){
        if (metge.length() == 0) {
            return "X";
        } else {
            String tato = listarCSV();
            return tato.substring(0, tato.length()-1);
        }
    }
    private String listarCSV() {
        String printo = 
                data.getDayOfMonth()+"-"+
                data.getMonthValue()+"-"+
                data.getYear()+"-"+
                data.getHour()+"."+data.getMinute()
                +","+metge+","+diagnostic.codi+","; 
        if (nodo != null) {
            printo += nodo.listarCSV();
        }
        return printo;
    }
    Visita(String[] csv, List<Malaltia> malalties) {
        metge = "";
        String[] fecha = csv[0].split("-");
        visitar(Integer.parseInt(fecha[0]),
                Integer.parseInt(fecha[1]),
                Integer.parseInt(fecha[2]),
                Double.parseDouble(fecha[3]),
                csv[1],
                malalties.get(Integer.parseInt(csv[2])));
        if(csv.length > 3){
            nodo = new Visita(Arrays.copyOfRange(csv, 3, csv.length), malalties);
        }
    }
}
