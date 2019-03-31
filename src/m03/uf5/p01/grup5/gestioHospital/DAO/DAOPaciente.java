/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.p01.grup5.gestioHospital.DAO;

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
import m03.uf5.p01.grup5.gestioHospital.controlador.GestioHospital;
import m03.uf5.p01.grup5.gestioHospital.model.Adreca;
import m03.uf5.p01.grup5.gestioHospital.model.Pacient;
import m03.uf5.p01.grup5.gestioHospital.utils.ConexionDB;

public class DAOPaciente {

    public static ResultSet getPacientsResultSet() {
        try {
            Connection join = ConexionDB.contectar();
            PreparedStatement states = join.prepareStatement("SELECT * FROM PACIENTS;");
            states.executeQuery();
            return states.getResultSet();
        } catch (Exception e) {
            System.out.println("ERROR EN SQL:" + e.getMessage());
            return null;
        }
    }

    public static Pacient[] getPacients() throws Exception {
        try {
            ArrayList<Pacient> llistaPacients = new ArrayList<>();
            ResultSet rs = getPacientsResultSet();
            while (rs.next()) {
                llistaPacients.add(creaPAcientO(rs, llistaPacients.size() - 1));
            }
            Pacient[] aPacients = new Pacient[llistaPacients.size()];
            return llistaPacients.toArray(aPacients);

        } catch (SQLException ex) {
            System.out.println("ERROR EN SQL: " + ex.getMessage());
            return null;
        }
    }

    public static ResultSet pacientNifResult(String nif) {
        try {
            Connection join = ConexionDB.contectar();
            PreparedStatement states = null;
            String csql = "SELECT * FROM PACIENTS WHERE nifPacient = ?";
            states = join.prepareStatement(csql);
            states.setString(1, nif);
            states.executeQuery();
            return states.getResultSet();
        } catch (SQLException ex) {
            System.out.println("ERROR EN SQL: " + ex.getMessage());
            return null;
        }
    }

    public static ResultSet pacientNSS(String nss) {
        try {
            Connection join = ConexionDB.contectar();
            PreparedStatement states = null;
            String csql = "SELECT * FROM PACIENTS WHERE numSegSoc = ?";
            states = join.prepareStatement(csql);
            states.setString(1, nss);
            states.executeQuery();
            return states.getResultSet();
        } catch (SQLException ex) {
            System.out.println("ERROR EN SQL: " + ex.getMessage());
            return null;
        }
    }

    public static ResultSet pacientIEX(int codiHist) {
        try {
            Connection join = ConexionDB.contectar();
            PreparedStatement states = null;
            String csql = "SELECT * FROM PACIENTS WHERE codiHistorial = ?";
            states = join.prepareStatement(csql);
            states.setInt(1, codiHist);
            states.executeQuery();
            return states.getResultSet();
        } catch (SQLException ex) {
            System.out.println("ERROR EN SQL: " + ex.getMessage());
            return null;
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

    public static boolean modificarPacient(Pacient pacient) {
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
            System.out.println("ERROR EN SQL: " + ex.getMessage());
            return false;
        }
    }

    private static Pacient creaPAcientO(ResultSet rs, int numeroH) throws Exception {
        String nifPacient = rs.getString("nifPacient");        
        String nomPacient = rs.getString("nomPacient");
        String cognom1Pacient = rs.getString("cognom1Pacient");
        String cognom2Pacient = rs.getString("cognom2Pacient");
        String numSegSoc = rs.getString("numSegSoc");
        String telefon = rs.getString("telefon");
        String ciutat = rs.getString("ciutat");
        int codiPostal = rs.getInt("codiPostal");
        boolean casaObloque = rs.getBoolean("casaObloque");
        String tipo = rs.getString("tipo");
        String carrer = rs.getString("carrer");
        int numero = rs.getInt("numero");
        int planta = rs.getInt("planta");
        String porta = rs.getString("porta");

        return new Pacient(casaObloque, nomPacient, cognom1Pacient, cognom2Pacient, numSegSoc, nifPacient, telefon, tipo, carrer, numero, planta, porta, ciutat, codiPostal, numeroH);

    }

    public static boolean creaPacient(Pacient pacient, int iex) throws SQLException {
        Connection join = ConexionDB.contectar();
        PreparedStatement states = null;
        String consulta = "INSERT INTO PACIENTS (casaObloque, nomPacient, cognom1Pacient, cognom2Pacient,numSegSoc,nifPacient, telefon,tipo, carrer, numero, planta, porta,ciutat, codiPostal,codiHistorial)"
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
