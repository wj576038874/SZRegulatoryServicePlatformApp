package com.winfo.szrsp.app.mvp.maplayer.presenter;

import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.entity.request.AisRequestModel;
import com.winfo.szrsp.app.listener.ChangeSwitchButtonState;
import com.winfo.szrsp.app.mvp.maplayer.model.AisModel;
import com.winfo.szrsp.app.mvp.maplayer.model.IAisModel;
import com.winfo.szrsp.app.mvp.maplayer.view.IMapLayerView;
import com.winfo.szrsp.app.sdk.entity.aisdata.TelephoneMmsi;

import java.util.List;

public class AisPresenter {
    private IAisModel aisModel;
    private IMapLayerView mapLayerView;

    public AisPresenter(IMapLayerView mapLayerView) {
        this.aisModel = new AisModel();
        this.mapLayerView = mapLayerView;
    }

    /**
     * 刷新ais数据
     */
    public void refreshAis(final ChangeSwitchButtonState changeSwitchButtonState, boolean isShowDialog, final String shipTypeCode) {
        aisModel.loadAis(mapLayerView.getDialog(), isShowDialog, mapLayerView.getAisRequestModel(),
                new AisModel.OnLoadAisDataLitener() {

                    @Override
                    public void onSuccess(List<Ais> aisDatas) {
                        mapLayerView.setAisData(aisDatas, shipTypeCode, shipTypeCode);
                    }

                    @Override
                    public void onFailure(String error) {
                        mapLayerView.showMsg(error);
                        mapLayerView.setIsShowAis(false);
                        if (changeSwitchButtonState != null) {
                            changeSwitchButtonState.changeState(false);
                        }
                    }
                });
    }

    /**
     * 刷新执法船数据
     */
    public void refreshZhifa(final ChangeSwitchButtonState changeSwitchButtonState, boolean isShowDialog) {
        aisModel.loadZhifaAis(mapLayerView.getDialog(), isShowDialog,
                new AisModel.OnLoadAisDataLitener() {
                    @Override
                    public void onSuccess(List<Ais> aisDatas) {
                        mapLayerView.setAllZhifaData(aisDatas);
                    }

                    @Override
                    public void onFailure(String error) {
                        mapLayerView.showMsg(error);
                        mapLayerView.setIsShowAllZhifa(false);
                        if (changeSwitchButtonState != null) {
                            changeSwitchButtonState.changeState(false);
                        }
                    }
                });
    }

    /**
     * 加载网络point
     *
     * @param
     * @param changeSwitchButtonState
     */
    public void loadNetAisPoint(AisRequestModel aisRequestModel, final ChangeSwitchButtonState changeSwitchButtonState, boolean isShowDialog, final String shipTypeCode) {
//        aisModel.loadNetAisPoint(mapLayerView.getDialog(), isShowDialog, mapLayerView.getAisRequestModel(),
//                new AisModel.OnLoadAisDataLitener() {
//                    @Override
//                    public void onSuccess(List<Ais> ais) {
//                        mapLayerView.setAisData(ais);
//                    }
//
//                    @Override
//                    public void onFailure(String msg) {
//                        mapLayerView.showMsg(msg);
//                        mapLayerView.setIsShowAis(false);
//                        if (changeSwitchButtonState != null) {
//                            changeSwitchButtonState.changeState(false);
//                        }
//                    }
//                });
        aisModel.loadAis(mapLayerView.getDialog(), isShowDialog, mapLayerView.getAisRequestModel(),
                new AisModel.OnLoadAisDataLitener() {

                    @Override
                    public void onSuccess(List<Ais> aisDatas) {
                        mapLayerView.setAisData(aisDatas, shipTypeCode, shipTypeCode);
                    }

                    @Override
                    public void onFailure(String error) {
                        mapLayerView.showMsg(error);
                        mapLayerView.setIsShowAis(false);
                        if (changeSwitchButtonState != null) {
                            changeSwitchButtonState.changeState(false);
                        }
                    }
                });
    }

    /**
     * 加载当前屏幕范围内的ais数据
     */
    public void getShipAisData(final ChangeSwitchButtonState changeSwitchButtonState, final boolean isShowDialog, final String shipTypeCode, final String shipTypeNameCn) {
        aisModel.loadAis(mapLayerView.getDialog(), isShowDialog, mapLayerView.getAisRequestModel(),
                new AisModel.OnLoadAisDataLitener() {

                    @Override
                    public void onSuccess(List<Ais> aisDatas) {
                        mapLayerView.setAisData(aisDatas, shipTypeCode, shipTypeNameCn);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mapLayerView.showMsg(msg);
                        mapLayerView.setIsShowAis(false);
                        if (changeSwitchButtonState != null) {
                            changeSwitchButtonState.changeState(false);
                        }
                    }
                });
    }


    /**
     * 加载所有执法船
     */
    public void getAllZhifaData(final ChangeSwitchButtonState changeSwitchButtonState, final boolean isShowDialog) {
        aisModel.loadZhifaAis(mapLayerView.getDialog(), isShowDialog,
                new AisModel.OnLoadAisDataLitener() {
                    @Override
                    public void onSuccess(List<Ais> aisDatas) {
                        mapLayerView.setAllZhifaData(aisDatas);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mapLayerView.showMsg(msg);
                        mapLayerView.setIsShowAllZhifa(false);
                        if (changeSwitchButtonState != null) {
                            changeSwitchButtonState.changeState(false);
                        }
                    }
                });
    }


    /**
     * 加载1-11级的点的所有的黄点数据
     */
    public void get_1_11_points(final ChangeSwitchButtonState changeSwitchButtonState, final boolean isShowDialog) {
//        File file = aisModel.getCacheFile();
//        // 先加载本地point不存在的话 就加载网络
//        if (file.exists()) {
//            aisModel.loadLocalAisPoint(mapLayerView.getDialog(), isShowDialog, file,
//                    new AisModel.OnLoadLocalAisPointLitener() {
//
//                        @Override
//                        public void onSuccess(
//                                List<AisPointModel> localpoint) {
//                            mapLayerView.setAisPointJWData(localpoint);
//                        }
//
//                        @Override
//                        public void onFailure(String msg) {
//                            // 如果本地加载失败的话 就去加载网络
//                            loadNetAisPoint(mapLayerView.getAisRequestModel(),
//                                    changeSwitchButtonState, isShowDialog);
//                            if (changeSwitchButtonState != null) {
//                                changeSwitchButtonState
//                                        .changeState(false);
//                            }
//                        }
//                    });
//        } else {
//            //加载网络
//            loadNetAisPoint(mapLayerView.getAisRequestModel(), changeSwitchButtonState, isShowDialog);
//        }
    }

    /**
     * 加载12 13级的网络的当前屏幕范围的黄点数据
     */
    public void get_12_13_netPoints(final ChangeSwitchButtonState changeSwitchButtonState, final boolean isShowDialog) {
//        aisModel.loadNetAisPoint(mapLayerView.getDialog(), isShowDialog, mapLayerView.getAisRequestModel(),
//                new AisModel.OnLoadNetAisPointLitener() {
//
//                    @Override
//                    public void onSuccess(List<AisPointModel> localpoint) {
//                        mapLayerView.setAisPointJWData(localpoint);
//                    }
//
//                    @Override
//                    public void onFailure(String msg) {
//                        mapLayerView.showMsg(msg);
//                        mapLayerView.setIsShowAis(false);
//                        if (changeSwitchButtonState != null) {
//                            changeSwitchButtonState.changeState(false);
//                        }
//                    }
//                });
    }

    /**
     * 根据mmsi查询船电话
     *
     * @param mmsi
     */
    public void getPhoneByMmsi(String mmsi, boolean isShowDialog) {
        aisModel.getPhoneByMmsi(mmsi, mapLayerView.getDialog(), isShowDialog, new AisModel.OnLoadPhoneDataLitener() {
            @Override
            public void onSuccess(String msg, TelephoneMmsi resultData) {
                mapLayerView.setShipPhone(msg, resultData);
            }

            @Override
            public void onFailure(String msg, TelephoneMmsi resultData) {
                mapLayerView.setShipPhone(msg, resultData);
            }

            @Override
            public void OnLoginExpired(String msg) {
                mapLayerView.loginExpired(msg);
            }
        });
    }


}
