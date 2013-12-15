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
 * Fenêtre qui demande le nom et le mot de passe à l'utilisateur
 * 
 * @author Maxime Dupuis
 *
 */
@SuppressWarnings("serial")
public class LoginWindow extends JDialog
{	
	private JLabel nameLabel = new JLabel("Nom:");
	private JTextField nameField = new JTextField();
	
	private JLabel passwordLabel = new JLabel("Password:");
	private JPasswordField passwordField = new JPasswordField();
	
	private JButton saveButton = new JButton("OK");
	private JButton closeButton = new JButton("Fermer");
	
	private LoginInfo loginInfo = null;
	
	public LoginInfo getLoginInfo()
	{
		return loginInfo;
	}
	
	LoginWindow()
	{
		super((Frame)null, "Identification", true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		// Screen Size
		setSize(300,175);
		setMinimumSize(new Dimension(300,175));

		// Screen centered
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2
				- getSize().height / 2);

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
		c.weightx = 1;
		panel.add(nameField,c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0;
		panel.add(passwordLabel,c);
		
		c.gridx = 1;
		c.weightx = 1;
		panel.add(passwordField,c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.weighty = 0;
		panel.add(saveButton,c);

		c.gridx = 1;
		panel.add(closeButton,c);
		
		getContentPane().add(panel);
				
		saveButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				String name = nameField.getText();
				if(name.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Nom obligatoire!", "Message", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				String password = new String(passwordField.getPassword());
				if(password.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Mot de passe obligatoire!", "Message", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				loginInfo = new LoginInfo(name, password);
				
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
