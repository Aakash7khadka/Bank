package project;
import java.util.Scanner;

import java.sql.*;

public class Bank {

	public void createAccount() throws Exception {
		String nam,add,phn;
		int acc_no,pin;
		Scanner input=new Scanner(System.in);
		Connection con= new DatabaseConnection().getConnection();
		Statement st=con.createStatement();
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("Enter your name:");
		nam=input.nextLine();
		System.out.println("Enter your address:");
		add=input.nextLine();
		System.out.println("Enter your phone:");
		phn=input.nextLine();
		System.out.println("Enter a  account number:");
		acc_no=input.nextInt();
		System.out.println("Enter a pin:");
		pin=input.nextInt();
		String query1="select * from costumer where account_no="+acc_no+"";
		ResultSet rs=st.executeQuery(query1);
		if(rs.next())
			System.out.println("This account number is already in use,try another number");
		else
		{
			String query2="insert into counter (account_no,count)values('"+acc_no+"',"+3+")";
		String query="insert into costumer (account_no,name,address,phone,pin)values('"+acc_no+"','"+nam+"','"+add+"','"+phn+"','"+pin+"')";
		st.executeUpdate(query);
		st.executeUpdate(query2);
		
		System.out.println("You have created account sucessfully");
		}
	}

	public void deposit() throws Exception {
		Scanner input=new Scanner(System.in);
		Connection con= new DatabaseConnection().getConnection();
		Statement st=con.createStatement();
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------");
		
		System.out.println("Enter  account number:");
		int acc_no=input.nextInt();
		String query="select * from costumer where account_no="+acc_no+"";
		ResultSet rs=st.executeQuery(query);
		if(rs.next())
		{
		System.out.println("Enter the balance to deposit:");
		 int bal=input.nextInt();
		String query1="insert into ministatement (account_no,deposit,type)values('"+acc_no+"','"+bal+"','deposit')";
		bal+=rs.getInt(6);
		query="update costumer set balance="+bal+" where account_no="+acc_no+";"; 
		st.execute(query);
		st.execute(query1);
		}
		else
			System.out.println("The account you are requesting doesnot exist!");
		
		
		
		
		
		
	}



}
