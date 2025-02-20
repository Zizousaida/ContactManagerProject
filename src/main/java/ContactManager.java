import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactManager {
    private List<Contact> contacts;

    public ContactManager() {
        contacts = new ArrayList<>();
    }

    // Add a contact
    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    // Delete a contact by name and surname
    public void deleteContact(String name, String surname) {
        contacts.removeIf(contact -> contact.getName().equals(name) && contact.getSurname().equals(surname));
    }

    // Modify a contact
    public void modifyContact(String name, String surname, Contact newContact) {
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            if (contact.getName().equals(name) && contact.getSurname().equals(surname)) {
                contacts.set(i, newContact);
                break;
            }
        }
    }

    // Search for a contact by name and surname
    public Contact searchContact(String name, String surname) {
        for (Contact contact : contacts) {
            if (contact.getName().equals(name) && contact.getSurname().equals(surname)) {
                return contact;
            }
        }
        return null;
    }

    // Save contacts to a file using serialization
    public void saveContacts(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(contacts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load contacts from a file using deserialization
    @SuppressWarnings("unchecked")
    public void loadContacts(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            contacts = (List<Contact>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Display all contacts (for console-based applications)
    public void displayContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    // Get all contacts (for GUI)
    public List<Contact> getContacts() {
        return contacts;
    }
}
