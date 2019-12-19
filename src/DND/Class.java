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
	
	public static Class fromJson(String json)
	{
		return new Gson().fromJson(json, Class.class);
	}
	
	public String toJson()
	{
		return new Gson().toJson(this);
	}
	
	public String toString()
	{
		return data.toString();
	}
}
