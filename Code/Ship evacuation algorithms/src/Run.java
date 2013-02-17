
public class Run {
	public static void main(String [ ] args)
	{
		Shipbuilder sb = new Shipbuilder();		
		Ship s = sb.createCelebrityXpedition();
		System.out.println(s.fifthFlor.size());		
	}
}
