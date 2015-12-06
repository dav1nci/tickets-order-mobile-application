package dav1nci.org.ticketsorder;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import dav1nci.org.ticketsorder.dbservice.DBServiceContract;
import dav1nci.org.ticketsorder.dbservice.DatabaseHandler;

public class MainActivity extends Activity
{
    private Button buyTickets;
    private Button reserveTickets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buyTickets = (Button)findViewById(R.id.buy);
        reserveTickets = (Button)findViewById(R.id.reserve);

        buyTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(true);
            }
        });

        reserveTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(false);
            }
        });
        /*DatabaseHandler dbService = new DatabaseHandler(getApplicationContext());
        final SQLiteDatabase database = dbService.getWritableDatabase();

        database.execSQL("UPDATE tables SET places_occupied = 0");*/

    }

    private void startNewActivity(boolean isBuy)
    {
        Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
        intent.putExtra("isBuy", String.valueOf(isBuy));
        startActivity(intent);
        //finish();
    }

}


/*
for (int i = 2; i < 10; ++i)
        {
        values.put("id", i);
        values.put("number", i);
        values.put("amount_of_sits", 2);
        db.insert(DatabaseHandler.TABLE_TABLES, "", values);
        }
        for (int i = 16; i < 21; ++i)
        {
            values.put("id", i);
            values.put("number", i);
            values.put("amount_of_sits", 6);
            values.put("price", 110.5);
            db.insert(DatabaseHandler.TABLE_CABINS, "", values);
        }*/
