package vn.edu.tdmu.lethanhhiep.bcnckh.Activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

import vn.edu.tdmu.lethanhhiep.bcnckh.R;
import vn.edu.tdmu.lethanhhiep.bcnckh.Sevice.ConnectionReceiver;


public class Second extends TabActivity {

    private TabHost tabHost;
    private TabHost.TabSpec tabSpec;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        dangkynut();
        ax();
    }
    private void dangkynut()
    {
        tabHost = (TabHost)findViewById(android.R.id.tabhost);
    }
//    private void check()
//    {
//        boolean ret = ConnectionReceiver.isConnected();
//        String ms;
//        if(ret)
//        {
//            ms = "The device has an Internet connection and can be done online";
//        }
//        else
//        {
//            ms = "The device does not have an Internet connection and can be performed offline";
//        }
//        Toast.makeText(Second.this,ms,Toast.LENGTH_SHORT).show();
//    }

    private void ax()
    {
        tabSpec = tabHost.newTabSpec ("Account");
        tabSpec.setIndicator ("",getDrawable(R.drawable.userrr));
        intent = new Intent (this, Info_user.class);
        tabSpec.setContent (intent);
        tabHost.addTab (tabSpec);


        tabSpec = tabHost.newTabSpec ("News");
        tabSpec.setIndicator ("",getDrawable(R.drawable.qr));
        intent = new Intent (this, QR.class);
        tabSpec.setContent (intent);
        tabHost.addTab (tabSpec);

        //tabHost.setBackgroundColor(0xff2b0000);
        tabHost.setCurrentTab(1);

    }

    @Override
    protected void onStart()
    {
       // check();
        super.onStart();
    }

    private class sukiencuatoi implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {

        }
    }
}