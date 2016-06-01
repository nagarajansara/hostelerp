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

}
