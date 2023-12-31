package Domen;
public class HotDrink extends Product {

    private float temperature;
    public HotDrink(String name, int price, int amount, float temperature) {
        super(name, price, amount);
        this.temperature = temperature;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString(){
        return super.toString() + String.format("; temperature=%s", this.temperature);
    }
}

