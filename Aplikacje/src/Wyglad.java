import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by wodzu on 01.04.14.
 */

/**
 * Okienko startowe
 */
public class Wyglad extends JFrame{
    JButton register;
    JButton logIn;
    public Wyglad(){
        setSize(300, 300);
        setLocation(150, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));
        setTitle("Computer_Land");
        setResizable(false);
        register=new JButton("Rejestruj");
        register.setToolTipText("Zarejestruj sie jesli jeszcze nie posiadasz konta");
        logIn=new JButton("Zaloguj sie");
        logIn.setToolTipText("Przejdz do ekranu logowania");
        register.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                final JFrame rejestracja=new JFrame();
                rejestracja.setDefaultCloseOperation(EXIT_ON_CLOSE);
                rejestracja.setResizable(false);
                rejestracja.setSize(250,400);
                rejestracja.setLocation(200,200);
                rejestracja.setLayout(new GridLayout(13,1));
                JButton rejestruj=new JButton("Zarejestruj");
                rejestruj.addActionListener(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(null, "Rejestracja ukonczona, przejdz do logowania", "Powodzenie operacji", JOptionPane.INFORMATION_MESSAGE);
                        rejestracja.dispose();
                        new Logowanie().setVisible(true);
                    }
                });
                JTextField nazwisko=new JTextField("Nazwisko/Firma");
                nazwisko.addMouseListener(new Mysz(nazwisko));
                JTextField email=new JTextField("Adres email");
                email.addMouseListener(new Mysz(email));
                JTextField passwrd1=new JPasswordField("Haslo");
                JTextField passwrd2=new JPasswordField("Powtorz Haslo");
                JTextField ulica=new JTextField("Ulica");
                JTextField nr=new JTextField("Nr domu/mieszkania");
                JTextField miejscowosc=new JTextField("Miejscowosc");
                passwrd1.addMouseListener(new Mysz(passwrd1));
                passwrd2.addMouseListener(new Mysz(passwrd2));
                ulica.addMouseListener(new Mysz(ulica));
                nr.addMouseListener(new Mysz(nr));
                miejscowosc.addMouseListener(new Mysz(miejscowosc));
                JLabel l1=new JLabel("Podaj nazwisko/nazwe firmy");
                JLabel l2=new JLabel("podaj adres email, bedzie to twoj identyfikator");
                JLabel l3=new JLabel("podaj haslo");
                JLabel l4=new JLabel("Powtorz haslo");
                JLabel l5=new JLabel("Adres do wysylki");
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
        add(logIn);
        add(new JLabel());
        add(register);
        add(new JLabel());

    }
    public static void main(String[]args){
        Wyglad wg=new Wyglad();
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