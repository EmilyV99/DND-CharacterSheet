package DND;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class Race
{
	public static ArrayList<Race> customRaces = new ArrayList<>();
	public static Race[] packRaces;
	private static boolean initialized;
	byte str, dex, con, intel, wis, cha, assignable_points;
	String name;
	String description;
	String description_age;
	String description_physical;
	String description_society;
	String trait_age;
	String trait_alignment;
	String trait_size;
	String trait_speed;
	ArrayList<String> traits = new ArrayList<>();
	String languages;
	
	public Race(int str, int dex, int con, int intel, int wis, int cha, int assignable_points, String name, String description, String description_age, String description_physical, String description_society, String trait_age, String trait_alignment, String trait_size, String trait_speed, String[] traits, String languages)
	{
		this.str = (byte) str;
		this.dex = (byte) dex;
		this.con = (byte) con;
		this.intel = (byte) intel;
		this.wis = (byte) wis;
		this.cha = (byte) cha;
		this.assignable_points = (byte) assignable_points;
		this.name = name;
		this.description = description;
		this.description_age = description_age;
		this.description_physical = description_physical;
		this.description_society = description_society;
		this.trait_age = trait_age;
		this.trait_alignment = trait_alignment;
		this.trait_size = trait_size;
		this.trait_speed = trait_speed;
		this.traits.addAll(Arrays.asList(traits));
		this.languages = languages;
	}
	//
	
	public static void init()
	{
		if (initialized)
			return;
		initialized = true;
		initRaceData();
	}
	
	private static void initRaceData()
	{
		packRaces = new Race[DefaultRace.Num_Pack_Races.ordinal()];
		packRaces[DefaultRace.Dwarf__Hill.ordinal()] = new Race(0, 0, 2, 0, 1, 0, 0, "Hill Dwarf", "Bold and hardy, dwarves are known as skilled warriors, miners, and workers of stone and metal. Though they stand well under 5 feet tall, dwarves are so broad and compact that they can weigh as much as a human standing nearly two feet taller. Their courage and endurance are also easily a match for any of the larger folk.\nAs a hill dwarf, you have keen senses, deep intuition, and remarkable resilience.", "Can live to be over 400 years old. This longevity gives them a unique perspective compared to shorter-lived races.", "Skin color: Ranges from deep brown to paler hues tinged with red. Most common- light brown or deep tan.\nHair: Worn long but simply styled. Usually black, gray, or brown - though paler dwarves often have red hair. Male dwarves highly value their beards.", "Dwarven kingdoms stretch deep beneath the mountains where the dwarves mine gems and precious metals and forge " +
				"items of wonder. They love the beauty and artistry of precious metals and fine jewelry, and in some dwarves this love festers into avarice. Whatever wealth they can’t find in their mountains, they gain through trade. They dislike boats, so enterprising humans and halflings frequently handle trade in dwarven goods along water routes. Trustworthy members of other races are welcome in dwarf settlements, though som e areas are off limits even to them. The chief unit of dwarven society is the clan, and dwarves highly value social standing. Even dwarves w ho live far from their ow n kingdom s cherish their clan identities and affiliations, recognize related dwarves, and invoke their ancestors’ names in oaths and curses. To be clanless is the worst fate that can befall a dwarf. Dwarves in other lands are typically artisans, especially weaponsmiths, armorers, and jewelers. Som e become mercenaries or bodyguards, highly sought after for their courage and loyalty.", "Dwarves "
				+ "mature at the same rate as humans. They are considered adults at age 50, and live on average 350 years.", "Most dwarves are lawful, believing firmly in the benefits of a well-ordered society. They tend toward good as well, with a strong sense of fair play and a belief that everyone deserves to share in the benefits of a just order.", "Between 4-5 feet tall, average 150 pounds. Considered Medium size.", "Base walking speed of 25 feet. Speed is not reduced by heavy armor.", new String[]{"Darkvision: Within 60 feet, you can see in dim light as though it were bright, and dark as though it were dim light. You can not discern color in darkness, and see only in gray.", "Dwarven Resilience: You have advantage on saving throws against Poison, and resistance against Poison damage.", "Dwarven Combat Training: You have proficiency with the Battleaxe, Handaxe, Throwing Hammer, and Warhammer",
		                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     "Tool Proficiency: You gain proficiency with the artisan's tools of choice: Smith's " + "Tools, Brewer's Supplies, or Mason's Tools.", "Stonecunning: When making an Intelligence (History) check relating to stonework, you are considered proficient in History, and add DOUBLE your proficiency bonus.", "Dwarven Toughness: Start with +1 max HP, and gain +1 max HP each level up."}, "You can speak, read, and write Common and Dwarvish. As Dwarvish is full of hard consonants and guttural " +
				"sounds, these spill over into any other languages you might speak.");
		packRaces[DefaultRace.Dwarf__Mountain.ordinal()] = new Race(2, 0, 2, 0, 0, 0, 0, "Mountain Dwarf", "Bold and hardy, dwarves are known as skilled warriors, miners, and workers of stone and metal. Though they stand well under 5 feet tall, dwarves are so broad and compact that they can weigh as much as a human standing nearly two feet taller. Their courage and endurance are also easily a match for any of the larger folk.\nAs a mountain dwarf, you're strong and hardy, accustom ed to a difficult life in rugged terrain. You’re probably on the tall side (for a dwarf), and tend toward lighter coloration.", "Can live to be over 400 years old. This longevity gives them a unique perspective compared to shorter-lived races.", "Skin color: Ranges from deep brown to paler hues tinged with red. Most common- light brown or deep tan.\nHair: Worn long but simply styled. Usually black, gray, or brown - though paler dwarves often have red hair. Male dwarves highly value their beards.", "Dwarven"
				+ " kingdoms stretch deep beneath the mountains where the dwarves mine gems and precious metals and forge items of wonder. They love the beauty and artistry of precious metals and fine jewelry, and in some dwarves this love festers into avarice. Whatever wealth they can’t find in their mountains, they gain through trade. They dislike boats, so enterprising humans and halflings frequently handle trade in dwarven goods along water routes. Trustworthy members of other races are welcome in dwarf settlements, though som e areas are off limits even to them. The chief unit of dwarven society is the clan, and dwarves highly value social standing. Even dwarves w ho live far from their ow n kingdom s cherish their clan identities and affiliations, recognize related dwarves, and invoke their ancestors’ names in oaths and curses. To be clanless is the worst fate that can befall a dwarf. Dwarves in other lands are typically artisans, especially weaponsmiths, armorers, and " +
				"jewelers. Some become mercenaries or bodyguards, highly sought after for their courage and loyalty.", "Dwarves mature at the same rate as humans. They are considered adults at age 50, and live on average 350 years.", "Most dwarves are lawful, believing firmly in the benefits of a well-ordered society. They tend toward good as well, with a strong sense of fair play and a belief that everyone deserves to share in the benefits of a just order.", "Between 4-5 feet tall, average 150 pounds. Considered Medium size.", "Base walking speed of 25 feet. Speed is not reduced by heavy armor.", new String[]{"Darkvision: Within 60 feet, you can see in dim light as though it were bright, and dark as though it were dim light. You can not discern color in darkness, and see only in gray.", "Dwarven Resilience: You have advantage on saving throws against Poison, and resistance against Poison damage.", "Dwarven Combat Training: You have proficiency with the Battleaxe, Handaxe, " +
				"Throwing Hammer, and Warhammer", "Tool Proficiency: You gain proficiency with the artisan's tools of choice: Smith's Tools, Brewer's Supplies, or Mason's Tools.", "Stonecunning: When making an Intelligence (History) check relating to stonework, you are considered proficient in History, and add DOUBLE your proficiency bonus.", "Dwarven Armor Training: You are proficient with Light and Medium armor."}, "You can speak, read, and write Common and Dwarvish. As Dwarvish is full of hard consonants and guttural sounds, these spill over into any other languages you might speak.");
		packRaces[DefaultRace.Elf.ordinal()] = new Race(0, 0, 0, 0, 0, 0, 0, "Elf", "", "", "", "", "", "", "", "", new String[]{""}, "");
		packRaces[DefaultRace.Halfling.ordinal()] = new Race(0, 0, 0, 0, 0, 0, 0, "Halfling", "", "", "", "", "", "", "", "", new String[]{""}, "");
		packRaces[DefaultRace.Human.ordinal()] = new Race(0, 0, 0, 0, 0, 0, 0, "Human", "", "", "", "", "", "", "", "", new String[]{""}, "");
		packRaces[DefaultRace.Dragonborn.ordinal()] = new Race(0, 0, 0, 0, 0, 0, 0, "Dragonborn", "", "", "", "", "", "", "", "", new String[]{""}, "");
		packRaces[DefaultRace.Gnome.ordinal()] = new Race(0, 0, 0, 0, 0, 0, 0, "Gnome", "", "", "", "", "", "", "", "", new String[]{""}, "");
		packRaces[DefaultRace.Half_Elf.ordinal()] = new Race(0, 0, 0, 0, 0, 0, 0, "Half_Elf", "", "", "", "", "", "", "", "", new String[]{""}, "");
		packRaces[DefaultRace.Half_Orc.ordinal()] = new Race(0, 0, 0, 0, 0, 0, 0, "Half_Orc", "", "", "", "", "", "", "", "", new String[]{""}, "");
		packRaces[DefaultRace.Tiefling.ordinal()] = new Race(0, 0, 0, 0, 0, 0, 0, "Tiefling", "", "", "", "", "", "", "", "", new String[]{""}, "");
	}
	
	public static Race fromString(String json)
	{
		Race r = new Gson().fromJson(json, Race.class);
		if (!r.isLoaded())
			customRaces.add(r);
		return r;
	}
	
	public static Race getRace(DefaultRace raceName)
	{
		return packRaces[raceName.ordinal()];
	}
	
	public String toString()
	{
		return new Gson().toJson(this);
	}
	
	@SuppressWarnings("RedundantIfStatement")
	public boolean equals(Race other)
	{
		if (!name.equals(other.name))
			return false;
		if (str != other.str || dex != other.dex || con != other.con || intel != other.intel || wis != other.wis || cha != other.cha || assignable_points != other.assignable_points)
			return false;
		if (!description.equals(other.description))
			return false;
		if (!description_age.equals(other.description_age))
			return false;
		if (!description_physical.equals(other.description_physical))
			return false;
		if (!description_society.equals(other.description_society))
			return false;
		if (!trait_age.equals(other.trait_age))
			return false;
		if (!trait_alignment.equals(other.trait_alignment))
			return false;
		if (!trait_size.equals(other.trait_size))
			return false;
		if (!trait_speed.equals(other.trait_speed))
			return false;
		if (traits.size() != other.traits.size())
			return false;
		for (int q = 0; q < traits.size(); ++q)
			if (!traits.get(q).equals(other.traits.get(q)))
				return false;
		if (!languages.equals(other.languages))
			return false;
		//
		return true;
	}
	
	public boolean isLoaded()
	{
		if (isDefault())
			return true;
		for (Race r : customRaces)
			if (equals(r))
				return true;
		return false;
	}
	
	public boolean isDefault()
	{
		for (Race r : packRaces)
			if (equals(r))
				return true;
		return false;
	}
	
	public enum DefaultRace
	{
		Dwarf__Hill, Dwarf__Mountain, Elf, Halfling, Human, Dragonborn, Gnome, Half_Elf, Half_Orc, Tiefling, Num_Pack_Races
	}
}
