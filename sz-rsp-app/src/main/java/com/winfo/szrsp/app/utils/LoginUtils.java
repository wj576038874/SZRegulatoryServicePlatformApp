package com.winfo.szrsp.app.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.mine.login.view.impl.UserLoginActivity;
import com.winfo.szrsp.app.sdk.utils.ACache;


public class LoginUtils {
    private static boolean isShowLoginOutDialao;//标记全局是否显示登入过期的dialog，默认为false 未显示
    public static void loginOut(Context context){
        PreferenceUtils.setBoolean(context, "islogin", false);
        ACache.get(context).remove("access_token");
        ACache.get(context).remove("uuid");
        ACache.get(context).remove("userName");
        ACache.get(context).remove("deptName");
        ACache.get(context).remove("deptCode");
        ACache.get(context).remove("orgCode");
        ACache.get(context).remove("orgName");
    }
    public static void loginOutShowDialog(final Context context,String title,String content){
        loginOut(context);
        if(!isShowLoginOutDialao){
            isShowLoginOutDialao=true;
         Dialog dialog=   DialogUtils.showDialog(context, "温馨提示", content, new DialogUtils.DialogOnClickListenner() {
                @Override
                public void btnConfirmClick(Dialog dialog) {
                    dialog.dismiss();
                    context.startActivity(new Intent(context, UserLoginActivity.class));
                }
                @Override
                public void btnCancelClick(Dialog dialog) {
                    dialog.dismiss();
                }
            });
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    isShowLoginOutDialao=false;
                }
            });
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    isShowLoginOutDialao=false;
                }
            });
        }



    }
}
