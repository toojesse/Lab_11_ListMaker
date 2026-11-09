import java.util.ArrayList;
import java.util.Scanner;

public class ListMaker {

    private static ArrayList<String> myArrList = new ArrayList<>();
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        boolean quit = false;
        String menuChoice;

        do {
            displayList();
            // Get menu choice using SafeInput getRegExString with pattern [AaDdIiPpQq]
            menuChoice = SafeInput.getRegExString(in, "Choose an option [A - Add, D - Delete, I - Insert, P - Print, Q - Quit]: ", "[AaDdIiPpQq]");

            switch (menuChoice.toUpperCase()) {
                case "A":
                    addItem();
                    break;
                case "D":
                    deleteItem();
                    break;
                case "I":
                    insertItem();
                    break;
                case "P":
                    printList();
                    break;
                case "Q":
                    quit = confirmQuit();
                    break;
            }
        } while (!quit);

        System.out.println("Thank you for using ListMaker!");
    }

    // --- Menu Helper Methods / Functions ---

    private static void displayList() {
        System.out.println("\n--------------------------------");
        if (myArrList.isEmpty()) {
            System.out.println("--- Your list is currently empty ---");
        } else {
            System.out.println("Current List:");
            for (int i = 0; i < myArrList.size(); i++) {
                System.out.println((i + 1) + ": " + myArrList.get(i));
            }
        }
        System.out.println("--------------------------------");
    }

    private static void addItem() {
        String item = SafeInput.getNonZeroLenString(in, "Enter the string item to add to the end of the list");
        myArrList.add(item);
        System.out.println("Item added successfully.");
    }

    private static void deleteItem() {
        if (myArrList.isEmpty()) {
            System.out.println("The list is empty. There is nothing to delete.");
            return;
        }
        // Use getRangedInt to pick the item number to delete (1 to list size)
        int itemNum = SafeInput.getRangedInt(in, "Enter the item number to delete", 1, myArrList.size());
        int index = itemNum - 1; // Convert to 0-based index
        String removed = myArrList.remove(index);
        System.out.println("Removed item: \"" + removed + "\"");
    }

    private static void insertItem() {
        String item = SafeInput.getNonZeroLenString(in, "Enter the string item to insert");

        int targetNum;
        if (myArrList.isEmpty()) {
            targetNum = 1; // If empty, inserting at position 1 is valid (becomes index 0)
        } else {
            // Allow user to insert anywhere from index 1 up to size + 1
            targetNum = SafeInput.getRangedInt(in, "Enter the position number where you want to insert the item", 1, myArrList.size() + 1);
        }

        int index = targetNum - 1; // Convert to 0-based index
        myArrList.add(index, item);
        System.out.println("Item inserted successfully at position " + targetNum + ".");
    }

    private static void printList() {
        displayList();
        System.out.println("[Press Enter / Any key to continue...]");
        // Optional pause so user can view printed list before menu loops
    }

    private static boolean confirmQuit() {
        return SafeInput.getYNConfirm(in, "Are you sure you want to quit?");
    }
}
