/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.p01.grup5.gestioHospital.utils;

/**
 *
 * @author miral
 */
public class Connec {
    public static void main(String[] args) {
        ConexionDB conexio=new ConexionDB();
        conexio.contectar();
    }
}
