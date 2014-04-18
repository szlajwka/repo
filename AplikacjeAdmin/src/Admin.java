import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by wodzu on 18.04.14.
 */
public class Admin extends JFrame{
    JTextField tf1;
    JTextField tf2;
    public Admin(){
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch(Exception e){}
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(200, 200);
        setSize(300, 100);
        setLayout(new GridLayout(2, 1));
        setResizable(false);
        tf1=new JFormattedTextField("Podaj email");
        tf1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tf1.setText("");
            }
        });
        tf2=new JPasswordField();
        tf2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tf2.setText("");
            }
        });
        tf2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame adminPanel=new JFrame();
                JFrame wyczerpane=new JFrame();
                wyczerpane.setLocation(10,200);
                wyczerpane.setSize(200, 300);
                wyczerpane.setLayout(new GridLayout(1, 1));
                wyczerpane.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                wyczerpane.setResizable(false);
                wyczerpane.setTitle("Braki w magazynie");
                wyczerpane.setVisible(true);
                String[]columns={"Id","Typ","Nazwa"};
                Object[][]data1={{"1","Typ1","Nazwa1"},{"2","Typ2","Nazwa2"},{"3","Typ3","Nazwa3"},{"4","Typ4","Nazwa4"}};
                JTable table1=new JTable(data1,columns);
                JScrollPane scrollPane1 = new JScrollPane(table1);
                table1.setFillsViewportHeight(true);
                wyczerpane.add(scrollPane1);
                adminPanel.setLocation(250,150);
                adminPanel.setDefaultCloseOperation(EXIT_ON_CLOSE);
                adminPanel.setSize(500,500);
                adminPanel.setLayout(new GridLayout(1,1));
                adminPanel.setResizable(false);
                adminPanel.setVisible(true);
                String[]columnnames={"Id","NazwaHurtowni","NazwaProduktu","Cena","Typ"};
                Object [][]data={{"1","Hurtownia1","Produkt1","100","Typ1"},{"2","Hurtownia2","Produkt2","200","Typ2"},{"3","Hurtownia3","Produkt3","300","Typ3"}
                ,{"1","Hurtownia1","Produkt1","100","Typ3"},{"4","Hurtownia4","Produkt4","400","Typ4"}};
                final JTable table=new JTable(data,columnnames);
                JScrollPane scrollPane = new JScrollPane(table);
                table.setFillsViewportHeight(true);
                table.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int row=table.getSelectedRow();
                        final JFrame f1=new JFrame();
                        f1.setLayout(new GridLayout(1,1));
                        f1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                        f1.setLocation(200, 200);
                        f1.setSize(200, 100);
                        f1.setResizable(false);
                        final JTextField tf=new JTextField("Podaj ilosc");
                        tf.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                tf.setText("");
                            }
                        });
                        tf.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int count=0;
                                try {
                                    count = Integer.parseInt(tf.getText());
                                    if(count<=0){
                                        JOptionPane.showMessageDialog(null, "Nieprawidlowa Liczba", "Blad", JOptionPane.ERROR_MESSAGE);

                                    }else{
                                    JOptionPane.showMessageDialog(null, "Dodano "+count+" sztuk", "Powodzenie operacji", JOptionPane.INFORMATION_MESSAGE);
                                    f1.dispose();}
                                }catch(Exception e1){
                                    JOptionPane.showMessageDialog(null, "PodajLiczbe", "Blad", JOptionPane.ERROR_MESSAGE);

                                }

                            }
                        });
                        f1.add(tf);
                        f1.setVisible(true);

                        System.out.println(table.getValueAt(row,2));
                    }

                });
                adminPanel.add(scrollPane);
                wyczerpane.setVisible(true);
            }
        });
        add(tf1);
        add(tf2);

    }
    public static void main(String[]args){
        Admin ad=new Admin();
        ad.setVisible(true);
    }
}
