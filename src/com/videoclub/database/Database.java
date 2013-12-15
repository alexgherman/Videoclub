package com.videoclub.database;

import java.sql.Connection;
import java.sql.DriverManager;

import com.videoclub.models.article.Article;

public class Database extends SQLiteJDBC {

    private static Database instance = null;
    
    public static Database instance() {
        
        Database i;
        if (instance == null) {
            i = new Database();
        } else {
            i = instance;
        }
        
        return i;
    }
    
    public static void main( String args[] ) {
        
        Database db = new Database();
        
//        db.select("SELECT * FROM articles;", new Article());

    }
    
}
