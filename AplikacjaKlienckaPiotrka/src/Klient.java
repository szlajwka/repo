import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class Klient implements Observer{
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Klient();

	}
	
	
	private GUI gui;
	private Klient k = this;
	private DAO dao;
	
	Klient(){
		
		EventQueue.invokeLater(new Runnable() {
		@Override
		public void run() {
			try {
				gui = new GUI();
				gui.setVisible(true);

				
			} catch (Exception e) {
				e.printStackTrace();
			}
			gui.addObservers(k);
		}});
		
		try {
			dao = new DAO("jdbc:sqlserver://localhost:52572;user=sa;password=root;database=AplikacjeSklepy");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		System.out.println("UPDATE");
		if(arg0.equals(gui.getSb_listner())){
			String compName = (String) arg1;
			onSwitchProduct(compName);
			
		}
		
	}
	
	
	
	private void onSwitchProduct(String compName){
		String[] columnNames = {};
		String tableName = "";
		String[] header = {};
		
		switch (compName){
		case "CPU":
			columnNames = new String[] {"Id","cena_netto", "cena_brutto", "waga","ilosc_rdzeni","typ_gniazda","czestotliwosc_taktowania","stan_mag"};
			tableName = "Procesor";
			header = new String[] {"ID", "Cena Netto", "Cena Brutto", "Waga", "IloscRdzeni", "Typ Gniazda", "HZ", "StanMag"};
			//ArrayList<String> ar = new ArrayList<String>(header);
			break;
		case "GUP":
			break;
		case "HDD":
			break;
		case "IO":
			break;
		case "Monitor":
			break;
		case "ODD":
			columnNames = new String[] {"Id","cena_netto","cena_brutto","waga","model","typ","interfejs","stan_mag"};
			tableName = "Naped";
			header = new String[] {"ID", "Cena Netto", "Cena Brutto", "Waga", "Model", "Typ", "Interfejs", "StanMag"};
			break;
		case "RAM":
			break;
		case "MotherBoard":
			break;
		}
		
		String query = "SELECT * FROM "+tableName;
		ArrayList<String[]> al = null;
		try {
			al = dao.executeSelect(query, columnNames);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		gui.setDataMainTable(al, header);
		
	
		

	}
	

}
