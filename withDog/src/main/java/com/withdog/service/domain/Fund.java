package com.withdog.service.domain;

import java.sql.Date;

public class Fund {
	
	
	public Fund() {
		super();
		
	}
	//private User user;
	private Point point;
	private int fundNo;
	private String fundTitle;
	private String fundImg;
	private String fundCenter;
	private String fundPhone;
	private String fundTerm;
	private int fundRaising;
	private int fundPersonnel;
	private int fundMyPriceNo;
	private int fundPay;
	private int fundResultPrice;
	private String fundState;
	private int fundMyPrice;
	private Date fundMyDate;
	private String paymentOption;
	private String reqName;
	private String reqCenter;
	private String reqPhone;
	private String reqDetail;
	private String reqAccount;
	private String reqFile;
	
	
	
	
	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
	public int getFundNo() {
		return fundNo;
	}
	public void setFundNo(int fundNo) {
		this.fundNo = fundNo;
	}
	public String getFundTitle() {
		return fundTitle;
	}
	public void setFundTitle(String fundTitle) {
		this.fundTitle = fundTitle;
	}
	public String getFundImg() {
		return fundImg;
	}
	public void setFundImg(String fundImg) {
		this.fundImg = fundImg;
	}
	public String getFundCenter() {
		return fundCenter;
	}
	public void setFundCenter(String fundCenter) {
		this.fundCenter = fundCenter;
	}
	public String getFundPhone() {
		return fundPhone;
	}
	public void setFundPhone(String fundPhone) {
		this.fundPhone = fundPhone;
	}
	public String getFundTerm() {
		return fundTerm;
	}
	public void setFundTerm(String fundTerm) {
		this.fundTerm = fundTerm;
	}
	public int getFundRaising() {
		return fundRaising;
	}
	public void setFundRaising(int fundRaising) {
		this.fundRaising = fundRaising;
	}
	public int getFundPersonnel() {
		return fundPersonnel;
	}
	public void setFundPersonnel(int fundPersonnel) {
		this.fundPersonnel = fundPersonnel;
	}
	public int getFundMyPriceNo() {
		return fundMyPriceNo;
	}
	public void setFundMyPriceNo(int fundMyPriceNo) {
		this.fundMyPriceNo = fundMyPriceNo;
	}
	public int getFundPay() {
		return fundPay;
	}
	public void setFundPay(int fundPay) {
		this.fundPay = fundPay;
	}
	public int getFundResultPrice() {
		return fundResultPrice;
	}
	public void setFundResultPrice(int fundResultPrice) {
		this.fundResultPrice = fundResultPrice;
	}
	public String getFundState() {
		return fundState;
	}
	public void setFundState(String fundState) {
		this.fundState = fundState;
	}
	public int getFundMyPrice() {
		return fundMyPrice;
	}
	public void setFundMyPrice(int fundMyPrice) {
		this.fundMyPrice = fundMyPrice;
	}
	public Date getFundMyDate() {
		return fundMyDate;
	}
	public void setFundMyDate(Date fundMyDate) {
		this.fundMyDate = fundMyDate;
	}
	public String getReqName() {
		return reqName;
	}
	public void setReqName(String reqName) {
		this.reqName = reqName;
	}
	public String getReqCenter() {
		return reqCenter;
	}
	public void setReqCenter(String reqCenter) {
		this.reqCenter = reqCenter;
	}
	public String getReqPhone() {
		return reqPhone;
	}
	public void setReqPhone(String reqPhone) {
		this.reqPhone = reqPhone;
	}
	public String getReqDetail() {
		return reqDetail;
	}
	public void setReqDetail(String reqDetail) {
		this.reqDetail = reqDetail;
	}
	public String getReqAccount() {
		return reqAccount;
	}
	public void setReqAccount(String reqAccount) {
		this.reqAccount = reqAccount;
	}
	public String getReqFile() {
		return reqFile;
	}
	public void setReqFile(String reqFile) {
		this.reqFile = reqFile;
	}
	public String getPaymentOption() {
		return paymentOption;
	}
	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption;
	}
	@Override
	public String toString() {
		return "Fund [point=" + point + ", fundNo=" + fundNo + ", fundTitle=" + fundTitle + ", fundImg=" + fundImg
				+ ", fundCenter=" + fundCenter + ", fundPhone=" + fundPhone + ", fundTerm=" + fundTerm
				+ ", fundRaising=" + fundRaising + ", fundPersonnel=" + fundPersonnel + ", fundMyPriceNo="
				+ fundMyPriceNo + ", fundPay=" + fundPay + ", fundResultPrice=" + fundResultPrice + ", fundState="
				+ fundState + ", fundMyPrice=" + fundMyPrice + ", fundMyDate=" + fundMyDate + ", paymentOption="
				+ paymentOption + ", reqName=" + reqName + ", reqCenter=" + reqCenter + ", reqPhone=" + reqPhone
				+ ", reqDetail=" + reqDetail + ", reqAccount=" + reqAccount + ", reqFile=" + reqFile + "]";
	}
	
	
	

}
