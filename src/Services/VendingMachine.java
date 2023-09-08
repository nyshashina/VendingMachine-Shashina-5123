package Services;

import java.util.List;

import Domen.Product;

public class VendingMachine {
    private Holder holder;
    private CoinDispenser dispenser;


    public VendingMachine(Holder holder, CoinDispenser dispenser) {
        this.holder = holder;
        this.dispenser = dispenser;

    }

    public void buyProduct(int row, int column, int payment) {

    }

    public void release() {

    }

    public Holder getHolder() {
        return holder;
    }

    public Product getProduct(int row, int column) {
        return holder.getProduct(row, column);
    }

    public CoinDispenser getDispenser() {
        return dispenser;
    }
}
