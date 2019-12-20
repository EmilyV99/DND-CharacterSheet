package DND;

import GUI.Main_Gui;

import java.util.Arrays;
import java.util.Random;

public class CharGen
{
	private static CharGen wip;
	public GenStage stage;
	public Character c;
	public StatStyle statStyle = StatStyle.NULL;
	public byte[] statsarr = new byte[6];
	public int points;
	private Random rand;
	
	private CharGen()
	{
		wip = this;
		c = new Character();
		stage = GenStage.START;
		rand = new Random();
	}
	
	public static CharGen init()
	{
		if (wip == null)
			new CharGen();
		return wip;
	}
	
	private static void delete()
	{
		wip = null;
	}
	
	private int dice(int num, int faces)
	{
		int sum = 0;
		for (int q = 0; q < num; ++q)
		{
			sum += die(faces);
		}
		return sum;
	}
	
	private int die(int faces)
	{
		return rand.nextInt(faces) + 1;
	}
	
	private byte statRoll()
	{
		byte[] rolls = new byte[4];
		for (int q = 0; q < 4; ++q)
			rolls[q] = (byte) die(6);
		Arrays.sort(rolls);
		return (byte) (rolls[1] + rolls[2] + rolls[3]);
	}
	
	public void initStats()
	{
		switch (statStyle)
		{
			case ROLL_RANDOM:
				points = 0;
				c.str = statRoll();
				c.dex = statRoll();
				c.con = statRoll();
				c.intel = statRoll();
				c.wis = statRoll();
				c.cha = statRoll();
				break;
			case ROLL_ASSIGN:
				c.str = 0;
				c.dex = 0;
				c.con = 0;
				c.intel = 0;
				c.wis = 0;
				c.cha = 0;
				points = 0;
				byte[] foo = new byte[6];
				for (int q = 0; q < 6; ++q)
					foo[q] = statRoll();
				Arrays.sort(foo);
				for (int q = 0; q < 6; ++q)
					statsarr[q] = foo[5 - q];
				break;
			case ARRAY:
				c.str = 0;
				c.dex = 0;
				c.con = 0;
				c.intel = 0;
				c.wis = 0;
				c.cha = 0;
				points = 0;
				statsarr[0] = 15;
				statsarr[1] = 14;
				statsarr[2] = 13;
				statsarr[3] = 12;
				statsarr[4] = 10;
				statsarr[5] = 8;
				break;
			case POINT_BUY:
				points = 27;
				c.str = 8;
				c.dex = 8;
				c.con = 8;
				c.intel = 8;
				c.wis = 8;
				c.cha = 8;
				break;
			case MANUAL:
				c.str = 0;
				c.dex = 0;
				c.con = 0;
				c.intel = 0;
				c.wis = 0;
				c.cha = 0;
				points = 0;
				break;
		}
	}
	
	public boolean confirm()
	{
		switch (stage)
		{
			case START:
				if (statStyle == StatStyle.NULL)
					return false;
				stage = GenStage.STATS;
				initStats();
				break;
			case STATS:
				if (!(c.str > 0 && c.dex > 0 && c.con > 0 && c.intel > 0 && c.wis > 0 && c.cha > 0 && points == 0))
					return false;
				stage = GenStage.SETUP;
				break;
			case SETUP:
				if (c.age <= 0 || c.name.equals(""))
					return false;
				break;
		}
		return true;
	}
	
	public boolean back()
	{
		switch (stage)
		{
			case START:
				break;
			case STATS:
				stage = GenStage.START;
				break;
			case SETUP:
				stage = GenStage.STATS;
				break;
		}
		return true;
	}
	
	public void end()
	{
		Character.loaded.add(c);
		delete();
		Main_Gui.gen = null;
		Main_Gui.main_controller.refreshChars();
		Main_Gui.setScene(Main_Gui.menu);
	}
	
	public enum GenStage
	{
		START, STATS, SETUP
	}
	
	public enum StatStyle
	{
		NULL, ARRAY, ROLL_ASSIGN, ROLL_RANDOM, POINT_BUY, MANUAL
	}
	
	public static String getStatStyleString(StatStyle style)
	{
		switch(style)
		{
			case ARRAY:
				return "Array";
			case ROLL_ASSIGN:
				return "Roll (Assign)";
			case ROLL_RANDOM:
				return "Roll (Random)";
			case POINT_BUY:
				return "Point Buy";
			case MANUAL:
				return "Manual";
			case NULL:
			default:
				return null;
		}
	}
}
