
/**
 * Dummy class
 * @author Maxime Dupuis
 *
 */
public class LocationFilm
{
	String nom;
	boolean nouveauté;
	
	public LocationFilm(String nom, boolean nouveauté)
	{
		this.nom = nom;
		this.nouveauté = nouveauté;
	}
	
	public String toString()
	{
		String str = "(" + nom + ": ";
		
		if(nouveauté)
			str += "Nouveauté";
		else
			str += "Régulier";
		
		str += ")";
		
		
		return str;
	}
}
