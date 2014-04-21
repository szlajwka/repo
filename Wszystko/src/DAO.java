
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAO {


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
		connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;user=sa;password=haslo;database=AplikacjeSklepy");
//		connection = DriverManager.getConnection(url, user, pass);

	}
	
	
	public void executeUpdate(String query) throws SQLException {

		statement = connection.createStatement();
		statement.executeUpdate(query);

		statement.close();
		
		//connection.close();
	}
        
        public ResultSet execute(String query)throws SQLException{
            statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(query);
            return rs;
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
                System.out.println(query);
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
