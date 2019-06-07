package project;
import java.sql.*;
public class DatabaseConnection {
	public Connection getConnection() throws Exception
	{
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");
		return con;
	}

}
