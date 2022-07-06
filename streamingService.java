import java.util.ArrayList;
import java.util.Scanner;

public class streamingService 
{
	private ArrayList<Content> arrayList;
	
	public streamingService()
	{
		this.arrayList = new ArrayList<Content>();
	}
	
	public void add(Content input)
	{
		if(input != null)
		{
			this.arrayList.add(input);
		}
	}
	
	public ArrayList<Content> match(String input)
	{
		ArrayList<Content> newList = new ArrayList<Content>();
		
		for(int x = 0; x <this.arrayList.size();x++)
		{
			if(arrayList.get(x).match(input))
			{
				newList.add(arrayList.get(x));
			}
		}

		return newList;
	}
	
	public String toString()
	{		
		for(int x = 0; x < this.arrayList.size();x++)
		{
			System.out.println((x + 1) + ". " + this.arrayList.get(x));
		}
		System.out.println();
		
		return "";
	}
}

interface play 
{
	public Content getCurrentStream();
	public void stream(String query);
	public void stop();
}

class Song extends Content
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

class Movie extends Content
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

class Content implements Comparable<String>
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

class clientSide implements play
{
	private streamingService service;
	private Content currentlyStreaming;
	
	public clientSide(streamingService service) 
	{
		this.service = service;
		this.currentlyStreaming = null;
	}

	@Override
	public Content getCurrentStream() 
	{
		return this.currentlyStreaming;
	}

	@Override
	public void stream(String query)
	{
		this.currentlyStreaming = service.match(query).get(0);
	}

	@Override
	public void stop()
	{
		this.currentlyStreaming = null;
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		
		Movie movie1 = new Movie("first movie", "publisher0", "10/9/1997", "crew1");
		Movie movie2 = new Movie("second movie", "publisher1", "15/4/1977", "crew2");
		Movie movie3 = new Movie("third movie", "publisher2", "9/09/1952", "crew3");
		Movie movie4 = new Movie("fourth movie", "publisher3", "20/12/2001", "crew1");
		Movie movie5 = new Movie("fifth movie", "publisher4", "11/4/2012", "crew2");
		Song song1 = new Song("first song", "publisher5", "5/7/2003", "artist1");
		Song song2 = new Song("second song", "publisher6", "12/9/2022", "artist2");
		Song song3 = new Song("third song", "publisher7", "1/2/2021", "artist3");
		Song song4 = new Song("fourth song", "publisher8", "17/3/2005", "artist1");
		Song song5 = new Song("fifth song", "publisher9", "10/05/1997", "artist2");
		
		streamingService service = new streamingService();
		clientSide client = new clientSide(service);
		
		service.add(movie1);
		service.add(movie2);
		service.add(movie3);
		service.add(movie4);
		service.add(movie5);
		service.add(song1);
		service.add(song2);
		service.add(song3);
		service.add(song4);
		service.add(song5);
		
		for(;;)
		{
			System.out.println("A. Display Digital Content library");
			System.out.println("B. Display currently streaming DigitalContent");
			System.out.println("C. Match Digital Content to Stream");
			System.out.println("D. Stop streaming");
			System.out.println("E. Quit Client Application");
			String input = scan.nextLine();
			
			if(input.equalsIgnoreCase("a"))
			{
				service.toString();
			}
			else if(input.equalsIgnoreCase("b"))
			{
				if(client.getCurrentStream() == null)
				{
					System.out.println("Nothing is currently streaming\n");
				}
				else
				{
					System.out.println(client.getCurrentStream());
				}
			}
			else if(input.equalsIgnoreCase("c"))
			{
				System.out.println("What would you like to stream?");
				input = scan.nextLine();
				
				client.stream(input);
				System.out.println("Currently streaming: " + client.getCurrentStream());
				System.out.println();
			}
			else if(input.equalsIgnoreCase("d"))
			{
				client.stop();
				System.out.println("You are no longer streaming anything\n");
			}
			else if(input.equalsIgnoreCase("e"))
			{
				System.exit(0);
			}
		}
	}
}
