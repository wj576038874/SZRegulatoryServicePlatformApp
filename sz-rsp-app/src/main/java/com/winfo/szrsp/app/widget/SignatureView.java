package com.winfo.szrsp.app.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.utils.ToastUtils;
import com.winfo.szrsp.app.widget.LinePathView;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.mine.userinfo
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.widget.SignatureView.java
 * Date: 2017/12/8 17:26
 * Description:
 */

public class SignatureView {

    private PopupWindow signaturePopupwindow;
    private ViewGroup mParent;
    private Context mContext;
    // 改变activity的透明度
    private WindowManager.LayoutParams activityAlphaParams;
    private LinePathView linePathView;

    public SignatureView(final Context context, ViewGroup parent) {
        this.mParent = parent;
        this.mContext = context;
        this.activityAlphaParams = ((Activity) context).getWindow().getAttributes();
        View contentView = LayoutInflater.from(context).inflate(R.layout.signature_view, null);
        linePathView = contentView.findViewById(R.id.view);
        final TextView textView = contentView.findViewById(R.id.textview);
        Button btnCancel = contentView.findViewById(R.id.btn_cancel);
        Button btnRewrite = contentView.findViewById(R.id.btn_rewrite);
        Button btnOk = contentView.findViewById(R.id.btn_ok);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signaturePopupwindow.dismiss();
            }
        });

        btnRewrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linePathView.clear();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBtnSureClickListener != null) {
                    if (linePathView.getTouched()) {
                        onBtnSureClickListener.onSave(linePathView);
                        signaturePopupwindow.dismiss();
                    } else {
                        ToastUtils.showToast(mContext, "请签名");
                    }
                }
            }
        });

        linePathView.setOnTouchEventDownLsstener(new LinePathView.OnTouchEventDownListener() {
            @Override
            public void clearTextView() {
                textView.setVisibility(View.GONE);
            }
        });
        signaturePopupwindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        signaturePopupwindow.setContentView(contentView);
        signaturePopupwindow.setBackgroundDrawable(new BitmapDrawable());
        signaturePopupwindow.setOutsideTouchable(true);
        signaturePopupwindow.setFocusable(true);
        signaturePopupwindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        signaturePopupwindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        signaturePopupwindow.setAnimationStyle(R.style.photo_popup_anim_style);
        signaturePopupwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                activityAlphaParams.alpha = 1.0f;
                ((Activity) context).getWindow().setAttributes(activityAlphaParams);
            }
        });
    }

    public void showSignatureView() {
        activityAlphaParams.alpha = 0.5f;
        ((Activity) mContext).getWindow().setAttributes(activityAlphaParams);
        signaturePopupwindow.showAtLocation(mParent, Gravity.BOTTOM, 0, 0);
    }

    public OnBtnSureClickListener onBtnSureClickListener;

    public void setOnBtnSureClickListener(OnBtnSureClickListener onBtnSureClickListener) {
        this.onBtnSureClickListener = onBtnSureClickListener;
    }

    public interface OnBtnSureClickListener {
        void onSave(LinePathView linePathView);
    }

}
