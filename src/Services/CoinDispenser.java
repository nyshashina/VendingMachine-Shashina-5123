package Services;

public class CoinDispenser {
    private int nominal;
    private int insertedSum = 0;

    public CoinDispenser(int nominal) {
        this.nominal = nominal;
    }

    public String toString() {
        return "Номинал монеты равен " + nominal;
    }



    public int insertc(int coin) {
        insertedSum = insertedSum + coin;
        return insertedSum;
    }

    public int returnc() {
        int rinsertedSum = insertedSum;
        insertedSum = 0;
        return rinsertedSum;
    }

    public void acceptInserted() {
        insertedSum = 0;
    }

    public int getInsertedSum() {
        return insertedSum;
    }

    public int giveChange(int price) {
        return price < insertedSum ? insertedSum - price : 0;
    }
}