package com.example.pyd.util;

public class URLFactory {

//	public static String baseUrl = "http://employeetracker.us/approprice/services/";
	public static String baseUrl = "http://checksaving.com/app/services/";
	// private static String baseUrl =
	// "http://192.168.1.2//approprice/services/";

	public static String GetProductUrl() {
		return baseUrl + "ws-products.php?type=GETPRODUCT";
	}

	public static String GetProductByTitleUrl() {
		return baseUrl + "ws-products.php?type=GETPRODUCTBYTITLE";
	}

	public static String GetProductByAsinUrl() {
		return baseUrl + "ws-products.php?type=GETPRODUCTBYASSIN";
	}

	public static String SetSubscribeEmail() {
		return baseUrl + "ws-user.php?type=ADDSUBSCRIBER";
	}

	public static String postMailUrl() {
		return baseUrl + "ws-products.php?type=PORDUCTMAIL";
	}

}
