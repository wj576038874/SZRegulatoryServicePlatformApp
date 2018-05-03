package com.winfo.szrsp.app.sdk.entity.table;

import java.util.List;

/**
 * Created by ChengQi on 2017/12/7.
 *
 */

public class WatersPatrol {

    private List<DetailArea>detailArea;

    private List<DetailShip>detailShip;

    private Info info;

    public List<DetailArea> getDetailArea() {
        return detailArea;
    }

    public void setDetailArea(List<DetailArea> detailArea) {
        this.detailArea = detailArea;
    }

    public List<DetailShip> getDetailShip() {
        return detailShip;
    }

    public void setDetailShip(List<DetailShip> detailShip) {
        this.detailShip = detailShip;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
