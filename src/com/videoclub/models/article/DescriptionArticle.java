package com.videoclub.models.article;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.videoclub.database.Database;
import com.videoclub.database.DatabaseTableName;
import com.videoclub.models.Common;
import com.videoclub.models.CommonInterface;

public class DescriptionArticle extends Common<DescriptionArticle> implements CommonInterface<DescriptionArticle> {

    private Integer id = null;
    
    private String code;
    
    private String name;
    
    private String description;
    
    private float price;

    public DescriptionArticle() {
        super(DatabaseTableName.DESCRIPTION_ARTICLES, DescriptionArticle.class);
    }
    
    public DescriptionArticle(String code, String name, String description, float price) {
        super(DatabaseTableName.DESCRIPTION_ARTICLES, DescriptionArticle.class);
        this.code = code;
        this.name = name;
        this.description = description;
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
    public String generateRandomCode() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    public String toString() {
        return this.name + " [" + this.price + "]";
    }
    
    /**
     * TODO: do this in the Common class, will do for now
     * @throws SQLException
     */
    public Integer create() throws SQLException {
        
        String sql = "INSERT INTO " + DatabaseTableName.DESCRIPTION_ARTICLES.getTableName() + " ("
                + "CODE,"
                + "NAME,"
                + "DESCRIPTION,"
                + "PRICE" +
         ") VALUES ("
                + "'" + generateRandomCode() + "', "
                + "'" + name + "', "
                + "'" + description + "', " 
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
    
    public void updateSelf(DescriptionArticle loaded) {
        this.setId(loaded.getId());
        this.setCode(loaded.getCode());
        this.setName(loaded.getName());
        this.setDescription(loaded.getDescription());
        this.setPrice(loaded.getPrice());
    }
    
    /**
     * Construct the article entity from a resultSet row
     */
    @Override
    public DescriptionArticle constructEntity(HashMap<String, String> row) throws InstantiationException, IllegalAccessException {
        
        DescriptionArticle descriptionArticle = new DescriptionArticle();
        descriptionArticle.setId(Integer.parseInt((String) row.get("ID")));
        descriptionArticle.setCode((String) row.get("CODE"));
        descriptionArticle.setName((String) row.get("NAME"));
        descriptionArticle.setDescription((String) row.get("DESCRIPTION"));
        descriptionArticle.setPrice(Float.parseFloat((String) row.get("PRICE")));
        
        return descriptionArticle;
    }
    
    /**
     * Returns the article list with populated descriptions
     * @param articles Articles
     * @return Populated articles
     */
    public static ArrayList<com.videoclub.models.article.Article> returnWithPopulatedDescriptions(ArrayList<com.videoclub.models.article.Article> articles) {

//      ----- unnecessary overhead for an sqlite database ----
//      Uncomment if situation changes
//      ArrayList<Integer> descriptionIds = new ArrayList<Integer>();
      
      // collect the description ids
        for (com.videoclub.models.article.Article article : articles) {
//          ----- unnecessary overhead for an sqlite database ----
//          descriptionIds.add(article.getDescription().getId());
            DescriptionArticle descriptionArticle = new DescriptionArticle();
            descriptionArticle.setId(article.getDescription().getId());
            descriptionArticle.load();
            article.setDescription(descriptionArticle);
        }
       
//      ----- unnecessary overhead for an sqlite database ----
//      // resolve the description objects
//      DescriptionArticle descriptionArticle = new DescriptionArticle();
//      ArrayList<DescriptionArticle> descriptionArticles = null;
//      try {
//          descriptionArticles = descriptionArticle.getById(descriptionIds);
//      } catch (InstantiationException | IllegalAccessException e) {
//          e.printStackTrace(); // TODO: change the exception error
//      }
//      
//      // link the articles with their collected description objects
//      // ...
        
        return articles;
    }
    
    public static void main(String [] args) {
        
        DescriptionArticle description = new DescriptionArticle("code2", "Coke2", "blabla_description2", 10.95f);
        
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
