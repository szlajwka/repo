import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.*;

/**
 * Created by wodzu on 01.04.14.
 */

/**
 * Okienko startowe
 */
public class Start extends JFrame{
    private JButton register;
    private JButton logIn;
    private String Semail;
    private String Shaslo;
    private String Shaslo2;
    private String Snazwa;
    private String Sulica;
    private String Snr;
    private String Smiejscowosc;
    private String Skod;
    
    private Connection conn;
    private Statement st;
    public Start(){
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            conn=Connect.getConnection();
            st=conn.createStatement();
        }catch(Exception e){}
  
        setSize(300, 300);
        setLocation(150, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));
        setTitle("Computer_Land");
        setResizable(false);
        
        register=new JButton("Rejestruj");
        register.setToolTipText("Zarejestruj sie jesli jeszcze nie posiadasz konta");
        logIn=new JButton("Zaloguj sie");
        logIn.setToolTipText("Przejdz do ekranu logowania");
        
        register.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                final JFrame rejestracja = new JFrame();
        
                rejestracja.setDefaultCloseOperation(EXIT_ON_CLOSE);
                rejestracja.setResizable(false);
                rejestracja.setSize(250, 400);
                rejestracja.setLocation(200, 200);
                rejestracja.setLayout(new GridLayout(14, 1));
                
                JTextField nazwisko = new JTextField("");
                nazwisko.addMouseListener(new Mysz(nazwisko));
                JTextField email = new JTextField("");
                email.addMouseListener(new Mysz(email));
                JTextField passwrd1 = new JPasswordField("");
                JTextField passwrd2 = new JPasswordField("");
                JTextField ulica = new JTextField("Ulica");
                JTextField nr = new JTextField("Nr domu/mieszkania");
                JTextField miejscowosc = new JTextField("Miejscowosc");
                JTextField kod=new JTextField("Kod pocztowy");
                
                passwrd1.addMouseListener(new Mysz(passwrd1));
                passwrd2.addMouseListener(new Mysz(passwrd2));
                ulica.addMouseListener(new Mysz(ulica));
                nr.addMouseListener(new Mysz(nr));
                miejscowosc.addMouseListener(new Mysz(miejscowosc));
                kod.addMouseListener(new Mysz(kod));
                
                JButton rejestruj = new JButton("Zarejestruj");
                rejestruj.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Semail=email.getText();
                        Snazwa=nazwisko.getText();
                        Shaslo=passwrd1.getText();
                        Shaslo2=passwrd2.getText();
                        Sulica=ulica.getText();
                        Smiejscowosc=miejscowosc.getText();
                        Snr=nr.getText();
                        Skod=kod.getText();
                        if(Snr.equals("Nr domu/mieszkania")||Smiejscowosc.equals("Miejscowosc")||Sulica.equals("Ulica")||Skod.equals("Kod pocztowy")){
                            JOptionPane.showMessageDialog(null, "Nie mozesz zostawiac domyslnych wartosci", "Blad", JOptionPane.ERROR_MESSAGE);
                        }
                        else if(Skod.isEmpty()||Semail.isEmpty()||Snazwa.isEmpty()||Sulica.isEmpty()||Snr.isEmpty()||Smiejscowosc.isEmpty()){
                            JOptionPane.showMessageDialog(null, "Przynajmniej jedno z pol jest puste", "Blad", JOptionPane.ERROR_MESSAGE);
                            
                        }else if(Shaslo.length()<6){
                            JOptionPane.showMessageDialog(null, "Haslo za krotkie, podaj minimum 6 znakow", "Blad", JOptionPane.ERROR_MESSAGE);

                        }else if (!Shaslo.equals(Shaslo2)){
                            JOptionPane.showMessageDialog(null, "Hasla musza sie zgadzac", "Blad", JOptionPane.INFORMATION_MESSAGE);

                        }else{
                        try{
                            st.execute("exec addKlient '"+Snazwa+"','"+Semail+"','"+Shaslo.hashCode()+"','"+Sulica+"','"+Snr+"','"+Skod+"','"+Smiejscowosc+"'");
                            JOptionPane.showMessageDialog(null, "Rejestracja ukonczona, przejdz do logowania", "Powodzenie operacji", JOptionPane.INFORMATION_MESSAGE);
                            rejestracja.dispose();
                            conn.close();
                            new Logowanie().setVisible(true);
                        }catch(Exception e1){
                            JOptionPane.showMessageDialog(null, "adres email juz wykorzystany", "blad", JOptionPane.INFORMATION_MESSAGE);
                            passwrd1.setText("");
                            passwrd2.setText("");
                            nazwisko.setText("");
                            ulica.setText("");
                            kod.setText("");
                            email.setText("");
                            miejscowosc.setText("");
                            nr.setText("");
                        }
                        
                        }
                    }
                });
                
                
                
                JLabel l1 = new JLabel("Podaj nazwisko/nazwe firmy");
                JLabel l2 = new JLabel("podaj adres email, bedzie to twoj identyfikator");
                JLabel l3 = new JLabel("podaj haslo");
                JLabel l4 = new JLabel("Powtorz haslo");
                JLabel l5 = new JLabel("Adres do wysylki");
                
                l1.setOpaque(true);
                l2.setOpaque(true);
                l3.setOpaque(true);
                l4.setOpaque(true);
                l5.setOpaque(true);
                
                l1.setBackground(Color.YELLOW);
                l2.setBackground(Color.YELLOW);
                l3.setBackground(Color.YELLOW);
                l4.setBackground(Color.YELLOW);
                l5.setBackground(Color.YELLOW);
                
                ulica.setToolTipText("Podaj ulice na ktora maja byc wysylane paczki");
                nr.setToolTipText("Podaj nr domu/mieszkania");
                passwrd1.setToolTipText("Podaj swoje haslo");
                passwrd2.setToolTipText("Podaj jeszcze raz haslo");
                
                rejestracja.add(l1);
                rejestracja.add(nazwisko);
                rejestracja.add(l2);
                rejestracja.add(email);
                rejestracja.add(l3);
                rejestracja.add(passwrd1);
                rejestracja.add(l4);
                rejestracja.add(passwrd2);
                rejestracja.add(l5);
                rejestracja.add(ulica);
                rejestracja.add(nr);
                rejestracja.add(kod);
                rejestracja.add(miejscowosc);
                rejestracja.add(rejestruj);
                rejestracja.setVisible(true);
            }
        });
        logIn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Logowanie().setVisible(true);
            }
        });
        //logIn.setBackground(new Color(255, 40, 67));
        add(logIn);
        JPanel p1=new JPanel();
        p1.setBackground(new Color(255, 251, 66));
        JPanel p2=new JPanel();
        p2.setBackground(new Color(255,255,255));
        add(p1);
        add(register);
        //add(p2);

    }
    public static void main(String[]args){
        Start wg=new Start();
        wg.setVisible(true);
    }
}

/**
 * Klasa do reagowania na nacisniecie myszy na komponencie
 */
class Mysz implements MouseListener{
    JTextField text;
    public Mysz(JTextField text){
        this.text=text;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        text.setText("");
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}