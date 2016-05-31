package hostelerp.com.dao;

import java.io.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import hostelerp.com.model.*;
import hostelerp.com.util.Response;

@SuppressWarnings(
{ "unused", "unchecked" })
public class LoginDAOImpl implements LoginDAO
{
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	final String GET_USER_VALIDATE =
			"Select * from users where username =:username AND password =:password AND status =:status";

	@Override
	public List<Login> validate(String email, String password, String sTATUS_ACTIVE)
			throws Exception
	{
		Map paramMap = new HashMap();
		paramMap.put("username", email);
		paramMap.put("password", password);
		paramMap.put("status", sTATUS_ACTIVE);

		return namedParameterJdbcTemplate.query(GET_USER_VALIDATE, paramMap,
				new BeanPropertyRowMapper<Login>(Login.class));

	}

}
