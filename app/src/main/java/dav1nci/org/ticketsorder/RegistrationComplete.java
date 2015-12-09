package dav1nci.org.ticketsorder;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;

import dav1nci.org.ticketsorder.dbutil.DatabaseHandler;
import dav1nci.org.ticketsorder.dbutil.OutputInfo;

/**
 * Created by dav1nci on 02.12.15.
 */
public class RegistrationComplete extends Activity {

    private TextView name;
    private TextView textViewCabin;
    private TextView textViewDate;
    private TextView textViewPrice;
    private TextView textViewTable;
    private TextView date;
    private TextView price;
    private TextView cabin;
    private TextView table;
    private TextView totalPeoples;
    private TextView totalProfitOrdered;
    private TextView totalProfitBought;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_complete);
        initViews();
        DatabaseHandler dbHandler = new DatabaseHandler(getApplicationContext());
        OutputInfo info = dbHandler.getOutputInfo(getIntent().getLongExtra("ticket_id", 1));
        try {
            outputTicketData(info);
        }catch (NullPointerException e)
        {
            textViewCabin.setText("");
            textViewTable.setText("");
            textViewPrice.setText("Licence Plates");
            textViewDate.setText("Gouvernment");
            name.setText(info.getBrand());
            date.setText(info.getGouvernment());
            price.setText(info.getLicencePlates());
        }

    }

    private void outputTicketData(OutputInfo info) throws NullPointerException
    {
        DatabaseHandler dbHandler = new DatabaseHandler(getApplicationContext());
        totalPeoples.setText(String.valueOf(dbHandler.getTotalPassengers()));
        double[] profit = dbHandler.getProfit();
        totalProfitOrdered.setText(String.valueOf(profit[0]));
        totalProfitBought.setText(String.valueOf(profit[1]));
        try {
            if (info.getName() == null || info.getName().equals(""))
                throw new NullPointerException("Passenger not exist");
            name.setText(info.getName());
            date.setText((new Date(info.getTravelDate())).toString());
            price.setText(String.valueOf(info.getPrice()));
            cabin.setText(String.valueOf(info.getCabinNumber()));
            table.setText(String.valueOf(info.getTableNumber()));
        }catch (Exception e){throw new NullPointerException("Passenger not exist");}

    }

    private void initViews()
    {
        textViewDate = (TextView)findViewById(R.id.textViewDate);
        textViewPrice = (TextView)findViewById(R.id.textViewPrice);
        textViewCabin = (TextView)findViewById(R.id.textViewCabin);
        textViewTable = (TextView)findViewById(R.id.textViewTable);
        name = (TextView)findViewById(R.id.output_name_surname);
        date = (TextView)findViewById(R.id.output_travel_date);
        price = (TextView)findViewById(R.id.output_price);
        cabin = (TextView)findViewById(R.id.output_cabin_number);
        table = (TextView)findViewById(R.id.output_table_number);
        totalPeoples = (TextView)findViewById(R.id.output_total_number_of_peoples);
        totalProfitOrdered = (TextView)findViewById(R.id.output_total_profit_ordered);
        totalProfitBought = (TextView)findViewById(R.id.output_total_profit_bought);
    }
}
