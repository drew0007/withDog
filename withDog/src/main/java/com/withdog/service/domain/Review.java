package com.withdog.service.domain;

import java.sql.Date;


public class Review {
	
	private User user;
	private Product product;
	private Purchase purchase;
	private String reviewTitle;
	private String reviewContent;
	private int reviewScore;
	private Date regDate;
	private String deleteFlag;
	
	public Review(){
	}

	
	public Purchase getPurchase() {
		return purchase;
	}


	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}


	public String getReviewTitle() {
		return reviewTitle;
	}


	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}


	public String getReviewContent() {
		return reviewContent;
	}


	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}


	public int getReviewScore() {
		return reviewScore;
	}


	public void setReviewScore(int reviewScore) {
		this.reviewScore = reviewScore;
	}


	public Date getRegDate() {
		return regDate;
	}


	public void setRegDate(Date regDate) {
		this.regDate = regDate;
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
		return "Review [user=" + user + ", product=" + product + ", purchase=" + purchase + ", reviewTitle="
				+ reviewTitle + ", reviewContent=" + reviewContent + ", reviewScore=" + reviewScore + ", regDate="
				+ regDate + ", deleteFlag=" + deleteFlag + "]";
	}


	
	
}