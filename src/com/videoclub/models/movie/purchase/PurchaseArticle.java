package com.videoclub.models.movie.purchase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.videoclub.database.Database;
import com.videoclub.database.DatabaseTableName;
import com.videoclub.models.*;

public class PurchaseArticle extends Common<PurchaseArticle> implements CommonInterface<PurchaseArticle> {
    
    HashMap<String, String> fieldValues = new HashMap<String, String>();
    
    private Integer id = null;
    private Integer orderId = null;
    private Integer articleId = null;
    
    
    public PurchaseArticle() {
        super(DatabaseTableName.PURCHASE_ARTICLES, PurchaseArticle.class);
    }
    
    public PurchaseArticle(Integer id) {
        super(DatabaseTableName.PURCHASE_ARTICLES, PurchaseArticle.class);
        setId(id);
    }
    
    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        super.setId(id);
        this.id = id;
    }
    
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
    
    public String toString() {
        return "[" + getId() + "] " + " ";
    }

    /**
     * TODO: do this in the Common class, will do for now
     * @throws SQLException
     */
    public Integer create() throws SQLException {
        
        String sql = "INSERT INTO " + tableName + " (ORDER_ID, ARTICLE_ID) "
                   + "VALUES (" + getOrderId() + ", " + getArticleId() + ");";
        
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
    public PurchaseArticle constructEntity(HashMap<String, String> row) throws InstantiationException, IllegalAccessException {
        PurchaseArticle rentArticle = new PurchaseArticle();
        rentArticle.setId(Integer.parseInt((String) row.get("ID")));
        rentArticle.setOrderId(Integer.parseInt((String) row.get("ORDER_ID")));
        rentArticle.setOrderId(Integer.parseInt((String) row.get("ARTICLE_ID")));
        return rentArticle;
    }
    
    public static void main(String [] args) {
        
        PurchaseArticle rentArticle = new PurchaseArticle();
        rentArticle.setArticleId(23);
        rentArticle.setOrderId(24);
        
        
        try {
            rentArticle.save();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        System.out.println("Success");
        
    }

}


