package com.winfo.szrsp.app.mvp.maplayer.presenter;

import com.winfo.szrsp.app.mvp.maplayer.model.IObtainModel;
import com.winfo.szrsp.app.mvp.maplayer.model.ObtainModel;
import com.winfo.szrsp.app.mvp.maplayer.view.IObtainView;
import com.winfo.szrsp.app.sdk.entity.aisdata.Ais;

import java.util.List;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.maplayer.presenter
 * @Filename: ObtainPresenter
 * @Author: lsj
 * @Date: 2018/1/31  16:42
 * @Description:
 * @Version:
 */
public class ObtainPresenter {
    private IObtainModel model;
    private IObtainView activity;

    public ObtainPresenter(IObtainView activity){
        this.model = new ObtainModel();
        this.activity = activity;
    }

    public void subObtainData(Ais ais,List<String> list1, List<String> list2){
        model.subObtain(activity.getDialog(),ais,list1,list2, new ObtainModel.OnSubObtainListener() {
            @Override
            public void onSuccess(String msg) {
                activity.showMsg("上传成功！");
            }

            @Override
            public void onFaile(String msg) {
                activity.showMsg(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                activity.loginExpired(msg);
            }
        });
    }
}
