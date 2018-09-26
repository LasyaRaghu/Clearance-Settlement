package com.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.beans.Security;
import com.operations.SecurityOperations;
import com.operations.impl.SecurityOperationsImpl;

class Securityimpl {

	@Test
	void testDisplayBorrowingRate() {
		SecurityOperations dao = new SecurityOperationsImpl();
		List<Security> actual = dao.displayBorrowingRate();
		List<Security> expected =new ArrayList<Security>();
		Security s01 = new Security("S01", "Apple", 101.10f, 0.07f);
		Security s02 = new Security("S02", "GE", 45.40f, 0.08f);
		Security s03 = new Security("S03", "Walmart", 76.50f, 0.07f);
		Security s04 = new Security("S04", "LinkedIn", 45.50f, 0.11f);
		Security s05 = new Security("S05", "Facebook", 155.00f, 0.06f);
		expected.add(s01);
		expected.add(s02);
		expected.add(s03);
		expected.add(s04);
		expected.add(s05);
		for(int i=0;i<5;i++) {
			assertEquals(expected.get(i).getBorrowingRate(), actual.get(i).getBorrowingRate());
		}
	}

}
