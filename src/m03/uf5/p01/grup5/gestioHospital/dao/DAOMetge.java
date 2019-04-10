package m03.uf5.p01.grup5.gestioHospital.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import m03.uf5.p01.grup5.gestioHospital.model.Metge;
import m03.uf5.p01.grup5.gestioHospital.utils.ConexionDB;

public class DAOMetge {

    public static ResultSet MetgesResultat() throws Exception {
        try {
            Connection join = ConexionDB.contectar();
            PreparedStatement states = join.prepareStatement("SELECT * FROM metges;");
            states.executeQuery();
            return states.getResultSet();
        } catch (SQLException ex) {
            throw (new Exception("Hubo un error al conectar la Base de datos: " + ex.getMessage()));
        }
    }

    public static List<Metge> Metges() throws Exception {
        try {
            ArrayList<Metge> malalt = new ArrayList<>();

            ResultSet resultat = MetgesResultat();

            while (resultat.next()) {
                malalt.add(newMetgeO(resultat));
            }
            Metge[] masMalaltias = new Metge[malalt.size()];
            return malalt;

        } catch (SQLException ex) {
            throw (new Exception("Hubo un error al conectar la Base de datos: " + ex.getMessage()));
        }
    }

    public static ResultSet DniMetgeResultat(String dni) throws Exception {
        try {
            Connection join = ConexionDB.contectar();
            PreparedStatement states = join.prepareStatement("SELECT * FROM metges WHERE nifMet = ?;");
            states.setString(1, dni);
            states.executeQuery();
            return states.getResultSet();
        } catch (SQLException ex) {
            throw (new Exception("Hubo un error: " + ex.getMessage()));
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
        try {
            boolean COB = resultat.getBoolean("casaObloqueMet");
            int numEmpleatMet = resultat.getInt("numEmpleatMet");
            String nifMet = resultat.getString("nifMet");
            String nomMetge = resultat.getString("nomMet");
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

            return new Metge(COB, nomMetge, primerCognomMet, segonCognomMet, numSegSocialMet, nifMet, telMet, numEmpleatMet, salariMensualMet, codiCompteCorrentMet, tipoMet, carrerMet, numeroMet, plantaMet, portaMet, ciutatMet, codiPostalMet);
        } catch (SQLException ex) {
            throw (new Exception("Hubo un error al crear el metge en la Base de datos" + ex.getMessage()));
        }

    }

    public static ResultSet cssMetge(String css) throws Exception {
        try {
            Connection join = ConexionDB.contectar();
            PreparedStatement states = join.prepareStatement("SELECT * FROM metges WHERE numSegSocialMet = ?;");
            states.setString(1, css);
            states.executeQuery();
            return states.getResultSet();
        } catch (SQLException ex) {
            throw (new Exception("Hubo un error al crear el metge en la Base de datos" + ex.getMessage()));
        }
    }

    /*public static boolean updateMetge(Metge metge) throws Exception {
        try {
            Connection join = ConexionDB.contectar();
            CallableStatement states = join.prepareCall("{call actualizaMetge(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
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
            states.setString(12, metge.getAdreca().getPlanta() + "");
            states.setString(13, metge.getAdreca().getPorta());
            states.execute();
            return true;
        } catch (SQLException ex) {
            throw (new Exception("Hubo un error al actlualizar el médico en la Base de datos: " + ex.getMessage()));
        }
    }*/
    public static boolean newMetge(Metge metge) throws Exception {
        Connection join = ConexionDB.contectar();
        PreparedStatement states;
        try {
            states = join.prepareStatement("INSERT INTO metges "
                    + "(casaObloqueMet,nomMetge,primerCognomMet,segonCognomMet,numSegSocialMet,nifMet,telMet,numEmpleatMet,salariMensualMet,codiCompteCorrentMet,tipoMet,carrerMet,numeroMet,plantaMet,portaMet,ciutatMet,codiPostalMet)"
                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            states.setBoolean(1, metge.getCasaOBloque());
            states.setString(2, metge.getNom());
            states.setString(3, metge.getCognom1());
            states.setString(4, metge.getCognom2());
            states.setString(5, metge.getNumSegSocial());
            states.setString(6, metge.getNif());
            states.setString(7, metge.getTelefon());
            states.setInt(8, metge.getNumEmpleat());
            states.setInt(9, metge.getSalariMensual());
            states.setString(10, metge.getCodiCompteCorrent());
            states.setString(11, metge.getAdreca().getTipo());
            states.setString(12, metge.getAdreca().getCarrer());
            states.setInt(13, metge.getAdreca().getNumero());
            states.setInt(14, metge.getAdreca().getPlanta());
            states.setString(15, metge.getAdreca().getPorta());
            states.setString(16, metge.getAdreca().getCiutat());
            states.setLong(17, metge.getAdreca().getCodiPostal());
            states.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw (new Exception("Hubo un error al registrar el médico en la Base de datos: " + ex.getMessage()));
        }
    }
}
