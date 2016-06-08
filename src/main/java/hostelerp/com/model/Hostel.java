package hostelerp.com.model;

import java.io.Serializable;

public class Hostel implements Serializable
{
	private int id;
	private String name;
	private String collegename;
	private String image;
	private String landlineno;
	private String mobileno;
	private String address;
	private String state;
	private String status;

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getEditbtn()
	{
		return editbtn;
	}

	public void setEditbtn(String editbtn)
	{
		this.editbtn = editbtn;
	}

	private String city;
	private String editbtn;

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

	public String getCollegename()
	{
		return collegename;
	}

	public void setCollegename(String collegename)
	{
		this.collegename = collegename;
	}

	public String getImage()
	{
		return image;
	}

	public void setImage(String image)
	{
		this.image = image;
	}

	public String getLandlineno()
	{
		return landlineno;
	}

	public void setLandlineno(String landlineno)
	{
		this.landlineno = landlineno;
	}

	public String getMobileno()
	{
		return mobileno;
	}

	public void setMobileno(String mobileno)
	{
		this.mobileno = mobileno;
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

	public String getCreatedat()
	{
		return createdat;
	}

	public void setCreatedat(String createdat)
	{
		this.createdat = createdat;
	}

	private String country;
	private String createdat;

	public Hostel()
	{

	}

	public Hostel(String name, String collegename, String mobileno,
			String address, String state, String city, String country, int id)
	{
		this.name = name;
		this.collegename = collegename;
		this.mobileno = mobileno;
		this.address = address;
		this.state = state;
		this.city = city;
		this.country = country;
		this.id = id;

	}

	public Hostel(String name, String collegename, String mobileno,
			String address, String state, String city, String country)
	{
		this.name = name;
		this.collegename = collegename;
		this.mobileno = mobileno;
		this.address = address;
		this.state = state;
		this.city = city;
		this.country = country;

	}
}
