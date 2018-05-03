package com.winfo.szrsp.app.mvp.nearby.view;

import com.winfo.dnc.sdk.Point;

/**
 * 自定义物标
 *
 * @author King
 */
public class CustomTarget {

    private Point point;


    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    private int position;


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
