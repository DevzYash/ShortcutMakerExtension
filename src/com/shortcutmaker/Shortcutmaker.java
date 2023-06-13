package com.shortcutmaker;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutManagerCompat;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.net.Uri;
import androidx.core.graphics.drawable.IconCompat;
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;

import javax.management.Descriptor;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class Shortcutmaker extends AndroidNonvisibleComponent {
  private final Context context;
  public Shortcutmaker(ComponentContainer container) {
    super(container.$form());
    this.context = container.$context();
  }


  @SimpleFunction(description = "Returns the sum of the given list of integers.")
  public void ShowDynamicShortcutWithScreen (String icon,String icon2,String icon3,
                                             String shortLable,String shortlabel2,String shortlabel3,
                                             String longLabel,String longLabel2,String longLabel3,
                                             String ScreenName,
                                             String Startvalue,
                                             String url,String url2
  ){

      if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N_MR1) {
        InputStream bitmap = null;
        Bitmap bit;
        try {
          bitmap = this.context.getAssets().open(icon);
        } catch (IOException e) {
          e.printStackTrace();
        }

        bit = BitmapFactory.decodeStream(bitmap);
        Intent myintent = new Intent();
        myintent.setAction(Intent.ACTION_VIEW);
        myintent.setClassName(this.context.getPackageName(), this.context.getPackageName() + "."+ScreenName);
        myintent.putExtra(Startvalue,Startvalue);
        ShortcutManager shortcutManager = this.context.getSystemService(ShortcutManager.class);
        ShortcutInfo screenshortcut = new ShortcutInfo.Builder(this.context, "1")
                .setShortLabel(shortLable)
                .setLongLabel(longLabel)
                .setIcon(Icon.createWithBitmap(bit))
                .setIntent(myintent)
                .build();


        try {
          bitmap = this.context.getAssets().open(icon2);
        } catch (IOException e) {
          e.printStackTrace();
        }
        bit = BitmapFactory.decodeStream(bitmap);
        Intent intent2 = new Intent();
        intent2.setAction(Intent.ACTION_VIEW);
        intent2.setData(Uri.parse(url));
        ShortcutInfo webShortcut1 = new ShortcutInfo.Builder(this.context, "2")
                .setShortLabel(shortlabel2)
                .setLongLabel(longLabel2)
                .setIcon(Icon.createWithBitmap(bit))
                .setIntent(intent2)
                .build();

        try {
          bitmap = this.context.getAssets().open(icon3);
        } catch (IOException e) {
          e.printStackTrace();
        }
        bit = BitmapFactory.decodeStream(bitmap);
        Intent intent3 = new Intent();
        intent3.setAction(Intent.ACTION_VIEW);
        intent3.setData(Uri.parse(url2));
        ShortcutInfo webShortcut2 = new ShortcutInfo.Builder(this.context, "3")
                .setShortLabel(shortlabel3)
                .setLongLabel(longLabel3)
                .setIcon(Icon.createWithBitmap(bit))
                .setIntent(intent3)
                .build();



        shortcutManager.setDynamicShortcuts(Arrays.asList(screenshortcut,webShortcut1,webShortcut2));
      }}

  @SimpleFunction(description="")
  public void setpinnedShortcut(String icon
  ,String title,String longtitle,String url,String id){
    InputStream bitmap = null;
    Bitmap bit;
    try {
      bitmap = this.context.getAssets().open(icon);
    } catch (IOException e) {
      e.printStackTrace();
    }
    bit = BitmapFactory.decodeStream(bitmap);
    ShortcutInfoCompat shortcutInfo2 = new ShortcutInfoCompat.Builder(this.context.getApplicationContext(), id)
            .setShortLabel(title)
            .setLongLabel(longtitle)
            .setIcon(IconCompat.createFromIcon(Icon.createWithBitmap(bit)))
            .setIntent(new Intent(Intent.ACTION_VIEW,Uri.parse(url)))
            .build();
    ShortcutManagerCompat.requestPinShortcut(this.context.getApplicationContext(), shortcutInfo2, null);
    ShortcutManager shortcutManager = this.context.getSystemService(ShortcutManager.class);
  }


}

