package person;

public class Skill
{
	public String name;
	public String descrption;
	public double rate;

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
