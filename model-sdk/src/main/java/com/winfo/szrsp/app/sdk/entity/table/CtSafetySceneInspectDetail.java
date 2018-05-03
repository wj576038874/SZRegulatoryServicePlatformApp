package com.winfo.szrsp.app.sdk.entity.table;

import java.io.Serializable;
/**
 * 船舶危防现场监督检查记录表 详细表
 * @author Administrator
 *
 */
public class CtSafetySceneInspectDetail implements Serializable {
	 
	private String inspectNo;

	private String sex;

    private String itemNum;

    private String itemName;

    private String describe;

    private String handle;

    private String rectificat;

    private static final long serialVersionUID = 1L;
    
    public String getInspectNo() {
        return inspectNo;
    }

    public void setInspectNo(String inspectNo) {
        this.inspectNo = inspectNo == null ? null : inspectNo.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getItemNum() {
        return itemNum;
    }

    public void setItemNum(String itemNum) {
        this.itemNum = itemNum == null ? null : itemNum.trim();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle == null ? null : handle.trim();
    }

    public String getRectificat() {
        return rectificat;
    }

    public void setRectificat(String rectificat) {
        this.rectificat = rectificat == null ? null : rectificat.trim();
    }
}