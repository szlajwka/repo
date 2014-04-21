
import java.awt.BorderLayout;

import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;


public class GUI {

	private JFrame frame;
	private JTable tableMain;
	private JTable tableShopcart;
	private JButton btnSwichHDD; //dyski twarde
	private JButton btnSwichODD; //napedy
	private JButton btnSwichMonitor; //monitory
	private JButton btnSwichCPU; //procesory
	private JButton btnSwichGPU; //karty graficzne
	private JButton btnSwichMotherboard; //plyty glowne
	private JButton btnSwichIO; //peryferia
	private JButton btnSwichRAM; //ram
	private JButton btnSwichZestaw;
	
	private DefaultTableModel tableMainModel;
	private DefaultTableModel tableShopcartModel;
	
	private AddToShopcartButtonsListener addToShopcartButtonsListener;
	private DeleteFromShopcartButtonsListener deleteFromShopcartButtonsListener;
	private ToPayButtonListener toPayButtonListener;
	
	private String idKlienta;
	
	
	private SwitchButtonsListener sb_listner;
	private JScrollPane scrollPane_1;
	private JPanel panel_tableShopcartCenter;
	private JScrollPane scrollPane;
	private JButton btn_toPay;
	private JPanel panel_tableShopCartNorth;

        

	/**
	 * Create the application.
	 */
	public GUI(String id) {
		this.idKlienta=id;
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
			e.printStackTrace();
		}
		
		initialize();
		addListenersAndModels();
	}
	
	
	public void addListenersAndModels(){
		sb_listner = new SwitchButtonsListener();
		addToShopcartButtonsListener = new AddToShopcartButtonsListener();
		deleteFromShopcartButtonsListener = new DeleteFromShopcartButtonsListener();
		toPayButtonListener = new ToPayButtonListener();
		
		btnSwichCPU.addActionListener(sb_listner);
		btnSwichHDD.addActionListener(sb_listner);
		btnSwichODD.addActionListener(sb_listner);
		btnSwichMonitor.addActionListener(sb_listner);
		btnSwichGPU.addActionListener(sb_listner);
		btnSwichMotherboard.addActionListener(sb_listner);
		btnSwichIO.addActionListener(sb_listner);
		btnSwichRAM.addActionListener(sb_listner);
                btnSwichZestaw.addActionListener(sb_listner);
		btn_toPay.addActionListener(toPayButtonListener);
		
		
		tableMainModel = new DefaultTableModel(0,0){
			/**
			 * Domyslnie w JTable kazda komorke mozna edytowac (zachowuja sie one ja JTextFieldy tylko troche bardziej topornie).
			 * Ta metoda ustalamy, ze tylko przedostatnie kolumny mozna edytowac (przeznaczona na ilosc i z przyciskiem)
			 */
		    @Override
			public boolean isCellEditable(int row, int column)
		    {
		    	if(column>=getColumnCount()-2) return true;
		        
		    	return false;
		    }
		};
		tableMain.setModel(tableMainModel);
		
		tableShopcartModel = new DefaultTableModel(0,0);
		tableShopcart.setModel(tableShopcartModel);
		
		
	}
	
	public void setDataMainTable(ArrayList<String[]> al, String[] header){
		
		/*
		 * Usuwanie zawartosci tabeli
		 */
		if (tableMainModel .getRowCount() > 0) {
		    for (int i = tableMainModel.getRowCount() - 1; i > -1; i--) {
		    	tableMainModel.removeRow(i);
		    }
		}
		Vector<Object> data = null;
		
		/*
		 * Zwiekszenie naglowka o dwa pola i dodanie nazw kolumn "Ilosc" oraz "Dodaj"
		 */
		header = Arrays.copyOf(header, header.length + 2);
		header[header.length - 2] = "Ilosc";
	    header[header.length - 1] = "Dodaj";
	    
	    
		tableMainModel.setColumnIdentifiers(header);
		
		/*
		 * Wypelnienie tabeli wartosciami z arrayListy
		 */
		for(String[] s: al){
			 data = new Vector<Object>();
			 for(int i=0; i< s.length;i++){
				 data.add(s[i]);
			 }
			 data.add("0");
			 data.add("+");
			 tableMainModel.addRow(data);
		}
		/**
		 * Klasa z neta implementujaca przyciski w tabeli
		 */
		new ButtonColumn(tableMain, addToShopcartButtonsListener.getAbstractAction(),(header.length - 1));
	}
	
	public void setDataShopcartTable(ArrayList<String[]> al, String[] header){
		
		if (tableShopcartModel .getRowCount() > 0) {
		    for (int i = tableShopcartModel.getRowCount() - 1; i > -1; i--) {
		    	tableShopcartModel.removeRow(i);
		    }
		}
		
		header = Arrays.copyOf(header, header.length + 1);
	    header[header.length - 1] = "Usun";
		
		
		Vector<Object> data = null;
		tableShopcartModel.setColumnIdentifiers(header);
		for(String[] s: al){
			 data = new Vector<Object>();
			 for(int i=0; i< s.length;i++){
				 data.add(s[i]);
				
			 }
			 data.add("-");
			 tableShopcartModel.addRow(data);
			 
		}
		new ButtonColumn(tableShopcart, deleteFromShopcartButtonsListener.getAbstractAction(),(header.length - 1));

	}
	
	
	public void showErrorMessage(String message) {
			JOptionPane.showMessageDialog(frame, message, "Blad!", JOptionPane.ERROR_MESSAGE);
	}
	
	public void showInfoMessage(String message) {
		JOptionPane.showMessageDialog(frame, message, "Informacja", JOptionPane.INFORMATION_MESSAGE);
	}

	
	
	public void addObservers(Observer o){
		sb_listner.addObserver(o);
		addToShopcartButtonsListener.addObserver(o);
		deleteFromShopcartButtonsListener.addObserver(o);
	}
	
	
	public void setVisible(boolean b){
		frame.setVisible(b);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Computer_Land");
                frame.setResizable(false);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_main = new JPanel();
		frame.getContentPane().add(panel_main, BorderLayout.CENTER);
		GridBagLayout gbl_panel_main = new GridBagLayout();
		gbl_panel_main.columnWidths = new int[]{0, 0};
		gbl_panel_main.rowHeights = new int[]{0, 0, 0, 0, 0};
		//gbl_panel_main.rowHeights = new int[]{0, 600/400, 600/400, 600/400, 0};
		gbl_panel_main.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_main.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		panel_main.setLayout(gbl_panel_main);
		
		JPanel panel_tableMain = new JPanel();
		GridBagConstraints gbc_panel_tableMain = new GridBagConstraints();
		gbc_panel_tableMain.gridheight = 3;
		gbc_panel_tableMain.insets = new Insets(0, 0, 5, 0);
		gbc_panel_tableMain.fill = GridBagConstraints.BOTH;
		gbc_panel_tableMain.gridx = 0;
		gbc_panel_tableMain.gridy = 0;
		panel_main.add(panel_tableMain, gbc_panel_tableMain);
		panel_tableMain.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_switchButtons = new JPanel();
		panel_tableMain.add(panel_switchButtons, BorderLayout.NORTH);
		
		btnSwichHDD = new JButton("Dyski Twarde");
		panel_switchButtons.add(btnSwichHDD);
		
		btnSwichCPU = new JButton("Procesory");
		panel_switchButtons.add(btnSwichCPU);
		
		btnSwichMotherboard = new JButton("Plyty Glowne");
		panel_switchButtons.add(btnSwichMotherboard);
		
		btnSwichODD = new JButton("Napedy");
		panel_switchButtons.add(btnSwichODD);
		
		btnSwichRAM = new JButton("RAM");
		panel_switchButtons.add(btnSwichRAM);
		
		btnSwichGPU = new JButton("Grafika");
		panel_switchButtons.add(btnSwichGPU);
		
		btnSwichMonitor = new JButton("Monitor");
		panel_switchButtons.add(btnSwichMonitor);
		
		btnSwichIO = new JButton("Peryferia");
		panel_switchButtons.add(btnSwichIO);
		
                btnSwichZestaw=new JButton("Zestawy");
                panel_switchButtons.add(btnSwichZestaw);
                
		scrollPane_1 = new JScrollPane();
		panel_tableMain.add(scrollPane_1, BorderLayout.CENTER);
		
		tableMain = new JTable();
		scrollPane_1.setViewportView(tableMain);
		
		JPanel panel_tableShopcart = new JPanel();
		GridBagConstraints gbc_panel_tableShopcart = new GridBagConstraints();
		gbc_panel_tableShopcart.fill = GridBagConstraints.BOTH;
		gbc_panel_tableShopcart.gridx = 0;
		gbc_panel_tableShopcart.gridy = 3;
		panel_main.add(panel_tableShopcart, gbc_panel_tableShopcart);
		panel_tableShopcart.setLayout(new BorderLayout(0, 0));
		
		panel_tableShopcartCenter = new JPanel();
		panel_tableShopcart.add(panel_tableShopcartCenter, BorderLayout.CENTER);
		panel_tableShopcartCenter.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panel_tableShopcartCenter.add(scrollPane);
		
		tableShopcart = new JTable();
		scrollPane.setViewportView(tableShopcart);
		
		panel_tableShopCartNorth = new JPanel();
		panel_tableShopcart.add(panel_tableShopCartNorth, BorderLayout.SOUTH);
		panel_tableShopCartNorth.setLayout(new BorderLayout(0, 0));
		
		btn_toPay = new JButton("Do kasy");
		panel_tableShopCartNorth.add(btn_toPay, BorderLayout.EAST);
                
                JMenuBar menuBar=new JMenuBar();
                JMenu historia=new JMenu("Historia Zamowien");
                JMenu wyloguj=new JMenu("Wyloguj");
                JMenu mojedane=new JMenu("Moje dane");
                        
                historia.addMouseListener(new MouseAdapter(){
                   public void mouseClicked(MouseEvent e) {
                       try{
                            new History(Connect.getConnection(),idKlienta).setVisible(true);
                        }catch(Exception e1){
                            e1.printStackTrace();
                        };
                   }  
                });
                
                wyloguj.addMouseListener(new MouseAdapter(){
                     public void mouseClicked(MouseEvent e) {
                         frame.dispose();
                         try{
                             new Start().setVisible(true);
                         }catch(Exception e1){
                             
                         }
                    }
                });
                
                mojedane.addMouseListener(new MouseAdapter(){
                    public void mouseClicked(MouseEvent e) {
                        JFrame dane=new JFrame("Moje dane");
                        dane.setLocation(300,300);
                        dane.setSize(300,150);
                        dane.setResizable(false);
                        dane.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                        dane.setLayout(new GridLayout(1,1));
                        
                        try {
                            Connection conn=Connect.getConnection();
                            Statement st=conn.createStatement();
                            ResultSet rs=st.executeQuery("exec getKlient "+idKlienta);
                            rs.next();
                            Vector<String> vt=new Vector<String>();
                            
                            
                            vt.add(rs.getString("nazwa"));
                            vt.add(rs.getString("email"));
                            vt.add(rs.getString("ulica"));
                            vt.add(rs.getString("nr_domu"));
                            vt.add(rs.getString("kod_pocztowy"));
                            vt.add(rs.getString("miejscowosc"));
                            vt.add("ID: "+rs.getString("id"));
                            
                            JList list=new JList(vt);
                            list.setFont(Font.getFont("ComicSansMS"));
                            list.setOpaque(true);
                            list.setBackground(Color.getHSBColor(51,255,255));
                            
                            DefaultListCellRenderer renderer =  (DefaultListCellRenderer)list.getCellRenderer();  
                            renderer.setHorizontalAlignment(JLabel.CENTER);  
                            
                            dane.add(list);
                            conn.close();
                            dane.setVisible(true);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
                    
                });
                
                menuBar.add(historia);
                menuBar.add(mojedane);
                menuBar.add(wyloguj);
                frame.setJMenuBar(menuBar);
              
	}
	
	
	/**
	 * Klasa obslugujaca nacisniecia przyciskow slozacych zmianie kategorii produktu.
	 * @author Piotrek
	 *
	 */
	private class SwitchButtonsListener extends Observable implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String compName = "";
			Object source = arg0.getSource();

			if(source.equals(btnSwichCPU)) compName = "CPU";
			else if(source.equals(btnSwichGPU)) compName = "GPU";
			else if(source.equals(btnSwichHDD)) compName = "HDD";
			else if(source.equals(btnSwichIO)) compName = "IO";
			else if(source.equals(btnSwichMonitor)) compName = "Monitor";
			else if(source.equals(btnSwichODD)) compName = "ODD";
			else if(source.equals(btnSwichRAM))compName = "RAM";
			else if(source.equals(btnSwichMotherboard)) compName = "Motherboard";
                        else if(source.equals(btnSwichZestaw))compName="Zestawy";
			JButton b = (JButton) source;
			
			btnSwichHDD.getModel().setPressed(false);
			btnSwichODD.getModel().setPressed(false);
			btnSwichMonitor.getModel().setPressed(false);
			btnSwichCPU.getModel().setPressed(false);
			btnSwichGPU.getModel().setPressed(false);
			btnSwichMotherboard.getModel().setPressed(false);
			btnSwichIO.getModel().setPressed(false);
			btnSwichRAM.getModel().setPressed(false);
			btnSwichZestaw.getModel().setPressed(false);
			
			b.getModel().setPressed(true);

			
			setChanged();
			notifyObservers(compName);
		} 
		
	}
	
	
	
	
	private class ToPayButtonListener extends Observable implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			setChanged();
			notifyObservers();
			
		}
		
	}
	
	/**
	 * Klasa odpowiadajaca za obsluge przycisku "+" w tabeli z asortymentem. Musialem opakowac ta klase poniewaz potrzebuje widczonego observable do komunikacji z klasa "Klinet"
	 * @author Piotrek
	 *
	 */
	private class AddToShopcartButtonsListener extends Observable{
		
		AbstractAction aa = new AbstractAction(){
		    public void actionPerformed(ActionEvent e)
		    {
		        JTable table = (JTable)e.getSource();
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		      //  ((DefaultTableModel)table.getModel()).removeRow(modelRow);
		        int id = Integer.parseInt((String) (table.getValueAt(modelRow, 0)));
		        int quantity =  Integer.parseInt((String) (table.getValueAt(modelRow, tableMainModel.getColumnCount()-2)));
		        
				setChanged();
				notifyObservers(id+" "+quantity);

		      

		   }
		};
		
		public AbstractAction getAbstractAction(){
			return aa;
		}
	}
	/**
	 * Klasa obslugujaca przycisk "-" w tabeli z koszykiem sluzacy do usuwania elementu z koszyka. 
	 * @author Piotrek
	 *
	 */
	private class DeleteFromShopcartButtonsListener extends Observable{
		
		AbstractAction aa = new AbstractAction(){
		    public void actionPerformed(ActionEvent e)
		    {
		        JTable table = (JTable)e.getSource();
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		      //  ((DefaultTableModel)table.getModel()).removeRow(modelRow);
		        int id = Integer.parseInt((String) (table.getValueAt(modelRow, 0)));
		        
				setChanged();
				notifyObservers(id);


		   }
		};
		
		public AbstractAction getAbstractAction(){
			return aa;
		}
	}
	
	public AbstractAction getAAAddToShopcartButtonsListener(){
		return addToShopcartButtonsListener.getAbstractAction();
	}
	
	public AddToShopcartButtonsListener getAddToShopcartButtonsListener(){
		return addToShopcartButtonsListener;
	}



	public SwitchButtonsListener getSb_listner() {
		return sb_listner;
	}

	public DeleteFromShopcartButtonsListener getDeleteFromShopcartButtonsListener() {
		return deleteFromShopcartButtonsListener;
	}


	public JButton getBtn_toPay() {
		return btn_toPay;
	}

	public ToPayButtonListener getToPayButtonListener() {
		return toPayButtonListener;
	}
}
