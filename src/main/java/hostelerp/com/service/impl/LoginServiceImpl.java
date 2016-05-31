package hostelerp.com.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.util.*;

import hostelerp.com.service.*;
import hostelerp.com.dao.*;
import hostelerp.com.model.*;
import hostelerp.com.util.*;

public class LoginServiceImpl implements LoginService
{

	@Autowired
	@Qualifier("loginDAO")
	LoginDAO loginDAO;

	@Autowired
	@Qualifier("menuDAO")
	MenuDAO menuDAO;

	@Override
	public List<Login> validate(String email, String password,
			String sTATUS_ACTIVE) throws Exception
	{
		return loginDAO.validate(email, password, sTATUS_ACTIVE);
	}

	@Override
	public List<Menu> getMenus(String userRole) throws Exception
	{
		return menuDAO.getMenus(userRole);
	}

}
