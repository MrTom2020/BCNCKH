package vn.edu.tdmu.lethanhhiep.bcnckh.Sevice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import vn.edu.tdmu.lethanhhiep.bcnckh.Model.wifi_App;


public class ConnectionReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {

    }
    public static boolean isConnected()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) wifi_App.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return  networkInfo !=null && networkInfo.isConnectedOrConnecting();

    }
}
