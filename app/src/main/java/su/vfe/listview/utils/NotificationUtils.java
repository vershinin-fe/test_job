package su.vfe.listview.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import su.vfe.listview.R;
import su.vfe.listview.model.Item;

public class NotificationUtils {

    private static final String NOTIFICATION_CHANNEL_ID = "notification_channel";

    public static void showNotification(Context context, Item item, int notificationId) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        StatusBarNotification[] activeNotifications = notificationManager.getActiveNotifications();
        for(StatusBarNotification notification : activeNotifications) {
            if(notification.getId() == notificationId) {
                notificationManager.cancel(notificationId);
                return;
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    context.getString(R.string.main_notification_channel_name),
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context,NOTIFICATION_CHANNEL_ID)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(item.getTitle())
                .setContentText(item.getTitle())
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        notificationManager.notify(notificationId, notificationBuilder.build());
    }
}
