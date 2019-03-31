/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.p01.grup5.gestioHospital.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import m03.uf5.p01.grup5.gestioHospital.model.Adreca;
import m03.uf5.p01.grup5.gestioHospital.utils.ConexionDB;

/**
 *
 * @author roger
 */
public class DAOAdreca {
    public static ResultSet getAdrecaResultSet() throws Exception {
        try {
            Connection join = ConexionDB.contectar();
            PreparedStatement states = join.prepareStatement("SELECT * FROM ADRECA;");
            states.executeQuery();
            return states.getResultSet();
        } catch (SQLException ex) {
           throw (new Exception("Hubo un error1: " + ex.getMessage()));
        }
    }
    public static List<Adreca> getAdreca() throws Exception {
        try {
            ArrayList<Adreca> llistaAdrecas = new ArrayList<>();
            ResultSet rs = getAdrecaResultSet();
            while (rs.next()) {
                llistaAdrecas.add(creaAdrecaO(rs));
            }
            Adreca[] aAdreca = new Adreca[llistaAdrecas.size()];
            return llistaAdrecas;

        } catch (SQLException ex) {
             throw (new Exception("Hubo un error2: " + ex.getMessage()));
        }
    }
    private static Adreca creaAdrecaO(ResultSet rs) throws Exception {        
        String tipo = rs.getString("tipo");
        String carrer = rs.getString("carrer");
        int numero = rs.getInt("numero");
        int planta = rs.getInt("planta");
        String porta = rs.getString("porta");
        String ciutat = rs.getString("ciutat");
        int codiPostal = rs.getInt("codiPostal");     
        
        return new Adreca(tipo,carrer,numero,planta,porta,ciutat,codiPostal);

    }
}
