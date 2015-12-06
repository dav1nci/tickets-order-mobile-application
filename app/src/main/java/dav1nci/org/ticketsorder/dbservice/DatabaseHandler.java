package dav1nci.org.ticketsorder.dbservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import dav1nci.org.ticketsorder.R;
import dav1nci.org.ticketsorder.dbservice.entities.Cabin;
import dav1nci.org.ticketsorder.dbservice.entities.Passenger;
import dav1nci.org.ticketsorder.dbservice.entities.Table;
import dav1nci.org.ticketsorder.dbservice.entities.Ticket;

/**
 * Created by dav1nci on 02.12.15.
 */
public class DatabaseHandler extends SQLiteOpenHelper implements IDatabaseHandler
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "BoatTravel.db";
    public static final String TABLE_PASSENGERS = "passengers";
    public static final String TABLE_AUTOS = "autos";
    public static final String TABLE_CABINS = "cabins";
    public static final String TABLE_TABLES = "tables";
    public static final String TABLE_TICKETS = "tickets";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        final String CREATE_TABLE_PASSENGERS = "CREATE TABLE " + TABLE_PASSENGERS + "(id INTEGER PRIMARY KEY, passport TEXT, name_surname TEXT, birth_date INTEGER, cabin_id INTEGER, table_id INTEGER, ticket_id INTEGER, FOREIGN KEY(table_id) REFERENCES " + TABLE_TABLES + "(id), FOREIGN KEY(cabin_id) REFERENCES " + TABLE_CABINS + "(id), FOREIGN KEY(ticket_id) REFERENCES " + TABLE_TICKETS + "(id))";
        final String CREATE_TABLE_AUTOS = "CREATE TABLE " + TABLE_AUTOS + "(id INTEGER PRIMARY KEY, licence_plates TEXT, gouvernment TEXT, brand TEXT, transporting_cost REAL, driver_id INTEGER, ticket_id INTEGER, FOREIGN KEY(driver_id) REFERENCES " + TABLE_PASSENGERS + "(id), FOREIGN KEY(ticket_id) REFERENCES " + TABLE_TICKETS + "(id))";
        final String CREATE_TABLE_TICKETS = "CREATE TABLE " + TABLE_TICKETS + "(id INTEGER PRIMARY KEY, is_buy INTEGER, travel_date INTEGER, price REAL, discount INTEGER)";
        final String CREATE_TABLE_TABLES = "CREATE TABLE " + TABLE_TABLES + "(id INTEGER PRIMARY KEY, number INTEGER, amount_of_sits INTEGER, places_occupied INTEGER)";
        final String CREATE_TABLE_CABINS = "CREATE TABLE " + TABLE_CABINS + "(id INTEGER PRIMARY KEY, number INTEGER, amount_of_sits INTEGER, price REAL, places_occupied INTEGER)";
        db.execSQL(CREATE_TABLE_PASSENGERS);
        db.execSQL(CREATE_TABLE_AUTOS);
        db.execSQL(CREATE_TABLE_TICKETS);
        db.execSQL(CREATE_TABLE_TABLES);
        db.execSQL(CREATE_TABLE_CABINS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public long addTicket(Ticket ticket)
    {
        final SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("is_buy", ticket.isBuy());
        values.put("travel_date", ticket.getTravelDate().getTime());
        values.put("price", ticket.getPrice());
        values.put("discount", ticket.getDiscount());

        long ticketId = db.insert("tickets", "", values);
        long driverId = -1;
        if (ticket.getPassenger() != null)
        {
            ContentValues currentValues = new ContentValues();
            currentValues.put("passport", ticket.getPassenger().getPassportSerial());
            currentValues.put("name_surname", ticket.getPassenger().getNameSurname());
            currentValues.put("birth_date", ticket.getPassenger().getBirthDate().getTime());
            currentValues.put("cabin_id", ticket.getPassenger().getCabin().getNumber());
            currentValues.put("table_id", ticket.getPassenger().getTable().getNumber());
            currentValues.put("ticket_id", ticketId);
            driverId = db.insert("passengers", "", currentValues);
        }
        if (ticket.getAuto() != null)
        {
            ContentValues currentValues = new ContentValues();
            currentValues.put("licence_plates", ticket.getAuto().getLicencePlates());
            currentValues.put("gouvernment", ticket.getAuto().getGouvernment());
            currentValues.put("brand", ticket.getAuto().getBrand());
            currentValues.put("transporting_cost", ticket.getAuto().getTransportingCost());
            currentValues.put("driver_id", driverId);
            currentValues.put("ticket_id", ticketId);
            db.insert("autos", "", currentValues);
        }
        db.close();
        return ticketId;
    }

    @Override
    public OutputInfo getOutputInfo(long id) {
        final SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT passengers.name_surname, tickets.travel_date, tickets.price, tickets.discount, cabins.number, tables.number " +
                "FROM passengers " +
                "INNER JOIN tickets ON passengers.ticket_id = tickets.id " +
                "INNER JOIN cabins ON passengers.cabin_id = cabins.id " +
                "INNER JOIN tables ON passengers.table_id = tables.id " +
                "WHERE tickets.id = " + id, null);
        if (cursor.moveToFirst())
        {
            final String name = cursor.getString(0);
            final long travelDate = cursor.getInt(1);
            final double price = cursor.getDouble(2);
            final int discount = cursor.getInt(3);
            final int cabinNumber = cursor.getInt(4);
            final int tableNumber = cursor.getInt(5);
            OutputInfo outputInfo = new OutputInfo(name, travelDate, price, discount, cabinNumber, tableNumber);
            cursor.close();
            db.close();
            return  outputInfo;
            /*Log.d("INNER JOIN::::", name);
            Log.d("INNER JOIN::::", String.valueOf(travelDate));
            Log.d("INNER JOIN::::", String.valueOf(price));
            Log.d("INNER JOIN::::", String.valueOf(discount));
            Log.d("INNER JOIN::::", String.valueOf(cabinNumber));
            Log.d("INNER JOIN::::", String.valueOf(tableNumber));*/
        }
        else Log.d("INNER JOIN::::::", "RETURNED 0 ROWS!!!!!!!!!");
        return null;
    }

    @Override
    public int getTotalPassengers() {
        final SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT count(id) FROM passengers", null);
        if (cursor.moveToFirst())
        {
            final int total = cursor.getInt(0);
            cursor.close();
            db.close();
            return total;
        }
        return -1;
    }

    @Override
    public double[] getProfit() {
        final SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT sum(price) FROM tickets GROUP BY is_buy", null);
        if (cursor.moveToFirst())
        {
            double[] profit = new double[2];
            profit[0] = cursor.getDouble(0);
            cursor.moveToNext();
            profit[1] = cursor.getDouble(0);
            cursor.close();
            db.close();
            return profit;
        }
        double profit[] = {0, 0};
        return profit;
    }

    @Override
    public void updatePassenger(Passenger passenger) {

    }

    @Override
    public void deletePassenger(Passenger passenger) {

    }


    @Override
    public Cabin findCabin(int amountOfPlaces) {
        SQLiteDatabase db = getWritableDatabase();

        for (int i = amountOfPlaces; i <= 6; i+=2)
        {
            Cursor cursor = db.rawQuery("SELECT * FROM cabins WHERE amount_of_sits = " + i, null);
            if (cursor.moveToFirst())
            {
                do {
                    final int id = cursor.getInt(0);
                    final int cabinNumber = cursor.getInt(1);
                    final int amountOfSits = cursor.getInt(2);
                    final double price = cursor.getDouble(3);
                    final int placesOccupied = cursor.getInt(4);
                    if (placesOccupied < amountOfSits)
                    {
                        Cabin cabin = new Cabin();
                        cabin.setNumber(cabinNumber);
                        cabin.setAmountOfSits(amountOfSits);
                        cabin.setPrice(price);
                        cabin.setPlacesOccupied(placesOccupied);
                        db.execSQL("UPDATE cabins SET places_occupied = " + (placesOccupied + 1) + " WHERE id = " + id);
                        cursor.close();
                        db.close();
                        return cabin;
                    }

                } while (cursor.moveToNext());
            }
            /*else
            {
                if (i >= 6)
                    return null;
            }*/
        }
        return null;
    }

    @Override
    public Table findTable(int amountOfPlaces) {
        SQLiteDatabase db = getWritableDatabase();

        for (int i = amountOfPlaces; i <= 6; i+=2)
        {
            Cursor cursor = db.rawQuery("SELECT * FROM tables WHERE amount_of_sits = " + i, null);
            if (cursor.moveToFirst())
            {
                do {
                    final int id = cursor.getInt(0);
                    final int tableNumber = cursor.getInt(1);
                    final int amountOfSits = cursor.getInt(2);
                    final int placesOccupied = cursor.getInt(3);
                    if (placesOccupied < amountOfSits)
                    {
                        Table table = new Table();
                        table.setNumber(tableNumber);
                        table.setAmountOfPlaces(amountOfSits);
                        table.setPlacesOccupied(placesOccupied);
                        db.execSQL("UPDATE tables SET places_occupied = " + (placesOccupied + 1) + " WHERE id = " + id);
                        cursor.close();
                        db.close();
                        return table;
                    }

                } while (cursor.moveToNext());
            }
        }
        return null;
    }

}
