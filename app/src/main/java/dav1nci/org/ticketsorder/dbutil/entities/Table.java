package dav1nci.org.ticketsorder.dbutil.entities;

import java.util.Date;

/**
 * Created by dav1nci on 02.12.15.
 */
public class Table
{
    private int id;
    private int number;
    private int amountOfPlaces;
    private int placesOccupied;
    private Date date;

    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

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
