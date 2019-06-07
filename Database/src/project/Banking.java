package project;
import java.util.Scanner;
import java.sql.*;
import java.sql.*;

public class Banking {
	public static void main(String[] args) throws Exception {
		int choice,choice1,choice2,check;
		String name,acc_no;
		int pin;
		Scanner obj=new Scanner(System.in);
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");
		Statement st=con.createStatement();
		System.out.println("\t\t\t\tHamro Bank");
		do {
			System.out.println("1.Go to Bank");
			System.out.println("2.Go to ATM");
			System.out.println("3.Exit");
			System.out.println("Select:");
			choice=obj.nextInt();
			switch (choice)
			{
			case 1:
				Bank b=new Bank();
				do {
					System.out.println("1.Create Account");
					System.out.println("2.Deposit");
					System.out.println("3.Recover account");
					System.out.println("4.Exit");
					System.out.println("Choose:");
					choice1=obj.nextInt();
				switch(choice1)
				{
				case 1:
				b.createAccount();
				break;
				case 2:
					b.deposit();
					break;
				case 3:
					break;
					default:
						System.out.println("Invalid choice");
					
					
				}
				}while(choice1!=4);
				break;
			case 2:
				
				obj.nextLine();
				System.out.println("Enter your account_no:");
				acc_no=obj.nextLine();
				
				String sql="select * from costumer where account_no="+acc_no+";";
				String sqla="select * from counter where account_no="+acc_no+";";
				System.out.println(sqla);
				
				ResultSet rs1=st.executeQuery(sqla);
				rs1.next();
				check =rs1.getInt(2);
				System.out.println(check);
				rs1.close();
				ResultSet rs=st.executeQuery(sql);
				
				//if(check>0)
				if(rs.next() && check >0)
				{
					 int realpin=rs.getInt(5);
					boolean set=true;
					while(set==true)
					{
					System.out.println("Enter your pin:");
					pin=obj.nextInt();
				ATM a=new ATM(acc_no);
				
				
				if(pin==realpin)
				{
					
				do {
					System.out.println("1.Balance Enquiry");
					System.out.println("2.Change Pin");
					System.out.println("3.Withdraw");
					System.out.println("4.Statement");
					System.out.println("5.Exit");
					System.out.println("Choose:");
					choice2=obj.nextInt();
				switch(choice2)
				{
				case 1:
					a.balanceEnquiry();
					break;
				case 2:
					a.changePin();
					break;
				case 3:
					a.withDraw();
					break;
				case 4:
					a.statement();
					break;
				case 5:
					break;
					
				}
				}while(choice2!=5);
				set=false;
				break;
				}
				
					else {
						if(check!=1)
						{
					check--;
					String sql2="update counter set count = "+check+"";
					System.out.println("You have "+check +" trys remaining");
					st.execute(sql2);
						}
						else
						{
							System.out.println("Your account has been blocked! Please visit bank for the recovery ");
							set=false;
							
						}
					
				}
				}
				}
				
				
				
				
				else
					System.out.println("Your ATM card is invalid ! Try again");
			
			case 3:
				break;
			}
				
			
			
		}while(choice!=3);
	}

}
