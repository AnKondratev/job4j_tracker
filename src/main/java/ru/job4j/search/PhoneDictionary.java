package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        Predicate<Person> nameCont = p -> (p.getName().contains(key));
        Predicate<Person> surnameCont = p -> (p.getSurname().contains(key));
        Predicate<Person> phoneCont = p -> (p.getPhone().contains(key));
        Predicate<Person> adressCont = p -> (p.getAddress().contains(key));
        Predicate<Person> combine = nameCont.or(surnameCont).or(phoneCont).or(adressCont);

        ArrayList<Person> result = new ArrayList<>();
        for (var person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}