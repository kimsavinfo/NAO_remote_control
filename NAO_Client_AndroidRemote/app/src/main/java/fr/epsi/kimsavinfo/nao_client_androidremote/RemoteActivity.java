package fr.epsi.kimsavinfo.nao_client_androidremote;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

/**
 * Created by kimsavinfo on 26/04/15.
 */
public class RemoteActivity extends Activity
{
    private NAO_SocketManager orderManager;
    private String order;
    private String orderParameters;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);

        Bundle bundle = getIntent().getExtras();
        orderManager = new NAO_SocketManager(bundle.getString("ipAdress"), bundle.getInt("port"));

        order = "";
        orderParameters = "";
    }

    private void sendOrder() throws ExecutionException, InterruptedException
    {
        SendOrderTask orderNaoTask = new SendOrderTask();
        orderNaoTask.execute(orderManager);
        boolean suceed = orderNaoTask.get();
        handleOrderResponse(suceed);

        if(suceed && order.equals("goodbye"))
        {
            Intent intent = new Intent(RemoteActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void handleOrderResponse(boolean _succeed)
    {
        if(!_succeed)
        {
            Context context = getApplicationContext();
            CharSequence text = "Error, please try again latter";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void logout(View view) throws ExecutionException, InterruptedException
    {
        order = "goodbye";
        orderParameters = "";
        sendOrder();
    }

    /* ===========================================================================
    * Send order socket to NAO via a new thread
    ===========================================================================*/

    private class SendOrderTask extends AsyncTask<NAO_SocketManager, Void, Boolean>
    {
        private ProgressDialog progressDialog;
        private Boolean succeed;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(RemoteActivity.this,
                    "Please wait", "Sending orders to NAO",
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
                succeed = orderManager[0].sendOrder(order, orderParameters);
            }
            catch (Exception e)
            {
                Log.e("SendOrderToNAO - doInBackground ", e.toString());
            }

            return succeed;
        }
    }

}
