package com.videoclub.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.videoclub.models.article.DescriptionArticle;
import com.videoclub.util.PrettyPrintingMap;

public class SQLiteJDBC {

    private String tableName = null;
    
    private String databaseName = "test";
    
    private Connection connection = null;
    
//    private ResultSet lastStatementResultSet = null;
    
    Integer lastId;
    
    public SQLiteJDBC() {
        connect();
    }
    
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
       
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    
    private boolean connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + this.getDatabaseName() + ".db");
        } catch ( Exception e ) {
            return false;
        }
        return true;
    }
    
    private HashMap<String, Class> getColumnNames(String tableName) throws SQLException {
        
        /**
         * Get all the column names
         */
        String pragma = "PRAGMA table_info("+ tableName +");";
        
        ResultSet r = connection.createStatement().executeQuery(pragma);
        
        HashMap<String, Class> columnNames = new HashMap<String, Class>();
        
        while(r.next()) {
            
            Class type = null;
            switch(r.getString("type")) {
                case "INT":
                case "integer":
                    type = Integer.TYPE;
                    break;
                case "TEXT":
                case "text":
                    type = String.class;
                    break;
            }
            
//            System.out.println(r.getString("name") + "-" + type);
            columnNames.put(r.getString("name"), type);
            
        }
        
        return columnNames;
    }
    
    public HashMap<String, String> iterateResultSet(ResultSet rs, Map<String, Class> columnNames) throws SQLException {
        
        Map<String, Class> columnNames2 = new HashMap<String, Class>(columnNames);
        Iterator it = columnNames.entrySet().iterator();
        
        HashMap<String, String> result = new HashMap<String, String>();
        
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Object val = pair.getValue();
            
            // System.out.println(val);
            // TODO: figure out why no switch statement is allowed... 
//            if (val == Integer.TYPE) {
//                result.put(pair.getKey().toString(), new Integer(rs.getInt(pair.getKey().toString())));
//            } else if(val == String.class) {
//                result.put(pair.getKey().toString(), rs.getString(pair.getKey().toString()));
//            } else {
                result.put(pair.getKey().toString(), rs.getString(pair.getKey().toString()));
                // ... 
//                System.out.println("???" + (val == Integer.TYPE) + "[" + val + "]");
//            }
             
        }
        
        return result;
    }
    
    public HashMap<String, String> selectOne(String sql, DatabaseTableName obj) {
        ArrayList<HashMap<String, String>> r = this.select(sql, obj);
        return (r.size() == 0 ? null : r.get(0));
    }

    /**
     * Execute a SELECT statement
     * 
     * @param sql SQL statement to execute
     * @param obj 
     * @return
     */
    public ArrayList<HashMap<String, String>> select(String sql, DatabaseTableName obj) {
        
        ArrayList<Map<String, java.lang.Object>> collect = new ArrayList<Map<String, java.lang.Object>>();
        
        ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
                
        try {
            
            HashMap<String, Class> columnNames = getColumnNames(obj.getTableName());
            
            // System.out.println("test:" + new PrettyPrintingMap(columnNames));
            
            /**
             * Select Query
             */
            Statement stmt = connection.createStatement();
            
            ResultSet rs = null;
            try {
                rs = stmt.executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
//                stmt.close();
            }
            
//            System.out.println(rs.);
            
            if (!rs.next()) {
//                System.out.println("no rows found");
            } else {
                do {
                    result.add(iterateResultSet(rs, columnNames));
                } while(rs.next());
            }
            
        } catch(Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        
        return result;
    }
    
    public Integer update(String sql) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(sql);
        Integer lastInsertedId = stmt.getGeneratedKeys().getInt(1);
        lastId = (lastInsertedId == (Integer)lastInsertedId ? lastInsertedId : 0);
        stmt.close();
        return lastId;
    }
    
    public Integer getLastInsertedId() {
        return lastId;
    }
    
    public static void main(String [] args) {
        
        SQLiteJDBC db = new SQLiteJDBC();
//        try {
//            db.select("insert into articles (description_id) values (8);", DatabaseTableName.ARTICLES);
        db.select("select * from articles;", DatabaseTableName.ARTICLES);

//        } catch (SQLException e) {
//            System.out.println("error");
//            e.printStackTrace();
//        }
        
        System.out.println("sdfds");
//        System.out.println(description.toString());
        
    }
    
}
