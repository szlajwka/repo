import javax.swing.*;

/**
 * Created by wodzu on 01.04.14.
 */

/**
 * glowny wyglad sklepu
 */
public class Sklep extends JFrame {
    String email;
    public Sklep(String email){
        this.email=email;
        setSize(1024,768);
        setTitle("Computer_Land");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        JMenuBar menuBar=new JMenuBar();
        JMenu menu=new JMenu("Opcje");
        JMenuItem item=new JMenuItem("Wylacz");
        JMenuItem item2=new JMenuItem("Wyszukaj");
        JMenuItem item3=new JMenuItem("Historia");
        menu.add(item);
        menu.addSeparator();
        menu.add(item2);
        menu.addSeparator();
        menu.add(item3);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        setVisible(true);
    }
}
