package hostelerp.com.model;

import java.io.Serializable;

public class Student implements Serializable
{

	private int id;
	private int collegeid;

	private String name;
	private String rollno;
	private String batch;
	private String course;
	private String messtype;
	private String address;
	private String state;
	private String city;
	private String country;

	public String getCollegeName()
	{
		return collegeName;
	}

	public void setCollegeName(String collegeName)
	{
		this.collegeName = collegeName;
	}

	private String mobileno;
	private String collegeName;

	public String getMobileno()
	{
		return mobileno;
	}

	public void setMobileno(String mobileno)
	{
		this.mobileno = mobileno;
	}

	public String getEditBtn()
	{
		return editBtn;
	}

	public void setEditBtn(String editBtn)
	{
		this.editBtn = editBtn;
	}

	private String status;
	private String created_at;
	private String editBtn;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getRollno()
	{
		return rollno;
	}

	public void setRollno(String rollno)
	{
		this.rollno = rollno;
	}

	public String getBatch()
	{
		return batch;
	}

	public void setBatch(String batch)
	{
		this.batch = batch;
	}

	public String getCourse()
	{
		return course;
	}

	public void setCourse(String course)
	{
		this.course = course;
	}

	public String getMesstype()
	{
		return messtype;
	}

	public void setMesstype(String messtype)
	{
		this.messtype = messtype;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getCreated_at()
	{
		return created_at;
	}

	public void setCreated_at(String created_at)
	{
		this.created_at = created_at;
	}

	public Student()
	{

	}

	public Student(String name, String rollno, String batch, String course,
			String messtype, String address, String state, String city,
			String country, String mobileno, int collegeId)
	{
		this.name = name;
		this.rollno = rollno;
		this.batch = batch;
		this.course = course;
		this.messtype = messtype;
		this.address = address;
		this.state = state;
		this.city = city;
		this.country = country;
		this.mobileno = mobileno;
		this.collegeid = collegeId;
	}

	public int getCollegeid()
	{
		return collegeid;
	}

	public void setCollegeid(int collegeid)
	{
		this.collegeid = collegeid;
	}

	public Student(String name, String batch, String course, String messtype,
			String address, String state, String city, String country,
			String mobileno, int id)
	{
		this.name = name;
		this.batch = batch;
		this.course = course;
		this.messtype = messtype;
		this.address = address;
		this.state = state;
		this.city = city;
		this.country = country;
		this.mobileno = mobileno;
		this.id = id;
	}

	public Student(String name, String batch, String course, String messtype,
			String address, String state, String city, String country,
			String mobileno, int id, String rollNo, int collegeId)
	{
		this.name = name;
		this.batch = batch;
		this.course = course;
		this.messtype = messtype;
		this.address = address;
		this.state = state;
		this.city = city;
		this.country = country;
		this.mobileno = mobileno;
		this.id = id;
		this.rollno = rollNo;
		this.collegeid = collegeId;
	}
}
