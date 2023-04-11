package org.example;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PersonDAO {

	public void addPerson(Person person) throws SQLException;
	public void removePerson(Person person) throws SQLException;
	public Person getPerson(String name) throws SQLException;
	public ArrayList<Person> getAllPersons() throws SQLException;
}
