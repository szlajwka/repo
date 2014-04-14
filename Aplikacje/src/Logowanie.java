import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * Created by wodzu on 01.04.14.
 */

/**
 * Ekran logowania
 *
 */
public class Logowanie extends JFrame {
    JButton zaloguj;
    JTextField email;
    JTextField passwrd;
    JLabel l1;
    JLabel l2;
    public Logowanie(){
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch(Exception e){}
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(200, 200);
        setSize(200, 300);
        setLayout(new GridLayout(5, 1));
        setResizable(false);
        setTitle("Ekran logowania");
        zaloguj=new JButton("Zaloguj");
        email=new JTextField("Adres e-mail");
        passwrd=new JPasswordField("Haslo");
        l1=new JLabel("Podaj adres e-mail");
        l2=new JLabel("Podaj haslo");
        email.setToolTipText("Adres e-mail ktory byl wpisany podczas rejestracji ");
        passwrd.setToolTipText("Haslo podane podczas rejestracji");
        zaloguj.setToolTipText("Witamy w sklepie\nZacznij Zakupy !");
        email.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                email.setText("");
            }
        });
        email.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                email.setText("");
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
        });
        passwrd.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
                passwrd.setText("");
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
        });
        passwrd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                passwrd.setText("");
            }
        });
        zaloguj.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    new Sklep(email.getText());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        add(l1);
        add(email);
        add(l2);
        add(passwrd);
        add(zaloguj);
    }
}
