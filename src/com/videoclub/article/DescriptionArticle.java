package com.videoclub.article;

import java.sql.SQLException;
import java.util.HashMap;

import com.videoclub.database.Database;
import com.videoclub.database.DatabaseTableName;
import com.videoclub.models.Common;
import com.videoclub.models.CommonInterface;

public class DescriptionArticle extends Common<DescriptionArticle> implements CommonInterface<DescriptionArticle> {

    private Integer id = null;
    
    private String code;
    
    private String name;
    
    private String description;
    
    private float price;

    public DescriptionArticle() {
        super(DatabaseTableName.DESCRIPTION_ARTICLES, DescriptionArticle.class);
    }
    
    public DescriptionArticle(String code, String name, String description, float price) {
        super(DatabaseTableName.DESCRIPTION_ARTICLES, DescriptionArticle.class);
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
    }
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        super.setId(id);
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
    
    public boolean load() {
        return super.load(this);
    }
    
    public void updateSelf(DescriptionArticle loaded) {
        System.out.println("updating self description article:" + loaded);
        this.setId(23);
        this.setCode("testCode");
        this.setName("testName");
        this.setDescription("testDescription");
        this.setPrice(11.11f);
    }
    
    /**
     * Construct the article entity from a resultSet row
     */
    @Override
    public DescriptionArticle constructEntity(HashMap<String, String> row) throws InstantiationException, IllegalAccessException {
        
        
        
        DescriptionArticle descriptionArticle = new DescriptionArticle();
        descriptionArticle.setId(Integer.parseInt((String) row.get("ID")));
        descriptionArticle.setCode((String) row.get("CODE"));
        descriptionArticle.setName((String) row.get("NAME"));
        descriptionArticle.setDescription((String) row.get("DESCRIPTION"));
        descriptionArticle.setPrice(Float.parseFloat((String) row.get("PRICE")));
        
        
        
        
        System.out.println("description artcile construct entity: " + descriptionArticle);
        
        return descriptionArticle;
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
