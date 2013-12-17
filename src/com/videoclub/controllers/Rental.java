package com.videoclub.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import com.videoclub.models.account.User;
import com.videoclub.models.movie.DescriptionMovie;
import com.videoclub.models.movie.Movie;
import com.videoclub.models.movie.MovieCopy;

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
    
    /**
     * Get all movies available for sale
     * @return
     */
    public static ArrayList<Movie> getAllMoviesAvailableForSale() {
        
        Movie m = new Movie();
        
        ArrayList<Movie> movies = m.getOldReleases();
        return movies;
    }
    
    /**
     * Add new movie
     * 
     * @param title
     * @param description
     * @param releaseDate
     * @param isNew
     * @param numberOfCopies
     * @return
     */
    public static boolean addNewMovie(String title, String description, String releaseDate, boolean isNew, Integer numberOfCopies) {
        
        DescriptionMovie movieDescription = new DescriptionMovie(DescriptionMovie.generateRandomCode(), title, description, releaseDate, true, 20.85f);
        Movie movie = new Movie();
        
        try {
            movieDescription.save();
            
            movie.setDescription(movieDescription);
            movie.save();
            System.out.println(movie);
            for(int i = 0; i < numberOfCopies; i++) {
                
                MovieCopy movieCopy = new MovieCopy();
                movieCopy.setMovie(movie);
                movieCopy.save();
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return true;
        
    }
    
    public static void main(String [] args) {
        
        System.out.println("movies available for sale:");
        
        ArrayList<Movie> forSaleMovies = Rental.getAllMoviesAvailableForSale();
        
        for(Movie movie: forSaleMovies) {
            System.out.println(movie.getDescription().getTitle());
        }
        
    }
    
}
