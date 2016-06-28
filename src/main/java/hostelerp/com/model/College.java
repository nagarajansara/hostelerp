package hostelerp.com.model;

import java.io.Serializable;

public class College implements Serializable
{
	private int id;
	private String name;
	private String image;
	private String address;
	private String state;
	private String city;
	private String mobileno;
	private String editBtn;

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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getImage()
	{
		return image;
	}

	public void setImage(String image)
	{
		this.image = image;
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

	public String getcountry()
	{
		return country;
	}

	public void setcountry(String country)
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

	private String country;
	private String status;
	private String created_at;

	public College()
	{

	}

	public College(String name, String address, String state, String city,
			String country, String mobileno)
	{
		this.name = name;
		this.address = address;
		this.state = state;
		this.city = city;
		this.country = country;
		this.mobileno = mobileno;
	}

	public String getMobileno()
	{
		return mobileno;
	}

	public void setMobileno(String mobileno)
	{
		this.mobileno = mobileno;
	}

	public College(String name, String address, String state, String city,
			String country, String mobileno, int id)
	{
		this.name = name;
		this.address = address;
		this.state = state;
		this.city = city;
		this.country = country;
		this.mobileno = mobileno;
		this.id = id;
	}

}
