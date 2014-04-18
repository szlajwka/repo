/**
 * Created by wodzu on 2014-04-18.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        System.out.println("driver works");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;user=sa;password=haslo;database=AplikacjeSklepy");
        return conn;
    }
}
