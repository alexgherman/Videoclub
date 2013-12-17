package com.videoclub.InterfaceUtilisateur;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.videoclub.models.account.User;

@SuppressWarnings("serial")
/**
 * Fen�tre d'articles � acheter
 * @author Maxime Dupuis
 *
 */
public class PickUsersWindow extends JDialog
{
	private ArrayList<User> userChoices = new ArrayList<User>();
	private ArrayList<User> selectedusers = new ArrayList<User>();

	private JLabel choiceLabel = new JLabel("Choix:");
	private JLabel selectionLabel = new JLabel("Votre s�lection:");

	private JButton okButton = new JButton("OK");
	private JButton closeButton = new JButton("Fermer");

	private JPanel userChoicesPanel = new JPanel();
	private JScrollPane choicesPanel = new JScrollPane(userChoicesPanel);

	private JPanel userSelectionPanel = new JPanel();
	private JScrollPane selectionPanel = new JScrollPane(userSelectionPanel);

	public ArrayList<User> getSelection()
	{
		return selectedusers;
	}

	/**
	 * Constructeur
	 */
	PickUsersWindow(final VideoClub videoClub)
	{
		super((Frame) null, "Choisir articles", true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// Screen Size
		setSize(600, 400);
		setMinimumSize(new Dimension(600, 400));

		// Screen centered
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2
				- getSize().height / 2);

		userChoicesPanel.setLayout(new BoxLayout(userChoicesPanel,
				BoxLayout.Y_AXIS));
		userSelectionPanel.setLayout(new BoxLayout(userSelectionPanel,
				BoxLayout.Y_AXIS));

		userChoices = videoClub.getUserChoices();

		for (User user : userChoices)
		{
			addChoiceuser(user);
		}

		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;

		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 0;
		panel.add(choiceLabel, c);

		c.gridx = 1;
		panel.add(selectionLabel, c);

		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 1;
		panel.add(choicesPanel, c);

		c.gridx = 1;
		panel.add(selectionPanel, c);

		c.gridx = 0;
		c.gridy = 2;
		c.weighty = 0;
		panel.add(okButton, c);

		c.gridx = 1;
		panel.add(closeButton, c);

		setContentPane(panel);

		okButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					dispose();
				}
			});

		closeButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					selectedusers.clear();
					dispose();
				}
			});
	}

	/**
	 * Ajoute un user dans la liste des choix
	 */
	private void addChoiceuser(final User user)
	{
		JButton bouton = new JButton(user.getFirstName() + " " + user.getLastName());
		userChoicesPanel.add(bouton);

		bouton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					addSelectionuser(user);
					removeChoiceuser(user);
				}
			});
		revalidate();
	}

	/**
	 * Enl�ve un user de la liste des choix
	 */
	private void removeChoiceuser(User user)
	{
		for (Component c : userChoicesPanel.getComponents())
		{
			JButton bouton = (JButton) c;

			if (bouton.getText().equals(user.getFirstName() + " " + user.getLastName()))
			{
				userChoicesPanel.remove(bouton);
				this.update(getGraphics());
				break;
			}
		}

		revalidate();
	}

	/**
	 * Ajoute un user dans la liste de s�lection
	 */
	private void addSelectionuser(final User user)
	{
		JButton bouton = new JButton(user.getFirstName() + " " + user.getLastName());
		userSelectionPanel.add(bouton);

		bouton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					addChoiceuser(user);
					removeSelectionuser(user);
				}
			});

		selectedusers.add(user);
		revalidate();
	}

	/**
	 * Enl�ve un user de la liste des s�lections
	 */
	private void removeSelectionuser(User user)
	{
		for (Component c : userSelectionPanel.getComponents())
		{
			JButton bouton = (JButton) c;
			bouton.setVisible(true);

			if (bouton.getText().equals(user.getFirstName() + " " + user.getLastName()))
			{
				userSelectionPanel.remove(bouton);
				this.update(getGraphics());
				break;
			}
		}

		selectedusers.remove(user);

		revalidate();
	}
}
