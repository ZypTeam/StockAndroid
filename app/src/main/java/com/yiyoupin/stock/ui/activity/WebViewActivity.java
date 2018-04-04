package com.yiyoupin.stock.ui.activity;

import android.graphics.Bitmap;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.ui.util.UiUtils;
import com.yiyoupin.stock.ui.view.BackTitleView;

/**
 * @author zhaoyapeng
 * @version create time:18/3/3014:21
 * @Email zyp@jusfoun.com
 * @Description ${TODO}
 */
public class WebViewActivity extends BaseTakeActivity {
    public static final String TITLE = "title";
    public static final String WEB_URL = "web_url";
    protected WebView webView;
    protected BackTitleView titleView;
    private String title, url;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_webview;
    }

    @Override
    public void initDatas() {
        title = getIntent().getExtras().getString(UiUtils.TITLE);
        url = getIntent().getExtras().getString(UiUtils.WEB_URL);
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
        titleView = (BackTitleView) findViewById(R.id.title_view);
    }

    @Override
    public void initAction() {
        titleView.setTitle(title);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                showLoadDialog();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                hideLoadDialog();
            }
        });
        webView.loadUrl(url);
//        webView.loadUrl("http://www.sse.com.cn/disclosure/listedinfo/announcement/c/2018-04-04/600109_20180404_1.pdf");
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
