package com.videoclub.ui;

import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 * UI Component for the Article entity
 */
public class Article {

	/**
	 * Add Article UI window
	 */
    public static void add() {
	    
	    final JFrame f = new JFrame("Ajouter Article");
	    
		/**
		 * Window components
		 */
		
		/* Article name */
		JLabel nameLabel = new JLabel("Nom de l'article:");       // label
		final JTextField nameInput = new JTextField();            // input
		
		/* Article count */
		JLabel countLabel = new JLabel("Quantité:");                                        // label
	    JSpinner countSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 99999, 1));       // spinner
		
	    /* Price */
	    JLabel priceLabel = new JLabel("Prix:");   // label
	    final JSpinner priceSpinner = new JSpinner(
	            new SpinnerNumberModel(
	                    new Double(1.99), new Double(0), new Double(99999.99), new Double(.01)
	            ));                                // spinner
	    
		/* Submit button */
		JButton enregistrerBouton = new JButton("Enregistrer");
		
		/* Cancel button */
		JButton annulerBouton = new JButton("Annuler");
		
		/**
		 * Main Panel
		 */
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
		panel.add(countLabel,c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 10;
		panel.add(countSpinner,c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0;
		panel.add(priceLabel,c);
		
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 10;
		panel.add(priceSpinner,c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 0;
		panel.add(enregistrerBouton,c);
		
		c.gridx = 1;
		c.gridy = 3;
		c.weightx = 0;
		panel.add(annulerBouton,c);
		
		f.getContentPane().add(panel);
		
		enregistrerBouton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String nom = nameInput.getText();
				if(nom.equals("")) {
					JOptionPane.showMessageDialog(null, "L'article n'a pas de nom!", "Message", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				int prix = (int)((double)priceSpinner.getValue()*100);
				if(prix == 0) {
					int reponse = JOptionPane.showConfirmDialog(null, "L'article est-il vraiment gratuit?", "Confirmation", JOptionPane.YES_NO_OPTION);
					if(reponse == JOptionPane.NO_OPTION)
						return;
				}
				
//				SellableItem article = new SellableItem(nom, prix);
				
//				for(int i=0; i < (Integer)quantiteSpinner.getValue(); ++i)
//					db.addArticle(article);
			}
		});
		
		annulerBouton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				f.dispose();
			}
		});
		
		/**
         * Main window adjustments
         */
        f.setSize(400, 250);
        f.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);
        
	}
	
	public static void main(String[] args) {
	    Article.add();
    }
}
