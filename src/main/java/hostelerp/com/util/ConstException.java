package hostelerp.com.util;

public class ConstException extends Exception
{
	public static int ERR_CODE_SUCCESS = 200;
	public static int ERR_CODE_UNKNOWN = -1;
	public static int ERR_CODE_DB_ERROR = 2;
	public static int ERR_CODE_VIEW_PAGE = 12;
	public static int ERR_CODE_INVALID_LOGIN = 3;
	public static int ERR_CODE_INVALID_THREAD_STOP = 5;
	public static int ERR_CODE_BLANK_DATA = 15;
	public static int ERR_CODE_MULTIPART_FORM_DATA = 16;
	public static int ERR_CODE_FILE_RENAME = 20;
	public static int ERR_CODE_NO_DATA = 21;
	public static int ERR_CODE_NO_CREDITS = 22;
	public static int ERR_CODE_ACCESS_DENIED = 25;
	public static int ERR_CODE_DUPLICATE_ENTERY = 26;
	public static int ERR_CODE_REFERENCES_KEY = 27;
	public static int ERR_CODE_MAX_FLOOR = 28;
	public static int ERR_CODE_DUPLICATE_ROOM_ALLOCATION = 29;
	public static int ERR_CODE_ROOM_ALLOCATION_MAX_RECORS = 29;
	public static int ERR_CODE_ROOM_NOT_AVAILABLE = 30;

	public static String ERR_MSG_SUCCESS = "Success";
	public static String ERR_MSG_SESSION_EXP = "Session expired";
	public static String ERR_MSG_INVALID_LOGIN = "Invalid login";
	public static String ERR_MSG_BLANK_DATA = "Please fill all the data";
	public static String ERR_MSG_MULTIPART_FORM_DATA =
			"This is not a multipart form data";
	public static String ERR_MSG_FILE_RENAME = "File rename failed";
	public static String ERR_MSG_NO_DATA = "No data found";
	public static String ERR_MSG_NO_CREDITS = "No credits";
	public static String ERR_MSG_ACCESS_DENIED = "Access Denied";
	public static String ERR_MSG_DUPLICATE_ENTERY = "Already registered";
	public static String ERR_MSG_REFERENCES_KEY =
			"This entry reference used another table. Please delete that table entry";
	public static String ERR_MSG_MAX_FLOOR = "Floor is exceed";
	public static String ERR_MSG_DUPLICATE_ROOM_ALLOCATION =
			"Room is alloted already";
	public static String ERR_MSG_ROOM_ALLOCATION_MAX_RECORS =
			"Max room allocation entries";
	public static String ERR_MSG_ROOM_NOT_AVAILABLE =
			"Room seat not available";


	int code = this.ERR_CODE_UNKNOWN;

	public ConstException()
	{
		super();
	}

	public ConstException(String msg)
	{
		super(msg);
	}

	public ConstException(int code, String msg)
	{
		super(msg);
		setCode(code);
	}

	public int getCode()
	{
		return code;
	}

	public void setCode(int code)
	{
		this.code = code;
	}
}
