package com.winfo.szrsp.app.mvp.task.view;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.mvp.table.fragmentlist.ChecklistMainActvity;
import com.winfo.szrsp.app.mvp.task.presenter.TaskInspectionPresenter;
import com.winfo.szrsp.app.sdk.entity.task.TaskAssign;
import com.winfo.szrsp.app.sdk.entity.task.TaskInfo;
import com.winfo.szrsp.app.sdk.entity.task.TaskInfoDetails;
import com.winfo.szrsp.app.sdk.entity.task.TaskInspectionItemData;
import com.winfo.szrsp.app.utils.DateFormatUtils;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.DimensUtils;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.utils.SDCardUtils;
import com.winfo.szrsp.app.utils.ToastUtils;
import com.winfo.szrsp.app.utils.Util;
import com.winfo.szrsp.app.widget.BadgeView;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table
 * @Filename: TaskInspectionActivity
 * @Author: lsj
 * @Date: 2017/12/9  19:24
 * @Description:
 * @Version:
 */

public class TaskInspectionActivity extends Activity implements ITaskInspectionActivity, View.OnClickListener {

    private TaskInspectionPresenter presenter;
    private ExpandableItemAdapter expandableItemAdapter;
    private RecyclerView recyclerView;
    private View titie_view;
    private ImageButton btn_back;
    private TextView tv_title;
    private Button btn_save;
    private String typeId;
    private String checkFormIds;
    private TaskInfo taskInfo;
    private List<TaskInfoDetails> taskInfoDetails;
    private TaskInfoDetails shipinfo;
    private LinearLayout ll_content;
    private LinearLayout ll_faile;
    private TextView tv_faile;
    private int postion;
    private List<TaskAssign> taskAssignList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        intView();
        initData();
        initEvent();
    }

    private void initEvent() {
        ll_faile.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        btn_save.setOnClickListener(this);
    }

    private void initData() {
        tv_title.setText("执行任务");
        List<String> id = (List<String>) getIntent().getSerializableExtra("typeId");

        String str = id.toString();
        int len = str.length() - 1;
        typeId = str.substring(1, len).replace(" ", "");
        taskInfo = (TaskInfo) getIntent().getSerializableExtra("taskInfo");
        taskInfoDetails = (List<TaskInfoDetails>) getIntent().getSerializableExtra("ShipInfo");
        taskAssignList= (List<TaskAssign>)getIntent().getSerializableExtra("taskAssignList");
        checkFormIds = getIntent().getExtras().getString("checkFormIds");
        presenter.GetTaskItemData(checkFormIds);
    }

    private void intView() {
        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter = new TaskInspectionPresenter(this);
        titie_view = findViewById(R.id.title);
        btn_back = titie_view.findViewById(R.id.table_titleBar_imgbtn_back);
        tv_title = findViewById(R.id.table_titleBar_titleText);
        btn_save = findViewById(R.id.btn_save);
        ll_content = findViewById(R.id.ll_content);
        ll_faile = findViewById(R.id.ll_faile);
        tv_faile = ll_faile.findViewById(R.id.id_tv_data_load_faild);
    }


    public List<TaskInspectionItemData> bulid(List<TaskInspectionItemData> taskInspectionItemDataList) {

        List<TaskInspectionItemData> taskInspectionItemDatas = new ArrayList<>();

        for (TaskInspectionItemData taskInspectionItemData : taskInspectionItemDataList) {

            if ("".equals(taskInspectionItemData.getItemFuseFaterId())) {
                taskInspectionItemDatas.add(taskInspectionItemData);
            }

            for (TaskInspectionItemData it : taskInspectionItemDataList) {
                if (it.getItemFuseFaterId().equals(taskInspectionItemData.getItemFuseId())) {
                    if (taskInspectionItemData.getChild() == null) {
                        taskInspectionItemData.setChild(new ArrayList<TaskInspectionItemData>());
                    }
                    taskInspectionItemData.getChild().add(it);
                }
            }
        }
        return taskInspectionItemDatas;
    }


    @Override
    public void setData(List<TaskInspectionItemData> taskInspectionItemData) {

        List<MultiItemEntity> datas = new ArrayList<>();

        List<TaskInspectionItemData> taskInspectionItemDataList = bulid(taskInspectionItemData);

        if (taskInspectionItemDataList.size() == 0){
            ll_content.setVisibility(View.GONE);
            ll_faile.setVisibility(View.VISIBLE);
            tv_faile.setText("没有查到数据！");
        }else {
            ll_content.setVisibility(View.VISIBLE);
            ll_faile.setVisibility(View.GONE);
        }

        for (int i = 0; i < taskInspectionItemDataList.size(); i++) {
            Level0Item level0Item = new Level0Item();

            level0Item.setItemFuseName(taskInspectionItemDataList.get(i).getItemFuseName());
            level0Item.setFscCode(taskInspectionItemDataList.get(i).getFscCode());
            level0Item.setIsMustCheck(taskInspectionItemDataList.get(i).getIsMustCheck());
            level0Item.setItemFuseDetails(taskInspectionItemDataList.get(i).getItemFuseDetails());
            level0Item.setItemFuseFaterId(taskInspectionItemDataList.get(i).getItemFuseFaterId());
            level0Item.setRemarks(taskInspectionItemDataList.get(i).getRemarks());
            level0Item.setItemFuseId(taskInspectionItemDataList.get(i).getItemFuseId());
            for (int j = 0; j < taskInspectionItemDataList.get(i).getChild().size(); j++) {
                if (taskInspectionItemDataList.get(i).getChild().size() > 0 && taskInspectionItemDataList.get(i).getChild() !=null){
                    Level1Item level1Item = new Level1Item();

                    level1Item.setItemFuseName(taskInspectionItemDataList.get(i).getChild().get(j).getItemFuseName());
                    level1Item.setFscCode(taskInspectionItemDataList.get(i).getChild().get(j).getFscCode());
                    level1Item.setIsMustCheck(taskInspectionItemDataList.get(i).getChild().get(j).getIsMustCheck());
                    level1Item.setItemFuseDetails(taskInspectionItemDataList.get(i).getChild().get(j).getItemFuseDetails());
                    level1Item.setItemFuseFaterId(taskInspectionItemDataList.get(i).getChild().get(j).getItemFuseFaterId());
                    level1Item.setRemarks(taskInspectionItemDataList.get(i).getChild().get(j).getRemarks());
                    level1Item.setItemFuseId(taskInspectionItemDataList.get(i).getChild().get(j).getItemFuseId());
                    for (int k = 0; k < taskInspectionItemDataList.get(i).getChild().get(j).getChild().size(); k++) {
                        Level2Item level2Item = new Level2Item();
                        level2Item.setItemFuseName(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getItemFuseName());

                        level2Item.setFscCode(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getFscCode());
                        level2Item.setIsMustCheck(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getIsMustCheck());
                        level2Item.setItemFuseDetails(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getItemFuseDetails());
                        level2Item.setItemFuseFaterId(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getItemFuseFaterId());
                        level2Item.setRemarks(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getRemarks());
                        level2Item.setItemFuseId(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getItemFuseId());
                        level1Item.addSubItem(level2Item);
                        for (int l = 0; l < taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().size(); l++) {
                            Level3Item level3Item = new Level3Item();
                            level3Item.setItemFuseName(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getItemFuseName());

                            level3Item.setFscCode(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getFscCode());
                            level3Item.setIsMustCheck(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getIsMustCheck());
                            level3Item.setItemFuseDetails(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getItemFuseDetails());
                            level3Item.setItemFuseFaterId(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getItemFuseFaterId());
                            level3Item.setRemarks(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getRemarks());
                            level3Item.setItemFuseId(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getItemFuseId());
                            level2Item.addSubItem(level3Item);
                            for (int m = 0; m < taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().size(); m++) {
                                Level4Item level4Item = new Level4Item();
                                level4Item.setItemFuseName(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).getItemFuseName());

                                level4Item.setFscCode(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).getFscCode());
                                level4Item.setIsMustCheck(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).getIsMustCheck());
                                level4Item.setItemFuseDetails(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).getItemFuseDetails());
                                level4Item.setItemFuseFaterId(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).getItemFuseFaterId());
                                level4Item.setRemarks(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).getRemarks());
                                level4Item.setItemFuseId(taskInspectionItemDataList.get(i).getChild().get(j).getChild().get(k).getChild().get(l).getChild().get(m).getItemFuseId());
                                level3Item.addSubItem(level4Item);
                            }
                        }
                    }
                    level0Item.addSubItem(level1Item);
                }
            }
            datas.add(level0Item);
        }

        expandableItemAdapter = new ExpandableItemAdapter(datas,TaskInspectionActivity.this);

        recyclerView.setAdapter(expandableItemAdapter);
        expandableItemAdapter.expand(0 , true);

        expandableItemAdapter.setOnTakePhotoListener(new ExpandableItemAdapter.OnTakePhotoListener() {
            @Override
            public void photo(int pos,ImageView imageView) {
                postion = pos;
                showpop(imageView);
            }
        });

    }

    @Override
    public Dialog getDialog() {
        Dialog dialog = DialogUtils.createLoadingDialog(this, "加载中...");
        return dialog;
    }

    @Override
    public void OnFaile(String msg) {
        ll_content.setVisibility(View.GONE);
        ll_faile.setVisibility(View.VISIBLE);
        tv_faile.setText(msg);
    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(this,msg,msg);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_faile:
                presenter.GetTaskItemData(checkFormIds);
                break;

            case R.id.table_titleBar_imgbtn_back:
                finish();
                break;
            case R.id.photo_img_1:
                openPhoto(1001);
                break;
            case R.id.video_img_1:
                openVideo(1002);
                break;
            case R.id.xiangce_img_1:
                openAlbum(1003);
                break;
            case R.id.btn_save:
                List<MultiItemEntity> data = expandableItemAdapter.getData();
                List<LevelItem> levelItems = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getItemType() == 0){
                        Level0Item level0Item = (Level0Item) data.get(i);
                        if (level0Item.getSubItems() !=null){
                            for (int j = 0; j < level0Item.getSubItems().size(); j++) {
                                if (!level0Item.getSubItems().get(j).getInput().equals("")){
                                    LevelItem item = new LevelItem();
                                    item.setFscCode(level0Item.getSubItems().get(j).getFscCode());
                                    item.setIsMustCheck(level0Item.getSubItems().get(j).getIsMustCheck());
                                    item.setItemFuseDetails(level0Item.getSubItems().get(j).getItemFuseDetails());
                                    item.setItemFuseFaterId(level0Item.getSubItems().get(j).getItemFuseFaterId());
                                    item.setItemFuseId(level0Item.getSubItems().get(j).getItemFuseId());
                                    item.setItemFuseName(level0Item.getSubItems().get(j).getItemFuseName());
                                    item.setRemarks(level0Item.getSubItems().get(j).getRemarks());
                                    item.setInput(level0Item.getSubItems().get(j).getInput());
                                    item.setImg_path(level0Item.getSubItems().get(j).getImg_path());
                                    item.setVideo_path(level0Item.getSubItems().get(j).getVideo_path());
                                    item.setAlbum_path(level0Item.getSubItems().get(j).getAlbum_path());
                                    levelItems.add(item);
                                }
                                if (level0Item.getSubItems().get(j).getSubItems() !=null){
                                    for (int k = 0; k < level0Item.getSubItems().get(j).getSubItems().size(); k++) {
                                        if (!level0Item.getSubItems().get(j).getSubItems().get(k).getInput().equals("")){
                                            LevelItem item = new LevelItem();
                                            item.setFscCode(level0Item.getSubItems().get(j).getSubItems().get(k).getFscCode());
                                            item.setIsMustCheck(level0Item.getSubItems().get(j).getSubItems().get(k).getIsMustCheck());
                                            item.setItemFuseDetails(level0Item.getSubItems().get(j).getSubItems().get(k).getItemFuseDetails());
                                            item.setItemFuseFaterId(level0Item.getSubItems().get(j).getSubItems().get(k).getItemFuseFaterId());
                                            item.setItemFuseId(level0Item.getSubItems().get(j).getSubItems().get(k).getItemFuseId());
                                            item.setItemFuseName(level0Item.getSubItems().get(j).getSubItems().get(k).getItemFuseName());
                                            item.setRemarks(level0Item.getSubItems().get(j).getSubItems().get(k).getRemarks());
                                            item.setInput(level0Item.getSubItems().get(j).getSubItems().get(k).getInput());
                                            item.setImg_path(level0Item.getSubItems().get(j).getSubItems().get(k).getImg_path());
                                            item.setVideo_path(level0Item.getSubItems().get(j).getSubItems().get(k).getVideo_path());
                                            item.setAlbum_path(level0Item.getSubItems().get(j).getSubItems().get(k).getAlbum_path());
                                            levelItems.add(item);
                                        }
                                        if (level0Item.getSubItems().get(j).getSubItems().get(k).getSubItems() != null){
                                            for (int l = 0; l < level0Item.getSubItems().get(j).getSubItems().get(k).getSubItems().size(); l++) {
                                                if (!level0Item.getSubItems().get(j).getSubItems().get(k).getSubItems().get(l).getInput().equals("")){
                                                    LevelItem item = new LevelItem();
                                                    item.setFscCode(level0Item.getSubItems().get(j).getSubItems().get(k).getSubItems().get(l).getFscCode());
                                                    item.setIsMustCheck(level0Item.getSubItems().get(j).getSubItems().get(k).getSubItems().get(l).getIsMustCheck());
                                                    item.setItemFuseDetails(level0Item.getSubItems().get(j).getSubItems().get(k).getSubItems().get(l).getItemFuseDetails());
                                                    item.setItemFuseFaterId(level0Item.getSubItems().get(j).getSubItems().get(k).getSubItems().get(l).getItemFuseFaterId());
                                                    item.setItemFuseId(level0Item.getSubItems().get(j).getSubItems().get(k).getSubItems().get(l).getItemFuseId());
                                                    item.setItemFuseName(level0Item.getSubItems().get(j).getSubItems().get(k).getSubItems().get(l).getItemFuseName());
                                                    item.setRemarks(level0Item.getSubItems().get(j).getSubItems().get(k).getSubItems().get(l).getRemarks());
                                                    item.setInput(level0Item.getSubItems().get(j).getSubItems().get(k).getSubItems().get(l).getInput());
                                                    item.setImg_path(level0Item.getSubItems().get(j).getSubItems().get(k).getSubItems().get(l).getImg_path());
                                                    item.setVideo_path(level0Item.getSubItems().get(j).getSubItems().get(k).getSubItems().get(l).getVideo_path());
                                                    item.setAlbum_path(level0Item.getSubItems().get(j).getSubItems().get(k).getSubItems().get(l).getAlbum_path());
                                                    levelItems.add(item);
                                                }
                                                if (level0Item.getSubItems().get(j).getSubItems().get(k).getSubItems().get(l).getSubItems() != null){
                                                    for (int m = 0; m < level0Item.getSubItems().get(j).getSubItems().get(k).getSubItems().get(l).getSubItems().size(); m++) {
                                                        Level4Item level4Item = level0Item.getSubItems().get(j).getSubItems().get(k).getSubItems().get(l).getSubItems().get(m);
                                                        if (level4Item!=null){
                                                            if (!level0Item.getSubItems().get(j).getSubItems().get(k).getSubItems().get(l).getSubItems().get(m).getInput().equals("")){
                                                                LevelItem item = new LevelItem();
                                                                item.setFscCode(level0Item.getSubItems().get(j).getSubItems().get(k).getSubItems().get(l).getSubItems().get(m).getFscCode());
                                                                item.setIsMustCheck(level0Item.getSubItems().get(j).getSubItems().get(k).getSubItems().get(l).getSubItems().get(m).getIsMustCheck());
                                                                item.setItemFuseDetails(level0Item.getSubItems().get(j).getSubItems().get(k).getSubItems().get(l).getSubItems().get(m).getItemFuseDetails());
                                                                item.setItemFuseFaterId(level0Item.getSubItems().get(j).getSubItems().get(k).getSubItems().get(l).getSubItems().get(m).getItemFuseFaterId());
                                                                item.setItemFuseId(level0Item.getSubItems().get(j).getSubItems().get(k).getSubItems().get(l).getSubItems().get(m).getItemFuseId());
                                                                item.setItemFuseName(level0Item.getSubItems().get(j).getSubItems().get(k).getSubItems().get(l).getSubItems().get(m).getItemFuseName());
                                                                item.setRemarks(level0Item.getSubItems().get(j).getSubItems().get(k).getSubItems().get(l).getSubItems().get(m).getRemarks());
                                                                item.setInput(level0Item.getSubItems().get(j).getSubItems().get(k).getSubItems().get(l).getSubItems().get(m).getInput());
                                                                item.setImg_path(level0Item.getSubItems().get(j).getSubItems().get(k).getSubItems().get(l).getSubItems().get(m).getImg_path());
                                                                item.setVideo_path(level0Item.getSubItems().get(j).getSubItems().get(k).getSubItems().get(l).getSubItems().get(m).getVideo_path());
                                                                item.setAlbum_path(level0Item.getSubItems().get(j).getSubItems().get(k).getSubItems().get(l).getSubItems().get(m).getAlbum_path());
                                                                levelItems.add(item);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

//                if (levelItems.size() > 0){
//                    Intent intent = new Intent(TaskInspectionActivity.this, ChecklistMainActvity.class);
//                    intent.putExtra("taskItem", (Serializable) levelItems);
//                    intent.putExtra("typeId",typeId);
//                    intent.putExtra("taskInfo",taskInfo);
//                    intent.putExtra("ShipInfo", (Serializable) taskInfoDetails);
//                    intent.putExtra("taskAssignList", (Serializable) taskAssignList);
//                    startActivity(intent);
//                }else {
//                    ToastUtils.showToast(TaskInspectionActivity.this,"请填写问题描述！");
//                }

                Intent intent = new Intent(TaskInspectionActivity.this, ChecklistMainActvity.class);
                intent.putExtra("taskItem", (Serializable) levelItems);
                intent.putExtra("typeId",typeId);
                intent.putExtra("taskInfo",taskInfo);
                intent.putExtra("ShipInfo", (Serializable) taskInfoDetails);
                intent.putExtra("taskAssignList", (Serializable) taskAssignList);
                startActivity(intent);
                break;
        }
    }

    private void showBadge(ImageView imageView){
        BadgeView badgeView = new BadgeView(TaskInspectionActivity.this,imageView);
        badgeView.setText("1");
        badgeView.setTextSize(5);
        badgeView.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001 && resultCode == Activity.RESULT_OK){
            String path = photoFile.getAbsolutePath();
            if (expandableItemAdapter.getData().get(postion).getItemType() == 1){
                Level1Item level1Item = (Level1Item) expandableItemAdapter.getData().get(postion);
                level1Item.setImg_path(path);
                if(photoFile!=null){
                    showBadge(photo_img);
                }
            }else if (expandableItemAdapter.getData().get(postion).getItemType() == 2){
                Level2Item level2Item = (Level2Item) expandableItemAdapter.getData().get(postion);
                level2Item.setImg_path(path);
                if(photoFile!=null){
                    showBadge(photo_img);
                }
            }else if (expandableItemAdapter.getData().get(postion).getItemType() == 3){
                Level3Item level2Item = (Level3Item) expandableItemAdapter.getData().get(postion);
                level2Item.setImg_path(path);
                if(photoFile!=null){
                    showBadge(photo_img);
                }
            }else if (expandableItemAdapter.getData().get(postion).getItemType() == 4){
                Level4Item level2Item = (Level4Item) expandableItemAdapter.getData().get(postion);
                level2Item.setImg_path(path);
                if(photoFile!=null){
                    showBadge(photo_img);
                }
            }
        }else if (requestCode == 1002 && resultCode == Activity.RESULT_OK){
            String path = videoFile.getAbsolutePath();
            if (expandableItemAdapter.getData().get(postion).getItemType() == 1){
                Level1Item level1Item = (Level1Item) expandableItemAdapter.getData().get(postion);
                level1Item.setVideo_path(path);
                if(videoFile!=null){
                    showBadge(video_img);
                }
            }else if (expandableItemAdapter.getData().get(postion).getItemType() == 2){
                Level2Item level2Item = (Level2Item) expandableItemAdapter.getData().get(postion);
                level2Item.setVideo_path(path);
                if(videoFile!=null){
                    showBadge(video_img);
                }
            }else if (expandableItemAdapter.getData().get(postion).getItemType() == 2){
                Level3Item level2Item = (Level3Item) expandableItemAdapter.getData().get(postion);
                level2Item.setVideo_path(path);
                if(videoFile!=null){
                    showBadge(video_img);
                }
            }else if (expandableItemAdapter.getData().get(postion).getItemType() == 2){
                Level4Item level2Item = (Level4Item) expandableItemAdapter.getData().get(postion);
                level2Item.setVideo_path(path);
                if(videoFile!=null){
                    showBadge(video_img);
                }
            }
        }else if (requestCode == 1003 && resultCode == Activity.RESULT_OK){
            Uri uri = data.getData();
            String path = Util.getPath(TaskInspectionActivity.this, uri);
            assert path != null;
            albumFile = new File(path);
            if (expandableItemAdapter.getData().get(postion).getItemType() == 1){
                Level1Item level1Item = (Level1Item) expandableItemAdapter.getData().get(postion);
                level1Item.setAlbum_path(path);
                if(albumFile!=null){
                    showBadge(album_img);
                }
            }else if (expandableItemAdapter.getData().get(postion).getItemType() == 2){
                Level2Item level2Item = (Level2Item) expandableItemAdapter.getData().get(postion);
                level2Item.setAlbum_path(path);
                if(albumFile!=null){
                    showBadge(album_img);
                }
            }else if (expandableItemAdapter.getData().get(postion).getItemType() == 2){
                Level3Item level2Item = (Level3Item) expandableItemAdapter.getData().get(postion);
                level2Item.setAlbum_path(path);
                if(albumFile!=null){
                    showBadge(album_img);
                }
            }else if (expandableItemAdapter.getData().get(postion).getItemType() == 2){
                Level4Item level2Item = (Level4Item) expandableItemAdapter.getData().get(postion);
                level2Item.setAlbum_path(path);
                if(albumFile!=null){
                    showBadge(album_img);
                }
            }
        }
    }

    View mView;
    private PopupWindow mPopupWindow;
    private ImageView photo_img;
    private ImageView video_img;
    private ImageView album_img;
    public File photoFile; // 照片 语音 视频的文件
    public File videoFile;
    public File albumFile;

    private void showpop(final ImageView imageView) {
        mView = View.inflate(this, R.layout.scene_pop, null);
        mPopupWindow = new PopupWindow(mView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopupWindow.setFocusable(true);// 设置焦点
        mPopupWindow.setAnimationStyle(R.style.scene_popwin_anim_style); // 动画效果
        mPopupWindow.setOutsideTouchable(true);// 关闭弹出窗体
        mPopupWindow.showAsDropDown(imageView, DimensUtils.dp2px(this, -120), DimensUtils.dp2px(this, -32));
        video_img = mView.findViewById(R.id.video_img_1);
        photo_img = mView.findViewById(R.id.photo_img_1);
        album_img = mView.findViewById(R.id.xiangce_img_1);
        video_img.setOnClickListener(this);
        photo_img.setOnClickListener(this);
        album_img.setOnClickListener(this);

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
//                if (expandableItemAdapter.getData().get(postion).getItemType() == 1){
//                    int num = 0;
//                    TaskDefectItemData level1Item = (TaskDefectItemData) expandableItemAdapter.getData().get(postion);
//                    if(!level1Item.getImg_path().equals("")){
//                        num = +1;
//                    }else if(!level1Item.getVideo_path().equals("")){
//                        num = +1;
//                    }
//                    level1Item.setNum(num);
//                }else if (expandableItemAdapter.getData().get(postion).getItemType() == 2){
//                    int num = 0;
//                    Level2Item level2Item = (Level2Item) expandableItemAdapter.getData().get(postion);
//                    if(!level2Item.getImg_path().equals("")){
//                        num = +1;
//                    }else if(!level2Item.getVideo_path().equals("")){
//                        num = +1;
//                    }
//                    level2Item.setNum(num);
//                }
//                showBadge(imageView);
            }
        });

        if (expandableItemAdapter.getData().get(postion).getItemType() == 1){
            Level1Item level1Item = (Level1Item) expandableItemAdapter.getData().get(postion);
            if(!level1Item.getImg_path().equals("")){
                showBadge(photo_img);
            }
            if(!level1Item.getVideo_path().equals("")){
                showBadge(video_img);
            }
            if(!level1Item.getAlbum_path().equals("")){
                showBadge(album_img);
            }
        }else if (expandableItemAdapter.getData().get(postion).getItemType() == 2){
            Level2Item level2Item = (Level2Item) expandableItemAdapter.getData().get(postion);
            if(!level2Item.getImg_path().equals("")){
                showBadge(photo_img);
            }
            if(!level2Item.getVideo_path().equals("")){
                showBadge(video_img);
            }
            if(!level2Item.getAlbum_path().equals("")){
                showBadge(album_img);
            }
        }else if (expandableItemAdapter.getData().get(postion).getItemType() == 3){
            Level3Item level3Item = (Level3Item) expandableItemAdapter.getData().get(postion);
            if(!level3Item.getImg_path().equals("")){
                showBadge(photo_img);
            }
            if(!level3Item.getVideo_path().equals("")){
                showBadge(video_img);
            }
            if(!level3Item.getAlbum_path().equals("")){
                showBadge(album_img);
            }
        }else if (expandableItemAdapter.getData().get(postion).getItemType() == 4){
            Level4Item level4Item = (Level4Item) expandableItemAdapter.getData().get(postion);
            if(!level4Item.getImg_path().equals("")){
                showBadge(photo_img);
            }
            if(!level4Item.getVideo_path().equals("")){
                showBadge(video_img);
            }
            if(!level4Item.getAlbum_path().equals("")){
                showBadge(album_img);
            }
        }

    }

    private void openPhoto(int result){
        //申明意图对象
        Intent intent;
        //申明文件要保存的位置
        File dir;
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        //初始化文件保存位置的对象
        dir = new File(SDCardUtils.getRootDirectory() + "/szrsApp/picture/");
        if (!dir.exists()) {
            //如果文件夹不存在 则创建
            dir.mkdirs();
        }
        //创建要保存的文件
        if (result == 1001){
            photoFile = new File(dir.getAbsolutePath(), "IMG_" + DateFormatUtils.getPVAFormatDate() + ".jpg");
        }
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ToastUtils.showToast(this, "please open this permission");
            return;
        }
        // 打开系统相机
        intent = new Intent();
        //兼容7.0
        if (currentapiVersion < 24) {
            // 从文件中创建uri
            Uri uri = null;
            if (result == 1001){
                uri = Uri.fromFile(photoFile);
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }else{
            //兼容android7.0 使用共享文件的形式
            ContentValues contentValues = new ContentValues(1);
            if (result == 1001){
                contentValues.put(MediaStore.Images.Media.DATA, photoFile.getAbsolutePath());
            }
            Uri uri = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        //设置打开系统相机的意图action
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        //打开相机
        startActivityForResult(intent,1001);
    }

    public void openVideo(int result){
        //申明意图对象
        Intent intent;
        //申明文件要保存的位置
        File dir;
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        //申明视频文件需要保存的文件夹
        dir = new File(SDCardUtils.getRootDirectory() + "/szrsApp/video/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if(ActivityCompat.checkSelfPermission(this , Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            ToastUtils.showToast(this, "please open this permission");
            return;
        }
        intent = new Intent();
        //初始化视频文件的名字
        if (result == 1002){
            videoFile = new File(dir.getAbsoluteFile(), "VID_" + DateFormatUtils.getPVAFormatDate() + ".3gp");
        }
        if (currentapiVersion < 24) {
            // 从文件中创建uri
            Uri uri = null;
            if (result == 1002){
                uri = Uri.fromFile(videoFile);
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }else{
            //兼容android7.0 使用共享文件的形式
            ContentValues contentValues = new ContentValues(1);
            if (result == 1002){
                contentValues.put(MediaStore.Images.Media.DATA, videoFile.getAbsolutePath());
            }
            Uri uri = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        //设置打开视频录制的action
        intent.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 1002);
        startActivityForResult(intent,1002);
    }

    private void openAlbum(int result){
        // 打开系统相册
        Intent intent = new Intent();
        //过滤 选择的相册是所有类型的
        intent.setType("image/*");
        //设置action
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, result);
    }
}
