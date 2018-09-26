package com.operations;

import java.util.List;

import com.beans.Manages;
import com.beans.Security;

public interface ManagesOperations {
	List<Manages> securitiesShortage();
	
	Manages findByCompSec(String companyId, String securityId);
	List<Manages> findByComp(String companyId);
}
