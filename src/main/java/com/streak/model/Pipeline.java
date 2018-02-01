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

/**
 * Pipelines are a core data object in Streak. They represent a business process
 * that a user or set of users would like managed. Pipelines are typically used
 * for Sales, Hiring, Product Development, Bug Tracking, Project Management,
 * Fundraising, Dealflow and others.
 * 
 * A pipeline defines the schema for the boxes it contains. It defines the set
 * of stages that contained boxes can be in as well as the set of custom fields.
 * A pipeline is created by a single user but can be shared to other users or to
 * an entire organization.
 * 
 * Pipelines have several fields that describe the schema of the pipeline and
 * hence describe the schema of any boxes contained within it.
 * 
 * @see <a href="https://www.streak.com/api/#pipeline">Streak API</a>
 * 
 * @author Sandeep Khanna
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"creatorKey",
"name",
"description",
"orgWide",
"fields",
"stages",
"stageOrder",
"creationTimestamp",
"lastUpdatedTimestamp",
"aclEntries",
"owner",
"pipelineKey",
"key"
})
@Data
@ToString(includeFieldNames = false, of = { "key", "name" })
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pipeline {
	@JsonProperty("creatorKey")
	public String creatorKey;

	@JsonProperty("name")
	public String name;
	
	@JsonProperty("description")
	public String description;
	
	@JsonProperty("orgWide")
	public Boolean orgWide;
	
	@JsonProperty("fields")
	@Valid
	public List<Field> fields = null;
	
	@JsonProperty("stages")
	@Valid
	public List<Stage> stages;
	
	@JsonProperty("stageOrder")
	@Valid
	public List<String> stageOrder = null;
	
	@JsonProperty("creationTimestamp")
	public Long creationTimestamp;
	
	@JsonProperty("lastUpdatedTimestamp")
	public Long lastUpdatedTimestamp;
	
	@JsonProperty("aclEntries")
	@Valid
	public List<Object> aclEntries = null;
	
	@JsonProperty("owner")
	@Valid
	public User owner;
	
	@JsonProperty("pipelineKey")
	public String pipelineKey;
	
	@JsonProperty("key")
	public String key;
}
