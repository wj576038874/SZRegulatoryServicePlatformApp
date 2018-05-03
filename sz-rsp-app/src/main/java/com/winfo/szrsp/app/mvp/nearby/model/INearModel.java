package com.winfo.szrsp.app.mvp.nearby.model;

import android.app.Dialog;

/**
 * Created by HoBo on 2018/4/2.
 */

public interface INearModel {
    void loadData(boolean isLogin, Dialog dialog, NearModel.OnloadDatasListener onloadDatasListener);
}
