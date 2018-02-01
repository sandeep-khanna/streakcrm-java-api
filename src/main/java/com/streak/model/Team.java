package com.streak.model;

import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"creationDate",
"creator",
"members",
"name",
"sharingRestrictedToTeam",
"key",
"lastSavedTimestamp"
})
@Data
@ToString(includeFieldNames = false, of = { "key", "name" })
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Team {
	@JsonProperty("creationDate")
	public Long creationDate;
	
	@JsonProperty("creator")
	public String creator;
	
	@JsonProperty("members")
	@Valid
	public List<Member> members = null;
	
	@JsonProperty("name")
	public String name;
	
	@JsonProperty("sharingRestrictedToTeam")
	public Boolean sharingRestrictedToTeam;
	
	@JsonProperty("key")
	public String key;
	
	@JsonProperty("lastSavedTimestamp")
	public Long lastSavedTimestamp;
}
