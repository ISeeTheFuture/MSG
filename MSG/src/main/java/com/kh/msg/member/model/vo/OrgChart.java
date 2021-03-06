package com.kh.msg.member.model.vo;

import java.io.Serializable;
import java.sql.Date;


public class OrgChart extends Member implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String deptName;
	private String jobName;
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public OrgChart(int empNo, String jobCd, String deptCd, String userId, String isManager, String userPwd,
			String empName, String empRRNNo, String empImage, String empContact, String empEmail, String empAddress, String authority,
			Date hireDate, String quitYn, String empMsg, String deptName, String jobName) {
		super(empNo, jobCd, deptCd, userId, isManager, userPwd, empName, empRRNNo, empImage, empContact, empEmail, empAddress, authority,
				hireDate, quitYn, empMsg);
		this.deptName = deptName;
		this.jobName = jobName;
	}


	public OrgChart(String deptName, String jobName) {
		super();
		this.deptName = deptName;
		this.jobName = jobName;
	}


	public OrgChart() {
		super();
		// TODO Auto-generated constructor stub
	}


	public OrgChart(int empNo, String jobCd, String deptCd, String userId, String isManager, String userPwd,
			String empName, String empRRNNo, String empImage, String empContact, String empEmail, String empAddress, String authority,
			Date hireDate, String quitYn, String empMsg) {
		super(empNo, jobCd, deptCd, userId, isManager, userPwd, empName, empRRNNo, empImage, empContact, empEmail, empAddress, authority,
				hireDate, quitYn, empMsg);
		// TODO Auto-generated constructor stub
	}


	public String getDeptName() {
		return deptName;
	}


	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	public String getJobName() {
		return jobName;
	}


	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	@Override
	public String toString() {
		return "OrgChart [deptName=" + deptName + ", jobName=" + jobName + ", getEmpNo()=" + getEmpNo()
				+ ", getJobCd()=" + getJobCd() + ", getDeptCd()=" + getDeptCd() + ", getUserId()=" + getUserId()
				+ ", getIsManager()=" + getIsManager() + ", getUserPwd()=" + getUserPwd() + ", getEmpName()="
				+ getEmpName() + ", getEmpRRNNo()=" + getEmpRRNNo() + ", getEmpImage()=" + getEmpImage()
				+ ", getEmpContact()=" + getEmpContact() + ", getEmpEmail()=" + getEmpEmail() + ", getEmpAddress()="
				+ getEmpAddress() + ", getAuthority()=" + getAuthority() + ", getHireDate()=" + getHireDate()
				+ ", getQuitYn()=" + getQuitYn() + ", getEmpMsg()=" + getEmpMsg() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
	
}