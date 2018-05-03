package com.winfo.szrsp.app.mvp.nearby.view;

import android.app.Dialog;

import com.winfo.szrsp.app.mvp.IBaseMvpView;
import com.winfo.szrsp.app.sdk.entity.thys.ThysAndShipData;

/**
 * Created by HoBo on 2018/4/2.
 */

public interface INearView extends IBaseMvpView {
    Dialog getDialog();

    void OnSuccess(ThysAndShipData resultData);

    void OnFaile(String msg);
}
