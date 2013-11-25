package com.videoclub.database;

import com.videoclub.article.Article;

public enum DatabaseTableName {

    SQLITE_MASTER("sqlite_master", new Object()),
    ARTICLES("articles", new Article());
    
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
