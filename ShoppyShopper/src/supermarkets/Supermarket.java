package supermarkets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Supermarket {
    public Queue<Product> products;

    public Supermarket(){
        products = new LinkedList<>();
    }

    public void add(String name, double price, double regPrice){
        Product newProduct = new Product(name, price, regPrice);
        products.add(newProduct);
    }

    public static boolean isExit(String choice) {
        if (choice.toLowerCase().equals("done")) {
            System.out.println();
            return true;
        }
        return false;
    }
    public boolean containsString(String str) {
        for (Product t: products){
            if (t.getName().equals(str)) return true;
        }
        return false;
    }

//    public double getPrice(String name){
//        for (String s: pricesStrings) {
//            if (s.equals(name)) {
//                pricesStrings.
//            }
//        }
//        return ;
//    }

    public void inputPrices(){
        String name;
        double price;
        double regPrice;
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.print("Enter item name: ");
            name = sc.next();
            if (isExit(name)) break;
            System.out.print("Enter item price: ");
            while (!sc.hasNextDouble()) {
                System.out.print("Invalid input, try again: ");
                sc.next();
            }
            price = sc.nextDouble();
            System.out.print("Enter item's regular price: ");
            while (!sc.hasNextDouble()) {
                System.out.print("Invalid input, try again: ");
                sc.next();
            }
            regPrice = sc.nextDouble();
            add(name, price, regPrice);
        }
    }

    public void fileWrite(FileWriter writer) throws IOException {
        int size = products.size();
        for (int i = 0; i < size; i++) {
            Product temp = products.poll();
            String price = temp.getPrice() + "";
            String regPrice = temp.getRegPrice() + "";
            price = price.replace('.', ',');
            regPrice = regPrice.replace('.', ',');
            writer.write("\t" + temp.getName() + "   " + price + ".    " + regPrice + ".\n");
        }
        System.out.println();
    }
}
