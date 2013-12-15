package com.videoclub.InterfaceUtilisateur;

public class LoginInfo
{
	private String name;
	private String password;
	private String firstName;
	private String lastName;
	
	LoginInfo(String name, String password, String firstName, String lastName)
	{
		this.setName(name);
		this.setPassword(password);
		this.setFirstName(firstName);
		this.setLastName(lastName);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getFirstName() {
	    return firstName;
	}
	
	public void setFirstName(String firstName) {
	    this.firstName = firstName;
	}

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
	
}
