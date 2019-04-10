package m03.uf5.p01.grup5.gestioHospital.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionDB {
    public static String url = "jdbc:mysql://127.0.0.1:3307/hospital_grup5?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC ",
            usuario = "root",
            pass = "123456";
    
    public static Connection contectar(){
        Connection conexion = null;
        try{
            conexion = DriverManager.getConnection(url,usuario,pass);
            System.out.println("Conexión establecida");
        }catch (Exception e){
            System.out.println("Error de conexión a la BD en ConexionDB.java");
            System.out.println(e.getMessage());
        }
        return conexion;
    }

    public PreparedStatement prepareStatement(String select__from_malalties) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
