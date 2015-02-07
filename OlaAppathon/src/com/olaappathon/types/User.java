package com.olaappathon.types;

import java.util.List;
import java.util.Map;

public class User {
	private String userId;
	private String firstName;
	private String lastName;
	private String personName;
	private String emailId;
	private String password;
	private String phoneNo;
	private int verificationInd;
	private DeviceDetails deviceDetails;
	private List<GroupDetails> groupDetails;
	private Expenses expenses;

	private Map<String, Integer> ruleTypeMap;

	/**
	 * @return the ruleTypeMap
	 */
	public Map<String, Integer> getRuleTypeMap() {
		return ruleTypeMap;
	}

	/**
	 * @param ruleTypeMap
	 *            the ruleTypeMap to set
	 */
	public void setRuleTypeMap(Map<String, Integer> ruleTypeMap) {
		this.ruleTypeMap = ruleTypeMap;
	}

	/**
	 * @return the groupDetails
	 */
	public List<GroupDetails> getGroupDetails() {
		return groupDetails;
	}

	/**
	 * @param groupDetails
	 *            the groupDetails to set
	 */
	public void setGroupDetails(List<GroupDetails> groupDetails) {
		this.groupDetails = groupDetails;
	}

	/**
	 * @return the verificationInd
	 */
	public int getVerificationInd() {
		return verificationInd;
	}

	/**
	 * @param verificationInd
	 *            the verificationInd to set
	 */
	public void setVerificationInd(int verificationInd) {
		this.verificationInd = verificationInd;
	}

	/**
	 * @return the personName
	 */
	public String getPersonName() {
		return personName;
	}

	/**
	 * @param personName
	 *            the personName to set
	 */
	public void setPersonName(String personName) {
		this.personName = personName;
	}

	/**
	 * @return the expenses
	 */
	public Expenses getExpenses() {
		return expenses;
	}

	/**
	 * @param expenses
	 *            the expenses to set
	 */
	public void setExpenses(Expenses expenses) {
		this.expenses = expenses;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId
	 *            the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * @param phoneNo
	 *            the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * @return the deviceDetails
	 */
	public DeviceDetails getDeviceDetails() {
		return deviceDetails;
	}

	/**
	 * @param deviceDetails
	 *            the deviceDetails to set
	 */
	public void setDeviceDetails(DeviceDetails deviceDetails) {
		this.deviceDetails = deviceDetails;
	}

	/**
	 * @return the expenses
	 */
	public Expenses getExpences() {
		return expenses;
	}

	/**
	 * @param expences
	 *            the expenses to set
	 */
	public void setExpences(Expenses expenses) {
		this.expenses = expenses;
	}
}
