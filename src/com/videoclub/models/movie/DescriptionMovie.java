package com.videoclub.models.movie;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.videoclub.database.Database;
import com.videoclub.database.DatabaseTableName;
import com.videoclub.models.Common;
import com.videoclub.models.CommonInterface;

public class DescriptionMovie extends Common<DescriptionMovie> implements CommonInterface<DescriptionMovie> {

    private Integer id = null;
    
    private String code;
    
    private String title;
    
    private String description;
    
    private String releaseDate;
    
    private boolean newRelease;
    
    private float price;

    public DescriptionMovie() {
        super(DatabaseTableName.DESCRIPTION_MOVIES, DescriptionMovie.class);
    }
    
    public DescriptionMovie(String code, String title, String description, String releaseDate, boolean newRelease, float price) {
        super(DatabaseTableName.DESCRIPTION_MOVIES, DescriptionMovie.class);
        this.code = code;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.newRelease = newRelease;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        super.setId(id);
        this.id = id;
    }
    
    /**
     * Generates a random code
     * 
     * @return random code
     */
    public static String generateRandomCode() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isNewRelease() {
        return newRelease;
    }

    public void setNewRelease(boolean newRelease) {
        this.newRelease = newRelease;
    }
    
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    public String toString() {
        return this.title + " [" + this.price + "]";
    }
    
    public ArrayList<DescriptionMovie> getOldReleases() throws InstantiationException, IllegalAccessException {
        String sql = "SELECT * FROM " + tableName + " where NEW = 0";
        
        return constructEntities(Database.instance().select(sql, DatabaseTableName.DESCRIPTION_MOVIES));
    }
    
    /**
     * TODO: do this in the Common class, will do for now
     * @throws SQLException
     */
    public Integer create() throws SQLException {

        String sql = "INSERT INTO " + DatabaseTableName.DESCRIPTION_MOVIES +" ("
                + "CODE,"
                + "TITLE,"
                + "DESCRIPTION,"
                + "RELEASE_DATE,"
                + "NEW,"
                + "PRICE" +
         ") VALUES ("
                + "'" + code + "', "
                + "'" + title + "', "
                + "'" + description + "', "
                + "'" + releaseDate + "', "
                + "" + (newRelease ? 1 : 0) + ", "
                + price + "" +
         ");";

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
    
    public boolean load() {
        return super.load(this);
    }
    
    public void updateSelf(DescriptionMovie loaded) {
        this.setId(loaded.getId());
        this.setCode(loaded.getCode());
        this.setTitle(loaded.getTitle());
        this.setDescription(loaded.getDescription());
        this.setPrice(loaded.getPrice());
    }
    
    /**
     * Construct the article entity from a resultSet row
     */
    @Override
    public DescriptionMovie constructEntity(HashMap<String, String> row) throws InstantiationException, IllegalAccessException {
        
        DescriptionMovie descriptionArticle = new DescriptionMovie();
        descriptionArticle.setId(Integer.parseInt((String) row.get("ID")));
        descriptionArticle.setCode((String) row.get("CODE"));
        descriptionArticle.setTitle((String) row.get("TITLE"));
        descriptionArticle.setDescription((String) row.get("DESCRIPTION"));
        descriptionArticle.setReleaseDate((String) row.get("RELEASE_DATE"));
        descriptionArticle.setNewRelease(((String) row.get("NEW")) == "1");
        descriptionArticle.setPrice(Float.parseFloat((String) row.get("PRICE")));
        
        return descriptionArticle;
    }
    
    /**
     * Returns the article list with populated descriptions
     * @param articles Articles
     * @return Populated articles
     */
    public static ArrayList<Movie> returnWithPopulatedDescriptions(ArrayList<Movie> articles) {

        for (Movie movie : articles) {
            DescriptionMovie descriptionArticle = new DescriptionMovie();
            descriptionArticle.setId(movie.getDescription().getId());
            descriptionArticle.load();
            movie.setDescription(descriptionArticle);
        }
        
        return articles;
    }
    
    public static void main(String [] args) {
        
        DescriptionMovie description = new DescriptionMovie("code3", "Coke5", "blabla_description2", "1387132240", true, 10.95f);
        
        try {
            description.save();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.println("Hello, World!!!");
        System.out.println(description.toString());
        
    }

}

