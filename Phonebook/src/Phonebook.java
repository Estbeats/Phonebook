import javax.swing.*;
import java.util.Arrays;

public class Phonebook {
    private Contact[] contacts;
    private int size;

    public Phonebook(int capacity) {
        contacts = new Contact[capacity];
        size = 0;
    }

    public void insertContact(String name, String phoneNumber) {
        if (size < contacts.length) {
            contacts[size++] = new Contact(name, phoneNumber);
            JOptionPane.showMessageDialog(null, "Contact added successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Phonebook is full!");
        }
    }

    public Contact searchContact(String name) {
        for (int i = 0; i < size; i++) {
            if (contacts[i].name.equalsIgnoreCase(name)) {
                return contacts[i];
            }
        }
        return null; // Contact not found
    }

    public void displayContacts(JTextArea textArea) {
        textArea.setText(""); // Clear previous text
        for (int i = 0; i < size; i++) {
            textArea.append(contacts[i].name + ": " + contacts[i].phoneNumber + "\n");
        }
    }

    public boolean deleteContact(String name) {
        for (int i = 0; i < size; i++) {
            if (contacts[i].name.equalsIgnoreCase(name)) {
                for (int j = i; j < size - 1; j++) {
                    contacts[j] = contacts[j + 1];
                }
                contacts[--size] = null; // Clear last position
                return true; // Contact deleted
            }
        }
        return false; // Contact not found
    }

    public boolean updateContact(String name, String newPhoneNumber) {
        Contact contact = searchContact(name);
        if (contact != null) {
            contact.phoneNumber = newPhoneNumber;
            return true; // Update successful
        }
        return false; // Contact not found
    }

    public void sortContacts() {
        Arrays.sort(contacts, 0, size, (c1, c2) -> c1.name.compareToIgnoreCase(c2.name));
    }
}