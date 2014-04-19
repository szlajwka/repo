import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAO {
	
	public static void main(String args[]){
		DAO dao = null;
		try {
			//dao = new DAO("jdbc:mysql://127.0.0.1:3306/projekt", "root", "", "com.mysql.jdbc.Driver");
			dao = new DAO("jdbc:sqlserver://localhost:52572/AplikacjeSklepy", "sa", "root", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] columnNames = {"nazwa", "kraj", "specjalizacja"};
		String query = "SELECT * FROM Producent";
		ArrayList<String[]> al = null;
		try {
			al = dao.executeSelect(query, columnNames);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(String[] s: al){
			for(int i = 0; i < s.length ;i++){
				System.out.print(s[i]+" ");
			}
			System.out.println();
		}
	}

	private Connection connection;
	private Statement statement;

	/**
	 * Wykonuje polecenie insert, delete lub update
	 * @param query
	 * @throws SQLException 
	 */
	
	DAO(String url) throws SQLException{
		connection = DriverManager.getConnection(url);
	}
	
	
	DAO(String url, String user, String pass, String driver) throws SQLException{
		try {
			Class.forName(driver).newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection = DriverManager.getConnection("jdbc:sqlserver://localhost:52572;user=sa;password=root;database=AplikacjeSklepy");
//		connection = DriverManager.getConnection(url, user, pass);

	}
	
	
	public void executeUpdate(String query) throws SQLException {

		statement = connection.createStatement();
		statement.executeUpdate(query);

		statement.close();
		
		//connection.close();
	}

	
	/**
	 * Wykonuje zapytanie typu select (czyli ze zwracaniem)
	 * @param query - zapytanie do bazy
	 * @param columnNames - nazwy kolumnt
	 * @return - ArrayLista<String[]> 
	 * @throws SQLException
	 */
	public ArrayList<String[]> executeSelect(String query, String[] columnNames) throws SQLException{
		ArrayList<String[]> al = new ArrayList<String[]>();
		String[] r = new String[columnNames.length];

		statement = connection.createStatement();
		ResultSet result = statement.executeQuery(query);

		while (result.next()) {
			r = new String[columnNames.length];
			for (int i = 0; i < columnNames.length; i++) {
				r[i] = result.getString(columnNames[i]);
			}

			al.add(r);

		}

		return al;

	}
	



}
