package fr.epsi.kimsavinfo.nao_client_androidremote;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import fr.epsi.kimsavinfo.nao_client_androidremote.Lib_ID.IDManager;

/**
 * Created by kimsavinfo on 26/04/15.
 */
public class NAO_SocketManager
{
    String ipAddress;
    int port;
    String uid;
    Socket socket;

    public NAO_SocketManager(String _ipAdresse, int _port)
    {
        ipAddress = _ipAdresse;
        port = _port;
        uid = IDManager.getUID();
    }

    public boolean sendOrder(String _order, String _parameters)
    {
        boolean succeed = false;

        try
        {
            socket = new Socket(ipAddress, port);

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            String sendMessage = uid+"#"+_order+"#"+_parameters;
            Log.d("Message envoye au serveur : ", sendMessage);

            bw.write(sendMessage);
            bw.flush();
            socket.close();

            succeed = true;
        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return succeed;
    }
}
