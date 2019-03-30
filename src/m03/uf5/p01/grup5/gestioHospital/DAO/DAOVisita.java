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
import java.time.format.DateTimeFormatter;
import m03.uf5.p01.grup5.gestioHospital.model.Visita;
import m03.uf5.p01.grup5.gestioHospital.utils.ConexionDB;
/**
 *
 * @author raulk
 */
public class DAOVisita {
    public static ResultSet getVisitaResultSet() {
        try {
            Connection conn = ConexionDB.contectar();
            PreparedStatement sentencia = conn.prepareStatement("SELECT * FROM VISITES ORDER BY fecha DESC;");
            sentencia.executeQuery();
            return sentencia.getResultSet();
        } catch (SQLException ex) {
            System.out.println("ERROR CONSULTA SQL: " + ex.getMessage());
            return null;
        }
    }

    public static ResultSet getVisitaDNIRS(String dni) {
        try {
            Connection conn = ConexionDB.contectar();
            PreparedStatement sentencia = conn.prepareStatement("SELECT * FROM VISITES WHERE dniPacient = ?;");
            sentencia.setString(1, dni);
            sentencia.executeQuery();
            return sentencia.getResultSet();
        } catch (SQLException ex) {
            System.out.println("ERROR CONSULTA SQL: " + ex.getMessage());
            return null;
        }
    }
public static ResultSet getVisitaCodiHistorialResultSet(int codiHistorial) {
        try {
            Connection conn = ConexionDB.contectar();
            PreparedStatement sentencia = conn.prepareStatement("SELECT * FROM VISITES WHERE dniPacient = (SELECT codiHistorial FROM PACIENTS WHERE codiHistorial = ?);");
            sentencia.setInt(1, codiHistorial);
            sentencia.executeQuery();
            return sentencia.getResultSet();
        } catch (SQLException ex) {
            System.out.println("ERROR CONSULTA SQL: " + ex.getMessage());
            return null;
        }
    }

    public static boolean createVisita(Visita visita) throws SQLException {
        try {
            Connection con = ConexionDB.contectar();
            PreparedStatement sentencia = null;
            String consulta = "INSERT INTO VISITES"
                    + " (fecha, codiMalaltia, dniMetge, dniPacient)"
                    + " VALUES (?,?,?,?)";
            sentencia = con.prepareStatement(consulta);
            sentencia.setString(1, visita.getData().format(DateTimeFormatter.ofPattern("uuuu-MM-d HH:mm:ss")));
            sentencia.setInt(2, visita.getMalaltia().getCodi());
            sentencia.setString(3, visita.getMetge().getNif());
            sentencia.setString(4, visita.getDni());
            sentencia.executeUpdate();
            return true;

        } catch (NullPointerException ex) {
            System.out.println("ERROR NULL OBJ VISITA: " + ex.getMessage());
            return false;
        }
    }
}
