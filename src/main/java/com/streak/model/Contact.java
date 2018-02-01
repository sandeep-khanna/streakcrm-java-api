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
 * Contacts are the individual people that you interact with in your business
 * process. You can save profile information for a Contact including phone
 * numbers, email addresses, social media details, and more!
 * 
 * Contacts exist inside Boxes (the centralized location where you save all the
 * emails, files, and details related to a sales lead, hiring candidate, support
 * ticket, etc). You can add more than one Contact to a single Box if (for
 * example) your leads have multiple associated people. And, you can add the
 * same contact to different boxes if a hiring candidate is applying for more
 * than one position in your Hiring Pipeline.
 * 
 * Contacts are automatically shared with your Team. If you have opened your
 * pipelines to guest users, they will not be able to view any Contact
 * information.
 * 
 * @see <a href="https://www.streak.com/api/#contact">Streak API</a>
 * 
 * @author Sandeep Khanna
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@JsonPropertyOrder({
"teamKey",
"givenName",
"familyName",
"title",
"emailAddresses",
"phoneNumbers",
"addresses",
"lastEnrichmentTimestamp",
"lastSavedUserKey",
"creatorKey",
"twitterHandle",
"creationDate",
"key",
"lastSavedTimestamp"
})
@Data
@ToString(includeFieldNames = false, of = { "key", "givenName", "familyName", "emailAddresses", "phoneNumbers" })
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contact {
	@JsonProperty("teamKey")
	@Builder.Default
	private String teamKey = null;

	@JsonProperty("key")
	@Builder.Default
	private String key = null;

	@JsonProperty("givenName")
	@NonNull
	@Builder.Default
	private String givenName = null;

	@JsonProperty("familyName")
	@NonNull
	@Builder.Default
	private String familyName = null;

	@JsonProperty("title")
	@NonNull
	@Builder.Default
	private String title = null;

	@JsonProperty("emailAddresses")
	@Builder.Default
	private List<String> emailAddresses = null;

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
