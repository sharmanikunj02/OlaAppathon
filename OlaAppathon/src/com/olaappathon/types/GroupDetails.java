package com.olaappathon.types;

import java.util.List;

/**
 * @author nsingh
 * Date 13-Oct-2014
 *
 *  Version History
 *	0.1		nsingh		13-Oct-2014		Initial Draft
 *
 *
 *  The Group.java is
 *
 */
public class GroupDetails {
    private int groupId;
    private String groupName;
    private int ownerNo;
    private String phoneNo;
    private List<MemberDetails> memberDetails;
    private String creationDate;
    private String lastUpatedDate;
    private String callingApiName;
    
    public String getPhoneNo() {
        return phoneNo;
    }
    /**
     * @param phoneNo the phoneNo to set
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    /**
     * @return the ownerNo
     */
    public int getOwnerNo() {
        return ownerNo;
    }
    /**
     * @param ownerNo the ownerNo to set
     */
    public void setOwnerNo(int ownerNo) {
        this.ownerNo = ownerNo;
    }
  
    /**
     * @return the memberDetails
     */
    public List<MemberDetails> getMemberDetails() {
        return memberDetails;
    }
    /**
     * @param memberDetails the memberDetails to set
     */
    public void setMemberDetails(List<MemberDetails> memberDetails) {
        this.memberDetails = memberDetails;
    }
    /**
     * @return the creationDate
     */
    public String getCreationDate() {
        return creationDate;
    }
  
    /**
     * @return the groupId
     */
    public int getGroupId() {
        return groupId;
    }
    /**
     * @param groupId the groupId to set
     */
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
    /**
     * @return the groupName
     */
    public String getGroupName() {
        return groupName;
    }
    /**
     * @param groupName the groupName to set
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    /**
     * @return the createdDate
     */
    public String getcreationDate() {
        return creationDate;
    }
    /**
     * @param createdDate the createdDate to set
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
    /**
     * @return the lastUpatedDate
     */
    public String getLastUpatedDate() {
        return lastUpatedDate;
    }
    /**
     * @param lastUpatedDate the lastUpatedDate to set
     */
    public void setLastUpatedDate(String lastUpatedDate) {
        this.lastUpatedDate = lastUpatedDate;
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
