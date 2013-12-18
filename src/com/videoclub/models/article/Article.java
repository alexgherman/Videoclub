package com.videoclub.models.article;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.videoclub.InterfaceUtilisateur.SellableItem;
import com.videoclub.database.Database;
import com.videoclub.database.DatabaseTableName;
import com.videoclub.models.*;

public class Article extends Common<Article> implements CommonInterface<Article> {
    
    HashMap<String, String> fieldValues = new HashMap<String, String>();
    
    private Integer id = null;
    
    private DescriptionArticle description = null;

//    public Article(DatabaseTableName tableName) {
//        super(tableName);
//    }
    
    public Article() {
        super(DatabaseTableName.ARTICLES, Article.class);
    }
    
    public Article(DescriptionArticle description) {
        super(DatabaseTableName.ARTICLES, Article.class);
        this.description = description;
    }
    
    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        super.setId(id);
        this.id = id;
    }

    public DescriptionArticle getDescription() {
        return description;
    }

    public void setDescription(DescriptionArticle description) {
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
    public Article constructEntity(HashMap<String, String> row) throws InstantiationException, IllegalAccessException {
        
        DescriptionArticle descriptionArticle = new DescriptionArticle();
        descriptionArticle.setId(Integer.parseInt((String) row.get("DESCRIPTION_ID")));
        
        Article article = new Article();
        article.setId(Integer.parseInt((String) row.get("ID")));
        article.setDescription(descriptionArticle);
        
        return article;
    }
    
    
    public static void main(String [] args) {
        
        DescriptionArticle description1 = new DescriptionArticle("code1", "Chips", "delicious chips", 1.85f);
        DescriptionArticle description2 = new DescriptionArticle("code1", "Liqueur", "delicious liquer", 2.00f);
        DescriptionArticle description3 = new DescriptionArticle("code1", "Bonbons", "delicious bonbons", 2.00f);
        
        try {
            description1.save();
            description2.save();
            description3.save();            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        Article article1 = new Article();
        Article article2 = new Article();
        Article article3 = new Article();
        
        article1.setDescription(description1);
        article2.setDescription(description2);
        article3.setDescription(description3);
        
        try {
            article1.save();
            article2.save();
            article3.save();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
}
