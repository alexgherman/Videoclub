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
public class ManagerWindow extends JDialog
{
	private JButton identificationButton = new JButton("Identification");

	private JButton addMovieButton = new JButton("Ajouter film");
	private JButton addItemButton = new JButton("Ajouter article");

	private JButton removeMovieButton = new JButton("Modifier film");
	private JButton removeItemButton = new JButton("Modifier article");

	/**
	 * Constructeur
	 */
	ManagerWindow(final VideoClub videoClub)
	{
		super((Frame)null, "Mode Gérant", true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//Screen Size
		setSize(300, 200);
		setMinimumSize(new Dimension(300,200));
		
		//Screen centered
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);

		updateAuthorizations(videoClub);
		
		//Window Layout
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		panel.add(identificationButton, c);

		c.gridy = 1;
		c.gridwidth = 1;
		panel.add(addMovieButton, c);

		c.gridx = 1;
		c.gridy = 1;
		panel.add(addItemButton, c);

		c.gridx = 0;
		c.gridy = 2;
		panel.add(removeMovieButton, c);

		c.gridx = 1;
		c.gridy = 2;
		panel.add(removeItemButton, c);

		getContentPane().add(panel);

		identificationButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					updateAuthorizations(videoClub);
				}
			});

		addMovieButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					AddRentableMovieWindow win = new AddRentableMovieWindow(videoClub);
					win.setVisible(true);
				}
			});

		addItemButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					AddItemWindow win = new AddItemWindow(videoClub);
					win.setVisible(true);
				}
			});

		removeMovieButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					PickMoviesWindow win = new PickMoviesWindow(videoClub);
					win.setVisible(true);

					Vector<RentableMovie> selectedMovies = win.getSelection();
					videoClub.removeMovies(selectedMovies);
				}
			});

		removeItemButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					PickItemsWindow win = new PickItemsWindow(videoClub);
					win.setVisible(true);

					Vector<SellableItem> selectedItems = win.getSelection();
					videoClub.removeItems(selectedItems);
				}
			});
	}

	/**
	 * Si c'est bien le gérant, on active les boutons pour qu'il puisse utiliser
	 * ses fonctionnalités réservées
	 */
	private void updateAuthorizations(VideoClub videoClub)
	{
		LoginWindow win = new LoginWindow();
		win.setVisible(true);
		LoginInfo info = win.getLoginInfo();
		
		boolean hasManagerRights = videoClub.validManager(info);
		
		addMovieButton.setEnabled(hasManagerRights);
		addItemButton.setEnabled(hasManagerRights);
		removeMovieButton.setEnabled(hasManagerRights);
		removeItemButton.setEnabled(hasManagerRights);
	}
}
