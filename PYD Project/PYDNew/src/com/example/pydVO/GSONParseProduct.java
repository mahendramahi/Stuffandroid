package com.example.pydVO;

import java.util.ArrayList;

public class GSONParseProduct {

public class GsonParseProduct {
	private String TotalPages;
	private String TotalResults;
	private String msg;
	private ArrayList<ProductDetailByName> products;
	private String status;

	public String getMsg() {
		return this.msg;
	}

	public ArrayList<ProductDetailByName> getProducts() {
		return this.products;
	}

	public String getStatus() {
		return this.status;
	}

	public String getTotalPages() {
		return this.TotalPages;
	}

	public String getTotalResults() {
		return this.TotalResults;
	}

	public void setMsg(String paramString) {
		this.msg = paramString;
	}

	public void setProducts(ArrayList<ProductDetailByName> paramArrayList) {
		this.products = paramArrayList;
	}

	public void setStatus(String paramString) {
		this.status = paramString;
	}

	public void setTotalPages(String paramString) {
		this.TotalPages = paramString;
	}

	public void setTotalResults(String paramString) {
		this.TotalResults = paramString;
	}
}
}