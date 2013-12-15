package com.videoclub.account;

import java.sql.SQLException;

import com.videoclub.article.DescriptionArticle;

public class Account {
    
    /**
     * Account registration
     * 
     * @param username Username
     * @param password Password
     * @param firstName First name
     * @param lastName Last name
     * @return
     */
    public static boolean registration(String username, String password, String firstName, String lastName) {
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        try {
            user.save();
        } catch (SQLException e) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Login by username and password
     * 
     * @param username Username
     * @param password Password
     * @return TRUE on success and FALSE on failure
     */
    public static boolean login(String username, String password) {
        User u1 = new User();
        User u = null;
        try {
            u = u1.getByUsername(username);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        
        if (u == null) {
            return false;
        }
        
        User u2 = new User();
        u2.setUsername(username);
        u2.setPassword(password);

        return u1.checkSameUser(u2);
    }
}
