package hostelerp.com.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;

@ControllerAdvice
public class GlobalExceptionHandler
{

	private static final Logger logger = Logger
			.getLogger(GlobalExceptionHandler.class.getName());

	@ExceptionHandler(SQLException.class)
	public String handleSQLException(HttpServletRequest request, Exception ex)
	{
		logger.info("SQLException Occured:: URL=" + request.getRequestURL());
		return "database_error";
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND,
			reason = "IOException occured")
	@ExceptionHandler(IOException.class)
	public ModelAndView handleIOException()
	{
		logger.info("NOT_FOUND IOException occured :");
		ModelAndView model = new ModelAndView("404error");
		return model;
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ModelAndView handle404Error()
	{
		logger.info("NOT_FOUND :");
		ModelAndView model = new ModelAndView("404error");
		return model;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex)
	{
		logger.info("handleAllException :");
		ModelAndView model = new ModelAndView("error/errorexceptionerror");
		model.addObject("errMsg", ex.getMessage());
		model.addObject("name", ex.getClass().getSimpleName());
		return model;
	}
}
