package com.videoclub.movie;

import java.sql.SQLException;
import java.util.ArrayList;
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
    
    public Movie(DescriptionMovie description) {
        super(DatabaseTableName.ARTICLES, Movie.class);
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
    public void create() throws SQLException {
//        super.create(fieldValues);
        
        String sql = "INSERT INTO articles (DESCRIPTION_ID) "
                   + "VALUES (" + description.getId() + ");";
        
        Database.instance().update(sql);
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
    
    
    public static void main(String [] args) {
        
        DescriptionMovie description = new DescriptionMovie("code1", "Coke", "blabla_description", 10.85f);
//        description.save();
        
        Movie article = new Movie();
        
        
        
//        article.save();
        
        System.out.println(article);
        
    }
    
}
