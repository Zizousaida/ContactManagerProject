import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ContactManagerGUI extends JFrame {
    private ContactManager contactManager;
    private JTextArea contactsArea;
    private JTextArea searchResultsArea;

    public ContactManagerGUI() {
        contactManager = new ContactManager();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Contact Manager");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Create a tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Tab 1: Contacts
        JPanel contactsPanel = createContactsPanel();
        tabbedPane.addTab("Contacts", contactsPanel);

        // Tab 2: Add Contact
        JPanel addContactPanel = createAddContactPanel();
        tabbedPane.addTab("Add Contact", addContactPanel);

        // Tab 3: Search Contact
        JPanel searchContactPanel = createSearchContactPanel();
        tabbedPane.addTab("Search Contact", searchContactPanel);

        // Add the tabbed pane to the frame
        add(tabbedPane);
    }

    // Create the Contacts tab
    private JPanel createContactsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        contactsArea = new JTextArea();
        contactsArea.setEditable(false);
        contactsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(contactsArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Load and display contacts
        displayContacts();

        return panel;
    }

    // Create the Add Contact tab
    private JPanel createAddContactPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Create input fields
        JTextField nameField = new JTextField(20);
        JTextField surnameField = new JTextField(20);
        JTextField addressField = new JTextField(20);
        JTextField phoneField = new JTextField(20);
        JTextField emailField = new JTextField(20);

        // Add labels and fields to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Surname:"), gbc);
        gbc.gridx = 1;
        panel.add(surnameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Address:"), gbc);
        gbc.gridx = 1;
        panel.add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1;
        panel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        // Add button
        JButton addButton = createStyledButton("Add Contact", new Color(76, 175, 80)); // Green
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(addButton, gbc);

        // Add button listener
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String surname = surnameField.getText();
                String address = addressField.getText();
                String phone = phoneField.getText();
                String email = emailField.getText();

                if (name.isEmpty() || surname.isEmpty()) {
                    showError("Name and Surname are required!");
                } else {
                    contactManager.addContact(new Contact(name, surname, address, phone, email));
                    displayContacts(); // Refresh the contacts list
                    clearFields(nameField, surnameField, addressField, phoneField, emailField);
                }
            }
        });

        return panel;
    }

    // Create the Search Contact tab
    private JPanel createSearchContactPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create search bar
        JPanel searchBarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JTextField searchField = new JTextField(20);
        JButton searchButton = createStyledButton("Search", new Color(33, 150, 243)); // Blue
        searchBarPanel.add(searchField);
        searchBarPanel.add(searchButton);

        // Create search results area
        searchResultsArea = new JTextArea();
        searchResultsArea.setEditable(false);
        searchResultsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(searchResultsArea);

        // Add components to the panel
        panel.add(searchBarPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add button listener
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = searchField.getText();
                if (name.isEmpty()) {
                    showError("Please enter a name to search!");
                } else {
                    Contact contact = contactManager.searchContact(name, "");
                    searchResultsArea.setText(""); // Clear previous results
                    if (contact != null) {
                        searchResultsArea.append("Contact found: " + contact + "\n");
                    } else {
                        searchResultsArea.append("Contact not found.\n");
                    }
                }
            }
        });

        return panel;
    }

    // Helper method to display all contacts
    private void displayContacts() {
        contactsArea.setText(""); // Clear the display area
        List<Contact> contacts = contactManager.getContacts();
        if (contacts.isEmpty()) {
            contactsArea.append("No contacts found.\n");
        } else {
            for (Contact contact : contacts) {
                contactsArea.append(contact.toString() + "\n");
            }
        }
    }

    // Helper method to create styled buttons
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }

    // Helper method to show error messages
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Helper method to clear input fields
    private void clearFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ContactManagerGUI().setVisible(true);
            }
        });
    }
}
