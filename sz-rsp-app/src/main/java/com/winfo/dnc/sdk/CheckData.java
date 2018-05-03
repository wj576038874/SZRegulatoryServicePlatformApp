package com.winfo.dnc.sdk;

import java.io.Serializable;

/**
 * Created by ChengQi on 2017/5/16.
 */

public class CheckData implements Serializable {

    private String XYCM;//CHECKDATA里的中文船名


    public void setXYCM(String XYCM) {
        this.XYCM = XYCM;
    }

    public String getXYCM() {
        return XYCM;
    }

}
