package com.winfo.szrsp.app.sdk.entity.task;

import com.winfo.szrsp.app.sdk.entity.user.UserInfo;

import java.util.List;

/**
 * Created by Guan on 2017-12-15.
 */

public class TaskPersonResouse {

    private List<UserInfo> userInfoList;

    private List<LawEnforcementCar> lawEnforcementCarList ;

    private List<LawEnforcementShip>lawEnforcementShipList;

    public List<UserInfo> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }

    public List<LawEnforcementCar> getLawEnforcementCarList() {
        return lawEnforcementCarList;
    }

    public void setLawEnforcementCarList(List<LawEnforcementCar> lawEnforcementCarList) {
        this.lawEnforcementCarList = lawEnforcementCarList;
    }

    public List<LawEnforcementShip> getLawEnforcementShipList() {
        return lawEnforcementShipList;
    }

    public void setLawEnforcementShipList(List<LawEnforcementShip> lawEnforcementShipList) {
        this.lawEnforcementShipList = lawEnforcementShipList;
    }
}
