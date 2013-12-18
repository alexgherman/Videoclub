package com.videoclub.InterfaceUtilisateur;

import java.util.ArrayList;

import com.videoclub.models.movie.Movie;

public class Cart
{
	private ArrayList<Movie> movies = new ArrayList<Movie>();

	public ArrayList<Movie> getMovies()
	{
		return movies;
	}

	public void setMovies(ArrayList<Movie> selectedMovies)
	{
		this.movies = selectedMovies;
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

		for (Movie movie : movies)
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
