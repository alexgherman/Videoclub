package com.videoclub.InterfaceUtilisateur;


/**
 * Dummy class
 * @author Maxime Dupuis
 *
 */
public class SellableItem
{
	private String name;
	private int price;	//Le prix est en cent(¢) pour être plus précis *ATTENTION*
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getPrice()
	{
		return price;
	}

	public void setPrice(int price)
	{
		this.price = price;
	}

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