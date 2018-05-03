package com.winfo.szrsp.app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.sdk.entity.task.TaskAssign;
import com.winfo.szrsp.app.sdk.entity.task.TaskInfo;

import java.util.List;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.task.view
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.adapter.TaskAdapter.java
 * Date: 2017/12/8 14:37
 * Description:
 */

public class TaskAdapter extends CommonAdapter<TaskInfo> {


    /**
     * 构造方法
     *
     * @param context  上下文  子类获得
     * @param datas    数据 子类获得
     * @param resource 资源id 子类获得
     *
     */

    private List<TaskInfo> taskBeans;
    private Context mContext;
    public TaskAdapter(Context context, List<TaskInfo> datas, int resource) {
        super(context, datas, resource);
        this.mContext = context;
        this.taskBeans = datas;
    }

    @Override
    public void convert(ViewHolder holder, int position) {

        TextView tvTaskTime=holder.getView(R.id.gettask_tv_time);
        TextView tvTaskTitle=holder.getView(R.id.gettask_tv_title);
        TextView tvTaskStatus=holder.getView(R.id.gettask_tv_status);
        ImageView ivTaskStatus=holder.getView(R.id.gettask_iv_status);
        LinearLayout gettask_ll=holder.getView(R.id.gettask_ll);

        TextView tvTaskDec=holder.getView(R.id.task_tv_dec);

        TextView btn=holder.getView(R.id.btn_see_desc);
        TaskInfo group = taskBeans.get(position);

        tvTaskTime.setText(group.getTaskReleaseTime());

       String taskName= group.getTaskName();
        tvTaskTitle.setText(taskName);
        String dec="";
        if(group.getTaskTypeList().size()>0){
                for(int i=0;i<group.getTaskTypeList().size();i++){
                    String task=group.getTaskTypeList().get(i).getTaskTypeName();
                    if(i==group.getTaskTypeList().size()-1){
                        dec+=task;
                    }else {
                        dec+=task+"\n";;
                    }
                }
        }
        tvTaskDec.setText(dec);
//任务指派状态(0默认已指派个人,1指派人接收,2指派人退回.3任务被改派或转交其他人
// 4.任务改派接受者5.指派部门6.指派到部门,已经领取7.已完成,8.指派到机构）9.待分发

        //新    任务指派状态(1指派机构,2已指派部门.3已指派队长
// 4.进行中5.任务已退回 8.已完成
        switch (group.getStatus().toString()) {
            case "0":
//                tvTaskStatus.setText("未接收");
//                ivTaskStatus.setImageResource(R.mipmap.icon_task_unaccepted);
//                tvTaskStatus.setTextColor(Color.parseColor("#ffd80303"));
                break;
            case "1":

                break;
            case "2":
                 tvTaskStatus.setText("待接收");
                ivTaskStatus.setImageResource(R.mipmap.icon_task_acceptable);
                tvTaskStatus.setTextColor(Color.parseColor("#ff9c44ff"));
                break;
            case "3":
                tvTaskStatus.setText("待分发");
                ivTaskStatus.setImageResource(R.mipmap.icon_task_unaccepted);
                tvTaskStatus.setTextColor(Color.parseColor("#ffd80303"));
//                tvTaskStatus.setText("改派");
//                ivTaskStatus.setImageResource(R.mipmap.icon_task_retreated);
//                tvTaskStatus.setTextColor(Color.parseColor("#fffaab12"));
                break;
            case "4":
                tvTaskStatus.setText("执行中");
                ivTaskStatus.setImageResource(R.mipmap.icon_task_doing);
                tvTaskStatus.setTextColor(Color.parseColor("#ff05c701"));

                break;
            case "5":
                tvTaskStatus.setText("已取消");
                ivTaskStatus.setImageResource(R.mipmap.icon_task_retreated);
                tvTaskStatus.setTextColor(Color.parseColor("#fffaab12"));
//                tvTaskStatus.setText("指派部门");
//                ivTaskStatus.setImageResource(R.mipmap.icon_task_acceptable);
//                tvTaskStatus.setTextColor(Color.parseColor("#ff9c44ff"));
                break;
            case "6":
 //               tvTaskStatus.setText("指派到部门,有人已经领取");
//                ivTaskStatus.setImageResource(R.mipmap.icon_task_acceptable);
//                tvTaskStatus.setTextColor(Color.parseColor("#ff9c44ff"));
//                tvTaskStatus.setText("已领取");
//                ivTaskStatus.setImageResource(R.mipmap.icon_task_doing);
//                tvTaskStatus.setTextColor(Color.parseColor("#ff05c701"));
                break;
            case "7":
//                tvTaskStatus.setText("已完成");
//                ivTaskStatus.setImageResource(R.mipmap.icon_task_completed);
//                tvTaskStatus.setTextColor(Color.parseColor("#ff3286fe"));
//                //gettask_ll.setBackground(ContextCompat.getDrawable(mContext, R.drawable.item_border_press));
                break;
            case "8":
                tvTaskStatus.setText("已完成");
                ivTaskStatus.setImageResource(R.mipmap.icon_task_completed);
                tvTaskStatus.setTextColor(Color.parseColor("#ff3286fe"));
                //gettask_ll.setBackground(ContextCompat.getDrawable(mContext, R.drawable.item_border_press));
                break;
            case "9":
//                tvTaskStatus.setText("待分发");
//                ivTaskStatus.setImageResource(R.mipmap.icon_task_unaccepted);
//                tvTaskStatus.setTextColor(Color.parseColor("#ffd80303"));
//                //gettask_ll.setBackground(ContextCompat.getDrawable(mContext, R.drawable.item_border_press));
                break;
            case "17":
//                tvTaskStatus.setText("他人已完成");
//                ivTaskStatus.setImageResource(R.mipmap.icon_task_completed);
//                tvTaskStatus.setTextColor(Color.parseColor("#ff3286fe"));
//                //gettask_ll.setBackground(ContextCompat.getDrawable(mContext, R.drawable.item_border_press));
                break;
            default:
                break;

        }
    }

}
