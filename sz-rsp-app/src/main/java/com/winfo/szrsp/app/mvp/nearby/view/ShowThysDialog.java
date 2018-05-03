package com.winfo.szrsp.app.mvp.nearby.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.sdk.entity.thys.NavigableElementsData;
import com.winfo.szrsp.app.utils.DeviceUtils;
import com.winfo.szrsp.app.utils.DimensUtils;
import com.winfo.szrsp.app.utils.ToastUtils;


/**
 * Created by HoBo on 2018/3/16.
 */

public class ShowThysDialog {
    private ImageButton but_close;
    private TextView thysName;
    private TextView thysType;
    private View chuliView;
    private int width;
    // 距离两边的像素
    private int magin;
    private Context mContext;
    // 改变activity的透明度
    private WindowManager.LayoutParams activityAlphaParams;
    private Dialog dialog;
    private NavigableElementsData data;

    public ShowThysDialog(Context context, NavigableElementsData data) {
        this.mContext = context;
        this.data = data;
        width = DeviceUtils.getScreenSize((Activity) mContext)[0]; // 屏幕宽度（像素）
//        // 窗体距离屏幕最右边或者最左边10dp所对应的像素（把10dp转换成px）
        magin = DimensUtils.dp2px(mContext, 40);
        dialog = new AlertDialog.Builder(mContext).create();
        dialog.setCancelable(true);//
        dialog.setCanceledOnTouchOutside(false);//
        chuliView = LayoutInflater.from(context).inflate(R.layout.dialog_near_thys, null);
        dialog.show();
        dialog.setContentView(chuliView);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams windowparams = window.getAttributes();
        windowparams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        windowparams.width = width - 2 * magin;
        // window.setWindowAnimations(R.style.AnimationDialog);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes((WindowManager.LayoutParams) windowparams);
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initView();
    }

    private void initView() {
        but_close = chuliView.findViewById(R.id.but_close);
        thysName = chuliView.findViewById(R.id.thysName);
        thysType = chuliView.findViewById(R.id.thysType);
        thysName.setText(data.getNameCn());
        if (data.getTypeId().equals("NE0001000004")) {
            thysType.setText("CCTV");
        } else if (data.getTypeId().equals("NE0001000005")) {
            thysType.setText("DSC基站");
        } else if (data.getTypeId().equals("NE0001000006")) {
            thysType.setText("VHF基站");
        } else if (data.getTypeId().equals("NE0001000007")) {
            thysType.setText("雷达站");
        } else if (data.getTypeId().equals("NE0001000008")) {
            thysType.setText("VTS中心");
        } else if (data.getTypeId().equals("NE0002000002")) {
            thysType.setText("泊位");
        } else if (data.getTypeId().equals("NE0002000003")) {
            thysType.setText("码头");
        } else if (data.getTypeId().equals("NE0002000004")) {
            thysType.setText("桥梁");
        } else if (data.getTypeId().equals("NE0002000005")) {
            thysType.setText("通航孔");
        } else if (data.getTypeId().equals("NE0002000006")) {
            thysType.setText("船厂");
        } else if (data.getTypeId().equals("NE0002000007")) {
            thysType.setText("水下管线");
        } else if (data.getTypeId().equals("NE0002000008")) {
            thysType.setText("穿越段");
        } else if (data.getTypeId().equals("NE0002000009")) {
            thysType.setText("渡口");
        } else if (data.getTypeId().equals("NE00020000010")) {
            thysType.setText("渡线");
        } else if (data.getTypeId().equals("NE0002000011")) {
            thysType.setText("船台");
        } else if (data.getTypeId().equals("NE0002000012")) {
            thysType.setText("船坞");
        } else if (data.getTypeId().equals("NE0002000013")) {
            thysType.setText("水上管线");
        } else if (data.getTypeId().equals("NE0002000014")) {
            thysType.setText("跨越段");
        } else if (data.getTypeId().equals("NE002000015")) {
            thysType.setText("沉船沉物");
        } else if (data.getTypeId().equals("NE0002000016")) {
            thysType.setText("石油平台");
        } else if (data.getTypeId().equals("NE0002000017")) {
            thysType.setText("闸坝");
        } else if (data.getTypeId().equals("NE0002000020")) {
            thysType.setText("安全作业区");
        } else if (data.getTypeId().equals("NE0002000021")) {
            thysType.setText("航路");
        } else if (data.getTypeId().equals("NE0002000022")) {
            thysType.setText("航道");
        } else if (data.getTypeId().equals("NE0002000023")) {
            thysType.setText("锚地");
        } else if (data.getTypeId().equals("NE0002000024")) {
            thysType.setText("VTS报告线");
        } else if (data.getTypeId().equals("NE0003000001")) {
            thysType.setText("取排水口");
        } else if (data.getTypeId().equals("NE0004000001")) {
            thysType.setText("测试");
        } else if (data.getTypeId().equals("NE0005000001")) {
            thysType.setText("交通管制区");
        } else if (data.getTypeId().equals("NE0005000002")) {
            thysType.setText("停泊区");
        } else if (data.getTypeId().equals("NE0005000003")) {
            thysType.setText("船舶定线制区域");
        } else if (data.getTypeId().equals("NE0005000004")) {
            thysType.setText("禁锚区");
        } else if (data.getTypeId().equals("NE0005000005")) {
            thysType.setText("禁航区");
        } else if (data.getTypeId().equals("NE0005000006")) {
            thysType.setText("倾废区");
        }
        but_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }


    public void show() {
        dialog.show();
    }
}
