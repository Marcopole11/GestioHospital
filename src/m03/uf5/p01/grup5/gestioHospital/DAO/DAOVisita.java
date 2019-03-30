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
 * @author raulk, Roger, Marco
 */
public class DAOVisita {

    public static ResultSet getVisitaResultSet() {
        try {
            Connection join = ConexionDB.contectar();
            PreparedStatement states = join.prepareStatement("SELECT * FROM VISITES ORDER BY fecha DESC;");
            states.executeQuery();
            return states.getResultSet();
        } catch (SQLException ex) {
            System.out.println("ERROR EN SQL: " + ex.getMessage());
            return null;
        }
    }

    public static ResultSet getVisitaNifRS(String dni) {
        try {
            Connection join = ConexionDB.contectar();
            PreparedStatement states = join.prepareStatement("SELECT * FROM VISITES WHERE dniPacient = ?;");
            states.setString(1, dni);
            states.executeQuery();
            return states.getResultSet();
        } catch (SQLException ex) {
            System.out.println("ERROR EN SQL: " + ex.getMessage());
            return null;
        }
    }

    public static ResultSet getVisitaIEXResultSet(int codiHistorial) {
        try {
            Connection join = ConexionDB.contectar();
            PreparedStatement states = join.prepareStatement("SELECT * FROM VISITES WHERE dniPacient = (SELECT codiHistorial FROM PACIENTS WHERE codiHistorial = ?);");
            states.setInt(1, codiHistorial);
            states.executeQuery();
            return states.getResultSet();
        } catch (SQLException ex) {
            System.out.println("ERROR EN SQL: " + ex.getMessage());
            return null;
        }
    }

    public static boolean createVisita(Visita visita) throws SQLException {
        try {
            Connection join = ConexionDB.contectar();
            PreparedStatement states = null;
            String consulta = "INSERT INTO VISITES"
                    + " (fecha, codiMalaltia, dniMetge, dniPacient)"
                    + " VALUES (?,?,?,?)";
            states = join.prepareStatement(consulta);
            states.setString(1, visita.getData().format(DateTimeFormatter.ofPattern("uuuu-MM-d HH:mm:ss")));
            // states.setInt(2, visita.getMalaltia().getCodi());
            // states.setString(3, visita.getMetge().getNif());
            // states.setString(4, visita.getDni());
            states.executeUpdate();
            return true;

        } catch (NullPointerException ex) {
            System.out.println("ERROR NULL OBJ VISITA: " + ex.getMessage());
            return false;
        }
    }
}
