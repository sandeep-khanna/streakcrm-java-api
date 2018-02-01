package com.streak.api;

import java.util.List;
import java.util.Optional;

import com.streak.model.Box;
import com.streak.model.Contact;
import com.streak.model.Field;
import com.streak.model.Member;
import com.streak.model.Organization;
import com.streak.model.SearchResults;
import com.streakapi.crm.exceptions.NoValidObjectsReturned;

public interface IBoxApi {
	public static final String ALL_BOXES = "https://www.streak.com/api/v1/boxes";
	public static final String BOXES_IN_PIPELINE = "https://www.streak.com/api/v1/pipelines/{pipelineKey}/boxes";
	public static final String BOX_BY_KEY = "https://www.streak.com/api/v1/boxes/{boxKey}";
	public static final String BOX_BY_NAME = "https://www.streak.com/api/v1/search?name={boxName}";
	public static final String FIELDS_BY_BOX_KEY = "https://www.streak.com/api/v1/boxes/{boxKey}/fields";
	public static final String FIELD_BY_FIELD_KEY = "https://www.streak.com/api/v1/boxes/{boxKey}/fields/{fieldKey}";
	
	/**
	 * Retrieves a <List> of all the Boxes available for the User
	 * 
	 * @return List<{@link Box}>
	 * @see Box
	 * @see <a href="https://streak.readme.io/v9/reference#list-all-boxes-in-pipeline">List all boxes in pipeline</a>
	 * @throws NoValidObjectsReturned
	 */
	public List<Box> findAll() throws NoValidObjectsReturned;

	/**
	 * Retrieves a <List> of Boxes in a Pipeline
	 * 
	 * @param pipelineKey
	 *            Pipeline key
	 * @return List<{@link Box}>
	 * 
	 * @see Box
	 * @see <a href="https://streak.readme.io/reference#list-all-boxes-in-pipeline">List all boxes in pipeline</a>
	 * @throws NoValidObjectsReturned
	 */
	public List<Box> findInPipeline(String pipelineKey) throws NoValidObjectsReturned;

	/**
	 * Find a Box based by its ID/key
	 * 
	 * @param boxKey
	 *            Box key
	 * @return {@link Box}
	 * 
	 * @see Box
	 * @see <a href="https://streak.readme.io/v9/reference#get-a-box">Get a box</a>
	 * @throws NoValidObjectsReturned
	 */
	public Box findById(String boxKey) throws NoValidObjectsReturned;

	/**
	 * Find a Box by name
	 * 
	 * @param boxKey
	 *            Box name
	 * @return {@link Box}
	 * 
	 * @see Box
	 * @see <a href=
	 *      "https://streak.readme.io/v9/reference#searchng-for-boxes-by-name">Searching
	 *      for a box by name</a>
	 * @throws NoValidObjectsReturned
	 */
	public Optional<Box> findByName(String boxName) throws NoValidObjectsReturned;

	/**
	 * Creates a Box in Streak.
	 * 
	 * The following Box attributes need to sent as url-encoded form data parameters
	 * 
	 * <li>name (String)</li>
	 * <li>stageKey (String)</li>
	 * <li>notes (String)</li>
	 * <li>assignedToSharingEntries (Array of Member objects)</li>
	 * 
	 * @param pipelineKey
	 *            Pipeline key
	 * @param boxName
	 *            Box name
	 * @param stageKey
	 *            Stage key
	 * @param notes
	 *            String notes on the box
	 * @param members
	 *            List<Member> The team member(s) this box will be assigned to
	 * @return {@link Box}
	 * 
	 * @see Box
	 * @see Member
	 * @see <a href="https://streak.readme.io/v9/reference#create-a-box">Create a box</a>
	 * @throws NoValidObjectsReturned
	 */
	public Box create(String pipelineKey, String boxName, String stageKey, String notes, List<Member> members) throws NoValidObjectsReturned;

	/**
	 * Creates a Box within a Pipeline
	 * 
	 * The following Box attributes need to sent as url-encoded form data parameters
	 * 
	 * <li>name (String)</li>
	 * 
	 * @param pipelineKey
	 *            Pipeline key
	 * @param boxName
	 *            Box name
	 * @return {@link Box}
	 * 
	 * @see Box
	 * @see <a href="https://streak.readme.io/v9/reference#create-a-box">Create a box</a>
	 * @throws NoValidObjectsReturned
	 */
	public Box create(String pipelineKey, String boxName) throws NoValidObjectsReturned;

	/**
	 * Updates an existing Box using its boxKey and returns the updated Box object
	 * 
	 * @param box
	 *            Box
	 * @return boolean
	 * @see Box
	 * @see <a href="https://streak.readme.io/v9/reference#edit-a-box">Update a box</a>
	 * @throws NoValidObjectsReturned
	 */
	public Box update(Box box) throws NoValidObjectsReturned;

	/**
	 * Deletes a Box and returns 'true' if successful
	 * 
	 * @param boxKey
	 *            Box key
	 * @return boolean
	 * 
	 * @see Box
	 * @see <a href="https://streak.readme.io/v9/reference#delete-a-box">Delete a box</a>
	 * @throws NoValidObjectsReturned
	 */
	public boolean delete(String boxKey) throws NoValidObjectsReturned;

	/**
	 * Adds Contacts to a Box
	 * 
	 * @param boxKey
	 *            Box key
	 * @param contacts
	 *            The only contacts associated with the box will be the ones you
	 *            include here; make sure to include any previously associated
	 *            contacts as well as the new one(s).
	 * @return SearchResults
	 * 
	 * @see Contact
	 * @see SearchResults
	 * @see <a href=
	 *      "https://streak.readme.io/v9/reference#adding-multiple-contacts-to-a-box">Add
	 *      contact(s) to a box</a>
	 * @throws NoValidObjectsReturned
	 */
	public SearchResults addContacts(String boxKey, List<Contact> contacts) throws NoValidObjectsReturned;
	
	/**
	 * Adds Organizations to a Box
	 * 
	 * @param boxKey
	 *            Box key
	 * @param organizations
	 *            The only organizations associated with the box will be the ones
	 *            you include here; make sure to include any previously associated
	 *            organizations as well as the new one(s).
	 * @return SearchResults
	 * 
	 * @see Organization
	 * @see SearchResults
	 * @see <a href=
	 *      "https://streak.readme.io/v9/reference#add-organizations-to-box">Add
	 *      organization(s) to box</a>
	 * @throws NoValidObjectsReturned
	 */
	public SearchResults addOrganizations(String boxKey, List<Organization> organizations) throws NoValidObjectsReturned;
	
	/**
	 * Retrieves a <List> of all Fields for the specified Box
	 * 
	 * @param boxKey
	 *            Box key
	 *
	 * @return List<{@link Field}>
	 * 
	 * @see Field
	 * @see <a href=
	 *      "https://streak.readme.io/v9/reference#list-field-values-for-a-box">List field values for a box</a>
	 * @throws NoValidObjectsReturned
	 */
	public List<Field> getFields(String boxKey) throws NoValidObjectsReturned;
	
	/**
	 * Retrieves a specific Field for the specified Box
	 * 
	 * @param boxKey
	 *            Box key
	 * @param fieldKey
	 *            Field key
	 *
	 * @return {@link Field}
	 * 
	 * @see Field
	 * @see <a href=
	 *      "https://streak.readme.io/v9/reference#get-a-field-value-for-a-box">Get a field value</a>
	 * @throws NoValidObjectsReturned
	 */
	public Field getField(String boxKey, String fieldKey) throws NoValidObjectsReturned;
	
	/**
	 * Updates a specific Field for the specified Box
	 * 
	 * @param boxKey
	 *            Box key
	 * @param field
	 *            Field
	 *
	 * @return {@link Field}
	 * 
	 * @see Field
	 * @see <a href=
	 *      "https://streak.readme.io/v9/reference#update-field-value-for-a-box">Update field value</a>
	 * @throws NoValidObjectsReturned
	 */
	public Field updateField(String boxKey, Field field) throws NoValidObjectsReturned;
}
