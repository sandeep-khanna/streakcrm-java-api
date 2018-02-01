package com.streak.api;

import java.util.Optional;

import com.streak.model.Organization;
import com.streakapi.crm.exceptions.NoValidObjectsReturned;

public interface IOrganizationApi {
	public static final String ALL_ORGANIZATIONS = "https://www.streak.com/api/v1/organizations";
	public static final String ORGANIZATIONS_IN_TEAM = "https://www.streak.com/api/v1/teams/{teamKey}/organizations";
	public static final String ORGANIZATION_BY_KEY = "https://www.streak.com/api/v1/organizations/{organizationKey}";
	public static final String ORGANIZATION_BY_NAME = "https://www.streak.com/api/v1/search?query={organizationName}";
	
	/**
	 * Find an Organization by its Organization ID/key
	 * 
	 * @param organizationKey
	 *            Organization key
	 * @return {@link Organization}
	 * @see Organization
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public Organization findById(String organizationKey) throws NoValidObjectsReturned;

	/**
	 * Find a specific Organization by name
	 * 
	 * @param organizationName
	 *            Organization name
	 * @return {@link Organization}
	 * @see Organization
	 * @see <a href=
	 *      "https://streak.readme.io/v9/reference#searching-for-boxes-contacts-and-organizations">Searching
	 *      for boxes, contacts, and organizations by query</a>
	 * @throws NoValidObjectsReturned
	 */
	public Optional<Organization> findByName(String organizationName) throws NoValidObjectsReturned;

	/**
	 * Creates an Organization within a Pipeline
	 * 
	 * @param teamKey
	 *            Team key
	 * @param organization
	 *            Organization
	 * @param getIfExisting
	 *            If set to true, will check for existing organization based on
	 *            website(s). Including the getIfExisting parameter will return an
	 *            organization with a matching website if it exists, otherwise it
	 *            will create the organization. Either way, the call will return an
	 *            organization. This particular call can only take a teamKey and a
	 *            organization's domains. If you have more information on your
	 *            organization, you'll need to run a separate call to update it.
	 * @return {@link Organization}
	 * @see Organization
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public Organization create(String teamKey, Organization organization, Boolean getIfExisting)
			throws NoValidObjectsReturned;

	/**
	 * Updates an Organization and returns the updated Organization object
	 * 
	 * Note:
	 * 
	 * The only websites associated with the organization will be the ones you
	 * include here; make sure to include any previously associated addresses as
	 * well as the new one(s).
	 * 
	 * The only phone numbers associated with the organization will be the ones you
	 * include here; make sure to include any previously associated numbers as well
	 * as the new one(s).
	 * 
	 * The only addresses associated with the organization will be the ones you
	 * include here; make sure to include any previously associated addresses as
	 * well as the new one(s).
	 * 
	 * @param organizationKey
	 *            Organization key
	 * @param organization
	 *            Organization
	 * @return {@link Organization}
	 * @see Organization
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public Organization update(Organization organization) throws NoValidObjectsReturned;

	/**
	 * Deletes an Organization and returns 'true' if successful
	 * 
	 * @param organizationKey
	 *            Organization key
	 * @return boolean
	 * @see Organization
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public boolean delete(String organizationKey) throws NoValidObjectsReturned;
}
