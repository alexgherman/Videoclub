package com.videoclub.models;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.videoclub.article.DescriptionArticle;
import com.videoclub.database.Database;
import com.videoclub.database.DatabaseTableName;

abstract public class Common<T extends Common> implements CommonInterface<T> {
    
    public Integer id = null;
    
    public Class<? extends Common> clazz = null;
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * Table name
     */
    public DatabaseTableName tableName;
    
    /**
     * Constructor
     * 
     * @param tableName Table name
     */
    protected Common(DatabaseTableName tableName, Class<? extends Common> clazz) {
        this.tableName = tableName;
        this.clazz = clazz;
    }
    
    /**
     * Construct entities array list from a result set.
     * 
     * @param resultSet
     * @return List of constructed entities
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    protected ArrayList<T> constructEntities(ArrayList<HashMap<String, String>> resultSet) throws InstantiationException, IllegalAccessException {
        ArrayList<T> result = new ArrayList<T>();
        for (HashMap<String, String> row : resultSet) {
            result.add(constructEntity(row));
        }
        return result;
    }
    
    /**
     * Constructs an entity
     * This methods will be always overridden by children.
     * 
     * @param row 
     * @return Object of the generic type.
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @SuppressWarnings({ "unchecked" })
    public T constructEntity(HashMap<String, String> row) throws InstantiationException, IllegalAccessException {
        T obj = (T) clazz.newInstance();
        System.out.println("bla:" + obj.toString());
        return (T) obj.constructEntity(row);
    }
    
    @SuppressWarnings({ "unchecked" })
    public boolean load(T obj) {
        T loaded = null;
        
        try {
            loaded = (T) obj.getById(this.id);
            obj.updateSelf(loaded);
        } catch (InstantiationException | IllegalAccessException  | SecurityException | NoSuchFieldException e) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Updates self.
     * This methods will be always overridden by children.
     * 
     * @param loaded Loaded object
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     */
    public void updateSelf(T loaded) throws NoSuchFieldException, SecurityException {
        
    }
    
    /**
     * Extract all the entities
     * 
     * @return list of entities
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public ArrayList<T> getAll() throws InstantiationException, IllegalAccessException {
        String sql = "SELECT * FROM articles";
        
        return constructEntities(Database.instance().select(sql, tableName));
    }
    
    
    public T getById(Integer id) throws InstantiationException, IllegalAccessException {
        String sql = "SELECT * FROM " + tableName.toString() + " WHERE id = " + id;

        return constructEntity(Database.instance().selectOne(sql, tableName));
    }
    
    public ArrayList<T> getById(ArrayList<Integer> ids) throws InstantiationException, IllegalAccessException {
        String sql = "SELECT * FROM articles WHERE id IN (" + implode(",", ids, false) + ")";

        return constructEntities(Database.instance().select(sql, tableName));
    }
    
    
    
    public ArrayList<HashMap<String, String>> getByField(String field, String value) {
        String sql = "SELECT * FROM articles";
     
        return Database.instance().select(sql, DatabaseTableName.ARTICLES);
    }
    
    @SuppressWarnings("unchecked")
    public void create(HashMap<String, String> fieldValues) {
        String sql = "INSERT INTO " + tableName + " ("
                  + implode(",", (ArrayList<? extends Object>) fieldValues.keySet(), false)
                  + ") VALUES ("
                  + implode(",", (ArrayList<? extends Object>) fieldValues.entrySet(), true)
                  + ");";

//        Database.instance().update(sql);
    }
    
    public void update() {
        
    }
    
    public void save(HashMap<String, String> fieldValues) throws SQLException {
        if (id == null) {
            create(fieldValues);
        } else {
//            update();
        }
    }
    
    /**
     * Join the array elements with a glue string
     * 
     * @param glue
     * @param pieces
     * @param quotes add quotations
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String implode(String glue, ArrayList<? extends Object> pieces, boolean quotes) {
        
        String result = "";
        
        Object s = pieces.get(0);
        if (s.getClass() == Integer.class) {
            result = implodeInteger(glue, (ArrayList<Integer>) pieces);
        } else {
            result = implodeString(glue, (ArrayList<String>) pieces, quotes);
        }
        
        return result;
    }
    
    /**
     * Join the array of integer elements with a glue string
     * 
     * @param glue
     * @param pieces
     * @return
     */
    public static String implodeInteger(String glue, ArrayList<Integer> pieces) {
        StringBuilder builder = new StringBuilder();
        builder.append(pieces.remove(0));

        for(int i = 0; i < pieces.size(); i++) {
            Integer piece = pieces.get(i);
            if (i != 0) {
                builder.append(glue);
            }
            builder.append(String.valueOf(piece));
        }
        
        return builder.toString();
    }
    
    /**
     * Join the array of string elements with a glue string
     * 
     * @param glue
     * @param pieces
     * @param quotes add quotations
     * @return
     */
    public static String implodeString(String glue, ArrayList<String> pieces, boolean quotes) {
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < pieces.size(); i++) {
            String piece = pieces.get(i);
            if (i != 0) {
                builder.append(glue);
            }
            if (quotes) {
                builder.append("'" + piece + "'");
            } else {
                builder.append(piece);
            }
        }
        
        return builder.toString();
    }
    
    public static void main(String [] args) {
        
        /* implodes test */
        ArrayList<Integer> pieces = new ArrayList<Integer>();
        pieces.add(1);
        pieces.add(2);
        System.out.println("imploded integers: " + Common.implode(",", pieces, false));
        
        ArrayList<String> stringPieces = new ArrayList<String>();
        stringPieces.add("first");
        stringPieces.add("second");
        System.out.println("imploded strings without quotations: " + Common.implode(",", stringPieces, false));
        
        System.out.println("imploded strings with quotations: " + Common.implode(",", stringPieces, true));
        
        
        
        
    }
    
}
