package com.videoclub.InterfaceUtilisateur;

public class LoginInfo
{
	private String name;
	private String password;
	
	LoginInfo(String name, String password)
	{
		this.setName(name);
		this.setPassword(password);
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
	
	
}
