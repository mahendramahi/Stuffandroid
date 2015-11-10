package com.example.pyd.network;

import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.WindowManager.LayoutParams;
import android.widget.ProgressBar;

import com.example.pyd.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class PYDConnectionProvider {

	private ProgressDialog pDialog;
	private PYDResponseController mRevvNetworkListener;
	private APIResponse apiResponse = null;

	public void doHttpGetRequest(final Context context, String url,
			RequestParams params, Object listner, final boolean isShowLoading) {

		String appHeader;
		mRevvNetworkListener = (PYDResponseController) listner;
		apiResponse = new APIResponse();
		// appHeader = CommonMethods.getAppTokenTypeFromPrefs(context) + " "
		// + CommonMethods.getAppTokenIDFromPrefs(context);

		System.out.println("hh url = " + url);
		System.out.println("hh params = " + params);
		// System.out.println("hh app hedaer Token = " + appHeader);

		// creating the object for the library asynchttplibrary
		AsyncHttpClient client = new AsyncHttpClient();
		// client.addHeader("Authorization", appHeader);

		client.setTimeout(600000);

		client.get(url, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, String response) {

				System.out.println("hh response = " + response);
				System.out.println("hh response code = " + statusCode);

				dismissDialog();
				apiResponse.setCode(statusCode);
				apiResponse.setResponse(response);
				mRevvNetworkListener.OnComplete(apiResponse);

			}

			@SuppressWarnings("deprecation")
			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable error, String content) {
				super.onFailure(statusCode, headers, error, content);

				System.out.println("hh onFailure : " + error.getMessage()
						+ " : " + content + " status code : " + statusCode);
				apiResponse.setCode(statusCode);
				apiResponse.setResponse(content);
				mRevvNetworkListener.OnComplete(apiResponse);

			}

			@Override
			public void onStart() {
				super.onStart();
				if (isShowLoading) {
					showDialog(context);
				}
			}

			@Override
			public void onFinish() {
				super.onFinish();
				if (isShowLoading) {
					dismissDialog();
				}
			}

		});
	}

	/**
	 * method is used for show dialog when web service is used
	 * 
	 * @param context
	 */
	private void showDialog(Context context) {

		pDialog = ProgressDialog.show(context, null, "");
		pDialog.setContentView(R.layout.view_progress);

		// ProgressBar spinner = new android.widget.ProgressBar(context, null,
		// android.R.attr.progressBarStyle);

		ProgressBar pb = (ProgressBar) pDialog.findViewById(R.id.pb);

		pb.getIndeterminateDrawable().setColorFilter(0xFF2078af,
				android.graphics.PorterDuff.Mode.MULTIPLY);

		// pDialog = new ProgressDialog(context);
		// pDialog.setMessage("Loading..");
		pDialog.getWindow().clearFlags(LayoutParams.FLAG_DIM_BEHIND);
		pDialog.setCanceledOnTouchOutside(false);
		pDialog.setCancelable(false);
		pDialog.show();
	}

	/**
	 * method is used for dismissing the progress dialog
	 */
	private void dismissDialog() {
		if (pDialog != null) {
			pDialog.dismiss();
			pDialog = null;
		}
	}

	/**
	 * method is used for send post request by using asynchttpclientlibrary
	 * 
	 * @param context
	 * @param url
	 * @param params
	 * @param listner
	 */
	public void doHttpPostRequest(final Context context, String url,
			String params, Object listner, final boolean isShowLoading) {

		String appHeader;
		StringEntity entity = null;

		mRevvNetworkListener = (PYDResponseController) listner;
		apiResponse = new APIResponse();
		// appHeader = CommonMethods.getAppTokenTypeFromPrefs(context) + " "
		// + CommonMethods.getAppTokenIDFromPrefs(context);

		System.out.println("hh url : " + url);
		System.out.println("hh params : " + params);
		// System.out.println("hh app hedaer Token : " + appHeader);

		// creating the object for the library asynchttplibrary
		AsyncHttpClient client = new AsyncHttpClient();
		// client.addHeader("Authorization", appHeader);

		if (params != null) {
			try {
				entity = new StringEntity(params);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		// client.setTimeout(30000);
		client.post(context, url, entity, "application/json",
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int statusCode, String response) {

						System.out.println("hh response = " + response);
						System.out.println("hh response code = " + statusCode);

						dismissDialog();
						apiResponse.setCode(statusCode);
						apiResponse.setResponse(response);
						mRevvNetworkListener.OnComplete(apiResponse);
					}

					@SuppressWarnings("deprecation")
					@Override
					public void onFailure(int statusCode, Header[] headers,
							Throwable error, String content) {
						super.onFailure(statusCode, headers, error, content);

						System.out.println("hh onFailure : "
								+ error.getMessage() + " : " + content
								+ " status code : " + statusCode);
						apiResponse.setCode(statusCode);
						apiResponse.setResponse(content);
						mRevvNetworkListener.OnComplete(apiResponse);

					}

					@Override
					public void onStart() {
						super.onStart();
						if (isShowLoading) {
							showDialog(context);
						}
					}

					@Override
					public void onFinish() {
						super.onFinish();
						if (isShowLoading) {
							dismissDialog();
						}
					}
				});
	}

	/**
	 * method is used for send post request by using asynchttpclientlibrary
	 * without header
	 * 
	 * @param context
	 * @param url
	 * @param requestParams
	 * @param listner
	 */
	public void doHttpPostRequestWithOutHeader(final Context context,
			String url, RequestParams requestParams, Object listner) {

		// String appHeader;
		StringEntity entity = null;

		mRevvNetworkListener = (PYDResponseController) listner;
		apiResponse = new APIResponse();
		// appHeader = CommonMethods.getAppTokenTypeFromPrefs(context) + " "
		// + CommonMethods.getAppTokenIDFromPrefs(context);

		System.out.println("hh url : " + url);
		System.out.println("hh params : " + requestParams);
		// System.out.println("hh app hedaer Token : " + appHeader);

		// creating the object for the library asynchttplibrary
		AsyncHttpClient client = new AsyncHttpClient();
		// client.addHeader("Authorization", appHeader);

		client.post(context, url, requestParams,
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int statusCode, String response) {

						System.out.println("hh response = " + response);
						System.out.println("hh response code = " + statusCode);

						dismissDialog();
						apiResponse.setCode(statusCode);
						apiResponse.setResponse(response);
						mRevvNetworkListener.OnComplete(apiResponse);
					}

					@SuppressWarnings("deprecation")
					@Override
					public void onFailure(int statusCode, Header[] headers,
							Throwable error, String content) {
						super.onFailure(statusCode, headers, error, content);

						System.out.println("hh onFailure : "
								+ error.getMessage() + " : " + content
								+ " status code : " + statusCode);
						apiResponse.setCode(statusCode);
						apiResponse.setResponse(content);
						mRevvNetworkListener.OnComplete(apiResponse);

					}

					@Override
					public void onStart() {
						super.onStart();
						showDialog(context);
					}

					@Override
					public void onFinish() {
						super.onFinish();
						dismissDialog();
					}

				});
	}

	/**
	 * method is used for send put request by using asynchttpclientlibrary
	 * 
	 * @param context
	 * @param url
	 * @param params
	 * @param listner
	 */
	public void doHttpPutRequest(final Context context, String url,
			String params, Object listner, final boolean isShowLoading) {

		String appHeader;
		StringEntity entity = null;

		mRevvNetworkListener = (PYDResponseController) listner;
		apiResponse = new APIResponse();
		// appHeader = CommonMethods.getAppTokenTypeFromPrefs(context) + " "
		// + CommonMethods.getAppTokenIDFromPrefs(context);

		System.out.println("hh url : " + url);
		System.out.println("hh params : " + params);
		// System.out.println("hh app hedaer Token : " + appHeader);

		// creating the object for the library asynchttplibrary
		AsyncHttpClient client = new AsyncHttpClient();
		// client.addHeader("Authorization", appHeader);

		if (params != null) {
			try {
				entity = new StringEntity(params);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		client.put(context, url, entity, "application/json",
				new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(int statusCode, String response) {
						System.out.println("hh response = " + response);
						System.out.println("hh response code = " + statusCode);

						dismissDialog();
						apiResponse.setCode(statusCode);
						apiResponse.setResponse(response);
						mRevvNetworkListener.OnComplete(apiResponse);
					}

					@SuppressWarnings("deprecation")
					@Override
					public void onFailure(int statusCode, Header[] headers,
							Throwable error, String content) {
						super.onFailure(statusCode, headers, error, content);

						System.out.println("hh onFailure : "
								+ error.getMessage() + " : " + content
								+ " status code : " + statusCode);
						apiResponse.setCode(statusCode);
						apiResponse.setResponse(content);
						mRevvNetworkListener.OnComplete(apiResponse);

					}

					@Override
					public void onStart() {
						super.onStart();
						if (isShowLoading) {
							showDialog(context);
						}
					}

					@Override
					public void onFinish() {
						super.onFinish();
						if (isShowLoading) {
							dismissDialog();
						}
					}
				});
	}

	/**
	 * method is used for send delete request by using asynchttpclientlibrary
	 * 
	 * @param context
	 * @param url
	 * @param params
	 * @param listner
	 */
	public void doHttpDeleteRequest(final Context context, String url,
			String params, Object listner, final boolean isShowLoading) {

		String appHeader;
		// StringEntity entity = null;

		mRevvNetworkListener = (PYDResponseController) listner;
		apiResponse = new APIResponse();
		// appHeader = CommonMethods.getAppTokenTypeFromPrefs(context) + " "
		// + CommonMethods.getAppTokenIDFromPrefs(context);

		System.out.println("hh url : " + url);
		System.out.println("hh params : " + params);
		// System.out.println("hh app hedaer Token : " + appHeader);

		// creating the object for the library asynchttplibrary
		AsyncHttpClient client = new AsyncHttpClient();
		// client.addHeader("Authorization", appHeader);

		// if (params != null) {
		// try {
		// entity = new StringEntity(params);
		// } catch (UnsupportedEncodingException e) {
		// e.printStackTrace();
		// }
		// }

		client.delete(context, url, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, String response) {
				System.out.println("hh response = " + response);
				System.out.println("hh response code = " + statusCode);

				dismissDialog();
				apiResponse.setCode(statusCode);
				apiResponse.setResponse(response);
				mRevvNetworkListener.OnComplete(apiResponse);
			}

			@SuppressWarnings("deprecation")
			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable error, String content) {
				super.onFailure(statusCode, headers, error, content);

				System.out.println("hh onFailure : " + error.getMessage()
						+ " : " + content + " status code : " + statusCode);
				apiResponse.setCode(statusCode);
				apiResponse.setResponse(content);
				mRevvNetworkListener.OnComplete(apiResponse);

			}

			@Override
			public void onStart() {
				super.onStart();
				if (isShowLoading) {
					showDialog(context);
				}
			}

			@Override
			public void onFinish() {
				super.onFinish();
				if (isShowLoading) {
					dismissDialog();
				}
			}
		});

	}
}
