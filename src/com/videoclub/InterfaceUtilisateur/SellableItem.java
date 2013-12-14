package com.videoclub.InterfaceUtilisateur;


/**
 * Dummy class
 * @author Maxime Dupuis
 *
 */
public class SellableItem
{
	String name;
	int price;	//Le prix est en cent(¢) pour être plus précis *ATTENTION*
	
	public SellableItem(String nom, int prix)
	{
		this.name = nom;
		this.price = prix;
	}
	
	public String toString()
	{
		String str = name + ": " + (float)price / 100 + "$";
		return str;
	}
}