package com.voicerecog.dao;

public class UserInfo {

	public String name;
	public String sid;
	public String profile_id;
	public String verify_id;
	public String enroll_status;
	
	
	public String getProfile_id() {
		return profile_id;
	}
	public void setProfile_id(String profile_id) {
		this.profile_id = profile_id;
	}
	public String getVerify_id() {
		return verify_id;
	}
	public void setVerify_id(String verify_id) {
		this.verify_id = verify_id;
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getEnroll_status() {
		return enroll_status;
	}
	public void setEnroll_status(String enroll_status) {
		this.enroll_status = enroll_status;
	}
	@Override
	public String toString() {
		return "UserInfo [name=" + name + ", sid=" + sid + ", profile_id=" + profile_id + ", verify_id=" + verify_id
				+ ", enroll_status=" + enroll_status + "]";
	}
	
	
}
