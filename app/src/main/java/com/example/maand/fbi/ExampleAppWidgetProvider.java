package com.example.maand.fbi;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RemoteViews;
import android.media.MediaPlayer;
/**
 * Created by maand on 8/20/2018.
 */

public class ExampleAppWidgetProvider extends AppWidgetProvider {
    public static String ACTION_WIDGET_RECEIVER = "ActionReceiverWidget";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for(int appWidgetId : appWidgetIds){
            //Intent intent = new Intent(context, MainActivity.class);
            Intent intent = new Intent(context, ExampleAppWidgetProvider.class);
            intent.setAction(ACTION_WIDGET_RECEIVER);
            //PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
            PendingIntent actionPendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
            // RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.example_widget);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.example_widget);
            views.setOnClickPendingIntent(R.id.button,actionPendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId,views);
        }
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        // v1.5 fix that doesn't call onDelete Action
        final String action = intent.getAction();
        if (AppWidgetManager.ACTION_APPWIDGET_DELETED.equals(action)) {
            //The widget is being deleted off the desktop
            final int appWidgetId = intent.getExtras().getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
            if (appWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
                this.onDeleted(context, new int[] { appWidgetId });
            }
        } else {
            // check, if our Action was called
            if (intent.getAction().equals(ACTION_WIDGET_RECEIVER)) {

                //Play the audio file
                //The audio file is in /res/raw/ and is an OGG file
                MediaPlayer mPlay = MediaPlayer.create(context, R.raw.fbi_sound);
                mPlay.start();
            } else {
                // do nothing
            }


            super.onReceive(context, intent);
        }
    }
}
