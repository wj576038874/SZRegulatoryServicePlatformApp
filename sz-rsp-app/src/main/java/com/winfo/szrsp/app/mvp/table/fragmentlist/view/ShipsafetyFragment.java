package com.winfo.szrsp.app.mvp.table.fragmentlist.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.winfo.szrsp.app.R;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.fragmentlist.view
 * @Filename: ShipsafetyFragment
 * @Author: lsj
 * @Date: 2017/12/20  10:11
 * @Description:
 * @Version:船舶危防现场监督检查记录表
 */
public class ShipsafetyFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_safety,null);
        return view;
    }
}
