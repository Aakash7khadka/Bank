import java.sql.*;
public class connect {
	public static void main(String[] args) throws Exception {
		String url="jdbc:mysql://localhost:3306/csit";
		String query="select * from student";
		Connection con=DriverManager.getConnection(url,"root","");
		Class.forName("com.mysql.cj.jdbc.Driver");
		Statement st=con.createStatement();
		
		ResultSet rs=st.executeQuery(query);
		rs.next();
		String name=rs.getString("first_name");
		System.out.println(name);
		st.close();
		con.close();
	}

}
