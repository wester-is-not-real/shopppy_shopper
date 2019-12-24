package lists;

import supermarkets.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ShoppingList {
    public static Queue<String> list;
    public static Date dateCreated;

    public ShoppingList(){
        list = new LinkedList<>();
        dateCreated = new Date();
    }

    public static void add(String item){
        list.add(item);
    }
    public String show(){
        return list.peek();
    }
    public static boolean isExit(String option) {
        if (option.toLowerCase().equals("done")) return true;
        return false;
    }

    public static void addList() throws IOException {
        while (true) {
            Scanner sc = new Scanner(System.in);
            String item;
            if (list.size() == 0) {
                System.out.print("Enter first item: ");
                item = sc.next();
                if (isExit(item)) break;
                add(item);
            }
            System.out.print("Enter next item: ");
            item = sc.next();
            if (isExit(item)) {
                System.out.print("Do you wish to save list? (Y/N): ");
                String choice = sc.next();
                if (choice.toLowerCase().equals("y")){
                    createFile();
                    break;
                }
                break;
            }
            add(item);
        }
    }
    public boolean openFile() throws IOException {
            FileDialog dialog = new FileDialog((Frame) null, "Select File to Open");
            dialog.setMode(FileDialog.LOAD);
            dialog.setVisible(true);
            String file = dialog.getFile();
            if (file != null) {
                File selectedFile = new File("C:\\Users\\Wester\\Documents\\ShoppyShopper\\Shopping_Lists\\" + dialog.getFile());
                System.out.println("Opening...");
                loadFile(selectedFile);
                return true;
            }
            return false;
    }
    public static void loadFile(File file) throws IOException {
        Scanner sc = new Scanner(file);
        sc.nextLine();
        String item = sc.nextLine();
        while(!item.equals("//")) {
            list.add(item);
            item = sc.nextLine();
        }
        addList();
    }
    private static void fileWriter(FileWriter autr) throws IOException {
        int size = list.size();
        for (int i = 0; i < size; i++){
            autr.write(list.poll() + "\n");
        }
    }
    private static void createFile() throws IOException {
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
        String date = format.format(dateCreated);
        File shoppingFile = new File("C:\\Users\\Wester\\Documents\\ShoppyShopper\\Shopping_Lists\\" + date + ".txt");
        System.out.println("Generating File...");
        FileWriter writer = new FileWriter(shoppingFile);
        writer.write(date + "\n");
        fileWriter(writer);
        writer.write("//");
        writer.close();
        shoppingFile.setWritable(true);
        System.out.println("File saved");
    }
    private static String checkDates(String[] dates){
        for (String str: dates){
            Date date1;
            Date date2;
            String[] nig = str.split("__|\\.");
            String temp = nig[0];
            int day = Integer.parseInt(temp.substring(0, 2));
            int month = Integer.parseInt(temp.substring(2, 4)) - 1;
            int year = Integer.parseInt(temp.substring(4, 8)) - 1900;
            date1 = new Date(year, month, day);
            temp = nig[1];
            day = Integer.parseInt(temp.substring(0, 2));
            month = Integer.parseInt(temp.substring(2, 4)) - 1;
            year = Integer.parseInt(temp.substring(4, 8)) - 1900;
            date2 = new Date(year, month, day);
            if (dateCreated.compareTo(date1) > 0 && dateCreated.compareTo(date2) < 0){
                return str;
            }
        }
        return null;
        //if (dateCreated.compareTo())
    }
    private static PriceList getCurrentPrices() throws IOException {
        String[] stre;
        File direct = new File("C:\\Users\\Wester\\Documents\\ShoppyShopper\\Prices");
        stre = direct.list();
        File currentPricesList = new File ("C:\\Users\\Wester\\Documents\\ShoppyShopper\\Prices\\" + checkDates(stre));
        PriceList currentPrices = new PriceList();
        currentPrices.openFile(currentPricesList);
        return currentPrices;
    }
    public static void comparePrices() throws IOException {
        PriceList prices = getCurrentPrices();
        if (list != null) {
            Queue<Double> savings = prices.comparePrices(list);
            System.out.println("Econo: " + savings.poll() + " saved");
            System.out.println("Selectos: " + savings.poll() + " saved");
            System.out.println("Mr. Special: " + savings.poll() + " saved");
            System.out.printf("Pueblo: " + savings.poll() + " saved");
        }
        else System.out.println("New list exists");
    }

//    public static void main (String args[]){
//        Scanner sc = new Scanner(System.in);
//
//    }
}
