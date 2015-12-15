package com.yyang.project.ecommerce.products.review.core;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class Page<T> {

	@Getter
	private List<T> results;
	
	//start from 1, 0 means get all in one page
	@Getter
	private int pageNumber = 1;
	
	@Getter
	private int pageCount = 10;
	
	
	public Page() {
		results = new ArrayList<T>(10);
	}
	
	/**
	 * 
	 * @param pageNumber if is 0 will get all data
	 * @param pageCount if pageNumber is 0 this value will be ignore.
	 */
	public Page(int pageNumber, int pageCount) {
		this.pageNumber = pageNumber;
		
		if(pageNumber == 0) {
			results = new ArrayList<T>(10);
		}else {
			this.pageCount = pageCount;
			revertResults();
		}
		
	}
	
	public Page(int pageCount) {
		this.pageCount = pageCount;
		revertResults();
	}
	
	public void addItem(T item) {
		results.add(item);
	}
	
	public int getBeginNumber() {
		int beginNumber = 0;
		switch (Math.abs(pageNumber)) {
		case 0:
			beginNumber = 0;
			break;
		default:
			beginNumber = Math.abs(pageNumber) - 1;
			break;
		}

		return beginNumber;
	}
	
	public int getEndNumber() {
		int endNumber = 0;
		switch (Math.abs(pageNumber)) {
		case 0:
			endNumber = Integer.MAX_VALUE;
			break;
		default:
			endNumber = Math.abs(pageNumber) * pageCount - 1;
			break;
		}

		return endNumber;
	}
	
	public void revertResults() {
		if(pageCount > 1000 || pageCount == 0)
			this.results = new ArrayList<T>(10);
		else
			this.results = new ArrayList<T>(Math.abs(pageCount));
	}
	
}
