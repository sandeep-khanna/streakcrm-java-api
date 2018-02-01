package com.streak.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import com.streak.api.IUserApi;
import com.streak.api.util.ApiClient;
import com.streak.model.User;
import com.streakapi.crm.exceptions.NoValidObjectsReturned;

@Component
public class UserApi implements IUserApi {
	@Autowired
    private ApiClient apiClient;

    /**
	 * {@inheritDoc}
	 */
	@Override
	public User getCurrentUser() throws NoValidObjectsReturned {
		String path = UriComponentsBuilder.fromHttpUrl(CURRENT_USER).toUriString();
		Object postBody = null;
        MultiValueMap<String, String> queryParams = null;
        MultiValueMap<String, Object> formParams = null;
        ParameterizedTypeReference<User> returnType = new ParameterizedTypeReference<User>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, formParams, returnType);
	}

    /**
	 * {@inheritDoc}
	 */
	@Override
	public User findById(String userKey) throws NoValidObjectsReturned {
		String path = UriComponentsBuilder.fromHttpUrl(USER_BY_KEY).buildAndExpand(userKey).toUriString();
		Object postBody = null;
        MultiValueMap<String, String> queryParams = null;
        MultiValueMap<String, Object> formParams = null;
        ParameterizedTypeReference<User> returnType = new ParameterizedTypeReference<User>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, formParams, returnType);
	}
}
