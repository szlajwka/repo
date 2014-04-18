import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
                Object[][]data=null;
                Statement st=null;
                ResultSet rs;
                try {
                    Connection conn=Connect.getConnection();
                    System.out.println("connected");
                    st=conn.createStatement();
                    rs=st.executeQuery("exec wyczerpane");
                    int counter=0;
                    while(rs.next()){
                        counter++;
                    }
                    rs=st.executeQuery("exec wyczerpane");
                    data=new Object[counter][3];
                    int i=0;
                    while(rs.next()){
                        data[i][0]=rs.getString(1);
                        data[i][1]=rs.getString(2);
                        data[i][2]=rs.getString(3);
                        i++;
                    }
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                wyczerpane.setLocation(10,200);
                wyczerpane.setSize(200, 300);
                wyczerpane.setLayout(new GridLayout(1, 1));
                wyczerpane.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                wyczerpane.setResizable(false);
                wyczerpane.setTitle("Braki w magazynie");
                wyczerpane.setVisible(true);
                String[]columns={"Id","Typ","Cena"};
                JTable table1=new JTable(data,columns);
                JScrollPane scrollPane1 = new JScrollPane(table1);
                table1.setFillsViewportHeight(true);
                wyczerpane.add(scrollPane1);
                adminPanel.setLocation(250,150);
                adminPanel.setDefaultCloseOperation(EXIT_ON_CLOSE);
                adminPanel.setSize(500,500);
                adminPanel.setLayout(new GridLayout(1,1));
                adminPanel.setResizable(false);
                adminPanel.setVisible(true);
                String[]columnnames={"Id","NazwaHurtowni","Cena","Typ"};
                Object[][]data2=null;
                try {
                    rs=st.executeQuery("Select * from Hurtownia");
                    int counter=0;
                    while(rs.next()){
                        counter++;
                    }
                    rs=st.executeQuery("Select * from Hurtownia");
                    data2=new Object[counter][4];
                    int i=0;
                    while(rs.next()){
                        data2[i][0]=rs.getString(2);
                        data2[i][1]=rs.getString(6);
                        data2[i][2]=rs.getString(3);
                        data2[i][3]=rs.getString(5);
                        i++;
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                final JTable table=new JTable(data2,columnnames);
                JScrollPane scrollPane = new JScrollPane(table);
                table.setFillsViewportHeight(true);
                table.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        final int row = table.getSelectedRow();
                        final JFrame f1 = new JFrame();
                        f1.setLayout(new GridLayout(1, 1));
                        f1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                        f1.setLocation(200, 200);
                        f1.setSize(200, 100);
                        f1.setResizable(false);
                        final JTextField tf = new JTextField("Podaj ilosc");
                        tf.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                tf.setText("");
                            }
                        });
                        tf.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int count = 0;
                                try {
                                    count = Integer.parseInt(tf.getText());
                                    if (count <= 0) {
                                        JOptionPane.showMessageDialog(null, "Nieprawidlowa Liczba", "Blad", JOptionPane.ERROR_MESSAGE);

                                    } else {
                                        String id = (String) table.getValueAt(row, 0);
                                        String tabela=(String)table.getValueAt(row,3);
                                        System.out.println(tabela);
                                        Connection con=Connect.getConnection();
                                        Statement st=con.createStatement();
                                        st.execute("exec dodaj "+id+","+count+","+tabela);
                                        JOptionPane.showMessageDialog(null, "Dodano " + count + " sztuk", "Powodzenie operacji", JOptionPane.INFORMATION_MESSAGE);
                                        f1.dispose();
                                    }
                                } catch (Exception e1) {
                                    JOptionPane.showMessageDialog(null, "PodajLiczbe", "Blad", JOptionPane.ERROR_MESSAGE);
                                    e1.printStackTrace();
                                }

                            }
                        });
                        f1.add(tf);
                        f1.setVisible(true);

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
