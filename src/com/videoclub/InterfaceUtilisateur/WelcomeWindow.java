package com.videoclub.InterfaceUtilisateur;

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
import javax.swing.JPanel;

@SuppressWarnings("serial")
/**
 * Fenêtre de bienvenue
 * La première fenêtre ouverte par le programme
 * 
 * Donne le choix entre le mode Employé ou Gérant
 * 
 * @author Maxime Dupuis
 *
 */
public class WelcomeWindow extends JFrame
{
	private JLabel welcomeLabel = new JLabel("<HTML><U>Bienvenue!<U><HTML>");

	private JButton employeeButton = new JButton("Employé");
	private JButton managerButton = new JButton("Gérant");

	/**
	 * Constructeur
	 */
	WelcomeWindow(final VideoClub videoClub)
	{
		super(videoClub.getName());	//Titre de la fenêtre		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//Screen Size
		setSize(300, 200);
		setMinimumSize(new Dimension(300,200));
		
		//Screen centered
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);

		welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
		
		//Window Layout
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.ipady = 10;
		c.weightx = 1;
		c.insets = new Insets(10,10,10,10);
		
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		panel.add(welcomeLabel, c);

		c.weighty = 0;
		c.gridy=1;
		c.gridwidth = 1;
		panel.add(employeeButton, c);

		c.gridx = 1;
		panel.add(managerButton, c);

		getContentPane().add(panel);

		employeeButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					EmployeeWindow win = new EmployeeWindow(videoClub);
					win.setVisible(true);
				}
			});

		managerButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					ManagerWindow win = new ManagerWindow(videoClub);
					win.setVisible(true);
					
				}
			});
	}
}
