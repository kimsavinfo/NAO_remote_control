package fr.epsi.kimsavinfo.nao_client_androidremote;

import android.app.ProgressDialog;
import android.os.AsyncTask;
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
        // Set the socket for NAO
        String ipAdress = ((EditText)findViewById(R.id.IP_address_input)).getText().toString();
        int port = Integer.parseInt(((EditText)findViewById(R.id.port_input)).getText().toString());
        NAO_SocketManager orderManager = new NAO_SocketManager(ipAdress, port);

        // Socket can only be send in another thread, not in the main
        new SendSocketTask().execute(orderManager);
    }

    private class SendSocketTask extends AsyncTask<NAO_SocketManager, Void, Void>
    {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(LoginActivity.this,
                    "Please wait", "Sending information to NAO",
                    true, false);
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
        }

        @Override
        protected Void doInBackground(NAO_SocketManager... orderManager)
        {
            try
            {
                Thread.sleep(1000);

                /*
                if(orderManager.sendOrder("goodbye", ""))
                    Log.d("orderManager", "OK");
                else
                    Log.d("orderManager", "KO");
                */
            }
            catch (Exception e)
            {
                Log.e("SendSocketTask - doInBackground ", e.toString());
            }
            return null;
        }
    }
}
