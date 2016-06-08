package hostelerp.com.service.impl;

import hostelerp.com.dao.ProjectManagerDAO;
import hostelerp.com.model.Block;
import hostelerp.com.model.CityState;
import hostelerp.com.model.Hostel;
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

	@Override
	public List<Student> getStudentsViaId(int studentId, String status)
			throws Exception
	{
		return projectManagerDAO.getStudentsViaId(studentId, status);
	}

	@Override
	public void deleteStudentViaId(int studentId) throws Exception
	{
		projectManagerDAO.deleteStudentViaId(studentId);
	}

	@Override
	public void updateStudensViaId(Student student) throws Exception
	{

		projectManagerDAO.updateStudensViaId(student);
	}

	@Override
	public List<Hostel> getHostels(int startIndx, int maxIndx,
			String sTATUS_ACTIVE) throws Exception
	{
		return projectManagerDAO.getHostels(startIndx, maxIndx, sTATUS_ACTIVE);
	}

	@Override
	public List<Hostel> getHostelsViaSearchParam(int startIndx, int maxIndx,
			String sTATUS_ACTIVE, String searchParameter) throws Exception
	{
		return projectManagerDAO.getHostelsViaSearchParam(startIndx, maxIndx,
				sTATUS_ACTIVE, searchParameter);
	}

	@Override
	public List<Hostel> getCollegeNameApi(String locationname, String status)
			throws Exception
	{

		return projectManagerDAO.getCollegeNameApi(locationname, status);
	}

	@Override
	public void addHostel(Hostel hostel) throws Exception
	{
		projectManagerDAO.addHostel(hostel);
	}

	@Override
	public List<Hostel> getHostelViaId(int hostelId, String sTATUS_ACTIVE)
			throws Exception
	{
		return projectManagerDAO.getHostelViaId(hostelId, sTATUS_ACTIVE);
	}

	@Override
	public void updateHostelViaId(Hostel hostel) throws Exception
	{
		projectManagerDAO.updateHostelViaId(hostel);
	}

	@Override
	public int getHostelsNumEntries(String sTATUS_ACTIVE) throws Exception
	{
		return projectManagerDAO.getHostelsNumEntries(sTATUS_ACTIVE);
	}

	@Override
	public int getHostelsNumEntriesViaSearchParam(String sTATUS_ACTIVE,
			String searchParameter) throws Exception
	{
		return projectManagerDAO.getHostelsNumEntriesViaSearchParam(
				sTATUS_ACTIVE, searchParameter);
	}

	@Override
	public void deleteHostelViaId(int hostelId, String sTATUS_DEACTIVE)
			throws Exception
	{
		projectManagerDAO.deleteHostelViaId(hostelId, sTATUS_DEACTIVE);

	}

	@Override
	public List<Block> getBlocks(int startIndx, int maxIndx,
			String sTATUS_ACTIVE) throws Exception
	{
		return projectManagerDAO.getBlocks(startIndx, maxIndx, sTATUS_ACTIVE);
	}

	@Override
	public int getBlocksNumEntries(String sTATUS_ACTIVE) throws Exception
	{
		return projectManagerDAO.getBlocksNumEntries(sTATUS_ACTIVE);
	}

	@Override
	public List<Block> getBlocksViaSearchParam(int startIndx, int maxIndx,
			String sTATUS_ACTIVE, String searchParameter) throws Exception
	{
		return projectManagerDAO.getBlocksViaSearchParam(startIndx, maxIndx,
				sTATUS_ACTIVE, searchParameter);
	}

	@Override
	public int getBlocksViaSearchParamNumEntriesViaSearchParam(
			String sTATUS_ACTIVE, String searchParameter) throws Exception
	{
		return projectManagerDAO
				.getBlocksViaSearchParamNumEntriesViaSearchParam(sTATUS_ACTIVE,
						searchParameter);
	}

	@Override
	public List<Hostel> getHostelNameApi(String locationname, String status)
			throws Exception
	{
		return projectManagerDAO.getHostelNameApi(locationname, status);
	}

	@Override
	public void addBlock(Block block) throws Exception
	{
		projectManagerDAO.addBlock(block);
	}

	@Override
	public List<Block> getBlockViaId(int id, String sTATUS_ACTIVE)
			throws Exception
	{
		return projectManagerDAO.getBlockViaId(id, sTATUS_ACTIVE);
	}

	@Override
	public void updateBlockViaId(Block block) throws Exception
	{
		projectManagerDAO.updateBlockViaId(block);

	}

	@Override
	public void deleteBlockViaId(int id, String status) throws Exception
	{
		projectManagerDAO.deleteBlockViaId(id, status);
	}

}
