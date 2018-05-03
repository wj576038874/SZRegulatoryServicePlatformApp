package com.winfo.szrsp.app.mvp.table.psca.model;


import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.table.CtPscFromObject;

/**
 * Created by wly on 2017/12/20.
 *
 */

public interface IPSCFormModel {

    void subPSCFormAData(Dialog dialog, CtPscFromObject ctPscFromObject,String qm_path ,String qm_path2, PSCFormModel.OnSubPSCFormADataListener subPSCFormADataListener);


}
