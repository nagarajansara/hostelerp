package hostelerp.com.service.impl;

import hostelerp.com.dao.ProjectManagerDAO;
import hostelerp.com.model.Users;
import hostelerp.com.service.ProjectManagerService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings(
{ "unused", "unchecked" })
public class ProjectManagerServiceImpl implements ProjectManagerService
{

	@Autowired
	ProjectManagerDAO projectManagerDAO;

	@Override
	public void addUsers(Users user) throws Exception
	{
		projectManagerDAO.addUsers(user);
	}

	@Override
	public
			List<Users> getUsers(int startIndx, int maxIndx,
					String sTATUS_ACTIVE) throws Exception
	{
		return projectManagerDAO.getUsers(startIndx, maxIndx, sTATUS_ACTIVE);
	}

	@Override
	public int getUsersNumEntries(String sTATUS_ACTIVE) throws Exception
	{
		return projectManagerDAO.getUsersNumEntries(sTATUS_ACTIVE);
	}

	@Override
	public List<Users> getUsersViaSearchParam(int startIndx, int maxIndx,
			String sTATUS_ACTIVE, String searchParameter) throws Exception
	{
		// TODO Auto-generated method stub
		return projectManagerDAO.getUsersViaSearchParam(startIndx, maxIndx,
				sTATUS_ACTIVE, searchParameter);
	}

	@Override
	public int getUsersNumEntriesViaSearchParam(String sTATUS_ACTIVE,
			String searchParameter) throws Exception
	{
		//
		return projectManagerDAO.getUsersNumEntriesViaSearchParam(
				sTATUS_ACTIVE, searchParameter);
	}

}
