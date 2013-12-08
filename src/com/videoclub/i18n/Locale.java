package com.videoclub.i18n;

import java.util.*;

/**
 * Locale class
 */
public class Locale {
    
    /**
     * Default language
     */
    private final static String DEFAULT_LANGUAGE = "en";
    
    /**
     * Default country
     */
    private final static String DEFAULT_COUNTRY = "US";
    
    /**
     * Locale language
     */
    private String language;
    
    /**
     * Locale country
     */
    private String country;
    
    /**
     * Locale messages
     */
    private ResourceBundle messages;
    
    /**
     * Previous locale instance
     */
    private static Locale previousInstance = null;
    
    /**
     * Constructor without arguments
     */
    public Locale() {
        new Locale(DEFAULT_LANGUAGE, DEFAULT_COUNTRY);
    }
    
    /**
     * Constructor
     * 
     * @param language Locale language
     * @param country Locale country
     */
    public Locale(String language, String country) {
        this.setLanguage(language);
        this.setCountry(country);
        
        setMessages(ResourceBundle.getBundle("locales.MessagesBundle", new java.util.Locale(language, country)));
    }
    
    /**
     * Locale language getter
     * 
     * @return Locale language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Locale language setter
     * 
     * @param language Locale language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Locale country getter
     * @return Locale country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Locale country setter
     * @param country Locale country
     */
    public void setCountry(String country) {
        this.country = country;
    }
    
    /**
     * Messages getter
     * @return Messages
     */
    public ResourceBundle getMessages() {
        return messages;
    }

    /**
     * Messages setter
     * @param messages Messages
     */
    public void setMessages(ResourceBundle messages) {
        this.messages = messages;
    }
    
    /**
     * Get instance
     * 
     * @param language Locale language
     * @param country Locale country
     * @return the locale instance
     */
    public static Locale instance(String language, String country) {
        
        if (previousInstance == null) {
            previousInstance = new Locale(language, country);
        }
        
        return previousInstance;
    }
    
    public static Locale instance() {
        return instance(DEFAULT_LANGUAGE, DEFAULT_COUNTRY);
    }
    
    public static void main(String[] args) {
        // only the first instance call needs parameters
        System.out.println(Locale.instance("en", "US").getMessages().getString("greetings"));
        System.out.println(Locale.instance().getMessages().getString("inquiry"));
        System.out.println(Locale.instance().getMessages().getString("farewell"));
    }

}