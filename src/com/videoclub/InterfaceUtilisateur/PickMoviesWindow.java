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

import com.videoclub.models.movie.Movie;

@SuppressWarnings("serial")
/**
 * Fen�tre de s�lection de films � louer
 * @author Maxime Dupuis
 *
 */
public class PickMoviesWindow extends JDialog
{
	private ArrayList<Movie> movieChoices = new ArrayList<Movie>();
	private ArrayList<Movie> selectedMovies = new ArrayList<Movie>();

	private JLabel choiceLabel = new JLabel("Choix:");
	private JLabel selectionLabel = new JLabel("Votre s�lection:");

	private JButton okButton = new JButton("OK");
	private JButton closeButton = new JButton("Fermer");

	private JPanel movieChoicesPanel = new JPanel();
	private JScrollPane choicesPanel = new JScrollPane(movieChoicesPanel); //Un JScrollPane garde toujours la m�me dimension. S'il y a trop de composants, il cr�e un scrollBar

	private JPanel movieSelectionPanel = new JPanel();
	private JScrollPane selectionPanel = new JScrollPane(movieSelectionPanel);

	public ArrayList<Movie> getSelection()
	{
		return selectedMovies;
	}

	/**
	 * Constructeur
	 */
	PickMoviesWindow(final VideoClub videoClub)
	{
		super((Frame) null, "Choisir films", true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		// Screen Size
		setSize(600, 400);
		setMinimumSize(new Dimension(600, 400));

		// Screen centered
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

		movieChoicesPanel.setLayout(new BoxLayout(movieChoicesPanel, BoxLayout.Y_AXIS));
		movieSelectionPanel.setLayout(new BoxLayout(movieSelectionPanel, BoxLayout.Y_AXIS));

		movieChoices = videoClub.getMovieChoices();

		for (Movie movie : movieChoices)
		{
			addChoiceMovie(movie);
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

		getContentPane().add(panel);

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
					selectedMovies.clear();
					dispose();
				}
			});
	}

	/**
	 * Ajoute un film dans la liste des choix
	 */
	private void addChoiceMovie(final Movie movie)
	{
		JButton bouton = new JButton(movie.toString());
		movieChoicesPanel.add(bouton);

		bouton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					addSelectionMovie(movie);
					removeChoiceMovie(movie);
				}
			});
		revalidate();
	}

	/**
	 * Enl�ve un film de la liste des choix
	 */
	private void removeChoiceMovie(Movie movie)
	{
		for (Component c : movieChoicesPanel.getComponents())
		{
			JButton bouton = (JButton) c;

			if (bouton.getText().equals(movie.toString()))
			{
				movieChoicesPanel.remove(bouton);
				this.update(getGraphics());
				break;
			}
		}

		revalidate();
	}

	/**
	 * Ajoute un film dans la liste de s�lection
	 */
	private void addSelectionMovie(final Movie movie)
	{
		JButton bouton = new JButton(movie.toString());
		movieSelectionPanel.add(bouton);

		bouton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					addChoiceMovie(movie);
					removeSelectionMovie(movie);
				}
			});

		selectedMovies.add(movie);
		revalidate();
	}

	/**
	 * Enl�ve un film de la liste des s�lections
	 */
	private void removeSelectionMovie(Movie movie)
	{
		for (Component c : movieSelectionPanel.getComponents())
		{
			JButton bouton = (JButton) c;
			bouton.setVisible(true);

			if (bouton.getText().equals(movie.toString()))
			{
				movieSelectionPanel.remove(bouton);
				this.update(getGraphics());
				break;
			}
		}

		selectedMovies.remove(movie);

		revalidate();
	}
}
