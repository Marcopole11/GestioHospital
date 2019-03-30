/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.p01.grup5.gestioHospital.controlador;

import m03.uf5.p01.grup5.gestioHospital.model.Hospital;
import java.util.Scanner;
import m03.uf5.p01.grup5.gestioHospital.model.Metge;
import m03.uf5.p01.grup5.gestioHospital.vista.vista;
 
/**
 *
 * @author Marc Cardenas, Raul Barrero, Roger Miralles
 */
public class GestioHospital {

    public static Hospital elObjeto;

    public static void main(String[] args) {
        elObjeto = new Hospital("Avenida", "Atlanta", 32,0,null, "Barcelona", 03245);

        try { // dades de prova
            elObjeto.nouMetge(false, "Bergasso", "Grande", "Pill", "281234567840", "05413374W", "+34 969491580", 1500, "0123456789", "C/", "Atlanta", 2, 3, "C", "Sabadell", 03245);
            elObjeto.nouPacient(true, "Cubi", "Lemocan", "Resat", "041985550627", "22222222J", "+33 99 323 13 87", "Carretera", "Basetnasci sutnu", 10, 0, null, "Tumas", 03245);
            elObjeto.nouPacient(false, "Resugebi", "Tolmaronse", "Cusnatu", "369479017434", "95017587X", "+83 997586816", "Calle", "Cotitmo gilge", 50, 6, "A", "Ninrucoba", 06235);
            elObjeto.nouPacient(true, "Nit Cilbaro", "Nuneca", "Rane", "335538858991", "65439293S", "+56 954760539", "Avenida", "Mubonul gegu", 79, 0, null, "Bubeca", 03245);
            elObjeto.nouMetge(false, "Samuel", "Eto", "Pill", "281234567840", "90200992Y", "+34 969491580", 1500, "0123456789", "C/", "Atlanta", 2, 3, "C", "Sabadell", 03245);
            elObjeto.nouPacient(true, "David", "Phil", "Jesus", "248896023079", "27451527S", "+34 968 39 42 56", "C/", "Test", 6, 0, null, "Terrasa", 03245);
            elObjeto.novaMalatia("refredat", "Caldo de pollo\nBisolgrip\nBivaporux", false, 8);
            elObjeto.novaMalatia("refredat chungo", "Reposo\nBisolgrip\nVapor de eucalipto", true, 12);
        } catch (Exception ex) {
            vista.muestraTexto("ERROR en dades inicials: " + ex.getMessage());
        } //per a generar usuaris valids aleatoriament utilitzar randomUser.java

        int opcion;
        while (true) {
            String separador = "-------------------------------------------";
            vista.muestraTexto("\nEscriu una de les opcions: ");
            vista.muestraTexto(separador);
            vista.muestraTexto("1. Afegir dades");
            vista.muestraTexto("2. Mostrar dades");
            vista.muestraTexto("3. Sortir");
            vista.muestraTexto(separador);
            opcion = vista.pedirInt();
            switch (opcion) {
                case 1:
                    vista.muestraTexto(separador);
                    vista.muestraTexto("1. Registrar visita\n2. Nou Pacient\n"
                            + "3. Nou Metge\n4. Nova Malaltia\n5. Tornar");
                    vista.muestraTexto(separador);
                    opcion = margenIn(5, -1);
                    break;
                case 2:
                    vista.muestraTexto(separador);
                    vista.muestraTexto("1. Mostrar pacient\n2. Mostrar Metge\n"
                            + "3. Mostar Malaltia\n4. Veure Historial\n5. Tornar");
                    vista.muestraTexto(separador);
                    opcion = margenIn(5, -1);
                    if (opcion > 0) {
                        opcion += 4;
                    }
                    break;
                case 3:
                    opcion = -1;
                    break;
                default:
                    opcion = 0;

            }

            try {

            } catch (Exception ex) {
                ex.getMessage();
            }

            switch (opcion) {
                case 1:
                    vista.muestraTexto("Has seleccionat Registar visita");
                    vista.muestraTexto(separador);
                    registrarVisita();
                    break;
                case 2:
                    vista.muestraTexto("Has seleccionat Nou Pacient");
                    vista.muestraTexto(separador);
                    nouPacient();
                    break;
                case 3:
                    vista.muestraTexto("Has seleccionat Nou Metge");
                    vista.muestraTexto(separador);
                    nouMetge();
                    break;
                case 4:
                    vista.muestraTexto("Has seleccionat Nova Malaltia");
                    vista.muestraTexto(separador);
                    novaMalaltia();
                    break;
                case 5:
                    vista.muestraTexto("Has seleccionat Mostrar Pacient");
                    vista.muestraTexto(separador);
                    mostrarPacient();
                    break;
                case 6:
                    vista.muestraTexto("Has seleccionat Mostrar Pacient");
                    vista.muestraTexto(separador);
                    mostrarMetge();
                    break;
                case 7:
                    vista.muestraTexto("Has seleccionat Mostrar Malaltia");
                    vista.muestraTexto(separador);
                    mostrarMalaltia();
                    break;
                case 8:
                    vista.muestraTexto("Has seleccionat Veure Historial");
                    vista.muestraTexto(separador);
                    mostrarHistorial();
                    break;
                default:
            }
            if (opcion == -1) {
                vista.muestraTexto("Saliendo...");
                break;
            }
        }
    }

    public static void registrarVisita() {
        // Introducir los datos de fecha

        vista.muestraTexto("Introdueix el dia: (numero)");
        int dia = vista.pedirInt();
        vista.muestraTexto("Introdueix el mes: (numero)");
        int mes = vista.pedirInt();
        vista.muestraTexto("Introdueix el any: (numero)");
        int ano = vista.pedirInt();
        vista.muestraTexto("Introdueix la hora: (0-23) / Los minutos iran despues");
        double hora = 0;
        if (true) {
            double horas = margenIn(23, 0);
            vista.muestraTexto("Introdueix los minutos: (0-59)");
            double minutos = margenIn(60, -1);
            hora = (horas / 1) + (minutos / 100);
        }

        vista.muestraTexto("Identificació del pacient------------------");
        vista.muestraTexto("1. NIF");
        vista.muestraTexto("2. Numero Seguretat social ");
        vista.muestraTexto("3. Codi de l'historial");
        vista.muestraTexto("-------------------------------------------");
        int opcionPacient = 0;
        while (true) {
            opcionPacient = margenIn(4, -1);
            if (opcionPacient != 0) {
                break;
            }
            vista.muestraTexto("Número no reconocido");
        }

        int codhis = 0;
        switch (opcionPacient) {
            case 1:
                String tempnif;
                vista.muestraTexto("Introdueix NIF del pacient");
                while (true) {
                    tempnif = vista.pedirString();
                    if (elObjeto.pacient(tempnif) != null) {
                        break;
                    }
                    vista.muestraTexto("No existe usuario con este DNI");
                }
                codhis = elObjeto.pacient(tempnif).historial.codi;

                break;
            case 2:
                String segsoc;
                vista.muestraTexto("Introdueix numero de la Seguretat Social del pacient");
                while (true) {
                    segsoc = vista.pedirString();
                    if (elObjeto.pacientSegSoc(segsoc) != null) {
                        break;
                    }
                    vista.muestraTexto("No existe usuario con este número");
                }
                codhis = elObjeto.pacientSegSoc(segsoc).historial.codi;

                break;
            case 3:
                vista.muestraTexto("Introdueix codi de l'historial del pacient");
                codhis = vista.pedirInt();

                break;
            default:
                break;
        }

        vista.muestraTexto("Identificació del metge--------------------");
        vista.muestraTexto("1. NIF del metge");
        vista.muestraTexto("2. Codi del metge ");
        vista.muestraTexto("-------------------------------------------");
        while (opcionPacient == 0) {
            opcionPacient = margenIn(4, -1);
            vista.muestraTexto("Número no reconocido");
        }
        Metge metge = null;
        switch (opcionPacient) {
            case 1:
                vista.muestraTexto("Introdueix NIF del metge");
                String tempnifmetge;
                tempnifmetge = vista.pedirString();
                metge = elObjeto.metge(tempnifmetge);
                break;
            case 2:
                vista.muestraTexto("Introdueix numero del metge");
                int codmetge;
                codmetge = vista.pedirInt();
                metge = elObjeto.metges.get(codmetge);
                break;
            default:
                break;
        }

        vista.muestraTexto("Introdueix codi Enfermetat-----------------");
        int codiEnfermetat;
        codiEnfermetat = vista.pedirInt();
        
        elObjeto.pacients.get(codhis).historial.visites.visitar(dia, mes, ano, hora, metge, elObjeto.malalties.get(codiEnfermetat));
        vista.muestraTexto(" Añadido a las visitas ");
    }

    public static void nouPacient() {
        String nom, primerCognom, segonCognom, numSegSocial, nif, tel, tipo, carrer, piso, ciudad;
        int numero, planta,codiPostal;         
        boolean casaObloque = false;

        vista.muestraTexto("-----------------------------");
        vista.muestraTexto("Nom pacient: ");
        nom = vista.pedirString();
        vista.muestraTexto("PrimerCognom: ");
        primerCognom = vista.pedirString();
        vista.muestraTexto("SegonCognom: ");
        segonCognom = vista.pedirString();
        vista.muestraTexto("Numero seguretat social: ");
        numSegSocial = vista.pedirString();
        vista.muestraTexto("NIF: ");
        nif = vista.pedirString();
        vista.muestraTexto("Telefono: ");
        tel = vista.pedirString();
        vista.muestraTexto("Tipo de via: ");
        tipo = vista.pedirString();
        vista.muestraTexto("Nombre de la calle/avenida/carretera: ");
        carrer = vista.pedirString();
        vista.muestraTexto("Numero de la vivienda: ");
        numero = vista.pedirInt();
        vista.muestraTexto("Es una casa o un bloque(0 casa, 1 bloque): ");
        int aux = vista.pedirInt();
        while (aux == 1 || aux == 0) {
            vista.muestraTexto("valor no valido");
            vista.muestraTexto("Es una casa o un bloque(0 casa, 1 bloque): ");
            aux = vista.pedirInt();
        }

        if (aux == 0) {
            casaObloque = true;
        } else if (aux == 1) {
            casaObloque = false;
        }

        if (casaObloque) {
            planta = 0;
            piso = null;
        } else {//es un bloque
            vista.muestraTexto("Numero de la planta: ");
            planta = vista.pedirInt();
            vista.muestraTexto("Piso: ");
            piso = vista.pedirString();
        }
        vista.muestraTexto("Nombre de la ciudad: ");
        ciudad = vista.pedirString();
        vista.muestraTexto("Codigo postal: ");
        codiPostal = vista.pedirInt();
        vista.muestraTexto("-----------------------------");
        try {
            elObjeto.nouPacient(casaObloque, nom, primerCognom, segonCognom, numSegSocial, nif, tel, tipo, carrer, numero, planta, piso, ciudad, codiPostal);
        } catch (Exception ex) {
            vista.muestraTexto(ex.getMessage());
            vista.muestraTexto("error");
        }
    }

    public static void nouMetge() {
        String nom, primerCognom, segonCognom, numSegSocial, nif, tel, tipo, carrer, piso, ciudad;
        int numero, planta,codiPostal;
        boolean casaObloque = false;

        vista.muestraTexto("-----------------------------");
        vista.muestraTexto("Nom pacient: ");
        nom = vista.pedirString();
        vista.muestraTexto("PrimerCognom: ");
        primerCognom = vista.pedirString();
        vista.muestraTexto("SegonCognom: ");
        segonCognom = vista.pedirString();
        vista.muestraTexto("Numero seguretat social: ");
        numSegSocial = vista.pedirString();
        vista.muestraTexto("NIF: ");
        nif = vista.pedirString();
        vista.muestraTexto("Telefono: ");
        tel = vista.pedirString();
        vista.muestraTexto("Tipo de via: ");
        tipo = vista.pedirString();
        vista.muestraTexto("Nombre de la calle/avenida/carretera: ");
        carrer = vista.pedirString();
        vista.muestraTexto("Numero de la vivienda: ");
        numero = vista.pedirInt();
        vista.muestraTexto("Es una casa o un bloque(0 casa, 1 bloque): ");
        int aux = vista.pedirInt();
        while (aux == 1 || aux == 0) {
            vista.muestraTexto("valor no valido");
            vista.muestraTexto("Es una casa o un bloque(0 casa, 1 bloque): ");
            aux = vista.pedirInt();
        }

        if (aux == 0) {
            casaObloque = true;
        } else if (aux == 1) {
            casaObloque = false;
        }
        if (casaObloque) {
            planta = 0;
            piso = null;
        } else {//es un bloque
            vista.muestraTexto("Numero de la planta: ");
            planta = vista.pedirInt();
            vista.muestraTexto("Piso: ");
            piso = vista.pedirString();
        }
        vista.muestraTexto("Nombre de la ciudad: ");
        ciudad = vista.pedirString();
        vista.muestraTexto("Codigo postal: ");
        codiPostal = vista.pedirInt();
        vista.muestraTexto("Salari mensual: ");
        int salariMensual = vista.pedirInt();
        vista.muestraTexto("Compte corrent on depositar: ");
        String codiCompteCorrent = vista.pedirString();
        vista.muestraTexto("-----------------------------");
        try {
            elObjeto.nouMetge(casaObloque, nom, primerCognom, segonCognom, numSegSocial, nif, tel, salariMensual, codiCompteCorrent, tipo, carrer, numero, planta, piso, ciudad, codiPostal);
        } catch (Exception ex) {
            vista.muestraTexto(ex.getMessage());
            vista.muestraTexto("error");
        }
    }

    public static void novaMalaltia() {
        String nom, tractament;
        boolean causaBaixa;
        int duradaTractament;
        String separador = "-----------------------------";
        vista.muestraTexto(separador);
        vista.pedirString();
        vista.muestraTexto("Nom de la malaltia: ");
        nom = vista.pedirString();
        vista.muestraTexto("¿Es motiu de baixa? (Si/No)");
        causaBaixa = soloMayus(vista.pedirString()).equals("S");
        vista.muestraTexto("Descriu el seu tractament:"
                + "deixa la línea en blanc per a finaliztar");
        tractament = vista.pedirString();
        while (true) {
            String input = vista.pedirString();
            if (input.length() == 0) {
                break;
            }
            tractament += input;
        }
        vista.muestraTexto("Cuans dies dura el tractament: ");
        duradaTractament = vista.pedirInt();
        vista.muestraTexto(separador);
        try {
            elObjeto.novaMalatia(nom, tractament, causaBaixa, duradaTractament);
        } catch (Exception ex) {
            vista.muestraTexto(ex.getMessage());
        }
    }

    public static void mostrarPacient() {

        int opcio;
        boolean acabat = false;
        do {
            vista.muestraTexto("-------------------------------------------");
            vista.muestraTexto("1. Mostrar nombre,apellidos y dni de los pacientes");
            vista.muestraTexto("2. Mostrar informacion del paciente por el dni");
            vista.muestraTexto("-------------------------------------------");
            vista.muestraTexto("Escribe una de las opciones: ");
            opcio = vista.pedirInt();
            switch (opcio) {
                case 1:
                    mostrar(1);
                    break;
                case 2:
                    String tempnif;
                    vista.muestraTexto("DNI/NIF del pacient:");
                    tempnif = vista.pedirString();
                    if (tempnif.length() > 8) {
                        tempnif = tempnif.substring(0, tempnif.length() - 1) + soloMayus(tempnif.substring(tempnif.length() - 1));
                        System.out.println(elObjeto.pacient(tempnif).nom);
                    } else {
                        vista.muestraTexto("Agrega tots els caracters amb la lletra.");
                    }
                    acabat = true;

                    break;
            }
        } while (!acabat);
    }

    public static void mostrarMetge() {

        int opcio;
        boolean acabat = false;
        do {
            vista.muestraTexto("-------------------------------------------");
            vista.muestraTexto("1. Mostrar nombre,apellidos y dni de los medicos");
            vista.muestraTexto("2. Mostrar informacion del medico por el dni");
            vista.muestraTexto("-------------------------------------------");
            vista.muestraTexto("Escribe una de las opciones: ");
            opcio = vista.pedirInt();
            switch (opcio) {
                case 1:
                    mostrar(2);
                    break;
                case 2:
                    String tempnif;
                    vista.muestraTexto("DNI/NIF del metge:");
                    tempnif = vista.pedirString();
                    System.out.println(elObjeto.metge(tempnif));
                    acabat = true;
                    break;
            }
        } while (!acabat);
    }
    
    public static void mostrarMalaltia(){
        int opcio;
        boolean acabat = false;
        do {
            vista.muestraTexto("-------------------------------------------");
            vista.muestraTexto("1. Mostrar enfermedad y caracteristicas");
            vista.muestraTexto("2. Mostrar informacion de la enfermedad por codigo de enfermedad");
            vista.muestraTexto("-------------------------------------------");
            vista.muestraTexto("Escribe una de las opciones: ");
            opcio = vista.pedirInt();
            switch (opcio) {
                case 1:
                    mostrar(3);
                    break;
                case 2:
                    int tempCodi;
                    vista.muestraTexto("codi de la malaltia:");
                    tempCodi = vista.pedirInt();
                    System.out.println(elObjeto.malaltia(tempCodi));
                    acabat = true;
                    break;
            }
        } while (!acabat);
    }

    public static void mostrarHistorial() {
        vista.muestraTexto("Introdueix el codi del historial:");
        int idHistorial;
        idHistorial = vista.pedirInt();
        System.out.println(elObjeto.pacients.get(idHistorial).historial);
    }

    /**
     * Función que recorta una String tomando solo el primer carácter y
     * devolviendolo en mayuscula.
     *
     * @param caract La string que contiene el caracter;
     * @return el primer caracter de la cadena, si es minúscula en mayusculas.
     */
    public static String soloMayus(String caract) {
        if (caract.getBytes()[0] > 90) {
            return "" + (char) (caract.getBytes()[0] - 32);
        } else {
            return caract;
        }
    }

    /**
     * Funció que imprimeix una llista dels elements solicitats.
     *
     * @param opcio (1)Pacients, (2)Metges
     */
    public static void mostrar(int opcio) {
        int i;
        switch (opcio) {
            case 1:
                for (i = 0; i < elObjeto.pacients.size(); i++) {
                    vista.muestraTexto(elObjeto.pacients.get(i).nom + " " + elObjeto.pacients.get(i).cognom1 + " "
                            + elObjeto.pacients.get(i).cognom2 + " " + elObjeto.pacients.get(i).nif);
                }
                break;
            case 2:
                for (i = 0; i < elObjeto.metges.size(); i++) {
                    vista.muestraTexto(elObjeto.metges.get(i).nom + " " + elObjeto.metges.get(i).cognom1 + " "
                            + elObjeto.metges.get(i).cognom2 + " " + elObjeto.metges.get(i).nif);

                }
                break;
            case 3:
                for(i=0;i<elObjeto.malalties.size();i++){
                    vista.muestraTexto(elObjeto.malalties.get(i).codi+" "+elObjeto.malalties.get(i).nom );
                }
                break;
        }
    }

    /**
     * Demana una entada numérica per teclat pero no accepta negatius i te un
     * marge valid.
     *
     * @param maximo La quantitat que no ha de superar el nombre introduit
     * @param ceroToMax (-1) fora de rang = 0 (1) fora de rang = max
     * @return El nombre introduit per l'usuari si está dins del rang o la
     * resposta definida per ceroToMax si es troba fora de rang.
     */
    public static int margenIn(int maximo, int ceroToMax) {
        int numero = vista.pedirInt();
        if (numero < 0) {
            numero = -numero;
        }
        if (numero == 0) {
            if (ceroToMax == 1) {
                return maximo;
            } else {
                return 0;
            }
        } else if (numero >= maximo) {
            if (ceroToMax == -1) {
                return 0;
            } else {
                return maximo;
            }
        } else {
            return numero;
        }
    }
}
