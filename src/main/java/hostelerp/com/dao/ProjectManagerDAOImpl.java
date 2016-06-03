package hostelerp.com.dao;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.*;

import hostelerp.com.util.*;
import hostelerp.com.model.*;

@SuppressWarnings(
{ "unused", "unchecked" })
public class ProjectManagerDAOImpl implements ProjectManagerDAO
{
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	final String ADD_USERS =
			"INSERT INTO users (username, password, firstname, usertype) VALUES (:username, :password, :firstname, :usertype)";
	final String GET_USERS =
			"SELECT *,  REPLACE('<button pk_id=\"userid\" title=\"Edit\" class=\"btn btn-success btn-xs btn-perspective hfmsEditWorker\"><i class=\"fa fa-pencil-square\"></i> "
					+ "</button> &nbsp; <button pk_id=\"userid\" title=\"Delete\" class=\"btn btn-danger btn-xs btn-perspective hfmsDelWorker\">"
					+ "<i class=\"fa fa-trash-o\"></i> </button>', 'userid', userid) AS editBtn "
					+ "from users where status =:status ORDER BY created_on DESC LIMIT :startINdx, :endIndx ";
	final String GET_USERS_NUMENTRIES =
			"SELECT count(*) from users where status =:status";
	final String GET_USERS_SEARCH_PARAM =
			"SELECT *, REPLACE('<button pk_id=\"userid\" title=\"Edit\" class=\"btn btn-success btn-xs btn-perspective hfmsEditWorker\"><i class=\"fa fa-pencil-square\"></i> "
					+ "</button> &nbsp; <button pk_id=\"userid\" title=\"Delete\" class=\"btn btn-danger btn-xs btn-perspective hfmsDelWorker\">"
					+ "<i class=\"fa fa-trash-o\"></i> </button>', 'userid', userid) AS editBtn "
					+ " from users where status =:status AND (username like :searchParam OR usertype like :searchParam) "
					+ "ORDER BY created_on DESC LIMIT :startINdx, :endIndx ";
	final String GET_USERS_SEARCH_PARAM_NUMENTRIES =
			"SELECT count(*) from users where status =:status AND (username like :searchParam OR usertype like :searchParam) ";
	final String DELETE_USER =
			"Update users set status=:status where userid =:userid";
	final String GET_USERS_VIA_ID = "SELECT * from users where userid =:userid";
	final String UPDATE_USERS_VIA_ID =
			"UPDATE users set username=:username, firstname=:firstname, usertype=:usertype "
					+ "Where userid=:userid";
	final String GET_CITY =
			"Select * from city where status =:status AND name like :name";
	final String GET_STATE =
			"Select * from state where status =:status AND name like :name";
	final String GET_STUDENTS =
			"SELECT *,REPLACE('<button pk_id=\"userid\" title=\"Edit\" class=\"btn btn-success btn-xs btn-perspective hfmsEditWorker\"><i class=\"fa fa-pencil-square\"></i> "
					+ "</button> &nbsp; <button pk_id=\"userid\" title=\"Delete\" class=\"btn btn-danger btn-xs btn-perspective hfmsDelWorker\">"
					+ "<i class=\"fa fa-trash-o\"></i> </button> &nbsp; <button pk_id=\"userid\" title=\"Info\" class=\"btn btn-default btn-xs btn-perspective hfmsInfo\">"
					+ "<i class=\"fa fa-info-circle\"></i> </button>', 'userid', id) AS editBtn "
					+ "from student where status =:status ORDER BY created_at DESC LIMIT :startIndx, :endIndx";
	final String GET_STUDENTS_NUMENTRIES =
			"SELECT count(*) from student where status =:status";
	final String GET_STUDENT_VIA_SEARCHPARAM =
			"SELECT *, REPLACE('<button pk_id=\"userid\" title=\"Edit\" class=\"btn btn-success btn-xs btn-perspective hfmsEditWorker\"><i class=\"fa fa-pencil-square\"></i> "
					+ "</button> &nbsp; <button pk_id=\"userid\" title=\"Delete\" class=\"btn btn-danger btn-xs btn-perspective hfmsDelWorker\">"
					+ "<i class=\"fa fa-trash-o\"></i> </button> &nbsp; <button pk_id=\"userid\" title=\"Info\" class=\"btn btn-default btn-xs btn-perspective hfmsInfo\">"
					+ "<i class=\"fa fa-info-circle\"></i> </button>', 'userid', id) AS editBtn "
					+ "from student where status =:status AND "
					+ "(name LIKE :searchParam OR rollno LIKE :searchParam OR course LIKE :searchParam OR messtype LIKE :searchParam) "
					+ "ORDER BY created_at LIMIT :startIndx, :endIndx";
	final String GET_STUDENT_NUMENTRIES_VIA_SEARCHPARAM =
			"SELECT count(*) from student where status =:status AND "
					+ "(name LIKE :searchParam OR rollno LIKE :searchParam OR course LIKE :searchParam OR messtype LIKE :searchParam) ";
	final String ADD_STUDENT =
			"INSERT INTO student (name, rollno, batch, course, messtype, address, state, city, country, mobileno) "
					+ "VALUES (:name, :rollno, :batch, :course, :messtype, :address, :state, :city, :country, :mobileno)";
	final String GET_STUDENTS_VIA_ID =
			"Select * from student where id =:id AND status =:status";
	final String DELETE_STUDENTS_VIA_ID =
			"Update student set status =:status where id =:id";
	final String UPDATE_STUDENT_VIA_ID =
			"UPDATE student set name =:name, batch =:batch, course =:course, "
					+ "messtype =:messtype, address =:address, state =:state, city =:city, country =:country, mobileno =:mobileno WHERE id =:id";

	@Override
	public void addUsers(Users user) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("username", user.getUsername());
		paramMap.put("password", user.getPassword());
		paramMap.put("firstname", user.getFirstname());
		paramMap.put("usertype", user.getUsertype());

		namedParameterJdbcTemplate.update(ADD_USERS, paramMap);
	}

	@Override
	public
			List<Users> getUsers(int startIndx, int maxIndx,
					String sTATUS_ACTIVE) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("startINdx", startIndx);
		paramMap.put("endIndx", maxIndx);
		paramMap.put("status", sTATUS_ACTIVE);

		paramMap.put("status", sTATUS_ACTIVE);
		return namedParameterJdbcTemplate.query(GET_USERS, paramMap,
				new BeanPropertyRowMapper<Users>(Users.class));
	}

	@Override
	public int getUsersNumEntries(String sTATUS_ACTIVE) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);

		return namedParameterJdbcTemplate.queryForInt(GET_USERS_NUMENTRIES,
				paramMap);
	}

	@Override
	public List<Users> getUsersViaSearchParam(int startIndx, int maxIndx,
			String sTATUS_ACTIVE, String searchParam) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("startINdx", startIndx);
		paramMap.put("endIndx", maxIndx);
		paramMap.put("status", sTATUS_ACTIVE);
		paramMap.put("searchParam", searchParam + "%");

		return namedParameterJdbcTemplate.query(GET_USERS_SEARCH_PARAM,
				paramMap, new BeanPropertyRowMapper(Users.class));

	}

	@Override
	public int getUsersNumEntriesViaSearchParam(String status,
			String searchParam) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", status);
		paramMap.put("searchParam", searchParam + "%");
		return namedParameterJdbcTemplate.queryForInt(
				GET_USERS_SEARCH_PARAM_NUMENTRIES, paramMap);
	}

	@Override
	public void deleteUsers(int userId, String sTATUS_INACTIVE)
			throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("userid", userId);
		paramMap.put("status", sTATUS_INACTIVE);

		namedParameterJdbcTemplate.update(DELETE_USER, paramMap);

	}

	@Override
	public List<Users> getUsersViaId(int userId) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("userid", userId);

		return namedParameterJdbcTemplate.query(GET_USERS_VIA_ID, paramMap,
				new BeanPropertyRowMapper(Users.class));
	}

	@Override
	public void updateUsersViaId(Users users) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("username", users.getUsername());
		paramMap.put("firstname", users.getFirstname());
		paramMap.put("usertype", users.getUsertype());
		paramMap.put("userid", users.getUserid());

		namedParameterJdbcTemplate.update(UPDATE_USERS_VIA_ID, paramMap);

	}

	@Override
	public List<CityState> getCityAndState(String locationname, String status,
			boolean isCity) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", status);
		paramMap.put("name", locationname + "%");
		if (isCity)
		{
			return namedParameterJdbcTemplate.query(GET_CITY, paramMap,
					new BeanPropertyRowMapper(CityState.class));
		} else
		{
			return namedParameterJdbcTemplate.query(GET_STATE, paramMap,
					new BeanPropertyRowMapper(CityState.class));
		}

	}

	@Override
	public List<Student> getStudentViaSearchParam(int startIndx, int maxIndx,
			String sTATUS_ACTIVE, String searchParameter) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		paramMap.put("startIndx", startIndx);
		paramMap.put("endIndx", maxIndx);
		paramMap.put("searchParam", searchParameter + "%");

		return namedParameterJdbcTemplate.query(GET_STUDENT_VIA_SEARCHPARAM,
				paramMap, new BeanPropertyRowMapper(Student.class));
	}

	@Override
	public List<Student> getStudent(int startIndx, int maxIndx,
			String sTATUS_ACTIVE) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		paramMap.put("startIndx", startIndx);
		paramMap.put("endIndx", maxIndx);

		return namedParameterJdbcTemplate.query(GET_STUDENTS, paramMap,
				new BeanPropertyRowMapper(Student.class));
	}

	@Override
	public int getStudentNumEntries(String sTATUS_ACTIVE) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		return namedParameterJdbcTemplate.queryForInt(GET_STUDENTS_NUMENTRIES,
				paramMap);
	}

	@Override
	public int getStudentNumEntriesViaSearchParam(String sTATUS_ACTIVE,
			String searchParameter) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		paramMap.put("searchParam", searchParameter + "%");
		return namedParameterJdbcTemplate.queryForInt(
				GET_STUDENT_NUMENTRIES_VIA_SEARCHPARAM, paramMap);
	}

	@Override
	public void addStudent(Student student) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("name", student.getName());
		paramMap.put("rollno", student.getRollno());
		paramMap.put("batch", student.getBatch());
		paramMap.put("course", student.getCourse());
		paramMap.put("messtype", student.getMesstype());
		paramMap.put("address", student.getAddress());
		paramMap.put("state", student.getState());
		paramMap.put("city", student.getCity());
		paramMap.put("country", student.getCountry());
		paramMap.put("mobileno", student.getMobileno());

		namedParameterJdbcTemplate.update(ADD_STUDENT, paramMap);

	}

	@Override
	public List<Student> getStudentsViaId(int studentId, String status)
			throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("id", studentId);
		paramMap.put("status", status);
		return namedParameterJdbcTemplate.query(GET_STUDENTS_VIA_ID, paramMap,
				new BeanPropertyRowMapper(Student.class));
	}

	@Override
	public void deleteStudentViaId(int studentId) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", "deactive");
		paramMap.put("id", studentId);

		namedParameterJdbcTemplate.update(DELETE_STUDENTS_VIA_ID, paramMap);
	}

	@Override
	public void updateStudensViaId(Student student) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("name", student.getName());
		paramMap.put("batch", student.getBatch());
		paramMap.put("course", student.getCourse());
		paramMap.put("messtype", student.getMesstype());
		paramMap.put("address", student.getAddress());
		paramMap.put("state", student.getState());
		paramMap.put("city", student.getCity());
		paramMap.put("country", student.getCountry());
		paramMap.put("mobileno", student.getMobileno());
		paramMap.put("id", student.getId());

		namedParameterJdbcTemplate.update(UPDATE_STUDENT_VIA_ID, paramMap);
	}

}
