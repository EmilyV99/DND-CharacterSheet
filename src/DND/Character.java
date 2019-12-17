package DND;

import java.util.ArrayList;

public class Character
{
	public static ArrayList<Character> loaded = new ArrayList<>();
	public String name;
	public int age;
	public byte str, dex, con, intel, wis, cha;
	
	protected Character() {}
	
	private Character(String loadstr)
	{
		loaded.add(this);
	}
}
