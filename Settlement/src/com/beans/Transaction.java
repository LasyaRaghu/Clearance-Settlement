package com.beans;

public class Transaction {
	private int transId;
	private String buyerCompId;
	private String securityId;
	private String sellerCompId;
	private int quantity;
	private float price;
	
	public Transaction() {
		
	}
	public Transaction(int transId, String buyerCompId,String securityId,String sellerCompId,int quantity, float price) {
		super();
		this.transId = transId;
		this.buyerCompId = buyerCompId;
		this.securityId = securityId;
		this.sellerCompId = sellerCompId;
		this.quantity=quantity;
		this.price =  price;
	}
	public int getTransId() {
		return transId;
	}
	public void setTransId(int transId) {
		this.transId = transId;
	}
	public String getBuyerCompId() {
		return buyerCompId;
	}
	public void setBuyerCompId(String buyerCompId) {
		this.buyerCompId = buyerCompId;
	}
	public String getSecurityId() {
		return securityId;
	}
	public void setSecurityId(String securityId) {
		this.securityId = securityId;
	}
	public String getSellerCompId() {
		return sellerCompId;
	}
	public void setSellerCompId(String sellerCompId) {
		this.sellerCompId = sellerCompId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
  
}
