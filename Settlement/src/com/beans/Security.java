package com.beans;

public class Security {
	private String securityId;
	private String securityName;
	private float marketPrice;
	private float borrowingRate;
	public Security() {
		
	}
	public Security(String securityId, String securityName, float marketPrice, float borrowingRate) {
		super();
		this.securityId = securityId;
		this.securityName = securityName;
		this.marketPrice = marketPrice;
		this.borrowingRate = borrowingRate;
	}
	public String getSecurityId() {
		return securityId;
	}
	public void setSecurityId(String securityId) {
		this.securityId = securityId;
	}
	public String getSecurityName() {
		return securityName;
	}
	public void setSecurityName(String securityName) {
		this.securityName = securityName;
	}
	public float getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(float marketPrice) {
		this.marketPrice = marketPrice;
	}
	public float getBorrowingRate() {
		return borrowingRate;
	}
	public void setBorrowingRate(float borrowingRate) {
		this.borrowingRate = borrowingRate;
	}
	

}
