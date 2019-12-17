import java.util.ArrayList;

public class Character
{
	public static ArrayList<Character> loaded;
	public byte str, dex, con, intel, wis, cha;
	
	protected Character() {}
	
	private Character(String loadstr)
	{
		loaded.add(this);
	}
}
