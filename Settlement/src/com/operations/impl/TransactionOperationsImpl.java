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
					int transactionId= set.getInt(1);
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
				int transactionId= set.getInt(1);
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
				int transactionId= set.getInt(1);
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
				int transactionId= set.getInt(1);
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

	@Override
	public void addTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
	
		String ADDTRANSACTION = "Insert into TRANSACTIONS values(?,?,?,?,?,?)";

		try {
			Connection con = MyConnection.openConnection();

			PreparedStatement ps = con.prepareStatement(ADDTRANSACTION);
			ps.setInt(1, transaction.getTransId());
			ps.setString(3, transaction.getSecurityId());
			ps.setInt(5, transaction.getQuantity());
			ps.setFloat(6, transaction.getPrice());
			ps.setString(2, transaction.getBuyerCompId());
			ps.setString(4, transaction.getSellerCompId());
			 ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		}
		
		
	}

	@Override
	public boolean deleteTransaction(int TransId) {
		// TODO Auto-generated method stub
		String DELETETRANSACTION="DELETE FROM TRANSACTIONS WHERE transactionId=?";
		
		Connection con= MyConnection.openConnection();
		try {
			PreparedStatement ps= con.prepareStatement(DELETETRANSACTION);
			ps.setInt(1, TransId);
			 ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Transaction findTransactionByID(int TransID) {
		// TODO Auto-generated method stub
	String FINDBYID="select * from transactions where transactionId=?";
	Transaction transaction = new Transaction();
	Connection con= MyConnection.openConnection();
	try {
		PreparedStatement ps= con.prepareStatement(FINDBYID);
		ps.setInt(1, TransID);
		ResultSet set=ps.executeQuery();
		int transId= set.getInt(1);
		String buyerId= set.getString(2);
		String security= set.getString(3);
		String sellerId= set.getString(4);
		int quantity= set.getInt(5);
		float price= set.getFloat(6);
		 transaction= new Transaction(transId, buyerId, security, sellerId,quantity, price);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return transaction;
	}

	@Override
	public void updateTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		String UPDATETRANSACTION = "update TRANSACTIONS set buyerCompanyId=?,securityId=?,sellerCompanyId=?,quantity=?,price=? where transactionId=?";

		try {
			Connection con = MyConnection.openConnection();

			PreparedStatement ps = con.prepareStatement(UPDATETRANSACTION);
			ps.setInt(6, transaction.getTransId());
			ps.setString(2, transaction.getSecurityId());
			ps.setInt(4, transaction.getQuantity());
			ps.setFloat(5, transaction.getPrice());
			ps.setString(1, transaction.getBuyerCompId());
			ps.setString(3, transaction.getSellerCompId());
			 ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		}
		
		
	}


	

}
