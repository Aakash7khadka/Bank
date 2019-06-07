import java.sql.*;
public class test {
	public static void main(String[] args) throws Exception {
		String query="select * from student";
		String url="jdbc:mysql://localhost:3306/csit";
		Connection con=DriverManager.getConnection(url,"root","");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		while(rs.next())
		{
			System.out.println(rs.getString(4)+" "+rs.getString(5));
		}
		st.close();
		con.close();

	}
}
