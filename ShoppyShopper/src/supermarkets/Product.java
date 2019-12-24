package supermarkets;

public class Product {
    public final String name;
    public final double price;
    public final double regPrice;
    public final double netPrice;

    public Product(String name, double price, double regPrice){
        this.name = name;
        this.price = price;
        this.regPrice = regPrice;
        this.netPrice = regPrice - price;
    }

    public String getName(){
        return this.name;
    }
    public double getPrice(){
        return this.price;
    }
    public double getRegPrice(){ return this.regPrice;}
    public double getNetPrice(){ return this.netPrice;}
}
