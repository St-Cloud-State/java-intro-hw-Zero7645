package Hw1Q3;
import java.io.*;
import java.util.LinkedList;


class Person {
    private String firstName;
    private String lastName;
    private String id;

    // Constructor
    public Person(String firstName, String lastName, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    // Accessor methods (getters)
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getId() {
        return id;
    }

    // toString method to display object
    @Override
    public String toString() {
        return firstName + " " + lastName + " (ID: " + id + ")";
    }
}


class PersonList {
    private LinkedList<Person> list = new LinkedList<>();

    // Method to store persons from a data file into the linked list
    public void store(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length == 3) {
                list.add(new Person(data[0], data[1], data[2]));
            } else {
                System.err.println("Invalid line format: " + line);
            }
        }
        reader.close();
    }

    // Method to display persons from the linked list
    public void display() {
        for (Person person : list) {
            System.out.println(person);
        }
    }

    // Method to find a person by ID
    public int find(String id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1; // Return -1 if not found
    }

    // Getter for the list
    public LinkedList<Person> getList() {
        return list;
    }
}

public class MyMain2 {
    public static void main(String[] args) {
        PersonList personList = new PersonList();

        try {
            // Store persons from the data file into the linked list
            personList.store("Hw1Q3/person_data.txt");
        } catch (IOException e) {
            System.err.println("Error reading data file: " + e.getMessage());
            return;
        }

        // Display the persons stored in the linked list
        personList.display();

        // Find persons by ID and display the results
        System.out.println("\nFinding person with ID 2: Index " + personList.find("2"));
        System.out.println("Finding person with ID 5: Index " + personList.find("5"));
        System.out.println("Finding person with ID 11: Index " + personList.find("11")); // Should return -1
    }
}
