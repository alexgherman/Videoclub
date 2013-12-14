package com.videoclub.InterfaceUtilisateur;


/**
 * Dummy class
 * @author Maxime Dupuis
 *
 */
public class RentableMovie
{
	String name;
	boolean isNewMovie;
	
	public RentableMovie(String name, boolean isNewMovie)
	{
		this.name = name;
		this.isNewMovie = isNewMovie;
	}
	
	public String toString()
	{
		String str = name + ": ";
		
		if(isNewMovie)
			str += "(Nouveauté)";
		else
			str += "(Régulier)";		
		
		return str;
	}
}
