package com.withdog.service.domain;

import java.sql.Date;

import com.withdog.service.domain.Product;
import com.withdog.service.domain.User;

public class Purchase {
	
	private User user;
	private Product product;
	private int purchaseNo;
	private String receiverName;
	private String receiverPhone;
	private String receiverAddr;
	private String divyRequest;
	private int purchaseQuantity;
	private int purchasePrice;
	private Date PurchaseDate;
	private String purchaseCondition;
	private Date cancelDate;
	private Date divyDate;
	private String paymentOption;
	
	public Purchase(){
	}
	
	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public int getPurchaseNo() {
		return purchaseNo;
	}


	public void setPurchaseNo(int purchaseNo) {
		this.purchaseNo = purchaseNo;
	}


	public String getReceiverAddr() {
		return receiverAddr;
	}


	public void setReceiverAddr(String receiverAddr) {
		this.receiverAddr = receiverAddr;
	}


	public int getPurchaseQuantity() {
		return purchaseQuantity;
	}


	public void setPurchaseQuantity(int purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}


	public int getPurchasePrice() {
		return purchasePrice;
	}


	public void setPurchasePrice(int purchasePrice) {
		this.purchasePrice = purchasePrice;
	}


	public Date getPurchaseDate() {
		return PurchaseDate;
	}


	public void setPurchaseDate(Date purchaseDate) {
		PurchaseDate = purchaseDate;
	}


	public String getPurchaseCondition() {
		return purchaseCondition;
	}


	public void setPurchaseCondition(String purchaseCondition) {
		this.purchaseCondition = purchaseCondition;
	}


	public Date getCancelDate() {
		return cancelDate;
	}


	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}


	public void setDivyDate(Date divyDate) {
		this.divyDate = divyDate;
	}


	public String getDivyRequest() {
		return divyRequest;
	}
	public void setDivyRequest(String divyRequest) {
		this.divyRequest = divyRequest;
	}
	public String getPaymentOption() {
		return paymentOption;
	}
	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}


	@Override
	public String toString() {
		return "Purchase [user=" + user + ", product=" + product + ", purchaseNo=" + purchaseNo + ", receiverName="
				+ receiverName + ", receiverPhone=" + receiverPhone + ", receiverAddr=" + receiverAddr
				+ ", divyRequest=" + divyRequest + ", purchaseQuantity=" + purchaseQuantity + ", purchasePrice="
				+ purchasePrice + ", PurchaseDate=" + PurchaseDate + ", purchaseCondition=" + purchaseCondition
				+ ", cancelDate=" + cancelDate + ", divyDate=" + divyDate + ", paymentOption=" + paymentOption + "]";
	}
	
	
}