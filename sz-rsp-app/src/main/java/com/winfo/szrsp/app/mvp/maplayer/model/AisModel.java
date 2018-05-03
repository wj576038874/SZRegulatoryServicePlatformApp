package com.winfo.szrsp.app.mvp.maplayer.model;

import android.app.Dialog;
import android.os.Environment;

import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;
import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.entity.request.AisRequestModel;
import com.winfo.szrsp.app.sdk.entity.aisdata.TelephoneMmsi;
import com.winfo.szrsp.app.sdk.http.DialogSubscriber;
import com.winfo.szrsp.app.sdk.http.ResponseResult;
import com.winfo.szrsp.app.sdk.model.BaseModel;
import com.winfo.szrsp.app.sdk.service.AisService;
import com.winfo.szrsp.app.sdk.utils.ACache;
import com.winfo.szrsp.app.sdk.utils.FastJsonUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * AIS业务处理模型
 *
 * @author winfo-wj
 */
public class AisModel extends BaseModel<AisService> implements IAisModel {

    public AisModel() {
        super(SzRspApplication.getContext());
    }

    @Override
    public void loadAis(Dialog dialog, boolean isShowDialog, AisRequestModel aisRequestModel, final OnLoadAisDataLitener loadAisDataLitener) {

        Observable<List<Ais>> observable = mService.loadAisData("AISAreaShip", aisRequestModel.getStartPoint() + "$" + aisRequestModel.getStopPoint(), true);
        Subscriber<List<Ais>> subscriber = new DialogSubscriber<List<Ais>>(dialog, isShowDialog) {
            @Override
            public void onSuccess(List<Ais> aislist) {
                if (null != aislist) {
                    List<Ais> aiss = new ArrayList<>();
                    String zhifa_mmsi = ACache.get(SzRspApplication.getContext()).getAsString("zhifa_mmsi");
                    if (zhifa_mmsi != null) {
                        List<String> ss = Arrays.asList(zhifa_mmsi.split(","));
                        for (Ais ais : aislist) {
                            if (!ss.contains(ais.getID())) {
                                aiss.add(ais);
                            }
                        }
                        loadAisDataLitener.onSuccess(aiss);
                    } else {
                        loadAisDataLitener.onSuccess(aislist);
                    }
                } else {
                    loadAisDataLitener.onFailure("没有查询到数据");
                }

            }

            @Override
            public void onFailure(String msg) {
                loadAisDataLitener.onFailure(msg);
            }
        };
        toSubscribe(observable, subscriber);

    }

    @Override
    public void loadNetAisPoint(Dialog dialog, boolean isShowDialog, final AisRequestModel aisRequestModel, final OnLoadAisDataLitener loadAisDataLitener) {
        //110.3906250000,15.7076627696$130.8691406250,44.0244215197

        Observable<List<Ais>> observable = mService.loadAisData("AISAreaShip", "110.3906250000,15.7076627696$130.8691406250,44.0244215197", true);
        Subscriber<List<Ais>> subscriber = new DialogSubscriber<List<Ais>>(dialog, isShowDialog) {
            @Override
            public void onSuccess(List<Ais> ais) {
                if (null != ais) {
                    loadAisDataLitener.onSuccess(ais);
                } else {
                    loadAisDataLitener.onFailure("没有查询到数据");
                }
            }

            @Override
            public void onFailure(String msg) {
                loadAisDataLitener.onFailure(msg);
            }
        };
        toSubscribe(observable, subscriber);
    }

    @Override
    public void loadNetAisPoint_T_T(Dialog dialog, boolean isShowDialog, final AisRequestModel aisRequestModel, final OnLoadNetAisPointLitener loadNetAisPointLitener) {

//        String point1 = "";
//        String point2 = "";
//        String pageSize = "";
//
//        point1 = aisRequestModel.getStartPoint();
//        point2 = aisRequestModel.getStopPoint();
//        pageSize = aisRequestModel.getPageSize();
//
//        Observable observable = OkHttpUtils.getRetrofit().create(ApiService.class).loadNetAisPoint(point1, point2, pageSize, aisRequestModel.getSpanPerPixel(), aisRequestModel.getMinX(), aisRequestModel.getMinY(), aisRequestModel.getLevel(), aisRequestModel.getHeight());
//        Subscriber<ResponseResult<List<AisPointModel>>> subscriber = new DialogSubscriber<ResponseResult<List<AisPointModel>>>(dialog, isShowDialog) {
//            @Override
//            public void onSuccess(ResponseResult<List<AisPointModel>> listResponseResult) {
//                switch (listResponseResult.getResult()) {
//                    case 1:
//                        if (!cacheIsExist()) {
//                            try {
//                                if (Integer.parseInt(aisRequestModel.getLevel()) < 12) {
//
//                                    String s = JsonUtil.beanToJson(listResponseResult);
//                                    saveCache(JsonUtil.beanToJson(listResponseResult));
//                                }
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        loadNetAisPointLitener.onSuccess(listResponseResult.getData());
//                        break;
//                    case -1:
//                        loadNetAisPointLitener.onFailure(listResponseResult.getMsg());
//                        break;
//                    case 0:
//                        loadNetAisPointLitener.onFailure(listResponseResult.getMsg());
//                        break;
//                }
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                loadNetAisPointLitener.onFailure(msg);
//            }
//        };
//        toSubscribe(observable, subscriber);
    }

    @Override
    public void loadZhifaAis(Dialog dialog, boolean isShowDialog, final OnLoadAisDataLitener loadAisDataLitener) {
//        loadZhifaData
//        String mmsi="413050360,413050320,413050340,413050370,413050350,413775379,413057590,413905979,413906171,413775384,413050090,413905978,413775383,413050080,413057550,413906172,413057550";
//        String mmsi = "413050080,412057550,413775384,413775383,413905978,413050090,413906171,413905979,412057590,413775379,413050350,413050370,413050340,413050320,413050360,413906172";
//        Observable<List<Ais>> observable = mService.loadZhifaData(mmsi, true);

        String zhifa_mmsi = ACache.get(mContext).getAsString("zhifa_mmsi");
        Observable<List<Ais>> observable;
        Subscriber<List<Ais>> subscriber;
        if (zhifa_mmsi != null) {
            observable = mService.loadZhifaData(zhifa_mmsi, true);
            subscriber = new DialogSubscriber<List<Ais>>(dialog, isShowDialog) {
                @Override
                public void onSuccess(List<Ais> responseResult) {
                    if (null != responseResult) {
                        loadAisDataLitener.onSuccess(responseResult);
                    } else {
                        loadAisDataLitener.onFailure("没有查询到数据");
                    }
                }

                @Override
                public void onFailure(String msg) {
                    loadAisDataLitener.onFailure(msg);
                }
            };
        } else {
            observable = mService.loadHaiXunMmsiList("{}")
                    .flatMap(new Func1<ResponseResult<String>, Observable<List<Ais>>>() {
                        @Override
                        public Observable<List<Ais>> call(ResponseResult<String> stringResponseResult) {
                            if (stringResponseResult.getCode() == 1) {
                                ACache.get(SzRspApplication.getContext()).put("zhifa_mmsi", stringResponseResult.getDatas(), 60 * 60 * 24 * 7);
                                return mService.loadZhifaData(stringResponseResult.getDatas(), true);
                            } else {
                                return null;
                            }
                        }
                    }).map(new Func1<List<Ais>, List<Ais>>() {
                        @Override
                        public List<Ais> call(List<Ais> aisList) {
                            return aisList;
                        }
                    });
            subscriber = new DialogSubscriber<List<Ais>>(dialog, isShowDialog) {
                @Override
                public void onSuccess(List<Ais> ais) {
                    if (null != ais) {
                        loadAisDataLitener.onSuccess(ais);
                    } else {
                        loadAisDataLitener.onFailure("没有查询到数据");
                    }
                }

                @Override
                public void onFailure(String msg) {
                    loadAisDataLitener.onFailure(msg);
                }
            };
        }
        toSubscribe(observable, subscriber);
    }


    @Override
    public void loadLocalAisPoint(Dialog dialog, boolean isShowDialog, final File cacheFle, final OnLoadLocalAisPointLitener localAisPointLitener) {
//        Subscriber<List<AisPointModel>> subscriber = new DialogSubscriber<List<AisPointModel>>(dialog, isShowDialog) {
//            @Override
//            public void onSuccess(List<AisPointModel> aisPointModels) {
//                localAisPointLitener.onSuccess(aisPointModels);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                localAisPointLitener.onFailure(msg);
//            }
//        };
//
//        Observable<List<AisPointModel>> observable = Observable.create(new Observable.OnSubscribe<List<AisPointModel>>() {
//            @Override
//            public void call(Subscriber<? super List<AisPointModel>> subscriber) {
//                BufferedReader reader = null;
//                try {
//                    reader = new BufferedReader(new FileReader(cacheFle));
//                    // 2.内容
//                    String json = reader.readLine();
//                    JSONObject jsonObject = JsonUtil.stringToJson(json);
//                    List<AisPointModel> localPoint = new ArrayList<>();
//                    assert jsonObject != null;
//                    JSONArray jsonArray = jsonObject.getJSONArray(Constants.JSON_DATA);
//                    for (int i = 0, len = jsonArray.length(); i < len; i++) {
//                        AisPointModel aisPointModel = JsonUtil.jsonToBean(jsonArray
//                                .getJSONObject(i).toString(), AisPointModel.class);
//                        localPoint.add(aisPointModel);
//                    }
//                    subscriber.onNext(localPoint);
//                    subscriber.onCompleted();
//                } catch (Exception e) {
//                    subscriber.onError(e);
//                } finally {
//                    if (reader != null) {
//                        try {
//                            reader.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        });
//        toSubscribe(observable, subscriber);
    }

    @Override
    public void getPhoneByMmsi(String mmsi, Dialog dialog, boolean isShowDialog, final OnLoadPhoneDataLitener onLoadPhoneDataLitener) {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("mmsi", mmsi);
        String parames = FastJsonUtil.beanToJsonStr(mapParams);
        Observable<ResponseResult<TelephoneMmsi>> observable = mService.selectPhoneByMmsi(parames);
        Subscriber<ResponseResult<TelephoneMmsi>> subscriber = new DialogSubscriber<ResponseResult<TelephoneMmsi>>(dialog, isShowDialog) {
            @Override
            public void onSuccess(ResponseResult<TelephoneMmsi> responseResult) {
                switch (responseResult.getCode()) {
                    case 1:
                        onLoadPhoneDataLitener.onSuccess(responseResult.getMsg(), responseResult.getDatas());
                        break;
                    case 10008:
                        onLoadPhoneDataLitener.OnLoginExpired(responseResult.getMsg());
                        break;
                    default:
                        onLoadPhoneDataLitener.onFailure(responseResult.getMsg(), responseResult.getDatas());
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                onLoadPhoneDataLitener.onFailure(msg, null);
            }
        };
        toSubscribe(observable, subscriber);
    }


    /**
     * 加载ais模型数据
     *
     * @author winfo-wj
     */
    public interface OnLoadAisDataLitener {
//        /**
//         * 成功
//         *
//         * @param aisDatas 解析出的ais数据集合
//         */
//        void onSuccess(List<AisData> aisDatas);

        void onSuccess(List<Ais> ais);

        /**
         * 失败
         *
         * @param msg 错误信息
         */
        void onFailure(String msg);
    }


    public interface OnLoadPhoneDataLitener {
        void onSuccess(String msg, TelephoneMmsi data);

        void onFailure(String msg, TelephoneMmsi data);

        void OnLoginExpired(String msg);
    }


    /**
     * 网络通航要素点数据
     *
     * @author winfo-wj
     */
    public interface OnLoadNetAisPointLitener {
//        void onSuccess(List<AisPointModel> aisPointModels);
//
//        void onFailure(String msg);
    }

    /**
     * 获取 缓存文件
     *
     * @return 缓存文件
     */

    /**
     * 本地通航要素点数据
     *
     * @author TODO 修改list集合
     */
    public interface OnLoadLocalAisPointLitener {
//        void onSuccess(List<AisPointModel> aisPointModels);
//
//        void onFailure(String msg);
    }

    @Override
    public File getCacheFile() {
        File dir;
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            // sd卡存在
            dir = new File(Environment.getExternalStorageDirectory(),
                    "/Android/data/"
                            + SzRspApplication.getContext().getPackageName()
                            + "/json");

        } else {
            dir = new File(SzRspApplication.getContext().getCacheDir(),
                    "/json");
        }

        if (!dir.exists()) {
            dir.mkdirs();
        }

        String name = "AisCache";
        return new File(dir, name);
    }

    protected boolean cacheIsExist() {
        File file;
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            // sd卡存在
            file = new File(Environment.getExternalStorageDirectory(),
                    "/Android/data/"
                            + SzRspApplication.getContext().getPackageName()
                            + "/json/ThysCache");

        } else {
            file = new File(SzRspApplication.getContext().getCacheDir(),
                    "/json/AisCache");
        }

        if (file.exists() && file.getName().equals("AisCache")) {
            // 删除
            return true;
        } else {
            return false;
        }
    }

    /**
     * 存储缓存
     *
     * @param result json字符串
     * @throws IOException
     */
    protected void saveCache(String result) throws IOException {
        File file = getCacheFile();
        // 文件的存储格式 ：时间戳 + "\n" + 内容
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));

            // writer.write("" + System.currentTimeMillis());
            // writer.write("\r\n");
            writer.write(result);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

}

