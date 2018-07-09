package com.withdog.service.domain;

public class DogInfo {
	private User user;
	private int dogInfoNo;
	private String dogInfoTitle;
	private String dogInfoContent;
	private String dogInfoTopic;
	private String dogInfoImage;
	private String recommended;
	private String notRecommended;
	private String viewCount;
	private String recommendCondition;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getDogInfoNo() {
		return dogInfoNo;
	}

	public void setDogInfoNo(int dogInfoNo) {
		this.dogInfoNo = dogInfoNo;
	}

	public String getDogInfoTitle() {
		return dogInfoTitle;
	}

	public void setDogInfoTitle(String dogInfoTitle) {
		this.dogInfoTitle = dogInfoTitle;
	}

	public String getDogInfoContent() {
		return dogInfoContent;
	}

	public void setDogInfoContent(String dogInfoContent) {
		this.dogInfoContent = dogInfoContent;
	}

	public String getDogInfoTopic() {
		return dogInfoTopic;
	}

	public void setDogInfoTopic(String dogInfoTopic) {
		this.dogInfoTopic = dogInfoTopic;
	}

	public String getDogInfoImage() {
		return dogInfoImage;
	}

	public void setDogInfoImage(String dogInfoImage) {
		this.dogInfoImage = dogInfoImage;
	}

	public String getRecommended() {
		return recommended;
	}

	public void setRecommended(String recommended) {
		this.recommended = recommended;
	}

	public String getNotRecommended() {
		return notRecommended;
	}

	public void setNotRecommended(String notRecommended) {
		this.notRecommended = notRecommended;
	}

	public String getViewCount() {
		return viewCount;
	}

	public void setViewCount(String viewCount) {
		this.viewCount = viewCount;
	}

	public String getRecommendCondition() {
		return recommendCondition;
	}

	public void setRecommendCondition(String recommendCondition) {
		this.recommendCondition = recommendCondition;
	}

	@Override
	public String toString() {
		return "DogInfo [dogInfoNo=" + dogInfoNo + ", dogInfoTitle=" + dogInfoTitle + ", dogInfoContent="
				+ dogInfoContent + ", dogInfoTopic=" + dogInfoTopic + ", dogInfoImage=" + dogInfoImage
				+ ", recommended=" + recommended + ", notRecommended=" + notRecommended + ", viewCount=" + viewCount
				+ ", recommendCondition=" + recommendCondition + "]";
	}

}
