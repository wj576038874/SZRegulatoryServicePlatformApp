package com.winfo.szrsp.app.mvp.plotting.model;

import android.app.Dialog;

import com.winfo.szrsp.app.mvp.task.model.imp.TaskListModel;
import com.winfo.szrsp.app.sdk.entity.plotting.Plotting;

/**
 * Created by winfo053 on 2018/4/9.
 */

public interface IPlottingModel {
    void getPlottingListData(Dialog dialog, String pageNum, String pageSize,String bhname,String drawingType, boolean bol, PlottingModel.OnLoadGetPlottingListListener onLoadGetPlottingListListener);
    void deletePlottingById(Dialog dialog,String id,boolean bol,PlottingModel.OnDeletePlottingListener onDeletePlottingListener);
    void addPlotting(Dialog dialog, Plotting plotting, boolean bol, PlottingModel.OnAddPlottingListener onAddPlottingListener);
    void editPlotting(Dialog dialog, Plotting plotting, boolean bol, PlottingModel.OnEditPlottingListener onEditPlottingListener);
}
