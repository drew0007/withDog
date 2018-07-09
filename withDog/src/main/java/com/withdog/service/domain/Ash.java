package com.withdog.service.domain;

public class Ash {
	private HealingDog healingDog;
	private User user;
	private int ashReservationNo;
	private String ashReservationCondition;
	private String ashReservationName;
	private String ashReservationDate;
	private String ashReservationTime;
	private String ashReservationAddress;
	private String ashReservationPhone;
	private String ashReservationEtc;
	private String ashReservationPrice;
	private String paymentOption;
	private String purchaseDate;

	public HealingDog getHealingDog() {
		return healingDog;
	}

	public void setHealingDog(HealingDog healingDog) {
		this.healingDog = healingDog;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getAshReservationNo() {
		return ashReservationNo;
	}

	public void setAshReservationNo(int ashReservationNo) {
		this.ashReservationNo = ashReservationNo;
	}

	public String getAshReservationCondition() {
		return ashReservationCondition;
	}

	public void setAshReservationCondition(String ashReservationCondition) {
		this.ashReservationCondition = ashReservationCondition;
	}

	public String getAshReservationName() {
		return ashReservationName;
	}

	public void setAshReservationName(String ashReservationName) {
		this.ashReservationName = ashReservationName;
	}

	public String getAshReservationDate() {
		return ashReservationDate;
	}

	public void setAshReservationDate(String ashReservationDate) {
		this.ashReservationDate = ashReservationDate;
	}

	public String getAshReservationTime() {
		return ashReservationTime;
	}

	public void setAshReservationTime(String ashReservationTime) {
		this.ashReservationTime = ashReservationTime;
	}

	public String getAshReservationAddress() {
		return ashReservationAddress;
	}

	public void setAshReservationAddress(String ashReservationAddress) {
		this.ashReservationAddress = ashReservationAddress;
	}

	public String getAshReservationPhone() {
		return ashReservationPhone;
	}

	public void setAshReservationPhone(String ashReservationPhone) {
		this.ashReservationPhone = ashReservationPhone;
	}

	public String getAshReservationEtc() {
		return ashReservationEtc;
	}

	public void setAshReservationEtc(String ashReservationEtc) {
		this.ashReservationEtc = ashReservationEtc;
	}

	public String getAshReservationPrice() {
		return ashReservationPrice;
	}

	public void setAshReservationPrice(String ashReservationPrice) {
		this.ashReservationPrice = ashReservationPrice;
	}

	public String getPaymentOption() {
		return paymentOption;
	}

	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	@Override
	public String toString() {
		return "Ash [healingDog=" + healingDog + ", ashReservationNo=" + ashReservationNo + ", ashReservationCondition="
				+ ashReservationCondition + ", ashReservationName=" + ashReservationName + ", ashReservationDate="
				+ ashReservationDate + ", ashReservationTime=" + ashReservationTime + ", ashReservationAddress="
				+ ashReservationAddress + ", ashReservationPhone=" + ashReservationPhone + ", ashReservationEtc="
				+ ashReservationEtc + ", ashReservationPrice=" + ashReservationPrice + ", paymentOption="
				+ paymentOption + ", purchaseDate=" + purchaseDate + "]";
	}

}
