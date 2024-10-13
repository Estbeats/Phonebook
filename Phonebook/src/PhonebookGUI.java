import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PhonebookGUI extends JFrame {
    private Phonebook phonebook;
    private JTextArea textArea;
    private JTextField nameField;
    private JTextField phoneField;

    public PhonebookGUI() {
        phonebook = new Phonebook(100);
        setTitle("Phonebook Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Phone Number:"));
        phoneField = new JTextField();
        inputPanel.add(phoneField);

        JButton insertButton = new JButton("Insert");
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String phoneNumber = phoneField.getText();
                phonebook.insertContact(name, phoneNumber);
                phonebook.displayContacts(textArea);
                clearFields();
            }
        });
        inputPanel.add(insertButton);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                Contact contact = phonebook.searchContact(name);
                if (contact != null) {
                    JOptionPane.showMessageDialog(null, "Found: " + contact.name + ": " + contact.phoneNumber);
                } else {
                    JOptionPane.showMessageDialog(null, "Contact not found.");
                }
            }
        });
        inputPanel.add(searchButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                if (phonebook.deleteContact(name)) {
                    JOptionPane.showMessageDialog(null, "Contact deleted.");
                } else {
                    JOptionPane.showMessageDialog(null, "Contact not found.");
                }
                phonebook.displayContacts(textArea);
                clearFields();
            }
        });
        inputPanel.add(deleteButton);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String newPhoneNumber = phoneField.getText();
                if (phonebook.updateContact(name, newPhoneNumber)) {
                    JOptionPane.showMessageDialog(null, "Contact updated.");
                } else {
                    JOptionPane.showMessageDialog(null, "Contact not found.");
                }
                phonebook.displayContacts(textArea);
                clearFields();
            }
        });
        inputPanel.add(updateButton);

        JButton sortButton = new JButton("Sort");
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                phonebook.sortContacts();
                phonebook.displayContacts(textArea);
                JOptionPane.showMessageDialog(null, "Contacts sorted.");
            }
        });
        inputPanel.add(sortButton);

        add(inputPanel, BorderLayout.SOUTH);
    }

    private void clearFields() {
        nameField.setText("");
        phoneField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PhonebookGUI gui = new PhonebookGUI();
            gui.setVisible(true);
        });
    }
}