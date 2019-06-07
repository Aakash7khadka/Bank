package project;
import java.util.Date;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;public class ATM {
	
	String acc_no;

	public ATM(String acc_no2) {
		this.acc_no=acc_no2;
	}
	public void balanceEnquiry()throws Exception {
		Scanner input=new Scanner(System.in);
		Connection con= new DatabaseConnection().getConnection();
		Statement st=con.createStatement();
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------");
		
		String query="select * from costumer where account_no="+acc_no+"";
		ResultSet rs=st.executeQuery(query);
		rs.next();
		int bal=rs.getInt(6);
		System.out.println("Your current balance is:"+bal);
	}

	public void changePin() throws Exception{
		Scanner input=new Scanner(System.in);
		
		Connection con= new DatabaseConnection().getConnection();
		Statement st=con.createStatement();
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------");
		
		System.out.println("Enter your old pin");
		int old_pin=input.nextInt();
		String query="select * from costumer where account_no="+acc_no+"";
		
		ResultSet rs= st.executeQuery(query);
		rs.next();
		System.out.println(rs.getInt(5));
		if(old_pin==rs.getInt(5))
		{
		System.out.println("Enter new pin:");
		int pin_new=input.nextInt();
		query="update costumer set pin="+pin_new+" where account_no="+acc_no+"";
		
		st.execute(query);
		System.out.println("You have changed your pin sucessfully");
		}
		else
			System.out.println("Pin mismatch!");
		
	}

	public void withDraw() throws Exception {
		Scanner input=new Scanner(System.in);
		Connection con= new DatabaseConnection().getConnection();
		Statement st=con.createStatement();
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------");
		
		String query="select * from costumer where account_no="+acc_no+"";
		String query1;
		ResultSet rs=st.executeQuery(query);
		rs.next();
		int bal=rs.getInt(6);
		System.out.println("Your current balance is:"+bal);
		System.out.println("Enter the balance to withdraw:");
		int amt=input.nextInt();
		if(bal>=amt)
		{
			bal-=amt;
		query="update costumer set balance="+bal+" where account_no="+acc_no+";"; 
		query1="insert into ministatement (account_no,withdraw,type)values('"+acc_no+"','"+amt+"','withdraw')";
		st.execute(query);
		st.execute(query1);
		}
		else
			System.out.println("You dont have sufficient amount!");
		
		
	}

	public void statement() throws Exception {
		Document document=new Document();
		PdfWriter.getInstance(document, new FileOutputStream("d:/"+acc_no+".pdf"));
		document.open();
		document.add(new Paragraph("Date:"+new Date().toString()));
		document.add(new Paragraph("BANK STATEMENT",FontFactory.getFont(FontFactory.TIMES_BOLD,18,Font.BOLD,BaseColor.RED)));
		document.add(new Paragraph("_____________________________________________________________________________"));
		document.add(new Paragraph("Account Number: "+acc_no));
		document.add(new Paragraph(" "));
		PdfPTable table=new PdfPTable(3);
		table.addCell("Transaction Type");
		table.addCell("Amount");
		table.addCell("Date");
	
		
		int amount,i=0;
		System.out.println("----------------------------------------------------------------------------------");
		Connection con= new DatabaseConnection().getConnection();
		Statement st=con.createStatement();
		String query="select * from ministatement where account_no="+acc_no+" order by sn desc";
		ResultSet rs=st.executeQuery(query);
		
			while(rs.next() && i<=5)
			{
				System.out.println("---------------------------------------------------");
			System.out.println("Transaction type:"+rs.getString(6)); 
			table.addCell(rs.getString(6));
			if(rs.getInt(3)==0)
			{
			
				amount=rs.getInt(4);
				table.addCell(amount+"");
			}
			else
			{
				amount=rs.getInt(3);
				table.addCell(amount+"");
			}
				System.out.println("Amount:"+ amount);
			//System.out.println(rs.getDate(5));
		System.out.println(rs.getTimestamp(5));
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(rs.getTimestamp(5));
		table.addCell(timeStamp);
		}
			rs.close();
			String query1="select * from costumer where account_no="+acc_no+" ";
			ResultSet rs1=st.executeQuery(query1);

			rs1.next();
			query=rs1.getInt(6)+"";
			document.add(new Paragraph("Main Balance:"+query));
			document.add(new Paragraph("Sent by:Hamro Bank Pvt.Ltd"));
			
			System.out.println("----------------------------------------------------------------------------------");
			document.add(new Paragraph(" "));
			document.add(table);
			document.close();
		
	}

	

}
