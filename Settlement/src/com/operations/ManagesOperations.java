package com.operations;

import java.util.List;

import com.beans.Manages;
import com.beans.Security;

public interface ManagesOperations {
	List<Manages> securitiesShortage();
	List<Manages> securitiesShortageByCompany(String CompanyId);
}
