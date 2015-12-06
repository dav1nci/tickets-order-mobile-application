package dav1nci.org.ticketsorder.dbservice.entities;

/**
 * Created by dav1nci on 02.12.15.
 */
public class Auto
{
    private String licencePlates;
    private String gouvernment;
    private String brand;
    private double transportingCost;
    private Passenger driver;
    private Ticket ticket;

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

    public double getTransportingCost() {
        return transportingCost;
    }

    public void setTransportingCost(double transportingCost) {
        this.transportingCost = transportingCost;
    }

    public Passenger getDriver() {
        return driver;
    }

    public void setDriver(Passenger driver) {
        this.driver = driver;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
