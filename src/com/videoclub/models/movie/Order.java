package com.videoclub.models.movie;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.videoclub.database.Database;
import com.videoclub.database.DatabaseTableName;
import com.videoclub.models.*;

public class Order extends Common<Order> implements CommonInterface<Order> {
    
    HashMap<String, String> fieldValues = new HashMap<String, String>();
    
    private Integer id = null;
    private Integer userId = null;
    private String date = null;
    
    
    public Order() {
        super(DatabaseTableName.ORDERS, Order.class);
    }
    
    public Order(Integer id) {
        super(DatabaseTableName.ORDERS, Order.class);
        setId(id);
    }
    
    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        super.setId(id);
        this.id = id;
    }
    
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    
    public String toString() {
        return "[" + getId() + "] " + " ";
    }

    /**
     * TODO: do this in the Common class, will do for now
     * @throws SQLException
     */
    public Integer create() throws SQLException {

        Long now = (System.currentTimeMillis() / 1000L);
        
        setDate(now.toString());
        String sql = "INSERT INTO " + tableName + " (USER_ID, DATE) "
                   + "VALUES (" + getUserId() + ", " + getDate() + ");";
        
        return Database.instance().update(sql);
    }
    
    public void update() {
        
    }
    
    public boolean save() throws SQLException {
        if (id == null) {
            Integer lastId = create();
            this.setId(lastId);
            return lastId > 0;
        } else {
            update();
            return true;
        }
    }
    
    /**
     * Construct the article entity from a resultSet row
     */
    @Override
    public Order constructEntity(HashMap<String, String> row) throws InstantiationException, IllegalAccessException {
        Order rent = new Order();
        rent.setId(Integer.parseInt((String) row.get("ID")));
        rent.setUserId(Integer.parseInt((String) row.get("USER_ID")));
        rent.setDate((String) row.get("DATE"));
        return rent;
    }
    
    public static void main(String [] args) {
        
        Order order = new Order();
        order.setUserId(1);
        
        try {
            order.save();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        System.out.println("Success");
        
    }


    
}

