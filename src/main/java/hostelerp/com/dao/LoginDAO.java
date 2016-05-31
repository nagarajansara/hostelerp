package hostelerp.com.dao;

import java.io.*;
import java.util.*;

import hostelerp.com.model.*;

@SuppressWarnings("unused")
public interface LoginDAO
{

	List<Login> validate(String email, String password, String sTATUS_ACTIVE)
			throws Exception;

}
