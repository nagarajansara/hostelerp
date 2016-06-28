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

	void deleteUsers(int userId, String sTATUS_INACTIVE) throws Exception;

	List<Users> getUsersViaId(int userId) throws Exception;

	void updateUsersViaId(Users users) throws Exception;

	List<CityState> getCityAndState(String locationname, String status,
			boolean isCity) throws Exception;

	List<Student> getStudentViaSearchParam(int startIndx, int maxIndx,
			String sTATUS_ACTIVE, String searchParameter) throws Exception;

	List<Student> getStudent(int startIndx, int maxIndx, String sTATUS_ACTIVE)
			throws Exception;

	int getStudentNumEntries(String sTATUS_ACTIVE) throws Exception;

	int getStudentNumEntriesViaSearchParam(String sTATUS_ACTIVE,
			String searchParameter) throws Exception;

	void addStudent(Student student) throws Exception;

	List<Student> getStudentsViaId(int studentId, String status)
			throws Exception;

	void deleteStudentViaId(int studentId) throws Exception;

	void updateStudensViaId(Student student) throws Exception;

	List<Hostel> getHostels(int startIndx, int maxIndx, String sTATUS_ACTIVE)
			throws Exception;

	List<Hostel> getHostelsViaSearchParam(int startIndx, int maxIndx,
			String sTATUS_ACTIVE, String searchParameter) throws Exception;

	List<Hostel> getCollegeNameApi(String locationname, String status)
			throws Exception;

	void addHostel(Hostel hostel) throws Exception;

	List<Hostel> getHostelViaId(int hostelId, String sTATUS_ACTIVE)
			throws Exception;

	void updateHostelViaId(Hostel hostel) throws Exception;

	int getHostelsNumEntries(String status) throws Exception;

	int getHostelsNumEntriesViaSearchParam(String sTATUS_ACTIVE,
			String searchParameter) throws Exception;

	void deleteHostelViaId(int hostelId, String sTATUS_DEACTIVE)
			throws Exception;

	List<Block> getBlocks(int startIndx, int maxIndx, String sTATUS_ACTIVE)
			throws Exception;

	int getBlocksNumEntries(String sTATUS_ACTIVE) throws Exception;

	List<Block> getBlocksViaSearchParam(int startIndx, int maxIndx,
			String sTATUS_ACTIVE, String searchParameter) throws Exception;

	int getBlocksViaSearchParamNumEntriesViaSearchParam(String sTATUS_ACTIVE,
			String searchParameter) throws Exception;

	List<Hostel> getHostelNameApi(String locationname, String status)
			throws Exception;

	void addBlock(Block block) throws Exception;

	List<Block> getBlockViaId(int id, String sTATUS_ACTIVE) throws Exception;

	void updateBlockViaId(Block block) throws Exception;

	void deleteBlockViaId(int id, String status) throws Exception;

	List<Users> getUsers(String sTATUS_ACTIVE) throws Exception;

	void addColleges(College college) throws Exception;

	List<College> getColleges(int startIndx, int maxIndx, String sTATUS_ACTIVE)
			throws Exception;

	int getCollegesNumEntries(String sTATUS_ACTIVE) throws Exception;

	List<College> getCollegesViaSearchParam(int startIndx, int maxIndx,
			String sTATUS_ACTIVE, String searchParameter) throws Exception;

	int getCollegeNumEntriesViaSearchParam(String sTATUS_ACTIVE,
			String searchParameter) throws Exception;

	List<College> getCollegeViaId(int id, String sTATUS_ACTIVE)
			throws Exception;

	void updateCollegesViaId(College college) throws Exception;

	void deleteCollegeViaId(int id, String dEACTIVE) throws Exception;

	List<Room> getRooms(int startIndx, int maxIndx, String sTATUS_ACTIVE)
			throws Exception;

	int getRoomsNumEntries(String sTATUS_ACTIVE) throws Exception;

	List<Room> getRoomsViaSearchParam(int startIndx, int maxIndx,
			String sTATUS_ACTIVE, String searchParameter) throws Exception;

	int getRoomsNumEntriesViaSearchParam(String sTATUS_ACTIVE,
			String searchParameter) throws Exception;

	List<College> getAllCollegesViaStatus(String sTATUS_ACTIVE)
			throws Exception;

	List<Hostel> getHostelsViaCollegeId(int collegeId) throws Exception;

	List<Block> getBlockViaHostelId(int collegeId, int hostelId)
			throws Exception;

	void addRoom(Room room) throws Exception;

	List<Room> getRoomViaId(int id) throws Exception;

	List<Hostel> getHostelsViaRoomId(int id, String sTATUS_ACTIVE)
			throws Exception;

	List<Block> getBlocksViaRoomId(int id, String sTATUS_ACTIVE)
			throws Exception;

	void updateRoomViaId(Room room) throws Exception;

	List<Block> getNoFloorsViaHostelAndBlockId(int hostelId, int blockId,
			String sTATUS_ACTIVE) throws Exception;

	List<RoomAllocation> getRoomAllocationDetails(int startIndx, int maxIndx,
			String sTATUS_ACTIVE) throws Exception;

	int getRoomAllocationDetailsNumEntries(String sTATUS_ACTIVE)
			throws Exception;

	List<RoomAllocation> getRoomAllocationDetailsViaSearchParam(int startIndx,
			int maxIndx, String sTATUS_ACTIVE, String searchParameter)
			throws Exception;

	int getRoomAllocationDetailsNumEntriesViaSearchParam(String sTATUS_ACTIVE,
			String searchParameter) throws Exception;

	List<Student> getStudentViaCollegeId(int collegeId, String sTATUS_ACTIVE)
			throws Exception;

	List<Hostel> getHostelViaCollegeId(int collegeId) throws Exception;

	List<Room> getRoomNoViaHostelId(int hostelId, String sTATUS_ACTIVE)
			throws Exception;

	int chkIsAlloted(RoomAllocation roomAllocation) throws Exception;

	void addRoomAllocation(RoomAllocation roomAllocation) throws Exception;

			List<RoomAllocation> getRoomAllocationDetailsViaRoomId(
					int roomAllocationId) throws Exception;

	void updateRoomAllocationViaId(RoomAllocation roomAllocation)
			throws Exception;

	int getRoomNoViaHostelId_RoomNo(int hostelId, int RoomNo) throws Exception;

}
