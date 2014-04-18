import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

import java.awt.GridBagLayout;

import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;


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
	
	
	private DefaultTableModel tableMainModel;
	private DefaultTableModel tableShopcartModel;
	
	
	
	
	private SwitchButtonsListener sb_listner;
	private JScrollPane scrollPane_1;
	private JPanel panel;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		
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
		
		btnSwichCPU.addActionListener(sb_listner);
		btnSwichHDD.addActionListener(sb_listner);
		btnSwichODD.addActionListener(sb_listner);
		btnSwichMonitor.addActionListener(sb_listner);
		btnSwichGPU.addActionListener(sb_listner);
		btnSwichMotherboard.addActionListener(sb_listner);
		btnSwichIO.addActionListener(sb_listner);
		btnSwichRAM.addActionListener(sb_listner);
		
		
		tableMainModel = new DefaultTableModel(0,0);
		tableMain.setModel(tableMainModel);
		
		tableShopcartModel = new DefaultTableModel(0,0);
		tableShopcart.setModel(tableShopcartModel);
		
		
	}
	
	
	public void setDataMainTable(ArrayList<String[]> al, String[] header){
		
		if (tableMainModel .getRowCount() > 0) {
		    for (int i = tableMainModel.getRowCount() - 1; i > -1; i--) {
		    	tableMainModel.removeRow(i);
		    }
		}
		Vector<Object> data = null;
		tableMainModel.setColumnIdentifiers(header);
		for(String[] s: al){
			 data = new Vector<Object>();
			 for(int i=0; i< s.length;i++){
				 data.add(s[i]);
			 }
			 tableMainModel.addRow(data);
		}
	}
	
	public void setDataShopcartTable(ArrayList<String[]> al, String[] header){
		
		if (tableShopcartModel .getRowCount() > 0) {
		    for (int i = tableShopcartModel.getRowCount() - 1; i > -1; i--) {
		    	tableShopcartModel.removeRow(i);
		    }
		}
		Vector<Object> data = null;
		tableShopcartModel.setColumnIdentifiers(header);
		for(String[] s: al){
			 data = new Vector<Object>();
			 for(int i=0; i< s.length;i++){
				 data.add(s[i]);
			 }
			 tableShopcartModel.addRow(data);
		}
	}
	
	
	public void addObservers(Observer o){
		sb_listner.addObserver(o);
	}
	
	
	public void setVisible(boolean b){
		frame.setVisible(b);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
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
		
		panel = new JPanel();
		panel_tableShopcart.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		tableShopcart = new JTable();
		scrollPane.setViewportView(tableShopcart);
	}
	
	
	/**
	 * Klasa obslugujaca nacisniecia przyciskow slozacych zmianie kategorii produktu.
	 * @author Piotrek
	 *
	 */
	private class SwitchButtonsListener extends Observable implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			System.out.println("LIST");
			
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
			
			System.out.println(compName);
			
			setChanged();
			notifyObservers(compName);
		} 
		
	}


	public SwitchButtonsListener getSb_listner() {
		return sb_listner;
	}


}
