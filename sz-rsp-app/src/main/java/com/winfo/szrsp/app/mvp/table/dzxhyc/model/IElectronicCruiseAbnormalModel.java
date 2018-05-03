package com.winfo.szrsp.app.mvp.table.dzxhyc.model;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.table.CtElectronicCruiseException;

/**
 * Created by wly on 2018/1/26.
 *
 */

public interface IElectronicCruiseAbnormalModel {

    void subCtElectronicCruiseException(Dialog dialog, CtElectronicCruiseException ctElectronicCruiseException, ElectronicCruiseAbnormalModel.OnSubCtElectronicCruiseExceptionListener onSubCtElectronicCruiseExceptionListener);

}
