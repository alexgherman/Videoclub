package com.videoclub.controllers;

import java.util.ArrayList;


public class Article {
    
    /**
     * Get the list of articles
     * 
     * @return list of articles
     */
    public static ArrayList<com.videoclub.models.article.Article> getArticles() {
        com.videoclub.models.article.Article a = new com.videoclub.models.article.Article();
        ArrayList<com.videoclub.models.article.Article> list = new ArrayList<com.videoclub.models.article.Article>();
        try {
            list = a.getAll();
            
            for(com.videoclub.models.article.Article article : list) {
                article.setDescription(article.getDescription().getById(article.getDescription().getId()));
                // TODO: ugly, fix later
            }
            
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static void main(String [] args) {
        
        System.out.println("getArticles() test:");
        System.out.println(Article.getArticles());
        
    }
}
