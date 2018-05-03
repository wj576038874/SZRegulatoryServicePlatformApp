package com.winfo.szrsp.app.adapter.task;

import android.content.Context;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.adapter.CommonAdapter;
import com.winfo.szrsp.app.adapter.ViewHolder;
import com.winfo.szrsp.app.sdk.entity.task.TaskInfoDetails;

import java.util.List;

/**
 * Created by Guan on 2017-12-10.
 */

public class TaskDetCbAdapter extends CommonAdapter<TaskInfoDetails> {
    /**
     * 构造方法
     *
     * @param context  上下文  子类获得
     * @param datas    数据 子类获得
     * @param resource 资源id 子类获得
     */
    private List<TaskInfoDetails> taskBeans;
    private Context mContext;
    public TaskDetCbAdapter(Context context, List<TaskInfoDetails> datas, int resource) {
        super(context, datas, resource);
        this.mContext = context;
        this.taskBeans = datas;
    }

    @Override
    public void convert(ViewHolder holder, int position) {



        TextView task_detail_mmsi=holder.getView(R.id.task_detail_mmsi);
        TextView task_detail_shipnamecn=holder.getView(R.id.task_detail_shipnamecn);
        TextView task_detail_shipnameen=holder.getView(R.id.task_detail_shipnameen);
        TextView task_detail_shipcallsign=holder.getView(R.id.task_detail_shipcallsign);
        TextView task_detail_shipimo=holder.getView(R.id.task_detail_shipimo);
        TextView task_detail_shiptype=holder.getView(R.id.task_detail_shiptype);
        TextView task_detail_shiplocation=holder.getView(R.id.task_detail_shiplocation);
        TextView task_detail_berthname=holder.getView(R.id.task_detail_berthname);
        TextView task_detail_shipcompanyname=holder.getView(R.id.task_detail_shipcompanyname);

        TaskInfoDetails group = taskBeans.get(position);
        task_detail_mmsi.setText(group.getMmsi());
        task_detail_shipnamecn.setText(group.getShipNameCn());
        task_detail_shipnameen.setText(group.getShipNameEn());
        task_detail_shipcallsign.setText(group.getShipCallsign());
        task_detail_shipimo.setText(group.getShipImo());
        task_detail_shiptype.setText(group.getShipType());
        task_detail_shiplocation.setText(group.getShipLatitude()+"   "+group.getShipLongitude());
        task_detail_berthname.setText(group.getBerthName());
        task_detail_shipcompanyname.setText(group.getShipCompanyName());


    }
}
