package com.operations.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.beans.Company;
import com.connections.MyConnection;
import com.operations.CompanyOperations;

public class CompanyOperationsImpl implements CompanyOperations {

	@Override
	public List<Company> fundsShortage() {
		// TODO Auto-generated method stub
		
		List<Company> funds= new ArrayList<>();
		
		String FIND_ALL = "select companyId,availableFunds-nettedFunds from Company";
		//String FIND="select companyName from Company where companyId=?";
		try(Connection con =MyConnection.openConnection();) {
			PreparedStatement ps= con.prepareStatement(FIND_ALL);
			ResultSet set = ps.executeQuery();
			while(set.next())
			{
				String compId=set.getString(1);
				float diff= set.getFloat(2);
				if(diff<0)
				{
					String stat1="UPDATE Company SET availableFunds=?, nettedFunds=? WHERE companyId=?";
					PreparedStatement ps3= con.prepareStatement(stat1);
					ps3.setFloat(1, (float)0);
					ps3.setFloat(2, (float)diff);
					ps3.setString(3, compId);
					ps3.executeUpdate();
				}
				else if(diff>0)
				{
					String stat1="UPDATE Company SET availableFunds=?, nettedFunds=? WHERE companyId=?";
					PreparedStatement ps3= con.prepareStatement(stat1);
					ps3.setFloat(1, (float)diff);
					ps3.setFloat(2, (float)0);
					ps3.setString(3, compId);
					ps3.executeUpdate();
				}
				else
				{
					String stat1="UPDATE Company SET availableFunds=?, nettedFunds=? WHERE companyId=?";
					PreparedStatement ps3= con.prepareStatement(stat1);
					ps3.setFloat(1, (float)0);
					ps3.setFloat(2, (float)0);
					ps3.setString(3, compId);
					ps3.executeUpdate();
				}
				String stat3 = "SELECT nettedFunds FROM Company WHERE companyId=?";
				PreparedStatement ps5= con.prepareStatement(stat3);
				ps5.setString(1,compId);
				ResultSet set2= ps5.executeQuery();
				float fundsCost=(float)((0.000069445)*set2.getFloat("nettedFunds"));
				
				String stat4="UPDATE Company SET costOfSettlementForFunds=? WHERE companyId=?";
				PreparedStatement ps4= con.prepareStatement(stat4);
				ps4.setFloat(1, (float)fundsCost);
				ps4.setString(2, compId);
				ps4.executeUpdate();
			}
			
					
			String FIND = "select * from Company";
				PreparedStatement ps1= con.prepareStatement(FIND);
				ResultSet set1 = ps1.executeQuery();
				while(set1.next())
				{
					String companyId = set1.getString(1);
					String companyName=set1.getString(2);
					float availableFunds = set1.getFloat(3);
					float nettedFunds = set1.getFloat(4);
					float costOfSettlementForFunds=set1.getFloat(5);
					Company company = new Company(companyId, companyName, availableFunds, nettedFunds,costOfSettlementForFunds);
					funds.add(company);
					
				}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return funds;
		
	}

	@Override
	public Company fundsShortageByCompany(String CompanyId) {
		// TODO Auto-generated method stub
		
		Company company = new Company();
		String FIND = "select * from Company where companyId=?";
		
		try(Connection con =MyConnection.openConnection();) {
		PreparedStatement ps1= con.prepareStatement(FIND);
		ps1.setString(1, CompanyId);
		ResultSet set1 = ps1.executeQuery();
		while(set1.next())
		{
			String companyId = set1.getString(1);
			String companyName=set1.getString(2);
			float availableFunds = set1.getFloat(3);
			float nettedFunds = set1.getFloat(4);
			float costOfSettlementForFunds=set1.getFloat(5);
			company = new Company(companyId, companyName, availableFunds, nettedFunds, costOfSettlementForFunds);
			
			
		}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return company;
	}

	@Override
	public String getCompanyId(String username) {
		// TODO Auto-generated method stub
		String companyId=null;
		String FIND = "select companyId from Login where companyName=?";
		try(Connection con =MyConnection.openConnection();) {
			PreparedStatement ps1= con.prepareStatement(FIND);
			ps1.setString(1, username);
			ResultSet set1 = ps1.executeQuery();
			
			companyId = set1.getString("companyId");
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return companyId;
	}

}
