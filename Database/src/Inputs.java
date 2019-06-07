import java.sql.*;
import java.util.Scanner;

public class Inputs {
public static void main(String[] args) throws Exception{
	String url="jdbc:mysql://localhost:3306/csit_fourth";
	Scanner obj=new Scanner(System.in);
	Connection con=DriverManager.getConnection(url,"root","");
	boolean ch;
	int roll;
	String name,rol;
	do {
		System.out.print("Enter  name:");
		name=obj.nextLine();
		System.out.print("Enter  roll no:");
		rol=obj.nextLine();
				
		roll=Integer.parseInt(rol);
		System.out.println(name+" "+roll);
		System.out.println("name --- "+name);
		
		
		String query="insert into studentInput values (?,?)";
		PreparedStatement st=con.prepareStatement(query);
		st.setInt(1, roll);
		st.setString(2, name);
		int count=st.executeUpdate();
		System.out.println(count+"row/s affected");
		st.close();
		con.close();
	
		System.out.println("Do you want to continue?(if yes type true otherwise false)");
		ch=obj.nextBoolean();
	}while (ch==true);
	
}
}
