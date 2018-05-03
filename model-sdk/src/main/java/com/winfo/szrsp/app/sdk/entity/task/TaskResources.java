package com.winfo.szrsp.app.sdk.entity.task;

import java.io.Serializable;

public class TaskResources implements Serializable {
    private String taskId;

    private String patrolBoatCode;

    private String patrolBoatMmsi;

    private String patrolBoatName;

    private String enforceVehicleCode;

    private String enforceVehicleLicense;

    private String enforceVehicleName;

    private static final long serialVersionUID = 1L;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public String getPatrolBoatCode() {
        return patrolBoatCode;
    }

    public void setPatrolBoatCode(String patrolBoatCode) {
        this.patrolBoatCode = patrolBoatCode == null ? null : patrolBoatCode.trim();
    }

    public String getPatrolBoatMmsi() {
        return patrolBoatMmsi;
    }

    public void setPatrolBoatMmsi(String patrolBoatMmsi) {
        this.patrolBoatMmsi = patrolBoatMmsi == null ? null : patrolBoatMmsi.trim();
    }

    public String getPatrolBoatName() {
        return patrolBoatName;
    }

    public void setPatrolBoatName(String patrolBoatName) {
        this.patrolBoatName = patrolBoatName == null ? null : patrolBoatName.trim();
    }

    public String getEnforceVehicleCode() {
        return enforceVehicleCode;
    }

    public void setEnforceVehicleCode(String enforceVehicleCode) {
        this.enforceVehicleCode = enforceVehicleCode == null ? null : enforceVehicleCode.trim();
    }

    public String getEnforceVehicleLicense() {
        return enforceVehicleLicense;
    }

    public void setEnforceVehicleLicense(String enforceVehicleLicense) {
        this.enforceVehicleLicense = enforceVehicleLicense == null ? null : enforceVehicleLicense.trim();
    }

    public String getEnforceVehicleName() {
        return enforceVehicleName;
    }

    public void setEnforceVehicleName(String enforceVehicleName) {
        this.enforceVehicleName = enforceVehicleName == null ? null : enforceVehicleName.trim();
    }
}