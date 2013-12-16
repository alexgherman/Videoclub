package com.videoclub.InterfaceUtilisateur;

/**
 * Dummy class
 * @author Maxime Dupuis
 *
 */
public class User
{
	protected String firstName;
    protected String lastName;
	
	User(String firstName, String lastName)
	{
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getName()
	{
		return firstName + " " + lastName;
	}
}
