package com.videoclub.InterfaceUtilisateur;

import java.util.Vector;

public class Cart
{
	private Vector<RentableMovie> movies = new Vector<RentableMovie>();
	public Vector<RentableMovie> getMovies()
	{
		return movies;
	}

	public void setMovies(Vector<RentableMovie> movies)
	{
		this.movies = movies;
	}

	public Vector<SellableItem> getItems()
	{
		return items;
	}

	public void setItems(Vector<SellableItem> items)
	{
		this.items = items;
	}

	private Vector<SellableItem> items = new Vector<SellableItem>();
	private VideoClub videoClub;
	
	Cart(VideoClub videoClub)
	{
		this.videoClub = videoClub;
	}
	
	public void clear()
	{
		movies.clear();
		items.clear();
	}
	
	public int getTotal()
	{
		int total = 0; //En cents
		
		for(RentableMovie movie : movies)
		{
			videoClub.getMoviePrice(movie);
		}
		
		for(SellableItem item : items)
		{
			total += item.getPrice();
		}
		
		return total;
	}
}
