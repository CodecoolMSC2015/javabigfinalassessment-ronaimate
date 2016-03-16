package dataParser;

import java.io.BufferedReader;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import enums.SearchType;
import person.Person;
import person.Skill;

public class CSVDataReader extends DataReader
{
	private String csvFilePath;
	List<Person> persons;
	Set<Person> personSet = new HashSet<>();

	public CSVDataReader(String filePath)
	{
		csvFilePath = filePath;
	}

	@Override
	public Set<Person> getPersons()
	{
		File f = new File(csvFilePath);
		try
		{
			BufferedReader br = new BufferedReader(new java.io.FileReader(f));
			String line;
			String[] criteria = this.searchCriteria.split(",");
			while ((line = br.readLine()) != null)
			{
				String[] parsedLine = line.split(",");
				if (searchType == SearchType.OPTIONAL)
				{
					for (String string : criteria)
					{
						if (parsedLine[2].equals(string))
						{
							Person person = new Person(parsedLine[0], parsedLine[1]);
							if (personSet.contains(person))
							{
								personSet.remove(person);
							}
							Skill skill = new Skill(parsedLine[2], parsedLine[3]);
							person.addSkill(skill);
							personSet.add(person);
						}
					}
				} else if (searchType == SearchType.MANDATORY)
				{
					int i = 0;
					int lenCriteria = criteria.length;
				}
			}
			br.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return personSet;
	}

	@Override
	public void setSearchCriteria(String searchCriteria)
	{
		this.searchCriteria = searchCriteria;
	}

	@Override
	public void setSearchType(SearchType searchType)
	{
		this.searchType = searchType;
	}
}
