/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.p01.grup5.gestioHospital.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import m03.uf5.p01.grup5.gestioHospital.model.Malaltia;
import m03.uf5.p01.grup5.gestioHospital.model.Pacient;
import m03.uf5.p01.grup5.gestioHospital.model.Visita;
import m03.uf5.p01.grup5.gestioHospital.utils.ConexionDB;

/**
 *
 * @author raulk, Roger, Marco
 */
public class DAOVisita {

    public static ResultSet getVisitaResultSet() throws Exception {
        try {
            Connection join = ConexionDB.contectar();
            PreparedStatement states = join.prepareStatement("SELECT * FROM VISITES ORDER BY nifPac ASC,orden ASC;");
            states.executeQuery();
            return states.getResultSet();
        } catch (SQLException ex) {
           throw (new Exception("Hubo un error: " + ex.getMessage()));
        }
    }

    public static ResultSet getVisitaNifRS(String dni) throws Exception {
        try {
            Connection join = ConexionDB.contectar();
            PreparedStatement states = join.prepareStatement("SELECT * FROM VISITES WHERE dniPacient = ?;");
            states.setString(1, dni);
            states.executeQuery();
            return states.getResultSet();
        } catch (SQLException ex) {
          throw (new Exception("Hubo un error: " + ex.getMessage()));
        }
    }

    public static ResultSet getVisitaIEXResultSet(int codiHistorial) throws Exception {
        try {
            Connection join = ConexionDB.contectar();
            PreparedStatement states = join.prepareStatement("SELECT * FROM VISITES WHERE dniPacient = (SELECT codiHistorial FROM PACIENTS WHERE codiHistorial = ?);");
            states.setInt(1, codiHistorial);
            states.executeQuery();
            return states.getResultSet();
        } catch (SQLException ex) {
            throw (new Exception("Hubo un error al conectar con la Base de datos: " + ex.getMessage()));
        }
    }

    public static boolean createVisita(Visita visita) throws Exception {
        try {
            Connection join = ConexionDB.contectar();
            PreparedStatement states = null;
            String consulta = "INSERT INTO VISITES"
                    + " (fecha, codiMalaltia, dniMetge, dniPacient)"
                    + " VALUES (?,?,?,?)";
            states = join.prepareStatement(consulta);
            states.setString(1, visita.getData().format(DateTimeFormatter.ofPattern("uuuu-MM-d HH:mm:ss")));
            states.setInt(2, visita.getDiagnostic().getCodi());
            states.setString(3, visita.getMetge());
            states.setString(4, visita.getDni());
            states.executeUpdate();
            return true;

        } catch (NullPointerException ex) {
           throw (new Exception("Hubo un error: " + ex.getMessage()));
        }
    }
    public static List<Pacient> rellenarHistoriales(List<Pacient> pacientes,List<Malaltia> malalties) throws Exception{
        try {
            ResultSet rs = getVisitaResultSet();
            String actualNif = "X";
            int actualP = -1;
            while (rs.next()) {
                if(!rs.getString("nifPac").equals(actualNif)){
                    actualNif = rs.getString("nifPac");
                    actualP = buscarDNI(pacientes,actualNif);
                }
                if(actualP != -1){
                    Date date = rs.getDate("fecha");
                    LocalDate dia = date.toLocalDate();
                    LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneOffset.UTC);
                    pacientes.get(actualP).historial.visites.visitar(
                            dia.getDayOfMonth(),
                            dia.getMonthValue(),
                            dia.getYear(),
                            ((ldt.getHour()*100)+ldt.getMinute())/100,
                            rs.getString("nomMet"), malalties.get(rs.getInt("codiMalaltia")));
                }
            }
            return pacientes;
        } catch (SQLException ex) {
            throw(new Exception("Se ha perdido el acceso a la Base de datos: "+ex.getMessage()));
        }
    }
    public static int buscarDNI(List<Pacient> pacientes, String DNI){
        for(int i = 0; i < pacientes.size(); i++){
            if(pacientes.get(i).nif.equals(DNI)){
                return i;
            }
        }
        return -1;
    }
}
