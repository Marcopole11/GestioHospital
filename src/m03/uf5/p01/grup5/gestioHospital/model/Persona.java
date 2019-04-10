
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.p01.grup5.gestioHospital.model;

/**
 *
 * @author Marc Cardenas, Raul Barrero, Roger Miralles
 */
public abstract class Persona {
    
    public String nom, cognom1, cognom2, numSegSocial, nif, telefon;
    public Adreca adreca;
    boolean cOb;

    public Persona(boolean casaObloque,String nom, String primerCognom, String segonCognom, String numSegSocial, String nif, String tel, String tipo,String carrer,int numero,int planta,String porta,String ciudad,int codiPostal) throws Exception {
        create(nom, primerCognom, segonCognom, numSegSocial, nif, tel);
        this.cOb=casaObloque;
        try{
            if(cOb){// si es una casa es TRUE
                this.adreca = new Adreca(tipo,carrer,numero,ciudad,codiPostal);
            }else{// si es un bloque de pisos es FALSE
                this.adreca = new Adreca(tipo,carrer,numero,planta,porta,ciudad,codiPostal);
            }            
        } catch (Exception e){
            throw(new Exception("El formato de la direcciÛn no es correcto, asegurate de introducir tambiÈn ciudad y cÛdigo postal."));
        }
    }
    public Persona(String nom, String primerCognom, String segonCognom, String numSegSocial, String nif, String tel, Adreca adreca) throws Exception {
        create(nom, primerCognom, segonCognom, numSegSocial, nif, tel);
        this.cOb=adreca.porta==null;
        this.adreca = adreca;
    }
    private void create(String nom, String primerCognom, String segonCognom, String numSegSocial, String nif, String tel) throws Exception{
        if (nom.length() > 0 && primerCognom.length() > 0 && segonCognom.length() > 0) {
            if (esAlfanumerico(nom) || esAlfanumerico(primerCognom) || esAlfanumerico(segonCognom)) {
                this.nom = nom;
                cognom1 = primerCognom;
                cognom2 = segonCognom;
            } else {
                throw(new Exception("El nombre o apellidos no son alfanumÈricos"));
            }
        } else {
            throw(new Exception("Faltan datos"));
        }
        this.numSegSocial = checkNumSeg(numSegSocial); 
        this.nif = checkDNI(nif);
        checkTel(tel);
    }
    private static boolean esAlfanumerico(String dato) {
        if (isNotAlfanumeric(dato)) {
            String otros = "·ÈÌÛ˙‡ËÚ¡…Õ”⁄¿»“Ô¸œ‹";
            for (int i = 0; i < 20; i++) {
                dato = dato.replaceAll(otros.substring(i, i + 1), "");
            }
            return !isNotAlfanumeric(dato);
        } else {
            return true;
        }
    }

    private static boolean isNotAlfanumeric(String dato) {
        return dato.matches("^.*[^a-zA-Z0-9].*$");
    }
    
    private static boolean isNotNumeric(String dato) {
        return dato.matches("^.*[^0-9].*$");
    }
    
    private void checkTel(String tel) throws Exception{
        if(tel.substring(0,1).equals("+") && !isNotNumeric(tel.substring(1).replaceAll(" ", ""))){
            telefon = tel.replaceFirst(" ", "@").replaceAll(" ", "").replaceAll("@", " ");
            if(telefon.split(" ")[1].length() != 9||telefon.split(" ")[0].length() != 3){
                System.out.println(telefon.split(" ")[1].length()+" "+telefon.split(" ")[0].length());
               throw(new Exception("Quantitat de digits incorrecte en el telÈfon.")); 
            } else {
                switch(telefon.split(" ")[1].substring(0,1)){
                    case "9": case "7": case "6":
                        break;
                    default:
                        throw(new Exception("No es reconeix aquest n˙mero de telÈfon"));
                }
            }
        } else {
            throw(new Exception("el n˙mero no es correcto, debe ser internacional."));
        }
    }
    
    private static String checkNumSeg(String numSegSocial) throws Exception {
        String codProv = numSegSocial.substring(0, 2);
        String codSeg = numSegSocial.substring(0, 10);
        String codControl = numSegSocial.substring(10, numSegSocial.length());
        String[] charCheck1 = {"01", "02", "03", "04", "33", "05", "06", "07", "08", "09", "10", "11", "39", "12", "13",
            "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "27", "28", "29", "30", "31", "32", "34",
            "35", "36", "26", "37", "38", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "53", "66"};
        if (numSegSocial.length() != 12 || isNotNumeric(numSegSocial)) {
            throw(new Exception("formato del codigo de la seguridad social incorrecto"));
        } else {
            boolean codT = false;
            for (String a : charCheck1) {
                if (a.equals(codProv)) {
                    codT = true;
                }
            }
            if (!codT) {
                throw (new Exception("el codigo de provincia es invalido"));
            } else {
                double numCodSeg = Double.parseDouble(codSeg);
                int numCodControl = Integer.parseInt(codControl);
                double x = numCodSeg % 97;
                if (numCodControl != x) {
                    throw (new Exception("los digitos de control son invalidos"));
                } else {
                    return numSegSocial;
                }
            }
        }
    }
    
    private static String checkDNI(String nif) throws Exception{
        String DNInumbs = nif.substring(0, nif.length()-1);
        String DNIletr = nif.substring(nif.length()-1);
        if(isNotNumeric(DNInumbs)|| isNotAlfanumeric(DNIletr)){
            throw(new Exception("formato de DNI incorrecto"));
        } else {
            short unNum;
            if(DNIletr.matches("^.*[^0-9].*$")){
                unNum = 1;
            } else {
                throw(new Exception("Falta la letra"));
            }
            if (DNInumbs.length()<7+unNum) {
                throw(new Exception("DNI demasiado corto"));
            }  else if (DNInumbs.length()>7+unNum) {
                throw(new Exception("DNI demasiado largo"));
            } else {
                String[] charCheck = {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E"};
                if(unNum == 1){
                    if (DNIletr.getBytes()[0]>90){
                        DNIletr = ""+(char)(DNIletr.getBytes()[0]-32);
                    }
                    if(charCheck[Integer.parseInt(DNInumbs)%23].equals(DNIletr)){
                        return DNInumbs + DNIletr;
                    } else {
                       throw(new Exception("DNI falso"));
                    }
                } else {
                    return DNInumbs + DNIletr + charCheck[Integer.parseInt(DNInumbs)%23];
                }
            }
        }
    }
    
    public String toString(){
        return "error.";
    }
    public String getNom() {
        return nom;
    }

    public String getCognom1() {
        return cognom1;
    }

    public String getCognom2() {
        return cognom2;
    }

    public String getNumSegSocial() {
        return numSegSocial;
    }

    public String getNif() {
        return nif;
    }

    public String getTelefon() {
        return telefon;
    }

    public Adreca getAdreca() {
        return adreca;
    }
    
    public Boolean getCasaOBloque(){
        return cOb;
    }
}