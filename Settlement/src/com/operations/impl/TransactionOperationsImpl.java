package com.operations.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.beans.Transaction;
import com.connections.MyConnection;
import com.operations.TransactionOperations;

public class TransactionOperationsImpl implements TransactionOperations {

	@Override
	public boolean Login(String username, String password) {
		// TODO Auto-generated method stub
		String FIND = "select * from Login";
		try(Connection con =MyConnection.openConnection();) {
			PreparedStatement ps= con.prepareStatement(FIND);
			ResultSet set = ps.executeQuery();
			while(set.next())
			{
				String compName = set.getString("companyName");
				String pass=set.getString("password");
				if((compName.equals(username) && pass.equals(password))||(compName.equals("Admin") && pass.equals("Admin123"))){
					return true;
				}
				
			}
			
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public boolean NettingFunds() {
		// TODO Auto-generated method stub
		boolean isUpdated= false;
		String stat1="SELECT buyerCompanyId,sum(quantity*price) as payFund FROM Transactions GROUP BY buyerCompanyId";
		String stat2="SELECT sellerCompanyId,sum(quantity*price) as getFund FROM Transactions GROUP BY sellerCompanyId";
		
		try(Connection con =MyConnection.openConnection();) {
			PreparedStatement ps= con.prepareStatement(stat1);
            PreparedStatement ps1= con.prepareStatement(stat2);
			ResultSet set = ps.executeQuery();
			ResultSet set1 = ps1.executeQuery();
			while(set.next())
			{
				while(set1.next())
				{
				String buyerId = set.getString("buyerCompanyId");
				String sellerId = set1.getString("sellerCompanyId");
				if(buyerId.equals(sellerId)) {
				float payFund = set.getFloat(2);
				float getFund = set1.getFloat(2);
				float nettedfunds=getFund-payFund;
				String stat3="UPDATE Company SET nettedFunds=? WHERE companyId=?";
				
				PreparedStatement ps3= con.prepareStatement(stat3);
				ps3.setFloat(1, (float)nettedfunds);
				ps3.setString(2, buyerId);
				int rows=ps3.executeUpdate();
				if(rows>0)
				{
					isUpdated=true;
				}
				}
				}
				//float payFund=set.getFloat("payFund");
				//float getFund=set1.getFloat("getFund");
			}
	}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isUpdated;
	}

	@Override
	public boolean NettingShares() {
		// TODO Auto-generated method stub
		boolean isUpdated = false;
		String stat1 = "SELECT buyerCompanyId,securityId,sum(quantity) from Transactions GROUP BY securityId,buyerCompanyId";
		String stat2 = "SELECT sellerCompanyId,securityId,sum(quantity) from Transactions GROUP BY securityId,sellerCompanyId";
		
		try(Connection con =MyConnection.openConnection();) {
			PreparedStatement ps= con.prepareStatement(stat1);
            PreparedStatement ps1= con.prepareStatement(stat2);
			ResultSet set = ps.executeQuery();
			ResultSet set1 = ps1.executeQuery();
			while(set.next())
			{
				String buyerId = set.getString("buyerCompanyId");
				while(set1.next())
				{
				String sellerId = set1.getString("sellerCompanyId");
				if(buyerId.equals(sellerId)) {
					int paySecurities = set1.getInt(3);
					int getSecurities = set.getInt(3);
					int netSecurities = getSecurities-paySecurities;
					String stat3="UPDATE Manages SET nettedSecurities=? WHERE companyId=?";
				
					PreparedStatement ps3= con.prepareStatement(stat3);
					ps3.setInt(1, (int)netSecurities);
					ps3.setString(2, buyerId);
					int rows=ps3.executeUpdate();
					if(rows>0)
					{
						isUpdated=true;
					}
				}
				}
				//float payFund=set.getFloat("payFund");
				//float getFund=set1.getFloat("getFund");
			}
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return isUpdated;
	}
	@Override
	public List<Transaction> findAll() {
		// TODO Auto-generated method stub
		 List<Transaction> transactions= new ArrayList<>();
			
			String FIND_ALL = "select * from Transaction";
			//String FIND="select companyName from Company where companyId=?";
			try(Connection con =MyConnection.openConnection();) {
				PreparedStatement ps= con.prepareStatement(FIND_ALL);
				ResultSet set = ps.executeQuery();
				while(set.next())
				{
					String transactionId= set.getString(1);
					String buyerCompanyId=set.getString(2);
					//PreparedStatement ps1= con.prepareStatement(FIND);
					//ResultSet set1 = ps1.executeQuery();
					//ps1.setString(1,buyerCompanyId);
					//String buyerCompanyName=set1.getString(1);
					String securityId = set.getString(3);
					String sellerCompanyId = set.getString(4);
					
					int quantity = set.getInt(5);
					float price =set.getFloat(6);
					Transaction transaction = new Transaction(transactionId,buyerCompanyId,securityId,sellerCompanyId,quantity,price);
					transactions.add(transaction);
				}
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return transactions;
		
	}

	@Override
	public List<Transaction> findTransactionBySecurity(String securityId) {
		// TODO Auto-generated method stub
		List<Transaction> transactions= new ArrayList<>();
		String FIND_BY_SECURITY = "select * from Transaction where securityId=?";
		try(Connection con =MyConnection.openConnection();) {
			PreparedStatement ps= con.prepareStatement(FIND_BY_SECURITY);
			ps.setString(1, securityId);
			ResultSet set = ps.executeQuery();
			while(set.next())
			{
				String transactionId= set.getString(1);
				String buyerCompanyId=set.getString(2);
				String securityid = set.getString(3);
				String sellerCompanyId = set.getString(4);		
				int quantity = set.getInt(5);
				float price =set.getFloat(6);
				Transaction transaction = new Transaction(transactionId,buyerCompanyId,securityid,sellerCompanyId,quantity,price);
				transactions.add(transaction);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return transactions;
	}

	@Override
	public List<Transaction> findTransactionByBuyer(String buyerCompanyId) {
		// TODO Auto-generated method stub
		List<Transaction> transactions= new ArrayList<>();
		String FIND_BY_SECURITY = "select * from Transaction where buyerCompanyId=?";
		try(Connection con =MyConnection.openConnection();) {
			PreparedStatement ps= con.prepareStatement(FIND_BY_SECURITY);
			ps.setString(1, buyerCompanyId);
			ResultSet set = ps.executeQuery();
			while(set.next())
			{
				String transactionId= set.getString(1);
				String buyerCompId=set.getString(2);
				String securityid = set.getString(3);
				String sellerCompanyId = set.getString(4);		
				int quantity = set.getInt(5);
				float price =set.getFloat(6);
				Transaction transaction = new Transaction(transactionId,buyerCompId,securityid,sellerCompanyId,quantity,price);
				transactions.add(transaction);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return transactions;
		
		
	}

	@Override
	public List<Transaction> findTransactionBySeller(String sellerCompanyId) {
		// TODO Auto-generated method stub
		List<Transaction> transactions= new ArrayList<>();
		String FIND_BY_SECURITY = "select * from Transaction where sellerCompanyId=?";
		try(Connection con =MyConnection.openConnection();) {
			PreparedStatement ps= con.prepareStatement(FIND_BY_SECURITY);
			ps.setString(1, sellerCompanyId);
			ResultSet set = ps.executeQuery();
			while(set.next())
			{
				String transactionId= set.getString(1);
				String buyerCompId=set.getString(2);
				String securityid = set.getString(3);
				String sellerCompId = set.getString(4);		
				int quantity = set.getInt(5);
				float price =set.getFloat(6);
				Transaction transaction = new Transaction(transactionId,buyerCompId,securityid,sellerCompId,quantity,price);
				transactions.add(transaction);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return transactions;
		
	}


	

}
