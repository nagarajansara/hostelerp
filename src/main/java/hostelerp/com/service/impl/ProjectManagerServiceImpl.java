package hostelerp.com.service.impl;

import hostelerp.com.dao.ProjectManagerDAO;
import hostelerp.com.dao.UserMenuDAO;
import hostelerp.com.model.Block;
import hostelerp.com.model.CityState;
import hostelerp.com.model.College;
import hostelerp.com.model.Hostel;
import hostelerp.com.model.Payment;
import hostelerp.com.model.Room;
import hostelerp.com.model.RoomAllocation;
import hostelerp.com.model.Student;
import hostelerp.com.model.UserMenu;
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

	@Autowired
	UserMenuDAO userMenuDAO;

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

	@Override
	public List<UserMenu> getUserMenus(String status, int userId)
			throws Exception
	{
		return userMenuDAO.getUserMenu(userId, status);
	}

	@Override
	public List<Users> getUsers(String sTATUS_ACTIVE) throws Exception
	{
		return projectManagerDAO.getUsers(sTATUS_ACTIVE);
	}

	@Override
	public void addUserMenuRights(UserMenu userMenu) throws Exception
	{
		userMenuDAO.addUserMenuRights(userMenu);
	}

	@Override
	public void addColleges(College college) throws Exception
	{
		projectManagerDAO.addColleges(college);
	}

	@Override
	public List<College> getColleges(int startIndx, int maxIndx,
			String sTATUS_ACTIVE) throws Exception
	{
		return projectManagerDAO.getColleges(startIndx, maxIndx, sTATUS_ACTIVE);
	}

	@Override
	public int getCollegesNumEntries(String sTATUS_ACTIVE) throws Exception
	{
		return projectManagerDAO.getCollegesNumEntries(sTATUS_ACTIVE);
	}

	@Override
	public List<College> getCollegesViaSearchParam(int startIndx, int maxIndx,
			String sTATUS_ACTIVE, String searchParameter) throws Exception
	{
		return projectManagerDAO.getCollegesViaSearchParam(startIndx, maxIndx,
				sTATUS_ACTIVE, searchParameter);
	}

	@Override
	public int getCollegeNumEntriesViaSearchParam(String sTATUS_ACTIVE,
			String searchParameter) throws Exception
	{
		return projectManagerDAO.getCollegeNumEntriesViaSearchParam(
				sTATUS_ACTIVE, searchParameter);
	}

	@Override
	public List<College> getCollegeViaId(int id, String sTATUS_ACTIVE)
			throws Exception
	{
		return projectManagerDAO.getCollegeViaId(id, sTATUS_ACTIVE);
	}

	@Override
	public void updateCollegesViaId(College college) throws Exception
	{
		projectManagerDAO.updateCollegesViaId(college);
	}

	@Override
	public void deleteCollegeViaId(int id, String dEACTIVE) throws Exception
	{
		projectManagerDAO.deleteCollegeViaId(id, dEACTIVE);
	}

	@Override
	public
			List<Room>
			getRooms(int startIndx, int maxIndx, String sTATUS_ACTIVE)
					throws Exception
	{
		return projectManagerDAO.getRooms(startIndx, maxIndx, sTATUS_ACTIVE);
	}

	@Override
	public int getRoomsNumEntries(String sTATUS_ACTIVE) throws Exception
	{
		return projectManagerDAO.getRoomsNumEntries(sTATUS_ACTIVE);
	}

	@Override
	public List<Room> getRoomsViaSearchParam(int startIndx, int maxIndx,
			String sTATUS_ACTIVE, String searchParameter) throws Exception
	{
		return projectManagerDAO.getRoomsViaSearchParam(startIndx, maxIndx,
				sTATUS_ACTIVE, searchParameter);
	}

	@Override
	public int getRoomsNumEntriesViaSearchParam(String sTATUS_ACTIVE,
			String searchParameter) throws Exception
	{
		return projectManagerDAO.getRoomsNumEntriesViaSearchParam(
				sTATUS_ACTIVE, searchParameter);
	}

	@Override
	public List<College> getAllCollegesViaStatus(String sTATUS_ACTIVE)
			throws Exception
	{
		return projectManagerDAO.getAllCollegesViaStatus(sTATUS_ACTIVE);
	}

	@Override
	public List<Hostel> getHostelsViaCollegeId(int collegeId) throws Exception
	{
		return projectManagerDAO.getHostelsViaCollegeId(collegeId);
	}

	@Override
	public List<Block> getBlockViaHostelId(int collegeId, int hostelId)
			throws Exception
	{
		return projectManagerDAO.getBlockViaHostelId(collegeId, hostelId);
	}

	@Override
	public void addRoom(Room room) throws Exception
	{
		projectManagerDAO.addRoom(room);
	}

	@Override
	public List<Room> getRoomViaId(int id) throws Exception
	{
		return projectManagerDAO.getRoomViaId(id);
	}

	@Override
	public List<Hostel> getHostelsViaRoomId(int id, String STATUS_ACTIVE)
			throws Exception
	{
		return projectManagerDAO.getHostelsViaRoomId(id, STATUS_ACTIVE);
	}

	@Override
	public List<Block> getBlocksViaRoomId(int id, String sTATUS_ACTIVE)
			throws Exception
	{
		return projectManagerDAO.getBlocksViaRoomId(id, sTATUS_ACTIVE);
	}

	@Override
	public void updateRoomViaId(Room room) throws Exception
	{
		projectManagerDAO.updateRoomViaId(room);
	}

	@Override
	public List<Block> getNoFloorsViaHostelAndBlockId(int hostelId,
			int blockId, String sTATUS_ACTIVE) throws Exception
	{
		return projectManagerDAO.getNoFloorsViaHostelAndBlockId(hostelId,
				blockId, sTATUS_ACTIVE);
	}

	@Override
	public List<RoomAllocation> getRoomAllocationDetails(int startIndx,
			int maxIndx, String sTATUS_ACTIVE) throws Exception
	{
		return projectManagerDAO.getRoomAllocationDetails(startIndx, maxIndx,
				sTATUS_ACTIVE);
	}

	@Override
	public int getRoomAllocationDetailsNumEntries(String sTATUS_ACTIVE)
			throws Exception
	{
		return projectManagerDAO
				.getRoomAllocationDetailsNumEntries(sTATUS_ACTIVE);
	}

	@Override
	public List<RoomAllocation> getRoomAllocationDetailsViaSearchParam(
			int startIndx, int maxIndx, String sTATUS_ACTIVE,
			String searchParameter) throws Exception
	{
		return projectManagerDAO.getRoomAllocationDetailsViaSearchParam(
				startIndx, maxIndx, sTATUS_ACTIVE, searchParameter);
	}

	@Override
	public int getRoomAllocationDetailsNumEntriesViaSearchParam(
			String sTATUS_ACTIVE, String searchParameter) throws Exception
	{
		return projectManagerDAO
				.getRoomAllocationDetailsNumEntriesViaSearchParam(
						sTATUS_ACTIVE, searchParameter);
	}

	@Override
	public List<Payment> getPaymentsViaSearchParam(int startIndx, int maxIndx,
			String searchParameter) throws Exception
	{

		return projectManagerDAO.getPaymentsViaSearchParam(startIndx, maxIndx,
				searchParameter);
	}

	@Override
	public int getPaymentsNumEntriesNumEntriesViaSearchParam(
			String sTATUS_ACTIVE, String searchParameter) throws Exception
	{
		return projectManagerDAO.getPaymentsNumEntriesNumEntriesViaSearchParam(searchParameter);
	}

	@Override
	public List<Student> getStudentViaCollegeId(int collegeId,
			String sTATUS_ACTIVE) throws Exception
	{
		return projectManagerDAO.getStudentViaCollegeId(collegeId,
				sTATUS_ACTIVE);
	}

	@Override
	public List<Hostel> getHostelViaCollegeId(int collegeId) throws Exception
	{
		return projectManagerDAO.getHostelViaCollegeId(collegeId);
	}

	@Override
	public List<Room> getRoomNoViaHostelId(int hostelId, String STATUS_ACTIVE)
			throws Exception
	{
		return projectManagerDAO.getRoomNoViaHostelId(hostelId, STATUS_ACTIVE);
	}

	@Override
	public int chkIsAlloted(RoomAllocation roomAllocation) throws Exception
	{
		return projectManagerDAO.chkIsAlloted(roomAllocation);
	}

	@Override
	public void addRoomAllocation(RoomAllocation roomAllocation)
			throws Exception
	{
		projectManagerDAO.addRoomAllocation(roomAllocation);
	}

	@Override
	public List<RoomAllocation> getRoomAllocationDetailsViaRoomId(
			int roomAllocationId) throws Exception
	{
		return projectManagerDAO
				.getRoomAllocationDetailsViaRoomId(roomAllocationId);
	}

	@Override
	public void updateRoomAllocationViaId(RoomAllocation roomAllocation)
			throws Exception
	{
		projectManagerDAO.updateRoomAllocationViaId(roomAllocation);
	}

	@Override
	public int getRoomNoViaHostelId_RoomNo(int hostelId, int RoomNo)
			throws Exception
	{
		return projectManagerDAO.getRoomNoViaHostelId_RoomNo(hostelId, RoomNo);
	}

	@Override
	public void addPayment(Payment payment) throws Exception
	{
		projectManagerDAO.addPayment(payment);

	}

	@Override
	public List<Payment> getPayments(int startIndx, int endIndx)
			throws Exception
	{
		return projectManagerDAO.getPayments(startIndx, endIndx);
	}

	@Override
	public int getPaymentsNumEntries(String searchParam) throws Exception
	{
		return projectManagerDAO.getPaymentsNumEntries(searchParam);
	}

	@Override
	public List<Payment> getPaymentsViaId(int id) throws Exception
	{
		return projectManagerDAO.getPaymentsViaId(id);
	}

	@Override
	public void updatePaymentsViaId(Payment payment) throws Exception
	{
		projectManagerDAO.updatePaymentsViaId(payment);
	}



}
