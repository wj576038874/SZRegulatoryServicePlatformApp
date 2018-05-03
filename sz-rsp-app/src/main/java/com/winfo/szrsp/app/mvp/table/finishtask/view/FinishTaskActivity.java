package com.winfo.szrsp.app.mvp.table.finishtask.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.adapter.FinishTaskAdapter;
import com.winfo.szrsp.app.sdk.entity.task.FinishTaskModel;
import com.winfo.szrsp.app.sdk.entity.task.FourTableSubData;
import com.winfo.szrsp.app.sdk.entity.task.TaskFinishData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wly on 2017/12/15.
 */

public class FinishTaskActivity extends Activity implements View.OnClickListener {

    private String ctSpecialShipType0203Return;//大型散货
    private String ctWaterCruiseRecordReturn;//水上巡航
    private String tFlagStateControlReturn;//船旗国
    private String tSiteSupervisionReturn;//船舶现场
    private String ctCruiseStatisticsReturn;//巡航统计
    private String ctElectronicCruiseExceptionReturn;//电子巡航异常
    private String ctSafetySceneInspectReturn;//船舶危防现场
    private String ctDangerPolluteScene;//危险货物现场检查
    private String ctDangerPolluteOut;//危险货物开箱检查
    private String ctGoodSecneOut;//普通货物开箱检查
    private String ctCaseWeightInspect;//集装箱重量检查
    private ImageButton table_titleBar_imgbtn_back;
    private TextView table_titleBar_titleText;
    private ListView lv_table_list;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_task);

        initView();

        initData();

        initEvent();


    }

    private void initEvent() {
        table_titleBar_imgbtn_back.setOnClickListener(this);
    }

    private void initView() {
        table_titleBar_imgbtn_back = findViewById(R.id.table_titleBar_imgbtn_back);
        table_titleBar_titleText = findViewById(R.id.table_titleBar_titleText);
        lv_table_list = findViewById(R.id.lv_table_list);
    }

    private void initData() {
        table_titleBar_titleText.setText("下载与打印");
        List<FinishTaskModel> list = new ArrayList<>();
        TaskFinishData data = (TaskFinishData) getIntent().getSerializableExtra("data");
        FourTableSubData data1 = (FourTableSubData) getIntent().getSerializableExtra("fourData");
        String shipName = getIntent().getStringExtra("shipName");
        if (null != data) {
            ctSpecialShipType0203Return = data.getCtSpecialShipType0203Return();
            ctWaterCruiseRecordReturn = data.getCtWaterCruiseRecordReturn();
            tFlagStateControlReturn = data.gettFlagStateControlReturn();
            tSiteSupervisionReturn = data.gettSiteSupervisionReturn();
            ctCruiseStatisticsReturn = data.getCtCruiseStatisticsReturn();
            ctElectronicCruiseExceptionReturn = data.getCtElectronicCruiseExceptionReturn();
            ctSafetySceneInspectReturn = data.getCtSafetySceneInspectReturn();
        } else if (null != data1) {
            ctDangerPolluteScene = data1.getCtDangerPolluteScene();
            ctDangerPolluteOut = data1.getCtDangerPolluteOut();
            ctGoodSecneOut = data1.getCtGoodSecneOut();
            ctCaseWeightInspect = data1.getCtCaseWeightInspect();
        }
        FinishTaskModel model;
        if (null != ctSpecialShipType0203Return && !"".equals(ctSpecialShipType0203Return)) {
            model = new FinishTaskModel();
            model.setInspectNo(ctSpecialShipType0203Return);
            model.setTableName("大型散货船检查表");
            model.setShipName(shipName);
            list.add(model);
        }

        if (null != ctWaterCruiseRecordReturn && !"".equals(ctWaterCruiseRecordReturn)) {
            model = new FinishTaskModel();
            model.setInspectNo(ctWaterCruiseRecordReturn);
            model.setTableName("水上巡航表格");
            model.setShipName(shipName);
            list.add(model);

        }

        if (null != tFlagStateControlReturn && !"".equals(tFlagStateControlReturn)) {
            model = new FinishTaskModel();
            model.setInspectNo(tFlagStateControlReturn);
            model.setTableName("船旗国监督检查报告");
            model.setShipName(shipName);
            list.add(model);
        }

        if (null != tSiteSupervisionReturn && !"".equals(tSiteSupervisionReturn)) {
            model = new FinishTaskModel();
            model.setInspectNo(tSiteSupervisionReturn);
            model.setTableName("船舶现场监督报告");
            model.setShipName(shipName);
            list.add(model);
        }

        if (null != ctCruiseStatisticsReturn && !"".equals(ctCruiseStatisticsReturn)) {
            model = new FinishTaskModel();
            model.setInspectNo(ctCruiseStatisticsReturn);
            model.setTableName("本次巡航工作统计");
            model.setShipName(shipName);
            list.add(model);
        }

        if (null != ctElectronicCruiseExceptionReturn && !"".equals(ctElectronicCruiseExceptionReturn)) {
            model = new FinishTaskModel();
            model.setInspectNo(ctElectronicCruiseExceptionReturn);
            model.setTableName("电子巡航异常");
            model.setShipName(shipName);
            list.add(model);
        }
        if (null != ctSafetySceneInspectReturn && !"".equals(ctSafetySceneInspectReturn)) {
            model = new FinishTaskModel();
            model.setInspectNo(ctSafetySceneInspectReturn);
            model.setTableName("船舶危防现场");
            model.setShipName(shipName);
            list.add(model);
        }

        if (null != ctDangerPolluteScene && !"".equals(ctDangerPolluteScene)) {
            model = new FinishTaskModel();
            model.setInspectNo(ctDangerPolluteScene);
            model.setTableName("集装箱危险货物现场检查记录表");
            model.setShipName(shipName);
            list.add(model);
        }
        if (null != ctDangerPolluteOut && !"".equals(ctDangerPolluteOut)) {
            model = new FinishTaskModel();
            model.setInspectNo(ctDangerPolluteOut);
            model.setTableName("集装箱危险货物开箱检查记录表");
            model.setShipName(shipName);
            list.add(model);
        }
        if (null != ctGoodSecneOut && !"".equals(ctGoodSecneOut)) {
            model = new FinishTaskModel();
            model.setInspectNo(ctGoodSecneOut);
            model.setTableName("普通货物集装箱开箱检查记录表");
            model.setShipName(shipName);
            list.add(model);
        }
        if (null != ctCaseWeightInspect && !"".equals(ctCaseWeightInspect)) {
            model = new FinishTaskModel();
            model.setInspectNo(ctCaseWeightInspect);
            model.setTableName("货物集装箱重量验证检查记录表");
            model.setShipName(shipName);
            list.add(model);
        }
        FinishTaskAdapter adapter = new FinishTaskAdapter(this, list, R.layout.layout_listview_table_list);
        lv_table_list.setAdapter(adapter);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.table_titleBar_imgbtn_back:
                finish();
                break;

        }
    }
}
