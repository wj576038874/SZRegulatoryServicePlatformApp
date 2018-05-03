package com.winfo.szrsp.app.mvp.nearby.view;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import com.winfo.szrsp.app.R;
/**
 * Created by HoBo on 2018/4/10.
 */

public class ShipAisView extends CoordinatorLayout {
    private Context mContext;

    public ShipAisView(Context context) {
        super(context);
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.view_near_ship_ais, this);
    }
}
