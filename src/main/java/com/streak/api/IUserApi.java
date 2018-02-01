package com.streak.api;

import com.streak.model.User;
import com.streakapi.crm.exceptions.NoValidObjectsReturned;

public interface IUserApi {
	public static final String CURRENT_USER = "https://www.streak.com/api/v1/users/me";
	public static final String USER_BY_KEY = "https://www.streak.com/api/v1/users/{userKey}";
	
	/**
	 * Retrieves the User associated with the current authenticated session
	 * 
	 * @return {@link User}
	 * @see {@link User}
	 * @see <a href="https://streak.readme.io/v9/reference#get-current-user">Get Current User</a>
	 * @throws NoValidObjectsReturned
	 */
	public User getCurrentUser() throws NoValidObjectsReturned;

	/**
	 * Find a User based by its ID/key
	 * 
	 * @param userKey
	 *            User key
	 * @return {@link User}
	 * 
	 * @see {@link User}
	 * @see <a href="https://streak.readme.io/v9/reference#get-user">Get User</a>
	 * @throws NoValidObjectsReturned
	 */
	public User findById(String userKey) throws NoValidObjectsReturned;
}
