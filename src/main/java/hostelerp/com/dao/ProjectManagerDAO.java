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

}
