package m03.uf5.p01.grup5.gestiohospital.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

//    public static Malaltia CodiMalaltia(int codi) {
//        try {
//            ResultSet resultat = CodiMalaltiaResultat(codi);
//            resultat.next();
//            return newMalaltiaO(resultat);
//
//        } catch (SQLException ex) {
//            System.out.println("ERROR: " + ex.getMessage());
//            return null;
//        }
//    }

    public static boolean updateMalaltia(Malaltia malaltia) throws Exception {
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
            throw(new Exception("Hubo un error al actualizar la Malaltia en la Base de datos: "+ex.getMessage()));
        }
    }

    public static boolean newMalaltia(Malaltia malaltia) throws Exception{
        try {
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
        } catch (SQLException ex) {
            throw(new Exception("Hubo un error al registrar la Malaltia en la Base de datos: "+ex.getMessage()));
        }
    }

    private static Malaltia newMalaltiaO(ResultSet resultat) throws Exception{
        try {
            int codi = resultat.getInt("codiMalaltia");
            String nom = resultat.getString("nomMalaltia");
            boolean causabaixa = resultat.getString("causaBaixa").toLowerCase().equals("si");
            String tractament = resultat.getString("tractament");
            long durac = resultat.getInt("duracio");

            return new Malaltia(codi, nom, tractament, causabaixa, durac);
        } catch (SQLException ex) {
            throw(new Exception("Se ha perdido el acceso a la Base de datos: "+ex.getMessage()));
        }
    }
}
