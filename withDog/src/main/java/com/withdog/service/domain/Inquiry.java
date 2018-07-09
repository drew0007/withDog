package com.withdog.service.domain;

import java.sql.Date;


public class Inquiry {
	
	private int inquiryNo;
	private User user;
	private Product product;
	private String inquiryTitle;
	private String iquiryContent;
	private Date regDate;
	private String secret;
	private String replyCondition;
	private String inquiryReply;
	private String deleteFlag;
	
	public Inquiry(){
	}

	
	public int getInquiryNo() {
		return inquiryNo;
	}


	public void setInquiryNo(int inquiryNo) {
		this.inquiryNo = inquiryNo;
	}


	public String getInquiryTitle() {
		return inquiryTitle;
	}


	public void setInquiryTitle(String inquiryTitle) {
		this.inquiryTitle = inquiryTitle;
	}


	public String getIquiryContent() {
		return iquiryContent;
	}


	public void setIquiryContent(String iquiryContent) {
		this.iquiryContent = iquiryContent;
	}


	public Date getRegDate() {
		return regDate;
	}


	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}


	public String getSecret() {
		return secret;
	}


	public void setSecret(String secret) {
		this.secret = secret;
	}


	public String getReplyCondition() {
		return replyCondition;
	}


	public void setReplyCondition(String replyCondition) {
		this.replyCondition = replyCondition;
	}


	public String getInquiryReply() {
		return inquiryReply;
	}


	public void setInquiryReply(String inquiryReply) {
		this.inquiryReply = inquiryReply;
	}


	public String getDeleteFlag() {
		return deleteFlag;
	}


	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
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


	@Override
	public String toString() {
		return "Inquiry [inquiryNo=" + inquiryNo + ", user=" + user + ", product=" + product + ", inquiryTitle="
				+ inquiryTitle + ", iquiryContent=" + iquiryContent + ", regDate=" + regDate + ", secret=" + secret
				+ ", replyCondition=" + replyCondition + ", inquiryReply=" + inquiryReply + ", deleteFlag=" + deleteFlag
				+ "]";
	}


	
	
	
}