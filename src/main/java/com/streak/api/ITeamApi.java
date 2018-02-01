package com.streak.api;

import java.util.List;

import com.streak.model.Team;
import com.streakapi.crm.exceptions.NoValidObjectsReturned;

public interface ITeamApi {
	public static final String CURRENT_USER_TEAMS = "https://www.streak.com/api/v1/users/me/teams";
	public static final String TEAM_BY_KEY = "https://www.streak.com/api/v1/teams/{teamKey}";
	
	/**
	 * Retrieves the teams associated with the current authenticated User
	 * 
	 * @return {@link Team}
	 * @see {@link Team}
	 * @see <a href="https://streak.readme.io/v9/reference#get-teams">Get current User teams</a>
	 * @throws NoValidObjectsReturned
	 */
	public List<Team> getCurrentUserTeams() throws NoValidObjectsReturned;

	/**
	 * Find a User based by its ID/key
	 * 
	 * @param teamKey
	 *            Team key
	 * @return {@link Team}
	 * 
	 * @see {@link Team}
	 * @see <a href="https://streak.readme.io/v9/reference#get-team">Get a team</a>
	 * @throws NoValidObjectsReturned
	 */
	public Team findById(String teamKey) throws NoValidObjectsReturned;
}
