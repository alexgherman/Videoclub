package com.videoclub.models.movie.rent;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.videoclub.database.Database;
import com.videoclub.database.DatabaseTableName;
import com.videoclub.models.*;

public class Rent extends Common<Rent> implements CommonInterface<Rent> {
    
    HashMap<String, String> fieldValues = new HashMap<String, String>();
    
    private Integer id = null;
    private Integer userId = null;
    private String rentDate = null;
    
    
    public Rent() {
        super(DatabaseTableName.RENTS, Rent.class);
    }
    
    public Rent(Integer id) {
        super(DatabaseTableName.RENTS, Rent.class);
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

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
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
        
        setRentDate(now.toString());
        String sql = "INSERT INTO " + tableName + " (USER_ID, RENT_DATE) "
                   + "VALUES (" + getUserId() + ", " + getRentDate() + ");";
        
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
    public Rent constructEntity(HashMap<String, String> row) throws InstantiationException, IllegalAccessException {
        Rent rent = new Rent();
        rent.setId(Integer.parseInt((String) row.get("ID")));
        rent.setUserId(Integer.parseInt((String) row.get("USER_ID")));
        rent.setRentDate((String) row.get("RENT_DATE"));
        return rent;
    }
    
    public static void main(String [] args) {
        
        Rent rent = new Rent();
        rent.setUserId(1);
        
        try {
            rent.save();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.println("Success");
        
    }


    
}

