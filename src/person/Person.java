package person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable
{
	private static final long serialVersionUID = -31797628626104588L;
	private String name;
	private String email;
	List<Skill> skillset = new ArrayList<>();

	public Person(String name, String email)
	{
		this.name = name;
		this.email = email;
	}

	public Person()
	{

	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public List<Skill> getSkillset()
	{
		return skillset;
	}

	public void setSkillset(List<Skill> skillset)
	{
		this.skillset = skillset;
	}

	public void addSkill(Skill skill)
	{
		skillset.add(skill);
	}

	@Override
	public boolean equals(Object obj)
	{
		Person p = (Person) obj;
		return name.equals(p.getName());
	}

	@Override
	public int hashCode()
	{
		return -1;
	}

	@Override
	public String toString()
	{
		return "Name: " + name + " " + "Email: " + email;
	}
}
