import com.mysql.cj.jdbc.StatementImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;

public class Kullanici {


    Connection bag;
    ResultSet resultSet;
    Connection baglanti;


    JButton no1[][];
    JButton no2[][];
    JButton no3[][];
    JButton no4[][];
    JButton no5[][];
    JButton no6[][];
    JButton no7[][];
    int flag1 = 0;
    int flag2 = 0;
    int flag3 = 0;
    int flag4 = 0;
    int flag5 = 0;
    int flag6 = 0;
    int flag7 = 0;


    ArrayList market_no = new ArrayList<Integer>();
    ArrayList mağaza_no = new ArrayList<Integer>();
    ArrayList emlak_no = new ArrayList<Integer>();
    int bayrak = 0;
    int say;
    int i, j;
    ArrayList satılık_arsalar = new ArrayList<Integer>();
    ArrayList satılık_işletmeler = new ArrayList<Integer>();


    public Kullanici(int satir, int sutun, Timestamp dateTimeNow, int işletme_inşa_ücreti, JPanel panel1_1, JPanel panel2_1, JPanel panel3_1, JPanel panel4_1, JPanel panel5_1, JPanel panel6_1, JPanel panel7_1,
                     JTextArea alan1, JTextArea alan2, JTextArea alan3, JTextArea alan4, JTextArea alan5, JTextArea alan6, JTextArea alan7, JTabbedPane sekme, JPanel panel1, JPanel panel2, JPanel panel3, JPanel panel4, JPanel panel5, JPanel panel6, JPanel panel7) {

        JButton no1[][] = new JButton[satir][sutun];
        JButton no2[][] = new JButton[satir][sutun];
        JButton no3[][] = new JButton[satir][sutun];
        JButton no4[][] = new JButton[satir][sutun];
        JButton no5[][] = new JButton[satir][sutun];
        JButton no6[][] = new JButton[satir][sutun];
        JButton no7[][] = new JButton[satir][sutun];


        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            baglanti = DriverManager.getConnection("jdbc:mysql://localhost:3306/proje?autoReconnect=" +
                            "true&useSSL=false" + "&useUnicode= true&useLegacyDatetimeCode=false&serverTimezone=Turkey",
                    "root", "1234");


            String query = "UPDATE kullanıcı SET kullanıcı_çalışma_durumu = ?";
            PreparedStatement statement = baglanti.prepareStatement(query);
            statement.setInt(1, 0);
            statement.executeUpdate();
            baglanti.close();
            statement.close();


        } catch (Exception ex) {
            ex.printStackTrace();
        }


        for (int k = 4; k <= satir * sutun; k++) {

            satılık_arsalar.add(k);
        }


        JFrame kullanici = new JFrame("GİRİŞ");
        kullanici.setBounds(1000, 300, 205, 205);

        JLayeredPane panel = new JLayeredPane();
        panel.setSize(500, 400);

        JTextField kullaniciAdi = new JTextField(" Kullanıcı Adı");
        kullaniciAdi.setBounds(20, 20, 150, 30);

        kullaniciAdi.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (kullaniciAdi.getText().equals(" Kullanıcı Adı")) {
                    kullaniciAdi.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (kullaniciAdi.getText().isEmpty()) {
                    kullaniciAdi.setText(" Kullanıcı Adı");
                }
            }
        });

        JTextField sifre = new JTextField(" Şifre");
        sifre.setBounds(20, 60, 150, 30);

        sifre.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (sifre.getText().equals(" Şifre")) {
                    sifre.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (sifre.getText().isEmpty()) {
                    sifre.setText(" Şifre");
                }
            }
        });

        JButton buton = new JButton("Giriş Yap");
        buton.setBounds(20, 120, 150, 30);


        JFrame frame = new JFrame("KULLANICILAR");
        frame.setBounds(750, 0, 750, 800);

        buton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    bag = DriverManager.getConnection("jdbc:mysql://localhost:3306/proje?autoReconnect=" +
                                    "true&useSSL=false" + "&useUnicode= true&useLegacyDatetimeCode=false&serverTimezone=Turkey",
                            "root", "1234");


                    String text1 = kullaniciAdi.getText();
                    String text2 = sifre.getText();

                    String query = "SELECT * FROM kullanıcı WHERE kullanıcı_adı = ? AND kullanıcı_şifresi = ?";
                    PreparedStatement statement = bag.prepareStatement(query);
                    statement.setString(1, text1);
                    statement.setString(2, text2);
                    resultSet = statement.executeQuery();

                    JLabel label1 = new JLabel("Kullanıcı doğru giriş yaptı! ");
                    label1.setBounds(20, 90, 250, 30);
                    JLabel label2 = new JLabel("Kullanıcı adı veya şifre yanlış! ");
                    label2.setBounds(20, 90, 250, 30);


                    Timer timer = new Timer(2000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            panel.remove(label1);
                            panel.remove(label2);
                            panel.revalidate();
                            panel.repaint();
                        }
                    });

                    if (resultSet.next()) {


                        panel.add(label1);
                        System.out.println("Kullanıcı doğru giriş yaptı!");


                        if (resultSet.getInt("kullanıcı_no") == 1) {

                            flag1 = 1;

                            Oyun_alani(no1, satir, sutun, panel1, sekme, text1, panel1_1, alan1);
                            Resim_koy(no1, satir, sutun);


                            alan1.setText("yemek miktarı:  " + resultSet.getString("kullanıcı_yemek_miktarı") + ",  para miktarı:  " + resultSet.getString("kullanıcı_para_miktarı") + ",  eşya miktarı:  " + resultSet.getString("kullanıcı_eşya_miktarı"));

                            JButton arsa_satılık_çıkar = new JButton("arsa satılığa çıkar");

                            arsa_satılık_çıkar.setBounds(0, 700, 200, 30);

                            arsa_satılık_çıkar.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    arsa_satılığa_çıkar(1, alan1, alan2, alan3, alan4, alan5, alan6, alan7);

                                }
                            });


                            JButton işletme_satılığa_çıkar = new JButton("işletmeni satılığa çıkar");

                            işletme_satılığa_çıkar.setBounds(0, 650, 200, 30);

                            işletme_satılığa_çıkar.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    işletme_satılığa_çıkar(1, alan1, alan2, alan3, alan4, alan5, alan6, alan7);
                                }
                            });


                            işlemler(satir, sutun, no1, no1, no2, no3, no4, no5, no6, no7, 1, dateTimeNow, panel1, işletme_inşa_ücreti, alan1, alan2, alan3, alan4, alan5, alan6, alan7, panel1, panel2, panel3, panel4, panel5, panel6, panel7);

                            panel1.add(arsa_satılık_çıkar);
                            panel1.add(işletme_satılığa_çıkar);


                        } else if (resultSet.getInt("kullanıcı_no") == 2) {

                            flag2 = 1;

                            Oyun_alani(no2, satir, sutun, panel2, sekme, text1, panel2_1, alan2);
                            Resim_koy(no2, satir, sutun);

                            alan2.setText("yemek miktarı:  " + resultSet.getString("kullanıcı_yemek_miktarı") + ",  para miktarı:  " + resultSet.getString("kullanıcı_para_miktarı") + ",  eşya miktarı:  " + resultSet.getString("kullanıcı_eşya_miktarı"));

                            JButton arsa_satılık_çıkar = new JButton("arsa satılığa çıkar");
                            arsa_satılık_çıkar.setBounds(0, 700, 200, 30);

                            arsa_satılık_çıkar.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    arsa_satılığa_çıkar(2, alan1, alan2, alan3, alan4, alan5, alan6, alan7);

                                }
                            });


                            JButton işletme_satılığa_çıkar = new JButton("işletmeni satılığa çıkar");

                            işletme_satılığa_çıkar.setBounds(0, 650, 200, 30);

                            işletme_satılığa_çıkar.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    işletme_satılığa_çıkar(2, alan1, alan2, alan3, alan4, alan5, alan6, alan7);
                                }
                            });

                            /*
                            JButton işletme_kiralığa_çıkar = new JButton("işletmeni satılığa çıkar");

                            işletme_kiralığa_çıkar.setBounds(550,600,200,30);

                            işletme_kiralığa_çıkar.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {


                                }
                            });
*/

                            işlemler(satir, sutun, no2, no1, no2, no3, no4, no5, no6, no7, 2, dateTimeNow, panel2, işletme_inşa_ücreti, alan1, alan2, alan3, alan4, alan5, alan6, alan7, panel1, panel2, panel3, panel4, panel5, panel6, panel7);

                            panel2.add(arsa_satılık_çıkar);
                            panel2.add(işletme_satılığa_çıkar);
                            //  panel2.add(işletme_kiralığa_çıkar);


                        } else if (resultSet.getInt("kullanıcı_no") == 3) {


                            flag3 = 1;
                            Oyun_alani(no3, satir, sutun, panel3, sekme, text1, panel3_1, alan3);

                            Resim_koy(no3, satir, sutun);

                            alan3.setText("yemek miktarı:  " + resultSet.getString("kullanıcı_yemek_miktarı") + ",  para miktarı:  " + resultSet.getString("kullanıcı_para_miktarı") + ",  eşya miktarı:  " + resultSet.getString("kullanıcı_eşya_miktarı"));
                            JButton arsa_satılık_çıkar = new JButton("arsa satılığa çıkar");
                            arsa_satılık_çıkar.setBounds(0, 700, 200, 30);

                            arsa_satılık_çıkar.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    arsa_satılığa_çıkar(3, alan1, alan2, alan3, alan4, alan5, alan6, alan7);

                                }
                            });

                            JButton işletme_satılığa_çıkar = new JButton("işletmeni satılığa çıkar");

                            işletme_satılığa_çıkar.setBounds(0, 650, 200, 30);

                            işletme_satılığa_çıkar.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    işletme_satılığa_çıkar(3, alan1, alan2, alan3, alan4, alan5, alan6, alan7);
                                }
                            });

                            /*
                            JButton işletme_kiralığa_çıkar = new JButton("işletmeni satılığa çıkar");

                            işletme_kiralığa_çıkar.setBounds(550,600,200,30);

                            işletme_kiralığa_çıkar.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {


                                }
                            });
*/

                            işlemler(satir, sutun, no3, no1, no2, no3, no4, no5, no6, no7, 3, dateTimeNow, panel3, işletme_inşa_ücreti, alan1, alan2, alan3, alan4, alan5, alan6, alan7, panel1, panel2, panel3, panel4, panel5, panel6, panel7);

                            panel3.add(arsa_satılık_çıkar);
                            panel3.add(işletme_satılığa_çıkar);
                            //  panel3.add(işletme_kiralığa_çıkar);


                        } else if (resultSet.getInt("kullanıcı_no") == 4) {

                            flag4 = 1;
                            Oyun_alani(no4, satir, sutun, panel4, sekme, text1, panel4_1, alan4);
                            Resim_koy(no4, satir, sutun);

                            alan4.setText("yemek miktarı:  " + resultSet.getString("kullanıcı_yemek_miktarı") + ",  para miktarı:  " + resultSet.getString("kullanıcı_para_miktarı") + ",  eşya miktarı:  " + resultSet.getString("kullanıcı_eşya_miktarı"));
                            JButton arsa_satılık_çıkar = new JButton("arsa satılığa çıkar");
                            arsa_satılık_çıkar.setBounds(0, 700, 200, 30);

                            arsa_satılık_çıkar.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    arsa_satılığa_çıkar(4, alan1, alan2, alan3, alan4, alan5, alan6, alan7);

                                }
                            });

                            JButton işletme_satılığa_çıkar = new JButton("işletmeni satılığa çıkar");

                            işletme_satılığa_çıkar.setBounds(0, 650, 200, 30);

                            işletme_satılığa_çıkar.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    işletme_satılığa_çıkar(4, alan1, alan2, alan3, alan4, alan5, alan6, alan7);
                                }
                            });

                            /*
                            JButton işletme_kiralığa_çıkar = new JButton("işletmeni satılığa çıkar");

                            işletme_kiralığa_çıkar.setBounds(550,600,200,30);

                            işletme_kiralığa_çıkar.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {


                                }
                            });
*/

                            işlemler(satir, sutun, no4, no1, no2, no3, no4, no5, no6, no7, 4, dateTimeNow, panel4, işletme_inşa_ücreti, alan1, alan2, alan3, alan4, alan5, alan6, alan7, panel1, panel2, panel3, panel4, panel5, panel6, panel7);

                            panel4.add(arsa_satılık_çıkar);
                            panel4.add(işletme_satılığa_çıkar);
                            //   panel4.add(işletme_kiralığa_çıkar);

                        } else if (resultSet.getInt("kullanıcı_no") == 5) {

                            flag5 = 1;

                            Oyun_alani(no5, satir, sutun, panel5, sekme, text1, panel5_1, alan5);

                            Resim_koy(no5, satir, sutun);
                            alan5.setText("yemek miktarı:  " + resultSet.getString("kullanıcı_yemek_miktarı") + ",  para miktarı:  " + resultSet.getString("kullanıcı_para_miktarı") + ",  eşya miktarı:  " + resultSet.getString("kullanıcı_eşya_miktarı"));
                            JButton arsa_satılık_çıkar = new JButton("arsa satılığa çıkar");
                            arsa_satılık_çıkar.setBounds(0, 700, 200, 30);

                            arsa_satılık_çıkar.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    arsa_satılığa_çıkar(5, alan1, alan2, alan3, alan4, alan5, alan6, alan7);

                                }
                            });


                            JButton işletme_satılığa_çıkar = new JButton("işletmeni satılığa çıkar");

                            işletme_satılığa_çıkar.setBounds(0, 650, 200, 30);

                            işletme_satılığa_çıkar.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    işletme_satılığa_çıkar(5, alan1, alan2, alan3, alan4, alan5, alan6, alan7);
                                }
                            });

                            /*
                            JButton işletme_kiralığa_çıkar = new JButton("işletmeni satılığa çıkar");

                            işletme_kiralığa_çıkar.setBounds(550,600,200,30);

                            işletme_kiralığa_çıkar.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {


                                }
                            });
*/
                            işlemler(satir, sutun, no5, no1, no2, no3, no4, no5, no6, no7, 5, dateTimeNow, panel5, işletme_inşa_ücreti, alan1, alan2, alan3, alan4, alan5, alan6, alan7, panel1, panel2, panel3, panel4, panel5, panel6, panel7);

                            panel5.add(arsa_satılık_çıkar);
                            panel5.add(işletme_satılığa_çıkar);
                            //   panel5.add(işletme_kiralığa_çıkar);


                        } else if (resultSet.getInt("kullanıcı_no") == 6) {

                            flag6 = 1;

                            Oyun_alani(no6, satir, sutun, panel6, sekme, text1, panel6_1, alan6);
                            Resim_koy(no6, satir, sutun);
                            alan6.setText("yemek miktarı:  " + resultSet.getString("kullanıcı_yemek_miktarı") + ",  para miktarı:  " + resultSet.getString("kullanıcı_para_miktarı") + ",  eşya miktarı:  " + resultSet.getString("kullanıcı_eşya_miktarı"));
                            JButton arsa_satılık_çıkar = new JButton("arsa satılığa çıkar");
                            arsa_satılık_çıkar.setBounds(0, 700, 200, 30);

                            arsa_satılık_çıkar.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    arsa_satılığa_çıkar(6, alan1, alan2, alan3, alan4, alan5, alan6, alan7);

                                }
                            });

                            JButton işletme_satılığa_çıkar = new JButton("işletmeni satılığa çıkar");

                            işletme_satılığa_çıkar.setBounds(0, 650, 200, 30);

                            işletme_satılığa_çıkar.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    işletme_satılığa_çıkar(6, alan1, alan2, alan3, alan4, alan5, alan6, alan7);
                                }
                            });

                            /*
                            JButton işletme_kiralığa_çıkar = new JButton("işletmeni satılığa çıkar");

                            işletme_kiralığa_çıkar.setBounds(550,600,200,30);

                            işletme_kiralığa_çıkar.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {


                                }
                            });
*/
                            işlemler(satir, sutun, no6, no1, no2, no3, no4, no5, no6, no7, 6, dateTimeNow, panel6, işletme_inşa_ücreti, alan1, alan2, alan3, alan4, alan5, alan6, alan7, panel1, panel2, panel3, panel4, panel5, panel6, panel7);

                            panel6.add(arsa_satılık_çıkar);
                            panel6.add(işletme_satılığa_çıkar);
                            //                          panel6.add(işletme_kiralığa_çıkar);


                        } else if (resultSet.getInt("kullanıcı_no") == 7) {

                            flag1 = 7;
                            Oyun_alani(no7, satir, sutun, panel7, sekme, text1, panel7_1, alan7);
                            Resim_koy(no7, satir, sutun);
                            alan7.setText("yemek miktarı:  " + resultSet.getString("kullanıcı_yemek_miktarı") + ",  para miktarı:  " + resultSet.getString("kullanıcı_para_miktarı") + ",  eşya miktarı:  " + resultSet.getString("kullanıcı_eşya_miktarı"));
                            JButton arsa_satılık_çıkar = new JButton("arsa satılığa çıkar");
                            arsa_satılık_çıkar.setBounds(0, 700, 200, 30);

                            arsa_satılık_çıkar.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    arsa_satılığa_çıkar(7, alan1, alan2, alan3, alan4, alan5, alan6, alan7);

                                }
                            });

                            JButton işletme_satılığa_çıkar = new JButton("işletmeni satılığa çıkar");

                            işletme_satılığa_çıkar.setBounds(0, 650, 200, 30);

                            işletme_satılığa_çıkar.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    işletme_satılığa_çıkar(7, alan1, alan2, alan3, alan4, alan5, alan6, alan7);
                                }
                            });

/*
                            JButton işletme_kiralığa_çıkar = new JButton("işletmeni satılığa çıkar");

                            işletme_kiralığa_çıkar.setBounds(550,600,200,30);

                            işletme_kiralığa_çıkar.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {


                                }
                            });
*/
                            işlemler(satir, sutun, no7, no1, no2, no3, no4, no5, no6, no7, 7, dateTimeNow, panel7, işletme_inşa_ücreti, alan1, alan2, alan3, alan4, alan5, alan6, alan7, panel1, panel2, panel3, panel4, panel5, panel6, panel7);

                            panel7.add(arsa_satılık_çıkar);
                            panel7.add(işletme_satılığa_çıkar);
                            //   panel7.add(işletme_kiralığa_çıkar);

                        }

                    } else {

                        panel.add(label2);
                        System.out.println("Kullanıcı adı veya şifre yanlış!");

                    }
                    timer.start();


                } catch (Exception exp) {
                    exp.printStackTrace();
                }


            }
        });


        if (bag != null) {
            try {
                bag.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (resultSet != null) {
            try {
                resultSet.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        frame.getContentPane().add(sekme, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        panel.add(kullaniciAdi);
        panel.add(sifre);
        panel.add(buton);

        kullanici.add(panel);
        kullanici.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        kullanici.setLayout(null);
        kullanici.setVisible(true);

    }


    public void Oyun_alani(JButton[][] buton, int satir, int sutun, JPanel panelNo, JTabbedPane sekme, String ad, JPanel panelNo1, JTextArea alan) {

        int sayacResim = 0;

        for (int i = 0; i < satir; i++) {
            for (int j = 0; j < sutun; j++) {

                int alan_no = i * sutun + j + 1;
                buton[i][j] = new JButton(String.valueOf(alan_no));
                buton[i][j].setBounds(j * 60, i * 60, 60, 60);

                if (sayacResim == 0) {
                    Isletme_Resim(buton[i][j], "market");
                } else if (sayacResim == 1) {
                    Isletme_Resim(buton[i][j], "magaza");
                } else if (sayacResim == 2) {
                    Isletme_Resim(buton[i][j], "emlak");
                }
                sayacResim++;
            }
        }

        for (int i = 0; i < satir; i++) {
            for (int j = 0; j < sutun; j++) {

                panelNo.add(buton[i][j]);
            }
        }

        alan.setBounds(0, 0, 750, 800);


        panelNo1.add(alan);
        panelNo.setLayout(null);
        panelNo1.setLayout(null);
        sekme.addTab(ad, panelNo);
        sekme.addTab(ad + " bilgi", panelNo1);

    }

    public void Isletme_Resim(JButton arsaNo, String isletmeName) {

        String isletme = isletmeName + ".png";
        Image isletme0 = Toolkit.getDefaultToolkit().getImage(isletme).getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon isletme1 = new ImageIcon(isletme0);
        arsaNo.setIcon(new ImageIcon(isletme1.getImage()));

    }

    public void Resim_koy(JButton no[][], int satır, int sutun) {

        for (int k = 0; k < emlak_no.size(); k++) {

            int i = ((int) emlak_no.get(k) - 1) / sutun;
            int j = ((int) emlak_no.get(k) - 1) % sutun;


            Image isletme0 = Toolkit.getDefaultToolkit().getImage("emlak.png").getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            ImageIcon isletme1 = new ImageIcon(isletme0);
            no[i][j].setIcon(new ImageIcon(isletme1.getImage()));

        }

        for (int k = 0; k < market_no.size(); k++) {

            int i = ((int) market_no.get(k) - 1) / sutun;
            int j = ((int) market_no.get(k) - 1) % sutun;


            Image isletme0 = Toolkit.getDefaultToolkit().getImage("market.png").getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            ImageIcon isletme1 = new ImageIcon(isletme0);
            no[i][j].setIcon(new ImageIcon(isletme1.getImage()));

        }

        for (int k = 0; k < mağaza_no.size(); k++) {

            int i = ((int) mağaza_no.get(k) - 1) / sutun;
            int j = ((int) mağaza_no.get(k) - 1) % sutun;


            Image isletme0 = Toolkit.getDefaultToolkit().getImage("magaza.png").getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            ImageIcon isletme1 = new ImageIcon(isletme0);
            no[i][j].setIcon(new ImageIcon(isletme1.getImage()));

        }

    }


    public void işlemler(int satir, int sutun, JButton no[][], JButton no1[][], JButton no2[][], JButton no3[][], JButton no4[][], JButton no5[][], JButton no6[][], JButton no7[][], int kullanıcı_no, Timestamp dateTimeNow, JPanel panel, int işletme_inşa_ücreti, JTextArea alan1, JTextArea alan2, JTextArea alan3, JTextArea alan4, JTextArea alan5, JTextArea alan6, JTextArea alan7, JPanel panel1, JPanel panel2, JPanel panel3, JPanel panel4, JPanel panel5, JPanel panel6, JPanel panel7) {


        int say = 0;


        for (i = 0; i < satir; i++) {
            for (j = 0; j < sutun; j++) {

                say++;

                int finalSay = say;


                no[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        int bayrak = 1;

                        try {
                            String sql = "SELECT * FROM alan WHERE alan_no = ?";
                            PreparedStatement p1 = bag.prepareStatement(sql);
                            p1.setInt(1, finalSay);
                            ResultSet resultSet = p1.executeQuery();

                            if (resultSet.next()) {
                                String alan_türü = resultSet.getString("alan_türü");
                                if (alan_türü.equals("arsa")) {
                                    bayrak = 0;
                                }

                            }
                            p1.close();
                            resultSet.close();

                        } catch (Exception exp) {
                            exp.printStackTrace();
                        }

                        if (bayrak == 1) {
                            JFrame isIstegi = new JFrame();
                            isIstegi.setBounds(900, 200, 200, 150);
                            JButton is = new JButton("İş Talebi Gönder");
                            is.setBounds(10, 10, 150, 30);
                            JButton alışveriş = new JButton("Alışveriş");
                            alışveriş.setBounds(10, 50, 150, 30);


                            alışveriş.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    Timestamp time = dateTimeNow;
                                    if (Çalışma_saati_kontrol(kullanıcı_no, time)) {
                                        String msj = "çalışma saatleri içerisinde ticari işlem yapılamaz!";
                                        String baslik = "Uyarı";

                                        JOptionPane.showMessageDialog(null, msj, baslik, JOptionPane.WARNING_MESSAGE);
                                    } else {
                                        alışveriş1(kullanıcı_no, finalSay, dateTimeNow, işletme_inşa_ücreti, satir, sutun, no, no1, no2, no3, no4, no5, no6, no7, alan1, alan2, alan3, alan4, alan5, alan6, alan7);
                                        isIstegi.setVisible(false);
                                    }

                                }
                            });

                            is.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {


                                    try {
                                        String sql = "SELECT * FROM işletme WHERE işletme_no = ?";
                                        PreparedStatement p1 = bag.prepareStatement(sql);
                                        p1.setInt(1, finalSay);
                                        ResultSet resultSet = p1.executeQuery();

                                        boolean kapasite = false;
                                        if (resultSet.next()) {
                                            if (resultSet.getInt("işletme_kapasitesi") == resultSet.getInt("işletme_çalışan_sayısı")) {
                                                kapasite = true;
                                            }
                                        }
                                        if (kapasite) {

                                            String msj = "İşletme çalışan kapasitesi dolu!";
                                            String baslik = "Uyarı";
                                            JOptionPane.showMessageDialog(null, msj, baslik, JOptionPane.WARNING_MESSAGE);

                                        } else
                                            İş_Alımı(satir, sutun, no, kullanıcı_no, panel, finalSay, dateTimeNow, isIstegi, alan1, alan2, alan3, alan4, alan5, alan6, alan7, panel1, panel2, panel3, panel4, panel5, panel6, panel7);


                                    } catch (Exception exp) {
                                        exp.printStackTrace();
                                    }


                                }
                            });


                            isIstegi.add(alışveriş);

                            /////////////////////////////////////////////////////////////////////////////////////////////
                            isIstegi.add(is);

                            isIstegi.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            isIstegi.setLayout(null);
                            isIstegi.setVisible(true);

                        }

                    }
                });


            }
        }


    }


    public void alışveriş1(int kullanıcı_no, int finalSay, Timestamp dateTimeNow, int işletme_inşa_ücreti, int satir, int sutun, JButton no[][], JButton no1[][], JButton no2[][], JButton no3[][], JButton no4[][], JButton no5[][], JButton no6[][], JButton no7[][], JTextArea alan1, JTextArea alan2, JTextArea alan3, JTextArea alan4, JTextArea alan5, JTextArea alan6, JTextArea alan7) {


        JFrame alışveriş_frame = new JFrame();
        alışveriş_frame.setBounds(900, 200, 500, 250);

        JButton satın_al = new JButton();
        satın_al.setBounds(10, 10, 150, 30);

        JButton arsa_satın_al = new JButton("arsa satın al");
        arsa_satın_al.setBounds(0, 0, 150, 30);

        JButton işletme_satın_al = new JButton("işletme satın al");

        işletme_satın_al.setBounds(0, 40, 150, 30);

        JButton işletme_kirala = new JButton("işletme kirala");
        işletme_kirala.setBounds(0, 80, 150, 30);

        JButton işletme_kur = new JButton("işletme_kur");
        işletme_kur.setBounds(0, 120, 150, 30);

        JLabel etiket1 = new JLabel();
        etiket1.setBounds(10, 50, 150, 30);

        try {


            PreparedStatement preparedStatement1 = bag.prepareStatement("SELECT * FROM alan WHERE alan_no = ?");

            preparedStatement1.setInt(1, finalSay);

            ResultSet rs1 = preparedStatement1.executeQuery();


            String sql = "SELECT * FROM işletme WHERE işletme_no = ?";
            PreparedStatement p1 = bag.prepareStatement(sql);
            p1.setInt(1, finalSay);

            ResultSet resultSet1 = p1.executeQuery();

            if (resultSet1.next()) {
                String işletme_türü = resultSet1.getString("işletme_türü");


                if (işletme_türü.equals("market")) {
                    satın_al.setText("yiyecek satın al");


                    PreparedStatement preparedStatement = bag.prepareStatement("SELECT * FROM market WHERE market_no = ?");

                    preparedStatement.setInt(1, finalSay);
                    ResultSet resSet = preparedStatement.executeQuery();

                    if (resSet.next()) {

                        etiket1.setText("yiyecek ücreti : " + resSet.getInt("market_yiyecek_ücreti"));
                    }
                    preparedStatement.close();
                    resSet.close();


                    satın_al.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            try {


                                PreparedStatement pre1 = bag.prepareStatement("SELECT * FROM alan WHERE alan_no = ?");

                                pre1.setInt(1, finalSay);
                                ResultSet rs1 = pre1.executeQuery();
                                int kullanıcı_no1 = 0;

                                if (rs1.next()) {
                                    kullanıcı_no1 = rs1.getInt("alan_sahibi");
                                }


                                PreparedStatement pre2 = bag.prepareStatement("SELECT * FROM kullanıcı WHERE kullanıcı_no = ?");
                                pre2.setInt(1, kullanıcı_no1);

                                ResultSet rs2 = pre2.executeQuery();
                                int satıcı_para_miktarı = -1;
                                int satıcı_yemek_miktarı = -1;
                                int satıcı_eşya_miktarı = -1;
                                if (rs2.next()) {
                                    satıcı_para_miktarı = rs2.getInt("kullanıcı_para_miktarı");
                                    satıcı_yemek_miktarı = rs2.getInt("kullanıcı_yemek_miktarı");
                                    satıcı_eşya_miktarı = rs2.getInt("kullanıcı_eşya_miktarı");
                                }


                                PreparedStatement preparedStatement = bag.prepareStatement("SELECT * FROM market WHERE market_no = ?");

                                preparedStatement.setInt(1, finalSay);
                                ResultSet resSet = preparedStatement.executeQuery();

                                int yiyecek_ücreti = 0;
                                if (resSet.next()) {
                                    yiyecek_ücreti = resSet.getInt("market_yiyecek_ücreti");

                                }


                                PreparedStatement kontrol = bag.prepareStatement("SELECT * FROM kullanıcı WHERE kullanıcı_no = ?");
                                kontrol.setInt(1, kullanıcı_no);
                                ResultSet kontrol_sonuc = kontrol.executeQuery();

                                int para_miktarı = 0;
                                int yemek_miktarı = 0;
                                int eşya_miktarı = 0;
                                if (kontrol_sonuc.next()) {
                                    para_miktarı = kontrol_sonuc.getInt("kullanıcı_para_miktarı");
                                    yemek_miktarı = kontrol_sonuc.getInt("kullanıcı_yemek_miktarı");
                                    eşya_miktarı = kontrol_sonuc.getInt("kullanıcı_eşya_miktarı");
                                }
                                if (para_miktarı - yiyecek_ücreti > 0) {
                                    PreparedStatement güncelle = bag.prepareStatement("UPDATE kullanıcı SET kullanıcı_para_miktarı = ?, kullanıcı_yemek_miktarı = ? WHERE kullanıcı_no = ?");
                                    güncelle.setInt(1, para_miktarı - yiyecek_ücreti);
                                    güncelle.setInt(2, yemek_miktarı + 1);
                                    güncelle.setInt(3, kullanıcı_no);
                                    güncelle.executeUpdate();


                                    PreparedStatement güncelle1 = bag.prepareStatement("UPDATE kullanıcı SET kullanıcı_para_miktarı = ? WHERE kullanıcı_no = ?");
                                    güncelle1.setInt(1, satıcı_para_miktarı + yiyecek_ücreti);

                                    güncelle1.setInt(2, kullanıcı_no1);
                                    güncelle1.executeUpdate();


                                    String bilgi1 = "\nyiyecek alındı: " + yiyecek_ücreti + " tl" + ",  kalan para: " + (para_miktarı - yiyecek_ücreti) + ",  güncel yiyecek miktarı: " + (yemek_miktarı + 1) + ",  eşya miktarı:  " + eşya_miktarı;
                                    bilgi_ekranı(alan1, alan2, alan3, alan4, alan5, alan6, alan7, bilgi1, kullanıcı_no);
                                    String bilgi2 = "\nyiyecek satıldı: " + yiyecek_ücreti + " tl" + ",  toplam para: " + (satıcı_para_miktarı + yiyecek_ücreti) + ",  yiyecek miktarı: " + satıcı_yemek_miktarı + ",  eşya miktarı:  " + satıcı_eşya_miktarı;
                                    bilgi_ekranı(alan1, alan2, alan3, alan4, alan5, alan6, alan7, bilgi2, kullanıcı_no1);


                                    kontrol.close();
                                    kontrol_sonuc.close();
                                    güncelle.close();
                                    resSet.close();
                                    preparedStatement.close();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Yeterli para yok", "uyarı", JOptionPane.WARNING_MESSAGE);
                                }

                            } catch (Exception exp) {

                                exp.printStackTrace();
                            }


                        }
                    });


                } else if (işletme_türü.equals("mağaza")) {
                    satın_al.setText("eşya satın al");


                    PreparedStatement preparedStatement = bag.prepareStatement("SELECT * FROM mağaza WHERE mağaza_no = ?");
                    preparedStatement.setInt(1, finalSay);
                    ResultSet resSet = preparedStatement.executeQuery();

                    if (resSet.next()) {

                        etiket1.setText("eşya_ücreti : " + resSet.getInt("mağaza_eşya_ücreti"));
                    }

                    preparedStatement.close();
                    resSet.close();


                    satın_al.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {


                            try {

                                PreparedStatement pre1 = bag.prepareStatement("SELECT * FROM alan WHERE alan_no = ?");

                                pre1.setInt(1, finalSay);
                                ResultSet rs1 = pre1.executeQuery();
                                int kullanıcı_no1 = 0;

                                if (rs1.next()) {
                                    kullanıcı_no1 = rs1.getInt("alan_sahibi");
                                }


                                PreparedStatement pre2 = bag.prepareStatement("SELECT * FROM kullanıcı WHERE kullanıcı_no = ?");
                                pre2.setInt(1, kullanıcı_no1);

                                ResultSet rs2 = pre2.executeQuery();
                                int satıcı_para_miktarı = -1;
                                int satıcı_eşya_miktarı = -1;
                                int satıcı_yemek_miktarı = -1;
                                if (rs2.next()) {
                                    satıcı_para_miktarı = rs2.getInt("kullanıcı_para_miktarı");
                                    satıcı_eşya_miktarı = rs2.getInt("kullanıcı_eşya_miktarı");
                                    satıcı_yemek_miktarı = rs2.getInt("kullanıcı_yemek_miktarı");
                                }


                                PreparedStatement preparedStatement = bag.prepareStatement("SELECT * FROM mağaza WHERE mağaza_no = ?");

                                preparedStatement.setInt(1, finalSay);
                                ResultSet resSet = preparedStatement.executeQuery();

                                int eşya_ücreti = 0;

                                if (resSet.next()) {
                                    eşya_ücreti = resSet.getInt("mağaza_eşya_ücreti");

                                }


                                PreparedStatement kontrol = bag.prepareStatement("SELECT * FROM kullanıcı WHERE kullanıcı_no = ?");
                                kontrol.setInt(1, kullanıcı_no);
                                ResultSet kontrol_sonuc = kontrol.executeQuery();


                                int para_miktarı = 0;
                                int eşya_miktarı = 0;
                                int yemek_miktarı = 0;
                                if (kontrol_sonuc.next()) {
                                    para_miktarı = kontrol_sonuc.getInt("kullanıcı_para_miktarı");
                                    eşya_miktarı = kontrol_sonuc.getInt("kullanıcı_eşya_miktarı");
                                    yemek_miktarı = kontrol_sonuc.getInt("kullanıcı_yemek_miktarı");

                                }
                                if (para_miktarı - eşya_ücreti > 0) {
                                    PreparedStatement güncelle = bag.prepareStatement("UPDATE kullanıcı SET kullanıcı_para_miktarı = ?, kullanıcı_eşya_miktarı = ? WHERE kullanıcı_no =?");
                                    güncelle.setInt(1, para_miktarı - eşya_ücreti);
                                    güncelle.setInt(2, eşya_miktarı + 1);
                                    güncelle.setInt(3, kullanıcı_no);
                                    güncelle.executeUpdate();

                                    PreparedStatement güncelle1 = bag.prepareStatement("UPDATE kullanıcı SET kullanıcı_para_miktarı = ? WHERE kullanıcı_no = ?");
                                    güncelle1.setInt(1, satıcı_para_miktarı + eşya_ücreti);

                                    güncelle1.setInt(2, kullanıcı_no1);
                                    güncelle1.executeUpdate();


                                    String bilgi1 = "\neşya alındı: " + eşya_ücreti + " tl" + ",  kalan para: " + (para_miktarı - eşya_ücreti) + ",  yiyecek miktarı: " + yemek_miktarı + ",  güncel eşya miktarı:  " + (eşya_miktarı + 1);
                                    bilgi_ekranı(alan1, alan2, alan3, alan4, alan5, alan6, alan7, bilgi1, kullanıcı_no);
                                    String bilgi2 = "\neşya satıldı: " + eşya_ücreti + " tl" + ",  toplam para: " + (satıcı_para_miktarı + eşya_ücreti) + ",  yiyecek miktarı: " + satıcı_yemek_miktarı + ",  güncel eşya miktarı:  " + satıcı_eşya_miktarı;
                                    bilgi_ekranı(alan1, alan2, alan3, alan4, alan5, alan6, alan7, bilgi2, kullanıcı_no1);


                                    kontrol.close();
                                    kontrol_sonuc.close();
                                    güncelle.close();
                                    resSet.close();
                                    preparedStatement.close();
                                }


                            } catch (Exception exp) {

                                exp.printStackTrace();
                            }


                        }
                    });


                } else if (işletme_türü.equals("emlak")) {
                    satın_al.setVisible(false); // emlak kısmı için 3 buton olucak. Bu buton market ve mağaza da var.


                    arsa_satın_al.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            alışveriş_frame.setVisible(false);


                            JFrame arsa_satın_al = new JFrame();
                            arsa_satın_al.setBounds(900, 200, 500, 250);

                            JLabel lbl1 = new JLabel();
                            JLabel lbl2 = new JLabel();
                            lbl1.setBounds(0, 0, 450, 30);
                            lbl2.setBounds(0, 40, 450, 30);
                            lbl1.setText("satın alabileceğiniz arsaların numaraları: ");
                            JTextField txt = new JTextField();
                            txt.setBounds(0, 90, 150, 30);

                            JButton btn = new JButton("satın al");
                            btn.setBounds(0, 140, 100, 30);

                            for (int i = 0; i < satılık_arsalar.size(); i++) {

                                if (i == 0) {
                                    String cümle = lbl2.getText() + "   " + satılık_arsalar.get(i);
                                    lbl2.setText(cümle);
                                } else {
                                    String cümle = lbl2.getText() + ",   " + satılık_arsalar.get(i);
                                    lbl2.setText(cümle);
                                }
                            }

                            btn.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {


                                    int alan_no = Integer.parseInt(txt.getText());
                                    int arsa_fiyatı = 0;
                                    int alan_sahibi_no = 0;

                                    try {
                                        PreparedStatement preparedStatement = bag.prepareStatement("SELECT * from alan WHERE alan_no = ?");
                                        preparedStatement.setInt(1, alan_no);

                                        ResultSet sonuc = preparedStatement.executeQuery();

                                        if (sonuc.next()) {
                                            arsa_fiyatı = sonuc.getInt("arsa_fiyatı");
                                            alan_sahibi_no = sonuc.getInt("alan_sahibi");
                                            System.out.println(alan_sahibi_no);
                                        }

                                        int alan_sahibi_sayac = 0;
                                        try {
                                            PreparedStatement alan_kontrol_sorusu = bag.prepareStatement("SELECT * FROM alan");
                                            ResultSet alan_kontrolRs = alan_kontrol_sorusu.executeQuery();
                                            while (alan_kontrolRs.next()) {

                                                if (alan_kontrolRs.getInt("alan_sahibi") == kullanıcı_no && alan_kontrolRs.getString("alan_türü").equals("arsa")) {
                                                    alan_sahibi_sayac++;
                                                }
                                            }

                                            alan_kontrol_sorusu.close();
                                            alan_kontrolRs.close();

                                        } catch (Exception exp) {
                                            exp.printStackTrace();
                                        }

                                        if (alan_sahibi_sayac < 2) {

                                            PreparedStatement emlak_komisyon_bul = bag.prepareStatement("SELECT * FROM emlak WHERE emlak_no = ?");
                                            emlak_komisyon_bul.setInt(1, finalSay);
                                            ResultSet emlak_komisyon_bulucu = emlak_komisyon_bul.executeQuery();
                                            int emlak_komisyonu = 0;

                                            if (emlak_komisyon_bulucu.next()) {
                                                emlak_komisyonu = emlak_komisyon_bulucu.getInt("emlak_komisyonu");

                                                emlak_komisyon_bulucu.close();
                                                emlak_komisyon_bul.close();

                                            }

                                            alan_sahibi_sayac = 0;
                                            int cevap = JOptionPane.showConfirmDialog(null, "arsa fiyatı: " + arsa_fiyatı + "  Emlak komisyonu: " + emlak_komisyonu + "\ntoplam " + (arsa_fiyatı + emlak_komisyonu), "satış", JOptionPane.YES_NO_CANCEL_OPTION);

                                            if (cevap == 0) {
                                                PreparedStatement p = bag.prepareStatement("SELECT * from kullanıcı WHERE kullanıcı_no = ?");
                                                p.setInt(1, kullanıcı_no);
                                                ResultSet rs1 = p.executeQuery();


                                                if (rs1.next()) {
                                                    if (rs1.getInt("kullanıcı_para_miktarı") >= arsa_fiyatı + emlak_komisyonu) {
                                                        PreparedStatement p1 = bag.prepareStatement("UPDATE alan SET alan_sahibi = ?,arsa_fiyatı = ? WHERE alan_no = ?");
                                                        p1.setInt(1, kullanıcı_no);
                                                        p1.setInt(2, -1);
                                                        p1.setInt(3, alan_no);

                                                        p1.executeUpdate();

                                                        p1.close();


                                                        PreparedStatement p2 = bag.prepareStatement("INSERT INTO satış VALUES(?,?,?,?,?)");


                                                        dateTimeNow.setTime(dateTimeNow.getTime() + 1000);

                                                        p2.setInt(1, alan_no);
                                                        p2.setInt(2, finalSay);
                                                        p2.setTimestamp(3, dateTimeNow);
                                                        p2.setInt(4, -1);
                                                        p2.setInt(5, arsa_fiyatı);
                                                        p2.executeUpdate();
                                                        p2.close();

                                                        int kullanıcı_para_miktarı_güncel = rs1.getInt("kullanıcı_para_miktarı") - arsa_fiyatı - emlak_komisyonu;
                                                        PreparedStatement p3 = bag.prepareStatement("UPDATE kullanıcı SET kullanıcı_para_miktarı = ? WHERE kullanıcı_no = ?");

                                                        p3.setInt(1, kullanıcı_para_miktarı_güncel);
                                                        p3.setInt(2, kullanıcı_no);

                                                        p3.executeUpdate();
                                                        p3.close();

                                                        String bilgi1 = "\n" + alan_no + " numaralı " + "arsa satın alındı, arsa fiyatı: " + arsa_fiyatı + " tl" + ",  emlak komisyonu: " + emlak_komisyonu + " tl" + ",  toplam: " + (emlak_komisyonu + arsa_fiyatı) + " tl" + ",  kalan para miktarı: " + kullanıcı_para_miktarı_güncel + " tl";
                                                        bilgi_ekranı(alan1, alan2, alan3, alan4, alan5, alan6, alan7, bilgi1, kullanıcı_no);

                                                        if (alan_sahibi_no != 0) {
                                                            System.out.println(alan_sahibi_no);
                                                            PreparedStatement p4 = bag.prepareStatement("SELECT * from kullanıcı WHERE kullanıcı_no = ?");
                                                            p4.setInt(1, alan_sahibi_no);
                                                            ResultSet rs2 = p4.executeQuery();

                                                            if (rs2.next()) {
                                                                int kullanıcı_para_mik = rs2.getInt("kullanıcı_para_miktarı");
                                                                rs2.close();
                                                                p.close();

                                                                PreparedStatement p5 = bag.prepareStatement("UPDATE kullanıcı SET kullanıcı_para_miktarı = ? WHERE kullanıcı_no = ?");
                                                                p5.setInt(1, kullanıcı_para_mik + arsa_fiyatı - emlak_komisyonu);
                                                                p5.setInt(2, alan_sahibi_no);
                                                                int sonuc4 = p5.executeUpdate();
                                                                String bilgi2 = "\n" + alan_no + " numaralı " + "arsan satıldı , arsa fiyatı: " + arsa_fiyatı + " tl" + ",  emlak komisyonu: " + emlak_komisyonu + " tl" + ",  toplam: arsa fiyatı - emlak komisyonu" + (arsa_fiyatı - emlak_komisyonu) + " tl" + ",  güncel para miktarı: " + (kullanıcı_para_mik + arsa_fiyatı - emlak_komisyonu) + " tl";
                                                                bilgi_ekranı(alan1, alan2, alan3, alan4, alan5, alan6, alan7, bilgi2, alan_sahibi_no);
                                                                p5.close();


                                                            }


                                                        }


                                                        try { // emlakççının komisyonunu ekliyoruz

                                                            PreparedStatement p6 = bag.prepareStatement("SELECT * FROM alan WHERE alan_no = ?");
                                                            p6.setInt(1, finalSay);
                                                            ResultSet rs3 = p6.executeQuery();

                                                            int alan_sahibi = 0;
                                                            if (rs3.next()) {
                                                                alan_sahibi = rs3.getInt("alan_sahibi");

                                                            }
                                                            p6.close();
                                                            rs3.close();

                                                            if (alan_sahibi != 0) {
                                                                PreparedStatement p7 = bag.prepareStatement("SELECT * FROM kullanıcı WHERE kullanıcı_no = ?");
                                                                p7.setInt(1, alan_sahibi);
                                                                ResultSet rs4 = p7.executeQuery();
                                                                int para_miktarı = 0;
                                                                if (rs4.next()) {
                                                                    para_miktarı = rs4.getInt("kullanıcı_para_miktarı");
                                                                }


                                                                p7.close();
                                                                rs4.close();

                                                                PreparedStatement p8 = bag.prepareStatement("UPDATE kullanıcı SET kullanıcı_para_miktarı = ? WHERE kullanıcı_no = ?");
                                                                p8.setInt(1, para_miktarı + emlak_komisyonu * 2);
                                                                p8.setInt(2, alan_sahibi);
                                                                p8.executeUpdate();

                                                                String bilgi3 = "\n" + alan_no + " numaralı arsanın satışı için emlakçına başvuruldu. " + "emlak komisyonu: " + emlak_komisyonu + " tl" + ", satıcı ve alıcıdan alınan toplam emlak komisyonu: " + emlak_komisyonu * 2 + " tl" + "\ntoplam para miktarı: " + (para_miktarı + emlak_komisyonu * 2);
                                                                bilgi_ekranı(alan1, alan2, alan3, alan4, alan5, alan6, alan7, bilgi3, alan_sahibi);

                                                                p8.close();


                                                            }


                                                        } catch (Exception exp) {
                                                            exp.printStackTrace();
                                                        }


                                                        for (int i = 0; i < satılık_arsalar.size(); i++) {


                                                            if (alan_no == (int) satılık_arsalar.get(i)) {
                                                                satılık_arsalar.remove(i);
                                                            }


                                                        }

                                                    } else {
                                                        JOptionPane.showMessageDialog(null, "yeterli para yok", "Uyarı", JOptionPane.WARNING_MESSAGE);
                                                    }


                                                }


                                                p.close();
                                                rs1.close();


                                            }

                                            preparedStatement.close();
                                            sonuc.close();

                                        } else {
                                            JOptionPane.showMessageDialog(null, "en fazla 2 boş arsanız olabilir", "Uyarı", JOptionPane.WARNING_MESSAGE);
                                        }

                                    } catch (Exception exp) {
                                        exp.printStackTrace();
                                    }

                                    arsa_satın_al.setVisible(false);

                                }
                            });


                            arsa_satın_al.add(lbl1);
                            arsa_satın_al.add(lbl2);
                            arsa_satın_al.add(txt);
                            arsa_satın_al.add(btn);

                            arsa_satın_al.setLayout(null);
                            arsa_satın_al.setVisible(true);


                        }
                    });


                    işletme_kur.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            alışveriş_frame.setVisible(false);

                            int para_miktar = 0;
                            try {
                                PreparedStatement p0 = bag.prepareStatement("SELECT * FROM kullanıcı WHERE kullanıcı_no = ?");
                                p0.setInt(1, kullanıcı_no);
                                ResultSet rs0 = p0.executeQuery();


                                if (rs0.next()) {
                                    para_miktar = rs0.getInt("kullanıcı_para_miktarı");

                                    if (para_miktar - işletme_inşa_ücreti < 0) {
                                        JOptionPane.showMessageDialog(null, "yeterli para yok", "uyarı", JOptionPane.WARNING_MESSAGE);
                                    }

                                }

                            } catch (Exception exp) {
                                exp.printStackTrace();
                            }

                            int arsa1 = 0;
                            int arsa2 = 0;
                            try {


                                PreparedStatement p1 = bag.prepareStatement("SELECT * FROM alan WHERE alan_sahibi = ?");
                                p1.setInt(1, kullanıcı_no);
                                ResultSet rs1 = p1.executeQuery();

                                while (rs1.next()) {
                                    arsa1 = rs1.getInt("alan_no");
                                    String alan_türü1 = rs1.getString("alan_türü");

                                    if (alan_türü1.equals("işletme")) {
                                        arsa1 = 0;
                                    }

                                    if (arsa1 != 0) {
                                        break;
                                    }


                                }

                                while (rs1.next()) {
                                    arsa2 = rs1.getInt("alan_no");
                                    String alan_türü2 = rs1.getString("alan_türü");

                                    if (alan_türü2.equals("işletme")) {
                                        arsa2 = 0;
                                    }

                                    if (arsa2 != 0) {
                                        break;
                                    }


                                }


                                if (arsa1 == 0 && arsa2 == 0) {

                                    JOptionPane.showMessageDialog(null, "boş arsanız bulunmamaktadır", "uyarı", JOptionPane.WARNING_MESSAGE);

                                }


                            } catch (Exception exp) {
                                exp.printStackTrace();
                            }

                            int yes = -1;
                            if (arsa1 != 0 || arsa2 != 0) {
                                yes = JOptionPane.showConfirmDialog(null, "işletme inşa ücreti: " + işletme_inşa_ücreti, "soru" + işletme_inşa_ücreti, JOptionPane.YES_NO_OPTION);

                            }


                            if ((arsa1 != 0 || arsa2 != 0) && para_miktar - işletme_inşa_ücreti > 0 && yes == 0) {
                                JFrame frame = new JFrame("işletme kur");
                                JPanel panel = new JPanel();


                                JButton btn1 = new JButton("market kur");
                                JButton btn2 = new JButton("mağaza kur");
                                JButton btn3 = new JButton("emlak kur");

                                btn1.setBounds(0, 0, 100, 30);
                                btn2.setBounds(0, 50, 100, 30);
                                btn3.setBounds(0, 100, 100, 30);

                                int finalArsa1 = arsa1;
                                int finalArsa2 = arsa2;
                                btn1.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {


                                        if (finalArsa1 != 0 && finalArsa2 != 0) {
                                            panel.setVisible(false);

                                            JButton kur1 = new JButton(finalArsa1 + " numaralı arsana kur");
                                            JButton kur2 = new JButton(finalArsa2 + " numaralı arsana kur");

                                            kur1.setBounds(10, 10, 200, 30);
                                            kur2.setBounds(10, 50, 200, 30);

                                            kur1.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    frame.setVisible(false);


                                                    String market_yiyecek_ücreti = JOptionPane.showInputDialog("market yiyecek ücretini belirleyin:");
                                                    JOptionPane.showMessageDialog(null, "market yiyecek ücreti , " + market_yiyecek_ücreti + " olarak ayarlandı");

                                                    try {
                                                        PreparedStatement p2 = bag.prepareStatement("INSERT INTO işletme VALUES(?,?,?,?,?,?,?)");
                                                        p2.setInt(1, finalArsa1);
                                                        p2.setString(2, "market");
                                                        p2.setInt(3, 1);
                                                        p2.setInt(4, 3);
                                                        p2.setInt(5, 0);
                                                        p2.setTimestamp(6, dateTimeNow);
                                                        p2.setInt(7, 0);
                                                        p2.executeUpdate();


                                                        PreparedStatement p3 = bag.prepareStatement("UPDATE  alan SET alan_türü = ? WHERE alan_no = ?");
                                                        p3.setString(1, "işletme");
                                                        p3.setInt(2, finalArsa1);
                                                        p3.executeUpdate();


                                                        PreparedStatement p4 = bag.prepareStatement("SELECT * FROM kullanıcı WHERE kullanıcı_no = ?");
                                                        p4.setInt(1, kullanıcı_no);
                                                        ResultSet rs3 = p4.executeQuery();


                                                        int para_miktarı = 0;

                                                        if (rs3.next()) {
                                                            para_miktarı = rs3.getInt("kullanıcı_para_miktarı");

                                                        }

                                                        PreparedStatement p5 = bag.prepareStatement("UPDATE kullanıcı SET kullanıcı_para_miktarı = ? WHERE kullanıcı_no = ?");
                                                        p5.setInt(1, para_miktarı - işletme_inşa_ücreti);
                                                        p5.setInt(2, kullanıcı_no);
                                                        p5.executeUpdate();


                                                        PreparedStatement p6 = bag.prepareStatement("INSERT INTO market VALUES (?,?)");

                                                        p6.setInt(1, finalArsa1);
                                                        p6.setInt(2, Integer.parseInt(market_yiyecek_ücreti));

                                                        p6.executeUpdate();

                                                        String bilgi = "\n" + finalArsa1 + " numaralı arsanıza market kurdunuz." + " inşa ücreti: " + işletme_inşa_ücreti + " tl" + ", kalan para: " + (para_miktarı - işletme_inşa_ücreti);
                                                        bilgi_ekranı(alan1, alan2, alan3, alan4, alan5, alan6, alan7, bilgi, kullanıcı_no);

                                                    } catch (Exception exp) {
                                                        exp.printStackTrace();
                                                    }

                                                    int i = (finalArsa1 - 1) / sutun;
                                                    int j = (finalArsa1 - 1) % sutun;

                                                    market_no.add(finalArsa1);

                                                    Isletme_Resim(no[i][j], "market");

                                                    if (flag1 == 1) {
                                                        Isletme_Resim(no1[i][j], "market");
                                                    }

                                                    if (flag2 == 1) {
                                                        Isletme_Resim(no2[i][j], "market");
                                                    }

                                                    if (flag3 == 1) {
                                                        Isletme_Resim(no3[i][j], "market");
                                                    }

                                                    if (flag4 == 1) {
                                                        Isletme_Resim(no4[i][j], "market");
                                                    }

                                                    if (flag5 == 1) {
                                                        Isletme_Resim(no5[i][j], "market");
                                                    }

                                                    if (flag6 == 1) {
                                                        Isletme_Resim(no6[i][j], "market");
                                                    }

                                                    if (flag7 == 1) {
                                                        Isletme_Resim(no7[i][j], "market");
                                                    }


                                                }
                                            });


                                            kur2.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    frame.setVisible(false);


                                                    String market_yiyecek_ücreti = JOptionPane.showInputDialog("market yiyecek ücretini belirleyin:");
                                                    JOptionPane.showMessageDialog(null, "market yiyecek ücreti , " + market_yiyecek_ücreti + " olarak ayarlandı");

                                                    try {
                                                        PreparedStatement p2 = bag.prepareStatement("INSERT INTO işletme VALUES(?,?,?,?,?,?,?)");
                                                        p2.setInt(1, finalArsa2);
                                                        p2.setString(2, "market");
                                                        p2.setInt(3, 1);
                                                        p2.setInt(4, 3);
                                                        p2.setInt(5, 0);
                                                        p2.setTimestamp(6, dateTimeNow);
                                                        p2.setInt(7, 0);
                                                        p2.executeUpdate();


                                                        PreparedStatement p3 = bag.prepareStatement("UPDATE  alan SET alan_türü = ? WHERE alan_no = ?");
                                                        p3.setString(1, "işletme");
                                                        p3.setInt(2, finalArsa2);
                                                        p3.executeUpdate();


                                                        PreparedStatement p4 = bag.prepareStatement("SELECT * FROM kullanıcı WHERE kullanıcı_no = ?");
                                                        p4.setInt(1, kullanıcı_no);
                                                        ResultSet rs3 = p4.executeQuery();


                                                        int para_miktarı = 0;

                                                        if (rs3.next()) {
                                                            para_miktarı = rs3.getInt("kullanıcı_para_miktarı");

                                                        }

                                                        PreparedStatement p5 = bag.prepareStatement("UPDATE kullanıcı SET kullanıcı_para_miktarı = ? WHERE kullanıcı_no = ?");
                                                        p5.setInt(1, para_miktarı - işletme_inşa_ücreti);
                                                        p5.setInt(2, kullanıcı_no);
                                                        p5.executeUpdate();


                                                        PreparedStatement p6 = bag.prepareStatement("INSERT INTO market VALUES (?,?)");

                                                        p6.setInt(1, finalArsa2);
                                                        p6.setInt(2, Integer.parseInt(market_yiyecek_ücreti));

                                                        p6.executeUpdate();

                                                        String bilgi = "\n" + finalArsa2 + " numaralı arsanıza market kurdunuz." + " inşa ücreti: " + işletme_inşa_ücreti + " tl" + ", kalan para: " + (para_miktarı - işletme_inşa_ücreti);
                                                        bilgi_ekranı(alan1, alan2, alan3, alan4, alan5, alan6, alan7, bilgi, kullanıcı_no);


                                                    } catch (Exception exp) {
                                                        exp.printStackTrace();
                                                    }

                                                    int i = (finalArsa2 - 1) / sutun;
                                                    int j = (finalArsa2 - 1) % sutun;
                                                    market_no.add(finalArsa2);
                                                    Isletme_Resim(no[i][j], "market");


                                                    if (flag1 == 1) {
                                                        Isletme_Resim(no1[i][j], "market");
                                                    }

                                                    if (flag2 == 1) {
                                                        Isletme_Resim(no2[i][j], "market");
                                                    }

                                                    if (flag3 == 1) {
                                                        Isletme_Resim(no3[i][j], "market");
                                                    }

                                                    if (flag4 == 1) {
                                                        Isletme_Resim(no4[i][j], "market");
                                                    }

                                                    if (flag5 == 1) {
                                                        Isletme_Resim(no5[i][j], "market");
                                                    }

                                                    if (flag6 == 1) {
                                                        Isletme_Resim(no6[i][j], "market");
                                                    }

                                                    if (flag7 == 1) {
                                                        Isletme_Resim(no7[i][j], "market");
                                                    }


                                                }
                                            });

                                            frame.add(kur1);
                                            frame.add(kur2);


                                        } else if (finalArsa1 != 0) {
                                            panel.setVisible(false);
                                            JButton kur1 = new JButton(finalArsa1 + " numaralı arsana kur");
                                            kur1.setBounds(10, 10, 200, 30);

                                            kur1.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {

                                                    frame.setVisible(false);

                                                    String market_yiyecek_ücreti = JOptionPane.showInputDialog("market yiyecek ücretini belirleyin:");
                                                    JOptionPane.showMessageDialog(null, "market yiyecek ücreti , " + market_yiyecek_ücreti + " olarak ayarlandı");

                                                    try {
                                                        PreparedStatement p2 = bag.prepareStatement("INSERT INTO işletme VALUES(?,?,?,?,?,?,?)");
                                                        p2.setInt(1, finalArsa1);
                                                        p2.setString(2, "market");
                                                        p2.setInt(3, 1);
                                                        p2.setInt(4, 3);
                                                        p2.setInt(5, 0);
                                                        p2.setTimestamp(6, dateTimeNow);
                                                        p2.setInt(7, 0);
                                                        p2.executeUpdate();


                                                        PreparedStatement p3 = bag.prepareStatement("UPDATE  alan SET alan_türü = ? WHERE alan_no = ?");
                                                        p3.setString(1, "işletme");
                                                        p3.setInt(2, finalArsa1);
                                                        p3.executeUpdate();


                                                        PreparedStatement p4 = bag.prepareStatement("SELECT * FROM kullanıcı WHERE kullanıcı_no = ?");
                                                        p4.setInt(1, kullanıcı_no);
                                                        ResultSet rs3 = p4.executeQuery();


                                                        int para_miktarı = 0;

                                                        if (rs3.next()) {
                                                            para_miktarı = rs3.getInt("kullanıcı_para_miktarı");

                                                        }

                                                        PreparedStatement p5 = bag.prepareStatement("UPDATE kullanıcı SET kullanıcı_para_miktarı = ? WHERE kullanıcı_no = ?");
                                                        p5.setInt(1, para_miktarı - işletme_inşa_ücreti);
                                                        p5.setInt(2, kullanıcı_no);
                                                        p5.executeUpdate();


                                                        PreparedStatement p6 = bag.prepareStatement("INSERT INTO market VALUES (?,?)");

                                                        p6.setInt(1, finalArsa1);
                                                        p6.setInt(2, Integer.parseInt(market_yiyecek_ücreti));

                                                        p6.executeUpdate();

                                                        String bilgi = "\n" + finalArsa1 + " numaralı arsanıza market kurdunuz." + " inşa ücreti: " + işletme_inşa_ücreti + " tl" + ", kalan para: " + (para_miktarı - işletme_inşa_ücreti);
                                                        bilgi_ekranı(alan1, alan2, alan3, alan4, alan5, alan6, alan7, bilgi, kullanıcı_no);


                                                    } catch (Exception exp) {
                                                        exp.printStackTrace();
                                                    }


                                                    int i = (finalArsa1 - 1) / sutun;
                                                    int j = (finalArsa1 - 1) % sutun;
                                                    Isletme_Resim(no[i][j], "market");
                                                    market_no.add(finalArsa1);

                                                    if (flag1 == 1) {
                                                        Isletme_Resim(no1[i][j], "market");
                                                    }

                                                    if (flag2 == 1) {
                                                        Isletme_Resim(no2[i][j], "market");
                                                    }

                                                    if (flag3 == 1) {
                                                        Isletme_Resim(no3[i][j], "market");
                                                    }

                                                    if (flag4 == 1) {
                                                        Isletme_Resim(no4[i][j], "market");
                                                    }

                                                    if (flag5 == 1) {
                                                        Isletme_Resim(no5[i][j], "market");
                                                    }

                                                    if (flag6 == 1) {
                                                        Isletme_Resim(no6[i][j], "market");
                                                    }

                                                    if (flag7 == 1) {
                                                        Isletme_Resim(no7[i][j], "market");
                                                    }


                                                }
                                            });

                                            frame.add(kur1);

                                        }


                                    }
                                });


                                btn2.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {


                                        if (finalArsa1 != 0 && finalArsa2 != 0) {
                                            panel.setVisible(false);

                                            JButton kur1 = new JButton(finalArsa1 + " numaralı arsana kur");
                                            JButton kur2 = new JButton(finalArsa2 + " numaralı arsana kur");

                                            kur1.setBounds(10, 10, 200, 30);
                                            kur2.setBounds(10, 50, 200, 30);

                                            kur1.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    frame.setVisible(false);

                                                    String mağaza_eşya_ücreti = JOptionPane.showInputDialog("mağaza eşya ücretini belirleyin:");
                                                    JOptionPane.showMessageDialog(null, "mağaza eşya ücreti , " + mağaza_eşya_ücreti + " olarak ayarlandı");

                                                    try {
                                                        PreparedStatement p2 = bag.prepareStatement("INSERT INTO işletme VALUES(?,?,?,?,?,?,?)");
                                                        p2.setInt(1, finalArsa1);
                                                        p2.setString(2, "mağaza");
                                                        p2.setInt(3, 1);
                                                        p2.setInt(4, 3);
                                                        p2.setInt(5, 0);
                                                        p2.setTimestamp(6, dateTimeNow);
                                                        p2.setInt(7, 0);
                                                        p2.executeUpdate();


                                                        PreparedStatement p3 = bag.prepareStatement("UPDATE  alan SET alan_türü = ? WHERE alan_no = ?");
                                                        p3.setString(1, "işletme");
                                                        p3.setInt(2, finalArsa1);
                                                        p3.executeUpdate();


                                                        PreparedStatement p4 = bag.prepareStatement("SELECT * FROM kullanıcı WHERE kullanıcı_no = ?");
                                                        p4.setInt(1, kullanıcı_no);
                                                        ResultSet rs3 = p4.executeQuery();


                                                        int para_miktarı = 0;

                                                        if (rs3.next()) {
                                                            para_miktarı = rs3.getInt("kullanıcı_para_miktarı");

                                                        }

                                                        PreparedStatement p5 = bag.prepareStatement("UPDATE kullanıcı SET kullanıcı_para_miktarı = ? WHERE kullanıcı_no = ?");
                                                        p5.setInt(1, para_miktarı - işletme_inşa_ücreti);
                                                        p5.setInt(2, kullanıcı_no);
                                                        p5.executeUpdate();


                                                        PreparedStatement p6 = bag.prepareStatement("INSERT INTO mağaza VALUES (?,?)");

                                                        p6.setInt(1, finalArsa1);
                                                        p6.setInt(2, Integer.parseInt(mağaza_eşya_ücreti));

                                                        p6.executeUpdate();


                                                        String bilgi = "\n" + finalArsa1 + " numaralı arsanıza mağaza kurdunuz." + " inşa ücreti: " + işletme_inşa_ücreti + " tl" + ", kalan para: " + (para_miktarı - işletme_inşa_ücreti);
                                                        bilgi_ekranı(alan1, alan2, alan3, alan4, alan5, alan6, alan7, bilgi, kullanıcı_no);

                                                    } catch (Exception exp) {
                                                        exp.printStackTrace();
                                                    }

                                                    int i = (finalArsa1 - 1) / sutun;
                                                    int j = (finalArsa1 - 1) % sutun;

                                                    Isletme_Resim(no[i][j], "magaza");
                                                    mağaza_no.add(finalArsa1);


                                                    if (flag1 == 1) {
                                                        Isletme_Resim(no1[i][j], "magaza");
                                                    }

                                                    if (flag2 == 1) {
                                                        Isletme_Resim(no2[i][j], "magaza");
                                                    }

                                                    if (flag3 == 1) {
                                                        Isletme_Resim(no3[i][j], "magaza");
                                                    }

                                                    if (flag4 == 1) {
                                                        Isletme_Resim(no4[i][j], "magaza");
                                                    }

                                                    if (flag5 == 1) {
                                                        Isletme_Resim(no5[i][j], "magaza");
                                                    }

                                                    if (flag6 == 1) {
                                                        Isletme_Resim(no6[i][j], "magaza");
                                                    }

                                                    if (flag7 == 1) {
                                                        Isletme_Resim(no7[i][j], "magaza");
                                                    }

                                                }
                                            });


                                            kur2.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    frame.setVisible(false);


                                                    String mağaza_eşya_ücreti = JOptionPane.showInputDialog("mağaza eşya ücretini belirleyin:");
                                                    JOptionPane.showMessageDialog(null, "mağaza eşya ücreti , " + mağaza_eşya_ücreti + " olarak ayarlandı");

                                                    try {
                                                        PreparedStatement p2 = bag.prepareStatement("INSERT INTO işletme VALUES(?,?,?,?,?,?,?)");
                                                        p2.setInt(1, finalArsa2);
                                                        p2.setString(2, "mağaza");
                                                        p2.setInt(3, 1);
                                                        p2.setInt(4, 3);
                                                        p2.setInt(5, 0);
                                                        p2.setTimestamp(6, dateTimeNow);
                                                        p2.setInt(7, 0);
                                                        p2.executeUpdate();


                                                        PreparedStatement p3 = bag.prepareStatement("UPDATE  alan SET alan_türü = ? WHERE alan_no = ?");
                                                        p3.setString(1, "işletme");
                                                        p3.setInt(2, finalArsa2);
                                                        p3.executeUpdate();


                                                        PreparedStatement p4 = bag.prepareStatement("SELECT * FROM kullanıcı WHERE kullanıcı_no = ?");
                                                        p4.setInt(1, kullanıcı_no);
                                                        ResultSet rs3 = p4.executeQuery();


                                                        int para_miktarı = 0;

                                                        if (rs3.next()) {
                                                            para_miktarı = rs3.getInt("kullanıcı_para_miktarı");

                                                        }

                                                        PreparedStatement p5 = bag.prepareStatement("UPDATE kullanıcı SET kullanıcı_para_miktarı = ? WHERE kullanıcı_no = ?");
                                                        p5.setInt(1, para_miktarı - işletme_inşa_ücreti);
                                                        p5.setInt(2, kullanıcı_no);
                                                        p5.executeUpdate();


                                                        PreparedStatement p6 = bag.prepareStatement("INSERT INTO mağaza VALUES (?,?)");

                                                        p6.setInt(1, finalArsa2);
                                                        p6.setInt(2, Integer.parseInt(mağaza_eşya_ücreti));

                                                        p6.executeUpdate();

                                                        String bilgi = "\n" + finalArsa2 + " numaralı arsanıza mağaza kurdunuz." + " inşa ücreti: " + işletme_inşa_ücreti + " tl" + ", kalan para: " + (para_miktarı - işletme_inşa_ücreti);
                                                        bilgi_ekranı(alan1, alan2, alan3, alan4, alan5, alan6, alan7, bilgi, kullanıcı_no);


                                                    } catch (Exception exp) {
                                                        exp.printStackTrace();
                                                    }
                                                    int i = (finalArsa2 - 1) / sutun;
                                                    int j = (finalArsa2 - 1) % sutun;

                                                    Isletme_Resim(no[i][j], "magaza");
                                                    mağaza_no.add(finalArsa2);

                                                    if (flag1 == 1) {
                                                        Isletme_Resim(no1[i][j], "magaza");
                                                    }

                                                    if (flag2 == 1) {
                                                        Isletme_Resim(no2[i][j], "magaza");
                                                    }

                                                    if (flag3 == 1) {
                                                        Isletme_Resim(no3[i][j], "magaza");
                                                    }

                                                    if (flag4 == 1) {
                                                        Isletme_Resim(no4[i][j], "magaza");
                                                    }

                                                    if (flag5 == 1) {
                                                        Isletme_Resim(no5[i][j], "magaza");
                                                    }

                                                    if (flag6 == 1) {
                                                        Isletme_Resim(no6[i][j], "magaza");
                                                    }

                                                    if (flag7 == 1) {
                                                        Isletme_Resim(no7[i][j], "magaza");
                                                    }


                                                }
                                            });

                                            frame.add(kur1);
                                            frame.add(kur2);


                                        } else if (finalArsa1 != 0) {
                                            panel.setVisible(false);
                                            JButton kur1 = new JButton(finalArsa1 + " numaralı arsana kur");
                                            kur1.setBounds(10, 10, 200, 30);

                                            kur1.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {

                                                    frame.setVisible(false);

                                                    String mağaza_eşya_ücreti = JOptionPane.showInputDialog("mağaza eşya ücretini belirleyin:");
                                                    JOptionPane.showMessageDialog(null, "mağaza eşya ücreti , " + mağaza_eşya_ücreti + " olarak ayarlandı");

                                                    try {
                                                        PreparedStatement p2 = bag.prepareStatement("INSERT INTO işletme VALUES(?,?,?,?,?,?,?)");
                                                        p2.setInt(1, finalArsa1);
                                                        p2.setString(2, "mağaza");
                                                        p2.setInt(3, 1);
                                                        p2.setInt(4, 3);
                                                        p2.setInt(5, 0);
                                                        p2.setTimestamp(6, dateTimeNow);
                                                        p2.setInt(7, 0);
                                                        p2.executeUpdate();


                                                        PreparedStatement p3 = bag.prepareStatement("UPDATE  alan SET alan_türü = ? WHERE alan_no = ?");
                                                        p3.setString(1, "işletme");
                                                        p3.setInt(2, finalArsa1);
                                                        p3.executeUpdate();


                                                        PreparedStatement p4 = bag.prepareStatement("SELECT * FROM kullanıcı WHERE kullanıcı_no = ?");
                                                        p4.setInt(1, kullanıcı_no);
                                                        ResultSet rs3 = p4.executeQuery();


                                                        int para_miktarı = 0;

                                                        if (rs3.next()) {
                                                            para_miktarı = rs3.getInt("kullanıcı_para_miktarı");

                                                        }

                                                        PreparedStatement p5 = bag.prepareStatement("UPDATE kullanıcı SET kullanıcı_para_miktarı = ? WHERE kullanıcı_no = ?");
                                                        p5.setInt(1, para_miktarı - işletme_inşa_ücreti);
                                                        p5.setInt(2, kullanıcı_no);
                                                        p5.executeUpdate();


                                                        PreparedStatement p6 = bag.prepareStatement("INSERT INTO mağaza VALUES (?,?)");

                                                        p6.setInt(1, finalArsa1);
                                                        p6.setInt(2, Integer.parseInt(mağaza_eşya_ücreti));

                                                        p6.executeUpdate();


                                                        String bilgi = "\n" + finalArsa1 + " numaralı arsanıza mağaza kurdunuz." + " inşa ücreti: " + işletme_inşa_ücreti + " tl" + ", kalan para: " + (para_miktarı - işletme_inşa_ücreti);
                                                        bilgi_ekranı(alan1, alan2, alan3, alan4, alan5, alan6, alan7, bilgi, kullanıcı_no);

                                                    } catch (Exception exp) {
                                                        exp.printStackTrace();
                                                    }

                                                    int i = (finalArsa1 - 1) / sutun;
                                                    int j = (finalArsa1 - 1) % sutun;

                                                    Isletme_Resim(no[i][j], "magaza");
                                                    mağaza_no.add(finalArsa1);

                                                    if (flag1 == 1) {
                                                        Isletme_Resim(no1[i][j], "magaza");
                                                    }

                                                    if (flag2 == 1) {
                                                        Isletme_Resim(no2[i][j], "magaza");
                                                    }

                                                    if (flag3 == 1) {
                                                        Isletme_Resim(no3[i][j], "magaza");
                                                    }

                                                    if (flag4 == 1) {
                                                        Isletme_Resim(no4[i][j], "magaza");
                                                    }

                                                    if (flag5 == 1) {
                                                        Isletme_Resim(no5[i][j], "magaza");
                                                    }

                                                    if (flag6 == 1) {
                                                        Isletme_Resim(no6[i][j], "magaza");
                                                    }

                                                    if (flag7 == 1) {
                                                        Isletme_Resim(no7[i][j], "magaza");
                                                    }

                                                }
                                            });

                                            frame.add(kur1);

                                        }


                                    }
                                });


                                btn3.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {


                                        if (finalArsa1 != 0 && finalArsa2 != 0) {
                                            panel.setVisible(false);

                                            JButton kur1 = new JButton(finalArsa1 + " numaralı arsana kur");
                                            JButton kur2 = new JButton(finalArsa2 + " numaralı arsana kur");

                                            kur1.setBounds(10, 10, 200, 30);
                                            kur2.setBounds(10, 50, 200, 30);

                                            kur1.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    frame.setVisible(false);

                                                    String emlak_komisyon_ücreti = JOptionPane.showInputDialog("emlak komisyonu belirleyin:");
                                                    JOptionPane.showMessageDialog(null, "emlak komisyonu , " + emlak_komisyon_ücreti + " olarak ayarlandı");

                                                    try {
                                                        PreparedStatement p2 = bag.prepareStatement("INSERT INTO işletme VALUES(?,?,?,?,?,?,?)");
                                                        p2.setInt(1, finalArsa1);
                                                        p2.setString(2, "emlakt");
                                                        p2.setInt(3, 1);
                                                        p2.setInt(4, 3);
                                                        p2.setInt(5, 0);
                                                        p2.setTimestamp(6, dateTimeNow);
                                                        p2.setInt(7, 0);
                                                        p2.executeUpdate();


                                                        PreparedStatement p3 = bag.prepareStatement("UPDATE  alan SET alan_türü = ? WHERE alan_no = ?");
                                                        p3.setString(1, "işletme");
                                                        p3.setInt(2, finalArsa1);
                                                        p3.executeUpdate();


                                                        PreparedStatement p4 = bag.prepareStatement("SELECT * FROM kullanıcı WHERE kullanıcı_no = ?");
                                                        p4.setInt(1, kullanıcı_no);
                                                        ResultSet rs3 = p4.executeQuery();


                                                        int para_miktarı = 0;

                                                        if (rs3.next()) {
                                                            para_miktarı = rs3.getInt("kullanıcı_para_miktarı");

                                                        }

                                                        PreparedStatement p5 = bag.prepareStatement("UPDATE kullanıcı SET kullanıcı_para_miktarı = ? WHERE kullanıcı_no = ?");
                                                        p5.setInt(1, para_miktarı - işletme_inşa_ücreti);
                                                        p5.setInt(2, kullanıcı_no);
                                                        p5.executeUpdate();


                                                        PreparedStatement p6 = bag.prepareStatement("INSERT INTO emlak VALUES (?,?)");

                                                        p6.setInt(1, finalArsa1);
                                                        p6.setInt(2, Integer.parseInt(emlak_komisyon_ücreti));

                                                        p6.executeUpdate();
                                                        String bilgi = "\n" + finalArsa1 + " numaralı arsanıza emlak kurdunuz." + " inşa ücreti: " + işletme_inşa_ücreti + " tl" + ", kalan para: " + (para_miktarı - işletme_inşa_ücreti);
                                                        bilgi_ekranı(alan1, alan2, alan3, alan4, alan5, alan6, alan7, bilgi, kullanıcı_no);


                                                    } catch (Exception exp) {
                                                        exp.printStackTrace();
                                                    }
                                                    int i = (finalArsa1 - 1) / sutun;
                                                    int j = (finalArsa1 - 1) % sutun;

                                                    Isletme_Resim(no[i][j], "emlak");
                                                    emlak_no.add(finalArsa1);

                                                    if (flag1 == 1) {
                                                        Isletme_Resim(no1[i][j], "emlak");
                                                    }

                                                    if (flag2 == 1) {
                                                        Isletme_Resim(no2[i][j], "emlak");
                                                    }

                                                    if (flag3 == 1) {
                                                        Isletme_Resim(no3[i][j], "emlak");
                                                    }

                                                    if (flag4 == 1) {
                                                        Isletme_Resim(no4[i][j], "emlak");
                                                    }

                                                    if (flag5 == 1) {
                                                        Isletme_Resim(no5[i][j], "emlak");
                                                    }

                                                    if (flag6 == 1) {
                                                        Isletme_Resim(no6[i][j], "emlak");
                                                    }

                                                    if (flag7 == 1) {
                                                        Isletme_Resim(no7[i][j], "emlak");
                                                    }

                                                }
                                            });


                                            kur2.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    frame.setVisible(false);

                                                    String emlak_komisyon_ücreti = JOptionPane.showInputDialog("emlak komisyonu belirleyin:");
                                                    JOptionPane.showMessageDialog(null, "emlak komisyonu , " + emlak_komisyon_ücreti + " olarak ayarlandı");

                                                    try {
                                                        PreparedStatement p2 = bag.prepareStatement("INSERT INTO işletme VALUES(?,?,?,?,?,?,?)");
                                                        p2.setInt(1, finalArsa2);
                                                        p2.setString(2, "emlak");
                                                        p2.setInt(3, 1);
                                                        p2.setInt(4, 3);
                                                        p2.setInt(5, 0);
                                                        p2.setTimestamp(6, dateTimeNow);
                                                        p2.setInt(7, 0);
                                                        p2.executeUpdate();


                                                        PreparedStatement p3 = bag.prepareStatement("UPDATE  alan SET alan_türü = ? WHERE alan_no = ?");
                                                        p3.setString(1, "işletme");
                                                        p3.setInt(2, finalArsa2);
                                                        p3.executeUpdate();


                                                        PreparedStatement p4 = bag.prepareStatement("SELECT * FROM kullanıcı WHERE kullanıcı_no = ?");
                                                        p4.setInt(1, kullanıcı_no);
                                                        ResultSet rs3 = p4.executeQuery();


                                                        int para_miktarı = 0;

                                                        if (rs3.next()) {
                                                            para_miktarı = rs3.getInt("kullanıcı_para_miktarı");

                                                        }

                                                        PreparedStatement p5 = bag.prepareStatement("UPDATE kullanıcı SET kullanıcı_para_miktarı = ? WHERE kullanıcı_no = ?");
                                                        p5.setInt(1, para_miktarı - işletme_inşa_ücreti);
                                                        p5.setInt(2, kullanıcı_no);
                                                        p5.executeUpdate();


                                                        PreparedStatement p6 = bag.prepareStatement("INSERT INTO emlak VALUES (?,?)");

                                                        p6.setInt(1, finalArsa2);
                                                        p6.setInt(2, Integer.parseInt(emlak_komisyon_ücreti));

                                                        p6.executeUpdate();

                                                        String bilgi = "\n" + finalArsa2 + " numaralı arsanıza emlak kurdunuz." + " inşa ücreti: " + işletme_inşa_ücreti + " tl" + ", kalan para: " + (para_miktarı - işletme_inşa_ücreti);
                                                        bilgi_ekranı(alan1, alan2, alan3, alan4, alan5, alan6, alan7, bilgi, kullanıcı_no);


                                                    } catch (Exception exp) {
                                                        exp.printStackTrace();
                                                    }


                                                    int i = (finalArsa2 - 1) / sutun;
                                                    int j = (finalArsa2 - 1) % sutun;

                                                    Isletme_Resim(no[i][j], "emlak");
                                                    emlak_no.add(finalArsa2);

                                                    if (flag1 == 1) {
                                                        Isletme_Resim(no1[i][j], "emlak");
                                                    }

                                                    if (flag2 == 1) {
                                                        Isletme_Resim(no2[i][j], "emlak");
                                                    }

                                                    if (flag3 == 1) {
                                                        Isletme_Resim(no3[i][j], "emlak");
                                                    }

                                                    if (flag4 == 1) {
                                                        Isletme_Resim(no4[i][j], "emlak");
                                                    }

                                                    if (flag5 == 1) {
                                                        Isletme_Resim(no5[i][j], "emlak");
                                                    }

                                                    if (flag6 == 1) {
                                                        Isletme_Resim(no6[i][j], "emlak");
                                                    }

                                                    if (flag7 == 1) {
                                                        Isletme_Resim(no7[i][j], "emlak");
                                                    }

                                                }
                                            });

                                            frame.add(kur1);
                                            frame.add(kur2);


                                        } else if (finalArsa1 != 0) {
                                            panel.setVisible(false);
                                            JButton kur1 = new JButton(finalArsa1 + " numaralı arsana kur");
                                            kur1.setBounds(10, 10, 200, 30);

                                            kur1.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {

                                                    frame.setVisible(false);

                                                    String emlak_komisyon_ücreti = JOptionPane.showInputDialog("emlak komisyonu belirleyin:");
                                                    JOptionPane.showMessageDialog(null, "emlak komisyonu , " + emlak_komisyon_ücreti + " olarak ayarlandı");

                                                    try {
                                                        PreparedStatement p2 = bag.prepareStatement("INSERT INTO işletme VALUES(?,?,?,?,?,?,?)");
                                                        p2.setInt(1, finalArsa1);
                                                        p2.setString(2, "emlak");
                                                        p2.setInt(3, 1);
                                                        p2.setInt(4, 3);
                                                        p2.setInt(5, 0);
                                                        p2.setTimestamp(6, dateTimeNow);
                                                        p2.setInt(7, 0);
                                                        p2.executeUpdate();


                                                        PreparedStatement p3 = bag.prepareStatement("UPDATE  alan SET alan_türü = ? WHERE alan_no = ?");
                                                        p3.setString(1, "işletme");
                                                        p3.setInt(2, finalArsa1);
                                                        p3.executeUpdate();


                                                        PreparedStatement p4 = bag.prepareStatement("SELECT * FROM kullanıcı WHERE kullanıcı_no = ?");
                                                        p4.setInt(1, kullanıcı_no);
                                                        ResultSet rs3 = p4.executeQuery();


                                                        int para_miktarı = 0;

                                                        if (rs3.next()) {
                                                            para_miktarı = rs3.getInt("kullanıcı_para_miktarı");

                                                        }

                                                        PreparedStatement p5 = bag.prepareStatement("UPDATE kullanıcı SET kullanıcı_para_miktarı = ? WHERE kullanıcı_no = ?");
                                                        p5.setInt(1, para_miktarı - işletme_inşa_ücreti);
                                                        p5.setInt(2, kullanıcı_no);
                                                        p5.executeUpdate();


                                                        PreparedStatement p6 = bag.prepareStatement("INSERT INTO emlak VALUES (?,?)");

                                                        p6.setInt(1, finalArsa1);
                                                        p6.setInt(2, Integer.parseInt(emlak_komisyon_ücreti));

                                                        p6.executeUpdate();

                                                        String bilgi = "\n" + finalArsa1 + " numaralı arsanıza emlak kurdunuz." + " inşa ücreti: " + işletme_inşa_ücreti + " tl" + ", kalan para: " + (para_miktarı - işletme_inşa_ücreti);
                                                        bilgi_ekranı(alan1, alan2, alan3, alan4, alan5, alan6, alan7, bilgi, kullanıcı_no);


                                                    } catch (Exception exp) {
                                                        exp.printStackTrace();
                                                    }

                                                    int i = (finalArsa1 - 1) / sutun;
                                                    int j = (finalArsa1 - 1) % sutun;

                                                    Isletme_Resim(no[i][j], "emlak");
                                                    emlak_no.add(finalArsa1);

                                                    if (flag1 == 1) {
                                                        Isletme_Resim(no1[i][j], "emlak");
                                                    }

                                                    if (flag2 == 1) {
                                                        Isletme_Resim(no2[i][j], "emlak");
                                                    }

                                                    if (flag3 == 1) {
                                                        Isletme_Resim(no3[i][j], "emlak");
                                                    }

                                                    if (flag4 == 1) {
                                                        Isletme_Resim(no4[i][j], "emlak");
                                                    }

                                                    if (flag5 == 1) {
                                                        Isletme_Resim(no5[i][j], "emlak");
                                                    }

                                                    if (flag6 == 1) {
                                                        Isletme_Resim(no6[i][j], "emlak");
                                                    }

                                                    if (flag7 == 1) {
                                                        Isletme_Resim(no7[i][j], "emlak");
                                                    }


                                                }
                                            });

                                            frame.add(kur1);

                                        }


                                    }
                                });


                                panel.add(btn1);
                                panel.add(btn2);
                                panel.add(btn3);

                                frame.setBounds(800, 300, 300, 300);
                                panel.setSize(300, 300);
                                panel.setVisible(true);
                                panel.setLayout(null);
                                frame.setLayout(null);
                                frame.setVisible(true);
                                frame.add(panel);
                            }


                        }
                    });


                    işletme_satın_al.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            alışveriş_frame.setVisible(false);


                            if (satılık_işletmeler.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "satılık işletme bulunmamaktadır", "uyarı", JOptionPane.WARNING_MESSAGE);
                            } else {
                                Frame frame = new JFrame();
                                frame.setBounds(900, 200, 500, 250);

                                JLabel label1 = new JLabel("satın alabileceğiniz işletmeler: ");
                                label1.setBounds(0, 0, 200, 30);

                                JLabel label2 = new JLabel();
                                label2.setBounds(0, 40, 500, 30);
                                String str = "";
                                for (int i = 0; i < satılık_işletmeler.size(); i++) {

                                    str = str + satılık_işletmeler.get(i) + "  ,";

                                }
                                label2.setText(str);

                                JTextField textField = new JTextField();
                                textField.setBounds(0, 100, 100, 30);
                                JButton btn = new JButton("Satın al");
                                btn.setBounds(0, 160, 100, 30);

                                btn.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {

                                        frame.setVisible(false);
                                        int işletme_no = Integer.parseInt(textField.getText());
                                        int işletme_fiyatı = -1;
                                        int işletme_sahibi = -1;
                                        int satıcı_para_miktarı = -1;
                                        int alıcı_para_miktarı = -1;
                                        int emlak_komisyonu = -1;
                                        int emlak_sahibi = -1;
                                        int emlak_sahibi_para_miktarı = -1;

                                        try {
                                            PreparedStatement p1 = bag.prepareStatement("SELECT * from alan WHERE alan_no = ?");
                                            p1.setInt(1, işletme_no);

                                            ResultSet rs1 = p1.executeQuery();

                                            if (rs1.next()) {
                                                işletme_fiyatı = rs1.getInt("işletme_fiyatı");
                                                işletme_sahibi = rs1.getInt("alan_sahibi");

                                            }


                                            PreparedStatement p2 = bag.prepareStatement("SELECT * FROM kullanıcı WHERE kullanıcı_no = ?");
                                            p2.setInt(1, kullanıcı_no);

                                            ResultSet rs2 = p2.executeQuery();

                                            if (rs2.next()) {
                                                alıcı_para_miktarı = rs2.getInt("kullanıcı_para_miktarı");
                                            }


                                            PreparedStatement p3 = bag.prepareStatement("SELECT * FROM kullanıcı WHERE kullanıcı_no = ?");
                                            p3.setInt(1, işletme_sahibi);

                                            ResultSet rs3 = p3.executeQuery();

                                            if (rs3.next()) {
                                                satıcı_para_miktarı = rs3.getInt("kullanıcı_para_miktarı");
                                            }


                                            PreparedStatement p4 = bag.prepareStatement("SELECT * FROM emlak WHERE emlak_no = ?");
                                            p4.setInt(1, finalSay);
                                            ResultSet rs4 = p4.executeQuery();

                                            if (rs4.next()) {
                                                emlak_komisyonu = rs4.getInt("emlak_komisyonu");

                                            }

                                            PreparedStatement p5 = bag.prepareStatement("SELECT * from alan WHERE alan_no = ?");
                                            p5.setInt(1, finalSay);

                                            ResultSet rs5 = p5.executeQuery();

                                            if (rs5.next()) {
                                                emlak_sahibi = rs5.getInt("alan_sahibi");

                                            }

                                            PreparedStatement p6 = bag.prepareStatement("SELECT * FROM kullanıcı WHERE kullanıcı_no = ?");
                                            p6.setInt(1, emlak_sahibi);

                                            ResultSet rs6 = p6.executeQuery();

                                            if (rs6.next()) {
                                                emlak_sahibi_para_miktarı = rs6.getInt("kullanıcı_para_miktarı");
                                            }


                                        } catch (Exception exp) {
                                            exp.printStackTrace();
                                        }

                                        int cevap = JOptionPane.showConfirmDialog(null, "işletme fiyatı: " + işletme_fiyatı + " emlak komisyonu: " + emlak_komisyonu + "\n toplam: " + (işletme_fiyatı + emlak_komisyonu), "uyarı", JOptionPane.YES_NO_OPTION);


                                        if (alıcı_para_miktarı - işletme_fiyatı - emlak_komisyonu > 0) {

                                            if (cevap == 0) {
                                                try {
                                                    PreparedStatement p1 = bag.prepareStatement("UPDATE alan SET alan_sahibi = ?, işletme_fiyatı = ?, arsa_fiyatı = ? WHERE alan_no = ?");
                                                    p1.setInt(1, kullanıcı_no);
                                                    p1.setInt(2, -1);
                                                    p1.setInt(3, -1);
                                                    p1.setInt(4, işletme_no);
                                                    p1.executeUpdate();


                                                    PreparedStatement p2 = bag.prepareStatement("UPDATE kullanıcı SET kullanıcı_para_miktarı = ? WHERE kullanıcı_no = ?");
                                                    p2.setInt(1, alıcı_para_miktarı - (işletme_fiyatı + emlak_komisyonu));
                                                    p2.setInt(2, kullanıcı_no);
                                                    p2.executeUpdate();

                                                    String bilgi1 = "\n" + işletme_no + " numaralı  alanda bulunan işletme satın alındı. işletme fiyatı:  " + işletme_fiyatı + " tl" + " ,emlak komisyonu : " + emlak_komisyonu + ", kalan para: " + (alıcı_para_miktarı - (işletme_fiyatı + emlak_komisyonu));
                                                    bilgi_ekranı(alan1, alan2, alan3, alan4, alan5, alan6, alan7, bilgi1, kullanıcı_no);

                                                    PreparedStatement p3 = bag.prepareStatement("UPDATE kullanıcı SET kullanıcı_para_miktarı = ? WHERE kullanıcı_no = ?");
                                                    p3.setInt(1, satıcı_para_miktarı + işletme_fiyatı - emlak_komisyonu);
                                                    p3.setInt(2, işletme_sahibi);
                                                    p3.executeUpdate();

                                                    String bilgi2 = "\n" + işletme_no + " numaralı  alanda bulunan işletmen satıldı. işletme fiyatı:  " + işletme_fiyatı + " tl" + " ,emlak komisyonu : " + emlak_komisyonu + ", güncel para: " + (satıcı_para_miktarı + işletme_fiyatı - emlak_komisyonu);

                                                    bilgi_ekranı(alan1, alan2, alan3, alan4, alan5, alan6, alan7, bilgi2, işletme_sahibi);

                                                    if (emlak_sahibi != 0) {
                                                        PreparedStatement p4 = bag.prepareStatement("UPDATE kullanıcı SET kullanıcı_para_miktarı = ? WHERE kullanıcı_no = ?");
                                                        p4.setInt(1, emlak_sahibi_para_miktarı + 2 * emlak_komisyonu);
                                                        p4.setInt(2, emlak_sahibi);
                                                        p4.executeUpdate();

                                                        String bilgi3 = "\n" + işletme_no + " numaralı işletme satımında emlağına başvuruldu. Emlak komisyonu: " + emlak_komisyonu + "alıcı ve satıcıdan alınan toplam komisyon: " + emlak_komisyonu * 2 + ", \ntoplam para: " + (emlak_sahibi_para_miktarı + emlak_komisyonu * 2);

                                                        bilgi_ekranı(alan1, alan2, alan3, alan4, alan5, alan6, alan7, bilgi3, emlak_sahibi);

                                                    }


                                                    dateTimeNow.setTime(dateTimeNow.getTime() + 1000);
                                                    PreparedStatement p5 = bag.prepareStatement("INSERT INTO satış VALUES (?,?,?,?,?)");

                                                    p5.setInt(1, işletme_no);
                                                    p5.setInt(2, finalSay);
                                                    p5.setTimestamp(3, dateTimeNow);
                                                    p5.setInt(4, işletme_fiyatı);
                                                    p5.setInt(5, -1);
                                                    int sonu = p5.executeUpdate();

                                                    if (sonu == 1) {
                                                        System.out.println("sdfsd");

                                                    }


                                                } catch (Exception exp) {
                                                    exp.printStackTrace();
                                                }
                                            }


                                        } else {
                                            JOptionPane.showMessageDialog(null, "yeterli paranız yok", "uyarı", JOptionPane.WARNING_MESSAGE);
                                        }


                                    }
                                });


                                frame.add(label2);
                                frame.add(label1);
                                frame.add(textField);
                                frame.add(btn);

                                frame.setLayout(null);
                                frame.setVisible(true);
                            }


                        }
                    });


                    alışveriş_frame.add(arsa_satın_al);
                    alışveriş_frame.add(işletme_satın_al);
                    alışveriş_frame.add(işletme_kirala);
                    alışveriş_frame.add(işletme_kur);

                }


            }

        } catch (Exception exp) {
            exp.printStackTrace();
        }


        alışveriş_frame.add(satın_al);
        alışveriş_frame.add(etiket1);
        alışveriş_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        alışveriş_frame.setLayout(null);
        alışveriş_frame.setVisible(true);


    }


    public void arsa_satılığa_çıkar(int kullanıcı_no, JTextArea alan1, JTextArea alan2, JTextArea alan3, JTextArea alan4, JTextArea alan5, JTextArea alan6, JTextArea alan7) {

        int arsa_sayisi = 0;

        String arsa_numaraları = "";
        try {
            PreparedStatement preparedStatement = bag.prepareStatement("SELECT * FROM alan");
            ResultSet resultSet1 = preparedStatement.executeQuery();

            while (resultSet1.next()) {
                int alan_sahibi;
                String alan_türü;
                int arsa_fiyatı;

                alan_sahibi = resultSet1.getInt("alan_sahibi");
                alan_türü = resultSet1.getString("alan_türü");
                arsa_fiyatı = resultSet1.getInt("arsa_fiyatı");

                if (alan_sahibi == kullanıcı_no && alan_türü.equals("arsa") && arsa_fiyatı == -1) {
                    arsa_numaraları = arsa_numaraları + resultSet1.getInt("alan_no") + ",  ";
                    arsa_sayisi++;

                }

            }
            preparedStatement.close();
            resultSet1.close();

            if (arsa_sayisi > 0) {
                JFrame arsa_sat = new JFrame();
                arsa_sat.setBounds(400, 400, 300, 300);
                JLabel label = new JLabel();
                label.setText("satabileceğiniz arsalarınız: " + arsa_numaraları + " bir arsa no giriniz: ");
                label.setBounds(0, 0, 300, 30);

                JTextField textField = new JTextField();
                textField.setBounds(0, 50, 100, 30);

                JLabel label1 = new JLabel("fiyat belirleyin");
                label1.setBounds(0, 90, 100, 30);

                JTextField textField1 = new JTextField();
                textField1.setBounds(0, 130, 100, 30);

                JButton btn = new JButton("satılığa çıkar");
                btn.setBounds(0, 170, 150, 30);

                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int alan_no = Integer.parseInt(textField.getText());
                        int fiyat = Integer.parseInt(textField1.getText());

                        satılık_arsalar.add(alan_no);

                        try {
                            PreparedStatement preparedStatement1 = bag.prepareStatement("UPDATE alan SET arsa_fiyatı=? WHERE alan_no = ?");
                            preparedStatement1.setInt(1, fiyat);
                            preparedStatement1.setInt(2, alan_no);
                            preparedStatement1.executeUpdate();

                            preparedStatement1.close();

                            String bilgi = "\n" + alan_no + " numaralı arsanızı " + fiyat + " tl'den satışa çıkardınız";
                            bilgi_ekranı(alan1, alan2, alan3, alan4, alan5, alan6, alan7, bilgi, kullanıcı_no);


                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        arsa_sat.setVisible(false);
                    }
                });


                arsa_sat.add(label);
                arsa_sat.add(textField);
                arsa_sat.add(label1);
                arsa_sat.add(textField1);
                arsa_sat.add(btn);

                arsa_sat.setLayout(null);
                arsa_sat.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "satacak arsanız yok", "arsa sat", JOptionPane.WARNING_MESSAGE);
            }


        } catch (Exception exp) {

            exp.printStackTrace();

        }


    }

    public void işletme_satılığa_çıkar(int kullanıcı_no, JTextArea alan1, JTextArea alan2, JTextArea alan3, JTextArea alan4, JTextArea alan5, JTextArea alan6, JTextArea alan7) {
        int işletme_sayısı = 0;
        String işletme_numaraları = "";


        try {
            PreparedStatement preparedStatement = bag.prepareStatement("SELECT * FROM alan");
            ResultSet resultSet1 = preparedStatement.executeQuery();

            while (resultSet1.next()) {
                int alan_sahibi;
                String alan_türü;
                int işletme_fiyatı;

                alan_sahibi = resultSet1.getInt("alan_sahibi");
                alan_türü = resultSet1.getString("alan_türü");
                işletme_fiyatı = resultSet1.getInt("işletme_fiyatı");

                if (alan_sahibi == kullanıcı_no && alan_türü.equals("işletme") && işletme_fiyatı == -1) {
                    işletme_numaraları = işletme_numaraları + resultSet1.getInt("alan_no") + ",  ";
                    işletme_sayısı++;

                }

            }
            preparedStatement.close();
            resultSet1.close();

            if (işletme_sayısı > 0) {
                JFrame işletme_sat = new JFrame();
                işletme_sat.setBounds(400, 400, 300, 300);
                JLabel label = new JLabel();
                label.setText("satabileceğiniz işletmeler: " + işletme_numaraları + " bir işletme no giriniz: ");
                label.setBounds(0, 0, 300, 30);

                JTextField textField = new JTextField();
                textField.setBounds(0, 50, 100, 30);

                JLabel label1 = new JLabel("fiyat belirleyin");
                label1.setBounds(0, 90, 100, 30);

                JTextField textField1 = new JTextField();
                textField1.setBounds(0, 130, 100, 30);

                JButton btn = new JButton("satılığa çıkar");
                btn.setBounds(0, 170, 150, 30);

                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int alan_no = Integer.parseInt(textField.getText());
                        int fiyat = Integer.parseInt(textField1.getText());

                        satılık_işletmeler.add(alan_no);

                        try {
                            PreparedStatement preparedStatement1 = bag.prepareStatement("UPDATE alan SET işletme_fiyatı=? WHERE alan_no = ?");
                            preparedStatement1.setInt(1, fiyat);
                            preparedStatement1.setInt(2, alan_no);
                            preparedStatement1.executeUpdate();

                            preparedStatement1.close();
                            String bilgi = "\n" + alan_no + " numaralı arsanızda bulunan işletmeyi " + fiyat + " tl'den satışa çıkardınız";
                            bilgi_ekranı(alan1, alan2, alan3, alan4, alan5, alan6, alan7, bilgi, kullanıcı_no);

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        işletme_sat.setVisible(false);
                    }
                });


                işletme_sat.add(label);
                işletme_sat.add(textField);
                işletme_sat.add(label1);
                işletme_sat.add(textField1);
                işletme_sat.add(btn);

                işletme_sat.setLayout(null);
                işletme_sat.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "satacak işletmeniz  yok", "işletme sat", JOptionPane.WARNING_MESSAGE);
            }


        } catch (Exception exp) {

            exp.printStackTrace();

        }


    }

    public void İş_Alımı(int satir, int sutun, JButton[][] no, int kullanıcıNo, JPanel panel, int finalSay, Timestamp date, JFrame isIstegi, JTextArea alan1, JTextArea alan2, JTextArea alan3, JTextArea alan4, JTextArea alan5, JTextArea alan6, JTextArea alan7, JPanel panel1, JPanel panel2, JPanel panel3, JPanel panel4, JPanel panel5, JPanel panel6, JPanel panel7) {


        try {
            String sql0 = "SELECT * FROM kullanıcı WHERE kullanıcı_no = ?";
            PreparedStatement p0 = bag.prepareStatement(sql0);

            p0.setInt(1, kullanıcıNo);
            ResultSet uno = p0.executeQuery();

            if (uno.next()) {

                if (uno.getInt("kullanıcı_çalışma_durumu") == 0) {
                    isIstegi.setVisible(false);
                    JFrame yönetici_teklif = new JFrame();

                    JPanel isTeklif = new JPanel();
                    isTeklif.setBounds(500, 400, 300, 500);

                    JLabel no0 = new JLabel(kullanıcıNo + " Nolu Oyuncu " + finalSay + ". alan için");
                    no0.setBounds(10, 30, 250, 30);

                    JLabel no1 = new JLabel("İş isteğinde bulundu!");
                    no1.setBounds(10, 60, 200, 30);

                    JLabel a = new JLabel("<html>Çalışma Saati<br>Başlangıç :</html>");
                    a.setBounds(10, 95, 200, 35);

                    JTextField saat = new JTextField();
                    saat.setBounds(110, 100, 50, 30);

                    JLabel a1 = new JLabel("<html>Çalışma Saati<br>Bitiş :</html>");
                    a1.setBounds(10, 145, 200, 35);

                    JTextField saat1 = new JTextField();
                    saat1.setBounds(110, 150, 50, 30);

                    JLabel b = new JLabel("Maaş :");
                    b.setBounds(10, 200, 100, 30);

                    JTextField maas = new JTextField();
                    maas.setBounds(110, 200, 50, 30);

                    JButton ilkTeklif = new JButton("Teklif Gönder");
                    ilkTeklif.setBounds(10, 250, 150, 30);

                    //İşteklifi
                    isTeklif.add(no0);
                    isTeklif.add(no1);
                    isTeklif.add(a);
                    isTeklif.add(saat);
                    isTeklif.add(a1);
                    isTeklif.add(saat1);
                    isTeklif.add(b);
                    isTeklif.add(maas);
                    isTeklif.add(ilkTeklif);
                    isTeklif.setLayout(null);

                    String işletmeTürü;
                    maas.setText("");
                    try {
                        String sql = "SELECT * FROM alan,işletme WHERE alan_no = ?";
                        PreparedStatement p1 = bag.prepareStatement(sql);

                        p1.setInt(1, finalSay);

                        ResultSet resultSet1 = p1.executeQuery();

                        if (resultSet1.next()) {
                            int işletme_sahibi = resultSet1.getInt("alan_sahibi");
                            işletmeTürü = resultSet1.getString("işletme_türü");

                            System.out.println(işletme_sahibi);

                            if (işletme_sahibi == 1) {
                                panel1.add(isTeklif);

                            } else if (işletme_sahibi == 2) {
                                panel2.add(isTeklif);

                            } else if (işletme_sahibi == 3) {
                                panel3.add(isTeklif);

                            } else if (işletme_sahibi == 4) {
                                panel4.add(isTeklif);

                            } else if (işletme_sahibi == 5) {
                                panel5.add(isTeklif);

                            } else if (işletme_sahibi == 6) {
                                panel6.add(isTeklif);

                            } else if (işletme_sahibi == 7) {
                                panel7.add(isTeklif);

                            } else if (işletme_sahibi == 0) {

                                ilkTeklif.setBounds(10, 100, 150, 30);
                                yönetici_teklif.setBounds(150, 150, 200, 200);
                                yönetici_teklif.add(no0);
                                yönetici_teklif.add(no1);
                                yönetici_teklif.add(ilkTeklif);
                                yönetici_teklif.setLayout(null);
                                yönetici_teklif.setVisible(true);
                                saat.setText("08:00");
                                saat1.setText("17:00");
                                maas.setText("200");

                            }
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    ilkTeklif.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            isIstegi.setVisible(false);
                            isTeklif.setVisible(false);
                            yönetici_teklif.setVisible(false);

                            JPanel isAlimi = new JPanel();
                            isAlimi.setBounds(500, 400, 300, 500);

                            JLabel no2 = new JLabel("İş isteğine yanıt geldi!");
                            no2.setBounds(10, 10, 200, 30);

                            JLabel c = new JLabel("<html>Teklif Edilen Saat : <br> " + saat.getText() + " - " + saat1.getText() + "</html>");
                            c.setBounds(10, 40, 160, 30);


                            JLabel d = new JLabel("Teklif Edilen Maaş : " + maas.getText());
                            d.setBounds(10, 70, 160, 30);


                            JButton kabul = new JButton("Kabul Et");
                            kabul.setBounds(10, 120, 160, 30);

                            JButton red = new JButton("Reddet");
                            red.setBounds(10, 160, 160, 30);

                            //iş
                            isAlimi.add(kabul);
                            isAlimi.add(red);
                            isAlimi.add(no2);
                            isAlimi.add(d);
                            isAlimi.add(c);
                            isAlimi.setLayout(null);
                            isAlimi.setVisible(true);

                            panel.add(isAlimi);
                            panel.setVisible(true);

                            kabul.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    isAlimi.setVisible(false);
                                    int maas2 = Integer.parseInt(maas.getText());
                                    Timestamp baslangıcDate = new Timestamp(System.currentTimeMillis());
                                    baslangıcDate.setTime(date.getTime());

                                    JButton ayrıl = new JButton("İşten Ayrıl");
                                    ayrıl.setBounds(600, 600, 100, 30);
                                    panel.add(ayrıl);


                                    try {

                                        String sql1 = "UPDATE işletme SET işletme_çalışan_sayısı = işletme_çalışan_sayısı + 1 WHERE işletme_no = ?";
                                        PreparedStatement statement1 = bag.prepareStatement(sql1);
                                        statement1.setInt(1, finalSay);
                                        statement1.executeUpdate();

                                        String sql10 = "SELECT * from çalışma where kullanıcı_no = ?";
                                        PreparedStatement p10 = bag.prepareStatement(sql10);
                                        p10.setInt(1, kullanıcıNo);
                                        ResultSet r1 = p10.executeQuery();
                                        say = 1;
                                        while (r1.next()) {
                                            say++;
                                        }
                                        System.out.println(say);

                                        String sql = "INSERT INTO çalışma(kullanıcı_no, kullanıcı_çalışma_no, kullanıcı_maaş, kullanıcı_çalışma_başlangıç_tarihi, kullanıcı_çalışma_saatleri, kullanıcı_çalışma_yeri_türü ,kullanıcı_çalışma_bitiş_tarihi) VALUES(?,?,?,?,?,?,?)";
                                        PreparedStatement p1 = bag.prepareStatement(sql);

                                        p1.setInt(1, kullanıcıNo);
                                        p1.setInt(2, say);
                                        p1.setInt(3, maas2);
                                        p1.setTimestamp(4, baslangıcDate);
                                        p1.setTimestamp(7, null);
                                        p1.setString(5, saat.getText() + " - " + saat1.getText());

                                        String sql7 = "SELECT * from emlak where emlak_no = ?";
                                        PreparedStatement p7 = bag.prepareStatement(sql7);
                                        p7.setInt(1, finalSay);
                                        ResultSet tür = p7.executeQuery();
                                        String sql8 = "SELECT * from mağaza where mağaza_no = ?";
                                        PreparedStatement p8 = bag.prepareStatement(sql8);
                                        p8.setInt(1, finalSay);
                                        ResultSet tür1 = p8.executeQuery();
                                        String sql9 = "SELECT * from market where market_no = ?";
                                        PreparedStatement p9 = bag.prepareStatement(sql9);
                                        p9.setInt(1, finalSay);
                                        ResultSet tür2 = p9.executeQuery();

                                        String dükkan_türü = null;
                                        if (tür.next()) {
                                            p1.setString(6, "emlak");
                                            dükkan_türü = "emlak";
                                        } else if (tür1.next()) {
                                            p1.setString(6, "mağaza");
                                            dükkan_türü = "mağaza";
                                        } else if (tür2.next()) {
                                            p1.setString(6, "market");
                                            dükkan_türü = "market";
                                        }
                                        p1.executeUpdate();

                                        String query = "UPDATE kullanıcı SET kullanıcı_çalışma_durumu = ? WHERE kullanıcı_no = ?";
                                        PreparedStatement statement = bag.prepareStatement(query);
                                        statement.setInt(1, 1);
                                        statement.setInt(2, kullanıcıNo);

                                        statement.executeUpdate();

                                        String bilgi = "\n" + finalSay + " numaralı alanda bulunan " + dükkan_türü + " dükkanında " + maas2 + " tl'ye " + saat.getText() + " - " + saat1.getText() + " çalışma saatleri olmak üzere çalışmaya başlanmıştır";
                                        bilgi_ekranı(alan1, alan2, alan3, alan4, alan5, alan6, alan7, bilgi, kullanıcıNo);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }

                                    ayrıl.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            ayrıl.setVisible(false);

                                            try {

                                                String sql2 = "UPDATE işletme SET işletme_çalışan_sayısı = işletme_çalışan_sayısı - 1 WHERE işletme_no = ? AND işletme_çalışan_sayısı >= 0";
                                                PreparedStatement statement1 = bag.prepareStatement(sql2);
                                                statement1.setInt(1, finalSay);
                                                statement1.executeUpdate();

                                                String sql1 = "SELECT * from çalışma WHERE kullanıcı_no = ?";

                                                PreparedStatement p2 = bag.prepareStatement(sql1);
                                                p2.setInt(1, kullanıcıNo);

                                                ResultSet sonuc1 = p2.executeQuery();
                                                if (sonuc1.next()) {


                                                    String sql = "UPDATE çalışma SET kullanıcı_çalışma_bitiş_tarihi = ? WHERE kullanıcı_no = ? AND kullanıcı_çalışma_no = ?";
                                                    PreparedStatement p1 = bag.prepareStatement(sql);

                                                    p1.setTimestamp(1, date);
                                                    p1.setInt(2, kullanıcıNo);
                                                    p1.setInt(3, say);


                                                    p1.executeUpdate();
                                                }
                                                String query = "UPDATE kullanıcı SET kullanıcı_çalışma_durumu = ? WHERE kullanıcı_no = ?";
                                                PreparedStatement statement = bag.prepareStatement(query);
                                                statement.setInt(1, 0);
                                                statement.setInt(2, kullanıcıNo);
                                                statement.executeUpdate();

                                                String bilgi = "\nkullanıcı işten ayrıldı";
                                                bilgi_ekranı(alan1, alan2, alan3, alan4, alan5, alan6, alan7, bilgi, kullanıcıNo);

                                            } catch (Exception exx) {
                                                exx.printStackTrace();
                                            }


                                        }
                                    });


                                }
                            });

                            red.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    isAlimi.setVisible(false);


                                }
                            });


                        }
                    });

                } else if (uno.getInt("kullanıcı_çalışma_durumu") == 1) {

                    String msj = "Aynı anda birden fazla işletmede çalışılamaz!";
                    String baslik = "Uyarı";
                    JOptionPane.showMessageDialog(null, msj, baslik, JOptionPane.WARNING_MESSAGE);

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public boolean Çalışma_saati_kontrol(int kullanıcı_no, Timestamp time) {

        boolean bool = false;

        try {

            String sql1 = "SELECT*FROM kullanıcı where kullanıcı_çalışma_durumu = ? ";
            PreparedStatement p1 = bag.prepareStatement(sql1);
            p1.setInt(1, 1);
            ResultSet r1 = p1.executeQuery();

            while (r1.next()) {

                String sql = "SELECT*FROM çalışma where kullanıcı_no = ? AND kullanıcı_çalışma_bitiş_tarihi IS NULL";
                PreparedStatement p = bag.prepareStatement(sql);
                p.setInt(1, kullanıcı_no);
                ResultSet r = p.executeQuery();
                String saat = null;

                while (r.next()) {
                    saat = r.getString("kullanıcı_çalışma_saatleri");

                }
                String[] parça = saat.split(" - ");

                String baslangıç = parça[0];
                String bitiş = parça[1];

                LocalTime saatBaşlangıç = LocalTime.parse(baslangıç);
                LocalTime saatBitiş = LocalTime.parse(bitiş);

                LocalTime saatKontrol = time.toLocalDateTime().toLocalTime();

                if (saatKontrol.compareTo(saatBaşlangıç) >= 0 && saatKontrol.compareTo(saatBitiş) <= 0) {

                    bool = true;

                }
            }
        } catch (Exception exx) {
            exx.printStackTrace();
        }

        return bool;
    }

    public void bilgi_ekranı(JTextArea alan1, JTextArea alan2, JTextArea alan3, JTextArea alan4, JTextArea alan5, JTextArea alan6, JTextArea alan7, String bilgi, int kullanıcı_no) {
        if (kullanıcı_no == 1) {
            alan1.append(bilgi);
        }

        if (kullanıcı_no == 2) {
            alan2.append(bilgi);
        }

        if (kullanıcı_no == 3) {
            alan3.append(bilgi);
        }

        if (kullanıcı_no == 4) {
            alan4.append(bilgi);
        }
        if (kullanıcı_no == 5) {
            alan5.append(bilgi);
        }
        if (kullanıcı_no == 6) {
            alan6.append(bilgi);
        }

        if (kullanıcı_no == 7) {
            alan7.append(bilgi);
        }


    }


}
