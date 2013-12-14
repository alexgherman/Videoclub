package com.videoclub.InterfaceUtilisateur;

import java.util.Vector;

/**
 * Lien entre l'interface graphique et la database
 * Contrôleur
 * 
 * @author Maxime Dupuis
 */
public class VideoClub
{
	final String name = "Video Club Awesome";

	public static void main(String[] args)
	{
		VideoClub videoClub = new VideoClub();
		videoClub.start();
	}

	public void start()
	{
		System.out.println("start()");
		WelcomeWindow win = new WelcomeWindow(this);
		win.setVisible(true);
	}

	/**
	 * Add movie to database
	 * @param location
	 */
	public void addRentableMovie(RentableMovie location)
	{
		System.out.println("addRentableMovie(RentableMovie location)");
		System.out.println(location + " saved to rentableMovies");
	}

	/**
	 * Add item to database
	 * @param article
	 */
	public void addItem(SellableItem article)
	{
		System.out.println("addArticle(SellableItem article)");
		System.out.println(article + " saved to sellableItems");
	}

	/**
	 * Rent item from database
	 * Item no longer avaible to customer until returned
	 * @param movieSelection
	 */
	public void rentMovies(Vector<RentableMovie> movieSelection)
	{
		System.out.println("pickMovies(Vector<RentableMovie> movieSelection)");
		System.out.println("Movies selected: \n[");
		for (RentableMovie movie : movieSelection)
		{
			System.out.println("\t" + movie.toString());
		}
		System.out.println("]");
	}
	
	/**
	 * Buy items from database
	 * Items no longer avaible for sell to customers
	 * @param itemSelection
	 */
	public void buyItems(Vector<SellableItem> itemSelection)
	{
		// On pourrait ajouter les articles au panier
		System.out.println("pickItems(Vector<SellableItem> itemSelection)");
		System.out.println("Items selected: \n[");
		for (SellableItem item : itemSelection)
		{
			System.out.println("\t" + item.toString());
		}
		System.out.println("]");
	}

	/**
	 * Asks database for movie choices
	 * @return movie choices
	 */
	public Vector<RentableMovie> getMovieChoices()
	{
		System.out.println("getMovieChoices()");

		Vector<RentableMovie> choices = new Vector<RentableMovie>();
		choices.add(new RentableMovie("Les totons volants", true));
		choices.add(new RentableMovie("Martine au club échangiste", false));
		choices.add(new RentableMovie("Les foufounes rouges", false));
		choices.add(new RentableMovie("Lucile va au garage", false));
		choices.add(new RentableMovie("Bleu nuit version Miley Cyrus", false));
		choices.add(new RentableMovie("Les tentacules au Japon", false));

		return choices;
	}

	/**
	 * Asks database for item choices
	 * @return item choices
	 */
	public Vector<SellableItem> getItemChoices()
	{
		System.out.println("getMovieChoices()");

		Vector<SellableItem> choices = new Vector<SellableItem>();
		choices.add(new SellableItem("Chips", 150)); // Prix en cents btw
		choices.add(new SellableItem("Liqueur", 200));
		choices.add(new SellableItem("Bonbons", 100));
		choices.add(new SellableItem("Jouet", 500));
		choices.add(new SellableItem("La matrice IV", 2000));
		choices.add(new SellableItem("Les samurai pas fins", 2000));

		return choices;
	}

	/**
	 * Name of the videoclub
	 * @return
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Asks database if manager exists
	 */
	public boolean validManager(LoginInfo info)
	{
		System.out.println("hasManagerRights(LoginInfo info)");
		
		return true;
	}
	
	/**
	 * Asks database if user exists
	 */
	public boolean validUser(LoginInfo info)
	{
		return true;
	}
	
	/**
	 * Add a user to database
	 * @param info
	 */
	public void createUser(LoginInfo info)
	{
		
	}
	
	/**
	 * Remove user from database
	 * @param name
	 */
	public void removeUser(String name)
	{
		
	}

	/**
	 * Remove items from database
	 * @param itemsToRemove
	 */
	public void removeItems(Vector<SellableItem> itemsToRemove)
	{
		// TODO Auto-generated method stub
		
	}

	/**
	 * Remove movies from database
	 * @param moviesToRemove
	 */
	public void removeMovies(Vector<RentableMovie> moviesToRemove)
	{
		// TODO Auto-generated method stub
		
	}
}
