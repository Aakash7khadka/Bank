	import java.sql.*;
	import java.util.Scanner;

public class delete {

	
	public static void main(String[] args) throws Exception{
		Scanner obj=new Scanner(System.in);
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/csit_fourth","root","");
		Statement st=con.createStatement();
		System.out.println("Enter the roll to delete:");
		int roll=obj.nextInt();
		String sql = "select * from studen where rollNo="+roll+"";

		ResultSet rs=st.executeQuery(sql);
		rs.next();
		obj.nextLine();
		String fname =rs.getString("firstName");
		String lname =rs.getString("lastName");
		System.out.println("Name:"+fname+" "+lname);
		System.out.println("Do you want to confirm the delete:(if yes: true) ");
		boolean check=obj.nextBoolean();
		
		sql="delete from studen  where rollNo="+roll+"; ";
		if(check)
		System.out.println(sql);
		st.execute(sql);
		
	
	}


}
