package DND;

import com.google.gson.Gson;

public class Class
{
	ClassData data;
	byte level;
	
	public Class(ClassData data, byte level)
	{
		this.data = data;
		this.level = level;
	}
	
	public static Class fromString(String json)
	{
		return new Gson().fromJson(json, Class.class);
	}
	
	public String toString()
	{
		return new Gson().toJson(this);
	}
	
	private static String delimiter = "~cdelim~";
}
