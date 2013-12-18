package com.videoclub.models.movie.rent;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.videoclub.database.Database;
import com.videoclub.database.DatabaseTableName;
import com.videoclub.models.*;

public class RentArticle extends Common<RentArticle> implements CommonInterface<RentArticle> {
    
    HashMap<String, String> fieldValues = new HashMap<String, String>();
    
    private Integer id = null;
    private Integer rentId = null;
    private Integer movieId = null;
    
    
    public RentArticle() {
        super(DatabaseTableName.RENT_ARTICLES, RentArticle.class);
    }
    
    public RentArticle(Integer id) {
        super(DatabaseTableName.RENT_ARTICLES, RentArticle.class);
        setId(id);
    }
    
    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        super.setId(id);
        this.id = id;
    }
    
    public Integer getRentId() {
        return rentId;
    }

    public void setRentId(Integer rentId) {
        this.rentId = rentId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }
    
    public String toString() {
        return "[" + getId() + "] " + " ";
    }

    /**
     * TODO: do this in the Common class, will do for now
     * @throws SQLException
     */
    public Integer create() throws SQLException {
        
        String sql = "INSERT INTO " + tableName + " (RENT_ID, MOVIE_ID) "
                   + "VALUES (" + getRentId() + ", " + getMovieId() + ");";
        
        return Database.instance().update(sql);
    }
    
    public void update() {
        
    }
    
    public boolean save() throws SQLException {
        if (id == null) {
            Integer lastId = create();
            this.setId(lastId);
            return lastId > 0;
        } else {
            update();
            return true;
        }
    }
    
    /**
     * Construct the article entity from a resultSet row
     */
    @Override
    public RentArticle constructEntity(HashMap<String, String> row) throws InstantiationException, IllegalAccessException {
        RentArticle rentArticle = new RentArticle();
        rentArticle.setId(Integer.parseInt((String) row.get("ID")));
        rentArticle.setRentId(Integer.parseInt((String) row.get("RENT_ID")));
        rentArticle.setMovieId(Integer.parseInt((String) row.get("MOVIE_ID")));
        return rentArticle;
    }
    
    public static void main(String [] args) {
        
        RentArticle rentArticle = new RentArticle();
        rentArticle.setMovieId(23);
        rentArticle.setRentId(24);
        
        
        try {
            rentArticle.save();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.println("Success");
        
    }

}


