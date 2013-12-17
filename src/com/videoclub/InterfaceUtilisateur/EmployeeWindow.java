package com.videoclub.InterfaceUtilisateur;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
/**
 * Fenêtre de l'employé/client
 * Construit un Cart
 * 
 * @author Maxime Dupuis
 *
 */
public class EmployeeWindow extends JDialog
{
	private VideoClub videoClub;

	JPanel panel;

	private JButton pickMoviesButton = new JButton("Choisir Films");
	private JButton pickItemsButton = new JButton("Choisir Articles");

	JPanel cartPanel = new JPanel();
	private Cart cart;

	private JButton payButton = new JButton("Payer");
	private JButton emptyCartButton = new JButton("Effacer tout");

	/**
	 * Constructeur
	 */
	EmployeeWindow(final VideoClub videoClub)
	{
		super((Frame) null, "Mode Employé", true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// Cart panel
		cart = new Cart(videoClub);
		this.videoClub = videoClub;

		// Screen Size
		setSize(300, 500);
		setMinimumSize(new Dimension(300, 500));

		// Screen centered
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2
				- getSize().height / 2);

		// Window Layout
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10, 10, 10, 10);

		c.gridx = 0;
		c.gridy = 0;
		panel.add(pickMoviesButton, c);

		c.gridx = 1;
		panel.add(pickItemsButton, c);

		updateCartPanel();

		c.gridx = 0;
		c.gridy = 2;
		panel.add(payButton, c);

		c.gridx = 1;
		panel.add(emptyCartButton, c);

		getContentPane().add(panel);

		pickMoviesButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					PickMoviesWindow win = new PickMoviesWindow(videoClub);
					win.setVisible(true);

					Vector<RentableMovie> selectedMovies = win.getSelection();

					if (!selectedMovies.isEmpty())
					{
						cart.setMovies(selectedMovies);
						updateCartPanel();
					}
				}
			});

		pickItemsButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{

					PickItemsWindow win = new PickItemsWindow(videoClub);
					win.setVisible(true);

					Vector<SellableItem> selectedItems = win.getSelection();

					if (!selectedItems.isEmpty())
					{
						cart.setItems(selectedItems);
						updateCartPanel();
					}
				}
			});

		payButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					JOptionPane.showMessageDialog(
							null,
							"Demander au client de payer: "
									+ ((float) cart.getTotal() / 100) + " $",
							"Paiement", JOptionPane.INFORMATION_MESSAGE);
				}
			});

		emptyCartButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					cart.clear();
				}
			});
	}

	private void updateCartPanel()
	{
		cartPanel.removeAll();
		cartPanel = cartPanel(cart, videoClub);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weighty = 1;
		c.weightx = 1;

		panel.add(cartPanel, c);

		panel.revalidate();
	}

	private JPanel cartPanel(Cart cart, VideoClub videoClub)
	{
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.X_AXIS));

		JPanel thingsPanel = new JPanel();
		JPanel pricesPanel = new JPanel();

		thingsPanel.setLayout(new BoxLayout(thingsPanel, BoxLayout.Y_AXIS));
		pricesPanel.setLayout(new BoxLayout(pricesPanel, BoxLayout.Y_AXIS));
		//pricesPanel.setAlignmentX(RIGHT_ALIGNMENT);

		listPanel.add(thingsPanel);
		listPanel.add(pricesPanel);

		JScrollPane scrollPane = new JScrollPane(listPanel);

		for (RentableMovie movie : cart.getMovies())
		{
			String thing = "Location: " + movie.getName();
			String price = ((float) videoClub.getMoviePrice(movie) / 100)
					+ " $";

			thingsPanel.add(new JLabel(thing));
			pricesPanel.add(new JLabel(price));
		}

		for (SellableItem item : cart.getItems())
		{
			String thing = "Achat: " + item.getName();
			String price = ((float) item.getPrice() / 100) + " $";

			thingsPanel.add(new JLabel(thing));
			pricesPanel.add(new JLabel(price));
		}

		String price = ((float) cart.getTotal() / 100) + " $";
		thingsPanel.add(new JLabel("Total"));
		pricesPanel.add(new JLabel(price));

		// Put everything in a single JPanel that will be returned
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		// c.insets = new Insets(10, 10, 10, 10);

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		panel.add(new JLabel("Panier"), c);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.weighty = 1;
		c.weightx = 1;
		panel.add(scrollPane, c);

		return panel;
	}
}
