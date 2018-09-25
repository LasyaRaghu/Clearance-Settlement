package com.operations.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.beans.Company;
import com.beans.Manages;
import com.connections.MyConnection;
import com.operations.ManagesOperations;

public class ManagesOperationsImpl implements ManagesOperations {

	@Override
	public List<Manages> securitiesShortage() {
		// TODO Auto-generated method stub
         List<Manages> securities= new ArrayList<>();
		
		String FIND_ALL = "select companyId,security,availableSecurities-nettedSecurities from Manages";
		//String FIND="select companyName from Company where companyId=?";
		try(Connection con =MyConnection.openConnection();) {
			PreparedStatement ps= con.prepareStatement(FIND_ALL);
			ResultSet set = ps.executeQuery();
			while(set.next())
			{
				String compId=set.getString(1);
				String securityId=set.getString(2);
				float diff= set.getFloat(3);
				if(diff<0)
				{
					String stat1="UPDATE Manages SET availableSecurities=?, nettedSecurities=? WHERE companyId=? & securityId=?";
					PreparedStatement ps3= con.prepareStatement(stat1);
					ps3.setFloat(1, (float)0);
					ps3.setFloat(2, (float)diff);
					ps3.setString(3, compId);
					ps3.setString(4, securityId);
					ps3.executeUpdate();
				}
				else 
				{
					String stat1="UPDATE Manages SET availableSecurities=?, nettedSecurities=? WHERE companyId=? & securityId=?";
					PreparedStatement ps3= con.prepareStatement(stat1);
					ps3.setFloat(1, (float)diff);
					ps3.setFloat(2, (float)0);
					ps3.setString(3, compId);
					ps3.setString(4, securityId);
					ps3.executeUpdate();
				}
				String stat3 = "SELECT nettedSecurities FROM Manages WHERE companyId=? and securityId=?";
				PreparedStatement ps5= con.prepareStatement(stat3);
				ps5.setString(1,compId);
				ps5.setString(2, securityId);
				ResultSet set2= ps5.executeQuery();
				String stat5 = "SELECT borrowingRate FROM Security WHERE securityId=?";
				PreparedStatement ps6= con.prepareStatement(stat5);
				ps6.setString(1, securityId);
				ResultSet set3= ps6.executeQuery();
				
				float securityCost=(float)((0.00556)*set3.getFloat("borrowingRate") *set2.getInt("nettedSecurities"));
				
				String stat4="UPDATE Security SET costOfSettlementForSecurity=? WHERE securityId=?";
				PreparedStatement ps4= con.prepareStatement(stat4);
				ps4.setFloat(1, (float)securityCost);
				ps4.setString(2, securityId);
				ps4.executeUpdate();
				
			}
			String FIND = "select * from Manages";
			PreparedStatement ps1= con.prepareStatement(FIND);
			ResultSet set1 = ps1.executeQuery();
			while(set1.next())
			{
				String companyId = set1.getString(1);
				String SecurityId=set1.getString(2);
				int availableSecurities = set1.getInt(3);
				int nettedSecurities = set1.getInt(4);
				float costOfSettlementForSecurities=set1.getFloat(5);
				Manages security = new Manages(companyId, SecurityId, availableSecurities, nettedSecurities,costOfSettlementForSecurities);
				securities.add(security);
				
			}
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return securities;
	}
	
	@Override
	public List<Manages> securitiesShortageByCompany(String CompanyId) {
		// TODO Auto-generated method stub
		
		List<Manages> manages  = new ArrayList<>();
		String FIND = "select * from Manages where companyId=?";
		
		try(Connection con =MyConnection.openConnection();) {
		PreparedStatement ps1= con.prepareStatement(FIND);
		ps1.setString(1, CompanyId);
		ResultSet set1 = ps1.executeQuery();
		while(set1.next())
		{
			String companyId = set1.getString(1);
			String securityId=set1.getString(2);
			int availableSecurities = set1.getInt(3);
			int nettedSecurities = set1.getInt(4);
			float costOfSettlementForSecurities=set1.getFloat(5);
			Manages manage1 = new Manages(companyId, securityId, availableSecurities, nettedSecurities, costOfSettlementForSecurities);
			
			manages.add(manage1);
		}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return manages;
	}

}

