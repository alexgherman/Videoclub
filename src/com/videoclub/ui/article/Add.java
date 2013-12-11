package com.videoclub.ui.article;

import java.awt.Component;

import javax.swing.*;

import org.apache.commons.lang3.StringUtils;

import com.sun.jndi.url.corbaname.corbanameURLContextFactory;

import static javax.swing.GroupLayout.Alignment.*;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import com.videoclub.article.Article;
import com.videoclub.article.DescriptionArticle;
import com.videoclub.i18n.*;

public class Add extends JFrame {
    
    public Add() {
        
        /* Name */
        JLabel nameLabel = new JLabel(StringUtils.capitalize(Locale.instance("en", "US").getMessages().getString("name")));
        final JTextField nameField = new JTextField();
        
        /* Description */
        JLabel descriptionLabel = new JLabel(StringUtils.capitalize(Locale.instance().getMessages().getString("description")));
        final JTextField descriptionField = new JTextField();
 
        /* Price */
        JLabel priceLabel = new JLabel(StringUtils.capitalize(Locale.instance().getMessages().getString("price")));
        final JTextField priceField = new JTextField();

        /* Controls */
        JButton addButton = new JButton(StringUtils.capitalize(Locale.instance().getMessages().getString("add")));
        JButton cancelButton = new JButton(StringUtils.capitalize(Locale.instance().getMessages().getString("cancel")));
  
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(LEADING)
                .addComponent(nameLabel)
                .addComponent(descriptionLabel)
                .addComponent(priceLabel)
                .addComponent(addButton))
            .addGroup(layout.createParallelGroup(LEADING)
                .addComponent(nameField)
                .addComponent(descriptionField)
                .addComponent(priceField)
                .addComponent(cancelButton)));
        
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(BASELINE)
                .addComponent(nameLabel)
                .addComponent(nameField))
            .addGroup(layout.createParallelGroup(BASELINE)
                .addComponent(descriptionLabel)
                .addComponent(descriptionField))
            .addGroup(layout.createParallelGroup(BASELINE)
                .addComponent(priceLabel)
                .addComponent(priceField))
            .addGroup(layout.createParallelGroup(BASELINE)
                .addComponent(addButton)
                .addComponent(cancelButton)));

        /**
         * TODO: could be moved somewhere else.
         */
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                /* DescriptionArticle */
                String name = nameField.getText();
                String description = descriptionField.getText();
                Float price = Float.parseFloat(priceField.getText());
                DescriptionArticle descriptionObj = new DescriptionArticle("0001", name, description, price);
                try {
                    descriptionObj.save();
                    Article articleObj = new Article();
                    articleObj.setDescription(descriptionObj);
                    articleObj.save();
                    System.out.println("success");
                } catch (SQLException e) {
                    // should never get here.
                    e.printStackTrace();
                }
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
        });
 
        setTitle(Locale.instance().getMessages().getString("add_article"));
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
                new Add().setVisible(true);
            }
        });
    }
}

