public class Song extends Content
{
	private String artist;

	public String getArtist() 
	{
		return artist;
	}

	public void setArtist(String artist) 
	{
		this.artist = artist;
	}

	public Song(String title, String publisher, String releaseDate, String artist) 
	{
		super(title, publisher, releaseDate);
		this.artist = artist;
	}

	public String toString()
	{
		return super.toString() + ", artist: " + this.getArtist();
	}

	@Override
	public boolean match(String input)
	{
		if(this.getArtist().contains(input.toLowerCase()))
		{
			return true;
		}

		return super.match(input);
	}
}
