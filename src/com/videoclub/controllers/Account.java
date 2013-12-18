package com.videoclub.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import com.videoclub.InterfaceUtilisateur.LoginInfo;
import com.videoclub.models.account.User;
import com.videoclub.models.article.DescriptionArticle;
import com.videoclub.models.movie.Movie;

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
    
    /**
     * Get the list of users
     * 
     * @return list of users
     */
    public static ArrayList<User> getUsers() {
        User u = new User();
        ArrayList<User> list = new ArrayList<User>();
        try {
            list = u.getAll();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static void main(String [] args) {
        
        System.out.println("getUsers() test:");
        System.out.println(Account.getUsers());
        
    }
    
    public static User matchUser(LoginInfo customer) {
        User u = new User();
        User user = new User();
        try {
            user = u.getByUsername(customer.getName());
        } catch (InstantiationException
                | IllegalAccessException e) {
            e.printStackTrace();
        }
        
        return user;
    }
}
