package com.beans;

public class Company {

	private String companyId;
	private String companyName;
	private float availableFunds;
	private float nettedFunds;
	private float costOfSettlementForFunds;
	
	public Company()
	{
		
	}
	public Company(String companyId, String companyName, float availableFunds, float nettedFunds, float costOfSettlementForFunds) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
		this.availableFunds = availableFunds;
		this.nettedFunds = nettedFunds;
		this.costOfSettlementForFunds = costOfSettlementForFunds;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public float getAvailableFunds() {
		return availableFunds;
	}
	public void setAvailableFunds(float availableFunds) {
		this.availableFunds = availableFunds;
	}
	public float getNettedFunds() {
		return nettedFunds;
	}
	public void setNettedFunds(float nettedFunds) {
		this.nettedFunds = nettedFunds;
	}
	public float getCostOfSettlementForFunds() {
		return costOfSettlementForFunds;
	}
	public void setCostOfSettlementForFunds(float costOfSettlementForFunds) {
		this.costOfSettlementForFunds = costOfSettlementForFunds;
	}
	
	
}
