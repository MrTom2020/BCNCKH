package vn.edu.tdmu.lethanhhiep.bcnckh.Sevice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;


import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Random;

import vn.edu.tdmu.lethanhhiep.bcnckh.R;

public class MyFirebaseService extends FirebaseMessagingService
{
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if(remoteMessage.getData().isEmpty())
        {
            showNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
        }
        else
        {
            showNotificalion(remoteMessage.getData());
        }
    }



    private void showNotificalion(Map<String,String>data)
    {
        String title = data.get("title");
        String body = data.get("body");

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID ="example.nckh.Service.test";
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,"Notifiation",NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Code sphere");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(0xff93ab52);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder notificationCompatBuilder = new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID);
        notificationCompatBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.cloudy)
                .setContentTitle(title)
                .setContentText(body)
                .setContentInfo("Info");

        notificationManager.notify(new Random().nextInt(),notificationCompatBuilder.build());

    }
    private void showNotification(String title,String body)
    {
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID ="example.nckh.Service.test";
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,"Notifiation",NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Code sphere");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(0xff93ab52);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder notificationCompatBuilder = new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID);
        notificationCompatBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.cloudy)
                .setContentTitle(title)
                .setContentText(body)
                .setContentInfo("Info");
        notificationManager.notify(new Random().nextInt(),notificationCompatBuilder.build());
    }

    @Override
    public void onNewToken(@NonNull String s)
    {
        super.onNewToken(s);
    }
}
