package com.streak.api;

import java.util.List;

import com.streak.model.Contact;
import com.streakapi.crm.exceptions.NoValidObjectsReturned;

public interface IContactApi {
	public static final String CONTACTS_IN_TEAM = "https://www.streak.com/api/v1/teams/{teamKey}/contacts";
	public static final String CONTACT_BY_KEY = "https://www.streak.com/api/v1/organizations/{contactKey}";
	public static final String CONTACT_BY_NAME_OR_EMAIL = "https://www.streak.com/api/v1/search?query={nameOrEmail}";

	/**
	 * Find a Contact by its ID/key
	 * 
	 * @param contactKey
	 *            Contact key
	 * @return {@link Contact}
	 * @see Contact
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public Contact findById(String contactKey) throws NoValidObjectsReturned;

	/**
	 * Find a Contact by email address
	 * 
	 * @param email
	 *            Contact email address
	 * @return {@link Contact}
	 * @see Contact
	 * @see <a href=
	 *      "https://streak.readme.io/v9/reference#contacts-frequently-asked-questions">Searching
	 *      contacts by email address</a>
	 * @throws NoValidObjectsReturned
	 */
	public List<Contact> findByEmail(String emailAddress) throws NoValidObjectsReturned;
	
	/**
	 * Find a Contact by name
	 * 
	 * @param name
	 *            Contact name
	 * @return {@link Contact}
	 * @see Contact
	 * @see <a href=
	 *      "https://streak.readme.io/v9/reference#contacts-frequently-asked-questions">Searching
	 *      contacts by email address</a>
	 * @throws NoValidObjectsReturned
	 */
	public List<Contact> findByName(String name) throws NoValidObjectsReturned;

	/**
	 * Create a new contact. All contacts are scoped to a particular team so a
	 * teamKey is required. When creating a contact you can provide al of the
	 * contact info in a single call. See the possible parameters below.
	 * 
	 * Contacts must have at least one of Name or Email Address set.
	 * 
	 * Note: Including the parameter getIfExisting limits the parameters you can
	 * include in the call.
	 * 
	 * @param teamKey
	 *            Team key
	 * @param emailAddress
	 *            Contact emailAddress
	 * @param getIfExisting
	 *            If set to true, will check for existing contact based on email. If
	 *            contact does exist, call will return existing contact; else, call
	 *            will create new contact. Either way, the call will return a
	 *            contact. If you include this parameter, the call can only take a
	 *            teamKey and a contact's emailAddresses. If you have more
	 *            information on your contact, you'll need to run a separate call to
	 *            update it. See Update a contact.
	 * @return {@link Contact}
	 * @see Contact
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	Contact create(String teamKey, String emailAddress, Boolean getIfExisting) throws NoValidObjectsReturned;
	
	/**
	 * Create a new contact. All contacts are scoped to a particular team so a
	 * teamKey is required. When creating a contact you can provide al of the
	 * contact info in a single call. See the possible parameters below.
	 * 
	 * Contacts must have at least one of Name or Email Address set.
	 * 
	 * Note: Including the parameter getIfExisting limits the parameters you can
	 * include in the call.
	 * 
	 * @param teamKey
	 *            Team key
	 * @param contact
	 *            Contact
	 * @param getIfExisting
	 *            If set to true, will check for existing contact based on email. If
	 *            contact does exist, call will return existing contact; else, call
	 *            will create new contact. Either way, the call will return a
	 *            contact. If you include this parameter, the call can only take a
	 *            teamKey and a contact's emailAddresses. If you have more
	 *            information on your contact, you'll need to run a separate call to
	 *            update it. See Update a contact.
	 * @return {@link Contact}
	 * @see Contact
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public Contact create(String teamKey, Contact contact, Boolean getIfExisting) throws NoValidObjectsReturned;

	/**
	 * Updates an Contact and returns the updated Contact object
	 * 
	 * Note:
	 * 
	 * The only email addresses associated with the contact will be the ones you
	 * include here; make sure to include any previously associated addresses as
	 * well as the new one(s).
	 * 
	 * The only addresses associated with the contact will be the ones you include
	 * here; make sure to include any previously associated addresses as well as the
	 * new one(s).
	 * 
	 * The only phone numbers associated with the contact will be the ones you
	 * include here; make sure to include any previously associated numbers as well
	 * as the new one(s).
	 * 
	 * @param contactKey
	 *            Contact key
	 * @param contact
	 *            Contact
	 * @return {@link Contact}
	 * @see Contact
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public Contact update(Contact contact) throws NoValidObjectsReturned;

	/**
	 * Deletes an Contact and returns 'true' if successful
	 * 
	 * @param contactKey
	 *            Contact key
	 * @return boolean
	 * @see Contact
	 * @see <a href="https://www.streak.com/api/">Streak API Doc</a>
	 * @throws NoValidObjectsReturned
	 */
	public boolean delete(String contactKey) throws NoValidObjectsReturned;
}
