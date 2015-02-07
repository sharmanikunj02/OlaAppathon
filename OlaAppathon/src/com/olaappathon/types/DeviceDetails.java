package com.olaappathon.types;

/**
 * @author nsingh
 * Date 13-Oct-2014
 *
 *  Version History
 *	0.1		nsingh		13-Oct-2014		Initial Draft
 *
 *
 *  The DeviceDetails.java is
 *
 */
public class DeviceDetails {
    private String deviceId;
    private String deviceName;
    private String deviceModel;
    private String imei;
    private String remoteKey;
    private String buildVersion;
    private String creationDate;
    private String lastUpdatedDate;
    private String callingApiName;
    
    /**
     * @return the deviceId
     */
    public String getDeviceId() {
        return deviceId;
    }
    /**
     * @param deviceId the deviceId to set
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    /**
     * @return the deviceName
     */
    public String getDeviceName() {
        return deviceName;
    }
    /**
     * @param deviceName the deviceName to set
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
    /**
     * @return the deviceModel
     */
    public String getDeviceModel() {
        return deviceModel;
    }
    /**
     * @param deviceModel the deviceModel to set
     */
    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }
    /**
     * @return the imei
     */
    public String getImei() {
        return imei;
    }
    /**
     * @param imei the imei to set
     */
    public void setImei(String imei) {
        this.imei = imei;
    }
    /**
     * @return the remoteKey
     */
    public String getRemoteKey() {
        return remoteKey;
    }
    /**
     * @param remoteKey the remoteKey to set
     */
    public void setRemoteKey(String remoteKey) {
        this.remoteKey = remoteKey;
    }
    /**
     * @return the buildVersion
     */
    public String getBuildVersion() {
        return buildVersion;
    }
    /**
     * @param buildVersion the buildVersion to set
     */
    public void setBuildVersion(String buildVersion) {
        this.buildVersion = buildVersion;
    }
    /**
     * @return the creationDate
     */
    public String getCreationDate() {
        return creationDate;
    }
    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
    /**
     * @return the lastUpdatedDate
     */
    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }
    /**
     * @param lastUpdatedDate the lastUpdatedDate to set
     */
    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }
    /**
     * @return the callingApiName
     */
    public String getCallingApiName() {
        return callingApiName;
    }
    /**
     * @param callingApiName the callingApiName to set
     */
    public void setCallingApiName(String callingApiName) {
        this.callingApiName = callingApiName;
    }
}
