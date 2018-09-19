package com.operations.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
				String compName = set.getString("compName");
				String pass=set.getString("password");
				if(compName.equals(username) && pass.equals(password)){
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
		String stat1="SELECT buyerCompId,sum(quantity*price) as payFund FROM Transactions GROUP BY buyerCompId";
		String stat2="SELECT sellerCompId,sum(quantity*price) as getFund FROM Transactions GROUP BY sellerCompId";
		
		try(Connection con =MyConnection.openConnection();) {
			PreparedStatement ps= con.prepareStatement(stat1);
            PreparedStatement ps1= con.prepareStatement(stat2);
			ResultSet set = ps.executeQuery();
			ResultSet set1 = ps1.executeQuery();
			while(set.next())
			{
				while(set1.next())
				{
				String buyerId = set.getString("buyerCompId");
				String sellerId = set1.getString("sellerCompId");
				if(buyerId.equals(sellerId)) {
				float payFund = set.getFloat(2);
				float getFund = set1.getFloat(2);
				float nettedfunds=getFund-payFund;
				String stat3="UPDATE Company SET nettedFunds=nettedfunds WHERE compId=?";
				
				PreparedStatement ps3= con.prepareStatement(stat3);
				ps3.setString(1, buyerId);
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
	

}
