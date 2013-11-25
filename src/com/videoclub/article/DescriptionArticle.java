package com.videoclub.article;

import java.sql.SQLException;

import com.videoclub.database.Database;

public class DescriptionArticle {

    private Integer id = null;
    
    private String code;
    
    private String name;
    
    private String description;
    
    private float price;

    public DescriptionArticle(String code, String name, String description, float price) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
    }
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    public String toString() {
        return this.name + " [" + this.price + "]";
    }
    
    public void create() throws SQLException {
        
        String sql = "INSERT INTO description_articles ("
                            + "CODE,"
                            + "NAME,"
                            + "DESCRIPTION,"
                            + "PRICE" +
                     ") VALUES ("
                            + "'" + code + "', "
                            + "'" + name + "', "
                            + "'" + description + "', " 
                            + price + "" +
                     ");";
     
        Database.instance().update(sql);
    }
    
    public void update() {
        
    }
    
    public void save() throws SQLException {
        if (id == null) {
            create();
        } else {
            update();
        }
    }
    
    public static void main(String [] args) {
        
        DescriptionArticle description = new DescriptionArticle("code2", "Coke2", "blabla_description2", 10.95f);
        
        try {
            description.save();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.println("Hello, World!!!");
        System.out.println(description.toString());
        
    }
}
