import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Item[] store = setupStore();
        ArrayList<Item> cart = createCart(store, args);
        printReceiptInOrder(cart);
        cart = emptyCartReverseOrder(cart);
    }

    //defines the items that exist in the store
    public static Item[] setupStore() {
        Item[] store = new Item[5];
        store[0] = new Item("T-Shirt", 9.99);
        store[1] = new Item("Pants", 19.99);
        store[2] = new Item("Jacket", 59.99);
        store[3] = new Item("Dress", 49.99);
        store[4] = new Item("Hat", 8.99);
        return store;
    }

    //adds items to the user's cart based on args
    public static ArrayList<Item> createCart(Item[] store, String[] args) {
        int n = -1;
        try {
            n = Integer.parseInt(args[0]);
        }
        catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Exception Occurred: " + e);
            System.out.println("Please input an integer.");
            System.exit(0);
        }
        if (n != args.length-1)
        {
            System.out.println("Error: Please input an amount of items equal to the first integer.");
            System.exit(0);
        }

        ArrayList<Item> cart = new ArrayList<Item>();
        //add items from the store to the cart, skipping args that aren't integers
        for (int i=1; i<args.length; i++) {
            try {
                cart.add(store[Integer.parseInt(args[i])]);
            }
            catch (NumberFormatException e) {
                System.out.println("\nException Occurred: " + e);
                System.out.println("Please input an integer for index " + i + ".\nIgnoring index " + i + " and continuing program.");
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("\nException Occurred: " + e);
                System.out.println("Integer in index " + i + "is not within the range of store items.\nIgnoring index " + i + " and continuing program.");
            }
        }
        return cart;
    }

    //print statements to tell the user their receipt, including all items, price subtotal, sales tax, and price total
    public static void printReceiptInOrder(ArrayList<Item> cart) {
        double subtotal = 0;
        System.out.println("\nReceipt \n======================");
        for (int i=0; i<cart.size(); i++) {
            Item current = cart.get(i);
            double currentPrice = current.getItemPrice();
            System.out.println("Item " + (i+1) + ": " + current.getItemName() + ", $" + currentPrice);
            subtotal += currentPrice;
        }
        System.out.println("======================");
        System.out.println("(a) Price Subtotal: $" + subtotal);
        System.out.println("(b) Sales Tax: 5%");
        double total = (double)((int)((subtotal + subtotal * .05)*100))/100; //calculates and rounds price total
        System.out.println("(c) Price Total: $" + total + "\n");
    }

    //print statements to tell the user the items that are being emptied from their cart.
    public static ArrayList<Item> emptyCartReverseOrder(ArrayList<Item> cart) {
        System.out.println("Emptying cart...");
        for (int i=cart.size()-1; i>=0; i--) {
            System.out.println("Item " + (i+1) + ": " + cart.get(i).getItemName());
            cart.remove(i);
        }
        System.out.println("...Cart is now empty.\n");
        return cart;
    }
}