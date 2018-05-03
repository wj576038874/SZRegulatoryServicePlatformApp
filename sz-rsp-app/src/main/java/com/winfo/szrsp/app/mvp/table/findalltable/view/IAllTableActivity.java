package com.winfo.szrsp.app.mvp.table.findalltable.view;

import android.app.Dialog;

import com.winfo.szrsp.app.sdk.entity.table.ListCBWFXCData;
import com.winfo.szrsp.app.sdk.entity.table.ListContainerWeightInspectData;
import com.winfo.szrsp.app.sdk.entity.table.ListCruiseWorkData;
import com.winfo.szrsp.app.sdk.entity.table.ListDXSHCData;
import com.winfo.szrsp.app.sdk.entity.table.ListDangerousGoodsKaiXiangData;
import com.winfo.szrsp.app.sdk.entity.table.ListDangerousGoodsXianChangData;
import com.winfo.szrsp.app.sdk.entity.table.ListElectronicCruiseException;
import com.winfo.szrsp.app.sdk.entity.table.ListGoodSecneOutRestServiceData;
import com.winfo.szrsp.app.sdk.entity.table.ListPSCData;
import com.winfo.szrsp.app.sdk.entity.table.ListWatersPatrolData;
import com.winfo.szrsp.app.sdk.entity.table.ListcbjdjgData;
import com.winfo.szrsp.app.sdk.entity.table.ListcbxcData;
import com.winfo.szrsp.app.sdk.entity.table.xhtj.ListXHTJData;

import java.util.List;

/**
 * @ProjectName: SZRegulatoryServicePlatformApp
 * @Package: com.winfo.szrsp.app.mvp.table.findalltable.view
 * @Filename: IAllTableActivity
 * @Author: lsj
 * @Date: 2017/12/7  10:09
 * @Description:
 * @Version:
 */
public interface IAllTableActivity {

    //void showOnFaile1(String msg);
    void showOnFaile2(String msg);

    void showOnFaile3(String msg);

    void showOnFaile4(String msg);

    //void showOnFaile5(String msg);
    void showOnFaile6(String msg);

    void showOnFaile7(String msg);

    void showOnFaile8(String msg);

    void showOnFaile9(String msg);

    void showOnFaile10(String msg);

    void showOnFaile11(String msg);

    void showOnFaile12(String msg);

    void showOnfaile13(String msg);

    void showOnfaile14(String msg);

    void showOnfaile15(String msg);

    void notFound();

    Dialog getDailog();

    void setJDBGData(ListcbjdjgData data);

    void setCBXCData(ListcbxcData data);

    void setDXSHCData(ListDXSHCData data);

    void setWatersPatrolData(ListWatersPatrolData data);

    void setPSCData(ListPSCData data);

    void setXHTjData(ListXHTJData data);

    void setElectronicCruiseException(ListElectronicCruiseException data);

    void setCBWFXCData(ListCBWFXCData data);

    void setGoodSecneOutRestServiceData(ListGoodSecneOutRestServiceData data);

    void setDangerousGoodsXianChangData(ListDangerousGoodsXianChangData data);

    void setDangerousGoodsKaiXiangData(ListDangerousGoodsKaiXiangData data);

    void setContainerWeightInspectData(ListContainerWeightInspectData data);

    void setCruiseWorkData(ListCruiseWorkData data);


    void loginExpired(String msg);
}
