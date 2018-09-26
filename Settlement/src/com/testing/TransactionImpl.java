package com.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.beans.Company;
import com.beans.Manages;
import com.beans.Transaction;
import com.operations.TransactionOperations;
import com.operations.impl.CompanyOperationsImpl;
import com.operations.impl.ManagesOperationsImpl;
import com.operations.impl.TransactionOperationsImpl;

class TransactionImpl {

//	@Test
//	void testLogin() {
//		TransactionOperations dao = new TransactionOperationsImpl();
//		String name = "Citi";
//		String password ="Citi123";
//		boolean expected = true;
//		boolean actual = dao.Login(name, password);
//		assertEquals(expected, actual);
//	}
//
//	@Test
//	void testNetFunds() {
//		TransactionOperations dao = new TransactionOperationsImpl();
//		CompanyOperationsImpl dao1 = new CompanyOperationsImpl();
//		
//		float expected = -13229600.00f;
//		boolean val = dao.NettingFunds();
//		Company company =dao1.findByCompanyId("C01");
//		assertEquals(expected, company.getNettedFunds());
//		
//	}
//	
//	@Test
//	void testNetSecurities() {
//		TransactionOperations dao = new TransactionOperationsImpl();
//		ManagesOperationsImpl dao1 = new ManagesOperationsImpl();
//		int expected=2000;
//		boolean val = dao.NettingShares();
//		Manages actual = dao1.findByCompSec("C01", "S01");
//		assertEquals(expected, actual.getNettedSecurities());
//	}
//	
//	@Test
//	void testFindAll() {
//		TransactionOperations dao = new TransactionOperationsImpl();
//		List<Transaction> actual = dao.findAll();
//		List<Transaction> expected = new ArrayList<Transaction>();
//		Transaction t01 = new Transaction(1, "C02", "S01", "C04", 22000, 101.20f);
//		Transaction t02 = new Transaction(2, "C01", "S01", "C02", 2000, 99.80f);
//		Transaction t03 = new Transaction(3, "C02", "S03", "C03", 12000, 4.00f);
//		Transaction t04 = new Transaction(4, "C03", "S04", "C01", 7000, 47.00f);
//		expected.add(t01);
//		expected.add(t02);
//		expected.add(t03);
//		expected.add(t04);
//		for(int i=0;i<4;i++) {
//			assertEquals(expected.get(i).getQuantity(), actual.get(i).getQuantity());
//			assertEquals(expected.get(i).getPrice(), actual.get(i).getPrice());
//		}
//	}
//	
//	@Test
//	void testFindTransactionBySecurity() {
//		TransactionOperations dao = new TransactionOperationsImpl();
//		List<Transaction> actual = dao.findTransactionBySecurity("S01");
//		List<Transaction> expected = new ArrayList<>();
//		Transaction t01 = new Transaction(1, "C02", "S01", "C04", 22000, 101.20f);
//		Transaction t02 = new Transaction(2, "C01", "S01", "C02", 2000, 99.80f);
//		Transaction t03 = new Transaction(9, "C02", "S01", "C04", 10000, 100.10f);
//		Transaction t04 = new Transaction(20, "C04", "S01", "C03", 30000, 102.00f);
//		expected.add(t01);
//		expected.add(t02);
//		expected.add(t03);
//		expected.add(t04);
//		for(int i=0;i<4;i++) {
//			assertEquals(expected.get(i).getBuyerCompId(), actual.get(i).getBuyerCompId());
//			assertEquals(expected.get(i).getSecurityId(), actual.get(i).getSecurityId());
//			assertEquals(expected.get(i).getPrice(), actual.get(i).getPrice());
//			assertEquals(expected.get(i).getQuantity(), actual.get(i).getQuantity());
//		}
//		
//	}
//	
//	@Test
//	void testFindTransactionByBuyer() {
//		TransactionOperations dao = new TransactionOperationsImpl();
//		List<Transaction> actual = dao.findTransactionByBuyer("C04");
//		List<Transaction> expected = new ArrayList<>();
//		Transaction t01 = new Transaction(10, "C04", "S04", "C03", 25000, 44.50f);
//		Transaction t02 = new Transaction(12, "C04", "S04", "C02", 40000, 45.00f);
//		Transaction t03 = new Transaction(20, "C04", "S01", "C03", 30000, 102.00f);
//		expected.add(t01);
//		expected.add(t02);
//		expected.add(t03);
//		for(int i=0;i<3;i++) {
//			assertEquals(expected.get(i).getSecurityId(), actual.get(i).getSecurityId());
//			assertEquals(expected.get(i).getPrice(), actual.get(i).getPrice());
//			assertEquals(expected.get(i).getQuantity(), actual.get(i).getQuantity());
//		}
//	}
//	
//	@Test
//	void testFindTransactionBySeller() {
//		TransactionOperations dao = new TransactionOperationsImpl();
//		List<Transaction> actual = dao.findTransactionBySeller("C01");
//		List<Transaction> expected = new ArrayList<>();
//		Transaction t01 = new Transaction(4, "C03", "S04", "C01", 7000, 47.00f);
//		Transaction t02 = new Transaction(8, "C03", "S04", "C01", 100000, 46.00f);
//		Transaction t03 = new Transaction(14, "C03", "S05", "C01", 12000, 156.00f);
//		Transaction t04 = new Transaction(16, "C02", "S05", "C01", 72000, 159.00f);
//		expected.add(t01);
//		expected.add(t02);
//		expected.add(t03);
//		expected.add(t04);
//		for(int i=0;i<4;i++) {
//			assertEquals(expected.get(i).getBuyerCompId(), actual.get(i).getBuyerCompId());
//			assertEquals(expected.get(i).getSecurityId(), actual.get(i).getSecurityId());
//			assertEquals(expected.get(i).getPrice(), actual.get(i).getPrice());
//			assertEquals(expected.get(i).getQuantity(), actual.get(i).getQuantity());
//		}
//	}
	
//	@Test
//	void testAddTransaction() {
//		TransactionOperations dao = new TransactionOperationsImpl();
//		
//	}
}
