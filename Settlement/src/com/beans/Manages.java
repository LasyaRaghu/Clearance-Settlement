package com.beans;

public class Manages {
    private String companyId;
    private String securityId;
    private int availableSecurities;
    private int nettedSecurities;
    private float costOfSettlementForSecurities;
    
    public Manages() {
    	
    }
	public Manages(String companyId, String securityId, int availableSecurities, int nettedSecurities, float costOfSettlementForSecurities) {
		super();
		this.companyId = companyId;
		this.securityId = securityId;
		this.availableSecurities = availableSecurities;
		this.nettedSecurities = nettedSecurities;
		this.costOfSettlementForSecurities = costOfSettlementForSecurities;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public float getCostOfSettlementForSecurities() {
		return costOfSettlementForSecurities;
	}
	public void setCostOfSettlementForSecurities(float costOfSettlementForSecurities) {
		this.costOfSettlementForSecurities = costOfSettlementForSecurities;
	}
	public String getSecurityId() {
		return securityId;
	}
	public void setSecurityId(String securityId) {
		this.securityId = securityId;
	}
	public int getAvailableSecurities() {
		return availableSecurities;
	}
	public void setAvailableSecurities(int availableSecurities) {
		this.availableSecurities = availableSecurities;
	}
	public int getNettedSecurities() {
		return nettedSecurities;
	}
	public void setNettedSecurities(int nettedSecurities) {
		this.nettedSecurities = nettedSecurities;
	}
    
}
