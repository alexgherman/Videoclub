package com.videoclub.InterfaceUtilisateur;

import java.sql.SQLException;
import java.util.ArrayList;

import com.videoclub.controllers.Account;
import com.videoclub.controllers.Article;
import com.videoclub.controllers.Rental;
import com.videoclub.models.account.User;
import com.videoclub.models.movie.DescriptionMovie;
import com.videoclub.models.movie.Movie;
import com.videoclub.models.movie.Order;
import com.videoclub.models.movie.purchase.PurchaseArticle;
import com.videoclub.models.movie.rent.RentArticle;

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
	 * @param arrayList
	 * @param movieRentingCustomer
	 */
	public void rentMovies(ArrayList<Movie> arrayList, Order order)
	{
        
		for (Movie movie : arrayList)
		{
		    RentArticle rentArticle = new RentArticle();
		    rentArticle.setMovieId(movie.getId());
		    rentArticle.setOrderId(order.getId());
		    try {
                rentArticle.save();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
		}
	}

	/**
	 * Buy items from database Items no longer avaible for sell to customers
	 * 
	 * @param itemSelection
	 */
	public void buyItems(ArrayList<com.videoclub.models.article.Article> itemSelection, Order order)
	{
		for (com.videoclub.models.article.Article item : itemSelection)
		{
		    PurchaseArticle purchaseArticle = new PurchaseArticle();
		    purchaseArticle.setArticleId(item.getId());
		    purchaseArticle.setOrderId(order.getId());
		    try {
		        
		        purchaseArticle.save();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
		}
	}

	/**
	 * Asks database for movie choices
	 * 
	 * @return movie choices
	 */
	public ArrayList<Movie> getMovieChoices()
	{
		Movie m = new Movie();
		DescriptionMovie dm = new DescriptionMovie();
		ArrayList<Movie> choices = new ArrayList<Movie>();
        
        try {
            choices = m.getAll();
            for (Movie movie : choices) {
                dm = dm.getById(movie.getDescription().getId());
                movie.setDescription(dm);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return choices;
	}

	/**
	 * Asks database for item choices
	 * 
	 * @return item choices
	 */
	public ArrayList<com.videoclub.models.article.Article> getItemChoices()
	{

		ArrayList<com.videoclub.models.article.Article> choices = Article.getArticles();

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
	    User u = new User();
        User user = null;
        try
        {
            user = u.getByUsername(info.getName());
        }
        catch (InstantiationException | IllegalAccessException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return user != null && user.isRegular();
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
	public void removeItems(ArrayList<com.videoclub.models.article.Article> itemsToRemove)
	{
		System.out.println("removeItems(ArrayList<SellableItem> itemsToRemove)");
		System.out.println(itemsToRemove);
		// TODO Auto-generated method stub

	}

	/**
	 * Remove movies from database
	 * 
	 * @param selectedMovies
	 */
	public void removeMovies(ArrayList<Movie> selectedMovies)
	{
		System.out.println("removeMovies(ArrayList<Movie> moviesToRemove)");
		System.out.println(selectedMovies);
		// TODO Auto-generated method stub

	}

	public int getMoviePrice(Movie movie)
	{
		if (movie.getDescription().isNewRelease())
			return newMoviePrice;
		else
			return oldMoviePrice;
	}
	
}
