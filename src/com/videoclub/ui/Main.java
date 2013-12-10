package com.videoclub.ui;

import java.awt.Component;

import javax.swing.*;

import org.apache.commons.lang3.StringUtils;

import com.sun.jndi.url.corbaname.corbanameURLContextFactory;

import static javax.swing.GroupLayout.Alignment.*;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.videoclub.article.Article;
import com.videoclub.article.DescriptionArticle;
import com.videoclub.i18n.*;
import com.videoclub.models.Common;
import com.videoclub.ui.article.Add;

public class Main extends JFrame {
    
    public Main(ArrayList<com.videoclub.article.Article> articles) {
        
        JLabel label = new JLabel(StringUtils.capitalize(Locale.instance("en", "US").getMessages().getString("article_list")));
        
        List articleList = new List(10);
 
        /* Populate the list with articles */
        
        for (com.videoclub.article.Article a : articles) {
            articleList.add(a.toString());
        }
        
        JButton addButton = new JButton(StringUtils.capitalize(Locale.instance("en", "US").getMessages().getString("add")));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(LEADING)
                .addComponent(label)
                .addComponent(articleList)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(addButton))));
        
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(label)
            .addComponent(articleList)
            .addComponent(addButton));
 
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new Add().setVisible(true);
            }
        });
        
        setTitle(Locale.instance("en", "US").getMessages().getString("main_title"));
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(
                                  "javax.swing.plaf.metal.MetalLookAndFeel");
                                //  "com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                                //UIManager.getCrossPlatformLookAndFeelClassName());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                
                
                com.videoclub.article.Article articleObj = new com.videoclub.article.Article();
                
                ArrayList<com.videoclub.article.Article> articles = null;
                try {
                    articles = articleObj.getAll();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace(); // TODO: change the exception error
                }
                
//                ----- unnecessary overhead for an sqlite database ----
//                Uncomment if situation changes
//                ArrayList<Integer> descriptionIds = new ArrayList<Integer>();
                
                // collect the description ids
                for (com.videoclub.article.Article article : articles) {
//                    ----- unnecessary overhead for an sqlite database ----
//                    descriptionIds.add(article.getDescription().getId());
                    DescriptionArticle descriptionArticle = new DescriptionArticle();
                    descriptionArticle.setId(article.getDescription().getId());
                    descriptionArticle.load();
                    System.out.println("description article: " + descriptionArticle.toString());
                    article.setDescription(descriptionArticle);
                    System.out.println("article: " + article.toString());
                }
                
                System.out.println("boom");
                
//                ----- unnecessary overhead for an sqlite database ----
//                // resolve the description objects
//                DescriptionArticle descriptionArticle = new DescriptionArticle();
//                ArrayList<DescriptionArticle> descriptionArticles = null;
//                try {
//                    descriptionArticles = descriptionArticle.getById(descriptionIds);
//                } catch (InstantiationException | IllegalAccessException e) {
//                    e.printStackTrace(); // TODO: change the exception error
//                }
//                
//                // link the articles with their collected description objects
//                // ...

                new Main(articles).setVisible(true);
            }
        });
    }
}
