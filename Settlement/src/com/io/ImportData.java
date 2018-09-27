package com.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;

import com.connections.MyConnection;

public class ImportData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(Connection con =MyConnection.openConnection();) {
			             PreparedStatement pstm = null ;
			             FileInputStream input = new FileInputStream("C:\\Users\\Grad57\\Downloads\\trade.xls");
			             POIFSFileSystem fs = new POIFSFileSystem( input );
			             HSSFWorkbook wb = new HSSFWorkbook(fs);
			             HSSFSheet sheet = wb.getSheetAt(0);
			             Row row;
			             for(int i=0; i<=sheet.getLastRowNum(); i++){
			                 row = sheet.getRow(i);
			                 int transId = (int) row.getCell(0).getNumericCellValue();
			                 String buyerCompId = row.getCell(4).getStringCellValue();
			                 String securityId = row.getCell(1).getStringCellValue();
			                 String sellerCompId = row.getCell(5).getStringCellValue();
			                 int quantity = (int)row.getCell(2).getNumericCellValue();
			                 float price=(float)row.getCell(3).getNumericCellValue();
			                 String sql = "INSERT INTO Transactions VALUES('"+transId+"','"+buyerCompId+"','"+securityId+"','"+sellerCompId+"','"+quantity+"','"+price+"')";
			                 pstm = (PreparedStatement) con.prepareStatement(sql);
			                 pstm.execute();
			                 System.out.println("Import rows "+i);
			             }
			             con.commit();
			             pstm.close();
			             con.close();
			             input.close();
			             System.out.println("Success import excel to mysql table");
			         }
			         catch(SQLException ex){
			             System.out.println(ex);
			         }catch(IOException ioe){
			             System.out.println(ioe);
			         }
			 

	}

}
