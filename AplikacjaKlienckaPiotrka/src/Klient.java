import java.awt.EventQueue;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
	private List<int[]> shopCart; //koszyk na zakupy 
	
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
		
		shopCart = Collections.synchronizedList(new ArrayList<int[]>());
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		System.out.println("UPDATE");
		if(arg0.equals(gui.getSb_listner())){
			String compName = (String) arg1;
			onSwitchProduct(compName);
			
		}
		else if(arg0.equals(gui.getAddToShopcartButtonsListener())){
			System.out.println("U");
			String[] s = ((String) arg1).split(" ");
			int id = Integer.parseInt(s[0]);
			int quantity = Integer.parseInt(s[1]);
			onAddToShopCart(id, quantity);
		}
		else if(arg0.equals(gui.getDeleteFromShopcartButtonsListener())){
			int id = (int) arg1;
			onDeleteFromShopCart(id);
		}
		else if(arg0.equals(gui.getToPayButtonListener())){
			onToPay();
		}
		
	}
	
	private void onToPay(){
		System.out.println("LECIMY DO PLACENIA");
		for(int[] item: shopCart){
			System.out.println("ID: "+item[0]+" ilosc:"+item[1]);
		}
		
	}
	
	private void onDeleteFromShopCart(int id){
		synchronized (shopCart) {  
			for(int[] item: shopCart){
				if(item[0]==id)
					shopCart.remove(item);
			}
		}
		
		displayShopCart();
	}
	
	
	private void onAddToShopCart(int id, int quantity){
		if(quantity==0){
			gui.showInfoMessage("Ilosc jest rowna zero.");
			return;
		}
		int[] tuple = {id,quantity};
		shopCart.add(tuple);
		displayShopCart();
	}
	
	private void displayShopCart(){
		String[] header =  {"ID","Typ","Model","Cena Netto", "Cena Brutto","StanMag", "IloscZam"};
		String[] columnNames = {"id","typ","model",	"cena_netto",	"cena_brutto",	"stan_mag",	};
		String query;
		//ArrayList<String[]> row = null;
		String[] row = null;
		ArrayList<String[]> al = new ArrayList<String[]>();
		for(int[] item: shopCart){
			query = "exec getItem "+item[0];	
			try {
				row =dao.executeSelect(query, columnNames).get(0);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			row = Arrays.copyOf(row, row.length + 1);
			row[row.length -1] =  item[1]+"";
			
			al.add(row);
			
		}
		
		
		gui.setDataShopcartTable(al, header);
		

	}
	
	
	
	private void onSwitchProduct(String compName){
		String[] columnNames = {};
		String[] header = {};
		String query = null;
		switch (compName){
		case "CPU":
			columnNames = new String[] {"Id","cena_netto", "cena_brutto", "waga","ilosc_rdzeni","typ_gniazda","czestotliwosc_taktowania","stan_mag","nazwa"};
			query = "exec getProcesor";
			header = new String[] {"ID", "Cena Netto", "Cena Brutto", "Waga", "IloscRdzeni", "Typ Gniazda", "HZ", "StanMag","Producent"};
			break;
		case "GPU":
			columnNames = new String[] {"Id","cena_netto", "cena_brutto", "waga","model","taktowanie_rdzenia","taktowanie_pamieci","producent_chipsetu","stan_mag", "nazwa"};
			query = "exec getKartaGraficzna";
			header = new String[] {"ID", "Cena Netto", "Cena Brutto", "Waga", "Model", "Takt. Rdzenia", "Takt. Pamieci", "Producent", "StanMag", "Procudent"};
			break;
		case "HDD":
			break;
		case "IO":
			break;
		case "Monitor":
			break;
		case "ODD":
			columnNames = new String[] {"Id","cena_netto","cena_brutto","waga","model","typ","interfejs","stan_mag"};
			query = "SELECT * FROM Naped";
			header = new String[] {"ID", "Cena Netto", "Cena Brutto", "Waga", "Model", "Typ", "Interfejs", "StanMag"};
			break;
		case "RAM":
			break;
		case "MotherBoard":
			break;
		}
		

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
