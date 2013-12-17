package com.videoclub.InterfaceUtilisateur;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import com.videoclub.account.Account;
//import com.videoclub.controllers.Account;
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
	
	private int newMoviePrice;	//En cents
	private int oldMoviePrice;

	public static void main(String[] args)
	{
		VideoClub videoClub = new VideoClub();
		videoClub.start();
	}

	public void start()
	{
		System.out.println("start()");
		
		newMoviePrice = 1000;
		oldMoviePrice = 500;
		
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
	 * Buy items from database Items no longer avaible for sell to customers
	 * 
	 * @param itemSelection
	 */
	public void buyItems(Vector<SellableItem> itemSelection)
	{
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
	 * 
	 * @return movie choices
	 */
	public Vector<RentableMovie> getMovieChoices()
	{
		System.out.println("getMovieChoices()");

		Vector<RentableMovie> choices = new Vector<RentableMovie>();
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
		System.out.println("validManager(LoginInfo info)");
		System.out.println(info);

		return true;
	}

	/**
	 * Asks database if user exists
	 */
	public boolean validUser(LoginInfo info)
	{
		System.out.println("validUser(LoginInfo info)");
		System.out.println(info);
		
		return true;
	}

	/**
	 * Add a user to database
	 * 
	 * @param info
	 */
	public void createUser(LoginInfo info)
	{
		System.out.println("createUser(LoginInfo info)");
		System.out.println(info);
		
		User newUser = new User();
		newUser.setFirstName(info.getFirstName());
		newUser.setLastName(info.getLastName());
		newUser.setUsername(info.getName());
		newUser.setPassword(info.getPassword());
		
		try {
            newUser.save();
        } catch (SQLException e) {
            e.printStackTrace();
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
//		System.out.println("removeUsers(Vector<User> users)");
//		System.out.println(users);
		
		for (User user : users) {
		    try {
                user.remove();
            } catch (SQLException e) {
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
	public void removeItems(Vector<SellableItem> itemsToRemove)
	{
		System.out.println("removeItems(Vector<SellableItem> itemsToRemove)");
		System.out.println(itemsToRemove);
		// TODO Auto-generated method stub

	}

	/**
	 * Remove movies from database
	 * 
	 * @param moviesToRemove
	 */
	public void removeMovies(Vector<RentableMovie> moviesToRemove)
	{
		System.out.println("removeMovies(Vector<RentableMovie> moviesToRemove)");
		System.out.println(moviesToRemove);
		// TODO Auto-generated method stub

	}

	public int getMoviePrice(RentableMovie movie)
	{
		if(movie.isNewMovie())
			return newMoviePrice;
		else
			return oldMoviePrice;
	}
}
