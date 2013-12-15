package com.videoclub.models.account;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.videoclub.database.Database;
import com.videoclub.database.DatabaseTableName;
import com.videoclub.models.Common;
import com.videoclub.models.CommonInterface;
import com.videoclub.models.article.DescriptionArticle;

public class User extends Common<User> implements CommonInterface<User> {
	
	public User() {
        super(DatabaseTableName.USERS, User.class);
    }
    
    private Integer id = null;

    private String firstName;
	
	private String lastName;
	
	private String created;
	
	private String lastVisit;
	
	private String username;
	
	private String password;
	
	private String salt = "";
	
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        super.setId(id);
        this.id = id;
    }
	
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(String lastVisit) {
        this.lastVisit = lastVisit;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String toString() {
        return "{" + getId() + ": " + getUsername() + ", " + getPassword() + ", " + getFirstName() + ", " + getLastName() + "}";
    }
	
	public boolean checkSameUser(User user) {
	   
	   return (user.getUsername().equals(this.getUsername()) &&
//	       Arrays.equals(user.getSalt().getBytes(), this.getSalt().getBytes()) &&
	       user.getPassword().equals(this.getPassword()));
	}

// TODO: use later to generate hashed and salted passwords 
//    public String generatePassword() {
//        Random random = new Random();
//        byte[] salt = new byte[16];
//        random.nextBytes(salt);
//        
//        return generatePassword(salt);
//    }
//	
//    public String generatePassword(byte[] salt) {
//
//        System.out.println("generated salt: " + Arrays.toString(salt) + "/" + password);
//
//        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
//        SecretKeyFactory f;
//        byte[] hash = null;
//        try {
//            f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
//            hash = f.generateSecret(spec).getEncoded();
//        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
////        this.salt = new BigInteger(1, salt).toString(16);
//        try {
//            this.salt = new String(salt, "ISO-8859-1");
//        } catch (UnsupportedEncodingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        this.password = new BigInteger(1, hash).toString(16);
//        return password;
//    }
    
    public User getByUsername(String username) throws InstantiationException, IllegalAccessException {
        String sql = "SELECT * FROM users WHERE username = '" + username + "'";

        HashMap<String, String> result = Database.instance().selectOne(sql, tableName);
        return result == null ? null : constructEntity(result);
    }
    
    public boolean load() {
        return super.load(this);
    }
    
    public void updateSelf(User loaded) {
        this.setId(loaded.getId());
        this.setUsername(loaded.getUsername());
        this.setPassword(loaded.getPassword());
        this.setFirstName(loaded.getFirstName());
        this.setLastName(loaded.getLastName());
        this.setCreated(loaded.getCreated());
        this.setLastVisit(loaded.getLastVisit());
    }
    
    /**
     * Construct the article entity from a resultSet row
     */
    @Override
    public User constructEntity(HashMap<String, String> row) throws InstantiationException, IllegalAccessException {
        
        User user = new User();
        user.setId(Integer.parseInt((String) row.get("ID")));
        user.setUsername((String) row.get("USERNAME"));
        user.setPassword((String) row.get("PASSWORD"));
        user.setFirstName((String) row.get("FIRST_NAME"));
        user.setLastName((String) row.get("LAST_NAME"));
        user.setCreated((String) row.get("CREATED"));
        user.setLastVisit((String) row.get("LAST_VISIT"));
        
        return user;
    }
    
    public Integer create() throws SQLException {
        
        Long now = (System.currentTimeMillis() / 1000L);
        
        setCreated(now.toString());
        setLastVisit(now.toString());
        
        String sql = "INSERT INTO " + DatabaseTableName.USERS.getTableName() + " ("
                + "USERNAME,"
                + "PASSWORD,"
                + "FIRST_NAME,"
                + "LAST_NAME,"
                + "CREATED,"
                + "LAST_VISIT" +
         ") VALUES ("
                + "'" + username + "', "
                + "'" + password + "', "
                + "'" + firstName + "', "
                + "'" + lastName + "', "
                + "'" + created + "', "
                + "'" + lastVisit + "'" +
         ");";

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
    
    public static void main(String [] args) {
//        
//        User user = new User();
//        user.setUsername("s_connor");
//        user.setPassword("test");
//        user.setFirstName("Sarah");
//        user.setLastName("Connor");
//        try {
//            user.save();
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        
//        System.out.println("User created");
//        
        
        
        // Login test        
//        User u1 = new User();
//        try {
//            u1 = u1.getByUsername("s_connor");
//        } catch (InstantiationException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        
//        System.out.println("Matched user: " + u1);
//        
//        User u2 = new User();
//        u2.setUsername("s_connor");
//        u2.setPassword("test");
//
//        System.out.println("Same user: " + u1.checkSameUser(u2));
        

    }
    
}
