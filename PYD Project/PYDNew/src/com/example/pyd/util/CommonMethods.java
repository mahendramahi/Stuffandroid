package com.example.pyd.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;

import com.example.pyd.network.PYDConnectionProvider;
import com.loopj.android.http.RequestParams;

public class CommonMethods {
	
	
	private Activity context;
	Object listener;

	public CommonMethods(Activity _activity, Object listener) {
		this.context = _activity;
		this.listener = listener;
	}

	/**
	 * method used for getting the request parameters for the product web search
	 * service with the given parameter
	 * 
	 * @param strCode
	 * 
	 * 
	 * @return The complete Registration json request parameter
	 */
	public String getProductRequestParmas(String strCode) {

		JSONArray rootJsonArr = null;
		try {
			rootJsonArr = new JSONArray();
			JSONObject rootJsonObj = new JSONObject();
			rootJsonObj.put("upc", strCode);
			rootJsonArr.put(rootJsonObj);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return rootJsonArr.toString();
	}

	public RequestParams postAudioCommentRequestParmas(String strEmail,
			String msg, String strTitle) {

		RequestParams params = new RequestParams();

		params.put("email", strEmail);
		params.put("details", msg);
		params.put("product_name", strTitle);
		return params;

	}

	/**
	 * method used for getting the request parameters for the product web search
	 * service with the given parameter
	 * 
	 * @param productName
	 * @param string
	 * @param str_productName
	 * @param string2
	 * 
	 * @param strCode
	 * 
	 * 
	 * @return The complete Registration json request parameter
	 */
	public String getProductRequestByNameParmas(String productName,
			String string, String string2, String str_productName) {

		JSONArray rootJsonArr = null;
		try {
			rootJsonArr = new JSONArray();
			JSONObject rootJsonObj = new JSONObject();
			rootJsonObj.put("keyword", productName);
			rootJsonObj.put("pg", string);
			rootJsonObj.put("search_type", string2);
			rootJsonObj.put("upc", str_productName);
			rootJsonArr.put(rootJsonObj);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return rootJsonArr.toString();
	}

	/**
	 * method used for getting the request parameters for the product web search
	 * service with the given parameter
	 * 
	 * @param asin
	 * @param productName
	 * 
	 * @param strCode
	 * 
	 * 
	 * @return The complete Registration json request parameter
	 */
	public String getProductRequestByAsin(String asin) {

		JSONArray rootJsonArr = null;
		try {
			rootJsonArr = new JSONArray();
			JSONObject rootJsonObj = new JSONObject();
			rootJsonObj.put("asin", asin);
			rootJsonArr.put(rootJsonObj);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return rootJsonArr.toString();
	}

	/**
	 * method used for Login with the given parameter
	 * 
	 * @param requestParams
	 * @throws UnsupportedEncodingException
	 */
	public void GetProductDetail(String requestParams)
			throws UnsupportedEncodingException {

		// create the arraylist for getting the signature
		ArrayList<String> al_signature_strings = new ArrayList<String>();

		al_signature_strings.add("upc");

		al_signature_strings.add("getproduct");

		// calling a method for creating the signature for country list API
		String str_signature = SignatureCommonMenthods
				.getSignatureForAPI(al_signature_strings);

		PYDConnectionProvider approPriceConnectionProvider = new PYDConnectionProvider();
		approPriceConnectionProvider.doHttpGetRequest(context,
				URLFactory.GetProductUrl() + "&signature=" + str_signature
						+ "&data=" + URLEncoder.encode(requestParams, "UTF-8"),
				null, listener, true);
	}

	public void SetSubscribeEmail(String paramString)
			throws UnsupportedEncodingException {
		ArrayList<String> al_signature_strings = new ArrayList<String>();
		al_signature_strings.add("addsubscriber");
		al_signature_strings.add("email");
		String str = SignatureCommonMenthods
				.getSignatureForAPI(al_signature_strings);
		PYDConnectionProvider approPriceConnectionProvider = new PYDConnectionProvider();
		approPriceConnectionProvider.doHttpGetRequest(context,
				URLFactory.SetSubscribeEmail() + "&signature=" + str + "&data="
						+ URLEncoder.encode(paramString, "UTF-8"), null,
				listener, false);
	}

	/**
	 * method used for Login with the given parameter
	 * 
	 * @param requestParams
	 * @throws UnsupportedEncodingException
	 */
	public void GetProductDetailByName(String requestParams)
			throws UnsupportedEncodingException {

		// create the arraylist for getting the signature
		ArrayList<String> al_signature_strings = new ArrayList<String>();

		al_signature_strings.add("keyword");

		al_signature_strings.add("getproductbytitle");
		al_signature_strings.add("pg");
		al_signature_strings.add("search_type");
		al_signature_strings.add("upc");

		// calling a method for creating the signature for country list API
		String str_signature = SignatureCommonMenthods
				.getSignatureForAPI(al_signature_strings);

		PYDConnectionProvider approPriceConnectionProvider = new PYDConnectionProvider();
		approPriceConnectionProvider.doHttpGetRequest(
				context,
				URLFactory.GetProductByTitleUrl() + "&signature="
						+ str_signature + "&data="
						+ URLEncoder.encode(requestParams, "UTF-8"), null,
				listener, true);
	}

	/**
	 * method used for Login with the given parameter
	 * 
	 * @param requestParams
	 * @throws UnsupportedEncodingException
	 */
	public void GetProductDetailByAsin(String requestParams)
			throws UnsupportedEncodingException {

		// create the arraylist for getting the signature
		ArrayList<String> al_signature_strings = new ArrayList<String>();

		al_signature_strings.add("asin");

		al_signature_strings.add("getproductbyassin");

		// calling a method for creating the signature for country list API
		String str_signature = SignatureCommonMenthods
				.getSignatureForAPI(al_signature_strings);

		PYDConnectionProvider approPriceConnectionProvider = new PYDConnectionProvider();
		approPriceConnectionProvider.doHttpGetRequest(
				context,
				URLFactory.GetProductByAsinUrl() + "&signature="
						+ str_signature + "&data="
						+ URLEncoder.encode(requestParams, "UTF-8"), null,
				listener, true);
	}

	public String setSubscriber(String marketAssociatedEmailId) {
		JSONArray rootJsonArr = null;
		try {
			rootJsonArr = new JSONArray();
			JSONObject rootJsonObj = new JSONObject();
			rootJsonObj.put("email", marketAssociatedEmailId);
			rootJsonArr.put(rootJsonObj);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return rootJsonArr.toString();
	}

	/**
	 * method used for send email by admin panal with search result url to user
	 * with the given parameter
	 * 
	 * @param requestParams
	 * @throws UnsupportedEncodingException
	 */
	public void postAudioComment(RequestParams requestParams)
			throws UnsupportedEncodingException {

		// create the arraylist for getting the signature
		ArrayList<String> al_signature_strings = new ArrayList<String>();
		al_signature_strings.add("porductmail");
		al_signature_strings.add("email");
		al_signature_strings.add("details");
		al_signature_strings.add("product_name");
		
		// calling a method for creating the signature for country list API
		String str_signature = SignatureCommonMenthods
				.getSignatureForAPI(al_signature_strings);

		PYDConnectionProvider appropriceConnectionProvider = new PYDConnectionProvider();
		appropriceConnectionProvider.doHttpPostRequestWithOutHeader(context,
				URLFactory.postMailUrl() + "&signature="
						+ str_signature, requestParams, listener);

		// LookagramConnectionProvider lookagramConnectionProvider = new
		// LookagramConnectionProvider();
		// lookagramConnectionProvider.doHttpPostRequestWithOutHeader(activity,
		// URLFactory.postAudioCommentUrl() + "&signature="
		// + str_signature, requestParams, listener);

	}

}
