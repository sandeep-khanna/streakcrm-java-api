package com.streak.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

/**
 * SearchResults includes Boxes, Contacts and Organizations
 * 
 * @see <a href="https://www.streak.com/api/#search">Streak API</a>
 * 
 * @author Sandeep Khanna
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "boxes", "orgs", "contacts" })
@Data
public class SearchResults {
	@JsonProperty("boxes")
	public List<Box> boxes = null;
	@JsonProperty("orgs")
	public List<Organization> organizations = null;
	@JsonProperty("contacts")
	public List<Contact> contacts = null;
}
