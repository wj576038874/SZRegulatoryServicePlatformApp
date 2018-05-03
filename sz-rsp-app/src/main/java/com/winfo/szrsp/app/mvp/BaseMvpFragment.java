package com.winfo.szrsp.app.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.mvp
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.mvp.BaseMvpFragment.java
 * Date: 2017/12/11 18:43
 * Description:
 */

public abstract class BaseMvpFragment<V extends IBaseMvpView, P extends BaseMvpPresenter<V>> extends Fragment implements IBaseMvpView {

    protected P mPresenter;


    @Nullable
    @Override
    @SuppressWarnings("unchecked")
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        mPresenter.attachMvpView((V) this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    protected abstract P createPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachMvpView();
    }

}
