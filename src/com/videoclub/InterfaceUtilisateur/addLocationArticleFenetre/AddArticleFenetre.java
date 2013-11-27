import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 * Crée une fenêtre reliée à un VideoClub. 
 * Permet d'y ajouter des articles à vendre
 * 
 * @author Maxime Dupuis
 *
 */
@SuppressWarnings("serial")
public class AddArticleFenetre extends JFrame
{
	public static void main(String[] args)
	{
		VideoClub vc = new VideoClub();
		vc.addArticle();
	}

	VideoClub videoClub;
	
	JLabel nomLabel = new JLabel("Nom de l'article:");
	JTextField nomTxtField = new JTextField();
	
	JLabel quantitéLabel = new JLabel("Quantité:");
	SpinnerNumberModel modelQ = new SpinnerNumberModel(1, 1, 99999, 1);
	JSpinner quantitéSpinner = new JSpinner(modelQ);
	
	JLabel prixLabel = new JLabel("Prix:");
	SpinnerNumberModel modelP = new SpinnerNumberModel(new Double(1.99), new Double(0), new Double(99999.99), new Double(.01));
	JSpinner prixSpinner = new JSpinner(modelP);
	
	JButton enregistrerBouton = new JButton("Enregistrer");
	JButton annulerBouton = new JButton("Annuler");
	
	AddArticleFenetre(final VideoClub db)
	{
		super("Ajouter Article");
		this.videoClub = db;
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
		panel.add(prixLabel,c);
		
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 10;
		panel.add(prixSpinner,c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 0;
		panel.add(enregistrerBouton,c);
		
		c.gridx = 1;
		c.gridy = 3;
		c.weightx = 0;
		panel.add(annulerBouton,c);
		
		getContentPane().add(panel);
				
		enregistrerBouton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				String nom = nomTxtField.getText();
				if(nom.equals(""))
				{
					JOptionPane.showMessageDialog(null, "L'article n'a pas de nom!", "Message", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				int prix = (int)((double)prixSpinner.getValue()*100);
				if(prix == 0)
				{
					int reponse = JOptionPane.showConfirmDialog(null, "L'article est-il vraiment gratuit?", "Confirmation", JOptionPane.YES_NO_OPTION);
					if(reponse == JOptionPane.NO_OPTION)
						return;
				}
				
				
				SellableItem article = new SellableItem(nom, prix);
				
				for(int i=0; i < (Integer)quantitéSpinner.getValue(); ++i)
					db.addArticle(article);
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
