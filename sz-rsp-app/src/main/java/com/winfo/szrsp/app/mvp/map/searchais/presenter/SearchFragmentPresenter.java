//package com.winfo.szrsp.app.mvp.map.searchais.presenter;
//
//import android.content.Context;
//
//import com.winfo.dnc.sdk.AisData;
//import com.winfo.szrsp.app.application.SzRspApplication;
//import com.winfo.szrsp.app.entity.HistoryAis;
//import com.winfo.szrsp.app.entity.LocalAisData;
//import com.winfo.szrsp.app.mvp.map.searchais.model.ISearchAisModel;
//import com.winfo.szrsp.app.mvp.map.searchais.model.SearchAisModel;
//import com.winfo.szrsp.app.mvp.map.searchais.view.ISearchView;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by ChengQi on 2017/10/18.
// *
// */
//
//public class SearchFragmentPresenter {
//
//    private ISearchAisModel iSearchAisModel;
//    private ISearchView iSearchView;
//    private Context mContext;
//    private List<LocalAisData> _localAisDatas;//比较显示tip的提示的数getLocalAisdata据集合
//    /**
//     * 根据用户输入的关键字 判断提示给用户显示的数据的结集合
//     */
//    public List<LocalAisData> hintLocalAisDatas;//把判断好的数据集合给view
//
//    public SearchFragmentPresenter(ISearchView iSearchView, Context context){
//        this.iSearchAisModel=new SearchAisModel();
//        this.iSearchView=iSearchView;
//        this._localAisDatas = new ArrayList<>();
//        this.hintLocalAisDatas = new ArrayList<>();
//        mContext=context;
//
//    }
//
//    /**
//     * 查询本地的 aisdata
//     */
//    public void getLocalAisData() {
//
//        iSearchAisModel.loadLocalAisData(mContext, iSearchView.getDialog(), new SearchAisModel.OnLoadLocalAisDataListener() {
//            @Override
//            public void onSuccess(List<LocalAisData> localAisData) {
//                _localAisDatas = localAisData;
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                iSearchView.showMsg(msg);
//            }
//        });
//
//    }
//
//
//    /****
//     * 获取提示数据
//     */
//    public void getHintAisData() {
//
//        hintLocalAisDatas.clear();
//        if (!iSearchView.getEditString().equals("")) {
//            LocalAisData oneLocaAisData = new LocalAisData();
//            oneLocaAisData.setCm(iSearchView.getEditString());
//            hintLocalAisDatas.add(0, oneLocaAisData);
//            for (int i = 0; i < _localAisDatas.size(); i++) {
//                if (_localAisDatas.get(i).getCm().startsWith(iSearchView.getEditString())) {
//                    hintLocalAisDatas.add(_localAisDatas.get(i));
//                }
//            }
//            iSearchView.setData(hintLocalAisDatas);
//        }else {
//            getLocalHistory();
//        }
//
//    }
//
//    /**
//     * 根据用户关键字模糊查询
//     */
//    public void loadAisDataByKey(String key){
//        iSearchAisModel.loadAisDataByKey(iSearchView.getLoadingDialog(), key, new SearchAisModel.OnLoadAisDataByKeyListener() {
//            @Override
//            public void OnSuccess(List<AisData> datas) {
//                if (datas.size() == 1){
//                    iSearchView.setAisDataByMmssi(datas.get(0));
//                }
//                if (datas.size() > 1){
//                    iSearchView.setResultData(datas);
//                }
//            }
//
//            @Override
//            public void OnFaile(String msg) {
//                iSearchView.showMsg(msg);
//            }
//
//            @Override
//            public void OnNoData(String msg) {
//                iSearchView.showMsg(msg);
//            }
//        });
//    }
//
//
//    /**
//     * 加载本地的历史记录数据
//     */
//    public void getLocalHistory() {
//        iSearchAisModel.getLocalHistoryData(iSearchView.getLoadingDialog(), new SearchAisModel.OnLoadLocalHistoryDataListenner() {
//
//            @Override
//            public void onSuccess(List<HistoryAis> searchShips) {
//                iSearchView.setHistoryData(searchShips);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                iSearchView.showMsg(msg);
//            }
//        });
//    }
//
//
//    /**
//     * 保存历史记录
//     *
//     * @param historyAis
//     */
//    public void saveHistoryAis(final HistoryAis historyAis) {
//        iSearchAisModel.loadLocalHistoryAis(iSearchView.getLoadingDialog(), historyAis.getMmsi(), new SearchAisModel.OnLoadLocalHistoryDataListenner() {
//
//            @Override
//            public void onSuccess(List<HistoryAis> searchShips) {
//                try {
//                    if (searchShips.size() > 0) {
//                        if (searchShips.get(0).getMmsi().equals(historyAis.getMmsi())) {
//                            HistoryAis ais = searchShips.get(0);
//                            ais.setZwcm(historyAis.getZwcm());
//                            ais.setMmsi(historyAis.getMmsi());
//                            SzRspApplication.db.saveOrUpdate(ais);
//                        }
//                    } else {
//                        SzRspApplication.db.save(historyAis);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(String msg) {
//
//            }
//        });
//    }
//
//
//}
