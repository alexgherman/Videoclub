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

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.videoclub.models.article.Article;

@SuppressWarnings("serial")
/**
 * Fen�tre d'articles � acheter
 * @author Maxime Dupuis
 *
 */
public class PickItemsWindow extends JDialog
{
	private ArrayList<com.videoclub.models.article.Article> itemChoices = new ArrayList<com.videoclub.models.article.Article>();
	private ArrayList<com.videoclub.models.article.Article> selectedItems = new ArrayList<com.videoclub.models.article.Article>();

	private JLabel choiceLabel = new JLabel("Choix:");
	private JLabel selectionLabel = new JLabel("Votre s�lection:");

	private JButton okButton = new JButton("OK");
	private JButton closeButton = new JButton("Fermer");

	private JPanel itemChoicesPanel = new JPanel();
	private JScrollPane choicesPanel = new JScrollPane(itemChoicesPanel);

	private JPanel itemSelectionPanel = new JPanel();
	private JScrollPane selectionPanel = new JScrollPane(itemSelectionPanel);

	public ArrayList<com.videoclub.models.article.Article> getSelection()
	{
		return selectedItems;
	}

	/**
	 * Constructeur
	 */
	PickItemsWindow(final VideoClub videoClub)
	{
		super((Frame) null, "Choisir articles", true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		// Screen Size
		setSize(600, 400);
		setMinimumSize(new Dimension(600, 400));

		// Screen centered
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

		itemChoicesPanel.setLayout(new BoxLayout(itemChoicesPanel, BoxLayout.Y_AXIS));
		itemSelectionPanel.setLayout(new BoxLayout(itemSelectionPanel, BoxLayout.Y_AXIS));

		itemChoices = videoClub.getItemChoices();

		for (com.videoclub.models.article.Article item : itemChoices)
		{
			addChoiceitem(item);
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
					selectedItems.clear();
					dispose();
				}
			});
	}

	/**
	 * Ajoute un item dans la liste des choix
	 */
	private void addChoiceitem(final com.videoclub.models.article.Article item)
	{
		JButton bouton = new JButton(item.toString());
		itemChoicesPanel.add(bouton);

		bouton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					addSelectionitem(item);
					removeChoiceitem(item);
				}
			});
		revalidate();
	}

	/**
	 * Enl�ve un item de la liste des choix
	 */
	private void removeChoiceitem(com.videoclub.models.article.Article item)
	{
		for (Component c : itemChoicesPanel.getComponents())
		{
			JButton bouton = (JButton) c;

			if (bouton.getText().equals(item.toString()))
			{
				itemChoicesPanel.remove(bouton);
				this.update(getGraphics());
				break;
			}
		}

		revalidate();
	}

	/**
	 * Ajoute un item dans la liste de s�lection
	 */
	private void addSelectionitem(final com.videoclub.models.article.Article item)
	{
		JButton bouton = new JButton(item.toString());
		itemSelectionPanel.add(bouton);

		bouton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					addChoiceitem(item);
					removeSelectionitem(item);
				}
			});

		selectedItems.add(item);
		revalidate();
	}

	/**
	 * Enl�ve un item de la liste des s�lections
	 */
	private void removeSelectionitem(com.videoclub.models.article.Article item)
	{
		for (Component c : itemSelectionPanel.getComponents())
		{
			JButton bouton = (JButton) c;
			bouton.setVisible(true);

			if (bouton.getText().equals(item.toString()))
			{
				itemSelectionPanel.remove(bouton);
				this.update(getGraphics());
				break;
			}
		}

		selectedItems.remove(item);

		revalidate();
	}
}
