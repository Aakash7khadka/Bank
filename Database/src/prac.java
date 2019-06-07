import java.sql.*;
public class prac{
	public static void main(String[] args)throws Exception {
		String url="jdbc:mysql://localhost:3306/csit_fourth";
		String query="insert into studentinput values(?,?)";
		int roll=5;
		String name="shyam";
		Connection con=DriverManager.getConnection(url,"root","");
		PreparedStatement st=con.prepareStatement(query);
		st.setInt(1, roll);
		st.setString(2, name);
		int count=st.executeUpdate();
		System.out.println(count+"row/s affected");
		st.close();
		con.close();
		
	}

	
}