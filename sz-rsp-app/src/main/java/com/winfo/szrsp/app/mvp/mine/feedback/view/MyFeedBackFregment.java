package com.winfo.szrsp.app.mvp.mine.feedback.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.adapter.feedback.FeedBackMultipleAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MyFeedBackFregment extends Fragment {
    private Unbinder unbinder;
    public static int  page=1;
    private boolean bol= false;//判断加载全部数据
    private boolean showFaile = false;//判断是否加载失败
    @BindView(R.id.feedback_seach_recyclerview)
    RecyclerView feedback_seach_recyclerview;
    @BindView(R.id.feedback_all_recyclerview)
    RecyclerView feedback_all_recyclerview;

    FeedBackMultipleAdapter adapter;
    List<MultiItemEntity> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.feedback_fregment, null);
        unbinder = ButterKnife.bind(this, view);
        page=1;
        initData();
        initEvent();
        return view;
    }
    private void initData() {
        list = generateData();
        adapter = new FeedBackMultipleAdapter(list);
        feedback_all_recyclerview.setAdapter(adapter);
        feedback_all_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
    private void initEvent() {

    }
    private ArrayList<MultiItemEntity> generateData() {
        int lv0Count = 4;
        int lv1Count = 1;
        ArrayList<MultiItemEntity> res = new ArrayList<>();
        for (int i = 0; i < lv0Count; i++) {
            FeedBackLevelItem0 lv0 = new FeedBackLevelItem0();
            lv0.setTitle("我是标题");
            lv0.setTime("2018-04-08");
            lv0.setStatus(0);
            for (int j = 0; j < lv1Count; j++) {
                FeedBackLevelItem1 lv1 = new FeedBackLevelItem1();
                lv1.setProblem("我是问题");
                lv1.setSolution("我是问题");
                lv1.setProblemImgUrlList(generateData(14));
                lv1.setSolutionImgUrlList(generateData(4));
                lv0.addSubItem(lv1);
            }
            res.add(lv0);
        }
        return res;
    }

    private List<String> generateData(int size) {
        ArrayList<String> data = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523180763603&di=75cd894cfb3e9141cc989b09eb7c0c87&imgtype=0&src=http%3A%2F%2Fimg4.3lian.com%2Fimg2005%2F08%2F02%2F549.jpg");
        }
        return data;
    }
}
