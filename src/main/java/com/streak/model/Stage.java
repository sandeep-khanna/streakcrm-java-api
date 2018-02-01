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
 * Stages are different possible states a box can be in. The list of valid
 * stages a box can be in are defined in the pipeline it belongs to. All boxes
 * in the same pipeline all have the same set of permissible stages. Listing the
 * stages for a given pipeline can be done by getting a specific pipeline or
 * using the list stages enpoint below. To set what stage a given box is in,
 * update the stageKey property of a box using the edit box endpoint.
 * 
 * @see <a href="https://www.streak.com/api/#stage">Streak API</a>
 * 
 * @author Sandeep Khanna
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ 
"name", 
"key" 
})
@Data
@ToString(includeFieldNames = false, of = { "key", "name" })
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stage {
	@JsonProperty("name")
	public String name;
	
	@JsonProperty("key")
	public String key;
}
