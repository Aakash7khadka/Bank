import java.sql.*;
public class allData {
	public static void main(String[] args) throws Exception {
		String url="jdbc:mysql://localhost:3306/csit";
		Connection con=DriverManager.getConnection(url,"root","");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from student");
		while(rs.next())
		{
			System.out.println(rs.getString(1)+rs.getInt(2)+rs.getString(4)+rs.getString(5));
		}
		st.close();
		con.close();
		
	}

}
