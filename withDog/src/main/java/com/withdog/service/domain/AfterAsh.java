package com.withdog.service.domain;

public class AfterAsh {
	private HealingDog healingDog;
	private int afterASHNo;
	private String afterASHTitle;
	private String afterASHContent;
	private String afterASHImage;
	private String afterASHVideo;
	private String afterASHDate;
	private String afterASHTime;

	public HealingDog getHealingDog() {
		return healingDog;
	}

	public void setHealingDog(HealingDog healingDog) {
		this.healingDog = healingDog;
	}

	public int getAfterASHNo() {
		return afterASHNo;
	}

	public void setAfterASHNo(int afterASHNo) {
		this.afterASHNo = afterASHNo;
	}

	public String getAfterASHTitle() {
		return afterASHTitle;
	}

	public void setAfterASHTitle(String afterASHTitle) {
		this.afterASHTitle = afterASHTitle;
	}

	public String getAfterASHContent() {
		return afterASHContent;
	}

	public void setAfterASHContent(String afterASHContent) {
		this.afterASHContent = afterASHContent;
	}

	public String getAfterASHImage() {
		return afterASHImage;
	}

	public void setAfterASHImage(String afterASHImage) {
		this.afterASHImage = afterASHImage;
	}

	public String getAfterASHVideo() {
		return afterASHVideo;
	}

	public void setAfterASHVideo(String afterASHVideo) {
		this.afterASHVideo = afterASHVideo;
	}

	public String getAfterASHDate() {
		return afterASHDate;
	}

	public void setAfterASHDate(String afterASHDate) {
		this.afterASHDate = afterASHDate;
	}

	public String getAfterASHTime() {
		return afterASHTime;
	}

	public void setAfterASHTime(String afterASHTime) {
		this.afterASHTime = afterASHTime;
	}

	@Override
	public String toString() {
		return "AfterASH [afterASHNo=" + afterASHNo + ", afterASHTitle=" + afterASHTitle + ", afterASHContent="
				+ afterASHContent + ", afterASHImage=" + afterASHImage + ", afterASHVideo=" + afterASHVideo
				+ ", afterASHDate=" + afterASHDate + ", afterASHTime=" + afterASHTime + "]";
	}
}
