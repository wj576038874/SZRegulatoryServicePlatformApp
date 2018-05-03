package com.winfo.szrsp.app.mvp.plotting.presenter;

import com.winfo.szrsp.app.application.SzRspApplication;
import com.winfo.szrsp.app.mvp.BaseMvpPresenter;
import com.winfo.szrsp.app.mvp.plotting.model.IPlottingModel;
import com.winfo.szrsp.app.mvp.plotting.model.PlottingModel;
import com.winfo.szrsp.app.mvp.plotting.view.IPlottingView;
import com.winfo.szrsp.app.sdk.entity.plotting.Plotting;
import com.winfo.szrsp.app.sdk.entity.plotting.PlottingListData;
import com.winfo.szrsp.app.utils.ToastUtils;


public class PlottingPresenter extends BaseMvpPresenter<IPlottingView> {
    private IPlottingModel iPlottingModel;

    public PlottingPresenter() {
        iPlottingModel = new PlottingModel();
    }

    public void getPlottingListData(String pageNum, String pageSize,String bhname,String drawingType,boolean isShowDialog) {

        if (mView == null) return;
        iPlottingModel.getPlottingListData(mView.getDailog(), pageNum, pageSize, bhname, drawingType, isShowDialog, new PlottingModel.OnLoadGetPlottingListListener() {
            @Override
            public void OnSuccess(PlottingListData data) {
                mView.setPlottingListData(data);
            }

            @Override
            public void OnFaile(String msg) {
                mView.showOnFaile(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                mView.loginExpired(msg);
            }
        });
    }

    public void deletePlottingById(String id,boolean isShowDialog) {
        if (mView == null) return;
        iPlottingModel.deletePlottingById(mView.getDailog(), id, isShowDialog, new PlottingModel.OnDeletePlottingListener() {
            @Override
            public void OnSuccess(String msg) {
                mView.deleteSuccess(msg);
            }

            @Override
            public void OnFaile(String msg) {
                mView.deleteFaile(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                mView.loginExpired(msg);
            }
        });
    }
    public void addPlotting(Plotting plotting, boolean isShowDialog) {
        if (mView == null) return;
        iPlottingModel.addPlotting(mView.getDailog(),plotting,isShowDialog,new PlottingModel.OnAddPlottingListener(){
            @Override
            public void OnSuccess(String msg) {
                mView.addSuccess(msg);
            }

            @Override
            public void OnFaile(String msg) {
                mView.addFaile(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                mView.loginExpired(msg);
            }
        });
    }
    public void editPlotting(Plotting plotting, boolean isShowDialog) {
        if (mView == null) return;
        iPlottingModel.editPlotting(mView.getDailog(),plotting,isShowDialog,new PlottingModel.OnEditPlottingListener(){
            @Override
            public void OnSuccess(String msg) {
                mView.editSuccess(msg);
            }

            @Override
            public void OnFaile(String msg) {
                mView.editFaile(msg);
            }

            @Override
            public void OnLoginExpired(String msg) {
                mView.loginExpired(msg);
            }
        });
    }
}
