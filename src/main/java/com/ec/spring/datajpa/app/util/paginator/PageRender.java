package com.ec.spring.datajpa.app.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {

	private String url;
	private Page<T> page;
	
	private int totalPages;
	private int numElementsPage;
	private int actualpage; 
	
	private List<PageItem> pages;
	
	public PageRender(String url, Page<T> page) {
		super();
		this.url = url;
		this.page = page;
		this.pages = new ArrayList<PageItem>(); 
 		
		numElementsPage = page.getSize();
		totalPages = page.getTotalPages();
		actualpage = page.getNumber() + 1;
		
		int from, until;
		if (totalPages <= numElementsPage) {
			from = 1;
			until = totalPages;
		} else {
			if (actualpage <= numElementsPage/2) {
				from = 1;
				until = numElementsPage;
			}else if (actualpage >= totalPages -  numElementsPage/2) {
				from = totalPages - numElementsPage + 1;
				until = numElementsPage;
			} else {
				from = actualpage - numElementsPage/2;
				until = numElementsPage;
			}
		}
		
		
		for (int i = 0; i < until; i++) {
			pages.add(new PageItem(from + i, actualpage == from + i));
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getActualpage() {
		return actualpage;
	}

	public void setActualpage(int actualpage) {
		this.actualpage = actualpage;
	}

	public List<PageItem> getPages() {
		return pages;
	}

	public void setPages(List<PageItem> pages) {
		this.pages = pages;
	}
	
	public boolean isFirst() {
		return page.isFirst();
	}
	
	public boolean isLast() {
		return page.isLast();
	}
	
	public boolean isHashNext() {
		return page.hasNext();
	}
	
	public boolean isHashPrevious() {
		return page.hasPrevious();
	}
}


