package hostelerp.com.datatable;

import java.util.List;

@SuppressWarnings(
{ "unused", "unchecked" })
public class CommonDataTableJsonObj<T>
{
	int iTotalRecords;

	int iTotalDisplayRecords;

	String sEcho;

	String sColumns;

	T aaData;

	public int getiTotalRecords()
	{
		return iTotalRecords;
	}

	public void setiTotalRecords(int iTotalRecords)
	{
		this.iTotalRecords = iTotalRecords;
	}

	public int getiTotalDisplayRecords()
	{
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords)
	{
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public String getsEcho()
	{
		return sEcho;
	}

	public void setsEcho(String sEcho)
	{
		this.sEcho = sEcho;
	}

	public String getsColumns()
	{
		return sColumns;
	}

	public void setsColumns(String sColumns)
	{
		this.sColumns = sColumns;
	}

	public T getAaData()
	{
		return aaData;
	}

	public void setAaData(T aaData)
	{
		this.aaData = aaData;
	}

}
