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
import lombok.ToString;

/**
 * A Box is the fundamental data type in Streak. What a box represents depends
 * on the context it is being used. For sales, a box could be a deal. For
 * hiring, a box may be a candidate. For bug tracking, a box may be a single
 * bug. A box belongs to a single pipeline. A box contains emails, files, tasks
 * and data for custom fields of a pipeline. It also specifies what stage
 * (defined in its pipeline) this box belongs to.
 *
 * @see <a href="https://www.streak.com/api/#box">Streak API</a>
 * 
 * @author Sandeep Khanna
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@JsonPropertyOrder({
"pipelineKey",
"creatorKey",
"creationTimestamp",
"lastUpdatedTimestamp",
"name",
"stageKey",
"contacts",
"organizations",
"fields",
"followerKeys",
"followerCount",
"commentCount",
"taskTotal",
"gmailThreadCount",
"fileCount",
"boxKey",
"key"
})
@Data
@ToString(includeFieldNames = false, of = { "key", "name" })
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Box {
	@JsonProperty("pipelineKey")
	private String pipelineKey;

	@JsonProperty("creationTimestamp")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy,HH:00:00")
	private Calendar creationTimestamp;

	@JsonProperty("lastUpdatedTimestamp")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy,HH:00:00")
	private Calendar lastUpdatedTimestamp;

	@JsonProperty("name")
	private String name;

	@JsonProperty("stageKey")
	private String stageKey;

	@JsonProperty("boxKey")
	private String boxKey;

	@JsonProperty("key")
	private String key;
	
	@JsonProperty("contacts")
	private List<Contact> contacts;
	
	@JsonProperty("organizations")
	private List<Organization> organizations;
	
	@JsonProperty("fields")
	private List<Field> fields;
}
