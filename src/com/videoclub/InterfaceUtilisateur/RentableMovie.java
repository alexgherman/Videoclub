package com.videoclub.InterfaceUtilisateur;

/**
 * Dummy class
 * 
 * @author Maxime Dupuis
 * 
 */
public class RentableMovie
{
	private String name;
	private boolean isNewMovie;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public boolean isNewMovie()
	{
		return isNewMovie;
	}

	public void setNewMovie(boolean isNewMovie)
	{
		this.isNewMovie = isNewMovie;
	}

	public RentableMovie(String name, boolean isNewMovie)
	{
		this.name = name;
		this.isNewMovie = isNewMovie;
	}

	public String toString()
	{
		String str = name + ": ";

		if (isNewMovie)
			str += "(Nouveauté)";
		else
			str += "(Régulier)";

		return str;
	}
}
