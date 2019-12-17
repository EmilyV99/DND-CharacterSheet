package DND;

public class ClassData
{
	String title;
	String description;
	public String toString()
	{
		StringBuilder buffer = new StringBuilder();
		buffer.append(title);
		buffer.append(delimiter);
		buffer.append(description);
		return buffer.toString();
	}
	public ClassData(String title, String description)
	{
		this.title = title;
		this.description = description;
	}
	public ClassData(String fromstring)
	{
		String[] from = fromstring.split(delimiter);
		title = from[0];
		description = from[1];
	}
	private static String delimiter = "~cddelim~";
	//
	public static ClassData[] packClasses;
	private static boolean initialized;
	
	private static void initClassData()
	{
		if (initialized)
			return;
		initialized = true;
		packClasses = new ClassData[12];
		packClasses[DefaultClass.Barbarian.ordinal()] = new ClassData("Barbarian", "");
		packClasses[DefaultClass.Bard.ordinal()] = new ClassData("Bard", "");
		packClasses[DefaultClass.Cleric.ordinal()] = new ClassData("Cleric", "");
		packClasses[DefaultClass.Druid.ordinal()] = new ClassData("Druid", "");
		packClasses[DefaultClass.Fighter.ordinal()] = new ClassData("Fighter", "");
		packClasses[DefaultClass.Monk.ordinal()] = new ClassData("Monk", "");
		packClasses[DefaultClass.Paladin.ordinal()] = new ClassData("Paladin", "");
		packClasses[DefaultClass.Ranger.ordinal()] = new ClassData("Ranger", "");
		packClasses[DefaultClass.Rogue.ordinal()] = new ClassData("Rogue", "");
		packClasses[DefaultClass.Sorcerer.ordinal()] = new ClassData("Sorcerer", "");
		packClasses[DefaultClass.Warlock.ordinal()] = new ClassData("Warlock", "");
		packClasses[DefaultClass.Wizard.ordinal()] = new ClassData("Wizard", "");
	}
	
	public ClassData getClass(DefaultClass className)
	{
		if (!initialized)
			initClassData();
		return packClasses[className.ordinal()];
	}
	
	public enum DefaultClass
	{
		Barbarian, Bard, Cleric, Druid, Fighter, Monk, Paladin, Ranger, Rogue, Sorcerer, Warlock, Wizard
	}
}
