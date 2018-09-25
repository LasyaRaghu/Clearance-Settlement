package com.operations.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.beans.Security;
import com.beans.Transaction;
import com.connections.MyConnection;
import com.operations.SecurityOperations;
import com.operations.TransactionOperations;

public class SecurityOperationsImpl implements SecurityOperations {

	@Override
	public List<Security> displayBorrowingRate() {
		// TODO Auto-generated method stub
		List<Security> interest_rates= new ArrayList<>();
		String FIND_INTERESTRATES = "select * from Security";
		try(Connection con =MyConnection.openConnection();) {
			PreparedStatement ps= con.prepareStatement(FIND_INTERESTRATES);
			ResultSet set = ps.executeQuery();
			while(set.next())
			{
				String securityId= set.getString(1);
				String securityName=set.getString(2);
				
				float marketPrice = set.getFloat(3);
				float borrowingRate = set.getFloat(4);
				Security interest_rate = new Security(securityId, securityName, marketPrice, borrowingRate);
				interest_rates.add(interest_rate);
			}
			
		}
		 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return interest_rates;
		
	}

	
	

	
	

}
