import java.util.Scanner;

class Contact {
    String name;
    String email;
    String phoneNumber;

    Contact(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Email: " + email + ", Phone Number: " + phoneNumber;
    }
}

class ContactBook {
    private Node head;

    private static class Node {
        Contact contact;
        Node next;

        Node(Contact contact) {
            this.contact = contact;
        }
    }

    ContactBook() {
        this.head = null;
    }

    void addContact(String name, String email, String phoneNumber) {
        Contact newContact = new Contact(name.toLowerCase(), email.toLowerCase(), phoneNumber);
        Node newNode = new Node(newContact);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    void printContacts() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.contact);
            temp = temp.next;
        }
    }

    void deleteContact(String name) {
        String lowerCaseName = name.toLowerCase();
        Node prev = null;
        Node current = head;
        while (current != null && !current.contact.name.equals(lowerCaseName)) {
            prev = current;
            current = current.next;
        }
        if (current != null) {
            if (prev != null) {
                prev.next = current.next;
            } else {
                head = current.next;
            }
        }
    }

    Contact findContact(String name) {
        String lowerCaseName = name.toLowerCase();
        Node temp = head;
        while (temp != null) {
            if (temp.contact.name.equals(lowerCaseName)) {
                return temp.contact;
            }
            temp = temp.next;
        }
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactBook contactBook = new ContactBook();

        int choice = 0;
        while (choice != 5) {
            printMenu();
            choice = getIntInput(scanner);

            switch (choice) {
                case 1:
                    addContact(scanner, contactBook);
                    break;
                case 2:
                    deleteContact(scanner, contactBook);
                    break;
                case 3:
                    contactBook.printContacts();
                    break;
                case 4:
                    findContact(scanner, contactBook);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
                    break;
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("Action List:");
        System.out.println("1. Add");
        System.out.println("2. Delete");
        System.out.println("3. Print list");
        System.out.println("4. Select");
        System.out.println("5. Quit");
        System.out.print("Enter your choice: ");
    }

    private static int getIntInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void addContact(Scanner scanner, ContactBook contactBook) {
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        String phoneNumber;
        while (true) {
            System.out.print("Enter phone number: ");
            phoneNumber = scanner.nextLine();
            if (phoneNumber.matches("\\d+")) {
                contactBook.addContact(name, email, phoneNumber);
                break; // Exit the loop if phone number is valid
            } else {
                System.out.println("Invalid phone number format. Please enter numbers only.");
            }
        }
    }

    private static void deleteContact(Scanner scanner, ContactBook contactBook) {
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter name to delete: ");
        String deleteName = scanner.nextLine();
        contactBook.deleteContact(deleteName);
    }

    private static void findContact(Scanner scanner, ContactBook contactBook) {
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter name to search: ");
        String searchName = scanner.nextLine();
        Contact foundContact = contactBook.findContact(searchName);
        if (foundContact != null) {
            System.out.println("Contact found: " + foundContact);
        } else {
            System.out.println("Contact not found.");
        }
    }
}
