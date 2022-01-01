package edu.team04.entity;

import edu.team04.control.Utils;

public enum Skills
{
	ROCK_THROW,
	PAPER_CUT,
	SCISSORS_POKE,
	SHOOT_THE_MOON,
	REVERSAL_OF_FORTUNE;
	
	@Override
	public String toString()
	{		
		return Utils.convertEnumString(this.name());
	}

}
