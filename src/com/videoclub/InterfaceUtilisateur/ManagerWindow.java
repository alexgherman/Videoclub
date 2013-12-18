package com.videoclub.InterfaceUtilisateur;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.videoclub.models.account.User;
import com.videoclub.models.movie.Movie;

@SuppressWarnings("serial")
/**
 * Fen�tre du g�rant
 * Le g�rant doit s'identifier
 * 
 * @author Maxime Dupuis
 *
 */
public class ManagerWindow extends JDialog
{
	private JButton identificationButton = new JButton("Identification");
	private JButton pricesButton = new JButton("Modifier prix");

	private JButton addMovieButton = new JButton("Ajouter film");
	private JButton addItemButton = new JButton("Ajouter article");
	private JButton addUserButton = new JButton("Ajouter utilisateur");

	private JButton removeMovieButton = new JButton("Supprimer film");
	private JButton removeItemButton = new JButton("Supprimer article");
	private JButton removeUserButton = new JButton("Supprimer utilisateur");

	/**
	 * Constructeur
	 */
	ManagerWindow(final VideoClub videoClub)
	{
		super((Frame) null, "Mode G�rant", true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// Screen Size
		setSize(475, 250);
		setMinimumSize(new Dimension(475, 250));

		// Screen centered
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

		updateAuthorizations(videoClub);

		// Window Layout
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		panel.add(identificationButton, c);
		
		c.gridx = 0;
		c.gridy = 1;
		panel.add(pricesButton, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		panel.add(addMovieButton, c);

		c.gridx = 1;
		c.gridy = 2;
		panel.add(addItemButton, c);

		c.gridx = 2;
		panel.add(addUserButton, c);

		c.gridx = 0;
		c.gridy = 3;
		panel.add(removeMovieButton, c);

		c.gridx = 1;
		c.gridy = 3;
		panel.add(removeItemButton, c);

		c.gridx = 2;
		panel.add(removeUserButton, c);

		getContentPane().add(panel);

		identificationButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					updateAuthorizations(videoClub);
				}
			});
		
		pricesButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				JOptionPane.showMessageDialog(null, "Dans le prochain patch!", "Modifier prix des films", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		addMovieButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					CreateRentableMovieWindow win = new CreateRentableMovieWindow(videoClub);
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

		addUserButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					CreateUserWindow win = new CreateUserWindow();
					win.setVisible(true);

					LoginInfo info = win.getLoginInfo();
					if (info != null)
					{
						videoClub.createUser(info);
					}
				}
			});

		removeMovieButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					PickMoviesWindow win = new PickMoviesWindow(videoClub);
					win.setVisible(true);

					ArrayList<Movie> selectedMovies = win.getSelection();
					
					int answer = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer ce film de location?", "Supression de film", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (answer == JOptionPane.YES_OPTION)
					{
						videoClub.removeMovies(selectedMovies);
					}
				}
			});

		removeItemButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					PickItemsWindow win = new PickItemsWindow(videoClub);
					win.setVisible(true);

					ArrayList<SellableItem> selectedItems = win.getSelection();
					
					int answer = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer cet article?", "Supression d'article", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (answer == JOptionPane.YES_OPTION)
					{
						videoClub.removeItems(selectedItems);
					}
					
				}
			});

		removeUserButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					PickUsersWindow win = new PickUsersWindow(videoClub);
					win.setVisible(true);

					ArrayList<User> selectedUsers = win.getSelection();
					
					int answer = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer ce membre?", "Supression de membre", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (answer == JOptionPane.YES_OPTION)
					{
						videoClub.removeUsers(selectedUsers);
					}
				}
			});
		
	
	}

	/**
	 * Si c'est bien le g�rant, on active les boutons pour qu'il puisse
	 * utiliser ses fonctionnalit�s r�serv�es
	 */
	private void updateAuthorizations(VideoClub videoClub)
	{
		LoginWindow win = new LoginWindow();
		win.setVisible(true);
		LoginInfo info = win.getLoginInfo();

		boolean hasManagerRights = false;

		if (info != null)
		{
			hasManagerRights = videoClub.validManager(info);
		}

		addMovieButton.setEnabled(hasManagerRights);
		addItemButton.setEnabled(hasManagerRights);
		removeMovieButton.setEnabled(hasManagerRights);
		removeItemButton.setEnabled(hasManagerRights);
		removeUserButton.setEnabled(hasManagerRights);

	}
}
