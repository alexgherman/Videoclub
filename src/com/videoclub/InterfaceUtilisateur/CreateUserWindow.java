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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Fenï¿½tre qui demande le nom et le mot de passe ï¿½ l'utilisateur
 * 
 * @author Maxime Dupuis
 * 
 */
@SuppressWarnings("serial")
public class CreateUserWindow extends JDialog
{
	private JLabel nameLabel = new JLabel("Identifiant:");
	private JTextField nameField = new JTextField();

	private JLabel passwordLabel = new JLabel("Mot de passe:");
	private JPasswordField passwordField = new JPasswordField();

	private JLabel firstNameLabel = new JLabel("Prénom:");
	private JTextField firstNameField = new JTextField();

	private JLabel lastNameLabel = new JLabel("Nom de famille:");
	private JTextField lastNameField = new JTextField();

	private JButton saveButton = new JButton("OK");
	private JButton closeButton = new JButton("Fermer");

	private LoginInfo loginInfo = null;

	public LoginInfo getLoginInfo()
	{
		return loginInfo;
	}

	CreateUserWindow()
	{
		super((Frame) null, "Créer un utilisateur", true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// Screen Size
		setSize(300, 250);
		setMinimumSize(new Dimension(300, 175));

		// Screen centered
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

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
		c.weightx = 1;
		panel.add(nameField, c);

		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0;
		panel.add(passwordLabel, c);

		c.gridx = 1;
		c.weightx = 1;
		panel.add(passwordField, c);

		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0;
		panel.add(firstNameLabel, c);

		c.gridx = 1;
		c.weightx = 1;
		panel.add(firstNameField, c);

		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 0;
		panel.add(lastNameLabel, c);

		c.gridx = 1;
		c.weightx = 1;
		panel.add(lastNameField, c);

		c.gridx = 0;
		c.gridy = 4;
		c.weighty = 0;
		panel.add(saveButton, c);

		c.gridx = 1;
		panel.add(closeButton, c);

		getContentPane().add(panel);

		saveButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					String name = nameField.getText();
					if (name.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Nom obligatoire!", "Avertissement", JOptionPane.WARNING_MESSAGE);
						return;
					}

					String password = new String(passwordField.getPassword());
					if (password.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Mot de passe obligatoire!", "Avertissement", JOptionPane.WARNING_MESSAGE);
						return;
					}

					String firstName = firstNameField.getText();
					if (firstName.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Prénom obligatoire!", "Avertissement", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					
					String lastName = lastNameField.getText();
					if (lastName.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Nom obligatoire!", "Avertissement", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					

					loginInfo = new LoginInfo(name, password, firstName, lastName);

					dispose();
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
