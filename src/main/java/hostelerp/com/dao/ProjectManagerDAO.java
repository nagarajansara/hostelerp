package hostelerp.com.dao;

import javax.servlet.http.HttpServletRequest;

import java.util.*;

import hostelerp.com.util.*;
import hostelerp.com.model.*;

@SuppressWarnings(
{ "unused", "unchecked" })
public interface ProjectManagerDAO
{

	void addUsers(Users user) throws Exception;

	List<Users> getUsers(int startIndx, int maxIndx, String sTATUS_ACTIVE)
			throws Exception;

	int getUsersNumEntries(String sTATUS_ACTIVE) throws Exception;

	List<Users> getUsersViaSearchParam(int startIndx, int maxIndx,
			String sTATUS_ACTIVE, String searchParam) throws Exception;

	int getUsersNumEntriesViaSearchParam(String status, String searchParam)
			throws Exception;

}
