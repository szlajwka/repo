import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

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
    private Connection conn;
    private Statement st;
    public Logowanie(){
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            conn=Connect.getConnection();
            st=conn.createStatement();
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
        
        email.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                email.setText("");
            }

        });
        
        passwrd.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {
                passwrd.setText("");
            }
        });
        zaloguj.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String haslo="";
                String mail="";
                haslo=passwrd.getText();
                mail=email.getText();
                
                if(haslo.isEmpty()||mail.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Podaj haslo i email", "Blad", JOptionPane.ERROR_MESSAGE);

                }else{
                    try {
                        
                        String query="exec checklogin '"+mail+"','"+haslo.hashCode()+"'";
                        ResultSet rs=st.executeQuery(query);
                        rs.next();
                        String result=rs.getString("Id");
                        conn.close();
                        dispose();
                        new Klient();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Nieprawidlowy login lub haslo", "Blad", JOptionPane.ERROR_MESSAGE);
                        email.setText("");
                        passwrd.setText("");
                    } 
                
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
