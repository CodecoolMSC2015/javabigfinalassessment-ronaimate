package person;

import java.io.Serializable;

public class Skill implements Serializable
{
	private static final long serialVersionUID = 5120319893241484165L;
	private String name;
	private String descrption;
	private double rate;

	public Skill(String name, String description)
	{
		this.name = name;
		this.descrption = description;
	}

	public String getName()
	{
		return name;
	}

	public String getDescrption()
	{
		return descrption;
	}

	public double getRate()
	{
		return rate;
	}
}
