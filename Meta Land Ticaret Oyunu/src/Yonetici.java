import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class Yonetici {
    int geçen_saat = 0;
    int geçen_gün = 0;


    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel();
    JPanel panel5 = new JPanel();
    JPanel panel6 = new JPanel();
    JPanel panel7 = new JPanel();

    JPanel panel1_1 = new JPanel();
    JPanel panel2_1 = new JPanel();
    JPanel panel3_1 = new JPanel();
    JPanel panel4_1 = new JPanel();
    JPanel panel5_1 = new JPanel();
    JPanel panel6_1 = new JPanel();
    JPanel panel7_1 = new JPanel();

    JTextArea alan1 = new JTextArea();
    JTextArea alan2 = new JTextArea();
    JTextArea alan3 = new JTextArea();
    JTextArea alan4 = new JTextArea();
    JTextArea alan5 = new JTextArea();
    JTextArea alan6 = new JTextArea();
    JTextArea alan7 = new JTextArea();


    public Yonetici() {
        JTabbedPane sekme = new JTabbedPane();

        JFrame başlangıç = new JFrame("META LAND");
        JLayeredPane lpanel1 = new JLayeredPane();

        lpanel1.setBounds(0, 0, 750, 800);

        JLabel label1 = new JLabel("Satır: ");
        label1.setBounds(10, 0, 250, 30);

        JTextField textField1 = new JTextField();
        textField1.setBounds(10, 40, 100, 30);

        JLabel label1_2 = new JLabel("Sütun: ");
        label1_2.setBounds(10, 80, 250, 30);

        JTextField textField1_2 = new JTextField();
        textField1_2.setBounds(10, 120, 100, 30);


        JLabel label2 = new JLabel("Lütfen başlangıç yiyecek miktarını giriniz: ");

        label2.setBounds(10, 160, 250, 30);

        JTextField textField2 = new JTextField();
        textField2.setBounds(10, 200, 100, 30);


        JLabel label3 = new JLabel("Lütfen başlangıç eşya miktarını giriniz: ");

        label3.setBounds(10, 240, 250, 30);

        JTextField textField3 = new JTextField();
        textField3.setBounds(10, 280, 100, 30);


        JLabel label4 = new JLabel("Lütfen başlangıç para miktarını giriniz: ");

        label4.setBounds(10, 320, 250, 30);

        JTextField textField4 = new JTextField();
        textField4.setBounds(10, 360, 100, 30);


        JLabel label5 = new JLabel("Lütfen günlük yiyecek giderini giriniz: ");

        label5.setBounds(10, 400, 250, 30);

        JTextField textField5 = new JTextField();
        textField5.setBounds(10, 440, 100, 30);


        JLabel label6 = new JLabel("Lütfen günlük eşya giderini giriniz: ");

        label6.setBounds(10, 480, 250, 30);

        JTextField textField6 = new JTextField();
        textField6.setBounds(10, 520, 100, 30);


        JLabel label7 = new JLabel("Lütfen günlük para giderini giriniz: ");

        label7.setBounds(10, 560, 250, 30);

        JTextField textField7 = new JTextField();
        textField7.setBounds(10, 600, 100, 30);

        JLabel label8 = new JLabel("market yiyecek ücreti: ");
        label8.setBounds(400, 0, 150, 30);

        JTextField textField8 = new JTextField();
        textField8.setBounds(400, 40, 100, 30);

        JLabel label9 = new JLabel("mağaza eşya ücreti: ");
        label9.setBounds(400, 80, 150, 30);

        JTextField textField9 = new JTextField();
        textField9.setBounds(400, 120, 100, 30);


        JLabel label10 = new JLabel("Emlak komisyonunu giriniz: ");
        label10.setBounds(400, 160, 170, 30);
        JTextField textField10 = new JTextField();
        textField10.setBounds(400, 200, 100, 30);

        JLabel label11 = new JLabel("Arsa fiyatını giriniz: ");
        label11.setBounds(400, 240, 150, 30);
        JTextField textField11 = new JTextField();
        textField11.setBounds(400, 280, 100, 30);

        JLabel label12 = new JLabel("işletme inşa etme ücreti: ");
        label12.setBounds(400, 320, 150, 30);
        JTextField textField12 = new JTextField();
        textField12.setBounds(400, 360, 100, 30);

        JLabel label13 = new JLabel("işletme sabit gelir oranı: ");
        label13.setBounds(400, 400, 150, 30);
        JTextField textField13 = new JTextField();
        textField13.setBounds(400, 440, 100, 30);

        JLabel label14 = new JLabel("işletme sabit gelir miktarı: ");
        label14.setBounds(400, 480, 150, 30);
        JTextField textField14 = new JTextField();
        textField14.setBounds(400, 520, 100, 30);

        JButton buton1 = new JButton("oyunu başlat");

        buton1.setBounds(10, 650, 150, 30);


        buton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                lpanel1.setVisible(false);
                başlangıç.setVisible(false);

                String text1 = textField1.getText();
                String text2 = textField1_2.getText();
                String text3 = textField2.getText();
                String text4 = textField3.getText();
                String text5 = textField4.getText();
                String text6 = textField5.getText();
                String text7 = textField6.getText();
                String text8 = textField7.getText();
                String text9 = textField8.getText();
                String text10 = textField9.getText();
                String text11 = textField10.getText();
                String text12 = textField11.getText();
                String text13 = textField12.getText();
                String text14 = textField13.getText();
                String text15 = textField14.getText();


                int satir = Integer.parseInt(text1);
                int sutun = Integer.parseInt(text2);

                Date dateTime = new Date();
                Timestamp dateTimeNow = new Timestamp(dateTime.getTime());


                try {

                    Class.forName("com.mysql.cj.jdbc.Driver");

                    Connection bag = DriverManager.getConnection("jdbc:mysql://localhost:3306/proje?autoReconnect=true&useSSL=false" + "&useUnicode= true&useLegacyDatetimeCode=false&serverTimezone=Turkey", "root", "1234");


                    String delete = "DELETE FROM yönetici";
                    PreparedStatement delete0 = bag.prepareStatement(delete);
                    delete0.executeUpdate();

                    String delete1 = "DELETE FROM alan";
                    PreparedStatement delete2 = bag.prepareStatement(delete1);
                    delete2.executeUpdate();

                    String delete3 = "DELETE FROM emlak";
                    PreparedStatement delete4 = bag.prepareStatement(delete3);
                    delete4.executeUpdate();

                    String delete5 = "DELETE FROM işletme";
                    PreparedStatement delete6 = bag.prepareStatement(delete5);
                    delete6.executeUpdate();

                    String delete7 = "DELETE FROM market";
                    PreparedStatement delete8 = bag.prepareStatement(delete7);
                    delete8.executeUpdate();

                    String delete9 = "DELETE FROM mağaza";
                    PreparedStatement delete10 = bag.prepareStatement(delete9);
                    delete10.executeUpdate();

                    String delete11 = "DELETE FROM satış";
                    PreparedStatement delete12 = bag.prepareStatement(delete11);
                    delete12.executeUpdate();


                    String delete13 = "DELETE FROM çalışma";
                    PreparedStatement delete14 = bag.prepareStatement(delete13);
                    delete14.executeUpdate();


                    String delete15 = "DELETE FROM kullanıcı";
                    PreparedStatement delete16 = bag.prepareStatement(delete15);
                    delete16.executeUpdate();


                    String sql = "INSERT INTO yönetici VALUES(?,?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement p1 = bag.prepareStatement(sql);
                    p1.setInt(1, Integer.parseInt(text3));
                    p1.setInt(2, Integer.parseInt(text4));
                    p1.setInt(3, Integer.parseInt(text5));
                    p1.setTimestamp(4, dateTimeNow);
                    p1.setString(5, text1 + "X" + text2);
                    p1.setInt(6, Integer.parseInt(text6));
                    p1.setInt(7, Integer.parseInt(text7));
                    p1.setInt(8, Integer.parseInt(text8));
                    p1.setInt(9, 0);
                    p1.setFloat(10, Float.parseFloat(text14));
                    p1.setInt(11, Integer.parseInt(text15));


                    p1.executeUpdate();


                    /*
                    String sql2 = "UPDATE kullanıcı SET kullanıcı_yemek_miktarı = ?, kullanıcı_eşya_miktarı= ?, kullanıcı_para_miktarı = ?";
                    PreparedStatement p3 = bag.prepareStatement(sql2);
                    p3.setInt(1, Integer.parseInt(text3));
                    p3.setInt(2, Integer.parseInt(text4));
                    p3.setInt(3, Integer.parseInt(text5));

                    int sonuc2 = p3.executeUpdate();
*/


                    PreparedStatement kisi1 = bag.prepareStatement("INSERT INTO kullanıcı VALUES (?,?,?,?,?,?,?,?)");
                    kisi1.setInt(1, 1);
                    kisi1.setInt(2, 111);
                    kisi1.setString(3, "Fatma Nur");
                    kisi1.setString(4, "Kurt");
                    kisi1.setInt(5, Integer.parseInt(text3));
                    kisi1.setInt(6, Integer.parseInt(text4));
                    kisi1.setInt(7, Integer.parseInt(text5));
                    kisi1.setInt(8, 0);
                    kisi1.executeUpdate();


                    PreparedStatement kisi2 = bag.prepareStatement("INSERT INTO kullanıcı VALUES (?,?,?,?,?,?,?,?)");
                    kisi2.setInt(1, 2);
                    kisi2.setInt(2, 222);
                    kisi2.setString(3, "Tayyib");
                    kisi2.setString(4, "Okur");
                    kisi2.setInt(5, Integer.parseInt(text3));
                    kisi2.setInt(6, Integer.parseInt(text4));
                    kisi2.setInt(7, Integer.parseInt(text5));
                    kisi2.setInt(8, 0);
                    kisi2.executeUpdate();


                    PreparedStatement kisi3 = bag.prepareStatement("INSERT INTO kullanıcı VALUES (?,?,?,?,?,?,?,?)");
                    kisi3.setInt(1, 3);
                    kisi3.setInt(2, 333);
                    kisi3.setString(3, "Ada");
                    kisi3.setString(4, "Su");
                    kisi3.setInt(5, Integer.parseInt(text3));
                    kisi3.setInt(6, Integer.parseInt(text4));
                    kisi3.setInt(7, Integer.parseInt(text5));
                    kisi3.setInt(8, 0);
                    kisi3.executeUpdate();


                    PreparedStatement kisi4 = bag.prepareStatement("INSERT INTO kullanıcı VALUES (?,?,?,?,?,?,?,?)");
                    kisi4.setInt(1, 4);
                    kisi4.setInt(2, 444);
                    kisi4.setString(3, "Aras");
                    kisi4.setString(4, "Çekiç");
                    kisi4.setInt(5, Integer.parseInt(text3));
                    kisi4.setInt(6, Integer.parseInt(text4));
                    kisi4.setInt(7, Integer.parseInt(text5));
                    kisi4.setInt(8, 0);
                    kisi4.executeUpdate();


                    PreparedStatement kisi5 = bag.prepareStatement("INSERT INTO kullanıcı VALUES (?,?,?,?,?,?,?,?)");
                    kisi5.setInt(1, 5);
                    kisi5.setInt(2, 555);
                    kisi5.setString(3, "Ceylan");
                    kisi5.setString(4, "Çiçek");
                    kisi5.setInt(5, Integer.parseInt(text3));
                    kisi5.setInt(6, Integer.parseInt(text4));
                    kisi5.setInt(7, Integer.parseInt(text5));
                    kisi5.setInt(8, 0);
                    kisi5.executeUpdate();


                    PreparedStatement kisi6 = bag.prepareStatement("INSERT INTO kullanıcı VALUES (?,?,?,?,?,?,?,?)");
                    kisi6.setInt(1, 6);
                    kisi6.setInt(2, 666);
                    kisi6.setString(3, "Defne");
                    kisi6.setString(4, "Yıldız");
                    kisi6.setInt(5, Integer.parseInt(text3));
                    kisi6.setInt(6, Integer.parseInt(text4));
                    kisi6.setInt(7, Integer.parseInt(text5));
                    kisi6.setInt(8, 0);
                    kisi6.executeUpdate();

                    PreparedStatement kisi7 = bag.prepareStatement("INSERT INTO kullanıcı VALUES (?,?,?,?,?,?,?,?)");
                    kisi7.setInt(1, 7);
                    kisi7.setInt(2, 777);
                    kisi7.setString(3, "Deniz");
                    kisi7.setString(4, "Ok");
                    kisi7.setInt(5, Integer.parseInt(text3));
                    kisi7.setInt(6, Integer.parseInt(text4));
                    kisi7.setInt(7, Integer.parseInt(text5));
                    kisi7.setInt(8, 0);
                    kisi7.executeUpdate();


                    for (int i = 1; i <= satir * sutun; i++) {

                        String sql3 = "INSERT INTO alan VALUES(?,?,?,?,?)";
                        PreparedStatement p2 = bag.prepareStatement(sql3);

                        p2.setInt(1, i);


                        if (i == 1) {
                            p2.setString(2, "işletme");
                        } else if (i == 2) {
                            p2.setString(2, "işletme");
                        } else if (i == 3) {
                            p2.setString(2, "işletme");
                        } else {
                            p2.setString(2, "arsa");
                        }

                        p2.setInt(3, 0);
                        p2.setInt(4, -1);
                        p2.setInt(5, Integer.parseInt(text11));
                        p2.executeUpdate();

                        if (i == satir * sutun) {
                            p2.close();
                        }

                    }


                    String sql3 = "INSERT INTO işletme VALUES(?,?,?,?,?,?,?)";
                    PreparedStatement p4 = bag.prepareStatement(sql3);

                    String sql4 = "INSERT INTO market VALUES(?,?)";

                    PreparedStatement p5 = bag.prepareStatement(sql4);

                    p4.setInt(1, 1);
                    p4.setString(2, "market");
                    p4.setInt(3, 4);
                    p4.setInt(4, 100);
                    p4.setInt(5, 0);
                    p4.setTimestamp(6, dateTimeNow);
                    p4.setInt(7, 0);
                    p4.executeUpdate();

                    p5.setInt(1, 1);
                    p5.setInt(2, Integer.parseInt(text9));
                    p5.executeUpdate();
                    p5.close();


                    String sql5 = "INSERT INTO mağaza VALUES(?,?)";

                    PreparedStatement p6 = bag.prepareStatement(sql5);


                    p4.setInt(1, 2);
                    p4.setString(2, "mağaza");
                    p4.setInt(3, 4);
                    p4.setInt(4, 100);
                    p4.setInt(5, 0);
                    p4.setTimestamp(6, dateTimeNow);
                    p4.executeUpdate();

                    p6.setInt(1, 2);
                    p6.setInt(2, Integer.parseInt(text10));
                    p6.executeUpdate();

                    p6.close();

                    String sql6 = "INSERT INTO emlak VALUES(?,?)";

                    PreparedStatement p7 = bag.prepareStatement(sql6);
                    p7.setInt(1, 3);
                    p7.setInt(2, Integer.parseInt(text12));

                    p7.executeUpdate();
                    p7.close();

                    p4.setInt(1, 3);
                    p4.setString(2, "emlak");
                    p4.setInt(3, 4);
                    p4.setInt(4, 100);
                    p4.setInt(5, 0);
                    p4.setTimestamp(6, dateTimeNow);


                    p4.executeUpdate();

                    p5.close();
                    p4.close();

                    p1.close();
                    bag.close();


                } catch (Exception exp) {
                    exp.printStackTrace();
                }


                JButton alanlar[][] = new JButton[satir][sutun];

                JFrame yönetici = new JFrame();


                JButton gün = new JButton("1 gün ileri");
                JButton saat = new JButton("1 saat ileri");

                gün.setBounds(400, 630, 100, 30);
                saat.setBounds(400, 680, 100, 30);


                JButton btn1 = new JButton("güncelle");
                JButton btn2 = new JButton("güncelle");
                JButton btn3 = new JButton("güncelle");
                JButton btn4 = new JButton("güncelle");
                JButton btn5 = new JButton("güncelle");
                JButton btn6 = new JButton("güncelle");
                JButton btn7 = new JButton("güncelle");
                JButton btn8 = new JButton("güncelle");

                Font font1 = btn1.getFont();
                Font yeniFont1 = new Font(font1.getName(), font1.getStyle(), 8); // Yazıyı 2 puan küçültmek için
                btn1.setFont(yeniFont1);

                Font font2 = btn2.getFont();
                Font yeniFont2 = new Font(font2.getName(), font2.getStyle(), 8); // Yazıyı 2 puan küçültmek için
                btn2.setFont(yeniFont2);

                Font font3 = btn3.getFont();
                Font yeniFont3 = new Font(font3.getName(), font3.getStyle(), 8); // Yazıyı 2 puan küçültmek için
                btn3.setFont(yeniFont3);

                Font font4 = btn4.getFont();
                Font yeniFont4 = new Font(font4.getName(), font4.getStyle(), 8); // Yazıyı 2 puan küçültmek için
                btn4.setFont(yeniFont4);

                Font font5 = btn5.getFont();
                Font yeniFont5 = new Font(font5.getName(), font5.getStyle(), 8); // Yazıyı 2 puan küçültmek için
                btn5.setFont(yeniFont5);

                Font font6 = btn6.getFont();
                Font yeniFont6 = new Font(font6.getName(), font6.getStyle(), 8); // Yazıyı 2 puan küçültmek için
                btn6.setFont(yeniFont6);

                Font font7 = btn7.getFont();
                Font yeniFont7 = new Font(font7.getName(), font7.getStyle(), 8); // Yazıyı 2 puan küçültmek için
                btn7.setFont(yeniFont7);

                Font font8 = btn8.getFont();
                Font yeniFont8 = new Font(font8.getName(), font8.getStyle(), 8); // Yazıyı 2 puan küçültmek için
                btn8.setFont(yeniFont8);

                JTextField txt1 = new JTextField();
                JTextField txt2 = new JTextField();
                JTextField txt3 = new JTextField();
                JTextField txt4 = new JTextField();
                JTextField txt5 = new JTextField();
                JTextField txt6 = new JTextField();
                JTextField txt7 = new JTextField();
                JTextField txt8 = new JTextField();

                JLabel label1 = new JLabel("eksiltilcek yemek miktarı değiştir");
                JLabel label2 = new JLabel("eksiltilcek eşya miktarı değiştir");
                JLabel label3 = new JLabel("eksiltilcek para miktarı değiştir");
                JLabel label4 = new JLabel("mağaza eşya ücreti değiştir");
                JLabel label5 = new JLabel("market yemek ücreti değiştir");
                JLabel label6 = new JLabel("emlak komisyonu değiştir");
                JLabel label7 = new JLabel("işletme gelir oranını değiştir");
                JLabel label8 = new JLabel("işletme gelir miktarını değiştir");


                label1.setBounds(550, 10, 200, 30);
                txt1.setBounds(580, 50, 70, 30);
                btn1.setBounds(660, 50, 70, 30);

                label2.setBounds(550, 100, 200, 30);
                txt2.setBounds(580, 140, 70, 30);
                btn2.setBounds(660, 140, 70, 30);


                label3.setBounds(550, 190, 200, 30);
                txt3.setBounds(580, 230, 70, 30);
                btn3.setBounds(660, 230, 70, 30);


                label4.setBounds(550, 270, 200, 30);
                txt4.setBounds(580, 310, 70, 30);
                btn4.setBounds(660, 310, 70, 30);

                label5.setBounds(550, 350, 200, 30);
                txt5.setBounds(580, 390, 70, 30);
                btn5.setBounds(660, 390, 70, 30);

                label6.setBounds(550, 430, 200, 30);
                txt6.setBounds(580, 470, 70, 30);
                btn6.setBounds(660, 470, 70, 30);

                label7.setBounds(550, 510, 200, 30);
                txt7.setBounds(580, 550, 70, 30);
                btn7.setBounds(660, 550, 70, 30);

                label8.setBounds(550, 590, 200, 30);
                txt8.setBounds(580, 630, 70, 30);
                btn8.setBounds(660, 630, 70, 30);

                btn1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        int miktar = Integer.parseInt(txt1.getText());


                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");

                            Connection bag = DriverManager.getConnection("jdbc:mysql://localhost:3306/proje?autoReconnect=true&useSSL=false" + "&useUnicode= true&useLegacyDatetimeCode=false&serverTimezone=Turkey", "root", "1234");


                            String sql = "UPDATE yönetici SET günlük_yiyecek_gideri = ? ";
                            PreparedStatement p1 = bag.prepareStatement(sql);
                            p1.setInt(1, miktar);

                            p1.executeUpdate();


                            p1.close();
                            bag.close();


                        } catch (Exception exp) {
                            exp.printStackTrace();
                        }


                    }
                });

                btn2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int miktar = Integer.parseInt(txt2.getText());


                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");

                            Connection bag = DriverManager.getConnection("jdbc:mysql://localhost:3306/proje?autoReconnect=true&useSSL=false" + "&useUnicode= true&useLegacyDatetimeCode=false&serverTimezone=Turkey", "root", "1234");


                            String sql = "UPDATE yönetici SET günlük_eşya_gideri = ? ";
                            PreparedStatement p1 = bag.prepareStatement(sql);

                            p1.setInt(1, miktar);

                            p1.executeUpdate();


                            p1.close();
                            bag.close();


                        } catch (Exception exp) {
                            exp.printStackTrace();
                        }


                    }
                });

                btn3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int miktar = Integer.parseInt(txt3.getText());


                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");

                            Connection bag = DriverManager.getConnection("jdbc:mysql://localhost:3306/proje?autoReconnect=true&useSSL=false" + "&useUnicode= true&useLegacyDatetimeCode=false&serverTimezone=Turkey", "root", "1234");


                            String sql = "UPDATE yönetici SET günlük_para_gideri = ? ";
                            PreparedStatement p1 = bag.prepareStatement(sql);

                            p1.setInt(1, miktar);

                            p1.executeUpdate();


                            p1.close();
                            bag.close();


                        } catch (Exception exp) {
                            exp.printStackTrace();
                        }


                    }
                });


                btn4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int miktar = Integer.parseInt(txt4.getText());


                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");

                            Connection bag = DriverManager.getConnection("jdbc:mysql://localhost:3306/proje?autoReconnect=true&useSSL=false" + "&useUnicode= true&useLegacyDatetimeCode=false&serverTimezone=Turkey", "root", "1234");


                            String sql = "UPDATE mağaza SET mağaza_eşya_ücreti = ? WHERE mağaza_no = 2";
                            PreparedStatement p1 = bag.prepareStatement(sql);

                            p1.setInt(1, miktar);

                            p1.executeUpdate();


                            p1.close();
                            bag.close();


                        } catch (Exception exp) {
                            exp.printStackTrace();
                        }


                    }
                });


                btn5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int miktar = Integer.parseInt(txt5.getText());


                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");

                            Connection bag = DriverManager.getConnection("jdbc:mysql://localhost:3306/proje?autoReconnect=true&useSSL=false" + "&useUnicode= true&useLegacyDatetimeCode=false&serverTimezone=Turkey", "root", "1234");


                            String sql = "UPDATE market SET market_yiyecek_ücreti = ?  WHERE market_no = 1";
                            PreparedStatement p1 = bag.prepareStatement(sql);

                            p1.setInt(1, miktar);

                            p1.executeUpdate();


                            p1.close();
                            bag.close();


                        } catch (Exception exp) {
                            exp.printStackTrace();
                        }
                    }
                });


                btn6.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        int miktar = Integer.parseInt(txt6.getText());


                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");

                            Connection bag = DriverManager.getConnection("jdbc:mysql://localhost:3306/proje?autoReconnect=true&useSSL=false" + "&useUnicode= true&useLegacyDatetimeCode=false&serverTimezone=Turkey", "root", "1234");


                            String sql = "UPDATE emlak SET emlak_komisyonu = ?  WHERE emlak_no =3";
                            PreparedStatement p1 = bag.prepareStatement(sql);

                            p1.setInt(1, miktar);

                            p1.executeUpdate();


                            p1.close();
                            bag.close();


                        } catch (Exception exp) {
                            exp.printStackTrace();
                        }


                    }
                });


                btn7.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        float miktar = Float.parseFloat(txt7.getText());


                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");

                            Connection bag = DriverManager.getConnection("jdbc:mysql://localhost:3306/proje?autoReconnect=true&useSSL=false" + "&useUnicode= true&useLegacyDatetimeCode=false&serverTimezone=Turkey", "root", "1234");


                            String sql = "UPDATE yönetici SET işletme_sabit_gelir_oranı = ? ";
                            PreparedStatement p1 = bag.prepareStatement(sql);

                            p1.setFloat(1, miktar);

                            p1.executeUpdate();


                            p1.close();
                            bag.close();


                        } catch (Exception exp) {
                            exp.printStackTrace();
                        }


                    }
                });


                btn8.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        int miktar = Integer.parseInt(txt8.getText());


                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");

                            Connection bag = DriverManager.getConnection("jdbc:mysql://localhost:3306/proje?autoReconnect=true&useSSL=false" + "&useUnicode= true&useLegacyDatetimeCode=false&serverTimezone=Turkey", "root", "1234");


                            String sql = "UPDATE yönetici SET işletme_sabit_gelir_miktarı = ? ";
                            PreparedStatement p1 = bag.prepareStatement(sql);

                            p1.setInt(1, miktar);

                            p1.executeUpdate();


                            p1.close();
                            bag.close();


                        } catch (Exception exp) {
                            exp.printStackTrace();
                        }


                    }
                });

                gün.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        try {

                            Class.forName("com.mysql.cj.jdbc.Driver");

                            Connection bag = DriverManager.getConnection("jdbc:mysql://localhost:3306/proje?autoReconnect=true&useSSL=false" + "&useUnicode= true&useLegacyDatetimeCode=false&serverTimezone=Turkey", "root", "1234");

                            String sql = "SELECT * from yönetici";

                            Statement p1 = bag.createStatement();
                            ResultSet sonuc = p1.executeQuery(sql);
                            sonuc.next();
                            int yemek_gideri = 0;
                            int eşya_gideri = 0;
                            int para_gideri = 0;


                            yemek_gideri = sonuc.getInt(6);
                            eşya_gideri = sonuc.getInt(7);
                            para_gideri = sonuc.getInt(8);


                            String sql1 = "SELECT * from kullanıcı";

                            PreparedStatement p2 = bag.prepareStatement(sql1);

                            ResultSet sonuc1 = p2.executeQuery();

                            String sql2 = "UPDATE kullanıcı SET kullanıcı_yemek_miktarı = ?, kullanıcı_eşya_miktarı = ?, kullanıcı_para_miktarı =? WHERE kullanıcı_no = ?";

                            PreparedStatement p3 = bag.prepareStatement(sql2);
                            int sayac = 1;
                            while (sonuc1.next()) {
                                int kullanıcı_yemek_miktarı = sonuc1.getInt(5);
                                int kullanıcı_eşya_miktarı = sonuc1.getInt(6);
                                int kullanıcı_para_miktarı = sonuc1.getInt(7);


                                String sql10 = "SELECT * from çalışma where kullanıcı_no = ?";
                                PreparedStatement p10 = bag.prepareStatement(sql10);
                                p10.setInt(1, sayac);
                                ResultSet r1 = p10.executeQuery();
                                int say = 1;
                                while (r1.next()) {
                                    say++;
                                }

                                String sql3 = "SELECT * FROM çalışma WHERE kullanıcı_no = ? AND kullanıcı_çalışma_bitiş_tarihi IS NULL";

                                PreparedStatement p0 = bag.prepareStatement(sql3);
                                p0.setInt(1, sayac);

                                ResultSet sonuc2 = p0.executeQuery();
                                if (sonuc2.next()) {
                                    if (sonuc2.getString("kullanıcı_çalışma_yeri_türü").equals("market")) {
                                        yemek_gideri = 0;
                                    } else if (sonuc2.getString("kullanıcı_çalışma_yeri_türü").equals("mağaza")) {
                                        eşya_gideri = 0;
                                    } else if (sonuc2.getString("kullanıcı_çalışma_yeri_türü").equals("emlak")) {
                                        para_gideri = 0;
                                    }
                                }

                                int kalan_yemek_miktarı = kullanıcı_yemek_miktarı - yemek_gideri;
                                int kalan_eşya_miktarı = kullanıcı_eşya_miktarı - eşya_gideri;
                                int kalan_para_miktarı = kullanıcı_para_miktarı - para_gideri;


                                if (sayac == 1 && panel1_1.isValid()) {

                                    if (kalan_yemek_miktarı <= 0 || kalan_eşya_miktarı <= 0 || kalan_para_miktarı <= 0) {

                                        sekme.remove(panel1_1);
                                        sekme.remove(panel1);

                                        String upd = "UPDATE alan SET alan_sahibi = 0 WHERE alan_sahibi = ?";
                                        PreparedStatement upda = bag.prepareStatement(upd);
                                        upda.setInt(1, sayac);
                                        upda.executeUpdate();


                                        String del = "DELETE FROM kullanıcı WHERE kullanıcı_no = ?";
                                        PreparedStatement del1 = bag.prepareStatement(del);
                                        del1.setInt(1, sayac);
                                        del1.executeUpdate();


                                        JOptionPane.showMessageDialog(null, sayac + " numaralı kullanıcı elendi", "uyarı", JOptionPane.WARNING_MESSAGE);

                                    }

                                    p3.setInt(1, kalan_yemek_miktarı);
                                    p3.setInt(2, kalan_eşya_miktarı);
                                    p3.setInt(3, kalan_para_miktarı);
                                    p3.setInt(4, sayac);
                                    p3.executeUpdate();
                                    String bilgi = "\n1 gün geçildi. Kalan yemek miktarı: " + kalan_yemek_miktarı + " kalan eşya miktarı: " + kalan_eşya_miktarı + " kalan para miktarı: " + kalan_para_miktarı + " tl'dir";


                                    alan1.append(bilgi);
                                }
                                if (sayac == 2 && panel2_1.isValid()) {


                                    if (kalan_yemek_miktarı <= 0 || kalan_eşya_miktarı <= 0 || kalan_para_miktarı <= 0) {

                                        sekme.remove(panel2_1);

                                        sekme.remove(panel2);

                                        String upd = "UPDATE alan SET alan_sahibi = 0 WHERE alan_sahibi = ?";
                                        PreparedStatement upda = bag.prepareStatement(upd);
                                        upda.setInt(1, sayac);
                                        upda.executeUpdate();

                                        String del = "DELETE FROM kullanıcı WHERE kullanıcı_no = ?";
                                        PreparedStatement del1 = bag.prepareStatement(del);
                                        del1.setInt(1, sayac);
                                        del1.executeUpdate();


                                        JOptionPane.showMessageDialog(null, sayac + " numaralı kullanıcı elendi", "uyarı", JOptionPane.WARNING_MESSAGE);

                                    }


                                    p3.setInt(1, kalan_yemek_miktarı);
                                    p3.setInt(2, kalan_eşya_miktarı);
                                    p3.setInt(3, kalan_para_miktarı);
                                    p3.setInt(4, sayac);
                                    p3.executeUpdate();
                                    String bilgi = "\n1 gün geçildi. Kalan yemek miktarı: " + kalan_yemek_miktarı + " kalan eşya miktarı: " + kalan_eşya_miktarı + " kalan para miktarı: " + kalan_para_miktarı + " tl'dir";

                                    alan2.append(bilgi);
                                }
                                if (sayac == 3 && panel3_1.isValid()) {

                                    if (kalan_yemek_miktarı <= 0 || kalan_eşya_miktarı <= 0 || kalan_para_miktarı <= 0) {


                                        sekme.remove(panel3_1);

                                        sekme.remove(panel3);

                                        String upd = "UPDATE alan SET alan_sahibi = 0 WHERE alan_sahibi = ?";
                                        PreparedStatement upda = bag.prepareStatement(upd);
                                        upda.setInt(1, sayac);
                                        upda.executeUpdate();

                                        String del = "DELETE FROM kullanıcı WHERE kullanıcı_no = ?";
                                        PreparedStatement del1 = bag.prepareStatement(del);
                                        del1.setInt(1, sayac);
                                        del1.executeUpdate();


                                        JOptionPane.showMessageDialog(null, sayac + " numaralı kullanıcı elendi", "uyarı", JOptionPane.WARNING_MESSAGE);

                                    }


                                    p3.setInt(1, kalan_yemek_miktarı);
                                    p3.setInt(2, kalan_eşya_miktarı);
                                    p3.setInt(3, kalan_para_miktarı);
                                    p3.setInt(4, sayac);
                                    p3.executeUpdate();
                                    String bilgi = "\n1 gün geçildi. Kalan yemek miktarı: " + kalan_yemek_miktarı + " kalan eşya miktarı: " + kalan_eşya_miktarı + " kalan para miktarı: " + kalan_para_miktarı + " tl'dir";

                                    alan3.append(bilgi);
                                }
                                if (sayac == 4 && panel4_1.isValid()) {

                                    if (kalan_yemek_miktarı <= 0 || kalan_eşya_miktarı <= 0 || kalan_para_miktarı <= 0) {
                                        sekme.remove(panel4_1);

                                        sekme.remove(panel4);

                                        String upd = "UPDATE alan SET alan_sahibi = 0 WHERE alan_sahibi = ?";
                                        PreparedStatement upda = bag.prepareStatement(upd);
                                        upda.setInt(1, sayac);
                                        upda.executeUpdate();

                                        String del = "DELETE FROM kullanıcı WHERE kullanıcı_no = ?";
                                        PreparedStatement del1 = bag.prepareStatement(del);
                                        del1.setInt(1, sayac);
                                        del1.executeUpdate();


                                        JOptionPane.showMessageDialog(null, sayac + " numaralı kullanıcı elendi", "uyarı", JOptionPane.WARNING_MESSAGE);

                                    }


                                    p3.setInt(1, kalan_yemek_miktarı);
                                    p3.setInt(2, kalan_eşya_miktarı);
                                    p3.setInt(3, kalan_para_miktarı);
                                    p3.setInt(4, sayac);
                                    p3.executeUpdate();
                                    String bilgi = "\n1 gün geçildi. Kalan yemek miktarı: " + kalan_yemek_miktarı + " kalan eşya miktarı: " + kalan_eşya_miktarı + " kalan para miktarı: " + kalan_para_miktarı + " tl'dir";

                                    alan4.append(bilgi);
                                }
                                if (sayac == 5 && panel5_1.isValid()) {

                                    if (kalan_yemek_miktarı <= 0 || kalan_eşya_miktarı <= 0 || kalan_para_miktarı <= 0) {

                                        sekme.remove(panel5_1);

                                        sekme.remove(panel5);

                                        String upd = "UPDATE alan SET alan_sahibi = 0 WHERE alan_sahibi = ?";
                                        PreparedStatement upda = bag.prepareStatement(upd);
                                        upda.setInt(1, sayac);
                                        upda.executeUpdate();
                                        String del = "DELETE FROM kullanıcı WHERE kullanıcı_no = ?";
                                        PreparedStatement del1 = bag.prepareStatement(del);
                                        del1.setInt(1, sayac);
                                        del1.executeUpdate();


                                        JOptionPane.showMessageDialog(null, sayac + " numaralı kullanıcı elendi", "uyarı", JOptionPane.WARNING_MESSAGE);

                                    }


                                    p3.setInt(1, kalan_yemek_miktarı);
                                    p3.setInt(2, kalan_eşya_miktarı);
                                    p3.setInt(3, kalan_para_miktarı);
                                    p3.setInt(4, sayac);
                                    p3.executeUpdate();
                                    String bilgi = "\n1 gün geçildi. Kalan yemek miktarı: " + kalan_yemek_miktarı + " kalan eşya miktarı: " + kalan_eşya_miktarı + " kalan para miktarı: " + kalan_para_miktarı + " tl'dir";

                                    alan5.append(bilgi);
                                }
                                if (sayac == 6 && panel6_1.isValid()) {

                                    if (kalan_yemek_miktarı <= 0 || kalan_eşya_miktarı <= 0 || kalan_para_miktarı <= 0) {
                                        sekme.remove(panel6_1);
                                        sekme.remove(panel6);

                                        String upd = "UPDATE alan SET alan_sahibi = 0 WHERE alan_sahibi = ?";
                                        PreparedStatement upda = bag.prepareStatement(upd);
                                        upda.setInt(1, sayac);
                                        upda.executeUpdate();

                                        String del = "DELETE FROM kullanıcı WHERE kullanıcı_no = ?";
                                        PreparedStatement del1 = bag.prepareStatement(del);
                                        del1.setInt(1, sayac);
                                        del1.executeUpdate();


                                        JOptionPane.showMessageDialog(null, sayac + " numaralı kullanıcı elendi", "uyarı", JOptionPane.WARNING_MESSAGE);

                                    }

                                    p3.setInt(1, kalan_yemek_miktarı);
                                    p3.setInt(2, kalan_eşya_miktarı);
                                    p3.setInt(3, kalan_para_miktarı);
                                    p3.setInt(4, sayac);
                                    p3.executeUpdate();
                                    String bilgi = "\n1 gün geçildi. Kalan yemek miktarı: " + kalan_yemek_miktarı + " kalan eşya miktarı: " + kalan_eşya_miktarı + " kalan para miktarı: " + kalan_para_miktarı + " tl'dir";

                                    alan6.append(bilgi);
                                }
                                if (sayac == 7 && panel7_1.isValid()) {

                                    if (kalan_yemek_miktarı <= 0 || kalan_eşya_miktarı <= 0 || kalan_para_miktarı <= 0) {

                                        sekme.remove(panel7_1);
                                        sekme.remove(panel7);

                                        String upd = "UPDATE alan SET alan_sahibi = 0 WHERE alan_sahibi = ?";
                                        PreparedStatement upda = bag.prepareStatement(upd);
                                        upda.setInt(1, sayac);
                                        upda.executeUpdate();
                                        String del = "DELETE FROM kullanıcı WHERE kullanıcı_no = ?";
                                        PreparedStatement del1 = bag.prepareStatement(del);
                                        del1.setInt(1, sayac);
                                        del1.executeUpdate();


                                        JOptionPane.showMessageDialog(null, sayac + " numaralı kullanıcı elendi", "uyarı", JOptionPane.WARNING_MESSAGE);

                                    }

                                    p3.setInt(1, kalan_yemek_miktarı);
                                    p3.setInt(2, kalan_eşya_miktarı);
                                    p3.setInt(3, kalan_para_miktarı);
                                    p3.setInt(4, sayac);

                                    p3.executeUpdate();
                                    String bilgi = "\n1 gün geçildi. Kalan yemek miktarı: " + kalan_yemek_miktarı + " kalan eşya miktarı: " + kalan_eşya_miktarı + " kalan para miktarı: " + kalan_para_miktarı + " tl'dir";

                                    alan7.append(bilgi);
                                }


                                String sql6 = "SELECT * from kullanıcı where kullanıcı_çalışma_durumu = ?";

                                PreparedStatement p4 = bag.prepareStatement(sql6);
                                p4.setInt(1, 1);
                                ResultSet sonuc0 = p4.executeQuery();

                                if (sonuc0.next()) {

                                    String sql5 = "SELECT * from çalışma where kullanıcı_no = ?";

                                    PreparedStatement p5 = bag.prepareStatement(sql5);
                                    p5.setInt(1, sonuc0.getInt("kullanıcı_no"));
                                    ResultSet sonuc3 = p5.executeQuery();
                                    if (sonuc3.next()) {
                                        int maaşEkle = sonuc3.getInt("kullanıcı_maaş");
                                        int eskiPara = sonuc0.getInt("kullanıcı_para_miktarı");
                                        int yeniPara = maaşEkle + eskiPara;

                                        int no = sonuc0.getInt("kullanıcı_no");
                                        String sql4 = "UPDATE kullanıcı SET kullanıcı_para_miktarı = ? where kullanıcı_no = ?";
                                        PreparedStatement p = bag.prepareStatement(sql4);
                                        p.setInt(1, yeniPara);
                                        p.setInt(2, sonuc0.getInt("kullanıcı_no"));

                                        String bilgi = "\nkullanıcının günlük maaşı: " + maaşEkle + " tl'dir " + " güncel para miktarı: " + yeniPara;

                                        if (no == 1 && sayac == 1) {
                                            alan1.append(bilgi);
                                        }

                                        if (no == 2 && sayac == 2) {
                                            alan2.append(bilgi);
                                        }

                                        if (no == 3 && sayac == 3) {
                                            alan3.append(bilgi);
                                        }

                                        if (no == 4 && sayac == 4) {
                                            alan4.append(bilgi);
                                        }

                                        if (no == 5 && sayac == 5) {
                                            alan5.append(bilgi);
                                        }

                                        if (no == 6 && sayac == 6) {
                                            alan6.append(bilgi);
                                        }

                                        if (no == 7 && sayac == 7) {
                                            alan7.append(bilgi);
                                        }


                                        p.executeUpdate();
                                    }
                                }


                                sayac++;
                            }

                            int miktar = 10;

                            String sql4 = "SELECT * from alan, işletme where alan_no = işletme_no ";
                            PreparedStatement p5 = bag.prepareStatement(sql4);
                            ResultSet sonuc4 = p5.executeQuery();

                            while (sonuc4.next()) {

                                String sql5 = "SELECT * from yönetici ";
                                PreparedStatement p6 = bag.prepareStatement(sql5);
                                ResultSet sonuc5 = p6.executeQuery();
                                if (sonuc5.next()) {


                                    if (sonuc4.getInt("işletme_seviyesi") == 1) {

                                        miktar = sonuc5.getInt("işletme_sabit_gelir_miktarı");

                                    } else if (sonuc4.getInt("işletme_seviyesi") == 2) {

                                        float oran = sonuc5.getInt("işletme_sabit_gelir_oranı");
                                        miktar = (int) (sonuc5.getInt("işletme_sabit_gelir_miktarı") * oran + sonuc5.getInt("işletme_sabit_gelir_miktarı"));

                                    } else if (sonuc4.getInt("işletme_seviyesi") == 3) {

                                        float oran = sonuc5.getInt("işletme_sabit_gelir_oranı");
                                        miktar = (int) (sonuc5.getInt("işletme_sabit_gelir_miktarı") * oran + sonuc5.getInt("işletme_sabit_gelir_miktarı"));

                                    }

                                }
                                String sql7 = "SELECT * from kullanıcı where kullanıcı_no = ?";
                                PreparedStatement p8 = bag.prepareStatement(sql7);
                                p8.setInt(1, sonuc4.getInt("alan_sahibi"));
                                ResultSet sonuc6 = p8.executeQuery();
                                if (sonuc6.next()) {
                                    int exMoney = sonuc6.getInt("kullanıcı_para_miktarı");
                                    int newMoney = exMoney + miktar;


                                    String sql6 = "UPDATE kullanıcı SET kullanıcı_para_miktarı = ? where kullanıcı_no = ? ";
                                    PreparedStatement p7 = bag.prepareStatement(sql6);
                                    p7.setInt(1, newMoney);
                                    p7.setInt(2, sonuc4.getInt("alan_sahibi"));
                                    p7.executeUpdate();

                                    String bilgi = "\nişletme gelir miktarı: " + miktar + " güncel para miktarı: " + newMoney;

                                    if (sonuc4.getInt("alan_sahibi") == 1) {
                                        alan1.append(bilgi);
                                    }

                                    if (sonuc4.getInt("alan_sahibi") == 2) {
                                        alan2.append(bilgi);
                                    }

                                    if (sonuc4.getInt("alan_sahibi") == 3) {
                                        alan3.append(bilgi);
                                    }

                                    if (sonuc4.getInt("alan_sahibi") == 4) {
                                        alan4.append(bilgi);
                                    }

                                    if (sonuc4.getInt("alan_sahibi") == 5) {
                                        alan5.append(bilgi);
                                    }

                                    if (sonuc4.getInt("alan_sahibi") == 6) {
                                        alan6.append(bilgi);
                                    }

                                    if (sonuc4.getInt("alan_sahibi") == 7) {
                                        alan7.append(bilgi);
                                    }

                                }

                            }

                            String sql6 = "UPDATE işletme SET işletme_tam_kapasite_çalışma_gün_sayısı = işletme_tam_kapasite_çalışma_gün_sayısı + ? where işletme_no = ? ";
                            PreparedStatement p7 = bag.prepareStatement(sql6);


                            String sql3 = "SELECT * from işletme";
                            PreparedStatement p4 = bag.prepareStatement(sql3);
                            ResultSet sonuc2 = p4.executeQuery();

                            while (sonuc2.next()) {

                                if (sonuc2.getInt("işletme_çalışan_sayısı") == sonuc2.getInt("işletme_kapasitesi")) {

                                    p7.setInt(1, 1);
                                    p7.setInt(2, sonuc2.getInt("işletme_no"));
                                    p7.executeUpdate();
                                }
                                if (sonuc2.getInt("işletme_tam_kapasite_çalışma_gün_sayısı") == 7) {

                                    String sql7 = "UPDATE işletme SET işletme_seviyesi = işletme_seviyesi +1 , işletme_tam_kapasite_çalışma_gün_sayısı = 0 where (işletme_seviyesi <= 3 AND işletme_no = ?)";
                                    PreparedStatement p8 = bag.prepareStatement(sql7);
                                    p8.setInt(1, sonuc2.getInt("işletme_no"));
                                    p8.executeUpdate();


                                    String bilgi = "\n" + sonuc2.getInt("işletme_no") + " Nolu " + sonuc2.getString("işletme_türü") + " " + (sonuc2.getInt("işletme_seviyesi") + 1) + "." + " işletme seviyesindedir.";

                                    String sql8 = "SELECT * from alan where alan_no = ? ";
                                    PreparedStatement p9 = bag.prepareStatement(sql8);
                                    p9.setInt(1, sonuc2.getInt("işletme_no"));
                                    ResultSet sonuc5 = p9.executeQuery();
                                    while (sonuc5.next()) {

                                        if (sonuc5.getInt("alan_sahibi") == 1) {

                                            alan1.append(bilgi);

                                        } else if (sonuc5.getInt("alan_sahibi") == 2) {

                                            alan2.append(bilgi);

                                        } else if (sonuc5.getInt("alan_sahibi") == 3) {

                                            alan3.append(bilgi);

                                        } else if (sonuc5.getInt("alan_sahibi") == 4) {

                                            alan4.append(bilgi);

                                        } else if (sonuc5.getInt("alan_sahibi") == 5) {

                                            alan5.append(bilgi);

                                        } else if (sonuc5.getInt("alan_sahibi") == 6) {

                                            alan6.append(bilgi);

                                        } else if (sonuc5.getInt("alan_sahibi") == 7) {

                                            alan7.append(bilgi);

                                        }


                                    }


                                }


                            }
                            String sql5 = "SELECT * from işletme";
                            PreparedStatement p6 = bag.prepareStatement(sql5);
                            ResultSet sonuc3 = p6.executeQuery();

                            while (sonuc3.next()) {

                                String sql7 = "UPDATE işletme SET işletme_kapasitesi = ? WHERE işletme_no = ?";
                                PreparedStatement p8 = bag.prepareStatement(sql7);
                                p8.setInt(2, sonuc3.getInt("işletme_no"));

                                if (sonuc3.getInt("işletme_seviyesi") == 1) {
                                    p8.setInt(1, 3);
                                } else if (sonuc3.getInt("işletme_seviyesi") == 2) {
                                    p8.setInt(1, 6);
                                } else if (sonuc3.getInt("işletme_seviyesi") == 3) {
                                    p8.setInt(1, 12);
                                } else if (sonuc3.getInt("işletme_seviyesi") == 4)
                                    p8.setInt(1, 100);
                                p8.executeUpdate();

                            }


                            bag.close();
                            sonuc.close();
                            sonuc1.close();
                            p1.close();
                            p2.close();
                            p3.close();


                            Calendar takvim = Calendar.getInstance();

                            takvim.setTime(dateTimeNow);
                            takvim.add(Calendar.DAY_OF_MONTH, 1);

                            Timestamp yeniZaman = new Timestamp(takvim.getTimeInMillis());
                            dateTimeNow.setTime(yeniZaman.getTime());


                        } catch (Exception exp) {
                            exp.printStackTrace();
                        }


                    }
                });


                saat.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        geçen_saat++;

                        Calendar takvim = Calendar.getInstance();

                        takvim.setTime(dateTimeNow);
                        takvim.add(Calendar.HOUR_OF_DAY, 1);

                        Timestamp yeniZaman = new Timestamp(takvim.getTimeInMillis());
                        dateTimeNow.setTime(yeniZaman.getTime());

                        if (geçen_saat / 24 > 0) {
                            geçen_gün = geçen_gün + geçen_saat / 24;
                            geçen_saat = geçen_saat % 24;


                            try {

                                Class.forName("com.mysql.cj.jdbc.Driver");

                                Connection bag = DriverManager.getConnection("jdbc:mysql://localhost:3306/proje?autoReconnect=true&useSSL=false" + "&useUnicode= true&useLegacyDatetimeCode=false&serverTimezone=Turkey", "root", "1234");

                                String sql = "SELECT * from yönetici";

                                Statement p1 = bag.createStatement();
                                ResultSet sonuc = p1.executeQuery(sql);
                                sonuc.next();
                                int yemek_gideri = 0;
                                int eşya_gideri = 0;
                                int para_gideri = 0;


                                yemek_gideri = sonuc.getInt(6);
                                eşya_gideri = sonuc.getInt(7);
                                para_gideri = sonuc.getInt(8);


                                String sql1 = "SELECT * from kullanıcı";

                                PreparedStatement p2 = bag.prepareStatement(sql1);

                                ResultSet sonuc1 = p2.executeQuery();

                                int sayac = 1;
                                while (sonuc1.next()) {
                                    int kullanıcı_yemek_miktarı = sonuc1.getInt(5);
                                    int kullanıcı_eşya_miktarı = sonuc1.getInt(6);
                                    int kullanıcı_para_miktarı = sonuc1.getInt(7);

                                    int kalan_yemek_miktarı = kullanıcı_yemek_miktarı - yemek_gideri;
                                    int kalan_eşya_miktarı = kullanıcı_eşya_miktarı - eşya_gideri;
                                    int kalan_para_miktarı = kullanıcı_para_miktarı - para_gideri;


                                    String sql2 = "UPDATE kullanıcı SET kullanıcı_yemek_miktarı = ?, kullanıcı_eşya_miktarı = ?, kullanıcı_para_miktarı =? WHERE kullanıcı_no = ?";


                                    PreparedStatement p3 = bag.prepareStatement(sql2);
                                    if (sayac == 1 && panel1_1.isValid()) {

                                        if (kalan_yemek_miktarı <= 0 || kalan_eşya_miktarı <= 0 || kalan_para_miktarı <= 0) {

                                            sekme.remove(panel1_1);
                                            sekme.remove(panel1);

                                            String upd = "UPDATE alan SET alan_sahibi = 0 WHERE alan_sahibi = ?";
                                            PreparedStatement upda = bag.prepareStatement(upd);
                                            upda.setInt(1, sayac);
                                            upda.executeUpdate();

                                            String del = "DELETE FROM kullanıcı WHERE kullanıcı_no = ?";
                                            PreparedStatement del1 = bag.prepareStatement(del);
                                            del1.setInt(1, sayac);
                                            del1.executeUpdate();
                                            JOptionPane.showMessageDialog(null, sayac + " numaralı kullanıcı elendi", "uyarı", JOptionPane.WARNING_MESSAGE);

                                        }


                                        p3.setInt(1, kalan_yemek_miktarı);
                                        p3.setInt(2, kalan_eşya_miktarı);
                                        p3.setInt(3, kalan_para_miktarı);
                                        p3.setInt(4, sayac);

                                        String bilgi = "\n1 gün geçildi. Kalan yemek miktarı: " + kalan_yemek_miktarı + " kalan eşya miktarı: " + kalan_eşya_miktarı + " kalan para miktarı: " + kalan_para_miktarı + " tl'dir";


                                        alan1.append(bilgi);
                                    }
                                    if (sayac == 2 && panel2_1.isValid()) {

                                        if (kalan_yemek_miktarı <= 0 || kalan_eşya_miktarı <= 0 || kalan_para_miktarı <= 0) {

                                            sekme.remove(panel2_1);
                                            sekme.remove(panel2);

                                            String upd = "UPDATE alan SET alan_sahibi = 0 WHERE alan_sahibi = ?";
                                            PreparedStatement upda = bag.prepareStatement(upd);
                                            upda.setInt(1, sayac);
                                            upda.executeUpdate();

                                            String del = "DELETE FROM kullanıcı WHERE kullanıcı_no = ?";
                                            PreparedStatement del1 = bag.prepareStatement(del);
                                            del1.setInt(1, sayac);
                                            del1.executeUpdate();
                                            JOptionPane.showMessageDialog(null, sayac + " numaralı kullanıcı elendi", "uyarı", JOptionPane.WARNING_MESSAGE);

                                        }


                                        p3.setInt(1, kalan_yemek_miktarı);
                                        p3.setInt(2, kalan_eşya_miktarı);
                                        p3.setInt(3, kalan_para_miktarı);
                                        p3.setInt(4, sayac);

                                        String bilgi = "\n1 gün geçildi. Kalan yemek miktarı: " + kalan_yemek_miktarı + " kalan eşya miktarı: " + kalan_eşya_miktarı + " kalan para miktarı: " + kalan_para_miktarı + " tl'dir";

                                        alan2.append(bilgi);
                                    }
                                    if (sayac == 3 && panel3_1.isValid()) {

                                        if (kalan_yemek_miktarı <= 0 || kalan_eşya_miktarı <= 0 || kalan_para_miktarı <= 0) {

                                            sekme.remove(panel3_1);
                                            sekme.remove(panel3);

                                            String upd = "UPDATE alan SET alan_sahibi = 0 WHERE alan_sahibi = ?";
                                            PreparedStatement upda = bag.prepareStatement(upd);
                                            upda.setInt(1, sayac);
                                            upda.executeUpdate();
                                            String del = "DELETE FROM kullanıcı WHERE kullanıcı_no = ?";
                                            PreparedStatement del1 = bag.prepareStatement(del);
                                            del1.setInt(1, sayac);
                                            del1.executeUpdate();
                                            JOptionPane.showMessageDialog(null, sayac + " numaralı kullanıcı elendi", "uyarı", JOptionPane.WARNING_MESSAGE);

                                        }

                                        p3.setInt(1, kalan_yemek_miktarı);
                                        p3.setInt(2, kalan_eşya_miktarı);
                                        p3.setInt(3, kalan_para_miktarı);
                                        p3.setInt(4, sayac);

                                        String bilgi = "\n1 gün geçildi. Kalan yemek miktarı: " + kalan_yemek_miktarı + " kalan eşya miktarı: " + kalan_eşya_miktarı + " kalan para miktarı: " + kalan_para_miktarı + " tl'dir";

                                        alan3.append(bilgi);
                                    }
                                    if (sayac == 4 && panel4_1.isValid()) {

                                        if (kalan_yemek_miktarı <= 0 || kalan_eşya_miktarı <= 0 || kalan_para_miktarı <= 0) {

                                            sekme.remove(panel4_1);
                                            sekme.remove(panel4);

                                            String upd = "UPDATE alan SET alan_sahibi = 0 WHERE alan_sahibi = ?";
                                            PreparedStatement upda = bag.prepareStatement(upd);
                                            upda.setInt(1, sayac);
                                            upda.executeUpdate();

                                            String del = "DELETE FROM kullanıcı WHERE kullanıcı_no = ?";
                                            PreparedStatement del1 = bag.prepareStatement(del);
                                            del1.setInt(1, sayac);
                                            del1.executeUpdate();
                                            JOptionPane.showMessageDialog(null, sayac + " numaralı kullanıcı elendi", "uyarı", JOptionPane.WARNING_MESSAGE);

                                        }

                                        p3.setInt(1, kalan_yemek_miktarı);
                                        p3.setInt(2, kalan_eşya_miktarı);
                                        p3.setInt(3, kalan_para_miktarı);
                                        p3.setInt(4, sayac);

                                        String bilgi = "\n1 gün geçildi. Kalan yemek miktarı: " + kalan_yemek_miktarı + " kalan eşya miktarı: " + kalan_eşya_miktarı + " kalan para miktarı: " + kalan_para_miktarı + " tl'dir";

                                        alan4.append(bilgi);
                                    }
                                    if (sayac == 5 && panel5_1.isValid()) {


                                        if (kalan_yemek_miktarı <= 0 || kalan_eşya_miktarı <= 0 || kalan_para_miktarı <= 0) {

                                            sekme.remove(panel5_1);
                                            sekme.remove(panel5);

                                            String upd = "UPDATE alan SET alan_sahibi = 0 WHERE alan_sahibi = ?";
                                            PreparedStatement upda = bag.prepareStatement(upd);
                                            upda.setInt(1, sayac);
                                            upda.executeUpdate();

                                            String del = "DELETE FROM kullanıcı WHERE kullanıcı_no = ?";
                                            PreparedStatement del1 = bag.prepareStatement(del);
                                            del1.setInt(1, sayac);
                                            del1.executeUpdate();
                                            JOptionPane.showMessageDialog(null, sayac + " numaralı kullanıcı elendi", "uyarı", JOptionPane.WARNING_MESSAGE);

                                        }

                                        p3.setInt(1, kalan_yemek_miktarı);
                                        p3.setInt(2, kalan_eşya_miktarı);
                                        p3.setInt(3, kalan_para_miktarı);
                                        p3.setInt(4, sayac);

                                        String bilgi = "\n1 gün geçildi. Kalan yemek miktarı: " + kalan_yemek_miktarı + " kalan eşya miktarı: " + kalan_eşya_miktarı + " kalan para miktarı: " + kalan_para_miktarı + " tl'dir";

                                        alan5.append(bilgi);
                                    }
                                    if (sayac == 6 && panel6_1.isValid()) {

                                        if (kalan_yemek_miktarı <= 0 || kalan_eşya_miktarı <= 0 || kalan_para_miktarı <= 0) {

                                            sekme.remove(panel6_1);
                                            sekme.remove(panel6);

                                            String upd = "UPDATE alan SET alan_sahibi = 0 WHERE alan_sahibi = ?";
                                            PreparedStatement upda = bag.prepareStatement(upd);
                                            upda.setInt(1, sayac);
                                            upda.executeUpdate();
                                            String del = "DELETE FROM kullanıcı WHERE kullanıcı_no = ?";
                                            PreparedStatement del1 = bag.prepareStatement(del);
                                            del1.setInt(1, sayac);
                                            del1.executeUpdate();
                                            JOptionPane.showMessageDialog(null, sayac + " numaralı kullanıcı elendi", "uyarı", JOptionPane.WARNING_MESSAGE);

                                        }


                                        p3.setInt(1, kalan_yemek_miktarı);
                                        p3.setInt(2, kalan_eşya_miktarı);
                                        p3.setInt(3, kalan_para_miktarı);
                                        p3.setInt(4, sayac);

                                        String bilgi = "\n1 gün geçildi. Kalan yemek miktarı: " + kalan_yemek_miktarı + " kalan eşya miktarı: " + kalan_eşya_miktarı + " kalan para miktarı: " + kalan_para_miktarı + " tl'dir";

                                        alan6.append(bilgi);
                                    }
                                    if (sayac == 7 && panel7_1.isValid()) {

                                        if (kalan_yemek_miktarı <= 0 || kalan_eşya_miktarı <= 0 || kalan_para_miktarı <= 0) {

                                            sekme.remove(panel7_1);
                                            sekme.remove(panel7);

                                            String upd = "UPDATE alan SET alan_sahibi = 0 WHERE alan_sahibi = ?";
                                            PreparedStatement upda = bag.prepareStatement(upd);
                                            upda.setInt(1, sayac);
                                            upda.executeUpdate();
                                            String del = "DELETE FROM kullanıcı WHERE kullanıcı_no = ?";
                                            PreparedStatement del1 = bag.prepareStatement(del);
                                            del1.setInt(1, sayac);
                                            del1.executeUpdate();
                                            JOptionPane.showMessageDialog(null, sayac + " numaralı kullanıcı elendi", "uyarı", JOptionPane.WARNING_MESSAGE);

                                        }

                                        p3.setInt(1, kalan_yemek_miktarı);
                                        p3.setInt(2, kalan_eşya_miktarı);
                                        p3.setInt(3, kalan_para_miktarı);
                                        p3.setInt(4, sayac);

                                        String bilgi = "\n1 gün geçildi. Kalan yemek miktarı: " + kalan_yemek_miktarı + " kalan eşya miktarı: " + kalan_eşya_miktarı + " kalan para miktarı: " + kalan_para_miktarı + " tl'dir";

                                        alan7.append(bilgi);
                                    }


                                    p3.executeUpdate();

                                    sayac++;
                                }


                            } catch (Exception exp) {
                                exp.printStackTrace();
                            }


                        }


                    }
                });


//////////////////////////////////////////////////////

                int sayacResim = 0;
                for (int i = 0; i < satir; i++) {

                    for (int j = 0; j < sutun; j++) {

                        alanlar[i][j] = new JButton();
                        alanlar[i][j].setBounds(j * 60, i * 60, 60, 60);

                        if (sayacResim == 0) {
                            Isletme_Resim(alanlar[i][j], "market");
                        } else if (sayacResim == 1) {
                            Isletme_Resim(alanlar[i][j], "magaza");
                        } else if (sayacResim == 2) {
                            Isletme_Resim(alanlar[i][j], "emlak");
                        }
                        sayacResim++;
                    }
                }

                for (int i = 0; i < satir; i++) {

                    for (int j = 0; j < sutun; j++) {

                        yönetici.add(alanlar[i][j]);
                    }

                }

                Kullanici kullanici1 = new Kullanici(satir, sutun, dateTimeNow, Integer.parseInt(text13), panel1_1, panel2_1, panel3_1, panel4_1, panel5_1, panel6_1, panel7_1, alan1, alan2, alan3, alan4, alan5, alan6, alan7, sekme, panel1, panel2, panel3, panel4, panel5, panel6, panel7);


                JButton kullanıcı1 = new JButton("Fatma Nur");
                JButton kullanıcı2 = new JButton("Tayyib");
                JButton kullanıcı3 = new JButton("Ada");
                JButton kullanıcı4 = new JButton("Aras");
                JButton kullanıcı5 = new JButton("Ceylan");
                JButton kullanıcı6 = new JButton("Defne");
                JButton kullanıcı7 = new JButton("Deniz");


                JButton bilgi_butonu = new JButton("kullanıcı bilgilerini gör");

                bilgi_butonu.setBounds(0, 650, 200, 30);

                JFrame bilgiEkranı = new JFrame("bilgi ekranı");

                kullanıcı1.setBounds(10, 10, 100, 30);
                kullanıcı2.setBounds(10, 50, 100, 30);
                kullanıcı3.setBounds(10, 90, 100, 30);
                kullanıcı4.setBounds(10, 130, 100, 30);
                kullanıcı5.setBounds(150, 10, 100, 30);
                kullanıcı6.setBounds(150, 50, 100, 30);
                kullanıcı7.setBounds(150, 90, 100, 30);

                JFrame kullanıcıBilgi = new JFrame();
                JTextArea alan = new JTextArea();
                alan.setBounds(0, 0, 750, 800);
                kullanıcıBilgi.setBounds(0, 0, 750, 800);

                bilgi_butonu.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {


                        kullanıcı1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                bilgiEkranı.setVisible(false);
                                alan.setText(alan1.getText());
                                kullanıcıBilgi.add(alan);
                                kullanıcıBilgi.setLayout(null);
                                kullanıcıBilgi.setVisible(true);
                            }
                        });

                        kullanıcı2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                bilgiEkranı.setVisible(false);
                                alan.setText(alan2.getText());
                                kullanıcıBilgi.add(alan);
                                kullanıcıBilgi.setLayout(null);
                                kullanıcıBilgi.setVisible(true);

                            }
                        });

                        kullanıcı3.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                bilgiEkranı.setVisible(false);
                                alan.setText(alan3.getText());
                                kullanıcıBilgi.add(alan);
                                kullanıcıBilgi.setLayout(null);
                                kullanıcıBilgi.setVisible(true);
                            }
                        });


                        kullanıcı4.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                bilgiEkranı.setVisible(false);
                                alan.setText(alan4.getText());
                                kullanıcıBilgi.add(alan);
                                kullanıcıBilgi.setLayout(null);
                                kullanıcıBilgi.setVisible(true);
                            }
                        });


                        kullanıcı5.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                bilgiEkranı.setVisible(false);
                                alan.setText(alan5.getText());
                                kullanıcıBilgi.add(alan);
                                kullanıcıBilgi.setLayout(null);
                                kullanıcıBilgi.setVisible(true);
                            }
                        });

                        kullanıcı6.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                bilgiEkranı.setVisible(false);
                                alan.setText(alan6.getText());
                                kullanıcıBilgi.add(alan);
                                kullanıcıBilgi.setLayout(null);
                                kullanıcıBilgi.setVisible(true);
                            }
                        });

                        kullanıcı7.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                bilgiEkranı.setVisible(false);
                                alan.setText(alan7.getText());
                                kullanıcıBilgi.add(alan);
                                kullanıcıBilgi.setLayout(null);
                                kullanıcıBilgi.setVisible(true);
                            }
                        });


                        bilgiEkranı.add(kullanıcı1);
                        bilgiEkranı.add(kullanıcı2);
                        bilgiEkranı.add(kullanıcı3);
                        bilgiEkranı.add(kullanıcı4);
                        bilgiEkranı.add(kullanıcı5);
                        bilgiEkranı.add(kullanıcı6);
                        bilgiEkranı.add(kullanıcı7);
                        bilgiEkranı.setSize(300, 300);
                        bilgiEkranı.setLayout(null);
                        bilgiEkranı.setVisible(true);
                    }
                });


                yönetici.add(bilgi_butonu);
                yönetici.add(gün);
                yönetici.add(saat);
                yönetici.add(label1);
                yönetici.add(txt1);
                yönetici.add(btn1);

                yönetici.add(label2);
                yönetici.add(txt2);
                yönetici.add(btn2);
                yönetici.add(label3);
                yönetici.add(txt3);
                yönetici.add(btn3);
                yönetici.add(label4);
                yönetici.add(txt4);
                yönetici.add(btn4);
                yönetici.add(label5);
                yönetici.add(txt5);
                yönetici.add(btn5);
                yönetici.add(label6);
                yönetici.add(txt6);
                yönetici.add(btn6);
                yönetici.add(label7);
                yönetici.add(txt7);
                yönetici.add(btn7);
                yönetici.add(label8);
                yönetici.add(txt8);
                yönetici.add(btn8);


                yönetici.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                yönetici.setResizable(false);
                yönetici.setSize(750, 800);
                yönetici.setLayout(null);
                yönetici.setVisible(true);


            }
        });


        başlangıç.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        başlangıç.setResizable(false);
        başlangıç.setSize(750, 800);
        başlangıç.setLayout(null);
        başlangıç.add(lpanel1);
        lpanel1.add(label1_2);
        lpanel1.add(textField1_2);
        lpanel1.add(label1);
        lpanel1.add(textField1);
        lpanel1.add(label2);
        lpanel1.add(textField2);
        lpanel1.add(label3);
        lpanel1.add(textField3);
        lpanel1.add(label4);
        lpanel1.add(textField4);
        lpanel1.add(buton1);

        lpanel1.add(label5);
        lpanel1.add(textField5);
        lpanel1.add(label6);
        lpanel1.add(textField6);
        lpanel1.add(label7);
        lpanel1.add(textField7);
        lpanel1.add(label8);
        lpanel1.add(textField8);
        lpanel1.add(label9);
        lpanel1.add(textField9);
        lpanel1.add(label10);
        lpanel1.add(textField10);
        lpanel1.add(label11);
        lpanel1.add(textField11);
        lpanel1.add(label12);
        lpanel1.add(textField12);
        lpanel1.add(label13);
        lpanel1.add(textField13);
        lpanel1.add(label14);
        lpanel1.add(textField14);


        başlangıç.add(lpanel1);
        başlangıç.setVisible(true);


        Image resim1 = Toolkit.getDefaultToolkit().getImage("nf1.png").getScaledInstance(770, 500, Image.SCALE_SMOOTH);
        Image resim2 = Toolkit.getDefaultToolkit().getImage("nf2.png").getScaledInstance(770, 600, Image.SCALE_SMOOTH);

        ImageIcon n1 = new ImageIcon(resim1);
        ImageIcon n2 = new ImageIcon(resim2);
        ImageIcon n3 = new ImageIcon("nf3.png");
        JLabel nf1 = new JLabel(n1);
        JLabel nf2 = new JLabel(n2);
        JLabel nf3 = new JLabel(n3);

        nf1.setBounds(0, 150, 770, 500);
        nf2.setBounds(775, 100, 770, 600);

        JFrame nf = new JFrame("1 ve 2 Normalizasyon Form Adımları");
        nf.add(nf1);
        nf.add(nf2);

        nf.setExtendedState(JFrame.MAXIMIZED_BOTH);
        nf.setLayout(null);
        nf.setVisible(true);

        JFrame nff = new JFrame("3 Normalizasyon form");
        nff.add(nf3);

        nff.setExtendedState(JFrame.MAXIMIZED_BOTH);
        nff.setVisible(true);


    }

    public void Isletme_Resim(JButton arsaNo, String isletmeName) {

        String isletme = isletmeName + ".png";
        Image isletme0 = Toolkit.getDefaultToolkit().getImage(isletme).getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon isletme1 = new ImageIcon(isletme0);
        arsaNo.setIcon(new ImageIcon(isletme1.getImage()));

    }


}


