import java.sql.*;
import java.util.Scanner;

public class update {
public static void main(String[] args) throws Exception{
	Scanner obj=new Scanner(System.in);
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/csit_fourth","root","");
	Statement st=con.createStatement();
	System.out.println("Enter the roll to update:");
	int roll=obj.nextInt();
	String sql = "select * from studen where rollNo="+roll+"";
	System.out.println(sql);
	ResultSet rs=st.executeQuery(sql);
	rs.next();
	obj.nextLine();
	String fname =rs.getString("firstName");
	String lname =rs.getString("lastName");
	System.out.println("Name:"+fname+" "+lname);
	System.out.println("Enter first name and last name to update:");
	fname=obj.nextLine();
	lname=obj.nextLine();  
	sql="update studen set firstName ='"+fname+"',lastName='"+lname+"' where rollNo="+roll+"; ";
	System.out.println(sql);
	st.execute(sql);
	
}
}
