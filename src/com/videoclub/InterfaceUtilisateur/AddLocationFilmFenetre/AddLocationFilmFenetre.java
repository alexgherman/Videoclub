import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 * Crée une fenêtre reliée à un DataBase.
 * Permet d'ajouter des films à la DataBase si l'utilisateur le désire
 * @author Maxime Dupuis
 *
 */
@SuppressWarnings("serial")
public class AddLocationFilmFenetre extends JFrame
{
	public static void main(String[] args)
	{
		DataBase db = new DataBase();
		new AddLocationFilmFenetre(db);
	}
	
	DataBase db;
	
	JLabel nomLabel = new JLabel("Nom du film");
	JTextField nomTxtField = new JTextField();
	
	JLabel quantitéLabel = new JLabel("Quantité");
	JSpinner quantitéSpinner = new JSpinner();
	
	JLabel nouveautéLabel = new JLabel("Nouveauté");
	JCheckBox nouveautéCheckBox = new JCheckBox();

	JButton enregistrerBouton = new JButton("Enregistrer");
	JButton annulerBouton = new JButton("Annuler");
	
	
	
	
	AddLocationFilmFenetre(final DataBase db)
	{
		super("Ajouter Location");
		this.db = db;
		setSize(400, 250);
		setVisible(true);
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10,10,10,10);
		
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0;
		panel.add(nomLabel,c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 10;
		panel.add(nomTxtField,c);
		
		
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0;
		panel.add(quantitéLabel,c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 10;
		panel.add(quantitéSpinner,c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0;
		panel.add(nouveautéLabel,c);
		
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 10;
		panel.add(nouveautéCheckBox,c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 0;
		panel.add(enregistrerBouton,c);
		
		c.gridx = 1;
		c.gridy = 3;
		c.weightx = 0;
		panel.add(annulerBouton,c);
		
		getContentPane().add(panel);
		
		quantitéSpinner.setValue(1);
		
		enregistrerBouton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				String nom = nomTxtField.getText();
				boolean nouveauté = nouveautéCheckBox.isSelected();
				LocationFilm film = new LocationFilm(nom, nouveauté);
				
				for(int i=0; i < (Integer)quantitéSpinner.getValue(); ++i)
					db.addLocation(film);
			}
		});
		
		annulerBouton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				dispose();
			}
		});
	}
}
