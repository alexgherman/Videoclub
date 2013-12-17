package com.videoclub.InterfaceUtilisateur;

import java.util.ArrayList;

public class Cart
{
	private ArrayList<RentableMovie> movies = new ArrayList<RentableMovie>();

	public ArrayList<RentableMovie> getMovies()
	{
		return movies;
	}

	public void setMovies(ArrayList<RentableMovie> movies)
	{
		this.movies = movies;
	}

	public ArrayList<SellableItem> getItems()
	{
		return items;
	}

	public void setItems(ArrayList<SellableItem> items)
	{
		this.items = items;
	}

	private ArrayList<SellableItem> items = new ArrayList<SellableItem>();
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

		for (RentableMovie movie : movies)
		{
			total += videoClub.getMoviePrice(movie);
		}

		for (SellableItem item : items)
		{
			total += item.getPrice();
		}

		return total;
	}
}
