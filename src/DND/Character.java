package DND;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Character
{
	public static ArrayList<Character> loaded = new ArrayList<>();
	public ArrayList<Class> classes = new ArrayList<>();
	public String name;
	public Race race;
	public int age;
	public byte str, dex, con, intel, wis, cha;
	
	protected Character() {}
	
	public static Character fromString(String json)
	{
		return new Gson().fromJson(json, Character.class);
	}
	
	public String toString()
	{
		return new Gson().toJson(this);
	}
}
