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
		packClasses[DefaultClass.Barbarian.ordinal()] = new ClassData("Barbarian", "A fierce warrior of primitive background who can enter a battle rage");
		packClasses[DefaultClass.Bard.ordinal()] = new ClassData("Bard", "An inspiring magician whose power echoes the music of creation");
		packClasses[DefaultClass.Cleric.ordinal()] = new ClassData("Cleric", "A priestly champion who wields divine magic in service of a higher power");
		packClasses[DefaultClass.Druid.ordinal()] = new ClassData("Druid", "A priest of the Old Faith, wielding the powers of nature — moonlight and plant growth, fire and lightning — and adopting animal forms");
		packClasses[DefaultClass.Fighter.ordinal()] = new ClassData("Fighter", "A master of martial combat, skilled with a variety of weapons and armor");
		packClasses[DefaultClass.Monk.ordinal()] = new ClassData("Monk", "A master of martial arts, harnessing the power of the body in pursuit of physical and spiritual perfection");
		packClasses[DefaultClass.Paladin.ordinal()] = new ClassData("Paladin", "A holy warrior bound to a sacred oath");
		packClasses[DefaultClass.Ranger.ordinal()] = new ClassData("Ranger", "A warrior who uses martial prowess and nature magic to combat threats on the edges of civilization");
		packClasses[DefaultClass.Rogue.ordinal()] = new ClassData("Rogue", "A scoundrel who uses stealth and trickery to overcome obstacles and enemies");
		packClasses[DefaultClass.Sorcerer.ordinal()] = new ClassData("Sorcerer", "A spellcaster who draws on inherent magic from a gift or bloodline");
		packClasses[DefaultClass.Warlock.ordinal()] = new ClassData("Warlock", "A wielder of magic that is derived from a bargain with an extraplanar entity");
		packClasses[DefaultClass.Wizard.ordinal()] = new ClassData("Wizard", "A scholarly magic-user capable of manipulating the structures of reality");
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
