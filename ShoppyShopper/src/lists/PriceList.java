package lists;

import supermarkets.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PriceList extends Component {
    public static String availableDates;
    public static String startDate;
    public static String endDate;
    public static Econo econoPrice;
    private static Selectos selectoPrice;
    private static MrSpecial specialPrices;
    private static Pueblo puebloPrices;

    public PriceList(){
        econoPrice = new Econo();
        selectoPrice = new Selectos();
        specialPrices = new MrSpecial();
        puebloPrices = new Pueblo();
    }

    private static boolean isExit(String option) {
        if (option.toLowerCase().equals("done")) return true;
        return false;
    }

    private static void createFile(Supermarket econo, Supermarket selectos, Supermarket mrspecial, Supermarket pueblo) throws IOException {

        File priceFile = new File("C:\\Users\\Wester\\Documents\\ShoppyShopper\\Prices\\" + availableDates + ".txt");
        System.out.println("Generating File...");
        FileWriter writer = new FileWriter(priceFile);
        writer.write(availableDates + "\n");

        //Writes Econo Prices
        writer.write("Econo:\n");
        econo.fileWrite(writer);

        //Writes Selectos Prices
        writer.write("Selectos:\n");
        selectos.fileWrite(writer);

        //Writes MrSpecial Prices
        writer.write("MrSpecial:\n");
        mrspecial.fileWrite(writer);

        //Writes Pueblo Prices
        writer.write("Pueblo:\n");
        pueblo.fileWrite(writer);

        //Writes date file was created
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        writer.write("\nFile created(" + format.format(date) + ")");

        writer.close();
        priceFile.setWritable(true);
        System.out.println("File saved");
    }

    public boolean openFile() throws IOException {
        FileDialog dialog = new FileDialog((Frame) null, "Select File to Open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        String file = dialog.getFile();
        if (file != null) {
            File selectedFile = new File("C:\\Users\\Wester\\Documents\\ShoppyShopper\\Prices\\" + dialog.getFile());
            System.out.println("Opening...");
            loadFile(selectedFile, true);
            return true;
        }
        return false;
    }
    public void openFile(File file) throws IOException {
        loadFile(file, false);
    }

    private boolean isNotStore(String line) {
        if (!line.equals("Selectos:") && !line.equals("MrSpecial:") && !line.equals("Pueblo:") && !line.equals("") && !line.equals("File")){
            return true;
        }
        return false;
    }

    public void loadFile(File file, boolean addItems) throws IOException {
        Scanner sc = new Scanner(file);
        String name;
        double price;
        double normPrice;
        System.out.println("Loading File... \n");
        availableDates = sc.nextLine();
        String check = sc.nextLine();
        if(check.equals("Econo:")){
            check = sc.next();
            while (isNotStore(check)){
                name = check;
                String strPrice = sc.next();
                price = Double.parseDouble(formatDouble(strPrice));
                strPrice = sc.next();
                normPrice = Double.parseDouble(formatDouble(strPrice));
                Product newProd = new Product(name, price, normPrice);
                econoPrice.products.add(newProd);
                check = sc.next();
            }
        }
        if (check.equals("Selectos:")){
            check = sc.next();
            while (isNotStore(check)){
                name = check;
                String strPrice = sc.next();
                price = Double.parseDouble(formatDouble(strPrice));
                strPrice = sc.next();
                normPrice = Double.parseDouble(formatDouble(strPrice));
                Product newProd = new Product(name, price, normPrice);
                selectoPrice.products.add(newProd);
                check = sc.next();
            }
        }
        if (check.equals("MrSpecial:")){
            check = sc.next();
            while (isNotStore(check)){
                name = check;
                String strPrice = sc.next();
                price = Double.parseDouble(formatDouble(strPrice));
                strPrice = sc.next();
                normPrice = Double.parseDouble(formatDouble(strPrice));
                Product newProd = new Product(name, price, normPrice);
                specialPrices.products.add(newProd);
                check = sc.next();
            }
        }
        if (check.equals("Pueblo:")){
            check = sc.next();
            while (isNotStore(check)){
                name = check;
                String strPrice = sc.next();
                price = Double.parseDouble(formatDouble(strPrice));
                strPrice = sc.next();
                normPrice = Double.parseDouble(formatDouble(strPrice));
                Product newProd = new Product(name, price, normPrice);
                puebloPrices.products.add(newProd);
                check = sc.next();
            }
        }
        if (check.equals("File")) {
            if (addItems) addList(false);
            return;
        }
    }
    private String formatDouble(String strPrice) {
        strPrice = strPrice.replace('.', ' ');
        strPrice = strPrice.replace(',', '.');
        return strPrice;
    }

    public static void addList(boolean checkDate) throws IOException {
        Scanner sc = new Scanner(System.in);
        if (checkDate) {
            System.out.print("Starting date for available prices? (ddMMyyyy): ");
            startDate = sc.next();
            if (isExit(startDate)) return;
            System.out.print("Ending date for available prices? (ddMMyyyy): ");
            endDate = sc.next();
            if (isExit(endDate)) return;
            checkDateFormat();
            availableDates = startDate + "__" + endDate;
        }

        int store;
        boolean cont = true;
        while (cont) {
            System.out.println("1.Econo  2.Selectos  3.MrSpecial  4.Pueblo  5.Save  6.Exit");
            System.out.print("Enter store digit: ");
            store = sc.nextInt();
            switch (store) {
                case 1:
                    econoPrice.inputPrices();
                    break;
                case 2:
                    selectoPrice.inputPrices();
                    break;
                case 3:
                    specialPrices.inputPrices();
                    break;
                case 4:
                    puebloPrices.inputPrices();
                    break;
                case 5:
                    createFile(econoPrice, selectoPrice, specialPrices, puebloPrices);
                    break;
                case 6:
                    cont = false;
                    break;
                case 0:
                    break;
            }
        }
    }

    public static void checkDateFormat(){
        //Checks if has the correct number of digits
        while (startDate.length() != 8 || endDate.length() != 8) {
            incorrectMessage();
        }

        //Checks if all digits are characters
        for (int i =0; i < startDate.length(); i++) {
            while (!Character.isDigit(startDate.charAt(i)) || !Character.isDigit(endDate.charAt(i))) incorrectMessage();
        }
    }

    private static void incorrectMessage(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Incorrect format, try again(ddMMyyyy): ");
        startDate = sc.next();
        System.out.print("Ending date for available prices? (ddMMyyyy): ");
        endDate = sc.next();
    }

    public static Queue<Double> comparePrices(Queue<String> shoppingList){
        double ecototalSavings = 0.0;
        double seltotalSavings = 0.0;
        double spectotalSavings = 0.0;
        double puebtotalSavings = 0.0;
        Queue<Double> savings = new LinkedList<>();
        for (String str: shoppingList){
            for (Product nig: econoPrice.products) {
                if (nig.getName().equals(str)) ecototalSavings = ecototalSavings + nig.getNetPrice();
            }
            for (Product nig: selectoPrice.products) {
                if (nig.getName().equals(str)) seltotalSavings = seltotalSavings + nig.getNetPrice();
            }
            for (Product nig: specialPrices.products) {
                if (nig.getName().equals(str)) spectotalSavings = spectotalSavings + nig.getNetPrice();
            }
            for (Product nig: puebloPrices.products) {
                if (nig.getName().equals(str)) puebtotalSavings = puebtotalSavings + nig.getNetPrice();
            }
        }
        savings.add(ecototalSavings);
        savings.add(seltotalSavings);
        savings.add(spectotalSavings);
        savings.add(puebtotalSavings);
        return savings;
    }
//    public static void main (String args[]) throws IOException {
//
//    }
}
