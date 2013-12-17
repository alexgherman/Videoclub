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
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.videoclub.models.account.User;

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

	private JButton addMovieButton = new JButton("Ajouter film");
	private JButton addItemButton = new JButton("Ajouter article");
	private JButton addUserButton = new JButton("Ajouter utilisateur");

	private JButton removeMovieButton = new JButton("Enlever film");
	private JButton removeItemButton = new JButton("Enlever article");
	private JButton removeUserButton = new JButton("Enlever utilisateur");

	/**
	 * Constructeur
	 */
	ManagerWindow(final VideoClub videoClub)
	{
		super((Frame) null, "Mode G�rant", true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// Screen Size
		setSize(425, 200);
		setMinimumSize(new Dimension(425, 200));

		// Screen centered
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2
				- getSize().height / 2);

		updateAuthorizations(videoClub);

		// Window Layout
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		panel.add(identificationButton, c);

		c.gridy = 1;
		c.gridwidth = 1;
		panel.add(addMovieButton, c);

		c.gridx = 1;
		c.gridy = 1;
		panel.add(addItemButton, c);

		c.gridx = 2;
		panel.add(addUserButton, c);

		c.gridx = 0;
		c.gridy = 2;
		panel.add(removeMovieButton, c);

		c.gridx = 1;
		c.gridy = 2;
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

		addMovieButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					CreateRentableMovieWindow win = new CreateRentableMovieWindow(
							videoClub);
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

		removeUserButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					PickUsersWindow win = new PickUsersWindow(videoClub);
					win.setVisible(true);

					ArrayList<User> selectedUsers = win.getSelection();
					videoClub.removeUsers(selectedUsers);
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

	}
}
