package com.dajo.model;

import java.io.Serializable;

public enum UserProfileType implements Serializable{
	USER("SELLER"),
	DBA("DBA"),
	ADMIN("ADMIN");
	
	String userProfileType;
	
	private UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType(){
		return userProfileType;
	}
	
}
