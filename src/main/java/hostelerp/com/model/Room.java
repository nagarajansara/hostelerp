package hostelerp.com.model;

import java.io.Serializable;

public class Room implements Serializable
{
	private int id;
	private int roomno;
	private int hostelid;
	private int blockid;
	private String roomtype;
	private String studentname;

	public String getStudentname()
	{
		return studentname;
	}

	public void setStudentname(String studentname)
	{
		this.studentname = studentname;
	}

	private int floorname;
	private int roomid;
	private int collegeid;

	public int getRoomid()
	{
		return roomid;
	}

	public void setRoomid(int roomid)
	{
		this.roomid = roomid;
	}

	public String getBlockname()
	{
		return blockname;
	}

	public void setBlockname(String blockname)
	{
		this.blockname = blockname;
	}

	private int noofperson;
	private String blockname;

	public String getCollegename()
	{
		return collegename;
	}

	public void setCollegename(String collegename)
	{
		this.collegename = collegename;
	}

	public int getCollegeid()
	{
		return collegeid;
	}

	public void setCollegeid(int collegeid)
	{
		this.collegeid = collegeid;
	}

	public String getHostelname()
	{
		return hostelname;
	}

	public void setHostelname(String hostelname)
	{
		this.hostelname = hostelname;
	}

	private String created_at;
	private String editBtn;
	private String collegename;
	private String hostelname;

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

	public int getRoomno()
	{
		return roomno;
	}

	public void setRoomno(int roomno)
	{
		this.roomno = roomno;
	}

	public int getHostelid()
	{
		return hostelid;
	}

	public void setHostelid(int hostelid)
	{
		this.hostelid = hostelid;
	}

	public int getBlockid()
	{
		return blockid;
	}

	public void setBlockid(int blockid)
	{
		this.blockid = blockid;
	}

	public String getRoomtype()
	{
		return roomtype;
	}

	public void setRoomtype(String roomtype)
	{
		this.roomtype = roomtype;
	}

	public int getFloorname()
	{
		return floorname;
	}

	public void setFloorname(int floorname)
	{
		this.floorname = floorname;
	}

	public int getNoofperson()
	{
		return noofperson;
	}

	public void setNoofperson(int noofperson)
	{
		this.noofperson = noofperson;
	}

	public String getCreated_at()
	{
		return created_at;
	}

	public void setCreated_at(String created_at)
	{
		this.created_at = created_at;
	}

	public Room()
	{

	}

	public Room(int blockid, int collegeid, int floorname, int hostelid,
			int noofperson, int roomno, String roomtype)
	{
		this.blockid = blockid;
		this.floorname = floorname;
		this.hostelid = hostelid;
		this.noofperson = noofperson;
		this.roomno = roomno;
		this.roomtype = roomtype;
	}

	public Room(int blockid, int collegeid, int floorname, int hostelid,
			int noofperson, int roomno, String roomtype, int id)
	{
		this.blockid = blockid;
		this.floorname = floorname;
		this.hostelid = hostelid;
		this.noofperson = noofperson;
		this.roomno = roomno;
		this.roomtype = roomtype;
		this.id = id;
	}

}
