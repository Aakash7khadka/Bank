package project;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class testdb {
	public static void main(String[] args) throws Exception {
		Document document=new Document();
		PdfWriter.getInstance(document, new FileOutputStream("d:/hello.pdf"));
		document.open();
		document.add(new Paragraph("BANK STATEMENT",FontFactory.getFont(FontFactory.TIMES_BOLD,18,Font.BOLD,BaseColor.RED)));
		document.add(new Paragraph("_____________________________________________________________________________"));
		document.add(new Paragraph(" "));
		PdfPTable table=new PdfPTable(3);
		table.addCell("Transaction Type");
		table.addCell("Amount");
		table.addCell("Date");
		table.addCell("-");
		table.addCell("-");
		table.addCell("-");
		document.add(table);
		document.close();
		
		
	}

}
