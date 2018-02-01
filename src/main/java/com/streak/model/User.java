package com.streak.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Streak CRM User. Creation of users is done when a user signs up for Streak
 * CRM and these objects can not be altered through the API. Since API keys are
 * associated with the user, each API key only has privileges to access its own
 * user object.
 * 
 * @see <a href="https://www.streak.com/api/#user">Streak API</a>
 * 
 * @author Sandeep Khanna
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"email",
"isOwner",
"lowercaseEmail",
"creationTimestamp",
"lastUpdatedTimestamp",
"lastSeenTimestamp",
"isOauthComplete",
"userKey",
"displayName",
"key"
})
@Data
@ToString(includeFieldNames = false, of = { "key", "name" })
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	@JsonProperty("email")
	public String email;
	
	@JsonProperty("isOwner")
	public Boolean isOwner;
	
	@JsonProperty("lowercaseEmail")
	public String lowercaseEmail;
	
	@JsonProperty("creationTimestamp")
	public Long creationTimestamp;
	
	@JsonProperty("lastUpdatedTimestamp")
	public Long lastUpdatedTimestamp;
	
	@JsonProperty("lastSeenTimestamp")
	public Long lastSeenTimestamp;
	
	@JsonProperty("isOauthComplete")
	public Boolean isOauthComplete;
	
	@JsonProperty("userKey")
	public String userKey;
	
	@JsonProperty("displayName")
	public String displayName;
	
	@JsonProperty("key")
	public String key;
}
