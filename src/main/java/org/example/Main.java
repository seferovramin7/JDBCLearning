package org.example;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:postgresql://localhost:5433/tesJdbc";
        String user = "postgres";
        String password = "12345";
        PersonJDBC pjdbc = new PersonJDBC(url, user, password);

        Person person = new Person();
        person.setId(4);
        person.setName("Rafael");
        person.setIdentity("ZAA21");
        person.setBirthday("10/10/1980");
        pjdbc.addPerson(person);


        ArrayList<Person> array = pjdbc.getAllPersons();

        for (Person i : array) {
            System.out.println(i.getName() + ", your id is " + i.getId() +
                    ", " + i.getBirthday());
        }

        System.out.println(pjdbc.getPerson("Rafael").getName());
        pjdbc.removePerson(person);
    }

}
