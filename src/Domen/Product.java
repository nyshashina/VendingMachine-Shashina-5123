package Domen;

public class Product {
    private String name;
    private int price;

    private int amount;
    public Product(String name, int price, int amount) throws IllegalArgumentException {
        this.name = name;

        if(price > 0)
        {
            this.price = price;
        }
        else
        {
            throw new IllegalArgumentException("Цена должна быть больше нуля");
        }

        if(amount < 0)
        {
            throw new IllegalArgumentException("Количество не может быть меньше нуля");
        }
        else
        {
            this.amount = amount;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {

        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString(){
        return "Product: price = " + this.price + "; name:" + this.name;
    }


}
