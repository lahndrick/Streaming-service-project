import java.util.Scanner;

public class clientSide implements play
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
