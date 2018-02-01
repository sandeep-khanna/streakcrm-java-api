package com.streak.api.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.UriComponentsBuilder;

import com.streak.api.IBoxApi;
import com.streak.api.util.ApiClient;
import com.streak.model.Box;
import com.streak.model.Contact;
import com.streak.model.Field;
import com.streak.model.Member;
import com.streak.model.Organization;
import com.streak.model.SearchResults;
import com.streakapi.crm.exceptions.NoValidObjectsReturned;

@Component
public class BoxApi implements IBoxApi {
	@Autowired
    private ApiClient apiClient;
	
    /**
	 * {@inheritDoc}
	 */
	@Override
	public List<Box> findAll() throws NoValidObjectsReturned {
        String path = ALL_BOXES;
        Object postBody = null;
        MultiValueMap<String, String> queryParams = null;
        MultiValueMap<String, Object> formParams = null;
        ParameterizedTypeReference<List<Box>> returnType = new ParameterizedTypeReference<List<Box>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, formParams, returnType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Box> findInPipeline(String pipelineKey) throws NoValidObjectsReturned {
        String path = UriComponentsBuilder.fromHttpUrl(BOXES_IN_PIPELINE).buildAndExpand(pipelineKey).toUriString();
        Object postBody = null;
        MultiValueMap<String, String> queryParams = null;
        MultiValueMap<String, Object> formParams = null;
        ParameterizedTypeReference<List<Box>> returnType = new ParameterizedTypeReference<List<Box>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, formParams, returnType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Box findById(String boxKey) throws NoValidObjectsReturned {
        String path = UriComponentsBuilder.fromHttpUrl(BOX_BY_KEY).buildAndExpand(boxKey).toUriString();
		Object postBody = null;
        MultiValueMap<String, String> queryParams = null;
        MultiValueMap<String, Object> formParams = null;
        ParameterizedTypeReference<Box> returnType = new ParameterizedTypeReference<Box>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, formParams, returnType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<Box> findByName(String boxName) throws NoValidObjectsReturned {
        String path = UriComponentsBuilder.fromHttpUrl(BOX_BY_NAME).buildAndExpand(boxName).toUriString();
		Object postBody = null;
        MultiValueMap<String, String> queryParams = null;
        MultiValueMap<String, Object> formParams = null;
        ParameterizedTypeReference<SearchResults> returnType = new ParameterizedTypeReference<SearchResults>() {};
        SearchResults searchResults = apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, formParams, returnType);
        Predicate<Box> boxNameMatches = box -> StringUtils.equalsIgnoreCase(boxName, box.getName());
        return Stream.ofNullable(searchResults.getBoxes())
        				.flatMap(Collection::stream)
        				.filter(boxNameMatches)
        				.findFirst();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Box create(String pipelineKey, String boxName, String stageKey, String notes, List<Member> members) throws NoValidObjectsReturned {
        String path = UriComponentsBuilder.fromHttpUrl(BOXES_IN_PIPELINE).buildAndExpand(pipelineKey).toUriString();
        Object postBody = null;
        MultiValueMap<String, String> queryParams = null;
        MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
        formParams.add("name", boxName);
        formParams.add("stageKey", stageKey);
        formParams.add("notes", notes);
//        formParams.add("assignedToSharingEntries", members); // TODO members need to be an array of JSON objects
        ParameterizedTypeReference<Box> returnType = new ParameterizedTypeReference<Box>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, formParams, returnType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Box create(String pipelineKey, String boxName) throws NoValidObjectsReturned {
		return create(pipelineKey, boxName, null, null, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Box update(Box box) throws NoValidObjectsReturned {
		
        if ( null == box 
        		|| StringUtils.isBlank(box.getKey()) ) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Box object containing a boxKey is required to update a Box.");
        }
        String path = UriComponentsBuilder.fromHttpUrl(BOX_BY_KEY).buildAndExpand(box.getBoxKey()).toUriString();
        Object postBody = box;
        MultiValueMap<String, String> queryParams = null;
        MultiValueMap<String, Object> formParams = null;
        ParameterizedTypeReference<Box> returnType = new ParameterizedTypeReference<Box>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, formParams, returnType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean delete(String boxKey) throws NoValidObjectsReturned {
        String path = UriComponentsBuilder.fromHttpUrl(BOX_BY_KEY).buildAndExpand(boxKey).toUriString();
		Object postBody = null;
        MultiValueMap<String, String> queryParams = null;
        MultiValueMap<String, Object> formParams = null;
        ParameterizedTypeReference<Boolean> returnType = new ParameterizedTypeReference<Boolean>() {};
        return apiClient.invokeAPI(path, HttpMethod.DELETE, queryParams, postBody, formParams, returnType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SearchResults addContacts(String boxKey, List<Contact> contacts) throws NoValidObjectsReturned {
		String path = UriComponentsBuilder.fromHttpUrl(BOX_BY_KEY).buildAndExpand(boxKey).toUriString();
		Object postBody = Box.builder().boxKey(boxKey).contacts(contacts).build();
        MultiValueMap<String, String> queryParams = null;
        MultiValueMap<String, Object> formParams = null;
        ParameterizedTypeReference<SearchResults> returnType = new ParameterizedTypeReference<SearchResults>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, formParams, returnType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SearchResults addOrganizations(String boxKey, List<Organization> organizations)
			throws NoValidObjectsReturned {
		String path = UriComponentsBuilder.fromHttpUrl(BOX_BY_KEY).buildAndExpand(boxKey).toUriString();
		Object postBody = Box.builder().boxKey(boxKey).organizations(organizations).build();
        MultiValueMap<String, String> queryParams = null;
        MultiValueMap<String, Object> formParams = null;
        ParameterizedTypeReference<SearchResults> returnType = new ParameterizedTypeReference<SearchResults>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, formParams, returnType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Field> getFields(String boxKey) throws NoValidObjectsReturned {
        String path = UriComponentsBuilder.fromHttpUrl(FIELDS_BY_BOX_KEY).buildAndExpand(boxKey).toUriString();
        Object postBody = null;
        MultiValueMap<String, String> queryParams = null;
        MultiValueMap<String, Object> formParams = null;
        ParameterizedTypeReference<List<Field>> returnType = new ParameterizedTypeReference<List<Field>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, formParams, returnType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field getField(String boxKey, String fieldKey) throws NoValidObjectsReturned {
		String path = UriComponentsBuilder.fromHttpUrl(FIELD_BY_FIELD_KEY).buildAndExpand(boxKey, fieldKey).toUriString();
        Object postBody = null;
        MultiValueMap<String, String> queryParams = null;
        MultiValueMap<String, Object> formParams = null;
        ParameterizedTypeReference<Field> returnType = new ParameterizedTypeReference<Field>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, formParams, returnType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field updateField(String boxKey, Field field) throws NoValidObjectsReturned {

		if ( null == field 
        		|| StringUtils.isBlank(field.getKey()) ) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Field object containing a fieldKey is required to update a Field.");
        }
        String path = UriComponentsBuilder.fromHttpUrl(FIELD_BY_FIELD_KEY).buildAndExpand(boxKey, field.getKey()).toUriString();
        Object postBody = field;
        MultiValueMap<String, String> queryParams = null;
        MultiValueMap<String, Object> formParams = null;
        ParameterizedTypeReference<Field> returnType = new ParameterizedTypeReference<Field>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, formParams, returnType);
	}
}
