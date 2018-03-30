package com.yiyoupin.stock.ui.activity;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.yiyoupin.stock.R;

/**
 * @author zhaoyapeng
 * @version create time:18/3/3014:21
 * @Email zyp@jusfoun.com
 * @Description ${TODO}
 */
public class WebViewActivity extends BaseTakeActivity {
    protected WebView webView;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_webview;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initView() {
        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setGeolocationEnabled(true);
        webView.getSettings().setAppCacheEnabled(false);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setSaveFormData(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setWebChromeClient(chromeClient);
    }

    @Override
    public void initAction() {
        webView.loadUrl("http://www.51purse.com/pdf/web/viewer.html?name=b.pdf");
    }

    WebChromeClient chromeClient = new WebChromeClient() {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }

    };
}
