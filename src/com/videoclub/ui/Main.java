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

import com.videoclub.article.DescriptionArticle;
import com.videoclub.i18n.*;
import com.videoclub.ui.article.Add;

public class Main extends JFrame {
    
    public Main(com.videoclub.article.Article[] articles) {
        
        JLabel label = new JLabel(StringUtils.capitalize(Locale.instance("en", "US").getMessages().getString("article_list")));
        
        List articleList = new List(10);
 
        /* Populate the list with articles */
        
        for (int i = 0; i < articles.length; i++) {
            articleList.add(articles[i].toString());
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
                com.videoclub.article.Article[] articles = new com.videoclub.article.Article[2];
                articles[0] = new com.videoclub.article.Article(new DescriptionArticle("001", "BlaArticle", "blaDescription", 1.80f));
                articles[1] = new com.videoclub.article.Article(new DescriptionArticle("002", "BlaArticle 2", "blaDescription 2", 1.81f));
                
                new Main(articles).setVisible(true);
            }
        });
    }
}
