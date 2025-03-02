package invmanagement;

import java.io.*;
import java.util.*;

class Item {
    String name;
    String code;
    int quantity;
    double price;

    Item(String name, String code, int quantity, double price) {
        this.name = name;
        this.code = code;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Code: " + code + ", Quantity: " + quantity + ", Price: " + price;
    }
}

public class InventoryManagement {
    private static Map<String, Item> inventory = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add an item");
            System.out.println("2. Remove an item");
            System.out.println("3. Update item quantity");
            System.out.println("4. Display inventory");
            System.out.println("5. Save inventory");
            System.out.println("6. Load inventory");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addItem(scanner);
                    break;
                case 2:
                    removeItem(scanner);
                    break;
                case 3:
                    updateQuantity(scanner);
                    break;
                case 4:
                    displayInventory();
                    break;
                case 5:
                    saveInventory();
                    break;
                case 6:
                    loadInventory();
                    break;
                case 7:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addItem(Scanner scanner) {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter item code: ");
        String code = scanner.nextLine();
        System.out.print("Enter item quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter item price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        inventory.put(code, new Item(name, code, quantity, price));
        System.out.println("Item added successfully.");
    }

    private static void removeItem(Scanner scanner) {
        System.out.print("Enter the code of the item to remove: ");
        String code = scanner.nextLine();

        if (inventory.remove(code) != null) {
            System.out.println("Item removed successfully.");
        } else {
            System.out.println("Item not found.");
        }
    }

    private static void updateQuantity(Scanner scanner) {
        System.out.print("Enter the code of the item to update: ");
        String code = scanner.nextLine();
        System.out.print("Enter the new quantity: ");
        int newQuantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Item item = inventory.get(code);
        if (item != null) {
            item.quantity = newQuantity;
            System.out.println("Quantity updated successfully.");
        } else {
            System.out.println("Item not found.");
        }
    }

    private static void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("The inventory is empty.");
        } else {
            for (Item item : inventory.values()) {
                System.out.println(item);
            }
        }
    }

    private static void saveInventory() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("inventory.dat"))) {
            oos.writeObject(inventory);
            System.out.println("Inventory saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving inventory.");
        }
    }

    private static void loadInventory() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("inventory.dat"))) {
            inventory = (Map<String, Item>) ois.readObject();
            System.out.println("Inventory loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading inventory.");
        }
    }
}