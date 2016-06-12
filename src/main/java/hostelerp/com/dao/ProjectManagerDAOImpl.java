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
					+ "messtype =:messtype, address =:address, state =:state, city =:city, country =:country, mobileno =:mobileno, rollno =:rollno WHERE id =:id";
	final String GET_HOSTELS =
			"SELECT *, REPLACE('<button pk_id=\"userid\" title=\"Edit\" class=\"btn btn-success btn-xs btn-perspective hfmsEditWorker\"><i class=\"fa fa-pencil-square\"></i> "
					+ "</button> &nbsp; <button pk_id=\"userid\" title=\"Delete\" class=\"btn btn-danger btn-xs btn-perspective hfmsDelWorker\">"
					+ "<i class=\"fa fa-trash-o\"></i> </button> &nbsp; <button pk_id=\"userid\" title=\"Info\" class=\"btn btn-default btn-xs btn-perspective hfmsInfo\">"
					+ "<i class=\"fa fa-info-circle\"></i> </button>', 'userid', id) AS editBtn from hostel where status =:status ORDER BY createdat DESC LIMIT :startIndx, :maxIndx";
	final String GET_HOSTEL_NUMENTRIES =
			"Select count(*) from hostel where status =:status";
	final String GET_HOSTELS_VIA_SEARCH_PARAMETER =
			"SELECT *, REPLACE('<button pk_id=\"userid\" title=\"Edit\" class=\"btn btn-success btn-xs btn-perspective hfmsEditWorker\"><i class=\"fa fa-pencil-square\"></i> "
					+ "</button> &nbsp; <button pk_id=\"userid\" title=\"Delete\" class=\"btn btn-danger btn-xs btn-perspective hfmsDelWorker\">"
					+ "<i class=\"fa fa-trash-o\"></i> </button> &nbsp; <button pk_id=\"userid\" title=\"Info\" class=\"btn btn-default btn-xs btn-perspective hfmsInfo\">"
					+ "<i class=\"fa fa-info-circle\"></i> </button>', 'userid', id) AS editBtn from hostel where status =:status AND (name LIKE :searchParam OR collegename LIKE :searchParam) "
					+ "ORDER BY createdat DESC LIMIT :startIndx, :maxIndx";
	final String GET_SEARCHPARAM_HOSTEL_NUMENTRIES =
			"Select count(*) from hostel where status =:status AND (name LIKE :searchParam OR collegename LIKE :searchParam)";
	final String GET_COLLEGES =
			"SELECT * from college where status =:status AND name LIKE :name";
	final String ADD_HOSTEL =
			"INSERT INTO hostel (name, collegename, mobileno, address, state, city, country) "
					+ "values(:name, :collegename, :mobileno, :address, :state, :city, :country)";
	final String GET_HOSTEL_VIA_ID =
			"Select * from hostel where status =:status AND id =:id";
	final String UPDATE_HOSTEL_VIA_ID =
			"Update hostel set name =:name, "
					+ "collegename =:collegename, mobileno =:mobileno, address =:address, "
					+ "state =:state, city =:city, country =:country where id =:id";
	final String DELETE_HOSTEL_VIA_ID =
			"Update hostel set status =:status where id =:id";
	final String GET_BLOCKS =
			"SELECT *, REPLACE('<button pk_id=\"userid\" title=\"Edit\" class=\"btn btn-success btn-xs btn-perspective hfmsEditWorker\"><i class=\"fa fa-pencil-square\"></i> "
					+ "</button> &nbsp; <button pk_id=\"userid\" title=\"Delete\" class=\"btn btn-danger btn-xs btn-perspective hfmsDelWorker\">"
					+ "<i class=\"fa fa-trash-o\"></i> </button> &nbsp; <!--<button pk_id=\"userid\" title=\"Info\" class=\"btn btn-default btn-xs btn-perspective hfmsInfo\">"
					+ "<i class=\"fa fa-info-circle\"></i> </button>-->', 'userid', id) AS editBtn from block where status =:status "
					+ "ORDER BY createdat DESC LIMIT :startIndx, :endIndx";
	final String GET_BLOCKS_NUMENTRIES =
			"SELECT count(*) from block where status =:status ";
	final String GET_BLOCKS_VIA_SEARCHPARAM =
			"SELECT *, REPLACE('<button pk_id=\"userid\" title=\"Edit\" class=\"btn btn-success btn-xs btn-perspective hfmsEditWorker\"><i class=\"fa fa-pencil-square\"></i> "
					+ "</button> &nbsp; <button pk_id=\"userid\" title=\"Delete\" class=\"btn btn-danger btn-xs btn-perspective hfmsDelWorker\">"
					+ "<i class=\"fa fa-trash-o\"></i> </button> &nbsp; <!--<button pk_id=\"userid\" title=\"Info\" class=\"btn btn-default btn-xs btn-perspective hfmsInfo\">"
					+ "<i class=\"fa fa-info-circle\"></i> </button>-->', 'userid', id) AS editBtn from block where status =:status  AND (hostelname LIKE :searchParameter OR blockname LIKE :searchParameter) "
					+ "ORDER BY createdat DESC LIMIT :startIndx, :endIndx";
	final String GET_BLOCKS_VIA_SEARCHPARAM_NUMENTRIES =
			"SELECT count(*) from block where status =:status  AND (hostelname LIKE :searchParameter OR blockname LIKE :searchParameter) ";
	final String GET_HOSTEL_NAME_API =
			"SELECT name from hostel where status =:status AND (name LIKE :hostelname)";
	final String ADD_BLOCK =
			"INSERT INTO block (hostelname, blockname, nooffloor) values (:hostelname, :blockname, :nooffloor)";
	final String GET_BLOCKS_VIA_ID =
			"Select * from block where status =:status AND id =:id";
	final String UPDATE_BLOCKS_VIA_ID =
			"UPdate block set hostelname =:hostelname, blockname =:blockname, nooffloor =:nooffloor where id =:id";
	final String DELETE_BLOCK_VIA_ID =
			"Update block set status =:status where id =:id";
	final String GET_ALL_USERS = "SELECT * from users where status =:status";

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
		paramMap.put("rollno", student.getRollno());

		namedParameterJdbcTemplate.update(UPDATE_STUDENT_VIA_ID, paramMap);
	}

	@Override
	public List<Hostel> getHostels(int startIndx, int maxIndx,
			String sTATUS_ACTIVE) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("startIndx", startIndx);
		paramMap.put("maxIndx", maxIndx);
		paramMap.put("status", sTATUS_ACTIVE);

		return namedParameterJdbcTemplate.query(GET_HOSTELS, paramMap,
				new BeanPropertyRowMapper<Hostel>(Hostel.class));
	}

	@Override
	public List<Hostel> getHostelsViaSearchParam(int startIndx, int maxIndx,
			String sTATUS_ACTIVE, String searchParameter) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("startIndx", startIndx);
		paramMap.put("maxIndx", maxIndx);
		paramMap.put("status", sTATUS_ACTIVE);
		paramMap.put("searchParam", searchParameter + "%");

		return namedParameterJdbcTemplate.query(
				GET_HOSTELS_VIA_SEARCH_PARAMETER, paramMap,
				new BeanPropertyRowMapper<Hostel>(Hostel.class));
	}

	@Override
	public List<Hostel> getCollegeNameApi(String locationname, String status)
			throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("name", locationname + "%");
		paramMap.put("status", status);

		return namedParameterJdbcTemplate.query(GET_COLLEGES, paramMap,
				new BeanPropertyRowMapper(Hostel.class));
	}

	@Override
	public void addHostel(Hostel hostel) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("name", hostel.getName());
		paramMap.put("collegename", hostel.getCollegename());
		paramMap.put("mobileno", hostel.getMobileno());
		paramMap.put("address", hostel.getAddress());
		paramMap.put("state", hostel.getState());
		paramMap.put("city", hostel.getCity());
		paramMap.put("country", hostel.getCountry());

		namedParameterJdbcTemplate.update(ADD_HOSTEL, paramMap);
	}

	@Override
	public List<Hostel> getHostelViaId(int hostelId, String sTATUS_ACTIVE)
			throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		paramMap.put("id", hostelId);

		return namedParameterJdbcTemplate.query(GET_HOSTEL_VIA_ID, paramMap,
				new BeanPropertyRowMapper(Hostel.class));
	}

	@Override
	public void updateHostelViaId(Hostel hostel) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("name", hostel.getName());
		paramMap.put("collegename", hostel.getCollegename());
		paramMap.put("mobileno", hostel.getMobileno());
		paramMap.put("address", hostel.getAddress());
		paramMap.put("state", hostel.getState());
		paramMap.put("city", hostel.getCity());
		paramMap.put("country", hostel.getCountry());
		paramMap.put("id", hostel.getId());

		namedParameterJdbcTemplate.update(UPDATE_HOSTEL_VIA_ID, paramMap);

	}

	@Override
	public int getHostelsNumEntries(String status) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", status);

		return namedParameterJdbcTemplate.queryForInt(GET_HOSTEL_NUMENTRIES,
				paramMap);

	}

	@Override
	public int getHostelsNumEntriesViaSearchParam(String sTATUS_ACTIVE,
			String searchParameter) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		paramMap.put("searchParam", searchParameter + "%");

		return namedParameterJdbcTemplate.queryForInt(
				GET_SEARCHPARAM_HOSTEL_NUMENTRIES, paramMap);

	}

	@Override
	public void deleteHostelViaId(int hostelId, String sTATUS_DEACTIVE)
			throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_DEACTIVE);
		paramMap.put("id", hostelId);

		namedParameterJdbcTemplate.update(DELETE_HOSTEL_VIA_ID, paramMap);
	}

	@Override
	public List<Block> getBlocks(int startIndx, int maxIndx,
			String sTATUS_ACTIVE) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		paramMap.put("startIndx", startIndx);
		paramMap.put("endIndx", maxIndx);

		return namedParameterJdbcTemplate.query(GET_BLOCKS, paramMap,
				new BeanPropertyRowMapper(Block.class));
	}

	@Override
	public int getBlocksNumEntries(String sTATUS_ACTIVE) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		return namedParameterJdbcTemplate.queryForInt(GET_BLOCKS_NUMENTRIES,
				paramMap);
	}

	@Override
	public List<Block> getBlocksViaSearchParam(int startIndx, int maxIndx,
			String sTATUS_ACTIVE, String searchParameter) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		paramMap.put("startIndx", startIndx);
		paramMap.put("endIndx", maxIndx);
		paramMap.put("searchParameter", searchParameter + "%");

		return namedParameterJdbcTemplate.query(GET_BLOCKS_VIA_SEARCHPARAM,
				paramMap, new BeanPropertyRowMapper(Block.class));

	}

	@Override
	public int getBlocksViaSearchParamNumEntriesViaSearchParam(
			String sTATUS_ACTIVE, String searchParameter) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		paramMap.put("searchParameter", searchParameter + "%");

		return namedParameterJdbcTemplate.queryForInt(
				GET_BLOCKS_VIA_SEARCHPARAM_NUMENTRIES, paramMap);
	}

	@Override
	public List<Hostel> getHostelNameApi(String locationname, String status)
			throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", status);
		paramMap.put("hostelname", locationname + "%");

		return namedParameterJdbcTemplate.query(GET_HOSTEL_NAME_API, paramMap,
				new BeanPropertyRowMapper(Hostel.class));
	}

	@Override
	public void addBlock(Block block) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("hostelname", block.getHostelname());
		paramMap.put("blockname", block.getBlockname());
		paramMap.put("nooffloor", block.getNooffloor());

		namedParameterJdbcTemplate.update(ADD_BLOCK, paramMap);
	}

	@Override
	public List<Block> getBlockViaId(int id, String sTATUS_ACTIVE)
			throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		paramMap.put("id", id);
		return namedParameterJdbcTemplate.query(GET_BLOCKS_VIA_ID, paramMap,
				new BeanPropertyRowMapper(Block.class));

	}

	@Override
	public void updateBlockViaId(Block block) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("hostelname", block.getHostelname());
		paramMap.put("blockname", block.getBlockname());
		paramMap.put("nooffloor", block.getNooffloor());
		paramMap.put("id", block.getId());

		namedParameterJdbcTemplate.update(UPDATE_BLOCKS_VIA_ID, paramMap);
	}

	@Override
	public void deleteBlockViaId(int id, String status) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", status);
		paramMap.put("id", id);

		namedParameterJdbcTemplate.update(DELETE_BLOCK_VIA_ID, paramMap);

	}

	@Override
	public List<Users> getUsers(String sTATUS_ACTIVE) throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("status", sTATUS_ACTIVE);
		return namedParameterJdbcTemplate.query(GET_ALL_USERS, paramMap,
				new BeanPropertyRowMapper(Users.class));
	}

}
