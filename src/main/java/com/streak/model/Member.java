package com.streak.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"role",
"inviteDate",
"inviteStatus",
"orgKey",
"displayName",
"fullName",
"email",
"image",
"userKey"
})
public class Member {
	@JsonProperty("role")
	public String role;
	
	@JsonProperty("inviteDate")
	public Long inviteDate;
	
	@JsonProperty("inviteStatus")
	public String inviteStatus;
	
	@JsonProperty("orgKey")
	public String orgKey;
	
	@JsonProperty("displayName")
	public String displayName;
	
	@JsonProperty("fullName")
	public String fullName;
	
	@JsonProperty("email")
	public String email;
	
	@JsonProperty("image")
	public String image;
	
	@JsonProperty("userKey")
	public String userKey;
}
