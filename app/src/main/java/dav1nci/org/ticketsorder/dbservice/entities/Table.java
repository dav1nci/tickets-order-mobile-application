package dav1nci.org.ticketsorder.dbservice.entities;

/**
 * Created by dav1nci on 02.12.15.
 */
public class Table
{
    private int number;
    private int amountOfPlaces;
    private int placesOccupied;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getAmountOfPlaces() {
        return amountOfPlaces;
    }

    public void setAmountOfPlaces(int amountOfPlaces) {
        this.amountOfPlaces = amountOfPlaces;
    }

    public int getPlacesOccupied() {
        return placesOccupied;
    }

    public void setPlacesOccupied(int placesOccupied) {
        this.placesOccupied = placesOccupied;
    }
}
