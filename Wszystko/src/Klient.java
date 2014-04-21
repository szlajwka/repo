
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
        private int currentWeigh=0;
	private int currentCost=0;
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
			dao = new DAO("jdbc:sqlserver://localhost:1433;user=sa;password=haslo;database=AplikacjeSklepy");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		shopCart = Collections.synchronizedList(new ArrayList<int[]>());
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		if(arg0.equals(gui.getSb_listner())){
			String compName = (String) arg1;
			onSwitchProduct(compName);
			
		}
		else if(arg0.equals(gui.getAddToShopcartButtonsListener())){
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
		if(quantity>=0){
			gui.showInfoMessage("Ilosc jest rowna zero.");
			return;
		}
		int[] tuple = {id,quantity};
		shopCart.add(tuple);
		displayShopCart();
	}
	
	private void displayShopCart(){
		String[] header =  {"ID","Producent","Model","Cena Netto", "Cena Brutto","StanMag", "IloscZam"};
		String[] columnNames = {"id","NazwaProducenta","model",	"cena_netto",	"cena_brutto",	"stan_mag",	};
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
                        columnNames = new String[] {"Id","cena_netto", "cena_brutto", "waga","model","typ","pojemnosc","szerokosc","interfejs", "predkosc_obrotowa","stan_mag","nazwa"};
			query = "exec getDyskTwardy";
			header = new String[] {"ID", "Cena Netto", "Cena Brutto", "Waga", "Model", "Typ", "Pojemnosc", "Szerokosc", "Interfejs", "Predkosc Obrotowa","StanMag","Producent"};

			break;
		case "IO":
                        columnNames = new String[] {"Id","cena_netto", "cena_brutto", "waga","rodzaj","model","typ","stan_mag", "nazwa"};
			query = "exec getPeryferia";
			header = new String[] {"ID", "Cena Netto", "Cena Brutto", "Waga", "Rodzaj", "Model", "Typ", "StanMag", "Procudent"};
			break;
		case "Monitor":
                        columnNames = new String[] {"Id","cena_netto", "cena_brutto", "waga","model","typ","przekatna","rodzaj_podswietlenia","rozdzielczosc", "stan_mag","Producent"};
			query = "exec getMonitor";
			header = new String[] {"ID", "Cena Netto", "Cena Brutto", "Waga", "Model", "Typ", "Przekatna", "RodzajPodswietlenia","Rozdzielczosc", "StanMag", "Procudent"};
			break;
		case "ODD":
			columnNames = new String[] {"Id","cena_netto","cena_brutto","waga","model","typ","interfejs","stan_mag","nazwa"};
			query = "exec getNaped";
			header = new String[] {"ID", "Cena Netto", "Cena Brutto", "Waga", "Model", "Typ", "Interfejs", "StanMag","Producent"};
			break;
		case "RAM":
                        columnNames = new String[] {"Id","cena_netto", "cena_brutto", "waga","model","rodzaj","czestotliwosc_pracy","przepustowosc","stan_mag", "nazwa"};
			query = "exec getRam";
			header = new String[] {"ID", "Cena Netto", "Cena Brutto", "Waga", "Model", "Rodzaj", "CzestotliwoscPracy", "Przepustowosc", "StanMag", "Procudent"};

			break;
		case "Motherboard":
                        columnNames = new String[] {"Id","cena_netto", "cena_brutto", "waga","model","gniazdo_procesora","chipset","rodzaj_pamieci","karta_graficzna","karta_sieciowa","stan_mag", "nazwa"};
			query = "exec getPlytaGlowna";
			header = new String[] {"ID", "Cena Netto", "Cena Brutto", "Waga", "Model", "GniazdoProcesora", "Chipset", "RodzajPamieci","KartaGraficzna","KartaSieciowa", "StanMag", "Procudent"};
			break;
		
                case "Zestawy":
                        columnNames = new String[] {"Id","cena_netto", "cena_brutto", "waga","model","ModelNapedu","ModelProcesora","ModelRam","ModelKarty","ModelDysku","modelMonitora"," ModelPlyty","stan_mag", "nazwa"};
			query = "exec getZestawPC";
			header = new String[] {"ID", "Cena Netto", "Cena Brutto", "Waga", "Model", "Naped", "Procesor", "Ram","KartaGraficzna","Dysk","Monitor","PlytaGlowna", "StanMag", "Procudent"};
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
