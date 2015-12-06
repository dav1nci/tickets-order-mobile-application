package dav1nci.org.ticketsorder;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;

import dav1nci.org.ticketsorder.dbservice.DatabaseHandler;
import dav1nci.org.ticketsorder.dbservice.OutputInfo;

/**
 * Created by dav1nci on 02.12.15.
 */
public class RegistrationComplete extends Activity {

    private TextView name;
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
        outputTicketData();
    }

    private void outputTicketData()
    {
        DatabaseHandler dbHandler = new DatabaseHandler(getApplicationContext());
        OutputInfo info = dbHandler.getOutputInfo(getIntent().getLongExtra("ticket_id", 1));
        name.setText(info.getName());
        date.setText((new Date(info.getTravelDate())).toString());
        price.setText(String.valueOf(info.getPrice()));
        cabin.setText(String.valueOf(info.getCabinNumber()));
        table.setText(String.valueOf(info.getTableNumber()));
        totalPeoples.setText(String.valueOf(dbHandler.getTotalPassengers()));
        double[] profit = dbHandler.getProfit();
        totalProfitOrdered.setText(String.valueOf(profit[0]));
        totalProfitBought.setText(String.valueOf(profit[1]));
    }

    private void initViews()
    {
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
