/**
 * Dummy class
 * @author Maxime Dupuis
 *
 */
public class VideoClub
{
	public VideoClub()
	{
		
	}
	
	public void addLocationFilm()
	{
		new AddLocationFilmFenetre(this);
	}
		
	public void addLocation(LocationFilm location)
	{
		System.out.println(location + " saved to rentableMovies");
	}
	
	public void addArticle()
	{
		new AddArticleFenetre(this);
	}
		
	public void addArticle(SellableItem article)
	{
		System.out.println(article + " saved to sellableItems");
	}
}
