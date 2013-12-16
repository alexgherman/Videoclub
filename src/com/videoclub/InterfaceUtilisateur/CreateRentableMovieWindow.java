package com.videoclub.InterfaceUtilisateur;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 * Crée une fenêtre reliée à un VideoClub. Permet d'y ajouter des films à louer
 * 
 * @author Maxime Dupuis
 * 
 */
@SuppressWarnings("serial")
public class CreateRentableMovieWindow extends JDialog
{
	private JLabel nameLabel = new JLabel("Nom du film:");
	private JTextField nameTxtField = new JTextField();

	private JLabel quantityLabel = new JLabel("Quantité:");
	private SpinnerNumberModel modelQ = new SpinnerNumberModel(1, 1, 99999, 1);
	private JSpinner quantitySpinner = new JSpinner(modelQ);

	private JLabel isNewLabel = new JLabel("Nouveauté:");
	private JCheckBox isNewCheckBox = new JCheckBox();

	private JButton saveButton = new JButton("Enregistrer");
	private JButton closeButton = new JButton("Fermer");
	
	CreateRentableMovieWindow(final VideoClub videoClub)
	{
		super((Frame)null, "Ajouter Location", true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// Screen Size
		setSize(400,250);
		setMinimumSize(new Dimension(400,250));

		// Screen centered
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2
				- getSize().height / 2);
		
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10, 10, 10, 10);
		c.weighty = 1;

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0;
		panel.add(nameLabel, c);

		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 10;
		panel.add(nameTxtField, c);

		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0;
		panel.add(quantityLabel, c);

		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 10;
		panel.add(quantitySpinner, c);

		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0;
		panel.add(isNewLabel, c);

		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 10;
		panel.add(isNewCheckBox, c);

		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 0;
		c.weighty = 0;
		panel.add(saveButton, c);

		c.gridx = 1;
		c.gridy = 3;
		c.weightx = 0;
		panel.add(closeButton, c);

		getContentPane().add(panel);

		saveButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				String name = nameTxtField.getText();
				if (name.equals(""))
				{
					JOptionPane.showMessageDialog(null,
							"Le film n'a pas de nom!", "Message",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				boolean isNew = isNewCheckBox.isSelected();
				RentableMovie rentableMovie = new RentableMovie(name, isNew);

				for (int i = 0; i < (Integer) quantitySpinner.getValue(); ++i)
					videoClub.addRentableMovie(rentableMovie);
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
