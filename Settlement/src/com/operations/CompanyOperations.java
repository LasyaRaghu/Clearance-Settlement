package com.operations;

import java.util.List;

import com.beans.Company;

public interface CompanyOperations {

	List<Company> fundsShortage();
	Company fundsShortageByCompany(String CompanyId);
	String getCompanyId(String username);
	Company findByCompanyId(String companyId);
}
