package com.androiddevs.firebasenotifications

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.core.content.ContextCompat.startActivity

/**
 * Implementation of App Widget functionality.
 */

class WalkerAlertWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.walker_alert_widget)

    val pendingL1 : PendingIntent = Intent(context, MainActivity::class.java)
        .let { intent ->
            intent.action = Constants.ACTION_L1
            intent.putExtra("message", "Level One!")
            PendingIntent.getActivity(context, 0, intent, 0)
        }
    views.setOnClickPendingIntent(R.id.btnLevelOne, pendingL1)

    val pendingL2 : PendingIntent = Intent(context, MainActivity::class.java)
        .let { intent ->
            intent.action = Constants.ACTION_L2
            intent.putExtra("message", "Level Two!")
            PendingIntent.getActivity(context, 0, intent, 0)
        }
    views.setOnClickPendingIntent(R.id.btnLevelTwo, pendingL2)

    val pendingL3 : PendingIntent = Intent(context, MainActivity::class.java)
        .let { intent ->
            intent.action = Constants.ACTION_L3
            intent.putExtra("message", "Level Three!")
            PendingIntent.getActivity(context, 0, intent, 0)
        }
    views.setOnClickPendingIntent(R.id.btnLevelThree, pendingL3)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}