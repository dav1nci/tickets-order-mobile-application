package dav1nci.org.ticketsorder.dbutil;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import dav1nci.org.ticketsorder.dbutil.entities.Ticket;

/**
 * Created by dav1nci on 08.12.15.
 */
public class ServerUtill implements Runnable
{
    private String serverAddress;
    private int serverPort;
    public Thread t;
    private Ticket ticket;

    public ServerUtill(String serverAddress, int serverPort, Ticket ticket) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.ticket = ticket;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        Socket socket = null;
        try
        {
            Log.d("SOCKET INFORMATION:::", "SOCKET HAS BEEN STARTED!!!");
            socket = new Socket(serverAddress, serverPort);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            DataInputStream in = new DataInputStream(inputStream);
            DataOutputStream out = new DataOutputStream(outputStream);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String ticketInJson = gson.toJson(ticket);
            Log.d("JSON::::::", ticketInJson);
            out.writeUTF("saveTicket");
            out.writeUTF(ticketInJson);
            out.flush();
            String responce = in.readUTF();
            in.close();
            out.close();
            socket.close();
        }catch (Exception e)
        {
            Log.d("CONNECTION PROBLEMS", e.toString());
        }
    }
}
