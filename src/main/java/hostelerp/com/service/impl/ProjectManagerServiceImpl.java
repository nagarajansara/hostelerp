package hostelerp.com.service.impl;

import hostelerp.com.dao.ProjectManagerDAO;
import hostelerp.com.model.CityState;
import hostelerp.com.model.Student;
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
		return projectManagerDAO.getUsersViaSearchParam(startIndx, maxIndx,
				sTATUS_ACTIVE, searchParameter);
	}

	@Override
	public int getUsersNumEntriesViaSearchParam(String sTATUS_ACTIVE,
			String searchParameter) throws Exception
	{

		return projectManagerDAO.getUsersNumEntriesViaSearchParam(
				sTATUS_ACTIVE, searchParameter);
	}

	@Override
	public void deleteUsers(int userId, String sTATUS_INACTIVE)
			throws Exception
	{
		projectManagerDAO.deleteUsers(userId, sTATUS_INACTIVE);
	}

	@Override
	public List<Users> getUsersViaId(int userId) throws Exception
	{
		return projectManagerDAO.getUsersViaId(userId);
	}

	@Override
	public void updateUsersViaId(Users users) throws Exception
	{
		projectManagerDAO.updateUsersViaId(users);

	}

	@Override
	public List<CityState> getCityAndState(String locationname, String status,
			boolean isCity) throws Exception
	{
		return projectManagerDAO.getCityAndState(locationname, status, isCity);
	}

	@Override
	public List<Student> getStudentViaSearchParam(int startIndx, int maxIndx,
			String sTATUS_ACTIVE, String searchParameter) throws Exception
	{
		return projectManagerDAO.getStudentViaSearchParam(startIndx, maxIndx,
				sTATUS_ACTIVE, searchParameter);
	}

	@Override
	public List<Student> getStudent(int startIndx, int maxIndx,
			String sTATUS_ACTIVE) throws Exception
	{
		return projectManagerDAO.getStudent(startIndx, maxIndx, sTATUS_ACTIVE);

	}

	@Override
	public int getStudentNumEntries(String sTATUS_ACTIVE) throws Exception
	{
		return projectManagerDAO.getStudentNumEntries(sTATUS_ACTIVE);
	}

	@Override
	public int getStudentNumEntriesViaSearchParam(String sTATUS_ACTIVE,
			String searchParameter) throws Exception
	{
		return projectManagerDAO.getStudentNumEntriesViaSearchParam(
				sTATUS_ACTIVE, searchParameter);
	}

	@Override
	public void addStudent(Student student) throws Exception
	{
		projectManagerDAO.addStudent(student);

	}

}
