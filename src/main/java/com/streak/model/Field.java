package com.streak.model;

import java.util.Calendar;

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
 * Fields allow users to define custom schema on their boxes.
 * 
 * That is, a field is user defined metadata on a box. In the web UI of Streak,
 * a field shows up as an additional column in the pipeline view and box views.
 * Fields are defined on a per pipeline basis. This means that all boxes in the
 * same pipeline have the same custom fields (schema).
 * 
 * Fields have a name and a type. The type can be any of: TEXT_INPUT, DATE, TAG,
 * FORMULA, DROPDOWN, CHECKBOX, or `TEAM_CONTACT'.
 * 
 * @see <a href="https://www.streak.com/api/#field">Streak API</a>
 * 
 * @author Sandeep Khanna
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@JsonPropertyOrder({
"name",
"key",
"type"
})
@Data
@ToString(includeFieldNames = false, of = { "key", "name" })
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Field {

	public enum TYPE {
		TEXT_INPUT, DATE, TAG, FORMULA, DROPDOWN, CHECKBOX, TEAM_CONTACT
	};

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("key")
	private String key;

	@JsonProperty("type")
	private TYPE type;
	
	@JsonProperty("lastUpdatedTimestamp")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy,HH:00:00")
	private Calendar lastUpdatedTimestamp;
}
