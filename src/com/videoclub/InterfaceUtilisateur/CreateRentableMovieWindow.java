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

import com.videoclub.controllers.Rental;

/**
 * Cr�e une fen�tre reli�e � un VideoClub. Permet d'y ajouter des films
 * � louer
 * 
 * @author Maxime Dupuis
 * 
 */
@SuppressWarnings("serial")
public class CreateRentableMovieWindow extends JDialog
{
	private JLabel nameLabel = new JLabel("Nom du film:");
	private JTextField nameTxtField = new JTextField();

	private JLabel quantityLabel = new JLabel("Quantit�:");
	private SpinnerNumberModel modelQ = new SpinnerNumberModel(1, 1, 99999, 1);
	private JSpinner quantitySpinner = new JSpinner(modelQ);

	JLabel descriptionLabel = new JLabel("Description:");
	JTextField descriptionTxtField = new JTextField();

	JLabel releaseDateLabel = new JLabel("Date de sortie:");
	SpinnerNumberModel rdModelQ = new SpinnerNumberModel(2000, 1950, 2020, 1);
	JSpinner releaseDateSpinner = new JSpinner(rdModelQ);

	JLabel priceLabel = new JLabel("Prix:");
	SpinnerNumberModel priceModelQ = new SpinnerNumberModel(new Double(0), new Double(0), new Double(99999.99), new Double(.01));
	JSpinner priceSpinner = new JSpinner(priceModelQ);

	private JLabel isNewLabel = new JLabel("Nouveaut�:");
	private JCheckBox isNewCheckBox = new JCheckBox();

	private JButton saveButton = new JButton("Enregistrer");
	private JButton closeButton = new JButton("Fermer");

	CreateRentableMovieWindow(final VideoClub videoClub)
	{
		super((Frame) null, "Ajouter Location", true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// Screen Size
		setSize(400, 250);
		setMinimumSize(new Dimension(400, 250));

		// Screen centered
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10, 10, 10, 10);
		c.weighty = 1;

		/**
		 * Name
		 */
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0;
		panel.add(nameLabel, c);

		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 10;
		panel.add(nameTxtField, c);

		/**
		 * Description
		 */
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0;
		panel.add(descriptionLabel, c);

		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 10;
		panel.add(descriptionTxtField, c);

		/**
		 * Release date
		 */

		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0;
		panel.add(releaseDateLabel, c);

		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 10;
		panel.add(releaseDateSpinner, c);

		/**
		 * Price
		 */
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 0;
		panel.add(releaseDateLabel, c);

		c.gridx = 1;
		c.gridy = 3;
		c.weightx = 10;
		panel.add(releaseDateSpinner, c);

		/**
		 * Quantity
		 */

		c.gridx = 0;
		c.gridy = 4;
		c.weightx = 0;
		panel.add(quantityLabel, c);

		c.gridx = 1;
		c.gridy = 4;
		c.weightx = 10;
		panel.add(quantitySpinner, c);

		/**
		 * New release
		 */
		c.gridx = 0;
		c.gridy = 5;
		c.weightx = 0;
		panel.add(isNewLabel, c);

		c.gridx = 1;
		c.gridy = 5;
		c.weightx = 10;
		panel.add(isNewCheckBox, c);

		/**
		 * Controls
		 */
		c.gridx = 0;
		c.gridy = 6;
		c.weightx = 0;
		c.weighty = 0;
		panel.add(saveButton, c);

		c.gridx = 1;
		c.gridy = 6;
		c.weightx = 0;
		panel.add(closeButton, c);

		getContentPane().add(panel);

		saveButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					String name = nameTxtField.getText();
					String description = descriptionTxtField.getText();

					boolean isNew = isNewCheckBox.isSelected();
					String releaseDate = String.valueOf((Integer) releaseDateSpinner.getValue());
					Integer numberOfCopies = (Integer) quantitySpinner.getValue();
					if (name.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Le film n'a pas de nom!", "Message", JOptionPane.WARNING_MESSAGE);
						return;
					}

					Rental.addNewMovie(name, description, releaseDate, isNew, numberOfCopies);

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
