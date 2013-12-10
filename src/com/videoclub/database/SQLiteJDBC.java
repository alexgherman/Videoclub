package com.videoclub.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SQLiteJDBC {

    private String tableName = null;
    
    private String databaseName = "test";
    
    private Connection connection = null;
    
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
        
        Statement stmt = null;
        
        ArrayList<Map<String, java.lang.Object>> collect = new ArrayList<Map<String, java.lang.Object>>();
        
        ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
                
        try {
            
            HashMap<String, Class> columnNames = getColumnNames(obj.getTableName());
            
            /**
             * Select Query
             */
            ResultSet rs = connection.createStatement().executeQuery(sql);
            
            while (rs.next()) {
                result.add(iterateResultSet(rs, columnNames));
            }
            
        } catch(Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        
        return result;
    }
    
    public void update(String sql) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
        
    }
    
}
