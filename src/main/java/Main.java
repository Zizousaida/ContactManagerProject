import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ContactManager manager = new ContactManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n1. Add Contact\n2. Delete Contact\n3. Modify Contact\n4. Search Contact\n5. Display Contacts\n6. Save Contacts\n7. Load Contacts\n8. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter surname: ");
                    String surname = scanner.nextLine();
                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    manager.addContact(new Contact(name, surname, address, phone, email));
                    break;
                case 2:
                    System.out.print("Enter name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter surname: ");
                    surname = scanner.nextLine();
                    manager.deleteContact(name, surname);
                    break;
                case 3:
                    System.out.print("Enter name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter surname: ");
                    surname = scanner.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new surname: ");
                    String newSurname = scanner.nextLine();
                    System.out.print("Enter new address: ");
                    String newAddress = scanner.nextLine();
                    System.out.print("Enter new phone: ");
                    String newPhone = scanner.nextLine();
                    System.out.print("Enter new email: ");
                    String newEmail = scanner.nextLine();
                    manager.modifyContact(name, surname, new Contact(newName, newSurname, newAddress, newPhone, newEmail));
                    break;
                case 4:
                    System.out.print("Enter name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter surname: ");
                    surname = scanner.nextLine();
                    Contact contact = manager.searchContact(name, surname);
                    if (contact != null) {
                        System.out.println("Contact found: " + contact);
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;
                case 5:
                    manager.displayContacts();
                    break;
                case 6:
                    System.out.print("Enter filename to save: ");
                    String saveFilename = scanner.nextLine();
                    manager.saveContacts(saveFilename);
                    break;
                case 7:
                    System.out.print("Enter filename to load: ");
                    String loadFilename = scanner.nextLine();
                    manager.loadContacts(loadFilename);
                    break;
                case 8:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}
