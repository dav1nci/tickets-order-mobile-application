package dav1nci.org.ticketsorder;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import dav1nci.org.ticketsorder.dbutil.StatisticsInfo;

/**
 * Created by dav1nci on 10.12.15.
 */
public class ShowStatisticsActivity extends Activity
{

    private TextView date;
    private TextView sum;
    private TextView pleaseWait;
    private List<StatisticsInfo> statisticsInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_statistics);
        date = (TextView)findViewById(R.id.statistics_date);
        sum = (TextView)findViewById(R.id.statistics_sum);
        pleaseWait = (TextView)findViewById(R.id.plese_wait);
        statisticsInfo = new ArrayList<>();
        StatystycsTask statystycsTask = new StatystycsTask();
        statystycsTask.execute();
    }

    class StatystycsTask extends AsyncTask<String, Void, List<StatisticsInfo>>
    {

        @Override
        protected List<StatisticsInfo> doInBackground(String... params) {
            Socket socket = null;
            try
            {
                Log.d("SOCKET INFORMATION:::", "SOCKET HAS BEEN STARTED!!!");
                socket = new Socket("192.168.100.105", 6666);
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                DataInputStream in = new DataInputStream(inputStream);
                DataOutputStream out = new DataOutputStream(outputStream);

                out.writeUTF("statistics");
                String responce = in.readUTF();
                Gson gson = new Gson();
                statisticsInfo = gson.fromJson(responce, new TypeToken<List<StatisticsInfo>>(){}.getType());
                out.flush();
                out.close();
                in.close();
                socket.close();
                return statisticsInfo;
            }catch (Exception e)
            {
                Log.d("CONNECTION PROBLEMS", e.toString());
            }
            return null;

        }

        @Override
        protected void onPostExecute(List<StatisticsInfo> statisticsInfo) {
            if (statisticsInfo == null)
            {
                pleaseWait.setText("Internet connection problems");
                return;
            }
            pleaseWait.setText("Statistics");
            String resultDate = "";
            String resultSum = "";
            for (StatisticsInfo i : statisticsInfo)
            {
                resultDate += i.getDate().toString() + "\n";
                resultSum += i.getProfit() + "\n";
            }
            date.setText(resultDate);
            sum.setText(resultSum);
        }
    }
}
