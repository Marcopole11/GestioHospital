package m03.uf5.p01.grup5.gestiohospital.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import m03.uf5.p01.grup5.gestioHospital.model.Malaltia;
import m03.uf5.p01.grup5.gestioHospital.utils.ConexionDB;

public class DAOMalaltia {

    public static ResultSet MalaltiasResultat() {
        try {
            Connection join = ConexionDB.contectar();

            PreparedStatement states = join.prepareStatement("SELECT * FROM malalties;"); // REVISAR
            states.executeQuery();

            return states.getResultSet();

        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
            return null;
        }
    }

    public static Malaltia[] Malaltias() {
        try {

            ArrayList<Malaltia> malalt = new ArrayList<>();

            ResultSet resultat = MalaltiasResultat();

            while (resultat.next()) {
                malalt.add(newMalaltiaO(resultat));
            }

            Malaltia[] masMalaltias = new Malaltia[malalt.size()];
            return malalt.toArray(masMalaltias);

        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
            return null;
        }
    }

    public static ResultSet CodiMalaltiaResultat(int codi) {
        try {
            Connection join = ConexionDB.contectar();

            PreparedStatement states = join.prepareStatement("SELECT * FROM malalties WHERE codi = ?;"); // REVISAR
            states.setInt(1, codi);
            states.executeQuery();

            return states.getResultSet();

        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
            return null;
        }
    }

    public static Malaltia CodiMalaltia(int codi) {
        try {
            ResultSet resultat = CodiMalaltiaResultat(codi);
            resultat.next();
            return newMalaltiaO(resultat);

        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
            return null;
        }
    }

    public static boolean updateMalaltia(Malaltia malaltia) {
        try {

            Connection join = ConexionDB.contectar();

            CallableStatement state = join.prepareCall("{call Malaltia(?, ?, ?, ?, ?)}"); // REVISAR

            state.setInt(1, malaltia.getCodi());
            state.setString(2, malaltia.getNom());
            state.setString(3, malaltia.isCausaBaixaString());
            state.setString(4, malaltia.getTractament());
            state.setInt(5, (int) malaltia.getDuradaTractament().toDays());
            state.execute();

            return true;
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
            return false;
        }
    }

    public static boolean newMalaltia(Malaltia malaltia) throws SQLException {
        Connection join = ConexionDB.contectar();
        PreparedStatement states = null;

        String state = "INSERT INTO malalties VALUES(?,?,?,?,?)"; // REVISAR

        states = join.prepareStatement(state);
        states.setInt(1, malaltia.getCodi());
        states.setString(2, malaltia.getNom());
        states.setString(3, malaltia.isCausaBaixaString());
        states.setString(4, malaltia.getTractament());
        states.setInt(5, (int) malaltia.getDuradaTractament().toDays());
        states.executeUpdate();
        return true;
    }

    private static Malaltia newMalaltiaO(ResultSet resultat) throws SQLException {
        int codi = resultat.getInt("codiMalaltia");
        String nom = resultat.getString("nomMalaltia");
        boolean causabaixa = resultat.getString("causaBaixa").toLowerCase().equals("si");
        String tractament = resultat.getString("tractament");
        long durac = resultat.getInt("duracio");

        return new Malaltia(codi, nom, tractament, causabaixa, durac);
    }
}
