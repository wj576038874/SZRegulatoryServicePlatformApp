package com.winfo.szrsp.app.mvp.table.cbxcjdbg.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.sdk.entity.table.DetailcbxcjdbgData;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.cbxcjdbg.view
 * @Filename: DetailjdbgwtmsLayout
 * @Author: lsj
 * @Date: 2017/12/8  13:29
 * @Description:
 * @Version:
 */
public class DetailjdbgjcxmLayout extends LinearLayout {
    String  num;
    String  description;
    String  result;

    public DetailjdbgjcxmLayout(Context context, String num,String description ,String result) {
        super(context);
        this.num = num;
        this.description = description;
        this.result = result;
        LayoutInflater.from(context).inflate(R.layout.item_jdbg_detail_jcxm, this);
        initView();
    }

    private void initView() {
        TextView tv_index = findViewById(R.id.tv_index);
        tv_index.setText(num);

        TextView tv_dec = findViewById(R.id.tv_dec);
        tv_dec.setText(description);
        CheckBox item_ck1 = findViewById(R.id.item_ck1);
        CheckBox item_ck2 = findViewById(R.id.item_ck2);
        CheckBox item_ck3 = findViewById(R.id.item_ck3);

        if(result.equals("0")){
            item_ck1.setChecked(true);
        }
        if(result.equals("1")){
            item_ck2.setChecked(true);
        }
        if(result.equals("2")){
            item_ck3.setChecked(true);
        }
    }
}
