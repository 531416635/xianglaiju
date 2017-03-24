package com.yao.vo;

public class PageVO {

	private int page; // 当前页数
	private int rows; // 分页行数
	private int totalRow; // 总行数
	private int startPage;// 起始行
	private int endPage;// 结束行
	private int totalPage; // 总页数

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page < 1) {
			page = 1;
		} else {
			startPage = rows * (page - 1);
		}
		endPage = startPage + rows > totalRow ? totalRow : startPage
				+ rows;
		this.page = page;
	}

	public int getStartPage() {
		// startPage=page*rows;
		return startPage;
	}

	public int getEndPage() {

		return endPage;
	}

	public int getrows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(int totalRow) {
		totalPage = (totalRow + rows - 1) / rows;
		this.totalRow = totalRow;
		if (totalPage <= page) {
			page = totalPage;
			endPage = totalRow;
		}
		startPage = rows * (page - 1);
		endPage = startPage + rows > totalRow ? totalRow : startPage
				+ rows;
	}

	public int getTotalPage() {
		return this.totalPage;
	}

}
