package dav1nci.org.ticketsorder;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TabHost;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import dav1nci.org.ticketsorder.dbutil.DatabaseHandler;
import dav1nci.org.ticketsorder.dbutil.ServerUtill;
import dav1nci.org.ticketsorder.dbutil.entities.Auto;
import dav1nci.org.ticketsorder.dbutil.entities.Passenger;
import dav1nci.org.ticketsorder.dbutil.entities.Ticket;

/**
 * Created by dav1nci on 02.12.15.
 */
public class RegistrationActivity extends Activity implements View.OnClickListener, View.OnFocusChangeListener{

    private TabHost tabHost;
    private EditText nameSurname;
    private EditText passport;
    private EditText birthDay;
    private EditText table;
    private EditText cabin;
    private EditText travelDate;
    private EditText licencePlates;
    private EditText gouvernment;
    private EditText brand;
    private EditText travelDateCar;
    private DatePickerDialog birthDatePickerDialog;
    private DatePickerDialog travelDatePickerDialog;
    private DatePickerDialog carDatePickerDialog;


    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        initViews();
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        birthDay.setOnFocusChangeListener(this);
        travelDate.setOnFocusChangeListener(this);
        setDateTimeFields();



        /*Log.d("IsBuy", String.valueOf(*//*Boolean.getBoolean(*//*getIntent().getStringExtra("isBuy")));*/

    }

    public void nextStep(View view)
    {
        tabHost.setCurrentTab(1);
    }

    private void setDateTimeFields()
    {
        travelDate.setOnFocusChangeListener(this);
        travelDate.setOnClickListener(this);
        travelDateCar.setOnClickListener(this);
        travelDateCar.setOnFocusChangeListener(this);
        birthDay.setOnClickListener(this);
        birthDay.setOnFocusChangeListener(this);
        Calendar newCalendar = Calendar.getInstance();
        birthDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                birthDay.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        travelDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                travelDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        carDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                travelDateCar.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    //save objects to database
    public void finishRegistration(View view) {
        DatabaseHandler dbHandler = new DatabaseHandler(getApplicationContext());

        Ticket ticket = new Ticket();

        ticket.setIsBuy(Boolean.valueOf(getIntent().getStringExtra("isBuy")));
        Passenger passenger = null;
        if (!isEmptyPasenger())
        {
            passenger = new Passenger();
            passenger.setNameSurname(nameSurname.getText().toString());
            passenger.setPassportSerial(nameSurname.getText().toString());
            try
            {
                passenger.setBirthDate(dateFormatter.parse(birthDay.getText().toString()));
                ticket.setTravelDate(dateFormatter.parse(travelDate.getText().toString()));
            } catch (ParseException e){Log.d("EXCEPTION", "DATE PARSE EXCEPTION");}
            passenger.setCabin(dbHandler.findCabin(Integer.valueOf(cabin.getText().toString())));
            passenger.setTable(dbHandler.findTable(Integer.valueOf(table.getText().toString())));
            ticket.setPassenger(passenger);
        }
        if (!isEmptyGoods())
        {
            Auto auto = new Auto();
            try {
                ticket.setTravelDate(dateFormatter.parse(travelDateCar.getText().toString()));
            } catch (ParseException e){Log.d("EXCEPTION", "DATE PARSE EXCEPTION");}
            auto.setLicencePlates(licencePlates.getText().toString());
            auto.setGouvernment(gouvernment.getText().toString());
            auto.setBrand(brand.getText().toString());
            auto.setTransportingCost(150);
            auto.setDriver(passenger != null ? passenger : null);
            auto.setTicket(ticket);
            ticket.setAuto(auto);
        }
        if (!isEmptyPasenger() || !isEmptyGoods())
        {
            countPriceForTicket(ticket);
            long ticketId = dbHandler.addTicket(ticket);
            saveTicketOnServer(ticket);
            Intent intent = new Intent(RegistrationActivity.this, RegistrationComplete.class);
            intent.putExtra("ticket_id", ticketId);
            startActivity(intent);
            finish();
        }
        Log.d("NULL:::::::", String.valueOf(isEmptyPasenger()));
    }

    private void saveTicketOnServer(Ticket ticket)
    {
        ServerUtill serverUtill = new ServerUtill("192.168.100.105", 6666, ticket);
        try
        {
            //waiting for thread stop
            System.out.println("Wait for end of threads.");
            serverUtill.t.join();
        } catch (InterruptedException e)
        {
            System.out.println("Main thread has been crushed!!!");
        }
    }

    private void countPriceForTicket(Ticket ticket)
    {
        //travelPriceWithDiscount can't be 0 because i call this method in if (!isEmptyPasenger() || !isEmptyGoods()) block
        double travelPriceWithDiscount = 0;
        if (ticket.getPassenger() != null)
        {
            long currentTime = System.currentTimeMillis();
            long passengerBirthDate = ticket.getPassenger().getBirthDate().getTime();
            Log.d("AGE", String.valueOf(currentTime - passengerBirthDate));
            Log.d("10Y", String.valueOf(31536000000l));
            if (currentTime - passengerBirthDate < 315360000000l)
            {
                ticket.setDiscount(50);
                travelPriceWithDiscount = ticket.getPassenger().getCabin().getPrice() * ticket.getDiscount() / 100;
            }
            else
                travelPriceWithDiscount = ticket.getPassenger().getCabin().getPrice();
        }
        if (ticket.getAuto() != null)
        {
            if (ticket.getAuto().getTransportingCost() != 0)
                travelPriceWithDiscount += ticket.getAuto().getTransportingCost();
        }
        ticket.setPrice(travelPriceWithDiscount);
    }

    private boolean isEmptyPasenger()
    {

        return (nameSurname.getText().toString().equals("")
                || passport.getText().toString().equals("")
                || birthDay.getText().toString().equals("")
                || table.getText().toString().equals("")
                || cabin.getText().toString().equals("")
                || travelDate.getText().toString().equals(""));
    }

    private boolean isEmptyGoods()
    {
        return (licencePlates.getText().toString().equals("")
                || gouvernment.getText().toString().equals("")
                || brand.getText().toString().equals(""));
    }

    private void initViews()
    {
        tabHost = (TabHost)findViewById(R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("tag1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Passenger");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("tag2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Auto");
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
        nameSurname = (EditText)findViewById(R.id.valueNameSurname);
        passport = (EditText)findViewById(R.id.valuePassport);
        birthDay = (EditText)findViewById(R.id.valueBirthDay);
        table = (EditText)findViewById(R.id.valueTable);
        cabin = (EditText)findViewById(R.id.valueCabin);
        travelDate = (EditText)findViewById(R.id.valueTravelDate);
        licencePlates = (EditText)findViewById(R.id.valueLicencePlates);
        gouvernment = (EditText)findViewById(R.id.valueGouvernment);
        brand = (EditText)findViewById(R.id.valueBrand);
        travelDateCar = (EditText)findViewById(R.id.valueTravelDateCar);
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus)
        {
            switch (v.getId())
            {
                case R.id.valueBirthDay:
                    birthDatePickerDialog.show();
                    return;
                case R.id.valueTravelDate:
                    travelDatePickerDialog.show();
                    return;
                case R.id.valueTravelDateCar:
                    carDatePickerDialog.show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.valueBirthDay:
                birthDatePickerDialog.show();
                return;
            case R.id.valueTravelDate:
                travelDatePickerDialog.show();
                return;
            case R.id.valueTravelDateCar:
                carDatePickerDialog.show();
        }
    }
}
