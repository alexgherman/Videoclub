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

import com.videoclub.i18n.*;
import com.videoclub.models.Common;
import com.videoclub.models.article.Article;
import com.videoclub.models.article.DescriptionArticle;
import com.videoclub.ui.article.Add;

public class Main extends JFrame {
    
    JLabel label = new JLabel(StringUtils.capitalize(Locale.instance("en", "US").getMessages().getString("article_list")));
    List articleList = new List(10);
    
    /**
     * Populate the list with articles
     * @param articles Article list
     */
    public void populateArticleList(ArrayList<com.videoclub.models.article.Article> articles) {
        for (com.videoclub.models.article.Article a : articles) {
            articleList.add(a.toString());
        }
    }
    
    public Main(ArrayList<com.videoclub.models.article.Article> articles) {
        
        populateArticleList(articles);
 
        JButton addButton = new JButton(StringUtils.capitalize(Locale.instance("en", "US").getMessages().getString("add")));

        JButton refreshButton = new JButton("refresh");
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(LEADING)
                .addComponent(label)
                .addComponent(articleList)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(addButton)
                    .addComponent(refreshButton))));
        
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(label)
            .addComponent(articleList)
            .addGroup(layout.createParallelGroup(LEADING)
                .addComponent(addButton)
                .addComponent(refreshButton)));
 
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new Add().setVisible(true);
            }
        });
        
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                articleList.removeAll();
                com.videoclub.models.article.Article article = new Article();
                try {
                    
                    ArrayList<Article> articles = article.getAll();
                    articles = DescriptionArticle.returnWithPopulatedDescriptions(articles);
                    populateArticleList(articles);
                    
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace(); // TODO: change the exception error
                }
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
                
                
                com.videoclub.models.article.Article articleObj = new com.videoclub.models.article.Article();
                
                ArrayList<com.videoclub.models.article.Article> articles = null;
                try {
                    articles = DescriptionArticle.returnWithPopulatedDescriptions(articleObj.getAll());
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace(); // TODO: change the exception error
                }
                
                new Main(articles).setVisible(true);
            }
        });
    }
}
