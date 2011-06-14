package com.xinchejian.android.tutorials;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
// http://www.netzgesta.de/gauge/
public class AnimationControlView extends WebView {
	public static final String TAG = AnimationControlView.class.getSimpleName();
	private JSInterface myJSInterface;
	private int topSpeed;
	private int topSafeSpeed;

	/** Called when the activity is first created. 
	 * @param url TODO*/
	public AnimationControlView(Context context, String url) {
		super(context);
		// needed configuration
		getSettings().setAllowFileAccess(true);
		getSettings().setPluginsEnabled(true);
		getSettings().setJavaScriptEnabled(true);
        //getSettings().setLoadWithOverviewMode(true);
        getSettings().setUseWideViewPort(true);		
		// only start calling Javascript when page fully loaded
		setWebViewClient(new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {
				Log.d(TAG, "Paged loaded");
			}
		});

		final Context myContext = context;
		// enable "Javascript" alerts
		myJSInterface = new JSInterface(this);
		
		setWebChromeClient(new WebChromeClient() {
			@Override
			public boolean onJsAlert(WebView view, String url, String message,
					final android.webkit.JsResult result) {
				new AlertDialog.Builder(myContext)
						.setTitle("javaScript dialog")
						.setMessage(message)
						.setPositiveButton(android.R.string.ok,
								new AlertDialog.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										result.confirm();
									}
								}).setCancelable(false).create().show();

				return true;
			}

		});
		// register class containing methods to be exposed to JavaScript
		addJavascriptInterface(myJSInterface, "JSInterface");
		loadUrl(url);
	}

	public class JSInterface {

		private WebView mAppView;

		public JSInterface(WebView appView) {
			this.mAppView = appView;
		}
		public void initCompleted() {
			Log.d(TAG, "Init completed, JSInterface callback");
			loadUrl("javascript:initSpeedometer(300,75)");			
			loadUrl("javascript:setSpeed(0,0,0)");
		}
		public void doEchoTest(String echo) {
			Toast toast = Toast.makeText(mAppView.getContext(), echo,
					Toast.LENGTH_SHORT);
			toast.show();
		}
	}
	public void setSpeed(int speed) {
		if(speed > topSpeed) {
			Log.e(TAG, "Top speed exceeded: " + speed);
			speed = topSpeed;
		}
		int overSafe = 0;
		if(speed>topSafeSpeed) {
			overSafe = speed - topSafeSpeed;
		}
		int toTopSpeed = topSpeed - speed;
		loadUrl("javascript:setSpeed(" + speed + "," + overSafe + "," + toTopSpeed +")");
	}
	
	public void setTopSpeed(int i) {
		this.topSpeed = i;
		
	}
	public void setTopSafeSpeed(int i) {
		this.topSafeSpeed = i;
	}
}