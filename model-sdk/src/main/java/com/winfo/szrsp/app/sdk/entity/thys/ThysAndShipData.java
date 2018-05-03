package com.winfo.szrsp.app.sdk.entity.thys;

import com.winfo.szrsp.app.sdk.http.ResponseResult;

import java.util.List;

/**
 * Created by HoBo on 2018/4/2.
 */

public class ThysAndShipData {
    private ResponseResult<List<ThysData>> listThysResult;
    private ResponseResult<List<ShipData>> listShipResult;

    public ResponseResult<List<ThysData>> getListThysResult() {
        return listThysResult;
    }

    public void setListThysResult(ResponseResult<List<ThysData>> listThysResult) {
        this.listThysResult = listThysResult;
    }

    public ResponseResult<List<ShipData>> getListShipResult() {
        return listShipResult;
    }

    public void setListShipResult(ResponseResult<List<ShipData>> listShipResult) {
        this.listShipResult = listShipResult;
    }
}
