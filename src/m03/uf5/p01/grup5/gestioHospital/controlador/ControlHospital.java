/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.p01.grup5.gestioHospital.controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import m03.uf5.p01.grup5.gestioHospital.model.*;
import m03.uf5.p01.grup5.gestioHospital.vista.Ventana;
import m03.uf5.p01.grup5.gestioHospital.vista.vista;
import m03.uf5.p01.grup5.gestiohospital.DAO.DAOMalaltia;
import m03.uf5.p01.grup5.gestiohospital.DAO.DAOMetge;

/**
 *
 * @author Marc
 */
public class ControlHospital {

    public Hospital elObjeto;
    public String ruta;
    int historiales = 0;
    int direcciones = 1;

    public ControlHospital() {
        List<Adreca> adrecas = new ArrayList<Adreca>();
        try {
            ruta = Paths.get("").toAbsolutePath().toString() + "\\saved";
        } catch (Exception e) {
            ruta = System.getProperty("user.dir") + "\\saved";
        }
        File directorio = new File(ruta);
        directorio.mkdir();
        try {
            File Registro = new File(ruta + "\\adreca.csv");
            FileReader fr;
            BufferedReader br;
            String lect;
            int totalRead;
            if (Registro.exists()) {
                fr = new FileReader(Registro);
                br = new BufferedReader(fr);
                int[] orden = ordenar(br.readLine(), Adreca.CSV());
                elObjeto = new Hospital(new Adreca(br.readLine().split(";"), orden));
                totalRead = 1;
                while ((lect = br.readLine()) != null) {
                    totalRead++;
                    adrecas.add(new Adreca(lect.split(";"), orden));
                }
                fr.close();
                br.close();
                System.out.println(totalRead + " direcciones leidas");
            } else {
                elObjeto = new Hospital("Avenida", "Atlanta", 32, 0, null, "Barcelona", 3245);
            }
            Registro = new File(ruta + "\\metges.csv");
            if (Registro.exists()) {
                fr = new FileReader(Registro);
                br = new BufferedReader(fr);
                int[] orden = ordenar(br.readLine(), Metge.CSV());
                totalRead = 0;
                while ((lect = br.readLine()) != null) {
                    totalRead++;
                    elObjeto.metges.add(new Metge(lect.split(";"), adrecas));
                }
                fr.close();
                br.close();
                System.out.println(totalRead + " medicos leidos");
            }
            Registro = new File(ruta + "\\malaltia.csv");
            if (Registro.exists()) {
                fr = new FileReader(Registro);
                br = new BufferedReader(fr);
                int[] orden = ordenar(br.readLine(), Malaltia.CSV());
                totalRead = 0;
                while ((lect = br.readLine()) != null) {
                    totalRead++;
                    elObjeto.malalties.add(new Malaltia(lect.split(";")));
                }
                fr.close();
                br.close();
                System.out.println(totalRead + " malalties leidas");
            }
            Registro = new File(ruta + "\\pacients.csv");
            if (Registro.exists()) {
                fr = new FileReader(Registro);
                br = new BufferedReader(fr);
                int[] orden = ordenar(br.readLine(), Pacient.CSV());
                totalRead = 0;
                while ((lect = br.readLine()) != null) {
                    totalRead++;
                    elObjeto.pacients.add(new Pacient(totalRead, lect.split(";"), adrecas, elObjeto.malalties));
                }
                fr.close();
                br.close();
                System.out.println(totalRead + " pacientes leidos");
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public boolean guardarSession() {
        ////YA NO GUARDAMOS EN LOCAL
//        try {
//            File Registro = new File(ruta + "\\adreca.csv");
//            FileWriter fichero;
//            fichero = new FileWriter(Registro, false);
//            PrintWriter pw = new PrintWriter(fichero);
//            pw.println(formatCSV(elObjeto.adreca.CSV()));
//            pw.println(elObjeto.adreca.toCSV());
//            pw.close();
//            fichero.close();
//            Registro = new File(ruta + "\\metges.csv");
//            fichero = new FileWriter(Registro, false);
//            pw = new PrintWriter(fichero);
//            pw.println(formatCSV(Metge.CSV()));
//            for (Metge cada : elObjeto.metges) {
//                pw.println(cada.toCSV(addDireccion(cada.adreca)));
//            }
//            pw.close();
//            fichero.close();
//            Registro = new File(ruta + "\\malaltia.csv");
//            fichero = new FileWriter(Registro, false);
//            pw = new PrintWriter(fichero);
//            pw.println(formatCSV(Malaltia.CSV()));
//            for (Malaltia cada : elObjeto.malalties) {
//                pw.println(cada.toCSV());
//            }
//            pw.close();
//            fichero.close();
//            Registro = new File(ruta + "\\pacients.csv");
//            fichero = new FileWriter(Registro, false);
//            pw = new PrintWriter(fichero);
//            pw.println(formatCSV(Pacient.CSV()));
//            for (Pacient cada : elObjeto.pacients) {
//                //////// PROBLEMA DETECTADO //////////////
//                pw.println(cada.toCSV(addDireccion(cada.adreca)));
//                
//            }
//            pw.close();
//            fichero.close();
//            return true;
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//            return false;
//        }
        return true;
    }

    static String formatCSV(String[] asd) {
        String printo = asd[0];
        for (int i = 1; i < asd.length; i++) {
            printo += ";" + asd[i];
        }
        return printo;
    }

    public static int[] ordenar(String lectura, String[] keys) {
        String[] fileNames = lectura.split(";");
        int[] retorno = new int[fileNames.length];
        for (int i = 0; i < retorno.length; i++) {
            retorno[i] = Arrays.asList(keys).indexOf(fileNames[i]);
        }
        return retorno;
    }

    private int addDireccion(Adreca adreca) throws IOException {
        File Registro = new File(ruta + "\\adreca.csv");
        FileWriter fichero;
        fichero = new FileWriter(Registro, true);
        PrintWriter pw = new PrintWriter(fichero);
        pw.println(adreca.toCSV());
        pw.close();
        fichero.close();
        direcciones++;
        return direcciones;
    }
    // A PARTIR DE AQUI VAN LAS COMPROBACIONES

    public Visitando registrarVisita(String getdia, String getmes, String getany, String gethora, String getminuts) {
        return new Visitando(
            Integer.parseInt(getdia),
            Integer.parseInt(getmes),
            Integer.parseInt(getany),
            margenIn(23,Integer.parseInt(gethora)),
            margenIn(59,Integer.parseInt(getminuts)));
    }
      
    
    public void comprobarNouPacient(String Nom,String Pcognom,String Scognom,String Nss,String Nif,String Tlf,String Via,String Calle,String Num,String cob,String Numpl,String Piso,String Ciudad,String CP)throws Exception{
        String nom=Nom, primerCognom=Pcognom,segonCognom=Scognom, numSegSocial=Nss, nif=Nif,tel=Tlf,tipo=Via,carrer=Calle,piso=Piso,ciudad=Ciudad;
        int numero=Integer.parseInt(Num),planta=Integer.parseInt(Numpl),codiPostal=Integer.parseInt(CP);
        boolean casaObloque=false;
        if(cob.equals("Si")){
            casaObloque=true;
        }else if(cob.equals("No")){
            casaObloque=false;
        }
        elObjeto.nouPacient(casaObloque, nom, primerCognom, segonCognom, numSegSocial, nif, tel, tipo, carrer, numero, planta, piso, ciudad, codiPostal);
    }
    
    public void comprobarNouMetge(String Nom,String Pcognom,String Scognom,String Nss,String Nif,String Tlf,String Via,String Calle,String Num,String cob,String Numpl,String Piso,String Ciudad,String CP,String compteCorrent,String salari)throws Exception{
        String nom=Nom, primerCognom=Pcognom,segonCognom=Scognom, numSegSocial=Nss, nif=Nif,tel=Tlf,tipo=Via,carrer=Calle,piso=Piso,ciudad=Ciudad,codiCompteCorrent=compteCorrent;
        int numero=Integer.parseInt(Num),planta=Integer.parseInt(Numpl),codiPostal=Integer.parseInt(CP),salariMensual=Integer.parseInt(salari);
        boolean casaObloque=false;
        if(cob.equals("Si")){
            casaObloque=true;
        }else if(cob.equals("No")){
            casaObloque=false;
        }
        elObjeto.nouMetge(casaObloque, nom, primerCognom, segonCognom, numSegSocial, nif, tel, salariMensual, codiCompteCorrent, tipo, carrer, numero, planta, piso, ciudad, codiPostal);
        DAOMetge.newMetge(elObjeto.metges.get(elObjeto.metges.size()-1));
    }
    
    public void comprobarNovaMalaltia(String Malal,String Motiu,String Descriu, String Durada) throws Exception{
        String nom=Malal,tractament=Descriu;
        int duradaTractament=Integer.parseInt(Durada);
        boolean causaBaixa=false;
        if(Motiu.equals("Si")){
            causaBaixa=true;
        }else if(Motiu.equals("No")){
            causaBaixa=false;
        }
        elObjeto.novaMalatia(nom, tractament, causaBaixa, duradaTractament);
        DAOMalaltia.newMalaltia(elObjeto.malalties.get(elObjeto.malalties.size()-1));
    }
    
    public int comprobarDniPacient(String tempnif) throws Exception {
        Pacient retorno = elObjeto.pacient(tempnif);
        if (retorno != null) {
            return retorno.historial.codi;
        } else {
            throw (new Exception("Este dni no corresponde a ningun pacient, introduce otro. "));
        }
    }

    public int comprobarNSSPacient(String segsoc) throws Exception {
        Pacient retorno = elObjeto.pacientSegSoc(segsoc);
        if (retorno != null) {
            return retorno.historial.codi;
        } else {
            throw (new Exception("Este numeros de la seguridad social no corresponde a ningun pacient, introduce otro. "));
        }
    }

    public void comprobarCodiPacient(String codi) throws Exception {
        int num = Integer.parseInt(codi);
        if (elObjeto.pacients.size() > num && num > -1) {

        } else {
            throw (new Exception("Este codi de historial no existe. "));
        }
    }

    public Metge comprobarDniMetge(String tempnifmetge) throws Exception {
        Metge metge = elObjeto.metge(tempnifmetge);
        if (metge != null) {
            return metge;
        } else {
            throw (new Exception("Este dni no corresponde a ningun pacient, introduce otro. "));
        }     
    }
    
    public Metge comprobarCodiMetge(String codmetge) throws Exception {
        int codi = Integer.parseInt(codmetge);
        Metge metge = elObjeto.metges.get(codi);
        if (metge != null) {
            return metge;
        } else {
            throw (new Exception("Este dni no corresponde a ningun pacient, introduce otro. "));
        }  

    }

    // FIN COMPROBACIONES
    /**
     * Demana una entada numérica per teclat pero no accepta negatius i te un
     * marge valid.
     *
     * @param maximo La quantitat que no ha de superar el nombre introduit
     * @param numero El numero rebut
     * @return El nombre introduit per l'usuari si está dins del rang o la
     * resposta definida per ceroToMax si es troba fora de rang.
     */
    public static int margenIn(int maximo, int numero) {
        if (numero < 0) {
            numero = -numero;
        }
        if (numero >= maximo) {
            return maximo;
        } else {
            return numero;
        }
    }
}
