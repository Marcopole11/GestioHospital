/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.p01.grup5.gestioHospital.dao;

/**
 *
 * @author Marc, Raul, Roger
 */
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import m03.uf5.p01.grup5.gestioHospital.model.Malaltia;
import m03.uf5.p01.grup5.gestioHospital.utils.ConexionDB;

public class DAOMalaltia {

    public static ResultSet MalaltiasResultat() throws Exception {
        try {
            Connection join = ConexionDB.contectar();

            PreparedStatement states = join.prepareStatement("SELECT * FROM malalties;");
            states.executeQuery();

            return states.getResultSet();

        } catch (SQLException ex) {
            throw (new Exception("Hubo un error al conectar la Base de datos: " + ex.getMessage()));
        }
    }

    public static List<Malaltia> MalaltiasIN() throws Exception {
        try {

            ArrayList<Malaltia> malalt = new ArrayList<>();

            ResultSet resultat = MalaltiasResultat();

            while (resultat.next()) {
                malalt.add(newMalaltiaO(resultat));
            }

            Malaltia[] masMalaltias = new Malaltia[malalt.size()];
            return malalt;

        } catch (SQLException ex) {
            throw (new Exception("Hubo un error al actualizar en la Base de datos: " + ex.getMessage()));
        }
    }

    public static ResultSet CodiMalaltiaResultat(int codi) throws Exception {
        try {
            Connection join = ConexionDB.contectar();

            PreparedStatement states = join.prepareStatement("SELECT * FROM malalties WHERE codi = ?;");
            states.setInt(1, codi);
            states.executeQuery();

            return states.getResultSet();

        } catch (SQLException ex) {
            throw (new Exception("Hubo un error con la Base de datos: " + ex.getMessage()));
        }
    }

    /*public static Malaltia CodiMalaltia(int codi) {
        try {
            ResultSet resultat = CodiMalaltiaResultat(codi);
            resultat.next();
            return newMalaltiaO(resultat);

        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
            return null;
        }
    }*/
    
  /* public static boolean updateMalaltia(Malaltia malaltia) throws Exception {
        try {

            Connection join = ConexionDB.contectar();

            CallableStatement state = join.prepareCall("{call malalties(?, ?, ?, ?, ?)}");

            state.setInt(1, malaltia.getCodi());
            state.setString(2, malaltia.getNom());
            state.setString(3, malaltia.isCausaBaixaString());
            state.setString(4, malaltia.getTractament());
            state.setInt(5, (int) malaltia.getDuradaTractament().toDays());
            state.execute();

            return true;
        } catch (SQLException ex) {
            throw (new Exception("Hubo un error al actualizar la Malaltia en la Base de datos: " + ex.getMessage()));
        }
    }*/

    public static boolean newMalaltia(Malaltia malaltia) throws Exception {
        try {
            Connection join = ConexionDB.contectar();
            PreparedStatement states = null;

            String state = "INSERT INTO malalties VALUES(?,?,?,?,?)";

            states = join.prepareStatement(state);
            states.setInt(1, malaltia.getCodi());
            states.setString(2, malaltia.getNom());
            states.setString(3, malaltia.getTractament());
            states.setBoolean(4, malaltia.isCausaBaixa());
            states.setInt(5, (int) malaltia.getDuradaTractament().toDays());
            states.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw (new Exception("Hubo un error al crear la Malaltia en la Base de datos: " + ex.getMessage()));
        }
    }

    private static Malaltia newMalaltiaO(ResultSet resultat) throws Exception {
        try {
            int codi = resultat.getInt("codi");
            String nom = resultat.getString("nom");
            boolean causabaixa = resultat.getBoolean("causaBaixa");
            String tractament = resultat.getString("tractament");
            long durac = resultat.getInt("duradaTractament");

            return new Malaltia(codi, nom, tractament, causabaixa, durac);
        } catch (SQLException ex) {
            throw (new Exception("Hubo un error al crear la Malaltia en la Base de datos: " + ex.getMessage()));
        }
    }
}

