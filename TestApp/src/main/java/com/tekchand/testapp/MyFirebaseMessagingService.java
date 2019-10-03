package com.tekchand.testapp;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.tekchand.testapp.activities.LogInActivity;
import com.tekchand.testapp.activities.MainActivity;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull String s) {
        Log.e("NEW_TOKEN", s);
    }
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        showNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());

        }

        public void showNotification(String title, String message) {
            Intent intent = new Intent(this, LogInActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
            Intent intent2 = new Intent(this, MainActivity.class);
            intent2.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                    PendingIntent.FLAG_ONE_SHOT);
            PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0 /* Request code */, intent2,
                    PendingIntent.FLAG_ONE_SHOT);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "MyNotifications")
                    .setContentTitle(title)
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setAutoCancel(true)
                    .setContentText(message)
                    .setContentIntent(pendingIntent)
                    .setColor(Color.BLUE)
                    .setOnlyAlertOnce(true)
                    .addAction(R.drawable.ic_launcher,"LogIn", pendingIntent)
                    .addAction(R.drawable.ic_launcher,"Home", pendingIntent2);
            NotificationManagerCompat manager = NotificationManagerCompat.from(this);
            manager.notify(0,builder.build());
        }
}
