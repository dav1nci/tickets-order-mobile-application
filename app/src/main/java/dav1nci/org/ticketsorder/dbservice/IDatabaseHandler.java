package dav1nci.org.ticketsorder.dbservice;

import dav1nci.org.ticketsorder.dbservice.entities.Cabin;
import dav1nci.org.ticketsorder.dbservice.entities.Passenger;
import dav1nci.org.ticketsorder.dbservice.entities.Table;
import dav1nci.org.ticketsorder.dbservice.entities.Ticket;

/**
 * Created by dav1nci on 02.12.15.
 */
public interface IDatabaseHandler
{
    long addTicket(Ticket ticket);
    OutputInfo getOutputInfo(long id);
    int getTotalPassengers();
    double[] getProfit();
    void updatePassenger(Passenger passenger);
    void deletePassenger(Passenger passenger);
    Cabin findCabin(int amountOfPlaces);
    Table findTable(int amountOfPlaces);
}
