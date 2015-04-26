package fr.epsi.kimsavinfo.nao_client_androidremote;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends Activity
{
    private ConnectToNAOTask naoConnectionTask;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        naoConnectionTask = new ConnectToNAOTask();
    }

    public void connection(View view) throws ExecutionException, InterruptedException
    {
        // Set the socket for NAO
        String ipAdress = ((EditText)findViewById(R.id.IP_address_input)).getText().toString();
        int port = Integer.parseInt(((EditText)findViewById(R.id.port_input)).getText().toString());
        NAO_SocketManager orderManager = new NAO_SocketManager(ipAdress, port);

        // Socket can only be send in another thread, not in the main
        // naoConnectionTask.execute(orderManager);
        // boolean isTaskExecuted = naoConnectionTask.get();


        if(true)
        {
            Intent intent = new Intent(LoginActivity.this, RemoteActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("ipAdress", ipAdress);
            bundle.putInt("port", port);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
    }

    /* ===========================================================================
    * Send connection socket to NAO via a new thread
    ===========================================================================*/

    private class ConnectToNAOTask extends AsyncTask<NAO_SocketManager, Void, Boolean>
    {
        private ProgressDialog progressDialog;
        private Boolean succeed;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(LoginActivity.this,
                    "Please wait", "Connecting to NAO",
                    true, false);
        }

        @Override
        protected void onPostExecute(Boolean _succeed)
        {
            super.onPostExecute(_succeed);
            progressDialog.dismiss();
        }

        @Override
        protected Boolean doInBackground(NAO_SocketManager... orderManager)
        {
            succeed = false;

            try
            {
                succeed = orderManager[0].sendOrder("say", "Bonjour, je vous écoute.");

                if(!succeed)
                {
                    Context context = getApplicationContext();
                    CharSequence text = "Connection not found";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
            catch (Exception e)
            {
                Log.e("SendSocketTask - doInBackground ", e.toString());
            }

            return succeed;
        }
    }
}
