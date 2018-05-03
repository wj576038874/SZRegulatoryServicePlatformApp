package com.winfo.szrsp.app.mvp.table.cqgjdbg.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.sdk.entity.table.jdbgData;
import com.winfo.szrsp.app.sdk.entity.task.DefectCode;
import com.winfo.szrsp.app.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.cqgjdbg.view
 * @Filename: cljdLayout
 * @Author: lsj
 * @Date: 2017/12/4  15:29
 * @Description:
 * @Version:
 */
public class cljdLayout extends LinearLayout {
    //    private EditText edt_qxdm;
    private EditText edt_qxms;
    private Spinner sp_qxdm;
    private Spinner sp_qxms;
    private EditText edt_yj;
    private EditText edt_cjzr;
    private Spinner sp_cljd;
    private List<String> list_data = new ArrayList<>();
    private List<String> list_data2;
    private List<String> sp_data;
    private String cljd;
    private String FscCode;
    private String str_qxms;
    private List<DefectCode> defectCodes;
    private String code;

    public cljdLayout(Context context, String FscCode, String qxms, List<DefectCode> defectCodes) {
        super(context);
        this.FscCode = FscCode;
        this.defectCodes = defectCodes;
        str_qxms = qxms;
        LayoutInflater.from(context).inflate(R.layout.item_cljd, this);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {

        sp_qxdm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                list_data2 = new ArrayList<>();
                int pos = i - 1;
                if (!list_data.get(i).equals("---请选择---")) {
                    sp_qxms.setVisibility(VISIBLE);
                    sp_qxdm.setVisibility(GONE);
                    sp_qxms.performClick();
                    List<DefectCode.SmallList> smallLists = defectCodes.get(pos).getSmallLists();
                    for (int j = 0; j < smallLists.size(); j++) {
                        list_data2.add(smallLists.get(j).getInspectItemCode() + "." + smallLists.get(j).getInspectItemName());
                    }
                    list_data2.add("--重新选缺陷代码--");
                    ArrayAdapter<String> sp_Adapter1 = new ArrayAdapter<String>(SzRspApplication.getContext(), R.layout.spinner_item_1, list_data2);
                    sp_Adapter1.setDropDownViewResource(R.layout.dropdown_style_1);
                    sp_qxms.setAdapter(sp_Adapter1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_qxms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (list_data2.get(i).equals("--重新选缺陷代码--")) {
                    sp_qxdm.setVisibility(VISIBLE);
                    sp_qxms.setVisibility(GONE);
                    sp_qxdm.performClick();
                } else {
                    code = list_data2.get(i).substring(0, 4);
                    sp_qxdm.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_cljd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (sp_data.get(i).equals("---请选择---")) {
                    cljd = "无";
                } else {
                    cljd = sp_data.get(i).substring(0, 2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void initData() {
        edt_qxms.setText(str_qxms);
        list_data.add("---请选择---");
        for (int i = 0; i < defectCodes.size(); i++) {
            list_data.add(defectCodes.get(i).getInspectItemCode() + "." + defectCodes.get(i).getInspectItemName());
        }
        ArrayAdapter<String> sp_Adapter1 = new ArrayAdapter<String>(SzRspApplication.getContext(), R.layout.spinner_item_1, list_data);
        sp_Adapter1.setDropDownViewResource(R.layout.dropdown_style_1);
        sp_qxdm.setAdapter(sp_Adapter1);
//        sp_qxdm.setSelection(0);

        sp_data = new ArrayList<>();
        sp_data.add("---请选择---");
        sp_data.add("06 船籍港纠正");
        sp_data.add("10 缺陷已纠正");
        sp_data.add("15 下一港纠正");
        sp_data.add("16 十四天内纠正");
        sp_data.add("17 开航前纠正");
        sp_data.add("18 三个月纠正(ISM或NSM)");
        sp_data.add("30 禁止离港(滞留)");
        sp_data.add("41 限制操作");
        sp_data.add("42 责令船舶驶向指定区域");
        sp_data.add("43 责令船舶离港");
        sp_data.add("44 禁止船舶进港");
        sp_data.add("46 驶往经同意的修理港纠正滞留缺陷");
        sp_data.add("49 经同意的缺陷纠正计划");
        sp_data.add("99 其他措施(详细说明)");

        ArrayAdapter<String> sp_Adapter = new ArrayAdapter<String>(SzRspApplication.getContext(), R.layout.spinner_item_1, sp_data);
        sp_Adapter.setDropDownViewResource(R.layout.dropdown_style_1);
        sp_cljd.setAdapter(sp_Adapter);
        sp_cljd.setSelection(0);
        list_data2 = new ArrayList<>();
        for (int i = 0; i < defectCodes.size(); i++) {
            for (int j = 0; j < defectCodes.get(i).getSmallLists().size(); j++) {
                if (defectCodes.get(i).getSmallLists().get(j).getInspectItemCode().equals(FscCode)) {
                    for (int k = 0; k < defectCodes.get(i).getSmallLists().size(); k++) {
                        list_data2.add(defectCodes.get(i).getSmallLists().get(k).getInspectItemCode() + "." + defectCodes.get(i).getSmallLists().get(k).getInspectItemName());
                    }
                    list_data2.add("--重新选缺陷代码--");
                    ArrayAdapter<String> sp_Adapter2 = new ArrayAdapter<String>(SzRspApplication.getContext(), R.layout.spinner_item_1, list_data2);
                    sp_Adapter2.setDropDownViewResource(R.layout.dropdown_style_1);
                    sp_qxms.setAdapter(sp_Adapter2);
                    sp_qxms.setVisibility(VISIBLE);
                    sp_qxdm.setVisibility(GONE);
                    sp_qxms.setSelection(j);
                    code = list_data2.get(j).substring(0, 4);
                }
            }
        }

    }

    private void initView() {
        sp_qxdm = findViewById(R.id.spinner_qxdm);
        sp_qxms = findViewById(R.id.spinner_qxms);
        sp_cljd = findViewById(R.id.spinner_cljd);
        edt_qxms = findViewById(R.id.edt_qxms);
        edt_yj = findViewById(R.id.edt_yj);
        edt_cjzr = findViewById(R.id.edt_cjzr);
    }

    public jdbgData.detail getData() {
        jdbgData.detail detail = new jdbgData.detail();
        if (edt_qxms.getText().toString().trim().equals("")) {
            ToastUtils.showToast(SzRspApplication.getContext(), "请将船旗国监督检查报告缺陷描述填写完整！");
            return null;
        } else {
            if (code != null) {
                detail.setDefectCode(code);
                detail.setDefectDesc(edt_qxms.getText().toString().trim());
                detail.setEnforceFoundation(edt_yj.getText().toString().trim());
                detail.setCommentCode(cljd);
                detail.setIsInspectOrgRelated(edt_cjzr.getText().toString().trim());
                return detail;
            } else {
                ToastUtils.showToast(SzRspApplication.getContext(), "请将船旗国监督检查报告缺陷代码填写完整！");
                return null;
            }
        }

//        detail.setDefectDesc(edt_qxms.getText().toString());
//        detail.setEnforceFoundation(edt_yj.getText().toString());
//        detail.setCommentCode(cljd);
//        detail.setIsInspectOrgRelated("1");
//        return detail;
    }
}
