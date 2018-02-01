package com.streak.api;

import java.util.List;

import com.streak.model.Pipeline;
import com.streakapi.crm.exceptions.NoValidObjectsReturned;

public interface IPipelineApi {
	public static final String ALL_PIPELINES = "https://www.streak.com/api/v1/pipelines";
	public static final String PIPELINE_BY_KEY = "https://www.streak.com/api/v1/pipelines/{pipelineKey}";
	
	/**
	 * Retrieves a <List> of all the Pipelines available for the User
	 * 
	 * @return List<{@link Pipeline}>
	 * @see Pipeline
	 * @see <a href="https://streak.readme.io/v9/reference#list-all-pipelines">List all pipelines</a>
	 * @throws NoValidObjectsReturned
	 */
	public List<Pipeline> findAll() throws NoValidObjectsReturned;

	/**
	 * Find a Pipeline based by its ID/key
	 * 
	 * @param pipelineKey
	 *            Pipeline key
	 * @return {@link Pipeline}
	 * 
	 * @see Pipeline
	 * @see <a href="https://streak.readme.io/v9/reference#getting-a-specific-pipeline">Get a pipeline</a>
	 * @throws NoValidObjectsReturned
	 */
	public Pipeline findById(String pipelineKey) throws NoValidObjectsReturned;

	/**
	 * Creates a Pipeline in Streak with a specified Pipeline Name and a Stage Key
	 * information.
	 * 
	 * The following Pipeline attributes need to sent as url-encoded form data parameters
	 * 
	 * <li>name (String)</li>
	 * <li>teamWide (Boolean)</li>
	 * <li>fieldNames (Array of Strings)</li>
	 * <li>fieldTypes (Array of Strings)</li>
	 * <li>stageNames (Array of Strings)</li>
	 * <li>teamKey (String)</li>
	 * 
	 * @param pipeline
	 *            Pipeline pipeline
	 * @return {@link Pipeline}
	 * 
	 * @see Pipeline
	 * @see <a href="https://streak.readme.io/v9/reference#create-a-pipeline">Create a pipeline</a>
	 * @throws NoValidObjectsReturned
	 */
	public Pipeline create(String pipelineName, Boolean teamWide, List<String> fieldNames, List<String> fieldTypes, List<String> stageNames, String teamKey) throws NoValidObjectsReturned;

	/**
	 * Updates an existing Pipeline using its pipelineKey and returns the updated Pipeline object
	 * 
	 * @param pipeline
	 *            Pipeline
	 * @return boolean
	 * @see Pipeline
	 * @see <a href="https://streak.readme.io/v9/reference#edit-a-pipeline">Update a pipeline</a>
	 * @throws NoValidObjectsReturned
	 */
	public Pipeline update(Pipeline pipeline) throws NoValidObjectsReturned;

	/**
	 * Deletes a Pipeline and returns 'true' if successful
	 * 
	 * @param pipelineKey
	 *            Pipeline key
	 * @return boolean
	 * 
	 * @see Pipeline
	 * @see <a href="https://streak.readme.io/v9/reference#delete-a-pipeline">Delete a pipeline</a>
	 * @throws NoValidObjectsReturned
	 */
	public boolean delete(String pipelineKey) throws NoValidObjectsReturned;
}
