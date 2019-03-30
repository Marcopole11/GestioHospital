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
import m03.uf5.p01.grup5.gestioHospital.model.Adreca;
import m03.uf5.p01.grup5.gestioHospital.model.Pacient;
import m03.uf5.p01.grup5.gestioHospital.utils.ConexionDB;
public class DAOPaciente {
    public static ResultSet getPacientsResultSet(){
        try {
            Connection join = ConexionDB.contectar();
            PreparedStatement states=join.prepareStatement("SELECT * FROM PACIENTS;");
            states.executeQuery();
            return states.getResultSet();
        } catch (Exception e) {
            System.out.println("ERROR EN SQL:"+e.getMessage());
            return null;
        }
    }
    public static Pacient[] getAllPacients() {
        try {
            ArrayList<Pacient> listPacients = new ArrayList<>();
            ResultSet rs = getPacientsResultSet();
            while (rs.next()) {
                listPacients.add(createPacientObj(rs));
            }
            Pacient[] arrayPacients = new Pacient[listPacients.size()];
            return listPacients.toArray(arrayPacients);

        } catch (SQLException ex) {
            System.out.println("ERROR CONSULTA SQL: " + ex.getMessage());
            return null;
        }
    }
public static ResultSet pacienteByNifRS(String nif) {
        try {
            Connection join = ConexionDB.contectar();
            PreparedStatement states = null;
            String consulta = "SELECT * FROM PACIENTS WHERE nifPacient = ?";
            states = join.prepareStatement(consulta);
            states.setString(1, nif);
            states.executeQuery();
            return states.getResultSet();
        } catch (SQLException ex) {
            System.out.println("ERROR CONSULTA SQL: " + ex.getMessage());
            return null;
        }
    }
public static ResultSet pacienteByNSS(String nss) {
        try {
            Connection join = ConexionDB.contectar();
            PreparedStatement states = null;
            String consulta = "SELECT * FROM PACIENTS WHERE numSegSoc = ?";
            states = join.prepareStatement(consulta);
            states.setString(1, nss);
            states.executeQuery();
            return states.getResultSet();
        } catch (SQLException ex) {
            System.out.println("ERROR CONSULTA SQL: " + ex.getMessage());
            return null;
        }
    }

    public static ResultSet pacienteByCodiHistorial(int codiHist) {
        try {
            Connection join = ConexionDB.contectar();
            PreparedStatement states = null;
            String consulta = "SELECT * FROM PACIENTS WHERE codiHistorial = ?";
            states = join.prepareStatement(consulta);
            states.setInt(1, codiHist);
            states.executeQuery();
            return states.getResultSet();
        } catch (SQLException ex) {
            System.out.println("ERROR CONSULTA SQL: " + ex.getMessage());
            return null;
        }
    }
public static Pacient getPacientByNif(String DNI) {
        try {
            ResultSet rs = pacienteByNifRS(DNI);
            rs.next();
            return createPacientObj(rs);
        } catch (SQLException ex) {
            System.out.println("ERROR CONSULTA SQL: " + ex.getMessage());
            return null;
        }
    }

    public static boolean modificaPacient(Pacient pacient) {
        try {
            Connection join = ConexionDB.contectar();
            CallableStatement states = null;
            String consulta = "{call actualizaPacient(?,?,?,?,?,?,?,?,?,?,?,?)}";
            states = join.prepareCall(consulta);
            states.setString(1, pacient.getNif());
            states.setString(2, pacient.getNom());
            states.setString(3, pacient.getCognom1());
            states.setString(4, pacient.getCognom2());
            states.setString(5, pacient.getNumSegSocial());
            states.setString(6, pacient.getTelefon());
            states.setString(7, pacient.getAdreca().getCiutat());
            states.setLong(8, pacient.getAdreca().getCodiPostal());
            states.setString(9, pacient.getAdreca().getCarrer());
            states.setInt(10, pacient.getAdreca().getNumero());
            states.setInt(11, pacient.getAdreca().getPlanta());
            states.setString(12, pacient.getAdreca().getPorta());
            states.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("ERROR CONSULTA SQL: " + ex.getMessage());
            return false;
        }
    }
    private static Pacient createPacientObj(ResultSet rs) throws SQLException {
        String nifPacient = rs.getString("nifPacient");
        int codiHistorial = rs.getInt("codiHistorial");
        String nomPacient = rs.getString("nomPacient");
        String cognom1Pacient = rs.getString("cognom1Pacient");
        String cognom2Pacient = rs.getString("cognom2Pacient");
        String numSegSoc = rs.getString("numSegSoc");
        String telefon = rs.getString("telefon");
        String ciutat = rs.getString("ciutat");
        long codiPostal = rs.getLong("codiPostal");
        String carrer = rs.getString("carrer");
        int numero = rs.getInt("numero");
        String planta = rs.getString("planta");
        String porta = rs.getString("porta");

        Adreca adreca = new Adreca(ciutat, codiPostal, carrer, numero, planta, porta);
        return new Pacient(nomPacient, cognom1Pacient, cognom2Pacient, numSegSoc, nifPacient, telefon, adreca);
    }
    
    public static boolean createPaciente(Pacient pacient) throws SQLException {
        Connection join = ConexionDB.contectar();
        PreparedStatement states = null;
        String consulta = "INSERT INTO PACIENTS (nifPacient, codiHistorial, numSegSoc, nomPacient, cognom1Pacient, cognom2Pacient, telefon, ciutat, codiPostal, carrer, numero, planta, porta)"
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

        states = join.prepareStatement(consulta);
        states.setString(1, pacient.getNif());
        states.setInt(2, pacient.getHistorial().getCodi());
        states.setString(3, pacient.getNumSegSocial());
        states.setString(4, pacient.getNom());
        states.setString(5, pacient.getCognom1());
        states.setString(6, pacient.getCognom2());
        states.setString(7, pacient.getTelefon());
        states.setString(8, pacient.getAdreca().getCiutat());
        states.setLong(9, pacient.getAdreca().getCodiPostal());
        states.setString(10, pacient.getAdreca().getCarrer());
        states.setInt(11, pacient.getAdreca().getNumero());
        states.setInt(12, pacient.getAdreca().getPlanta());
        states.setString(13, pacient.getAdreca().getPorta());
        states.executeUpdate();
        return true;
    }
    
}
