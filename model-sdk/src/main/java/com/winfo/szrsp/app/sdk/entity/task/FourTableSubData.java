package com.winfo.szrsp.app.sdk.entity.task;

import java.io.Serializable;

/**
 * Created by HoBo on 2018/3/27.
 */

public class FourTableSubData implements Serializable{

    /**
     * ctCaseWeightInspect : 140320180327152237701
     * ctDangerPolluteOut : 140320180327152237168
     * ctDangerPolluteScene : null
     * ctGoodSecneOut : 140320180327152237972
     */

    private String ctCaseWeightInspect;
    private String ctDangerPolluteOut;
    private String ctDangerPolluteScene;
    private String ctGoodSecneOut;

    public String getCtCaseWeightInspect() {
        return ctCaseWeightInspect;
    }

    public void setCtCaseWeightInspect(String ctCaseWeightInspect) {
        this.ctCaseWeightInspect = ctCaseWeightInspect;
    }

    public String getCtDangerPolluteOut() {
        return ctDangerPolluteOut;
    }

    public void setCtDangerPolluteOut(String ctDangerPolluteOut) {
        this.ctDangerPolluteOut = ctDangerPolluteOut;
    }

    public String getCtDangerPolluteScene() {
        return ctDangerPolluteScene;
    }

    public void setCtDangerPolluteScene(String ctDangerPolluteScene) {
        this.ctDangerPolluteScene = ctDangerPolluteScene;
    }

    public String getCtGoodSecneOut() {
        return ctGoodSecneOut;
    }

    public void setCtGoodSecneOut(String ctGoodSecneOut) {
        this.ctGoodSecneOut = ctGoodSecneOut;
    }
}
