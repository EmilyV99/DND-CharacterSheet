package DND;

public class Class
{
	ClassData data;
	byte level;
	public String toString()
	{
		StringBuilder buffer = new StringBuilder();
		buffer.append(data.toString());
		buffer.append(delimiter);
		buffer.append(level);
		return buffer.toString();
	}
	public Class(ClassData data, byte level)
	{
		this.data = data;
		this.level = level;
	}
	public Class(String fromstring)
	{
		String[] from = fromstring.split(delimiter);
		data = new ClassData(from[0]);
		level = Byte.parseByte(from[1]);
	}
	private static String delimiter = "~cdelim~";
}
