package com.winfo.szrsp.app.mvp.message.view;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.message.model.entity.MessageBean;
import com.winfo.szrsp.app.utils.DeviceUtils;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.DimensUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageCenterActivity extends Activity implements View.OnClickListener {
    @BindView(R.id.message_listview)
    ListView message_listview;
    @BindView(R.id.titleBar_imgbtn_more)
    ImageButton titleBar_imgbtn_more;
    @BindView(R.id.titleBar_imgbtn_back)
    ImageButton titleBar_imgbtn_back;
    List<MessageBean> messageList;
    private  MyAdapter adapter;
    View view;//pop布局
    int magin;
    int width;
    private PopupWindow popupWindow;//操作消息窗体
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_center);
        ButterKnife.bind(this);
        initData();
        initEvent();
    }



    private void initData() {

        messageList=new ArrayList<>();

        MessageBean messageBean=new MessageBean();
        messageBean.setTitle("任务提醒");
        messageBean.setContent("对船舶 粤河源1355进行安全检查");
        messageBean.setRead(false);
        messageBean.setTime("2017-10-15 02:35:07");
        messageBean.setVoice(false);
        messageList.add(messageBean);


        MessageBean messageBean2=new MessageBean();
        messageBean2.setTitle("预警信息");
        messageBean2.setContent("广东气象局于12日上午8时发布暴雨橙色预警");
        messageBean2.setRead(false);
        messageBean2.setTime("2017-10-12 08:35:07");
        messageBean2.setVoice(false);
        messageList.add(messageBean2);


        MessageBean messageBean3=new MessageBean();
        messageBean3.setTitle("报警提醒");
        messageBean3.setContent("海洋一号即将驶入东部禁航区");
        messageBean3.setRead(false);
        messageBean3.setTime("2017-10-11 02:35:07");
        messageBean3.setVoice(false);
        messageList.add(messageBean3);


        MessageBean messageBean4=new MessageBean();
        messageBean4.setTitle("语音提醒");
        messageBean4.setContent("对船舶 粤河源1355进行安全检查");
        messageBean4.setRead(false);
        messageBean4.setTime("2017-10-10 02:35:07");
        messageBean4.setVoice(true);
        messageBean4.setVoiceLong("15s");
        messageList.add(messageBean4);


        MessageBean messageBean5=new MessageBean();
        messageBean5.setTitle("报警提醒");
        messageBean5.setContent("海洋一号即将驶入东部禁航区");
        messageBean5.setRead(false);
        messageBean5.setTime("2017-10-09 02:35:07");
        messageBean5.setVoice(false);
        messageList.add(messageBean5);

        MessageBean messageBean6=new MessageBean();
        messageBean6.setTitle("任务提醒");
        messageBean6.setContent("对船舶 粤河源1355进行安全检查");
        messageBean6.setRead(false);
        messageBean6.setTime("2017-10-08 02:35:07");
        messageBean6.setVoice(false);
        messageList.add(messageBean6);

        for (int i=0;i<15;i++){
            MessageBean messageBean7=new MessageBean();
            messageBean7.setTitle("任务提醒");
            messageBean7.setContent("对船舶 粤河源1355进行安全检查"+i);
            messageBean7.setRead(false);
            messageBean7.setTime("2017-10-08 02:35:07");
            messageBean7.setVoice(false);
            messageList.add(messageBean7);

        }


        adapter=new MyAdapter();
        message_listview.setAdapter(adapter);
    }
    private void initEvent() {

        message_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                messageList.get(position).setRead(true);
                adapter.notifyDataSetChanged();
            }
        });

        titleBar_imgbtn_more.setOnClickListener(this);
        titleBar_imgbtn_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titleBar_imgbtn_back:
                finish();
                break;
            case R.id.titleBar_imgbtn_more:
                alertPop();
                break;
        }
    }

    /**
     * 弹出选择搜索类型窗体
     */
    private void alertPop() {
        view = View.inflate(this, R.layout.pw_message_operate, null);

        final TextView pw_message_opt_read = (TextView) view.findViewById(R.id.pw_message_opt_read);
        final TextView pw_message_opt_delete = (TextView) view.findViewById(R.id.pw_message_opt_delete);
        // 获取手机屏幕的宽度 单位像素px
        width = DeviceUtils.getScreenSize(this)[0]; // 屏幕宽度（像素）
        // 窗体距离屏幕最右边或者最左边10dp所对应的像素（把10dp转换成px）
        magin = DimensUtils.dp2px(this, 10);
        // 初始化popupwindow
        popupWindow = new PopupWindow(view, width - (width - DimensUtils.dp2px(this, 130)), ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setFocusable(true);// 设置焦点
        popupWindow.setAnimationStyle(R.style.popwin_anim_style); // 动画效果
        popupWindow.setOutsideTouchable(true);// 关闭弹出窗体
        popupWindow.showAsDropDown(titleBar_imgbtn_more,DimensUtils.dp2px(this, 40) - popupWindow.getWidth(), DimensUtils.dp2px(this, 10));

        pw_message_opt_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i=0;i<messageList.size();i++){
                    messageList.get(i).setRead(true);
                }
                adapter.notifyDataSetChanged();
                popupWindow.dismiss();
            }
        });

        pw_message_opt_delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                DialogUtils.showDialog(MessageCenterActivity.this,"温馨提示","是否删除所有消息记录？","删除","取消", new DialogUtils.DialogOnClickListenner() {
                    @Override
                    public void btnConfirmClick(Dialog dialog) {

                        messageList.clear();
                        adapter.notifyDataSetChanged();
                        popupWindow.dismiss();
                        dialog.dismiss();
                    }
                    @Override
                    public void btnCancelClick(Dialog dialog) {
                        dialog.dismiss();
                    }
                });



            }
        });
    }


    private  class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return messageList.size();
        }

        @Override
        public Object getItem(int position) {
            return messageList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            MessageBean messageBean =  messageList.get(position);
            if (convertView == null) {
                // 没有复用
                // 1.加载布局
                convertView = View.inflate(SzRspApplication.getContext(), R.layout.message_center_item, null);
                // 2. 新建holder
                viewHolder = new ViewHolder();
                // 3. 设置标记
                convertView.setTag(viewHolder);
                // 4.给holder去找view

                viewHolder.tvTitle = (TextView) convertView
                        .findViewById(R.id.message_center_tv_title);
                viewHolder.tvIsRead= (TextView) convertView
                        .findViewById(R.id.message_center_tv_isread);
                viewHolder.tvContent= (TextView) convertView
                        .findViewById(R.id.message_center_tv_content);
                viewHolder.tvVoiceLong= (TextView) convertView
                        .findViewById(R.id.message_center_tv_voiceLong);

                viewHolder.tvTime= (TextView) convertView
                        .findViewById(R.id.message_center_tv_time);

                viewHolder.rlVoice = (RelativeLayout) convertView
                        .findViewById(R.id.message_center_rl_voice);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tvTitle.setText(messageBean.getTitle());
            if(messageBean.isRead()){
                viewHolder.tvIsRead.setText("已读");
                viewHolder.tvIsRead.setTextColor(Color.parseColor("#666666"));
            }else{
                viewHolder.tvIsRead.setText("未读");
                viewHolder.tvIsRead.setTextColor(Color.parseColor("#fe4534"));
            }
            viewHolder.tvTime.setText(messageBean.getTime());

            if(messageBean.isVoice()){
                viewHolder.rlVoice.setVisibility(View.VISIBLE);
                viewHolder.tvContent.setVisibility(View.GONE);
                viewHolder.tvVoiceLong.setText(messageBean.getVoiceLong());

            }else{
                viewHolder.rlVoice.setVisibility(View.GONE);
                viewHolder.tvContent.setVisibility(View.VISIBLE);
                viewHolder.tvContent.setText(messageBean.getContent());
            }



            return convertView;
        }
    }


    private static class ViewHolder {

        TextView tvTitle;
        TextView tvIsRead;
        TextView tvContent;
        TextView tvVoiceLong;
        TextView tvTime;
        RelativeLayout rlVoice;

    }
}
