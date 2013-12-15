package com.videoclub.rental;

import java.util.ArrayList;

import com.videoclub.models.account.User;
import com.videoclub.models.movie.Movie;

public class Rental {

    /**
     * Rent movie to a member
     * 
     * @param user User
     * @param movie Movie
     * @return TRUE on success and FALSE on failure
     */
    public static boolean rentMovie(User user, Movie movie) {
        return true;
    }
    
    public static ArrayList<Movie> getAllMoviesAvailableForSale() {
        
        Movie m = new Movie();
        
        ArrayList<Movie> movies = m.getOldReleases();
        return movies;
    }
    
    public static void main(String [] args) {
        
        System.out.println("movies available for sale:");
        
        ArrayList<Movie> forSaleMovies = Rental.getAllMoviesAvailableForSale();
        
        for(Movie movie: forSaleMovies) {
            System.out.println(movie.getDescription().getTitle());
        }
        
    }
    
}
