package m03.uf5.p01.grup5.gestioHospital.controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import m03.uf5.p01.grup5.gestioHospital.model.Persona;

/**
 *
 * @author Raul Barrero, Marc Cardenas y Roger Miralles
 */
public class csv {

    public static void leeCSV(String nombreFichero) throws FileNotFoundException {
        File fichero = new File(nombreFichero);
        Scanner sc = new Scanner(fichero);
        while (sc.hasNext()) {
            String[] datos = sc.nextLine().split(",");
            try{
            Persona p = new Persona(datos[0].equals("true"), datos[1],datos[2],datos[3],datos[4],datos[5],datos[6],datos[7],datos[8],Integer.parseInt(datos[9]),Integer.parseInt(datos[10]),datos[11],datos[12],Integer.parseInt(datos[13])) {};
            System.out.println(p);
            }catch(Exception e){
                System.out.println("Error al leer el fichero "+e.getMessage());
            }
        }
    }

    public static void escribeCSV(String archivo, Persona p) {
        try {
            FileWriter fichero = new FileWriter(archivo);
            fichero.append(p.nom + ",");
            fichero.append(p.cognom1 + ",");
            fichero.append(p.numSegSocial + ",");
            fichero.append(p.nif + ",");
            fichero.append(p.telefon + ",");
            fichero.append(p.adreca + "\n");
            fichero.close();
        } catch (IOException e) {
            System.out.println("Error al crear el fichero "+e.getMessage());
        }
    }

          // Ejemplo de uso para leer;  leeCSV("personas.csv");
          
          // Ejemplo de uso para escribir
          //Persona p1 = new Persona("datos", "datos", "datos");
          // escribeCSV("personas.csv", p1);

}
