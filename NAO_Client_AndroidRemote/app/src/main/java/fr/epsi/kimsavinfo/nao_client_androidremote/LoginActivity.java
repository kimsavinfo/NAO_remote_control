package fr.epsi.kimsavinfo.nao_client_androidremote;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends ActionBarActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void connection(View view)
    {
        String ipAdresse = ((EditText)findViewById(R.id.IP_address_input)).getText().toString();
        int port = Integer.parseInt(((EditText)findViewById(R.id.port_input)).getText().toString());

        NAO_SocketFactory orderManager = new NAO_SocketFactory(ipAdresse, port);

        /* Ne peut pas être envoyée dans un Thread principal
        if(orderManager.sendOrder("goodbye", ""))
            Log.d("orderManager", "OK");
        else
            Log.d("orderManager", "KO");
            */
    }
}
