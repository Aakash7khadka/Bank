import java.sql.*;
import java.util.Scanner;
public class dynamicInput   {
	public static void main(String[] args)throws Exception {
		Scanner obj=new Scanner(System.in);
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/csit_fourth","root","");
		Statement st=con.createStatement();
		System.out.println("Enter roll:");
		int r=obj.nextInt();
		obj.nextLine();
		System.out.println("Enter first name:");
		String f=obj.nextLine();
		
		System.out.println("Enter last name:");
		String l=obj.nextLine();
		System.out.println("Enter gender:");
		char g=obj.nextLine().charAt(0);
		System.out.println("Enter course:");
		String c=obj.nextLine();
		System.out.println("Enter address:");
		String a=obj.nextLine();
		System.out.println("Enter semester");
		String s=obj.nextLine();
		String query="insert into studen(rollNo,firstName,lastName,gender,course,address,semester)values ('"+r+"','"+f+"','"+l+"','"+g+"','"+c+"','"+a+"','"+s+"')";
		st.executeUpdate(query);
		st.close();
		con.close();
	}
	

}
