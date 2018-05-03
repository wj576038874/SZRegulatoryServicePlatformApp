package com.winfo.szrsp.app.mvp.table.kxjc.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.utils.v4.Bundler;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.mvp.table.finishtask.view.FinishTaskActivity;
import com.winfo.szrsp.app.mvp.table.fragmentlist.DisposalDecisionDialog;
import com.winfo.szrsp.app.mvp.table.kxjc.presenter.KxjcPresenter;
import com.winfo.szrsp.app.sdk.entity.table.ContainerWeightInspectData;
import com.winfo.szrsp.app.sdk.entity.table.DangerousGoodsKaiXiangData;
import com.winfo.szrsp.app.sdk.entity.table.DangerousGoodsXianChangData;
import com.winfo.szrsp.app.sdk.entity.table.KxjcData;
import com.winfo.szrsp.app.sdk.entity.table.OrdinaryGoodsKaiXiangData;
import com.winfo.szrsp.app.sdk.entity.task.FourTableSubData;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.utils.ToastUtils;
import com.winfo.szrsp.app.widget.MySmartTabLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



public class KxjcActivity extends FragmentActivity implements IKxjcActivity, View.OnClickListener {
    @BindView(R.id.but_Submit)
    TextView but_Submit;
    @BindView(R.id.titleBar_imgbtn_back)
    ImageButton titleBar_imgbtn_back;
    private FragmentPagerItems pages;
    private String taskId;
    private String shipNameCn;
    private String shipNameEn;
    private String check1, check2, check3, check4;
    private DangerousGoodsXianChangFragment fragment;
    private KxjcData data;
    private DangerousGoodsXianChangData ctDangerPolluteScene;
    private DangerousGoodsKaiXiangData ctDangerPolluteOut;
    private OrdinaryGoodsKaiXiangData ctGoodSecneOut;
    private ContainerWeightInspectData ctCaseWeightInspect;
    private DisposalDecisionDialog disposalDecisionDialog;
    private Dialog dialog;
    private KxjcPresenter presenter;
    private String disposalDecision;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kxjc);
        ButterKnife.bind(this);
        initView();
        initEvent();
    }

    private void initView() {
        dialog = DialogUtils.createLoadingDialog(this, "请稍后...");
        data = new KxjcData();
        presenter = new KxjcPresenter(this);
        taskId = getIntent().getStringExtra("taskId");
        shipNameCn = getIntent().getStringExtra("shipNameCn");
        shipNameEn = getIntent().getStringExtra("shipNameEn");
        check1 = getIntent().getStringExtra("check1");
        check2 = getIntent().getStringExtra("check2");
        check3 = getIntent().getStringExtra("check3");
        check4 = getIntent().getStringExtra("check4");

        if ("".equals(shipNameCn)) {
            shipNameCn = "";
        } else if ("".equals(shipNameEn)) {
            shipNameEn = "";
        }
        fragment = new DangerousGoodsXianChangFragment();
        Bundle bundle = new Bundle();
        bundle.putString("shipNameCn", shipNameCn);
        bundle.putString("shipNameEn", shipNameEn);
        fragment.setArguments(bundle);
        pages = new FragmentPagerItems(this);
        if (check1.equals("1") && check2.equals("1") && check3.equals("1") && check4.equals("1")) {//1234
            pages.add(FragmentPagerItem.of("集装箱危险/污染危害性货物现场检查记录表", DangerousGoodsXianChangFragment.class, new Bundler().putInt("value", 1).putString("shipNameCn", shipNameCn).putString("shipNameEn", shipNameEn).get()));
            pages.add(FragmentPagerItem.of("集装箱危险/污染危害性货物开箱检查记录表", DangerousGoodsKaiXiangFragment.class, new Bundler().putInt("value", 2).get()));
            pages.add(FragmentPagerItem.of("普通货物集装箱开箱检查记录表", OrdinaryGoodsKaiXiangFragment.class, new Bundler().putInt("value", 3).get()));
            pages.add(FragmentPagerItem.of("货物集装箱重量验证检查记录表", ContainerWeightInspectFragment.class, new Bundler().putInt("value", 4).get()));
        } else if (check1.equals("1") && check2.equals("1") && check3.equals("1") && !check4.equals("1")) {//123
            pages.add(FragmentPagerItem.of("集装箱危险/污染危害性货物现场检查记录表", DangerousGoodsXianChangFragment.class, new Bundler().putInt("value", 1).putString("shipNameCn", shipNameCn).putString("shipNameEn", shipNameEn).get()));
            pages.add(FragmentPagerItem.of("集装箱危险/污染危害性货物开箱检查记录表", DangerousGoodsKaiXiangFragment.class, new Bundler().putInt("value", 2).get()));
            pages.add(FragmentPagerItem.of("普通货物集装箱开箱检查记录表", OrdinaryGoodsKaiXiangFragment.class, new Bundler().putInt("value", 3).get()));
        } else if (check1.equals("1") && check2.equals("1") && !check3.equals("1") && check4.equals("1")) {//124
            pages.add(FragmentPagerItem.of("集装箱危险/污染危害性货物现场检查记录表", DangerousGoodsXianChangFragment.class, new Bundler().putInt("value", 1).putString("shipNameCn", shipNameCn).putString("shipNameEn", shipNameEn).get()));
            pages.add(FragmentPagerItem.of("集装箱危险/污染危害性货物开箱检查记录表", DangerousGoodsKaiXiangFragment.class, new Bundler().putInt("value", 2).get()));
            pages.add(FragmentPagerItem.of("货物集装箱重量验证检查记录表", ContainerWeightInspectFragment.class, new Bundler().putInt("value", 4).get()));
        } else if (check1.equals("1") && !check2.equals("1") && check3.equals("1") && check4.equals("1")) {//134
            pages.add(FragmentPagerItem.of("集装箱危险/污染危害性货物现场检查记录表", DangerousGoodsXianChangFragment.class, new Bundler().putInt("value", 1).putString("shipNameCn", shipNameCn).putString("shipNameEn", shipNameEn).get()));
            pages.add(FragmentPagerItem.of("普通货物集装箱开箱检查记录表", OrdinaryGoodsKaiXiangFragment.class, new Bundler().putInt("value", 3).get()));
            pages.add(FragmentPagerItem.of("货物集装箱重量验证检查记录表", ContainerWeightInspectFragment.class, new Bundler().putInt("value", 4).get()));
        } else if (!check1.equals("1") && check2.equals("1") && check3.equals("1") && check4.equals("1")) {//234
            pages.add(FragmentPagerItem.of("集装箱危险/污染危害性货物开箱检查记录表", DangerousGoodsKaiXiangFragment.class, new Bundler().putInt("value", 2).get()));
            pages.add(FragmentPagerItem.of("普通货物集装箱开箱检查记录表", OrdinaryGoodsKaiXiangFragment.class, new Bundler().putInt("value", 3).get()));
            pages.add(FragmentPagerItem.of("货物集装箱重量验证检查记录表", ContainerWeightInspectFragment.class, new Bundler().putInt("value", 4).get()));
        } else if (check1.equals("1") && check2.equals("1") && !check3.equals("1") && !check4.equals("1")) {//12
            pages.add(FragmentPagerItem.of("集装箱危险/污染危害性货物现场检查记录表", DangerousGoodsXianChangFragment.class, new Bundler().putInt("value", 1).putString("shipNameCn", shipNameCn).putString("shipNameEn", shipNameEn).get()));
            pages.add(FragmentPagerItem.of("集装箱危险/污染危害性货物开箱检查记录表", DangerousGoodsKaiXiangFragment.class, new Bundler().putInt("value", 2).get()));
        } else if (check1.equals("1") && !check2.equals("1") && check3.equals("1") && !check4.equals("1")) {//13
            pages.add(FragmentPagerItem.of("集装箱危险/污染危害性货物现场检查记录表", DangerousGoodsXianChangFragment.class, new Bundler().putInt("value", 1).putString("shipNameCn", shipNameCn).putString("shipNameEn", shipNameEn).get()));
            pages.add(FragmentPagerItem.of("普通货物集装箱开箱检查记录表", OrdinaryGoodsKaiXiangFragment.class, new Bundler().putInt("value", 3).get()));
        } else if (check1.equals("1") && !check2.equals("1") && !check3.equals("1") && check4.equals("1")) {//14
            pages.add(FragmentPagerItem.of("集装箱危险/污染危害性货物现场检查记录表", DangerousGoodsXianChangFragment.class, new Bundler().putInt("value", 1).putString("shipNameCn", shipNameCn).putString("shipNameEn", shipNameEn).get()));
            pages.add(FragmentPagerItem.of("货物集装箱重量验证检查记录表", ContainerWeightInspectFragment.class, new Bundler().putInt("value", 4).get()));
        } else if (check1.equals("1") && !check2.equals("1") && !check3.equals("1") && check4.equals("1")) {//23
            pages.add(FragmentPagerItem.of("集装箱危险/污染危害性货物开箱检查记录表", DangerousGoodsKaiXiangFragment.class, new Bundler().putInt("value", 2).get()));
            pages.add(FragmentPagerItem.of("普通货物集装箱开箱检查记录表", OrdinaryGoodsKaiXiangFragment.class, new Bundler().putInt("value", 3).get()));
        } else if (!check1.equals("1") && check2.equals("1") && !check3.equals("1") && check4.equals("1")) {//24
            pages.add(FragmentPagerItem.of("集装箱危险/污染危害性货物开箱检查记录表", DangerousGoodsKaiXiangFragment.class, new Bundler().putInt("value", 2).get()));
            pages.add(FragmentPagerItem.of("货物集装箱重量验证检查记录表", ContainerWeightInspectFragment.class, new Bundler().putInt("value", 4).get()));
        } else if (!check1.equals("1") && !check2.equals("1") && check3.equals("1") && check4.equals("1")) {//34
            pages.add(FragmentPagerItem.of("普通货物集装箱开箱检查记录表", OrdinaryGoodsKaiXiangFragment.class, new Bundler().putInt("value", 3).get()));
            pages.add(FragmentPagerItem.of("货物集装箱重量验证检查记录表", ContainerWeightInspectFragment.class, new Bundler().putInt("value", 4).get()));
        } else if (check1.equals("1") && !check2.equals("1") && !check3.equals("1") && !check4.equals("1")) {//1
            pages.add(FragmentPagerItem.of("集装箱危险/污染危害性货物现场检查记录表", DangerousGoodsXianChangFragment.class, new Bundler().putInt("value", 1).putString("shipNameCn", shipNameCn).putString("shipNameEn", shipNameEn).get()));
        } else if (!check1.equals("1") && check2.equals("1") && !check3.equals("1") && !check4.equals("1")) {//2
            pages.add(FragmentPagerItem.of("集装箱危险/污染危害性货物开箱检查记录表", DangerousGoodsKaiXiangFragment.class, new Bundler().putInt("value", 2).get()));
        } else if (!check1.equals("1") && !check2.equals("1") && check3.equals("1") && !check4.equals("1")) {//3
            pages.add(FragmentPagerItem.of("普通货物集装箱开箱检查记录表", OrdinaryGoodsKaiXiangFragment.class, new Bundler().putInt("value", 3).get()));
        } else if (!check1.equals("1") && !check2.equals("1") && !check3.equals("1") && check4.equals("1")) {//4
            pages.add(FragmentPagerItem.of("货物集装箱重量验证检查记录表", ContainerWeightInspectFragment.class, new Bundler().putInt("value", 4).get()));
        }

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), pages);
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(pages.size());
        MySmartTabLayout viewPagerTab = findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);
    }

    private void initEvent() {
        titleBar_imgbtn_back.setOnClickListener(this);
        but_Submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleBar_imgbtn_back:
                finish();
                break;
            case R.id.but_Submit:
                submit();
                break;
        }
    }

    private void submit() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            if (fragment instanceof DangerousGoodsXianChangFragment) {
                DangerousGoodsXianChangFragment dangerousGoodsXianChangFragment = (DangerousGoodsXianChangFragment) fragment;
                dangerousGoodsXianChangFragment.getData(new DangerousGoodsXianChangFragment.GetDataCallBack() {
                    @Override
                    public void getResult(DangerousGoodsXianChangData data) {
                        ctDangerPolluteScene = data;
                    }
                });
            } else if (fragment instanceof DangerousGoodsKaiXiangFragment) {
                DangerousGoodsKaiXiangFragment dangerousGoodsKaiXiangFragment = (DangerousGoodsKaiXiangFragment) fragment;
                dangerousGoodsKaiXiangFragment.getData(new DangerousGoodsKaiXiangFragment.GetDataCallBack() {
                    @Override
                    public void getResult(DangerousGoodsKaiXiangData data) {
                        ctDangerPolluteOut = data;
                    }
                });
            } else if (fragment instanceof OrdinaryGoodsKaiXiangFragment) {
                OrdinaryGoodsKaiXiangFragment ordinaryGoodsKaiXiangFragment = (OrdinaryGoodsKaiXiangFragment) fragment;
                ordinaryGoodsKaiXiangFragment.getData(new OrdinaryGoodsKaiXiangFragment.GetDataCallBack() {
                    @Override
                    public void getResult(OrdinaryGoodsKaiXiangData data) {
                        ctGoodSecneOut = data;
                    }
                });
            } else if (fragment instanceof ContainerWeightInspectFragment) {
                ContainerWeightInspectFragment containerWeightInspectFragment = (ContainerWeightInspectFragment) fragment;
                containerWeightInspectFragment.getData(new ContainerWeightInspectFragment.GetDataCallBack() {
                    @Override
                    public void getResult(ContainerWeightInspectData data) {
                        ctCaseWeightInspect = data;
                    }
                });
            }
        }
        boolean bol = true;
        if (check1.equals("1") && check2.equals("1") && check3.equals("1") && check4.equals("1")) {//1234
            if ("".equals(ctDangerPolluteScene.getCtDangerPolluteScene().getShipNameCn()) && "".equals(ctDangerPolluteScene.getCtDangerPolluteScene().getShipNameEn())) {
                ToastUtils.showToast(KxjcActivity.this, "请将危险货物现场检查表船名填写完整");
                bol = false;
            } else if (ctDangerPolluteScene.getCtDangerPolluteSceneDetail().size() <= 0) {
                ToastUtils.showToast(KxjcActivity.this, "危险货物现场检查表至少填写一行整改结果");
                bol = false;
            }
        } else if (check1.equals("1") && check2.equals("1") && check3.equals("1") && !check4.equals("1")) {//123
            if ("".equals(ctDangerPolluteScene.getCtDangerPolluteScene().getShipNameCn()) && "".equals(ctDangerPolluteScene.getCtDangerPolluteScene().getShipNameEn())) {
                ToastUtils.showToast(KxjcActivity.this, "请将危险货物现场检查表船名填写完整");
                bol = false;
            } else if (ctDangerPolluteScene.getCtDangerPolluteSceneDetail().size() <= 0) {
                ToastUtils.showToast(KxjcActivity.this, "危险货物现场检查表至少填写一行整改结果");
                bol = false;
            }
        } else if (check1.equals("1") && check2.equals("1") && !check3.equals("1") && check4.equals("1")) {//124
            if ("".equals(ctDangerPolluteScene.getCtDangerPolluteScene().getShipNameCn()) && "".equals(ctDangerPolluteScene.getCtDangerPolluteScene().getShipNameEn())) {
                ToastUtils.showToast(KxjcActivity.this, "请将危险货物现场检查表船名填写完整");
                bol = false;
            } else if (ctDangerPolluteScene.getCtDangerPolluteSceneDetail().size() <= 0) {
                ToastUtils.showToast(KxjcActivity.this, "危险货物现场检查表至少填写一行整改结果");
                bol = false;
            }
        } else if (check1.equals("1") && !check2.equals("1") && check3.equals("1") && check4.equals("1")) {//134
            if ("".equals(ctDangerPolluteScene.getCtDangerPolluteScene().getShipNameCn()) && "".equals(ctDangerPolluteScene.getCtDangerPolluteScene().getShipNameEn())) {
                ToastUtils.showToast(KxjcActivity.this, "请将危险货物现场检查表船名填写完整");
                bol = false;
            } else if (ctDangerPolluteScene.getCtDangerPolluteSceneDetail().size() <= 0) {
                ToastUtils.showToast(KxjcActivity.this, "危险货物现场检查表至少填写一行整改结果");
                bol = false;
            }
        }
//                    else if (!check1.equals("1") && check2.equals("1") && check3.equals("1") && check4.equals("1")) {//234
//                    }
        else if (check1.equals("1") && check2.equals("1") && !check3.equals("1") && !check4.equals("1")) {//12
            if ("".equals(ctDangerPolluteScene.getCtDangerPolluteScene().getShipNameCn()) && "".equals(ctDangerPolluteScene.getCtDangerPolluteScene().getShipNameEn())) {
                ToastUtils.showToast(KxjcActivity.this, "请将危险货物现场检查表船名填写完整");
                bol = false;
            } else if (ctDangerPolluteScene.getCtDangerPolluteSceneDetail().size() <= 0) {
                ToastUtils.showToast(KxjcActivity.this, "危险货物现场检查表至少填写一行整改结果");
                bol = false;
            }
        } else if (check1.equals("1") && !check2.equals("1") && check3.equals("1") && !check4.equals("1")) {//13
            if ("".equals(ctDangerPolluteScene.getCtDangerPolluteScene().getShipNameCn()) && "".equals(ctDangerPolluteScene.getCtDangerPolluteScene().getShipNameEn())) {
                ToastUtils.showToast(KxjcActivity.this, "请将危险货物现场检查表船名填写完整");
                bol = false;
            } else if (ctDangerPolluteScene.getCtDangerPolluteSceneDetail().size() <= 0) {
                ToastUtils.showToast(KxjcActivity.this, "危险货物现场检查表至少填写一行整改结果");
                bol = false;
            }
        } else if (check1.equals("1") && !check2.equals("1") && !check3.equals("1") && check4.equals("1")) {//14
            if ("".equals(ctDangerPolluteScene.getCtDangerPolluteScene().getShipNameCn()) && "".equals(ctDangerPolluteScene.getCtDangerPolluteScene().getShipNameEn())) {
                ToastUtils.showToast(KxjcActivity.this, "请将危险货物现场检查表船名填写完整");
                bol = false;
            } else if (ctDangerPolluteScene.getCtDangerPolluteSceneDetail().size() <= 0) {
                ToastUtils.showToast(KxjcActivity.this, "危险货物现场检查表至少填写一行整改结果");
                bol = false;
            }
        }
//                    else if (check1.equals("1") && !check2.equals("1") && !check3.equals("1") && check4.equals("1")) {//23
//                    } else if (!check1.equals("1") && check2.equals("1") && !check3.equals("1") && check4.equals("1")) {//24
//                    } else if (!check1.equals("1") && !check2.equals("1") && check3.equals("1") && check4.equals("1")) {//34
//                    }
        else if (check1.equals("1") && !check2.equals("1") && !check3.equals("1") && !check4.equals("1")) {//1
            if ("".equals(ctDangerPolluteScene.getCtDangerPolluteScene().getShipNameCn()) && "".equals(ctDangerPolluteScene.getCtDangerPolluteScene().getShipNameEn())) {
                ToastUtils.showToast(KxjcActivity.this, "请将危险货物现场检查表船名填写完整");
                bol = false;
            } else if (ctDangerPolluteScene.getCtDangerPolluteSceneDetail().size() <= 0) {
                ToastUtils.showToast(KxjcActivity.this, "危险货物现场检查表至少填写一行整改结果");
                bol = false;
            }
        }
//                    else if (!check1.equals("1") && check2.equals("1") && !check3.equals("1") && !check4.equals("1")) {//2
//                    } else if (!check1.equals("1") && !check2.equals("1") && check3.equals("1") && !check4.equals("1")) {//3
//                    } else if (!check1.equals("1") && !check2.equals("1") && !check3.equals("1") && check4.equals("1")) {//4
//                    }

        if (bol) {
            if (disposalDecisionDialog == null) {
                disposalDecisionDialog = new DisposalDecisionDialog(KxjcActivity.this);
                disposalDecisionDialog.showDialog();
                disposalDecisionDialog.setOnData(new DisposalDecisionDialog.OnGetData() {
                    @Override
                    public void onDataCallBack(String disposalDecision) {
                        KxjcActivity.this.disposalDecision = disposalDecision;
                        presenter.subData();
                    }
                });
            } else {
                disposalDecisionDialog.showDialog();
            }
        }

    }


    @Override
    public KxjcData getData() {
        if (check1.equals("1") && check2.equals("1") && check3.equals("1") && check4.equals("1")) {//1234
            ctDangerPolluteScene.setTaskId(taskId);
            ctDangerPolluteOut.setTaskId(taskId);
            ctGoodSecneOut.setTaskId(taskId);
            ctCaseWeightInspect.setTaskId(taskId);

            data.setCtDangerPolluteScene(ctDangerPolluteScene);
            data.setCtDangerPolluteOut(ctDangerPolluteOut);
            data.setCtGoodSecneOut(ctGoodSecneOut);
            data.setCtCaseWeightInspect(ctCaseWeightInspect);
        } else if (check1.equals("1") && check2.equals("1") && check3.equals("1") && !check4.equals("1")) {//123
            ctDangerPolluteScene.setTaskId(taskId);
            ctDangerPolluteOut.setTaskId(taskId);
            ctGoodSecneOut.setTaskId(taskId);
//            ctCaseWeightInspect.setTaskId(taskId);

            data.setCtDangerPolluteScene(ctDangerPolluteScene);
            data.setCtDangerPolluteOut(ctDangerPolluteOut);
            data.setCtGoodSecneOut(ctGoodSecneOut);
//            data.setCtCaseWeightInspect(new ContainerWeightInspectData());
        } else if (check1.equals("1") && check2.equals("1") && !check3.equals("1") && check4.equals("1")) {//124
            ctDangerPolluteScene.setTaskId(taskId);
            ctDangerPolluteOut.setTaskId(taskId);
//            ctGoodSecneOut.setTaskId(taskId);
            ctCaseWeightInspect.setTaskId(taskId);

            data.setCtDangerPolluteScene(ctDangerPolluteScene);
            data.setCtDangerPolluteOut(ctDangerPolluteOut);
//            data.setCtGoodSecneOut(new OrdinaryGoodsKaiXiangData());
            data.setCtCaseWeightInspect(ctCaseWeightInspect);
        } else if (check1.equals("1") && !check2.equals("1") && check3.equals("1") && check4.equals("1")) {//134
            ctDangerPolluteScene.setTaskId(taskId);
//            ctDangerPolluteOut.setTaskId(taskId);
            ctGoodSecneOut.setTaskId(taskId);
            ctCaseWeightInspect.setTaskId(taskId);

            data.setCtDangerPolluteScene(ctDangerPolluteScene);
//            data.setCtDangerPolluteOut(new DangerousGoodsKaiXiangData());
            data.setCtGoodSecneOut(ctGoodSecneOut);
            data.setCtCaseWeightInspect(ctCaseWeightInspect);
        } else if (!check1.equals("1") && check2.equals("1") && check3.equals("1") && check4.equals("1")) {//234
//            ctDangerPolluteScene.setTaskId(taskId);
            ctDangerPolluteOut.setTaskId(taskId);
            ctGoodSecneOut.setTaskId(taskId);
            ctCaseWeightInspect.setTaskId(taskId);

//            data.setCtDangerPolluteScene(new DangerousGoodsXianChangData());
            data.setCtDangerPolluteOut(ctDangerPolluteOut);
            data.setCtGoodSecneOut(ctGoodSecneOut);
            data.setCtCaseWeightInspect(ctCaseWeightInspect);
        } else if (check1.equals("1") && check2.equals("1") && !check3.equals("1") && !check4.equals("1")) {//12
            ctDangerPolluteScene.setTaskId(taskId);
            ctDangerPolluteOut.setTaskId(taskId);
//            ctGoodSecneOut.setTaskId(taskId);
//            ctCaseWeightInspect.setTaskId(taskId);

            data.setCtDangerPolluteScene(ctDangerPolluteScene);
            data.setCtDangerPolluteOut(ctDangerPolluteOut);
//            data.setCtGoodSecneOut(new OrdinaryGoodsKaiXiangData());
//            data.setCtCaseWeightInspect(new ContainerWeightInspectData());
        } else if (check1.equals("1") && !check2.equals("1") && check3.equals("1") && !check4.equals("1")) {//13
            ctDangerPolluteScene.setTaskId(taskId);
//            ctDangerPolluteOut.setTaskId(taskId);
            ctGoodSecneOut.setTaskId(taskId);
//            ctCaseWeightInspect.setTaskId(taskId);

            data.setCtDangerPolluteScene(ctDangerPolluteScene);
//            data.setCtDangerPolluteOut(new DangerousGoodsKaiXiangData());
            data.setCtGoodSecneOut(ctGoodSecneOut);
//            data.setCtCaseWeightInspect(new ContainerWeightInspectData());
        } else if (check1.equals("1") && !check2.equals("1") && !check3.equals("1") && check4.equals("1")) {//14
            ctDangerPolluteScene.setTaskId(taskId);
//            ctDangerPolluteOut.setTaskId(taskId);
//            ctGoodSecneOut.setTaskId(taskId);
            ctCaseWeightInspect.setTaskId(taskId);

            data.setCtDangerPolluteScene(ctDangerPolluteScene);
//            data.setCtDangerPolluteOut(new DangerousGoodsKaiXiangData());
//            data.setCtGoodSecneOut(new OrdinaryGoodsKaiXiangData());
            data.setCtCaseWeightInspect(ctCaseWeightInspect);
        } else if (check1.equals("1") && !check2.equals("1") && !check3.equals("1") && check4.equals("1")) {//23
//            ctDangerPolluteScene.setTaskId(taskId);
            ctDangerPolluteOut.setTaskId(taskId);
            ctGoodSecneOut.setTaskId(taskId);
//            ctCaseWeightInspect.setTaskId(taskId);

//            data.setCtDangerPolluteScene(new DangerousGoodsXianChangData());
            data.setCtDangerPolluteOut(ctDangerPolluteOut);
            data.setCtGoodSecneOut(ctGoodSecneOut);
//            data.setCtCaseWeightInspect(new ContainerWeightInspectData());
        } else if (!check1.equals("1") && check2.equals("1") && !check3.equals("1") && check4.equals("1")) {//24
//            ctDangerPolluteScene.setTaskId(taskId);
            ctDangerPolluteOut.setTaskId(taskId);
//            ctGoodSecneOut.setTaskId(taskId);
            ctCaseWeightInspect.setTaskId(taskId);

//            data.setCtDangerPolluteScene(new DangerousGoodsXianChangData());
            data.setCtDangerPolluteOut(ctDangerPolluteOut);
//            data.setCtGoodSecneOut(new OrdinaryGoodsKaiXiangData());
            data.setCtCaseWeightInspect(ctCaseWeightInspect);
        } else if (!check1.equals("1") && !check2.equals("1") && check3.equals("1") && check4.equals("1")) {//34
//            ctDangerPolluteScene.setTaskId(taskId);
//            ctDangerPolluteOut.setTaskId(taskId);
            ctGoodSecneOut.setTaskId(taskId);
            ctCaseWeightInspect.setTaskId(taskId);

//            data.setCtDangerPolluteScene(new DangerousGoodsXianChangData());
//            data.setCtDangerPolluteOut(new DangerousGoodsKaiXiangData());
            data.setCtGoodSecneOut(ctGoodSecneOut);
            data.setCtCaseWeightInspect(ctCaseWeightInspect);
        } else if (check1.equals("1") && !check2.equals("1") && !check3.equals("1") && !check4.equals("1")) {//1
            ctDangerPolluteScene.setTaskId(taskId);
//            ctDangerPolluteOut.setTaskId(taskId);
//            ctGoodSecneOut.setTaskId(taskId);
//            ctCaseWeightInspect.setTaskId(taskId);

            data.setCtDangerPolluteScene(ctDangerPolluteScene);
//            data.setCtDangerPolluteOut(new DangerousGoodsKaiXiangData());
//            data.setCtGoodSecneOut(new OrdinaryGoodsKaiXiangData());
//            data.setCtCaseWeightInspect(new ContainerWeightInspectData());
        } else if (!check1.equals("1") && check2.equals("1") && !check3.equals("1") && !check4.equals("1")) {//2
//            ctDangerPolluteScene.setTaskId(taskId);
            ctDangerPolluteOut.setTaskId(taskId);
//            ctGoodSecneOut.setTaskId(taskId);
//            ctCaseWeightInspect.setTaskId(taskId);

//            data.setCtDangerPolluteScene(new DangerousGoodsXianChangData());
            data.setCtDangerPolluteOut(ctDangerPolluteOut);
//            data.setCtGoodSecneOut(new OrdinaryGoodsKaiXiangData());
//            data.setCtCaseWeightInspect(new ContainerWeightInspectData());
        } else if (!check1.equals("1") && !check2.equals("1") && check3.equals("1") && !check4.equals("1")) {//3
//            ctDangerPolluteScene.setTaskId(taskId);
//            ctDangerPolluteOut.setTaskId(taskId);
            ctGoodSecneOut.setTaskId(taskId);
//            ctCaseWeightInspect.setTaskId(taskId);

//            data.setCtDangerPolluteScene(new DangerousGoodsXianChangData());
//            data.setCtDangerPolluteOut(new DangerousGoodsKaiXiangData());
            data.setCtGoodSecneOut(ctGoodSecneOut);
//            data.setCtCaseWeightInspect(new ContainerWeightInspectData());
        } else if (!check1.equals("1") && !check2.equals("1") && !check3.equals("1") && check4.equals("1")) {//4
//            ctDangerPolluteScene.setTaskId(taskId);
//            ctDangerPolluteOut.setTaskId(taskId);
//            ctGoodSecneOut.setTaskId(taskId);
            ctCaseWeightInspect.setTaskId(taskId);

//            data.setCtDangerPolluteScene(new DangerousGoodsXianChangData());
//            data.setCtDangerPolluteOut(new DangerousGoodsKaiXiangData());
//            data.setCtGoodSecneOut(new OrdinaryGoodsKaiXiangData());
            data.setCtCaseWeightInspect(ctCaseWeightInspect);
        }
        data.setTaskId(taskId);
        data.setDisposalDecision(disposalDecision);
        return data;
    }

    @Override
    public Dialog getDialog() {
        return dialog;
    }

    @Override
    public void OnSuccess(String s, FourTableSubData data) {
        dialog.dismiss();
        disposalDecisionDialog.dismiss();
        Intent intent = new Intent(this, FinishTaskActivity.class);
        intent.putExtra("fourData", data);
        if (null != shipNameCn && !"".equals(shipNameCn)) {
            intent.putExtra("shipName", shipNameCn);
        } else if (null != shipNameEn && !"".equals(shipNameEn)) {
            intent.putExtra("shipName", shipNameEn);
        } else {
            intent.putExtra("shipName", "");
        }
        startActivity(intent);
    }

    @Override
    public void OnFaile(String msg) {
        dialog.dismiss();
        ToastUtils.showToast(this, msg);
    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(this, msg, msg);
    }
}
