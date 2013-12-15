package com.videoclub.models.movie;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.videoclub.database.Database;
import com.videoclub.database.DatabaseTableName;
import com.videoclub.models.*;

public class MovieCopy extends Common<MovieCopy> implements CommonInterface<MovieCopy> {
    
    HashMap<String, String> fieldValues = new HashMap<String, String>();
    
    private Integer id = null;
    
    private Movie movie = null;
    
    public MovieCopy() {
        super(DatabaseTableName.MOVIE_COPIES, MovieCopy.class);
    }
    
    public MovieCopy(Movie movie) {
        super(DatabaseTableName.MOVIE_COPIES, MovieCopy.class);
        this.movie = movie;
    }
    
    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        super.setId(id);
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    
    public String toString() {
        return getId() + " - " + movie.toString();
    }

    /**
     * TODO: do this in the Common class, will do for now
     * @throws SQLException
     */
    public void create() throws SQLException {
//        super.create(fieldValues);
        
        String sql = "INSERT INTO " + tableName + " (MOVIE_ID) "
                   + "VALUES (" + movie.getId() + ");";
        
        Database.instance().update(sql);
    }
    
    public void update() {
        
    }
    
    public Integer save() {
        if (id == null) {
            try {
                create();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Integer lastId = Database.instance().getLastInsertedId();
            this.setId(lastId);
            return lastId;
        } else {
            update();
            return 0;
        }
    }
    
    /**
     * Construct the article entity from a resultSet row
     */
    @Override
    public MovieCopy constructEntity(HashMap<String, String> row) throws InstantiationException, IllegalAccessException {
        
        Movie movie = new Movie();
        movie.setId(Integer.parseInt((String) row.get("MOVIE_ID")));
        
        MovieCopy movieCopy = new MovieCopy();
        movieCopy.setId(Integer.parseInt((String) row.get("ID")));
        movieCopy.setMovie(movie);
        
        return movieCopy;
    }
    
    public ArrayList<MovieCopy> getOldReleases() {
        
        DescriptionMovie dm = new DescriptionMovie();
        ArrayList<DescriptionMovie> descriptions = null;
        try {
            descriptions = dm.getOldReleases();
        } catch (InstantiationException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        // collect descriptions
        HashMap<Integer, DescriptionMovie> descriptionMap = new HashMap<Integer, DescriptionMovie>();
        
        for (DescriptionMovie description: descriptions) {
            if (description.isNewRelease() == false) {
                descriptionMap.put(description.getId(), description);
            }
        }
        
        // collect the description ids
        ArrayList<Integer> descriptionIds = new ArrayList<Integer>(descriptionMap.keySet());
        
//        System.out.println("list: "+ descriptionIds);
        
        MovieCopy m = new MovieCopy();
        ArrayList<MovieCopy> movies = null;
        try {
            movies = m.getbyMovieIds(descriptionIds);
        } catch (InstantiationException | IllegalAccessException e) {
            movies = new ArrayList<MovieCopy>();
        }
        
        return movies;
    }
    
    public ArrayList<MovieCopy> getbyMovieIds(ArrayList<Integer> ids) throws InstantiationException, IllegalAccessException {
        String sql = "SELECT * FROM " + tableName + " where MOVIE_ID IN (" + implode(",", ids, false) + ")";
        
        return constructEntities(Database.instance().select(sql, DatabaseTableName.MOVIES));
    }
    
    public static void main(String [] args) {
        MovieCopy movieCopy = new MovieCopy();
        movieCopy.setMovie(new Movie(1));
        movieCopy.save();
    }
    
}
