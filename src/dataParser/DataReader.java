package dataParser;

import java.util.Set;

import enums.SearchType;
import person.Person;

public abstract class DataReader
{
	protected String searchCriteria;
	protected SearchType searchType;

	public abstract Set<Person> getPersons();

	public abstract void setSearchCriteria(String searchCriteria);

	public abstract void setSearchType(SearchType searchType);

}
