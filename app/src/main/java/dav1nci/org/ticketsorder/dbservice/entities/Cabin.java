package dav1nci.org.ticketsorder.dbservice.entities;

/**
 * Created by dav1nci on 02.12.15.
 */
public class Cabin
{
    private int number;
    private int amountOfSits;
    private double price;
    private int placesOccupied;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getAmountOfSits() {
        return amountOfSits;
    }

    public void setAmountOfSits(int amountOfSits) {
        this.amountOfSits = amountOfSits;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPlacesOccupied() {
        return placesOccupied;
    }

    public void setPlacesOccupied(int placesOccupied) {
        this.placesOccupied = placesOccupied;
    }
}
