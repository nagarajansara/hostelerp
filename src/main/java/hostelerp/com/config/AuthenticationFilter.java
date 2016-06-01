package hostelerp.com.config;

import hostelerp.com.controller.BaseController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter extends BaseController implements Filter
{

	public String SERVER_NAME = "localhost:8082";

	private String SESSION_FAILURE_URL = "http://" + SERVER_NAME
			+ "/api/login/getlogin";
	private String SESSION_FAILURE_PATH = "/api/login/getlogin";

	private List<String> AUTHENTICATED_URL = new ArrayList<String>();
	private List<String> AUTHENTICATED_URL_INDEXOF = new ArrayList<String>();
	private List<String> ROLE_CUSTOMER_URL = new ArrayList<String>();
	private List<String> ROLE_VENDOR_URL = new ArrayList<String>();
	private final String ROLE_CUSTOMER = "ROLE_CUSTOMER";
	private final String ROLE_VENDOR = "ROLE_VENDOR";

	private ServletContext context;

	public void init(FilterConfig fConfig) throws ServletException
	{
		this.context = fConfig.getServletContext();

		/************ View pages *****************************/

		AUTHENTICATED_URL.add("ping");
		AUTHENTICATED_URL.add("getlogin");
		AUTHENTICATED_URL.add("getlogin.json");
		AUTHENTICATED_URL.add("validate.json");
		AUTHENTICATED_URL.add("validate");
		AUTHENTICATED_URL.add("logout");

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();

		HttpSession session = req.getSession(false);
		Object userDetails =
				(session != null) ? session.getAttribute(ATTR_USER_ID) : null;

		String lastURIWord = uri.substring(uri.lastIndexOf("/") + 1);

		if (userDetails == null
				&& !(AUTHENTICATED_URL.indexOf(lastURIWord) >= 0))
		{

			boolean isAuthntificated = false;
			String lastURLIndex = uri.substring(0, uri.lastIndexOf("/"));
			String[] params = lastURLIndex.split("/");
			for (int i = 0; i < params.length; i++)
			{
				if (AUTHENTICATED_URL_INDEXOF.indexOf(params[i]) >= 0)
				{
					isAuthntificated = true;
					break;
				}
			}
			lastURLIndex =
					lastURLIndex.substring(lastURLIndex.lastIndexOf("/") + 1,
							lastURLIndex.length());

			if (AUTHENTICATED_URL_INDEXOF.indexOf(lastURLIndex) >= 0
					|| isAuthntificated)
			{
				chain.doFilter(request, response);
			} else
			{
				// res.sendRedirect(SESSION_FAILURE_URL);
				RequestDispatcher requestDispatcher =
						request.getRequestDispatcher(SESSION_FAILURE_PATH);
				requestDispatcher.forward(request, response);

			}

		} else
		{
			chain.doFilter(request, response);
		}

	}

	public void destroy()
	{

	}

}
