package com.videoclub.article;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.videoclub.database.Database;
import com.videoclub.database.DatabaseTableName;
import com.videoclub.models.*;

public class Article extends Common<Article> implements CommonInterface<Article> {
    
    
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

    
    
    public void create() throws SQLException {
        
        String sql = "INSERT INTO articles (DESCRIPTION_ID) "
                   + "VALUES (" + description.getId() + ");";
        
        Database.instance().update(sql);
    }
    
    public void update() {
        
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
        
        DescriptionArticle description = new DescriptionArticle("code1", "Coke", "blabla_description", 10.85f);
//        description.save();
        
        Article article = new Article();
        
        
        
//        article.save();
        
        System.out.println(article);
        
    }
    
}
