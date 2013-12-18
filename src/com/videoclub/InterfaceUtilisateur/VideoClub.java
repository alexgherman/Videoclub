package com.videoclub.InterfaceUtilisateur;

import java.sql.SQLException;
import java.util.ArrayList;

import com.videoclub.controllers.Account;
import com.videoclub.controllers.Rental;
import com.videoclub.models.account.User;
import com.videoclub.models.movie.Movie;

/**
 * Lien entre l'interface graphique et la database Contr�leur
 * 
 * @author Maxime Dupuis
 */
public class VideoClub
{
	private final String name = "Video Club Awesome";

	private int newMoviePrice = 1000;; // En cents
	private int oldMoviePrice = 500;

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
	 * 
	 * @param location
	 */
	public void addRentableMovie(RentableMovie location)
	{
		System.out.println("addRentableMovie(RentableMovie location)");
		System.out.println(location + " saved to rentableMovies");
	}

	/**
	 * Add item to database
	 * 
	 * @param article
	 */
	public void addItem(SellableItem article)
	{
		System.out.println("addArticle(SellableItem article)");
		System.out.println(article + " saved to sellableItems");
	}

	/**
	 * Rent item from database Item no longer avaible to customer until returned
	 * 
	 * @param movieSelection
	 * @param movieRentingCustomer
	 */
	public void rentMovies(ArrayList<RentableMovie> movieSelection, LoginInfo movieRentingCustomer)
	{
		System.out.println("pickMovies(ArrayList<RentableMovie> movieSelection)");
		System.out.println("Movies selected: \n[");
		for (RentableMovie movie : movieSelection)
		{
			System.out.println("\t" + movie.toString());
		}
		System.out.println("]");
	}

	/**
	 * Buy items from database Items no longer avaible for sell to customers
	 * 
	 * @param itemSelection
	 */
	public void buyItems(ArrayList<SellableItem> itemSelection)
	{
		System.out.println("pickItems(ArrayList<SellableItem> itemSelection)");
		System.out.println("Items selected: \n[");
		for (SellableItem item : itemSelection)
		{
			System.out.println("\t" + item.toString());
		}
		System.out.println("]");
	}

	/**
	 * Asks database for movie choices
	 * 
	 * @return movie choices
	 */
	public ArrayList<RentableMovie> getMovieChoices()
	{
		System.out.println("getMovieChoices()");

		ArrayList<RentableMovie> choices = new ArrayList<RentableMovie>();
		choices.add(new RentableMovie("Les totons volants", true));
		choices.add(new RentableMovie("Martine au club �changiste", false));
		choices.add(new RentableMovie("Les foufounes rouges", false));
		choices.add(new RentableMovie("Lucile va au garage", false));
		choices.add(new RentableMovie("Bleu nuit version Miley Cyrus", false));
		choices.add(new RentableMovie("Les tentacules au Japon", false));

		return choices;
	}

	/**
	 * Asks database for item choices
	 * 
	 * @return item choices
	 */
	public ArrayList<SellableItem> getItemChoices()
	{
		System.out.println("getItemChoices()");

		ArrayList<SellableItem> choices = new ArrayList<SellableItem>();
		choices.add(new SellableItem("Chips", 150)); // Prix en cents btw
		choices.add(new SellableItem("Liqueur", 200));
		choices.add(new SellableItem("Bonbons", 100));
		choices.add(new SellableItem("Jouet", 500));
		choices.add(new SellableItem("La matrice IV", 2000));
		choices.add(new SellableItem("Les samurai pas fins", 2000));

		return choices;
	}

	/**
	 * Asks database for a list of all the usernames
	 */
	public ArrayList<User> getUserChoices()
	{
		ArrayList<User> choices = Account.getUsers();
		return choices;
	}

	/**
	 * Name of the videoclub
	 * 
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
		User u = new User();
		User manager = null; // potential manager
		try
		{
			System.out.println("name");
			System.out.println(info.getName());
			manager = u.getByUsername(info.getName());
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return manager != null && manager.isManager();
	}

	/**
	 * Asks database if user exists
	 */
	public boolean validUser(LoginInfo info)
	{
		System.out.println("validUser(LoginInfo info)");
		System.out.println(info);

		return false;
	}

	/**
	 * Add a user to database
	 * 
	 * @param info
	 */
	public boolean createUser(LoginInfo info)
	{
		System.out.println("createUser(LoginInfo info)");
		System.out.println(info);

		User newUser = new User();
		newUser.setFirstName(info.getFirstName());
		newUser.setLastName(info.getLastName());
		newUser.setUsername(info.getName());
		newUser.setPassword(info.getPassword());

		try
		{
			newUser.save();
			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}

		// TODO: Success msg after creation
	}

	/**
	 * Remove user from database
	 * 
	 * @param name
	 */
	public void removeUsers(ArrayList<User> users)
	{
		// System.out.println("removeUsers(ArrayList<User> users)");
		// System.out.println(users);

		for (User user : users)
		{
			try
			{
				user.remove();
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Remove items from database
	 * 
	 * @param itemsToRemove
	 */
	public void removeItems(ArrayList<SellableItem> itemsToRemove)
	{
		System.out.println("removeItems(ArrayList<SellableItem> itemsToRemove)");
		System.out.println(itemsToRemove);
		// TODO Auto-generated method stub

	}

	/**
	 * Remove movies from database
	 * 
	 * @param moviesToRemove
	 */
	public void removeMovies(ArrayList<RentableMovie> moviesToRemove)
	{
		System.out.println("removeMovies(ArrayList<RentableMovie> moviesToRemove)");
		System.out.println(moviesToRemove);
		// TODO Auto-generated method stub

	}

	public int getMoviePrice(RentableMovie movie)
	{
		if (movie.isNewMovie())
			return newMoviePrice;
		else
			return oldMoviePrice;
	}
}
