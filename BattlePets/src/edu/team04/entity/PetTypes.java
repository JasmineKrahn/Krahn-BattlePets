package edu.team04.entity;

import edu.team04.control.Utils;

public enum PetTypes
{
	POWER,
	SPEED,
	INTELLIGENCE;
	
	@Override
	public String toString()
	{		
		return Utils.convertEnumString(this.name());
	}
}
