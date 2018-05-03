package com.winfo.szrsp.app.mvp.table.cqgjdjcqktzs.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.utils.ToastUtils;
import com.winfo.szrsp.app.widget.wheelview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ChengQi on 2017/12/4.
 * 船旗国监督检查情况通知书
 */

public class CqgjdjcqktzsActivity extends Activity implements View.OnClickListener{
    private ImageButton table_titleBar_imgbtn_back;
    private TextView table_titleBar_titleText;
    private TimePickerView timePickerView;
    private WebView webView;
    private Button btn_save;
    private EditText edt_hsjg;
    private EditText edt_dh;
    private EditText edt_cz;
    private EditText edt_zrr;
    private TextView tv_qfrq;
    private EditText edt_cs1;
    private EditText edt_cs2;
    private EditText edt_cjg;
    private EditText edt_sbh;
    private EditText edt_syr;
    private EditText edt_jyjg;
    private EditText edt_xyg;
    private EditText edt_dgsj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cqgjdjcqktzs);
        initView();
        initData();
        initEvent();
    }

    private void initData() {
        table_titleBar_titleText.setText("执行表格");
        webView.getSettings().setJavaScriptEnabled(true);//获取webview对象中的setting对象 设置为可以支持javascript
        webView.loadUrl("file:///android_asset/tzs/tzs.html");
        webView.addJavascriptInterface(new JsInterface(), "handler");
        //添加客户端支持
        webView.setWebViewClient(new JsWebViewClient(""));
    }

    private void initEvent() {
        btn_save.setOnClickListener(this);
        table_titleBar_imgbtn_back.setOnClickListener(this);
    }

    private void initView() {
        table_titleBar_imgbtn_back = findViewById(R.id.table_titleBar_imgbtn_back);
        table_titleBar_titleText = findViewById(R.id.table_titleBar_titleText);
        webView = findViewById(R.id.webview);
        timePickerView = new TimePickerView(this, TimePickerView.Type.ALL);
        timePickerView.setCyclic(true);
        timePickerView.setTime(new Date());
        btn_save = findViewById(R.id.btn_sub);
        edt_hsjg = findViewById(R.id.edt_hsjg);
        edt_dh = findViewById(R.id.edt_dh);
        edt_cz = findViewById(R.id.edt_cz);
        edt_zrr = findViewById(R.id.edt_zrr);
        tv_qfrq = findViewById(R.id.tv_qfrq);
        edt_cs1 = findViewById(R.id.edt_cs1);
        edt_cs2 = findViewById(R.id.edt_cs2);
        edt_cjg = findViewById(R.id.edt_cjg);
        edt_sbh = findViewById(R.id.edt_sbh);
        edt_syr = findViewById(R.id.edt_syr);
        edt_jyjg = findViewById(R.id.edt_jyjg);
        edt_xyg = findViewById(R.id.edt_xyg);
        edt_dgsj = findViewById(R.id.edt_dgsj);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.table_titleBar_imgbtn_back:
                finish();
                break;
            case R.id.btn_sub:
                webView.loadUrl("javascript:jsShowData()");
                break;
        }
    }

    private class JsWebViewClient extends WebViewClient {
        private String str;

        JsWebViewClient(String str) {
            this.str = str;
        }

        /**
         * 如果紧跟着
         * webView.loadUrl(file:///android_asset/index.html);
         * 调用Js中的方法是不起作用的，必须页面加载完成才可以
         */
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            webView.loadUrl("javascript:setShipName('" + str + "')");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
            String time = format.format(new Date());
            webView.loadUrl("javascript:initTime('" + time + "')");
        }
    }

    private class JsInterface {
        //在js中调用window.handler.showAndroidTimeSelect()，便会触发此方法。
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void showAndroidTimeSelect() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

                        @Override
                        public void onTimeSelect(Date date) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(date);
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
                            String time = format.format(calendar.getTime());
                            webView.loadUrl("javascript:setTime('" + time+ "')");
                        }
                    });
                    timePickerView.show();
                }
            });
        }

        /**
         * 获取html上  用户输入的数据
         *
         * @param shipname 船名
         * @param address  检查港
         * @param str1     str1
         * @param str2     str2
         */
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void showData(final String shipname, final String time, final String address, final String str1, final String str2) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    if (etPhone.getText().toString().equals("")
//                            || etChuanzhen.getText().toString().equals("")
//                            || shipname.equals("")
//                            || year.equals("")
//                            || month.equals("")
//                            || day.equals("")
//                            || hour.equals("")
//                            || address.equals("")
//                            || str1.equals("")
//                            || str2.equals("")
//                            || etCjg.getText().toString().equals("")
//                            || etCbsbh.getText().toString().equals("")
//                            || etCbsyr.getText().toString().equals("")
//                            || etCbjyjg.getText().toString().equals("")
//                            || etNextStation.getText().toString().equals("")
//                            || tvNextStationTime.getText().toString().equals("")) {
//                        ToastUtils.showToast(CqgjdjcqktzsActivity.this, "请先将表格填写完整");
//                    } else {
//
//                    }

                    ToastUtils.showToast(CqgjdjcqktzsActivity.this,shipname+time+address+str1+str2);
                }
            });
        }
    }


}
