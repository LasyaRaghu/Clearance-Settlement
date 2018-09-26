package com.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.beans.Company;
import com.operations.CompanyOperations;
import com.operations.impl.CompanyOperationsImpl;

class CompanyImpl {
	@Test
	void testfundsShortage() {
		CompanyOperations dao = new CompanyOperationsImpl();
		float expected = 904.87f;
		List <Company> funds = dao.fundsShortage();
		Company actual = dao.fundsShortageByCompany("C01");
		assertEquals(expected,actual.getCostOfSettlementForFunds());
	}
	@Test
	void testFindByCompanyId() {
		CompanyOperations dao = new CompanyOperationsImpl();
		Company expected = new Company();
		expected.setCompanyId("C01");
		expected.setCompanyName("Citi");
		//expected.setAvailableFunds(199600.00f);
		Company actual = dao.findByCompanyId("C01");
		
		//assertEquals(expected.getAvailableFunds(), actual.getAvailableFunds());
		assertEquals(expected.getCompanyName(), actual.getCompanyName());
	}
	
	@Test
	void testGetCompanyId() {
		String expected = "C04";
		CompanyOperations dao = new CompanyOperationsImpl();
		String actual = dao.getCompanyId("JPMorgan");
		assertEquals(expected, actual);
	}
	
}
