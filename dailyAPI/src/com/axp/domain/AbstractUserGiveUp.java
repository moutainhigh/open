package com.axp.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;



public abstract class AbstractUserGiveUp implements java.io.Serializable {
	
	private Integer id;
	private Boolean isValid;
	private Timestamp createTime ;
	private UserFriendsInfo userFriendsInfoId;
	private Users readerUserId;
	
	
	
	public AbstractUserGiveUp() {
		super();
	}
	public AbstractUserGiveUp(Integer id, Boolean isValid, Timestamp createTime, UserFriendsInfo userFriendsInfoId,
			Users readerUserId) {
		super();
		this.id = id;
		this.isValid = isValid;
		this.createTime = createTime;
		this.userFriendsInfoId = userFriendsInfoId;
		this.readerUserId = readerUserId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Boolean getIsValid() {
		return isValid;
	}
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public UserFriendsInfo getUserFriendsInfoId() {
		return userFriendsInfoId;
	}
	public void setUserFriendsInfoId(UserFriendsInfo userFriendsInfoId) {
		this.userFriendsInfoId = userFriendsInfoId;
	}
	public Users getReaderUserId() {
		return readerUserId;
	}
	public void setReaderUserId(Users readerUserId) {
		this.readerUserId = readerUserId;
	}
	
	
	
	
}