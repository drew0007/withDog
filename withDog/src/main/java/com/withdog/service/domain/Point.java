package com.withdog.service.domain;

import java.sql.Date;

public class Point {

	//private User user;
	//private Purchase purchase;
	//privaet Ash ash;
	private Fund fund;
	private int pointNo;
	private Date pointDate;
	private int usePoint;
	private int point;
	private int currentPoint;
	private int pointHistory;
	
	public Point() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Point [fund=" + fund + ", pointNo=" + pointNo + ", pointDate=" + pointDate + ", usePoint=" + usePoint
				+ ", point=" + point + ", currentPoint=" + currentPoint + ", pointHistory=" + pointHistory + "]";
	}

	
	
	
}
