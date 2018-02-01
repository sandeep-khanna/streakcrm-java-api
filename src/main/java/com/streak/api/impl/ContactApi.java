package com.streak.api.impl;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
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

import com.streak.api.IContactApi;
import com.streak.api.util.ApiClient;
import com.streak.model.Contact;
import com.streak.model.SearchResults;
import com.streakapi.crm.exceptions.NoValidObjectsReturned;

@Component
public class ContactApi implements IContactApi {
	@Autowired
	private ApiClient apiClient;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Contact findById(String contactKey) throws NoValidObjectsReturned {
		String path = UriComponentsBuilder.fromHttpUrl(CONTACT_BY_KEY).buildAndExpand(contactKey).toUriString();
		Object postBody = null;
		MultiValueMap<String, String> queryParams = null;
		MultiValueMap<String, Object> formParams = null;
		ParameterizedTypeReference<Contact> returnType = new ParameterizedTypeReference<Contact>() {};
		return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, formParams, returnType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Contact> findByEmail(String email) throws NoValidObjectsReturned {
		String path = UriComponentsBuilder.fromHttpUrl(CONTACT_BY_NAME_OR_EMAIL).buildAndExpand(email)
				.toUriString();
		Object postBody = null;
		MultiValueMap<String, String> queryParams = null;
		MultiValueMap<String, Object> formParams = null;
		ParameterizedTypeReference<SearchResults> returnType = new ParameterizedTypeReference<SearchResults>() {};
		SearchResults searchResults = apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, formParams, returnType);
		Predicate<Contact> contactEmailMatches = contact -> {
			return contact.getEmailAddresses()
							.stream()
							.anyMatch(emailAddress -> StringUtils.equals(email, emailAddress));
		};
		return Stream.ofNullable(searchResults.getContacts())
						.flatMap(Collection::stream)
						.filter(contactEmailMatches)
						.collect(Collectors.toList());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Contact> findByName(String name) throws NoValidObjectsReturned {
		String path = UriComponentsBuilder.fromHttpUrl(CONTACT_BY_NAME_OR_EMAIL).buildAndExpand(name)
				.toUriString();
		Object postBody = null;
		MultiValueMap<String, String> queryParams = null;
		MultiValueMap<String, Object> formParams = null;
		ParameterizedTypeReference<SearchResults> returnType = new ParameterizedTypeReference<SearchResults>() {};
		SearchResults searchResults = apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, formParams, returnType);
		Predicate<Contact> contactNameMatches = contact -> {
			return StringUtils.equalsIgnoreCase(name, StringUtils.joinWith(StringUtils.SPACE, contact.getGivenName(), contact.getFamilyName()));
		};
		return Stream.ofNullable(searchResults.getContacts())
						.flatMap(Collection::stream)
						.filter(contactNameMatches)
						.collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Contact create(String teamKey, String emailAddress, Boolean getIfExisting) throws NoValidObjectsReturned {
		String path = UriComponentsBuilder.fromHttpUrl(CONTACTS_IN_TEAM).buildAndExpand(teamKey).toUriString();
		Object postBody = Contact.builder().emailAddresses(List.of(emailAddress)).build();
		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
		MultiValueMap<String, Object> formParams = null;

		if (BooleanUtils.toBoolean(getIfExisting)) {
			queryParams.add("getIfExisting", String.valueOf(getIfExisting));
		}
		ParameterizedTypeReference<Contact> returnType = new ParameterizedTypeReference<Contact>() {};
		return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, formParams, returnType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Contact create(String teamKey, Contact contact, Boolean getIfExisting) throws NoValidObjectsReturned {
		String path = UriComponentsBuilder.fromHttpUrl(CONTACTS_IN_TEAM).buildAndExpand(teamKey).toUriString();
		Object postBody = null;
		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
		MultiValueMap<String, Object> formParams = null;

		if (BooleanUtils.toBoolean(getIfExisting)) {
			queryParams.add("getIfExisting", String.valueOf(getIfExisting));
		}
		ParameterizedTypeReference<Contact> returnType = new ParameterizedTypeReference<Contact>() {};
		return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, formParams, returnType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Contact update(Contact contact) throws NoValidObjectsReturned {

		if (null == contact || StringUtils.isBlank(contact.getKey())) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
					"Contact object containing a boxKey is required to update a Contact.");
		}
		String path = UriComponentsBuilder.fromHttpUrl(CONTACT_BY_KEY).buildAndExpand(contact.getKey()).toUriString();
		Object postBody = contact;
		MultiValueMap<String, String> queryParams = null;
		MultiValueMap<String, Object> formParams = null;
		ParameterizedTypeReference<Contact> returnType = new ParameterizedTypeReference<Contact>() {};
		return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, formParams, returnType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean delete(String contactKey) throws NoValidObjectsReturned {
		String path = UriComponentsBuilder.fromHttpUrl(CONTACT_BY_KEY).buildAndExpand(contactKey).toUriString();
		Object postBody = null;
		MultiValueMap<String, String> queryParams = null;
		MultiValueMap<String, Object> formParams = null;
		ParameterizedTypeReference<Boolean> returnType = new ParameterizedTypeReference<Boolean>() {};
		return apiClient.invokeAPI(path, HttpMethod.DELETE, queryParams, postBody, formParams, returnType);
	}
}
