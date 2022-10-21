package com.iu.home.util;

import lombok.Data;

@Data
public class Pager {

	private Long startRow;
	private Long page;
	private Long perPage;
	
	public void getRow() {
		this.startRow = (this.getPage() - 1) * this.getPerPage();
	}
	
	public Long getPage() {
		if(this.page == null || this.page < 1) {
			this.page = 1L;
		}
		return this.page;
	}
	
	public Long getPerPage() {
		if(this.perPage == null) {
			this.perPage = 10L;
		}
		return this.perPage;
	}
}