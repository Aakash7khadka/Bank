import java.sql.*;
public class inp{
	public static void main(String[] args)throws Exception {
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/csit_fourth","root","");
		Statement st=con.createStatement();
		String query="insert into studen(rollNo,firstName,lastName,gender,course,address,semester)values (1,'Aakash','Khadka','m','csit','Ktm','Fourth')";
		st.executeUpdate(query);
		
	}
}