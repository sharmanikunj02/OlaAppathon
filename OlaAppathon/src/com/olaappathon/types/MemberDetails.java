package com.olaappathon.types;

import java.util.List;

/**
 * @author nsingh
 * Date 26-Nov-2014
 *
 *  Version History
 *	0.1		nsingh		26-Nov-2014		Initial Draft
 *
 *
 *  The MemberDetails.java is
 *
 */
public class MemberDetails {
    private String personName;
    private String phoneNo;
    private String emailId;
    private String role;
    private List<Expenses> expenseDetails;
    
    /**
     * @return the expenseDetails
     */
    public List<Expenses> getExpenseDetails() {
        return expenseDetails;
    }
    /**
     * @param expenseDetails the expenseDetails to set
     */
    public void setExpenseDetails(List<Expenses> expenseDetails) {
        this.expenseDetails = expenseDetails;
    }
    /**
     * @return the personName
     */
    public String getPersonName() {
        return personName;
    }
    /**
     * @param personName the personName to set
     */
    public void setPersonName(String personName) {
        this.personName = personName;
    }
    /**
     * @return the phoneNo
     */
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
     * @return the emailId
     */
    public String getEmailId() {
        return emailId;
    }
    /**
     * @param emailId the emailId to set
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }
    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }
    
}
