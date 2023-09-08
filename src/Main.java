// VendingMachine
import Domen.Bottle;
import Domen.Product;
import Domen.HotDrink;

import java.util.ArrayList;
import java.util.List;

import Services.CoinDispenser;
import Services.Holder;
import Services.VendingMachine;


public class Main {
    public static void main(String[] args) throws Exception {
        List<Product> assort = new ArrayList<Product>();

        Product item1 = new Product("Lays", 100, 10);
        Product item2 = new Product("cola", 50, 10);
        Product item3 = new Bottle("Mineral water", 101, 10, (float)1.5);
        Product item4 = new HotDrink("Nescafe", 75, 10, 70);
        Product item5 = new HotDrink("Black Tea", 50, 10, 75);
        Product item6 = new HotDrink("Green Tea", 60, 10, 60);

        assort.add(item1);
        assort.add(item2);
        assort.add(item3);
        assort.add(item4);
        assort.add(item5);
        assort.add(item6);

//        Holder hold1 = new Holder(10, 10);
//        CoinDispenser cd1 = new CoinDispenser(120);
//
//        VendingMachine venMach1 = new VendingMachine(hold1, cd1, assort);

//        for(Product prod: venMach1.getProductList())
//        {
//            System.out.println(prod);
//        }
    }
}