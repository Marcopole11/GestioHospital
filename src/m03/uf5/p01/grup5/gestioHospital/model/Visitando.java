/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.p01.grup5.gestioHospital.model;

import java.time.LocalDateTime;

/**
 *
 * @author Marc
 */
public class Visitando {
    private int dia, mes, any, hores, minuts;
    
    public Metge metge;
    public Pacient pacient;
    public Malaltia diagnostic;
    public Visitando(int dia, int mes, int any, int hora, int minuts){
        this.dia = dia;
        this.mes = mes;
        this.any = any;
        this.hores = hora;
        this.minuts = minuts;
    }
    public int tiempo(int i){
        switch(i){
            case 0:return dia;
            case 1:return mes;
            case 2:return any;
            case 3:return hores;
            case 4:return minuts;
            default:return -1;
        }
    }
    public double hora(){
        return (minuts+(hores*100))/100;
    }
}
