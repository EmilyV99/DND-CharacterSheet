package DND;

public class Race
{
	String name;
	String description;
	
	public String toString()
	{
		StringBuilder buffer = new StringBuilder();
		buffer.append(name);
		buffer.append(delimiter);
		buffer.append(description);
		return buffer.toString();
	}
	
	public Race(String name, String description)
	{
		this.name = name;
		this.description = description;
	}
	
	public Race(String fromString)
	{
		String[] from = fromString.split(delimiter);
		name = from[0];
		description = from[1];
	}
	
	private static String delimiter = "~rdelim~";
	//
	
	public static Race[] packRaces;
	private static boolean initialized;
	
	private static void initRaceData()
	{
		if (initialized)
			return;
		initialized = true;
		packRaces = new Race[12];
		packRaces[DefaultRace.Dwarf.ordinal()] = new Race("Dwarf", "");
		packRaces[DefaultRace.Elf.ordinal()] = new Race("Elf", "");
		packRaces[DefaultRace.Halfling.ordinal()] = new Race("Halfling", "");
		packRaces[DefaultRace.Human.ordinal()] = new Race("Human", "");
		packRaces[DefaultRace.Dragonborn.ordinal()] = new Race("Dragonborn", "");
		packRaces[DefaultRace.Gnome.ordinal()] = new Race("Gnome", "");
		packRaces[DefaultRace.Half_Elf.ordinal()] = new Race("Half_Elf", "");
		packRaces[DefaultRace.Half_Orc.ordinal()] = new Race("Half_Orc", "");
		packRaces[DefaultRace.Tiefling.ordinal()] = new Race("Tiefling", "");
	}
	
	public Race getRace(DefaultRace raceName)
	{
		if (!initialized)
			initRaceData();
		return packRaces[raceName.ordinal()];
	}
	
	public enum DefaultRace
	{
		Dwarf, Elf, Halfling, Human, Dragonborn, Gnome, Half_Elf, Half_Orc, Tiefling
	}
}
