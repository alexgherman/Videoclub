package com.videoclub.InterfaceUtilisateur;


import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

@SuppressWarnings("serial")
/**
 * Fenêtre du gérant
 * Le gérant doit s'identifier
 * 
 * @author Maxime Dupuis
 *
 */
public class EmployeeWindow extends JDialog
{
	private JButton pickMoviesButton = new JButton("Choisir Films");
	private JButton pickItemsButton = new JButton("Choisir Articles");

	private JPanel cart = new JPanel();

	/**
	 * Constructeur
	 */
	EmployeeWindow(final VideoClub videoClub)
	{
		super((Frame)null, "Mode Employé", true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// Screen Size
		setSize(300, 200);
		setMinimumSize(new Dimension(300, 200));

		// Screen centered
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2
				- getSize().height / 2);

		// Window Layout
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10, 10, 10, 10);

		c.gridx = 0;
		c.gridy = 0;
		panel.add(pickMoviesButton, c);

		c.gridx = 1;
		panel.add(pickItemsButton, c);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		panel.add(cart, c);

		getContentPane().add(panel);

		pickMoviesButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					PickMoviesWindow win = new PickMoviesWindow(videoClub);
					win.setVisible(true);

					Vector<RentableMovie> selectedMovies = win.getSelection();
					videoClub.rentMovies(selectedMovies);
				}
			});

		pickItemsButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{

					PickItemsWindow win = new PickItemsWindow(videoClub);
					win.setVisible(true);

					Vector<SellableItem> selectedItems = win.getSelection();
					videoClub.buyItems(selectedItems);

				}
			});
	}
}
