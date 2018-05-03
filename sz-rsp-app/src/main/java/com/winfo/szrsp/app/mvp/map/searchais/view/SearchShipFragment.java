package com.winfo.szrsp.app.mvp.map.searchais.view;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;
import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.activity.MainActivity;
import com.winfo.szrsp.app.adapter.SearchNavigableElementsAdapter;
import com.winfo.szrsp.app.adapter.searchship.SearchHistoryShipAdapter;
import com.winfo.szrsp.app.adapter.searchship.SearchHistoryThsyAdapter;
import com.winfo.szrsp.app.adapter.searchship.SearchShipAdapter;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.entity.HistoryAis;
import com.winfo.szrsp.app.entity.HistoryThsy;
import com.winfo.szrsp.app.mvp.BaseMvpFragment;
import com.winfo.szrsp.app.mvp.map.searchais.presenter.SearchShipPresenter;
import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.sdk.entity.shipdata.ShipSearch;
import com.winfo.szrsp.app.sdk.entity.thys.NavigableElementsData;
import com.winfo.szrsp.app.utils.DeviceUtils;
import com.winfo.szrsp.app.utils.DialogUtils;
import com.winfo.szrsp.app.utils.DimensUtils;
import com.winfo.szrsp.app.utils.EditTextUtils;
import com.winfo.szrsp.app.utils.LoginUtils;
import com.winfo.szrsp.app.utils.ToastUtils;
import com.winfo.szrsp.app.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp.map.searchais.view
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.map.searchais.view.SearchShipFragment.java
 * Date: 2017/12/15 21:59
 * Description:
 */

public class SearchShipFragment extends BaseMvpFragment<ISearchShipView, SearchShipPresenter> implements ISearchShipView, View.OnClickListener {

    private Dialog loadingDialog;
    private ImageButton ib_back;
    private SearchShipPresenter searchShipPresenter;
    private RecyclerView rvSearch;
    private EditText etSearchShip;
    private SearchHistoryShipAdapter searchHistoryShipAdapter;
    private SearchHistoryThsyAdapter searchHistoryThsyAdapter;
    private LinearLayout llHistory;
    private RecyclerView rvHistory;
    private TextView tvClearHistory;
    private PopupWindow popupWindow;//选择搜索窗体
    private ImageButton img_switch;
    private View view;
    int magin;
    int width;
    private int type = 1;
    private List<HistoryThsy> historyThsies;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_search_ship, container, false);
        initView(view);
        initData();
        initEvent();
        return view;
    }

    @Override
    protected SearchShipPresenter createPresenter() {
        searchShipPresenter = new SearchShipPresenter();
        return searchShipPresenter;
    }

    private void initData() {
        if (type == 1){
            etSearchShip.setHint("输入船名或者MMSI");
            searchShipPresenter.getHistory();
        }else if(type == 2){
            etSearchShip.setHint("输入通航要素名称");
            searchShipPresenter.getHistoryThsy();
        }
    }

    private void initEvent() {
        tvClearHistory.setOnClickListener(this);
        ib_back.setOnClickListener(this);
        img_switch.setOnClickListener(this);
        etSearchShip.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    if (type == 1){
                        if (!textView.getText().toString().trim().equals("")) {
                            Pattern pattern = Pattern.compile("^[0-9]*[1-9][0-9]*$");
                            Matcher matcher = pattern.matcher(etSearchShip.getText().toString().trim());
                            if (matcher.matches() && etSearchShip.getText().toString().trim().length() == 9) {
                                searchShipPresenter.searShipByMmsi(etSearchShip.getText().toString().trim());
                            } else {
                                searchShipPresenter.getShipsByName(etSearchShip.getText().toString().trim());
                            }
                        } else {
                            ShowMsg("请输入船名或者mmsi");
                        }
                    }else if (type == 2){
                        searchShipPresenter.serachNavigableElementsDataByNameCn(etSearchShip.getText().toString().trim());
                    }
                }
                return true;
            }
        });
    }

    private void initView(View view) {
        loadingDialog = DialogUtils.createLoadingDialog(getActivity(), "正在搜索...");
        rvSearch = view.findViewById(R.id.rv_search);
        rvHistory = view.findViewById(R.id.rv_history);
        rvSearch.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvHistory.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvHistory.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL, R.drawable.item_divider_bg));
        rvSearch.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL, R.drawable.item_divider_bg));
        ib_back = view.findViewById(R.id.ib_back);
        tvClearHistory = view.findViewById(R.id.tv_clear_history);
        llHistory = view.findViewById(R.id.ll_history);
        etSearchShip = view.findViewById(R.id.et_search_ship);
        EditTextUtils.setEditTextInhibitInputSpace(etSearchShip);
        EditTextUtils.setEditTextInhibitInputSpeChat(etSearchShip);
        img_switch = view.findViewById(R.id.switch_btn);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        MainActivity activity = (MainActivity) getActivity();
        activity.openDrawLayouy();
    }


    @Override
    public void ShowMsg(String msg) {
        ToastUtils.showToast(getActivity(), msg);
    }

    @Override
    public Dialog getLoadingDialog() {
        return loadingDialog;
    }

    @Override
    public void setSearchShips(final List<ShipSearch> shipSearches) {
        llHistory.setVisibility(View.GONE);
        rvSearch.setVisibility(View.VISIBLE);
        SearchShipAdapter searchShipAdapter = new SearchShipAdapter(R.layout.fragment_search_item, shipSearches);
        rvSearch.setAdapter(searchShipAdapter);
        searchShipAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                searchShipPresenter.searchShipBySearchShip(shipSearches.get(position));
            }
        });
    }

    @Override
    public void setSearchShipByMMSI(Ais ais) {
        if (getActivity() instanceof SearchOnItemClickListener) {
            ((SearchOnItemClickListener) getActivity()).locationSearchShip(ais);
        }
    }

    @Override
    public void setHistoryAis(final List<HistoryAis> historyAis) {
        llHistory.setVisibility(View.VISIBLE);
        rvSearch.setVisibility(View.GONE);
        if (searchHistoryShipAdapter == null) {
            searchHistoryShipAdapter = new SearchHistoryShipAdapter(R.layout.activity_history_ben, historyAis);
        } else {
            searchHistoryShipAdapter.setNewData(historyAis);
            searchHistoryShipAdapter.notifyDataSetChanged();
        }
        rvHistory.setAdapter(searchHistoryShipAdapter);
        searchHistoryShipAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                searchShipPresenter.searShipByMmsi(historyAis.get(position).getMmsi());
            }
        });
    }

    @Override
    public void setHistoryThys(final List<HistoryThsy> historyAis) {
        this.historyThsies = historyAis;
        llHistory.setVisibility(View.VISIBLE);
        rvSearch.setVisibility(View.GONE);
        if (searchHistoryThsyAdapter == null) {
            searchHistoryThsyAdapter = new SearchHistoryThsyAdapter(R.layout.activity_history_ben, historyAis);
        } else {
            searchHistoryThsyAdapter.setNewData(historyAis);
            searchHistoryThsyAdapter.notifyDataSetChanged();
        }
        rvHistory.setAdapter(searchHistoryThsyAdapter);
        searchHistoryThsyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                searchShipPresenter.serachNavigableElementsDataByNameCn(historyAis.get(position).getName());
            }
        });
    }

    @Override
    public void setNavigableElementsData(final List<NavigableElementsData> navigableElementsData) {
        if (navigableElementsData.size() == 1){
            locationThys(navigableElementsData.get(0));
        }else {
            llHistory.setVisibility(View.GONE);
            rvSearch.setVisibility(View.VISIBLE);
            SearchNavigableElementsAdapter elementsAdapter = new SearchNavigableElementsAdapter(R.layout.fragment_search_item,navigableElementsData);
            rvSearch.setAdapter(elementsAdapter);
            elementsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    locationThys(navigableElementsData.get(position));
                    if (historyThsies.size()>0){
                        HistoryThsy historyThsy = new HistoryThsy();
                        historyThsy.setName(navigableElementsData.get(position).getNameCn());
                        historyThsy.setTypename(navigableElementsData.get(position).getTypeName());
                        historyThsy.setTel(navigableElementsData.get(position).getLinkmanTel());
                        try {
                            List<HistoryThsy> list = SzRspApplication.db.findAll(Selector.from(HistoryThsy.class).where(WhereBuilder.b("name", "=", historyThsy.getName())));
                            if (list.size() == 0){
                                SzRspApplication.db.saveOrUpdate(historyThsy);
                            }
                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                    }else {
                        HistoryThsy historyThsy = new HistoryThsy();
                        historyThsy.setName(navigableElementsData.get(position).getNameCn());
                        historyThsy.setTypename(navigableElementsData.get(position).getTypeName());
                        historyThsy.setTel(navigableElementsData.get(position).getLinkmanTel());
                        try {
                            SzRspApplication.db.saveOrUpdate(historyThsy);
                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    @Override
    public void loginExpired(String msg) {
        LoginUtils.loginOutShowDialog(getActivity(),msg,msg);
    }

    private void locationThys(NavigableElementsData navigableElementsData){
        String str = navigableElementsData.getCenterLatLon();
        String[] strings = str.split(",");
        if (strings[0].contains("°") || str.equals("")){
            ToastUtils.showToast(getActivity(),"数据异常。");
        }else {
            if (getActivity() instanceof SearchThysOnItemClickListener) {
                ((SearchThysOnItemClickListener) getActivity()).locationSearchThys(navigableElementsData);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back:
                if (getActivity() instanceof SearchShipFragment.BackSearchShipFragment) {
                    ((SearchShipFragment.BackSearchShipFragment) getActivity()).popBackStack();
                }
                break;
            case R.id.tv_clear_history:
                DialogUtils.showDialog(getActivity(), "温馨提示", "确定要清除历史记录吗？", "确定", "取消", new DialogUtils.DialogOnClickListenner() {
                    @Override
                    public void btnConfirmClick(Dialog dialog) {
                        if (type == 1){
                            try {
                                SzRspApplication.db.deleteAll(HistoryAis.class);
                            } catch (DbException e) {
                                e.printStackTrace();
                            }
                            searchShipPresenter.getHistory();
                            dialog.dismiss();
                        }else if (type == 2){
                            try {
                                SzRspApplication.db.deleteAll(HistoryThsy.class);
                            } catch (DbException e) {
                                e.printStackTrace();
                            }
                            searchShipPresenter.getHistoryThsy();
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void btnCancelClick(Dialog dialog) {
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.switch_btn:
                alertPop();
                break;
        }
    }

    /**
     * 弹出选择搜索类型窗体
     */
    private void alertPop() {
        view = View.inflate(getActivity(), R.layout.pw_search, null);
        final ImageButton img_cb =  view.findViewById(R.id.img_cb);
        final ImageButton img_thys = view.findViewById(R.id.img_thys);
        final LinearLayout layout_cb = view.findViewById(R.id.lay_cb);
        final LinearLayout layout_thys =  view.findViewById(R.id.lay_thys);
        // 获取手机屏幕的宽度 单位像素px
        width = DeviceUtils.getScreenSize(getActivity())[0]; // 屏幕宽度（像素）
        // 窗体距离屏幕最右边或者最左边10dp所对应的像素（把10dp转换成px）
        magin = DimensUtils.dp2px(getActivity(), 10);
        // 初始化popupwindow
        popupWindow = new PopupWindow(view, width - (width - DimensUtils.dp2px(getActivity(), 180)), ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setFocusable(true);// 设置焦点
        popupWindow.setAnimationStyle(R.style.popwin_anim_style); // 动画效果
        popupWindow.setOutsideTouchable(true);// 关闭弹出窗体
        popupWindow.showAsDropDown(img_switch, magin * 8 - popupWindow.getWidth(), DimensUtils.dp2px(getActivity(), -4));

        if (type == 1) {
            img_cb.setVisibility(View.VISIBLE);
            img_thys.setVisibility(View.INVISIBLE);
        } else if (type == 2) {
            img_cb.setVisibility(View.INVISIBLE);
            img_thys.setVisibility(View.VISIBLE);
        }

        layout_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchShipPresenter.getHistory();
                type = 1;
                img_thys.setVisibility(View.VISIBLE);
                img_cb.setVisibility(View.INVISIBLE);
                popupWindow.dismiss();
                etSearchShip.setHint("输入船名或者MMSI");
            }
        });

        layout_thys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchShipPresenter.getHistoryThsy();
                type = 2;
                img_thys.setVisibility(View.VISIBLE);
                img_cb.setVisibility(View.INVISIBLE);
                popupWindow.dismiss();
                etSearchShip.setHint("输入通航要素名称");
            }
        });
    }

    public interface BackSearchShipFragment {
        /**
         * 关闭当前fragment弹出栈
         */
        void popBackStack();
    }

    public interface SearchOnItemClickListener {
        void locationSearchShip(Ais aisData);
    }

    public interface SearchThysOnItemClickListener{
        void locationSearchThys(NavigableElementsData navigableElementsData);
    }
}
