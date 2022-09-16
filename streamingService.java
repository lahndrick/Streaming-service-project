import java.util.ArrayList;

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
