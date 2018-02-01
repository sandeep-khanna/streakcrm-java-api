package com.streak.model;

import java.util.Calendar;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

/**
 * All Organizations are scoped to a particular team so a teamKey is required.
 * When creating an organization you can provide all of the organization info in
 * a single call. See the possible parameters below.
 * 
 * Organizations must have at least one of Name or Domains set.
 *
 * @see <a href="https://www.streak.com/api/">Streak API</a>
 * 
 * @author Sandeep Khanna
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@JsonPropertyOrder({
"teamKey",
"name",
"other",
"domains",
"industry",
"phoneNumbers",
"addresses",
"employeeCount",
"logoURL",
"twitterHandle",
"facebookHandle",
"linkedinHandle",
"lastSavedUserKey",
"creatorKey",
"creationDate",
"key",
"versionTimestamp",
"lastSavedTimestamp"
})
@Data
@ToString(includeFieldNames = false, of = { "key", "name" })
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Organization {
	@JsonProperty("teamKey")
	@Builder.Default
	private String teamId = null;

	@JsonProperty("key")
	@Builder.Default
	private String key = null;

	@JsonProperty("name")
	@NonNull
	@Builder.Default
	private String name = null;

	@JsonProperty("other")
	@Builder.Default
	private String description = null;

	@JsonProperty("domains")
	@Builder.Default
	private List<String> domains = null;

	@JsonProperty("phoneNumbers")
	@Builder.Default
	private List<String> phoneNumbers = null;

	@JsonProperty("addresses")
	@Builder.Default
	private List<String> addresses = null;

	@JsonProperty("creationDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy,HH:00:00")
	private Calendar creationTimestamp;

	@JsonProperty("lastSavedTimestamp")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy,HH:00:00")
	private Calendar lastUpdatedTimestamp;
}
