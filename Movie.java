public class Movie extends Content
{
	private String cast;

	public Movie(String title, String publisher, String releaseDate, String cast) 
	{
		super(title, publisher, releaseDate);
		this.cast = cast;
	}

	public String getCast() 
	{
		return cast;
	}

	public void setCast(String cast) 
	{
		this.cast = cast;
	}

	public String toString()
	{
		return super.toString() + ", cast: " + this.getCast();
	}

	@Override
	public boolean match(String input)
	{
		if(this.getCast().contains(input.toLowerCase()))
		{
			return true;
		}

		return super.match(input);
	}
}
