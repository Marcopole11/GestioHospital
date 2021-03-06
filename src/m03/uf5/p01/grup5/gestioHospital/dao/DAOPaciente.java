/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.p01.grup5.gestioHospital.dao;

/**
 *
 * @author raulk
 */
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import m03.uf5.p01.grup5.gestioHospital.controlador.GestioHospital;
import m03.uf5.p01.grup5.gestioHospital.model.Adreca;
import m03.uf5.p01.grup5.gestioHospital.model.Pacient;
import m03.uf5.p01.grup5.gestioHospital.utils.ConexionDB;

public class DAOPaciente {

    public static ResultSet getPacientsResultSet()  throws Exception {
        try {
            Connection join = ConexionDB.contectar();
            PreparedStatement states = join.prepareStatement("SELECT * FROM pacients;");
            states.executeQuery();
            return states.getResultSet();
        } catch (Exception ex) {
            throw (new Exception("Hubo un error: " + ex.getMessage()));
        }
    }

    public static List<Pacient> getPacients() throws Exception {
        try {
            ArrayList<Pacient> llistaPacients = new ArrayList<>();
            ResultSet rs = getPacientsResultSet();
            while (rs.next()) {
                llistaPacients.add(creaPAcientO(rs, llistaPacients.size() - 1));
            }
            Pacient[] aPacients = new Pacient[llistaPacients.size()];
            return llistaPacients;

        } catch (SQLException ex) {
             throw (new Exception("Hubo un error: " + ex.getMessage()));
        }
    }

    public static ResultSet pacientNifResult(String nif) throws Exception  {
        try {
            Connection join = ConexionDB.contectar();
            PreparedStatement states = null;
            String csql = "SELECT * FROM pacients WHERE nifPac = ?";
            states = join.prepareStatement(csql);
            states.setString(1, nif);
            states.executeQuery();
            return states.getResultSet();
        } catch (SQLException ex) {
             throw (new Exception("Hubo un error: " + ex.getMessage()));
        }
    }

    public static ResultSet pacientNSS(String nss)  throws Exception {
        try {
            Connection join = ConexionDB.contectar();
            PreparedStatement states = null;
            String csql = "SELECT * FROM pacients WHERE numSegSocialPac = ?";
            states = join.prepareStatement(csql);
            states.setString(1, nss);
            states.executeQuery();
            return states.getResultSet();
        } catch (SQLException ex) {
             throw (new Exception("Hubo un error: " + ex.getMessage()));
        }
    }

    public static ResultSet pacientIEX(int codiHist)  throws Exception {
        try {
            Connection join = ConexionDB.contectar();
            PreparedStatement states = null;
            String csql = "SELECT * FROM pacients WHERE iexPac = ?";
            states = join.prepareStatement(csql);
            states.setInt(1, codiHist);
            states.executeQuery();
            return states.getResultSet();
        } catch (SQLException ex) {
            throw (new Exception("Hubo un error: " + ex.getMessage()));
        }
    }
////public static Pacient getPacientNif(String DNI) throws Exception {
////        try {
////            ResultSet rs = pacientNifResult(DNI);
////            rs.next();
////            return creaPAcientO(rs,);
////        } catch (SQLException ex) {
////            System.out.println("ERROR EN SQL: " + ex.getMessage());
////            return null;
////        }
////    }

    /*public static boolean modificarPacient(Pacient pacient) throws Exception  {
        try {
            Connection join = ConexionDB.contectar();
            CallableStatement states = null;
            String csql = "{call actualizaPacient(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            states = join.prepareCall(csql);
            states.setBoolean(1, pacient.getCasaOBloque());
            states.setString(2, pacient.getNom());
            states.setString(3, pacient.getCognom1());
            states.setString(4, pacient.getCognom2());
            states.setString(5, pacient.getNumSegSocial());
            states.setString(6, pacient.getNif());
            states.setString(7, pacient.getTelefon());
            states.setString(8, pacient.getAdreca().getTipo());
            states.setString(9, pacient.getAdreca().getCarrer());
            states.setInt(10, pacient.getAdreca().getNumero());
            states.setInt(11, pacient.getAdreca().getPlanta());
            states.setString(12, pacient.getAdreca().getPorta());
            states.setString(13, pacient.getAdreca().getCiutat());
            states.setLong(14, pacient.getAdreca().getCodiPostal());
            states.execute();
            return true;
        } catch (SQLException ex) {
             throw (new Exception("Hubo un error: " + ex.getMessage()));
        }
    }*/

    private static Pacient creaPAcientO(ResultSet resultat, int numeroH) throws Exception {
        String nifPacient = resultat.getString("nifPac");        
        String nomPacient = resultat.getString("nomPac");
        String cognom1Pacient = resultat.getString("primerCognomPac");
        String cognom2Pacient = resultat.getString("segonCognomPac");
        String numSegSoc = resultat.getString("numSegSocialPac");
        String telefon = resultat.getString("telPac");
        String ciutat = resultat.getString("ciutatPac");
        int codiPostal = resultat.getInt("codiPostalPac");
        boolean casaObloque = resultat.getBoolean("casaObloquePac");
        String tipo = resultat.getString("tipoPac");
        String carrer = resultat.getString("carrerPac");
        int numero = resultat.getInt("numeroPac");
        int planta = resultat.getInt("plantaPac");
        String porta = resultat.getString("portaPac");

        return new Pacient(casaObloque, nomPacient, cognom1Pacient, cognom2Pacient, numSegSoc, nifPacient, telefon, tipo, carrer, numero, planta, porta, ciutat, codiPostal, numeroH);

    }

    public static boolean creaPacient(Pacient pacient, int iex) throws Exception {
        Connection join = ConexionDB.contectar();
        PreparedStatement states = null;
        String consulta = "INSERT INTO pacients (casaObloquePac,nomPac,primerCognomPac,segonCognomPac,numSegSocialPac,nifPac,telPac,tipoPac,carrerPac,numeroPac,plantaPac,portaPac,ciutatPac,codiPostalPac,iexPac)"
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        states = join.prepareStatement(consulta);
        states.setBoolean(1, pacient.getCasaOBloque());
        states.setString(2, pacient.getNom());
        states.setString(3, pacient.getCognom1());
        states.setString(4, pacient.getCognom2());
        states.setString(5, pacient.getNumSegSocial());
        states.setString(6, pacient.getNif());
        states.setString(7, pacient.getTelefon());
        states.setString(8, pacient.getAdreca().getTipo());
        states.setString(9, pacient.getAdreca().getCarrer());
        states.setInt(10, pacient.getAdreca().getNumero());
        states.setInt(11, pacient.getAdreca().getPlanta());
        states.setString(12, pacient.getAdreca().getPorta());
        states.setString(13, pacient.getAdreca().getCiutat());
        states.setLong(14, pacient.getAdreca().getCodiPostal());
        states.setInt(15,iex);
        states.executeUpdate();
        return true;
    }

}
