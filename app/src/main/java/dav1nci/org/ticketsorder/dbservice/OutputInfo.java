package dav1nci.org.ticketsorder.dbservice;

/**
 * Created by dav1nci on 06.12.15.
 */
public class OutputInfo
{
    private String name;
    private long travelDate;
    private double price;
    private int discount;
    private int cabinNumber;
    private int tableNumber;

    public OutputInfo(String name, long travelDate, double price, int discount, int cabinNumber, int tableNumber) {
        this.name = name;
        this.travelDate = travelDate;
        this.price = price;
        this.discount = discount;
        this.cabinNumber = cabinNumber;
        this.tableNumber = tableNumber;
    }

    public String getName() {
        return name;
    }

    public long getTravelDate() {
        return travelDate;
    }

    public double getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }

    public int getCabinNumber() {
        return cabinNumber;
    }

    public int getTableNumber() {
        return tableNumber;
    }
}
