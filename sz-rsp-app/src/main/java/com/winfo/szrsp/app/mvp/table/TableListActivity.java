package com.winfo.szrsp.app.mvp.table;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.mvp.table.cbwfxcjdbg.view.CBWFXCJDActivity;
import com.winfo.szrsp.app.mvp.table.cbxcjdbg.view.JdbgActivity;
import com.winfo.szrsp.app.mvp.table.cbzy.view.DangerousGoodsKaiXiangActivity;
import com.winfo.szrsp.app.mvp.table.cbzyjzxzljc.view.ContainerWeightInspectActivity;
import com.winfo.szrsp.app.mvp.table.cbzypthwkxjc.view.OrdinaryGoodsKaiXiangActivity;
import com.winfo.szrsp.app.mvp.table.cbzywxhwxcjc.view.DangerousGoodsXianChangActivity;
import com.winfo.szrsp.app.mvp.table.cqgjdbg.view.SupervisionActivity;
import com.winfo.szrsp.app.mvp.table.cqgjdjcqktzs.view.CqgjdjcqktzsActivity;
import com.winfo.szrsp.app.mvp.table.dxshcjcb.view.DXSHCJCBActivity;
import com.winfo.szrsp.app.mvp.table.dzxhyc.view.ElectronicCruiseAbnormalActivity;
import com.winfo.szrsp.app.mvp.table.psca.view.PSCFormAActivity;
import com.winfo.szrsp.app.mvp.table.pscb.view.PSCFormBActivity;
import com.winfo.szrsp.app.mvp.table.ssxh.view.WatersPatrolActivity;
import com.winfo.szrsp.app.mvp.table.xhgzjl.view.CruiseWorkRecordActivity;
import com.winfo.szrsp.app.mvp.table.xhtj.view.CruiseStatisticsActivity;
import com.winfo.szrsp.app.mvp.task.view.TaskdetailsActivity;

/**
 * Created by Guan on 2017-12-20.
 */

public class TableListActivity extends Activity implements View.OnClickListener {
    private ImageButton table_titleBar_imgbtn_back;

    private Button btn_cqg;
    private Button btn_cbxc;
    private Button btn_waters_patrol;
    private Button btn_dxshcjcb;
    private Button btn_tzs;
    private Button btn_table_psc_a;
    private Button btn_table_psc_b;
    private Button btn_table_xhtj;
    private Button btn_table_dzxh_yc;
    private Button btn_table_cbwfxcjd;
    private Button btn_xhgzjl;//巡航工作记录表
    private Button btn_table_cbzy_wxhwxcjc;//船舶载运危险货物现场检查
    private Button btn_table_cbzy_wxhwkxjc;//船舶载运危险货物开箱检查
    private Button btn_table_cbzy_jzxkxjc;//船舶载运集装箱开箱检查
    private Button btn_table_cbzy_jzxzljc;//船舶载运集装箱重量检查

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tablelist);

        initView();
        initEvent();

    }

    private void initEvent() {
        btn_cqg.setOnClickListener(this);
        btn_cbxc.setOnClickListener(this);
        btn_waters_patrol.setOnClickListener(this);
        btn_dxshcjcb.setOnClickListener(this);
        btn_tzs.setOnClickListener(this);
        btn_table_psc_a.setOnClickListener(this);
        table_titleBar_imgbtn_back.setOnClickListener(this);
        btn_table_psc_b.setOnClickListener(this);
        btn_table_xhtj.setOnClickListener(this);
        btn_table_dzxh_yc.setOnClickListener(this);
        btn_table_cbwfxcjd.setOnClickListener(this);

        btn_xhgzjl.setOnClickListener(this);
        btn_table_cbzy_wxhwxcjc.setOnClickListener(this);
        btn_table_cbzy_wxhwkxjc.setOnClickListener(this);
        btn_table_cbzy_jzxkxjc.setOnClickListener(this);
        btn_table_cbzy_jzxzljc.setOnClickListener(this);
    }

    private void initView() {
        btn_cqg = findViewById(R.id.btn_cqg);
        btn_cbxc = findViewById(R.id.btn_cbxc);
        btn_waters_patrol = findViewById(R.id.btn_waters_patrol);
        btn_tzs = findViewById(R.id.btn_tzs);
        btn_table_psc_a = findViewById(R.id.btn_table_psc_a);
        btn_dxshcjcb = findViewById(R.id.btn_dxshcjcb);
        table_titleBar_imgbtn_back = findViewById(R.id.table_titleBar_imgbtn_back);
        btn_table_psc_b = findViewById(R.id.btn_table_psc_b);
        btn_table_xhtj = findViewById(R.id.btn_table_xhtj);
        btn_table_dzxh_yc = findViewById(R.id.btn_table_dzxh_yc);
        btn_table_cbwfxcjd = findViewById(R.id.btn_table_cbwfxcjd);
        btn_xhgzjl = findViewById(R.id.btn_xhgzjl);
        btn_table_cbzy_wxhwxcjc = findViewById(R.id.btn_table_cbzy_wxhwxcjc);
        btn_table_cbzy_wxhwkxjc = findViewById(R.id.btn_table_cbzy_wxhwkxjc);
        btn_table_cbzy_jzxkxjc = findViewById(R.id.btn_table_cbzy_jzxkxjc);
        btn_table_cbzy_jzxzljc = findViewById(R.id.btn_table_cbzy_jzxzljc);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.table_titleBar_imgbtn_back:
                finish();
                break;
            case R.id.btn_cqg:
                startActivity(new Intent(TableListActivity.this, SupervisionActivity.class));
                break;
            case R.id.btn_cbxc:
                startActivity(new Intent(TableListActivity.this, JdbgActivity.class));
                break;
            case R.id.btn_waters_patrol:
                startActivity(new Intent(TableListActivity.this, WatersPatrolActivity.class));
                break;
            case R.id.btn_dxshcjcb:
                startActivity(new Intent(TableListActivity.this, DXSHCJCBActivity.class));
                break;
            case R.id.btn_xhgzjl:
                Intent intent = new Intent(TableListActivity.this, CruiseWorkRecordActivity.class);
                intent.putExtra("taskTypeId", "");
                startActivity(intent);
                break;
            case R.id.btn_tzs:
                startActivity(new Intent(TableListActivity.this, CqgjdjcqktzsActivity.class));
                break;
            case R.id.btn_table_psc_a:
                startActivity(new Intent(TableListActivity.this, PSCFormAActivity.class));
                break;
            case R.id.btn_table_psc_b:
                startActivity(new Intent(TableListActivity.this, PSCFormBActivity.class));
                break;
            case R.id.btn_table_xhtj:
                startActivity(new Intent(TableListActivity.this, CruiseStatisticsActivity.class));
                break;
            case R.id.btn_table_dzxh_yc:
                startActivity(new Intent(TableListActivity.this, ElectronicCruiseAbnormalActivity.class));
                break;
            case R.id.btn_table_cbwfxcjd:
                startActivity(new Intent(TableListActivity.this, CBWFXCJDActivity.class));
                break;
            case R.id.btn_table_cbzy_wxhwxcjc:
                startActivity(new Intent(TableListActivity.this, DangerousGoodsXianChangActivity.class));
                break;
            case R.id.btn_table_cbzy_wxhwkxjc:
                startActivity(new Intent(TableListActivity.this, DangerousGoodsKaiXiangActivity.class));
                break;
            case R.id.btn_table_cbzy_jzxkxjc:
                startActivity(new Intent(TableListActivity.this, OrdinaryGoodsKaiXiangActivity.class));
                break;
            case R.id.btn_table_cbzy_jzxzljc:
                startActivity(new Intent(TableListActivity.this, ContainerWeightInspectActivity.class));
                break;
        }

    }
}
