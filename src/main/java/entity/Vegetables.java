package entity;

public class Vegetables {
    private int price;
    private String name;
    private int time;
    private int fullPrice;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(int totalcost) {
        this.fullPrice = fullPrice;
    }

    public Vegetables(int price, String name, int time, int totalcost) {
        this.price = price;
        this.name = name;
        this.time = time;
        this.fullPrice = totalcost;
    }
}
