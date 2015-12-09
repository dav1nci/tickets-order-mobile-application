package dav1nci.org.ticketsorder.dbutil;

import dav1nci.org.ticketsorder.dbutil.entities.Cabin;
import dav1nci.org.ticketsorder.dbutil.entities.Passenger;
import dav1nci.org.ticketsorder.dbutil.entities.Table;
import dav1nci.org.ticketsorder.dbutil.entities.Ticket;

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
