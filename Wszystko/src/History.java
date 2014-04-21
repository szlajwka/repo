
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Wodzu
 */
public class History extends JFrame{
    private Connection connection;
    private Statement statement;
    private JList list;
    private String id;
    
    public History(Connection connection, String id) throws SQLException{
        this.connection=connection;
        statement=connection.createStatement();
        this.id=id;
        init();
    }
    
    void init() throws SQLException{
        setTitle("Historia zamowien");
        setLocation(200,200);
        setSize(300,300);
        setResizable(false);
        setLayout(new BorderLayout(5,5));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        String[]columns={"id","data_zamowienia"};
        List l=query("exec getZamowienie "+id,columns);
        Object[]values=l.toArray();
        
        list=new JList(values);
        list.addMouseListener(new MouseAdapter(){
             public void mouseClicked(MouseEvent e) {
                 String item=(String) list.getSelectedValue();
                 try {
                     createFrame(item);
                 } catch (SQLException ex) {
                     Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
                 } catch(NullPointerException ex){
                     JOptionPane.showMessageDialog(null, "Nie wykonales zadnych zakupow", "", JOptionPane.INFORMATION_MESSAGE);
                 }
             }
        });
        add(list,BorderLayout.CENTER);
    }
    private List query(String command, String[]columns) throws SQLException{
        ResultSet rs=statement.executeQuery(command);
        List<String> list=new ArrayList<String>();
        while(rs.next()){
            String item="";
            int i=0;
            for(String column:columns){
                item+=column+": "+rs.getString(column)+"; ";
                i++;
            }
            list.add(item);
        }
        return list;
    }
    private Object[][] getSzczegoly(String id)throws SQLException{
        ResultSet rs=statement.executeQuery("exec getSzczegoly"+id);
        int counter=0;
        while(rs.next()){
            counter++;
        }
        rs=statement.executeQuery("exec getSzczegoly"+id);
        Object[][]data=new Object[counter][3];
        int i=0;
        while(rs.next()){
            data[i][0]=rs.getString(1);
            data[i][1]=rs.getString(2);
            data[i][2]=rs.getString(3);
            i++;
        }
        return data;
    }
    private void createFrame(String item) throws SQLException{
        String[]temp=item.split(";");
        temp=temp[0].split(":");
        Object[]columnNames ={"Model","Typ","Producent"};
        Object[][]data=getSzczegoly(temp[1]);
        String[] koszt={"koszt_calkowity"};
        String calkowity=(String)query("exec getKoszt"+temp[1],koszt).get(0);
        JTable table=new JTable(data,columnNames);
        JFrame frame=new JFrame("Szczegoly");
        JPanel panel=new JPanel(new BorderLayout());
        JScrollPane scroll=new JScrollPane(table);
        
        panel.add(new JLabel("Koszt zamowienia:  "+calkowity),BorderLayout.NORTH);
        panel.add(scroll,BorderLayout.CENTER);
        
        frame.setSize(500,300);
        frame.setLocation(200,200);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true);
       

    }

}
