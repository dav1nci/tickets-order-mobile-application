package dav1nci.org.ticketsorder.dbutil.entities;

import java.util.Date;

/**
 * Created by dav1nci on 02.12.15.
 */
public class Ticket
{
    private int id;
    private boolean isBuy;
    private Date travelDate;
    private Passenger passenger;
    private Auto auto;
    private double price;
    private double discount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isBuy() {
        return isBuy;
    }

    public void setIsBuy(boolean isBuy) {
        this.isBuy = isBuy;
    }

    public Date getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }
}
