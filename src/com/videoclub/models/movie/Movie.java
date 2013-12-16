package com.videoclub.models.movie;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.videoclub.database.Database;
import com.videoclub.database.DatabaseTableName;
import com.videoclub.models.*;

public class Movie extends Common<Movie> implements CommonInterface<Movie> {
    
    HashMap<String, String> fieldValues = new HashMap<String, String>();
    
    private Integer id = null;
    
    private DescriptionMovie description = null;
    
    public Movie() {
        super(DatabaseTableName.MOVIES, Movie.class);
    }
    
    public Movie(Integer id) {
        super(DatabaseTableName.MOVIES, Movie.class);
        setId(id);
    }
    
    public Movie(DescriptionMovie description) {
        super(DatabaseTableName.MOVIES, Movie.class);
        this.description = description;
    }
    
    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        super.setId(id);
        this.id = id;
    }

    public DescriptionMovie getDescription() {
        return description;
    }

    public void setDescription(DescriptionMovie description) {
        this.description = description;
    }
    
    public String toString() {
        return description.toString();
    }

    /**
     * TODO: do this in the Common class, will do for now
     * @throws SQLException
     */
    public Integer create() throws SQLException {
//        super.create(fieldValues);
        
        String sql = "INSERT INTO " + tableName + " (DESCRIPTION_ID) "
                   + "VALUES (" + description.getId() + ");";
        
        return Database.instance().update(sql);
    }
    
    public void update() {
        
    }
    
    public Integer save() throws SQLException {
        if (id == null) {
            create();
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
    public Movie constructEntity(HashMap<String, String> row) throws InstantiationException, IllegalAccessException {
        
        DescriptionMovie descriptionArticle = new DescriptionMovie();
        descriptionArticle.setId(Integer.parseInt((String) row.get("DESCRIPTION_ID")));
        
        Movie article = new Movie();
        article.setId(Integer.parseInt((String) row.get("ID")));
        article.setDescription(descriptionArticle);
        
        return article;
    }
    
    public ArrayList<Movie> getOldReleases() {
        
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
        
        Movie m = new Movie();
        ArrayList<Movie> movies = null;
        try {
            movies = m.getbyDescriptinIds(descriptionIds);
        } catch (InstantiationException | IllegalAccessException e) {
            movies = new ArrayList<Movie>();
        }
        
        return movies;
    }
    
    public ArrayList<Movie> getbyDescriptinIds(ArrayList<Integer> ids) throws InstantiationException, IllegalAccessException {
        String sql = "SELECT * FROM " + tableName + " where DESCRIPTION_ID IN (" + implode(",", ids, false) + ")";
        
        return constructEntities(Database.instance().select(sql, DatabaseTableName.MOVIES));
    }
    
    public static void main(String [] args) {
        
//        DescriptionMovie description = new DescriptionMovie("code1", "Inception ", "blabla_description", "1387132283", true, 20.85f);
//        try {
//            description.save();
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        
//        Movie movie = new Movie();
//        movie.setDescription(description);
//        
//        try {
//            movie.save();
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        
//        System.out.println("Success");
        
    }
    
}
