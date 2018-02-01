package com.streak.api.impl;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.apache.commons.lang3.BooleanUtils;
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

import com.streak.api.IOrganizationApi;
import com.streak.api.util.ApiClient;
import com.streak.model.Organization;
import com.streak.model.SearchResults;
import com.streakapi.crm.exceptions.NoValidObjectsReturned;

@Component
public class OrganizationApi implements IOrganizationApi {
	@Autowired
    private ApiClient apiClient;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Organization findById(String organizationKey) throws NoValidObjectsReturned {
        String path = UriComponentsBuilder.fromHttpUrl(ORGANIZATION_BY_KEY).buildAndExpand(organizationKey).toUriString();
        Object postBody = null;
        MultiValueMap<String, String> queryParams = null;
        MultiValueMap<String, Object> formParams = null;
        ParameterizedTypeReference<Organization> returnType = new ParameterizedTypeReference<Organization>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, formParams, returnType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<Organization> findByName(String organizationName) throws NoValidObjectsReturned {
        String path = UriComponentsBuilder.fromHttpUrl(ORGANIZATION_BY_NAME).buildAndExpand(organizationName).toUriString();
		Object postBody = null;
        MultiValueMap<String, String> queryParams = null;
        MultiValueMap<String, Object> formParams = null;
        ParameterizedTypeReference<SearchResults> returnType = new ParameterizedTypeReference<SearchResults>() {};
        SearchResults searchResults = apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, formParams, returnType);
        Predicate<Organization> organizationNameMatches = org -> StringUtils.equalsIgnoreCase(organizationName, org.getName());
        return Stream.ofNullable(searchResults.getOrganizations())
        				.flatMap(Collection::stream)
        				.filter(organizationNameMatches)
        				.findFirst();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Organization create(String teamKey, Organization organization, Boolean getIfExisting)
			throws NoValidObjectsReturned {
		String path = UriComponentsBuilder.fromHttpUrl(ORGANIZATIONS_IN_TEAM).buildAndExpand(teamKey).toUriString();
	    Object postBody = null;
	    MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
	    MultiValueMap<String, Object> formParams = null;
	    
	    if(BooleanUtils.toBoolean(getIfExisting)) {
	    	queryParams.add("getIfExisting", String.valueOf(getIfExisting));
	    }
	    ParameterizedTypeReference<Organization> returnType = new ParameterizedTypeReference<Organization>() {};
	    return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, formParams, returnType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Organization update(Organization organization) throws NoValidObjectsReturned {
		
        if ( null == organization 
        		|| StringUtils.isBlank(organization.getKey()) ) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Organization object containing a organizationKey is required to update an Organization.");
        }
        String path = UriComponentsBuilder.fromHttpUrl(ORGANIZATION_BY_KEY).buildAndExpand(organization.getKey()).toUriString();
        Object postBody = organization;
        MultiValueMap<String, String> queryParams = null;
        MultiValueMap<String, Object> formParams = null;
        ParameterizedTypeReference<Organization> returnType = new ParameterizedTypeReference<Organization>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, formParams, returnType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean delete(String organizationKey) throws NoValidObjectsReturned {
        String path = UriComponentsBuilder.fromHttpUrl(ORGANIZATION_BY_KEY).buildAndExpand(organizationKey).toUriString();
		Object postBody = null;
        MultiValueMap<String, String> queryParams = null;
        MultiValueMap<String, Object> formParams = null;
        ParameterizedTypeReference<Boolean> returnType = new ParameterizedTypeReference<Boolean>() {};
        return apiClient.invokeAPI(path, HttpMethod.DELETE, queryParams, postBody, formParams, returnType);
	}
}
