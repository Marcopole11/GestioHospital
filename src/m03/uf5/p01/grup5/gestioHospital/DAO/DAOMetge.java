/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.p01.grup5.gestiohospital.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import m03.uf5.p01.grup5.gestioHospital.model.Adreca;
import m03.uf5.p01.grup5.gestioHospital.model.Metge;
import m03.uf5.p01.grup5.gestioHospital.utils.ConexionDB;

public class DAOMetge {

    public static ResultSet MetgesResultat() {
        try {
            Connection join = ConexionDB.contectar();
            PreparedStatement states = join.prepareStatement("SELECT * FROM metges;");
            states.executeQuery();
            return states.getResultSet();
        } catch (SQLException ex) {
            System.out.println("ERROR" + ex.getMessage());
            return null;
        }
    }

    public static Metge[] Metges() throws Exception {
        try {
            ArrayList<Metge> malalt = new ArrayList<>();

            ResultSet resultat = MetgesResultat();

            while (resultat.next()) {
                malalt.add(newMetgeO(resultat));
            }
            Metge[] masMalaltias = new Metge[malalt.size()];
            return malalt.toArray(masMalaltias);

        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
            return null;
        }
    }

    public static ResultSet DniMetgeResultat(String dni) {
        try {
            Connection join = ConexionDB.contectar();
            PreparedStatement states = join.prepareStatement("SELECT * FROM metges WHERE nifMet = ?;");
            states.setString(1, dni);
            states.executeQuery();
            return states.getResultSet();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
            return null;
        }
    }

   /*
    public static Metge DniMetge(String dni) {
        try {
            ResultSet resultat = DniMetgeResultat(dni);
            resultat.next();
            return newMetgeO(resultat);
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
            return null;
        }
    }
    */

    private static Metge newMetgeO(ResultSet resultat) throws Exception {
        
        boolean COB = resultat.getBoolean("casaObloqueMet");
        int numEmpleatMet = resultat.getInt("numEmpleatMet");
        String nifMet = resultat.getString("nifMet");
        String nomMetge = resultat.getString("nomMetge");
        String primerCognomMet = resultat.getString("primerCognomMet");
        String segonCognomMet = resultat.getString("segonCognomMet");
        String numSegSocialMet = resultat.getString("numSegSocialMet");
        String telMet = resultat.getString("telMet");
        int salariMensualMet = resultat.getInt("salariMensualMet");
        String codiCompteCorrentMet = resultat.getString("codiCompteCorrentMet");
        String ciutatMet = resultat.getString("ciutatMet");
        String tipoMet = resultat.getString("tipoMet");
        int codiPostalMet = resultat.getInt("codiPostalMet");
        String carrerMet = resultat.getString("carrerMet");
        int numeroMet = resultat.getInt("numeroMet");
        int plantaMet = resultat.getInt("plantaMet");
        String portaMet = resultat.getString("portaMet");

       return new Metge(COB,nomMetge, primerCognomMet, segonCognomMet, numSegSocialMet, nifMet, telMet, numEmpleatMet,salariMensualMet,codiCompteCorrentMet,tipoMet,carrerMet,numeroMet,plantaMet,portaMet,ciutatMet,codiPostalMet);


    }

    public static ResultSet cssMetge(String css) {
        try {
            Connection join = ConexionDB.contectar();
            PreparedStatement states = join.prepareStatement("SELECT * FROM metges WHERE numSegSocialMet = ?;");
            states.setString(1, css);
            states.executeQuery();
            return states.getResultSet();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
            return null;
        }
    }

    public static boolean updateMetge(Metge metge) throws Exception {
        try {
            Connection join = ConexionDB.contectar();
            CallableStatement states = join.prepareCall("{call actualizaMetge(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}"); // revisa
            states.setInt(1, metge.getNumEmpleat());
            states.setString(2, metge.getNom());
            states.setString(3, metge.getCognom1());
            states.setString(4, metge.getCognom2());
            states.setString(5, metge.getNumSegSocial());
            states.setString(6, metge.getTelefon());
            states.setInt(7, metge.getSalariMensual());
            states.setString(8, metge.getCodiCompteCorrent());
            states.setString(9, metge.getAdreca().getCiutat());
            states.setLong(10, metge.getAdreca().getCodiPostal());
            states.setInt(11, metge.getAdreca().getNumero());
            states.setString(12, metge.getAdreca().getPlanta()+"");
            states.setString(13, metge.getAdreca().getPorta());
            states.execute();
            return true;
        } catch (SQLException ex) {
            throw(new Exception("Hubo un error al actlualizar el médico en la Base de datos: "+ex.getMessage()));
        }
    }

    public static boolean newMetge(Metge metge) throws Exception{
        Connection join = ConexionDB.contectar();
        PreparedStatement states;
        try {
            states = join.prepareStatement("INSERT INTO metges "
                    + "(nomMetge, primerCognomMet, segonCognomMet, numSegSocialMet, nifMet, telMet, ciutatMet, codiPostalMet, carrerMet, numeroMet, plantaMet, portaMet, numEmpleatMet, salariMensualMet, codiCompteCorrentMet) "
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            states.setString(1, metge.getNom());
            states.setString(2, metge.getCognom1());
            states.setString(3, metge.getCognom2());
            states.setString(4, metge.getNumSegSocial());
            states.setString(5, metge.getNif());
            states.setString(6, metge.getTelefon());
            states.setString(7, metge.getAdreca().getCiutat());
            states.setLong(8, metge.getAdreca().getCodiPostal());
            states.setString(9, metge.getAdreca().getCarrer());
            states.setInt(10, metge.getAdreca().getNumero());
            states.setString(11, metge.getAdreca().getPlanta()+"");
            states.setString(12, metge.getAdreca().getPorta());
            states.setInt(13, metge.getNumEmpleat());
            states.setInt(14, metge.getSalariMensual());
            states.setString(15, metge.getCodiCompteCorrent());
            states.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw(new Exception("Hubo un error al registrar el médico en la Base de datos: "+ex.getMessage()));
        }
    }
}
