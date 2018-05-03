package com.winfo.szrsp.app.sdk.entity.table;

import java.io.Serializable;

/**
 * Created by HoBo on 2018/3/26.
 */

public class KxjcData implements Serializable {
    private DangerousGoodsXianChangData ctDangerPolluteScene;
    private DangerousGoodsKaiXiangData ctDangerPolluteOut;
    private OrdinaryGoodsKaiXiangData ctGoodSecneOut;
    private ContainerWeightInspectData ctCaseWeightInspect;

    private String disposalDecision;
    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getDisposalDecision() {
        return disposalDecision;
    }

    public void setDisposalDecision(String disposalDecision) {
        this.disposalDecision = disposalDecision;
    }

    public DangerousGoodsXianChangData getCtDangerPolluteScene() {
        return ctDangerPolluteScene;
    }

    public void setCtDangerPolluteScene(DangerousGoodsXianChangData ctDangerPolluteScene) {
        this.ctDangerPolluteScene = ctDangerPolluteScene;
    }

    public DangerousGoodsKaiXiangData getCtDangerPolluteOut() {
        return ctDangerPolluteOut;
    }

    public void setCtDangerPolluteOut(DangerousGoodsKaiXiangData ctDangerPolluteOut) {
        this.ctDangerPolluteOut = ctDangerPolluteOut;
    }

    public OrdinaryGoodsKaiXiangData getCtGoodSecneOut() {
        return ctGoodSecneOut;
    }

    public void setCtGoodSecneOut(OrdinaryGoodsKaiXiangData ctGoodSecneOut) {
        this.ctGoodSecneOut = ctGoodSecneOut;
    }

    public ContainerWeightInspectData getCtCaseWeightInspect() {
        return ctCaseWeightInspect;
    }

    public void setCtCaseWeightInspect(ContainerWeightInspectData ctCaseWeightInspect) {
        this.ctCaseWeightInspect = ctCaseWeightInspect;
    }
}
