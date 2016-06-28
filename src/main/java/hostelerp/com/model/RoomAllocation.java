package hostelerp.com.model;

import java.io.Serializable;

public class RoomAllocation implements Serializable
{
	private int id;
	private int studentid;
	private int roomid;
	private int hostelid;
	private int roomno;
	private int roomallocationid;

	public int getRoomallocationid()
	{
		return roomallocationid;
	}

	public void setRoomallocationid(int roomallocationid)
	{
		this.roomallocationid = roomallocationid;
	}

	public int getRoomno()
	{
		return roomno;
	}

	public void setRoomno(int roomno)
	{
		this.roomno = roomno;
	}

	private int collegeid;

	public int getCollegeid()
	{
		return collegeid;
	}

	public void setCollegeid(int collegeid)
	{
		this.collegeid = collegeid;
	}

	public int getHostelid()
	{
		return hostelid;
	}

	public void setHostelid(int hostelid)
	{
		this.hostelid = hostelid;
	}

	private String roomname;
	private String studentname;
	private String collegename;
	private String hostelname;
	private String editBtn;
	private String rollno;
	private String status;
	private String roomallocationstatus;

	public String getLast_modified()
	{
		return last_modified;
	}

	public void setLast_modified(String last_modified)
	{
		this.last_modified = last_modified;
	}

	private String last_modified;

	public String getRoomallocationstatus()
	{
		return roomallocationstatus;
	}

	public void setRoomallocationstatus(String roomallocationstatus)
	{
		this.roomallocationstatus = roomallocationstatus;
	}

	public String getRollno()
	{
		return rollno;
	}

	public void setRollno(String rollno)
	{
		this.rollno = rollno;
	}

	public String getEditBtn()
	{
		return editBtn;
	}

	public void setEditBtn(String editBtn)
	{
		this.editBtn = editBtn;
	}

	public String getHostelname()
	{
		return hostelname;
	}

	public void setHostelname(String hostelname)
	{
		this.hostelname = hostelname;
	}

	public String getCollegename()
	{
		return collegename;
	}

	public void setCollegename(String collegename)
	{
		this.collegename = collegename;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getStudentid()
	{
		return studentid;
	}

	public void setStudentid(int studentid)
	{
		this.studentid = studentid;
	}

	public int getRoomid()
	{
		return roomid;
	}

	public void setRoomid(int roomid)
	{
		this.roomid = roomid;
	}

	public String getRoomname()
	{
		return roomname;
	}

	public void setRoomname(String roomname)
	{
		this.roomname = roomname;
	}

	public String getStudentname()
	{
		return studentname;
	}

	public void setStudentname(String studentname)
	{
		this.studentname = studentname;
	}

	public String getEntry_date()
	{
		return entry_date;
	}

	public void setEntry_date(String entry_date)
	{
		this.entry_date = entry_date;
	}

	public String getVacated_date()
	{
		return vacated_date;
	}

	public void setVacated_date(String vacated_date)
	{
		this.vacated_date = vacated_date;
	}

	public String getShifted_date()
	{
		return shifted_date;
	}

	public void setShifted_date(String shifted_date)
	{
		this.shifted_date = shifted_date;
	}

	private String entry_date;
	private String vacated_date;
	private String shifted_date;

	public RoomAllocation(int roomId, int studentId, String status, String date)
	{
		this.roomid = roomId;
		this.studentid = studentId;
		this.status = status;
		this.entry_date = date;
	}

	public RoomAllocation(int id, int roomId, int studentId, String date,
			String status)
	{
		this.id = id;
		this.roomid = roomId;
		this.studentid = studentId;
		this.status = status;
		this.last_modified = date;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public RoomAllocation()
	{

	}

}
