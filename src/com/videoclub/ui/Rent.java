package com.videoclub.ui;

import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 * UI Component for the Rent entity
 */
public class Rent {

    /**
     * Add Rent UI window
     */
    public static void add() {
        
        final JFrame f = new JFrame("Ajouter Location");
        
        /**
         * Window components
         */
        /* Film name */
        JLabel nameLabel = new JLabel("Nom du film");       // label
        final JTextField nameInput = new JTextField();      // input
        
        /* Rent count */
        JLabel quantiteLabel = new JLabel("Quantit???");    // label
        JSpinner quantiteSpinner = new JSpinner();          // input
        
        /* Novelty */
        JLabel noveltyLabel = new JLabel("Nouveaut???");    // label
        final JCheckBox noveltyCheckBox = new JCheckBox();  // checkbox

        /* Submit button */
        JButton submitBouton = new JButton("Enregistrer");
        
        /* Cancel button */
        JButton cancelBouton = new JButton("Annuler");
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,10,10,10);
        
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0;
        panel.add(nameLabel,c);
        
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 10;
        panel.add(nameInput,c);
        
        
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0;
        panel.add(quantiteLabel,c);
        
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 10;
        panel.add(quantiteSpinner,c);
        
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0;
        panel.add(noveltyLabel,c);
        
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 10;
        panel.add(noveltyCheckBox,c);
        
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 0;
        panel.add(submitBouton,c);
        
        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 0;
        panel.add(cancelBouton,c);
        
        f.getContentPane().add(panel);
        
        quantiteSpinner.setValue(1);
        
        submitBouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String nom = nameInput.getText();
                boolean nouveaute = noveltyCheckBox.isSelected();
//                LocationFilm film = new LocationFilm(nom, nouveaute);
                
//                for(int i=0; i < (Integer)quantiteSpinner.getValue(); ++i)
//                    db.addLocation(film);
            }
        });
        
        cancelBouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                f.dispose();
            }
        });
        
        /**
         * Main window adjustments
         */
        f.setSize(400, 250);
        f.setVisible(true);
        f.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
    }
    
    public static void main(String[] args) {
        Rent.add();
    }
}
