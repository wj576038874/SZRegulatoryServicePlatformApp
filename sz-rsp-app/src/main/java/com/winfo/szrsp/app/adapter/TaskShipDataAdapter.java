package com.winfo.szrsp.app.adapter;

import android.content.Context;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.sdk.entity.task.TaskShipData;

import java.util.List;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.adapter
 * @Filename: TaskShipDataAdapter
 * @Author: lsj
 * @Date: 2018/3/9  14:42
 * @Description:
 * @Version:
 */
public class TaskShipDataAdapter extends CommonAdapter<TaskShipData> {
    /**
     * 构造方法
     *
     * @param context  上下文  子类获得
     * @param datas    数据 子类获得
     * @param resource 资源id 子类获得
     */
    public TaskShipDataAdapter(Context context, List<TaskShipData> datas, int resource) {
        super(context, datas, resource);
    }

    @Override
    public void convert(ViewHolder holder, int position) {
        TextView tv = holder.getView(R.id.tv_ferryName);
        tv.setText(datas.get(position).getZwcm());
    }
}
