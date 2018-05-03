package com.winfo.szrsp.app.mvp.table.findalltable.presenter;

import com.winfo.szrsp.app.mvp.table.findalltable.model.FindAllTableModel;
import com.winfo.szrsp.app.mvp.table.findalltable.model.IFindAllTableModel;
import com.winfo.szrsp.app.mvp.table.findalltable.view.IAllTableActivity;
import com.winfo.szrsp.app.sdk.entity.table.ListCBWFXCData;
import com.winfo.szrsp.app.sdk.entity.table.ListContainerWeightInspectData;
import com.winfo.szrsp.app.sdk.entity.table.ListCruiseWorkData;
import com.winfo.szrsp.app.sdk.entity.table.ListDXSHCData;
import com.winfo.szrsp.app.sdk.entity.table.ListDangerousGoodsKaiXiangData;
import com.winfo.szrsp.app.sdk.entity.table.ListDangerousGoodsXianChangData;
import com.winfo.szrsp.app.sdk.entity.table.ListElectronicCruiseException;
import com.winfo.szrsp.app.sdk.entity.table.ListGoodSecneOutRestServiceData;
import com.winfo.szrsp.app.sdk.entity.table.ListPSCData;
import com.winfo.szrsp.app.sdk.entity.table.ListWatersPatrolData;
import com.winfo.szrsp.app.sdk.entity.table.ListcbjdjgData;
import com.winfo.szrsp.app.sdk.entity.table.ListcbxcData;
import com.winfo.szrsp.app.sdk.entity.table.xhtj.ListXHTJData;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.findalltable.presenter
 * @Filename: FindAllTablePresenter
 * @Author: lsj
 * @Date: 2017/12/7  10:08
 * @Description:
 * @Version:
 */
public class FindAllTablePresenter {
    private IAllTableActivity activity;
    private IFindAllTableModel model;

    public FindAllTablePresenter(IAllTableActivity activity) {
        this.activity = activity;
        this.model = new FindAllTableModel();
    }

    public void loadJGBGData(String pageNum, String pageSize, String isUser, boolean bol) {
        model.findJDBGData(activity.getDailog(), pageNum, pageSize, isUser, bol, new FindAllTableModel.OnLoadFindAllJDBGListener() {
            @Override
            public void OnSuccess(ListcbjdjgData data) {
                activity.setJDBGData(data);
            }

            @Override
            public void OnFaile(String msg) {
                activity.showOnFaile6(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }

    public void loadCBXCData(String pageNum, String pageSize, String isUser, boolean bol) {
        model.findCBXCData(activity.getDailog(), pageNum, pageSize, isUser, bol, new FindAllTableModel.OnLoadFindAllCBXCListener() {
            @Override
            public void OnSuccess(ListcbxcData data) {
                activity.setCBXCData(data);
            }

            @Override
            public void OnFaile(String msg) {
                activity.showOnFaile2(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }

    public void loadDXSHCData(String pageNum, String pageSize, String isUser, boolean bol) {
        model.findDXSHCData(activity.getDailog(), pageNum, pageSize, isUser, bol, new FindAllTableModel.OnLoadFindAllDXSHCListener() {
            @Override
            public void OnSuccess(ListDXSHCData data) {
                activity.setDXSHCData(data);
            }

            @Override
            public void OnFaile(String msg) {
                activity.showOnFaile3(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }

    public void loadWatersPatrolData(String pageNume, String pageSize, String isUser, boolean bol) {
        model.findWatersPatrolData(activity.getDailog(), pageNume, pageSize, isUser, bol, new FindAllTableModel.OnLoadFindAllWatersPatrolListener() {
            @Override
            public void onSuccess(ListWatersPatrolData data) {
                activity.setWatersPatrolData(data);
            }

            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }

            @Override
            public void onFailure(String msg) {
                activity.showOnFaile4(msg);
            }
        });
    }

    public void loadPSCData(String pageNume, String pageSize, String isUser, boolean bol) {
        model.findPSCData(activity.getDailog(), pageNume, pageSize, isUser, bol, new FindAllTableModel.OnLoadFindAllPSCDataListener() {
            @Override
            public void onSuccess(ListPSCData data) {
                activity.setPSCData(data);
            }

            @Override
            public void onFialure(String msg) {
                activity.showOnFaile7(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }

    public void loadXHTJData(String pageNume, String pageSize, String isUser, boolean bol) {
        model.findXhtjDatya(activity.getDailog(), pageNume, pageSize, isUser, bol, new FindAllTableModel.OnLoadFindAllXHTJDataListener() {
            @Override
            public void onSuccess(ListXHTJData data) {
                activity.setXHTjData(data);
            }

            @Override
            public void onFaile(String msg) {
                activity.showOnFaile8(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }

    public void loadCtElectronicCruiseException(String pageNume, String pageSize, String isUser, boolean bol) {
        isUser = "0";
        model.findElectronicCruiseException(activity.getDailog(), pageNume, pageSize, isUser, bol, new FindAllTableModel.OnLoadFindAllElectronicCruiseExceptionListener() {
            @Override
            public void onSuccess(ListElectronicCruiseException data) {
                activity.setElectronicCruiseException(data);
            }

            @Override
            public void onFailure(String msg) {
                activity.showOnFaile9(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }

    public void loadCBWFXCData(String pageNume, String pageSize, String isUser, boolean bol) {
        isUser = "0";
        model.findCBWFData(activity.getDailog(), pageNume, pageSize, isUser, bol, new FindAllTableModel.OnLoadFindAllCBWFXCDataListener() {
            @Override
            public void onSuccess(ListCBWFXCData data) {
                activity.setCBWFXCData(data);
            }

            @Override
            public void onFailure(String msg) {
                activity.showOnFaile10(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }

    public void loadGoodSecneOutRestServiceData(String pageNum, String pageSize, String isUser, boolean bol) {
        model.findGoodSecneOutRestServiceData(activity.getDailog(), pageNum, pageSize, isUser, bol, new FindAllTableModel.OnLoadFindAllGoodSecneOutRestServiceListener() {
            @Override
            public void onSuccess(ListGoodSecneOutRestServiceData data) {
                activity.setGoodSecneOutRestServiceData(data);
            }

            @Override
            public void onFailure(String msg) {
                activity.showOnfaile13(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }

    public void loadDangerousGoodsXianChangData(String pageNum, String pageSize, String isUser, boolean bol) {
        model.findDangerousGoodsXianChangData(activity.getDailog(), pageNum, pageSize, isUser, bol, new FindAllTableModel.OnLoadFindAllDangerousGoodsXianChangListener() {
            @Override
            public void onSuccess(ListDangerousGoodsXianChangData data) {
                activity.setDangerousGoodsXianChangData(data);
            }

            @Override
            public void onFailure(String msg) {
                activity.showOnFaile11(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }

    public void loadContainerWeightInspectData(String pageNum, String pageSize, String isUser, boolean bol) {
        model.findContainerWeightInspectData(activity.getDailog(), pageNum, pageSize, isUser, bol, new FindAllTableModel.OnFindAllContainerWeightInspectListener() {

            @Override
            public void onSuccess(ListContainerWeightInspectData listContainerWeightInspectData) {
                activity.setContainerWeightInspectData(listContainerWeightInspectData);
            }

            @Override
            public void onFailure(String msg) {
                activity.showOnfaile14(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }


    public void loadDangerousGoodsKaiXiangData(String pageNum, String pageSize, String isUser, boolean bol) {
        model.findDangerousGoodsKaiXiangData(activity.getDailog(), pageNum, pageSize, isUser, bol, new FindAllTableModel.OnLoadFindAllDangerousGoodsKaiXiangListener() {
            @Override
            public void onSuccess(String msg, ListDangerousGoodsKaiXiangData data) {
                activity.setDangerousGoodsKaiXiangData(data);
            }

            @Override
            public void onFailure(String msg) {
                activity.showOnFaile12(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }

    public void loadCruiseWorkData(String pageNum, String pageSize, String isUser, boolean bol) {
        model.findCruiseWorkData(activity.getDailog(), pageNum, pageSize, isUser, bol, new FindAllTableModel.OnFindAllCruiseWorkListener() {
            @Override
            public void onSuccess(ListCruiseWorkData data) {
                activity.setCruiseWorkData(data);
            }

            @Override
            public void onFailure(String msg) {
                activity.showOnfaile15(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }
}
