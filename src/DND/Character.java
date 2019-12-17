package DND;

import java.util.ArrayList;

public class Character
{
	public static ArrayList<Character> loaded = new ArrayList<>();
	private static String delimiter = "~chardelim~";
	private static String c_arr_delimiter = "~`cdelim`~";
	public ArrayList<Class> classes = new ArrayList<>();
	public String name;
	public Race race;
	public int age;
	public byte str, dex, con, intel, wis, cha;
	
	protected Character() {}
	
	private Character(String loadstr)
	{
		String[] from = loadstr.split(delimiter);
		load_classes(from[0]);
		name = from[1];
		race = new Race(from[2]);
		age = Integer.parseInt(from[3]);
		str = Byte.parseByte(from[4]);
		dex = Byte.parseByte(from[5]);
		con = Byte.parseByte(from[6]);
		intel = Byte.parseByte(from[7]);
		wis = Byte.parseByte(from[8]);
		cha = Byte.parseByte(from[9]);
		loaded.add(this);
	}
	
	public String toString()
	{
		StringBuilder buffer = new StringBuilder();
		buffer.append(classes_tostring());
		buffer.append(delimiter);
		buffer.append(name);
		buffer.append(delimiter);
		buffer.append(race.toString());
		buffer.append(delimiter);
		buffer.append(name);
		buffer.append(delimiter);
		buffer.append(race.toString());
		buffer.append(delimiter);
		buffer.append(age);
		buffer.append(delimiter);
		buffer.append(str);
		buffer.append(delimiter);
		buffer.append(dex);
		buffer.append(delimiter);
		buffer.append(con);
		buffer.append(delimiter);
		buffer.append(intel);
		buffer.append(delimiter);
		buffer.append(wis);
		buffer.append(delimiter);
		buffer.append(cha);
		return buffer.toString();
	}
	
	private String classes_tostring()
	{
		StringBuilder buffer = new StringBuilder();
		buffer.append(classes.get(0).toString());
		for (int q = 1; q < classes.size(); ++q)
		{
			buffer.append(c_arr_delimiter);
			buffer.append(classes.get(q).toString());
		}
		return buffer.toString();
	}

	private void load_classes(String class_str)
	{
		String[] class_strs = class_str.split(c_arr_delimiter);
		for(String str : class_strs)
		{
			classes.add(new Class(str));
		}
	}
}
