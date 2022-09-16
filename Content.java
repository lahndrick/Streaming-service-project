public class Content implements Comparable<String>
{
	private String title;
	private String publisher;
	private String releaseDate;

	public String getTitle() 
	{
		return title;
	}

	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getPublisher() 
	{
		return publisher;
	}

	public void setPublisher(String publisher) 
	{
		this.publisher = publisher;
	}

	public String getReleaseDate()
	{
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) 
	{
		this.releaseDate = releaseDate;
	}

	public Content(String title, String publisher, String releaseDate)
	{
		this.title = title;
		this.publisher = publisher;
		this.releaseDate = releaseDate;
	}

	public String toString()
	{
		return "Title: " + this.getTitle() + ", publisher: " + this.getPublisher() + ", released: " + this.getReleaseDate();
	}

	public boolean match(String input)
	{
		if(this.getTitle().contains(input.toLowerCase()))
		{
			return true;
		}
		else if(this.getPublisher().contains(input.toLowerCase()))
		{
			return true;
		}
		else if(this.getReleaseDate().contains(input.toLowerCase()))
		{
			return true;
		}

		return false;
	}

	@Override
	public int compareTo(String o) 
	{
		return this.title.compareToIgnoreCase(o);
	}
}
