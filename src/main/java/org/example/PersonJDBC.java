package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PersonJDBC implements PersonDAO {

    private Connection connection;

    public PersonJDBC(String url, String user, String password) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        this.connection = DriverManager.getConnection(url, user, password);
    }

    public void addPerson(Person person) throws SQLException {
        //query of postgresql
        String sql = "insert into person(id,name, identity, birthday)"
                + "values (?,?,?,?)";

        PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, person.getId());
        ps.setString(2, person.getName());
        ps.setString(3, person.getIdentity());
        ps.setString(4, person.getBirthday());

        ps.executeUpdate();

        ResultSet generatedKeys = ps.getGeneratedKeys();
        if (generatedKeys.next()) {
            person.setId(generatedKeys.getInt(1));
        }
    }

    public void removePerson(Person person) throws SQLException {
        String sql = "delete from person where id = ?";
        PreparedStatement ps = this.connection.prepareStatement(sql);
        ps.setInt(1, person.getId());
        ps.executeUpdate();


    }

    public Person getPerson(String name) throws SQLException {
        ArrayList<Person> array = getAllPersons();
        for (Person person : array) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }

    public ArrayList<Person> getAllPersons() throws SQLException {
        ArrayList<Person> array = new ArrayList<Person>();

        ResultSet result = this.connection.prepareStatement("select * from person").executeQuery();
        while (result.next()) {
            Person person = new Person();
            person.setName(result.getString("name"));
            person.setIdentity(result.getString("identity"));
            person.setBirthday(result.getString("birthday"));
            array.add(person);
        }
        result.close();
        return array;
    }
}
