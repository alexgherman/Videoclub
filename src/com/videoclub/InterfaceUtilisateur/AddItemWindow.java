package com.videoclub.InterfaceUtilisateur;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
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
 * Crée une fenêtre reliée à un VideoClub. 
 * Permet d'y ajouter des articles à vendre
 * 
 * @author Maxime Dupuis
 *
 */
@SuppressWarnings("serial")
public class AddItemWindow extends JFrame
{	
	JLabel nameLabel = new JLabel("Nom de l'article:");
	JTextField nameTxtField = new JTextField();
	
	JLabel quantityLabel = new JLabel("Quantité:");
	SpinnerNumberModel modelQ = new SpinnerNumberModel(1, 1, 99999, 1);
	JSpinner quantitySpinner = new JSpinner(modelQ);
	
	JLabel priceLabel = new JLabel("Prix:");
	SpinnerNumberModel modelP = new SpinnerNumberModel(new Double(0), new Double(0), new Double(99999.99), new Double(.01));
	JSpinner priceSpinner = new JSpinner(modelP);
	
	JButton saveButton = new JButton("Enregistrer");
	JButton closeButton = new JButton("Fermer");
	
	AddItemWindow(final VideoClub videoClub)
	{
		super("Ajouter Article");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// Screen Size
		setSize(400,250);
		setMinimumSize(new Dimension(400,250));

		// Screen centered
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2
				- getSize().height / 2);
		
		
		setSize(400, 250);
		setVisible(true);
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10,10,10,10);
		c.weighty = 1;
		
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0;
		panel.add(nameLabel,c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 10;
		panel.add(nameTxtField,c);
		
		
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0;
		panel.add(quantityLabel,c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 10;
		panel.add(quantitySpinner,c);
		
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
		c.weighty= .1;
		panel.add(saveButton,c);
		
		c.gridx = 1;
		c.gridy = 3;
		c.weightx = 0;
		panel.add(closeButton,c);
		
		getContentPane().add(panel);
				
		saveButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				String nom = nameTxtField.getText();
				if(nom.equals(""))
				{
					JOptionPane.showMessageDialog(null, "L'article n'a pas de nom!", "Message", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				int prix = (int)((double)priceSpinner.getValue()*100);
				if(prix == 0)
				{
					int reponse = JOptionPane.showConfirmDialog(null, "L'article est-il vraiment gratuit?", "Confirmation", JOptionPane.YES_NO_OPTION);
					if(reponse == JOptionPane.NO_OPTION)
						return;
				}
				
				
				SellableItem article = new SellableItem(nom, prix);
				
				for(int i=0; i < (Integer)quantitySpinner.getValue(); ++i)
					videoClub.addItem(article);
			}
		});
		
		closeButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				dispose();
			}
		});
	}
}
