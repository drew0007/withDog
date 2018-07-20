package com.withdog.common;


//==>리스트화면을 모델링(추상화/캡슐화)한 Bean 
public class Search {
	
	///Field
	private int currentPage;
	private String searchCondition;
	private String searchKeyword;
	private int pageSize;
	//==> 리스트화면 currentPage에 해당하는 회원정보를 ROWNUM 사용 SELECT 위해 추가된 Field 
	//==> UserMapper.xml 의 
	//==> <select  id="getUserList"  parameterType="search"	resultMap="userSelectMap">
	//==> 참조
	private int endRowNum;
	private int startRowNum;
	private int priceUpDown;
	private int recently;
	private String searchStartDay;
	private String searchEndDay;
	
	//상품정렬
	private int prodSort;
	private int sorting;
	private String prodType;
	private String role;

	


	///Constructor
	public Search() {
	}
	
	///Method
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int paseSize) {
		this.pageSize = paseSize;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	
	//==> Select Query 시 ROWNUM 마지막 값 
	public int getEndRowNum() {
		return getCurrentPage()*getPageSize();
	}
	//==> Select Query 시 ROWNUM 시작 값
	public int getStartRowNum() {
		return (getCurrentPage()-1)*getPageSize()+1;
	}

	public int getSorting() {
		return sorting;
	}

	public void setSorting(int sorting) {
		this.sorting = sorting;
	}


	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	public String getSearchStartDay() {
		return searchStartDay;
	}

	public void setSearchStartDay(String searchStartDay) {
		this.searchStartDay = searchStartDay;
	}

	public String getSearchEndDay() {
		return searchEndDay;
	}

	public void setSearchEndDay(String searchEndDay) {
		this.searchEndDay = searchEndDay;
	}

	@Override
	public String toString() {
		return "Search [currentPage=" + currentPage + ", searchCondition=" + searchCondition + ", searchKeyword="
				+ searchKeyword + ", pageSize=" + pageSize + ", endRowNum=" + endRowNum + ", startRowNum=" + startRowNum
				+ ", priceUpDown=" + priceUpDown + ", recently=" + recently + ", sorting=" + sorting + ", prodType="
				+ prodType + ", role=" + role + "]"
				+ ", priceUpDown=" + priceUpDown + ", recently=" + recently + ", searchStartDay=" + searchStartDay
				+ ", searchEndDay=" + searchEndDay + ", prodSort=" + prodSort + ", sorting=" + sorting + "]";
	}



}