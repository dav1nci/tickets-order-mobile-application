package dav1nci.org.ticketsorder.dbutil;

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

    //Car output info
    private String licencePlates;
    private String gouvernment;
    private String brand;

    public OutputInfo() {
    }

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

    public String getLicencePlates() {
        return licencePlates;
    }

    public void setLicencePlates(String licencePlates) {
        this.licencePlates = licencePlates;
    }

    public String getGouvernment() {
        return gouvernment;
    }

    public void setGouvernment(String gouvernment) {
        this.gouvernment = gouvernment;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
