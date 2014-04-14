/**
 * Created by wodzu on 01.04.14.
 */
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Klasa odpowiedzialna za polaczenie z baza danych
 * http://msdn.microsoft.com/pl-pl/sqlserver/aa937724.aspx //driver do pobrania
 */
public class Polacz {
      public static void main(String[]args)throws ClassNotFoundException,SQLException{
	  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	  System.out.println("driver works");
	  Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;user=sa;password=haslo;database=AplikacjeSklepy");
	  System.out.println("connected");
	  Statement st=conn.createStatement();
	  ResultSet rs=st.executeQuery("Select * from Test");
	  while(rs.next()){
	    System.out.println(rs.getString("nazwa");
	  }
	  conn.close();
      }	  
}
