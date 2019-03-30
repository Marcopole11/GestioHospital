/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.p01.grup5.gestioHospital.vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import m03.uf5.p01.grup5.gestioHospital.controlador.*;
import m03.uf5.p01.grup5.gestioHospital.model.Visitando;
/**
 *
 * @author Marc Cardenas, Raul Barrero, Roger Miralles
 */
public class Ventana {

    byte tipo;
    int totalW, totalH;
    private GraphicsConfiguration gc;
    public Visitando tempVData = null;
    public static Ventana menuPrincipal = new Ventana((byte) 1);
    public static Ventana menuAnadir = new Ventana((byte) 2);
    public static Ventana menuMostrar = new Ventana((byte) 3);
    public static Ventana menuRegistraVisita = new Ventana((byte) 4);
    public static Ventana menuNouPacient = new Ventana((byte) 5);
    public static Ventana menuNouMetge = new Ventana((byte) 6);
    public static Ventana menuNovaMalaltia = new Ventana((byte) 7);
    public static Ventana menuMostraPacient = new Ventana((byte) 8);
    public static Ventana menuMostraMetge = new Ventana((byte) 9);
    public static Ventana menuMostraHistorial = new Ventana((byte) 10);
    public static Ventana menuMostraMalaltia = new Ventana((byte) 11);
    public static Ventana idPacient = new Ventana((byte) 12);
    public static Ventana menuAntesPTots = new Ventana((byte) 13);
    public static Ventana menuAntesPEspecific = new Ventana((byte) 14);
    public static Ventana menuAntesMTots = new Ventana((byte) 15);
    public static Ventana menuAntesMEspecific = new Ventana((byte) 16);
    public static Ventana idNIFPacient = new Ventana((byte) 17);
    public static Ventana idNSSPacient = new Ventana((byte) 18);
    public static Ventana idHistorialPacient = new Ventana((byte) 19);
    public static Ventana idMetge = new Ventana((byte) 20);
    public static Ventana idNIFMetge = new Ventana((byte) 21);
    public static Ventana idCodiMetge = new Ventana((byte) 22);
    public static Ventana menuAntesMalTots = new Ventana((byte) 23);
    public static Ventana menuAntesMalEspecific = new Ventana((byte) 24);
    public static Ventana codiMalaltia = new Ventana((byte) 25);
    public static Ventana menuAntesHTot = new Ventana((byte) 26);
    public static Ventana menuAntesHEspecific = new Ventana((byte) 27);

    public ControlHospital saved;

    private Ventana(byte tipo) {
        this.tipo = tipo;
    }
    public JFrame abrir(ControlHospital saved, Visitando tempVisita) {
        
    }
    public JFrame abrir(ControlHospital saved) {
        this.saved = saved;
        JFrame ventana;
        ventana = new JFrame(gc);
        Dimension ventanaT = Toolkit.getDefaultToolkit().getScreenSize();

        ventana.setLayout(null);
        ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        ventana.setTitle("Gestio Hospital");
        ventana.setSize((int) (ventanaT.width / 1.5), (int) (ventanaT.height / 1.5));
        ventana.setLocationRelativeTo(null);
        totalW = ventana.getBounds().width;
        totalH = ventana.getSize().height;
        if (tipo == 1) {
            ventana.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    if (saved.guardarSession()) {
                        ventana.dispose();
                    } else {
                        if (JOptionPane.showConfirmDialog(null,
                                "Algun otro programa está usando los archivos de guardado,\nsi sales ahora no se guardaran los cambios.\n¿Estas seguro?", "Error de guardado",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                            ventana.dispose();
                        }
                        System.out.println("El archivo se est? utilizando");
                    }
                }
            });
        } else {
            ventana.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    Ventana.menuPrincipal.abrir(saved);
                    ventana.dispose();
                }
            });
        }
        ventana.setVisible(true);
        ventana.setResizable(false);
        switch (tipo) {
            case 1:
                return Vmenu(ventana);
            case 2:
                return VmenuAnadir(ventana);
            case 3:
                return VmenuMostrar(ventana);
            case 4:
                return VregistrarVisita(ventana);
            case 5:
                return VnouPacient(ventana);
            case 6:
                return VnouMetge(ventana);
            case 7:
                return VnovaMalaltia(ventana);
            case 8:
                return VmostraPacient(ventana);
            case 9:
                return VmostraMetge(ventana);
            case 10:
                return VmostraHistorial(ventana);
            case 11:
                return VmostraMalaltia(ventana);
            case 12:
                return VidPacient(ventana);
            case 13:
                return VtotPacient(ventana);
            case 14:
                return VespecificPacient(ventana);
            case 15:
                return VtotMetge(ventana);
            case 16:
                return VespecificMetge(ventana);
            case 17:
                return VidNIFPacient(ventana);
            case 18:
                return VidNSSPacient(ventana);
            case 19:
                return VidHistorialPacient(ventana);
            case 20:
                return VidMetge(ventana);
            case 21:
                return VidNIFMetge(ventana);
            case 22:
                return VidCodiMetge(ventana);
            case 23:
                return VtotMalaltia(ventana);
            case 24:
                return VespecificMalaltia(ventana);
            case 25:
                return VcodiMalaltia(ventana);
            case 26:
                return VtotHistorial(ventana);
            case 27:
                return VespecificHistorial(ventana);
            default:
                return ventana;
        }
    }

    public JFrame Vmenu(JFrame ventana) {
        JButton btnAdades = new JButton();
        JButton btnMdades = new JButton();
        btnAdades.setBounds(porcW(5) + 30, porcH(5) + 30, porcW(40) - 60, porcH(90) - 60);
        btnMdades.setBounds(porcW(55) + 30, porcH(5) + 30, porcW(40) - 60, porcH(90) - 60);
        btnAdades.setText("Afegir Dades");
        btnMdades.setText("Mostra Dades");

        btnAdades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana.menuAnadir.abrir(saved);
                ventana.dispose();
            }
        });

        btnMdades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana.menuMostrar.abrir(saved);
                ventana.dispose();
            }
        });

        //System.out.println(totalH);
        //System.out.println(((double) (totalH - 15) / 100 * 100));
        ventana.add(btnAdades);
        ventana.add(btnMdades);
        return ventana;
    }

    public JFrame VmenuAnadir(JFrame ventana) {

        JButton btnNVisita = new JButton();
        JButton btnNPacient = new JButton();
        JButton btnNMetge = new JButton();
        JButton btnNMalaltia = new JButton();

        btnNVisita.setText("Registrar Visita");
        btnNPacient.setText("Nou Pacient");
        btnNMetge.setText("Nou Metge");
        btnNMalaltia.setText("Nova Malalties");

        btnNVisita.setBounds(porcW(5) + 30, porcH(5) + 30, porcW(25) - 60, porcH(90) - 60);
        btnNPacient.setBounds(porcW(25) + 30, porcH(5) + 30, porcW(25) - 60, porcH(90) - 60);
        btnNMetge.setBounds(porcW(45) + 30, porcH(5) + 30, porcW(25) - 60, porcH(90) - 60);
        btnNMalaltia.setBounds(porcW(65) + 30, porcH(5) + 30, porcW(25) - 60, porcH(90) - 60);

        btnNVisita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana.menuRegistraVisita.abrir(saved);
                ventana.dispose();
            }
        });

        btnNPacient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana.menuNouPacient.abrir(saved);
                ventana.dispose();
            }
        });

        btnNMetge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana.menuNouMetge.abrir(saved);
                ventana.dispose();
            }
        });

        btnNMalaltia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana.menuNovaMalaltia.abrir(saved);
                ventana.dispose();
            }
        });

        ventana.add(btnNMalaltia);
        ventana.add(btnNMetge);
        ventana.add(btnNPacient);
        ventana.add(btnNVisita);
        return ventana;
    }

    public JFrame VmenuMostrar(JFrame ventana) {
        JButton btnMHistorial = new JButton();
        JButton btnMPacient = new JButton();
        JButton btnMMetge = new JButton();
        JButton btnMMalaltia = new JButton();

        btnMPacient.setText("Mostrar Pacient");
        btnMMetge.setText("Mostar Metge");
        btnMHistorial.setText("Mostrar Historial");
        btnMMalaltia.setText("Mostrar Malalties");

        btnMHistorial.setBounds(porcW(5) + 30, porcH(5) + 30, porcW(25) - 60, porcH(90) - 60);
        btnMPacient.setBounds(porcW(25) + 30, porcH(5) + 30, porcW(25) - 60, porcH(90) - 60);
        btnMMetge.setBounds(porcW(45) + 30, porcH(5) + 30, porcW(25) - 60, porcH(90) - 60);
        btnMMalaltia.setBounds(porcW(65) + 30, porcH(5) + 30, porcW(25) - 60, porcH(90) - 60);

        btnMPacient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana.menuMostraPacient.abrir(saved);
                ventana.dispose();
            }
        });

        btnMMetge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana.menuMostraMetge.abrir(saved);
                ventana.dispose();
            }
        });

        btnMHistorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana.menuMostraHistorial.abrir(saved);
                ventana.dispose();
            }
        });

        btnMMalaltia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana.menuMostraMalaltia.abrir(saved);
                ventana.dispose();
            }
        });

        ventana.add(btnMHistorial);
        ventana.add(btnMMalaltia);
        ventana.add(btnMMetge);
        ventana.add(btnMPacient);
        return ventana;
    }

//------------ REGISTRAR VISITA, NOU PACIENT, NOU METGE, NOVA MALALTIA -------------------    
    //-------- Registrar Visita ----------------------------------------------------------
    public JFrame VregistrarVisita(JFrame ventana) {
        JButton btnEnviar = new JButton();
        JLabel dia = new JLabel();
        JTextField txtDia = new JTextField(2);
        JLabel mes = new JLabel();
        JTextField txtMes = new JTextField(2);
        JLabel any = new JLabel();
        JTextField txtAny = new JTextField(4);
        JLabel hora = new JLabel();
        JTextField txtHora = new JTextField(2);
        JLabel minuts = new JLabel();
        JTextField txtMinuts = new JTextField(2);

        btnEnviar.setText("Enviar");
        dia.setText("Introdueix el dia:");
        mes.setText("Introdueix el mes:");
        any.setText("Introdueix el any:");
        hora.setText("Introdueix la hora:");
        minuts.setText("Introdueix els minuts:");

        dia.setBounds(porcW(5) + 30, porcH(5) + 30, 200, 30);
        txtDia.setBounds(porcW(20) + 30, porcH(5) + 30, 200, 30);
        mes.setBounds(porcW(5) + 30, porcH(10) + 30, 200, 30);
        txtMes.setBounds(porcW(20) + 30, porcH(10) + 30, 200, 30);
        any.setBounds(porcW(5) + 30, porcH(15) + 30, 200, 30);
        txtAny.setBounds(porcW(20) + 30, porcH(15) + 30, 200, 30);
        hora.setBounds(porcW(5) + 30, porcH(20) + 30, 200, 30);
        txtHora.setBounds(porcW(20) + 30, porcH(20) + 30, 200, 30);
        minuts.setBounds(porcW(5) + 30, porcH(25) + 30, 200, 30);
        txtMinuts.setBounds(porcW(20) + 30, porcH(25) + 30, 200, 30);
        btnEnviar.setBounds(porcW(30) + 30, porcH(35) + 30, 200, 30);

        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String dia = txtDia.getText();
                String mes = txtMes.getText();
                String any = txtAny.getText();
                String hora = txtHora.getText();
                String minuts = txtMinuts.getText();
                if ("".equals(dia) || "".equals(mes) || "".equals(any) || "".equals(hora) || "".equals(minuts)) {
                    JOptionPane.showMessageDialog(null, "Algunos de los campos esta vacio. Rellenalo y vuelve a probar");
                } else {
                    if (isNotNumeric(dia) || isNotNumeric(mes) || isNotNumeric(any) || isNotNumeric(hora) || isNotNumeric(minuts)) {
                        JOptionPane.showMessageDialog(null, "Algunos de los campos no son numericos. Arreglalos y vuelve a probar");
                    } else {
                        if (minuts.length() < 2) {
                            minuts = "0" + minuts;
                        }
                        try {
                            Visitando tempVisit = saved.registrarVisita(dia, mes, any, hora, minuts);
                            String mesg = tempVisit.tiempo(0) + "/" + tempVisit.tiempo(1) + "/" + tempVisit.tiempo(2) + " a les " + tempVisit.tiempo(3) + ":";
                            if (tempVisit.tiempo(4) < 10) {
                                mesg += "0";
                            }
                            JOptionPane.showMessageDialog(null, "Visita realitzada el " + mesg + tempVisit.tiempo(4) + ". Continua per identificar el pacient");
                            Ventana.idPacient.abrir(saved,tempVisit);
                            ventana.dispose();
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "No se ha podido registrar la visita\n" + e.getMessage());
                        }

                    }
                }
            }
        });

        ventana.add(dia);
        ventana.add(txtDia);
        ventana.add(mes);
        ventana.add(txtMes);
        ventana.add(any);
        ventana.add(txtAny);
        ventana.add(hora);
        ventana.add(txtHora);
        ventana.add(minuts);
        ventana.add(txtMinuts);
        ventana.add(btnEnviar);
        return ventana;
    }

    public JFrame VidPacient(JFrame ventana) {
        JButton btnNIF = new JButton();
        JButton btnNSS = new JButton();
        JButton btnHistorial = new JButton();

        btnNIF.setText("Identificar PACIENT per NIF");
        btnNSS.setText("Identificar PACIENT per Numero de la seguretat social");
        btnHistorial.setText("Identificar PACIENT per codi historial");

        btnNIF.setBounds(porcW(5) + 30, porcH(5) + 30, 250, porcH(90) - 60);
        btnNSS.setBounds(porcW(30) + 30, porcH(5) + 30, 250, porcH(90) - 60);
        btnHistorial.setBounds(porcW(55) + 30, porcH(5) + 30, 250, porcH(90) - 60);

        btnNIF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana.idNIFPacient.abrir(saved);
                ventana.dispose();
            }
        });

        btnNSS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana.idNSSPacient.abrir(saved);
                ventana.dispose();
            }
        });

        btnHistorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana.idHistorialPacient.abrir(saved);
                ventana.dispose();
            }
        });

        ventana.add(btnNIF);
        ventana.add(btnNSS);
        ventana.add(btnHistorial);
        return ventana;
    }

    public JFrame VidMetge(JFrame ventana) {
        JButton btnNIF = new JButton();
        JButton btnCodi = new JButton();

        btnNIF.setText("Identificar METGE per NIF");
        btnCodi.setText("Identificar METGE per codi de metge");

        btnNIF.setBounds(porcW(10) + 30, porcH(5) + 30, 250, porcH(90) - 60);
        btnCodi.setBounds(porcW(35) + 30, porcH(5) + 30, 250, porcH(90) - 60);

        btnNIF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana.idNIFMetge.abrir(saved);
                ventana.dispose();
            }
        });

        btnCodi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana.idCodiMetge.abrir(saved);
                ventana.dispose();
            }
        });

        ventana.add(btnNIF);
        ventana.add(btnCodi);
        return ventana;
    }

    public JFrame VidNIFPacient(JFrame ventana) {
        JButton btnEnviar = new JButton();
        JLabel Nif = new JLabel();
        JTextField txtNif = new JTextField();
        btnEnviar.setText("Enviar");
        Nif.setText("Introdueix el dni:");
        Nif.setBounds(porcW(5) + 30, porcH(5) + 30, 200, 30);
        txtNif.setBounds(porcW(15) + 30, porcH(5) + 30, 200, 30);
        btnEnviar.setBounds(porcW(30) + 30, porcH(10) + 30, 200, 30);
        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String nif = txtNif.getText();
                if ("".equals(nif)) {
                    JOptionPane.showMessageDialog(null, "El campo esta vacio. Rellenalo y vuelve a probar");
                } else {
                    if (isNotAlfanumeric(nif)) {
                        JOptionPane.showMessageDialog(null, "Algun campo no es alfanumerico. Arreglalo y vuelve a probar");
                    } else {
                        try {
                            saved.comprobarDniPacient(nif);
                            Ventana.idMetge.abrir(saved);
                            ventana.dispose();
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }

                    }
                }
            }
        });
        ventana.add(Nif);
        ventana.add(txtNif);
        ventana.add(btnEnviar);
        return ventana;
    }

    public JFrame VidNSSPacient(JFrame ventana) {
        JButton btnEnviar = new JButton();
        JLabel NSS = new JLabel();
        JTextField txtNSS = new JTextField();
        btnEnviar.setText("Enviar");
        NSS.setText("Introdueix el numero de la segureta social:");
        NSS.setBounds(porcW(5) + 30, porcH(5) + 30, 350, 30);
        txtNSS.setBounds(porcW(25) + 30, porcH(5) + 30, 200, 30);
        btnEnviar.setBounds(porcW(30) + 30, porcH(10) + 30, 200, 30);
        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String nss = txtNSS.getText();
                if ("".equals(nss)) {
                    JOptionPane.showMessageDialog(null, "El campo esta vacio. Rellenalo y vuelve a probar");
                } else {

                    try {
                        JOptionPane.showMessageDialog(null, "El numeros de la segureta social es " + nss + ".");
                        saved.comprobarNSSPacient(nss);
                        Ventana.idMetge.abrir(saved);
                        ventana.dispose();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                }
            }
        });
        ventana.add(NSS);
        ventana.add(txtNSS);
        ventana.add(btnEnviar);
        return ventana;
    }

    public JFrame VidHistorialPacient(JFrame ventana) {
        JButton btnEnviar = new JButton();
        JLabel codi = new JLabel();
        JTextField txtCodi = new JTextField();
        btnEnviar.setText("Enviar");
        codi.setText("Introdueix el codi historial:");
        codi.setBounds(porcW(5) + 30, porcH(5) + 30, 200, 30);
        txtCodi.setBounds(porcW(20) + 30, porcH(5) + 30, 200, 30);
        btnEnviar.setBounds(porcW(30) + 30, porcH(15) + 30, 200, 30);
        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String codi = txtCodi.getText();
                if ("".equals(codi)) {
                    JOptionPane.showMessageDialog(null, "El campo esta vacio. Rellenalo y vuelve a probar");
                } else {

                    try {
                        JOptionPane.showMessageDialog(null, "El codi de historial es " + codi + ".");
                        saved.comprobarCodiPacient(codi);
                        Ventana.idMetge.abrir(saved);
                        ventana.dispose();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                }
            }
        });
        ventana.add(codi);
        ventana.add(txtCodi);
        ventana.add(btnEnviar);
        return ventana;
    }

    public JFrame VidCodiMetge(JFrame ventana) {
        JButton btnEnviar = new JButton();
        JLabel codi = new JLabel();
        JTextField txtCodi = new JTextField();
        btnEnviar.setText("Enviar");
        codi.setText("Introdueix el codi de metge:");
        codi.setBounds(porcW(5) + 30, porcH(5) + 30, 200, 30);
        txtCodi.setBounds(porcW(20) + 30, porcH(5) + 30, 200, 30);
        btnEnviar.setBounds(porcW(30) + 30, porcH(10) + 30, 200, 30);
        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String codi = txtCodi.getText();
                if ("".equals(codi)) {
                    JOptionPane.showMessageDialog(null, "El campo esta vacio. Rellenalo y vuelve a probar");
                } else {
                    try {
                        JOptionPane.showMessageDialog(null, "El codi de historial es " + codi + ".");
                        saved.comprobarCodiMetge(codi);
                        Ventana.codiMalaltia.abrir(saved);
                        ventana.dispose();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                }
            }
        });
        ventana.add(codi);
        ventana.add(txtCodi);
        ventana.add(btnEnviar);
        return ventana;
    }

    public JFrame VidNIFMetge(JFrame ventana) {
        JButton btnEnviar = new JButton();
        JLabel Nif = new JLabel();
        JTextField txtNif = new JTextField();
        btnEnviar.setText("Enviar");
        Nif.setText("Introdueix el dni:");
        Nif.setBounds(porcW(5) + 30, porcH(5) + 30, 200, 30);
        txtNif.setBounds(porcW(15) + 30, porcH(5) + 30, 200, 30);
        btnEnviar.setBounds(porcW(30) + 30, porcH(15) + 30, 200, 30);
        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String nif = txtNif.getText();
                if ("".equals(nif)) {
                    JOptionPane.showMessageDialog(null, "El campo esta vacio. Rellenalo y vuelve a probar");
                } else {
                    if (isNotAlfanumeric(nif)) {
                        JOptionPane.showMessageDialog(null, "Algun campo no es alfanumero. Arreglalo y vuelve a probar");
                    } else {
                        try {
                            JOptionPane.showMessageDialog(null, "El dni es " + nif + ".");
                            saved.comprobarDniMetge(nif);
                            Ventana.codiMalaltia.abrir(saved);
                            ventana.dispose();
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                    }
                }
            }
        });
        ventana.add(Nif);
        ventana.add(txtNif);
        ventana.add(btnEnviar);
        return ventana;
    }

    public JFrame VcodiMalaltia(JFrame ventana) {
        JButton btnEnviar = new JButton();
        JLabel codiMalaltia = new JLabel();
        JTextField txtCodiMalaltia = new JTextField();

        btnEnviar.setText("Enviar");
        codiMalaltia.setText("Codi Malaltia");

        codiMalaltia.setBounds(porcW(5) + 30, porcH(5) + 30, 400, 30);
        txtCodiMalaltia.setBounds(porcW(25) + 30, porcH(5) + 30, 400, 30);
        btnEnviar.setBounds(porcW(5) + 30, porcH(10) + 30, 400, 30);

        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(null, "Visita registrada correctamente");
                Ventana.menuPrincipal.abrir(saved);
                ventana.dispose();
            }
        });

        ventana.add(codiMalaltia);
        ventana.add(txtCodiMalaltia);
        ventana.add(btnEnviar);
        return ventana;
    }
    //-------- Fin Registrar Visita ------------------------------------------------------

    //-------- Nou Pacient ---------------------------------------------------------------
    public JFrame VnouPacient(JFrame ventana) {
        JButton btnEnviar = new JButton();
        JLabel Nom = new JLabel();
        JTextField txtNom = new JTextField();
        JLabel Pcognom = new JLabel();
        JTextField txtPcognom = new JTextField();
        JLabel Scognom = new JLabel();
        JTextField txtScognom = new JTextField();
        JLabel Nss = new JLabel();
        JTextField txtNss = new JTextField();
        JLabel Nif = new JLabel();
        JTextField txtNif = new JTextField();
        JLabel Tlf = new JLabel();
        JTextField txtTlf = new JTextField();
        JLabel Via = new JLabel();
        JTextField txtVia = new JTextField();
        JLabel Calle = new JLabel();
        JTextField txtCalle = new JTextField();
        JLabel Num = new JLabel();
        JTextField txtNum = new JTextField();
        JLabel cob = new JLabel();
        JCheckBox txtcob = new JCheckBox();
        txtcob.setSelected(false);
        JLabel Numpl = new JLabel();
        JTextField txtNumpl = new JTextField();
        JLabel Piso = new JLabel();
        JTextField txtPiso = new JTextField();
        JLabel Ciudad = new JLabel();
        JTextField txtCiudad = new JTextField();
        JLabel CP = new JLabel();
        JTextField txtCP = new JTextField();

        btnEnviar.setText("Enviar");
        Nom.setText("Nom pacient: ");
        Pcognom.setText("PrimerCognom: ");
        Scognom.setText("SegonCognom: ");
        Nss.setText("Numero seguretat social: ");
        Nif.setText("NIF: ");
        Tlf.setText("Telefono: ");
        Via.setText("Tipo de via: ");
        Calle.setText("Nombre de la calle/avenida/carretera: ");
        Num.setText("Numero de la vivienda:");
        cob.setText("Es una casa??Check sin marcar significa piso?");
        Numpl.setText("Numero de la planta: ");
        Piso.setText("Piso: ");
        Ciudad.setText("Nombre de la ciudad: ");
        CP.setText("Codigo postal:");

        Nom.setBounds(porcW(5) + 30, porcH(5) + 30, 400, 30);
        txtNom.setBounds(porcW(25) + 30, porcH(5) + 30, 400, 30);
        Pcognom.setBounds(porcW(5) + 30, porcH(10) + 30, 400, 30);
        txtPcognom.setBounds(porcW(25) + 30, porcH(10) + 30, 400, 30);
        Scognom.setBounds(porcW(5) + 30, porcH(15) + 30, 400, 30);
        txtScognom.setBounds(porcW(25) + 30, porcH(15) + 30, 400, 30);
        Nss.setBounds(porcW(5) + 30, porcH(20) + 30, 400, 30);
        txtNss.setBounds(porcW(25) + 30, porcH(20) + 30, 400, 30);
        Nif.setBounds(porcW(5) + 30, porcH(25) + 30, 400, 30);
        txtNif.setBounds(porcW(25) + 30, porcH(25) + 30, 400, 30);
        Tlf.setBounds(porcW(5) + 30, porcH(30) + 30, 400, 30);
        txtTlf.setBounds(porcW(25) + 30, porcH(30) + 30, 400, 30);
        Via.setBounds(porcW(5) + 30, porcH(35) + 30, 400, 30);
        txtVia.setBounds(porcW(25) + 30, porcH(35) + 30, 400, 30);
        Calle.setBounds(porcW(5) + 30, porcH(40) + 30, 400, 30);
        txtCalle.setBounds(porcW(25) + 30, porcH(40) + 30, 400, 30);
        Num.setBounds(porcW(5) + 30, porcH(45) + 30, 400, 30);
        txtNum.setBounds(porcW(25) + 30, porcH(45) + 30, 400, 30);
        cob.setBounds(porcW(5) + 30, porcH(50) + 30, 400, 30);
        txtcob.setBounds(porcW(30) + 30, porcH(50) + 30, 400, 30);
        Numpl.setBounds(porcW(5) + 30, porcH(55) + 30, 400, 30);
        txtNumpl.setBounds(porcW(25) + 30, porcH(55) + 30, 400, 30);
        Piso.setBounds(porcW(5) + 30, porcH(60) + 30, 400, 30);
        txtPiso.setBounds(porcW(25) + 30, porcH(60) + 30, 400, 30);
        Ciudad.setBounds(porcW(5) + 30, porcH(65) + 30, 400, 30);
        txtCiudad.setBounds(porcW(25) + 30, porcH(65) + 30, 400, 30);
        CP.setBounds(porcW(5) + 30, porcH(70) + 30, 400, 30);
        txtCP.setBounds(porcW(25) + 30, porcH(70) + 30, 400, 30);
        btnEnviar.setBounds(porcW(30) + 30, porcH(75) + 30, 400, 30);

        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String Nom = txtNom.getText();
                String Pcognom = txtPcognom.getText();
                String Scognom = txtScognom.getText();
                String Nss = txtNss.getText();
                String Nif = txtNif.getText();
                String Tlf = txtTlf.getText();
                String Via = txtVia.getText();
                String Calle = txtCalle.getText();
                String Num = txtNum.getText();
                String cob;
                String Numpl = txtNumpl.getText();
                String Piso = txtPiso.getText();
                String Ciudad = txtCiudad.getText();
                String CP = txtCP.getText();

                if (txtcob.isSelected()) {
                    cob = "Si";//CASA
                } else {
                    cob = "No";//PISO
                }

                if ("".equals(Nom) || "".equals(Pcognom) || "".equals(Scognom) || "".equals(Nss) || "".equals(Nif) || "".equals(Tlf) || "".equals(Via) || "".equals(Calle) || "".equals(Num) || "".equals(cob) || "".equals(Numpl) || "".equals(Piso) || "".equals(Ciudad) || "".equals(CP)) {
                    JOptionPane.showMessageDialog(null, "Algunos de los campos esta vacio. Rellenalo y vuelve a probar");
                } else {
                    try {
                        saved.comprobarNouPacient(Nom, Pcognom, Scognom, Nss, Nif, Tlf, Via, Calle, Num, cob, Numpl, Piso, Ciudad, CP);
                        JOptionPane.showMessageDialog(null, "Nou pacient " + Pcognom + " " + Scognom + " , " + Nom + " creado correctamente.");
                        Ventana.menuAnadir.abrir(saved);
                        ventana.dispose();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "No se ha podido registrar el pacient\n" + e.getMessage());
                    }
                }
            }

        });

        ventana.add(Nom);
        ventana.add(txtNom);
        ventana.add(Pcognom);
        ventana.add(txtPcognom);
        ventana.add(Scognom);
        ventana.add(txtScognom);
        ventana.add(Nss);
        ventana.add(txtNss);
        ventana.add(Nif);
        ventana.add(txtNif);
        ventana.add(Tlf);
        ventana.add(txtTlf);
        ventana.add(Via);
        ventana.add(txtVia);
        ventana.add(Calle);
        ventana.add(txtCalle);
        ventana.add(Num);
        ventana.add(txtNum);
        ventana.add(cob);
        ventana.add(txtcob);
        ventana.add(Numpl);
        ventana.add(txtNumpl);
        ventana.add(Piso);
        ventana.add(txtPiso);
        ventana.add(Ciudad);
        ventana.add(txtCiudad);
        ventana.add(CP);
        ventana.add(txtCP);
        ventana.add(btnEnviar);
        return ventana;
    }
    //-------- Fin Pacient ---------------------------------------------------------------

    //-------- Nou Metge -----------------------------------------------------------------
    public JFrame VnouMetge(JFrame ventana) {
        JButton btnEnviar = new JButton();
        JLabel Nom = new JLabel();
        JTextField txtNom = new JTextField();
        JLabel numMetge = new JLabel();
        JTextField txtNumMetge = new JTextField();
        JLabel Pcognom = new JLabel();
        JTextField txtPcognom = new JTextField();
        JLabel Scognom = new JLabel();
        JTextField txtScognom = new JTextField();
        JLabel Nss = new JLabel();
        JTextField txtNss = new JTextField();
        JLabel Nif = new JLabel();
        JTextField txtNif = new JTextField();
        JLabel Tlf = new JLabel();
        JTextField txtTlf = new JTextField();
        JLabel Via = new JLabel();
        JTextField txtVia = new JTextField();
        JLabel Calle = new JLabel();
        JTextField txtCalle = new JTextField();
        JLabel Num = new JLabel();
        JTextField txtNum = new JTextField();
        JLabel cob = new JLabel();
        JCheckBox txtcob = new JCheckBox();
        txtcob.setSelected(false);
        JLabel Numpl = new JLabel();
        JTextField txtNumpl = new JTextField();
        JLabel Piso = new JLabel();
        JTextField txtPiso = new JTextField();
        JLabel Ciudad = new JLabel();
        JTextField txtCiudad = new JTextField();
        JLabel CP = new JLabel();
        JTextField txtCP = new JTextField();
        JLabel salari = new JLabel();
        JLabel compteCorrent = new JLabel();
        JTextField txtSalari = new JTextField();
        JTextField txtCompteCorrent = new JTextField();

        btnEnviar.setText("Enviar");
        Nom.setText("Nom metge: ");
        Pcognom.setText("PrimerCognom: ");
        Scognom.setText("SegonCognom: ");
        Nss.setText("Numero seguretat social: ");
        Nif.setText("NIF: ");
        Tlf.setText("Telefono: ");
        Via.setText("Tipo de via: ");
        Calle.setText("Nombre de la calle/avenida/carretera: ");
        Num.setText("Numero de la vivienda:");
        cob.setText("Es una casa??Check sin marcar significa piso?");
        Numpl.setText("Numero de la planta: ");
        Piso.setText("Piso: ");
        Ciudad.setText("Nombre de la ciudad: ");
        CP.setText("Codigo postal:");
        salari.setText("Salari Mensual:");
        compteCorrent.setText("Compte Corrent:");
        numMetge.setText("Num Empleat");

        Nom.setBounds(porcW(5) + 30, porcH(5) + 30, 400, 30);
        txtNom.setBounds(porcW(25) + 30, porcH(5) + 30, 400, 30);
        Pcognom.setBounds(porcW(5) + 30, porcH(10) + 30, 400, 30);
        txtPcognom.setBounds(porcW(25) + 30, porcH(10) + 30, 400, 30);
        Scognom.setBounds(porcW(5) + 30, porcH(15) + 30, 400, 30);
        txtScognom.setBounds(porcW(25) + 30, porcH(15) + 30, 400, 30);
        Nss.setBounds(porcW(5) + 30, porcH(20) + 30, 400, 30);
        txtNss.setBounds(porcW(25) + 30, porcH(20) + 30, 400, 30);
        Nif.setBounds(porcW(5) + 30, porcH(25) + 30, 400, 30);
        txtNif.setBounds(porcW(25) + 30, porcH(25) + 30, 400, 30);
        Tlf.setBounds(porcW(5) + 30, porcH(30) + 30, 400, 30);
        txtTlf.setBounds(porcW(25) + 30, porcH(30) + 30, 400, 30);
        Via.setBounds(porcW(5) + 30, porcH(35) + 30, 400, 30);
        txtVia.setBounds(porcW(25) + 30, porcH(35) + 30, 400, 30);
        Calle.setBounds(porcW(5) + 30, porcH(40) + 30, 400, 30);
        txtCalle.setBounds(porcW(25) + 30, porcH(40) + 30, 400, 30);
        Num.setBounds(porcW(5) + 30, porcH(45) + 30, 400, 30);
        txtNum.setBounds(porcW(25) + 30, porcH(45) + 30, 400, 30);
        cob.setBounds(porcW(5) + 30, porcH(50) + 30, 400, 30);
        txtcob.setBounds(porcW(30) + 30, porcH(50) + 30, 400, 30);
        Numpl.setBounds(porcW(5) + 30, porcH(55) + 30, 400, 30);
        txtNumpl.setBounds(porcW(25) + 30, porcH(55) + 30, 400, 30);
        Piso.setBounds(porcW(5) + 30, porcH(60) + 30, 400, 30);
        txtPiso.setBounds(porcW(25) + 30, porcH(60) + 30, 400, 30);
        Ciudad.setBounds(porcW(5) + 30, porcH(65) + 30, 400, 30);
        txtCiudad.setBounds(porcW(25) + 30, porcH(65) + 30, 400, 30);
        CP.setBounds(porcW(5) + 30, porcH(70) + 30, 400, 30);
        txtCP.setBounds(porcW(25) + 30, porcH(70) + 30, 400, 30);
        salari.setBounds(porcW(5) + 30, porcH(75) + 30, 400, 30);
        txtSalari.setBounds(porcW(25) + 30, porcH(75) + 30, 400, 30);
        compteCorrent.setBounds(porcW(5) + 30, porcH(80) + 30, 400, 30);
        txtCompteCorrent.setBounds(porcW(25) + 30, porcH(80) + 30, 400, 30);
        numMetge.setBounds(porcW(5) + 30, porcH(85) + 30, 400, 30);
        txtNumMetge.setBounds(porcW(25) + 30, porcH(85) + 30, 400, 30);
        btnEnviar.setBounds(porcW(30) + 30, porcH(90) + 30, 400, 30);

        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String Nom = txtNom.getText();
                String Pcognom = txtPcognom.getText();
                String Scognom = txtScognom.getText();
                String Nss = txtNss.getText();
                String Nif = txtNif.getText();
                String Tlf = txtTlf.getText();
                String Via = txtVia.getText();
                String Calle = txtCalle.getText();
                String Num = txtNum.getText();
                String cob;
                String Numpl = txtNumpl.getText();
                String Piso = txtPiso.getText();
                String Ciudad = txtCiudad.getText();
                String CP = txtCP.getText();
                String salari = txtSalari.getText();
                String compteCorrent = txtCompteCorrent.getText();
                String numeroEmpleat = txtNumMetge.getText();

                if (txtcob.isSelected()) {
                    cob = "Si";
                } else {
                    cob = "No";
                }

                if ("".equals(numeroEmpleat) || "".equals(salari) || "".equals(compteCorrent) || "".equals(Nom) || "".equals(Pcognom) || "".equals(Scognom) || "".equals(Nss) || "".equals(Nif) || "".equals(Tlf) || "".equals(Via) || "".equals(Calle) || "".equals(Num) || "".equals(cob) || "".equals(Numpl) || "".equals(Piso) || "".equals(Ciudad) || "".equals(CP)) {
                    JOptionPane.showMessageDialog(null, "Algunos de los campos esta vacio. Rellenalo y vuelve a probar");
                } else {
                    try {
                        saved.comprobarNouMetge(Nom, Pcognom, Scognom, Nss, Nif, Tlf, Via, Calle, Num, cob, Numpl, Piso, Ciudad, CP, salari, compteCorrent);
                        JOptionPane.showMessageDialog(null, "Nou metge " + Pcognom + " " + Scognom + " , " + Nom + " creado correctamente.");
                        Ventana.menuAnadir.abrir(saved);
                        ventana.dispose();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "No se ha podido registrar el pacient\n" + e.getMessage());
                    }
                }
            }

        });

        ventana.add(Nom);
        ventana.add(txtNom);
        ventana.add(Pcognom);
        ventana.add(txtPcognom);
        ventana.add(Scognom);
        ventana.add(txtScognom);
        ventana.add(Nss);
        ventana.add(txtNss);
        ventana.add(Nif);
        ventana.add(txtNif);
        ventana.add(Tlf);
        ventana.add(txtTlf);
        ventana.add(Via);
        ventana.add(txtVia);
        ventana.add(Calle);
        ventana.add(txtCalle);
        ventana.add(Num);
        ventana.add(txtNum);
        ventana.add(cob);
        ventana.add(txtcob);
        ventana.add(Numpl);
        ventana.add(txtNumpl);
        ventana.add(Piso);
        ventana.add(txtPiso);
        ventana.add(Ciudad);
        ventana.add(txtCiudad);
        ventana.add(CP);
        ventana.add(txtCP);
        ventana.add(salari);
        ventana.add(txtSalari);
        ventana.add(compteCorrent);
        ventana.add(txtCompteCorrent);
        ventana.add(numMetge);
        ventana.add(txtNumMetge);
        ventana.add(btnEnviar);
        return ventana;
    }
    //-------- Fin Metge -----------------------------------------------------------------

    //-------- Nova Malaltia -------------------------------------------------------------
    public JFrame VnovaMalaltia(JFrame ventana) {
        JButton btnEnviar = new JButton();
        JLabel Malal = new JLabel();
        JTextField txtMalal = new JTextField();
        JLabel Motiu = new JLabel();
        JCheckBox txtMotiu = new JCheckBox();
        txtMotiu.setSelected(false);
        JLabel Descriu = new JLabel();
        JTextField txtDescriu = new JTextField();
        JLabel Durada = new JLabel();
        JTextField txtDurada = new JTextField();

        btnEnviar.setText("Enviar");
        Malal.setText("Introdueix nom malaltia:");
        Motiu.setText("Es motiu de baixa?");
        Descriu.setText("Descriu el seu tractament:");
        Durada.setText("Cuans dies dura el tractament:");

        Malal.setBounds(porcW(5) + 30, porcH(5) + 30, 200, 30);
        txtMalal.setBounds(porcW(25) + 30, porcH(5) + 30, 200, 30);
        Motiu.setBounds(porcW(5) + 30, porcH(10) + 30, 200, 30);
        txtMotiu.setBounds(porcW(25) + 30, porcH(10) + 30, 200, 30);
        Descriu.setBounds(porcW(5) + 30, porcH(15) + 30, 200, 30);
        txtDescriu.setBounds(porcW(25) + 30, porcH(15) + 30, 200, 30);
        Durada.setBounds(porcW(5) + 30, porcH(20) + 30, 200, 30);
        txtDurada.setBounds(porcW(25) + 30, porcH(20) + 30, 200, 30);
        btnEnviar.setBounds(porcW(30) + 30, porcH(30) + 30, 200, 30);

        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String Malal = txtMalal.getText();
                String Motiu;
                String Descriu = txtDescriu.getText();
                String Durada = txtDurada.getText();

                if (txtMotiu.isSelected()) {
                    Motiu = "Si";
                } else {
                    Motiu = "No";
                }
                if ("".equals(Malal) || "".equals(Motiu) || "".equals(Descriu) || "".equals(Durada)) {
                    JOptionPane.showMessageDialog(null, "Algunos de los campos esta vacio. Rellenalo y vuelve a probar");
                } else {
                    try {
                        saved.comprobarNovaMalaltia(Malal, Motiu, Descriu, Durada);
                        JOptionPane.showMessageDialog(null, "Malaltia " + Malal + " creada correctamente.");
                        Ventana.menuAnadir.abrir(saved);
                        ventana.dispose();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "No se ha podido registrar el pacient\n" + e.getMessage());
                    }
                }
            }
        });

        ventana.add(Malal);
        ventana.add(txtMalal);
        ventana.add(Motiu);
        ventana.add(txtMotiu);
        ventana.add(Descriu);
        ventana.add(txtDescriu);
        ventana.add(Durada);
        ventana.add(txtDurada);
        ventana.add(btnEnviar);
        return ventana;
    }
    //-------- Fin Malaltia --------------------------------------------------------------
//----------------------------------------------------------------------------------------

//------------ MOSTA PACIENT, MOSTRA METGE, MOSTRA HISTORIAL, MOSTRA MALALTIA ------------
    //------------ Mostra Pacient --------------------------------------------------------
    public JFrame VmostraPacient(JFrame ventana) {
        JButton btnMostraTot = new JButton();
        JButton btnMostraEspecific = new JButton();
        btnMostraTot.setText("Mostar tots (noms i cognoms amb el dni)");
        btnMostraEspecific.setText("Mostrar especific");

        btnMostraTot.setBounds(porcW(5) + 30, porcH(5) + 30, porcW(40) - 60, porcH(90) - 60);
        btnMostraEspecific.setBounds(porcW(55) + 30, porcH(5) + 30, porcW(40) - 60, porcH(90) - 60);

        btnMostraTot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana.menuAntesPTots.abrir(saved);
                ventana.dispose();
            }
        });

        btnMostraEspecific.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana.menuAntesPEspecific.abrir(saved);
                ventana.dispose();
            }
        });
        ventana.add(btnMostraTot);
        ventana.add(btnMostraEspecific);
        return ventana;
    }

    public JFrame VtotPacient(JFrame ventana) {
        JButton btnTornar = new JButton();
        JLabel test = new JLabel();
        //JTextField txtMostraDades = new JTextField();
        btnTornar.setText("Tornar");
        btnTornar.setBounds(porcW(30) + 30, porcH(85) + 30, 400, 30);
        test.setBounds(porcW(5) + 30, porcH(10) + 30, 900, 400);
        //txtMostraDades.setBounds(porcW(5) + 30, porcH(10) + 30, 900, 400);
        //txtMostraDades.setEditable(false);

        String aux = "", aux1 = "";
        for (int i = 0; i < saved.elObjeto.pacients.size(); i++) {
            String frase = saved.elObjeto.pacients.get(i).nom + " " + saved.elObjeto.pacients.get(i).cognom1 + " "
                    + saved.elObjeto.pacients.get(i).cognom2 + " " + saved.elObjeto.pacients.get(i).nif;
            aux = aux1;
            aux1 = aux.concat(frase + "<br>");
        }
        aux1 = "<html>" + aux1 + "</html>";
        test.setText(aux1);

        btnTornar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana.menuMostraPacient.abrir(saved);
                ventana.dispose();
            }
        });
        ventana.add(btnTornar);
        ventana.add(test);
        //ventana.add(txtMostraDades);
        return ventana;
    }

    public JFrame VespecificPacient(JFrame ventana) {
        JButton btnEnviar = new JButton();
        JLabel NIF = new JLabel();
        JTextField txtNIF = new JTextField();

        btnEnviar.setText("Enviar");
        NIF.setText("NIF");

        NIF.setBounds(50, 10, 100, 30);
        txtNIF.setBounds(100, 10, 200, 30);
        btnEnviar.setBounds(350, 10, 100, 30);

        JButton btnAceptar = new JButton();
        JLabel Nom = new JLabel();
        JTextField txtNom = new JTextField();
        JLabel Pcognom = new JLabel();
        JTextField txtPcognom = new JTextField();
        JLabel Scognom = new JLabel();
        JTextField txtScognom = new JTextField();
        JLabel Nss = new JLabel();
        JTextField txtNss = new JTextField();
        JLabel Nif = new JLabel();
        JTextField txtNif = new JTextField();
        JLabel Tlf = new JLabel();
        JTextField txtTlf = new JTextField();
        JLabel Via = new JLabel();
        JTextField txtVia = new JTextField();

        txtNif.setEditable(false);
        txtNom.setEditable(false);
        txtNss.setEditable(false);
        txtPcognom.setEditable(false);
        txtScognom.setEditable(false);
        txtTlf.setEditable(false);
        txtVia.setEditable(false);

        btnAceptar.setText("Aceptar");
        Nom.setText("Nom pacient: ");
        Pcognom.setText("PrimerCognom: ");
        Scognom.setText("SegonCognom: ");
        Nss.setText("Numero seguretat social: ");
        Nif.setText("NIF: ");
        Tlf.setText("Telefono: ");
        Via.setText("Tipo de via: ");

        Nom.setBounds(porcW(5) + 30, porcH(5) + 30, 400, 30);
        txtNom.setBounds(porcW(25) + 30, porcH(5) + 30, 400, 30);
        Pcognom.setBounds(porcW(5) + 30, porcH(10) + 30, 400, 30);
        txtPcognom.setBounds(porcW(25) + 30, porcH(10) + 30, 400, 30);
        Scognom.setBounds(porcW(5) + 30, porcH(15) + 30, 400, 30);
        txtScognom.setBounds(porcW(25) + 30, porcH(15) + 30, 400, 30);
        Nss.setBounds(porcW(5) + 30, porcH(20) + 30, 400, 30);
        txtNss.setBounds(porcW(25) + 30, porcH(20) + 30, 400, 30);
        Nif.setBounds(porcW(5) + 30, porcH(25) + 30, 400, 30);
        txtNif.setBounds(porcW(25) + 30, porcH(25) + 30, 400, 30);
        Tlf.setBounds(porcW(5) + 30, porcH(30) + 30, 400, 30);
        txtTlf.setBounds(porcW(25) + 30, porcH(30) + 30, 400, 30);
        Via.setBounds(porcW(5) + 30, porcH(35) + 30, 400, 30);
        txtVia.setBounds(porcW(25) + 30, porcH(35) + 30, 400, 30);
        btnAceptar.setBounds(porcW(30) + 30, porcH(75) + 30, 400, 30);

        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana.menuPrincipal.abrir(saved);
                ventana.dispose();
            }
        });

        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String nif = txtNIF.getText();
                txtNom.setText(saved.elObjeto.pacient(nif).nom);
                txtPcognom.setText(saved.elObjeto.pacient(nif).cognom1);
                txtScognom.setText(saved.elObjeto.pacient(nif).cognom2);
                txtNss.setText(saved.elObjeto.pacient(nif).numSegSocial);
                txtNif.setText(saved.elObjeto.pacient(nif).nif);
                txtTlf.setText(saved.elObjeto.pacient(nif).telefon);
                txtVia.setText(saved.elObjeto.pacient(nif).adreca.toString());
            }
        });

        ventana.add(Nom);
        ventana.add(txtNom);
        ventana.add(Pcognom);
        ventana.add(txtPcognom);
        ventana.add(Scognom);
        ventana.add(txtScognom);
        ventana.add(Nss);
        ventana.add(txtNss);
        ventana.add(Nif);
        ventana.add(txtNif);
        ventana.add(Tlf);
        ventana.add(txtTlf);
        ventana.add(Via);
        ventana.add(txtVia);
        ventana.add(btnAceptar);

        ventana.add(NIF);
        ventana.add(txtNIF);
        ventana.add(btnEnviar);
        return ventana;
    }
    //------------ Fin Pacient -----------------------------------------------------------

    //----------- Mostra Metge -----------------------------------------------------------
    public JFrame VmostraMetge(JFrame ventana) {
        JButton btnMostraTot = new JButton();
        JButton btnMostraEspecific = new JButton();
        btnMostraTot.setText("Mostar tots (noms i cognoms amb el dni)");
        btnMostraEspecific.setText("Mostrar especific");

        btnMostraTot.setBounds(porcW(5) + 30, porcH(5) + 30, porcW(40) - 60, porcH(90) - 60);
        btnMostraEspecific.setBounds(porcW(55) + 30, porcH(5) + 30, porcW(40) - 60, porcH(90) - 60);

        btnMostraTot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana.menuAntesMTots.abrir(saved);
                ventana.dispose();
            }
        });

        btnMostraEspecific.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana.menuAntesMEspecific.abrir(saved);
                ventana.dispose();
            }
        });
        ventana.add(btnMostraTot);
        ventana.add(btnMostraEspecific);
        return ventana;
    }

    public JFrame VtotMetge(JFrame ventana) {
        JButton btnTornar = new JButton();
        //JTextField txtMostraDades = new JTextField();
        JLabel test = new JLabel();
        btnTornar.setText("Tornar");
        btnTornar.setBounds(porcW(30) + 30, porcH(85) + 30, 400, 30);
        //txtMostraDades.setBounds(porcW(5) + 30, porcH(10) + 30, 900, 400);
        test.setBounds(porcW(5) + 30, porcH(5) + 30, 900, 400);
        //txtMostraDades.setEditable(false);

        String aux = "", aux1 = "";
        for (int i = 0; i < saved.elObjeto.metges.size(); i++) {
            String frase = saved.elObjeto.metges.get(i).nom + " " + saved.elObjeto.metges.get(i).cognom1 + " "
                    + saved.elObjeto.metges.get(i).cognom2 + " " + saved.elObjeto.metges.get(i).nif;
            aux = aux1;
            aux1 = aux.concat(frase + "<br>");
        }
        aux1 = "<html>" + aux1 + "</html>";
        test.setText(aux1);

        btnTornar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana.menuMostraMetge.abrir(saved);
                ventana.dispose();
            }
        });
        ventana.add(btnTornar);
        ventana.add(test);
        // ventana.add(txtMostraDades);
        return ventana;
    }

    public JFrame VespecificMetge(JFrame ventana) {
        JButton btnEnviar = new JButton();
        JLabel NIF = new JLabel();
        JTextField txtNIF = new JTextField();

        btnEnviar.setText("rellenar");
        NIF.setText("NIF");

        NIF.setBounds(50, 10, 100, 30);
        txtNIF.setBounds(100, 10, 200, 30);
        btnEnviar.setBounds(350, 10, 100, 30);

        JButton btnAceptar = new JButton();
        JLabel Nom = new JLabel();
        JTextField txtNom = new JTextField();
        JLabel numMetge = new JLabel();
        JTextField txtNumMetge = new JTextField();
        JLabel Pcognom = new JLabel();
        JTextField txtPcognom = new JTextField();
        JLabel Scognom = new JLabel();
        JTextField txtScognom = new JTextField();
        JLabel Nss = new JLabel();
        JTextField txtNss = new JTextField();
        JLabel Nif = new JLabel();
        JTextField txtNif = new JTextField();
        JLabel Tlf = new JLabel();
        JTextField txtTlf = new JTextField();
        JLabel Via = new JLabel();
        JTextField txtVia = new JTextField();
        JLabel salari = new JLabel();
        JLabel compteCorrent = new JLabel();
        JTextField txtSalari = new JTextField();
        JTextField txtCompteCorrent = new JTextField();

        btnAceptar.setText("Aceptar");
        Nom.setText("Nom metge: ");
        Pcognom.setText("PrimerCognom: ");
        Scognom.setText("SegonCognom: ");
        Nss.setText("Numero seguretat social: ");
        Nif.setText("NIF: ");
        Tlf.setText("Telefono: ");
        Via.setText("Tipo de via: ");
        salari.setText("Salari Mensual:");
        compteCorrent.setText("Compte Corrent:");
        numMetge.setText("Numero Empleat");

        txtCompteCorrent.setEditable(false);
        txtNif.setEditable(false);
        txtNom.setEditable(false);
        txtNss.setEditable(false);
        txtPcognom.setEditable(false);
        txtSalari.setEditable(false);
        txtScognom.setEditable(false);
        txtTlf.setEditable(false);
        txtVia.setEditable(false);
        txtNumMetge.setEditable(false);

        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String nif = txtNIF.getText();
                txtNom.setText(saved.elObjeto.metge(nif).nom);
                txtPcognom.setText(saved.elObjeto.metge(nif).cognom1);
                txtScognom.setText(saved.elObjeto.metge(nif).cognom2);
                txtNss.setText(saved.elObjeto.metge(nif).numSegSocial);
                txtNif.setText(saved.elObjeto.metge(nif).nif);
                txtTlf.setText(saved.elObjeto.metge(nif).telefon);
                txtVia.setText(saved.elObjeto.metge(nif).adreca.toString());
                txtSalari.setText(saved.elObjeto.metge(nif).salariMensual + "");
                txtCompteCorrent.setText(saved.elObjeto.metge(nif).codiCompteCorrent);
                txtNumMetge.setText(saved.elObjeto.metge(nif).numEmpleat + "");
            }
        });

        //txtCalle.setText(elObjeto.metge().nom);
        Nom.setBounds(porcW(5) + 30, porcH(5) + 30, 400, 30);
        txtNom.setBounds(porcW(25) + 30, porcH(5) + 30, 400, 30);
        Pcognom.setBounds(porcW(5) + 30, porcH(10) + 30, 400, 30);
        txtPcognom.setBounds(porcW(25) + 30, porcH(10) + 30, 400, 30);
        Scognom.setBounds(porcW(5) + 30, porcH(15) + 30, 400, 30);
        txtScognom.setBounds(porcW(25) + 30, porcH(15) + 30, 400, 30);
        Nss.setBounds(porcW(5) + 30, porcH(20) + 30, 400, 30);
        txtNss.setBounds(porcW(25) + 30, porcH(20) + 30, 400, 30);
        Nif.setBounds(porcW(5) + 30, porcH(25) + 30, 400, 30);
        txtNif.setBounds(porcW(25) + 30, porcH(25) + 30, 400, 30);
        Tlf.setBounds(porcW(5) + 30, porcH(30) + 30, 400, 30);
        txtTlf.setBounds(porcW(25) + 30, porcH(30) + 30, 400, 30);
        Via.setBounds(porcW(5) + 30, porcH(35) + 30, 400, 30);
        txtVia.setBounds(porcW(25) + 30, porcH(35) + 30, 400, 30);
        salari.setBounds(porcW(5) + 30, porcH(40) + 30, 400, 30);
        txtSalari.setBounds(porcW(25) + 30, porcH(40) + 30, 400, 30);
        compteCorrent.setBounds(porcW(5) + 30, porcH(45) + 30, 400, 30);
        txtCompteCorrent.setBounds(porcW(25) + 30, porcH(45) + 30, 400, 30);
        numMetge.setBounds(porcW(5) + 30, porcH(50) + 30, 400, 30);
        txtNumMetge.setBounds(porcW(25) + 30, porcH(50) + 30, 400, 30);
        btnAceptar.setBounds(porcW(30) + 30, porcH(55) + 30, 400, 30);

        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana.menuPrincipal.abrir(saved);
                ventana.dispose();
            }
        });

        ventana.add(Nom);
        ventana.add(txtNom);
        ventana.add(Pcognom);
        ventana.add(txtPcognom);
        ventana.add(Scognom);
        ventana.add(txtScognom);
        ventana.add(Nss);
        ventana.add(txtNss);
        ventana.add(Nif);
        ventana.add(txtNif);
        ventana.add(Tlf);
        ventana.add(txtTlf);
        ventana.add(Via);
        ventana.add(txtVia);
        ventana.add(salari);
        ventana.add(compteCorrent);
        ventana.add(txtSalari);
        ventana.add(txtCompteCorrent);
        ventana.add(numMetge);
        ventana.add(txtNumMetge);
        ventana.add(btnAceptar);

        ventana.add(NIF);
        ventana.add(txtNIF);
        ventana.add(btnEnviar);
        return ventana;
    }
    //----------- Fin Metge --------------------------------------------------------------

    //----------- Mostrar Historial ------------------------------------------------------    
    public JFrame VmostraHistorial(JFrame ventana) {
        JButton btnMostraTot = new JButton();
        JButton btnMostraEspecific = new JButton();
        btnMostraTot.setText("Mostar nom i codi historial dels pacients");
        btnMostraEspecific.setText("Mostrar historial de visita especific");

        btnMostraTot.setBounds(porcW(5) + 30, porcH(5) + 30, porcW(40) - 60, porcH(90) - 60);
        btnMostraEspecific.setBounds(porcW(55) + 30, porcH(5) + 30, porcW(40) - 60, porcH(90) - 60);

        btnMostraTot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana.menuAntesHTot.abrir(saved);
                ventana.dispose();
            }
        });

        btnMostraEspecific.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana.menuAntesHEspecific.abrir(saved);
                ventana.dispose();
            }
        });

        ventana.add(btnMostraTot);
        ventana.add(btnMostraEspecific);
        return ventana;
    }

    public JFrame VtotHistorial(JFrame ventana) {
        JButton btnTornar = new JButton();
        //JTextField txtMostraDades = new JTextField();
        JLabel test = new JLabel();
        btnTornar.setText("Tornar");
        btnTornar.setBounds(porcW(30) + 30, porcH(85) + 30, 400, 30);
        //txtMostraDades.setBounds(porcW(5) + 30, porcH(10) + 30, 900, 400);
        test.setBounds(porcW(5) + 30, porcH(5) + 30, 400, 30);
        //txtMostraDades.setEditable(false);

        String aux = "", aux1 = "";
        for (int i = 0; i < saved.elObjeto.pacients.size(); i++) {
            String frase = saved.elObjeto.pacients.get(i).nom + " " + saved.elObjeto.pacients.get(i).cognom1 + " "
                    + saved.elObjeto.pacients.get(i).cognom2 + " " + i;
            aux = aux1;
            aux1 = aux.concat(frase + "<br>");
        }
        aux1 = "<html>" + aux1 + "</html>";
        test.setText(aux1);

        btnTornar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana.menuMostraHistorial.abrir(saved);
                ventana.dispose();
            }
        });
        ventana.add(btnTornar);
        ventana.add(test);
        //ventana.add(txtMostraDades);
        return ventana;
    }

    public JFrame VespecificHistorial(JFrame ventana) {
        JButton btnEnviar = new JButton();
        JLabel codiHistorial = new JLabel();
        JTextField txtCodiHistorial = new JTextField();

        btnEnviar.setText("Enviar");
        codiHistorial.setText("Introdueix el codi Historial:");

        codiHistorial.setBounds(50, 10, 200, 30);
        txtCodiHistorial.setBounds(200, 10, 200, 30);
        btnEnviar.setBounds(400, 10, 100, 30);

        JButton btnAceptar = new JButton();
        JLabel visita = new JLabel();
        //JTextField txtMostraDades = new JTextField();

        visita.setText("Historial de vistes:");
        btnAceptar.setText("Aceptar");
        btnAceptar.setBounds(porcW(30) + 30, porcH(85) + 30, 400, 30);
        visita.setBounds(porcW(5) + 30, porcH(2) + 30, 400, 30);
        //txtMostraDades.setBounds(porcW(5) + 30, porcH(10) + 30, 600, 200);
        //txtMostraDades.setEditable(false);
        
        JLabel test =new JLabel();        
        
        test.setBounds(porcW(5) + 30, porcH(7) + 30, 800, 400);
                
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana.menuPrincipal.abrir(saved);
                ventana.dispose();
            }
        });

        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String nif = txtCodiHistorial.getText();
                int c=Integer.parseInt(nif);
                System.out.print(saved.elObjeto.pacients.get(c).historial.toString());
                test.setText("<html>"+saved.elObjeto.pacients.get(c).historial.toString().replace("Historial de visites:\n", "").replace("\n |","<br>")+ "</html>");
            }
        });

        ventana.add(btnAceptar);
        ventana.add(visita);
        //ventana.add(txtMostraDades);
        ventana.add(test);
        ventana.add(codiHistorial);
        ventana.add(txtCodiHistorial);
        ventana.add(btnEnviar);
        return ventana;
    }
    //----------- Fin Historial ----------------------------------------------------------

    //---------- Mostra Malaltia ---------------------------------------------------------
    public JFrame VmostraMalaltia(JFrame ventana) {
        JButton btnMostraTot = new JButton();
        JButton btnMostraEspecific = new JButton();
        btnMostraTot.setText("Mostar totes les malalties (noms i codis)");
        btnMostraEspecific.setText("Mostrar malaltia especifica");

        btnMostraTot.setBounds(porcW(5) + 30, porcH(5) + 30, porcW(40) - 60, porcH(90) - 60);
        btnMostraEspecific.setBounds(porcW(55) + 30, porcH(5) + 30, porcW(40) - 60, porcH(90) - 60);

        btnMostraTot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana.menuAntesMalTots.abrir(saved);
                ventana.dispose();
            }
        });

        btnMostraEspecific.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana.menuAntesMalEspecific.abrir(saved);
                ventana.dispose();
            }
        });

        ventana.add(btnMostraTot);
        ventana.add(btnMostraEspecific);
        return ventana;
    }

    public JFrame VtotMalaltia(JFrame ventana) {
        JButton btnTornar = new JButton();
        //JTextField txtMostraDades = new JTextField();
        JLabel test = new JLabel();
        btnTornar.setText("Tornar");
        btnTornar.setBounds(porcW(30) + 30, porcH(85) + 30, 400, 30);
        test.setBounds(porcW(5) + 30, porcH(5) + 30, 400, 30);
        //txtMostraDades.setBounds(porcW(5) + 30, porcH(10) + 30, 900, 400);
        //txtMostraDades.setEditable(false);

        String aux = "", aux1 = "";
        for (int i = 0; i < saved.elObjeto.malalties.size(); i++) {
            String frase = saved.elObjeto.malalties.get(i).codi + " " + saved.elObjeto.malalties.get(i).nom;
            aux = aux1;
            aux1 = aux.concat(frase + "<br>");
        }
        aux1 = "<html>" + aux1 + "</html>";
        test.setText(aux1);
        
        btnTornar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana.menuMostraMalaltia.abrir(saved);
                ventana.dispose();
            }
        });
        ventana.add(btnTornar);
        ventana.add(test);
        //ventana.add(txtMostraDades);
        return ventana;
    }

    public JFrame VespecificMalaltia(JFrame ventana) {
        JButton btnEnviar = new JButton();
        JLabel codiMalaltia = new JLabel();
        JTextField txtCodiMalaltia = new JTextField();

        btnEnviar.setText("Enviar");
        codiMalaltia.setText("Codi Malaltia:");

        codiMalaltia.setBounds(50, 10, 100, 30);
        txtCodiMalaltia.setBounds(200, 10, 200, 30);
        btnEnviar.setBounds(400, 10, 100, 30);

        ventana.add(codiMalaltia);
        ventana.add(txtCodiMalaltia);
        ventana.add(btnEnviar);

        JButton btnAceptar = new JButton();
        JLabel Malal = new JLabel();
        JTextField txtMalal = new JTextField();
        JLabel Motiu = new JLabel();
        JTextField txtMotiu = new JTextField();
        JLabel Descriu = new JLabel();
        JTextField txtDescriu = new JTextField();
        JLabel Durada = new JLabel();
        JTextField txtDurada = new JTextField();

        btnAceptar.setText("Aceptar");
        Malal.setText("Introdueix nom malaltia:");
        Motiu.setText("Es motiu de baixa?");
        Descriu.setText("Descriu el seu tractament:");
        Durada.setText("Cuans dies dura el tractament:");

        txtDescriu.setEditable(false);
        txtDurada.setEditable(false);
        txtMalal.setEditable(false);
        txtMotiu.setEditable(false);

        Malal.setBounds(porcW(5) + 30, porcH(5) + 30, 200, 30);
        txtMalal.setBounds(porcW(25) + 30, porcH(5) + 30, 200, 30);
        Motiu.setBounds(porcW(5) + 30, porcH(10) + 30, 200, 30);
        txtMotiu.setBounds(porcW(25) + 30, porcH(10) + 30, 200, 30);
        Descriu.setBounds(porcW(5) + 30, porcH(15) + 30, 200, 30);
        txtDescriu.setBounds(porcW(25) + 30, porcH(15) + 30, 200, 30);
        Durada.setBounds(porcW(5) + 30, porcH(20) + 30, 200, 30);
        txtDurada.setBounds(porcW(25) + 30, porcH(20) + 30, 200, 30);
        btnAceptar.setBounds(porcW(30) + 30, porcH(30) + 30, 200, 30);

        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ventana.menuPrincipal.abrir(saved);
                ventana.dispose();
            }
        });

        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String nif = txtCodiMalaltia.getText();
                int num = Integer.parseInt(nif);
                txtMalal.setText(saved.elObjeto.malaltia(num).nom);
                txtMotiu.setText(saved.elObjeto.malaltia(num).causaBaixa + "");
                txtDescriu.setText(saved.elObjeto.malaltia(num).tractament);
                txtDurada.setText(saved.elObjeto.malaltia(num).duradaTractament.toDays() + "");

            }
        });

        ventana.add(Malal);
        ventana.add(txtMalal);
        ventana.add(Motiu);
        ventana.add(txtMotiu);
        ventana.add(Descriu);
        ventana.add(txtDescriu);
        ventana.add(Durada);
        ventana.add(txtDurada);
        ventana.add(btnAceptar);

        return ventana;
    }
    //---------- Fin Malaltia ------------------------------------------------------------
//----------------------------------------------------------------------------------------    

    public int porcW(double entr) {
        return (int) ((double) totalW / 100 * entr);
    }

    public int porcH(double entr) {
        return (int) ((double) (totalH - 30) / 100 * entr);
    }

    private static boolean isNotAlfanumeric(String dato) {
        return dato.matches("^.*[^a-zA-Z0-9].*$");
    }

    private static boolean isNotNumeric(String dato) {
        return dato.matches("^.*[^0-9].*$");
    }

}