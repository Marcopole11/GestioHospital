package m03.uf5.p01.grup5.gestioHospital.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionDB {
    public static String url = "jdbc:mysql://127.0.0.1:3307/hospital?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC ",
            usuario = "root",
            pass = "123456",
            clase = "com.mysql.jdbc.Driver";
    
    public static Connection contectar(){
        Connection conexion = null;
        try{
            Class.forName(clase);
            conexion = (Connection) DriverManager.getConnection(url,usuario,pass);
            System.out.println("Conexión establecida");
        }catch (Exception e){
            System.out.println("Error de conexión a la BD en ConexionDB.java");
            System.out.println(e.getMessage());
        }
        return conexion;
    }
}