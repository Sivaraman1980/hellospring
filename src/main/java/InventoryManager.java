import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * The InventoryManager class manages a list of InventoryItem objects.
 * It provides functionality to add, remove, view, and find items.
 */
public class InventoryManager {

    // A private list to store the inventory items, demonstrating encapsulation
    private final List<InventoryItem> inventoryItems = new ArrayList<>();
    private int nextItemId = 1;

    /**
     * Inner class representing a single item in the inventory.
     * Demonstrates an inner class and data encapsulation.
     */
    public static class InventoryItem {
        private final int id;
        private String name;
        private int quantity;

        public InventoryItem(int id, String name, int quantity) {
            this.id = id;
            this.name = name;
            this.quantity = quantity;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            if (quantity >= 0) {
                this.quantity = quantity;
            } else {
                System.out.println("Quantity cannot be negative.");
            }
        }

        @Override
        public String toString() {
            return "Item ID: " + id + ", Name: " + name + ", Quantity: " + quantity;
        }
    }

    /**
     * Adds a new item to the inventory.
     *
     * @param name     The name of the item.
     * @param quantity The initial quantity.
     */
    public void addItem(String name, int quantity) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Item name cannot be empty.");
            return;
        }
        if (quantity < 0) {
            System.out.println("Quantity cannot be negative.");
            return;
        }
        InventoryItem newItem = new InventoryItem(nextItemId++, name, quantity);
        inventoryItems.add(newItem);
        System.out.println("Added: " + newItem);
    }

    /**
     * Removes an item by its ID.
     *
     * @param id The ID of the item to remove.
     */
    public void removeItem(int id) {
        boolean removed = inventoryItems.removeIf(item -> item.getId() == id);
        if (removed) {
            System.out.println("Item with ID " + id + " removed.");
        } else {
            System.out.println("Item with ID " + id + " not found.");
        }
    }

    /**
     * Finds an item by its ID and returns an Optional.
     *
     * @param id The ID of the item to find.
     * @return An Optional containing the item if found, otherwise empty.
     */
    public Optional<InventoryItem> findItemById(int id) {
        return inventoryItems.stream()
                .filter(item -> item.getId() == id)
                .findFirst();
    }

    /**
     * Prints all items in the inventory.
     */
    public void viewAllItems() {
        if (inventoryItems.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("--- Current Inventory ---");
            inventoryItems.forEach(System.out::println); // Uses method reference
            System.out.println("-------------------------");
        }
    }

    /**
     * Main method to run a simple command-line interface for the InventoryManager.
     */
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add item");
            System.out.println("2. Remove item by ID");
            System.out.println("3. View all items");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter quantity: ");
                        int quantity = Integer.parseInt(scanner.nextLine());
                        manager.addItem(name, quantity);
                        break;
                    case 2:
                        System.out.print("Enter ID to remove: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        manager.removeItem(id);
                        break;
                    case 3:
                        manager.viewAllItems();
                        break;
                    case 4:
                        running = false;
                        System.out.println("Exiting Inventory Manager. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) { // Demonstrates exception handling
                System.out.println("Invalid input. Please enter a number for the choice and quantity/ID.");
            }
        }
        scanner.close();
    }
}
