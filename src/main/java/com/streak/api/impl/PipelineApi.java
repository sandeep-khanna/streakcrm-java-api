package com.streak.api.impl;

import java.util.List;

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

import com.streak.api.IPipelineApi;
import com.streak.api.util.ApiClient;
import com.streak.model.Box;
import com.streak.model.Pipeline;
import com.streakapi.crm.exceptions.NoValidObjectsReturned;

@Component
public class PipelineApi implements IPipelineApi {
	@Autowired
    private ApiClient apiClient;

    /**
	 * {@inheritDoc}
	 */
	@Override
	public List<Pipeline> findAll() throws NoValidObjectsReturned {
		String path = ALL_PIPELINES;
        Object postBody = null;
        MultiValueMap<String, String> queryParams = null;
        MultiValueMap<String, Object> formParams = null;
        ParameterizedTypeReference<List<Pipeline>> returnType = new ParameterizedTypeReference<List<Pipeline>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, formParams, returnType);
	}

    /**
	 * {@inheritDoc}
	 */
	@Override
	public Pipeline findById(String pipelineKey) throws NoValidObjectsReturned {
        String path = UriComponentsBuilder.fromHttpUrl(PIPELINE_BY_KEY).buildAndExpand(pipelineKey).toUriString();
        Object postBody = null;
        MultiValueMap<String, String> queryParams = null;
        MultiValueMap<String, Object> formParams = null;
        ParameterizedTypeReference<Pipeline> returnType = new ParameterizedTypeReference<Pipeline>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, formParams, returnType);
	}

    /**
	 * {@inheritDoc}
	 */
	@Override
	public Pipeline create(String pipelineName, Boolean teamWide, List<String> fieldNames, List<String> fieldTypes, List<String> stageNames, String teamKey) throws NoValidObjectsReturned {
        String path = UriComponentsBuilder.fromHttpUrl(ALL_PIPELINES).toUriString();
        Object postBody = null;
        MultiValueMap<String, String> queryParams = null;
        MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
        formParams.add("name", pipelineName);
        formParams.add("teamWide", teamWide);
        formParams.add("fieldNames", fieldNames.toArray());
        formParams.add("fieldTypes", fieldTypes.toArray());
        formParams.add("stageNames", stageNames.toArray());
        formParams.add("teamKey", teamKey);
        ParameterizedTypeReference<Pipeline> returnType = new ParameterizedTypeReference<Pipeline>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, formParams, returnType);
	}

    /**
	 * {@inheritDoc}
	 */
	@Override
	public Pipeline update(Pipeline pipeline) throws NoValidObjectsReturned {
		
        if ( null == pipeline 
        		|| StringUtils.isBlank(pipeline.getKey()) ) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Box object containing a boxKey is required to update a Box.");
        }
        String path = UriComponentsBuilder.fromHttpUrl(PIPELINE_BY_KEY).buildAndExpand(pipeline.getKey()).toUriString();
        Object postBody = pipeline;
        MultiValueMap<String, String> queryParams = null;
        MultiValueMap<String, Object> formParams = null;
        ParameterizedTypeReference<Pipeline> returnType = new ParameterizedTypeReference<Pipeline>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, formParams, returnType);
	}

    /**
	 * {@inheritDoc}
	 */
	@Override
	public boolean delete(String pipelineKey) throws NoValidObjectsReturned {
        String path = UriComponentsBuilder.fromHttpUrl(PIPELINE_BY_KEY).buildAndExpand(pipelineKey).toUriString();
		Object postBody = null;
        MultiValueMap<String, String> queryParams = null;
        MultiValueMap<String, Object> formParams = null;
        ParameterizedTypeReference<Boolean> returnType = new ParameterizedTypeReference<Boolean>() {};
        return apiClient.invokeAPI(path, HttpMethod.DELETE, queryParams, postBody, formParams, returnType);
	}
}
