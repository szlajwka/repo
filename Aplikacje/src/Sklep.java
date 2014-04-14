import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

/**
 * Created by wodzu on 01.04.14.
 */

/**
 * glowny wyglad sklepu
 */
public class Sklep extends JFrame {
    String email;
    public Sklep(String email) throws IOException {
        this.email=email;
        setSize(1024, 768);
        setTitle("Computer_Land");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        JPanel panel=new JPanel();
        panel.setBackground(new Color(191, 199, 255));
        JMenuBar menuBar=new JMenuBar();
        JMenu menu=new JMenu("Opcje");
        JMenuItem item=new JMenuItem("Wylacz");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Zamykanie programu", "Koniec zakupow,zapraszamy ponownie", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        });

        JMenu menu1=new JMenu("Koszyk");
        JMenu menu2=new JMenu("Wyloguj");
        menu2.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Logowanie().setVisible(true);

            }
        });
        menu2.setToolTipText("Wylogowanie bierzacego klienta i przeniesienie do ekranu logowania");
        menu1.setToolTipText("Twoj koszyk");
        menu1.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                final JFrame koszyk=new JFrame();
                String []twojeZakupy={"przedmiot1","przedmiot2","przedmiot3","przedmiot4","przedmiot5","przedmiot6","przedmiot7","przedmiot8"};
                final JList list=new JList(twojeZakupy);
                list.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.print(list.getSelectedValue());
                        final JFrame szczegoly=new JFrame();
                        szczegoly.setLocation(300,300);
                        szczegoly.setSize(300,200);
                        szczegoly.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                        szczegoly.setLayout(new GridLayout(1,2));
                        JLabel tekst=new JLabel();
                        tekst.setText("Nazwa "+list.getSelectedValue()+" Cena");
                        JButton usun=new JButton("Usun");
                        usun.addActionListener(new ActionListener() {
                           @Override
                               public void actionPerformed(ActionEvent e) {
                                 JOptionPane.showMessageDialog(null, "Usunieto z koszyka", "Usun", JOptionPane.INFORMATION_MESSAGE);
                                    szczegoly.dispose();
                                    }
                                               });
                        szczegoly.add(tekst);
                        szczegoly.add(usun);
                        szczegoly.setResizable(false);
                        szczegoly.setVisible(true);
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
                JScrollPane scroll=new JScrollPane(list);

                koszyk.setLocation(300,300);
                koszyk.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                koszyk.setSize(400, 400);
                koszyk.setLayout(new GridLayout(3, 1));
                JButton finalizuj=new JButton("Finalizuj zakupy");
                finalizuj.setSize(400,100);
                koszyk.setResizable(false);
                koszyk.setTitle("Aktualny koszyk");
                koszyk.add(scroll);
                koszyk.add(new JLabel(""));
                koszyk.add(finalizuj);
                finalizuj.addActionListener(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        koszyk.dispose();
                        final JFrame transakcja=new JFrame();
                        transakcja.setLocation(300, 300);
                        transakcja.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                        transakcja.setSize(400, 400);
                        transakcja.setResizable(false);
                        transakcja.setLayout(new GridLayout(7, 1));
                        JButton daneWysylka =new JButton("Twoje Dane");
                        daneWysylka.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JFrame dane=new JFrame();
                                dane.setTitle("Twoje Dane");
                                dane.setLocation(300,200);
                                dane.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                                dane.setLayout(new GridLayout(1,1));
                                dane.setSize(100,300);
                                dane.setResizable(false);
                                dane.setVisible(true);
                            }
                        });
                        JLabel koszt=new JLabel("Koszt zakupu");
                        JLabel wysylka=new JLabel("Koszt wysylki");
                        JLabel rabat=new JLabel("Rabat");
                        JLabel waga=new JLabel("Waga paczki");
                        JLabel lacznyKoszt=new JLabel("Laczny Koszt");
                        JButton zakoncz=new JButton("Zakoncz");
                        zakoncz.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JOptionPane.showMessageDialog(null, "Zamowienie zostanie zrealizowane \n oczekuj na paczke", "Zakup Pomyslny", JOptionPane.INFORMATION_MESSAGE);
                                transakcja.dispose();
                            }
                        });
                        transakcja.add(daneWysylka);
                        transakcja.add(koszt);
                        transakcja.add(wysylka);
                        transakcja.add(rabat);
                        transakcja.add(waga);
                        transakcja.add(lacznyKoszt);
                        transakcja.add(zakoncz);
                        transakcja.setVisible(true);

                    }
                });
                koszyk.setVisible(true);
            }
        });
        JMenuItem item2=new JMenuItem("Wyszukaj");
        JMenuItem item3=new JMenuItem("Historia");
        item2.setIcon(UIManager.getIcon("Tree.openIcon"));
        item3.setIcon(UIManager.getIcon("OptionPane.errorIcon"));
        menu1.setAlignmentX(100);
        menu.setIcon(UIManager.getIcon("RadioButton.icon"));
        menu1.setIcon(UIManager.getIcon("RadioButton.icon"));
        menu2.setIcon(UIManager.getIcon("RadioButton.icon"));
        menu.add(item);
        menu.addSeparator();
        menu.add(item2);
        menu.addSeparator();
        menu.add(item3);
        menuBar.add(menu);
        menuBar.add(menu1);
        menuBar.add(menu2);
        setLayout(new GridLayout(1,1));
        add(panel);
        setJMenuBar(menuBar);
        setVisible(true);
    }
}
