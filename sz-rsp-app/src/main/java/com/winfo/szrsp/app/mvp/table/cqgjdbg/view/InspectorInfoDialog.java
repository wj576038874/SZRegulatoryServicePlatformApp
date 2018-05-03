package com.winfo.szrsp.app.mvp.table.cqgjdbg.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.sdk.entity.table.SecurityInspectorInformation;
import com.winfo.szrsp.app.utils.DeviceUtils;
import com.winfo.szrsp.app.utils.DimensUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by wly on 2018/4/25.
 *
 */

public class InspectorInfoDialog implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private Dialog dialog;
    private View contentView;
    private GetCheckBoxDialogDataCallBack callBack;
    private int size;
    private LinearLayout ll_root;

    @SuppressLint("UseSparseArrays")
    private Map<Integer ,Boolean> maps = new HashMap<>();


    public InspectorInfoDialog(String title, Activity activity, List<SecurityInspectorInformation> list,GetCheckBoxDialogDataCallBack callBack){
        dialog=new AlertDialog.Builder(activity).create();
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        contentView=View.inflate(activity, R.layout.dialog_check_box_layout,null);
        ll_root = contentView.findViewById(R.id.ll_root);
        TextView tv_title = contentView.findViewById(R.id.tv_title);
        tv_title.setText(title);
        ImageButton but_close = contentView.findViewById(R.id.but_close);
        TextView but_save = contentView.findViewById(R.id.but_save);
        but_close.setOnClickListener(this);
        but_save.setOnClickListener(this);
        this.callBack=callBack;
        int marginStart= DimensUtils.dp2px(activity,30);//把dp转换成px
        int margin=DimensUtils.dp2px(activity,10);
        int padding=DimensUtils.dp2px(activity,10);

        size=list.size();
        for (int i=0;i<size;i++){
            LinearLayout linearLayout=new LinearLayout(activity);
            LinearLayout.LayoutParams linearParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setLayoutParams(linearParams);

            CheckBox checkBox=new CheckBox(activity);
            TableRow.LayoutParams checkBoxParams=new TableRow.LayoutParams(0,TableRow.LayoutParams.WRAP_CONTENT);
            checkBoxParams.weight=1;
            checkBoxParams.setMargins(marginStart,margin,margin,0);
            checkBox.setLayoutParams(checkBoxParams);
            checkBox.setPadding(padding,0,padding,0);
            checkBox.setTextSize(15);
            checkBox.setText(list.get(i).getInspector_name());
            checkBox.setGravity(Gravity.CENTER);
            checkBox.setOnCheckedChangeListener(this);
            linearLayout.addView(checkBox);

            TextView textText=new TextView(activity);
            android.widget.TableRow.LayoutParams textParams=new android.widget.TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT);
            textParams.weight=1;
            textText.setGravity(Gravity.CENTER_VERTICAL);
            textParams.setMargins(margin,margin,margin,margin);
            textText.setLayoutParams(textParams);
            textText.setPadding(padding,padding,padding,padding);
            textText.setInputType(InputType.TYPE_CLASS_NUMBER);
            textText.setTextSize(15);
            textText.setText(list.get(i).getInspector_card());
            linearLayout.addView(textText);
            ll_root.addView(linearLayout);
        }

        int screenWidth= DeviceUtils.getScreenSize(activity)[0];//屏幕宽度（像素）
        Window window=dialog.getWindow();
        if (window!=null){
            WindowManager.LayoutParams windowParams = window.getAttributes();
            windowParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            windowParams.width = screenWidth - 2*4* margin;
            window.setBackgroundDrawableResource(android.R.color.transparent);
            window.setAttributes(windowParams);
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        }


    }

    public   void showCheckBoxDialog(){
         for (int i=0;i<size;i++){
             LinearLayout linearLayout= (LinearLayout) ll_root.getChildAt(i);
             CheckBox checkBox= (CheckBox) linearLayout.getChildAt(0);
             if(maps!=null&&maps.containsKey(i)){
                 checkBox.setChecked(true);
             }else {
                 checkBox.setChecked(false);
             }
         }
        dialog.show();
        dialog.setContentView(contentView);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but_close:
                dialog.dismiss();
                break;

            case R.id.but_save:
                callBack.getDataCallBack(maps);
                dialog.dismiss();
                break;


        }
    }

    @Override
    public void onCheckedChanged(CompoundButton checkBox, boolean isChecked) {
        LinearLayout linearLayout= (LinearLayout) checkBox.getParent();
        int index=((ViewGroup)linearLayout.getParent()).indexOfChild(linearLayout);
        if(isChecked){
            maps.put(index,true);
        }else {
            maps.remove(index);
        }
    }


    public interface GetCheckBoxDialogDataCallBack{
        void getDataCallBack(Map<Integer ,Boolean>map);
    }

}
