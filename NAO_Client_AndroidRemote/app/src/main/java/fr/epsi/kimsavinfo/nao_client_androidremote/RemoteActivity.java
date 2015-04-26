package fr.epsi.kimsavinfo.nao_client_androidremote;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by kimsavinfo on 26/04/15.
 */
public class RemoteActivity extends Activity
{
    private SendOrderToNAO orderNaoTask;
    private NAO_SocketManager orderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);

        Bundle bundle = getIntent().getExtras();
        orderManager = new NAO_SocketManager(bundle.getString("ipAdress"), bundle.getInt("port"));
        orderNaoTask = new SendOrderToNAO();

        orderNaoTask.execute(orderManager);
    }

    /* ===========================================================================
    * Send order socket to NAO via a new thread
    ===========================================================================*/

    private class SendOrderToNAO extends AsyncTask<NAO_SocketManager, Void, Boolean>
    {
        private ProgressDialog progressDialog;
        private Boolean succeed;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(RemoteActivity.this,
                    "Please wait", "Sending information to NAO",
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
                succeed = orderManager[0].sendOrder("goodbye", "");

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
