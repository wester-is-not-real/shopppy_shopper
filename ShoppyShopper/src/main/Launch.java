package main;

import lists.PriceList;
import lists.ShoppingList;

import java.io.IOException;
import java.util.Scanner;

public class Launch {

    public static void startApp() throws IOException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Shopping List or Price List?: ");
            String choice = sc.next();

            if (choice.toLowerCase().equals("done")) break;

            else if (choice.toLowerCase().equals("price")) {
                PriceList priceList = new PriceList();
                boolean cont = true;
                while (cont) {
                    System.out.println("1:New File  2:Open File  3.Delete File  4:Exit");
                    System.out.print(": ");
                    int option = sc.nextInt();
                    switch (option) {
                        case 1:
                            priceList.addList(true);
                            break;
                        case 2:
                            priceList = new PriceList();
                            priceList.openFile();
                            //System.out.println("Option not available yet");
                            break;
                        case 3:
                            break;
                        case 4:
                            cont = false;
                            break;
                    }
                }
            }
            else if (choice.toLowerCase().equals("shopping")) {
                ShoppingList shopList = new ShoppingList();
                boolean cont = true;
                while (cont) {
                    System.out.println("1.New List   2.Open List   3.Check Prices   4.Exit");
                    System.out.print(":");
                    int option = sc.nextInt();
                    switch (option) {
                        case 1:
                            shopList.addList();
                            break;
                        case 2:
                            shopList = new ShoppingList();
                            shopList.openFile();
                            break;
                        case 3:
                            shopList.comparePrices();
                            break;
                        case 4:
                            cont = false;
                            break;
                    }
                }
            }
            else {
                System.out.println("Sorry I didn't get that...: ");
            }


        }
    }

    public static void main (String args[]) throws IOException {
        startApp();
        //PriceList list =  new PriceList();
        //list.openFile();
    }
}
