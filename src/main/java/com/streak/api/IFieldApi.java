package com.streak.api;

import java.util.List;

import com.streak.model.Field;
import com.streakapi.crm.exceptions.NoValidObjectsReturned;

public interface IFieldApi {
	public static final String FIELDS_IN_PIPELINE = "https://www.streak.com/api/v1/pipelines/{pipelineKey}/fields";
	public static final String FIELD_BY_KEY = "https://www.streak.com/api/v1/pipelines/{pipelineKey}/fields/{fieldKey}";
	
	/**
	 * Retrieves a <List> of Fields in a Pipeline
	 * 
	 * @param pipelineKey
	 *            Pipeline key
	 * @return List<{@link Field}>
	 * @see Field
	 * @see <a href="https://streak.readme.io/v9/reference#list-all-fields-in-a-pipeline">List all fields in pipeline</a>
	 * @throws NoValidObjectsReturned
	 */
	public List<Field> findInPipeline(String pipelineKey) throws NoValidObjectsReturned;

	/**
	 * Find a Field based by its ID/key
	 * 
	 * @param fieldKey
	 *            Field key
	 * @return {@link Field}
	 * @see Field
	 * @see <a href="https://streak.readme.io/v9/reference#get-a-field">Get a field</a>
	 * @throws NoValidObjectsReturned
	 */
	public Field findById(String fieldKey) throws NoValidObjectsReturned;

	/**
	 * Creates a Field within a Pipeline
	 * 
	 * @param pipelineKey
	 *            Pipeline key
	 * @param fieldName
	 *            Field name
	 * @param fieldType
	 *            Field type
	 * @return {@link Field}
	 * @see Field
	 * @see <a href="https://streak.readme.io/v9/reference#create-a-field">Create a field</a>
	 * @throws NoValidObjectsReturned
	 */
	public Field create(String pipelineKey, String fieldName, Field.TYPE fieldType) throws NoValidObjectsReturned;

	/**
	 * Updates an existing Field using its fieldKey and returns the updated Field object
	 * 
	 * @param field
	 *            Field
	 * @return boolean
	 * @see Field
	 * @see <a href="https://streak.readme.io/v9/reference#update-a-field">Update a field</a>
	 * @throws NoValidObjectsReturned
	 */
	public Field update(Field field) throws NoValidObjectsReturned;

	/**
	 * Deletes a Field and returns 'true' if successful
	 * 
	 * @param fieldKey
	 *            Field key
	 * @return boolean
	 * @see Field
	 * @see <a href="https://streak.readme.io/v9/reference#delete-a-field">Delete a field</a>
	 * @throws NoValidObjectsReturned
	 */
	public boolean delete(String fieldKey) throws NoValidObjectsReturned;
}
