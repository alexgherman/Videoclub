package com.videoclub.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import com.videoclub.article.Article;

/**
 * Fixtures class
 * Class used to create a new database table and populate with initial data, nothing else.
 */
public class Fixtures {

    Database db = null;
    
    public Fixtures(Database db) {
        this.db = db;
    }
    
    /**
     * Check if table exists
     * 
     * @param tableName Table name
     * @return TRUE if table exists and FALSE if not
     * @throws SQLException
     */
    public boolean checkTableExists(String tableName) throws SQLException {
        String sql = "SELECT        * "
                + "FROM             sqlite_master "
                + "WHERE            type='table' "
                + "AND              name='" + tableName + "';";

        HashMap<String, Object> r = db.selectOne(sql, DatabaseTableName.SQLITE_MASTER);
        
        return (r != null && r.get("name").equals(tableName));
    }
    
    /**
     * Create database structure
     */
    public void createStructure() {

        try{   
            
            /**
             * TODO: extract all from DatabaseTableName enum
             * @see DatabaseTableName
             */
            
            /**
             * Article table
             */
            String sql = "CREATE TABLE if not exists    articles (" +
                         "ID                            INTEGER PRIMARY KEY AUTOINCREMENT   NOT NULL, " +
                         "DESCRIPTION_ID                INTEGER                             NOT NULL)"; 
            
            db.update(sql);
            
            /**
             * Description Article table
             */
            
            sql = "CREATE TABLE IF NOT EXISTS    description_articles (" +
                  "ID                            INTEGER PRIMARY KEY AUTOINCREMENT      NOT NULL, " +
                  "CODE                          TEXT                                   NOT NULL, " +
                  "NAME                          TEXT                                   NOT NULL, " +
                  "DESCRIPTION                   TEXT                                   NOT NULL, " +
                  "PRICE                         REAL                                   NOT NULL)"; 
            
            db.update(sql);
            
        } catch (Exception e) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
    }
    
    /**
     * Populate database with initial data fixtures
     */
    public void populate() {
        
    }
    
    public static void main( String args[] ) {
        
        Fixtures fixtures = new Fixtures(new Database());
        fixtures.createStructure();
        fixtures.populate();
        
        /**
         * check if table exists
         */
        try {
            System.out.println(fixtures.checkTableExists("articles") ? "articles table created" : "oups, something went wrong");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
