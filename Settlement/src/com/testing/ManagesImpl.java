package com.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;


import com.beans.Manages;
import com.operations.ManagesOperations;
import com.operations.impl.ManagesOperationsImpl;

class ManagesImpl {
	
	@Test
	void testSecurityShortage() {
		ManagesOperations dao1 = new ManagesOperationsImpl();
		float expected = 106.8f;
		List <Manages> funds = dao1.securitiesShortage();
		Manages actual = dao1.findByCompSec("C02", "S02");
		assertEquals(expected,actual.getCostOfSettlementForSecurities());
		
	}
	@Test
	void testFindByCompSec() {
		ManagesOperations dao1 = new ManagesOperationsImpl();
		Manages expected = new Manages();
		expected.setCompanyId("C01");
		expected.setSecurityId("S01");;
		//expected.setAvailableSecurities(availableSecurities);
		//Company actual = dao1.findByCompanyId("C01");
	}

}
