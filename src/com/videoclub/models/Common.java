package com.videoclub.models;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

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
    @SuppressWarnings("null")
    public T constructEntity(HashMap<String, String> row) throws InstantiationException, IllegalAccessException {
//        Class<T> clazz = null;
        T obj = (T) clazz.newInstance();
//        return obj;
        System.out.println("bla:" + obj.toString());
        return (T) obj.constructEntity(row);
    }
    
    @SuppressWarnings({ "null", "rawtypes", "unchecked" })
    public boolean load(T obj) {
//        Class<T> clazz = T.class;
        
//        T obj = null;
        T loaded = null;
        
        try {
//            System.out.println("test");
//            System.out.println("test2:" + clazz.getCanonicalName());
//            obj = (T) clazz.newInstance();
            loaded = (T) obj.getById(this.id);
            
//            System.out.println("test2:" + loaded.toString());
            obj.updateSelf(loaded);
//            updateSelf((T) loaded);
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
        String sql = "SELECT * FROM articles WHERE id IN (" + implode(",", ids) + ")";

        return constructEntities(Database.instance().select(sql, tableName));
    }
    
    
    
    public ArrayList<HashMap<String, String>> getByField(String field, String value) {
        String sql = "SELECT * FROM articles";
     
        return Database.instance().select(sql, DatabaseTableName.ARTICLES);
    }
    
    /**
     * Join the array elements with a glue string
     * 
     * @param glue
     * @param pieces
     * @return
     */
    public String implode(String glue, ArrayList<Integer> pieces) {
        StringBuilder builder = new StringBuilder();
        builder.append(pieces.remove(0));

        for(Integer piece : pieces) {
            builder.append(glue);
            builder.append(String.valueOf(piece));
        }
        
        return builder.toString();
    }
    
}
