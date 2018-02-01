package com.streak.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import com.streak.api.ITeamApi;
import com.streak.api.util.ApiClient;
import com.streak.model.Team;
import com.streakapi.crm.exceptions.NoValidObjectsReturned;

@Component
public class TeamApi implements ITeamApi {
	@Autowired
    private ApiClient apiClient;

    /**
	 * {@inheritDoc}
	 */
	@Override
	public List<Team> getCurrentUserTeams() throws NoValidObjectsReturned {
		String path = UriComponentsBuilder.fromHttpUrl(CURRENT_USER_TEAMS).toUriString();
		Object postBody = null;
        MultiValueMap<String, String> queryParams = null;
        MultiValueMap<String, Object> formParams = null;
        ParameterizedTypeReference<List<Team>> returnType = new ParameterizedTypeReference<List<Team>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, formParams, returnType);
	}

    /**
	 * {@inheritDoc}
	 */
	@Override
	public Team findById(String teamKey) throws NoValidObjectsReturned {
		String path = UriComponentsBuilder.fromHttpUrl(TEAM_BY_KEY).buildAndExpand(teamKey).toUriString();
		Object postBody = null;
        MultiValueMap<String, String> queryParams = null;
        MultiValueMap<String, Object> formParams = null;
        ParameterizedTypeReference<Team> returnType = new ParameterizedTypeReference<Team>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, formParams, returnType);
	}
}
