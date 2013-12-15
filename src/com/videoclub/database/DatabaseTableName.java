package com.videoclub.database;

import com.videoclub.models.article.*;
import com.videoclub.models.movie.*;
import com.videoclub.models.account.*;

public enum DatabaseTableName {

    SQLITE_MASTER("sqlite_master", new Object()),
    ARTICLES("articles", new Article()),
    DESCRIPTION_ARTICLES("description_articles", new DescriptionArticle()),
    MOVIES("movies", new Movie()),
    MOVIE_COPIES("movie_copies", new MovieCopy()),
    DESCRIPTION_MOVIES("description_movies", new DescriptionMovie()),
    USERS("users", new User());
    
    private final String tableName;
    private final Object obj;
    
    DatabaseTableName(String tableName, Object obj) {
        this.tableName = tableName;
        this.obj = obj;
    }

    public String getTableName() {
        return tableName;
    }

    public Object getObj() {
        return obj;
    }
    
    public static DatabaseTableName getByObj(Object obj) {
        DatabaseTableName result = null;
        
        for (DatabaseTableName n : DatabaseTableName.values()) {
            if (n.getObj().getClass() == obj.getClass()) {
                result = n;
                break;
            }
        }
        
        return result;
    }
}
