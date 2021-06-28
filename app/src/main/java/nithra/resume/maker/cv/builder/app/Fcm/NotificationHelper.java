package nithra.resume.maker.cv.builder.app.Fcm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import android.widget.RemoteViews;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import nithra.resume.maker.cv.builder.app.R;

class NotificationHelper extends ContextWrapper {
    public static final String PRIMARY_CHANNEL = "default";
    NotificationChannel chan1 = null;
    Context context;
    private NotificationManager manager;

    private int getSmallIcon() {
        return R.drawable.resume_ic_white;
    }

    private int getlogo() {
        return R.drawable.resumeicon;
    }

    public NotificationHelper(Context context2) {
        super(context2);
        this.context = context2;
        if (Build.VERSION.SDK_INT >= 26) {
            this.chan1 = new NotificationChannel(PRIMARY_CHANNEL, "Primary Channel", 3);
            this.chan1.setLightColor(-16711936);
            this.chan1.setShowBadge(true);
            this.chan1.setLockscreenVisibility(0);
            getManager().createNotificationChannel(this.chan1);
        }
    }

    public void Notification_bm(int i, String str, String str2, String str3, String str4, String str5, Class cls) {
        Notification.Builder builder;
        try {
            Uri defaultUri = RingtoneManager.getDefaultUri(2);
            if (Build.VERSION.SDK_INT >= 26) {
                if (str4.equals("bt")) {
                    Notification.Builder largeIcon = new Notification.Builder(this.context, PRIMARY_CHANNEL).setContentTitle("Resume Builder").setSound(defaultUri).setContentText("").setContentIntent(resultPendingIntent(str5, str2, i, cls)).setSmallIcon(getSmallIcon()).setColor(Color.parseColor("#6460AA")).setLargeIcon(LargeIcon(str3));
                    builder = largeIcon.setGroup("" + str).setStyle(bigtext1("Resume Builder", "Resume Builder", str5)).setAutoCancel(true);
                } else {
                    Notification.Builder largeIcon2 = new Notification.Builder(this.context, PRIMARY_CHANNEL).setContentTitle(str5).setSound(defaultUri).setContentText("").setContentIntent(resultPendingIntent(str5, str2, i, cls)).setSmallIcon(getSmallIcon()).setColor(Color.parseColor("#6460AA")).setLargeIcon(LargeIcon(str3));
                    builder = largeIcon2.setGroup("" + str).setStyle(bigimg1("Resume Builder", str5, str3)).setAutoCancel(true);
                }
                notify(i, builder);
            } else if (str4.equals("bt")) {
                NotificationCompat.Builder contentText = new NotificationCompat.Builder(this.context).setSound(defaultUri).setSmallIcon(getSmallIcon()).setColor(Color.parseColor("#6460AA")).setLargeIcon(getlogo1()).setAutoCancel(true).setPriority(2).setContentIntent(resultPendingIntent(str5, str2, i, cls)).setContentTitle("Resume Builder").setContentText("");
                notify(i, contentText.setGroup("" + str).setStyle(bigtext("Resume Builder", "Resume Builder", str5)).build());
            } else {
                NotificationCompat.Builder contentText2 = new NotificationCompat.Builder(this.context).setSound(defaultUri).setSmallIcon(getSmallIcon()).setColor(Color.parseColor("#6460AA")).setLargeIcon(getlogo1()).setAutoCancel(true).setPriority(2).setContentIntent(resultPendingIntent(str5, str2, i, cls)).setContentTitle(str5).setContentText("");
                notify(i, contentText2.setGroup("" + str).setStyle(bigimg("Resume Builder", str5, str3)).build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Notification1(int i, String str, String str2, String str3, String str4, String str5, Class cls) {
        Notification.Builder builder;
        try {
            Uri defaultUri = RingtoneManager.getDefaultUri(2);
            if (Build.VERSION.SDK_INT >= 26) {
                if (str4.equals("bt")) {
                    Notification.Builder largeIcon = new Notification.Builder(this.context, PRIMARY_CHANNEL).setContentTitle(str).setSound(defaultUri).setContentText("").setContentIntent(resultPendingIntent(str5, str2, i, cls)).setSmallIcon(getSmallIcon()).setColor(Color.parseColor("#6460AA")).setLargeIcon(LargeIcon(str3));
                    builder = largeIcon.setGroup("" + str).setStyle(bigtext1(str, "Resume Builder", "")).setAutoCancel(true);
                } else {
                    Notification.Builder largeIcon2 = new Notification.Builder(this.context, PRIMARY_CHANNEL).setContentTitle(str).setSound(defaultUri).setContentText("").setContentIntent(resultPendingIntent1(str5)).setSmallIcon(getSmallIcon()).setColor(Color.parseColor("#6460AA")).setLargeIcon(LargeIcon(str3));
                    builder = largeIcon2.setGroup("" + str).setStyle(bigimg1(str, "Resume Builder", str3)).setAutoCancel(true);
                }
                notify(i, builder);
            } else if (str4.equals("bt")) {
                NotificationCompat.Builder contentText = new NotificationCompat.Builder(this.context).setSound(defaultUri).setSmallIcon(getSmallIcon()).setColor(Color.parseColor("#6460AA")).setLargeIcon(getlogo1()).setAutoCancel(true).setPriority(2).setContentIntent(resultPendingIntent(str5, str2, i, cls)).setContentTitle(str).setContentText("");
                notify(i, contentText.setGroup("" + str).setStyle(bigtext(str, "Resume Builder", "")).build());
            } else {
                NotificationCompat.Builder contentTitle = new NotificationCompat.Builder(this.context).setSound(defaultUri).setSmallIcon(getSmallIcon()).setColor(Color.parseColor("#6460AA")).setLargeIcon(getlogo1()).setAutoCancel(true).setPriority(2).setContentIntent(resultPendingIntent1(str5)).setContentTitle(str);
                notify(i, contentTitle.setGroup("" + str).setContentText("").setStyle(bigimg(str, "Resume Builder", str3)).build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Notification_custom(int i, String str, String str2, String str3, String str4, String str5, Class cls) {
        NotificationCompat.Builder builder;
        Notification.Builder builder2;
        try {
            RingtoneManager.getDefaultUri(2);
            if (Build.VERSION.SDK_INT >= 26) {
                RemoteViews remoteViews = new RemoteViews(getPackageName(), (int) R.layout.notification_shown_st);
                remoteViews.setImageViewResource(R.id.image, getlogo());
                remoteViews.setTextViewText(R.id.title, str5);
                if (str4.equals("bt")) {
                    builder2 = new Notification.Builder(this.context, PRIMARY_CHANNEL).setSmallIcon(getSmallIcon()).setColor(Color.parseColor("#6460AA")).setGroup("" + str).setCustomContentView(remoteViews);
                } else {
                    RemoteViews remoteViews2 = new RemoteViews(getPackageName(), (int) R.layout.notification_shown_bi);
                    remoteViews2.setImageViewResource(R.id.image, getlogo());
                    remoteViews2.setTextViewText(R.id.title, str5);
                    remoteViews2.setImageViewBitmap(R.id.imgg, LargeIcon(str3));
                    builder2 = new Notification.Builder(this.context, PRIMARY_CHANNEL).setSmallIcon(getSmallIcon()).setGroup("" + str).setColor(Color.parseColor("#6460AA")).setCustomContentView(remoteViews).setCustomBigContentView(remoteViews2);
                }
                Notification build = builder2.build();
                if (Build.VERSION.SDK_INT >= 19) {
                    build.priority |= 2;
                }
                build.flags |= 16;
                build.flags |= 1;
                build.contentIntent = resultPendingIntent(str5, str2, i, cls);
                getManager().notify(i, build);
                return;
            }
            RemoteViews remoteViews3 = new RemoteViews(getPackageName(), (int) R.layout.notification_shown_st);
            remoteViews3.setImageViewResource(R.id.image, getlogo());
            remoteViews3.setTextViewText(R.id.title, str5);
            if (str4.equals("bt")) {
                builder = new NotificationCompat.Builder(this.context).setSmallIcon(getSmallIcon()).setColor(Color.parseColor("#6460AA")).setGroup("" + str).setContent(remoteViews3);
            } else {
                RemoteViews remoteViews4 = new RemoteViews(getPackageName(), (int) R.layout.notification_shown_bi);
                remoteViews4.setImageViewResource(R.id.image, getlogo());
                remoteViews4.setTextViewText(R.id.title, str5);
                remoteViews4.setImageViewBitmap(R.id.imgg, LargeIcon(str3));
                builder = new NotificationCompat.Builder(this.context).setSmallIcon(getSmallIcon()).setColor(Color.parseColor("#6460AA")).setGroup("" + str).setContent(remoteViews3).setCustomBigContentView(remoteViews4);
            }
            Notification build2 = builder.build();
            build2.defaults |= 1;
            if (Build.VERSION.SDK_INT >= 19) {
                build2.priority |= 2;
            }
            build2.flags |= 16;
            build2.flags |= 1;
            build2.contentIntent = resultPendingIntent(str5, str2, i, cls);
            getManager().notify(i, build2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = 16)
    public void notify(int i, Notification.Builder builder) {
        getManager().notify(i, builder.build());
    }

    public void notify(int i, Notification notification) {
        getManager().notify(i, notification);
    }

    private Bitmap getlogo1() {
        return BitmapFactory.decodeResource(getResources(), getlogo());
    }

    private Bitmap LargeIcon(String str) {
        BitmapFactory.decodeResource(getResources(), getlogo());
        if (str.length() <= 5) {
            return BitmapFactory.decodeResource(getResources(), getlogo());
        }
        try {
            return BitmapFactory.decodeStream((InputStream) new URL(str).getContent());
        } catch (IOException e) {
            e.printStackTrace();
            return BitmapFactory.decodeResource(getResources(), getlogo());
        }
    }

    private NotificationManager getManager() {
        if (this.manager == null) {
            this.manager = (NotificationManager) getSystemService("notification");
        }
        return this.manager;
    }

    public NotificationCompat.BigTextStyle bigtext(String str, String str2, String str3) {
        return new NotificationCompat.BigTextStyle().setBigContentTitle(str).setSummaryText(str2).bigText(str3);
    }

    @RequiresApi(api = 16)
    public Notification.BigTextStyle bigtext1(String str, String str2, String str3) {
        return new Notification.BigTextStyle().setBigContentTitle(str).setSummaryText(str2).bigText(str3);
    }

    public NotificationCompat.BigPictureStyle bigimg(String str, String str2, String str3) {
        return new NotificationCompat.BigPictureStyle().setBigContentTitle(str).bigPicture(LargeIcon(str3));
    }

    @RequiresApi(api = 16)
    public Notification.BigPictureStyle bigimg1(String str, String str2, String str3) {
        return new Notification.BigPictureStyle().setBigContentTitle(str).setSummaryText(str2).bigPicture(LargeIcon(str3));
    }

    public PendingIntent resultPendingIntent(String str, String str2, int i, Class cls) {
        Intent intent = set_intent(this.context, i, str, str2, cls);
        TaskStackBuilder create = TaskStackBuilder.create(this.context);
        create.addParentStack(cls);
        create.addNextIntent(intent);
        return create.getPendingIntent((int) System.currentTimeMillis(), 134217728);
    }

    public PendingIntent resultPendingIntent1(String str) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        TaskStackBuilder create = TaskStackBuilder.create(this.context);
        create.addNextIntent(intent);
        return create.getPendingIntent((int) System.currentTimeMillis(), 134217728);
    }

    public Intent set_intent(Context context2, int i, String str, String str2, Class cls) {
        Intent intent = new Intent(context2, cls);
        intent.setFlags(67108864);
        intent.putExtra("message", str2);
        intent.putExtra("title", str);
        intent.putExtra("idd", i);
        intent.putExtra("Noti_add", 1);
        return intent;
    }

    public void Notification_rao(String str, int i, String str2, String str3, String str4, String str5, String str6, Class cls) {
        Notification.Builder builder;
        try {
            Uri defaultUri = RingtoneManager.getDefaultUri(2);
            if (Build.VERSION.SDK_INT >= 26) {
                if (str5.equals("bt")) {
                    Notification.Builder largeIcon = new Notification.Builder(this.context, PRIMARY_CHANNEL).setContentTitle("Resume Builder").setSound(defaultUri).setContentText("").setContentIntent(resultPendingIntent1(str, str6, str3, i, cls)).setSmallIcon(getSmallIcon()).setColor(Color.parseColor("#6460AA")).setLargeIcon(LargeIcon(str4));
                    builder = largeIcon.setGroup("" + str2).setStyle(bigtext1("Resume Builder", "Resume Builder", str6)).setAutoCancel(true);
                } else {
                    Notification.Builder largeIcon2 = new Notification.Builder(this.context, PRIMARY_CHANNEL).setContentTitle(str6).setSound(defaultUri).setContentText("").setContentIntent(resultPendingIntent1(str, str6, str3, i, cls)).setSmallIcon(getSmallIcon()).setColor(Color.parseColor("#6460AA")).setLargeIcon(LargeIcon(str4));
                    builder = largeIcon2.setGroup("" + str2).setStyle(bigimg1("Resume Builder", str6, str4)).setAutoCancel(true);
                }
                notify(i, builder);
            } else if (str5.equals("bt")) {
                NotificationCompat.Builder contentText = new NotificationCompat.Builder(this.context).setSound(defaultUri).setSmallIcon(getSmallIcon()).setColor(Color.parseColor("#6460AA")).setLargeIcon(getlogo1()).setAutoCancel(true).setPriority(2).setContentIntent(resultPendingIntent1(str, str6, str3, i, cls)).setContentTitle("Resume Builder").setContentText("");
                notify(i, contentText.setGroup("" + str2).setStyle(bigtext("Resume Builder", "Resume Builder", str6)).build());
            } else {
                NotificationCompat.Builder contentText2 = new NotificationCompat.Builder(this.context).setSound(defaultUri).setSmallIcon(getSmallIcon()).setColor(Color.parseColor("#6460AA")).setLargeIcon(getlogo1()).setAutoCancel(true).setPriority(2).setContentIntent(resultPendingIntent1(str, str6, str3, i, cls)).setContentTitle(str6).setContentText("");
                notify(i, contentText2.setGroup("" + str2).setStyle(bigimg("Resume Builder", str6, str4)).build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Notification_custom1(String str, int i, String str2, String str3, String str4, String str5, String str6, Class cls) {
        NotificationCompat.Builder builder;
        Notification.Builder builder2;
        Uri defaultUri = RingtoneManager.getDefaultUri(2);
        if (Build.VERSION.SDK_INT >= 26) {
            RemoteViews remoteViews = new RemoteViews(getPackageName(), (int) R.layout.notification_shown_st);
            remoteViews.setImageViewResource(R.id.image, getlogo());
            remoteViews.setTextViewText(R.id.title, str6);
            if (str5.equals("bt")) {
                builder2 = new Notification.Builder(this.context, PRIMARY_CHANNEL).setSmallIcon(getSmallIcon()).setColor(Color.parseColor("#F2B12F")).setGroup(str6).setCustomContentView(remoteViews);
            } else {
                RemoteViews remoteViews2 = new RemoteViews(getPackageName(), (int) R.layout.notification_shown_bi);
                remoteViews2.setImageViewResource(R.id.image, getlogo());
                remoteViews2.setTextViewText(R.id.title, str6);
                remoteViews2.setImageViewBitmap(R.id.imgg, LargeIcon(str4));
                builder2 = new Notification.Builder(this.context, PRIMARY_CHANNEL).setSmallIcon(getSmallIcon()).setColor(Color.parseColor("#F2B12F")).setCustomContentView(remoteViews).setGroup(str6).setCustomBigContentView(remoteViews2);
            }
            Notification build = builder2.build();
            build.flags |= 16;
            build.priority |= 2;
            build.contentIntent = resultPendingIntent1(str, str6, str3, i, cls);
            getManager().notify(i, build);
            return;
        }
        RemoteViews remoteViews3 = new RemoteViews(getPackageName(), (int) R.layout.notification_shown_st);
        remoteViews3.setImageViewResource(R.id.image, getlogo());
        remoteViews3.setTextViewText(R.id.title, str6);
        if (str5.equals("bt")) {
            builder = new NotificationCompat.Builder(this.context).setSmallIcon(getSmallIcon()).setColor(Color.parseColor("#F2B12F")).setContent(remoteViews3).setGroup(str6);
        } else {
            RemoteViews remoteViews4 = new RemoteViews(getPackageName(), (int) R.layout.notification_shown_bi);
            remoteViews4.setImageViewResource(R.id.image, getlogo());
            remoteViews4.setTextViewText(R.id.title, str6);
            remoteViews4.setImageViewBitmap(R.id.imgg, LargeIcon(str4));
            builder = new NotificationCompat.Builder(this.context).setSmallIcon(getSmallIcon()).setColor(Color.parseColor("#F2B12F")).setContent(remoteViews3).setCustomBigContentView(remoteViews4).setGroup(str6);
        }
        Notification build2 = builder.build();
        build2.flags |= 16;
        build2.sound = defaultUri;
        build2.priority |= 2;
        build2.contentIntent = resultPendingIntent1(str, str6, str3, i, cls);
        getManager().notify(i, build2);
    }

    public PendingIntent resultPendingIntent1(String str, String str2, String str3, int i, Class cls) {
        Intent intent = set_intent1(this.context, str, i, str2, str3, cls);
        TaskStackBuilder create = TaskStackBuilder.create(this.context);
        create.addParentStack(cls);
        create.addNextIntent(intent);
        return create.getPendingIntent((int) System.currentTimeMillis(), 134217728);
    }

    public Intent set_intent1(Context context2, String str, int i, String str2, String str3, Class cls) {
        Intent intent = new Intent(context2, cls);
        intent.setFlags(67108864);
        intent.putExtra("message", str3);
        intent.putExtra("title", str2);
        intent.putExtra("idd", i);
        intent.putExtra("type", str);
        intent.putExtra("Noti_add", 1);
        return intent;
    }
}
