package DND;

import java.util.Arrays;
import java.util.Random;

public class CharGen
{
	private static CharGen wip;
	public GenStage stage;
	public Character c;
	public StatStyle statStyle = StatStyle.NULL;
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
		int[] rolls = new int[4];
		for (int q = 0; q < 4; ++q)
			rolls[q] = die(6);
		Arrays.sort(rolls);
		for (int q = 0; q < 4; ++q)
		{
			System.out.println(rolls[q]);
		}
		return (byte) (rolls[0] + rolls[1] + rolls[2]);
	}
	
	private void initStats()
	{
		switch (statStyle)
		{
			case ROLL_RANDOM:
				c.str = statRoll();
				break;
			case ROLL_ASSIGN:
			default:
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
		}
		return true;
	}
	
	public enum GenStage
	{
		START, STATS
	}
	
	public enum StatStyle
	{
		NULL, ARRAY, ROLL_ASSIGN, ROLL_RANDOM, POINT_BUY
	}
}
