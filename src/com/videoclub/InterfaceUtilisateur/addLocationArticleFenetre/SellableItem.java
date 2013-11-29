
/**
 * Dummy class
 * @author Maxime Dupuis
 *
 */
public class SellableItem
{
	String nom;
	int prix;	//Le prix est en cent pour être plus précis *ATTENTION*
	
	public SellableItem(String nom, int prix)
	{
		this.nom = nom;
		this.prix = prix;
	}
	
	public String toString()
	{
		String str = "(" + nom + ": " + (float)prix / 100 + "$)";
		return str;
	}
}