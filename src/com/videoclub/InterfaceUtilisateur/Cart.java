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

	public ArrayList<com.videoclub.models.article.Article> getItems()
	{
		return items;
	}

	public void setItems(ArrayList<com.videoclub.models.article.Article> items)
	{
		this.items = items;
	}

	private ArrayList<com.videoclub.models.article.Article> items = new ArrayList<com.videoclub.models.article.Article>();
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

		for (com.videoclub.models.article.Article item : items)
		{
			total += item.getDescription().getPrice();
		}

		return total;
	}
}
