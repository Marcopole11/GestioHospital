/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.p01.grup5.gestioHospital;

/**
 *
 * @author Marc Cardenas, Raúl Barrero, Roger Miralles
 */
public class randomUser {
    String nombre,numSegSoc,DNI,telefono,direccion;
    String[] apellido = new String[2];
    public static void main(String[] args){
        String[] apellido={iniCaps(randomName()),iniCaps(randomName())};
        String nombre = iniCaps(randomName()),numSegSoc=randomNumSegSoc(),
            DNI = randomDNI(),telefono = randomTel(),direccion = randomDir();
        System.out.println("nombre: "+nombre);
        System.out.println("Apellido1: "+apellido[0]);
        System.out.println("Apellido2: "+apellido[1]);
        System.out.println("numSegSoc: "+numSegSoc);
        System.out.println("DNI: "+DNI);
        System.out.println("Telefono: "+telefono);
        System.out.println("Dirección: "+direccion);
        System.out.println("\n__________________________\nParse text:");
        System.out.println(nombre+"\n"+apellido[0]+"\n"+apellido[1]+"\n"
                +numSegSoc+"\n"+DNI+"\n"+telefono+"\n"+direccion);
        try{System.in.read();} catch (Exception e){}
    }
    public void randomUser(){
        nombre = iniCaps(randomName());
        apellido[0]=iniCaps(randomName());
        apellido[1]=iniCaps(randomName());
        numSegSoc=randomNumSegSoc();
        DNI = randomDNI();
        telefono = randomTel();
        direccion = randomDir();
    }
    public static String randomName(){
        String[] letras = new String[]{"ctbsrgnml","aeiou","tpsyln"};
        int silab = (int)(Math.random()*3.5+1.5);
        String generado = "";
        for(int i = 0; i<silab;i++){
            int ln = (int)(Math.random()*1.5)+2;
            for(int j = 0; j < ln; j++){
                int ch = (int)(Math.random()*letras[j].length());
                generado+=letras[j].substring(ch, ch+1);
            }
        }
        return generado;
    }
    public static String randomDNI(){
        String dni = randomSerial(8);
        String[] letra = {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E"};
        return dni+letra[Integer.parseInt(dni)%23];
    }
    public static String randomNumSegSoc(){
        String codProv = (int)(Math.random()*40+1)+"";
         if(codProv.length()<2){
             codProv = "0"+codProv;
         }
        String codSeg = codProv+randomSerial(8);
        double resto = (Double.parseDouble(codSeg)%97);
        String codControl = ""+(int)resto;
        if(codControl.length()<2){
            codControl = "0"+codControl;
        }
        return codSeg+codControl;
    }
    public static String randomTel(){
        String num = "X";
        while(!num.substring(0,1).equals("9")&&!num.substring(0,1).equals("6")){
            num = randomSerial(9);
        }
        if(num.substring(0,1).equals("9")){
            num = caraCruz(num,num.substring(0,2)
                    +" "+cortes(num.substring(2),2));
        } else {
            num = caraCruz(caraCruz(cortes(num,2),cortes(num,3)),num);
        }
        return "+"+randomSerial(2)+" "+num;
    }
    public static String randomDir(){
        String[] inic = {"Avenida","Calle","Carretera","Paseo","Plaza"};
        String regreso = inic[(int)(Math.random()*5)]+" "+iniCaps(randomName());
        regreso+=caraCruz(" "+randomName())+" "+caraCruz("nº")
                +randomSerial(2)+caraCruz(" "+randomSerial(1)
                 +caraCruz("-"," ")+(char)((Math.random()*5)+65))
                +caraCruz(", "," ")+iniCaps(randomName())+" 0"+randomSerial(4);
        return regreso;
    }
    public static String randomSerial(int largo){
        String rand = "";
        while(rand.length() < 8){
            rand+=(int)(Math.random()*10000000);
        }
        return rand.substring(rand.length()-largo);
    }
    public static String iniCaps(String palabra){
        return (char)((byte)palabra.charAt(0)-(byte)32)+palabra.substring(1);
    }
    public static String cortes(String cortar, int dist){
        int i = cortar.length()-(int)(cortar.length()/dist)*dist-1;
        dist++;
        while(i+dist < cortar.length()){
            cortar = cortar.substring(0, i+dist)+" "+cortar.substring(i+dist);
            i+=dist;
        }
        return cortar;
    }
    public static boolean caraCruz(){
        return Math.random()>0.5;
    }
    public static String caraCruz(String opcion){
        return caraCruz(opcion,"");
    }
    public static String caraCruz(String cara, String cruz){
        if(Math.random()>0.5){
            return cara;
        } else {
            return cruz;
        }
    }
}
