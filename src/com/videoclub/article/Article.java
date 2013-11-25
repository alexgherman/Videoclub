package com.videoclub.article;

import java.sql.SQLException;

import com.videoclub.database.Database;

public class Article {
    
    
    private int id;
    
    private DescriptionArticle description = null;

    public Article() {
        
    }
    
    public Article(DescriptionArticle description) {
        this.description = description;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    
    public static void main(String [] args) {
        
        DescriptionArticle description = new DescriptionArticle("code1", "Coke", "blabla_description", 10.85f);
//        description.save();
        
        Article article = new Article();
        
        
        
//        article.save();
        
        System.out.println(article);
        
    }
    
}
