package com.withdog.service.domain;

import java.sql.Date;

public class User {

	/// Field
	private String userId;
	private String password;
	private String userName;
	private String birth;
	private String email1;
	private String email2;
	private String phone;
	private String postNo;
	private String address1;
	private String address2;
	private int  dogNo; // 좋아하는 견종
	private String userCondition; // 계정상태 : 정상, 휴면, 탈퇴
	private Date recentlyDate;
	private Date joinDate;
	private Date leaveDate;
	private String leaveReason;
	private String role;
	private int currentPoint; // 현재 포인트
	private String snsNaverId;
	private String snsKakaoId;
	private String snsGoogleId;
	private String snsFacebookId;

	/// Constructor
	public User() {
	}

	/// Method
	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostNo() {
		return postNo;
	}

	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public int getDogNo() {
		return dogNo;
	}

	public void setDogNo(int dogNo) {
		this.dogNo = dogNo;
	}

	public String getUserCondition() {
		return userCondition;
	}

	public void setUserCondition(String userCondition) {
		this.userCondition = userCondition;
	}

	public Date getRecentlyDate() {
		return recentlyDate;
	}

	public void setRecentlyDate(Date recentlyDate) {
		this.recentlyDate = recentlyDate;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	public String getLeaveReason() {
		return leaveReason;
	}

	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getCurrentPoint() {
		return currentPoint;
	}

	public void setCurrentPoint(int currentPoint) {
		this.currentPoint = currentPoint;
	}

	public String getSnsNaverId() {
		return snsNaverId;
	}

	public void setSnsNaverId(String snsNaverId) {
		this.snsNaverId = snsNaverId;
	}

	public String getSnsKakaoId() {
		return snsKakaoId;
	}

	public void setSnsKakaoId(String snsKakaoId) {
		this.snsKakaoId = snsKakaoId;
	}

	public String getSnsGoogleId() {
		return snsGoogleId;
	}

	public void setSnsGoogleId(String snsGoogleId) {
		this.snsGoogleId = snsGoogleId;
	}

	public String getSnsFacebookId() {
		return snsFacebookId;
	}

	public void setSnsFacebookId(String snsFacebookId) {
		this.snsFacebookId = snsFacebookId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", userName=" + userName + ", birth=" + birth
				+ ", email1=" + email1 + ", email2=" + email2 + ", phone=" + phone + ", postNo=" + postNo
				+ ", address1=" + address1 + ", address2=" + address2 + ", dogNo=" + dogNo + ", userCondition="
				+ userCondition + ", recentlyDate=" + recentlyDate + ", joinDate=" + joinDate + ", leaveDate="
				+ leaveDate + ", leaveReason=" + leaveReason + ", role=" + role + ", currentPoint=" + currentPoint
				+ ", snsNaverId=" + snsNaverId + ", snsKakaoId=" + snsKakaoId + ", snsGoogleId=" + snsGoogleId
				+ ", snsFacebookId=" + snsFacebookId + "]";
	}

	

}