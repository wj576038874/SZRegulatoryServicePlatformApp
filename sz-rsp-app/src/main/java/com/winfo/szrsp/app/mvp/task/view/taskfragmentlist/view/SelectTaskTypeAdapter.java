package com.winfo.szrsp.app.mvp.task.view.taskfragmentlist.view;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.sdk.entity.task.TaskType2;

import java.util.ArrayList;
import java.util.List;


public class SelectTaskTypeAdapter extends BaseQuickAdapter<TaskType2,BaseViewHolder> {
    private List<TaskType2>  mData;
    List<String> list=new ArrayList<>();
    SelectTaskTypeAdapter(@Nullable List<TaskType2> data) {
        super(R.layout.item_task_select, data);
        this.mData=data;
        //初始化单选列表
        list.add("RS_00000003");
        list.add("RS_00000006");

    }

    @Override
    protected void convert(BaseViewHolder helper,final TaskType2 item) {
        helper.setChecked(R.id.check,item.isChecked());
        helper.setText(R.id.check,item.getTaskTypeName());
        final CheckBox checkBox = helper.getView(R.id.check);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    //设置为勾选
                    item.setChecked(true);
                    setCanClickWhenCheck(list,item);
//                    if(item.getTaskTypeId().equals("RS_00000003")){
//                        //如果点击了港口国，遍历将其他选项设置为不能点击
//                        for (int i = 0; i < mData.size(); i++) {
//                            if(mData.get(i).getTaskTypeId().equals("RS_00000003")){
//                                mData.get(i).setCanClick(true);
//                            }else {
//                                mData.get(i).setCanClick(false);
//                            }
//                        }
//                    }else {
//                        //如果点击了非港口国，遍历将港口国设置为不能点击
//                        for (int i = 0; i < mData.size(); i++) {
//                            if(mData.get(i).getTaskTypeId().equals("RS_00000003")){
//                                mData.get(i).setCanClick(false);
//                            }else {
//                                mData.get(i).setCanClick(true);
//                            }
//                        }
//                    }
                }else {
                    //设置为不勾选
                    item.setChecked(false);
                    setCanClickWhenNoCheck(list,item);
//                    if(item.getTaskTypeId().equals("RS_00000003")){
//                        //如果点击了港口国，遍历将全部设置为能点击
//                        for (int i = 0; i < mData.size(); i++) {
//                            mData.get(i).setCanClick(true);
//                        }
//                    }else {
//                        //如果点击了非港口国，判断除了港口国 其他选项勾选状态  其他都没有勾选时 港口国才能勾选
//                        boolean isOtherOneSelect=false;//除了港口国其他有一个勾选
//                        for (int i = 0; i < mData.size(); i++) {
//                            if (!mData.get(i).getTaskTypeId().equals("RS_00000003")){
//                                //除了港口国其他只要有一个勾选了 isOtherAllSelect为true
//                                if(mData.get(i).isChecked()){
//                                    isOtherOneSelect=true;
//                                }
//                            }
//                        }
//                        if (isOtherOneSelect){
//                            //  港口国不能勾选
//                            for (int i = 0; i < mData.size(); i++) {
//                                if(mData.get(i).getTaskTypeId().equals("RS_00000003")){
//                                    mData.get(i).setCanClick(false);
//                                }else {
//                                    mData.get(i).setCanClick(true);
//                                }
//                            }
//
//                        }else {
//                            //  港口国能勾选
//                            for (int i = 0; i < mData.size(); i++) {
//                                mData.get(i).setCanClick(true);
//                            }
//                        }
//
//                    }
                }
                notyfyData();
                onCheckChangListener.setData(mData);
            }
        });
        if(item.isCanClick()){
            helper.getView(R.id.check).setClickable(true);
            helper.getView(R.id.check).setEnabled(true);
            helper.setTextColor(R.id.check, Color.parseColor("#333333"));
        }else {
            helper.getView(R.id.check).setClickable(false);
            helper.getView(R.id.check).setEnabled(false);
            helper.setTextColor(R.id.check,Color.parseColor("#999999"));
        }
    }


    private void notyfyData(){
        try{
            notifyDataSetChanged();
        } catch (Exception ignored){

        }
    }
    public interface OnCheckChangListener{
        void setData(List<TaskType2> data);
    }
    private OnCheckChangListener onCheckChangListener;
    void  setOnCheckChangListener(OnCheckChangListener onCheckChangListener){
        this.onCheckChangListener=onCheckChangListener;
    }

    private void setCanClickWhenCheck(List<String> radioList,TaskType2 item){
        for (int i = 0; i <radioList.size() ; i++) {
           if(item.getTaskTypeId().equals(radioList.get(i))){
                //如果点击了只能单选项，遍历将其他选项设置为不能点击
                for (int j = 0; j < mData.size(); j++) {
                    if(!mData.get(j).getTaskTypeId().equals(radioList.get(i))){
                        mData.get(j).setCanClick(false);
                    }
                }
            }else {
               //如果点击了非单选项，遍历将单选项设置为不能点击
               for (int j = 0; j < mData.size(); j++) {
                   if(mData.get(j).getTaskTypeId().equals(radioList.get(i))){
                       mData.get(j).setCanClick(false);
                   }
               }
           }
        }
    }

    private void setCanClickWhenNoCheck(List<String> radioList,TaskType2 item){
        for (int i = 0; i <radioList.size() ; i++) {
            if(item.getTaskTypeId().equals(radioList.get(i))){
                //如果点击了单选项，遍历将全部设置为能点击
                for (int j = 0; j < mData.size(); j++) {
                    mData.get(j).setCanClick(true);
                }
            }else {
                //如果点击了非单选项，判断除了单选项 其他选项勾选状态  其他都没有勾选时 单选项才能勾选
                boolean isOtherOneSelect=false;//除了单选项其他有一个勾选
                for (int j = 0; j < mData.size(); j++) {
                   if(!mData.get(j).getTaskTypeId().equals(radioList.get(i))){
                       if(mData.get(j).isChecked()){
                            isOtherOneSelect=true;
                        }
                   }
                }
                if(isOtherOneSelect){
                    //  单选项不能勾选
                    for (int j = 0; j < mData.size(); j++) {
                        if(mData.get(j).getTaskTypeId().equals(radioList.get(i))){
                            mData.get(j).setCanClick(false);
                        }
                    }
                }else {
                    //  单选项能勾选
                    for (int j = 0; j < mData.size(); j++) {
                        if(mData.get(j).getTaskTypeId().equals(radioList.get(i))){
                            mData.get(j).setCanClick(true);
                        }
                    }
                }
            }
        }
    }
}
