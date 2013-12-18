package com.videoclub.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import com.videoclub.models.article.Article;

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

        HashMap<String, String> r = db.selectOne(sql, DatabaseTableName.SQLITE_MASTER);
        
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
            String sql = "CREATE TABLE if not exists    " + DatabaseTableName.ARTICLES.getTableName() + " (" +
                         "ID                            INTEGER PRIMARY KEY AUTOINCREMENT   NOT NULL, " +
                         "DESCRIPTION_ID                INTEGER                             NOT NULL)"; 
            
            db.update(sql);

            /**
             * Description Article table
             */
            
            sql = "CREATE TABLE IF NOT EXISTS    " + DatabaseTableName.DESCRIPTION_ARTICLES.getTableName() + " (" +
                  "ID                            INTEGER PRIMARY KEY AUTOINCREMENT      NOT NULL, " +
                  "CODE                          TEXT                                   NOT NULL, " +
                  "NAME                          TEXT                                   NOT NULL, " +
                  "DESCRIPTION                   TEXT                                   NOT NULL, " +
                  "PRICE                         REAL                                   NOT NULL)"; 
            
            db.update(sql);

            /**
             * Movie table
             */

            sql = "CREATE TABLE IF NOT EXISTS    " + DatabaseTableName.MOVIES.getTableName() + " (" +
                  "ID                            INTEGER PRIMARY KEY AUTOINCREMENT   NOT NULL, " +
                  "DESCRIPTION_ID                INTEGER                             NOT NULL)"; 
            
            db.update(sql);

            /**
             * Description Movie table
             */

            sql = "CREATE TABLE IF NOT EXISTS    " + DatabaseTableName.DESCRIPTION_MOVIES.getTableName() + " (" +
                  "ID                            INTEGER PRIMARY KEY AUTOINCREMENT      NOT NULL, " +
                  "CODE                          TEXT                                   NOT NULL, " +
                  "TITLE                         TEXT                                   NOT NULL, " +
                  "DESCRIPTION                   TEXT                                   NOT NULL, " +
                  "RELEASE_DATE                  TEXT                                   NOT NULL, " +
                  "NEW                           INTEGER                                NOT NULL, " +
                  "PRICE                         REAL                                   NOT NULL)";

            db.update(sql);
            
            /**
             * Movie copies table
             */

            sql = "CREATE TABLE IF NOT EXISTS    " + DatabaseTableName.MOVIE_COPIES.getTableName() + " (" +
                  "ID                            INTEGER PRIMARY KEY AUTOINCREMENT   NOT NULL, " +
                  "MOVIE_ID                      INTEGER                             NOT NULL)"; 

            db.update(sql);
            
            /**
             * Users table
             */

            sql = "CREATE TABLE IF NOT EXISTS    " + DatabaseTableName.USERS.getTableName() + " (" +
                  "ID                            INTEGER PRIMARY KEY AUTOINCREMENT      NOT NULL, " +
                  "USERNAME                      TEXT                                   NOT NULL, " +
                  "PASSWORD                      TEXT                                   NOT NULL, " +
                  "FIRST_NAME                    TEXT                                   NOT NULL, " +
                  "LAST_NAME                     TEXT                                   NOT NULL, " +
                  "CREATED                       TEXT                                   NOT NULL, " +
                  "LAST_VISIT                    TEXT                                   NOT NULL, " +
                  "GROUP_ID                      INTEGER                                NOT NULL)";

            db.update(sql);
            
            /**
             * Rent table
             */

            sql = "CREATE TABLE IF NOT EXISTS    " + DatabaseTableName.RENTS.getTableName() + " (" +
                  "ID                            INTEGER PRIMARY KEY AUTOINCREMENT      NOT NULL, " +
                  "USER_ID                       INTEGER                                   NOT NULL, " +
                  "RENT_DATE                     TEXT                                   NOT NULL)";

            db.update(sql);
            
                     
            /**
             * Rent table
             */

            sql = "CREATE TABLE IF NOT EXISTS    " + DatabaseTableName.RENT_ARTICLES.getTableName() + " (" +
                  "ID                            INTEGER PRIMARY KEY AUTOINCREMENT      NOT NULL, " +
                  "RENT_ID                       INTEGER                                NOT NULL, " +
                  "MOVIE_ID                      INTEGER                                NOT NULL)";

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
        
        ArrayList<String> databaseTableNameList = new ArrayList<String>();
        databaseTableNameList.add(DatabaseTableName.ARTICLES.getTableName());
        databaseTableNameList.add(DatabaseTableName.DESCRIPTION_ARTICLES.getTableName());
        databaseTableNameList.add(DatabaseTableName.MOVIES.getTableName());
        databaseTableNameList.add(DatabaseTableName.DESCRIPTION_MOVIES.getTableName());
        databaseTableNameList.add(DatabaseTableName.USERS.getTableName());
        databaseTableNameList.add(DatabaseTableName.MOVIE_COPIES.getTableName());
        databaseTableNameList.add(DatabaseTableName.RENTS.getTableName());
        databaseTableNameList.add(DatabaseTableName.RENT_ARTICLES.getTableName());
        
        /**
         * check if table exists
         */
        try {
            
            for (String tableName : databaseTableNameList) {
                System.out.println(tableName + " " + (fixtures.checkTableExists(tableName) ? "table created" : "table NOT created"));
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
