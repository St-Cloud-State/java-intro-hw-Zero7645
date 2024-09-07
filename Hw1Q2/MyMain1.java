package Hw1Q2;
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


public class MyMain1 {

    // Method to store persons in the linked list from a file
    public static void store(String filename, LinkedList<Person> list) throws IOException {
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
    public static void display(PrintStream out, LinkedList<Person> list) {
        for (Person person : list) {
            out.println(person);
        }
    }

    // Method to find a person by ID
    public static int find(String id, LinkedList<Person> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1; // Return -1 if not found
    }

    public static void main(String[] args) {
        LinkedList<Person> list = new LinkedList<>();

        try {
            // Read data from the file and store it in the linked list
            store("Hw1Q2/example.txt", list);
        } catch (IOException e) {
            System.err.println("Error reading data file: " + e.getMessage());
            return;
        }

        // Display the data
        display(System.out, list);

        // Find persons by ID
        System.out.println("\nFinding person with ID 2: Index " + find("2", list));
        System.out.println("Finding person with ID 5: Index " + find("5", list));
        System.out.println("Finding person with ID 11: Index " + find("11", list)); // Should return -1
    }
}
