package com.winfo.szrsp.app.adapter.ship;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.adapter.CommonAdapter;
import com.winfo.szrsp.app.adapter.ViewHolder;
import com.winfo.szrsp.app.mvp.exceptionship.presenter.ExceptionShipOperatePresenter;
import com.winfo.szrsp.app.mvp.exceptionship.view.IExceptionShipOperateView;
import com.winfo.szrsp.app.mvp.task.view.ShipMapActivity;
import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.sdk.entity.shipdata.ExceptionShip;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.utils.ToastUtils;

import java.util.List;

/**
 * Created by Guan on 2017-12-21.
 */

public class ExceptionShipAdapter extends CommonAdapter<ExceptionShip> implements IExceptionShipOperateView {
    /**
     * 构造方法
     *
     * @param context  上下文  子类获得
     * @param datas    数据 子类获得
     * @param resource 资源id 子类获得
     */

    private List<ExceptionShip> exceptionShips;
    private Context mContext;
    private ExceptionShipOperatePresenter presenter;
    private Dialog dialog;
    public ExceptionShipAdapter(Context context, List<ExceptionShip> datas, int resource) {
        super(context, datas, resource);
        this.mContext = context;
        this.exceptionShips = datas;
        presenter=new ExceptionShipOperatePresenter(this);
        dialog= DialogUtils.createLoadingDialog(mContext,"加载中...");
    }

    @Override
    public void convert(ViewHolder holder, int position) {
        TextView tvExceptionShipTime=holder.getView(R.id.exceptionship_tv_time);
        TextView tvtvExceptionShipName=holder.getView(R.id.exceptionship_tv_name);
        TextView tvtvExceptionShipDec= holder.getView(R.id.exceptionship_tv_dec);
        Button bt_location= holder.getView(R.id.btn_exception_location);
        final ExceptionShip group = exceptionShips.get(position);

        tvExceptionShipTime.setText(group.getCreateDate());
        String shipNameCn=group.getShipNameCn();
        String shipNameEn=group.getShipNameEn();

        if(shipNameCn.equals("")){
            tvtvExceptionShipName.setText(shipNameEn);
        }else {
            tvtvExceptionShipName.setText(shipNameCn);
        }
        tvtvExceptionShipDec.setText(group.getExceptionRemark());
        bt_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getAisDtat(group.getMmsi());
            }
        });

    }

    @Override
    public void addFaild(String msg) {

    }

    @Override
    public void addSucceed(String msg) {

    }

    @Override
    public void deleteFaild(String msg) {

    }

    @Override
    public void deleteSucceed(String msg) {

    }

    @Override
    public Dialog getDialog() {
        return dialog;
    }

    @Override
    public void queryFaild(String msg) {

    }

    @Override
    public void setIsOrNotExceptionShip(ExceptionShip exceptionShip) {

    }

    @Override
    public void setAisData(Ais aisData) {
        Intent intent = new Intent(mContext,ShipMapActivity.class);
        intent.putExtra("AisData",aisData);
        mContext.startActivity(intent);
    }

    @Override
    public void searchAsiFaild(String msg) {
        ToastUtils.showToast(mContext,msg);
    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(mContext,msg,msg);
    }
}
