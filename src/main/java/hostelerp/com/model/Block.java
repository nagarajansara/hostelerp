package hostelerp.com.model;

import java.io.Serializable;

public class Block implements Serializable
{
	private int id;
	private String hostelname;
	private String collegename;

	public String getCollegename()
	{
		return collegename;
	}

	public void setCollegename(String collegename)
	{
		this.collegename = collegename;
	}

	private int hostelid;

	public int getHostelid()
	{
		return hostelid;
	}

	public void setHostelid(int hostelid)
	{
		this.hostelid = hostelid;
	}

	private String blockname;
	private String editBtn;
	private String status;

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getEditBtn()
	{
		return editBtn;
	}

	public void setEditBtn(String editBtn)
	{
		this.editBtn = editBtn;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getHostelname()
	{
		return hostelname;
	}

	public void setHostelname(String hostelname)
	{
		this.hostelname = hostelname;
	}

	public String getBlockname()
	{
		return blockname;
	}

	public void setBlockname(String blockname)
	{
		this.blockname = blockname;
	}

	public int getNooffloor()
	{
		return nooffloor;
	}

	public void setNooffloor(int nooffloor)
	{
		this.nooffloor = nooffloor;
	}

	public String getCreatedat()
	{
		return createdat;
	}

	public void setCreatedat(String createdat)
	{
		this.createdat = createdat;
	}

	private int nooffloor;
	private String createdat;

	public Block()
	{

	}

	public Block(int hostelid, String blockname, int nooffloor)
	{
		this.hostelid = hostelid;
		this.blockname = blockname;
		this.nooffloor = nooffloor;
	}

	public Block(int hostelid, String blockname, int nooffloor, int id)
	{
		this.hostelid = hostelid;
		this.blockname = blockname;
		this.nooffloor = nooffloor;
		this.id = id;
	}

}
